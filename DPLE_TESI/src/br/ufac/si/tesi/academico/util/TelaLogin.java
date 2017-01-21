package br.ufac.si.tesi.academico.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.visao.AcademicoGUI;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private String usuario, senha;
	private static Conexao cnx = new Conexao();
	private AcademicoGUI frame1 = null;


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public TelaLogin(){
		setTitle("ACADEMICO - Login e Senha do Banco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("USUARIO");
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario = (String) e.getSource();
			}
		});
		panel.add(textField);
		textField.setColumns(30);

		JLabel lblNewLabel_1 = new JLabel("SENHA");
		panel.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				senha = (String) e.getSource();
			}
		});
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e ){
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					usuario = textField.getText();
					char [] pass = passwordField.getPassword();
					senha = new String (pass);
					System.out.println(usuario+senha);
					try {
						cnx.conecte(usuario, senha);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (cnx.estaConectado()){
						try {
							frame1 = new AcademicoGUI(cnx);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					frame1.setVisible(true);
					dispose();
					System.out.println(cnx);}
			}
		}
				);
		panel.add(passwordField);

		JButton btnNewButton = new JButton("LOGAR");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usuario = textField.getText();
				char [] pass = passwordField.getPassword();
				senha = new String (pass);
				System.out.println(usuario+senha);
				try {
					cnx.conecte(usuario, senha);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (cnx.estaConectado()){
					AcademicoGUI frame1 = null;
					try {
						frame1 = new AcademicoGUI(cnx);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame1.setVisible(true);
					//System.out.println(cnx);
					setVisible(false);
				}

				//	cnx.desconecte();
				//	System.out.println(cnx);
			}
		});

		JSeparator separator = new JSeparator();
		panel.add(separator);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("SAIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cnx.desconecte();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				System.exit(0); 
			}			
		});
		panel.add(btnNewButton_1);
	}

}
