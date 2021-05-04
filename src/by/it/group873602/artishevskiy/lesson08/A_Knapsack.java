package by.it.group873602.artishevskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_Knapsack {

    int getMaxWeight(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            int w = scanner.nextInt();
            int n = scanner.nextInt();
            int gold[] = new int[n];
            for (int i = 0; i < n; i++) {
                gold[i] = scanner.nextInt();
            }

            int[] destination = new int[w + 1];

            for (int i = 1; i < w + 1; i++) {
                for (int j = 0; j < n; j++) {
                    if (gold[j] <= i) {
                        destination[i] = Math.max(destination[i], destination[i - gold[j]] + gold[j]);
                    }
                }
            }

            return destination[w];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
