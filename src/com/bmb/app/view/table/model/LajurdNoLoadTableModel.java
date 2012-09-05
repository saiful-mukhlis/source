package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.base.abstrak.TableModelAbstract;

import com.bmb.app.dao.LajurdDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdNoLoadTableModel  extends TableModelAbstract{

	public LajurdNoLoadTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	
//	private ODocument lajur;
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		model=new ArrayList<ODocument>();
//		if (lajur!=null) {
//			if (paging != null) {
//				int tmp=paging.getCurentHalaman()
//						* paging.getJumlahPerHalaman();
//				if (tmp<0) {
//					tmp=0;
//				}
//				model = getDao().getAllByColumn(db, LajurdDao.lajur, lajur.getIdentity(), tmp
//						,
//						paging.getJumlahPerHalaman());
//			} else {
//				model = (List<ODocument>) getDao().getAllByColumn(db, LajurdDao.lajur, lajur.getIdentity());
//			}
//		}else{
//			model=new ArrayList<ODocument>();
//		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			paging.getPanelPaging().setVisible(false);
		}
//		if (paging != null) {
//			if (lajur!=null) {
//				paging.setJumlahData(dao.getCountByColumn(db, LajurdDao.lajur, lajur.getIdentity()));
//			}else{
//				paging.setJumlahData((long) 0);
//			}
//				if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
//					paging.getPanelPaging().setVisible(true);
//				} else {
//					paging.getPanelPaging().setVisible(false);
//				}
//			
//		}
		
	}

	protected final int NO = 0;
	protected final int TGL = 1;
	protected final int POPULASI = 2;
	protected final int MATI = 3;
	protected final int PB = 4;
	protected final int PR = 5;
	protected final int SB = 6;
	protected final int SR = 7;
	protected final int TB = 8;
	protected final int TR = 9;
	
	protected final int KPB = 10;
	protected final int KPR = 11;
	protected final int KSB = 12;
	protected final int KSR = 13;
	protected final int KTB = 14;
	protected final int KTR = 15;
	
	protected final int HD = 16;
	protected final int P = 17;
	
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[18];
		namaKolom[NO]= "No";
		namaKolom[TGL]= LajurdDao.ftgl;
		namaKolom[POPULASI]= LajurdDao.fpupulasi;
		namaKolom[MATI]= LajurdDao.fmati;
		namaKolom[PB]= LajurdDao.fbutirpagibagus;
		namaKolom[PR]= LajurdDao.fbutirpagiretak;
		namaKolom[SB]= LajurdDao.fbutirsorebagus;
		namaKolom[SR]= LajurdDao.fbutirsoreretak;
		namaKolom[TB]= LajurdDao.fbagus;
		namaKolom[TR]= LajurdDao.fretak;
		
		namaKolom[KPB]= LajurdDao.fkgpagibagus;
		namaKolom[KPR]= LajurdDao.fkgpagiretak;
		namaKolom[KSB]= LajurdDao.fkgsorebagus;
		namaKolom[KSR]= LajurdDao.fkgsoreretak;
		namaKolom[KTB]= LajurdDao.fbagus;
		namaKolom[KTR]= LajurdDao.fretak;
		namaKolom[HD]= LajurdDao.fhd;
		namaKolom[P]= LajurdDao.fp;
	}
	@Override
	public void setDefaultLebar(JTable table) {
//		if (table!=null) {
//			TableColumnModel t=table.getColumnModel();
//			t.getColumn(NO).setPreferredWidth(27);
//			}
		
	}
	public void load(ODatabaseDocumentTx db) {
		loadJumlahData(db);
		loadDataModel(db);
		super.load(db);
	}
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
			Date tmp=m.field(LajurdDao.tgl);
			if (tmp!=null) {
				return App.dateFormat.format(tmp);
			}
			return null;
		} else if (columnIndex == POPULASI) {
			return m.field(LajurdDao.pupulasi);
		}  else if (columnIndex == MATI) {
			return m.field(LajurdDao.mati);
		}  else if (columnIndex == PB) {
			return m.field(LajurdDao.butirpagibagus);
		}  else if (columnIndex == PR) {
			return m.field(LajurdDao.butirpagiretak);
		} else if (columnIndex == SB) {
			return m.field(LajurdDao.butirsorebagus);
		}  else if (columnIndex == SR) {
			return m.field(LajurdDao.butirsoreretak);
		}  else if (columnIndex == TB) {
			return App.getLajurdDao().getTotalButirBagus(m);
		}  else if (columnIndex == TR) {
			return App.getLajurdDao().getTotalButirRetak(m);
		}  else if (columnIndex == KPB) {
			return m.field(LajurdDao.kgpagibagus);
		}  else if (columnIndex == KPR) {
			return m.field(LajurdDao.kgpagiretak);
		}  else if (columnIndex == KSB) {
			return m.field(LajurdDao.kgsorebagus);
		}  else if (columnIndex == KSR) {
			return m.field(LajurdDao.kgsoreretak);
		}  else if (columnIndex == KTB) {
			return App.getLajurdDao().getTotalKgBagus(m);
		}  else if (columnIndex == KTR) {
			return App.getLajurdDao().getTotalKgRetak(m);
		}  else if (columnIndex == HD ) {
			return App.getLajurdDao().getHD(m);
		}  else if (columnIndex == P) {
			return App.getLajurdDao().getP(m);
		}    else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
//		setLajur(model);
		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		reload(db);
		db.close();
	}
	public void reload(ODatabaseDocumentTx db) {
//		loadDataModel(db);
//		loadJumlahData(db);
//		fireTableStructureChanged();
		fireTableDataChanged();
		setDefaultLebar(getTable().getTable());
		getTable().selected();
	}




	@Override
	public void initDao() {
		dao=App.getLajurdDao();
	}
	@Override
	public List getModel2() {
		return null;
	}
//	public ODocument getLajur() {
//		return lajur;
//	}
//	public void setLajur(ODocument lajur) {
//		if (lajur.field("@class").equals(App.getLajurDao().getClassName())) {
//			this.lajur = lajur;
//		}
//	}

}
