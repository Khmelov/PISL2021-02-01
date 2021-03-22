package by.it.group873602.artishevskiy.lesson01;

import java.math.BigInteger;

public class FiboB {
    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d %n\t time=%d %n%n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        if (n < 2)
            return BigInteger.valueOf(n);

        BigInteger[] arr = new BigInteger[n + 1];
        arr[0] = BigInteger.valueOf(0);
        arr[1] = BigInteger.valueOf(1);

        for (int i = 2; i <= n; i++)
            arr[i] = arr[i - 1].add(arr[i - 2]);
        
            return arr[n];
    }
}
