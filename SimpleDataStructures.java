package datastructures;

import java.util.*;

public class SimpleDataStructures{

    public static void main(String[] args){

        int[] array = new int[]{36, 74, 56, 64, 87, 52};

        Stack<Integer> stack = new Stack<>();
        Queue<Character> queue = new PriorityQueue<>();
        Map<Character, Integer> map = new HashMap<>();
        String string = "";
        StringBuilder stringBuilder = new StringBuilder("");

        System.out.println("Adding elements to data structures");
        for(int i=0; i<array.length; i++){

            int element = array[i];
            char characterElement = (char)element;
            stack.push(element);
            queue.add(characterElement);
            string += element;
            map.put(characterElement, element);
            stringBuilder.append(characterElement);
        }

        System.out.println("Checking elements in structures");

        for(int i=0; i<array.length; i++){

            System.out.println("Found the value "+array[i]+" in array on iteration "+i);
            System.out.println("Found the value "+queue.poll()+" in queue on iteration "+i);
            System.out.println("Found the value "+stack.pop()+" in stack on iteration "+i);
            System.out.println("Found the value "+map.get(stringBuilder.charAt(i))+" in map on iteration "+i+" using the key "+stringBuilder.charAt(i));
            System.out.println("Found the value "+string.charAt(i)+" in string on index "+i+"\n");
        }
        System.out.println("The full string was: "+string);
        System.out.println("The full stringBuilder was: "+stringBuilder);
    }
}