/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

/**
 *
 * @author Szintai
 */
public class Valasz {
    
    private String valasz;
    private boolean helyesE;
    private String jelenlegiValaszHely;

    public Valasz(String valasz, boolean helyesE) {
        this.valasz = valasz;
        this.helyesE = helyesE;
    }

    public void setHelyesE(boolean helyesE) {
        this.helyesE = helyesE;
    }

    public String getValasz() {
        return valasz;
    }

    public boolean isHelyesE() {
        return helyesE;
    }

    public String getJelenlegiValaszHely() {
        return jelenlegiValaszHely;
    }

    public void setJelenlegiValaszHely(String jelenlegiValaszHely) {
        this.jelenlegiValaszHely = jelenlegiValaszHely;
    }
    
    
    
    
    
}
