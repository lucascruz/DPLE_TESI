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
	private JComboBox<Centro> campoSigla;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	
	private CentroControle centroControle;
	private DisciplinaControle controle;
	private DisciplinaConsulta janelaPai;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DisciplinaCadastro(Conexao conexao, DisciplinaConsulta janelaPai) {
		
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
			campoSigla = new JComboBox(centroControle.getCentros().toArray());
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		painelCampos.add(campoCodigo);
		painelCampos.add(campoNome);
		painelCampos.add(campoCh);
		painelCampos.add(campoSigla);
		
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
			
			String codigo =campoCodigo.getText();
			String nome = campoNome.getText();
			int ch= Integer.parseInt(campoCh.getText());;
			Centro centro = (Centro) campoSigla.getSelectedItem();
			String centro_sigla= centro.getSigla();
			
//			Disciplina centro = new Disciplina();
//			centro.setMatricula(matricula);
//			centro.setNome(nome);
			
			confirmar(codigo, nome, ch,  centro_sigla);
			
		} else if (e.getSource() == botaoCancelar) {
			janelaPai.buscar();
			this.dispose();
		}
		
	}
	
	public void confirmar(String codigo, String nome, int ch, String  centro_sigla) {
		
		boolean status = false;
		try {
			status = controle.insertDisciplina(codigo, nome, ch, centro_sigla);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public DisciplinaControle getControle() {
		
		return controle;
		
	}

	public JTextField getCampoCodigo(){
		return campoCodigo;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

	public JTextField getCampoCh() {
		return campoCh;
	}


	public JComboBox<Centro> getCampoCentro() {
		return campoSigla;
	}


}
