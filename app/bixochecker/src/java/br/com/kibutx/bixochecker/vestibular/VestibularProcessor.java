package br.com.kibutx.bixochecker.vestibular;

import java.net.URL;
import java.util.List;

/**
 * Interface utilizada para buscar os resultados.
 * @author mricardo
 * @version 1.0
 */
public interface VestibularProcessor {
	/**
	 * Retorna o ano sobre o qual a busca de resultados deve ser feita.
	 * 
	 * @return Date o ano
	 */
	public abstract Integer getAno();

	/**
	 * Seta o ano sobre o qual a busca de resultados deve ser feita.
	 * 
	 * @param ano
	 */
	public abstract void setAno(Integer ano) throws AnoNaoSuportadoException;

	/**
	 * Retorna a url na qual sair�o os resultados da primeira fase. � necess�rio
	 * levar em considera��o o ano.
	 * 
	 * @return URL
	 */
	public abstract URL getResultURL();

	/**
	 * Processa os dados lidos da web para verificar se a lista da primeira fase
	 * saiu.
	 * @param response TODO
	 * 
	 * @return lista com os nomes dos aprovados na primeira fase.
	 */
	public abstract List<String> getResults(String response);
	
	/**
	 * Retorna o nome que será exibido para esse processor
	 * @return
	 */
	public abstract String getDisplayName();
}
