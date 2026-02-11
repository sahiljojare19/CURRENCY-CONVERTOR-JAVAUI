package converter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Aboutpanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private Currency_converter cn = new Currency_converter();

    public Aboutpanel() {

        setTitle("About");
        setType(Type.POPUP);
        setResizable(false);
        setBounds(100, 100, 735, 464);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/Images/icon.png")));

        getContentPane().setBackground(new Color(70, 35, 110));
        getContentPane().setLayout(null);

        cn.mainpage.setVisible(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cn.frmCurrencyConverter.setVisible(true);
                cn.mainpage.setVisible(true);
            }
        });

        /* ===== TITLE ===== */
        JLabel title = new JLabel("CURRENCY CONVERTER", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(220, 190, 255));
        title.setBounds(0, 10, 445, 40);
        getContentPane().add(title);

        /* ===== DESCRIPTION TEXT ===== */
        JTextPane description = new JTextPane();
        description.setEditable(false);
        description.setOpaque(false);
        description.setBorder(new EmptyBorder(10, 20, 10, 20));
        description.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        description.setForeground(new Color(210, 190, 240));

        description.setText(
                "Currency Converter is developed using Java and a free Exchange Rates API.\n\n"
              + "Currently supports 30 international currencies with real-time conversion.\n\n"
              + "Designed with a clean and user-friendly interface."
        );

        description.setBounds(10, 60, 425, 135);
        getContentPane().add(description);

        /* ===== AUTHOR NAME ===== */
        JLabel author = new JLabel("Made by Sahil Jojare", SwingConstants.CENTER);
        author.setFont(new Font("Segoe UI", Font.BOLD, 15));
        author.setForeground(new Color(255, 180, 180));
        author.setBounds(0, 200, 445, 25);
        getContentPane().add(author);
    }
}
