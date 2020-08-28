/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import regex.io.KonsoliIO;
import regex.ui.UI;

/**
 * Vaihtoehtoinen, tekstipohjaisen (TUI) käyttöliittymän käynnistys.
 * @author linaksel
 */
public class MainTUI {
    
    public static void main(String[] args) {
    	UI ui = new UI(new KonsoliIO());
        ui.suorita();
    }
}
