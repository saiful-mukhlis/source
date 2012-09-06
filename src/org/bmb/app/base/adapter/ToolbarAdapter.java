package org.bmb.app.base.adapter;

import javax.swing.JPanel;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface ToolbarAdapter extends HakAksesListener{
	public void build(ODatabaseDocumentTx db);
	public JPanel getPanel();
	public void setWindow(WindowAdapter window);
	public void showMaxi();
	public void showMini();
}
