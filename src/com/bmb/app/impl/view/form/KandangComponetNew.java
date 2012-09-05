package com.bmb.app.impl.view.form;

import org.bmb.app.base.adapter.ComponetNewAdapter;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangComponetNew extends KandangComponetFormDefault implements
		ComponetNewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Menambahkan Data Kandang Baru";
		icon = L.iconTambah16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	


	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}


	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 5, 7);
		builder.append( reset, 7, 7);
	}
	
	
	
	
	
	
	
	
	

	@Override
	public void actionReset() {
		clearText(name);
		clearText(note);
		code.setText("AUTO");
	}
	public void setFocusEnter(){
		setFocusEnter(name, note);
		//setFocusEnter(note, save);
		setFocusEnter(save, reset);
		setFocusEnter(reset, code);
		setFocusEnter(code, name);
	}
	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		return App.getKandangDao().factoryModel(code.getText(), name.getText(), note.getText());
	}
	public boolean validate(ODatabaseDocumentTx db){
		if (!(code.getText().equalsIgnoreCase("AUTO") || code.getText().trim().equalsIgnoreCase("")) ) {
			long tmp=App.getKandangDao().getCountByColumn(db, KandangDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, KandangDao.fcode);
				return false;
			}
		}
		
		if (!validate(db, name, KandangDao.fname)) {
			return false;
		}
		long tmp=App.getKandangDao().getCountByColumn(db, KandangDao.name, name.getText());
		if (tmp>0) {
			App.showErrorDataSudahAda(db, KandangDao.fname);
			return false;
		}
//		if (!validate(db, note, KandangDao.fnote)) {
//			return false;
//		}
		return true;
	}
	public void save(ODatabaseDocumentTx db, ODocument model) {
		dao.save(db, model);
	}


	
}
