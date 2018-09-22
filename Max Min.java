import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// The part of the program involving reading from STDIN and writing to STDOUT has been provided by us.

public class Solution {
    
   public static void main(String[] args) throws NumberFormatException, IOException {

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(in.readLine());
      int K = Integer.parseInt(in.readLine());
      int[] list = new int[N];

      for(int i = 0; i < N; i ++)
         list[i] = Integer.parseInt(in.readLine());
      
      int unfairness = Integer.MAX_VALUE;
      int i = 0;
       Arrays.sort(list);
       while (i + K - 1 <= N - 1){
           
           int min = list[i];
           int max = list[i + K - 1];
                   if ( max-min <= unfairness){
                       unfairness = max - min;}
           i++;
               }
          System.out.println(unfairness);
   }
}    
     
