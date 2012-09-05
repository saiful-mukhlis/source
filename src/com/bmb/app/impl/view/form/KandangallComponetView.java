package com.bmb.app.impl.view.form;

import org.bmb.app.base.adapter.ComponetViewAdapter;

import com.bmb.app.lang.L;
import com.bmb.app.lang.LProduksiTotal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangallComponetView extends KandangallComponetFormDefault implements
		ComponetViewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = LProduksiTotal.lihat;
		icon = L.iconView16;
		
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else if (modelIsTrue(model)) {
			setKandangdModel(model);
			setContentComponent(model);
		}
		
	}
	
}
