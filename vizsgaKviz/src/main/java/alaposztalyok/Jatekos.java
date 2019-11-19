/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alaposztalyok;

import java.util.Objects;

/**
 *
 * @author Szintai
 */
public class Jatekos implements Comparable<Jatekos>{

    private String nev;
    private int pontSzam;
    private int jelenlegiPontSzam;


    public Jatekos(String nev) {
        this.nev = nev;
    }

    public Jatekos(String nev, int pontSzam) {
        this.nev = nev;
        this.pontSzam = pontSzam;
    }

    public void pontotKap(int pont) {
        jelenlegiPontSzam += pont;

        if (jelenlegiPontSzam > pontSzam) {
            pontSzam = jelenlegiPontSzam;
        }
    }

    @Override
    public String toString() {
        return nev + ", " + pontSzam + " pont";
    }

    public String getNev() {
        return nev;
    }

    public int getPontSzam() {
        return pontSzam;
    }

    public int getJelenlegiPontSzam() {
        return jelenlegiPontSzam;
    }

    public void setJelenlegiPontSzam(int jelenlegiPontSzam) {
        this.jelenlegiPontSzam = jelenlegiPontSzam;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jatekos other = (Jatekos) obj;
        if (!Objects.equals(this.nev, other.nev)) {
            return false;
        }
        return true;
    }

   

    @Override
    public int compareTo(Jatekos t) {
        return t.getPontSzam() - pontSzam;
    }

}
