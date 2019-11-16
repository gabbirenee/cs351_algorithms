/*
    Gabbi Forsythe
    11/19/2019
    CS351-01
    Dr. Chen

    
    PROJECT 2: Part 2 - Knapsack with Fractions: (Greedy Algorithm)
    Use the greedy algorithm to solve the Knapsack with Fractions Problem
*/

import java.io.*;
import java.util.Scanner;

class kwf {
    // insertion sort to sort the items according to their PPU in decreasing order
    public static void sort (Items [] itemsList) {
        int length = itemsList.length; 
        
        for(int i = 1; i < length; ++i) {
            int val = itemsList[i].getPPU();
            Items temp = itemsList[i];
            int j = i-1;

            while (j>=0 && itemsList[j].getPPU() < val) {
                itemsList[j+1] = itemsList[j];
                j = j-1; 
            }
            itemsList[j+1] = temp; 
        }

        // for(int k = 0; k < length; k++) {
        //     System.out.println(itemsList[k].ToString());
        // }
    }

    // this is the function that decides what gets chosen to go in the knapsack and prints it out on the screen 
    public static void pickItems (Items [] itemsList, boolean [] k, int cap) {
        System.out.println("Items to be put in the knapsack: ");
        for (int i = 0; i < itemsList.length; i++) {
            if(itemsList[i].getWeight() <= cap) {
                k[i] = true; 
                cap = cap - itemsList[i].getWeight();
            }
            else {
                k[i] = false; 
            }
        }
        int total;
        total = 0;
        for (int j = 0; j < k.length; j++) {
            if (k[j] == true) {
                System.out.println("   " + itemsList[j].ToString()); 
                total = total + itemsList[j].getProfit();
            }
            else if (k[j] == false) {       // first one in k that is false can be used for the fraction
                if(cap != 0) {
                    System.out.println("   A fraction of- "+itemsList[j].ToString());
                    double fraction = (double)cap/(double)itemsList[j].getWeight(); 
                    total = total + (int)(itemsList[j].getProfit()*fraction); 
                }
                break;
            }
        }

        System.out.println("Total Profit: $"+total);
    }
    
    //main function
    public static void main (String [] args)
        throws IOException 
    {
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));    // the items will be in the knapsack.txt file

        int numItems, capacity, weight, profit;
        Items [] list;  // the items that are read in from the text file
        boolean [] ks;  // if the item in that position is put in the knapsack, it will recieve a true value, else = false
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
            sort(list);     // sort the items according to their ppu

            ks = new boolean [numItems];
            pickItems(list, ks, capacity);  // choose the items that will be in the knapsack

        } catch (Exception ex) {
            System.out.println("Error reading file.");
        }
        
        txtfile.close();
    }
}

/*
knapsack.txt: 
30
3
5 50
10 60
20 140

TEST RUN: 
thomas% java kwf
Items to be put in the knapsack: 
   ID: 1 weight: 5 profit: 50
   ID: 3 weight: 20 profit: 140
   A fraction of- ID: 2 weight: 10 profit: 60
Total Profit: $220
thomas% 

*/