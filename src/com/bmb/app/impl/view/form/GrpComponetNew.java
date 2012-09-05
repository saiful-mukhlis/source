package com.bmb.app.impl.view.form;


import java.util.Date;

import org.bmb.app.base.adapter.ComponetNewAdapter;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.UsrDao;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpComponetNew extends GrpComponetFormDefault implements
		ComponetNewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Menambahkan Hak Akses Baru";
		icon = L.iconTambah16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	
	public void afterOk() {
		name.requestFocus();
	}


	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}


	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append(save, 5, 7);
		builder.append(reset, 7, 7);
	}
	
	
	
	
	
	
	
	
	

	public void actionReset() {
		code.setText("AUTO");
		clearText(name);
		clearText(note);
	}
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, note);
		setFocusEnter(note, save);
		setFocusEnter(save, code);
	}
	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		if (DataUser.getUsr()!=null) {
			String tmpx=DataUser.getUsr().field(UsrDao.username);
			return App.getGrpDao().factoryModel(namea, codea, notea, tmpx, new Date());
		}else{
			return null;
		}
		
	}
	
	
	private String codea;
	private String namea;
	private String notea;
	public boolean validate(ODatabaseDocumentTx db){
		if (!(code.getText().equalsIgnoreCase("AUTO") || code.getText().trim().equalsIgnoreCase("")) ) {
			long tmp=App.getGrpDao().getCountByColumn(db, GrpDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, GrpDao.fcode);
				return false;
			}
		}
		codea=code.getText();
		if (!validate(db, name, "Nama")) {
			return false;
		}
		
		long tmp=App.getGrpDao().getCountByColumn(db, GrpDao.name, name.getText());
		if (tmp>0) {
			App.showErrorDataSudahAda(db, GrpDao.fname);
			return false;
		}
		namea=name.getText();
		
		notea=note.getText();
		
		return true;
	}
	public void save(ODatabaseDocumentTx db, ODocument model) {
		dao.save(db, model);
	}


	
}
