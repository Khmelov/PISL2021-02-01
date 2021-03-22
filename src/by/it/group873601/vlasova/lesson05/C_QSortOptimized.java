package by.it.group873601.vlasova.lesson05;

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
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start == o.start)
                return Integer.compare(this.stop, o.stop);
            return Integer.compare(this.start, o.start);
        }
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
        quicksort(segments, 0, n - 1);

        for (int i = 0; i < m; i++) {
            int l = 0;
            int r = n - 1;
            int res1 = 0;
            int res2 = 0;
            while (l <= r) {
                int mid = (l + r) / 2;
                while(mid + res1 <= r && segments[mid + res1].start <= points[i] && points[i] <= segments[mid + res1].stop) {
                    res1++;
                }
                while(mid - res2 - 1 >= l && segments[mid - res2  - 1].start <= points[i] && points[i] <= segments[mid - res2  - 1].stop) {
                    res2++;
                }
                if(res1 + res2 != 0)
                    break;
                if(segments[mid].start >= points[i])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            result[i] = res1 + res2;
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    void quicksort(Segment[] segments, int b, int e) {
        while (b < e) {
            int[] lr = partition(segments, b, e);

            if (lr[1] - b < e - lr[0]) {
                quicksort(segments, b, lr[1]);
                b = lr[0];
            } else {
                quicksort(segments, lr[0], e);
                e = lr[1];
            }
        }
    }

    int[] partition(Segment segments[], int b, int e) {
        int l = b - 1;
        int r = e;
        int p = b - 1;
        int q = e;

        Segment v = segments[e];

        while (true) {
            while (segments[++l].compareTo(v) < 0);
            while (segments[--r].compareTo(v) > 0)
                if (r == b)
                    break;
            if (l >= r)
                break;

            swap(segments, l, r);

            if (segments[l].compareTo(v) == 0)
                swap(segments, l, ++p);

            if (segments[r].compareTo(v) == 0)
                swap(segments, --q, r);
        }

        swap(segments, l, e);

        r = l - 1;
        for (int k = b; k <= p; k++, r--)
            swap(segments, k, r);


        l = l + 1;
        for (int k = e - 1; k >= q; k--, l++)
            swap(segments, l, k);

        return new int[]{l, r};
    }

    void swap(Segment[] arr, int i, int j) {
        Segment t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
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
