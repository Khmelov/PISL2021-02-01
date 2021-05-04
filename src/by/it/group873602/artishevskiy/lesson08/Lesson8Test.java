package by.it.group873602.artishevskiy.lesson08;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    String dataRoot = System.getProperty("user.dir") + "/src/by/it/a_khmelev/lesson08/";

    @Test
    public void A() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res = instance.getMaxWeight(stream);
        assertEquals("A failed", res, 14);
    }

    @Test
    public void B() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res = instance.getMaxWeight(stream);
        assertEquals("B failed", res, 9);
    }

    @Test
    public void C() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        assertEquals("C failed", res, 3);
    }

}
