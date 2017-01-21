package br.ufac.si.tesi.academico.visao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufac.si.tesi.academico.controle.CentroControle;
import br.ufac.si.tesi.academico.controle.DisciplinaControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;

@SuppressWarnings("serial")
public class DisciplinaCadastro extends JFrame implements ActionListener {
	
	private JLabel lblCodigo, lblNome, lblCh, lblCentroSigla;
	private JButton botaoConfirmar, botaoCancelar;
	private JTextField campoCodigo, campoNome, campoCh;
	private JComboBox<Centro> campoCentro;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	
	private CentroControle centroControle;
	private DisciplinaControle controle;
	private DisciplinaConsulta janelaPai;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DisciplinaCadastro(Conexao conexao, DisciplinaConsulta janelaPai) {
		setTitle("Controle Academico - Cadastro Disciplina");

		
		controle = new DisciplinaControle(conexao);
		centroControle = new CentroControle(conexao);
		
		this.janelaPai = janelaPai;

		setSize(300,400);
		setLocationRelativeTo(null);
		
		painelRotulos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		lblCodigo = new JLabel("Codigo:");
		lblNome = new JLabel("Nome:");
		lblCh = new JLabel("CH:");
		lblCentroSigla = new JLabel("Centro:");
		
		painelRotulos.add(lblCodigo);
		painelRotulos.add(lblNome);
		painelRotulos.add(lblCh);
		painelRotulos.add(lblCentroSigla);
		
		painelCampos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		campoCodigo = new JTextField();
		campoNome = new JTextField();
		campoCh = new JTextField();
		try {
			campoCentro = new JComboBox(centroControle.getCentros().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		painelCampos.add(campoCodigo);
		painelCampos.add(campoNome);
		painelCampos.add(campoCh);
		painelCampos.add(campoCentro);
		
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

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botaoConfirmar) {
			
			int codigo = Integer.parseInt(campoCodigo.getText());
			String nome = campoNome.getText();
			String ch = campoCh.getText();
			int valorCh = Integer.parseInt(ch);
			Centro centro = (Centro) campoCentro.getSelectedItem();
			String centro_sigla = centro.getSigla();
			
			
			try {
				confirmar(codigo, nome, valorCh, centro_sigla);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == botaoCancelar) {
			try {
				janelaPai.buscar();
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
			this.dispose();
		}
		
	}
	
	public void confirmar(int codigo, String nome, int Ch, String centro_sigla) throws SQLException {
		
		boolean status = controle.insertDisciplina(codigo, nome, Ch, centro_sigla);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public DisciplinaControle getControle() {
		
		return controle;
		
	}

	public JTextField getCampoCodigo() {
		return campoCodigo;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}
	
	public JTextField getCampoCh() {
		return campoCh;
	}
	
	public JComboBox<Centro> getCampoCentro() {
		return campoCentro;
	}

}

