/*
 * ResultsListFrame.java
 *
 * Created on 5 de Fevereiro de 2009, 02:14
 */

package br.com.kibutx.bixochecker.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author moa
 */
public class ResultsListFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = -5169269566126901052L;
	// private String textBackup = "";\
	private FilteringModel model;

	/** Creates new form ResultsListFrame */
	public ResultsListFrame() {
		initComponents();

		// init list
		model = new FilteringModel(this.txaResults) {
			private static final long serialVersionUID = 7901135250317612637L;

			@Override
			public void beforeFilter() {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterFilter() {
				// TODO Auto-generated method stub

			}
		};
		this.txaResults.setModel(model);
		this.txtSearchField.getDocument().addDocumentListener(model);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		scrollResults = new javax.swing.JScrollPane();
		txaResults = new javax.swing.JList();
		txtSearchField = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		// txaResults.setColumns(20);
		// txaResults.setRows(5);
		scrollResults.setViewportView(txaResults);

		txtSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				txtSearchFieldKeyTyped(evt);
			}
		});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				layout.createSequentialGroup().addContainerGap().add(
						layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.TRAILING).add(
								org.jdesktop.layout.GroupLayout.LEADING,
								scrollResults,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								376, Short.MAX_VALUE).add(
								org.jdesktop.layout.GroupLayout.LEADING,
								txtSearchField,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								376, Short.MAX_VALUE)).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				layout.createSequentialGroup().addContainerGap().add(
						scrollResults,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 543,
						Short.MAX_VALUE).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(
						txtSearchField,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void txtSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchFieldKeyTyped
		// String keyword = txtSearchField.getText().toUpperCase();
		// if (keyword.length() >= 3) {
		// StringBuffer text = new StringBuffer();
		// for (String line : textBackup.split("\n")) {
		// if (line.toUpperCase().indexOf(keyword) >= 0) {
		// text.append(line).append("\n");
		// }
		// }
		// txaResults.setText(text.toString());
		// } else {
		// txaResults.setText(textBackup);
		// }
	}// GEN-LAST:event_txtSearchFieldKeyTyped

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane scrollResults;
	private javax.swing.JList txaResults;
	private javax.swing.JTextField txtSearchField;

	// End of variables declaration//GEN-END:variables

//	public void setList(String list) {
//		textBackup = list;
//		txaResults.setText(list);
//		txaResults.setEnabled(true);
//		txtSearchField.setEnabled(true);
//		this.repaint();
//		this.invalidate();
//	}
	
	public void setList(List<String> list) {
		model.addAll(list);
	}

	public static void main(String[] args) {
		ResultsListFrame l = new ResultsListFrame();
		List<String> lista = new ArrayList<String>();
		lista.add("AFdsdd");
		lista.add("Guilherme");
		lista.add("Cristina");
		lista.add("Jonas");
		lista.add("Marina");
		lista.add("Goias");
		lista.add("Botafogo");
		lista.add("EstrelaSolitaria");
		l.setList(lista);
		l.setVisible(true);
	}
}
