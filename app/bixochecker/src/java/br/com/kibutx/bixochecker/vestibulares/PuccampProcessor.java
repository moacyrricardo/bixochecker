package br.com.kibutx.bixochecker.vestibulares;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.kibutx.bixochecker.vestibular.AnoNaoSuportadoException;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

public class PuccampProcessor implements VestibularProcessor {

	public static final String ANO_VAR = "${ano}";
	private static final int MAX_CURSO = 57;
	private static final String BEGIN_DELIM = "<span id=\"lblAlunos\">";
	private static final String END_DELIM = "</span>";

	private static final String RESULTADO_URL = "http://www.puc-campinas.edu.br/vestibular"
			+ ANO_VAR + "/resultado_lista_alunos.aspx?Curso=";

	private Integer ano;

	public void setAno(Integer ano) throws AnoNaoSuportadoException {
		// if (ano.intValue() < 2003)
		// throw new AnoNaoSuportadoException(ano,
		// "Resultados apenas para anos posteriores a 2003.");
		this.ano = ano;
	}

	public Integer getAno() {
		if (ano == null) {
			ano = Calendar.getInstance().get(Calendar.YEAR);
		}
		return ano;
	}

	public List<URL> getResultsURL() {
		List<URL> urls = new ArrayList<URL>();
		String url = RESULTADO_URL.replace(ANO_VAR, getAno().toString());
		try {
			for (int ind = 1; ind <= MAX_CURSO; ind++) {
				urls.add(new URL(url + (ind < 10 ? "0" + ind : ind)));
			}
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Não foi possível recuperar a URL.", e);
		}
		return urls;
	}

	public List<String> getResults(String response) {
		return processResponse(response);
	}

	private List<String> processResponse(String response) {
		List<String> newList = new ArrayList<String>();
		int i = response.indexOf(BEGIN_DELIM);
		if (i >= 0) {
			i += BEGIN_DELIM.length();
			response = response.substring(i);
			int e = response.indexOf(END_DELIM);
			String[] names = response.substring(0, e).toUpperCase().split(
					"<BR/>");
			List<String> list = Arrays.asList(names);
			for (int li = 0; li < list.size(); li++) {
				String line = list.get(li);
				if (line.trim().length() > 0){
					newList.add(line.trim());
				}
			}
		}
		return newList;
	}

	public String getDisplayName() {
		return "PUCCamp";
	}

}
