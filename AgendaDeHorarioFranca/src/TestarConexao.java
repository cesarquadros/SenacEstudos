import java.sql.ResultSet;
import java.sql.Statement;

import Conexao.Conexao;

public class TestarConexao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Conexao conexao = new Conexao();
		
		conexao.abreConexao();
		
		String sql = "SELECT * FROM AGENDAMENTOS";
		
		ResultSet rs = null;
		Statement stmt = null;
		
		

	}

}
