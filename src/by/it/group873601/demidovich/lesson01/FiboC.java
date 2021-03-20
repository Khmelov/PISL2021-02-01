package by.it.group873601.demidovich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        List<Long> arr = new ArrayList<>();
        arr.add((long)0);
        arr.add((long)1);

        for(int i=2;i<=m*m-1;i++){
            arr.add((arr.get(i-1) + arr.get(i-2))%m);
        }
        int pisanoLength =0;
        for(int i=0; i < arr.size()-1;i++){
            if(arr.get(0) == arr.get(i) && arr.get(1) == arr.get(i+1) && i!=0)break;
            pisanoLength++;
        }
        return arr.get((int)n%pisanoLength);
    }



}

