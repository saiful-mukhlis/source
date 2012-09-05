package com.bmb.app.view.master;




import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.ToolbarSmallRLTED;

import com.bmb.app.config.DataUser;
import com.bmb.app.impl.view.form.UsrComponetEdit;
import com.bmb.app.impl.view.form.UsrComponetNew;
import com.bmb.app.impl.view.form.UsrComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterPegawai;
import com.bmb.app.view.table.UsrTable;

public class PegawaiMaster extends MasterAbstract {

	
	
	public PegawaiMaster() {
		super();
		lebar=0.35;
		title=LMasterPegawai.title;
		urlIcon=L.iconPegawai16;
		viewForm=new UsrComponetView();
		viewForm.setMaster(this);
		table = new UsrTable();
		
	}

	@Override
	public void setEditForm() {
		editForm=new UsrComponetEdit();
		editForm.setMaster(this);
	}

	@Override
	public void setForm() {
		form=new UsrComponetNew();
		form.setMaster(this);
	}

	public void changeHakAkses() {
		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		toolBar.getAdd().setEnabled(getAdd());
		toolBar.getEdit().setEnabled(getEdit());
		toolBar.getDel().setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.USR_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.USR_DEL;
	}

	public boolean getLihat() {
		return DataUser.USR_VIEW;
	}

	public boolean getEdit() {
		return DataUser.USR_EDIT;
	}
	

}
