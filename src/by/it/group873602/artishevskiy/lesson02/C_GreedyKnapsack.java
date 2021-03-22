package by.it.group873602.artishevskiy.lesson02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" + "cost=" + cost + ", weight=" + weight + '}';
        }

        @Override
        public int compareTo(Item o) {

            return (this.cost / this.weight - o.cost / o.weight);
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.%n", n, W);

        double result = 0;
        int currentWeight = 0;
        int currentNumber = 0;

        Arrays.sort(items, Collections.reverseOrder());

        while (currentNumber < items.length && currentWeight != W) {
            if (currentWeight + items[currentNumber].weight < W) {
                currentWeight += items[currentNumber].weight;
                result += items[currentNumber].cost;
            } else {
                result += (W - currentWeight) / (double) items[currentNumber].weight * items[currentNumber].cost;
                currentWeight = W;
            }
            currentNumber++;
        }
        System.out.printf("Удалось собрать рюкзак на сумму %f%n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}
