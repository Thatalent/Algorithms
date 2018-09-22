import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        int t = in.nextInt();
        String T=in.nextLine();
        for(int i = 0;i<t;i++)
        {
            String w= in.nextLine();
            char [] W=w.toCharArray();
            ArrayList<Character> M = new ArrayList<Character>();
            boolean great=false;
            char [] B = new char [W.length];
            for(int j=W.length-2;j>=0;j--){
                //checks for next lexicographical string
                if(!great){
                M.add(W[j+1]);
                Collections.sort(M);
              test:  for(int k = 0;k<M.size();k++){                
                    if(M.get(k)>W[j]&&!great){
                        B[j]=M.get(k);
                        M.remove(k);
                        M.add(W[j]);
                        Collections.sort(M);
                        int q = j+1;
                        for(int p=0;p<M.size();p++){
                            B[q]=M.get(p);
                            q++;
                        }
                        great=true;
                        break test;
                    }
                }
                }
                //end of next lexicogrhical string test
                    
                else
                    {
                    B[j]=W[j];
                }
                
            }
            if (great)
            System.out.println(B);
            else
            {
                    System.out.println("no answer");
                }
            }
                
        }            
    }
