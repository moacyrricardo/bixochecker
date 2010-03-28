package br.com.kibutx.bixochecker.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;
import br.com.kibutx.bixochecker.vestibulares.ComvestPhase2Processor;
import br.com.kibutx.bixochecker.vestibulares.ComvestProcessor;
import br.com.kibutx.bixochecker.vestibulares.FuvestPhase2Processor;
import br.com.kibutx.bixochecker.vestibulares.FuvestProcessor;
import br.com.kibutx.bixochecker.vestibulares.PuccampProcessor;

public class RequesterApp {

	public static void main(String[] args) {
		try {
//			UIManager.setLookAndFeel(new NapkinLookAndFeel());
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<VestibularProcessor> v = new ArrayList<VestibularProcessor>();
		v.add(new ComvestProcessor());
		v.add(new ComvestPhase2Processor());
		v.add(new FuvestProcessor());
		v.add(new FuvestPhase2Processor());
		v.add(new PuccampProcessor());
		ResultsChecker rc = new ResultsChecker(v);
		Thread t = new Thread(rc);
		t.start();
	}
}
