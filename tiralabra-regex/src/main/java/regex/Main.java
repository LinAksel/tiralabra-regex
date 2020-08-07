/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;


/**
 *
 * @author linaksel
 */
public class Main {
    
    /**
     * Käynnistämiseen valjastettu main luo uuden regex-olion ja ajaa sen.
     * @param args 
     */
    public static void main(String[] args) {
    	UI ui = new UI(new KonsoliIO());
        ui.suorita();
    }
    
}
