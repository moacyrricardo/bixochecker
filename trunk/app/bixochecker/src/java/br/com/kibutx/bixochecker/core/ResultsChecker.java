package br.com.kibutx.bixochecker.core;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

public class ResultsChecker implements Runnable {

	private List<VestibularProcessor> processors;

	private Boolean running;

	private Integer seconds;

	@Override
	public void run() {
		while (running.booleanValue()) {
			for (int i = 0; i < seconds.intValue() || running.booleanValue(); i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(running.booleanValue()){
				HttpClient client = new HttpClient();
				
				for(VestibularProcessor p : processors){
					GetMethod met = null;
					met = new GetMethod(p.getResultURL().toString());
//					met = new GetMethod(p.getSegundaFase().toString());
					try {
						client.executeMethod(met);
						List<String> results = p.getResults(met.getResponseBodyAsString());
//						show window with results
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public ResultsChecker(List<VestibularProcessor> processors, Integer seconds) {
		if (seconds == null || this.processors == null) {
			throw new NullPointerException();
		}
		this.processors = processors;
		this.seconds = seconds;
	}
}
