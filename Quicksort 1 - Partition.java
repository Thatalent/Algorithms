import java.io.*;
import java.util.*;

public class Solution {
    static void printArray(List<Integer> arr) {
        for(int n: arr){
            System.out.print(n+" ");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        ArrayList <Integer> arr = new ArrayList<Integer>();
        int r=0;
        for(int i=1;i<n;i++){
            int ar=in.nextInt();
            if(p>ar){
                System.out.print(ar+" ");
            }
            else{
                arr.add(r,ar);
                r++;
            }
        }
        System.out.print(p +" ");
        printArray(arr);
    }
}
