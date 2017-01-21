package br.ufac.si.tesi.academico.visao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufac.si.tesi.academico.controle.CentroControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;

@SuppressWarnings("serial")
public class CentroCadastro extends JFrame implements ActionListener {
	
	private JLabel lblSigla, lblNome;
	private JButton botaoConfirmar, botaoCancelar;
	private JTextField campoSigla, campoNome;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	
	private CentroControle controle;
	private CentroConsulta janelaPai;
	
	public CentroCadastro(Conexao conexao, CentroConsulta janelaPai) {
		
		
		
		controle = new CentroControle(conexao);
		setTitle("Controle Academico - Cadastro Centro");

		
		this.janelaPai = janelaPai;

		setSize(300,125);
		setLocationRelativeTo(null);
		
		painelRotulos = new JPanel(new GridLayout(2, 1, 5, 5));
		lblSigla = new JLabel("Sigla:");
		lblNome = new JLabel("Nome:");
		painelRotulos.add(lblSigla);
		painelRotulos.add(lblNome);
		
		painelCampos = new JPanel(new GridLayout(2, 1, 5, 5));
		campoSigla = new JTextField();
		campoNome = new JTextField();
		painelCampos.add(campoSigla);
		painelCampos.add(campoNome);
		
		painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		botaoConfirmar = new JButton("Confirmar");
		botaoCancelar = new JButton("Cancelar");
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoCancelar);
		
		botaoConfirmar.addActionListener(this);
		botaoCancelar.addActionListener(this);
		
		add(painelRotulos, BorderLayout.WEST);
		add(painelCampos);
		add(painelBotoes, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botaoConfirmar) {
			
			String sigla = campoSigla.getText();
			String nome = campoNome.getText();
			
			Centro centro = new Centro();
			centro.setSigla(sigla);
			centro.setNome(nome);
			
			confirmar(sigla, nome);
			
		} else if (e.getSource() == botaoCancelar) {
			janelaPai.buscar();
			this.dispose();
		}
		
	}
	
	public void confirmar(String sigla, String nome) {
		
		boolean status = false;
		try {
			status = controle.insertCentro(sigla, nome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public CentroControle getControle() {
		
		return controle;
		
	}

	public JTextField getCampoSigla() {
		return campoSigla;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

}
