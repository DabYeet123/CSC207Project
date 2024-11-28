package Brokerage;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class BrokerageView extends JFrame {

    private final JPanel graphPanel;

    public BrokerageView(BrokerageController controller) {
        // Initialize Swing UI
        setTitle("Crazy Bank - Asset Managing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout());

        // Create Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Use FlowLayout for horizontal layout

        // Components for input
        JLabel nameLabel = new JLabel("Stock Symbol:");
        JTextField stockNameField = new JTextField(10); // Set preferred width for the text field
        JLabel priceLabel = new JLabel("Price $:");
        JLabel stockPriceLabel = new JLabel("N/A");

        // Search Button
        JButton searchButton = new JButton("Search");

        // Add components to the input panel
        inputPanel.add(nameLabel);
        inputPanel.add(stockNameField);
        inputPanel.add(searchButton);
        inputPanel.add(priceLabel);
        inputPanel.add(stockPriceLabel);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        JButton buyButton = new JButton("Buy Asset");
        JButton sellButton = new JButton("Sell Asset");
        buyButton.setEnabled(false);
        sellButton.setEnabled(false);

        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);

        // Graph Panel
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createTitledBorder("Stock Price Trends"));
        this.graphPanel = graphPanel;

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String stockSymbol = stockNameField.getText().trim();
            boolean success1 = controller.onSearchStockTriggered(stockSymbol);

            if (success1) {
                boolean success2 = controller.isStockFound(stockSymbol);
                if (success2){
                    List<StockUnit> stocks = controller.retrieveStocks(stockSymbol);
                    double stockPrice = controller.onSearchStockSuccess(stockSymbol);
                    stockPriceLabel.setText(String.format("$%.2f", stockPrice));
                    buyButton.setEnabled(true);
                    sellButton.setEnabled(true);

                    drawGraph(stockSymbol, stocks);
                } else {
                    JOptionPane.showMessageDialog(null, "No data found for this stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please enter a stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buyButton.addActionListener(e -> {
            String stockSymbol = stockNameField.getText().trim();
            double latestPrice = controller.onSearchStockSuccess(stockSymbol);

            // Spinner with min 1, max Integer.MAX_VALUE, step size 1
            JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
            JLabel l = new JLabel("Total Bid Price: " + latestPrice);
            amountSpinner.addChangeListener(f -> l.setText(String.format("Total Bid Price: %.2f", (Integer) amountSpinner.getValue() * latestPrice)));

            final JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Enter amount of security to buy:"));
            panel.add(amountSpinner);
            panel.add(l);

            int option = JOptionPane.showConfirmDialog(
                    null,  // Parent component
                    panel,  // The panel with the spinner
                    "Buy Asset",  // Dialog title
                    JOptionPane.OK_CANCEL_OPTION,  // Options: OK and Cancel buttons
                    JOptionPane.PLAIN_MESSAGE  // Message type
            );

            // If the user clicks OK
            if (option == JOptionPane.OK_OPTION) {
                int amount = (int) amountSpinner.getValue();
                if (amount > 0) {
                    boolean success = controller.onBuyTriggered(amount, latestPrice);
                    if (success) {
                        controller.addStock(stockSymbol, amount, latestPrice);
                        JOptionPane.showMessageDialog(
                                null,  // Parent component
                                "You have bought " + amount + " shares of " + stockSymbol,  // Message to display
                                "Success",  // Title
                                JOptionPane.INFORMATION_MESSAGE  // Message type
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid amount greater than zero.",
                            "Invalid Amount",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        sellButton.addActionListener(e -> {
            String stockSymbol = stockNameField.getText().trim();
            double latestPrice = controller.onSearchStockSuccess(stockSymbol);

            int amountOwn = controller.getQuantity(stockSymbol);

            // Spinner with min 1, max Integer.MAX_VALUE, step size 1
            JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, amountOwn, 1));
            JLabel l = new JLabel("Total Ask Price: " + latestPrice);
            amountSpinner.addChangeListener(f -> l.setText(String.format("Total Ask Price: %.2f", (Integer) amountSpinner.getValue() * latestPrice)));

            final JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Enter amount of security to sell:"));
            panel.add(amountSpinner);
            panel.add(l);

            int option = JOptionPane.showConfirmDialog(
                    null,  // Parent component
                    panel,  // The panel with the spinner
                    "Sell Asset",  // Dialog title
                    JOptionPane.OK_CANCEL_OPTION,  // Options: OK and Cancel buttons
                    JOptionPane.PLAIN_MESSAGE  // Message type
            );

            // If the user clicks OK
            if (option == JOptionPane.OK_OPTION) {
                int amount = (int) amountSpinner.getValue();
                if (amount > 0) {
                    boolean success = controller.onSellTriggered(stockSymbol, amount);
                    if (success) {
                        controller.addStock(stockSymbol, -1*amount, latestPrice);
                        JOptionPane.showMessageDialog(
                                null,  // Parent component
                                "You have sold " + amount + " shares of " + stockSymbol,  // Message to display
                                "Success",  // Title
                                JOptionPane.INFORMATION_MESSAGE  // Message type
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid amount.",
                            "Invalid Amount",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }



    private void drawGraph(String stockSymbol, List<StockUnit> stockUnits) {
        TimeSeries series = new TimeSeries(stockSymbol);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (StockUnit unit : stockUnits) {
            try {
                java.util.Date date = dateFormat.parse(unit.getDate());
                series.addOrUpdate(new Second(date), unit.getClose());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Stock Price Trends - " + stockSymbol,
                "Time",
                "Price ($)",
                dataset,
                false,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.decode("#222d64"));
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));

        ChartPanel chartPanel = new ChartPanel(chart);
        JPanel graphPanel = this.getGraphPanel();
        graphPanel.removeAll();
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.validate();
    }

    private JPanel getGraphPanel() {
        return this.graphPanel;
    }
}