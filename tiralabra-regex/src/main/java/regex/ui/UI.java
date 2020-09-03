/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.ui;

import regex.io.IO;
import regex.logic.Validator;
import regex.logic.Regex;

/**
 *
 * @author linaksel
 */
public class UI {
    
    private IO io;
    private Regex regex;
    private Validator validator;
            
    public UI(IO io) {
        this.io = io;
        this.regex = new Regex();
    }
    
    public void start() {
        io.print("Enter regular expression");
        regex.setRegex(io.next());
        validator = new Validator(regex.getRegex());
        while (!validator.validate()) {
            io.print(validator.getMessage());
            regex.setRegex(io.next());
            validator = new Validator(regex.getRegex());
        }
        while (true) {
            io.print("Enter string to compare");
            regex.setString(io.next());
            if (regex.getString().equals("exit!")) {
                io.print("To exit, enter 'e', else compare");
                if (io.next().toLowerCase().equals("e")) {
                    break;
                }
            }
            if (regex.getString().equals("change!")) {
                io.print("To change regex, enter 'r', else compare");
                if (io.next().toLowerCase().equals("r")) {
                    io.print("Enter regular expression");
                    String newregex = io.next();
                    validator = new Validator(newregex);
                    if (validator.validate()) {
                        regex.setRegex(newregex);
                    } else {
                        io.print(validator.getMessage() + ". Backing down to previous regular expression");
                    }
                    continue;
                }
            }
            
            if (regex.getString().equals("regex!")) {
                io.print("To print regex, enter 'r', else compare");
                if (io.next().toLowerCase().equals("r")) {
                    System.out.println(regex.getRegex());
                    continue;
                }
            }
            
            regex.translator("", regex.getRegex().length() - 1);
            io.print(Boolean.toString(regex.getFound()));
            regex.reset();
        }
    }
    
}
