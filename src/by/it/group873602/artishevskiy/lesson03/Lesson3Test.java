package by.it.group873602.artishevskiy.lesson03;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson3Test {
    String dataRoot = System.getProperty("user.dir") + "/src/by/it/a_khmelev/lesson03/";

    @Test
    public void A() throws Exception {
        File f = new File(dataRoot + "dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);
        boolean ok = result.equals("01001100100111");
        assertTrue("A failed", ok);
    }

    @Test
    public void B() throws Exception {
        File f = new File(dataRoot + "encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        boolean ok = result.equals("abacabad");
        assertTrue("B failed", ok);
    }

    @Test
    public void C() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "heapData.txt");
        C_HeapMax instance = new C_HeapMax();
        Long res = instance.findMaxValue(stream);
        boolean ok = (res == 500);
        assertTrue("C failed", ok);
    }

}
