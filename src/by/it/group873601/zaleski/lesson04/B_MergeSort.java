package by.it.group873601.zaleski.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    int[] merge(int[] ar_1, int[] ar_2){
        int max = ar_1.length + ar_2.length;
        int[] result = new int[max];
        int m = 0, n = 0;
        for (int i = 0; i < max; i++){
            if (m >= ar_1.length & n < ar_2.length){
                result[i] = ar_2[n];
                n++;
            }else if(n >= ar_2.length & m < ar_1.length){
                result[i] = ar_1[m];
                m++;
            }else if (ar_1[m] <= ar_2[n] & m < ar_1.length){
                result[i] = ar_1[m];
                m++;
            }else {
                result[i] = ar_2[n];
                n++;
            }
        }
        return result;
    }

    int[] mergeSort(int[] arr, int l, int r){
        int[] result = new int[1];
        int index = (int)(l + r) / 2;
        if (l < r){
           return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        }else {
            result[0] = arr[l];
            return result;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        //a = mergeSort(a, 0, a.length - 1);
        a = mySort(a);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
