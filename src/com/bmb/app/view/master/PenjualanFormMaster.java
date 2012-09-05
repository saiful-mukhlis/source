package com.bmb.app.view.master;

import javax.swing.JLabel;

import org.bmb.app.base.abstrak.MasterFAbstract;

import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.PenjualanForm;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LInputPenjualan;
import com.bmb.app.view.table.PenjualanFTable;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanFormMaster extends MasterFAbstract {

	

	public int getDevide() {
//		Double tmp = App.getW()*0.4;
		return 400;
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		form = new PenjualanForm();
		//form.addWidgetModel(table);
	}


	@Override
	public void initTable(ODatabaseDocumentTx db) {
		table = new PenjualanFTable();
		table.build(db);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		//tampilan default
		form.getPanel().setVisible(true);
		tampilkanDefault();

	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub

	}
	
	public void initLabelTitle(ODatabaseDocumentTx db){
		label = new JLabel(App.getIcon(L.iconPenjualan16));
		label.setText(LInputPenjualan.title);
	}

	@Override
	public void tampilkanDefault() {
		splitPane.setDividerLocation(270);
	}


}
