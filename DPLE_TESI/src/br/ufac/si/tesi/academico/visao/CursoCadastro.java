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
import br.ufac.si.tesi.academico.controle.CursoControle;
import br.ufac.si.tesi.academico.controle.DisciplinaControle;
import br.ufac.si.tesi.academico.controle.ProfessorControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;
import br.ufac.si.tesi.academico.modelo.Curso;
import br.ufac.si.tesi.academico.modelo.Professor;

@SuppressWarnings("serial")
public class CursoCadastro extends JFrame implements ActionListener {
	
	private JLabel lblCodigo, lblNome, lblProfessor;
	private JButton botaoConfirmar, botaoCancelar;
	private JTextField campoCodigo, campoNome;
	private JComboBox <Professor> campoProfessor;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	

	private ProfessorControle professorControle;
	private CursoControle cursoControle;
	private CursoConsulta janelaPai;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CursoCadastro(Conexao conexao, CursoConsulta janelaPai) {
		setTitle("Controle Academico - Cadastro Disciplina");

		

		cursoControle = new CursoControle(conexao);
		professorControle = new ProfessorControle(conexao);
		
		this.janelaPai = janelaPai;

		setSize(300,400);
		setLocationRelativeTo(null);
		
		painelRotulos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		lblCodigo = new JLabel("Codigo:");
		lblNome = new JLabel("Nome:");
		lblProfessor = new JLabel("Coordenador:");
		
		painelRotulos.add(lblCodigo);
		painelRotulos.add(lblNome);
		painelRotulos.add(lblProfessor);
		
		painelCampos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		campoCodigo = new JTextField();
		campoNome = new JTextField();
		try {
			campoProfessor= new JComboBox(professorControle.getProfessores().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
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
			int coordenador = professor.getMatricula();
			
			try {
				confirmar(codigo, nome, coordenador);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == botaoCancelar) {
			try {
				janelaPai.buscar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}
		
	}
	
	public void confirmar(int codigo, String nome, int coordenador) throws SQLException {
		
		boolean status = false;

			status = cursoControle.insertCurso(codigo, nome, coordenador);

		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public CursoControle getControle() {
		
		return cursoControle;
		
	}

	public JTextField getCampoCodigo(){
		return campoCodigo;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

	public JComboBox<Professor> getCampoProfessor() {
		return campoProfessor;
	}


}
