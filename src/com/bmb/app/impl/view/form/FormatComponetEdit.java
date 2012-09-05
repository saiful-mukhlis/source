package com.bmb.app.impl.view.form;


import org.bmb.app.base.adapter.ComponetEditAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;

import com.bmb.app.dao.FormatDao;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LFormatLaporan;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class FormatComponetEdit extends FormatComponetFormDefault implements
		ComponetEditAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = LFormatLaporan.edit;
		icon = L.iconEdit16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	
	
	protected ODocument model;

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else
		if (modelIsTrue(model)) {
			this.model=model;
			setContentComponent(model);
		}
	}

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
		name.setEditable(false);
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 6, 9);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(kop1, kop2);
		setFocusEnter(kop2, kop3);
		setFocusEnter(kop3, save);
		setFocusEnter(save, kop1);
	}
	
	
	public boolean validate(ODatabaseDocumentTx db){
		return true;
	}
	public void actionSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=model;
			tmp.field(FormatDao.kop1, kop1.getText());
			tmp.field(FormatDao.kop2, kop2.getText());
			tmp.field(FormatDao.kop3, kop3.getText());
			
			try {
				tmp.save();
				for (WidgetAdapter w: widgeds) {
					w.modelWidgetChange(tmp);
					this.model=tmp;
				}
				App.showSaveOk();
				
			} catch (Exception e) {
				
			}finally{
				db.close();
			}
			
		}
	
		
	}


	
}
