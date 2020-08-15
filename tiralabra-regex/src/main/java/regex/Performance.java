/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 * Tämä on testiluokista eriytetty suorituskykytestaaja.
 * @author linaksel
 */
public class Performance {
    
    private Regex regex = new Regex();
    
    
    /**
     * Metodi testaa sulkuryhmien toiston tehokkuutta eri sulkutasoilla
     */
    public void sulkuTestaus() {
        regex.setRegex("(a)*");
        regex.setSana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
        while (regex.getRegex().length() < 65)  {
            long alku = System.currentTimeMillis();
            regex.tulkki("", regex.getRegex().length() - 1);
            long aika = System.currentTimeMillis() - alku;
            System.out.println(aika + " tunnistettu: " + regex.getFound());
            regex.setRegex("(" + regex.getRegex() + ")*");
        }
    }
    
}
