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

    // function to find the max of the two values
    public static int maximum (int a, int b) {
        if(a>b)
            return a;
        else
            return b;
    }

    public static int totalProfit (int cap, int n, int [] wt, int [] ben) {
        int i, j;
        int P[][] = new int[n+1][cap+1];

        for(i=0; i<=n; i++) {
            for(j=0; j<=cap; j++) {
                if(i==0 || j==0) {
                    P[i][j] = 0;
                }
                else if (wt[i-1] <= j) {
                    P[i][j] = maximum(P[i-1][j] ,ben[i-1] + P[i-1][j-wt[i-1]]);
                }
                else {
                    P[i][j] = P[i-1][j]; 
                }
            }
        }

        return P[n][cap];
    }

    public static void main (String [] args)
      throws IOException 
    {
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));    // the items will be in the knapsack.txt file

        int numItems, capacity, weight, profit;
        // Items [] list;  // the items that are read in from the text file
        // boolean [] ks;  // if the item in that position is put in the knapsack, it will receive a true value, else = false
        // Items temp;     
        int [] w;
        int [] b; 

        try {
            capacity = txtfile.nextInt();   // first number in the text file is the capacity
            numItems = txtfile.nextInt();   // second number in the text file is the number of items in the file

            // list = new Items[numItems];
            w = new int [numItems];
            b = new int [numItems];

            for (int i = 0; i < numItems; i++) {    // get the weights and the profit from the text file
                weight = txtfile.nextInt();  
                profit = txtfile.nextInt();
                // temp = new Items (i+1, weight, profit);
                // list[i] = temp; 
                w[i] = weight; 
                b[i] = profit; 
            }

            System.out.println("List of Items: ");
            //Print the items in the array after they are read in from the text file
            for(int i = 0; i < numItems; i++) {
                System.out.println("    ID: "+i+"  Weight: "+w[i]+"  Profit: "+b[i]);
            }
            int total;
            total = totalProfit(capacity, numItems, w, b);
            System.out.println("Total Profit: $" + total);

        } catch (Exception ex) {
            System.out.println("Error reading file.");
        }

        txtfile.close();
    }
}