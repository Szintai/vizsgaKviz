/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatkezeles;

import alaposztalyok.Kerdes;
import alaposztalyok.Valasz;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vezerles.Vezerlo.TemakorEnum;

/**
 *
 * @author Szintai
 */
public class KerdesDao implements KerdesDaoInterfesz {

    private Connection kapcsolat;
    private Connection angolKapcsolat;

    public KerdesDao(Connection kapcsolat, Connection angolKapcsolat) {
        this.kapcsolat = kapcsolat;
        this.angolKapcsolat = angolKapcsolat;
    }

    @Override
    public List<Kerdes> kerdesek() throws Exception {

        List<Kerdes> kerdesek = new ArrayList<>();

        if (kapcsolat != null) {
            String sqlUtasitas = "SELECT * FROM KERDESEK";
            String kerdes;
            Valasz elsoValasz, masodikValasz, harmadikValasz;
            String helyesValasz;
            String temakor;
            TemakorEnum temakorEnum = null;

            try (Statement utasitasObj = kapcsolat.createStatement();
                    ResultSet eredmenyHalmaz = utasitasObj.executeQuery(sqlUtasitas)) {

                while (eredmenyHalmaz.next()) {

                    kerdes = eredmenyHalmaz.getString("kerdes");
                    elsoValasz = new Valasz(eredmenyHalmaz.getString("aValasz"), false);
                    masodikValasz = new Valasz(eredmenyHalmaz.getString("bValasz"), false);
                    harmadikValasz = new Valasz(eredmenyHalmaz.getString("cValasz"), false);
                    helyesValasz = eredmenyHalmaz.getString("helyesValasz");
                    temakor = eredmenyHalmaz.getString("temakor");
                    String[] splited = temakor.split(" ");
                    temakor = splited[0];

                    

                    switch (helyesValasz) {
                        case "A":
                            elsoValasz.setHelyesE(true);
                            break;

                        case "B":
                            masodikValasz.setHelyesE(true);
                            break;

                        case "C":
                            harmadikValasz.setHelyesE(true);
                            break;
                    }

                    switch (temakor) {

                        case "tortenelmi":
                            temakorEnum = temakorEnum.Tortenelmi;
                            break;
                        case "foldrajzi":
                            temakorEnum = temakorEnum.Foldrajzi;
                            break;
                        case "bulvar":
                            temakorEnum = temakorEnum.Bulvar;
                            break;
                        case "elovilag":
                            temakorEnum = temakorEnum.Elovilag;
                            break;
                        case "zene":
                            temakorEnum = temakorEnum.Zene;
                            break;
                        case "filmEsSzinhaz":
                            temakorEnum = temakorEnum.FilmEsSzinhaz;
                            break;
                        case "kepzomuveszeti":
                            temakorEnum = temakorEnum.Kepzomuveszeti;
                            break;
                        case "irodalmi":
                            temakorEnum = temakorEnum.Irodalmi;
                            break;
                        case "etelekEsItalok":
                            temakorEnum = temakorEnum.EtelekEsItalok;
                            break;
                        case "unnepek":
                            temakorEnum = temakorEnum.Unnepek;
                            break;
                        case "csillagaszat":
                            temakorEnum = temakorEnum.Csillagaszat;
                            break;
                        case "tvSorozat":
                            temakorEnum = temakorEnum.TvSorozat;
                            break;
                        case "kozlekedesEsJarmuvek":
                            temakorEnum = temakorEnum.KozlekedesEsJarmuvek;
                            break;
                        case "szolasokEsKozmondasok":
                            temakorEnum = temakorEnum.SzolasokEsKozmondasok;
                            break;
                        case "egyeb":
                            temakorEnum = temakorEnum.Egyeb;
                            break;
                    }
                    kerdesek.add(new Kerdes(kerdes, elsoValasz, masodikValasz, harmadikValasz, temakor, temakorEnum));
                }

            }
        }

        return kerdesek;

    }

    @Override
    public void kerdesLetrehozas() throws Exception {

        if (kapcsolat != null) {
            try (Statement utasitasObj = kapcsolat.createStatement()) {
                sorokMagyarBeolvasas(utasitasObj);
            }
        }

    }

    private void sorokAngolBeolvasas(Statement utasitasObj) throws SQLException, IOException {

        String fajlEleres = "/adatok/angolKerdesekEsValaszok.txt";
        String sqlUtasitas = "";
        String sor;
        String[] adatok;

        try (InputStream ins = getClass().getResourceAsStream(fajlEleres);
                Scanner fajlScanner = new Scanner(ins, "UTF-8")) {
            while (fajlScanner.hasNextLine()) {

                sor = fajlScanner.nextLine();
                adatok = sor.split(";");
                sqlUtasitas = "Insert into ANGOLKERDESEK(kerdes, aValasz, bValasz, cValasz, helyesValasz, temakor)"
                        + "values('" + adatok[0] + "','" + adatok[1] + "','" + adatok[2] + "','" + adatok[3] + "','" + adatok[4] + "',' ')";

                utasitasObj.executeUpdate(sqlUtasitas);
            }

        }
    }

    private void sorokMagyarBeolvasas(Statement utasitasObj) throws SQLException, IOException {

        String fajlEleres = "/adatok/magyarKerdesekEsValaszok.txt";
        String sqlUtasitas = "";
        String sor;
        String[] adatok;

        try (InputStream ins = getClass().getResourceAsStream(fajlEleres);
                Scanner fajlScanner = new Scanner(ins, "UTF-8")) {
            while (fajlScanner.hasNextLine()) {

                sor = fajlScanner.nextLine();
                adatok = sor.split(";");
                sqlUtasitas = "Insert into KERDESEK(kerdes, aValasz, bValasz, cValasz, helyesValasz, temakor)"
                        + "values('" + adatok[0] + "','" + adatok[1] + "','" + adatok[2] + "','" + adatok[3] + "','" + adatok[4] + "','" + adatok[5] + "')";

                utasitasObj.executeUpdate(sqlUtasitas);
            }

        }
    }

    @Override
    public List<Kerdes> angolKerdesek() throws Exception {
        List<Kerdes> kerdesek = new ArrayList<>();

        if (angolKapcsolat != null) {
            String sqlUtasitas = "SELECT * FROM ANGOLKERDESEK";
            String kerdes;
            Valasz elsoValasz, masodikValasz, harmadikValasz;
            String helyesValasz;
            String temakor;
            TemakorEnum temakorEnum = null;

            try (Statement utasitasObj = angolKapcsolat.createStatement();
                    ResultSet eredmenyHalmaz = utasitasObj.executeQuery(sqlUtasitas)) {

                while (eredmenyHalmaz.next()) {

                    kerdes = eredmenyHalmaz.getString("kerdes");
                    elsoValasz = new Valasz(eredmenyHalmaz.getString("aValasz"), false);
                    masodikValasz = new Valasz(eredmenyHalmaz.getString("bValasz"), false);
                    harmadikValasz = new Valasz(eredmenyHalmaz.getString("cValasz"), false);
                    helyesValasz = eredmenyHalmaz.getString("helyesValasz");
                    temakor = eredmenyHalmaz.getString("temakor");

                    switch (helyesValasz) {
                        case "A":
                            elsoValasz.setHelyesE(true);
                            break;

                        case "B":
                            masodikValasz.setHelyesE(true);
                            break;

                        case "C":
                            harmadikValasz.setHelyesE(true);
                            break;
                    }

                    kerdesek.add(new Kerdes(kerdes, elsoValasz, masodikValasz, harmadikValasz, temakor, temakorEnum));
                }

            }

        }
        return kerdesek;
    }

    @Override
    public void angolKerdesLetrehozas() throws Exception {
        if (angolKapcsolat != null) {
            try (Statement utasitasObj = angolKapcsolat.createStatement()) {
                sorokAngolBeolvasas(utasitasObj);
            }

        }
    }

}
