package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import org.jdesktop.swingx.JXTable;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.LajurdComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LProduksiLajur;
import com.bmb.app.print.LajurdPrint;
import com.bmb.app.view.table.KandangTable;
import com.bmb.app.view.table.LajurTable;
import com.bmb.app.view.table.LajurdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
public class LajurdMaster implements MasterActionAdapter, HakAksesListener, MasterAdapterForEfectWidget {

	protected JPanel panel;
	protected JLabel label;
	protected String title;
	protected String urlIcon;
	protected boolean perspectiveDefault=true;
	
	protected TableAdapter table1;
	protected TableAdapter table2;
	protected TableAdapter table3;
	protected FormAdapter viewForm;
	protected JSplitPane splitPane;
	protected JSplitPane splitPane1;
	protected JSplitPane splitPane2;
	
	
	protected JButton reload;
	protected JButton print;

	protected JPanel aksi;

	protected JToolBar toolBar;
	
	public void init(ODatabaseDocumentTx db){
		urlIcon=L.iconLajur16;
		title=LProduksiLajur.title;
		table1=new KandangTable();
		table2=new LajurTable();
		table3=new LajurdTable();
		viewForm=new LajurdComponetView();
		
		table1.setMaster(this);
		table2.setMaster(this);
		table3.setMaster(this);
		viewForm.setMaster(this);
		
		table1.setTypeEfectWidget(TableAbstract.WIDGET_1);
		table2.setTypeEfectWidget(TableAbstract.WIDGET_2);
		table3.setTypeEfectWidget(TableAbstract.WIDGET_3);
		viewForm.setTypeEfectWidget(TableAbstract.WIDGET_4);
		
		table1.build(db);
		table2.build(db);
		table3.build(db);
		viewForm.build(db);
		
		((JXTable)table1.getTable()).setColumnControlVisible(false);
		((JXTable)table2.getTable()).setColumnControlVisible(false);
		((JXTable)table3.getTable()).setColumnControlVisible(false);
		
//		table1.getTable().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						tampilkanTable1();
//					}else{
//						tampilkanDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		table1.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						tampilkanTable1();
//					}else{
//						tampilkanDefault();
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
//						tampilkanTable2();
//					}else{
//						tampilkanDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		table2.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						tampilkanTable2();
//					}else{
//						tampilkanDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		
//		table3.getTable().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						LajurdTable t=(LajurdTable) table3;
//						t.setShowAll();
//						tampilkanTable3();
//					}else{
//						LajurdTable t=(LajurdTable) table3;
//						t.setSimple();
//						tampilkanDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		table3.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (viewForm.getPanel().isVisible()) {
//						LajurdTable t=(LajurdTable) table3;
//						t.setShowAll();
//						tampilkanTable3();
//					}else{
//						LajurdTable t=(LajurdTable) table3;
//						t.setSimple();
//						tampilkanDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
		
//		viewForm.getPanelForm().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (table1.getPanel().isVisible()) {
//						tampilkanView();
//					}else{
//						tampilkanDefault();
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
//						tampilkanView();
//					}else{
//						tampilkanDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
		
		table1.addWidgetChange(table2);
		table1.addWidgetChange(table3);
		table2.addWidgetChange(table3);
		table1.addWidgetChange(viewForm);
		table2.addWidgetChange(viewForm);
		table2.addWidgetChange(this);
		table3.addWidgetChange(viewForm);
		
		reload = new JButton(App.getIcon(L.iconReload16));
		print = new JButton(App.getIcon(L.iconPrint16));
		label = new JLabel(App.getIcon(urlIcon));
		label.setText(title);
		
		reload.setBackground(Color.WHITE);
		print.setBackground(Color.WHITE);
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionReload();
			}
		});
		
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionPrint();
			}
		});
		
		
		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table1.getPanel(), table2.getPanel());
		splitPane1.setDividerSize(1);
		
		splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table3.getPanel(), viewForm.getPanel());
		splitPane2.setDividerSize(1);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				splitPane1, splitPane2);
		splitPane.setDividerSize(1);
		
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		splitPane2.setDividerLocation(d2.intValue());
		
		splitPane.setBorder(App.borderWhite);
		splitPane1.setBorder(App.borderWhite);
		splitPane2.setBorder(App.borderWhite);

		buildAksi(db);
		
		panel=new JPanel(new BorderLayout());
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
		
	}
	
	private Double d = App.getW()*0.4;
	private Double d1 = App.getW()*0.2;
	private Double d2 = App.getW()*0.2;
	
	public JFrame getFrame(Object o){
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return  getFrame(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	
	public void actionPrint() {
		if (lajur!=null) {
			LajurdPrint p=new LajurdPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, lajur);
			db.close();
		}
		
	}

	public Window getWindow(Object o){
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
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

	private ODocument lajur;
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model==null) {
			lajur=null;
		}else{
			if (model.field("@class").equals(App.getLajurDao().getClassName())) {
				lajur=model;
			}
		}
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu, p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,     4dlu,p,     4dlu",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		toolBar.setBackground(Color.WHITE);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
		toolBar.add(reload, cc.xy(14, 1));
		toolBar.add(print, cc.xy(16, 1));
		toolBar.setBorder(App.borderBlackBottom);
	}
	
	
	
	
	
	
	
	public void changeHakAkses() {
		print.setEnabled(getPrint());
	}

	public boolean getPrint() {
		return DataUser.LAJURD_PRINT;
	}
	
	
	
	public void perspectiveDefault() {
		perspectiveDefault=true;
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(true);
		table3.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		splitPane2.setVisible(true);
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		splitPane2.setDividerLocation(d2.intValue());
	}
	public void perspective1() {
		perspectiveDefault=false;
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		table3.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		splitPane2.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
		splitPane1.setDividerLocation(1.0);
	}
	public void perspective2() {
		perspectiveDefault=false;
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(true);
		table3.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		splitPane2.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
		splitPane1.setDividerLocation(0.0);
	}
	public void perspective3() {
		perspectiveDefault=false;
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(false);
		table3.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		splitPane2.setVisible(true);
		
		splitPane.setDividerLocation(0.0);
		splitPane2.setDividerLocation(1.0);
	}
	public void perspective4() {
		perspectiveDefault=false;
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(false);
		table3.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		splitPane2.setVisible(true);
		
		splitPane.setDividerLocation(0.0);
		splitPane2.setDividerLocation(0.0);
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
