package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import database.BD;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaTabelaMangas extends JFrame {

	private JPanel contentPane;
	private JTextField textEpisodiosTotal;
	private JTextField textField;
	private JTextField textoCapitulosTotal;
	private JTextField textCapitulosLidos;
	private String nomeUsuario;

	public TelaTabelaMangas() {
		
	}
	
	public TelaTabelaMangas(String nome) {
		nomeUsuario = nome;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JLabel lblNewLabel = new JLabel("Lista Animes");
		lblNewLabel.setBounds(33, 28, 79, 14);
		contentPane.add(lblNewLabel);

		textoCapitulosTotal = new JTextField();
		textoCapitulosTotal.setBounds(614, 213, 46, 20);
		contentPane.add(textoCapitulosTotal);
		textoCapitulosTotal.setColumns(10);
		textoCapitulosTotal.setEditable(false);

		textCapitulosLidos = new JTextField();
		textCapitulosLidos.setBounds(547, 213, 47, 20);
		contentPane.add(textCapitulosLidos);
		textCapitulosLidos.setColumns(10);

		DefaultListModel<String> model = new DefaultListModel<String>();
		JList lista = new JList();
		lista.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(lista.getSelectedIndex() >= 0) {
					String numeroEpisodios = model.getElementAt(lista.getSelectedIndex()).replaceAll("[^0123456789]", "");
					textoCapitulosTotal.setText(numeroEpisodios);
				}
			}
		});
		contentPane.add(lista);
		lista.setModel(model);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setBounds(33, 92, 376, 228);
		javax.swing.border.Border border = BorderFactory.createEtchedBorder(1);
		lista.setBorder(border);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(572, 136, 38, 22);
		contentPane.add(comboBox);
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		comboBox.addItem(6);
		comboBox.addItem(7);
		comboBox.addItem(8);
		comboBox.addItem(9);
		comboBox.addItem(10);


		BD banco = new BD();
		String [] dadosAnime = banco.retornaDadosTabela("manga", "capitulos", "autor");
		for(int i = 0;i < dadosAnime.length;i++) {
			model.add(i, dadosAnime[i]);
		}

		JButton btnAdicionarLista = new JButton("Adicionar para a minha lista");
		btnAdicionarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
				}
				else if(textCapitulosLidos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o n�mero de cap�tulos lidos");
				}       		
				else{
					int assistidos = Integer.parseInt(textCapitulosLidos.getText()), total = Integer.parseInt(textoCapitulosTotal.getText());
					if(assistidos > total || assistidos <= 0) {
						JOptionPane.showMessageDialog(null, "N�mero de cap�tulos maior que o total ou menor que 1, insira um n�mero v�lido");
					}
					else {
						String nomeAnime = model.getElementAt(lista.getSelectedIndex()).trim(); 
						String[] split = new String[nomeAnime.length()];
						for(int i = 0;i < nomeAnime.length();i++) {
							split = nomeAnime.split("          ");
						}
						int nota = Integer.parseInt(comboBox.getSelectedItem().toString());
						if(banco.consultaListaUsuario("manga", nomeUsuario, split[0])) {
        					JOptionPane.showMessageDialog(null, "O mang� selecionado j� est� na sua lista");
        				}
        				else {
        					banco.adicionaListaUsuario("manga", nomeUsuario, split[0], Integer.parseInt(textCapitulosLidos.getText()), nota);		
        					JOptionPane.showMessageDialog(null, "O mang� " + split[0] + " foi adicionado � sua lista com sucesso!");
        				}    					
					}     			
				}
			}
		});
		btnAdicionarLista.setBounds(254, 386, 206, 23);
		contentPane.add(btnAdicionarLista);

		JLabel lblNewLabel_1 = new JLabel("Escolha a nota");
		lblNewLabel_1.setBounds(482, 140, 112, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(457, 140, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cap\u00EDtulos");
		lblNewLabel_3.setBounds(482, 216, 55, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Cap\u00EDtulos");
		lblNewLabel_5.setBounds(180, 69, 55, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Autor");
		lblNewLabel_6.setBounds(350, 69, 46, 14);
		contentPane.add(lblNewLabel_6);

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaListaMangas(nomeUsuario).setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(525, 386, 97, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_7 = new JLabel("/");
		lblNewLabel_7.setBounds(604, 216, 14, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Nome");
		lblNewLabel_8.setBounds(33, 69, 46, 14);
		contentPane.add(lblNewLabel_8);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(33, 92, 376, 228);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(lista);
	}
}
