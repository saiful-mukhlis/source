package com.bmb.app.view.master;


import org.bmb.app.base.abstrak.MasterAbstract4;

import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.PenjualanComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.print.PenjualanPrint;
import com.bmb.app.view.table.PenjualanTable;
import com.bmb.app.view.table.PenjualanbTable;
import com.bmb.app.view.table.PenjualanhTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanMaster extends MasterAbstract4  {

	public PenjualanMaster() {
	}

	public void initComponent() {
		urlIcon = L.iconPenjualan16;
		title = "Laporan Penjualan";
		table1 = new PenjualanbTable();
		table2 = new PenjualanhTable();
		table3 = new PenjualanTable();
		viewForm =new PenjualanComponetView();
	}

	private ODocument penjualanb;
	private ODocument penjualanh;

	@Override
	public void modelWidgetChange(ODocument model) {
		if (model == null) {
			penjualanb = null;
			penjualanh = null;
			print.setEnabled(false);
		} else {
			if (model.field("@class").equals(
					App.getPenjualanbDao().getClassName())) {
				penjualanb = model;
				penjualanh = null;
				print.setEnabled(true);
			} else if (model.field("@class").equals(
					App.getPenjualanhDao().getClassName())) {
				penjualanh = model;
				penjualanb = null;
				print.setEnabled(true);
			}
		}

	}

	public void actionPrint() {
		if (penjualanb!=null) {
			PenjualanPrint p=new PenjualanPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, penjualanb);
			db.close();
		}else 
		if (penjualanh!=null) {
			PenjualanPrint p=new PenjualanPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, penjualanh);
			db.close();
		}
	}

}
