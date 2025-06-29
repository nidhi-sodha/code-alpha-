import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Market {
    private Map<String, Stock> stocks;

    public Market() {
        stocks = new HashMap<>();
        stocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.0));
        stocks.put("GOOG", new Stock("GOOG", "Alphabet Inc.", 2800.0));
        stocks.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 300.0));
        stocks.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.0));
        stocks.put("AMZN", new Stock("AMZN", "Amazon.com, Inc.", 3500.0));
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }

    public void displayMarket() {
        System.out.println("\nCurrent Market Data:");
        System.out.printf("%-6s %-20s %10s%n", "Symbol", "Name", "Price");
        System.out.println("------------------------------------------");
        for (Stock stock : stocks.values()) {
            System.out.printf("%-6s %-20s $%9.2f%n", stock.getSymbol(), stock.getName(), stock.getPrice());
        }
    }

    public void simulatePriceChanges() {
        Random random = new Random();
        for (Stock stock : stocks.values()) {
            double changePercent = random.nextDouble() * 0.1 - 0.05; // -5% to +5%
            double newPrice = stock.getPrice() * (1 + changePercent);
            stock.updatePrice(Math.round(newPrice * 100.0) / 100.0);
        }
    }
}
