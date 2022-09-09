import java.util.Random;

public class Sort {
    public static void main(String[] args){

        bench();
    }

    public static int fast_duplicate( int[] array, int[] key){
        int amount_of_hits = 0;
        int i = 0;
        int j = 0;
        while(i < key.length-1 && j < array.length-1){
        if(key[i] == array[j]){
            amount_of_hits++;
            i++;
            j++;
            continue;
        }
        if(key[i] < array[j]){
            i++;
            continue;
        }
        if(key[i] > array[j]){
            j++;
            continue;
        }
    }
    return amount_of_hits;
    }

    public static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = 0;
        for (int i = 0; i < n; i++) {
            nxt += rnd.nextInt(10) + 1;
            array[i] = nxt;
        }
        return array;
    }

    public static boolean search_unsorted(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;

        while (true) {
            // jump to the middle
            int index = ((last + first) / 2);
            if (array[index] == key) {
                // System.out.println("win");
                return true;
            }
            if (array[index] < key && index < last) {
                // The index position holds something that is less than
                // what we're looking for, what is the first possible page?
                first = index + 1;

                // System.out.println("to small");
                continue;
            }
            if (array[index] > key && index > first) {
                // The index position holds something that is larger than
                // what we're looking for, what is the last possible page?
                last = index - 1;

                // System.out.println("to big");
                continue;
            } else
                break;
        }
        return false;
    }

    public static void bench() {
        int[] bencharray = { 100, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300,1400,1500,1600,1700,1800,1900,2000 };
        int k = 1_000_000;

        Random rnd = new Random();
        long timestart = 0;
        long timeend = 0;
        long timesum = 0;
        long medianvalue = 0;
        long[] timearray = new long[k];

        System.out.println("\n\n Searchtimes for sorted array using binary search");
        for (int n : bencharray) {

            for (int i = 0; i < k; i++) {

                int[] sortedarray = sorted(n);
                int key = rnd.nextInt(n);

                timestart = System.nanoTime();
                binary_search(sortedarray, key);
                timeend = System.nanoTime();

                timesum += timeend - timestart;

                timearray[i] = timeend - timestart;

                if (i == (k - 1)) {
                    medianvalue = medianvalue(timearray);
                }

            }

            System.out.println("Size of array: " + n + "\t" + " median runtime: " + (medianvalue)
                    + " nanoseconds \t average runtime: " + (timesum / k));
            timesum = 0;
        }

        System.out.println("\n\n searchtimes for unsorted arrays using a simple for-loop");
        for (int n : bencharray) {

            for (int j = 0; j < k; j++) {
                int key = rnd.nextInt(n);
                int[] unsorted = new int[n];

                for (int i = 0; i < n; i++) {
                    unsorted[i] = rnd.nextInt(n);
                }

                timestart = System.nanoTime();
                search_unsorted(unsorted, key);
                timeend = System.nanoTime();

                timesum += timeend - timestart;

                timearray[j] = timeend - timestart;

                if (j == (k - 1)) {
                    medianvalue = medianvalue(timearray);
                }

            }

            System.out.println("Size of array: " + n + "\t" + " median runtime: " + (medianvalue)
                    + " nanoseconds \t average runtime: " + (timesum / k));
        }

    }

    public static long medianvalue(long[] array) {
        long medianvalue = 0;
        if (array.length % 2 == 0) {
            medianvalue = array[(array.length - 1) / 2];
        } else {
            medianvalue = array[array.length / 2];
        }

        return medianvalue;
    }
}
