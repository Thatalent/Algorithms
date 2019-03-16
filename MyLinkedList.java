package datastructures;

import java.lang.reflect.Type;

public class MyLinkedList<T> {

    public MyLinkedList(int length){
        list = (T[]) new Object[length];
    }

    public MyLinkedList(){
        list =  (T[]) new Object[10];
    }

    private T[] list;

    private int elementCount;

    /**
     * Adds a value of type T to the end of the list
     *
     * @param object Value of type T added to end of list
     * @return true if the linked list has updated
     */
    public boolean add(T object){

        if(++elementCount>list.length){
            T[] tempList = list;
            list = (T[]) new Object[elementCount];
            for (int i = 0; i< tempList.length; i++){
                list[i] = tempList[i];
            }
        }
        list[elementCount-1] = object;

        return true;
    }

    public boolean remove(int element) {

        if (element > list.length-1 ){
            return false;
        }

        T[] tempList = list;
        list = (T[]) new Object[--elementCount];

        for (int i = 0; i < list.length-1; i++){
            if(i == element){
                ++i;
            }
            list[i] = tempList[i];
        }

        boolean value = this.add(list[0]);
        return true;
    }


}
