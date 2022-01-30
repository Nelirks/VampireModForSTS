import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static final int ARRAY_SIZE = 32;
    static final int DIFF = 10;

    public static void main(String[] args) throws Exception {
        Random r = new Random();
        for (int i = 0; i < 10000; ++i) {
            if (! test(r)) System.out.println("ALED");
        }
    }

    private static boolean test(Random r) throws Exception {
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        a1.add(r.nextInt(DIFF));
        a2.add(r.nextInt(DIFF));
        for (int i = 1; i < ARRAY_SIZE; ++i) {
            a1.add(a1.get(i-1) + r.nextInt(DIFF));
            a2.add(a2.get(i-1) + r.nextInt(DIFF));
        }

        List<Integer> sorted = new ArrayList<>();
        sorted.addAll(a1);
        sorted.addAll(a2);
        sorted.sort(Integer::compare);

        return sorted.get(ARRAY_SIZE-1).equals(searchMediane(a1, a2));
    }

    private static int searchMediane(List<Integer> a1, List<Integer> a2) throws Exception {
        if (a1.size() != a2.size()) throw new Exception("Array de taille différente");
        int size = a1.size();
        if (size == 1) return Math.min(a1.get(0), a2.get(0));

        List<Integer> a1copy = new ArrayList<>();
        List<Integer> a2copy = new ArrayList<>();
        boolean a1IsLess = a1.get(size/2-1) < a2.get(size/2-1);

        for (int i = 0; i < size/2; ++i) {
            if(a1IsLess) a2copy.add(a2.get(i));
            else a1copy.add(a1.get(i));
        }
        for (int i = size/2; i < size; ++i) {
            if(a1IsLess) a1copy.add(a1.get(i));
            else a2copy.add(a2.get(i));
        }
        return searchMediane(a1copy, a2copy);
    }
}
