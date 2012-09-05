package com.bmb.app.view.table;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.bmb.app.base.abstrak.TableAbstract;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.bmb.app.view.table.model.LajurdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new LajurdTableModel(db);
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
		}
		
		if (master!=null) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
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
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
		}
		
		setSimple();
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
