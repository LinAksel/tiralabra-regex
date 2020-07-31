/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author linaksel
 */
public class TestRegex {
    
    @Test
    public void testaaTai() {
        TestiIO testi =  new TestiIO("ylli|yyteri", "yyteri");
        new Regex(testi).suorita();
        assertTrue(testi.loytyi());
    }
    
    @Test
    public void testaaTaiKaksi() {
        TestiIO testi =  new TestiIO("ylli|yyteri", "yyterä");
        new Regex(testi).suorita();
        assertFalse(testi.loytyi());
    }
    
    @Test
    public void testaaKysymys() {
        TestiIO testi =  new TestiIO("a?ccb?", "acc");
        new Regex(testi).suorita();
        assertTrue(testi.loytyi());
    }
    
    @Test
    public void testaaKysymysKaksi() {
        TestiIO testi =  new TestiIO("a?ccb?", "aacc");
        new Regex(testi).suorita();
        assertFalse(testi.loytyi());
    }
    
    @Test
    public void testaaPlus() {
        TestiIO testi =  new TestiIO("b+s", "bs");
        new Regex(testi).suorita();
        assertTrue(testi.loytyi());
    }
    
    @Test
    public void testaaPlusKaksi() {
        TestiIO testi =  new TestiIO("b+s", "s");
        new Regex(testi).suorita();
        assertFalse(testi.loytyi());
    }
    
    @Test
    public void testaaSulutTaiJaKleene() {
        TestiIO testi =  new TestiIO("a(bb|ca*)", "acaaa");
        new Regex(testi).suorita();
        assertTrue(testi.loytyi());
    }
    
    @Test
    public void testaaSulutTaiKleeneJaPlus() {
        TestiIO testi =  new TestiIO("a(db+|ca*)", "adbb");
        new Regex(testi).suorita();
        assertTrue(testi.loytyi());
    }
    
    @Test
    public void testaaSulutTaiKleeneJaPlusKaksi() {
        TestiIO testi =  new TestiIO("a(db+|ca*)", "adca");
        new Regex(testi).suorita();
        assertFalse(testi.loytyi());
    }
    
}
