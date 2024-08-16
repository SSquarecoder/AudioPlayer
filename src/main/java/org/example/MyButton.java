package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class MyButton {
    public static  Container C;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Action");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,1000,500);
        C = frame.getContentPane();
        C.setLayout(null);

        JButton L1 = new JButton("Level1");
        JButton L2 = new JButton("Level 2");
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");

        L1.setBounds(100,100,1000,50);
        L2.setBounds(200,100,1000,50);
        restart.setBounds(300,100,1000,50);
        quit.setBounds(400,100,1000,50);
//        btn.setSize(120,30);
//        btn.setLocation(100,100);
//        C.add(btn);
        C.add(L1);
        C.add(L2);
        C.add(restart);
        C.add(quit);

//        L1.addActionListener();

        frame.setVisible(true);
    }
}

class L1Class implements ActionListener {
    public void actionPerformed(ActionEvent e){
        MyButton.C.setBackground(Color.gray);
        BattleShip war = new BattleShip();
        war.initBoard();
        war.dispBoard();
        war.placeMark(8, 3, 'X');
        war.dispBoard();
    }
}