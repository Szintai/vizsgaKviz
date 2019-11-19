/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import vezerles.Vezerlo.JatekFajta;

/**
 *
 * @author Szintai
 */
public class JatekMod {
    
    private int rosszValasz;
    private int rosszValaszSeged;
   
    JatekFajta jatekFajta;


    public JatekMod(int rosszValasz,JatekFajta jatekFajta) {
        this.rosszValasz = rosszValasz;
        this.jatekFajta = jatekFajta;
    }

    
    
    public boolean jatekVegeLekeres()
    {
    
    rosszValaszSeged++;
    
    if(rosszValasz == rosszValaszSeged)
    {return true;}
    
    
    return false;
    }

    public JatekFajta getJatekFajta() {
        return jatekFajta;
    }
    
    public int elet()
    {
    return rosszValasz-rosszValaszSeged;}
    
    
}
