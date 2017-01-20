package br.ufac.si.tesi.academico.dados;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private Connection conexao;
	private boolean conectado = false;
	private Statement smt;
	
	public void conecte() {
		
		String url = "jdbc:mysql://localhost/academico?useSSL=false";
		String usuario = "root";
		String senha = "ufac";

		try {
			conexao = DriverManager.getConnection(url, usuario, senha);
			conectado = true;
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
	}
	
	public void desconecte() {

		try {
			conexao.close();
			conectado = false;
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}

	}
	
	public int atualize(String sql) {
		
		int status = 0;
		
		try {
			smt = conexao.createStatement();
			status = smt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return status;
		
	}
	
	public ResultSet consulte(String sql) {
		
		ResultSet rs = null;
		
		try {
			smt = conexao.createStatement();
			rs = smt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return rs;
		
	}
	
	public boolean estaConectado() {
		
		return conectado;
		
	}

}
