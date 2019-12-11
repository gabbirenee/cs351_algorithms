#include <iostream>
#include <fstream>
#include <string>
using namespace std;


int n, W, maxprofit, include, bestset, numbest; 

int main() { 

    ifstream File;
    File.open("knapsack.txt");

    File >> W;      // get the capacity from the file
    File >> n;      // get the number of items    

    int w [n]; 
    int p [n];

    for(int i = 0; i < n; i++) {        // read the items in from the text file
        File >> w[i];
        File >> p[i];
        // cout << w[i] << " " << p[i] << endl;
    }

    // cout << W << " " << n << endl; 

    File.close();

    return 0;
}