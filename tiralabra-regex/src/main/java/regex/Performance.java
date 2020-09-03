/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import regex.logic.Regex;

/**
 * Testiluokista eriytetty suorituskykytestaaja.
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
        groupRepetitionTest();  
    }
    
    /**
     * Metodi testaa sulkuryhmien toiston tehokkuutta eri sulkutasoilla.
     */
    
    public static void groupRepetitionTest() {
        regex.setRegex("(.)*");
        regex.setString("a");
        
        System.out.println("Group repetiotion test:");
        while (regex.getRegex().length() < 2500)  {
            long start = System.currentTimeMillis();
            regex.translator("", regex.getRegex().length() - 1);
            long time = System.currentTimeMillis() - start;
            System.out.println(time + " match: " + regex.getFound() + ". Length of regex: " + regex.getRegex().length());
            regex.setRegex("((" + regex.getRegex() + ")*)*");
        }
        
        groupRepetitionTestPart2();
    }
    
    /**
     * Metodi testaa merkkijonon koon merkitystä suoritusaikaan. 
     */
    
    public static void groupRepetitionTestPart2() {
        
        System.out.println("Group repetition part 2:");
        while (regex.getString().length() < 140)  {
            long start = System.currentTimeMillis();
            regex.translator("", regex.getRegex().length() - 1);
            long time = System.currentTimeMillis() - start;
            System.out.println(time + " match: " + regex.getFound() + ". Length of string: " + regex.getString().length());
            regex.setString(regex.getString() + (char) ((System.currentTimeMillis() % 50) + 50));
        }
    }
    
}
