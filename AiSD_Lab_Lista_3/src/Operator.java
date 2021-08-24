public class Operator
{
    private String symbol;
    private int priority;

    public Operator(String symbol, int priority)
    {
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
