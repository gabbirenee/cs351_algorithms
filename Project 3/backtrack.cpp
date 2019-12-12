/*
    Gabbi Forsythe
    12/12/2019
    CS351-01
    Dr. Chen

    
    PROJECT 3: Part 1 - 0/1 Knapsack (Backtracking Algorithm)
    Use the backtracking algorithm to find the solution to the 0/1 Knapsack problem
*/

#include <iostream>
#include <fstream>
#include <string>
using namespace std;

// global variables because that is what the book said to do on page 243
int n, W, numbest, maxprofit; 

// tells if the node is promising or not
bool promising (int i, int weight, int profit, int w [], int p []) { 
    int j, k, totalweight;
    float bound;

    if(weight >= W) {
        return false;   // if the weight exceeds the capacity of the knapsack
    }
    else {
        j = i+1;
        bound = profit; 
        totalweight = weight;
        while ((j <= n) && (totalweight +w[j] <= W)) {
            totalweight = totalweight + w[j];
            bound = bound + p[j];
            j ++;
        }
        k = j;
        if (k <= n) {
            bound = bound + (W - totalweight) * (p[k]/w[k]);
        }
        // cout << "Bound = " << bound; 
        return bound > maxprofit;       // if the bound is greater than the maximum profit, the node is promising
    }
}

void knapsack (int i, int profit, int weight, int* w, int* p, bool* include, bool* bestset)  {
    if ((weight <= W) && (profit > maxprofit))  {   // if it is the max profit so far 
        maxprofit = profit; 
        numbest = i;
        bestset = include; 
    }

    bool promise = promising(i, weight, profit, w, p); 

    if(promise == true ) {      // if the node is promising
        include[i+1] = true;        // include the node
        knapsack (i + 1, profit + p[i+1], weight + w[i+1], w , p, include, bestset);
        include[i+1] = false;        // don't include the node
        knapsack (i + 1, profit, weight, w , p, include, bestset);
    }
}

int main() { 

    ifstream File;
    File.open("knapsack.txt");

    File >> W;      // get the capacity from the file
    File >> n;      // get the number of items  

    // cout << W << " " << n << endl;   

    int w [n]; 
    int p [n];

    for(int i = 0; i < n; i++) {        // read the items in from the text file
        File >> w[i];
        File >> p[i];
        // cout << w[i] << " " << p[i] << endl;
    }

    cout << "List of Items: " << endl;
    //Print the items in the array after they are read in from the text file
    for(int i = 0; i < n; i++) {
        cout << "    ID: " << (i+1) << "  Weight: " << w[i] << "  Profit: " << p[i] << endl;
    }

    bool include [n+1];
    bool bestset [n+1];

    for (int g = 0; g < n+1; g++) {      // give bool arrays default values
        include[g] = false;
        bestset[g] = false; 
    }

    numbest = 0;
    maxprofit = 0; 
    knapsack(0, 0, 0, w, p, include, bestset);

    
    cout << "Max Profit: $" << maxprofit << endl; 
    cout << "Items included: "<< endl;
    for (int j = 1; j <= numbest; j++) {
        if (bestset[j] = true)
        cout << "    " << j << endl;
    }

    File.close();

    return 0;
}


/*
    KNAPSACK.TXT #1:
        30
        3
        5 50
        10 60
        20 140

    TEST RUN  #1: 
        thomas% a.out
        List of Items: 
            ID: 1  Weight: 5  Profit: 50
            ID: 2  Weight: 10  Profit: 60
            ID: 3  Weight: 20  Profit: 140
        Max Profit: $200
        Items included: 
            1
            2
        thomas%

    KNAPSACK.TXT #2:
        16
        4
        2 40
        5 30
        10 50
        5 10
    
    TEST RUN #2: 
        thomas% a.out
        List of Items: 
            ID: 1  Weight: 2  Profit: 40
            ID: 2  Weight: 5  Profit: 30
            ID: 3  Weight: 10  Profit: 50
            ID: 4  Weight: 5  Profit: 10
        Max Profit: $80
        Items included: 
            1
            3
        thomas% 
*/