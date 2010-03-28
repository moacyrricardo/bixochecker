package br.com.kibutx.bixochecker.vestibulares;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.kibutx.bixochecker.vestibular.AnoNaoSuportadoException;
import br.com.kibutx.bixochecker.vestibular.SingleListVestibularProcessor;

public class FuvestProcessor extends SingleListVestibularProcessor {

	public static final String ANO_VAR = "${ano}";

	private static final String PRI_FASE_URL = "http://www.fuvest.br/vest"
			+ ANO_VAR + "/listas/listconv.txt";

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

	public URL getResultURL() {
		try {
			return new URL(PRI_FASE_URL.replace(ANO_VAR, getAno().toString()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Não foi possível recuperar a URL(primeira fase).", e);
		}
	}

	public List<String> getResults(String response) {
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

	public String getDisplayName() {
		return "Fuvest (1a. Fase)";
	}

}
