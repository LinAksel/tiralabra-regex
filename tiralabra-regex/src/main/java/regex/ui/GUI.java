/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.ui;

import regex.logic.Validator;
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
        regex.setString("");
        
        JPanel fields = new JPanel();
        JPanel logs = new JPanel();
        
        JLabel l1 = new JLabel("Current regex: ");
        
        JTextField regexfield = new JTextField();
        JButton regexrewind = new JButton("" + (char) 9194);
        JButton regexenter = new JButton("Enter regex");
        
        JTextField stringfield = new JTextField();
        JButton stringrewind = new JButton("" + (char) 9194);
        JButton stringenter = new JButton("Enter string");
        
        JTextArea log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        
        ActionListener listenerR = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newregex = regexfield.getText();
                Validator validator = new Validator(newregex);
                if (validator.validate()) {
                    log.append("New regex set: " + newregex + "\n");
                    log.setCaretPosition(log.getDocument().getLength());
                    regex.setRegex(newregex);
                    l1.setText("Current regex: " + newregex);
                    regexfield.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, validator.getMessage());
                }
            }
        };
        
        ActionListener listenerS = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = stringfield.getText();
                regex.setString(input);
                regex.translator("", regex.getRegex().length() - 1);
                if (regex.getFound()) {
                    log.append(input + " " + (char) 10004 + "\n");
                    logs.setBackground(Color.green);
                    fields.setBackground(Color.green);
                } else {
                    log.append(input + " " + (char) 10060 + "\n");
                    logs.setBackground(Color.red);
                    fields.setBackground(Color.red);
                }
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
                stringfield.setText(regex.getString());
            }
        };
        
        stringfield.setPreferredSize(new Dimension(360, 20));
        regexfield.setPreferredSize(new Dimension(360, 20));
        scrollPane.setPreferredSize(new Dimension(350, 200));
        
        regexfield.addActionListener(listenerR);
        stringfield.addActionListener(listenerS);
        regexrewind.addActionListener(buttonR);
        regexenter.addActionListener(listenerR);
        stringrewind.addActionListener(buttonS);
        stringenter.addActionListener(listenerS);
        
        fields.add(regexfield);
        fields.add(regexenter);
        fields.add(regexrewind);
        fields.add(stringfield);
        fields.add(stringenter);
        fields.add(stringrewind);
        fields.add(l1);
        
        logs.add(scrollPane);
        
        fields.setPreferredSize(new Dimension(540, 90));
        frame.add(fields, BorderLayout.NORTH);
        frame.add(logs, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
}
