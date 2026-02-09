package converter;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class conveter_frame extends JFrame {

    private JPanel contentPane;
    private JTextField curren;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public conveter_frame() {

        setTitle("Converter");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ===== MAIN BACKGROUND ===== */
        contentPane = new JPanel(null);
        contentPane.setBackground(new Color(255, 182, 193)); // soft pink
        setContentPane(contentPane);

        /* ===== TITLE ===== */
        JLabel title = new JLabel("Currency Converter", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setBounds(40, 20, 460, 40);
        contentPane.add(title);

        /* ===== AMOUNT ===== */
        JLabel lblAmount = new JLabel("Amount :");
        lblAmount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblAmount.setBounds(40, 90, 120, 25);
        contentPane.add(lblAmount);

        curren = new JTextField();
        curren.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        curren.setBounds(40, 120, 220, 35);
        curren.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        contentPane.add(curren);

        /* ===== FROM ===== */
        JLabel lblFrom = new JLabel("From :");
        lblFrom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblFrom.setBounds(280, 90, 100, 25);
        contentPane.add(lblFrom);

        JComboBox from = new JComboBox(getCurrencies());
        from.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        from.setBounds(280, 120, 100, 35);
        contentPane.add(from);

        /* ===== TO ===== */
        JLabel lblTo = new JLabel("To :");
        lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTo.setBounds(400, 90, 100, 25);
        contentPane.add(lblTo);

        JComboBox to1 = new JComboBox(getCurrencies());
        to1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        to1.setBounds(400, 120, 100, 35);
        contentPane.add(to1);

        /* ===== RATE ===== */
        JLabel lblRate = new JLabel("Rate :");
        lblRate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblRate.setBounds(40, 190, 80, 25);
        contentPane.add(lblRate);

        JLabel rate = new JLabel("-");
        rate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        rate.setBounds(120, 190, 300, 25);
        contentPane.add(rate);

        /* ===== RESULT ===== */
        JLabel lblResult = new JLabel("Result :");
        lblResult.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblResult.setBounds(40, 230, 80, 25);
        contentPane.add(lblResult);

        JLabel result = new JLabel("-");
        result.setFont(new Font("Segoe UI", Font.BOLD, 20));
        result.setForeground(new Color(0, 120, 0));
        result.setBounds(120, 230, 300, 25);
        contentPane.add(result);

        /* ===== CONVERT BUTTON ===== */
        JButton convertbtn = new JButton("Convert");
        convertbtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        convertbtn.setBackground(new Color(245, 235, 140));
        convertbtn.setFocusPainted(false);
        convertbtn.setBounds(160, 300, 230, 55);
        contentPane.add(convertbtn);

        convertbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    backend bk = new backend(
                            from.getSelectedItem().toString(),
                            to1.getSelectedItem().toString(),
                            Double.parseDouble(curren.getText())
                    );

                    if (bk.checkstring()) {
                        rate.setText(bk.getRate() + " " + to1.getSelectedItem());
                        result.setText(bk.getConvertedCurrency() + " " + to1.getSelectedItem());
                    } else {
                        JOptionPane.showMessageDialog(convertbtn, "Currencies should not be same");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(convertbtn, "Enter valid amount");
                }
            }
        });

        /* ===== RIGHT SCROLLABLE CURRENCY LIST ===== */
        JPanel listPanel = new JPanel(null);
        listPanel.setBackground(Color.WHITE);
        listPanel.setPreferredSize(new Dimension(330, 900));

        Font listFont = new Font("Segoe UI", Font.BOLD, 14);
        Color blue = new Color(0, 102, 204);

        String[][] currencies = {
                {"USD","US Dollar"},{"INR","Indian Rupee"},{"EUR","Euro"},{"GBP","British Pound"},
                {"AUD","Australian Dollar"},{"CAD","Canadian Dollar"},{"CHF","Swiss Franc"},
                {"CNY","Chinese Yuan"},{"JPY","Japanese Yen"},{"KRW","South Korean Won"},
                {"HKD","Hong Kong Dollar"},{"SGD","Singapore Dollar"},{"NZD","New Zealand Dollar"},
                {"ZAR","South African Rand"},{"BRL","Brazilian Real"},{"MXN","Mexican Peso"},
                {"MYR","Malaysian Ringgit"},{"THB","Thai Baht"},{"TRY","Turkish Lira"},
                {"NOK","Norwegian Krone"},{"SEK","Swedish Krona"},{"DKK","Danish Krone"},
                {"PLN","Polish Zloty"},{"CZK","Czech Koruna"},{"HUF","Hungarian Forint"},
                {"RON","Romanian Leu"},{"ILS","Israeli Shekel"},{"IDR","Indonesian Rupiah"},
                {"PHP","Philippine Peso"},{"ISK","Icelandic Krona"}
        };

        int y = 15;
        for (String[] c : currencies) {
            JLabel lbl = new JLabel(c[0] + "  =  " + c[1]);
            lbl.setFont(listFont);
            lbl.setForeground(blue);
            lbl.setBounds(15, y, 300, 25);
            listPanel.add(lbl);
            y += 28;
        }

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBounds(520, 20, 350, 520);
        scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contentPane.add(scroll);

        setVisible(true);
    }

    private Vector<String> getCurrencies() {
        Vector<String> vc = new Vector<>();
        try (Scanner sc = new Scanner(new FileReader("src/codes.txt"))) {
            while (sc.hasNext()) vc.add(sc.next());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "codes.txt not found!");
        }
        return vc;
    }
}
