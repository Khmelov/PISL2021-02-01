package by.it.group873602.artishevskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class A_LIS {
    int getSeqSize(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            int[] m = new int[n];
            for (int i = 0; i < n; i++) {
                m[i] = scanner.nextInt();
            }

            return LISBottomUp(m);
        }
    }

    int LISBottomUp(int[] array) {
        int[] arrayD = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayD[i] = 1;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] < array[i] && arrayD[j] + 1 > arrayD[i]) {
                    arrayD[i] = arrayD[j] + 1;
                }
            }
        }
        return Arrays.stream(arrayD).max().getAsInt();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }
}
