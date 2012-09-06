package com.bmb.app.view.master;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;

import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.SplitPane;
import org.bmb.app.base.komponen.ToolbarSmallRLTED;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.KandangComponetEdit;
import com.bmb.app.impl.view.form.KandangComponetNew;
import com.bmb.app.impl.view.form.KandangComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LMasterKandang;
import com.bmb.app.view.table.KandangTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class KandangMaster extends MasterAbstract {
	
	public KandangMaster() {
		super();
		lebar=0.2;
		title=LMasterKandang.title;
		urlIcon=L.iconKandang16;
		viewForm=new KandangComponetView();//LajurViewForm();
		table = new KandangTable();
	}
	private LajurMaster lajurMaster;
	private SplitPane ao;

	public void perspectiveForm() {
		perspectiveDefault();
		ao.setDividerLocation(200);
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		
		ao=new SplitPane(JSplitPane.VERTICAL_SPLIT,
				viewForm.getPanel(), lajurMaster.getPanel());
		ao.setDividerLocation(200);
		splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), ao);

//		splitPane.setDividerSize(1);
//		
//
//		ao.setDividerSize(1);
//		ao.setBorder(App.borderWhite);
//
//		splitPane.setOneTouchExpandable(true);
//		splitPane.setBorder(App.borderWhite);
		
		perspectiveDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		lajurMaster=new LajurMaster();
		lajurMaster.build(db);
		table.addWidgetChange(lajurMaster.getTable());
		table.addWidgetChange(lajurMaster.getForm());
		table.addWidgetChange(lajurMaster);
		setViewForm(new KandangComponetView());
	}
	
	public void actionAdd() {
		if (form==null) {
			setForm();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			form.build(db);
			form.addWidgetModel(table);
			db.close();
		}
		ao.setTopComponent(form.getPanel());
		perspectiveForm();
		getForm().actionReset();
	}

	public void actionView() {
		perspectiveForm();
		ao.setTopComponent(viewForm.getPanel());
	}

	public void actionEdit() {
		if (editForm==null) {
			setEditForm();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			editForm.build(db);
			db.close();
			table.addWidgetChange(getEditForm());
			editForm.addWidgetModel(table);
			editForm.addWidgetModel(this);
			table.selected();
		}
		perspectiveForm();
		ao.setTopComponent(editForm.getPanel());
	}



	@Override
	public void setEditForm() {
		setEditForm(new KandangComponetEdit());
	}



	@Override
	public void setForm() {
		setForm(new KandangComponetNew());
		
	}
	
	
	public void changeHakAkses() {
		lajurMaster.changeHakAkses();
		super.changeHakAkses();
//		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
//		toolBar.getAdd().setEnabled(getAdd());
//		toolBar.getEdit().setEnabled(getEdit());
//		toolBar.getDel().setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.KANDANG_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.KANDANG_DEL;
	}

	public boolean getLihat() {
		return DataUser.KANDANG_VIEW;
	}

	public boolean getEdit() {
		return DataUser.KANDANG_EDIT;
	}

}
