//package com.bmb.app.impl.view.form;
//
//import java.awt.Component;
//
//import javax.swing.JPanel;
//
//import org.bmb.app.base.adapter.ComponetEditAdapter;
//import org.bmb.app.base.adapter.FormAdapter;
//import org.bmb.app.base.adapter.WidgetAdapter;
//
//import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
//import com.orientechnologies.orient.core.record.impl.ODocument;
//
//public class ComponentEdit implements FormAdapter{
//	private ComponetEditAdapter edit;
//
//	public ComponentEdit(ComponetEditAdapter edit) {
//		super();
//		this.edit = edit;
//	}
//
//	@Override
//	public void build(ODatabaseDocumentTx db) {
//		edit.init(db);
//	}
//
//	@Override
//	public void load(ODocument model) {
//		edit.load(model);
//		
//	}
//
//	@Override
//	public JPanel getPanel() {
//		return edit.getPanel();
//	}
//
//	@Override
//	public void modelWidgetChange(ODocument model) {
//		edit.load(model);
//	}
//
//	@Override
//	public void modelWidgetAdd(ODocument model) {
//	}
//
//
//
//	@Override
//	public void addWidgetModel(WidgetAdapter e) {
//		edit.addWidgetModel(e);
//		
//	}
//
//	@Override
//	public Component getPanelForm() {
//		// TODO Auto-generated method stub
//		return edit.getPanelForm();
//	}
//
//	@Override
//	public Component getLabelTitle() {
//		// TODO Auto-generated method stub
//		return edit.getLabelTitle();
//	}
//
//	public ComponetEditAdapter getEdit() {
//		return edit;
//	}
//
//	public void setEdit(ComponetEditAdapter edit) {
//		this.edit = edit;
//	}
//
//	@Override
//	public void aksiReset() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	
//	
//
//	
//
//	
//	
//
//}
