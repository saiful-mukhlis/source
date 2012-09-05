package com.bmb.app.view.master;

import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.ToolbarSmallRLTED;

import com.bmb.app.config.DataUser;
import com.bmb.app.impl.view.form.PelangganComponetEdit;
import com.bmb.app.impl.view.form.PelangganComponetNew;
import com.bmb.app.impl.view.form.PelangganComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterPelanggan;
import com.bmb.app.view.table.PelangganTable;

public class PelangganMaster extends MasterAbstract {

	public PelangganMaster() {
		super();
		lebar=0.35;
		title=LMasterPelanggan.title;
		urlIcon=L.iconCustomer16;
		viewForm=new PelangganComponetView();
		table = new PelangganTable();
	}


	@Override
	public void setEditForm() {
		setEditForm(new PelangganComponetEdit());
	}

	@Override
	public void setForm() {
		setForm(new PelangganComponetNew());
	}
	
	public void changeHakAkses() {
		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		toolBar.getAdd().setEnabled(getAdd());
		toolBar.getEdit().setEnabled(getEdit());
		toolBar.getDel().setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.PELANGGAN_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.PELANGGAN_DEL;
	}

	public boolean getLihat() {
		return DataUser.PELANGGAN_VIEW;
	}

	public boolean getEdit() {
		return DataUser.PELANGGAN_EDIT;
	}

}
