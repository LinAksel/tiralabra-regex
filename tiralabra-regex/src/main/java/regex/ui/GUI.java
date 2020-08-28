/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.ui;

import regex.logic.Tarkastaja;
import regex.logic.Regex;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author linaksel
 */
public class GUI {
    /**
     * GUI:n suoritusmetodi. Tässä luodaan komponentit ja kuuntelijat käyttöliittymään, kaikki algoritmiikka on eriytetty muualle.
     */
    public static void suorita() {
        JFrame frame = new JFrame("RegEx");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Regex regex = new Regex();
        regex.setRegex("");
        regex.setSana("");
        
        JPanel kentat = new JPanel();
        JPanel lokit = new JPanel();
        
        JLabel l1 = new JLabel("Current regex: ");
        
        JTextField regexfield = new JTextField();
        JButton regexrewind = new JButton("" + (char) 9194);
        JButton regexenter = new JButton("Enter regex");
        
        JTextField stringfield = new JTextField();
        JButton stringrewind = new JButton("" + (char) 9194);
        JButton stringenter = new JButton("Enter string");
        
        JTextArea loki = new JTextArea();
        loki.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(loki);
        
        ActionListener kuuntelijaR = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String reggex = regexfield.getText();
                Tarkastaja tarkastaja = new Tarkastaja(reggex);
                if (tarkastaja.tarkasta()) {
                    loki.append("New regex set: " + reggex + "\n");
                    loki.setCaretPosition(loki.getDocument().getLength());
                    regex.setRegex(reggex);
                    l1.setText("Current regex: " + reggex);
                    regexfield.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, tarkastaja.getViesti());
                }
            }
        };
        
        ActionListener kuuntelijaS = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String syote = stringfield.getText();
                regex.setSana(syote);
                regex.tulkki("", regex.getRegex().length() - 1);
                if (regex.getFound()) {
                    loki.append(syote + " " + (char) 10004 + "\n");
                    lokit.setBackground(Color.green);
                    kentat.setBackground(Color.green);
                } else {
                    loki.append(syote + " " + (char) 10060 + "\n");
                    lokit.setBackground(Color.red);
                    kentat.setBackground(Color.red);
                }
                regex.reset();
                stringfield.setText("");
            }
        };
        
        ActionListener buttonR = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regexfield.setText(regex.getRegex());
            }
        };
        
        ActionListener buttonS = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stringfield.setText(regex.getSana());
            }
        };
        
        stringfield.setPreferredSize(new Dimension(360, 20));
        regexfield.setPreferredSize(new Dimension(360, 20));
        scrollPane.setPreferredSize(new Dimension(350, 200));
        
        regexfield.addActionListener(kuuntelijaR);
        stringfield.addActionListener(kuuntelijaS);
        regexrewind.addActionListener(buttonR);
        regexenter.addActionListener(kuuntelijaR);
        stringrewind.addActionListener(buttonS);
        stringenter.addActionListener(kuuntelijaS);
        
        kentat.add(regexfield);
        kentat.add(regexenter);
        kentat.add(regexrewind);
        kentat.add(stringfield);
        kentat.add(stringenter);
        kentat.add(stringrewind);
        kentat.add(l1);
        
        lokit.add(scrollPane);
        
        kentat.setPreferredSize(new Dimension(540, 90));
        frame.add(kentat, BorderLayout.NORTH);
        frame.add(lokit, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
}
