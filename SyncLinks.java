package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SyncLinks {

    public static void main(String[] args){

        Queue<String> link = new LinkedList<>();

        String [] stringArr = {"This", "is", "just", "some", "random", "text"};

        Scanner input = new Scanner(System.in);

        System.out.println("Do you want to add your own random text?");

        String addText = input.nextLine();

        if(addText.equalsIgnoreCase("yes")){

            System.out.println("How much text do you want to add?");

            int noText = Integer.parseInt(input.nextLine());

            stringArr = new String[noText];

            for(int i = 0; i < noText; i++){

                System.out.println("Enter text: ");
                String text = input.nextLine();

                stringArr[i] = text;
            }
        }

        LinkWorker addWorker = makeWorker(true, stringArr, link);
        LinkWorker printWorker = makeWorker(false, stringArr, link);
        LinkWorker weirdWorker =  makeWorker(true, new String[]{"I", "am", "a", "weird", "worker", "that", "works!"}, link);

        new Thread(addWorker).start();
        new Thread(printWorker).start();
//        new Thread(printWorker).start();
        new Thread(weirdWorker).start();
        new Thread(addWorker).start();
        new Thread(addWorker).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    synchronized (this) {
                        this.wait(10000);
                    }
                } catch (InterruptedException e) {

                }
                finally {
                    addWorker.work = false;
                    printWorker.work = false;
                    weirdWorker.work = false;
                }
            }
        }).start();

    }

    public static LinkWorker makeWorker(boolean add, String [] text, Queue<String> link){

        LinkWorker runner = new LinkWorker();
        runner.add = add;
        runner.work = true;
        runner.randomText = text;
        runner.link = link;
        return runner;
    }


    private static class LinkWorker implements Runnable {


        Queue<String> link;

        boolean add;

        boolean work;

        String[] randomText;

        @Override
        public void run() {

            while (work){
                synchronized (this ) {
                    if(add) {
                        this.addString(link);
                    }
                    else {
                        this.popAndPrint(link);
                    }

                    try {
                        this.wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private synchronized void addString(Queue<String> link){

            link.add(randomText[(int)(Math.random()*randomText.length)]);

        }

        private synchronized void popAndPrint(Queue<String> link){

            System.out.println(link.poll());
        }


    }
}
