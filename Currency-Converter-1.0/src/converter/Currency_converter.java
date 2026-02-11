package converter;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Currency_converter {

    public JFrame frmCurrencyConverter;
    public JPanel mainpage;

    public Currency_converter() {
        initialize();
    }

    protected void initialize() {

        frmCurrencyConverter = new JFrame();
        frmCurrencyConverter.setTitle("Currency Converter");
        frmCurrencyConverter.setBounds(100, 100, 735, 464);
        frmCurrencyConverter.setResizable(false);
        frmCurrencyConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCurrencyConverter.setIconImage(
                Toolkit.getDefaultToolkit().getImage("src\\Images\\icon.png"));

        frmCurrencyConverter.getContentPane().setLayout(null);

        // MAIN BACKGROUND (SOFT PINK)
        mainpage = new JPanel();
        mainpage.setBackground(new Color(70, 35, 110));
        mainpage.setBounds(0, 0, 721, 427);
        mainpage.setLayout(null);
        frmCurrencyConverter.getContentPane().add(mainpage);

        // IMAGE PANEL (LEFT)
        JPanel imagepanel = new JPanel();
        imagepanel.setBackground(Color.WHITE);
        imagepanel.setBorder(new LineBorder(new Color(60, 60, 60), 3, true));
        imagepanel.setBounds(0, 0, 488, 427);
        imagepanel.setLayout(new GridLayout(1, 1));
        mainpage.add(imagepanel);

        JLabel mainpageimage = new JLabel();
        ImageIcon mainpageicon = new ImageIcon("src/Images/main_page.png");
        Image mainicon = mainpageicon.getImage().getScaledInstance(488, 427, Image.SCALE_SMOOTH);
        mainpageimage.setIcon(new ImageIcon(mainicon));
        imagepanel.add(mainpageimage);

        // BUTTON PANEL (RIGHT)
        JPanel buttonpanel = new JPanel();
        buttonpanel.setBackground(new Color(30, 30, 30));
        buttonpanel.setBounds(498, 29, 199, 359);
        buttonpanel.setLayout(new GridLayout(3, 1, 0, 35));
        mainpage.add(buttonpanel);

        Font buttonFont = new Font("Times New Roman", Font.BOLD, 26);
        Color buttonColor = new Color(222, 184, 135);

        // OPEN BUTTON
        JButton open = new JButton("Open");
        open.setToolTipText("Open Currency Converter");
        open.setBackground(buttonColor);
        open.setForeground(Color.DARK_GRAY);
        open.setFont(buttonFont);
        open.setFocusPainted(false);
        open.addActionListener(e -> {
            conveter_frame cn = new conveter_frame();
            cn.setVisible(true);
            frmCurrencyConverter.dispose();
        });
        buttonpanel.add(open);

        // ABOUT BUTTON
        JButton about = new JButton("About");
        about.setToolTipText("About this application");
        about.setBackground(buttonColor);
        about.setForeground(Color.DARK_GRAY);
        about.setFont(buttonFont);
        about.setFocusPainted(false);
        about.addActionListener(e -> {
            Aboutpanel ab = new Aboutpanel();
            ab.setVisible(true);
            frmCurrencyConverter.dispose();
        });
        buttonpanel.add(about);

        // EXIT BUTTON
        JButton exit = new JButton("Exit");
        exit.setToolTipText("Exit application");
        exit.setBackground(buttonColor);
        exit.setForeground(Color.DARK_GRAY);
        exit.setFont(buttonFont);
        exit.setFocusPainted(false);
        exit.addActionListener(e -> System.exit(0));
        buttonpanel.add(exit);
    }
}
