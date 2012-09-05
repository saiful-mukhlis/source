package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.base.abstrak.TableModelAbstract;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganTableModelSearch  extends TableModelAbstract{

	public PelangganTableModelSearch(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}
	protected final int CODE = 0;
	protected final int NAME = 1;
//	protected final int NAME_PEMILIK = 3;
//	protected final int TELP = 4;
//	protected final int ADDRESS = 5;
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
//			t.getColumn(NO).setPreferredWidth(27);
			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAME).setPreferredWidth(200);
//			t.getColumn(NAME_PEMILIK).setPreferredWidth(100);
//			t.getColumn(TELP).setPreferredWidth(27);
//			t.getColumn(ADDRESS).setPreferredWidth(27);
			}
		
	}
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[2];
//		namaKolom[NO]=App.getT(db, "No");
		namaKolom[CODE]="Kode";
		namaKolom[NAME]="Nama Toko";
//		namaKolom[NAME_PEMILIK]=App.getT(db, "Nama Pemilik");
//		namaKolom[TELP]=App.getT(db, "No Telp");
//		namaKolom[ADDRESS]=App.getT(db, "Alamat");
	}
	public void load(ODatabaseDocumentTx db) {
		loadJumlahData(db);
		loadDataModel(db);
		super.load(db);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		if (columnIndex == CODE) {
			return m.field("code");
		} else if (columnIndex == NAME) {
			return m.field("name");
		}
//		else if (columnIndex == TELP) {
//			return m.field("notelp");
//		} else if (columnIndex == ADDRESS) {
//			return m.field("alamat");
//		} 
		else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
		// TODO Auto-generated method stub
		
	}








	@Override
	public void initDao() {
		dao=App.getPelangganDao();
	}
	@Override
	public List getModel2() {
		return null;
	}
	
	
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (textSearch==null || textSearch.equalsIgnoreCase("")) {
			model = (List<ODocument>) getDao().getAll(db, 0, 50);
		}else{
			model = (List<ODocument>)((PelangganDao)getDao()).getAllByColumnLike(db, PelangganDao.name+".toLowerCase()",
					textSearch+"%", PelangganDao.code+".toLowerCase()",
					textSearch, 0, 50);
			if (model.size()==0) {
				model = (List<ODocument>) getDao().getAll(db, 0, 50);
			}
		}
	}
	
	public String getTextSearch() {
		return textSearch;
	}
	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}
	private String textSearch;
	

}
