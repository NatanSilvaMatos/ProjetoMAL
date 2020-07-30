package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Anime;
import model.Manga;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdicionarManga extends JFrame {

	private JPanel contentPane;
	private JTextField textoNome;
	private JTextField textoCapitulos;
	private JTextField textoAutor;
	private JTextField textoImagem;

	public static boolean validarCampo(String texto) {
		boolean verifica = true;
		try{
			Integer.parseInt(texto);
		} catch(NumberFormatException e1){
			verifica = false;
		}
		return verifica;
	}

	public TelaAdicionarManga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(31, 92, 46, 14);
		contentPane.add(lblNewLabel);
		
		textoNome = new JTextField();
		textoNome.setBounds(31, 121, 325, 20);
		contentPane.add(textoNome);
		textoNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setBounds(31, 168, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(31, 193, 188, 22);
		contentPane.add(comboBox);
		
		comboBox.addItem("Em publicacao");
		comboBox.addItem("Concluido");
		
		JLabel lblNewLabel_2 = new JLabel("Capitulos");
		lblNewLabel_2.setBounds(31, 242, 63, 14);
		contentPane.add(lblNewLabel_2);
		
		textoCapitulos = new JTextField();
		textoCapitulos.setBounds(31, 267, 104, 20);
		contentPane.add(textoCapitulos);
		textoCapitulos.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Autor");
		lblNewLabel_3.setBounds(31, 298, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textoAutor = new JTextField();
		textoAutor.setBounds(31, 323, 213, 20);
		contentPane.add(textoAutor);
		textoAutor.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textoNome.getText().isEmpty() || textoCapitulos.getText().isEmpty() || textoAutor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
				}
				else if(validarCampo(textoCapitulos.getText())) {
					JOptionPane.showMessageDialog(null, "Cadastro realizado!");
					int episodios = Integer.parseInt(textoCapitulos.getText());
					Manga manga = new Manga();
					manga.adicionarManga(textoNome.getText(), comboBox.getSelectedItem().toString(), episodios, textoAutor.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Coloque apenas numeros inteiros no campo de capitulos");
				}
			}
		});
		btnNewButton.setBounds(309, 400, 115, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAdmin().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(77, 400, 104, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sair");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(551, 400, 98, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("Adicionar Mang\u00E1");
		lblNewLabel_4.setBounds(31, 36, 104, 14);
		contentPane.add(lblNewLabel_4);
		
		textoImagem = new JTextField();
		textoImagem.setBounds(487, 121, 162, 216);
		contentPane.add(textoImagem);
		textoImagem.setColumns(10);
		textoImagem.setText("            Imagem aqui");
		textoImagem.setEditable(false);
	}
}
