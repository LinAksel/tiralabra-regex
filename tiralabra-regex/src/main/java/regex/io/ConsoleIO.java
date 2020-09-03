/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.io;

import regex.io.IO;
import java.util.Scanner;

/**
 * Tekstikäyttöliittymän IO-toteutus.
 * @author linaksel
 */
public class ConsoleIO implements IO {
    
    private Scanner reader;

    /**
     * Luokan konstruktori.
     */
    public ConsoleIO() {
        reader = new Scanner(System.in);
    }

    @Override
    public String next() {
        return reader.nextLine();
    }

    @Override
    public void print(String string) {
        System.out.println(string);
    }
    
}
