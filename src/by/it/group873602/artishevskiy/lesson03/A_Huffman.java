package by.it.group873602.artishevskiy.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {
    abstract class Node implements Comparable<Node> {
        private final int frequence;

        abstract void fillCodes(String code);

        private Node(int frequence) {
            this.frequence = frequence;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(frequence, n.frequence);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Node)) {
                return false;
            }
            Node node = (Node) o;
            return frequence == node.frequence;
        }

        @Override
        public int hashCode() {
            return Objects.hash(frequence);
        }
    }

    private class InternalNode extends Node {
        Node left;
        Node right;

        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof InternalNode)) {
                return false;
            }
            InternalNode inode = (InternalNode) o;
            return right == inode.right && left == inode.left;
        }

        @Override
        public int hashCode() {
            return Objects.hash(right, left);
        }
    }

    private class LeafNode extends Node {
        char symbol;

        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof LeafNode)) {
                return false;
            }
            LeafNode lnode = (LeafNode) o;
            return symbol == lnode.symbol;
        }

        @Override
        public int hashCode() {
            return Objects.hash(symbol);
        }
    }

    private static Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String s = scanner.next();

            char[] str = s.toCharArray();
            Map<Character, Integer> count = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (count.containsKey(str[i])) {
                    count.put(str[i], count.get(str[i]) + 1);
                } else {
                    count.put(str[i], 1);
                }
            }

            PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
            for (Map.Entry<Character, Integer> entry : count.entrySet()) {
                priorityQueue.add(new LeafNode(entry.getValue(), entry.getKey()));
            }

            while (priorityQueue.size() > 1) {
                Node node1 = priorityQueue.poll();
                Node node2 = priorityQueue.poll();
                priorityQueue.add(new InternalNode(node1, node2));
            }

            StringBuilder sb = new StringBuilder();
            priorityQueue.peek().fillCodes("");
            for (int i = 0; i < s.length(); i++) {
                sb.append(codes.get(s.charAt(i)));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        File f = new File(root + "/src/by/it/a_khmelev/lesson03/dataHuffman.txt");

        A_Huffman instance = new A_Huffman();
        String result = instance.encode(f);

        System.out.printf("%d %d%n", codes.size(), result.length());

        for (Map.Entry<Character, String> entry : codes.entrySet())
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());

        System.out.println(result);
    }
}
