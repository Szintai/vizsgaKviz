/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatkezeles;

import alaposztalyok.Jatekos;
import java.util.List;

/**
 *
 * @author Szintai
 */
public interface Dao {
    
    public List<Jatekos> jatekos() throws Exception;
    public void jatekosLetrehozas(Jatekos jatekos) throws Exception;
    public void jatekosFrissites(Jatekos jatekos) throws Exception;
    
}