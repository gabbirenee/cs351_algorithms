/*
    Gabbi Forsythe
    12/12/2019
    CS351-01
    Dr. Chen

    
    PROJECT 3: Part 2 - 0/1 Knapsack (Best-First Branch and Bound Algorithm)
    Use the Best-First algorithm to find solution to the 0/1 Knapsack problem
*/

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;


// the Node class that will be pushed onto the priority queue to represent the Nodes of the tree
class Node {
    int level;
    int profit;
    int weight; 
    int bound; 

    Node() {
        this.level = 0;
        this.profit = 0;
        this.weight = 0;
        this.bound = 0;
    }

    Node (int level, int profit, int weight, int bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }

    int getLevel() {
        return this.level;
    }

    int getProfit() {
        return this.profit;
    }

    int getWeight() {
        return this.weight;
    }
    int getBound() {
        return this.bound;
    }
}


class bestfirst {
    static int numItems, capacity;

    public static int bound (Node u, int [] w, int [] p) {
        System.out.println("Level - "+u.getLevel());
        int j, k, totalWeight, result; 
        j = k = totalWeight = result = 0; 

        if(u.getWeight() >= capacity) {
            return 0;       // if the weight of the Node is >= the capacity, set the bound to 0 
        }
        else {
            result = u.getProfit();
            j = u.getLevel() + 1;
            totalWeight = u.getWeight();
            while((j <= numItems) && (totalWeight + w[j] <= capacity)) {
                totalWeight = totalWeight + w[j]; 
                result = result + p[j];
                j++; 
            }
            k = j;
            if(k <= numItems) {
                result = result + (capacity - totalWeight) * (p[k]/w[k]);
            }
            System.out.println("result = "+result);
            return result;
        }
    }

    public static int bestFirstAlg (int n, int p [], int w[], int W) {
        int maxProfit, size;
        maxProfit = 0;
        PriorityQueue <Node> PQ = new PriorityQueue<Node>(); 
        Node u, v, temp; 
        v = new Node();
        u = new Node();
        
        v.bound = bound(v, w , p);

        PQ.add(v);

        size = PQ.size();
        
        while(size != 0) {
            System.out.println("here 1");
            v = (Node) PQ.remove();
            if(v.getBound() > maxProfit) {
                u.level = v.getLevel() + 1;
                u.weight = v.getWeight() + w[u.getLevel()];
                u.profit = v.getProfit() + p[u.getLevel()]; 
                System.out.println("here 2");

                if((u.getWeight() <= W) && (u.getProfit() > maxProfit)) {
                    maxProfit = u.getProfit();
                    System.out.println("here 3");
                }
                u.bound = bound(u, w , p);
                System.out.println("here 5");
                if(u.getBound() > maxProfit) {
                    PQ.add(u);
                    System.out.println("here 4");
                }
                u.weight = (int) v.getWeight(); 
                u.profit = (int) v.getProfit();
                u.bound = (int) bound(u, w, p);
                System.out.println("here 6");

                if(u.getBound() > maxProfit) {
                    temp = (Node) u;
                    System.out.println("here 10");
                    PQ.add(temp);
                    System.out.println("here 7");
                }
                System.out.println("here 8");
            }
            size = PQ.size();
            System.out.println("here 9");

        }
        return maxProfit;
    }
    public static void main (String [] args)
      throws IOException 
    {
        int maxProfit;
        int [] w;
        int [] p; 
        Scanner txtfile=new Scanner(new FileReader("knapsack.txt"));    // the items will be in the knapsack.txt file

        int itemW, itemP;

        // try {
            capacity = txtfile.nextInt();   // first number in the text file is the capacity
            numItems = txtfile.nextInt();   // second number in the text file is the number of items in the file

            w = new int [numItems+1];
            p = new int [numItems+1];


            for (int i = 0; i < numItems; i++) {    // get the weights and the profit from the text file
                itemW = txtfile.nextInt();  
                itemP = txtfile.nextInt();
                w[i] = itemW; 
                p[i] = itemP; 
            }

            System.out.println("List of Items: ");
            //Print the items in the array after they are read in from the text file
            for(int i = 0; i < numItems; i++) {
                System.out.println("    ID: "+i+"  Weight: "+w[i]+"  Profit: "+p[i]);
            }

            System.out.println();
            System.out.println();

            // calculations here
            maxProfit = bestFirstAlg(numItems, p, w, capacity);

            System.out.println("Maximum Profit: $"+maxProfit);

        // } catch (Exception ex) {
        //     System.out.println("Error reading file.");
        // }

        txtfile.close();
    }
}