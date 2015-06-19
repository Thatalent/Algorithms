import java.util.*;
import java.lang.reflect.Array;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {
         static boolean [] Pangram = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};

    public static String pangram (String s){
        char [] l = s.toCharArray();
      
        boolean letter;
        
        for (int i =0; i<l.length; i++)
        {
            switch (l[i]){
                case 'a':
                case 'A': Array.set(Pangram, 0, true);
                break;
                case 'b':
                case 'B': Array.set(Pangram, 1, true);
                break;
                case 'c':
                case 'C': Array.set(Pangram, 2, true);
                break;
                case 'd':
                case 'D': Array.set(Pangram, 3, true);
                break;
                case 'e':
                case 'E': Array.set(Pangram, 4, true);
                break;
                case 'f':
                case 'F': Array.set(Pangram, 5, true);
                break;
                case 'g':
                case 'G': Array.set(Pangram, 6, true);
                break;
                case 'h':
                case 'H': Array.set(Pangram, 7, true);
                break;
                case 'i':
                case 'I': Array.set(Pangram, 8, true);
                break;
                case 'j':
                case 'J': Array.set(Pangram, 9, true);
                break;
                case 'k':
                case 'K': Array.set(Pangram, 10, true);
                break;
                case 'l':
                case 'L': Array.set(Pangram, 11, true);
                break;
                case 'm':
                case 'M': Array.set(Pangram, 12, true);
                break;
                case 'n':
                case 'N': Array.set(Pangram, 13, true);
                break;
                case 'o':
                case 'O': Array.set(Pangram, 14, true);
                break;
                case 'p':
                case 'P': Array.set(Pangram, 15, true);
                break;
                case 'q':
                case 'Q': Array.set(Pangram, 16, true);
                break;
                case 'r':
                case 'R':Array.set(Pangram, 17,true);
                break;
                case 's':
                case 'S': Array.set(Pangram, 18, true);
                break;
                case 't':
                case 'T': Array.set(Pangram, 19, true);
                break;
                case 'u':
                case 'U':Array.set(Pangram, 20, true);
                break;
                case 'v':
                case 'V': Array.set(Pangram, 21, true);
                break;
                case 'w':
                case 'W': Array.set(Pangram, 22, true);
                break;
                case 'x':
                case 'X': Array.set(Pangram, 23, true);
                break;
                case 'y':
                case 'Y': Array.set(Pangram, 24, true);
                break;
                case 'z':
                case 'Z': Array.set(Pangram, 25, true);
                break;
                default: break;
            }
        }
            for (int j = 0;j<Pangram.length-1;j++)
                if (Pangram[j] != true){
                return ("not pangram");}
        return ("pangram");
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        String Results = pangram(s);
        System.out.println(Results);
    }
}
