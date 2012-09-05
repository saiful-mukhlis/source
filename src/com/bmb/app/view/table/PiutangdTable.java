package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.base.abstrak.TableAbstract;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.PiutangdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangdTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new PiutangdTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		tableModel.editObjModel(model);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		tableModel.editObjModel(model);
		tableModel.addObjModel(model);
	}





}
