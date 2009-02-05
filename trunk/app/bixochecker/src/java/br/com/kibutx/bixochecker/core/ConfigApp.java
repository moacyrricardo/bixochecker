package br.com.kibutx.bixochecker.core;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sourceforge.napkinlaf.NapkinLookAndFeel;
import br.com.kibutx.bixochecker.config.ConfigurationManager;
import br.com.kibutx.bixochecker.config.ProcessorsConfigManager;
import br.com.kibutx.bixochecker.gui.ConfigurationFrame;

public class ConfigApp {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NapkinLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConfigurationFrame configFrame = new ConfigurationFrame(ConfigurationManager.getInstance(),ProcessorsConfigManager.getInstance());
		configFrame.setVisible(true);
		configFrame.loadConfig();
	}
}
