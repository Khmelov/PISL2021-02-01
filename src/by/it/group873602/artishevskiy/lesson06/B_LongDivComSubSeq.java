package by.it.group873602.artishevskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class B_LongDivComSubSeq {
    int getDivSeqSize(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            int[] m = new int[n];
            for (int i = 0; i < n; i++) {
                m[i] = scanner.nextInt();
            }

            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (m[i] % m[j] == 0 && array[j] + 1 > array[i]) {
                        array[i] = array[j] + 1;
                    }

                }
            }

            return Arrays.stream(array).max().getAsInt();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

}
