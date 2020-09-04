/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.logic;

/**
 * Logiikan pääluokka. Tässä tapahtuu itse tulkkaus annetusta säännöllisestä lauseesta verraten merkkijonoon.
 * @author linaksel
 */

public class Regex {
    
    private String regex;
    private String string;
    private Boolean matchfound;
    private int[] lolimits;
    
    /**
     * Luokan konstruktori.
     */
    
    public Regex() {
        this.matchfound = false;
    }
    
    /** 
     * Metodi hoitaa testisyötteeseen merkin lisäämisen.
     * @param index Säännöllisen lauseen kohta
     * @param test Rakennettava merkkijono, jota verrataan sanaan.
     * @return Palauttaa joko annetun testijonon tai testijonon, johon lisätty kirjain
     */
    
    public String stringBuilder(int index, String test) {
        char character = regex.charAt(index);
        int diff = string.length() - test.length();
        if (!isSpecialCharacter(index)) {
            return character + test;
        } else if (regex.charAt(index) == '.' && diff > 0) {
            character = string.charAt(diff - 1);
            return character + test;
        } else if (regex.charAt(index) == '.') {
            return character + test;
        } else if (regex.charAt(index) == 'd' && diff > 0 && isNumber(string.charAt(diff - 1))) {
            character = string.charAt(diff - 1);
            return character + test;
        } else if (regex.charAt(index) == 'd') {
            return "1" + test;
        }
        return test;
    }
    
    /**
     * Metodi tarkistaa, toimiiko säännöllisen lauseen annetussa kohdassa oleva merkki erikoismerkkinä.
     * @param index Säännöllisen lauseen kohta
     * @return Palauttaa true, jos kyseessä erikoismerkki, muutoin false
     */
    
    public boolean isSpecialCharacter(int index) {
        char character = regex.charAt(index);
        if ((character != ')' && character != '(' && character != '*' && character != '?' && character != '+' && character != '.' && character != '|' && character != (char) 92 && character != 'e' && character != 'd')) {
            return false;
        } else if (index > 0 && regex.charAt(index - 1) == '\\') {
            int escapes = 1;
            while (index - escapes - 1 > -1 && regex.charAt(index - escapes - 1) == '\\') {
                escapes++;
            }
            if (escapes % 2 != 0 && (character != 'e' && character != 'd')) {
                return false;
            } else if ((character == 'd' || character == 'e') && escapes % 2 == 0) {
                return false;
            }
        } else if (index > 0 && (character == 'e' || character == 'd')) {
            return false;
        }
        return true;
    }
    
    /**
     * Metodi etsii käsiteltävän sulkutason alun.
     * @param index Säännöllisen lauseen kohta
     * @return Palauttaa sulkutason aloituskohdan
     */
    
    public int findStart(int index) {
        int open = 0;
        int close = 0;
        while (index > 0) {
            index--;
            if (regex.charAt(index) == '(' && isSpecialCharacter(index) && open == close) {
                return index;
            } else if (regex.charAt(index) == ')' && isSpecialCharacter(index)) {
                close++;
            } else if (regex.charAt(index) == '(' && isSpecialCharacter(index)) {
                open++;
            }
        }
        return -1;
    }
    
    /**
     * Metodi etsii käsiteltävän sulkutason lopun.
     * @param index Säännöllisen lauseen kohta
     * @return Palauttaa sulkutason päätöskohdan
     */
    
    public int findEnding(int index) {
        int open = 0;
        int close = 0;
        while (index < regex.length() - 2) {
            index++;
            if (regex.charAt(index) == ')' && isSpecialCharacter(index) && open == close) {
                break;
            } else if (regex.charAt(index) == ')' && isSpecialCharacter(index)) {
                close++;
            } else if (regex.charAt(index) == '(' && isSpecialCharacter(index)) {
                open++;
            }
        }
        return index; 
    }
    /**
     * Metodi etsii käsitelävällä sulkutasolla mahdollisesti olevan tai-merkin.
     * @param index Säännöllisen lauseen kohta
     * @return Palauttaa joko sopivan tai-merkin kohdan tai säännöllisen lauseen alun
     */
    
    public int findOr(int index) {
        int open = 0;
        int close = 0;
        while (index > 0) {
            index--;
            if (regex.charAt(index) == '(' && isSpecialCharacter(index)) {
                open++;
            } else if (regex.charAt(index) == ')' && isSpecialCharacter(index)) {
                close++;
            } else if (regex.charAt(index) == '|' && isSpecialCharacter(index) && open == close) {
                return index;
            }
        }
        return index;
    }
    
    /**
     * Tulkin toiminnallinen osa. Metodi käy läpi annettua säännöllistä lausetta oikealta vasemmalle,
     * ja rakentaa sen pohjalta rekursiivisesti merkkijonoja, joita verrataan annettuun sanaan.
     * @param test Säännöllisen lauseen pohjalta rakennettavia testimerkkijonoja
     * @param index Säännöllisen lauseen kohta
     */
    
    public void translator(String test, int index) {
        if (test.length() > string.length() || !string.endsWith(test) || matchfound || index < -1) {
            return;
        }
        if (index == -1) {
            if (test.equals(string)) {
                matchfound = true;
            }
            return;
        }
        if ((regex.charAt(index) == ')' && isSpecialCharacter(index)) || index == regex.length() - 1) {
            startOfGroup(test, index);
        }
        if (regex.charAt(index) == '(' && isSpecialCharacter(index)) {
            endOfGroup(test, index);
        } else if (regex.charAt(index) == '+' && isSpecialCharacter(index)) {
            atLeastOnce(test, index);
        } else if (regex.charAt(index) == '*' && isSpecialCharacter(index)) {
            kleeneStar(test, index);
        } else if (regex.charAt(index) == '?' && isSpecialCharacter(index)) {
            noneOrOnce(test, index);
        } else if (regex.charAt(index) == '|' && isSpecialCharacter(index)) {
            thisOrThat(test, index);
        } else if (regex.charAt(index) == 'e' && isSpecialCharacter(index)) {
            translator(test, index - 2);
        } else {
            String newtest = stringBuilder(index, test);
            translator(newtest, index - 1);
        }
    }
    
    /**
     * Metodi käsittelee erikoismerkin '*', eli mielivaltaisen toiston.
     * @param test Tämänhetkinen testattava merkkijono
     * @param index Säännöllisen lauseen kohta
     */
    public void kleeneStar(String test, int index) {
        String newtest = stringBuilder(index - 1, test);
        if (!newtest.equals(test)) {
            translator(newtest, index);
            translator(newtest, index - 2);
            translator(test, index - 2);
        } else {
            if (regex.charAt(index - 1) == ')') {
                translator(test, findStart(index - 1));
            }
            translator(test, index - 1);
        }
    }
    
    /**
     * Metodi käsittelee erikoismerkin '?', eli "kerran tai ei kertaakaan".
     * @param test Tämänhetkinen testattava merkkijono
     * @param index Säännöllisen lauseen kohta
     */
    
    public void noneOrOnce(String test, int index) {
        String newtest = stringBuilder(index - 1, test);
        if (!newtest.equals(test)) {
            translator(newtest, index - 2);
            translator(test, index - 2);
        } else {
            if (regex.charAt(index - 1) == ')') {
                translator(test, findStart(index - 1));
            }
            translator(test, index - 1);
        }
    }
    
    /**
     * Metodi käsittelee erikoismerkin '+', eli "vähintään yhden toiston".
     * @param test Tämänhetkinen testattava merkkijono
     * @param index Säännöllisen lauseen kohta
     */
    
    public void atLeastOnce(String test, int index) {
        String newtest = stringBuilder(index - 1, test);
        if (!newtest.equals(test)) {
            translator(newtest, index);
            translator(newtest, index - 2);
        } else {
            translator(test, index - 1);
        }
    }
    
    /**
     * Metodi käsittelee erikoismerkin '|', eli "tain".
     * Käytännössä metodi vain vie sulkutason loppuun, sillä kaikki mahdolliset "tait" aloitetaan
     * metodissa startOfGroup.
     * @param test Tämänhetkinen testattava merkkijono
     * @param index Säännöllisen lauseen kohta
     */
    
    public void thisOrThat(String test, int index) {
        translator(test, findStart(index));
        if (index == regex.length() - 1) {
            translator(test, index - 1);
        }
    }
    
    /**
     * Metodi käsittelee tilanteen, jossa nykyinen sulkutaso loppuu, ja tason mahdollisen toiston.
     * @param test Tämänhetkinen testattava merkkijono
     * @param index Säännöllisen lauseen kohta
     */
    
    public void endOfGroup(String test, int index) {
        int newindex = findEnding(index);
        if ((regex.charAt(newindex + 1) == '+' || regex.charAt(newindex + 1) == '*') && test.length() > 0 && lolimits[newindex] < test.length()) {
            translator(test, newindex);
        }
        translator(test, index - 1);
    }
    
    /**
     * Metodi käsittelee tilanteen, jossa kohdataan uusi sulkutaso.
     * Metodi etsii tason mahdolliset aloituskohdat avulla, ja
     * estää tyhjän loopin merkitsemällä listaan toistomerkkijonon vähimmäispituuden.
     * @param test Tämänhetkinen testattava merkkijono
     * @param index Säännöllisen lauseen kohta
     */
    
    public void startOfGroup(String test, int index) {
        int newindex = findOr(index);
        if (index != regex.length() - 1) {
            lolimits[index] = test.length();
        }
        while (newindex > 0) {
            translator(test, newindex - 1);
            newindex = findOr(newindex);
        }
    }
    
    /**
     * Metodi tutkii, onko annettu char-muotoinen merkki numero välillä 0-9.
     * Ascii-arvoiltaan nämä ovat 48-57.
     * @param character Tutkittava merkki
     * @return Palauttaa true, mikäli merkki on numero, muutoin false
     */
    
    public boolean isNumber(char character) {
        if ((int) character >= 48 && (int) character < 58) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi resetoi sulkuryhmälistan ja lukon uutta vertailua varten.  
     */
    
    public void reset() {
        this.matchfound = false;
        this.lolimits = new int[regex.length()];
    }
    
    /**
     * Metodi asettaa annetun merkkijonon säännölliseksi lausekkeeksi.
     * @param regex Uusi säännöllinen lauseke.
     */
    
    public void setRegex(String regex) {
        this.regex = regex;
        reset();
    }
    
    /**
     * Metodi asettaa annetun merkkijonon vertailtavaksi merkkijonoksi.
     * @param string Uusi vertailtava merkkijono.
     */
    
    public void setString(String string) {
        this.string = string;
        reset();
    }
    
    public boolean getFound() {
        return matchfound;
    }
    
    public String getString() {
        return string;
    }
    
    public String getRegex() {
        return regex;
    }
}
