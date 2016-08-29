
public class Seguranca extends Funcionario{

	private String perido;
	
	public Seguranca(String nome, String cpf, int salario, String periodo) {
		super(nome, cpf, salario);
		this.perido = periodo;
	}

	
	
	public String getPerido() {
		return perido;
	}

	public void setPerido(String perido) {
		this.perido = perido;
	}

	

}
