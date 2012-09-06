package org.bmb.app.base.komponen;

import java.awt.Component;

import javax.swing.Icon;

import org.bmb.app.base.abstrak.action.AddContentAction;
import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.MasterActionAdapter;
import org.bmb.app.base.adapter.ToolbarSmallAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.adapter.WindowAdapter;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;
import org.noos.xing.yasaf.view.ViewContext;

import com.bmb.app.global.App;
import com.bmb.app.view.master.FormatMaster;
import com.bmb.app.view.master.GrpMaster;
import com.bmb.app.view.master.KandangMaster;
import com.bmb.app.view.master.KandangallMaster;
import com.bmb.app.view.master.KandangdInputPakanMaster;
import com.bmb.app.view.master.KandangdMaster;
import com.bmb.app.view.master.LajurdMaster;
import com.bmb.app.view.master.PegawaiMaster;
import com.bmb.app.view.master.PelangganMaster;
import com.bmb.app.view.master.PenjualanFormMaster;
import com.bmb.app.view.master.PenjualanMaster;
import com.bmb.app.view.master.PiutangMaster;
import com.bmb.app.view.master.ProductdMaster;
import com.bmb.app.view.master.ProduksiFormMaster;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Komponent {
	private MasterActionAdapter widgetTop;
	private Component component;
	private AddContentAction add;
	private ViewContextAction view;
	private ToolWindowManager toolWindowManager;
	private HakAksesListener hakAksesListener;
	private String typeMaster;
	private WindowAdapter window;
	
	
	private String title;
	private char shortCut;
	private String urlIcon;
	private String urlIcon32;
	private Icon icon;
	
	
	public Komponent() {
		super();
	}
	
	public Komponent(WindowAdapter window, String typeMaster, String title, char shortCut,
			String urlIcon,String urlIcon32) {
		super();
		this.window=window;
		this.typeMaster = typeMaster;
		this.setHakAksesListener((HakAksesListener) widgetTop);
		this.setTitle(title);
		this.shortCut = shortCut;
		this.setUrlIcon(urlIcon);
		this.setUrlIcon32(urlIcon32);
		this.toolWindowManager=window.getToolWindowManager();
		this.icon=App.getIcon(urlIcon);
		
//		widgetTop.build(db);
//		component=widgetTop.getPanel();
		
		
		setAdd(new AddContentAction(this));
	}

	public MasterActionAdapter getWidgetTop() {
		return widgetTop;
	}
	public void setMaster(MasterActionAdapter widgetTop) {
		this.widgetTop = widgetTop;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public ViewContextAction getView() {
		return view;
	}
	public void setView(ODatabaseDocumentTx db, Object key, ViewContext myDoggySetContext) {
		this.view = new ViewContextAction(
				getTitle(), icon,
				myDoggySetContext, key);
	}
	
//	public AddContentAction factoryContentAction() {
//		return new AddContentAction(this);
//	}

	public AddContentAction getAdd() {
		return add;
	}

	public void setAdd(AddContentAction add) {
		this.add = add;
	}

	public HakAksesListener getHakAksesListener() {
		return hakAksesListener;
	}

	public void setHakAksesListener(HakAksesListener hakAksesListener) {
		this.hakAksesListener = hakAksesListener;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getIcon() {
//		return urlIcon;
//	}
//
//	public void setIcon(String icon) {
//		this.urlIcon = icon;
//	}
//
//	public String getIcon32() {
//		return urlIcon32;
//	}
//
//	public void setIcon32(String icon32) {
//		this.urlIcon32 = icon32;
//	}

	
	
	public String getTypeMaster() {
		return typeMaster;
	}

	public WindowAdapter getWindow() {
		return window;
	}

	public void setWindow(WindowAdapter window) {
		this.window = window;
	}

	public char getShortCut() {
		return shortCut;
	}

	public void setShortCut(char shortCut) {
		this.shortCut = shortCut;
	}

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	public String getUrlIcon32() {
		return urlIcon32;
	}

	public void setUrlIcon32(String urlIcon32) {
		this.urlIcon32 = urlIcon32;
	}

	public void setWidgetTop(MasterActionAdapter widgetTop) {
		this.widgetTop = widgetTop;
	}

	public void setView(ViewContextAction view) {
		this.view = view;
	}

	public void setTypeMaster(String typeMaster) {
		this.typeMaster = typeMaster;
	}

	public ToolWindowManager getToolWindowManager() {
		return toolWindowManager;
	}

	public void setToolWindowManager(ToolWindowManager toolWindowManager) {
		this.toolWindowManager = toolWindowManager;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	
	public Komponent createComponent(Komponent komponent){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
	    String type=komponent.getTypeMaster();
		if (type.equals(Window.PEGAWAI)) {
			komponent.setWidgetTop(new PegawaiMaster());
		}else if (type.equals(Window.HAK_AKSES)) {
			komponent.setWidgetTop(new GrpMaster());
		}else if (type.equals(Window.PELANGGAN)) {
			komponent.setWidgetTop(new PelangganMaster());
		}else if (type.equals(Window.INPUT_PENJUALAN)) {
			komponent.setWidgetTop(new PenjualanFormMaster());
		}else if (type.equals(Window.INPUT_PAKAN)) {
			komponent.setWidgetTop(new KandangdInputPakanMaster());
		}else if (type.equals(Window.FORMAT)) {
			komponent.setWidgetTop(new FormatMaster());
		}else if (type.equals(Window.PRODUKSI_LAJUR)) {
			komponent.setWidgetTop(new LajurdMaster());
		}else if (type.equals(Window.PRODUKSI_KANDANG)) {
			komponent.setWidgetTop(new KandangdMaster());
		}else if (type.equals(Window.PRODUKSI_ALL)) {
			komponent.setWidgetTop(new KandangallMaster());
		}else if (type.equals(Window.PENJUALAN)) {
			komponent.setWidgetTop(new PenjualanMaster());
		}else if (type.equals(Window.PRODUCT)) {
			komponent.setWidgetTop(new ProductdMaster());
		}else if (type.equals(Window.PIUTANG)) {
			komponent.setWidgetTop(new PiutangMaster());
		}else if (type.equals(Window.KANDANG)) {
			komponent.setWidgetTop(new KandangMaster());
		}else if (type.equals(Window.INPUT_PRODUKSI)) {
			komponent.setWidgetTop(new ProduksiFormMaster());
		}
		
		
		if (komponent.getWidgetTop()!=null) {
			komponent.getWidgetTop().getChangeStateActions().add(window.getMenu());
			komponent.getWidgetTop().build(db);
			komponent.setComponent(komponent.getWidgetTop().getPanel());
		}
		db.close();
		return komponent;
	}
	
}
