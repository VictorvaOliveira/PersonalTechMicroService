package alunorest.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aluno", catalog = "pdsc")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "dataCobranca", nullable = false)
	private String dataCobranca;
	
	@Column(name = "statusAtual", nullable = false)
	private String statusAtual;
	
	@Column(name = "statusProxCobranca", nullable = false)
	private String statusProxCobranca;
	
	@Column(name = "idPersonalTrainer", nullable = false)
	private int idPersonalTrainer;
	
	/*
	 *  PARAMÊTRO IDENTIFICADOR DO ALUNO
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*
	 * PARAMÊTRO NOME DO ALUNO
	 */
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/*
	 * 
	 */
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/*
	 * PARAMÊTRO DA DATA DA COBRANÇA DE PAGAMENTO DO ALUNO
	 */
	public String getDataCobranca() {
		return dataCobranca;
	}
	public void setDataCobranca(String dataCobranca) {
		this.dataCobranca = dataCobranca;
	}
	/*
	 *  PARAMÊTRO DO STATUS DO ALUNO (APTO, INAPTO)
	 */
	public String getStatusAtual() {
		return statusAtual;
	}
	public void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}
	/*
	 * PARAMÊTRO PRÓXIMA DATA DE COBRANÇA
	 */
	public String getStatusProxCobranca() {
		return statusProxCobranca;
	}
	public void setStatusProxCobranca(String statusProxCobranca) {
		this.statusProxCobranca = statusProxCobranca;
	}
	/*
	 * PARAMÊTRO DE IDENTIFICADOR DE QUAL PERSONAL ESTE ALUNO PERTENCE
	 */
	public int getIdPersonalTrainer() {
		return idPersonalTrainer;
	}
	public void setIdPersonalTrainer(int idPersonalTrainer) {
		this.idPersonalTrainer = idPersonalTrainer;
	}
	
}
