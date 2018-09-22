import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int r= in.nextInt();
        int c =in.nextInt();
        
        if(r%2==0){
            r=r/2-1;
            switch(c){
                case 1: c=1;
                break;
                case 2: c=3;
                break;
                case 3: c=5;
                break;
                case 4: c=7;
                break;
                case 5: c=9;
                break;
            }
        }
        else{
            r=r/2;
             switch(c){
                case 1: c=0;
                break;
                case 2: c=2;
                break;
                case 3: c=4;
                break;
                case 4: c=6;
                break;
                case 5: c=8;
                break;
            }
        }
        if(r!=0){
        String R=Integer.toString(r);
        String C=Integer.toString(c);
        System.out.println(R+C);
        }
        else{
            System.out.println(c);
        }
        
    }
}
