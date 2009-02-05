package br.com.kibutx.bixochecker.vestibulares;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.kibutx.bixochecker.vestibular.AnoNaoSuportadoException;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

public class ComvestProcessor implements VestibularProcessor {

	public static final String ANO_VAR = "${ano}";

	private static final String PRI_FASE_URL = "http://www.comvest.unicamp.br/vest"
			+ ANO_VAR + "/F1/aprova1/geral.html";

	private Integer ano;

	public void setAno(Integer ano) throws AnoNaoSuportadoException {
		this.ano = ano;
	}

	public Integer getAno() {
		if (ano == null) {
			ano = Calendar.getInstance().get(Calendar.YEAR);
		}
		return ano;
	}

	public URL getResultURL() {
		try {
			return new URL(PRI_FASE_URL
					.replace(ANO_VAR, getAno().toString()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"N�o foi poss�vel recuperar a URL(primeira fase).", e);
		}
	}

	public List<String> getResults(String response) {
		return processResponse(response);
	}

	private List<String> processResponse(String response) {
		int start = response.indexOf("<pre>") + 5;
		int end = response.indexOf("</pre>");
		response = response.substring(start, end);
		List<String> list = Arrays.asList(response.split("\n"));
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			if (line.trim().length() <= 1)
				continue;
			else {
				line = line.substring(0, line.indexOf("(")).trim();
				newList.add(line);
			}
		}
		return newList;
	}

	public String getDisplayName() {
		return "Comvest (1a. Fase)";
	}
}
