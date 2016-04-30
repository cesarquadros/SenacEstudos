package Principal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JCalendar;
import Conexao.Conexao;
import Conexao.CriacaoBD;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Dao.DaoAgendamento;
import Model.Agendamento;
import Model.Funcoes;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FmrPrincipal {

	private JFrame frmAgendaDeHorrios;
	private static JTable tableDados;
	private JScrollPane scroll;
	private JTextField txtNome;
	private JFormattedTextField txtCelular;
	private static JComboBox<String> comboHorario;
	private static JCalendar calendario;
	static JButton btnApagar;

	public static class Retorno implements Runnable {
		public void run() {
			DaoAgendamento agendamento = new DaoAgendamento();
			Funcoes funcoes = new Funcoes();
			
			try {
				while (true) {
					Thread.sleep(1000000);

					agendamento.atualizarComboHora(funcoes.listHorarios(), comboHorario, calendario);;
					agendamento.atualizarTable(tableDados, calendario);
					btnApagar.setEnabled(false);
				}

			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}		
	}

	public static void main(String[] args) throws SQLException {

		/*
		Conexao conexao = new Conexao();
		if (conexao.abreConexao() == null) {
			CriacaoBD conexaoTable = new CriacaoBD();
			conexaoTable.criarBanco();
		}*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					 final Retorno retorno = new Retorno(); Thread
					 threadRetorno = new Thread(retorno);
					 threadRetorno.start();
					

					FmrPrincipal window = new FmrPrincipal();
					window.frmAgendaDeHorrios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FmrPrincipal() throws ParseException {
		initialize();
	}

	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	private void initialize() throws ParseException {
		frmAgendaDeHorrios = new JFrame();
		frmAgendaDeHorrios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clique) {
				if (clique.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, "Ninox Solutions" + "\nContato: cesar.quadros88@gmail.com",
							"ninOX Solutions", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		frmAgendaDeHorrios.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FmrPrincipal.class.getResource("/image/navalhinha.png")));
		frmAgendaDeHorrios.setTitle(".:: Agenda de Hor\u00E1rios ::.");
		frmAgendaDeHorrios.setResizable(false);
		frmAgendaDeHorrios.setBounds(100, 100, 809, 524);
		frmAgendaDeHorrios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendaDeHorrios.getContentPane().setLayout(null);

		// Criando Componentes

		// Label's
		JLabel lblNomeDoCliente = new JLabel("Nome do cliente");
		JLabel lblCelularDoCliente = new JLabel("Celular");
		JLabel lblCliqueDuasVezes = new JLabel("Clique duas vezes p/ marcar como atendido");
		JLabel lblLogoNinox = new JLabel("");
		lblLogoNinox.setIcon(new ImageIcon(FmrPrincipal.class.getResource("/image/BannerNS_P.fw.png")));
		JLabel lblTopo = new JLabel("");
		JLabel lblBanner = new JLabel("");
		lblBanner.setIcon(new ImageIcon(FmrPrincipal.class.getResource("/image/Logo_520x85.fw.png")));
		JLabel lblTextoRodape = new JLabel("Copyright 2016 - Ninox Solution");
		JLabel lblVersao = new JLabel("Vers\u00E3o 1.0.0");
		JLabel lblSobre = new JLabel();
		JLabel lblhorario = new JLabel("Hor\u00E1rio");

		// Botões
		btnApagar = new JButton("Apagar");
		JButton btnSalvar = new JButton("Salvar");
		// ----------------------------------------------------------------------------------------------------------------------

		// Criando Table
		tableDados = new JTable();

		tableDados.setName("");
		tableDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDados.setSurrendersFocusOnKeystroke(true);
		tableDados.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));

		tableDados.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "DATA", "HORA", "CLIENTE", "TELEFONE", "", "STATUS" }) {
			public boolean isCellEditable(int row, int col) {

				return false;
			}

		});

		tableDados.getTableHeader().setReorderingAllowed(false);
		tableDados.getColumnModel().getColumn(0).setResizable(false);
		tableDados.getColumnModel().getColumn(0).setMaxWidth(90);
		tableDados.getColumnModel().getColumn(1).setMaxWidth(90);
		tableDados.getColumnModel().getColumn(2).setMaxWidth(115);
		tableDados.getColumnModel().getColumn(3).setMaxWidth(100);
		tableDados.getColumnModel().getColumn(4).setMaxWidth(0);
		tableDados.getColumnModel().getColumn(4).setMinWidth(0);
		tableDados.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		tableDados.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		tableDados.getColumnModel().getColumn(5).setMaxWidth(80);
		frmAgendaDeHorrios.getContentPane().add(tableDados);

		// Calendario, Scrollpane e combobox do horario
		calendario = new JCalendar();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 240, 240));
		menuBar.setBounds(0, 0, 750, 21);
		frmAgendaDeHorrios.getContentPane().add(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setForeground(new Color(0, 0, 128));
		menuBar.add(mnCadastro);

		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mnCadastro.add(mntmClientes);

		JMenuItem mntmFuncionrios = new JMenuItem("Funcion\u00E1rios");
		mnCadastro.add(mntmFuncionrios);
		scroll = new JScrollPane(tableDados);
		comboHorario = new JComboBox();

		// ----------------------------------------------------------------------------------------------------------------------

		// instanciando componentes
		// ----------------------------------------------------------------------------------------------------------------------

		MaskFormatter mask = new MaskFormatter("(##)#####-####");

		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnSalvar.setBounds(9, 214, 109, 23);
		frmAgendaDeHorrios.getContentPane().add(btnSalvar);

		btnApagar.setToolTipText("Apagar");
		btnApagar.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnApagar.setEnabled(false);
		btnApagar.setBounds(128, 214, 120, 23);
		frmAgendaDeHorrios.getContentPane().add(btnApagar);

		comboHorario.setBounds(254, 178, 75, 23);
		frmAgendaDeHorrios.getContentPane().add(comboHorario);

		scroll.setBounds(356, 150, 445, 325);
		frmAgendaDeHorrios.getContentPane().add(scroll);

		calendario.setBounds(9, 255, 330, 220);
		frmAgendaDeHorrios.getContentPane().add(calendario);

		txtCelular = new JFormattedTextField(mask);
		txtCelular.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				DaoAgendamento daoAgendamento = new DaoAgendamento();
				daoAgendamento.atualizarTable(tableDados, calendario);
				btnApagar.setEnabled(false);
			}
		});
		txtCelular.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtNome = new JTextField();
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

				DaoAgendamento daoAgendamento = new DaoAgendamento();
				daoAgendamento.atualizarTable(tableDados, calendario);
				btnApagar.setEnabled(false);
			}
		});

		txtNome.setColumns(10);
		txtNome.setBounds(10, 179, 135, 20);
		frmAgendaDeHorrios.getContentPane().add(txtNome);

		txtCelular.setColumns(10);
		txtCelular.setBounds(155, 179, 89, 20);
		frmAgendaDeHorrios.getContentPane().add(txtCelular);

		lblSobre.setText("Sobre");
		lblSobre.setToolTipText("Clique");
		lblSobre.setForeground(Color.WHITE);
		lblSobre.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
		lblSobre.setBounds(763, 1, 38, 14);
		frmAgendaDeHorrios.getContentPane().add(lblSobre);

		lblVersao.setForeground(Color.LIGHT_GRAY);
		lblVersao.setFont(new Font("Trebuchet MS", Font.BOLD, 10));
		lblVersao.setBounds(729, 478, 75, 14);
		frmAgendaDeHorrios.getContentPane().add(lblVersao);

		lblTextoRodape.setForeground(Color.LIGHT_GRAY);
		lblTextoRodape.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		lblTextoRodape.setBounds(3, 478, 142, 14);
		frmAgendaDeHorrios.getContentPane().add(lblTextoRodape);

		lblhorario.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblhorario.setBounds(254, 164, 46, 14);
		frmAgendaDeHorrios.getContentPane().add(lblhorario);

		lblNomeDoCliente.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNomeDoCliente.setBounds(10, 164, 108, 14);
		frmAgendaDeHorrios.getContentPane().add(lblNomeDoCliente);

		lblCelularDoCliente.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblCelularDoCliente.setBounds(155, 164, 61, 14);
		frmAgendaDeHorrios.getContentPane().add(lblCelularDoCliente);
		JLabel lblRodaPe = new JLabel("");

		lblRodaPe.setIcon(new ImageIcon(FmrPrincipal.class.getResource("/image/Fundo_Azul.png")));
		lblRodaPe.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblRodaPe.setBounds(1, 477, 800, 19);
		frmAgendaDeHorrios.getContentPane().add(lblRodaPe);
		lblBanner.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblBanner.setBounds(28, 29, 527, 85);
		frmAgendaDeHorrios.getContentPane().add(lblBanner);

		lblTopo.setIcon(new ImageIcon(FmrPrincipal.class.getResource("/image/Fundo_Azul.png")));
		lblTopo.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblTopo.setBounds(1, -2, 800, 21);
		frmAgendaDeHorrios.getContentPane().add(lblTopo);
		lblLogoNinox.setForeground(Color.LIGHT_GRAY);
		lblLogoNinox.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		lblLogoNinox.setBounds(626, 12, 191, 34);
		frmAgendaDeHorrios.getContentPane().add(lblLogoNinox);

		lblCliqueDuasVezes.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblCliqueDuasVezes.setBounds(547, 131, 248, 14);
		frmAgendaDeHorrios.getContentPane().add(lblCliqueDuasVezes);

		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon(FmrPrincipal.class.getResource("/image/Fundo.fw.png")));
		fundo.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		fundo.setBounds(0, 0, 801, 492);
		frmAgendaDeHorrios.getContentPane().add(fundo);
		frmAgendaDeHorrios.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { txtNome, txtCelular, comboHorario, btnSalvar, btnApagar }));
		frmAgendaDeHorrios.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { txtNome, txtCelular, comboHorario, btnSalvar, btnApagar }));

		// ----------------------------------------------------------------------------------------------------------------------

		tableDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clique) {
				if (clique.getClickCount() == 2) {
					String status = (String) tableDados.getModel().getValueAt(tableDados.getSelectedRow(), 5);

					String codigoString = (String) tableDados.getModel().getValueAt(tableDados.getSelectedRow(), 4);
					int codigoInt = Integer.parseInt(codigoString);
					DaoAgendamento agendamento = new DaoAgendamento();
					Funcoes funcoes = new Funcoes();

					if (status.equalsIgnoreCase("Atendido")) {
						agendamento.editarAgendamento(codigoInt, "Aberto");

					} else {
						agendamento.editarAgendamento(codigoInt, "Atendido");
					}
					agendamento.atualizarTable(tableDados, calendario);
					agendamento.atualizarComboHora(funcoes.listHorarios(), comboHorario, calendario);
					btnApagar.setEnabled(false);
				}

			}
		});
		scroll.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnApagar.setEnabled(true);
			}
		});
		lblSobre.addMouseListener(new MouseAdapter() {
			@Override
			// quando clicar duas vezes sobre o texto, apresenta a mensagem
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						"Ninox Agenda Eletronix\n" + "Version: 1.0.0\n" + "Copyright (c) 2016.\n" + "Build: 03/2016",
						"ninOX Solutions", JOptionPane.INFORMATION_MESSAGE);

			}

			// ao passar o mouse sobre o texto muda de cor
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblSobre.setForeground(Color.ORANGE);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblSobre.setForeground(Color.WHITE);
			}
		});
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DaoAgendamento daoAgendamento = new DaoAgendamento();
				Funcoes funcoes = new Funcoes();

				String codCli = (String) tableDados.getModel().getValueAt(tableDados.getSelectedRow(), 4);

				int codigo = Integer.parseInt(codCli);

				daoAgendamento.apagrAgendamento(codigo);
				daoAgendamento.atualizarTable(tableDados, calendario);
				daoAgendamento.atualizarComboHora(funcoes.listHorarios(), comboHorario, calendario);
				btnApagar.setEnabled(false);
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
				String data = dataFormat.format(calendario.getDate());
				Funcoes funcoes = new Funcoes();

				try {
					if (funcoes.validarCampos(txtNome, txtCelular, comboHorario) && funcoes.validarData(data)) {
						Agendamento agendamento = new Agendamento();
						DaoAgendamento daoAgendamento = new DaoAgendamento();

						agendamento.setData(data);
						agendamento.setNome(txtNome.getText());
						agendamento.setTelefone(txtCelular.getText().replaceAll("\\(", "").replaceAll("\\)", "")
								.replaceAll("[-/.]", ""));
						agendamento.setHora((String) comboHorario.getSelectedItem());
						daoAgendamento.salvarAgendamento(agendamento);
						daoAgendamento.atualizarTable(tableDados, calendario);
						daoAgendamento.atualizarComboHora(funcoes.listHorarios(), comboHorario, calendario);
						funcoes.limparCampos(txtNome, txtCelular, comboHorario);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		calendario.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {

				Funcoes funcoes = new Funcoes();
				DaoAgendamento agendamento = new DaoAgendamento();

				agendamento.atualizarTable(tableDados, calendario);
				agendamento.atualizarComboHora(funcoes.listHorarios(), comboHorario, calendario);
				btnApagar.setEnabled(false);
				agendamento.getNewRenderedTable(tableDados);

				funcoes.limparCampos(txtNome, txtCelular, comboHorario);

			}
		});
		tableDados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnApagar.setEnabled(true);

				DefaultTableModel model = (DefaultTableModel) tableDados.getModel();
				if (model.getRowCount() > 0) {
					if (tableDados.getSelectedRow() >= 0) {
						String status = (String) tableDados.getModel().getValueAt(tableDados.getSelectedRow(), 5);

					}
				}
			}
		});
	}
}
