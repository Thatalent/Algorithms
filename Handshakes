import java.io.*;
import java.util.*;

public class Solution {

    static int handShake(int n){
        if(n!=0){
            if(n%2==0){
            return (n/2)*(n-1);
            }
            else
                return((n-1)/2)*(n-2)+(n-1);
        }
        else
            return 0;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int T=in.nextInt();
        
        for(int i=0;i<T;i++){
            int n=in.nextInt();
            System.out.println(handShake(n));
            
        }
    }
}
