import java.io.*;
import java.util.*;

public class Solution {
    
    static void quickSort(int[] ar, int lo, int hi){
        
        int pa=lo;
        
        if (lo<hi){
            pa=partition(ar, lo, hi);
            quickSort(ar, lo, pa-1);
            quickSort(ar, pa+1, hi);
        }
        printArray(ar, lo, hi);
    }
    
    static int partition(int[] ar,int lo, int hi) {
        int p=ar[lo];
        int[] copy=Arrays.copyOf(ar, ar.length);
        int c=lo;
        for(int i=lo+1;i<hi+1;i++){
            if(copy[i]<=p){
                ar[c]=copy[i];
                c++;
            }
        }
        ar[c]=p;
        int point = c;
        c++;
        for(int j=lo;j<hi+1;j++){
            if(copy[j]>p){
                ar[c]=copy[j];
                c++;
            }
        }
        return point;
    }
    
 
    
     static void printArray(int[] ar,int lo,int hi) {
         if (lo>= hi)
             return;
         for(int h= lo; h<hi+1;h++){
            System.out.print(ar[h]+" ");
         }
           System.out.println("");
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
