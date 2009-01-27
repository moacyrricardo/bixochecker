package br.com.kibutx.bixochecker.vestibulares;

import java.net.MalformedURLException;
import java.net.URL;

public class ComvestPhase2Processor extends ComvestProcessor {

	private static final String SEG_FASE_URL = "http://www.comvest.unicamp.br/vest"
			+ ANO_VAR + "/F2/aprova2/chamada1/geral.html";

	@Override
	public URL getResultURL() {
		try {
			return new URL(SEG_FASE_URL
					.replaceAll(ANO_VAR, getAno().toString()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Não foi possível recuperar a URL(segunda fase).", e);
		}
	}

	@Override
	public String getDisplayName() {
		return "Fuvest (2a. Fase)";
	}

}
