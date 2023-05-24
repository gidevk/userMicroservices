/*
package Controller;

import java.util.Timer;

import java.math.BigDecimal;
        import java.net.URL;
        import java.util.Timer;
        import java.util.TimerTask;
        import yahoofinance.Stock;
        import yahoofinance.YahooFinance;

public class StockChecker {
    public static void main(String[] args) {
        String symbol = "AAPL"; // replace with the stock symbol you want to track
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Stock stock = YahooFinance.get(symbol);
                    BigDecimal price = stock.getQuote().getPrice();
                    System.out.println("Stock price for " + symbol + " is " + price);
                } catch (Exception e) {
                    System.out.println("Error fetching stock data for " + symbol + ": " + e.getMessage());
                }
            }
        }, 0, 60*60*1000); // check stock price once an hour (in milliseconds)
    }
}

*/
