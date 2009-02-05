package br.com.kibutx.bixochecker.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import br.com.kibutx.bixochecker.vestibular.VestibularProcessor;

class ProcessorsCellRenderer extends JLabel implements ListCellRenderer {
	// This is the only method defined by ListCellRenderer.
	// We just reconfigure the JLabel each time we're called.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1843326573015312775L;

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String s = ((VestibularProcessor)value).getDisplayName();
		setText(s);
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		// ((ProcessorsListModel)list.getModel()).setCheckBox(this,
		// (VestibularProcessor)value);

		return this;
	}
}
