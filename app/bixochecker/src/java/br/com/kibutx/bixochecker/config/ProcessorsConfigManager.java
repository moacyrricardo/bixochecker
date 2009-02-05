package br.com.kibutx.bixochecker.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

/**
 * Classe responsavel pelo controle dos vestibulars processors
 * @author moa
 *
 */
public class ProcessorsConfigManager {
	private static final String FILE_NAME = "/processors.cfg";	
	public static ProcessorsConfigManager config;
	
	private Properties properties;
	
	public static ProcessorsConfigManager getInstance(){
		if(config == null){
			config = new ProcessorsConfigManager();
		}
		return config;
	}
	
	private ProcessorsConfigManager(){
		reload();
	}

	public void reload() {
		loadConfiguration(FILE_NAME);
	}
	
	public void persist(){
		saveConfiguration(FILE_NAME);
	}

	private boolean loadConfiguration(String f) {
		properties = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream(f);
			properties.load(in);
			return true;
		} catch (IOException e) {
			// debug was not able to read configuration file
		}
		return false;
	}

	private boolean saveConfiguration(String f) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(getClass().getResource(f)
					.getFile());
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			// debug was not able to save configuration file
			return false;
		} catch (IOException e) {
			// debug was not able to save configuration file
			return false;
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					//debug error while trying to close outputstream
				}
				out = null;
			}
		}
		return true;
	}
	
	private String getKey(VestibularProcessor v){
		return v.getClass().getName();
	}

	public Boolean get(VestibularProcessor v) {
		String g = properties.getProperty(getKey(v));
		return new Boolean(g);
	}

	public void set(VestibularProcessor v, Boolean value) {
		properties.setProperty(getKey(v), value.toString());
	}
}
