package br.com.kibutx.bixochecker.vestibulares;

import java.net.MalformedURLException;
import java.net.URL;

public class FuvestPhase2Processor extends FuvestProcessor {

	private static final String SEG_FASE_URL = "http://www.fuvest.br/vest"
			+ ANO_VAR + "/chamada1/publ1.txt";
	@Override
	public URL getResultURL() {
		try {
			return new URL(SEG_FASE_URL
					.replace(ANO_VAR, getAno().toString()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Não foi possível recuperar a URL(primeira fase).", e);
		}
	}

	@Override
	public String getDisplayName() {
		return "Fuvest (2a. Fase)";
	}
}
