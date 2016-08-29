
public class Coordernador extends Funcionario {

	private String departamento;
	
	public Coordernador(String nome, String cpf, int salario, String departamento) {
		super(cpf, cpf, salario);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	
				
}
