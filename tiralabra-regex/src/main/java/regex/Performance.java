/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import regex.logic.Regex;

/**
 * Tämä on testiluokista eriytetty suorituskykytestaaja.
 * @author linaksel
 */
public class Performance {
    
    private static Regex regex = new Regex();
    
    /**
     * Erillinen käynnistäjä luokalle, jotta suorituskykytestaus voidaan suorittaa omana ohjelmanaan.
     * (Netbeansissa yksinkertaisesti "Run File")
     * @param args 
     */
    
    public static void main(String[] args) {
        sulkuTestaus();
    }
    
    /**
     * Metodi testaa sulkuryhmien toiston tehokkuutta eri sulkutasoilla
     */
    
    public static void sulkuTestaus() {
        regex.setRegex("(.)*");
        regex.setSana("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxxxxxxxxxxxxxxxxxxxaaaaaaaaaaaaaaayyyyyyyyyyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
        while (regex.getRegex().length() < 1000)  {
            long alku = System.currentTimeMillis();
            regex.tulkki("", regex.getRegex().length() - 1);
            long aika = System.currentTimeMillis() - alku;
            System.out.println(aika + " tunnistettu: " + regex.getFound());
            regex.setRegex("(" + regex.getRegex() + ")*");
        }
    }
    
    public static void sulkuTestaus2() {
        regex.setRegex("((((((((((((((((((((((((((((((.)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*)*");
        regex.setSana("a");
        
        while (regex.getSana().length() < 1000)  {
            long alku = System.currentTimeMillis();
            regex.tulkki("", regex.getRegex().length() - 1);
            long aika = System.currentTimeMillis() - alku;
            System.out.println(aika + " tunnistettu: " + regex.getFound());
            regex.setSana(regex.getSana() + (char) System.currentTimeMillis() % 200);
        }
    }
    
}
