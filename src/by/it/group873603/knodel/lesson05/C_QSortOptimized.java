package by.it.group873603.knodel.lesson05;

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
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Integer) {
                return Integer.compare(this.start, (int) o);
            }
            return -1;
        }
    }

    void sort(Segment[] input, int left, int right) {
        if (right > left) {
            return;
        }

        if (input[left].compareTo(input[right]) > 0) {
            swap(input, left, right);
        }

        Segment p = input[left];
        Segment q = input[right];

        int lt = left + 1;
        int gt = right - 1;
        int k = lt;

        while (k <= gt) {
            if (input[k].compareTo(p) < 0) {
                swap(input, k, lt++);
            }
            else if (input[k].compareTo(q) >= 0) {
                while (input[gt].compareTo(q) > 0 && k < gt) {
                    --gt;
                }
                swap(input, k, gt--);

                if (input[k].compareTo(p) < 0) {
                    swap(input, k, lt++);
                }
            }
            ++k;
        }
        lt--;
        gt++;

        swap(input, left, lt);
        swap(input, right, gt);

        sort(input, left, --lt);
        sort(input, ++lt, --gt);
        sort(input, ++gt, right);
    }

    private void swap(Segment[] input, int i, int j) {
        Segment temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    int count(Segment[] input, int value, int length) {
        int i = 0;
        int j = 0;

        i = first(input, 0, length - 1, value, length);

        if(i == -1) {
            return i;
        }

        j = last(input, i, length - 1, value, length);

        return j - i + 1;
    }

    int first(Segment[] input, int low, int high, int value, int length) {
        if(high >= low) {
            int mid = (low + high) % 2 == 0 ? (low + high) / 2 : ((low + high) / 2) + 1;

            if((mid == 0 || value > input[mid - 1].start && value > input[mid - 1].stop) && (value >= input[mid].start && value <= input[mid].stop)) {
                return mid;
            }
            else if(value > input[mid].start && value > input[mid].stop) {
                return first(input, (mid + 1), high, value, length);
            }
            else {
                return first(input, low, (mid - 1), value, length);
            }
        }
        return -1;
    }

    int last(Segment[] input, int low, int high, int value, int length) {
        if(high >= low) {
            int mid = (low + high) % 2 == 0 ? (low + high) / 2 : ((low + high) / 2) + 1;

            if((mid == length - 1 || value < input[mid + 1].start && value < input[mid + 1].stop) && (value >= input[mid].start && value <= input[mid].stop)) {
                return mid;
            }
            else if(value < input[mid].start && value < input[mid].stop) {
                return last(input, low, (mid - 1), value, length);
            }
            else {
                return last(input, (mid + 1), high, value, length);
            }
        }
        return -1;
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

        sort(segments, 0, segments.length - 1);

        for (int i = 0; i < points.length; i++) {
            int count = count(segments, points[i], segments.length);
            result[i] = count == -1 ? 0 : count;
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
