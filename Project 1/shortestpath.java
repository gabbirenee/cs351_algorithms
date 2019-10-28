/*
    PROJECT 1: FLOYD'S SHORTEST PATH ALGORITHM
    This program will read in data from a text file and use Floyd's algorithm to compute the
    shortest path matrix, for every vertex in the directed graph
*/

import java.io.*;
import java.util.*;

class shortestpath {

    // Function to print out the matrix all pretty like
    public static void printMatrix (int n, int mat[][]) {
        // for(int x=0; x<n; x++){
        //     System.out.print(x+" ");
        // }

        System.out.println();
        for (int i=1; i<=n; i++) {
            // System.out.print(i+": ");
            for (int j=1; j<=n; j++) {
                if(mat[i][j] == 10000) {        // if the value in the matrix is 10000 it will just print the word infnity instead
                    System.out.println("infinity ");
                }
                else {
                System.out.print(mat[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    public static void path (int [][] P, int q, int r) {
        if(P[q][r] != 0) {
            path(P, q, P[q][r]);
            System.out.print("v"+P[q][r]+" -> ");
            path(P, P[q][r], r);
        }
    }

    // Floyd's shortest path algorithm
    public static int[][] floydAlg (int n, int [][] W, int [][] P, int [][] D) {
        int i, j, k;    // indices
        
        for(i=1; i<=n; i++) {
            for(j=1; j<=n; j++) {
                P[i][j] = 0;        // fill P in with 0s by default
            }
        }

        D=W;

        for(k=1; k<=n; k++) {
            for(i=1; i<=n; i++) {
                for (j=1; j<=n; j++) {
                    if(D[i][k] + D[k][j] < D[i][j]) {
                        P[i][j] = k;
                        D[i][j] = D[i][k] + D[k][j]; 
                    }
                }
            }
        }
        System.out.println("Matrix of the shortest path to and from each vertex: ");
        System.out.println();

        // print the matrix
        printMatrix(n, D);

        // return P so it can be used for the print path algorithm
        return P; 
    }


    // Main function of the program
    public static void main (String [] args)
        throws IOException
    {
        // Text file that numbers will be read from; The first integer in the text file represents the number of vertices in the graph; 100000 in the text file represents infinity
        Scanner txtfile=new Scanner(new FileReader("paths.txt"));
        
        // first int in the file represents the number of vertices
        int size;

        // int infinity = (int) Integer.MAX_VALUE - 10;     //set infinity == to a very high number

        try {
            size = txtfile.nextInt(); 
        } catch (Exception ex) {
            System.out.println("Invalid data in text file.");
            return;     // program will terminate if the text file has invalid data
        } 

        // declare the matrices for the shortest path
        int[][] W = new int[size+1][size+1];
        int[][] P = new int [size+1][size+1];
        int[][] D = new int [size+1][size+1];

        try {
            for(int i=1; i<=size; i++) {
                for(int j=1; j<=size; j++) {
                    int temp = txtfile.nextInt();
                    W[i][j] = temp;
                }
            }
            System.out.println();
            System.out.println("Matrix of existing paths to/from each vertex from text file: ");
            System.out.println();
            printMatrix(size, W);
            System.out.println();
            System.out.println();

            // call Floyd's algorithm which prints D once it is finished
            P = floydAlg(size, W, P, D);

            System.out.println();
            System.out.println();
        } catch (Exception ex) {
            System.out.println("Invalid data in text file.");
            return; 
        }

        System.out.println();
        
        // the user input will go into q and r for the path function
        int q, r; 
        q=r=0;

        // user input
        Scanner input = new Scanner(System.in);

        // get the user input here
        try {
            System.out.println("Now we will calculate the path between two vertices. Enter in start vertex: ");
            q = input.nextInt(); 

            // error handling for if the user enters in a vertex that does not exist
            if(q > size && q <= 0) {
                System.out.println("Invalid Input.");
                return;
            }
            
            System.out.println("Enter in end vertex: ");
            r = input.nextInt();
            
            // error handling for if the user enters in a vertex that does not exist
            if(r > size && r <= 0) {
                System.out.println("Invalid Input.");
                return;
            }
        } catch (Exception ex) {
            System.out.println("Invalid input.");
            return; 
        }

        System.out.print("v"+q+" -> ");
        path(P, q, r);
        System.out.print("v"+r);
        System.out.println();
        System.out.println("Program end.");


        return;

    }

}

/* SAMPLE OUPUT:

thomas% java shortestpath

Matrix of existing paths to/from each vertex from text file: 


0 4 100000 100000 100000 10 100000 
3 0 100000 18 100000 100000 100000 
100000 6 0 100000 100000 100000 100000 
100000 5 15 0 2 19 5 
100000 100000 12 1 0 100000 100000 
100000 100000 100000 100000 100000 0 10 
100000 100000 100000 8 100000 100000 0 


Matrix of the shortest path to and from each vertex: 


0 4 36 22 24 10 20 
3 0 32 18 20 13 23 
9 6 0 24 26 19 29 
8 5 14 0 2 18 5 
9 6 12 1 0 19 6 
26 23 32 18 20 0 10 
16 13 22 8 10 26 0 



Now we will calculate the path between two vertices. Enter in start vertex: 
3
Enter in end vertex: 
5
v3 -> v2 -> v4 -> v5
Program end.
thomas% 

*/

/* SAMPLE OUTPUT 2 (DIFFERENT TEXT FILE - from pg. 101 in book):

thomas% java shortestpath

Matrix of existing paths to/from each vertex from text file: 


0 1 100000 1 5 
9 0 3 2 100000 
100000 100000 0 4 100000 
100000 100000 2 0 3 
3 100000 100000 100000 0 


Matrix of the shortest path to and from each vertex: 


0 1 3 1 4 
8 0 3 2 5 
10 11 0 4 7 
6 7 2 0 3 
3 4 6 4 0 



Now we will calculate the path between two vertices. Enter in start vertex: 
4
Enter in end vertex: 
2
v4 -> v5 -> v1 -> v2
Program end.
thomas%

*/
