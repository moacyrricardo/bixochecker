/*
 * ConfigGeneralTab.java
 *
 * Created on 15 de Dezembro de 2008, 21:45
 */

package br.com.kibutx.bixochecker.gui;

import br.com.kibutx.bixochecker.config.ConfigurationManager;
import br.com.kibutx.bixochecker.gui.config.Config;
import br.com.kibutx.bixochecker.gui.config.InvalidConfigException;

/**
 * 
 * @author moa
 */
public class ConfigGeneralTab extends javax.swing.JPanel implements Config {

	private static final long serialVersionUID = -7023244115542687519L;

	/** Creates new form ConfigGeneralTab */
	public ConfigGeneralTab() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnlGeneral = new javax.swing.JPanel();
		lblSleepTime = new javax.swing.JLabel();
		txtSleepTime = new javax.swing.JFormattedTextField();

		pnlGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(), "Geral"));

		lblSleepTime.setText("Tempo de pausa");

		org.jdesktop.layout.GroupLayout pnlGeneralLayout = new org.jdesktop.layout.GroupLayout(
				pnlGeneral);
		pnlGeneral.setLayout(pnlGeneralLayout);
		pnlGeneralLayout
				.setHorizontalGroup(pnlGeneralLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								pnlGeneralLayout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												pnlGeneralLayout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING,
																false)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																txtSleepTime)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																lblSleepTime,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(206, Short.MAX_VALUE)));
		pnlGeneralLayout.setVerticalGroup(pnlGeneralLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				pnlGeneralLayout.createSequentialGroup().addContainerGap().add(
						lblSleepTime).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(
						txtSleepTime,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(183, Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(
						pnlGeneral,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(
						pnlGeneral,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE).addContainerGap()));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel lblSleepTime;
	private javax.swing.JPanel pnlGeneral;
	private javax.swing.JFormattedTextField txtSleepTime;

	// End of variables declaration//GEN-END:variables

	private void validateFields() throws InvalidConfigException {
		Long value = null;
		try {
			value = new Long(txtSleepTime.getText());
		} catch (NumberFormatException nfe) {
			throw new InvalidConfigException("Sleep Time",txtSleepTime.getText(),"Intervalo entre requisições deve ser um número positivo.",nfe);
		}
		if(value.compareTo(new Long(0)) <= 0){
			throw new InvalidConfigException("Sleep Time",txtSleepTime.getText(),"Intervalo entre requisições deve ser um número positivo.");
		}
	}

	@Override
	public void load(ConfigurationManager config) {
		txtSleepTime.setText(config.get(ConfigurationManager.SLEEP_TIME));
	}

	@Override
	public void save(ConfigurationManager config) throws InvalidConfigException {
		// TODO Auto-generated method stub
		validateFields();
		config.set(ConfigurationManager.SLEEP_TIME, txtSleepTime.getText());
	}

}
