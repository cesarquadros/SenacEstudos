
public class Professor extends Funcionario {

	private String tipoContratacao;
	
	public Professor(String nome, String cpf, int salario, String tipoContratacao) {
		super(nome, cpf, salario);
		this.tipoContratacao = tipoContratacao;
	}

	
	public String getTipoContratacao() {
		return tipoContratacao;
	}

	public void setTipoContratacao(String tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
	}
	
}
