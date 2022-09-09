public class Fastduplicates {
    public static void main(String[] args) {

        int u = 0;
        int k = 100_000;
        int[] bencharray = { 100, 200, 400, 800, 1600, 3200 };
        for (int n : bencharray) {
            long t_total = 0;

            int[] array1 = Sort.sorted(n);
            int[] array2 = Sort.sorted(n);
            long t0 = System.nanoTime();
            for (int i = 0; i < k; i++) {

                u += Sort.fast_duplicate(array1, array2);

            }
            t_total += (System.nanoTime() - t0);
            
            System.out.println("average time for array of " + n + " length: " + (t_total / (double) k) + "\t amount of hits: " + ((double) u / (double) k));

        }

    }
}

