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
    private int[] maarat;
    private int maara = 0;
    
    public Regex() {
        this.lukko = false;
    }
    
    /** 
     * Metodi hoitaa testisyötteeseen merkin lisäämisen
     * @param kohta Säännöllisen lauseen kohta
     * @param testi Rakennettava merkkijono, jota verrataan sanaan.
     * @return Palauttaa joko annetun testijonon tai testijonon, johon lisätty kirjain
     */
    public String lisaaja(int kohta, String testi) {
        char merkki = regex.charAt(kohta);
        int erotus = sana.length() - testi.length();
        if (!onErikoismerkki(kohta)) {
            return merkki + testi;
        } else if (regex.charAt(kohta) == '.' && erotus > 0) {
            merkki = sana.charAt(erotus - 1);
            return merkki + testi;
        }
        return testi;
    }
    
    /**
     * Metodi tarkistaa, toimiiko säännöllisen lauseen annetussa kohdassa oleva merkki
     * metamerkkinä vai tavallisena merkkinä
     * @param kohta Säännöllisen lauseen kohta
     * @return Palauttaa true, jos kyseessä metamerkki, muutoin false
     */
    public boolean onErikoismerkki(int kohta) {
        char merkki = regex.charAt(kohta);
        if ((merkki != ')' && merkki != '(' && merkki != '*' && merkki != '?' && merkki != '+' && merkki != '.' && merkki != '|' && merkki != (char) 92 && merkki != 'e')) {
            return false;
        } else if (kohta > 0 && regex.charAt(kohta - 1) == (char) 92) {
            int maara = 1;
            while (kohta - maara - 1 > -1 && regex.charAt(kohta - maara - 1) == (char) 92) {
                maara++;
            }
            if (maara % 2 != 0 && (merkki != 'e')) {
                return false;
            } else if (merkki == 'e' && maara > 1 && maara % 2 == 0){
                return false;
            }
        } else if (kohta > 0 && regex.charAt(kohta - 1) != (char) 92 && merkki == 'e') {
            return false;
        }
        return true;
    }
    
    /**
     * Metodi etsii käsiteltävän sulkutason alun
     * @param kohta Säännöllisen lauseen kohta
     * @return Palauttaa sulkutason aloituskohdan
     */
    public int etsiAlku(int kohta) {
        int sulut = 0;
        int sullut = 0;
        while (kohta > 0) {
            kohta--;
            if (regex.charAt(kohta) == '(' && onErikoismerkki(kohta) && sulut == sullut) {
                return kohta;
            } else if (regex.charAt(kohta) == ')' && onErikoismerkki(kohta)) {
                sullut++;
            } else if (regex.charAt(kohta) == '(' && onErikoismerkki(kohta)) {
                sulut++;
            }
        }
        return -1;
    }
    
    /**
     * Metodi etsii käsiteltävän sulkutason lopun
     * @param kohta Säännöllisen lauseen kohta
     * @return Palauttaa sulkutason päätöskohdan
     */
    public int etsiLoppu(int kohta) {
        int sulut = 0;
        int sullut = 0;
        while (kohta < regex.length()) {
            kohta++;
            if (regex.charAt(kohta) == ')' && onErikoismerkki(kohta) && sulut == sullut) {
                return kohta;
            } else if (regex.charAt(kohta) == ')' && onErikoismerkki(kohta)) {
                sullut++;
            } else if (regex.charAt(kohta) == '(' && onErikoismerkki(kohta)) {
                sulut++;
            }
        }
        return regex.length() - 1; 
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
            if (regex.charAt(kohta) == '(' && onErikoismerkki(kohta)) {
                sulut++;
            } else if (regex.charAt(kohta) == ')' && onErikoismerkki(kohta)) {
                sullut++;
            } else if (regex.charAt(kohta) == '|' && onErikoismerkki(kohta) && sulut == sullut) {
                return kohta;
            }
        }
        return kohta;
    }
    
    //Välikommentti: pohdin jatkuvasti mahdollisuuksia purkaa tulkki-metodia pienempiin osiin, mutta näyttää koko ajan vahvemmin siltä,
    //että metodi jää "hirviöksi", sillä se on selkein tapa esittää rekursioluonne. Kurssia on kuitenkin vielä jäljellä, ja muutokset vielä mahdollisia.
    
    /**
     * Tulkin toiminnallinen osa. Metodi käy läpi annettua säännöllistä lausetta oikealta vasemmalle,
     * ja rakentaa sen pohjalta rekursiivisesti merkkijonoja, joita verrataan annettuun sanaan.
     * Mikäli vastaavuus löytyy annetun sanan ja testimerkkijonon väliltä niin, että ollaan päästy samalla säännöllisen lauseen loppuun,
     * kytketään päälle lukko, joka pysäyttää nopeasti loput rekursiot ja toimii myös "löydetty"-totuusarvona.
     * @param testi Säännöllisen lauseen pohjalta rakennettavia testimerkkijonoja
     * @param kohta Säännöllisen lauseen kohta
     */
    public void tulkki(String testi, int kohta) {
        
//        if(kohta >= 0 && !lukko){
//            maara++;
//            System.out.println(testi + " " + kohta);
//        }
        
        if (testi.length() > sana.length() || !sana.endsWith(testi) || lukko || kohta < -1) {
            return;
        }
        if (kohta == -1) {
            if (testi.equals(sana)) {
                lukko = true;
            }
            return;
        }
        if (kohta == regex.length() - 1 || (regex.charAt(kohta) == ')' && onErikoismerkki(kohta))) {
            int uusikohta = etsiTai(kohta);
            if (kohta != regex.length() - 1) {
                maarat[kohta] = testi.length();
            }
            while (uusikohta > 0) {
                tulkki(testi, uusikohta - 1);
                uusikohta = etsiTai(uusikohta);
            }
        }
        if (regex.charAt(kohta) == '(' && onErikoismerkki(kohta)) {
            
            int uusikohta = etsiLoppu(kohta);
            tulkki(testi, kohta - 1);
            if (uusikohta < regex.length() - 1 && (regex.charAt(uusikohta + 1) == '+' || regex.charAt(uusikohta + 1) == '*') && testi.length() > 0 && maarat[uusikohta] < testi.length()) {
                tulkki(testi, uusikohta);
            }
            
        } else if (regex.charAt(kohta) == '+' && onErikoismerkki(kohta)) {
            
            String uustesti = lisaaja(kohta - 1, testi);
            if (!uustesti.equals(testi)) {
                tulkki(uustesti, kohta);
                tulkki(uustesti, kohta - 2);
            } else {
                tulkki(testi, kohta - 1);
            }
            
        } else if (regex.charAt(kohta) == '*' && onErikoismerkki(kohta)) {
            
            String uustesti = lisaaja(kohta - 1, testi);
            if (!uustesti.equals(testi)) {
                tulkki(uustesti, kohta);
                tulkki(uustesti, kohta - 2);
                tulkki(testi, kohta - 2);
            } else {
                if(regex.charAt(kohta - 1) == ')') {
                    tulkki(testi, etsiAlku(kohta - 1));
                }
                tulkki(testi, kohta - 1);
            }
            
        } else if (regex.charAt(kohta) == '?' && onErikoismerkki(kohta)) {
            
            String uustesti = lisaaja(kohta - 1, testi);
            if (!uustesti.equals(testi)) {
                tulkki(uustesti, kohta - 2);
                tulkki(testi, kohta - 2);
            } else {
                tulkki(testi, etsiAlku(kohta - 1));
                tulkki(testi, kohta - 1);
            }
            
        } else if (regex.charAt(kohta) == '|' && onErikoismerkki(kohta)) {
            
            tulkki(testi, etsiAlku(kohta));
            if(kohta == regex.length() - 1) {
                tulkki(testi, kohta - 1);
            }
        
        } else if (regex.charAt(kohta) == 'e' && onErikoismerkki(kohta)) {
            
            tulkki(testi, kohta - 2);
            
        } else {
            
            String uustesti = lisaaja(kohta, testi);
            tulkki(uustesti, kohta - 1);
            
        }
    }
    
    //Getterit ja setterit UI-luokan erottamiseksi, sekä reset, jolla samaa regexiä tutkittaessa
    //uudelleen nollataan sulkutasolista ja lukko
    
    public void reset() {
        this.lukko = false;
        this.maarat = new int[regex.length()];
    }
    
    public void setRegex(String regex) {
        this.lukko = false;
        this.regex = regex;
        this.maarat = new int[regex.length()];
    }
    
    public void setSana(String sana) {
        this.sana = sana;
    }
    
    public boolean getFound() {
        return lukko;
    }
    
    public String getSana() {
        return sana;
    }
    
    public String getRegex() {
        return regex;
    }
}
