/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex.io;

/**
 * Rajapinta merkkijonon I/O-logiikalle.
 * @author linaksel
 */
public interface IO {
    
    /**
     * Sisäänlukemisen rajapintametodi.
     * @return Palauttaa luetun syötteen.
     */
    String next();
    /**
     * Tulostamisen rajapintametodi.
     * @param string Merkkijono, joka halutaan tulostaa.
     */
    void print(String string);
}
