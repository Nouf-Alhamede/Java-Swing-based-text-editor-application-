/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject1;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchPage extends JFrame{
    private JLabel l1, l2;
    private JTextField t1, t2;
    private JTextArea tA;
    private JButton b1, b2, b3;
    private JFrame prevPage;
    
    public SearchPage(String title, JFrame prevPage) {
        super(title);
        this.prevPage = prevPage;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("File to Search");
        t1 = new JTextField(30);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(l1);
        p1.add(t1);
        
        l2 = new JLabel("Keywords: ");
        t2 = new JTextField(15);
        b1 = new JButton("Search");
        b2 = new JButton("Claear");
        b3 = new JButton("Cancel");
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(l2);
        p2.add(t2);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        
        tA = new JTextArea(20, 50);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(tA);        
        
        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(p1);
        p.add(p2);
        p.add(p3);
        
        this.setLocation(500, 300);
        this.pack();
        
        b1.addActionListener(new searchListener());
        b2.addActionListener(new clearListener());
        b3.addActionListener(new cancelListener());
    }
    
    class searchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BufferedReader readFile = new BufferedReader(new FileReader(t1.getText() + ".txt"));
                String line;
                String keywords[];
                 keywords = t2.getText().split(" ");
                
                while ((line = readFile.readLine()) != null) {
                    for(int i = 0; i < keywords.length; i++) {
                      if (line.contains(keywords[i])) {
                        tA.append(line);
                        tA.append("\n");
                        }  
                    }   
                }
                readFile.close();
            }
            catch (FileNotFoundException fE) {
                JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException iE) {
                JOptionPane.showMessageDialog(null, "Input Output Exception", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class clearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            t1.setText("");
            t2.setText("");
            tA.setText("");
        }
    }
    
    class cancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prevPage.setVisible(true);
            SearchPage.this.dispose();
        }
    }
}
