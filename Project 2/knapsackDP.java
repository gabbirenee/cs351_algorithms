/*
    Gabbi Forsythe
    11/19/2019
    CS351-01
    Dr. Chen

    
    PROJECT 2: Part 3 - 0/1 Knapsack (Dynamic Programming Approach)
    Use the dynamic programming approach to find a solution to the 0/1 Knapsack Problem
*/

import java.io.*;
import java.util.Scanner;

class knapsackDP {

    public static void main (String [] args)
      throws IOException 
    {
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));    // the items will be in the knapsack.txt file

        int numItems, capacity, weight, profit;
        Items [] list;  // the items that are read in from the text file
        // boolean [] ks;  // if the item in that position is put in the knapsack, it will receive a true value, else = false
        Items temp;     

        try {
            capacity = txtfile.nextInt();   // first number in the text file is the capacity
            numItems = txtfile.nextInt();   // second number in the text file is the number of items in the file

            list = new Items[numItems];

            for (int i = 0; i < numItems; i++) {    // get the weights and the profit from the text file
                weight = txtfile.nextInt();  
                profit = txtfile.nextInt();
                temp = new Items (i+1, weight, profit);
                list[i] = temp; 
            }

            for(int i = 0; i < numItems; i++) {
                System.out.println(list[i].ToString());
            }

        } catch (Exception ex) {
            System.out.println("Error reading file.");
        }

        txtfile.close();
    }
}