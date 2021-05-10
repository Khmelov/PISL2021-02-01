package by.it.group873602.artishevskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_LongNotUpSubSeq {
    int getNotUpSeqSize(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            int[] m = new int[n];
            for (int i = 0; i < n; i++) {
                m[i] = scanner.nextInt();
            }
            int result = 0;

            int[] arrayD = new int[n];
            int[] prev = new int[n];
            for (int i = 0; i < n; i++) {
                arrayD[i] = 1;
                prev[i] = -1;
                for (int j = 0; j < i; j++) {
                    if (m[i] <= m[j] && (arrayD[j] + 1) > arrayD[i]) {
                        arrayD[i] = arrayD[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            result = Arrays.stream(arrayD).max().getAsInt();

            int[] arrayIndexes = new int[result];
            int k = 0;
            for (int i = 1; i < n; i++)
                if (arrayD[i] > arrayD[k]) {
                    k = i;
                }
            int j = k - 1;
            while (k >= 0) {
                arrayIndexes[j] = k + 1;
                j--;
                k = prev[k];
            }
            System.out.println(Arrays.toString(arrayIndexes));

            return result;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}
