import java.io.*;
import java.util.*;

public class Solution {

   static void printArray(int[] ar) {
         for(int h= 0; h<ar.length;h++){
            System.out.print(ar[h]+" ");
         }
           System.out.println("");
      }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] ar= new int [n]; 
        int [] array= new int [100];
        for(int i=0;i<n;i++){
            ar[i]=in.nextInt();
        }
        for(int i=0;i<100;i++){
            int a = 0;
            for( int j = 0;j<n;j++){
                if (ar[j]==i){
                    a++;
                }                
            }
            array[i]=a;
        }
        printArray(array);
    }
}
