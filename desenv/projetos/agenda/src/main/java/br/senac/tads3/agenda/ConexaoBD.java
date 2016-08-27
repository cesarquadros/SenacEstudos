
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cesar.cquadros1
 */
public class ConexaoBD {

    protected Connection obertConexao() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        //Passo 1: Registrar o driver JDBC
        Class.forName("org.apache.derby.jdbc.ClientDataSource");
        
//Passo 2: Abrir a Conexão
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/agendabd;SecurityMechanism=3",
               "app",
               "app");
        return conn;
    }
}
