package org.bmb.app.base.komponen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.bmb.app.base.adapter.FormAdapter;
import org.bmb.app.base.adapter.MasterAdapterForEfectWidget;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.model.HakAksesModel;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TreeHakAkses implements FormAdapter{
	public final static int WIDGET_1=0;
	public final static int WIDGET_2=1;
	public final static int WIDGET_3=2;
	protected MasterAdapterForEfectWidget master;
	
	protected int typeEfectWidget=WIDGET_2;
	
	protected JPanel panel;
	protected JXTreeTable treeTable;
	
	
	
	private ODocument group=null;
	
	protected HakAksesModel model;
	
	

	
	public void setLayout(){
		JScrollPane ss=new JScrollPane(treeTable);
		ss.setBorder(App.borderWhite);
		panel.add(ss, BorderLayout.CENTER);
	}
	public void buildTable(){
		treeTable=new JXTreeTable(model);
		
		treeTable.setHorizontalScrollEnabled(true);
		treeTable.setColumnControlVisible(true);
		treeTable.setHighlighters(HighlighterFactory.createSimpleStriping());
		 
		treeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		treeTable.setSelectionBackground(new Color(135, 206, 250));
		treeTable.expandAll();
		
		treeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (master.isPerspectiveDefault()) {
						if (typeEfectWidget==WIDGET_1) {
							master.perspective1();
						}else if(typeEfectWidget==WIDGET_2){
							master.perspective2();
						}else if (typeEfectWidget==WIDGET_3) {
							master.perspective3();
						}
					}else{
						master.perspectiveDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		treeTable.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (master.isPerspectiveDefault()) {
						if (typeEfectWidget==WIDGET_1) {
							master.perspective1();
						}else if(typeEfectWidget==WIDGET_2){
							master.perspective2();
						}else if (typeEfectWidget==WIDGET_3) {
							master.perspective3();
						}
					}else{
						master.perspectiveDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
	}
	
	


	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void load(ODocument object) {
		if (object==null || object.field("@class").equals(App.getGrpDao().getClassName())) {
//			panel.removeAll();
//			model=new HakAksesModel(object);
			model.setGroup(object);
//			buildTable();
//			setLayout();
//			panel.validate();
//			panel.repaint();
		}
		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		initComponent(db);
		buildTable();
		setLayout();
		
	}



	@Override
	public void modelWidgetChange(ODocument model) {
		load(model);
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	public void buildForm(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	public void initComponent(ODatabaseDocumentTx db) {
		panel=new JPanel();
		panel.setLayout(new BorderLayout());
		
		//Groupp g=daoGroup.getById((long) 1);
		model=new HakAksesModel(group);
		
	}
//	@Override
//	public Component getPanelForm() {
//		return treeTable;
//	}
//	@Override
//	public Component getLabelTitle() {
//		return treeTable.getTableHeader();
//	}
	@Override
	public void actionReset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addWidgetModel(WidgetAdapter table) {
		// TODO Auto-generated method stub
		
	}
	public MasterAdapterForEfectWidget getMaster() {
		return master;
	}
	public void setMaster(MasterAdapterForEfectWidget master) {
		this.master = master;
	}
	public int getTypeEfectWidget() {
		return typeEfectWidget;
	}
	public void setTypeEfectWidget(int typeEfectWidget) {
		this.typeEfectWidget = typeEfectWidget;
	}
	@Override
	public void requestDefaultFocus() {
		// TODO Auto-generated method stub
		
	}
	
}
