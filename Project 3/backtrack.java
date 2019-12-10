/*
    Gabbi Forsythe
    12/12/2019
    CS351-01
    Dr. Chen

    
    PROJECT 3: Part 1 - 0/1 Knapsack (Backtracking Algorithm)
    Use the backtracking algorithm to find the optimal solution to the 0/1 Knapsack problem
*/

import java.io.*;
import java.util.Scanner;

class backtrack {

    public static void main (String [] args)
      throws IOException 
    {
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));    // the items will be in the knapsack.txt file

        int numItems, capacity, itemW, itemP;
        int [] weight;
        int [] profit; 

        try {
            capacity = txtfile.nextInt();   // first number in the text file is the capacity
            numItems = txtfile.nextInt();   // second number in the text file is the number of items in the file

            weight = new int [numItems];
            profit = new int [numItems];

            for (int i = 0; i < numItems; i++) {    // get the weights and the profit from the text file
                itemW = txtfile.nextInt();  
                itemP = txtfile.nextInt();
                weight[i] = itemW; 
                profit[i] = itemP; 
            }

            System.out.println("List of Items: ");
            //Print the items in the array after they are read in from the text file
            for(int i = 0; i < numItems; i++) {
                System.out.println("    ID: "+i+"  Weight: "+weight[i]+"  Profit: "+profit[i]);
            }
            // int total;
            // total = totalProfit(capacity, numItems, w, b);
            // System.out.println("Total Profit: $" + total);

        } catch (Exception ex) {
            System.out.println("Error reading file.");
        }

        txtfile.close();
    }
}