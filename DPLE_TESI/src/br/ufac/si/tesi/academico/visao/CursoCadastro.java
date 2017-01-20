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

import br.ufac.si.tesi.academico.controle.ProfessorControle;
import br.ufac.si.tesi.academico.controle.CursoControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Professor;

@SuppressWarnings("serial")
public class CursoCadastro extends JFrame implements ActionListener {
	
	private JLabel lblCodigo, lblNome, lblCoordenador;
	private JButton botaoConfirmar, botaoCancelar;
	private JTextField campoCodigo, campoNome, campoCh;
	private JComboBox<Professor> campoProfessor;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	
	private ProfessorControle professorControle;
	private CursoControle controle;
	private CursoConsulta janelaPai;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CursoCadastro(Conexao conexao, CursoConsulta janelaPai) {
		
		controle = new CursoControle(conexao);
		professorControle = new ProfessorControle(conexao);
		
		this.janelaPai = janelaPai;

		setSize(300,400);
		setLocationRelativeTo(null);
		
		painelRotulos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		lblCodigo = new JLabel("Codigo:");
		lblNome = new JLabel("Nome:");
		lblCoordenador = new JLabel("Coordenador:");
		
		painelRotulos.add(lblCodigo);
		painelRotulos.add(lblNome);
		painelRotulos.add(lblCoordenador);
		
		painelCampos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		campoCodigo = new JTextField();
		campoNome = new JTextField();
		try {
			campoProfessor = new JComboBox(professorControle.getProfessores().toArray());
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		painelCampos.add(campoCodigo);
		painelCampos.add(campoNome);
		painelCampos.add(campoProfessor);
		
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
			
			int codigo = Integer.parseInt(campoCodigo.getText());
			String nome = campoNome.getText();
			Professor professor = (Professor) campoProfessor.getSelectedItem();
			int coordenador= professor.getMatricula();
			
//			Curso centro = new Curso();
//			centro.setMatricula(matricula);
//			centro.setNome(nome);
			
			confirmar(codigo, nome,coordenador);
			
		} else if (e.getSource() == botaoCancelar) {
			janelaPai.buscar();
			this.dispose();
		}
		
	}
	
	public void confirmar(int codigo, String nome, int coordenador) {
		
		boolean status = controle.insertCurso(codigo, nome, coordenador);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public CursoControle getControle() {
		
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


	public JComboBox<Professor> getCampoCoordenador() {
		return campoProfessor;
	}


}
