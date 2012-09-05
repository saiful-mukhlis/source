//package org.bmb.app.base.abstrak;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JSplitPane;
//
//import org.bmb.app.base.adapter.HakAksesListener;
//import org.bmb.app.base.adapter.WidgetAdapter;
//import org.bmb.app.base.komponen.SplitPane;
//import org.bmb.app.base.komponen.ToolbarSmall;
//
//import com.bmb.app.global.App;
//import com.bmb.app.lang.L;
//import com.bmb.app.view.component.ButtonAction;
//import com.bmb.app.view.component.MasterModel;
//import com.jgoodies.forms.layout.CellConstraints;
//import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
//import com.orientechnologies.orient.core.record.impl.ODocument;
//
//
//
//public class MasterController implements WidgetAdapter, HakAksesListener{
//
//	private MasterModel model;
//	private ToolbarSmall toolbar;
//	/* 
//	 * di gunakan untuk membuild tampilan
//	 * (non-Javadoc)
//	 * @see org.bmb.app.base.adapter.WidgetAdapter#build(com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx)
//	 */
//	@Override
//	public void build(ODatabaseDocumentTx db) {
//		setLayout();
//	}
//
//	/* 
//	 * tidak di gunakan
//	 * (non-Javadoc)
//	 * @see org.bmb.app.base.adapter.WidgetAdapter#load(com.orientechnologies.orient.core.record.impl.ODocument)
//	 */
//	@Override
//	public void load(ODocument model) {
//	}
//
//	/* 
//	 * 
//	 * (non-Javadoc)
//	 * @see org.bmb.app.base.adapter.WidgetAdapter#getPanel()
//	 */
//	@Override
//	public JPanel getPanel() {
//		return model.getPanel();
//	}
//
//	@Override
//	public void modelWidgetChange(ODocument model) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void modelWidgetAdd(ODocument model) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void changeHakAkses() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	public static final int TABLE_VIEW=0;
//	public void setLayout() {
//		if (model.getTypeLayout()==TABLE_VIEW) {
//			model.setPanel(new JPanel(new BorderLayout()));
//			model.setSplitPanes(new ArrayList<SplitPane>());
//			
//			SplitPane splitPane=new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
//					model.getWidgets().get(0).getPanel(), model.getWidgets().get(1).getPanel());
//			setPerspectiveDefault();
//			model.getSplitPanes().add(splitPane);
//			
//			JLabel label = new JLabel(App.getIcon(model.getUrlIcon()));
//			label.setText(model.getTitle());
//			
//			toolbar=new ToolbarSmall();
//			CellConstraints cc = new CellConstraints();
//			toolbar.add(label, cc.xy(2, 1));
//			
//			int nocc=12;
//			
//			List<ButtonAction> buttonActions=model.getButtonActions();
//			for (ButtonAction b : buttonActions) {
//				JButton button=new JButton(App.getIcon(b.getUrlIcon()));
//				button.setBackground(Color.WHITE);
//				toolbar.add(button, cc.xy(nocc, 1));
//				nocc=nocc+2;
//			}
//			
//			model.getPanel().add(splitPane, BorderLayout.CENTER);
//			model.getPanel().add(toolbar, BorderLayout.NORTH);
//		}
//		
//		
//		
//	}
//
//	public void setPerspectiveDefault() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
