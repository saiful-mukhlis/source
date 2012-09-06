package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.MasterActionAdapter;
import org.bmb.app.base.adapter.MasterAdapterForEfectWidget;
import org.bmb.app.base.adapter.TableAdapter;
import org.bmb.app.base.adapter.ToolbarSmallAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.komponen.SplitPane;
import org.bmb.app.base.komponen.ToolbarSmallR;

import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.PiutangComponetEdit;
import com.bmb.app.lang.L;
import com.bmb.app.view.table.PiutangTable;
import com.bmb.app.view.table.PiutangdTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangMaster implements MasterActionAdapter, HakAksesListener, MasterAdapterForEfectWidget {

	protected JPanel panel;
	protected JLabel label;
	protected String title;
	protected String urlIcon;
	
	protected TableAdapter table1;
	protected TableAdapter table2;
	protected SplitPane splitPane;
	protected SplitPane splitPane1;
	
	protected boolean perspectiveDefault=true;
	
//	protected JButton show1w;
//	protected JButton show2w;
	
//	protected JButton reload;

	protected JPanel aksi;

	protected ToolbarSmallR toolBar;
	
	protected PiutangComponetEdit editForm;
	
	protected List<ToolbarSmallAdapter> changeStateActions=new ArrayList<>();
	

	public void init(ODatabaseDocumentTx db){
		urlIcon=L.iconPiutang16;
		title="Data Piutang";
		table1=new PiutangTable();
		table2=new PiutangdTable();
		editForm=new PiutangComponetEdit();
		//viewForm=new ComponentView(new KandangdComponetView());
		
		table1.build(db);
		table2.build(db);
		editForm.build(db);
		editForm.getPanel().setBorder(App.borderWhite);
		
		
//		table1.getTable().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (editForm.getPanel().isVisible()) {
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
//					if (editForm.getPanel().isVisible()) {
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
//					if (editForm.getPanel().isVisible()) {
//						perspective2();
//					}else{
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		table2.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (editForm.getPanel().isVisible()) {
//						perspective2();
//					}else{
//						perspectiveDefault();
//					}
//				}
//			}
//			public void mouseReleased(MouseEvent e) {}
//		});
//		
//		editForm.getPanelForm().addMouseListener(new MouseAdapter() {
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
//		editForm.getLabelTitle().addMouseListener(new MouseAdapter() {
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
		table1.addWidgetChange(editForm);
		editForm.addWidgetModel(table1);
		editForm.addWidgetModel(table2);
		
//		reload = new JButton(App.getIcon(L.iconReload16));
//		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
//		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
//		label = new JLabel(App.getIcon(urlIcon));
//		label.setText(title);
//		
//		reload.setBackground(Color.WHITE);
//		show1w.setBackground(Color.WHITE);
//		show2w.setBackground(Color.WHITE);
		
//		reload.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				actionReload();
//			}
//		});
		
		
		splitPane1 = new SplitPane(JSplitPane.VERTICAL_SPLIT,
				editForm.getPanel(), table2.getPanel());
//		splitPane1.setDividerSize(1);
		//splitPane1.setOneTouchExpandable(true);
		
		
		splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table1.getPanel(), splitPane1);
//		splitPane.setDividerSize(1);
		//splitPane.setOneTouchExpandable(true);
		
//		Double d = App.getW()*0.3;
//		Double d1 = App.getH()*0.32;
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1);
		
//		splitPane.setBorder(App.borderWhite);
//		splitPane1.setBorder(App.borderWhite);

//		buildAksi(db);
		
		toolBar=new ToolbarSmallR(this);
		
		panel=new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
		
	}
	
	private Double d = App.getW()*0.3;
	private int d1 = 240;//App.getH()*0.32;
	
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

	@Override
	public void modelWidgetChange(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeHakAkses() {
		editForm.changeHakAkses();
		
	}
	
//	public void buildAksi(ODatabaseDocumentTx db){
//		FormLayout layout = new FormLayout(
//				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu, p,  4dlu,   	p:g,  4dlu,   	"
//						+ "p,     4dlu,",
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
//		toolBar.setBorder(App.borderBlackBottom);
//	}
	
	
	
	public void perspectiveDefault() {
		
//		protected TableAdapter table1;
//		protected TableAdapter table2;
//		protected ComponentEdit editForm;
//		protected JSplitPane splitPane;
//		protected JSplitPane splitPane1;
		
		splitPane1.setTopComponent(editForm.getPanel());
		splitPane1.setBottomComponent(table2.getPanel());
		
		splitPane.setLeftComponent(table1.getPanel());
		splitPane.setRightComponent(splitPane1);
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1);
		
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(true);
		editForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		
		
	}
	
	public void perspective1() {
		
		splitPane.setLeftComponent(table1.getPanel());
		
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		editForm.getPanel().setVisible(false);
		
		splitPane1.setVisible(false);
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
	}
	
	public void perspective2() {
		
		splitPane.setLeftComponent(table2.getPanel());
		
		table2.getPanel().setVisible(true);
		table1.getPanel().setVisible(false);
		editForm.getPanel().setVisible(false);
		
		splitPane1.setVisible(false);
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
	}


	public void perspective3() {
		splitPane.setLeftComponent(new JScrollPane(editForm.getPanel()));
		editForm.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		table1.getPanel().setVisible(false);
		
		splitPane1.setVisible(false);
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
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
	public void actionPrint() {
		// TODO Auto-generated method stub
		
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

	@Override
	public boolean getPrint() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ToolbarSmallAdapter> getChangeStateActions() {
		return changeStateActions;
	}

	public void setChangeStateActions(List<ToolbarSmallAdapter> changeStateActions) {
		this.changeStateActions = changeStateActions;
	}
	
	

}
