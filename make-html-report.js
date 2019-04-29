// Node script created by Jonathan Hudson to generate test report documentation from json file labeled testData.json

/**
*	Json Format required
*	
*	{
*		name: appName,
*		tests: [],
*		time: 0,
*		failures: 0,
*		errors: 0,
*		assertions: 0,
*		skipped: 0,
*		failedTests: [],
*		numTests: 0,
*		longestTest: {time: 0},
*		mostTest: 0,
*		mostAssertations: 0,
*		numTests: 0,
*		failed: 0
*	}
*
*   tests is an array of either test suties or test case. Test suites have test and are formatted like above but test case have no tests field.
**/

var d3 = require("d3");
var { JSDOM } = require("jsdom");
var fs = require('fs');
var xmlserializer = require('xmlserializer');
var _ = require("lodash");
var { execSync } = require('child_process');
const isCI = require('is-ci');
const path = require('path');


var pieColors = d3.scaleOrdinal(["#4CAF50","#F44336"]);
var pie = d3.pie()
    .sort(null);

var arcPath = d3.arc()
    .outerRadius(50)
    .innerRadius(0);

var createPieChart = (data, width, height, name) => {
    let dom = new JSDOM(`<svg  width="${width}px" height="${height}px" xmlns="http://www.w3.org/2000/svg"><g></g></svg>`,  { runScripts: "dangerously" });
    dom.window.d3 = d3.select(dom.window.document); //get d3 into the dom
    var graph = dom.window.d3.select("g");
    graph.attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    var arc = graph.selectAll(".arc")
        .data(pie(data))
        .enter()
        .append("g")
        .attr("class", "arc");

    arc.append("path")
        .attr("d", arcPath)
        .attr("fill", (d, i) => { return d.value || i !== 1 ? pieColors(i) : "none"; });

    let svg = dom.window.d3.select("svg");
    //get svg source.
    var source = xmlserializer.serializeToString(svg.node());

    let link = `${name}.svg`
    fs.writeFileSync(link, source); //using sync to keep the code simple

    return link;
};

var createDoubleBarSvg = (data, width, height, name) => {

    let heigthRatio = 11/18;
    let yOffset = ((height * 7/18)/2)+2;
    let barHeight = height * heigthRatio;
    let widthPadding = width * 0.15;
    let max = data.max;
    let scale = d3.scaleLinear()
        .domain([0, max])
        .range([0, width - widthPadding]);

    let passedBarWidth = scale(data.passed);
    let failedBarWidth = scale(data.failed);

    let passedTextAnchor = "middle";
    let failedTextAnchor = "middle";
    let passedTextPosition, failedTextPosition;

    if((passedBarWidth/2) >= 25){
        passedTextPosition =  (passedBarWidth/2);
    }
    else{
        passedTextAnchor = "start";
        passedTextPosition = 0;
    }

    if(passedBarWidth + (failedBarWidth/2) < width * 0.85 - 25  && passedBarWidth + (failedBarWidth/2) >= 25){
        failedTextPosition = passedBarWidth + (failedBarWidth/2);
    }
    else if(passedBarWidth + (failedBarWidth/2) < width * 0.85 - 25){
        failedTextPosition = 0;
        failedTextAnchor = "start";
    }
    else{

        failedTextAnchor = "end";
        failedTextPosition = width * 0.85;
    }

    let passedText = data.passed ? `<text text-anchor="${passedTextAnchor}" x="${passedTextPosition}" y="12" fill="#4CAF50" style="font-size: 12px; font-family: 'Source Sans Pro', sans-serif;">${data.passed} passed</text>` : "";
    let failedText = data.failed ? `<text text-anchor="${failedTextAnchor}" x="${failedTextPosition}" y="12" fill="#F44336" style="font-size: 12px; font-family: 'Source Sans Pro', sans-serif;">${data.failed} failed</text>` : "";

    let svgString = `<svg width="${width}px" height="${height + 2}px" xmlns="http://www.w3.org/2000/svg">
    <g>
    <rect x="0" y="${yOffset}" width="${passedBarWidth}" height="${barHeight}" fill="#4CAF50"></rect>
    <rect x="${passedBarWidth}" y="${yOffset}" width="${failedBarWidth}" height="${barHeight}" fill="#F44336"></rect>
    ${passedText}${failedText}
    </g>
    </svg>`

    let link = `${name}.svg`;
    fs.writeFileSync(link, svgString); //using sync to keep the code simple

    return link;
};

var makeLineOverStackedBarChart = (name, data, xAccessor, yLineAccessor, yBottomBarAccessor, yTopBarAccessor, maxWidth, maxHeight) =>{

    let xList = data.list.map(element => xAccessor(element));
    let yLineList = data.list.map(element => yLineAccessor);

    let height = (data.list.length * 45) < maxHeight ? data.list.length * 45 : maxHeight;

    let width = 150;

    let padding = ((maxHeight - height - 40) / data.list.length)  || 0.08;

    let xScale = d3.scaleLinear()
        .domain([0, data.maxBarValue])
        .range([0,width]);

    let yLineScale = d3.scaleLinear()
        .domain([0, data.maxLineValue])
        .range([height, 50]);
    let yBarScale = d3.scaleBand()
        .domain(xList)
        .range([25, height])
        .padding("0.08");

    let colors = d3.scaleOrdinal(["#4CAF50","#F44336", "#ddd"]);


    console.log(height);

    let yLineAxis = d3.axisLeft(yLineScale);
    let yBarAxis = d3.axisTop(yBarScale);
    let xAxis = d3.axisTop(xScale)
        .tickSize(-(height + (yBarScale.bandwidth()/2) + (padding * data.list.length-2)/2  + padding/data.list.length))
        .ticks(2);

    var customXAxis = (g) => {
        g.call(xAxis);
        g.select(".domain").remove();
        g.selectAll(".tick:not(:first-of-type) line").attr("stroke", "#777").attr("stroke-dasharray", "2,2");
        g.selectAll(".tick text").attr("y", 4).attr("dx", 10);
    }

    let dom = new JSDOM(`<svg  width="${maxWidth}px" height="${maxHeight+40}px" xmlns="http://www.w3.org/2000/svg"><g></g></svg>`,  { runScripts: "dangerously" });
    dom.window.d3 = d3.select(dom.window.document); //get d3 into the dom
    var graph = dom.window.d3.select("g");

    graph.attr("transform", "translate(" + 350 +", 20)")

    let timeCharts = graph.selectAll(".time-chart")
        .data(data.list)
        .enter().append("g") // Uses the enter().append() method
        .attr("class", "time-chart") // Assign a class for styling
        .attr("transform",  function(d, i) {
                return "translate("
                + 170
                + ","
                + (yBarScale(xAccessor(d)) + (yBarScale.bandwidth()/2) + (padding * i)/2  + (padding * i)/data.list.length) //centers .time-charts with each rect element
                + ")";
            }
        );

    let pieTime = d3.pie().sort(null);

    let k = data.list[0];



    let arcPathGen = d3.arc()
    .outerRadius(15)
    .innerRadius(8);

    timeCharts.append("text")
      .attr("style", "font: 12px sans-serif;")
      .attr("transform", (d) => { return "translate(" + -520 + ",0)"; } )
      .attr("class", "name")
      .text(function(d) { return d.name; });

    timeCharts.append("text")
        .attr("style", "font: 12px sans-serif; fill: #4CAF50;")
        .attr("class", ".passedTime")
        .text(function(d) { return d.passedTime ? `${secondsToTimestamp(d.passedTime)} ` : ""; });

    timeCharts.append("text")
        .attr("style", "font: 12px sans-serif; fill: #F44336;")
        .attr("transform", (d) => { return "translate(" + 140 + ",0)"; } )
        .attr("class", "failedTime")
        .text(function(d) { return  d.failedTime ? `${secondsToTimestamp(d.failedTime)}` : ""; });


    let timePie = timeCharts.selectAll(".time-pie")
        .data(function(d) { return pieTime([d.passedTime, d.failedTime, data.maxLineValue-d.time]); })
        .enter().append("g")
        .attr("transform", (d) => { return "translate(" + 100 + ",0)"; } )
        .attr("class", "time-pie"); // Assign a class for styling


    timePie.append("path")
        .attr("d", arcPathGen)
        .attr("fill", (d, i) => { return colors(i); });

    let passedBars = graph.selectAll('.passedBar')
        .data(data.list)
        .enter().append("rect")
        .attr("class", "passedBar")
        .attr("y", (dataElement, i) => { return yBarScale(xAccessor(dataElement)) + (padding * i)/2 + (padding * i)/data.list.length; })
        .attr("x", 0)
        .attr('height', yBarScale.bandwidth())
        .attr("fill", "#4CAF50")
        .attr("width", (d) => { return xScale(yBottomBarAccessor(d))});

    let failedBars = graph.selectAll('.failedBar')
        .data(data.list)
        .enter().append("rect")
        .attr("class", "failedBar")
        .attr("y", (dataElement, i) => { return yBarScale(xAccessor(dataElement)) + (padding * i)/2 + (padding * i)/data.list.length; })
        .attr("x", (dataElement) => { return  xScale(yBottomBarAccessor(dataElement)); })
        .attr('height', yBarScale.bandwidth())
        .attr("fill", "#F44336")
        .attr("width", (d) => { return  xScale(yTopBarAccessor(d))});


    graph.append("g")
        .attr("class","x axis")
        .call(customXAxis);

    let svg = dom.window.d3.select("svg");
    //get svg source.
    var source = xmlserializer.serializeToString(svg.node());

    var markup = dom.window.document.documentElement.innerHTML;

    let html = `${name}.html`
    fs.writeFileSync(html, source);

    let link = `${name}.svg`
    fs.writeFileSync(link, source); //using sync to keep the code simple



    return link;
};

var makeDisplayBlock = (displayOptions) =>{

    //Create label for block
    let displayTitle = displayOptions.title ? `<div class="display-title ${displayOptions.title.classes.toString().replace(","," ")}">${displayOptions.title.text}</div>` : "";

    let showcaseTag = displayOptions.showcase.isTable ? `table` : `div`;

    //Get divided width of block if using table.
    let showcaseElementWidth = showcaseTag === `table` ? 90/displayOptions.showcase.content.length : 100;
    let showcaseContent = "";
    let showcaseElementTag = displayOptions.showcase.isList ? "tr" : showcaseTag === `table` ? "td" : "div";

    let showcaseElementStartTag = showcaseElementTag === "tr" ? `${showcaseElementTag}><td` : `${showcaseElementTag}`;
    let showcaseElementEndTag = showcaseElementTag === "tr" ? `td></${showcaseElementTag}` : `${showcaseElementTag}`;

    //Create and add individual elements to showcaseContent
    displayOptions.showcase.content.forEach(contentElement => {
        let showcaseData = contentElement.isImage ? `<image class="${contentElement.imageClasses.toString().replace(","," ")}" src="${contentElement.value}"></image>` : contentElement.value;
        let showcaseElement = `<${showcaseElementStartTag} class="showcase-text ${contentElement.classes.toString().replace(","," ")}" style="width:${contentElement.width ? contentElement.width : showcaseElementWidth}%">${showcaseData}</${showcaseElementStartTag}>`;
        showcaseContent += showcaseElement;
    });

    //Create the block element
    let showcaseBlock =
    `<${showcaseTag} class="showcase">
        ${showcaseContent}
    </${showcaseTag}>`;

    let mentionsBlock = "";

    if(displayOptions.mentions && displayOptions.mentions.length > 0){
        //Create mentions block
        let mentionsTag = `div`;
        let mentionContent = "";
        displayOptions.mentions.forEach(contentElement => {

            let mention = `<div class="mention-content ${contentElement.classes.toString().replace(","," ")}">${contentElement.value}</div>`
            mentionContent += mention;
        });

        mentionsBlock =
        `<div class="mention-text">
            ${mentionContent}
        </div>`
    }

    let widthDims = displayOptions.size.width ? `width:${displayOptions.size.width}` : "";
    let heightDims = displayOptions.size.height ? `height:${displayOptions.size.height}` : "";

    let displayDims = widthDims ? `style="${widthDims};${heightDims}"`: `style="${heightDims}"`;

    let displayBlock =
    `<div class="rectangle" ${displayDims}>
        ${displayTitle}
        ${showcaseBlock}
        ${mentionsBlock}
    </div>`

    return displayBlock;
}

var makeDisplayOptions = (title, isTable, content, mentions, dims, isList) => {
    let displayOptions = {
        title: title,
        showcase: {
            isTable: isTable,
            isList: isList,
            content: content,
        },
        mentions: mentions,
        size: {
            width: dims ? dims[0] : null,
            height: dims ? dims[1]: null
        }
    };
    return displayOptions;
}

var titleObj = (text, classes) => {
    return  {
        text: text,
        classes: classes || []
    }
};

var mentionsObj = (value, classes) => {
    return {
        value: value,
        classes: classes || []
    }
};

var contentObj = (value, classes, width, isImage, imageClasses) => {
    return {
        value: value,
        classes: classes || [],
        width: width,
        isImage: isImage,
        imageClasses: imageClasses || []
    }
};

var secondsToTimestamp = (time) => {
    let millseconds = "000";
    let hours = Math.floor(time /3600);
    let minutes = Math.floor((time % 3600) /60);
    let seconds = Math.floor(time % 60);
    let decimalPoint = time.toString().indexOf(".");
    if(decimalPoint > -1){
        let partialSecond = time.toString().substring(decimalPoint+1, decimalPoint+4);
        millseconds = partialSecond.concat(millseconds.substring(partialSecond.length));
    }

    hours = hours.toString().length > 1 ? hours : "0" + hours;
    minutes = minutes.toString().length > 1 ? minutes : "0" + minutes;
    seconds = seconds.toString().length > 1 ? seconds : "0" + seconds;
    return `${hours}:${minutes}:${seconds}:${millseconds}`;
};

var makeStatusOptions = (failed, asserts, errors, failures, displayClasses)=> {
    let classes = displayClasses || [];
    classes.push(failed ? "failure" : "success");
    let title = titleObj("Status");
    let isTable = false;
    let content = [contentObj(failed ? "Failed" : "Passed", classes)];
    let mentions = [mentionsObj(`Assertions: ${asserts}`, ["success"]), mentionsObj("/"), mentionsObj(`Errors: ${errors}`, ["failure"]), mentionsObj("/"), mentionsObj(`Failures: ${failures}`, ["failure"])];
    let displayOptions = makeDisplayOptions(title, isTable, content, mentions);

    return displayOptions;
};

var makeTotalTimeOptions = (time, displayClasses)=> {
    let classes = displayClasses || [];
    let title = titleObj("Total Time", classes);
    let isTable = false;
    let content = [contentObj(time, classes)];
    let displayOptions = makeDisplayOptions(title, isTable, content);

    return displayOptions;
};

var makeNumberOfTestOptions = (failed, totalTest, passedTest, failedTest, pageName, displayClasses)=> {
    let classes = displayClasses || [];
    let title = titleObj("Number of Test", classes);
    let isTable = true;
    let pieChart = createPieChart([passedTest, failedTest], 100, 100, pageName+"-pieChart");
    let passedPercentage = Math.round((passedTest/totalTest) * 100);
    classes.push(failed ? "failure" : "success");
    let content = [contentObj(totalTest, classes), contentObj(pieChart, classes, null, true), contentObj(passedPercentage+"%", classes)];
    let mentions = [mentionsObj(`Passed: ${passedTest} `, ["success"]), mentionsObj(" / "), mentionsObj(` Failed: ${failedTest}`, ["failure"])];
    let displayOptions = makeDisplayOptions(title, isTable, content, mentions, [810]);

    return displayOptions;
};

var makeFailedTestsOptions = (failedTests, displayClasses) => {
    let classes = displayClasses || [];
    classes.push("test-list");
    let title = titleObj("Failed Tests");
    let isTable = true;
    let isList = true;
    let content = failedTests.length ? [] : [contentObj("None")];
    failedTests.forEach((failedTest, i) =>{

        let name = failedTest.name;

        if(i === 3 && failedTests.length <= 5){
            name = `...and ${failedTests.length - 3} more`;
        }
        else if(i > 3){
            return;
        }
        content.push(contentObj(name, classes));
    });
    let displayOptions = makeDisplayOptions(title, isTable, content, null, null, isList);

    return displayOptions;
};

var makeLongestTestOptions = (longestTest, failed, displayClasses) => {
    let classes = displayClasses || [];
    classes.push(failed ? "failure" : "success");
    classes.push("test-list");
    let title = titleObj("Longest Test");
    let isTable = false;
    let content = [contentObj(longestTest.name, classes)];
    let displayOptions = makeDisplayOptions(title, isTable, content, null);

    return displayOptions;
};

var makeTestResultOptions = (label, failed, assertions, time, passedTest, failedTest, largestTest, displayClasses) => {

    let classes = displayClasses || [];
    let title = titleObj(label, classes);
    let isTable = true;
    let barData = {
        max: largestTest,
        passed: passedTest,
        failed: failedTest
    }
    assertions = assertions == 'undefined' || assertions == undefined ? 0 : assertions;
    let doubleBar = createDoubleBarSvg(barData, 414, 72, label+"-doubleBar");
    let details = `${assertions} assertions</br>${time}`;
    let content = [contentObj(failed ? "Failed" : "Passed", classes), contentObj(details, classes.concat("middle")), contentObj(doubleBar, classes, null, true)];
    let mentions = null;
    let displayOptions = makeDisplayOptions(title, isTable, content, mentions, [810,140]);

    return displayOptions;
};

var getOverViewDisplayBlock = (testData) =>{

    testData.assertions = testData.assertions == 'undefined' || testData.assertions == undefined ? 0 : testData.assertions;
    let statusDisplay = makeDisplayBlock(makeStatusOptions(testData.failed, testData.assertions, testData.errors, testData.failures, []));

    let timeDisplay = makeDisplayBlock(makeTotalTimeOptions(secondsToTimestamp(testData.time-0) , []));

    let noTestDisplay = makeDisplayBlock(makeNumberOfTestOptions(testData.failed, testData.numTests-0, testData.numTests-testData.failedTests.length, testData.failedTests.length, testData.name, []));

    let failedTestDisplay = makeDisplayBlock(makeFailedTestsOptions(testData.failedTests, []));

    let longestTestDisplay = makeDisplayBlock(makeLongestTestOptions(testData.longestTest, testData.longestTest.failed, []));

    let assertsAndTimeDisplay = testData.passedTime === undefined ? makeDisplayBlock(getAssertionsAndTimeDisplayOptions(testData, [])) : "";

    let overViewDisplayBlock =
    `${statusDisplay}
    ${timeDisplay}
    ${noTestDisplay}
    ${failedTestDisplay}
    ${longestTestDisplay}
    ${assertsAndTimeDisplay}`;

    return overViewDisplayBlock;
};

var getTestResultsDisplayBlock = (tests, testData) => {

    let testResultDisplay = ``;

    let accessor = tests[0].numTests ? "numTests" : "assertions"
    let maxBarValue =  testData.mostTest-0|| testData.mostAssertations-0;
    let failures = (test) => {
        return accessor === "numTests" ? test.failedTests.length : test.failed ? 1 : 0;
    };

    let passed = (test) => {
        return test[accessor] - failures(test);
    };

    tests.forEach(test => {
        testResultDisplay += makeDisplayBlock(makeTestResultOptions(test.name, test.failed, test.assertions, secondsToTimestamp(test.time), passed(test), failures(test), maxBarValue, ["details"]));
    });

    return testResultDisplay;
};

var getAssertionsAndTimeDisplayOptions = (testData, classes) => {

    let name = `${testData.name}-graph`;
    let height = 705;
    let width = 775;
    let xAccessor = (d) => { return d.name;};
    let yLineAccessor = (d) => { return d.time;};

    let yBottomBarAccessor = (d) => { return d.passed};
    let yTopBarAccessor = (d) => { return d.failures;};

    let maxBarValue = testData.mostTest-0;
    console.log(maxBarValue);

    let data = {
        maxBarValue: maxBarValue,
        maxLineValue: testData.longestTest.time,
        list: testData.tests
    };

    data.list.forEach(test => {
        test.passed = test.numTests - test.failures;
    });

    let graph = makeLineOverStackedBarChart(name, data, xAccessor, yLineAccessor, yBottomBarAccessor, yTopBarAccessor, width, height);

    classes = classes || [];
    let title = titleObj("Number of Test and Time", classes);
    let isTable = false;
    let content = [contentObj(graph, classes, null, true, ["big-graph"])];
    let mentions = null;
    let displayOptions = makeDisplayOptions(title, isTable, content, mentions, [width+65, height+80]);

    return displayOptions;
}

var testData = JSON.parse(fs.readFileSync('testData.json', 'utf8'));

var testReportTemplate = fs.readFileSync('test-report-template.txt', 'utf8');

var testReports = "";
var testDir = isCI ? "tests/" : "";

var recursiveTestReport = (testData, testReportTemplate) => {
    let appReportTemplate = testReportTemplate.replace("####", '</br>' + testData.name.replace(/\_/g, " "));

    let appDetailsReportTemplate = testReportTemplate.replace("####", '</br>' + testData.name.replace(/\_/g, " ") + " Details");

    let display = getOverViewDisplayBlock(testData);
    let details = getTestResultsDisplayBlock(testData.tests, testData);

    let overviewDom = new JSDOM(`${appReportTemplate}${display}</div></body>`, { runScripts: "dangerously" });

    let detailsDom = new JSDOM(`${appDetailsReportTemplate}${details}</div></body>`, { runScripts: "dangerously" });

    let overviewOutputLocation = `${testData.name}-test-report.html`;
    let detailsOutputLocation = `${testData.name}-details-test-report.html`;

    testReports += testDir + overviewOutputLocation + " " + testDir + detailsOutputLocation + " ";

    let overviewMarkup = overviewDom.window.document.documentElement.innerHTML;
    let detailsMarkup = detailsDom.window.document.documentElement.innerHTML;
    fs.writeFileSync(overviewOutputLocation, overviewMarkup); //using sync to keep the code simple
    fs.writeFileSync(detailsOutputLocation, detailsMarkup); //using sync to keep the code simple
    if(testData.tests[0].tests){

        testData.tests.forEach(test => recursiveTestReport(test, testReportTemplate));
    }
};

recursiveTestReport(testData, testReportTemplate);

if (isCI){
    execSync(`docker run -v /opt/semapp/outfield/tests/:/tests/ openlabs/docker-wkhtmltopdf --encoding 'UTF-8' --footer-line --footer-left 'MISSION FOCUS TESTING METRICS' --footer-right '[page]' --footer-font-size '10' --margin-top '0' --margin-bottom '15' --zoom 0.75  ${testReports}tests/${testData.name}-test-report.pdf`);
}
else {
    execSync(`wkhtmltopdf --encoding 'UTF-8' --footer-line --footer-left 'MISSION FOCUS TESTING METRICS' --footer-right '[page]' --footer-font-size '10' --margin-top '0' --margin-bottom '15' ${testReports}${testData.name}-test-report.pdf`);
}
