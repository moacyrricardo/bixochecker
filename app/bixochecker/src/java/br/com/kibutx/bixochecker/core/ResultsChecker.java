package br.com.kibutx.bixochecker.core;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.kibutx.bixochecker.config.ConfigurationManager;
import br.com.kibutx.bixochecker.config.ProcessorsConfigManager;
import br.com.kibutx.bixochecker.gui.ResultsListFrame;
import br.com.kibutx.bixochecker.vestibular.AnoNaoSuportadoException;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

public class ResultsChecker implements Runnable {

	private List<VestibularProcessor> processors;

	private Boolean running = Boolean.TRUE;

	private ConfigurationManager configMan = ConfigurationManager.getInstance();

	private ProcessorsConfigManager pConfigMan = ProcessorsConfigManager
			.getInstance();

	public ResultsChecker(List<VestibularProcessor> processors) {
		if (processors == null) {
			throw new NullPointerException();
		}
		this.processors = processors;
	}

	private int getSeconds() {
		int s = Integer.parseInt(configMan
				.get(ConfigurationManager.GENERAL_SLEEP_TIME));
		return s;
	}

	private Integer getYear() {
		return new Integer(configMan
				.get(ConfigurationManager.GENERAL_REQUEST_YEAR));
	}

	private void configClient(HttpClient client) {
		client = new HttpClient();
		if (Boolean.getBoolean(configMan
				.get(ConfigurationManager.CONN_PROXY_ENABLED))) {
			client.getHostConfiguration().setProxy(
					configMan.get(ConfigurationManager.CONN_PROXY_HOST),
					Integer.parseInt(configMan
							.get(ConfigurationManager.CONN_PROXY_PORT)));
			if (Boolean.getBoolean(configMan
					.get(ConfigurationManager.CONN_PROXY_AUTH_ENABLED))) {
				client
						.getState()
						.setProxyCredentials(
								AuthScope.ANY,
								new UsernamePasswordCredentials(
										configMan
												.get(ConfigurationManager.CONN_PROXY_AUTH_USERNAME),
										configMan
												.get(ConfigurationManager.CONN_PROXY_AUTH_PASSWORD)));
			}
		}
	}

	public void run() {
		while (running.booleanValue()) {
			int s = getSeconds();
			System.out.println("Aguardando o início das requisições...(em "+s+" seg)");
			for (int i = 0; i < s && running.booleanValue(); i++) {
				try {
					System.out.print((i+1)+".");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			reloadConfig();
			resetProcessors();

			if (running.booleanValue()) {
				System.out.println("\nIniciando requisições...");
				HttpClient client = new HttpClient();
				configClient(client);

				for (VestibularProcessor p : processors) {
					if (!pConfigMan.get(p)) {// Não está habilitado
						continue;
					}
					GetMethod met = null;
					met = new GetMethod(p.getResultURL().toString());
					// met = new GetMethod(p.getSegundaFase().toString());
					try {
						int code = client.executeMethod(met);
						if (code == 200) {
							System.out.println("Saiu da "+p.getDisplayName()+"!!");
							List<String> results = p.getResults(met
									.getResponseBodyAsString());
							pConfigMan.set(p, Boolean.FALSE);
							ResultsListFrame dis = new ResultsListFrame();
							dis.setTitle("Resultados da " + p.getDisplayName());
							dis.setVisible(true);
							dis.setList(getListAsText(results));
							// show window with results
						}
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Fim das requisições.");
				pConfigMan.persist();
			}
		}
	}

	private String getListAsText(List<String> results) {
		StringBuffer b = new StringBuffer();
		for (String s : results) {
			b.append(s).append("\n");
		}
		return b.toString();
	}

	private void reloadConfig() {
		configMan.reload();
		pConfigMan.reload();
	}

	private void resetProcessors() {
		for (VestibularProcessor v : processors) {
			try {
				v.setAno(getYear());
			} catch (AnoNaoSuportadoException e) {
			}
		}
	}
}
