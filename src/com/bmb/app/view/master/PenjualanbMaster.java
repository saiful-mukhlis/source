package com.bmb.app.view.master;


import org.bmb.app.base.abstrak.MasterAbstract;

import com.bmb.app.global.App;
import com.bmb.app.view.table.UsrTable;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanbMaster extends MasterAbstract {

	

	public int getDevide() {
		Double tmp = App.getW()*0.4;
		App.info(tmp.intValue()+"");
		return tmp.intValue();
	}
	
	public void initBody(ODatabaseDocumentTx db) {
//		setForm(new UsrForm());
//		setEditForm(new UsrEditForm());
//		setViewForm(new UsrViewForm());
	}


	public void initTable(ODatabaseDocumentTx db) {
		table = new UsrTable();
		table.build(db);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		//tampilan default
//		cardLayout.show(cardPanel, "lihat");
		actionView();

	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void perspectiveDefault() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEditForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setForm() {
		// TODO Auto-generated method stub
		
	}

}
