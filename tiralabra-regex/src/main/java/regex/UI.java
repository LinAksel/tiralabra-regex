/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 *
 * @author linaksel
 */
public class UI {
    
    private IO io;
    private Regex regex;
            
    public UI(IO io){
        this.io = io;
        this.regex = new Regex();
    }
    
    public void suorita() {
        System.out.println("Enter regular expression");
        regex.setRegex(io.next());
        while(true) {
            regex.setSana(io.next());
            if (regex.getSana().equals("exit!")) {
                System.out.println("To exit, enter 'e', else compare");
                if(io.next().toLowerCase().equals("e")){
                    break;
                }
            }
            if (regex.getSana().equals("change!")) {
                System.out.println("To change regex, enter 'r', else compare");
                if(io.next().toLowerCase().equals("r")){
                    System.out.println("Enter regular expression");
                    regex.setRegex(io.next());
                    continue;
                }
            }
            
            if (regex.getSana().equals("regex!")) {
                System.out.println("To print regex, enter 'r', else compare");
                if(io.next().toLowerCase().equals("r")){
                    System.out.println(regex.getRegex());
                    continue;
                }
            }
            regex.tulkki("", regex.getRegex().length() - 1);
            io.print(regex.getFound());
            regex.setFalse();
        }
    }
    
}
