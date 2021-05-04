package by.it.group873603.Scheglov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {

    //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
    //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

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
                    int replace = editDistTD(i - 1, j - 1, distances, one, two) + getDiff(one.charAt(i - 1), two.charAt(j - 1));

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
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
