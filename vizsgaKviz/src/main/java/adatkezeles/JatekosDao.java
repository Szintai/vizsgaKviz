package adatkezeles;

import alaposztalyok.Jatekos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Szintai
 */
public class JatekosDao implements Dao {

    private Connection kapcsolat;

    public JatekosDao(Connection kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public List<Jatekos> jatekos() throws Exception {

        List<Jatekos> jatekos = new ArrayList<>();

        if (kapcsolat != null) {
            String sqlUtasitas = "SELECT * FROM JATEKOSOK";
            String nev;
            int pontSzam;

            try (Statement utasitasObj = kapcsolat.createStatement();
                    ResultSet eredmenyHalmaz = utasitasObj.executeQuery(sqlUtasitas)) {

                while (eredmenyHalmaz.next()) {
                    nev = eredmenyHalmaz.getString("nev");
                    pontSzam = eredmenyHalmaz.getInt("pontszam");
                    jatekos.add(new Jatekos(nev, pontSzam));
                }

            }
        }

        return jatekos;
    }

    @Override
    public void jatekosLetrehozas(Jatekos jatekos) throws Exception {

        if (kapcsolat != null) {
            try (Statement utasitasObj = kapcsolat.createStatement()) {
                String sqlUtasitas = "INSERT INTO APP.JATEKOSOK VALUES('" + jatekos.getNev() 
                        + "', " + jatekos.getPontSzam() + ")";
                utasitasObj.executeUpdate(sqlUtasitas);
            }
        }

    }

    @Override
    public void jatekosFrissites(Jatekos jatekos) throws Exception {
        if (kapcsolat != null) {
            try (Statement utasitasObj = kapcsolat.createStatement()) {
                String sqlUtasitas = "UPDATE APP.JATEKOSOK set pontszam=" 
                        + jatekos.getPontSzam() + " WHERE nev= '" + jatekos.getNev()+ "'";
                        utasitasObj.executeUpdate(sqlUtasitas);
            }
        }

    }

}
