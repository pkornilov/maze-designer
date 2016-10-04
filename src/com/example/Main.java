/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example;

/**
 * File name: Main.java
 * Description: A maze designer application made with Java
 * Revision history:
 *          Dec 3, 2014: Implemented GUI
 *          Dec 4, 2014: Added functionality and comments
 * 
 * @author Philippe Kornilov
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Creates an instance of Form class
        Form f = new Form();
        
        // Sets the form's size and title
        f.setSize(580, 360);
        f.setTitle("Maze Designer");
    }
    
}
