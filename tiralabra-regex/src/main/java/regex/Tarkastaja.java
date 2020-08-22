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
public class Tarkastaja {
    
    private int avaava;
    private int sulkeva;
    private boolean edellinenmeta;
    private boolean edellinenescape;
    private String regex;
    private String viesti;
    
    public Tarkastaja(String regex) {
        this.avaava = 0;
        this.sulkeva = 0;
        this.edellinenmeta = false;
        this.edellinenescape = false;
        this.regex = regex;
        this.viesti = "OK";
    }
    
    /**
     * Metodi tutkii, onko merkki mahdollisesti metamerkki. Tähän ei lasketa backslashia
     * @param merkki Säännöllisen lauseen tarkasteltava merkki
     * @return Palauttaa true, kun metamerkki, muutoin false
     */
    
    public boolean metamerkki(char merkki) {
        if (merkki == '*' || merkki == '+' || merkki == '?' || merkki == '|') {
            return true;
        }
        return false;
    }
    
    //HUOM! tarkasta-metodia on ehdottomasti tarkoitus purkaa ja siistiä, tämä oli ensimmäinen pikainen implementaatio.
    
    /**
     * Tarkastajan toiminnallinen osa. Metodi tutkii, onko säännöllinen lauseke oikeinkirjoitettu,
     * eli käytännössä tarkastetaan oikea sulkujen määrä, backslashin käyttö vain tuetuilla merkeillä ja
     * ettei operaattoreita ole ketjutettu väärin.
     * @return Palauttaa true, mikäli säännöllinen lauseke on kirjoitettu oikein, muutoin false
     */
    
    public boolean tarkasta() {
        if (regex.length() == 0) {
            return true;
        }
        char merkki = regex.charAt(0);
        if (merkki == ')' || metamerkki(merkki)) {
            viesti = "Invalid start of regular expression, check first character '" + merkki + "'";
            return false;
        } else if (merkki == '(') {
            avaava++;
        } else if (merkki == (char) 92) {
            edellinenescape = true;
        }
        for (int i = 1; i < regex.length(); i++) {
            merkki = regex.charAt(i);
            if (metamerkki(merkki) && edellinenmeta && merkki == '|') {
                continue;
            } else if (metamerkki(merkki) && edellinenmeta) {
                viesti = "Invalid quantifier at character " + (i + 1) + ", check for double quantifier error";
                return false;
            } else if (metamerkki(merkki) && edellinenescape) {
                edellinenescape = false;
                edellinenmeta = false;
            } else if (metamerkki(merkki)) {
                edellinenescape = false;
                edellinenmeta = true;
            } else if ((merkki == '(' || merkki == ')' || merkki == (char) 92 || merkki == 'e') && edellinenescape) {
                edellinenmeta = false;
                edellinenescape = false;
            } else if (merkki == (char) 92) {
                edellinenmeta = false;
                edellinenescape = true;
            } else if (merkki == '(') {
                edellinenescape = false;
                edellinenmeta = false;
                avaava++;
            } else if (merkki == ')') {
                edellinenescape = false;
                edellinenmeta = false;
                sulkeva++;
            } else if (edellinenescape && merkki != '.') {
                viesti = "Invalid use of escape at character" + (i) + ", check the manual for supported escape uses";
                return false;
            } else {
                edellinenmeta = false;
                edellinenescape = false;
            }
        }
        if (avaava != sulkeva) {
            viesti = "Non-matching amount of parenthesis found";
            return false;
        }
        viesti = "OK";
        return true;
    }
    
    //Tällä saadaan virheviesti haettua
    
    public String getViesti() {
        return viesti;
    }
}
