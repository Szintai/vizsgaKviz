/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author :)
 */
public class Zene implements Runnable{
    
    private String zenefajlEleres;
    private InputStream stream; 
    private Player player;
    
    public void setZeneFajlEleres(String zenefajlEleres) {
        try {
            this.zenefajlEleres = zenefajlEleres;
            stream = this.getClass().getResourceAsStream(zenefajlEleres);        
            player = new Player(stream);
        } catch (JavaLayerException ex) {
            Logger.getLogger(Zene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        try {
            player.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(Zene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leall() {
        player.close();
    }   

    public String getZenefajlEleres() {
        return zenefajlEleres;
    }    
}
