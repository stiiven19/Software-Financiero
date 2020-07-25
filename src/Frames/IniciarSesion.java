/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author lenovo
 */
public class IniciarSesion extends javax.swing.JFrame{
	
	private JLabel          jlLogin,jlUsuario,jlContra,jlImage;
	private JTextField	txUsuario;
	private JPasswordField  txContra;
	private JButton		jbIngresar;
	
	public IniciarSesion() {
            setTitle("Iniciar Sesíon");
            setSize(496, 336);
            setLocationRelativeTo(null);
            setupWidgets();
            setupEvents();
            setVisible(true);
		
	}

	private void setupEvents() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbIngresar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char[] chars=txContra.getPassword();
                    String pas = new String(chars);
                    int result = Verificar(txUsuario.getText(),pas);
                    if (result!=0) {
                        JOptionPane.showMessageDialog(rootPane, "Login correcto", "Atención", JOptionPane.INFORMATION_MESSAGE);
                        if(result ==1){
                            MenuPrincipal menuPrincipal = new MenuPrincipal(false);
                        }else{
                            MenuPrincipal menuPrincipal = new MenuPrincipal(true);
                        }
                        
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Login incorrecto", "Atención", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            
            addWindowListener(new WindowAdapter() {    
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);

                }
            });
	}

	private void setupWidgets() {
            jlLogin = new JLabel("LOGIN");jlLogin.setFont(new Font("Comic Sans MS",1,22));
            jlUsuario = new JLabel("Usuario:");
            jlContra = new JLabel("Contraseña:");
            jlImage = new JLabel("");

            txUsuario = new JTextField();		
            txContra = new JPasswordField();


            jbIngresar = new JButton("Ingresar");
            jlImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/init.jpg")));
            setLayout(null);

            jlLogin.setBounds(20, 20, 120, 30);
            jlUsuario.setBounds(20, 70, 60, 15);
            txUsuario.setBounds(20, 110, 190, 20);
            jlContra.setBounds(20, 150, 90, 15);
            txContra.setBounds(20, 190, 190, 20);
            
            jlImage.setBounds(230, -2, 250, 300);
            jbIngresar.setBounds(20, 240, 100, 20);
            
            add(jlContra);
            add(jlImage);
            add(jlLogin);
            add(jlUsuario);
            add(txUsuario);
            add(txContra);
            add(jbIngresar);
            setBackground(new Color(247,247,247));
            jbIngresar.setBackground(new Color(36, 83, 181));
	}
        
    private int Verificar(String user,String contra){//=0 no exist el user =1 si es correcto pero no es cargo =2 si es correcto y es cargo
        int retorno = 0;
        String url = "jdbc:postgresql://localhost:5432/InversionesPrestamos";
        String usuario = "postgres";
        String contraseña = "123";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario,contraseña);
            java.sql.Statement st = conexion.createStatement();
            String sql
                    = "Select * from usuarios where usuario like '"+user+"' and contrasenia like '"+contra+"';";
            ResultSet result = conexion.createStatement().executeQuery(sql);
            while (result.next()) {                
                String cargo = result.getString("cargo");
                if ("Auxiliar de Credito".equals(cargo)){
                    retorno =1;
                }else{
                    retorno =2;
                }
            }
            result.close();
            st.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR DE CONEXION " + e.getMessage());
        }
        return retorno;
    }
}
