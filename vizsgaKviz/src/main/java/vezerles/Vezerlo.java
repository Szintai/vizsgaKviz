/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vezerles;

import adatkezeles.Dao;
import adatkezeles.JatekosDao;
import adatkezeles.KerdesDao;
import adatkezeles.KerdesDaoInterfesz;
import alaposztalyok.JatekMod;
import alaposztalyok.Jatekos;
import alaposztalyok.Kerdes;
import alaposztalyok.KerdesKezelo;
import alaposztalyok.Temakor;
import alaposztalyok.Zene;
import feluletek.BejelentkezoPanel;
import feluletek.JatekTerPanel;
import feluletek.MenuPanel;
import feluletek.BeallitasPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Szintai
 */
public class Vezerlo implements Runnable {

    JatekTerPanel jatekPanel;
    BejelentkezoPanel bejelentkezoPanel;
    MenuPanel menuPanel;
    BeallitasPanel temakorPanel;
    private int HELYESVALASZ_PONTSZAM = 10;
    private int rosszValaszGyorsasagiMod = 2;
    private int rosszValaszUgyessegiMod = 3;
    private String ZENE_ELERES = "/zene/alapZene.mp3";
    private String ZENE_ELERES1 = "/zene/joValasz.mp3";
    private String ZENE_ELERES2 = "/zene/rosszValasz.mp3";
    private Temakor temakor;
    private Thread szal;
    private int KEZDOPONTSZAM=0;
    private List<Kerdes> osszesKerdes = new ArrayList<Kerdes>();
    private List<Kerdes> angolKerdes = new ArrayList<Kerdes>();
    private List<Kerdes> marElojottKerdes = new ArrayList<Kerdes>();
    private List<Jatekos> beolvasottJatekosLista = new ArrayList<Jatekos>();
    private List<Jatekos> jatekosLista = new ArrayList<Jatekos>();
    private Jatekos jatszoJatekos;
    private Dao dao;
    private KerdesDaoInterfesz kerdesDaoInterfesz;
    private boolean kellEFelotlteniAzAdatBazist = false;
    private Kerdes kivalasztottKerdes = null;
    private long altatasi_ido = 1000;
    private static int idoVegErtek = -1;
    private static int visszaMaradoIdo = 0;
    private JTextField txtFieldValasz = null;
    private String valasztottValasz;
    private int kerdesMegvalaszolasiIdo = 2;
    private int ujKerdesKesleltetesiIdo = 4;
    private boolean jatekIdoE = false;
    private int jatekIdoGyorsasagi = 10;
    private int jatekIdoUgyessegi = 15;
    private int helyesValaszMegmutatasaIdo = 2;
    JatekMod jatekMod;
    JatekFajta jatekFajta;
    String valasztottMod;
    Zene zene;
    Zene joValaszHang;
    Zene rosszValaszHang;
    boolean magyarNyelvue = true;
    private int GYORSASAGIIDOPONTSZAM = 0;
    private boolean jatekVegeE = false;
    private boolean hangBeVagyKi = false;

    public enum JatekFajta {
        Gyorsasagi, Ugyessegi
    };

    public enum TemakorEnum {
        Tortenelmi, Foldrajzi, Bulvar, Elovilag, Zene, FilmEsSzinhaz, Kepzomuveszeti,
        Irodalmi, EtelekEsItalok, Unnepek, Csillagaszat, TvSorozat, KozlekedesEsJarmuvek, SzolasokEsKozmondasok, Egyeb
    }

    public Vezerlo(JatekTerPanel jatekPanel, BejelentkezoPanel bejelentkezoPanel, MenuPanel menuPanel, BeallitasPanel temakorPanel) {
        this.jatekPanel = jatekPanel;
        this.bejelentkezoPanel = bejelentkezoPanel;
        this.menuPanel = menuPanel;
        this.temakorPanel = temakorPanel;
    }

    void statikusbeallitas() {
        zene = new Zene();
        joValaszHang = new Zene();
        rosszValaszHang = new Zene();
        Temakor.setVezerlo(this);
        jatekosokAtadasa();
        adatbazisMegnyitas();
    }

    public void panelCsere(boolean bejelententkezes, boolean temakor, boolean jatek, boolean menu) {

        bejelentkezoPanel.setVisible(bejelententkezes);
        menuPanel.setVisible(menu);
        temakorPanel.setVisible(temakor);
        jatekPanel.setVisible(jatek);

    }

    public void jatekInditas(int jatekModSzam) {
        jatekPanel.kerdesTxtEltuntetes();
        jatekLetrehozas(jatekModSzam);
        switch (jatekFajta) {
            case Gyorsasagi:
                visszaMaradoIdo = jatekIdoGyorsasagi;
                break;
            case Ugyessegi:
                visszaMaradoIdo = jatekIdoUgyessegi;
                break;
        }

        jatekIdoE = true;
        jatekVegeE = false;
        kerdesTemakorKezelo();
        jatek();
    }

    public void nevAtadas(String nev) {
        jatszoJatekos = new Jatekos(nev);
        jatekosVizsgalat();
    }

    private void jatekosVizsgalat() {
        boolean szerepel = false;

        for (Jatekos jatekos : jatekosLista) {
            if (jatekos.getNev().equals(bejelentkezoPanel.getTxtNev().getText())) {
                szerepel = true;
                jatszoJatekos = jatekos;
                jatszoJatekos.setJelenlegiPontSzam(KEZDOPONTSZAM);
            }
        }

        if (!szerepel) {

            try {
                jatszoJatekos = new Jatekos(bejelentkezoPanel.getTxtNev().getText());
                jatekosLista.add(jatszoJatekos);
                dao.jatekosLetrehozas(jatszoJatekos);
            } catch (Exception ex) {
                Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void jatekosPontSzamAdatbazisFrissites() {
        try {
            dao.jatekosFrissites(jatszoJatekos);
        } catch (Exception ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jatekosokAtadasa() {
        try {
            dao = new JatekosDao(kapcsolodas());
            beolvasottJatekosLista = dao.jatekos();
            jatekosLista = beolvasottJatekosLista;
            bejelentkezoPanel.listaAtadas(jatekosLista);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void adatMentes() {
        for (Jatekos jatekos : jatekosLista) {
            try {
                if (beolvasottJatekosLista.contains(jatekos)) {
                    for (Jatekos jatekosBeolv : beolvasottJatekosLista) {
                        if (jatekosBeolv.equals(jatekos) && jatekosBeolv.getPontSzam() < jatekos.getPontSzam()) {
                            dao.jatekosFrissites(jatekos);
                        }
                    }
                } else {
                    dao.jatekosLetrehozas(jatekos);
                    beolvasottJatekosLista.add(jatekos);
                }

            } catch (Exception ex) {
                Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jatek() {

        KerdesKezelo rnd;
        
        if(magyarNyelvue && marElojottKerdes.size() == osszesKerdes.size())
        {   JOptionPane.showMessageDialog(null, jatekPanel.getNINCSTOBBKERDES());
            jatekVege();}
        
        
        if(!magyarNyelvue && marElojottKerdes.size() == angolKerdes.size())
        {   JOptionPane.showMessageDialog(null, jatekPanel.getNINCSTOBBKERDES());
            jatekVege();}
        
        if (magyarNyelvue) {
            rnd = new KerdesKezelo(osszesKerdes);
        } else {
            rnd = new KerdesKezelo(angolKerdes);
        }
        
        boolean tartalmazzaE=true;
        while(tartalmazzaE)
        {
           kivalasztottKerdes = rnd.visszaadottKerdes();
        if(!marElojottKerdes.contains(kivalasztottKerdes))
        {tartalmazzaE=false;}
        }
        marElojottKerdes.add(kivalasztottKerdes);
        jatekPanel.kerdesBeallitas(kivalasztottKerdes);
        gombAlaphelyzet();

        switch (jatekFajta) {
            case Gyorsasagi:
                visszaMaradoIdo = jatekIdoGyorsasagi;
                break;
            case Ugyessegi:
                visszaMaradoIdo = jatekIdoUgyessegi;
                break;
        }

        jatekIdoE = true;
        szal = new Thread(this);
        szal.start();
        eletSzamKuldes();
    }

    void adatbazisMegnyitas() {

        try {
            kerdesDaoInterfesz = new KerdesDao(kerdesekKapcsolodas(), angolKerdesekKapcsolodas());
            if (kellEFelotlteniAzAdatBazist) {
                kerdesDaoInterfesz.kerdesLetrehozas();
                kerdesDaoInterfesz.angolKerdesLetrehozas();
            }

            osszesKerdes = kerdesDaoInterfesz.kerdesek();
            angolKerdes = kerdesDaoInterfesz.angolKerdesek();
        } catch (Exception ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Connection kapcsolodas() throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:JatekosDB;create=true;";

        Connection kapcsolat = DriverManager.getConnection(url);

        String sqlVaneMarTabla = "SELECT * FROM SYS.SYSTABLES WHERE tablename='JATEKOSOK'";

        try (Statement utasitasObjektum = kapcsolat.createStatement();
                ResultSet rs = utasitasObjektum.executeQuery(sqlVaneMarTabla)) {

            if (!rs.next()) {
                String sqlTablakeszites = "Create table APP.JATEKOSOK(nev varchar(50), pontszam int)";
                utasitasObjektum.execute(sqlTablakeszites);
            }

        }

        return kapcsolat;
    }

    private Connection kerdesekKapcsolodas() throws ClassNotFoundException, SQLException, Exception {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:KerdesDB;create=true;";

        Connection kapcsolat = DriverManager.getConnection(url);

        String sqlVaneMarTabla = "SELECT * FROM SYS.SYSTABLES WHERE tablename='KERDESEK'";

        try (Statement utasitasObjektum = kapcsolat.createStatement();
                ResultSet rs = utasitasObjektum.executeQuery(sqlVaneMarTabla)) {

            if (!rs.next()) {
                String sqlTablakeszites = "Create Table APP.KERDESEK(kerdes char(80), aValasz char(80),bValasz char(80),"
                        + "cValasz char(80), helyesValasz char(1),temakor char(50)  )";

                kellEFelotlteniAzAdatBazist = true;
                utasitasObjektum.execute(sqlTablakeszites);

            }

        }

        return kapcsolat;

    }

    private Connection angolKerdesekKapcsolodas() throws ClassNotFoundException, SQLException, Exception {

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        String url = "jdbc:derby:AngolKerdesDB;create=true;";

        Connection kapcsolat = DriverManager.getConnection(url);

        String sqlVaneMarTabla = "SELECT * FROM SYS.SYSTABLES WHERE tablename='ANGOLKERDESEK'";

        try (Statement utasitasObjektum = kapcsolat.createStatement();
                ResultSet rs = utasitasObjektum.executeQuery(sqlVaneMarTabla)) {

            if (!rs.next()) {
                String sqlTablakeszites = "Create Table APP.ANGOLKERDESEK(kerdes char(80), aValasz char(80),bValasz char(80),"
                        + "cValasz char(80), helyesValasz char(1),temakor char(80)  )";

                kellEFelotlteniAzAdatBazist = true;
                utasitasObjektum.execute(sqlTablakeszites);

            }

        }

        return kapcsolat;

    }

    public void kerdesKezelo(String valasz, JTextField valaszTxtField) {

        valasztottValasz = valasz;
        txtFieldValasz = valaszTxtField;
        visszaMaradoIdo = ujKerdesKesleltetesiIdo;
        jatekIdoE = false;

    }

    public void gombAlaphelyzet() {
        if (!jatekPanel.getBtnA().isEnabled()) {
            jatekPanel.gombElerhetosegCsere();
        }
        jatekPanel.szincsere(jatekPanel.getTxtAValasz(), 3);
        jatekPanel.szincsere(jatekPanel.getTxtBValasz(), 3);
        jatekPanel.szincsere(jatekPanel.getTxtCValasz(), 3);
    }

    @Override
    public void run() {
        while (visszaMaradoIdo != idoVegErtek && jatekIdoE && !jatekVegeE) {
            jatekPanel.getTxtVisszaMaradoIdo().setText(jatekPanel.getVISSZAMARADO_IDO() + visszaMaradoIdo);
            GYORSASAGIIDOPONTSZAM = visszaMaradoIdo;
            alszik();
            csokkent();
            if (visszaMaradoIdo == idoVegErtek) {
                valasztottValasz = null;
                helyesValaszMutatas();
            }
        }
        if (visszaMaradoIdo == idoVegErtek && !jatekVegeE) {
            visszaMaradoIdo = helyesValaszMegmutatasaIdo;
            jatekIdoE = false;
        }
        while (visszaMaradoIdo != idoVegErtek && !jatekIdoE && !jatekVegeE) {
            if (visszaMaradoIdo == kerdesMegvalaszolasiIdo && valasztottValasz != null) {

                kerdesVisszateres();
            }
            alszik();
            csokkent();
        }

        if (!jatekIdoE && !jatekVegeE) {
            jatek();
        }
        eletSzamKuldes();

    }

    private void csokkent() {
        visszaMaradoIdo--;
    }

    private void alszik() {

        try {
            Thread.sleep(altatasi_ido);
        } catch (InterruptedException ex) {
            Logger.getLogger(Vezerlo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void kerdesVisszateres() {

        if (kivalasztottKerdes.helyesValasz().equals(valasztottValasz)) {
            jatekPanel.szincsere(txtFieldValasz, 1);
            if (jatszoJatekos != null) {
                if (hangBeVagyKi) {
                    zeneJoValaszBeKapcsolas();
                }
                switch (jatekFajta) {
                    case Gyorsasagi:
                        jatszoJatekos.pontotKap(HELYESVALASZ_PONTSZAM + GYORSASAGIIDOPONTSZAM);
                        break;
                    case Ugyessegi:
                        jatszoJatekos.pontotKap(HELYESVALASZ_PONTSZAM);
                        break;
                }
                jatekosPontSzamAdatbazisFrissites();
                jatekPanel.pontSzam(jatszoJatekos.getJelenlegiPontSzam());

            }
        } else {
            if (hangBeVagyKi) {
                zeneRosszValaszBeKapcsolas();
            }
            jatekPanel.szincsere(txtFieldValasz, 2);

            if (kivalasztottKerdes.getElsoValasz().isHelyesE()) {
                jatekPanel.joValaszMutatas(kivalasztottKerdes.getElsoValasz().getJelenlegiValaszHely());
            }

            if (kivalasztottKerdes.getMasodikValasz().isHelyesE()) {
                jatekPanel.joValaszMutatas(kivalasztottKerdes.getMasodikValasz().getJelenlegiValaszHely());
            }

            if (kivalasztottKerdes.getHarmadikValasz().isHelyesE()) {
                jatekPanel.joValaszMutatas(kivalasztottKerdes.getHarmadikValasz().getJelenlegiValaszHely());
            }

            if (this.jatekMod.jatekVegeLekeres()) {
                jatekVege();
            }
        }

    }

    private void helyesValaszMutatas() {
        if (kivalasztottKerdes.getElsoValasz().isHelyesE()) {
            jatekPanel.joValaszMutatas(kivalasztottKerdes.getElsoValasz().getJelenlegiValaszHely());
        }

        if (kivalasztottKerdes.getMasodikValasz().isHelyesE()) {
            jatekPanel.joValaszMutatas(kivalasztottKerdes.getMasodikValasz().getJelenlegiValaszHely());
        }

        if (kivalasztottKerdes.getHarmadikValasz().isHelyesE()) {
            jatekPanel.joValaszMutatas(kivalasztottKerdes.getHarmadikValasz().getJelenlegiValaszHely());
        }

        if (this.jatekMod.jatekVegeLekeres()) {
            jatekVege();
        }
    }

    private void jatekLetrehozas(int jatekMod) {

        switch (jatekMod) {
            case 0:
                jatekFajta = jatekFajta.Gyorsasagi;
                this.jatekMod = new JatekMod(rosszValaszGyorsasagiMod, jatekFajta);
                break;
            case 1:
                jatekFajta = jatekFajta.Ugyessegi;
                this.jatekMod = new JatekMod(rosszValaszUgyessegiMod, jatekFajta);
                break;

        }

    }

    private void eletSzamKuldes() {
        jatekPanel.eletBeallitas(jatekMod.elet());
    }

    public void jatekVege() {
        marElojottKerdes.clear();
        jatekVegeE = true;
        if (jatszoJatekos != null) {
            JOptionPane.showMessageDialog(null, jatekPanel.getTxtPontSzam().getText());
        }
        adatMentes();
        bejelentkezoPanel.listaAtadas(jatekosLista);
        panelCsere(false, false, false, true);
    }

    public void nyelvvaltas(ResourceBundle Bundle) {
        magyarNyelvue = !magyarNyelvue;
        menuPanel.nyelvBeallitas(Bundle);
        bejelentkezoPanel.nyelvBeallitas(Bundle);
        jatekPanel.nyelvBeallitas(Bundle);

    }

    public void zeneKikapcsolas() {
        zene.leall();
    }

    public synchronized void zeneBeKapcsolas() {
        Thread zeneSzal = new Thread(zene);

        if (!zeneSzal.isAlive()) {
            zene.setZeneFajlEleres(ZENE_ELERES);
            zeneSzal.start();
        }

    }

    public synchronized void zeneRosszValaszBeKapcsolas() {
        Thread rosszValaszSzal = new Thread(rosszValaszHang);

        if (!rosszValaszSzal.isAlive()) {
            rosszValaszHang.setZeneFajlEleres(ZENE_ELERES2);
            rosszValaszSzal.start();
        }

    }

    public void rosszValaszHangKikapcsolas() {
        rosszValaszHang.leall();
    }

    public synchronized void zeneJoValaszBeKapcsolas() {
        Thread joValaszSzal = new Thread(joValaszHang);

        if (!joValaszSzal.isAlive()) {
            joValaszHang.setZeneFajlEleres(ZENE_ELERES1);
            joValaszSzal.start();
        }

    }

    public void zeneJoVajoValaszHangikapcsolas() {
        joValaszHang.leall();
    }

    private void kerdesTemakorKezelo() {

        temakor = new Temakor(temakorPanel.kerdesTemakorLekeres(),
                osszesKerdes);

        if (temakor.listaVisszaadas() != null || !temakorPanel.kerdesTemakorLekeres().isEmpty()) {
            osszesKerdes.clear();
            for (Kerdes kerdes : temakor.listaVisszaadas()) {
                osszesKerdes.add(kerdes);
            }
        }

    }

    public void menuPanelHangBEKI(boolean beVagyKi) {
        hangBeVagyKi = !beVagyKi;
        menuPanel.hangBEKI(beVagyKi);
    }

    public void beallitasPanelHangBEKI(boolean beVagyKi) {
        hangBeVagyKi = !beVagyKi;
        temakorPanel.hangBEKI(beVagyKi);
    }

    public void temakorMenuElerhetosegCsere(boolean v) {

        menuPanel.temakorMenuElerhetosegCsere(v);
    }

    public void bundleBeallitasPanel(boolean magyar, boolean angol) {

        temakorPanel.bundleBeallitasPanel(magyar, angol);

    }

    public void bundleMenuPanel(boolean magyar, boolean angol) {

        menuPanel.bundleMenuPanel(magyar, angol);

    }

}
