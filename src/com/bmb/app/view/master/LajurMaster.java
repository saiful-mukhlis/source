package com.bmb.app.view.master;



import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.ToolbarSmallRLTED;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.LajurComponetEdit;
import com.bmb.app.impl.view.form.LajurComponetNew;
import com.bmb.app.impl.view.form.LajurComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterLajur;
import com.bmb.app.view.table.LajurTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurMaster extends MasterAbstract {
	
	
	public LajurMaster() {
		super();
		lebar=0.4;
		title=LMasterLajur.title;
		urlIcon=L.iconLajur16;
		viewForm=new LajurComponetView();
		table = new LajurTable();
	}

	


	@Override
	public void actionAdd() {
		if (kandang==null) {
			App.showErrorEmptySelect(null, "Kandang");
		}else{
			super.actionAdd();
		}
	}




	public void actionReload() {
		super.actionReload();
		modelWidgetChange(kandang);
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		toolBar.getAdd().setEnabled(false);
	}

	@Override
	public void setEditForm() {
		setEditForm(new LajurComponetEdit());
	}

	@Override
	public void setForm() {
		setForm(new LajurComponetNew());
		
	}

	private ODocument kandang;
	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		actionView();
		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		if (model==null) {
			toolBar.getEdit().setEnabled(false);
			toolBar.getView().setEnabled(false);
			toolBar.getDel().setEnabled(false);
			toolBar.getAdd().setEnabled(false);
		}else{
			
			if (model.field("@class").equals(App.getLajurDao().getClassName())) {
				toolBar.getEdit().setEnabled(true);
				toolBar.getView().setEnabled(true);
				toolBar.getDel().setEnabled(true);
				viewForm.modelWidgetChange(model);
			}else if(model.field("@class").equals(App.getKandangDao().getClassName())){
				if (form==null) {
					setForm();
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
					form.build(db);
					form.addWidgetModel(table);
					db.close();
				}
				kandang=model;
				getForm().modelWidgetChange(model);
				toolBar.getAdd().setEnabled(true);
			}
		}

	}
	
	
	public void changeHakAkses() {
		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		toolBar.getAdd().setEnabled(getAdd());
		toolBar.getEdit().setEnabled(getEdit());
		toolBar.getDel().setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.KANDANG_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.KANDANG_DEL;
	}

	public boolean getLihat() {
		return DataUser.KANDANG_VIEW;
	}

	public boolean getEdit() {
		return DataUser.KANDANG_EDIT;
	}

}
