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
            timingWriter.print("For the linear model we can see that there are very differences in the runtimes. The first one is larger because " +
                    "of the warm-up behavior of the JVM. \nThe differences are also due to system noise and overhead.\n\nWe can still see that the linear model is way faster than the exponential one.\n\n" +
                    "I will not be running this code at n=200, since my computer is not a quantum computer.\n\n");

            int k = 0;
            for (int i = 0; i < 5; i++) {
                k +=5;
                long time = System.nanoTime();
                long[] linear = GoodOdd(k);
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
    static long BadOdd(int n){
        if(n<= 3) return 1;
        else{
            return BadOdd(n-1)+BadOdd(n-2)+BadOdd(n-3);
        }
    }
    //Linear oddonacci
    static long[] GoodOdd(int n){
        if(n==3) return new long[]{1,1,1};
        if(n==2) return new long[]{1,1,0};
        if(n==1) return new long[]{1,0,0};
        else{
            long[] arr = GoodOdd(n-1);
            long a = arr[0];
            long b = arr[1];
            long c = arr[2];
            return new long[]{a+b+c,a,b};
        }
    }
}
