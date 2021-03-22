package by.it.group873602.artishevskiy.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {
    String decode(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder result = new StringBuilder();
            Integer count = scanner.nextInt();

            Map<String, Character> map = new HashMap<>();

            for (int i = 0; i < count; i++) {
                String str = scanner.next();
                Character symbol = str.charAt(0);
                String code = scanner.next();
                map.put(code, symbol);
            }

            StringBuilder current = new StringBuilder();
            String str = scanner.next();

            for (char c : str.toCharArray()) {
                current.append(c);
                if (map.containsKey(current.toString())) {
                    result.append(map.get(current.toString()));
                    current.setLength(0);
                }
            }

            return result.toString();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        File f = new File(root + "/src/by/it/a_khmelev/lesson03/encodeHuffman.txt");

        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }

}
