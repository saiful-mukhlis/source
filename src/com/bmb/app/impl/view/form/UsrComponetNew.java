package com.bmb.app.impl.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

import org.bmb.app.base.adapter.ComponetNewAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.komponen.ComboBox;
import org.bmb.app.base.komponen.PasswordField;
import org.bmb.app.base.komponen.TextField;
import org.bmb.app.base.model.ODocumentToString;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.UsrDao;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterPegawai;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrComponetNew extends UsrComponetFormDefault implements
		ComponetNewAdapter {
	protected PasswordField ulang;
	protected ComboBox status;
	protected ComboBox grp;
	
	
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		initComponent(db);
		title = LMasterPegawai.tambah;
		icon = L.iconTambah16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	




	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		builder.append( "Ketik Ulang", ulang, 1, 7, 5);
		builder.append( "Status", status, 1, 9, 5);
		builder.append( "Hak Akses", grp, 1, 11, 5);
		
		buildButton(db);

	}
	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 5, 13);
		builder.append( reset, 7, 13);
	}
	
	
	
	
	public void initComponent(ODatabaseDocumentTx db){
		nikName=new TextField();
		username=new TextField();
		password=new PasswordField();
		
		String [] stringStatus={"Tidak Aktif","Aktif"};
		
		GrpDao grpDao=App.getGrpDao();
		List<ODocument> grps=grpDao.getAll(db);
		ODocumentToString [] modelGrps=new ODocumentToString[grps.size()+1];
		modelGrps[0]=new ODocumentToString(App.getGrpDao(), "Pilih Hak Akses");
		int i=1;
		for (ODocument oDocument : grps) {
			modelGrps[i]=new ODocumentToString(App.getGrpDao(), oDocument);
			i++;
		}
		grp=new ComboBox(modelGrps);
		
		ulang=new PasswordField();
		status=new ComboBox(stringStatus);
		
		
		save=new JButton(L.save);
		reset=new JButton(L.reset);
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionSave();
				
			}
		});
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
				
			}
		});
				
	}
	
	
	public void afterSave(ODocument tmp){
		for (WidgetAdapter w: widgeds) {
			w.modelWidgetAdd(tmp);
		}
		for (WidgetAdapter w: widgeds) {
			w.modelWidgetAdd(((ODocumentToString) grp.getSelectedItem()).getO());
		}
		actionReset();
	}
	

	public void actionReset(){
		clearText(nikName);
		clearText(username);
		clearText(password);
		clearText(ulang);
		status.setSelectedIndex(1);
		grp.setSelectedItem(0);
		requestDefaultFocus();
		
	}
	public void setFocusEnter(){
		setFocusEnter(nikName, username);
		setFocusEnter(username, password);
		setFocusEnter(password, ulang);
		setFocusEnter(ulang, status);
		setFocusEnter(status, grp);
		setFocusEnter(grp, save);
//		setFocusEnter(save, reset);
		setFocusEnter(reset, nikName);
	}
	

	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		byte tmps=0;
		if (status.getSelectedIndex()==1) {
			tmps=1;
		}
		ODocument logdb=new ODocument("Logdb");
		logdb.field("createdBy", DataUser.getUsr().field("name") );
		logdb.field("createdAt", new Date(), OType.DATE);
		
		return App.getUsrDao().factoryModel(username.getText(), new String(password.getPassword()), nikName.getText(), tmps,
				((ODocumentToString) grp.getSelectedItem()).getO(), logdb);
	}
	public boolean validate(ODatabaseDocumentTx db){
		if (!validate(db, nikName, "Nama")) {
			return false;
		}
		if (!validate(db, username, "Username")) {
			return false;
		}
		if (!validate(db, password, "Password")) {
			return false;
		}
		if (!validate(db, ulang, "Ketik Ulang")) {
			return false;
		}
		if (!validate(db, password, ulang, "Ketik Ulang")) {
			return false;
		}
		if (!validate(db, App.getUsrDao(), username, UsrDao.fusername, UsrDao.username)) {
			return false;
		}
		if (!validate(db, grp,  UsrDao.fgrp)) {
			return false;
		}

		return true;
	}
	public void save(ODatabaseDocumentTx db, ODocument model) {
		model.save();
	}


	
}
