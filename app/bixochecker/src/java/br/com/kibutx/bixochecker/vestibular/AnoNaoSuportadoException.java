package br.com.kibutx.bixochecker.vestibular;

/**
 * @author mricardo
 * @version 1.0
 */
public class AnoNaoSuportadoException extends Exception {

	private Integer anoNaoSuportado;

	private static final long serialVersionUID = -4318403425778622105L;

	public AnoNaoSuportadoException(Integer ano) {
		anoNaoSuportado = ano;
	}

	public AnoNaoSuportadoException(Integer ano, Exception e) {
		super(e);
		anoNaoSuportado = ano;
	}

	public AnoNaoSuportadoException(Integer ano, String msg) {
		super(msg);
		anoNaoSuportado = ano;
	}

	public AnoNaoSuportadoException(Integer ano, String msg, Exception e) {
		super(msg, e);
		anoNaoSuportado = ano;
	}

	public Integer getAnoNaoSuportado() {
		return anoNaoSuportado;
	}

}
