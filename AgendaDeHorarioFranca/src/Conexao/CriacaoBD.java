package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CriacaoBD {
	private static String URL = "jdbc:sqlserver://localhost:1433;databaseName=master;";
	private static String user;
	private static String password;
	// Esse é o nome do driver, que na internet você vai encontrar de varias
	// maneiras, mas só esse resolveu meus problemas
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection abreConexao() throws SQLException {
		
		String userPassword[] = LoginBD.loginBd();
		user = userPassword[0];
		password = userPassword[1];		
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, user, password);
			System.out.println("Conectado ERRADOOOOOOOOO");
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			throw new SQLException(e.getMessage());

		}
	}
		
	public static void criarBanco() {
		Conexao conexao = new Conexao();
		
		Connection con;
		try {
			con = abreConexao();
			Statement stmt = con.createStatement();
			
			String sql = "CREATE DATABASE BDAGENDAMENTO";		
			stmt.executeUpdate(sql);
			
			con = conexao.abreConexao();
			stmt = con.createStatement();
			
			sql = "CREATE TABLE [dbo].[AGENDAMENTO]([COD]"
					+ " [int] IDENTITY(1,1) NOT NULL,	"
					+ "[DATA] [date] NOT NULL,	"
					+ "[HORARIO] [time](7) NOT NULL,"
					+ "[NOME] [nvarchar](25) NOT NULL,"
					+ "[TELEFONE] [nvarchar](16) NOT NULL,	"
					+ "[STATUS] [nvarchar] (12)NOT NULL, CONSTRAINT "
					+ "[PK_AGENDAMENTO] PRIMARY KEY CLUSTERED (	[COD] ASC)WITH "
					+ "(PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON)"
					+ " ON [PRIMARY]) ON [PRIMARY]";
		
			stmt.executeUpdate(sql);
			
			JOptionPane.showMessageDialog(null, "Criado Banco de Dados.",
					"ninOX Solutions", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problemas ao criar o Banco de Dados.\n" ,
					"ninOX Solutions", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			e.printStackTrace();
		}

		
	}
}

