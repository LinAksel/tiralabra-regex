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

public class TestiIO implements IO{

    private String regex;
    private String sana;
    private Boolean reSyotetty;
    private String tosi;
    
    public TestiIO(String regex, String sana){
        this.regex = regex;
        this.sana = sana;
        this.reSyotetty = false;
    }
    
    @Override
    public String next() {
        if(reSyotetty){
            return sana;
        } else {
            reSyotetty = true;
            return regex;
        }
    }

    @Override
    public void print(String tuloste) {
        this.tosi = tuloste;
    }
    
    public Boolean loytyi(){
        if(tosi.equals("true")){
            return true;
        }
        return false;
    }
    
}
