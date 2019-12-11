/*
    Gabbi Forsythe
    12/12/2019
    CS351-01
    Dr. Chen

    
    PROJECT 3: Part 1 - 0/1 Knapsack (Backtracking Algorithm)
    Use the backtracking algorithm to find the solution to the 0/1 Knapsack problem
*/

import java.io.*;
import java.util.Scanner;

class backtrack {
    // Used global variables because that is how the book does it in Algorithm 5.7 (pg.242-243)
    static int numItems, capacity, maxProfit, numbest;
    static int [] w;
    static int [] p; 
    static boolean [] include;
    static boolean [] bestset;

    public static void knapsack (int i, int profit, int weight) {
        System.out.println("Knapsack - "+ i);
        if ((weight <= capacity) && (profit > maxProfit)) {     // if it is the best set so far
            maxProfit = profit;
            numbest = i; 
            bestset = include; 
        }

        if(promising(i, profit, weight)) {
            include[i+1] = true;        // incldue the next node
            knapsack(i+1, (profit + p[i+1]), (weight + w[i+1]));
            include[i+1] = false;       // don't include the next node
            knapsack(i+1, profit, weight);
        }
    }

    public static boolean promising (int i, int profit, int weight) {
        System.out.println("promising - "+ i);
        int j, k;
        j = k = 0;
        int totalWeight; 
        int bound; 

        if(weight >= capacity) {
            System.out.println("promising false - "+ i);
            return false;       // if we are over the weight limit, the node is not promising
        }
        else {
            j = i + 1; 
            bound = profit;
            totalWeight = weight;
            while ((j <= numItems) && (totalWeight+w[j] <= capacity))       // grab all possible items
            {
                totalWeight = totalWeight + w[j];
                bound = bound + p[j]; 
                j++;
            }
            k = j; 
            if (k <= numItems) {
                bound = (int) bound + (capacity - totalWeight) * (p[k]/w[k]); // recalculate the bound if k is not greater than number of Items 
            }
            System.out.println("promising trueish - "+ i);
            return bound > maxProfit; 
        }
    }

    public static void main (String [] args)
      throws IOException 
    {
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));    // the items will be in the knapsack.txt file

        int itemW, itemP;

        try {
            capacity = txtfile.nextInt();   // first number in the text file is the capacity
            numItems = txtfile.nextInt();   // second number in the text file is the number of items in the file

            w = new int [numItems];
            p = new int [numItems];
            include = new boolean[numItems];
            bestset = new boolean[numItems];


            for (int i = 0; i < numItems; i++) {    // get the weights and the profit from the text file
                itemW = txtfile.nextInt();  
                itemP = txtfile.nextInt();
                w[i] = itemW; 
                p[i] = itemP; 
            }

            System.out.println("List of Items: ");
            //Print the items in the array after they are read in from the text file
            for(int i = 0; i < numItems; i++) {
                System.out.println("    ID: "+(i+1)+"  Weight: "+w[i]+"  Profit: "+p[i]);
            }

            System.out.println();
            System.out.println();
            
            numbest = 0;
            maxProfit = 0;
            knapsack(0, 0, 0);
            System.out.println("Calculations done");
            System.out.println("Maximum Profit: $"+maxProfit);
            System.out.println();
            System.out.print("Best Set = { ");
            for(int j = 0; j <= numbest; j++) {
                System.out.print(bestset[j]+" ");
            }
            System.out.print("} \n");


        } catch (Exception ex) {
            System.out.println("Error reading file.");
        }

        txtfile.close();
    }
}