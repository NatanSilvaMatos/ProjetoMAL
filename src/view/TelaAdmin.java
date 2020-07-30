package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdmin extends JFrame {

	private JPanel contentPane;

	public TelaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Bem-vindo, Admin!");
		lblNewLabel.setBounds(62, 29, 133, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("O que precisa fazer hoje?");
		lblNewLabel_1.setBounds(62, 81, 160, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAdicionarAnime = new JButton("Adicionar anime");
		btnAdicionarAnime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAdicionarAnime().setVisible(true);
				dispose();
			}
		});
		btnAdicionarAnime.setBounds(136, 234, 139, 23);
		contentPane.add(btnAdicionarAnime);
		
		JButton btnAdicionarManga = new JButton("Adicionar manga");
		btnAdicionarManga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaAdicionarManga().setVisible(true);
				dispose();
			}
		});
		btnAdicionarManga.setBounds(408, 234, 133, 23);
		contentPane.add(btnAdicionarManga);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Principal().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(298, 366, 89, 23);
		contentPane.add(btnVoltar);
		this.setLocationRelativeTo(null);
	}
}
