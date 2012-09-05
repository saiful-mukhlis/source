package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.base.abstrak.TableModelAbstract;

import com.bmb.app.dao.KandangdDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangallTableModel  extends TableModelAbstract{

	public KandangallTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	
	

	protected final int NO = 0;
	protected final int TGL = 1;
	protected final int UMUR = 2;
	protected final int POPULASI = 3;
	protected final int MATI = 4;
	protected final int PAKAN = 5;
	protected final int BB = 6;
	protected final int BK = 7;
	protected final int RB = 8;
	protected final int RK = 9;
	protected final int HD = 10;
	protected final int P = 11;
	
	protected final int FCR = 12;
	protected final int K1 = 13;
	protected final int A = 14;
	protected final int K2 = 15;
	
	
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[16];
		namaKolom[NO]= "No";
		namaKolom[TGL]= KandangdDao.ftgl;
		namaKolom[UMUR]= KandangdDao.fumur;
		namaKolom[POPULASI]= KandangdDao.fpupulasi;
		namaKolom[MATI]= KandangdDao.fmati;
		namaKolom[PAKAN]= KandangdDao.fpakan;
		namaKolom[BB]= KandangdDao.fbutirb;
		namaKolom[BK]= KandangdDao.fkgb;
		namaKolom[RB]= KandangdDao.fbutir;
		namaKolom[RK]= KandangdDao.fkg;
		namaKolom[HD]= KandangdDao.fhd;
		namaKolom[P]= KandangdDao.fp;
		namaKolom[FCR]= KandangdDao.ffcr;
		namaKolom[K1]= KandangdDao.fket;
		namaKolom[A]= KandangdDao.fa;
		namaKolom[K2]= KandangdDao.fket2;
	}
	@Override
	public void setDefaultLebar(JTable table) {
//		if (table!=null) {
//			TableColumnModel t=table.getColumnModel();
//			t.getColumn(NO).setPreferredWidth(27);
//			}
		
	}
//	public void load(ODatabaseDocumentTx db) {
//		loadJumlahData(db);
//		loadDataModel(db);
//		super.load(db);
//	}
	
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		if (columnIndex == NO) {
			int no = rowIndex + 1;
			if (paging != null) {
					no += ((paging.getCurentHalaman()-1) * paging.getJumlahPerHalaman());
			}
			return no;
		} else if (columnIndex == 	TGL) {
			Date dtmp=m.field(KandangdDao.tgl);
			if (dtmp==null) {
				return null;
			}else{
				return App.dateFormat.format(dtmp);
			}
		} else if (columnIndex == UMUR) {
			return m.field(KandangdDao.umur);
		}  else if (columnIndex == POPULASI) {
			return m.field(KandangdDao.pupulasi);
		}  else if (columnIndex == MATI) {
			return m.field(KandangdDao.mati);
		}  else if (columnIndex == PAKAN) {
			Double ddtmp=m.field(KandangdDao.pakan);
			if (ddtmp!=null) {
				return App.paymentFormat2.format(ddtmp);
			}
			return null;
		}  else if (columnIndex == BB) {
			return m.field(KandangdDao.bagusbutir);
		}  else if (columnIndex == BK) {
			double dtmp=m.field(KandangdDao.baguskg);
			return App.paymentFormat2.format(dtmp);
		}  else if (columnIndex == RB) {
			return m.field(KandangdDao.retakbutir);
		}  else if (columnIndex == RK) {
			double dtmp=m.field(KandangdDao.retakkg);
			return App.paymentFormat2.format(dtmp);
		}  else if (columnIndex == HD) {
			return App.paymentFormat2.format(App.getKandangdDao().getHD(m));
		}  else if (columnIndex == P) {
			return App.paymentFormat2.format(App.getKandangdDao().getP(m));
		}  else if (columnIndex == FCR) {
			return App.paymentFormat2.format(App.getKandangdDao().getFcr(m));
		}  else if (columnIndex == K1) {
			return App.getKandangdDao().getKet1(m);
		}  else if (columnIndex == A) {
			return App.paymentFormat2.format(App.getKandangdDao().getA(m));
		}  else if (columnIndex == K2) {
			return App.getKandangdDao().getKet2(m);
		}   else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
//		setKandang(model);
//		ODatabaseDocumentTx db=App.getDbd();
//		ODatabaseRecordThreadLocal. INSTANCE.set(db);
//		reload(db);
//		db.close();
	}



	@Override
	public void initDao() {
		dao=App.getKandangallDao();
	}
	@Override
	public List getModel2() {
		return null;
	}


}
