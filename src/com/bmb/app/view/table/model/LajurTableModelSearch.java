package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.base.abstrak.TableModelAbstract;

import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurTableModelSearch  extends TableModelAbstract{

	public LajurTableModelSearch(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}
	protected final int CODE = 0;
	protected final int NAME = 1;
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAME).setPreferredWidth(200);
			}
		
	}
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[2];
		namaKolom[CODE]="Kode";
		namaKolom[NAME]="Lajur";
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
		dao=App.getLajurDao();
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
			model = (List<ODocument>)((LajurDao)getDao()).getAllByColumnLike(db, LajurDao.name+".toLowerCase()",
					textSearch+"%", LajurDao.code+".toLowerCase()",
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
