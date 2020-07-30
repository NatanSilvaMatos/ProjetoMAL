package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.BD;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textoImagem;
	private String emailUsuario;	
	
	public TelaUsuario() {
		
	}
	
	public TelaUsuario(String email) {
		emailUsuario = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		textoImagem = new JTextField();
		textoImagem.setBounds(25, 63, 147, 194);
		contentPane.add(textoImagem);
		textoImagem.setColumns(10);
		textoImagem.setText("            Imagem aqui");
		textoImagem.setEditable(false);
		
		BD banco = new BD();
		String[] dadosUsuario = banco.preencheDadosUsuario(emailUsuario);
		
		String dataCadastro = dadosUsuario[2].replace("-", "/");
		String dataAniversario = dadosUsuario[1].replace("-", "/");
		
		JLabel lblNewLabel = new JLabel("Bem vindo, " + dadosUsuario[0] + "!");
		lblNewLabel.setBounds(25, 40, 238, 14);
		contentPane.add(lblNewLabel);
		
		JLabel LabelDataCadastro = new JLabel("Data de Cadastro: " + dataCadastro);
		LabelDataCadastro.setBounds(25, 280, 238, 14);
		contentPane.add(LabelDataCadastro);
		
		JLabel LabelAniversario = new JLabel("Aniversario: " + dataAniversario);
		LabelAniversario.setBounds(25, 305, 238, 14);
		contentPane.add(LabelAniversario);
		
		JButton btnNewButton = new JButton("Lista de Anime");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaListaAnimes(dadosUsuario[0]).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(428, 129, 138, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lista de Mangas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaListaMangas(dadosUsuario[0]).setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(428, 205, 138, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Sair");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(325, 407, 119, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel LabelGenero = new JLabel("Genero: " + dadosUsuario[3]);
		LabelGenero.setBounds(25, 333, 238, 14);
		contentPane.add(LabelGenero);
		this.setLocationRelativeTo(null);		
	}
}
