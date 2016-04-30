package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private static String URL = "jdbc:sqlserver://ninox.database.windows.net:1433;"
			+ "database=DBAGENDAMENTO;"
			+ "user=CesarQuadros@ninox;"
			+ "password=Ces@r190788;"
			+ "encrypt=true;"
			+ "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;"
			+ "loginTimeout=30;";
	private static String user;
	private static String password;
	// Esse é o nome do driver, que na internet você vai encontrar de varias
	// maneiras, mas só esse resolveu meus problemas
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection abreConexao()  {
		Connection con = null;
		String userPassword[] = LoginBD.loginBd();
		user = userPassword[0];
		password = userPassword[1];		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL);
			System.out.println("Conectado CERTOOOOOOOOOO");
			return con;

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			return con = null;

		}
	}

}


