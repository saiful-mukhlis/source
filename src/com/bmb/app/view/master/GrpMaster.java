package com.bmb.app.view.master;


import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.ToolbarSmallRLTED;
import org.bmb.app.base.komponen.TreeHakAkses;

import com.bmb.app.config.DataUser;
import com.bmb.app.impl.view.form.GrpComponetEdit;
import com.bmb.app.impl.view.form.GrpComponetNew;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterHakAkses;
import com.bmb.app.view.table.GrpTable;

public class GrpMaster extends MasterAbstract {

	public GrpMaster() {
		super();
		lebar=0.35;
		title=LMasterHakAkses.title;
		urlIcon=L.iconHakAkses16;
		viewForm=new TreeHakAkses();
		table = new GrpTable();
	}
	
	public void setEditForm() {
		setEditForm(new GrpComponetEdit());
	}

	public void setForm() {
		setForm(new GrpComponetNew());
		
	}
	
//	public void changeHakAkses() {
//		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
//		toolBar.getAdd().setEnabled(getAdd());
//		toolBar.getEdit().setEnabled(getEdit());
//		toolBar.getDel().setEnabled(getHapus());
//	}

	public boolean getAdd() {
		return DataUser.HAK_AKSES_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.HAK_AKSES_HAPUS;
	}

	public boolean getLihat() {
		return DataUser.HAK_AKSES_VIEW;
	}

	public boolean getEdit() {
		return DataUser.HAK_AKSES_EDIT;
	}

}
