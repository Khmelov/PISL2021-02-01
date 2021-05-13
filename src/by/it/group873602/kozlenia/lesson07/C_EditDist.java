package by.it.group873602.kozlenia.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


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
                levensteignDistances[i + 1][j + 1] = getMin(
                        levensteignDistances[i][j + 1] + 1,
                        levensteignDistances[i + 1][j] + 1,
                        levensteignDistances[i][j] + cost);
            }
        }

        int a = one.length();
        int b = two.length();

        while (a >= 1) {
            while (b >= 1) {
                int needToBeInsert = levensteignDistances[a][b - 1];
                int needToBeDelete = levensteignDistances[a - 1][b];
                int needToBeReplace = levensteignDistances[a - 1][b - 1];
                int minimum = getMin(
                        needToBeDelete,
                        needToBeInsert,
                        needToBeReplace);

                if (minimum == needToBeReplace) {
                    int cost = getDiff(one.charAt(a - 1), two.charAt(b - 1));
                    switch (cost) {
                        case 0: {
                            result.append("#,");
                        }
                        break;
                        case 1: {
                            result.append("~").append(two.charAt(b - 1)).append(",");
                        }
                        break;
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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
