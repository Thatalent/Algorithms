package threads;


import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayProcessing implements Runnable {

    public void run(){

        boolean working = true;

        while(working){

            int currentIndex = index.getAndIncrement();

            if(currentIndex < array.length) {

                int oldValue = array[currentIndex];

                int newValue = superIntenseFunction(oldValue);

                array[currentIndex] = newValue;

                System.out.println(Thread.currentThread().getName()+": Changed the old value of " + oldValue + " at index " + currentIndex + " to a new value: " + newValue);
            }
                working = currentIndex + 1 < array.length;
        }

        System.out.println(Thread.currentThread().getName()+" is now ending");

    }

    static private int[] array;

    static private AtomicInteger index;

    public static void main(String[] args){

        Scanner input  = new Scanner(System.in);

        System.out.println("Please enter a the length of your array");

        int arrayLength = input.nextInt();

        index = new AtomicInteger(0);

        array = new int[arrayLength];

        for(int i= 0; i < arrayLength; i++){

            System.out.println("Please a number to populate the array");

            array[i]= input.nextInt();
        }

        new Thread(new ArrayProcessing(), "First Thread").start();
        new Thread(new ArrayProcessing(), "Second Thread").start();

        System.out.println("Started both threads");

        input.close();
    }

    private int superIntenseFunction(int value){
        return value *= value;
    }
}
