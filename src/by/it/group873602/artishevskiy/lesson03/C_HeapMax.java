package by.it.group873602.artishevskiy.lesson03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class C_HeapMax {
    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) {
            while (2 * i + 1 < heap.size()) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int j = left;
                if (right < heap.size() && heap.get(right) > heap.get(left))
                    j = right;
                if (heap.get(i) >= heap.get(j))
                    break;
                Collections.swap(heap, i, j);
                i = j;
            }

            return i;
        }

        int siftUp(int i) {
            while (heap.get(i) > heap.get((i - 1) / 2)) {
                Collections.swap(heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }

            return i;
        }

        void insert(Long value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() {
            Long result = heap.get(0);
            heap.remove(0);
            siftDown(0);
            return result;
        }

    }

    Long findMaxValue(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            Long maxValue = 0L;
            MaxHeap heap = new MaxHeap();

            int i;
            Integer count = scanner.nextInt();

            for (i = 0; i < count; i++) {
                String s = scanner.nextLine();
                if (s.equalsIgnoreCase("extractMax")) {
                    Long res = heap.extractMax();
                    if (res != null && res > maxValue)
                        maxValue = res;
                    System.out.println();
                } else if (s.contains(" ")) {
                    String[] p = s.split(" ");
                    if (p[0].equalsIgnoreCase("insert"))
                        heap.insert(Long.parseLong(p[1]));
                } else {
                    i--;
                }
            }

            return maxValue;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson03/heapData.txt");

        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }
}
