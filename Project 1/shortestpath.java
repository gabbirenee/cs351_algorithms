/*
    PROJECT 1: FLOYD'S SHORTEST PATH ALGORITHM
    This program will read in data from a text file and use Floyd's algorithm to compute the
    shortest path matrix, for every vertex in the directed graph
*/

import java.io.*;
import java.util.*;

class shortestpath {

    // Function to print out the matrix all pretty like
    public static void printMatrix (int n, int W[][]) {
        for(int x=0; x<n; x++){
            System.out.print(x+" ");
        }

        System.out.println();
        for (int i=0; i<n; i++) {
            System.out.print(i+": ");
            for (int j=0; j<n; j++) {
                System.out.print(W[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void floydAlg (int n, int [][] W, int P[][], int [][] D) {
        
    }


    // Main function of the program
    public static void main (String [] args)
        throws IOException
    {
        // Text file that numbers will be read from; The first integer in the text file represents the number of vertices in the graph; -1000 in the text file represents infinity
        Scanner txtfile=new Scanner(new FileReader("paths.txt"));
        
        // first int in the file represents the number of vertices
        int size;
        size = txtfile.nextInt(); 

        // declare the matrices for the shortest path
        int[][] W = new int[size][size];
        int[][] P = new int [size][size];
        int[][] D = new int [size][size];

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                W[i][j] = txtfile.nextInt();
            }
        }

    }

}