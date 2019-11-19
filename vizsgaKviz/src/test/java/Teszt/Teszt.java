/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teszt;

import adatkezeles.Dao;
import adatkezeles.KerdesDao;
import adatkezeles.KerdesDaoInterfesz;
import alaposztalyok.JatekMod;
import alaposztalyok.Jatekos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import vezerles.Vezerlo;
import vezerles.Vezerlo.JatekFajta;

/**
 *
 * @author Pintér Ádám
 */
public class Teszt {

    Jatekos jatekos1;
    Jatekos jatekos2;
    String nev1 = "Kristóf";
    JatekFajta jatekFajta1 = JatekFajta.Gyorsasagi;
    String nev2 = "Ádám";
    JatekFajta jatekFajta2 = JatekFajta.Ugyessegi;
    JatekMod jatekMod1;
    JatekMod jatekMod2;
    int rosszValaszGyorsasagi=2;
    int rosszValaszUgyessegi=3;
    private int pont=10;

    @Before
    public void setUp() {
        jatekos1 = new Jatekos(nev1);
        jatekos2 = new Jatekos(nev2);
        jatekMod1=new JatekMod(rosszValaszGyorsasagi, jatekFajta1);
        jatekMod2=new JatekMod(rosszValaszUgyessegi, jatekFajta2);
    }

    private Connection kapcsolodas() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:TestDB;create=true;";

        Connection kapcsolat = DriverManager.getConnection(url);

        String sqlVaneMarTabla = "SELECT * FROM SYS.SYSTABLES WHERE tablename='KERDESEK'";

        try (Statement utasitasObjektum = kapcsolat.createStatement();
                ResultSet rs = utasitasObjektum.executeQuery(sqlVaneMarTabla)) {
            if (!rs.next()) {
                String sqlTablakeszites = "Create Table APP.KERDESEK(kerdes char(100), aValasz char(100),bValasz char(100),"
                        + "cValasz char(100), helyesValasz char(1),temakor char(100)  )";
                utasitasObjektum.execute(sqlTablakeszites);
            }
        }

        return kapcsolat;
    }
    
        private Connection angolKapcsolodas() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:Test1DB;create=true;";

        Connection kapcsolat = DriverManager.getConnection(url);

        String sqlVaneMarTabla = "SELECT * FROM SYS.SYSTABLES WHERE tablename='ANGOLKERDESEK'";

        try (Statement utasitasObjektum = kapcsolat.createStatement();
                ResultSet rs = utasitasObjektum.executeQuery(sqlVaneMarTabla)) {
            if (!rs.next()) {
                String sqlTablakeszites = "Create Table APP.ANGOLKERDESEK(kerdes char(100), aValasz char(100),bValasz char(100),"
                        + "cValasz char(100), helyesValasz char(1),temakor char(100)  )";
                utasitasObjektum.execute(sqlTablakeszites);
            }
        }

        return kapcsolat;
    }

    @Test
    public void Test() throws Exception {

        jatekos1.pontotKap(pont);
        assertTrue(jatekos1.getPontSzam() == pont);
        jatekos2.pontotKap(pont+pont);
        assertTrue(jatekos2.getPontSzam() == pont+pont);
        
        for (int i = 0; i < rosszValaszGyorsasagi; i++) {
           jatekMod1.jatekVegeLekeres();
        }
        assertTrue(jatekMod1.jatekVegeLekeres()== false);
        
        for (int i = 0; i < rosszValaszUgyessegi-1; i++) {
             jatekMod2.jatekVegeLekeres();
        }
        assertTrue(jatekMod2.jatekVegeLekeres()!= false);
        

        KerdesDaoInterfesz dao = new KerdesDao(kapcsolodas(), angolKapcsolodas());

        try {
            int listaMeret1 = dao.kerdesek().size();
            int listaMeret2 = dao.angolKerdesek().size();
            
            dao.kerdesLetrehozas();
            dao.angolKerdesLetrehozas();
            dao.angolKerdesLetrehozas();

            assertTrue(dao.kerdesek().size() > listaMeret1 );
            assertTrue(dao.angolKerdesek().size() >listaMeret2);

        } catch (Exception ex) {
            Logger.getLogger(Teszt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            kapcsolodas().rollback();
            kapcsolodas().close();
            angolKapcsolodas().rollback();
            angolKapcsolodas().close();
        }

    }

}
