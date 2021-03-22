package by.it.group873602.golev.lesson01;

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
        long[] p = new long[6 * m + 1];
        p[0] = 1;
        p[1] = 1;
        for (int i = 2; i < 6 * m + 1; i++) {
            p[i] = (p[i - 1] + p[i - 2]) % m;
            if (p[i] == 1 && p[i - 1] == 0) {
                int ind = (int) (n % i) - 1;
                return p[ind];
            }
        }
        return 0L;
    }

}

