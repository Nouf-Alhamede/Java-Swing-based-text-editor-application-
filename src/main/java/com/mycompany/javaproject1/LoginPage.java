/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject1;

import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    private JLabel l1, l2;
    private JTextField t1;
    private JPasswordField pass;
    private JButton enterButton, cancelButton;
    
    public LoginPage(String title) {
        super(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("Login");
        t1 = new JTextField(10);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(l1);
        p1.add(t1);
        
        l2 = new JLabel("Password");
        pass = new JPasswordField(10);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(l2);
        p2.add(pass);
        
        enterButton = new JButton("Enter");
        cancelButton = new JButton("Cancel");
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.add(enterButton);
        p3.add(cancelButton);
        
        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(new GridLayout(3, 2));
        p.add(p1);
        p.add(p2);
        p.add(p3);
        
        this.setLocation(600, 300);
        this.pack();
        
        enterButton.addActionListener(new enterButtonActionListener());
        cancelButton.addActionListener(new cancelButtonActionListener());
    }
    
    class enterButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = t1.getText();
            char[] password = pass.getPassword();
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://Login.accdb");
                if(c == null)
                    System.out.println("Connection Failed");
                else {
                    String query = "SELECT * FROM [Login] WHERE name = ? AND password = ?";
                    try (PreparedStatement pstmt = c.prepareStatement(query)){
                        pstmt.setString(1, name);
                        pstmt.setString(2, new String(password)); 
                        ResultSet rs = pstmt.executeQuery();
                    
                        if(rs.next()) {
                           if(rs.getBoolean(2)) {
                               AdminPage adPage = new AdminPage("Text Editor");
                               LoginPage.this.dispose();
                           }
                           else {
                               UserPage uPage = new UserPage("Text Editor");
                               LoginPage.this.dispose();
                           }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Check your login and password", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                   } 
                }
                } 
            catch (SQLException ex) {
                System.out.println("Exeception : SQL");
            }
        }
    }
    
    class cancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           t1.setText("");
           pass.setText("");
        }
    }
}