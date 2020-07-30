package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import model.Anime;
import model.Usuario;

import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.components.JSpinField;

import database.BD;

import javax.swing.JFormattedTextField;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textoApelido;
	private JTextField textoEmail;
	private JPasswordField textoConfirmarSenha;
	private JPasswordField textoSenha;
	private JTextField textoGenero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JFormattedTextField formattedTextField = null;
	private JFormattedTextField campoData;

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		JLabel lblNewLabel = new JLabel("Insira seus dados para o cadastro");
		lblNewLabel.setBounds(26, 36, 218, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apelido");
		lblNewLabel_1.setBounds(26, 114, 46, 14);
		contentPane.add(lblNewLabel_1);

		textoApelido = new JTextField();
		textoApelido.setBounds(24, 139, 182, 20);
		contentPane.add(textoApelido);
		textoApelido.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Data de Nascimento");
		lblNewLabel_2.setBounds(26, 184, 117, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(26, 263, 46, 14);
		contentPane.add(lblNewLabel_3);

		textoEmail = new JTextField();
		textoEmail.setBounds(26, 288, 180, 20);
		contentPane.add(textoEmail);
		textoEmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(401, 114, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Confirmar Senha");
		lblNewLabel_5.setBounds(401, 184, 117, 14);
		contentPane.add(lblNewLabel_5);

		textoConfirmarSenha = new JPasswordField();
		textoConfirmarSenha.setBounds(401, 213, 180, 20);
		contentPane.add(textoConfirmarSenha);

		textoSenha = new JPasswordField();
		textoSenha.setBounds(401, 139, 180, 20);
		contentPane.add(textoSenha);

		JLabel lblNewLabel_6 = new JLabel("Genero");
		lblNewLabel_6.setBounds(401, 263, 46, 14);
		contentPane.add(lblNewLabel_6);

		textoGenero = new JTextField();
		textoGenero.setBounds(401, 288, 117, 20);
		contentPane.add(textoGenero);
		textoGenero.setColumns(10);

		try {
			campoData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		campoData.setBounds(26, 226, 180, 20);
		contentPane.add(campoData);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Principal().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(75, 388, 89, 23);
		contentPane.add(btnVoltar);


		JButton btnConfirmarCadastro = new JButton("Confirmar Cadastro");
		btnConfirmarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] split = {};
				BD banco = new BD();
				if(textoApelido.getText().isEmpty() || textoEmail.getText().isEmpty() || textoSenha.getText().isEmpty() || textoConfirmarSenha.getText().isEmpty()
						|| campoData.getText().isEmpty() || textoGenero.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
				}
				else {
					if(!textoConfirmarSenha.getText().equalsIgnoreCase(textoSenha.getText())) {
						JOptionPane.showMessageDialog(null, "As senhas não coincidem, insira a mesma senha no campo de Confirmar Senha");
					}
					else {
						split = campoData.getText().split("/");
					}	
					if(Integer.parseInt(split[0]) > 31 || Integer.parseInt(split[0]) <= 0 || Integer.parseInt(split[1]) > 13 || Integer.parseInt(split[1]) <= 0
							|| Integer.parseInt(split[2]) <= 1890) { 
						JOptionPane.showMessageDialog(null, "Data invalida");
					}
					else {
						if(banco.validaApelido(textoApelido.getText())) { 
							JOptionPane.showMessageDialog(null, "Apelido já existente, insira outro para efetuar o cadastro");
						}
						else {
							SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
							Date dataNascimento = null;
							try {
								dataNascimento = formato.parse(campoData.getText());
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							Usuario usuario = new Usuario();
							usuario.adicionarUsuario(textoApelido.getText(), textoEmail.getText(), textoSenha.getText(), dataNascimento, new Date(), textoGenero.getText());	
							JOptionPane.showMessageDialog(null, "Cadastro realizado!");
						}	
					}
				}
			}
		});
		btnConfirmarCadastro.setBounds(268, 388, 149, 23);
		contentPane.add(btnConfirmarCadastro);	
		this.setLocationRelativeTo(null);	
	}
}

