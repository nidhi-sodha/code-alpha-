import java.util.HashMap;

public class User {
    private String name;
    private String id;
    private HashMap<String, Integer> portfolio = new HashMap<>();

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void buyStock(String symbol, int quantity) {
        portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
    }

    public Object[][] getPortfolioData() {
        Object[][] data = new Object[portfolio.size()][2];
        int i = 0;
        for (String symbol : portfolio.keySet()) {
            data[i][0] = symbol;
            data[i][1] = portfolio.get(symbol);
            i++;
        }
        return data;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
