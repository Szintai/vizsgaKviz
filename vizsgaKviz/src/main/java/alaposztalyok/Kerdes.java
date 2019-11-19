/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;



import vezerles.Vezerlo.TemakorEnum;

/**
 *
 * @author Szintai
 */
public class Kerdes {

    private String kerdes;
    private Valasz elsoValasz;
    private Valasz masodikValasz;
    private Valasz harmadikValasz;
    private String temaKor;
    private TemakorEnum temakorEnum;
    private String helyesValaszSeged;

   
    public Kerdes(String kerdes, Valasz elsoValasz, Valasz masodikValasz, Valasz harmadikValasz, String temaKor, TemakorEnum temakorEnum) {
        this.kerdes = kerdes;
        this.elsoValasz = elsoValasz;
        this.masodikValasz = masodikValasz;
        this.harmadikValasz = harmadikValasz;
        this.temaKor = temaKor;

        this.temakorEnum = temakorEnum;
    }

    
    public String getKerdes() {
        return kerdes;
    }

    

    public String getTemaKor() {
        return temaKor;
    }

  
    public TemakorEnum getTemakorEnum() {
        return temakorEnum;
    }

    public Valasz getElsoValasz() {
        return elsoValasz;
    }

    public Valasz getMasodikValasz() {
        return masodikValasz;
    }

    public Valasz getHarmadikValasz() {
        return harmadikValasz;
    }
    
    public String helyesValasz()
    {
    if(elsoValasz.isHelyesE()){
    helyesValaszSeged=elsoValasz.getJelenlegiValaszHely();}
    
    if(masodikValasz.isHelyesE()){
    helyesValaszSeged=masodikValasz.getJelenlegiValaszHely();}
    
    if(harmadikValasz.isHelyesE()){
    helyesValaszSeged=harmadikValasz.getJelenlegiValaszHely();}
    
    return helyesValaszSeged;
    }
    

}
