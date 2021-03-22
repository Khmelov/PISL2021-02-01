package by.it.group873602.kavetski.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        int size = 6*m+1;
        long [] pisano = new long[size];
        pisano[0] = 1;
        pisano[1] = 1;
        for (int i = 2; i < size; i++) {
            pisano[i] = ((pisano[i-1] + pisano[i-2]) % m);
            if (pisano[i] == 1 && pisano[i-1] == 0) {
                int index = (int) (n % i) - 1;
                return pisano[index];
            }
        }
        return 0L;
    }


}

