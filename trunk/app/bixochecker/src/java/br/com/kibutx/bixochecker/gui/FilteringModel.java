package br.com.kibutx.bixochecker.gui;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public abstract class FilteringModel extends AbstractListModel implements
		DocumentListener {
	private static final long serialVersionUID = -4259417686985740950L;

	private List<Object> objects;
	private List<Object> filtered;
	private JList list;
	private String lastSearch = "";
	private Method lastSearchMethod = null;
	private Boolean caseSensitive = Boolean.TRUE;
	private Map<Document, Method> installedFields = new HashMap<Document, Method>();

	public FilteringModel(JList list) {
		super();
		this.list = list;
		this.objects = new ArrayList<Object>();
		filtered = new ArrayList<Object>();
		this.list.setModel(this);
	}

	public boolean installFiltering(JTextField field, Class klass,
			String methName) {
		try {
			Method meth = klass.getMethod(methName, null);
			installedFields.put(field.getDocument(), meth);
			field.getDocument().addDocumentListener(this);
			return true;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void uninstallFiltering(JTextField field) {
		installedFields.remove(field.getDocument());
		field.getDocument().removeDocumentListener(this);

	}

	private void reorderList(List<Object> list) {
		Collections.sort(list, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 != null && o2 != null) {
					return o1.toString().compareToIgnoreCase(o2.toString());
				}
				return 0;
			}
		});
	}

	public void addElement(Object element) {
		objects.add(element);
		reorderList(objects);
		filter(lastSearch, lastSearchMethod);
		int ind = this.filtered.indexOf(element);
		if (ind >= 0) {
			fireIntervalAdded(this, ind, ind);
		}
	}

	public void replaceElement(Object element, Object replace) {
		int indF = filtered.indexOf(element);
		int indO = objects.indexOf(element);
		filtered.set(indF, replace);
		objects.set(indO, replace);
	}

	public void removeElement(Object element) {
		int ind = filtered.indexOf(element);
		objects.remove(element);
		filter(lastSearch, lastSearchMethod);
		if (ind >= 0) {
			fireIntervalRemoved(this, ind, ind);
		}
	}

	public void removeElement(int index) {
		removeElement(objects.get(index));
	}

	private boolean filterComparison(String listItem, String comp,
			boolean caseSensitive) {
		if (comp != null && listItem != null) {
			if (caseSensitive) {
				comp = comp.toLowerCase();
				listItem = listItem.toLowerCase();
			}
			return listItem.indexOf(comp) >= 0;
		}
		return false;
	}

	private void filter(String word, Method method) {
		beforeFilter();
		filtered.clear();
		for (Object o : objects) {
			if (word != null && !word.equals("")) {
				String comp = o.toString();
				if (method != null) {
					try {
						Object ret = method.invoke(o);
						comp = ret.toString();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// if (o.toString().indexOf(word) >= 0) {
				if (filterComparison(comp, word, this.caseSensitive)) {
					filtered.add(o);
				}
			} else {
				filtered.add(o);
			}
		}

		// Workaround: se nao fizer isso a lista nao atualiza
		this.list.setModel(this);

		afterFilter();
	}

	public Object getElementAt(int index) {
		return filtered.get(index);
	}

	public int getSize() {
		return filtered.size();
	}

	// DocumentListener methods
	public void changedUpdate(DocumentEvent e) {
	}

	public void insertUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			documentChanged(doc);
		} catch (BadLocationException e1) {
			// Never supposed to be thrown, since we are in the bounds of
			// document.
			return;
		}
	}

	public void removeUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			documentChanged(doc);
		} catch (BadLocationException e1) {
			// Never supposed to be thrown, since we are in the bounds of
			// document.
			return;
		}
	}

	public void documentChanged(Document doc) throws BadLocationException {
		lastSearch = doc.getText(0, doc.getLength());
		lastSearchMethod = this.installedFields.get(doc);
		filter(lastSearch, lastSearchMethod);
		fireContentsChanged(this, 0, getSize() - 1);
		this.list.repaint();
	}

	public void clear() {
		int siz = getSize();
		if (siz > 0) {
			fireIntervalRemoved(this, 0, siz - 1);
		}
		this.objects.clear();
		filter(lastSearch, lastSearchMethod);
	}

	public void addAll(List contentList) {
		int siz = getSize();
		this.objects.addAll(contentList);
		reorderList(objects);
		filter(lastSearch, lastSearchMethod);
		if (getSize() > siz) {
			fireContentsChanged(this, siz, getSize() - 1);
		}
	}

	public void addAll(Object[] contentList) {
		int siz = getSize();
		for (Object o : contentList) {
			this.objects.add(o);
		}
		reorderList(objects);
		filter(lastSearch, lastSearchMethod);
		if (getSize() > siz) {
			fireContentsChanged(this, siz, getSize() - 1);
		}
	}

	public Boolean getCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(Boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public abstract void afterFilter();

	public abstract void beforeFilter();

}