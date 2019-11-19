/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import java.util.ArrayList;
import java.util.List;
import vezerles.Vezerlo;
import vezerles.Vezerlo.TemakorEnum;

/**
 *
 * @author Szintai
 */
public class Temakor {

    private static Vezerlo vezerlo;
    private List<TemakorEnum> temakor=null;
    private List<Kerdes> kerdesek= new ArrayList<>();
    private List<Kerdes> osszesKerdes;
 

    public Temakor(List<TemakorEnum> temakor, List<Kerdes> osszesKerdes) {
        this.temakor = temakor;
        this.osszesKerdes = osszesKerdes;
    }

    public List<Kerdes> listaVisszaadas() {
        

     
        for (Kerdes kerdes : osszesKerdes) {
            for (TemakorEnum temakorEnum : temakor) {
               if (temakorEnum.equals(kerdes.getTemakorEnum())) {
                    kerdesek.add(kerdes);
                } 
            }
  
        }
       
        
        
        
        if (kerdesek.isEmpty()) {
            return null;
        } else {
            return kerdesek;
        }

    }

    public static void setVezerlo(Vezerlo vezerlo) {
        Temakor.vezerlo = vezerlo;
    }

    public List<Kerdes> getKerdesek() {
        return kerdesek;
    }

    

}
