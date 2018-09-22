import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        char [] s = S.toCharArray();
        Arrays.sort(s);
        int odds = 0;
        int nonpairs=0;
        char [] letters= { 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int j=0; j<26;j++){
            int l= 0;
            for (int i= 0; i<s.length;i++){
                if(s[i]==letters[j]){
                    l++;
                }
            }
            if (l%2==1 &&l!=1){
                    odds++;
                }
            if(l==1){
                nonpairs++;
            }
            }
        if (odds%2==0 && s.length%2==1&& nonpairs==1){
            System.out.print("YES");
        }
        else if (odds%2==1 && s.length%2==1 && nonpairs==0){
            System.out.print("YES");
        }
        else if (odds==0 && s.length%2==0 && nonpairs==0){
            System.out.print("YES");
        }
        else{
           System.out.print("NO");
       }
    }
} 
