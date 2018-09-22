import java.io.*;
import java.util.*;

public class Solution {
    
    static void maxProduct(int [] ar){
        long max=0;
        for(int i =1;i<ar.length-1;i++){
            long p= indexProduct(ar,i);
            if(max<p){
                max=p;
            }
        }
        System.out.println(max);
    }
    
    static long indexProduct(int [] ar, int i ){
        long product = left(ar,i) * right(ar,i);
        return product;
    }
    
    static long right(int [] ar, int i){
        for(int r=i+1;r<ar.length;r++){
            if(ar[r]>ar[i]){
                 return r+1;
            }
        }
        return 0;
    }
    
    static long left(int [] ar, int i){
        for(int l=i-1;l>=i/2;l--){
            if(ar[l]>ar[i]){
                return 1+l;
            }
        }
        return 0;
    }
    
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int [] ar = new int [N];
        for (int i = 0;i<N;i++){
            ar[i]=in.nextInt();
        }
        maxProduct(ar);
    }
}
