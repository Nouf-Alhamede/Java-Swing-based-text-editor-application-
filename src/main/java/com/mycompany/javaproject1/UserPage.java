/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject1;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserPage extends JFrame {
    private JLabel l1, l2;
    private JTextField t1;
    private JTextArea tA;
    private JButton b1, b2, b3;
    
    public UserPage(String title) {
        super(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("File");
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(l1);
        
        l2 = new JLabel("Title: ");
        t1 = new JTextField(20);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(l2);
        p2.add(t1);
        
        tA = new JTextArea(20, 50);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(tA);
        
        b1 = new JButton("Save");
        b2 = new JButton("Search");
        b3 = new JButton("Clear");
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p4.add(b1);
        p4.add(b2);
        p4.add(b3);
        
        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        
        this.setLocation(500, 300);
        this.pack();
        
        b1.addActionListener(new saveListener());
        b2.addActionListener(new searchListener());
        b3.addActionListener(new clearListener());
    }
    
    class saveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                BufferedWriter writeFile = new BufferedWriter(new FileWriter(t1.getText() + ".txt"));
                writeFile.write(tA.getText());
                writeFile.newLine();
                writeFile.close();
                JOptionPane.showMessageDialog(null, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioE) {
                System.out.println("IOException");
                JOptionPane.showMessageDialog(null, "Error, File didnt save", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    class searchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SearchPage sPage = new SearchPage("Search", UserPage.this);
            setVisible(false);
        }
    }
    
    class clearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            t1.setText("");
            tA.setText("");
        }
    }
}