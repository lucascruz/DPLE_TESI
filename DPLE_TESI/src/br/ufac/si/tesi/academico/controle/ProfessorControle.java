package br.ufac.si.tesi.academico.controle;

import java.math.BigDecimal;
import java.util.List;

import br.ufac.si.tesi.academico.dados.ProfessorDB;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Professor;

public class ProfessorControle {
	
	private ProfessorDB dados = null;
	private CentroControle centroControle = null;
	
	public ProfessorControle(Conexao conexao) {

		dados = new ProfessorDB(conexao);
		centroControle = new CentroControle(conexao);
		
	}
	
	public boolean insertProfessor(int matricula, String nome, int rg, long cpf, String telefone, String endereco, String cep, String email, boolean substituto, String centro_sigla) {
		
		Professor professor = null;
		
		if (matricula == 0 || nome.isEmpty() || rg == 0 || cpf == 0 || email.isEmpty() || centro_sigla.isEmpty()) {
			return false;
		} else {
			professor = new Professor();
			professor.setMatricula(matricula);
			professor.setNome(nome);
			professor.setRg(rg);
			professor.setCpf(cpf);
			professor.setTelefone(telefone);
			professor.setEndereco(endereco);
			professor.setCep(cep);
			professor.setEmail(email);
			professor.setSubstituto(substituto);
			professor.setCentro(centroControle.getCentro(centro_sigla));
			return dados.insertProfessor(professor);
		}
		
	}
	
	public boolean updateProfessor(int matricula, String nome, int rg, long cpf, String telefone, String endereco, String cep, String email, boolean substituto, String centro_sigla) {
		
		Professor professor = null;
		
		if (matricula == 0 || nome.isEmpty() || rg == 0 || cpf == 0 || email.isEmpty() || centro_sigla.isEmpty()) {
			return false;
		} else {
			professor = dados.getProfessor(matricula);
			professor.setNome(nome);
			professor.setRg(rg);
			professor.setCpf(cpf);
			professor.setTelefone(telefone);
			professor.setEndereco(endereco);
			professor.setCep(cep);
			professor.setEmail(email);
			professor.setSubstituto(substituto);
			professor.setCentro(centroControle.getCentro(centro_sigla));
			return dados.updateProfessor(professor);
		}
		
	}
	
	public boolean deleteProfessor(int matricula) {
		
		return dados.deleteProfessor(matricula);		
		
	}
	
	public List<Professor> getProfessores(String nome) {
		
		return dados.getProfessores(nome);
		
	}
	
	public Professor getProfessor(int matricula) {
		
		return dados.getProfessor(matricula);
		
	}
	
	public List<Professor> getProfessores() {
		
		return dados.getProfessores();
		
	}

}
