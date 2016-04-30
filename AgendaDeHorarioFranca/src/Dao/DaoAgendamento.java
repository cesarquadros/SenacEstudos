package Dao;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.xml.crypto.Data;

import com.toedter.calendar.JCalendar;
import Conexao.Conexao;
import Model.Agendamento;
import Model.Funcoes;

public class DaoAgendamento {

	private Connection con;
	private Statement stmt;

	private void conectar() {
		try {
			con = Conexao.abreConexao();
			stmt = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"FALHA NA CONEXÃO DO BANCO DE DADOS\n" + "VERIFIQUE O ARQUIVO DE SENHA DO BANCO DE DADOS",
					"ninOX Solutions", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void salvarAgendamento(Agendamento agendamento) {

		conectar();
		try {

			String sql = "INSERT INTO AGENDAMENTO VALUES('" + agendamento.getData() + "','" + agendamento.getHora()
					+ "','" + agendamento.getNome() + "','" + agendamento.getTelefone() + "', '" + "Aberto" + "')";

			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "AGENDADO COM SUCESSO", "Agendamento", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "ERRO AO AGENDAR\nDATA OU HORA INVÁLIDA, " + "REFAÇA O AGENDAMENTO.",
					"Agendamento", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void editarAgendamento(int codigo, String status) {
		try {

			conectar();
			String sql = "UPDATE AGENDAMENTO SET STATUS ='" + status + "' WHERE COD = '" + codigo + "'";

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizarTable(JTable tableDados, JCalendar calendario) {

		String dataCalendar = null;
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataCalendar = dataFormat.format(calendario.getDate());

		DefaultTableModel model = (DefaultTableModel) tableDados.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
		conectar();
		try {
			ResultSet rs = null;
			stmt = con.createStatement();

			String sql = "SELECT CONVERT(VARCHAR(10),HORARIO,108),CONVERT(VARCHAR(10),DATA,103),NOME,TELEFONE,COD,STATUS "
					+ "FROM AGENDAMENTO where DATA between '" + dataCalendar + "' and '" + dataCalendar
					+ "' order by HORARIO";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MaskFormatter maskCelular;
				try {
					maskCelular = new MaskFormatter("(##)#####-####");
					JFormattedTextField celFormat = new JFormattedTextField(maskCelular);
					tableDados.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(celFormat));
					celFormat.setText(rs.getString(4));

					String telefone = celFormat.getText();

					// System.out.println(telefone);

					model = (DefaultTableModel) tableDados.getModel();
					model.addRow(new String[] { rs.getString(2), rs.getString(1), rs.getString(3), telefone,
							rs.getString(5), rs.getString(6) });

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void atualizarComboHora(ArrayList<String> listaHorarios, JComboBox<String> comboHorario,
			JCalendar calendario) {

		String dataCalendar = null;
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataCalendar = dataFormat.format(calendario.getDate());

		conectar();
		try {
			ResultSet rs = null;
			stmt = con.createStatement();

			String sql = "SELECT CONVERT(VARCHAR(10),HORARIO,108),CONVERT(VARCHAR(10),DATA,103),NOME,TELEFONE,COD,STATUS "
					+ "FROM AGENDAMENTO where DATA between '" + dataCalendar + "' and '" + dataCalendar
					+ "' order by HORARIO";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String hora = rs.getString(1);
				for (int i = 0; i < listaHorarios.size(); i++) {
					if (hora.equals(listaHorarios.get(i))) {
						listaHorarios.remove(i);
					}
				}
			}

			comboHorario.removeAllItems();
			comboHorario.addItem("");
			Funcoes funcoes = new Funcoes();

			for (int i = 0; i < listaHorarios.size(); i++) {
				try {
					Time horaCombo = funcoes.converterHora(listaHorarios.get(i));
					SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
					String horaString = horaFormat.format(new Date());
					Time horaAtual = funcoes.converterHora(horaString);

					comboHorario.addItem(listaHorarios.get(i));

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void apagrAgendamento(int codigo) {
		conectar();
		int confirmacao = JOptionPane.showConfirmDialog(null, "DESEJA REALMENTE EXCLUIR?", "Agendamento",
				JOptionPane.YES_NO_OPTION);

		if (confirmacao == JOptionPane.YES_OPTION) {
			try {
				con = Conexao.abreConexao();
				stmt.executeUpdate("DELETE FROM AGENDAMENTO WHERE COD='" + codigo + "'");
				JOptionPane.showMessageDialog(null, "APAGADO COM SUCESSO", "Agendamento",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				/*
				 * JOptionPane.showMessageDialog(null,
				 * "CLIENTE POSSUI CHAMADOS REGISTRADOS", null,
				 * JOptionPane.WARNING_MESSAGE);
				 */
			}

		} else if (confirmacao == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "EXCLUSÃO CANCELADA", "ninOX Solutions",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void verificarHorariosVencidos(JTable tableDados) throws ParseException {

		DefaultTableModel model = (DefaultTableModel) tableDados.getModel();
		int linhas = model.getRowCount();

		for (int i = 0; i < linhas; i++) {

			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd");

			String horaStr = (String) tableDados.getModel().getValueAt(i, 1);
			Date horaDate = formatoHora.parse(horaStr);
			Time horaTime = new Time(horaDate.getTime());

			String dataStr = (String) tableDados.getModel().getValueAt(i, 0);
			Date dataDate = formatoData.parse(dataStr);

			String dataAtual = formatoData.format(new Date());
			Date dataAtualDate = formatoData.parse(dataAtual);

			String horaAtual = formatoHora.format(new Date());
			Date horaAtualDate = formatoHora.parse(horaAtual);
			Time hrAtual = new Time(horaAtualDate.getTime());

			// System.out.println("hora agendamento " + horaTime);
			// System.out.println("hora Atual " + hrAtual);

			if (dataDate.before(dataAtualDate)) {
				System.out.println(horaTime + " já passou");
				tableDados.setForeground(Color.red);

			}

			System.out.println("");
		}
	}

	public static JTable getNewRenderedTable(final JTable table) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				/*
				 * Funcoes funcoes = new Funcoes(); SimpleDateFormat formatoData
				 * = new SimpleDateFormat("dd/MM/yyyy"); SimpleDateFormat
				 * formatoHora = new SimpleDateFormat("HH:mm"); String
				 * dataString = (String) table.getModel().getValueAt(row, 0);
				 * String dataAtualString = formatoData.format(new Date());
				 * String horaString = (String) table.getModel().getValueAt(row,
				 * 1); String horaAtualString = formatoHora.format(new Date());
				 * try { Date dataDate = funcoes.converterData(dataString); Date
				 * dateAtual = funcoes.converterData(dataAtualString);
				 * 
				 * Time horaTime = funcoes.converterHora(horaString); Time
				 * timeAtual = funcoes.converterHora(horaAtualString);
				 * 
				 * if (dataDate.before(dateAtual)) { setForeground(Color.RED); }
				 * else if(dataDate.equals(dateAtual)) {
				 * setForeground(table.getForeground()); if
				 * (horaTime.before(timeAtual)) { setForeground(Color.RED); } }
				 * } catch (ParseException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); }
				 */
				String status = (String) table.getModel().getValueAt(row, 5);

				if (status.equalsIgnoreCase("Aberto")) {
					setForeground(Color.red);
				} else if (status.equalsIgnoreCase("Atendido")) {
					setForeground(Color.blue);
				}

				return this;
			}
		});
		return table;
	}

}
