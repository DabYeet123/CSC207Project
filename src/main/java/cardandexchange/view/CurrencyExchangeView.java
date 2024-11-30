package cardandexchange.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cardandexchange.adapter.CurrencyExchangeController;
import cardandexchange.useCase.exchange.CurrencyExchangeDataUsage;
import lombok.Getter;

@Getter
@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class CurrencyExchangeView extends JFrame {
    private static final int SIZE_X = 600;
    private static final int SIZE_Y = 400;
    private static final int NUM_25 = 25;
    private static final int NUM_30 = 30;
    private static final int NUM_50 = 50;
    private static final int NUM_100 = 100;
    private static final int NUM_150 = 150;
    private static final int NUM_200 = 200;
    private static final int NUM_250 = 250;
    private static final int NUM_300 = 300;
    private static final int NUM_400 = 400;
    private JTextField inputAmountField;
    private JTextField outputAmountField;
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JButton exchangeButton;
    private JButton backButton;

    public CurrencyExchangeView(CurrencyExchangeController currencyExchangeController) {
        setTitle("Currency Exchange");
        setSize(SIZE_X, SIZE_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // input amount
        final JLabel inputLabel = new JLabel("Input Amount:");
        inputLabel.setBounds(NUM_50, NUM_50, NUM_100, NUM_25);
        add(inputLabel);

        setInputAmountField(new JTextField());
        getInputAmountField().setBounds(NUM_150, NUM_50, NUM_150, NUM_25);
        add(getInputAmountField());

        // input currency
        final JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setBounds(NUM_50, NUM_100, NUM_100, NUM_25);
        add(fromLabel);

        final String[] currencies = {"AMD", "ANG", "AOA", "AUD", "AWG", "AZN", "BAM", "BDT", "BGN", "BHD",
            "BIF", "BMD", "BND", "BOB", "BSD", "BTN", "BWP", "BZD", "CAD", "CDF", "CHF", "CLF",
            "CLP", "CNH", "CNY", "COP", "DOP", "EGP", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS",
            "GIP", "GMD", "GNF", "GYD", "HKD", "HNL", "HRK", "HTG", "ICP", "IDR", "ILS", "INR",
            "ISK", "JEP", "JPY", "KES", "KHR", "KMF", "KRW", "KWD", "KYD", "LAK", "LBP", "LKR",
            "MAD", "MDL", "MMK", "MOP", "MRU", "MWK", "MYR", "NGN", "NPR", "NZD", "OMR", "PAB",
            "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RUR", "RWF", "SAR",
            "SBD", "SDR", "SGD", "SHP", "SRD", "SYP", "SZL", "TMT", "TND", "TOP", "TRY", "TWD",
            "UGX", "USD", "UYU", "VUV", "WST", "XAF", "XDR", "XOF", "XPF", "ZWL"};
        setFromCurrencyBox(new JComboBox<>(currencies));
        getFromCurrencyBox().setBounds(NUM_150, NUM_100, NUM_150, NUM_25);
        add(getFromCurrencyBox());

        // aim currency
        setBound(currencies);

        setOutputAmountField(new JTextField());
        getOutputAmountField().setBounds(NUM_150, NUM_200, NUM_150, NUM_25);
        getOutputAmountField().setEditable(false);
        add(getOutputAmountField());

        creatButton(currencyExchangeController);
        setLocationRelativeTo(null);
    }

    private void setBound(String[] currencies) {
        final JLabel toLabel = new JLabel("To Currency:");
        toLabel.setBounds(NUM_50, NUM_150, NUM_100, NUM_25);
        add(toLabel);

        setToCurrencyBox(new JComboBox<>(currencies));
        getToCurrencyBox().setBounds(NUM_150, NUM_150, NUM_150, NUM_25);
        add(getToCurrencyBox());

        // output Amount
        final JLabel outputLabel = new JLabel("Output Amount:");
        outputLabel.setBounds(NUM_50, NUM_200, NUM_100, NUM_25);
        add(outputLabel);
    }

    private void creatButton(CurrencyExchangeController currencyExchangeController) {
        // exchange button
        setExchangeButton(new JButton("Exchange Currency"));
        getExchangeButton().setBounds(NUM_50, NUM_250, NUM_200, NUM_30);
        add(getExchangeButton());
        getExchangeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrencyExchangeView.this.exchangeCurrency(
                        CurrencyExchangeView.this.getFromCurrencyBox(),
                        CurrencyExchangeView.this.getInputAmountField(),
                        CurrencyExchangeView.this.getToCurrencyBox());
            }
        });

        // back button
        setBackButton(new JButton("Back to Main"));
        getBackButton().setBounds(NUM_400, NUM_300, NUM_150, NUM_30);
        add(getBackButton());
        getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currencyExchangeController.goBackToBaseView();
            }
        });
    }

    /**
     * Used to get the amount.
     * @param fromcurrencybox from box
     * @param tocurrencybox to box
     * @param inputamountfield input num
     */
    public void exchangeCurrency(JComboBox<String> fromcurrencybox,
                                 JTextField inputamountfield,
                                 JComboBox<String> tocurrencybox) {
        try {
            final double inputAmount = Double.parseDouble(inputamountfield.getText());
            final String fromCurrency = (String) fromcurrencybox.getSelectedItem();
            final String toCurrency = (String) tocurrencybox.getSelectedItem();

            CurrencyExchangeDataUsage.execute(fromCurrency, toCurrency);
            final double outputAmount = inputAmount * CurrencyExchangeController.getRate();
            getOutputAmountField().setText(String.format("%.2f", outputAmount));
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input Amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setInputAmountField(JTextField inputAmountField) {
        this.inputAmountField = inputAmountField;
    }

    public void setOutputAmountField(JTextField outputAmountField) {
        this.outputAmountField = outputAmountField;
    }

    public void setFromCurrencyBox(JComboBox<String> fromCurrencyBox) {
        this.fromCurrencyBox = fromCurrencyBox;
    }

    public void setToCurrencyBox(JComboBox<String> toCurrencyBox) {
        this.toCurrencyBox = toCurrencyBox;
    }

    public void setExchangeButton(JButton exchangeButton) {
        this.exchangeButton = exchangeButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}
