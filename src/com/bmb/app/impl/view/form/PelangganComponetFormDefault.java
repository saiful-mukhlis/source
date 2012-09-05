package com.bmb.app.impl.view.form;



import org.bmb.app.base.abstrak.ComponetFormAbstract;
import org.bmb.app.base.builder.FormBuilder;
import org.bmb.app.base.komponen.ScrollPane;
import org.bmb.app.base.komponen.TextArea;
import org.bmb.app.base.komponen.TextField;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField code;
	protected TextField name;
	protected TextField pemilik;
	protected TextField notelp;
	protected TextArea alamat;
	protected ScrollPane sAlamat;
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.36;
		dao=App.getPelangganDao();
		initComponent(db);
	}
	
	public void resetContentComponent(){
		code.setText("");
		name.setText("");
		pemilik.setText("");
		notelp.setText("");
		alamat.setText("");
	}
	
	
	
	public void setColorView(){
		code.setBackground(App.whiteSmoke);
		name.setBackground(App.whiteSmoke);
		pemilik.setBackground(App.whiteSmoke);
		notelp.setBackground(App.whiteSmoke);
		alamat.setBackground(App.whiteSmoke);
		sAlamat.setBackground(App.whiteSmoke);
//		Border line = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(App.gainsboro, 1, true),
//                BorderFactory.createEmptyBorder(5, 5, 5, 5));
//		code.setBorder(line);
//		name.setBorder(line);
//		pemilik.setBorder(line);
//		notelp.setBorder(line);
//		name.setBorder(BorderFactory.createm
	}
	
	public void setEditable(boolean isEdit){
		code.setEditable(false);
		name.setEditable(false);
		pemilik.setEditable(false);
		notelp.setEditable(false);
		alamat.setEditable(false);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 code.setText(model.field(PelangganDao.code)+"");
				name.setText(model.field(PelangganDao.name)+"");
				pemilik.setText(model.field(PelangganDao.pemilik)+"");
				notelp.setText(model.field(PelangganDao.notelp)+"");
				alamat.setText(model.field(PelangganDao.alamat)+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		code=new TextField();
		name=new TextField();
		pemilik=new TextField();
		notelp=new TextField();
		alamat=new TextArea();
		sAlamat=new ScrollPane(alamat);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  8px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   f:40dlu,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append( PelangganDao.fcode, code, 1, 1, 5);
		builder.append( PelangganDao.fname, name, 1, 3, 5);
		builder.append( PelangganDao.fpemilik, pemilik, 1, 5, 5);
		builder.append( PelangganDao.fnotelp, notelp, 1, 7, 5);
		builder.append( PelangganDao.falamat, sAlamat, 1, 9, 5);
		
		
	}


	
	
	
	
	//untuk new
}
