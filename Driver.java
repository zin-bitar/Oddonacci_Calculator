import java.io.*;

public class Driver {
    public static void main (String[] args){

        File oddText = new File("OddoOut.txt");
        PrintWriter oddWriter;
        File timingText = new File("Timing.txt");
        PrintWriter timingWriter;
        try {
            oddWriter = new PrintWriter(new FileOutputStream(oddText));
            timingWriter = new PrintWriter(new FileOutputStream(timingText));

            int k = 0;
            for (int i = 0; i < 5; i++) {
                k +=5;
                long time = System.nanoTime();
                int[] linear = GoodOdd(k);
                long timeDifference = System.nanoTime()-time;
                oddWriter.print("Linear for " + k + ": " + linear[0] + "\n");
                timingWriter.print("linear " + k+" took: " + timeDifference + " nanoseconds\n" );
            }

            oddWriter.print("\n");
            timingWriter.print("\n");

            k = 0;
            for (int i = 0; i < 5; i++) {
                k +=5;
                long time2 = System.nanoTime();
                oddWriter.print("Ternary for " + k + ": " + BadOdd(k) + "\n");
                timingWriter.print("ternary " + k+" took: " + (System.nanoTime()-time2) + " nanoseconds\n");
            }

            timingWriter.close();
            oddWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return;
        }
    }
    //Ternary oddonacci
    static int BadOdd(int n){
        if(n<= 3) return 1;
        else{
            return BadOdd(n-1)+BadOdd(n-2)+BadOdd(n-3);
        }
    }
    //Linear oddonacci
    static int[] GoodOdd(int n){
        if(n==3) return new int[]{1,1,1};
        if(n==2) return new int[]{1,1,0};
        if(n==1) return new int[]{1,0,0};
        else{
            int[] arr = GoodOdd(n-1);
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];
            return new int[]{a+b+c,a,b};
        }
    }
}
