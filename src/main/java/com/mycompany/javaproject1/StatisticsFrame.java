/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaproject1;

/**
 *
 * @author hp
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author janax
 */
public class StatisticsFrame extends JFrame{
    private JLabel l1;
    private JTextField t1;
    private JButton b1;
    
    public StatisticsFrame(String title) {
        super(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("File for Statistics: ");
        t1 = new JTextField(20);
        b1 = new JButton("Calculate");
        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
        p.add(l1);
        p.add(t1);
        p.add(b1);
        this.setLocation(500, 300);
        this.pack();

        
        b1.addActionListener(new calculateListener());
    }
    
    public class calculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                BufferedReader readFile = new BufferedReader(new FileReader(t1.getText() + ".txt"));
                String line= null ,words[];
                int a = 0, b = 0;

                while ((line = readFile.readLine()) != null) {
                     words = line.split(" ");
                    
                    for (String word : words) {
                    if (word.matches(".*\\d.*")) {
                        int number = Integer.parseInt(word);
                        if (number >= 10) {
                            a++;
                        } else {
                            b++;
                        }
                    }
                    }
                }
                readFile.close();
                JOptionPane.showMessageDialog(null, "Number of lines with numbers >= 10: " + a + "\nNumber of lines with numbers < 10: " + b, "Statistics", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (FileNotFoundException fE) {
                    System.out.println("File not found");
                }
                catch(IOException ioe) {
                    System.out.println("IOException");
                }
        }
    }
}
