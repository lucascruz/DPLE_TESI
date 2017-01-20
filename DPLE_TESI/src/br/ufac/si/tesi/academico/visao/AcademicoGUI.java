package br.ufac.si.tesi.academico.visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import br.ufac.si.tesi.academico.dados.Conexao;

public class AcademicoGUI extends JFrame implements ActionListener{

	private ProfessorConsulta professorConsultaGUI;
	private CentroConsulta centroConsultaGUI;
	private CursoConsulta cursoConsultaGUI;
	private DisciplinaConsulta disciplinaConsultaGUI;
	private AlunoConsulta alunoConsultaGui;
	private JMenu mnCadastro, mnSobre;
	private JMenuItem mntmProfessor, mntmCentro, mntmCurso, mntmDisciplina, mntmAluno, mntmHelp;
	private JSeparator separator;
	private JMenuItem mntmSair;
	private Conexao cnx;


	public AcademicoGUI(Conexao cnx)  {
		setTitle("Controle Academico");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		this.cnx = cnx;
		
		professorConsultaGUI = new ProfessorConsulta(cnx);		
		centroConsultaGUI = new CentroConsulta(cnx);
		cursoConsultaGUI = new CursoConsulta(cnx);
		disciplinaConsultaGUI = new DisciplinaConsulta(cnx);
		alunoConsultaGui = new AlunoConsulta (cnx);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		mntmProfessor = new JMenuItem("Professor");
		mntmProfessor .addActionListener(this);
		mnCadastro.add(mntmProfessor);
		
		mntmAluno = new JMenuItem("Aluno");
		mntmAluno .addActionListener(this);
		mnCadastro.add(mntmAluno);
		
		mntmDisciplina = new JMenuItem("Disciplina");
		mntmDisciplina .addActionListener(this);
		mnCadastro.add(mntmDisciplina);
		
		mntmCurso = new JMenuItem("Curso");
		mntmCurso.addActionListener(this);
		mnCadastro.add(mntmCurso);
		
		mntmCentro = new JMenuItem("Centro");
		mntmCentro.addActionListener(this);
		mnCadastro.add(mntmCentro);
		
		
		
		mntmHelp = new JMenuItem("Encontrou algum erro?");
		mntmHelp.addActionListener(this);
		mnSobre.add(mntmHelp);
		
		separator = new JSeparator();
		mnCadastro.add(separator);
			
		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(this);
		menuBar.add(mntmSair);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSair){
			try {
				cnx.desconecte();
				JOptionPane.showMessageDialog(null,"Academico","Encerrando aplicação", JOptionPane.PLAIN_MESSAGE);
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
			System.exit(0);			
		}
		if (e.getSource() == mntmProfessor){
			//setVisible(false);
			professorConsultaGUI.setVisible(true);
		}
		if(e.getSource()== mntmCentro){
			//setVisible(false);
			centroConsultaGUI.setVisible(true);
		}
		if(e.getSource()== mntmCurso){
			//setVisible(false);
			cursoConsultaGUI.setVisible(true);
		}
		if(e.getSource()== mntmDisciplina){
			//setVisible(false);
			disciplinaConsultaGUI.setVisible(true);
		}
		
		if(e.getSource()== mntmAluno){
		//	setVisible(false);
			alunoConsultaGui.setVisible(true);
		}

		if(e.getSource()== mntmHelp){
			JOptionPane.showMessageDialog(null, "Reporte qualquer erro para Lucas Cruz = )!", 
					"HELP", JOptionPane.PLAIN_MESSAGE);	
		}
	}
	
}
