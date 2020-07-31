/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.Scanner;

/**
 *
 * @author linaksel
 */
public class Regex {
    
    private String regex;
    private String sana;
    private Boolean lukko;
    private IO io;
    
    public Regex(IO io) {
    	this.io = io;
        this.lukko = false;
    }
    
    public void suorita() {
        regex = io.next();
        sana = io.next();
        tulkki("", regex.length() - 1);
        io.print(lukko.toString());
    }
    /** 
     * Metodi varmistaa, että liitettävä yksittäinen merkki ei ole jokin metamerkki.
     * @param kohta Säännöllisen lauseen kohta
     * @param testi Rakennettava merkkijono, jota verrataan sanaan.
     * @return Palauttaa joko annetun testijonon tai testijonon, johon lisätty kirjain
     */
    public String lisaaja(int kohta, String testi) {
        char merkki = regex.charAt(kohta);
        if (merkki != ')' && merkki != '(') {
            return merkki + testi;
        }
        return testi;
    }
    
    /**
     * Metodi etsii käsiteltävän sulkutason päätöksen tai-merkkiä varten
     * @param kohta Säännöllisen lauseen kohta
     * @return Palauttaa sulkutason päätöskohdan
     */
    public int etsiAlku(int kohta) {
        int sulut = 0;
        int sullut = 0;
        while (kohta > 0) {
            kohta--;
            if (regex.charAt(kohta) == '(' && sulut == sullut) {
                return kohta;
            } else if (regex.charAt(kohta) == ')') {
                sullut++;
            } else if (regex.charAt(kohta) == '(') {
                sulut++;
            }
        }
        return -1;
    }
    /**
     * Metodi etsii käsitelävällä sulkutasolla mahdollisesti olevan tai-merkin
     * @param kohta Säännöllisen lauseen kohta
     * @return Palauttaa joko sopivan tai-merkin kohdan tai säännöllisen lauseen alun
     */
    public int etsiTai(int kohta) {
        int sulut = 0;
        int sullut = 0;
        while (kohta > 0) {
            kohta--;
            if (regex.charAt(kohta) == '(') {
                sulut++;
            } else if (regex.charAt(kohta) == ')') {
                sullut++;
            } else if (regex.charAt(kohta) == '|' && sulut == sullut) {
                return kohta;
            }
        }
        return kohta;
    }
    
    //HUOM! Toimiessaan tulkin ehtolauseet hajotetaan selkeämmin luettaviin osiin.
    //Tällä hetkellä toimivat *,?,+,| ja |-merkin ympärillä sulkuryhmät OSITTAIN (esimerkiksi a(bb|cc)a ei hyväksy vielä abba, sillä |-merkki pyyhkii kaiken yli sulkurajojen).
    
    /**
     * Tulkin toiminnallinen osa. Metodi käy läpi annettua säännöllistä lausetta oikealta vasemmalle,
     * ja rakentaa sen pohjalta rekursiivisesti merkkijonoja, joita verrataan annettuun sanaan.
     * Mikäli vastaavuus löytyy annetun sanan ja testimerkkijonon väliltä niin, että ollaan päästy samalla säännöllisen lauseen loppuun,
     * kytketään päälle lukko, joka pysäyttää nopeasti loput rekursiot ja toimii myös "löydetty"-totuusarvona.
     * @param testi Säännöllisen lauseen pohjalta rakennettavia testimerkkijonoja
     * @param kohta Säännöllisen lauseen kohta
     */
    public void tulkki(String testi, int kohta) {
        if (testi.length() > sana.length() || !sana.endsWith(testi) || lukko || kohta < -1) {
            return;
        }
        if (kohta == -1) {
            if (testi.equals(sana)) {
                lukko = true;
            }
            return;
        }
        if (regex.charAt(kohta) == '+') {
            
            String uustesti = lisaaja(kohta - 1, testi);
            tulkki(uustesti, kohta);
            tulkki(uustesti, kohta - 2);
            
        } else if (regex.charAt(kohta) == '*') {
            
            String uustesti = lisaaja(kohta - 1, testi);
            tulkki(testi, kohta - 2);
            tulkki(uustesti, kohta);
            tulkki(uustesti, kohta - 2);
            
        } else if (regex.charAt(kohta) == '?') {
            
            String uustesti = lisaaja(kohta - 1, testi);
            tulkki(testi, kohta - 2);
            tulkki(uustesti, kohta - 2);
            
        } else if (regex.charAt(kohta) == '|') {
            
            String uustesti = "";
            tulkki(uustesti, kohta - 1);
            tulkki(testi, etsiAlku(kohta));
            
        } else {
            
            String uustesti = lisaaja(kohta, testi);
            int uuskohta = etsiTai(kohta);
            if (uuskohta > 0) {
                tulkki("", uuskohta - 1);
            }
            tulkki(uustesti, kohta - 1);
            
        }
    }
}
