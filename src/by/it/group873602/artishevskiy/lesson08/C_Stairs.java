package by.it.group873602.artishevskiy.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {
    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int stairs[] = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        int[] destination = new int[n];
        for (int i = 0; i < n; i++) {
            switch (i) {
                case 0:
                    destination[0] = stairs[0];
                    break;
                case 1:
                    destination[i] = Math.max(destination[0] + stairs[i], stairs[i]);
                    break;
                default:
                    destination[i] = Math.max(destination[i - 1] + stairs[i], destination[i - 2] + stairs[i]);
                    break;
            }
        }

        return destination[destination.length - 1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }

}
