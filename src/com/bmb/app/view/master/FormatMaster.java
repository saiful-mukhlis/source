package com.bmb.app.view.master;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.ToolbarSmallRLE;
import org.jdesktop.swingx.JXTable;

import com.bmb.app.config.DataUser;
import com.bmb.app.impl.view.form.FormatComponetEdit;
import com.bmb.app.impl.view.form.FormatComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LFormatLaporan;
import com.bmb.app.view.table.FormatTable;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class FormatMaster extends MasterAbstract {

	public FormatMaster() {
		super();
		lebar=0.35;
		title=LFormatLaporan.title;
		urlIcon=L.iconLap16;
		viewForm=new FormatComponetView();
		table = new FormatTable();
	}


	@Override
	public void setEditForm() {
		editForm=new FormatComponetEdit();
	}

	@Override
	public void setForm() {}
	
//	public void changeHakAkses() {
//		ToolbarSmallRLE toolBar=(ToolbarSmallRLE) this.toolBar;
//		toolBar.getEdit().setEnabled(getEdit());
//	}

	

	public boolean getLihat() {
		return DataUser.FORMAT_VIEW;
	}

	public boolean getEdit() {
		return DataUser.FORMAT_EDIT;
	}
	
	
	
	
	
	
	
	
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		
		table.build(db);
		((JXTable)table.getTable()).setColumnControlVisible(false);
		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
						perspective1();
					}else{
						perspectiveDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		table.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
						perspective1();
					}else{
						perspectiveDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		toolBar= new ToolbarSmallRLE(this);
		
//		initLabelTitle(db);
//		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
//		reload = new JButton(App.getIcon(L.iconReload16));
//		edit = new JButton(App.getIcon(L.iconEdit16));
//		lihat = new JButton(App.getIcon(L.iconView16));

//		showTable = new JButton(App.getIcon(db, "icon 1w 16"));
//		showForm = new JButton(App.getIcon(db, "icon 2l 16"));
		
//		reload.setBackground(Color.WHITE);
//		edit.setBackground(Color.WHITE);
//		lihat.setBackground(Color.WHITE);
//		showTable.setBackground(Color.WHITE);
//		showForm.setBackground(Color.WHITE);
		
	}
	
//	public void buildAction(ODatabaseDocumentTx db){
//		FormLayout layout = new FormLayout(
//				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,   	"
//						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",
//
//				"p,3dlu");
//
//		toolBar = new JToolBar();
//		toolBar.setLayout(layout);
//		toolBar.setBackground(Color.WHITE);
//		CellConstraints cc = new CellConstraints();
//		toolBar.add(label, cc.xy(2, 1));
////		toolBar.add(showTable, cc.xy(6, 1));
////		toolBar.add(showForm, cc.xy(8, 1));
//		toolBar.add(reload, cc.xy(12, 1));
//		toolBar.add(lihat, cc.xy(14, 1));
//		toolBar.add(edit, cc.xy(16, 1));
//	}
	
//	public void buildAksiListener(){
//		showTable.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				if (splitPane.getDividerLocation()!=getDevide()) {
//					tampilkanDefault();
//				}else{
//					splitPane.setDividerLocation(1.0);
//				}
//
//			}
//		});
//		showForm.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				if (splitPane.getDividerLocation()==getDevide()) {
//					splitPane.setDividerLocation(0.0);
//				}else{
//					tampilkanForm();
//				}
//			}
//		});


//		edit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				actionEdit();
//			}
//		});
//
//		lihat.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				actionView();
//			}
//		});
//		
//		reload.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				actionReload();
//			}
//		});
//		
//		
//		edit.setEnabled(false);
//		lihat.setEnabled(false);
//	}
	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		actionView();
		ToolbarSmallRLE toolBar=(ToolbarSmallRLE) this.toolBar;
		if (model==null) {
			toolBar.getEdit().setEnabled(false);
			toolBar.getView().setEnabled(false);
		}else{
			toolBar.getEdit().setEnabled(getEdit());
			toolBar.getView().setEnabled(getLihat());
			viewForm.modelWidgetChange(model);
		}

	}




}
