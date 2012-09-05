package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.base.abstrak.TableModelAbstract;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpTableModel  extends TableModelAbstract{

	public GrpTableModel(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	protected final int NOTE = 3;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[4];
		namaKolom[NO]="No";
		namaKolom[CODE]=GrpDao.fcode;
		namaKolom[NAMA]=GrpDao.fname;
		namaKolom[NOTE]=GrpDao.fnote;
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(15);
			t.getColumn(CODE).setPreferredWidth(15);
			t.getColumn(NAMA).setPreferredWidth(80);
			t.getColumn(NOTE).setPreferredWidth(80);
			}
		
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
		} else if (columnIndex == CODE) {
			return m.field(KandangDao.code);
		} else if (columnIndex == NAMA) {
			return m.field(KandangDao.name);
		} else if (columnIndex == NOTE) {
			return m.field(KandangDao.note);
		}  else {
			return null;
		}
	}
	



	@Override
	public void editObjModel(ODocument model) {
	}





	@Override
	public void initDao() {
		dao=App.getGrpDao();
	}
	@Override
	public List getModel2() {
		return null;
	}

}
