/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar.cquadros1
 */
public class Agenda extends ConexaoBD{
    
    private static Scanner sc = new Scanner(System.in);
    
    public Date converterData(String dataString){
        DateFormat df = new SimpleDateFormat("dd/MM/yyy");
        Date data = null;
        try {
            data = df.parse(dataString);
        } catch (ParseException ex) {
            System.out.println("Data de nascimento invalida");
        }
        return data;
    }
    
    public void incluir() {
        System.out.println("Digite o nome completo");
        String nome = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.println("Digite a data de nascimento no formato dd/mm/aaa");
        String dataNasc = sc.nextLine();
        sc = new Scanner(System.in);
        Date data = converterData(dataNasc);
        System.out.println("Digite o email");
        String email = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.println("Digite o telefone");
        String telefone = sc.nextLine();
        sc = new Scanner(System.in);        
        
        // 1- Abrir conexao
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
                + "VALUES(?, ?, ?, ?, ?)";

        try {
            conn = obertConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setDate(2, new java.sql.Date(data.getTime()));
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            
            // 2- Executar SQL
            stmt.executeQuery(sql);
            System.out.println("Contato cadastrado com sucesso");
            // 3 Fechar conecao
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel executar");
        } catch (SQLException ex) {
            System.out.println("Não foi possivel executar");
        }finally{
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                  System.out.println("Erro ao fechar stmt");
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erroao fechar conn");
                }
            }
        }

    }

    public static void main(String args[]) {
        Agenda instancia = new Agenda();
        
        do {
            System.out.println("*****DIGITE UMA OPÇÃO******");
            System.out.println("(1) Listar contato");
            System.out.println("(2) Incluir novo contato");
            System.out.println("(9) Sair");
            System.out.println("opção: ");

            String strOpcao = sc.nextLine();
            int opcao = Integer.parseInt(strOpcao);
            sc = new Scanner(System.in);  
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    instancia.incluir();
                    break;
                case 3:
                    break;
            }
        } while (true);
    }
}
