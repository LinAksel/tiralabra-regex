/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import static org.junit.Assert.assertEquals;
import regex.logic.Validator;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author linaksel
 */

public class TestValidator {
    
    private Validator validator;
    
    @Test
    public void checkForDoubleStars() {
        validator = new Validator("aabb**");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForDoublePlus() {
        validator = new Validator("aabb++");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForDoubleQuestion() {
        validator = new Validator("aabb??");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForDoubleAlternation() {
        validator = new Validator("aa||bb");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForBadStart1() {
        validator = new Validator("+aabb");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForBadStart2() {
        validator = new Validator("?aabb");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForBadStart3() {
        validator = new Validator("*aabb");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForBadStart4() {
        validator = new Validator("|aabb");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForBadStart5() {
        validator = new Validator(")aabc");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForParenthesis() {
        validator = new Validator("a(b)(");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForParenthesis2() {
        validator = new Validator("a)b(");
        assertFalse(validator.validate());
    }
    
    @Test
    public void checkForWrongBackslash() {
        validator = new Validator("ab\\c.");
        assertFalse(validator.validate());
    }
    
    @Test
    public void rightUseBackslash() {
        validator = new Validator("ab\\\\cd");
        assertTrue(validator.validate());
    }
    
    @Test
    public void rightUseBackslash2() {
        validator = new Validator("a\\ebc\\d");
        assertTrue(validator.validate());
    }
    
    @Test
    public void rightUseBackslash3() {
        validator = new Validator("\\(a.c\\)");
        assertTrue(validator.validate());
    }
    
    @Test
    public void rightUseBackslash4() {
        validator = new Validator("\\.(bc)*");
        assertTrue(validator.validate());
    }
    
    @Test
    public void emptyStringAsRegex() {
        validator = new Validator("");
        assertTrue(validator.validate());
    }
    
    @Test
    public void messageOKWhenNoErrors() {
        validator = new Validator("");
        validator.validate();
        assertEquals("OK", validator.getMessage());
    }
    
    @Test
    public void messageChangesForIncorrectStart() {
        validator = new Validator(")");
        validator.validate();
        assertNotEquals("OK", validator.getMessage());
    }
    
    @Test
    public void messageChangesForDoubleQuantifier() {
        validator = new Validator("a**bc");
        validator.validate();
        assertNotEquals("OK", validator.getMessage());
    }
    
    @Test
    public void messageChangesForNonMatchingParenthesis() {
        validator = new Validator("(aa)bb)");
        validator.validate();
        assertNotEquals("OK", validator.getMessage());
    }
    
    @Test
    public void messageChangesForWrongEscape() {
        validator = new Validator("(aa)b\\b");
        validator.validate();
        assertNotEquals("OK", validator.getMessage());
    }
    
}
