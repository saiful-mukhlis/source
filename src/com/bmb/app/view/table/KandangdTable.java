package com.bmb.app.view.table;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.bmb.app.base.abstrak.TableAbstract;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.KandangTableModel;
import com.bmb.app.view.table.model.KandangdTableModel;
import com.bmb.app.view.table.model.LajurdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new KandangdTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		tableModel.editObjModel(model);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		tableModel.editObjModel(model);
	}

	
	
	public void initTable() {
		setTable(new JXTable(tableModel));
		if (getTable() instanceof JXTable) {
			setJXTable((JXTable) getTable());
			
//			JXTable t=(JXTable) getTable();
////			for (int i = 0; i < t.getColumnCount(); i++) {
////				t.getColumnExt(i).setVisible(false);
////				App.info("asd"+i);
////			}
//			while (t.getColumnCount()>2) {
//				t.getColumnExt(t.getColumnCount()-1).setVisible(false);
//				
//			}
//			t.getColumnExt(0).setVisible(false);
			
			//t.getColumnExt(1).setVisible(true);
			setSimple();
		}
		
		if (master!=null) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								setShowAll();
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}
						}else{
							setSimple();
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								setShowAll();
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}
						}else{
							setSimple();
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
		}
	}

	public void setSimple(){
		if (getTable() instanceof JXTable) {
			JXTable t=(JXTable) getTable();
			String [] x=tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx=t.getColumnExt(string);
				if (tcx!=null) {
					tcx.setVisible(false);
				}
			}
			
			TableColumnExt tcx=t.getColumnExt(x[1]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
			
			
			t.setColumnControlVisible(false);
		}
	}
	public void setShowAll(){
		if (getTable() instanceof JXTable) {
			JXTable t=(JXTable) getTable();
			String [] x=tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx=t.getColumnExt(string);
				if (tcx!=null) {
					tcx.setVisible(true);
				}
			}
			t.setColumnControlVisible(true);
		}
	}


}
