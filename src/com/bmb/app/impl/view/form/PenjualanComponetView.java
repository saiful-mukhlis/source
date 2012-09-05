package com.bmb.app.impl.view.form;

import org.bmb.app.base.adapter.ComponetViewAdapter;

import com.bmb.app.lang.L;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanComponetView extends PenjualanComponetFormDefault implements
		ComponetViewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Lihat Data Penjualan";
		icon = L.iconView16;
		
		initComponent(db);
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else {
			setContentComponent(model);
		}
		
	}
	
}
