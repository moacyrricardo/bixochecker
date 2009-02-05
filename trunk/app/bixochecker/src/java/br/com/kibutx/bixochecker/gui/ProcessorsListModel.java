package br.com.kibutx.bixochecker.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import br.com.kibutx.bixochecker.config.ProcessorsConfigManager;
import br.com.kibutx.bixochecker.gui.config.InvalidConfigException;
import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;
import br.com.kibutx.bixochecker.vestibulares.ComvestPhase2Processor;
import br.com.kibutx.bixochecker.vestibulares.ComvestProcessor;
import br.com.kibutx.bixochecker.vestibulares.FuvestPhase2Processor;
import br.com.kibutx.bixochecker.vestibulares.FuvestProcessor;

public class ProcessorsListModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2577833714322896913L;
	private List<VestibularProcessor> vestibularProcessors;
	private JList list;

	public ProcessorsListModel(JList list) {
		this.list = list;
		vestibularProcessors = new ArrayList<VestibularProcessor>();
		vestibularProcessors.add(new ComvestProcessor());
		vestibularProcessors.add(new ComvestPhase2Processor());
		vestibularProcessors.add(new FuvestProcessor());
		vestibularProcessors.add(new FuvestPhase2Processor());
	}

	public void load(ProcessorsConfigManager config) {
		List<Integer> sel = new ArrayList<Integer>();
		for (int ind = 0; ind < vestibularProcessors.size(); ind++) {
			VestibularProcessor v = vestibularProcessors.get(ind);
			if (config.get(v)) {
				sel.add(new Integer(ind));
			}
		}
		int[] selec = new int[sel.size()];
		for (int ind = 0; ind < sel.size(); ind++) {
			Integer i = sel.get(ind);
			selec[ind] = i.intValue();
		}
		this.list.setSelectedIndices(selec);
	}

	public void save(ProcessorsConfigManager config)
			throws InvalidConfigException {
		List<Integer> selected = new ArrayList<Integer>();
		for (int i : this.list.getSelectedIndices()) {
			selected.add(new Integer(i));
		}
		for (int ind = 0; ind < vestibularProcessors.size(); ind++) {
			VestibularProcessor v = vestibularProcessors.get(ind);
			Boolean b = Boolean.FALSE;
			if (selected.contains(new Integer(ind))) {
				b = Boolean.TRUE;
			}
			config.set(v, b);
		}
	}

	public Object getElementAt(int index) {
		return vestibularProcessors.get(index);
	}

	public int getSize() {
		return vestibularProcessors.size();
	}

}
