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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListaAnimes extends JFrame {

	private JPanel contentPane;
	private String nomeUsuario;

	public TelaListaAnimes() {
		
	}
	
	public TelaListaAnimes(String nome) {
		nomeUsuario = nome;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Animes");
		lblNewLabel.setBounds(44, 33, 94, 14);
		contentPane.add(lblNewLabel);


		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> lista = new JList<String>();
		contentPane.add(lista);
		lista.setModel(model);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setBounds(293, 96, 376, 228);
		
		BD banco = new BD();
		String [] dadosAnime = banco.retornaListaUsuario("anime", nomeUsuario);
		for(int i = 0;i < dadosAnime.length;i++) {
			model.add(i, dadosAnime[i]);
		}

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(293, 71, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nota");
		lblNewLabel_2.setBounds(438, 71, 46, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Adicionar Anime");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaTabelaAnimes(nomeUsuario).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(44, 115, 149, 23);
		contentPane.add(btnNewButton);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(208, 232, 46, 22);
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
		javax.swing.border.Border border = BorderFactory.createEtchedBorder(1);
		lista.setBorder(border);

		JButton btnNewButton_1 = new JButton("Remover Anime");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista.getSelectedIndex() < 0) {
        			JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
        		}
				else {
					String nomeAnime = model.getElementAt(lista.getSelectedIndex()).trim(); //Pegando só o nome do anime
    				String[] split = new String[nomeAnime.length()];
    				for(int i = 0;i < nomeAnime.length();i++) {
    					 split = nomeAnime.split("                  ");
    				}
					if(banco.deletaObraLista("anime", nomeUsuario, split[0])) {
						model.remove(lista.getSelectedIndex());
						JOptionPane.showMessageDialog(null, "O anime " + split[0] + " foi removido com sucesso!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Não foi possível fazer a remoção do item");
					}
				}
			}
		});
		btnNewButton_1.setBounds(44, 173, 149, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Atualizar Nota");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista.getSelectedIndex() < 0) {
        			JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
        		}
				else {
					String nomeAnime = model.getElementAt(lista.getSelectedIndex()).trim(); 
    				String[] split = new String[nomeAnime.length()];
    				for(int i = 0;i < nomeAnime.length();i++) {
    					 split = nomeAnime.split("                  ");
    				}
    				if(banco.atualizaNota("anime", Integer.parseInt(comboBox.getSelectedItem().toString()), nomeUsuario, split[0])) {
    					JOptionPane.showMessageDialog(null, "Nota atualizada com sucesso");
    				}
    				else {
    					JOptionPane.showMessageDialog(null, "Não foi possível efetuar a atualização da nota");
    				}	
				}
			}
		});
		btnNewButton_2.setBounds(44, 232, 149, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaUsuario(nomeUsuario).setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(293, 395, 111, 23);
		contentPane.add(btnNewButton_3);
		lista.setVisible(true);

		JLabel lblNewLabel_3 = new JLabel("Capitulos");
		lblNewLabel_3.setBounds(584, 71, 54, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_4 = new JButton("Atualizar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaListaAnimes(nomeUsuario).setVisible(true);
			}
		});
		btnNewButton_4.setBounds(44, 285, 149, 23);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(293, 96, 376, 228);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(lista);
	}
}
