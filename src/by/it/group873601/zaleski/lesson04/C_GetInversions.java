package by.it.group873601.zaleski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {
    ///////////////////////////////////////////////////////////////////////////////////////////////
    int count = 0;
    int[] myMerge(int[] array1, int[] array2) {
        int[] array = new int[array1.length + array2.length];
        int n = 0, m = 0;
        for(int i = 0; i < array.length; i++) {
            if(n == array1.length) {
                array[i] = array2[m];
                m++;
            } else if(m == array2.length) {
                array[i] = array1[n];
                n++;
            } else if(array1[n] <= array2[m]) {
                array[i] = array1[n];
                n++;
            } else {
                array[i] = array2[m];
                m++;
                count += array1.length - n;
            }
        }
        return array;
    }
    int[] mySort(int[] array) {
        if(array.length == 1) return array;
        else {
            int[] array1 = new int[array.length/2];
            System.arraycopy(array, 0, array1, 0, array1.length);
            int[] array2 = new int[array.length - array1.length];
            System.arraycopy(array, array1.length, array2, 0, array2.length);
            return myMerge(mySort(array1), mySort(array2));
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(mySort(a)));
        result = count;






        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
