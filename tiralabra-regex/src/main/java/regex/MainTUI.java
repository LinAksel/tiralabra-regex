/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import regex.io.ConsoleIO;
import regex.ui.UI;

/**
 * Vaihtoehtoinen, tekstipohjaisen (TUI) käyttöliittymän käynnistys.
 * @author linaksel
 */

public class MainTUI {

    /**
     * Tekstipohjaisen käyttöliittymän käynnistävä main-metodi.
     * @param args 
     */
    
    public static void main(String[] args) {
    	UI ui = new UI(new ConsoleIO());
        ui.start();
    }
}
