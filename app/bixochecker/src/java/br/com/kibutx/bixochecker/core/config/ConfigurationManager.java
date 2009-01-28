package br.com.kibutx.bixochecker.core.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Classe responsavel pelo controle das configurações
 * @author moa
 *
 */
public class ConfigurationManager {
	private static final String FILE_NAME = "configuration.cfg";	
	public static ConfigurationManager config;
	/*Start Fields*/
	public static final String SLEEP_TIME = "sleep.time";
	public static final String CONN_PROXY_ENABLED = "conn.proxy.enabled";
	public static final String CONN_PROXY_HOST = "conn.proxy.host";
	public static final String CONN_PROXY_PORT = "conn.proxy.port";
	public static final String CONN_PROXY_AUTH_ENABLED = "conn.proxy.auth.enabled";
	public static final String CONN_PROXY_AUTH_USERNAME = "conn.proxy.auth.username";
	public static final String CONN_PROXY_AUTH_PASSWORD = "conn.proxy.auth.password";
	/*End Fields*/
	
	private Properties properties;
	
	public static ConfigurationManager getInstance(){
		if(config == null){
			config = new ConfigurationManager();
		}
		return config;
	}
	
	private ConfigurationManager(){
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
			properties.load(getClass().getResourceAsStream(f));
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

	public String get(String key) {
		return properties.getProperty(key);
	}

	public void set(String key, String value) {
		properties.setProperty(key, value);
	}
}
