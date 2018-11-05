
import java.util.Scanner;

public class LongDividson {

    public static void main(String[] args){

        String dividend = "", divisor="", remainder="", answer ="";

        System.out.println("Input dividend: ");

        Scanner input = new Scanner(System.in);

        dividend = Integer.toString(input.nextInt());
        System.out.println("Input divisor: ");

        divisor = Integer.toString(input.nextInt());


        for(int index = 0; index < dividend.length(); index++){

            int current_dividend=0, current_answer=0;

            current_dividend = Integer.parseInt(String.valueOf(dividend.charAt(index)));

            remainder= remainder.concat(Integer.toString(current_dividend));

            current_dividend = Integer.parseInt(remainder);

            current_answer = current_dividend/Integer.parseInt(divisor);

            remainder = Integer.toString(current_dividend%Integer.parseInt(divisor));

            answer = answer.concat(Integer.toString(current_answer));
        }

            String float_answer = "";

            float_answer = Float.toString(Float.parseFloat(remainder)/Float.parseFloat(divisor)).replace("0", "");

            answer = answer.concat(float_answer);

            System.out.println(answer);
    }
}
