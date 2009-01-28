package br.com.kibutx.bixochecker.gui.config;

import br.com.kibutx.bixochecker.config.ConfigurationManager;

public interface Config {
	public abstract void load(ConfigurationManager config);
	public abstract void save(ConfigurationManager config) throws InvalidConfigException;
}
