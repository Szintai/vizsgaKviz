/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feluletek;

import alaposztalyok.Kerdes;
import alaposztalyok.Valasz;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import vezerles.Vezerlo;

/**
 *
 * @author Szintai
 */
public class JatekTerPanel extends javax.swing.JPanel {

    private Vezerlo vezerlo;
    private boolean elindult = true;
    private Kerdes kerdes = null;
    private List<Valasz> valaszok=new ArrayList<>();
    private Image hatterKep = new ImageIcon(getClass().getResource("/adatok/hatter.jpg")).getImage();
    private String PONTSZAM = "Pontszám: ";
    private String TEMAKOR = "Témakör: ";
    private String ELET = "Élet: ";
    private String VISSZAMARADO_IDO = "Visszamaradó idő: ";
    private String NINCSTOBBKERDES = "Nincs több kérdés.";
    private int elsoElem=0;

    /**
     * Creates new form JatekTerPanel
     */
    public JatekTerPanel() {
        initComponents();
    }

    public void setVezerlo(Vezerlo vezerlo) {
        this.vezerlo = vezerlo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(hatterKep, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtAValasz = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKerdes = new javax.swing.JTextArea();
        txtBValasz = new javax.swing.JTextField();
        txtCValasz = new javax.swing.JTextField();
        btnMenu = new javax.swing.JButton();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        txtVisszaMaradoIdo = new javax.swing.JLabel();
        txtPontSzam = new javax.swing.JLabel();
        txtElet = new javax.swing.JLabel();
        txtTemakor = new javax.swing.JLabel();

        txtAValasz.setEditable(false);
        txtAValasz.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtKerdes.setEditable(false);
        txtKerdes.setColumns(20);
        txtKerdes.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtKerdes.setRows(5);
        txtKerdes.setAutoscrolls(false);
        jScrollPane1.setViewportView(txtKerdes);

        txtBValasz.setEditable(false);
        txtBValasz.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtCValasz.setEditable(false);
        txtCValasz.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnMenu.setBackground(java.awt.SystemColor.activeCaption);
        btnMenu.setText("Menü");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnA.setBackground(java.awt.SystemColor.activeCaption);
        btnA.setText("A");
        btnA.setMaximumSize(new java.awt.Dimension(30, 30));
        btnA.setMinimumSize(new java.awt.Dimension(30, 30));
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        btnB.setBackground(java.awt.SystemColor.activeCaption);
        btnB.setText("B");
        btnB.setMaximumSize(new java.awt.Dimension(30, 30));
        btnB.setMinimumSize(new java.awt.Dimension(30, 30));
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        btnC.setBackground(java.awt.SystemColor.activeCaption);
        btnC.setText("C");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        txtVisszaMaradoIdo.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtVisszaMaradoIdo.setForeground(new java.awt.Color(255, 255, 255));
        txtVisszaMaradoIdo.setText("Visszamaradó idő: 10");

        txtPontSzam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPontSzam.setForeground(new java.awt.Color(255, 255, 255));

        txtElet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtElet.setForeground(new java.awt.Color(255, 255, 255));
        txtElet.setText("Élet: ");

        txtTemakor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTemakor.setForeground(new java.awt.Color(255, 255, 255));
        txtTemakor.setText("Kérdés témakör: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBValasz, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                            .addComponent(txtCValasz, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAValasz, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(101, 101, 101))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(867, 867, 867)
                        .addComponent(btnMenu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(txtTemakor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(txtVisszaMaradoIdo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtElet)
                            .addComponent(txtPontSzam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtVisszaMaradoIdo)
                        .addGap(66, 66, 66)
                        .addComponent(txtTemakor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtPontSzam, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtElet)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAValasz, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBValasz, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCValasz, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnMenu)
                .addContainerGap(181, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        vezerlo.jatekVege();
        vezerlo.panelCsere(false, false, false, true);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed

        gombElerhetosegCsere();
        szincsere(txtAValasz, 0);
        vezerlo.kerdesKezelo("A", txtAValasz);


    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        gombElerhetosegCsere();
        szincsere(txtBValasz, 0);
        vezerlo.kerdesKezelo("B", txtBValasz);

    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        gombElerhetosegCsere();
        szincsere(txtCValasz, 0);
        vezerlo.kerdesKezelo("C", txtCValasz);

    }//GEN-LAST:event_btnCActionPerformed

    public void gombElerhetosegCsere() {
        boolean allas = btnA.isEnabled();
        if (allas) {
            btnA.setEnabled(false);
            btnB.setEnabled(false);
            btnC.setEnabled(false);
        } else {
            btnA.setEnabled(true);
            btnB.setEnabled(true);
            btnC.setEnabled(true);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAValasz;
    private javax.swing.JTextField txtBValasz;
    private javax.swing.JTextField txtCValasz;
    private javax.swing.JLabel txtElet;
    private javax.swing.JTextArea txtKerdes;
    private javax.swing.JLabel txtPontSzam;
    private javax.swing.JLabel txtTemakor;
    private javax.swing.JLabel txtVisszaMaradoIdo;
    // End of variables declaration//GEN-END:variables

    public void kerdesBeallitas(Kerdes kivalasztottKerdes) {
        valaszok.add(kivalasztottKerdes.getElsoValasz());
        valaszok.add(kivalasztottKerdes.getMasodikValasz());
        valaszok.add(kivalasztottKerdes.getHarmadikValasz());
        
        txtKerdes.setText(kivalasztottKerdes.getKerdes());
        
        int randomIndex=(int)(Math.random()*valaszok.size());
        
        txtAValasz.setText(valaszok.get(randomIndex).getValasz());
        valaszok.get(randomIndex).setJelenlegiValaszHely("A");
        valaszok.remove(randomIndex);
        
        randomIndex=(int)(Math.random()*valaszok.size());
        txtBValasz.setText(valaszok.get(randomIndex).getValasz());
        valaszok.get(randomIndex).setJelenlegiValaszHely("B");
        valaszok.remove(randomIndex);
        
        txtCValasz.setText(valaszok.get(elsoElem).getValasz());
        valaszok.get(elsoElem).setJelenlegiValaszHely("C");
        txtTemakor.setText(TEMAKOR + kivalasztottKerdes.getTemaKor());
        
        valaszok.clear();
    }

    public void szincsere(JTextField valasz, int szin) {

        switch (szin) {
            case 0: {
                valasz.setBackground(Color.yellow);
            }
            break;

            case 1: {
                valasz.setBackground(Color.green);
            }
            break;

            case 2: {
                valasz.setBackground(Color.red);
            }
            break;

            case 3: {
                valasz.setBackground(Color.white);
            }
            break;

            default:
                break;

        }
    }

    public void valaszokAlaphelyzet() {
        txtAValasz.setBackground(Color.white);
        txtBValasz.setBackground(Color.white);
        txtCValasz.setBackground(Color.white);

    }

    public void joValaszMutatas(String joValasz) {
        switch (joValasz) {
            case "A": {
                txtAValasz.setBackground(Color.green);
            }
            break;

            case "B": {
                txtBValasz.setBackground(Color.green);
            }
            break;

            case "C": {
                txtCValasz.setBackground(Color.green);
            }
            break;

        }

    }

    public JTextField getTxtAValasz() {
        return txtAValasz;
    }

    public JTextField getTxtBValasz() {
        return txtBValasz;
    }

    public JTextField getTxtCValasz() {
        return txtCValasz;
    }

    public JButton getBtnA() {
        return btnA;
    }

    public JLabel getTxtVisszaMaradoIdo() {
        return txtVisszaMaradoIdo;
    }

    public void pontSzam(int pontSzam) {
        txtPontSzam.setText(PONTSZAM + pontSzam);
    }

    public void eletBeallitas(int elet) {
        txtElet.setText(ELET + elet);
    }

    public void nyelvBeallitas(ResourceBundle Bundle) {

        PONTSZAM = Bundle.getString("JATEK_PONTSZAM");
        TEMAKOR = Bundle.getString("JATEK_TEMAKOR");
        ELET = Bundle.getString("JATEK_ELET");
        VISSZAMARADO_IDO = Bundle.getString("JATEK_VISSZAMARADOIDO");
        btnMenu.setText(Bundle.getString("BTN_MENU"));
        NINCSTOBBKERDES=Bundle.getString("JATEK_NINCSTOBBKERDES");

    }

    public String getVISSZAMARADO_IDO() {
        return VISSZAMARADO_IDO;
    }

    public JLabel getTxtPontSzam() {
        return txtPontSzam;
    }

    public String getNINCSTOBBKERDES() {
        return NINCSTOBBKERDES;
    }

    public void kerdesTxtEltuntetes() {
        txtPontSzam.setText("");
    }

}