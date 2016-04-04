/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Fernandez
 */
public class dropdown extends JApplet {

//    private final String[] description = {""};

    private JTextField t = new JTextField(15);
    private JComboBox c = new JComboBox();
    private JTextField k = new JTextField(15);
    private JComboBox d = new JComboBox();

    private JButton b = new JButton("Add");

    private int count = 0;
    private int count2 = 0;
  
    
    public dropdown(final String[] description) throws FileNotFoundException, IOException {
        for (int i = 0; i < description.length; i++) {
            c.addItem(description[count++]);
            
        }
        t.setEditable(false);
//        BufferedReader reader = new BufferedReader(new FileReader("movies.txt"));
//        String word = reader.readLine();
//        String[] split = reader.readLine().split("/");
//
//        List<String> words = new ArrayList<String>();
//        
//        while (word != null) {
//            words.add(word);
//            word = reader.readLine();
//        }
//        Random r = new Random();
//        String randomWord = words.get(r.nextInt(words.size()));
//        JOptionPane.showMessageDialog(null,randomLine);
        
FileInputStream wordsFile = new FileInputStream("movies.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(wordsFile));

    
    String line = reader.readLine();
    List<String> words = new ArrayList<String>();
    while(line != null) {
        String[] wordsLine = line.split("/");
        for(String word : wordsLine) {
            words.add(word);
        }
        line = reader.readLine();
    }

    Random rand = new Random(System.currentTimeMillis());
    String randomWord = words.get(rand.nextInt(words.size()));
    
        for (int j = 0; j < words.size; j++) {
            d.addItem(randomWord[count++]);
        }
        k.setEditable(false);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (count < description.length) {
                    c.addItem(description[count++]);
                    d.addItem(description[count++]);
                }
            }
        });
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.setText("     " + c.getSelectedIndex() + "   "
                        + ((JComboBox) e.getSource()).getSelectedItem());
            }
        });
        d.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                k.setText("     " + d.getSelectedIndex() + "   "
                        + ((JComboBox) e.getSource()).getSelectedItem());
            }
        });

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(t);
         cp.add(k);
        cp.add(c);
       
        cp.add(d);
        
        cp.add(b);
    }

    public static void main(String[] args) {
//        run(new dropdown(), 300, 300);
    }

    public static void run(JApplet applet, int width, int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(width, height);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
} ///:~

