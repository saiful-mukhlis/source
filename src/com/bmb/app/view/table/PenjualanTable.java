package com.bmb.app.view.table;

import org.bmb.app.base.abstrak.TableAbstract;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;
import com.bmb.app.view.table.model.PenjualanTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new PenjualanTableModel(db);
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

	
	public void selectedValid(int i) {
		if (widgets.size() != 0) {
			for (WidgetAdapter view : widgets) {
				if (view!=null) {
					view.modelWidgetChange((ODocument) tableModel.getModel().get(
							table.convertRowIndexToModel(i)));
					view.modelWidgetChange((ODocument) tableModel.getModel2().get(
							table.convertRowIndexToModel(i)));
				}
			}
		}
	}

	public void initTable() {
		setTable(new JXTable(tableModel));
		if (getTable() instanceof JXTable) {
			setJXTable((JXTable) getTable());
			JXTable t=(JXTable) getTable();
			while (t.getColumnCount()>4) {
				t.getColumnExt(t.getColumnCount()-1).setVisible(false);
				
			}
			t.getColumnExt(0).setVisible(false);
			t.getColumnExt(0).setVisible(false);
			
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
			TableColumnExt tcx1=t.getColumnExt(x[0]);
			if (tcx1!=null) {
				tcx1.setVisible(true);
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
