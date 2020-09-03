/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import regex.ui.GUI;


/**
 * Ohjelman käynnistävä pääluokka.
 * @author linaksel
 */

public class Main {
    
    /**
     * Ohjelman käynnistävä metodi. Tämä avaa GUI-tyyppisen käyttöliittymän.
     * @param args 
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.suorita();
    }
    
}
