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
     * Tästä pakataan lopulta kolme eri ohjelmaa: GUI-versio, konsoli-UI-versio ja suorituskykytestaaja.
     * Jaetussa koodissa on vakioasetuksena pelkkä GUI, mutta tätä voi muuttaa poistamalla kommentointi,
     * joskaan tämä ei ole tarkoituksenmukaista. Loppupalautukseen mahdollisesti jokin toinen ratkaisu.
     * @param args 
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.suorita();
    	//UI ui = new UI(new KonsoliIO());
        //ui.suorita();
        //Performance suoritus = new Performance();
        //suoritus.sulkuTestaus();
    }
    
}
