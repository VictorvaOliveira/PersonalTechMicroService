package tads.entidades;

public class Aluno {

	private int id;
	private String nome;
	private String telefone;
	private String dataCobranca;
	private String statusAtual;
	private String statusProxCobranca;
	private int idPersonalTrainer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataCobranca() {
		return dataCobranca;
	}
	public void setDataCobranca(String dataCobranca) {
		this.dataCobranca = dataCobranca;
	}
	public String getStatusAtual() {
		return statusAtual;
	}
	public void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}
	public String getStatusProxCobranca() {
		return statusProxCobranca;
	}
	public void setStatusProxCobranca(String statusProxCobranca) {
		this.statusProxCobranca = statusProxCobranca;
	}
	public int getIdPersonalTrainer() {
		return idPersonalTrainer;
	}
	public void setIdPersonalTrainer(int idPersonalTrainer) {
		this.idPersonalTrainer = idPersonalTrainer;
	}
	
	
}
