package br.com.kibutx.bixochecker.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sourceforge.napkinlaf.NapkinLookAndFeel;

import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;
import br.com.kibutx.bixochecker.vestibulares.ComvestPhase2Processor;
import br.com.kibutx.bixochecker.vestibulares.ComvestProcessor;
import br.com.kibutx.bixochecker.vestibulares.FuvestPhase2Processor;
import br.com.kibutx.bixochecker.vestibulares.FuvestProcessor;

public class RequesterApp {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NapkinLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<VestibularProcessor> v = new ArrayList<VestibularProcessor>();
		v.add(new ComvestProcessor());
		v.add(new ComvestPhase2Processor());
		v.add(new FuvestProcessor());
		v.add(new FuvestPhase2Processor());
		ResultsChecker rc = new ResultsChecker(v);
		Thread t = new Thread(rc);
		t.start();
	}
}
