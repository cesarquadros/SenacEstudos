package Model;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class Funcoes {

	public Date converterData(String dataString) throws ParseException{
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatoData.parse(dataString);
		
		return date;
	}
	
	public Time converterHora(String horaString) throws ParseException{
		
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		Date hora = formatoHora.parse(horaString);
		Time horaTime = new Time(hora.getTime());
		
		return horaTime;
	}

	public void limparCampos(JTextField txtNome, JTextField txtCelular, JComboBox<String> comboHorario ) {
		txtNome.setText(null);
		txtCelular.setText(null);
		comboHorario.setSelectedIndex(0);
	}
	
	public boolean validarCampos(JTextField txtNome, JTextField txtCelular, JComboBox<String> comboHorario) {
		if (txtCelular.getText().trim().equals("") || txtNome.getText().trim().equals("")
				|| comboHorario.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS", "Agendamento", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarData(String data) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");		 
		String dataAtualString = dateFormat.format(new Date());
		Date dataAtual = dateFormat.parse(dataAtualString);		
		Date dataAgendamento = dateFormat.parse(data);		
		
		//System.out.println(dataAtual);
		//System.out.println(data);
		if (dataAgendamento.before(dataAtual)) {
			JOptionPane.showMessageDialog(null, "DATA DO AGENDAMENTO INVÁLIDA", "Agendamento",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public static ArrayList<String> listHorarios() {
		ArrayList<String> listaHorarios = new ArrayList<>();
		listaHorarios.add("00:00:00");
		listaHorarios.add("01:00:00");
		listaHorarios.add("02:00:00");
		listaHorarios.add("03:00:00");
		listaHorarios.add("04:00:00");
		listaHorarios.add("05:00:00");
		listaHorarios.add("06:00:00");
		listaHorarios.add("07:00:00");
		listaHorarios.add("08:00:00");
		listaHorarios.add("09:00:00");
		listaHorarios.add("10:00:00");
		listaHorarios.add("11:00:00");
		listaHorarios.add("12:00:00");
		listaHorarios.add("13:00:00");
		listaHorarios.add("14:00:00");
		listaHorarios.add("15:00:00");
		listaHorarios.add("16:00:00");
		listaHorarios.add("17:00:00");
		listaHorarios.add("18:00:00");
		listaHorarios.add("19:00:00");
		listaHorarios.add("20:00:00");
		listaHorarios.add("21:00:00");
		listaHorarios.add("22:00:00");
		listaHorarios.add("23:00:00");

		return listaHorarios;
	}

	public Date dataCalendar(JCalendar calendario) throws ParseException{
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
		String dataString = dataFormat.format(calendario.getDate());
		Date dataCalendar = converterData(dataString);
		return dataCalendar;
	}
	
	public Date dataAtual() throws ParseException{
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
		String dataString = dataFormat.format(new Date());
		Date dataAtual = converterData(dataString);
		return dataAtual;
	}
	
	public void bloquearCampos(JTextField txtNome, JTextField txtCelular, JComboBox<String> comboHorarios, JButton btnSalvar){
		txtCelular.setEnabled(false);
		txtNome.setEnabled(false);
		comboHorarios.setEnabled(false);
		btnSalvar.setEnabled(false);		
	}
	
	public void desbloquearCampos(JTextField txtNome, JTextField txtCelular, JComboBox<String> comboHorarios, JButton btnSalvar){
		txtCelular.setEnabled(true);
		txtNome.setEnabled(true);
		comboHorarios.setEnabled(true);
		btnSalvar.setEnabled(true);
	}
}
