package by.it.group873602.artishevskiy.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    String dataRoot = System.getProperty("user.dir") + "/src/by/it/a_khmelev/lesson06/";

    @Test
    public void A() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        boolean ok = (result == 3);
        assertTrue("A failed", ok);
    }

    @Test
    public void B() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        boolean ok = (result == 3);
        assertTrue("B failed", ok);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        boolean ok = (result == 4);
        assertTrue("C failed", ok);
    }

}
