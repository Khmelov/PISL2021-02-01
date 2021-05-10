package by.it.group873602.artishevskiy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_EditDist {
    int getDistanceEdinting(String one, String two) {

        int result = 0;

        int[][] distances = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                result = editDistTD(i, j, distances, one, two);
            }

        }

        return result;
    }

    int editDistTD(int i, int j, int[][] distances, String one, String two) {

        if (distances[i][j] == Integer.MAX_VALUE) {
            if (i == 0) {
                distances[i][j] = j;
            } else {
                if (j == 0) {
                    distances[i][j] = i;
                } else {
                    int insert = editDistTD(i, j - 1, distances, one, two) + 1;
                    int delete = editDistTD(i - 1, j, distances, one, two) + 1;
                    int replace = editDistTD(i - 1, j - 1, distances, one, two)
                            + getDiff(one.charAt(i - 1), two.charAt(j - 1));

                    distances[i][j] = getMin(insert, delete, replace);
                }
            }
        }
        return distances[i][j];
    }

    int getDiff(char one, char two) {
        return one != two ? 1 : 0;
    }

    int getMin(int one, int two, int three) {
        int min = -1;
        min = Math.min(two, one);
        min = Math.min(min, three);
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        try (Scanner scanner = new Scanner(stream)) {
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        }
    }
}
