/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 * Nopeasti testejä varten räätälöity IO-luokka.
 * @author linaksel
 */

//VANHENTUNUT, pohdin viikolla 5 tarvitaanko johonkin testaukseen tällaista erikois inouttia
//Jos ei, poistetaan viikolle 6.


public class TestiIO implements IO {

    private String regex;
    private String sana;
    private String exit;
    private String exitConf;
    private Boolean reSyotetty;
    private Boolean saSyotetty;
    private Boolean eSyotetty;
    private String tosi;
    
    public TestiIO(String regex, String sana) {
        this.regex = regex;
        this.sana = sana;
        this.exit = "exit!";
        this.exitConf = "e";
        this.reSyotetty = false;
        this.saSyotetty = false;
        this.eSyotetty = false;
    }
    
    @Override
    public String next() {
        if (eSyotetty) {
            return exitConf;
        }
        if (saSyotetty) {
            eSyotetty = true;
            return exit;
        }
        if (reSyotetty) {
            saSyotetty = true;
            return sana;
        }
        reSyotetty = true;
        return regex;
    }

    @Override
    public void print(String tuloste) {
        this.tosi = tuloste;
    }
    
    public Boolean loytyi() {
        if (tosi.equals("true")) {
            return true;
        }
        return false;
    }
    
}
