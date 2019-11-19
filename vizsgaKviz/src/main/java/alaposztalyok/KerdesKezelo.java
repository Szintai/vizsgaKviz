/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import java.util.List;


/**
 *
 * @author Szintai
 */
public class KerdesKezelo {
    
    private List<Kerdes> kerdesek;

    public KerdesKezelo(List<Kerdes> kerdesek) {
        this.kerdesek = kerdesek;
    }
    
    public Kerdes visszaadottKerdes()
    {
   
    int randomIndex=(int)(Math.random()*kerdesek.size());
    Kerdes kerdes = kerdesek.get(randomIndex);
    
        return kerdes;
    }
    
    
    
}
