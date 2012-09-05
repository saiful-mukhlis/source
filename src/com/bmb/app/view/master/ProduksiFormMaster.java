package com.bmb.app.view.master;

import javax.swing.JLabel;

import org.bmb.app.base.abstrak.MasterFAbstract;

import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.ProduksiForm;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LInputProduksi;
import com.bmb.app.view.table.LajurdNoLoadTable;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProduksiFormMaster extends MasterFAbstract {

	

	public int getDevide() {
//		Double tmp = App.getW()*0.4;
		return 430;
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		form = new ProduksiForm();
	}


	@Override
	public void initTable(ODatabaseDocumentTx db) {
		table = new LajurdNoLoadTable();
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
		label = new JLabel(App.getIcon(L.iconProduksi16));
		label.setText(LInputProduksi.title);
	}

	public void tampilkanDefault() {
		splitPane.setDividerLocation(280);
	}

}
