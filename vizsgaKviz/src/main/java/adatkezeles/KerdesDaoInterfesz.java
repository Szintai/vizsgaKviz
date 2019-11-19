/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatkezeles;

import alaposztalyok.Kerdes;
import java.util.List;

/**
 *
 * @author Szintai
 */
public interface KerdesDaoInterfesz {
    
    public List<Kerdes> kerdesek() throws Exception;
      public void kerdesLetrehozas() throws Exception; 
    public List<Kerdes> angolKerdesek() throws Exception;
      public void angolKerdesLetrehozas() throws Exception; 
        
    
    
}
