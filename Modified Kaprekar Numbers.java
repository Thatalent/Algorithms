import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        int q = in.nextInt();
        boolean d =false;
        for(long i=p;i<=q;i++){
            long y =i*i;
            String I= String.valueOf(y);
            int T= I.length();
            String R = I.substring(T/2);
            String L = I.substring(0, T/2);
            int r = Integer.valueOf(R);
            if (T!=1){
                int l = Integer.valueOf(L);
                if (r+l==i&&l!=0)
                    {
                    System.out.print(i+" ");
                    d=true;
                }
               
            }
            else{ 
                if(r==i){
                    System.out.print(i + " ");
                    d=true;
                }
                }
            }
        if (d!=true){
        System.out.print("INVALID RANGE");
        }
    }
}
