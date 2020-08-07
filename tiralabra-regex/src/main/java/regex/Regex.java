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
public class Regex {
    
    private String regex;
    private String sana;
    private Boolean lukko;
    
    public Regex() {
        this.lukko = false;
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
    
    public int etsiLoppu(int kohta) {
       int sulut = 0;
        int sullut = 0;
        while (kohta < regex.length()-1) {
            kohta++;
            if (regex.charAt(kohta) == ')' && sulut == sullut) {
                return kohta;
            } else if (regex.charAt(kohta) == ')') {
                sullut++;
            } else if (regex.charAt(kohta) == '(') {
                sulut++;
            }
        }
        return regex.length()-1; 
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
    //Tällä hetkellä toimivat *,?,+,| ja |-merkin ympärillä sulkuryhmät (!)
    
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
        if (kohta == regex.length()-1 || regex.charAt(kohta+1) == ')') {
            int uusikohta = etsiTai(kohta);
            while(uusikohta > 0){
                tulkki(testi, uusikohta-1);
                uusikohta = etsiTai(uusikohta-1);
            }
        }
        if (regex.charAt(kohta) == '(') {
            int uusikohta = etsiLoppu(kohta);
            tulkki(testi, kohta-1);
            if(uusikohta < regex.length()-1 && (regex.charAt(uusikohta+1) == '+' || regex.charAt(uusikohta+1) == '*')) {
                tulkki(testi, uusikohta);
            }
        } else if (regex.charAt(kohta) == '+') {
            
            String uustesti = lisaaja(kohta - 1, testi);
            tulkki(uustesti, kohta - 2);
            if(!uustesti.equals(testi)){
                tulkki(uustesti, kohta);
            }
            
        } else if (regex.charAt(kohta) == '*') {
            
            String uustesti = lisaaja(kohta - 1, testi);
            if(!uustesti.equals(testi)){
                tulkki(uustesti, kohta - 2);
                tulkki(uustesti, kohta);
            } else {
                tulkki(testi, etsiAlku(kohta-1));
            }
            tulkki(testi, kohta - 2);
            
        } else if (regex.charAt(kohta) == '?') {
            
            String uustesti = lisaaja(kohta - 1, testi);
            if(!uustesti.equals(testi)){
                tulkki(uustesti, kohta - 2);
            } else {
                tulkki(testi, etsiAlku(kohta-1));
            }
            tulkki(testi, kohta - 2);
            
        } else if (regex.charAt(kohta) == '|') {
            
            tulkki(testi, etsiAlku(kohta));
            
        } else {
            
            String uustesti = lisaaja(kohta, testi);
            tulkki(uustesti, kohta - 1);
            
        }
    }
    
    //Getterit ja setterit UI-luokan erottamiseksi
    
    public void setFalse() {
        this.lukko = false;
    }
    
    public void setRegex(String regex) {
        this.regex = regex;
    }
    
    public void setSana(String sana) {
        this.sana = sana;
    }
    
    public String getFound() {
        return lukko.toString();
    }
    
    public String getSana() {
        return sana;
    }
    
    public String getRegex() {
        return regex;
    }
}