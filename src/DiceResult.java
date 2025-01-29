public class DiceResult{
    private int total;
    private int[] results;

    DiceResult(int[] results, int total) {
        this.results = results;
        this.total = total;
    }

    public int getTotal(){
        return total;
    }

    public String getResults() {
        String res = "[";
        for (int i = 0; i < results.length - 1; i++) {
            res += results[i] + " + ";
        }
        res += results[results.length - 1] + "]";
        return res;
    }
}
