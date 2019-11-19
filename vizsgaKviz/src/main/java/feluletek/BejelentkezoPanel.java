/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import alaposztalyok.Jatekos;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import vezerles.Vezerlo;

/**
 *
 * @author Szintai
 */
public class BejelentkezoPanel extends javax.swing.JPanel {

    private Image hatterKep = new ImageIcon(getClass().getResource("/adatok/hatter5.jpg")).getImage();
    private DefaultListModel<Jatekos> jatekosModel = new DefaultListModel<>();
    ResourceBundle magyarBundle = ResourceBundle.getBundle("lokalitas/Bundle");
    ResourceBundle angolBundle = ResourceBundle.getBundle("lokalitas/Bundle_en");
    private Vezerlo vezerlo;
    private int jatekModSzam = 0;
    private String BEJELENTKEZES = "Bejelentkezés";
    private String JATEKVENDEGKENT = "Játék vendégként";
    private String MEGSE = "Mégse";
    private String NEVADASVAGYVENDEG = "Adjon meg nevet vagy játszon vendégként!";
    private String HOGYSZERETNEJATSZANI = "Hogy szeretne játszani?";
    private String GYORSASAGIJATEK = "Gyorsasági játék";
    private String UGYESSEGIJATEK = "Ügyességi játék";
    private String JATEKMOD = "Játékmód:";
    private int VENDEGJATEKMODSZAM=1;
    private int MEGSEGOMBSZAM=2;
    private int focusGombSzam=0;

    /**
     * Creates new form BejelentkezoPanel
     */
    public BejelentkezoPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMenu = new javax.swing.JButton();
        lblNev = new javax.swing.JLabel();
        txtNev = new javax.swing.JTextField();
        lblToplista = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstToplista = new javax.swing.JList<>();
        btnJatek = new javax.swing.JButton();

        btnMenu.setBackground(java.awt.SystemColor.activeCaption);
        btnMenu.setText("Menü");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        lblNev.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNev.setForeground(new java.awt.Color(255, 255, 51));
        lblNev.setText("Név:");

        txtNev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNevKeyPressed(evt);
            }
        });

        lblToplista.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblToplista.setForeground(new java.awt.Color(255, 255, 0));
        lblToplista.setText("Toplista:");

        jScrollPane1.setViewportView(lstToplista);

        btnJatek.setBackground(java.awt.SystemColor.activeCaption);
        btnJatek.setText("Játék");
        btnJatek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJatekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(998, Short.MAX_VALUE)
                .addComponent(btnMenu)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblToplista)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNev)
                    .addComponent(txtNev, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addComponent(btnJatek, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNev)
                    .addComponent(lblToplista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJatek, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnMenu)
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        vezerlo.panelCsere(false, false, false, true);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnJatekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJatekActionPerformed

        if (!txtNev.getText().isEmpty()) {
            String[] jatekValasztoGombok = {GYORSASAGIJATEK, UGYESSEGIJATEK, MEGSE};
            jatekModSzam = JOptionPane.showOptionDialog(null, JATEKMOD, HOGYSZERETNEJATSZANI,
                    JOptionPane.WARNING_MESSAGE, 0, null, jatekValasztoGombok, jatekValasztoGombok[focusGombSzam]);
            

            if(jatekModSzam != MEGSEGOMBSZAM){
            vezerlo.nevAtadas(txtNev.getText());
            vezerlo.jatekInditas(jatekModSzam);
            vezerlo.panelCsere(false, false, true, false);
            }
        } else {
            String[] buttons2 = {BEJELENTKEZES, JATEKVENDEGKENT, MEGSE};
            int rc2 = JOptionPane.showOptionDialog(null, NEVADASVAGYVENDEG, HOGYSZERETNEJATSZANI,
                    JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[0]);

            switch (rc2) {
                case 0:
                    txtNev.requestFocus();
                    break;

                case 1:
                    vezerlo.jatekInditas(VENDEGJATEKMODSZAM);
                    vezerlo.panelCsere(false, false, true, false);
                    break;

                case 2:
                    break;

            }
        }
    }//GEN-LAST:event_btnJatekActionPerformed

    private void txtNevKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNevKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER && !txtNev.getText().isEmpty()) {
            String[] jatekValasztoGombok = {GYORSASAGIJATEK, UGYESSEGIJATEK, MEGSE};
            jatekModSzam = JOptionPane.showOptionDialog(null, JATEKMOD, HOGYSZERETNEJATSZANI,
                    JOptionPane.WARNING_MESSAGE, 0, null, jatekValasztoGombok, jatekValasztoGombok[focusGombSzam]);
            
            if (jatekModSzam != MEGSEGOMBSZAM) {
                vezerlo.nevAtadas(txtNev.getText());
                vezerlo.jatekInditas(jatekModSzam);
                vezerlo.panelCsere(false, false, true, false);
            }
        }

    }//GEN-LAST:event_txtNevKeyPressed

    public void setVezerlo(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(hatterKep, 0, 0, this.getWidth(), this.getHeight(), this);

    }

    public JTextField getTxtNev() {
        return txtNev;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJatek;
    private javax.swing.JButton btnMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNev;
    private javax.swing.JLabel lblToplista;
    private javax.swing.JList<Jatekos> lstToplista;
    private javax.swing.JTextField txtNev;
    // End of variables declaration//GEN-END:variables

    public void listaAtadas(List<Jatekos> jatekosLista) {
        jatekosModel.clear();
        Collections.sort(jatekosLista);
        
        for (Jatekos jatekos : jatekosLista) {
            jatekosModel.addElement(jatekos);
        }
        lstToplista.setModel(jatekosModel);

    }

    public void nyelvBeallitas(ResourceBundle Bundle) {

        BEJELENTKEZES = Bundle.getString("MENU_BEJELENTKEZES");
        JATEKVENDEGKENT = Bundle.getString("MENU_JATEKVENDEGKENT");
        MEGSE = Bundle.getString("MENU_MEGSE");
        NEVADASVAGYVENDEG = Bundle.getString("MENU_NEVADASVAGYVENDEG");
        HOGYSZERETNEJATSZANI = Bundle.getString("MENU_HOGYSZERETNEJATSZANI");
        btnJatek.setText(Bundle.getString("BTN_JATEK"));
        btnMenu.setText(Bundle.getString("BTN_MENU"));
        lblNev.setText(Bundle.getString("BEJELENTKEZO_NEV"));
        lblToplista.setText(Bundle.getString("BEJELENTKEZO_TOPLISTA"));
        GYORSASAGIJATEK = Bundle.getString("BEJELENTKEZO_GYORSASAGI");
        UGYESSEGIJATEK = Bundle.getString("BEJELENTKEZO_UGYESSEGI");
        JATEKMOD = Bundle.getString("BEJELENTKEZO_JATEKMOD");

    }
}