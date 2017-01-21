package br.ufac.si.tesi.academico.dados;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao{

	private Connection conexao;
	private boolean conectado = false;
	private Statement smt;
	private static final String urlDB = "jdbc:mysql://localhost/academico?useSSL=false";

	public void conecte(String u, String s) throws SQLException {
		String usuario = u;
		String senha = s;

		conexao = DriverManager.getConnection(urlDB, usuario, senha);
		conectado = true;


	}

	public void desconecte() throws SQLException {
		conectado = false;
		conexao.close();
	}

	public int atualize(String sql) throws SQLException {

		int status = 0;


		smt = conexao.createStatement();
		status = smt.executeUpdate(sql);


		return status;

	}

	public ResultSet consulte(String sql) throws SQLException{

		ResultSet rs = null;


		smt = conexao.createStatement();
		rs = smt.executeQuery(sql);

		return rs;

	}

	public boolean estaConectado() {

		return conectado;

	}

}
