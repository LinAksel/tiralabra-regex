/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import static org.junit.Assert.assertEquals;
import regex.logic.Tarkastaja;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author linaksel
 */

public class TestTarkastaja {
    
    private Tarkastaja tarkastaja;
    
    @Test
    public void checkForDoubleStars() {
        tarkastaja = new Tarkastaja("aabb**");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForDoublePlus() {
        tarkastaja = new Tarkastaja("aabb++");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForDoubleQuestion() {
        tarkastaja = new Tarkastaja("aabb??");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForDoubleAlternation() {
        tarkastaja = new Tarkastaja("aa||bb");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForBadStart1() {
        tarkastaja = new Tarkastaja("+aabb");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForBadStart2() {
        tarkastaja = new Tarkastaja("?aabb");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForBadStart3() {
        tarkastaja = new Tarkastaja("*aabb");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForBadStart4() {
        tarkastaja = new Tarkastaja("|aabb");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForBadStart5() {
        tarkastaja = new Tarkastaja(")aabc");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForParenthesis() {
        tarkastaja = new Tarkastaja("a(b)(");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForParenthesis2() {
        tarkastaja = new Tarkastaja("a)b(");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForWrongBackslash() {
        tarkastaja = new Tarkastaja("ab\\c.");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void rightUseBackslash() {
        tarkastaja = new Tarkastaja("ab\\\\cd");
        assertTrue(tarkastaja.tarkasta());
    }
    
    @Test
    public void rightUseBackslash2() {
        tarkastaja = new Tarkastaja("a\\ebc\\d");
        assertTrue(tarkastaja.tarkasta());
    }
    
    @Test
    public void rightUseBackslash3() {
        tarkastaja = new Tarkastaja("\\(a.c\\)");
        assertTrue(tarkastaja.tarkasta());
    }
    
    @Test
    public void rightUseBackslash4() {
        tarkastaja = new Tarkastaja("\\.(bc)*");
        assertTrue(tarkastaja.tarkasta());
    }
    
    @Test
    public void emptyStringAsRegex() {
        tarkastaja = new Tarkastaja("");
        assertTrue(tarkastaja.tarkasta());
    }
    
    @Test
    public void messageOKWhenNoErrors() {
        tarkastaja = new Tarkastaja("");
        tarkastaja.tarkasta();
        assertEquals("OK", tarkastaja.getViesti());
    }
    
    @Test
    public void messageChangesForIncorrectStart() {
        tarkastaja = new Tarkastaja(")");
        tarkastaja.tarkasta();
        assertNotEquals("OK", tarkastaja.getViesti());
    }
    
    @Test
    public void messageChangesForDoubleQuantifier() {
        tarkastaja = new Tarkastaja("a**bc");
        tarkastaja.tarkasta();
        assertNotEquals("OK", tarkastaja.getViesti());
    }
    
    @Test
    public void messageChangesForNonMatchingParenthesis() {
        tarkastaja = new Tarkastaja("(aa)bb)");
        tarkastaja.tarkasta();
        assertNotEquals("OK", tarkastaja.getViesti());
    }
    
    @Test
    public void messageChangesForWrongEscape() {
        tarkastaja = new Tarkastaja("(aa)b\\b");
        tarkastaja.tarkasta();
        assertNotEquals("OK", tarkastaja.getViesti());
    }
    
}
