/*
    Gabbi Forsythe
    12/12/2019
    CS351-01
    Dr. Chen

    
    PROJECT 3: Part 2 - 0/1 Knapsack (Best-First Branch and Bound Algorithm)
    Use best-first search (Branch and Bound) algorithm to find the solution to the 0/1 Knapsack problem
*/

#include <iostream>
#include <fstream>
#include <string>
#include <queue>
using namespace std;


int n, W;

struct Node {       // used for our "state space tree"
    int level;
    int profit; 
    int weight;
    float bound;
};

struct CompareLevel {   // will sort the priority queue based on the level 
    bool operator()(Node const& n1, Node const& n2)   
    { 
        return n1.level < n2.level;     
    } 
};

float bound (Node u, const int w [], const int p []) {
    int j, k; 
    int totalweight; 
    float result; 
    if(u.weight >= W) {     // if the weight is greater than the capacity
        return 0;
    }
    else {      // calculate the bound
        result = u.profit;
        j = u.level + 1; 
        totalweight = u.weight;
        while ((j <= n) && ((totalweight + w[j]) <= W)) {     // grab all the items
            totalweight = totalweight + w[j];
            result = result + p[j];
            j++;
        }
        k = j;
        if (k <= n) {
            result = result + (W - totalweight) * (p[k]/w[k]);
        }
        return result; 
    }
}

int knapsack (const int p [], const int w []) {
    int maxprofit;
    maxprofit = 0;
    priority_queue <Node, vector<Node>, CompareLevel> PQ;   // will use CompareLevel to sort based on the level
    Node u, v;
    v.level = 0; v.profit = 0; v.weight = 0; 
    v.bound = bound (v, w, p); 
    PQ.push(v);
    // cout << "bound: " << v.bound << endl;
    // cout << PQ.top().bound << endl;
    while(!PQ.empty()) {        // traverse the tree while it is not empty, tree will be empty when the maxprofit is reached and no other node is promising
        v = PQ.top();
        PQ.pop();
        if(v.bound > maxprofit) {
            u.level = v.level + 1; 
            u.weight = v.weight + w[u.level];
            u.profit = v.profit + p[u.level];
            
            if((u.weight <= W) && (u.profit > maxprofit)) {
                maxprofit = u.profit;
            }
            u.bound = bound(u, w, p);
            if(u.bound > maxprofit) {
                PQ.push(u);
            }
            u.weight = v.weight;
            u.profit = v.profit; 
            u.bound = bound(u, w, p); 
            if(u.bound > maxprofit) {
                PQ.push(u);
            }
        }
    }


    return maxprofit;
}


int main () {

    ifstream File;
    File.open("knapsack.txt");

    File >> W;      // get the capacity from the file
    File >> n;      // get the number of items  

    // cout << W << " " << n << endl;   

    int w [n]; 
    int p [n];

    int maxprofit;
    maxprofit = 0;

    for(int i = 0; i < n; i++) {        // read the items in from the text file
        File >> w[i];
        File >> p[i];
        // cout << w[i] << " " << p[i] << endl;
    }

    cout << "n=" << n << "   W=" << W <<endl;
    cout << "List of Items: " << endl;
    //Print the items in the array after they are read in from the text file
    for(int i = 0; i < n; i++) {
        cout << "    ID: " << (i+1) << "  Weight: " << w[i] << "  Profit: " << p[i] << endl;
    }

    maxprofit = knapsack(p, w);
    
    cout << "Maximum Profit: $" << maxprofit << endl;
    
    File.close();

    return 0;
}

/*
    KNAPSACK.TXT #1:
        16
        4
        2 40
        5 30
        10 50
        5 10

    TEST RUN #1: 
        thomas% g++ bestfirst.cpp
        thomas% a.out
        n=4   W=16
        List of Items: 
            ID: 1  Weight: 2  Profit: 40
            ID: 2  Weight: 5  Profit: 30
            ID: 3  Weight: 10  Profit: 50
            ID: 4  Weight: 5  Profit: 10
        Maximum Profit: $80
        thomas% 

    KNAPSACK.TXT #2:
        30
        3
        5 50
        10 60
        20 140

    TEST RUN  #2: 
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
*/