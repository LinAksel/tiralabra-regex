/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.io;

import regex.io.IO;
import java.util.Scanner;

/**
 *
 * @author linaksel
 */
public class KonsoliIO implements IO {
    
    private Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }

    @Override
    public String next() {
        return lukija.nextLine();
    }

    @Override
    public void print(String tuloste) {
        System.out.println(tuloste);
    }
    
}
