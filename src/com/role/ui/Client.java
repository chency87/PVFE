package com.role.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class Client extends JFrame {

    public Client() {

        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        panel.setLayout(null);

        JButton button1 = new JButton("DoCompute");
        button1.setLocation(60, 50);
        button1.setSize(80, 40);
        button1.setHorizontalTextPosition(JButton.CENTER);
        button1.setVerticalTextPosition(JButton.BOTTOM);
        button1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        // TODO
        button1.addActionListener(null);

        JButton button2 = new JButton("DoCompute");
        button2.setLocation(180, 50);
        button2.setSize(80, 40);
        button2.setHorizontalTextPosition(JButton.CENTER);
        button2.setVerticalTextPosition(JButton.BOTTOM);
        button2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        // TODO
        button2.addActionListener(null);

        JTextArea jta = new JTextArea();
        jta.setLocation(60, 120);
        jta.setSize(550, 240);
        jta.setLineWrap(true);// 激活自动换行功能
        jta.setWrapStyleWord(true);// 激活断行不断字功能
        jta.setBackground(Color.gray);
        jta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        panel.add(button1);
        panel.add(button2);
        panel.add(jta);

        this.setTitle("Servcer");
        this.setLocation(800, 400);
        this.setSize(680, 430);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
    }

    public static void main(String[] args) {
        new Client();
    }
}
