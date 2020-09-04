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
        regex.setString("yllii");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound()); 
    }
    
    @Test
    public void testBadStartingPoint() {
        regex.setRegex("ylli");
        regex.setString("yllii");
        regex.translator("", -2);
        assertFalse(regex.getFound()); 
    }
    
    @Test
    public void testAlternation() {
        regex.setRegex("ylli|yyteri");
        regex.setString("yyteri");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testAlternationTwo() {
        regex.setRegex("ylli|yyteri");
        regex.setString("ylliyyteri");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testAlternationThree() {
        regex.setRegex("ylli|yyteri|");
        regex.setString("");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testQuestionmark() {
        regex.setRegex("a?ccb?");
        regex.setString("acc");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testQuestionmarkTwo() {
        regex.setRegex("a?ccb?");
        regex.setString("aacc");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testQuestionmarkThree() {
        regex.setRegex("a?(ccb)?");
        regex.setString("a");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testPlus() {
        regex.setRegex("b+s");
        regex.setString("bbs");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testPlusTwo() {
        regex.setRegex("b+s");
        regex.setString("s");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testPlusThree() {
        regex.setRegex("(sb)+");
        regex.setString("sbsb");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDot() {
        regex.setRegex("a.c");
        regex.setString("abc");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDotTwo() {
        regex.setRegex("a.+b");
        regex.setString("aacb");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testStar() {
        regex.setRegex("ab*a");
        regex.setString("abba");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testStarTwo() {
        regex.setRegex("ab*a");
        regex.setString("aa");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    //As backslash is the escape character also in Java, doubles are needed to use it once
    
    @Test
    public void testBackslash() {
        regex.setRegex("a\\*\\+");
        regex.setString("a*+");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testBackslashTwo() {
        regex.setRegex("a\\**\\)?d\\\\e");
        regex.setString("a*******)d\\e");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testBackslash3() {
        regex.setRegex("(a*)\\?d\\e*");
        regex.setString("aaaa?d");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testParentheses() {
        regex.setRegex("(lu|ku|tai)mi");
        regex.setString("kumi");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testParenthesesTwo() {
        regex.setRegex("(tuo|kaa|kah|vi|a)*");
        regex.setString("tuokaatuokaakahvia");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testEpsilon() {
        regex.setRegex("a\\eb");
        regex.setString("ab");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDigit() {
        regex.setRegex("\\d*abc");
        regex.setString("123abc");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void testDigitTwo() {
        regex.setRegex("\\d*abc");
        regex.setString("abcabc");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void testDigitThree() {
        regex.setRegex("\\d*abc");
        regex.setString("!abc");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void comboTest1() {
        regex.setRegex("a(\\\\*|(b+a.)|\\.?)*k(a)");
        regex.setString("a\\\\\\.bai..ka");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void comboTest2() {
        regex.setRegex("a(b*(\\\\+a.b?)*)*");
        regex.setString("a\\a#bb\\\\aibb");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void comboTest3() {
        regex.setRegex("ab(c|d)+.*()");
        regex.setString("xabd");
        regex.translator("", regex.getRegex().length() - 1);
        assertFalse(regex.getFound());
    }
    
    @Test
    public void comboTest4() {
        regex.setRegex("a(bb\\)|\\(cd|\\e?|@%!)*\\*+");
        regex.setString("abb)(cd@%!bb)***");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void comboTest5() {
        regex.setRegex("a((bc+|\\d*)\\|123\\e!)");
        regex.setString("a1234567890|123!");
        regex.translator("", regex.getRegex().length() - 1);
        assertTrue(regex.getFound());
    }
    
    @Test
    public void getterWorks() {
        regex.setRegex("jjjj");
        regex.setString("jjjj");
        assertEquals("jjjj", regex.getString());
    }
}
