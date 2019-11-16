public class Items {
    int id; 
    int weight;
    int profit; 

    public Items (int id, int weight, int profit) {     // this is called before the ppu is known
        this.id = id;
        this.weight = weight;
        this.profit = profit;
    }

    public int getId () {
        return id;
    }

    public int getWeight () {
        return weight;
    }

    public int getProfit () {
        return profit;
    }

    public int getPPU () {
        return profit/weight;
    }

    public String ToString() {
        return "ID: "+ id + " weight: "+ weight+" profit: "+profit; 
    }
}