import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static public String toHour(int h, int m){
        if (m>30){
            h=h+1;
            if (h>12){
                h=1;
            }
        }
        switch(h){
            case 1: return "one";
            
            case 2: return "two";
            
            case 3: return "three";
           
            case 4: return "four";
           
            case 5: return "five";
                
            case 6: return "six";
        
            case 7: return "seven";
         
            case 8: return "eight";
          
            case 9: return "nine";
            
            case 10: return "ten";
         
            case 11: return "eleven";
            
            case 12: return "twelve";
         
        }
        return "";
    }
    
    static public String toMinutes(int m){
        if (m>30){
            return "to ";
            
        }
        else return "past ";
    }
    static public void toTime(int m, String h){
        String d= toMinutes(m);
        
        switch(m){
            case 0: System.out.println(h+" o' clock");
            break;
            case 59:
            case 1: System.out.println("one minute "+ d +h);
            break;
            case 58:
            case 2: System.out.println("two minutes "+d+h);
            break;
            case 57:
            case 3: System.out.println("three minutes "+d+h);
            break;
            case 56:
            case 4: System.out.println("four minutes "+d+h);
            break;
            case 55:
            case 5: System.out.println("five minutes "+d+h);
            break;
            case 54:
            case 6: System.out.println("six minutes "+d+h);
            break;
            case 53:
            case 7: System.out.println("seven minutes "+d+h);
            break;
            case 52:
            case 8: System.out.println("eight minutes "+d+h);
            break;
            case 51:
            case 9: System.out.println("nine minutes "+d+h);
            break;
            case 50:
            case 10: System.out.println("ten minutes "+d+h);
            break;
            case 49:
            case 11: System.out.println("eleven minutes "+d+h);
            break;
            case 48:
            case 12: System.out.println("twelve minutes "+d+h);
            break;
            case 47:
            case 13: System.out.println("thirteen minutes "+d+h);
            break;
            case 46:
            case 14: System.out.println("fourteen minutes "+d+h);
            break;
            case 45:
            case 15: System.out.println("quarter "+d+h);
            break;
            case 44:
            case 16: System.out.println("sixteen minutes "+d+h);
            break;
            case 43:
            case 17: System.out.println("seventeen minutes "+d+h);
            break;
            case 42:
            case 18: System.out.println("eighteen minutes "+d+h);
            break;
            case 41:
            case 19: System.out.println("nineteen minutes "+d+h);
            break;
            case 40:
            case 20: System.out.println("twenty minutes "+d+h);
            break;
            case 39:
            case 21: System.out.println("twenty one minutes "+d+h);
            break;
            case 38:
            case 22: System.out.println("twenty two minutes "+d+h);
            break;
            case 37:
            case 23: System.out.println("twenty three minutes "+d+h);
            break;
            case 36:
            case 24: System.out.println("twenty four minutes "+d+h);
            break;
            case 35:
            case 25: System.out.println("twenty five minutes "+d+h);
            break;
            case 34:
            case 26: System.out.println("twenty six minutes "+d+h);
            break;
            case 33:
            case 27: System.out.println("twenty seven minutes "+d+h);
            break;
            case 32:
            case 28: System.out.println("twenty eight minutes "+d+h);
            break;
            case 31:
            case 29: System.out.println("twenty nine minutes "+d+h);
            break;
            case 30: System.out.println("half "+ d+h);
            break;
        }
    }

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        
        int H = in.nextInt();
        int M= in. nextInt();
        
        String h= toHour(H, M);
        toTime(M,h);
    }
}
