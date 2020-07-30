package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textoEmail;
	private JPasswordField textoSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Bem-vindo ao My Animation List!");
		lblNewLabel.setBounds(241, 48, 213, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Entre na sua conta ou cadastre-se para ter acesso!");
		lblNewLabel_1.setBounds(187, 109, 299, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(310, 179, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textoEmail = new JTextField();
		textoEmail.setBounds(230, 204, 205, 20);
		contentPane.add(textoEmail);
		textoEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(310, 241, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textoSenha = new JPasswordField();
		textoSenha.setBounds(252, 266, 160, 20);
		contentPane.add(textoSenha);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textoEmail.getText().isEmpty() || textoSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
				}
				else {
					BD banco = new BD();
					if(banco.verificaLogin(textoEmail.getText(), textoSenha.getText())) {
						JOptionPane.showMessageDialog(null, "Logado com sucesso!");					
						if(banco.retornaTipoUsuario(textoEmail.getText(), textoSenha.getText()).equals("Admin")) {
							new TelaAdmin().setVisible(true);
						}
						else {
							new TelaUsuario(textoEmail.getText()).setVisible(true);
						}
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Email ou senha incorretos");
					}
				}			
			}
		});
		btnLogar.setBounds(286, 342, 89, 23);
		contentPane.add(btnLogar);
		
		JLabel lblNewLabel_4 = new JLabel("Ainda n\u00E3o tem uma conta?");
		lblNewLabel_4.setBounds(57, 312, 154, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Cadastre-se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastro().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(73, 342, 107, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(503, 342, 89, 23);
		contentPane.add(btnSair);
		this.setLocationRelativeTo(null);
	}
}
