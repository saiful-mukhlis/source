package org.bmb.app.base.adapter;

import javax.swing.JTable;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;



public interface TableAdapter extends WidgetAdapter{
	public void reload(ODatabaseDocumentTx db);

	public JTable getTable();
	public void setMaster(MasterAdapterForEfectWidget master);
	public void setTypeEfectWidget(int typeEfectWidget);
	public void selected();
	public void initTableModel(ODatabaseDocumentTx db);
	public void addWidgetChange(WidgetAdapter widget);
	public void aksiDelete(ODatabaseDocumentTx db);
}
