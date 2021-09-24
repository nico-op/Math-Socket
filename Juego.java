
package canva;

import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;

public class Juego extends javax.swing.JFrame {
    
    private int FILS = 4;
    private int COLS = 4;
     
      
    /**
     * Creates new form Juego
     */
    public Juego() {
        initComponents();
        this.setSize(650, 650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setMatrix();
    }
    
    public void cerrar(Juego ventanaJuego){
        ventanaJuego.dispose();
    }
    
    
    public void setMatrix(){
        int contadorR=0; 
        int contadorT=0; 
        int contadorTu=0;
        
        JButton[][] CUADRO;
        JLabel numeroDado = new JLabel();
        JLabel jug1 = new JLabel();
        JLabel jug2 = new JLabel();
        JLabel nameJug1 = new JLabel();
        JLabel nameJug2 = new JLabel();
        JButton dado = new JButton();
        ImageIcon iconoDado = new ImageIcon("dado.png");
        ImageIcon iconJ1 = new ImageIcon("iconJug1.png");
        ImageIcon iconJ2 = new ImageIcon("iconJug2.png");
        dado.setBounds(450, 250, 125, 125);
        dado.setIcon(new ImageIcon(iconoDado.getImage().getScaledInstance(dado.getWidth(), dado.getHeight(), 1)));
        numeroDado.setBounds(500, 375, 100, 100);
        numeroDado.setBackground(Color.red);
        
        jug1.setBounds(450, 10, 50, 35);
        jug1.setOpaque(true);
        jug1.setBackground(Color.white);
        
        jug2.setBounds(450, 55, 50, 35);
        jug2.setOpaque(true);
        jug2.setBackground(Color.white);
        
        nameJug1.setBounds(520, 10, 100, 35);
        nameJug1.setOpaque(true);
        nameJug1.setBackground(Color.yellow);
        nameJug1.setText("   Posición Jug1");
        
        nameJug2.setBounds(520, 55, 100, 35);
        nameJug2.setOpaque(true);
        nameJug2.setBackground(Color.yellow);
        nameJug2.setText("   Posición Jug2");
        
        jPanel1.add(numeroDado);
        jPanel1.add(dado);
        jPanel1.add(dado);
        jPanel1.add(jug1);
        jPanel1.add(jug2);
        jPanel1.add(nameJug1);
        jPanel1.add(nameJug2);
        
        CUADRO = new JButton[FILS][COLS];
        int x = 10;
        int y = 10;
        int i;
        int j;
        
        DoublyLinkedList list = new DoublyLinkedList<>();
        // Definicion de tamaños
        int num_retos = 7;
        int num_tuneles = 3;
        int num_trampas = 4;
        
        
        while(true){
            int tipo = (int) (Math.random()*(3-1+1)+1);
            
            // 1 reto
            // 2 tunel
            // 3 trampa
            if(tipo == 1 && num_retos > 0){
                list.addDataAtEnd(tipo);
                num_retos --;
            }else if(tipo == 2 && num_tuneles > 0){
                list.addDataAtEnd(tipo);
                num_tuneles --;
            }else if(tipo == 3 && num_trampas > 0){
                list.addDataAtEnd(tipo);
                num_trampas --;
            }
            // Condicion de parada
            if(num_retos == 0 && num_tuneles == 0 && num_trampas == 0){
                break;
            } 
        }
        
        System.out.println(list);
        
        
        int pos = 0;
        for (i = 0; i < FILS; i++){
            for (j = 0; j < COLS; j++){
                CUADRO[i][j]= new JButton();
                CUADRO[i][j].setBounds(y,x, 100 ,100);
                if(pos <= 14){
                    if(CUADRO[i][j] != CUADRO[0][0]){
                        CUADRO[i][j].setText(String.valueOf(pos));
                        CUADRO[i][j].setName(String.valueOf(list.index(pos)));
                    }
                     
                }
                else{
                    CUADRO[i][j].setText(null);
                }
                
                if(("1".equals(CUADRO[i][j].getName()))){
                    CUADRO[i][j].setBackground(Color.green);
                }
                if(("2".equals(CUADRO[i][j].getName()))){
                    CUADRO[i][j].setBackground(Color.blue);
                }
                if(("3".equals(CUADRO[i][j].getName()))){
                    CUADRO[i][j].setBackground(Color.red);
                }
                
                
                jPanel1.add(CUADRO[i][j]);
                y+=100;
                pos++;
            }
            x+=100;
            y=10;
        }
        CUADRO[0][0].setIcon(new ImageIcon(iconJ1.getImage()));
        CUADRO[0][0].setIcon(new ImageIcon(iconJ2.getImage()));
        
        
       
       dado.addActionListener(new ActionListener() { 
          boolean turnJ1 = false;
          boolean turnJ2 = false;
          int cont = 0; 
          int pos1 = 0;
          int pos2 = 0;
          
        public void actionPerformed(ActionEvent e) { 
            int randomDado = (int) (Math.random()*(4-1+1)+1);
            numeroDado.setText("" + randomDado);
            if(cont%2 == 0){
                pos1 += randomDado;
                
                if(pos1 < 15){
                    jug1.setText("      " + pos1);
                }
               else{
                    jug1.setText("     Win  ");
                    CUADRO[3][3].setIcon(new ImageIcon(iconJ1.getImage()));
                    JOptionPane.showMessageDialog(null, "Felicidades!\n" + "Ganó el Jugador 1");
                    cerrar(Juego.this);
                }
                for (int i = 0; i < FILS; i++){
                    for (int j = 0; j < COLS; j++){
                        CUADRO[i][j].setIcon(null);
                        if(CUADRO[i][j] != CUADRO[0][0]){
                            if(pos1 <= 14){
                                CUADRO[i][j].setIcon(null);
                                if(CUADRO[i][j].getText().equals(Integer.toString(pos1))){
                                    CUADRO[i][j].setIcon(new ImageIcon(iconJ1.getImage()));
                                    if(CUADRO[i][j].getName().equals("1")){
                                        System.out.println("Reto");
                                        pos2++;
                                        jug2.setText("      " + pos2);
                                        
                                        int simbolo = (int) (Math.random()*(4) + 1);
                                        int x = (int)(Math.random()*(50) + 1);
                                        int y =(int)(Math.random()*(50) + 1);
                                        int suma, resta, div, mult, respuesta;

                                        if (simbolo == 1){
                                            suma = x + y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "RETO\n" + "Cual es el resultado de sumar " + x + " + " + y + "?"));
                                            if (suma == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos1 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos1 - 1))){
                                                                    pos1--;
                                                                    jug1.setText("      " + pos1);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ1.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (simbolo == 2){
                                            resta = x - y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "RETO\n" + "Cual es el resultado de retar " + x + " - " + y + "?"));
                                            if (resta == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos1 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos1 - 1))){
                                                                    pos1--;
                                                                    jug1.setText("      " + pos1);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ1.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (simbolo == 3){
                                            div = x/y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null,"RETO\n" + "Cual es el resultado de dividir " + x + " / " + y + "?"));
                                            if (div == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos1 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos1 - 1))){
                                                                    pos1--;
                                                                    jug1.setText("      " + pos1);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ1.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (simbolo == 4){
                                            mult = x * y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "RETO\n" + "Cual es el resultado de multiplicar " + x + " * " + y + "?"));
                                            if (mult == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos1 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos1 - 1))){
                                                                    pos1--;
                                                                    jug1.setText("      " + pos1);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ1.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        
                                    }
                                    if(CUADRO[i][j].getName().equals("2")){
                                        System.out.println("Tunel");
                                        int casilla_tunel = (int)(Math.random()*(3) + 1);
                                        System.out.println("Avanza: " + casilla_tunel);
                                        pos1 += casilla_tunel;
                                        
                                        for(int m = 0; m < FILS; m++){
                                            for(int n = 0; n < COLS; n++){
                                                if(CUADRO[m][n] != CUADRO[0][0]){
                                                    if(pos1 <= 14){
                                                        CUADRO[m][n].setIcon(null);
                                                        if(CUADRO[m][n].getText().equals(Integer.toString(pos1))){
                                                            jug1.setText("      " + pos1);
                                                            CUADRO[m][n].setIcon(new ImageIcon(iconJ1.getImage()));
                                                            m = FILS;
                                                            n = COLS;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        
                                    }
                                    if(CUADRO[i][j].getName().equals("3")){
                                        System.out.println("Trampa");
                                        int casilla_trampa = (int)(Math.random()*(3) + 1);
                                        System.out.println("Retrocede: " + casilla_trampa);
                                        pos1 -= casilla_trampa;
                                        
                                        for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos1 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos1))){
                                                                    jug1.setText("      " + pos1);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ1.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                        
                                    }
                                    i = FILS;
                                    j = COLS;
                                }
                            }
                            else{
                                CUADRO[i][j].setIcon(null);
                                CUADRO[3][3].setIcon(new ImageIcon(iconJ1.getImage()));
                            }
                        }
                    }
                }
            }
            else{
                pos2 += randomDado;
                if(pos2 < 15){
                    jug2.setText("      " + pos2);
                }
                else{
                    jug2.setText("     Win  ");
                    CUADRO[3][3].setIcon(new ImageIcon(iconJ2.getImage()));
                    JOptionPane.showMessageDialog(null, "Felicidades!" + "\n" + "Ganó el Jugador 2");
                     cerrar(Juego.this);
                }
                
                for (int i = 0; i < FILS; i++){
                    for (int j = 0; j < COLS; j++){
                        CUADRO[0][0].setIcon(null);
                        if(CUADRO[i][j] != CUADRO[0][0]){
                            if(pos2 <= 14){
                                CUADRO[i][j].setIcon(null); //Genera cierto error
                                System.out.println(CUADRO[i][j].getText());
                                if(CUADRO[i][j].getText().equals(Integer.toString(pos2))){
                                    CUADRO[i][j].setIcon(new ImageIcon(iconJ2.getImage()));
                                    if(CUADRO[i][j].getName().equals("1")){
                                        System.out.println("Reto");
                                        pos1++;
                                        jug1.setText("      " + pos1);
                                        
                                        
                                        int simbolo = (int) (Math.random()*(4) + 1);
                                        int x = (int)(Math.random()*(50) + 1);
                                        int y =(int)(Math.random()*(50) + 1);
                                        int suma, resta, div, mult, respuesta;

                                        if (simbolo == 1){
                                            suma = x + y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "RETO\n" + "Cual es el resultado de sumar " + x + " + " + y + "?"));
                                            if (suma == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos2 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos2 - 1))){
                                                                    pos2--;
                                                                    jug2.setText("      " + pos2);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ2.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (simbolo == 2){
                                            resta = x - y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "RETO\n" + "Cual es el resultado de retar " + x + " - " + y + "?"));
                                            if (resta == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos2 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos2 - 1))){
                                                                    pos2--;
                                                                    jug2.setText("      " + pos2);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ2.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (simbolo == 3){
                                            div = x/y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null,"RETO\n" + "Cual es el resultado de dividir " + x + " / " + y + "?"));
                                            if (div == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos2 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos2 - 1))){
                                                                    pos2--;
                                                                    jug2.setText("      " + pos2);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ2.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (simbolo == 4){
                                            mult = x * y;
                                            respuesta=Integer.parseInt(JOptionPane.showInputDialog(null, "RETO\n" + "Cual es el resultado de multiplicar " + x + " * " + y + "?"));
                                            if (mult == respuesta){
                                                JOptionPane.showMessageDialog(null, "RESPUESTA CORRECTA!");
                                            }else{
                                                JOptionPane.showMessageDialog(null, "RESPUESTA INCORRECTA!");
                                                for(int m = 0; m < FILS; m++){
                                                    for(int n = 0; n < COLS; n++){
                                                        if(CUADRO[m][n] != CUADRO[0][0]){
                                                            if(pos2 <= 14){
                                                                CUADRO[m][n].setIcon(null);
                                                                if(CUADRO[m][n].getText().equals(Integer.toString(pos2 - 1))){
                                                                    pos2--;
                                                                    jug2.setText("      " + pos2);
                                                                    CUADRO[m][n].setIcon(new ImageIcon(iconJ2.getImage()));
                                                                    m = FILS;
                                                                    n = COLS;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    
                                    }
                                    if(CUADRO[i][j].getName().equals("2")){
                                        System.out.println("Tunel");
                                        
                                        
                                    }
                                    if(CUADRO[i][j].getName().equals("3")){
                                        System.out.println("Trampa");
                                        
                                        
                                    }
                                    i = FILS;
                                    j = COLS;
                                }
                            }
                            else{
                                CUADRO[i][j].setIcon(null);
                                CUADRO[3][3].setIcon(new ImageIcon(iconJ2.getImage()));
                            }
                        }
                    }
                }
            }
            cont++;
    } 
        } );
       
       
                 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 400));

        jPanel1.setPreferredSize(new java.awt.Dimension(750, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
