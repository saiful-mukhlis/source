package com.bmb.app.impl.view.form;

import org.bmb.app.base.adapter.ComponetViewAdapter;

import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdComponetView extends LajurdComponetFormDefault implements
		ComponetViewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Lihat Data Produksi Lajur";
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
			setLajurdModel(model);
			setContentComponent(model);
		}else if (model.field("@class").equals(App.getKandangDao().getClassName())) {
			setKandangModel(model);
		}else if (model.field("@class").equals(App.getLajurDao().getClassName())) {
			setLajurModel(model);
		}
		
	}
	
}
