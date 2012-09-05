package com.bmb.app.impl.view.form;


import org.bmb.app.base.adapter.ComponetEditAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterHakAkses;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpComponetEdit extends GrpComponetFormDefault implements
		ComponetEditAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = LMasterHakAkses.edit;
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
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 7);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, note);
		setFocusEnter(note, save);
		setFocusEnter(save, code);
	}
	
	
	public boolean validate(ODatabaseDocumentTx db){
		if (!code.getText().equalsIgnoreCase((String) model.field(GrpDao.code))) {
			long tmp=App.getGrpDao().getCountByColumn(db, GrpDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, GrpDao.fcode);
				return false;
			}
		}
		if (!validate(db, name, GrpDao.fname)) {
			return false;
		}
		if (!name.getText().equalsIgnoreCase((String)model.field(GrpDao.name))) {
			long tmp=App.getGrpDao().getCountByColumn(db, GrpDao.name, name.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, GrpDao.fname);
				return false;
			}
		}
		
		return true;
	}
	public void actionSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=model;
			tmp.field(GrpDao.code, code.getText());
			tmp.field(GrpDao.name, name.getText());
			tmp.field(GrpDao.note, note.getText());
			
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
