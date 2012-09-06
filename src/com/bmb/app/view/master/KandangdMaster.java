package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.base.abstrak.TableAbstract;
import org.bmb.app.base.adapter.FormAdapter;
import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.MasterActionAdapter;
import org.bmb.app.base.adapter.MasterAdapterForEfectWidget;
import org.bmb.app.base.adapter.TableAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.komponen.SplitPane;
import org.bmb.app.base.komponen.ToolbarSmallRP;
import org.jdesktop.swingx.JXTable;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.KandangdComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LProduksiKandang;
import com.bmb.app.print.KandangdPrint;
import com.bmb.app.view.table.KandangTable;
import com.bmb.app.view.table.KandangdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdMaster implements MasterActionAdapter, HakAksesListener, MasterAdapterForEfectWidget {

	protected JPanel panel;
	protected JLabel label;
	protected String title;
	protected String urlIcon;
	
	protected TableAdapter table1;
	protected TableAdapter table2;
	protected FormAdapter viewForm;
	protected SplitPane splitPane;
	protected SplitPane splitPane1;
	
	protected boolean perspectiveDefault=true;
	
//	protected JButton show1w;
//	protected JButton show2w;
	
//	protected JButton reload;
//	protected JButton print;
	
	protected JPanel aksi;

	protected ToolbarSmallRP toolBar;
	
	public void init(ODatabaseDocumentTx db){
		urlIcon=L.iconKandang16;
		title=LProduksiKandang.title;
		table1=new KandangTable();
		table2=new KandangdTable();
		viewForm=new KandangdComponetView();
		
		table1.setTypeEfectWidget(TableAbstract.WIDGET_1);
		table2.setTypeEfectWidget(TableAbstract.WIDGET_2);
		viewForm.setTypeEfectWidget(TableAbstract.WIDGET_3);
		
		table1.build(db);
		table2.build(db);
		viewForm.build(db);
		
//		((JXTable)table1.getTable()).setColumnControlVisible(false);
		
//		table1.getTable().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						perspective1();
//					}else{
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		table1.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						perspective1();
//					}else{
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		
//		table2.getTable().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						KandangdTable t=(KandangdTable) table2;
//						t.setShowAll();
//						perspective2();
//					}else{
//						KandangdTable t=(KandangdTable) table2;
//						t.setSimple();
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		table2.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						KandangdTable t=(KandangdTable) table2;
//						t.setShowAll();
//						perspective2();
//					}else{
//						KandangdTable t=(KandangdTable) table2;
//						t.setSimple();
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
		
		
		
//		viewForm.getPanelForm().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (table1.getPanel().isVisible()) {
//						perspective3();
//					}else{
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		
//		viewForm.getLabelTitle().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (table1.getPanel().isVisible()) {
//						perspective3();
//					}else{
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
		
		
		
		
		table1.addWidgetChange(table2);
		table1.addWidgetChange(this);
		table1.addWidgetChange(viewForm);
		table2.addWidgetChange(viewForm);
		
		toolBar=new ToolbarSmallRP(this);
		
//		reload = new JButton(App.getIcon(L.iconReload16));
////		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
////		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
//		print = new JButton(App.getIcon(L.iconPrint16));
//		label = new JLabel(App.getIcon(urlIcon));
//		label.setText(title);
//		
//		reload.setBackground(Color.WHITE);
////		show1w.setBackground(Color.WHITE);
////		show2w.setBackground(Color.WHITE);
//		
//		reload.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				aksiReload();
//			}
//		});
//		
//		print.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				aksiPrint();
//			}
//		});
//		
		table2.getPanel().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		table1.getPanel().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		table2.getTable().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		table1.getTable().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		viewForm.getPanel().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		
		
		splitPane1 = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table2.getPanel(), viewForm.getPanel());
//		splitPane1.setDividerSize(1);
		//splitPane1.setOneTouchExpandable(true);
		
		
		splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table1.getPanel(), splitPane1);
//		splitPane.setDividerSize(0);
		//splitPane.setOneTouchExpandable(true);
		
//		Double d = App.getW()*0.3;
//		Double d1 = App.getW()*0.3;
		
//		splitPane.setDividerSize(1);
//		splitPane1.setDividerSize(1);
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		
//		splitPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//		splitPane1.setBorder(BorderFactory.createLineBorder(Color.WHITE));

//		buildAksi(db);
		
		panel=new JPanel(new BorderLayout());
		
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
		
		
	}
	
	Double d = App.getW()*0.3;
	Double d1 = App.getW()*0.3;
	
	public void actionPrint() {
		if (kandang!=null) {
			KandangdPrint p=new KandangdPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, kandang);
			db.close();
		}
		
	}
	
	public void actionReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table1.reload(db);
		db.close();
		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	private ODocument kandang;
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model==null) {
			kandang=null;
		}else{
			if (model.field("@class").equals(App.getKandangDao().getClassName())) {
				kandang=model;
			}
		}
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	
//	public void buildAksi(ODatabaseDocumentTx db){
//		FormLayout layout = new FormLayout(
//				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu, p,  4dlu,   	p:g,  4dlu,   	"
//						+ "p,     4dlu,p,     4dlu",
//
//				"p,3dlu");
//
//		toolBar = new JToolBar();
//		toolBar.setLayout(layout);
//		toolBar.setBackground(Color.WHITE);
//		CellConstraints cc = new CellConstraints();
//		toolBar.add(label, cc.xy(2, 1));
////		toolBar.add(show1w, cc.xy(6, 1));
////		toolBar.add(show2w, cc.xy(8, 1));
////		toolBar.add(show3w, cc.xy(10, 1));
//		toolBar.add(reload, cc.xy(14, 1));
//		toolBar.add(print, cc.xy(16, 1));
//	}
	
	
	
	
	public void changeHakAkses() {
		toolBar.getPrint().setEnabled(getPrint());
	}

	public boolean getPrint() {
		return DataUser.KANDANGD_PRINT;
	}
	
	
	
	public void perspectiveDefault() {
		
		splitPane1.setLeftComponent(table2.getPanel());
		splitPane1.setRightComponent(viewForm.getPanel());
		
		splitPane.setLeftComponent(table1.getPanel());
		splitPane.setRightComponent(splitPane1);
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		
		
	}
	public void perspective1() {
		
		splitPane.setLeftComponent(table1.getPanel());
		
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(1.0);
	}
	public void perspective2() {
		splitPane.setLeftComponent(table2.getPanel());
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(1.0);
	}

	public void perspective3() {
		splitPane.setLeftComponent(viewForm.getPanel());
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(0.0);
	}

	public boolean isPerspectiveDefault() {
		return perspectiveDefault;
	}

	public void setPerspectiveDefault(boolean perspectiveDefault) {
		this.perspectiveDefault = perspectiveDefault;
	}

	@Override
	public void actionAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionEdit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionDel() {
		// TODO Auto-generated method stub
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	@Override
	public void perspective4() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getAdd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getHapus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getLihat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getEdit() {
		// TODO Auto-generated method stub
		return false;
	}





}
