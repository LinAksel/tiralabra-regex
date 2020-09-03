package regex.logic;

/**
 * Esitarkastaja säännölliselle lausekkeelle. Tämä tarkistaa, onko annetussa regexissä syntaksivirheitä.
 * @author linaksel
 */

public class Validator {
    
    private int opening;
    private int closing;
    private boolean specialbefore;
    private boolean escapebefore;
    private String regex;
    private String message;
    
    /**
     * Luokan konstruktori.
     * @param regex Tarkastettava uusi säännöllinen lauseke.
     */
    
    public Validator(String regex) {
        this.opening = 0;
        this.closing = 0;
        this.specialbefore = false;
        this.escapebefore = false;
        this.regex = regex;
        this.message = "OK";
    }
    
    /**
     * Metodi tutkii, onko merkki erikoismerkki.
     * @param character Säännöllisen lauseen tarkasteltava character
     * @return Palauttaa true, kun specialCharacter, muutoin false
     */
    
    public boolean specialCharacter(char character) {
        if (character == '*' || character == '+' || character == '?' || character == '|') {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi tutkii, onko merkki erikoismerkki escapekontekstissa.
     * @param character Säännöllisen lauseen tarkasteltava character
     * @return Palauttaa true, kun specialCharacter, muutoin false
     */
    
    public boolean escapeSpecial(char character) {
        if (character == '(' || character == ')' || character == 'e' || character == 'd' || character == '.' || character == '\\') {
            return true;
        }
        return false;
    }
    
    /**
     * Apufunktio totuusarvojen asettamiseen copypasten poistamiseksi.
     * @param special Asetettava arvo sille, onko edellinen specialCharacter vai ei
     * @param escape Asetettava arvo sille, onko edellinen escape vai ei
     */
    
    public void booleanSet(boolean special, boolean escape) {
        this.escapebefore = escape;
        this.specialbefore = special;
    }
    
    /**
     * Apufunktio tarkastajalle. Hoitaa totuusarvojen ja sulkujen maaran asettamisen.
     * @param character Tarkasteltava regexin character
     */
    
    public void validateCharacter(char character) {
        if (escapebefore) {
            booleanSet(false, false);
        } else if (specialCharacter(character)) {
            booleanSet(true, false);
        } else if (character == '\\') {
            booleanSet(false, true);
        } else if (character == '(') {
            booleanSet(false, false);
            this.opening++;
        } else if (character == ')') {
            booleanSet(false, false);
            this.closing++;
        } else {
            booleanSet(false, false);
        }
    }
    
    /**
     * Tarkastajan toiminnallinen osa. Metodi tutkii, onko säännöllinen lauseke kirjoitettu oikein.
     * @return Palauttaa true, mikäli säännöllinen lauseke on kirjoitettu oikein, muutoin false.
     */
    
    public boolean validate() {
        if (regex.length() == 0) {
            return true;
        }
        char character = regex.charAt(0);
        if (character == ')' || specialCharacter(character)) {
            this.message = "Invalid start of regular expression, check first character '" + character + "'";
            return false;
        }
        for (int i = 0; i < regex.length(); i++) {
            character = regex.charAt(i);
            if (specialCharacter(character) && specialbefore) {
                this.message = "Invalid quantifier at character " + (i + 1) + ": " + character + ", check for double quantifier error";
                return false;
            } else if (!specialCharacter(character) && !escapeSpecial(character) && escapebefore) {
                this.message = "Invalid use of escape at character " + (i + 1) + ": " + character + ", check the manual for supported escape uses";
                return false;
            }
            validateCharacter(character);
            if (closing > opening) {
                this.message = "Invalid parenthesis at character " + (i + 1) + ": " + character + ", nothing to close.";
                return false;
            }
        }
        if (opening != closing) {
            this.message = "Non-matching amount of parenthesis found";
            return false;
        }
        return true;
    }
    
    /**
     * Metodilla saadaan virheviesti haettua.
     * @return Palauttaa virheviestin tai "OK", mikäli virhettä ei ole
     */
    
    public String getMessage() {
        return message;
    }
}
