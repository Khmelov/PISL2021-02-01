package by.it.group873601.zhivitsa.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable{
        int start;
        int stop;

        Segment(int start, int stop){
            if (start > stop){
                this.start = stop;
                this.stop = start;
            }
            else {
                this.start = start;
                this.stop = stop;
            }
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            return Integer.compare(this.start, ((Segment)o).start);
        }
    }


    void qSort(Segment[] segments, int left, int right) {
        int midI = (left + right) / 2, i = left, j = right, leftMidI = midI, rightMidI = midI;
        Segment x = segments[midI];
        while (true) {
            while (i < leftMidI && segments[i].compareTo(x) <= 0) {
                if (segments[i].compareTo(x) == 0) {
                    leftMidI--;
                    swap(segments, i, leftMidI);
                } else {
                    i++;
                }
            }
            while (j > rightMidI && segments[j].compareTo(x) >= 0) {
                if (segments[j].compareTo(x) == 0) {
                    rightMidI++;
                    swap(segments, j, rightMidI);
                } else {
                    j--;
                }
            }
            if (i >= leftMidI && j <= rightMidI) {
                break;
            } else if (i >= leftMidI) {
                swap(segments, ++rightMidI, j);
                swap(segments, rightMidI, leftMidI);
                i = leftMidI;
                leftMidI++;
            } else if (j <= rightMidI) {
                swap(segments, --leftMidI, i);
                swap(segments, leftMidI, rightMidI);
                j = rightMidI;
                rightMidI--;
            } else {
                swap(segments, i, j);
                i++;
                j--;
            }
        }
        if (leftMidI > left) {
            qSort(segments, left, --leftMidI);
        }
        if (rightMidI < right) {
            qSort(segments, ++rightMidI, right);
        }
    }

    void swap(Segment[] mas, int l, int r){
        Segment tmp = mas[l];
        mas[l] = mas[r];
        mas[r] = tmp;
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


        qSort(segments, 0, segments.length - 1);
        int count;
        for (int i = 0; i < points.length; i++){
            count = 0;
            for (int j = 0; j < segments.length && points[i] >= segments[j].start; j++) {
                if (points[i] <= segments[j].stop){
                    count++;
                }
            }
            result[i] = count;
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }

    }

}
