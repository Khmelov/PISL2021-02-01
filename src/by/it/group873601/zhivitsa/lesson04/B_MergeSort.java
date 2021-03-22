package by.it.group873601.zhivitsa.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    int[] merge(int[] ar_1, int[] ar_2) {
        int max = ar_1.length + ar_2.length;
        int[] result = new int[max];
        int m = 0, n = 0;
        for (int i = 0; i < max; i++) {
            if (m >= ar_1.length & n < ar_2.length) {
                result[i] = ar_2[n];
                n++;
            } else if (n >= ar_2.length & m < ar_1.length) {
                result[i] = ar_1[m];
                m++;
            } else if (ar_1[m] <= ar_2[n] & m < ar_1.length) {
                result[i] = ar_1[m];
                m++;
            } else {
                result[i] = ar_2[n];
                n++;
            }
        }
        return result;
    }

    int[] mergeSort(int[] arr, int l, int r) {
        int[] result = new int[1];
        int index = (int) (l + r) / 2;
        if (l < r) {
            return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        } else {
            result[0] = arr[l];
            return result;
        }
    }

    int[] mySort(int[] leftPart, int[] rightPart, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex - 1) {
            return leftPart;
        }
        int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
        int[] leftSortedArray = mySort(leftPart, rightPart, leftIndex, middleIndex);
        int[] rightSortedArray = mySort(leftPart, rightPart, middleIndex, rightIndex);
        int[] result;
        int leftI = leftIndex, rightI = middleIndex, resI = leftIndex;
        if (leftSortedArray == leftPart) {
            result = rightPart;
        } else {
            result = leftPart;
        }
        while (leftI < middleIndex && rightI < rightIndex) {
            if (leftSortedArray[leftI] < rightSortedArray[rightI]) {
                result[resI++] = leftSortedArray[leftI++];
            } else {
                result[resI++] = rightSortedArray[rightI++];
            }
        }
        while (leftI < middleIndex) {
            result[resI++] = leftSortedArray[leftI++];
        }
        while (rightI < rightIndex) {
            result[resI++] = rightSortedArray[rightI++];
        }
        return result;
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

//        a = mergeSort(a, 0, a.length - 1);
        a = mySort(Arrays.copyOf(a, a.length), new int[a.length], 0, a.length);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }


}
