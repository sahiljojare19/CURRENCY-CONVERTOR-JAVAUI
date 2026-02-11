package converter;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class conveter_frame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField curren;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public conveter_frame() {

        setTitle("CURRENCY CONVERTER");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ===== MAIN BACKGROUND ===== */
        contentPane = new JPanel(null);
        contentPane.setBackground(new Color(70, 35, 110));
        setContentPane(contentPane);

        /* ===== TITLE ===== */
        JLabel title = new JLabel("CURRENCY CONVERTER", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(220, 190, 255));
        title.setBounds(40, 20, 460, 40);
        contentPane.add(title);

        /* ===== AMOUNT ===== */
        JLabel lblAmount = new JLabel("Amount :");
        lblAmount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblAmount.setForeground(Color.WHITE);
        lblAmount.setBounds(40, 90, 120, 25);
        contentPane.add(lblAmount);

        curren = new JTextField();
        curren.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        curren.setBounds(40, 120, 220, 35);
        curren.setBorder(BorderFactory.createLineBorder(new Color(180, 140, 220), 2));
        curren.setBackground(Color.WHITE);
        contentPane.add(curren);

        /* ===== FROM ===== */
        JLabel lblFrom = new JLabel("From :");
        lblFrom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblFrom.setForeground(Color.WHITE);
        lblFrom.setBounds(280, 90, 100, 25);
        contentPane.add(lblFrom);

        JComboBox from = new JComboBox(getCurrencies());
        from.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        from.setBounds(280, 120, 100, 35);
        from.setBackground(Color.WHITE);           // ← CHANGED TO WHITE
        from.setForeground(Color.BLACK);           // ← Changed to black so text is visible
        from.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 200), 1));
        from.setFocusable(false);                  // Removes ugly focus border
        contentPane.add(from);

        /* ===== TO ===== */
        JLabel lblTo = new JLabel("To :");
        lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTo.setForeground(Color.WHITE);
        lblTo.setBounds(400, 90, 100, 25);
        contentPane.add(lblTo);

        JComboBox to1 = new JComboBox(getCurrencies());
        to1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        to1.setBounds(400, 120, 100, 35);
        to1.setBackground(Color.WHITE);            // ← CHANGED TO WHITE
        to1.setForeground(Color.BLACK);            // ← Changed to black
        to1.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 200), 1));
        to1.setFocusable(false);                   // Removes ugly focus border
        contentPane.add(to1);

        /* ===== RATE ===== */
        JLabel lblRate = new JLabel("Rate :");
        lblRate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblRate.setForeground(Color.WHITE);
        lblRate.setBounds(40, 190, 80, 25);
        contentPane.add(lblRate);

        JLabel rate = new JLabel("-");
        rate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        rate.setForeground(new Color(200, 180, 240));
        rate.setBounds(120, 190, 300, 25);
        contentPane.add(rate);

        /* ===== RESULT ===== */
        JLabel lblResult = new JLabel("Result :");
        lblResult.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblResult.setForeground(Color.WHITE);
        lblResult.setBounds(40, 230, 80, 25);
        contentPane.add(lblResult);

        JLabel result = new JLabel("-");
        result.setFont(new Font("Segoe UI", Font.BOLD, 20));
        result.setForeground(new Color(180, 255, 180));
        result.setBounds(120, 230, 300, 25);
        contentPane.add(result);

        /* ===== CONVERT BUTTON ===== */
        JButton convertbtn = new JButton("Convert");
        convertbtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        convertbtn.setBackground(new Color(255, 193, 7));
        convertbtn.setForeground(Color.BLACK);
        convertbtn.setFocusPainted(false);
        convertbtn.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2, true));
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
                        rate.setText(String.format("%.4f", bk.getRate()) + " " + to1.getSelectedItem());
                        result.setText(String.format("%.2f", bk.getConvertedCurrency()) + " " + to1.getSelectedItem());
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
        listPanel.setBackground(new Color(50, 30, 90));
        listPanel.setPreferredSize(new Dimension(330, 900));

        Font listFont = new Font("Segoe UI", Font.BOLD, 14);
        Color textColor = new Color(220, 190, 255);

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
            JLabel lbl = new JLabel(c[0] + " = " + c[1]);
            lbl.setFont(listFont);
            lbl.setForeground(textColor);
            lbl.setBounds(15, y, 300, 25);
            listPanel.add(lbl);
            y += 28;
        }

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setBounds(520, 20, 350, 520);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(140, 100, 180), 2));
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
