/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import static org.junit.Assert.assertFalse;
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
    
    //This should be true, since the empty space is read as epsilon
    
    @Test
    public void checkForDoubleAlternation() {
        tarkastaja = new Tarkastaja("aa||bb");
        assertTrue(tarkastaja.tarkasta());
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
    public void checkForParenthesis() {
        tarkastaja = new Tarkastaja("a(b))");
        assertFalse(tarkastaja.tarkasta());
    }
    
    @Test
    public void checkForWrongBackslash() {
        tarkastaja = new Tarkastaja("ab\\c");
        assertFalse(tarkastaja.tarkasta());
    }
    
}
