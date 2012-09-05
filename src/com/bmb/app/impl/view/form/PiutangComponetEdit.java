package com.bmb.app.impl.view.form;


import java.util.Date;

import org.bmb.app.base.adapter.ComponetEditAdapter;
import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.WidgetAdapter;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.PiutangDao;
import com.bmb.app.db.Piutangd;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class PiutangComponetEdit extends PiutangComponetFormDefault implements
		ComponetEditAdapter, HakAksesListener {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Pembayaran";
		icon = L.iconEdit16;
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		actionReset();
	}
	
	
	protected ODocument model;

	public void load(ODocument model) {
		setContentComponent(model);
	}

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 13);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(name, pemilik);
		setFocusEnter(pemilik, bayar);
		setFocusEnter(bayar, save);
		setFocusEnter(save, bayar);
	}
	
	protected double bayara=0;
	protected Date tgla;
	
	
	public boolean validate(ODatabaseDocumentTx db){
		if (model==null) {
			App.showErrorEmptySelect(null, "Pelanggan");
			return false;
		}
		try {
			bayara=Double.parseDouble(bayar.getText());
		} catch (Exception e) {
			bayara=0;
			bayar.setText("");
			App.showErrorFieldEmpty(db, "Jumlah");
			return false;
		}
		tgla=tgl.getDate();
		if (tgla==null) {
			tgl.setDate(new Date());
			App.showErrorFieldEmpty(db, "Tanggal");
			return false;
		}
		return true;
	}
	public void actionSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument piutangtmp=getPiutang();
			// bayar
			// piutang -
			// piutang d
			
			try{
				  db.begin(TXTYPE.OPTIMISTIC);
				  
				  double ksebelumnya=piutangtmp.field(PiutangDao.total);
					ksebelumnya=ksebelumnya-bayara;
					piutangtmp.field(PiutangDao.total, ksebelumnya, OType.DOUBLE);
					piutangtmp.save();
					
				  ODocument piutangdtmp=App.getPiutangdDao().factoryModel(tgla, piutangtmp, Piutangd.TYPE_PEMBAYARAN, 0 , bayara, ksebelumnya, "Pembayaran");
					piutangdtmp.save();
				  
					piutangtmp.save();
				  db.commit();
				  for (WidgetAdapter w: widgeds) {
						w.modelWidgetChange(piutangtmp);
						this.model=piutangtmp;
					}
				  App.showSaveOk();
				  resetContentComponent();
				}catch( Exception e ){
				  db.rollback();
				} finally{
					db.close();
				}
			
			
			
			
		}
	
		
	}

	@Override
	public void changeHakAkses() {
		bayar.setEnabled(DataUser.PEMBAYARAN_EDIT);
		tgl.setEnabled(DataUser.PEMBAYARAN_EDIT);
		save.setEnabled(DataUser.PEMBAYARAN_EDIT);
	}


	
}
