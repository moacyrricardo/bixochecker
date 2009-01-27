package br.com.kibutx.bixochecker.gui.config;

import br.com.kibutx.bixochecker.core.ConfigurationManager;

import com.sun.org.apache.xml.internal.resolver.CatalogManager;

public interface Config {
	public abstract void load(ConfigurationManager config);
	public abstract void save(ConfigurationManager config) throws InvalidConfigException;
}
