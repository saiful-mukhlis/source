package org.bmb.app.base.abstrak;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.bmb.app.base.adapter.FormAdapter;
import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.MasterActionAdapter;
import org.bmb.app.base.adapter.MasterAdapterForEfectWidget;
import org.bmb.app.base.adapter.MasterAdapterForToolbar;
import org.bmb.app.base.adapter.TableAdapter;
import org.bmb.app.base.komponen.SplitPane;
import org.bmb.app.base.komponen.ToolbarSmall;
import org.bmb.app.base.komponen.ToolbarSmallRLTED;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public abstract class MasterAbstract implements MasterActionAdapter, HakAksesListener, MasterAdapterForToolbar, MasterAdapterForEfectWidget{


	protected JPanel panel;
	protected TableAdapter table;
	
	protected FormAdapter form;
	protected FormAdapter editForm;
	protected FormAdapter viewForm;


	protected SplitPane splitPane;


	protected JPanel panelAction;

	protected ToolbarSmall toolBar;
	protected double lebar;
	protected String title;
	protected String urlIcon;
	
	protected boolean perspectiveDefault=true;
	
	public int getDevide() {
		Double tmp = App.getW()*lebar;
		return tmp.intValue();
	}
	
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		table.setMaster(this);
		table.build(db);
		
//		table.getTable().addMouseListener(new MouseAdapter() {
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
//		table.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
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
		
		toolBar = new ToolbarSmallRLTED(this);
		
		
	}
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildBody(db);
		setLayout();
//		setAksiTampilan();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), viewForm.getPanel());
		

		splitPane.setDividerSize(1);
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setBorder(App.borderWhite);
		perspectiveDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
	}


	@Override
	public JPanel getPanel() {
		return panel;
	}


	@Override
	public void changeHakAkses() {
	}

	public TableAdapter getTable() {
		return table;
	}
	
	public void actionReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.reload(db);
		db.close();
		
	}
	
	public void actionDel(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.aksiDelete(db);
		db.close();
	}

	public void perspectiveForm() {
		splitPane.setDividerLocation(getDevide());
	}

	

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		viewForm.build(db);
		setBinding();
	}
	
//	public void setAksiTampilan(){
//		if (viewForm.getPanelForm()!=null) {
//			viewForm.getPanelForm().addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent e) {
//					if (e.getClickCount() == 2) {
//						if (table.getPanel().isVisible()) {
//							perspective2();
//						}else{
//							perspectiveDefault();
//						}
//					}
//				}
//				public void mouseReleased(MouseEvent e) {}
//			});
//		}
//		
//		if (viewForm.getLabelTitle()!=null) {
//			viewForm.getLabelTitle().addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent e) {
//					if (e.getClickCount() == 2) {
//						if (table.getPanel().isVisible()) {
//							perspective2();
//						}else{
//							perspectiveDefault();
//						}
//					}
//				}
//				public void mouseReleased(MouseEvent e) {}
//			});
//		}
//		
//	}
	
	public void setBinding(){
//		table.addWidgetChange(getEditForm());
		table.addWidgetChange(getViewForm());
		table.addWidgetChange(this);

		
	}

	public FormAdapter getForm() {
		return form;
	}

	public void setForm(FormAdapter form) {
		this.form = form;
	}

	public FormAdapter getEditForm() {
		return editForm;
	}

	public void setEditForm(FormAdapter editForm) {
		this.editForm = editForm;
	}

	public FormAdapter getViewForm() {
		return viewForm;
	}

	public void setViewForm(FormAdapter viewForm) {
		this.viewForm = viewForm;
	}
	
	

	public void actionView() {
		perspectiveForm();
		splitPane.setRightComponent(viewForm.getPanel());
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		actionView();
		ToolbarSmallRLTED toolBar=(ToolbarSmallRLTED) this.toolBar;
		if (model==null) {
			toolBar.getEdit().setEnabled(false);
			toolBar.getView().setEnabled(false);
			toolBar.getDel().setEnabled(false);
		}else{
			toolBar.getAdd().setEnabled(getAdd());
			toolBar.getEdit().setEnabled(getEdit());
			toolBar.getView().setEnabled(getLihat());
			toolBar.getDel().setEnabled(getHapus());
			viewForm.modelWidgetChange(model);
		}

	}

	public boolean getAdd() {
		return false;
	}

	public boolean getHapus() {
		return false;
	}

	public boolean getLihat() {
		return false;
	}

	public boolean getEdit() {
		return false;
	}

	@Override
	public void modelWidgetAdd(ODocument model) {

	}

	@Override
	public void load(ODocument model) {

	}
	
	
	
	public void actionEdit() {
		if (editForm==null) {
			setEditForm();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			editForm.build(db);
			db.close();
//			editForm.getLabelTitle().addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent e) {
//					if (e.getClickCount() == 2) {
//						if (table.getPanel().isVisible()) {
//							perspective2();
//						}else{
//							perspectiveDefault();
//						}
//					}
//				}
//				public void mouseReleased(MouseEvent e) {}
//			});
//			editForm.getPanelForm().addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent e) {
//					if (e.getClickCount() == 2) {
//						if (table.getPanel().isVisible()) {
//							perspective2();
//						}else{
//							perspectiveDefault();
//						}
//					}
//				}
//				public void mouseReleased(MouseEvent e) {}
//			});
			table.addWidgetChange(getEditForm());
			editForm.addWidgetModel(table);
			editForm.addWidgetModel(this);
			table.selected();
		}
		perspectiveForm();
		splitPane.setRightComponent(editForm.getPanel());
		editForm.requestDefaultFocus();
	}
	
	public void actionAdd() {
		App.showErrSementara("asdrasdf");
		if (form==null) {
			setForm();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		    form.setMaster(this);
			form.build(db);
			form.addWidgetModel(table);
			db.close();
//			form.getPanelForm().addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent e) {
//					if (e.getClickCount() == 2) {
//						if (table.getPanel().isVisible()) {
//							perspective2();
//						}else{
//							perspectiveDefault();
//						}
//					}
//				}
//				public void mouseReleased(MouseEvent e) {}
//			});
		}
		splitPane.setRightComponent(form.getPanel());
		perspectiveForm();
		getForm().actionReset();
	}
	public void perspectiveDefault() {
		perspectiveDefault=true;
		table.getPanel().setVisible(true);

		viewForm.getPanel().setVisible(true);
		if (editForm!=null) {
			editForm.getPanel().setVisible(true);
		}
		if (form!=null) {
			form.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(getDevide());
	}
	public void perspective1() {
		perspectiveDefault=false;
		table.getPanel().setVisible(true);
		splitPane.setDividerLocation(1.0);
		viewForm.getPanel().setVisible(false);
		if (editForm!=null) {
			editForm.getPanel().setVisible(false);
		}
		if (form!=null) {
			form.getPanel().setVisible(false);
		}
	}
	public void perspective2() {
		perspectiveDefault=false;
		table.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		if (editForm!=null) {
			editForm.getPanel().setVisible(true);
		}
		if (form!=null) {
			form.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(0.0);
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
	public void actionPrint() {}
	public void perspective3() {}
	public void perspective4() {}
	public void initBody(ODatabaseDocumentTx db) {}
	public void setEditForm() {}
	public void setForm() {}
}
