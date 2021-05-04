package by.it.group873602.artishevskiy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_EditDist {
    int getDistanceEdinting(String one, String two) {
        int[][] distances = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i < one.length() + 1; i++) {
            distances[i][0] = i;
        }

        for (int j = 0; j < two.length() + 1; j++) {
            distances[0][j] = j;
        }

        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                int cost = getDiff(one.charAt(i), two.charAt(j));
                distances[i + 1][j + 1] = getMin(distances[i][j + 1] + 1, distances[i + 1][j] + 1,
                        distances[i][j] + cost);
            }
        }

        int result = distances[one.length()][two.length()];

        return result;
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
        B_EditDist instance = new B_EditDist();
        try (Scanner scanner = new Scanner(stream)) {
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        }
    }

}
