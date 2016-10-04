/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * File name: Form.java
 * Description: A maze designer application made with Java
 * Revision history:
 *          Dec 3, 2014: Implemented GUI
 *          Dec 4, 2014: Added functionality and comments
 * 
 * @author Philippe Kornilov
 */
public class Form extends JFrame {
   
    // Global variables for JPanel, JButton, JTextField and JLabel objects
    private JPanel toolPanel, optionPanel, gridPanel;
    private JPanel[][] mazeArray;
    private JButton btnGenerate, btnSave, btnLoad;
    private JButton btnEmpty, btnRed, btnBlue, btnGreen, btnYellow, btnPink,
            btnCyan, btnMagenta, btnWhite, btnWall;
    private JTextField rowText, colText;
    private JLabel rowsLabel;
    
    // Global variables for colour management
    private Color[] colours = new Color[] { Color.LIGHT_GRAY, Color.RED,
        Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK, Color.CYAN,
        Color.MAGENTA, Color.WHITE, Color.BLACK };
    private Color selectedColour = colours[0];
    
    // Global variables for I/O management
    private String output = "";
    private String input = "";
    
    /**
     * Initializes the Form class
     */
    public Form() {
        //
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        ButtonHandler buttonHandler = new ButtonHandler();
        
        
        // TOOL PANEL
        
        // Initializes tool panel
        toolPanel = new JPanel();
        toolPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Tool panel controls
        rowsLabel = new JLabel("Rows:");
        rowText = new JTextField(5);
        colText = new JTextField(5);
        rowText.setText("5");
        colText.setText("5");
        btnGenerate = new JButton("Generate");
        btnSave = new JButton("Save");
        btnLoad = new JButton("Load");
        
        // Adds controls to tool panel
        toolPanel.add(rowsLabel);
        toolPanel.add(rowText);
        toolPanel.add(colText);        
        toolPanel.add(btnGenerate);
        toolPanel.add(btnSave);
        toolPanel.add(btnLoad);
        
        // Adds button handler
        btnGenerate.addActionListener(buttonHandler);
        btnSave.addActionListener(buttonHandler);
        btnLoad.addActionListener(buttonHandler);
        
        // Adds tool panel to the frame
        this.add(toolPanel, BorderLayout.NORTH);
        
        
        // OPTION PANEL
        
        // Initializes option panel
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(10, 1));
        optionPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Option panel controls
        btnEmpty = new JButton("Empty Slot");
        btnRed = new JButton("Red Box");
        btnBlue = new JButton("Blue Box");
        btnGreen = new JButton("Green Box");
        btnYellow = new JButton("Yellow Box");
        btnPink = new JButton("Pink Door");
        btnCyan = new JButton("Cyan Door");
        btnMagenta = new JButton("Magenta Door");
        btnWhite = new JButton("White Door");
        btnWall = new JButton("Wall");   
        
        // Adds controls to option panel
        optionPanel.add(btnEmpty);
        optionPanel.add(btnRed);
        optionPanel.add(btnBlue);
        optionPanel.add(btnGreen);
        optionPanel.add(btnYellow);
        optionPanel.add(btnPink);
        optionPanel.add(btnCyan);
        optionPanel.add(btnMagenta);
        optionPanel.add(btnWhite);
        optionPanel.add(btnWall);
        
        // Adds button handler
        btnEmpty.addActionListener(buttonHandler);
        btnRed.addActionListener(buttonHandler);
        btnBlue.addActionListener(buttonHandler);
        btnGreen.addActionListener(buttonHandler);
        btnYellow.addActionListener(buttonHandler);
        btnPink.addActionListener(buttonHandler);
        btnCyan.addActionListener(buttonHandler);
        btnMagenta.addActionListener(buttonHandler);
        btnWhite.addActionListener(buttonHandler);
        btnWall.addActionListener(buttonHandler);
        
        // Adds option panel to the frame
        this.add(optionPanel, BorderLayout.WEST);
        
        
        // GRID PANEL
        
        // Initializes grid panel
        gridPanel = new JPanel();
        gridPanel.setBorder(BorderFactory.createEtchedBorder());
           
        // Adds grid panel to the frame
        this.add(gridPanel, BorderLayout.CENTER);
      
        
        //Packs the frame and makes it visible
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Initiates a new maze
     * @param row Number of rows
     * @param col Number of columns
     */
    private void newMaze(int row, int col) {
        
        // Local 2-D array for colour numbers
        int[][] colourArray = new int[row][col];
        
        // Assigns 0 (light grey) to each array element
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                colourArray[i][j] = 0;
            }
        }
        
        createMaze(colourArray);
    }
    
    /**
     * Creates mazes and adds them to the frame
     * @param array 
     */
    private void createMaze(int[][] array) {
       
        // Local variables for rows and columns
        int row = array.length;
        int col = array[0].length;
        
        // Lays the panel new
        mazeArray = new JPanel[row][col];
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(row, col));
        Border border = BorderFactory.createLineBorder(Color.black, 1);
        MouseHandler mouseHandler = new MouseHandler();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mazeArray[i][j] = new JPanel();
                mazeArray[i][j].setBorder(border);
                mazeArray[i][j].setBackground(colours[array[i][j]]);
                mazeArray[i][j].addMouseListener(mouseHandler);
                gridPanel.add(mazeArray[i][j]);
                gridPanel.revalidate();
                gridPanel.repaint();
            }       
        }
    }
    
    /**
     * Button handlers for all buttons on the frame
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton btn = (JButton) ae.getSource();

            // Click event handler for Generate button
            if (btn == btnGenerate) {
                try {
                    int row = Integer.parseInt(rowText.getText());
                    int col = Integer.parseInt(colText.getText());
                    newMaze(row, col);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null,
                        "Rows must be integer");
                }
            }
            
            // Click event handler for Generate button
            if (btn == btnSave) {
                try {
                    int rows = mazeArray.length;
                    int cols = mazeArray[0].length;
                    output = rows + "\n" + cols + "\n";
                
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            output += Arrays.asList(colours).
                                indexOf(mazeArray[i][j].getBackground()) + "\n";
                        }
                    }
                    
                    doSave();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error on file save");
                }
                output = "";
            }
            
            // Click event handler for Generate button
            if (btn == btnLoad) {
                try {
                    doLoad();
                    
                    String[] inputLines = input.split("\n");
                    
                    int row = Integer.parseInt(inputLines[0]);
                    int col = Integer.parseInt(inputLines[1]);
                    
                    int[][] inputArray = new int[row][col];
                    int count = 2;
                    
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (count <= inputLines.length) {
                                inputArray[i][j] = Integer.
                                        parseInt(inputLines[count]);
                                count++;
                            }
                        }
                    }
                    
                    createMaze(inputArray);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error on file load");
                }
                input = "";
            }
            
            // Click event handlers for option buttons
            if (btn == btnEmpty) {
                selectedColour = colours[0];
            }
            if (btn == btnRed) {
                selectedColour = Color.RED;
            }
            if (btn == btnBlue) {
                selectedColour = colours[2];
            }
            if (btn == btnGreen) {
                selectedColour = colours[3];
            }
            if (btn == btnYellow) {
                selectedColour = colours[4];
            }
            if (btn == btnPink) {
                selectedColour = colours[5];
            }
            if (btn == btnCyan) {
                selectedColour = colours[6];
            }
            if (btn == btnMagenta) {
                selectedColour = colours[7];
            }
            if (btn == btnWhite) {
                selectedColour = colours[8];
            }
            if (btn == btnWall) {
                selectedColour = colours[9];
            }
        }
    }
    
    /**
     * Saves the current maze layout
     * @throws Exception 
     */
    private void doSave() throws Exception
    {
        JFileChooser dlg = new JFileChooser();
        int r = dlg.showSaveDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            FileWriter writer = new FileWriter(dlg.getSelectedFile());
            writer.write(output);
            writer.close();
            JOptionPane.showMessageDialog(this, "Maze saved");
        }
    }
    
    /**
     * Loads a previously created maze
     * @throws Exception 
     */
    private void doLoad() throws Exception
    {
        JFileChooser dlg = new JFileChooser();
        int r = dlg.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            FileReader fr = new FileReader(dlg.getSelectedFile());
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {                
                input += line + "\n";
            }
            br.close();
            fr.close();
        }
    }
    
    /**
     * Mouse handler for all objects
     */
    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            // Paints a panel
            JPanel panelClick = (JPanel)me.getSource();
            panelClick.setBackground(selectedColour);
        }

        @Override
        public void mousePressed(MouseEvent me) {
           
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            
        }

        @Override
        public void mouseEntered(MouseEvent me) {
           
        }

        @Override
        public void mouseExited(MouseEvent me) {
           
        }
    }    
}