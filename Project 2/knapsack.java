/*
    Gabbi Forsythe
    11/19/2019
    CS351-01
    Dr. Chen

    
    PROJECT 2: Part 1 - 0/1 Knapsack (Greedy Algorithm)
    Use the greedy algorithm to solve the 0/1 Knapsack Problem
*/

import java.io.*;
import java.util.Scanner;

class knapsack {

    public static void pricePerUnit (int [] w, int [] p, int [] pw) {
        for (int i = 0; i < w.length; i++) {
            pw[i] = p[i] / w[i]; 
        }
    }

    public static void pickItems (int [] w, int []p, int [] pw, boolean [] k, int cap) {
        for (int i = 0; i < w.length; i++) {
            if(w[i] <= cap) {
                k[i] = true; 
                cap = cap - w[i];
            }
            else {
                k[i] = false; 
            }
        }
        int total;
        total = 0;
        for (int j = 0; j < k.length; j++) {
            if (k[j] == true) {
                System.out.println("Item "+ (j+1) + ": Profit = "+p[j] + "  Weight = "+ w[j]); 
                total = total + p[j];
            }
        }
        System.out.println("Total Profit: $"+total);
    }
    public static void main (String [] args)
      throws IOException 
    {
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));

        int numItems, capacity;
        int [] weight;
        int [] profit; 
        int [] ppu;
        boolean [] ks;

        try {
            capacity = txtfile.nextInt();
            numItems = txtfile.nextInt();

            weight = new int [numItems];
            profit = new int [numItems];

            for (int i = 0; i < numItems; i++) {
                weight[i] = txtfile.nextInt(); 
                profit[i] = txtfile.nextInt();
            }
            
            ppu = new int [numItems];
            pricePerUnit(weight, profit, ppu);

            for (int i = 0; i < ppu.length; i++) {
                System.out.println(ppu[i]);
            }

            ks = new boolean [numItems];
            pickItems(weight, profit, ppu, ks, capacity);

        } catch (Exception ex) {
            System.out.println("Error reading file.");
            return;
        }
    }
}