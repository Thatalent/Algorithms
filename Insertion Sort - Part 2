import java.io.*;
import java.util.*;

public class Solution {

    //Prints line of array
    public static void printLine (int [] ar, int s){
        for (int k =0;k<s;k++){
            System.out.print(ar[k] + " ");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s= in.nextInt();
        int [] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        boolean b=false;
        int p = 1;
        while (!b){
            //Compares values of array based on length of array to the parameter being checked
            for (int j =0;j<p;j++){                
                if (ar[p]<ar[j]){
                    for(int e =p;e>j;e--)
                    {
                        int temp= ar[e-1];
                        ar[e-1]=ar[e];
                        ar[e]=temp;
                    }
                }
            }
            printLine(ar,s);                
            p++;
            if(p==s){
                b=true;
                }
        }        
    }
}
