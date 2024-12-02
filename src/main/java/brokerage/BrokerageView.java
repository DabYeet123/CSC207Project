package brokerage;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import brokerage.interface_adapter.BrokerageController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

/**
 * The {@code BrokerageView} class provides a graphical user interface (GUI) for managing stock assets.
 * It enables users to view stock price trends, search for stocks, and perform buy/sell operations.
 * <p>
 * This class uses the Swing framework for UI components and JFreeChart for rendering stock price trends.
 * It interacts with a {@link BrokerageController} to perform business logic operations and data retrieval.
 * </p>
 *
 * <h2>Features:</h2>
 * <ul>
 *     <li>Search for stocks by their symbol and display their current price.</li>
 *     <li>Visualize stock price trends over time using a line chart.</li>
 *     <li>Buy or sell shares of a selected stock.</li>
 *     <li>Navigate back to the base view.</li>
 * </ul>
 *
 * <h2>Components:</h2>
 * <ul>
 *     <li>Input panel: Contains input fields and a search button to retrieve stock data.</li>
 *     <li>Graph panel: Displays a chart showing stock price trends.</li>
 *     <li>Buttons panel: Includes buttons for buying, selling, and navigating back.</li>
 * </ul>
 *
 * <h2>Dependencies:</h2>
 * <ul>
 *     <li>JFreeChart library for chart rendering.</li>
 *     <li>{@link BrokerageController} for handling user actions and business logic.</li>
 *     <li>{@link StockUnit} for representing stock data points.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * BrokerageController controller = new BrokerageController();
 * BrokerageView view = new BrokerageView(controller);
 * view.setVisible(true);
 * }
 * </pre>
 *
 * @see BrokerageController
 * @see StockUnit
 * @see JFreeChart
 */
public class BrokerageView extends JFrame {

    private final JPanel graphPanel;
    private JTextField stockNameField;
    private JLabel stockPriceLabel;
    private JButton buyButton;
    private JButton sellButton;

    public BrokerageView(BrokerageController controller) {
        setTitle("Crazy Bank - Asset Managing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int frameWidth = 700;
        final int frameHeight = 500;
        setSize(frameWidth, frameHeight);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        final JPanel inputPanel = createInputPanel(controller);
        final JPanel buttonsPanel = createButtonsPanel(controller);
        this.graphPanel = createGraphPanel();

        add(inputPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JPanel createButtonsPanel(BrokerageController controller) {
        final JPanel buttonsPanel = new JPanel();
        this.buyButton = new JButton("Buy Asset");
        this.sellButton = new JButton("Sell Asset");
        final JButton backButton = new JButton("Back");

        buyButton.setEnabled(false);
        sellButton.setEnabled(false);

        final int backButtonWidth = 80;
        final int backButtonHeight = 50;
        backButton.setPreferredSize(new Dimension(backButtonWidth, backButtonHeight));

        buyButton.addActionListener(event -> buyButtonAction(controller, this.stockNameField));
        sellButton.addActionListener(event -> sellButtonAction(controller, this.stockNameField));
        backButton.addActionListener(event -> controller.goBackToBaseView());

        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);
        buttonsPanel.add(backButton);

        return buttonsPanel;
    }

    private JPanel createInputPanel(BrokerageController controller) {
        final JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        final JLabel nameLabel = new JLabel("Stock Symbol:");
        final JLabel priceLabel = new JLabel("Price $:");
        final int columnsNumber = 10;
        this.stockNameField = new JTextField(columnsNumber);
        this.stockPriceLabel = new JLabel("N/A");
        final JButton searchButton = new JButton("Search");
        inputPanel.add(nameLabel);
        inputPanel.add(stockNameField);
        inputPanel.add(searchButton);
        inputPanel.add(priceLabel);
        inputPanel.add(stockPriceLabel);

        searchButton.addActionListener(event -> {
            searchButtonAction(controller, stockNameField, stockPriceLabel, buyButton, sellButton); });

        return inputPanel;
    }

    private JPanel createGraphPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Stock Price Trends"));
        return panel;
    }

    private static void sellButtonAction(BrokerageController controller, JTextField stockNameField) {
        final String stockSymbol = stockNameField.getText().trim();
        final double latestPrice = controller.onSearchStockSuccess(stockSymbol);

        final int amountOwn = controller.getQuantity(stockSymbol);

        final JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, amountOwn, 1));
        final JLabel l = new JLabel("Total Ask Price: " + latestPrice);
        amountSpinner.addChangeListener(event -> {
            l.setText(String.format("Total Ask Price: %.2f", (Integer) amountSpinner.getValue() * latestPrice)); });

        final JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Enter amount of security to sell:"));
        panel.add(amountSpinner);
        panel.add(l);

        final int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Sell Asset",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            final int amount = (int) amountSpinner.getValue();
            if (amount > 0) {
                final boolean success = controller.onSellTriggered(stockSymbol, amount);
                if (success) {
                    controller.addStock(stockSymbol, -1 * amount, latestPrice);
                    JOptionPane.showMessageDialog(
                            null,
                            "You have sold " + amount + " shares of " + stockSymbol,
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid amount.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private static void buyButtonAction(BrokerageController controller, JTextField stockNameField) {
        final String stockSymbol = stockNameField.getText().trim();
        final double latestPrice = controller.onSearchStockSuccess(stockSymbol);

        final JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        final JLabel l = new JLabel("Total Bid Price: " + latestPrice);
        amountSpinner.addChangeListener(event -> {
            l.setText(String.format("Total Bid Price: %.2f", (Integer) amountSpinner.getValue() * latestPrice)); });

        final JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Enter amount of security to buy:"));
        panel.add(amountSpinner);
        panel.add(l);

        final int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Buy Asset",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            final int amount = (int) amountSpinner.getValue();
            if (amount > 0) {
                final boolean success = controller.onBuyTriggered(amount, latestPrice);
                if (success) {
                    controller.addStock(stockSymbol, amount, latestPrice);
                    JOptionPane.showMessageDialog(
                            null,
                            "You have bought " + amount + " shares of " + stockSymbol,
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Insufficient funds to buy the asset.",
                            "Insufficient funds",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid amount greater than zero.",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void searchButtonAction(BrokerageController controller, JTextField stockNameFieldPassed,
                                    JLabel stockPriceLabelPassed, JButton buyButtonPassed, JButton sellButtonPassed) {
        final String stockSymbol = stockNameFieldPassed.getText().trim();
        final boolean success1 = controller.onSearchStockTriggered(stockSymbol);

        if (success1) {
            final boolean success2 = controller.isStockFound(stockSymbol);
            if (success2) {
                final List<StockUnit> stocks = controller.retrieveStocks(stockSymbol);
                final double stockPrice = controller.onSearchStockSuccess(stockSymbol);
                stockPriceLabelPassed.setText(String.format("$%.2f", stockPrice));
                buyButtonPassed.setEnabled(true);
                sellButtonPassed.setEnabled(true);
                drawGraph(stockSymbol, stocks);
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "No data found for this stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Please enter a stock symbol.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void drawGraph(String stockSymbol, List<StockUnit> stockUnits) {
        final TimeSeries series = new TimeSeries(stockSymbol);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (StockUnit unit : stockUnits) {
            try {
                final java.util.Date date = dateFormat.parse(unit.getDate());
                series.addOrUpdate(new Second(date), unit.getClose());
            }
            catch (ParseException event) {
                throw new RuntimeException(event);
            }
        }

        final TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Stock Price Trends - " + stockSymbol,
                "Time",
                "Price ($)",
                dataset,
                false,
                true,
                false
        );

        final XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.decode("#222d64"));
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));

        final ChartPanel chartPanel = new ChartPanel(chart);
        final JPanel panel = this.getGraphPanel();
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    }

    private JPanel getGraphPanel() {
        return this.graphPanel;
    }
}
