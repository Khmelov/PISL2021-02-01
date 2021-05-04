package by.it.group873602.artishevskiy.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_EditDist {
    String getDistanceEdinting(String one, String two) {
        StringBuilder result = new StringBuilder();

        int[][] levensteignDistances = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i < one.length() + 1; i++) {
            levensteignDistances[i][0] = i;
        }

        for (int j = 0; j < two.length() + 1; j++) {
            levensteignDistances[0][j] = j;
        }

        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                int cost = getDiff(one.charAt(i), two.charAt(j));
                levensteignDistances[i + 1][j + 1] = getMin(levensteignDistances[i][j + 1] + 1,
                        levensteignDistances[i + 1][j] + 1, levensteignDistances[i][j] + cost);
            }
        }

        int a = one.length();
        int b = two.length();

        while (a >= 1) {
            while (b >= 1) {
                int needToBeInsert = levensteignDistances[a][b - 1];
                int needToBeDelete = levensteignDistances[a - 1][b];
                int needToBeReplace = levensteignDistances[a - 1][b - 1];
                int minimum = getMin(needToBeDelete, needToBeInsert, needToBeReplace);

                if (minimum == needToBeReplace) {
                    int cost = getDiff(one.charAt(a - 1), two.charAt(b - 1));
                    if (cost == 0) {
                        result.append("#,");
                    } else if (cost == 1) {
                        result.append("~").append(two.charAt(b - 1)).append(",");
                    }
                    a--;
                    b--;
                }
                if (minimum == needToBeDelete) {
                    result.append("-").append(one.charAt(a - 1)).append(",");
                    a--;
                } else {
                    if (minimum == needToBeInsert) {
                        result.append("+").append(two.charAt(b - 1)).append(",");
                        b--;
                    }
                }
            }
        }

        return result.toString();
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
        C_EditDist instance = new C_EditDist();
        try (Scanner scanner = new Scanner(stream)) {
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
            System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        }
    }

}
