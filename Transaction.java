import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String stockSymbol;
    private int shares;
    private double price;
    private String transactionType;
    private LocalDateTime date;

    public Transaction(String stockSymbol, int shares, double price, String transactionType) {
        this.stockSymbol = stockSymbol;
        this.shares = shares;
        this.price = price;
        this.transactionType = transactionType;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s - %s %d shares of %s at $%.2f", 
                date.format(formatter), transactionType.toUpperCase(), shares, stockSymbol, price);
    }
}
