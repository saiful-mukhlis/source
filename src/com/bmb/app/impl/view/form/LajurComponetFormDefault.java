package com.bmb.app.impl.view.form;

import org.bmb.app.base.abstrak.ComponetFormAbstract;
import org.bmb.app.base.builder.FormBuilder;
import org.bmb.app.base.komponen.ScrollPane;
import org.bmb.app.base.komponen.TextArea;
import org.bmb.app.base.komponen.TextField;

import com.bmb.app.dao.LajurDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane aNote;
	
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.11;
		dao=App.getLajurDao();
		initComponent(db);
	}
	
	public void resetContentComponent(){
		code.setText("");
		name.setText("");
		note.setText("");
	}
	
	
	
	public void setColorView(){
		code.setBackground(App.whiteSmoke);
		name.setBackground(App.whiteSmoke);
		note.setBackground(App.whiteSmoke);
		aNote.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		code.setEditable(isEdit);
		name.setEditable(isEdit);
		note.setEditable(isEdit);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 code.setText(model.field(LajurDao.code)+"");
			 name.setText(model.field(LajurDao.name)+"");
			 note.setText(model.field(LajurDao.note)+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		code=new TextField();
		name=new TextField();
		note=new TextArea();
		aNote=new ScrollPane(note);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g(.4),  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,  f:40dlu:g ,3dlu,   p,3dlu");

		 layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append(  LajurDao.fcode, code, 1, 1, 5);
		builder.append( LajurDao.fname, name, 1, 3, 5);
		builder.append( LajurDao.fnote, aNote , 1, 5, 5);
		
	}


	
	
	
	
	//untuk new
}
