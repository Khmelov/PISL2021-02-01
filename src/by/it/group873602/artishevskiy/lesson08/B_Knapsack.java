package by.it.group873602.artishevskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_Knapsack {
    int getMaxWeight(InputStream stream) {
        try (Scanner scanner = new Scanner(stream)) {
            int w = scanner.nextInt();
            int n = scanner.nextInt();
            int gold[] = new int[n];
            for (int i = 0; i < n; i++) {
                gold[i] = scanner.nextInt();
            }

            int[][] destination = new int[w + 1][n + 1];
            for (int i = 0; i < w + 1; i++) {
                destination[i] = new int[n + 1];
            }

            for (int i = 0; i < w; i++) {
                destination[i][0] = 0;
            }
            for (int j = 0; j < n; j++) {
                destination[0][j] = 0;
            }

            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < w + 1; j++) {
                    destination[j][i] = destination[j][i - 1];
                    if (gold[i - 1] <= j) {
                        destination[j][i] = Math.max(destination[j][i],
                                destination[j - gold[i - 1]][i - 1] + gold[i - 1]);
                    }
                }
            }

            return destination[w][n];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
