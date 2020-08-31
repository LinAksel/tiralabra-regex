package regex.logic;

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
     * @return Palauttaa true, kun erikoismerkki, muutoin false
     */
    
    public boolean erikoismerkki(char merkki) {
        if (merkki == '*' || merkki == '+' || merkki == '?' || merkki == '|') {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi tutkii, onko merkki erikoismerkki escapekontekstissa. Tästä johtuen hieman erilainen nimi, sillä
     * nämä eivät ole samalla tavalla erikoismerkkejä kuin ylemmät.
     * @param merkki Säännöllisen lauseen tarkasteltava merkki
     * @return Palauttaa true, kun erikoismerkki, muutoin false
     */
    
    public boolean escapeErikoismerkki(char merkki) {
        if (merkki == '(' || merkki == ')' || merkki == 'e' || merkki == 'd' || merkki == '.' || merkki == '\\') {
            return true;
        }
        return false;
    }
    
    /**
     * Apufunktio totuusarvojen asettamiseen copypasten poistamiseksi.
     * @param meta Asetettava arvo sille, onko edellinen erikoismerkki vai ei
     * @param escape Asetettava arvo sille, onko edellinen escape vai ei
     */
    
    public void booleanSet(boolean meta, boolean escape) {
        this.edellinenescape = escape;
        this.edellinenmeta = meta;
    }
    
    /**
     * Apufunktio tarkastajalle. Hoitaa totuusarvojen ja sulkujen maaran asettamisen,
     * kun syöte ei ainakaan toistaiseksi ole virheellinen.
     * @param merkki Tarkasteltava regexin merkki
     */
    
    public void tarkastaMerkki(char merkki) {
        if (edellinenescape) {
            booleanSet(false, false);
        } else if (erikoismerkki(merkki)) {
            booleanSet(true, false);
        } else if (merkki == '\\') {
            booleanSet(false, true);
        } else if (merkki == '(') {
            booleanSet(false, false);
            this.avaava++;
        } else if (merkki == ')') {
            booleanSet(false, false);
            this.sulkeva++;
        } else {
            booleanSet(false, false);
        }
    }
    
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
        if (merkki == ')' || erikoismerkki(merkki)) {
            this.viesti = "Invalid start of regular expression, check first character '" + merkki + "'";
            return false;
        }
        for (int i = 0; i < regex.length(); i++) {
            merkki = regex.charAt(i);
            if (erikoismerkki(merkki) && edellinenmeta) {
                this.viesti = "Invalid quantifier at character " + (i + 1) + ": " + merkki + ", check for double quantifier error";
                return false;
            } else if (!erikoismerkki(merkki) && !escapeErikoismerkki(merkki) && edellinenescape) {
                this.viesti = "Invalid use of escape at character " + (i + 1) + ": " + merkki + ", check the manual for supported escape uses";
                return false;
            }
            tarkastaMerkki(merkki);
        }
        if (avaava != sulkeva) {
            this.viesti = "Non-matching amount of parenthesis found";
            return false;
        }
        return true;
    }
    
    /**
     * Metodilla saadaan virheviesti haettua.
     * @return Palauttaa virheviestin tai "OK", mikäli virhettä ei ole
     */
    
    public String getViesti() {
        return viesti;
    }
}
