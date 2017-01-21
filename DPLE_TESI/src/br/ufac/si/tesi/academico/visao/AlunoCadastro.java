package br.ufac.si.tesi.academico.visao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.ufac.si.tesi.academico.controle.CursoControle;
import br.ufac.si.tesi.academico.controle.AlunoControle;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Curso;

@SuppressWarnings("serial")
public class AlunoCadastro extends JFrame implements ActionListener {
	
	private JLabel lblMatricula, lblNome, lblSexo, lblCpf, lblTelefone, lblEndereco, lblCep, lblEmail, lblPne, lblCurso;
	private JButton botaoConfirmar, botaoCancelar;
	private JTextField campoMatricula, campoNome, campoCpf, campoTelefone, campoEndereco, campoCep, campoEmail;
	private JComboBox<Curso> campoCurso;
	private JCheckBox campoPne;
	private JRadioButton campoSexoMasc, campoSexoFem;
	private ButtonGroup campoSexo;
	
	private JPanel painelBotoes, painelRotulos, painelCampos;
	
	private CursoControle cursoControle;
	private AlunoControle controle;
	private AlunoConsulta janelaPai;
	private JPanel painelSexo;
	private String sexoEscolhido;


	

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AlunoCadastro(Conexao conexao, AlunoConsulta janelaPai) {
		setTitle("Controle Academico - Cadastro Aluno");

		controle = new AlunoControle(conexao);
		cursoControle = new CursoControle(conexao);
		
		this.janelaPai = janelaPai;

		setSize(300,400);
		setLocationRelativeTo(null);
		
		painelRotulos = new JPanel(new GridLayout(10, 1, 5, 5));
		
		painelSexo = new JPanel(new GridLayout(2,1));
		
		lblMatricula = new JLabel("Matricula:");
		lblNome = new JLabel("Nome:");
		lblSexo = new JLabel("Sexo:");
		lblCpf = new JLabel("CPF:");
		lblTelefone = new JLabel("Telefone:");
		lblEndereco = new JLabel("Endereço:");
		lblCep = new JLabel("CEP:");
		lblEmail = new JLabel("E-mail:");
		lblPne= new JLabel("PNE:");
		lblCurso = new JLabel("Curso:");
		
		painelRotulos.add(lblMatricula);
		painelRotulos.add(lblNome);
		painelRotulos.add(lblSexo);
		painelRotulos.add(lblCpf);
		painelRotulos.add(lblTelefone);
		painelRotulos.add(lblEndereco);
		painelRotulos.add(lblCep);
		painelRotulos.add(lblEmail);
		painelRotulos.add(lblPne);
		painelRotulos.add(lblCurso);
		
		painelCampos = new JPanel(new GridLayout(10, 1, 5, 5));
		campoSexo = new ButtonGroup();
		campoMatricula = new JTextField();
		campoNome = new JTextField();
		campoSexoMasc = new JRadioButton("Masculino");
		campoSexoFem = new JRadioButton("Feminino");
		campoCpf = new JTextField();
		campoTelefone = new JTextField();
		campoEndereco = new JTextField();
		campoCep = new JTextField();
		campoEmail = new JTextField();
		campoPne = new JCheckBox();
		try {
			campoCurso = new JComboBox(cursoControle.getCursos().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		painelCampos.add(campoMatricula);
		painelCampos.add(campoNome);
		painelSexo.add(campoSexoMasc);
		painelSexo.add(campoSexoFem);
		painelCampos.add(painelSexo);	
		campoSexo.add(campoSexoMasc);
		campoSexo.add(campoSexoFem);
		painelCampos.add(campoCpf);
		painelCampos.add(campoTelefone);
		painelCampos.add(campoEndereco);
		painelCampos.add(campoCep);
		painelCampos.add(campoEmail);
		painelCampos.add(campoPne);
		painelCampos.add(campoCurso);
		
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
			
			int matricula = Integer.parseInt(campoMatricula.getText());
			String nome = campoNome.getText();
			sexoEscolhido = campoSexoMasc.isSelected()? "M" : " F";
			String telefone = campoTelefone.getText();
			String endereco = campoEndereco.getText();
			String cep = campoCep.getText();
			String email = campoEmail.getText();
			boolean pne = campoPne.isSelected();
			Curso curso= (Curso) campoCurso.getSelectedItem();
			int curso_codigo= curso.getCodigo();

			
			try {
				confirmar(matricula, nome, telefone, endereco, cep, email,sexoEscolhido, pne, curso_codigo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (e.getSource() == botaoCancelar) {
			janelaPai.buscar();
			this.dispose();
		}
		
	}
	
	public void confirmar(int matricula, String nome, String telefone, String endereco, String cep, String email, String sexo, boolean pne, int curso_codigo) throws SQLException {
		
		boolean status = controle.insertAluno(matricula, nome,  telefone, endereco, cep, email,sexo,  pne, curso_codigo);
		
		if (status) {
			JOptionPane.showMessageDialog(this, "Registro inserido com sucesso!", "Status", JOptionPane.INFORMATION_MESSAGE); 
		} else {
			JOptionPane.showMessageDialog(this, "Falha na inserção do registro!", "Status", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	public AlunoControle getControle() {
		
		return controle;
		
	}

	public JTextField getCampoMatricula() {
		return campoMatricula;
	}

	public JTextField getCampoNome() {
		return campoNome;
	}

	public String getCampoSexo() {
		return sexoEscolhido;
		
	}

	public JTextField getCampoCpf() {
		return campoCpf;
	}

	public JTextField getCampoTelefone() {
		return campoTelefone;
	}

	public JTextField getCampoEndereco() {
		return campoEndereco;
	}

	public JTextField getCampoCep() {
		return campoCep;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public JComboBox<Curso> getCampoCurso() {
		return campoCurso;
	}

	public JCheckBox getCampoPne() {
		return campoPne;
	}

}
