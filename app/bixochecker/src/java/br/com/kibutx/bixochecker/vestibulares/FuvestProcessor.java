package br.com.kibutx.bixochecker.vestibulares;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.kibutx.bixochecker.vestibular.AnoNaoSuportadoException;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

public class FuvestProcessor implements VestibularProcessor {

	private static final String ANO_VAR = "${ano}";

	private static final String PRI_FASE_URL = "http://www.fuvest.br/vest"
			+ ANO_VAR + "/listas/listconv.txt";

	private static final String SEG_FASE_URL = "http://www.fuvest.br/vest"
			+ ANO_VAR + "/chamada1/publ1.txt";

	private Integer ano;

	public void setAno(Integer ano) throws AnoNaoSuportadoException {
		if (ano.intValue() < 2003)
			throw new AnoNaoSuportadoException(ano,
					"Resultados apenas para anos posteriores a 2003.");
		this.ano = ano;
	}

	public Integer getAno() {
		if (ano == null) {
			ano = Calendar.getInstance().get(Calendar.YEAR);
		}
		return ano;
	}

	public URL getPrimeiraFase() {
		try {
			return new URL(PRI_FASE_URL
					.replaceAll(ANO_VAR, getAno().toString()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Não foi possível recuperar a URL(primeira fase).", e);
		}
	}

	public URL getSegundaFase() {
		try {
			return new URL(SEG_FASE_URL
					.replaceAll(ANO_VAR, getAno().toString()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Não foi possível recuperar a URL(primeira fase).", e);
		}
	}

	public List<String> getResultsPrimeiraFase(String response) {
		return processResponse(response);
	}

	public List<String> getResultsSegundaFase(String response) {
		return processResponse(response);
	}

	private List<String> processResponse(String response) {
		List<String> list = Arrays.asList(response.split("\n"));
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			if (line.trim().length() <= 1 || line.indexOf("----") >= 0)
				continue;
			else {
				newList.add(line.replaceAll("[0-9]", "").trim());
			}
		}
		return newList;
	}
}
