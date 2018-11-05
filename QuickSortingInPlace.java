import java.io.*;
import java.util.*;

public class QuickSortingInPlace {

    static void printArray(int[] ar, int lo, int hi) {
         for(int h= 0; h<ar.length;h++){
            System.out.print(ar[h]+" ");
         }
           System.out.println("");
      }
    
    
    static void quickSort(int[] ar, int lo, int hi){
        
        int pa=hi;
        
        if (lo<hi){
            pa=partition(ar, lo, hi);
            printArray(ar, lo, hi);
            quickSort(ar, lo, pa-1);
            quickSort(ar, pa+1, hi);
        }
    }
    
    static int partition(int []ar, int lo, int hi){
        int pI= hi;
        int pV= ar[pI];
        int sI= lo;
        for (int i = lo; i<hi; i++){
            if(ar[i]<pV){
                int temp1= ar[i];
                ar[i]=ar[sI];
                ar[sI]= temp1;
                sI++;
            }
        }
        int temp= ar[hi];
        ar[hi]=ar[sI];
        ar[sI]=temp;
        return sI;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] ar = new int [n];
        for(int i=0;i<ar.length;i++){
            ar[i] = in.nextInt();
        }
        quickSort(ar, 0, n-1);
    }
}
