/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import static org.junit.Assert.assertEquals;
import regex.logic.Regex;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author linaksel
 */
public class TestRegex {
    
    private Regex regex = new Regex();
    
    @Test
    public void testTooLongString() {
        regex.setRegex("ylli");
        regex.setSana("yllii");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound()); 
    }
    
    @Test
    public void testBadStartingPoint() {
        regex.setRegex("ylli");
        regex.setSana("yllii");
        regex.tulkki("", -2);
        assertFalse(regex.getFound()); 
    }
    
    @Test
    public void testAlternation() {
        regex.setRegex("ylli|yyteri");
        regex.setSana("yyteri");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testAlternationTwo() {
        regex.setRegex("ylli|yyteri");
        regex.setSana("ylliyyteri");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testAlternationThree() {
        regex.setRegex("ylli|yyteri|");
        regex.setSana("");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testQuestionmark() {
        regex.setRegex("a?ccb?");
        regex.setSana("acc");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testQuestionmarkTwo() {
        regex.setRegex("a?ccb?");
        regex.setSana("aacc");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testQuestionmarkThree() {
        regex.setRegex("a?(ccb)?");
        regex.setSana("a");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testPlus() {
        regex.setRegex("b+s");
        regex.setSana("bbs");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testPlusTwo() {
        regex.setRegex("b+s");
        regex.setSana("s");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testPlusThree() {
        regex.setRegex("(sb)+");
        regex.setSana("sbsb");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDot() {
        regex.setRegex("a.c");
        regex.setSana("abc");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDotTwo() {
        regex.setRegex("a.+b");
        regex.setSana("aacb");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testStar() {
        regex.setRegex("ab*a");
        regex.setSana("abba");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testStarTwo() {
        regex.setRegex("ab*a");
        regex.setSana("aa");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    //As backslash is the escape character also in Java, doubles are needed to use it once
    
    @Test
    public void testBackslash() {
        regex.setRegex("a\\*\\+");
        regex.setSana("a*+");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testBackslashTwo() {
        regex.setRegex("a\\**\\)?d\\\\e");
        regex.setSana("a*******)d\\e");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testBackslash3() {
        regex.setRegex("(a*)\\?d\\e*");
        regex.setSana("aaaa?d");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testParentheses() {
        regex.setRegex("(lu|ku|tai)mi");
        regex.setSana("kumi");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testParenthesesTwo() {
        regex.setRegex("(tuo|kaa|kah|vi|a)*");
        regex.setSana("tuokaatuokaakahvia");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testEpsilon() {
        regex.setRegex("a\\eb");
        regex.setSana("ab");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDigit() {
        regex.setRegex("\\d*abc");
        regex.setSana("123abc");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDigitTwo() {
        regex.setRegex("\\d*abc");
        regex.setSana("abcabc");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testDigitThree() {
        regex.setRegex("\\d*abc");
        regex.setSana("!abc");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void comboTest1() {
        regex.setRegex("a(\\\\*|(b+a.)|\\.?)*k(a)");
        regex.setSana("a\\\\\\.bai..ka");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void comboTest2() {
        regex.setRegex("a(b*(\\\\+a.b?)*)*");
        regex.setSana("a\\a#bb\\\\aibb");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void comboTest3() {
        regex.setRegex("ab(c|d)+.*");
        regex.setSana("xabd");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void comboTest4() {
        regex.setRegex("a(bb\\)|\\(cd|\\e?|@%!)*\\*+");
        regex.setSana("abb)(cd@%!bb)***");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void comboTest5() {
        regex.setRegex("a((bc+|\\d*)\\|123\\e!)");
        regex.setSana("a1234567890|123!");
        regex.tulkki("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void getterWorks() {
        regex.setRegex("jjjj");
        regex.setSana("jjjj");
        assertEquals("jjjj", regex.getSana());
    }
}
