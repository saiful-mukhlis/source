package com.bmb.app.view.master;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.bmb.app.base.abstrak.MasterAbstract;
import org.bmb.app.base.komponen.ToolbarSmallRP;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.KandangallDao;
import com.bmb.app.global.App;
import com.bmb.app.impl.view.form.KandangallComponetView;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LProduksiTotal;
import com.bmb.app.print.KandangallPrint;
import com.bmb.app.view.table.KandangallTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangallMaster extends MasterAbstract {

	public KandangallMaster() {
		super();
		lebar=0.4;
		title=LProduksiTotal.title;
		urlIcon=L.iconKandang16;
		viewForm=new KandangallComponetView();
		table = new KandangallTable();
	}
	

	@Override
	public void setEditForm() {
	}

	@Override
	public void setForm() {
	}

	
	private Date tgla;
	
	
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model==null) {
			tgla=null;
		}else if (model.field("@class").equals(App.getKandangallDao().getClassName())) {
			tgla=model.field(KandangallDao.tgl);
		}
		// tampilan default
		actionView();

	}
	
//	public void buildAksiListener(){
//		reload.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				actionReload();
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
//	}
	
	public void actionPrint() {
		if (tgla!=null) {
			KandangallPrint p=new KandangallPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, tgla);
			db.close();
		}
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
//		toolBar.add(print, cc.xy(14, 1));
//	}
	
//	protected JButton print;
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		table.build(db);
		
		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
						KandangallTable tx=(KandangallTable) table;
						tx.setShowAll();
						perspective1();
					}else{
						KandangallTable tx=(KandangallTable) table;
						tx.setSimple();
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
						KandangallTable tx=(KandangallTable) table;
						tx.setShowAll();
						perspective1();
					}else{
						KandangallTable tx=(KandangallTable) table;
						tx.setSimple();
						perspectiveDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		toolBar=new ToolbarSmallRP(this);
		
//		initLabelTitle(db);
//		//label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//		
//		reload = new JButton(App.getIcon(L.iconReload16));
//		print = new JButton(App.getIcon(L.iconPrint16));
//		
//		reload.setBackground(Color.WHITE);
//		print.setBackground(Color.WHITE);
//
//		
//		reload.setBackground(Color.WHITE);
		
	}
	
	
	
	
	
	
	public void changeHakAkses() {
		ToolbarSmallRP toolBar=(ToolbarSmallRP) this.toolBar;
		toolBar.getPrint().setEnabled(getPrint());
	}

	public boolean getPrint() {
		return DataUser.KANDANGD_PRINT;
	}
	
	
	public void perspectiveDefault() {
		
		
		splitPane.setLeftComponent(table.getPanel());
		splitPane.setRightComponent(viewForm.getPanel());
		
		splitPane.setDividerLocation(getDevide());
		
		table.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		
		
	}
	
	public void perspective1() {
		
		splitPane.setLeftComponent(table.getPanel());
		
		table.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(1.0);
	}


	public void perspective2() {
		splitPane.setLeftComponent(new JScrollPane(viewForm.getPanel()));
		table.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(0.0);
	}
	
	
}
