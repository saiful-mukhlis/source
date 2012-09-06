package org.bmb.app.base.komponen;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.bmb.app.base.abstrak.WindowAbstract;
import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.ToolbarSmallAdapter;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LWindow;
import com.bmb.app.view.master.FormatMaster;
import com.bmb.app.view.master.GrpMaster;
import com.bmb.app.view.master.KandangMaster;
import com.bmb.app.view.master.KandangallMaster;
import com.bmb.app.view.master.KandangdInputPakanMaster;
import com.bmb.app.view.master.KandangdMaster;
import com.bmb.app.view.master.LajurdMaster;
import com.bmb.app.view.master.PelangganMaster;
import com.bmb.app.view.master.PenjualanFormMaster;
import com.bmb.app.view.master.PenjualanMaster;
import com.bmb.app.view.master.PiutangMaster;
import com.bmb.app.view.master.ProductdMaster;
import com.bmb.app.view.master.ProduksiFormMaster;
import com.bmb.app.view.master.PegawaiMaster;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Window extends WindowAbstract{
	
	private List<Komponent> komponents=new ArrayList<Komponent>();
	private List<HakAksesListener> cangeHakAkses = new ArrayList<HakAksesListener>();
	private HashMap<String, Komponent> komponentMaps=new HashMap<>();
	
	
	protected AddContentAction windowAca;
	protected ViewContextAction windowVca;
	
	protected Component welcomeComponent;
	
	
	public static String PEGAWAI=PegawaiMaster.class.getName();
	public static String HAK_AKSES=GrpMaster.class.getName();
	public static String KANDANG=KandangMaster.class.getName();
	public static String PELANGGAN=PelangganMaster.class.getName();
	
	public static String INPUT_PRODUKSI=ProduksiFormMaster.class.getName();
	public static String INPUT_PENJUALAN=PenjualanFormMaster.class.getName();
	public static String INPUT_PAKAN=KandangdInputPakanMaster.class.getName();
	
	public static String FORMAT=FormatMaster.class.getName();
	public static String PRODUKSI_LAJUR=LajurdMaster.class.getName();
	public static String PRODUKSI_KANDANG=KandangdMaster.class.getName();
	public static String PRODUKSI_ALL=KandangallMaster.class.getName();
	public static String PENJUALAN=PenjualanMaster.class.getName();
	public static String PRODUCT=ProductdMaster.class.getName();
	public static String PIUTANG=PiutangMaster.class.getName();
	
	@Override
	public void buildMaster(ODatabaseDocumentTx db) {
		
		Komponent pegawai=new Komponent(this, PEGAWAI, LWindow.pegawai, 'P', L.iconPegawai16, L.iconPegawai32);
		Komponent hakAkses=new Komponent(this, HAK_AKSES, LWindow.hak_akses, 'H', L.iconHakAkses16, L.iconHakAkses32);
		Komponent kandang=new Komponent(this, KANDANG, LWindow.kandang, 'K', L.iconKandang16, L.iconKandang32);
		Komponent pelanggan=new Komponent(this, PELANGGAN, LWindow.pelanggan, 'E', L.iconCustomer16, L.iconCustomer32);
		
		Komponent inputProduksi=new Komponent(this, INPUT_PRODUKSI, LWindow.produksi, 'I', L.iconProduksi16, L.iconProduksi32);
		Komponent inputPenjualan=new Komponent(this, INPUT_PENJUALAN, LWindow.penjualan, 'J', L.iconPenjualan16, L.iconPenjualan32);
		Komponent inputPakan=new Komponent(this, INPUT_PAKAN, LWindow.pakan, 'U', L.iconPakan16, L.iconPakan32);
		
		Komponent format=new Komponent(this, FORMAT, LWindow.lap, 'F', L.iconLaporan16, L.iconLaporan32);
		Komponent produksiLajur=new Komponent(this, PRODUKSI_LAJUR, LWindow.lajur, 'O', L.iconLajur16, L.iconLajur32);
		Komponent produksiKandang=new Komponent(this, PRODUKSI_KANDANG, LWindow.produksi_kandang, 'D', L.iconKandang16, L.iconKandang32);
		Komponent produksiAll=new Komponent(this, PRODUKSI_ALL, LWindow.produksi_total, 'T', L.iconTotal16, L.iconTotal32);
		Komponent penjualan=new Komponent(this, PENJUALAN,  LWindow.lap_penjualan, 'L', L.iconPenjualan16, L.iconPenjualan32);
		Komponent product=new Komponent(this, PRODUCT, LWindow.lap_stock, 'S', L.iconStock16, L.iconStock32);
		Komponent piutang=new Komponent(this, PIUTANG, LWindow.lap_piutang, 'G', L.iconPiutang16, L.iconPiutang32);
		
		komponentMaps.put(PEGAWAI, pegawai);
		komponentMaps.put(HAK_AKSES, hakAkses);
		komponentMaps.put(KANDANG, kandang);
		komponentMaps.put(PELANGGAN, pelanggan);
		
		komponentMaps.put(INPUT_PRODUKSI, inputProduksi);
		komponentMaps.put(INPUT_PENJUALAN, inputPenjualan);
		komponentMaps.put(INPUT_PAKAN, inputPakan);
		
		komponentMaps.put(FORMAT, format);
		komponentMaps.put(PRODUKSI_LAJUR, produksiLajur);
		komponentMaps.put(PRODUKSI_KANDANG, produksiKandang);
		komponentMaps.put(PRODUKSI_ALL, produksiAll);
		komponentMaps.put(PENJUALAN, penjualan);
		komponentMaps.put(PRODUCT, product);
		komponentMaps.put(PIUTANG, piutang);
		
		komponents.add(pegawai);
		komponents.add(hakAkses);
		komponents.add(kandang);
		komponents.add(pelanggan);
		
		komponents.add(inputProduksi);
		komponents.add(inputPenjualan);
		komponents.add(inputPakan);
		
		komponents.add(format);
		komponents.add(produksiLajur);
		komponents.add(produksiKandang);
		komponents.add(produksiAll);
		komponents.add(penjualan);
		komponents.add(product);
		komponents.add(piutang);
		
		
		
//		getKomponents().add(new Komponent(db, toolWindowManager, new PegawaiMaster(), LWindow.pegawai, 'P', L.iconPegawai16, L.iconPegawai32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new GrpMaster(), LWindow.hak_akses, 'H', L.iconHakAkses16, L.iconHakAkses32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new KandangMaster(), LWindow.kandang, 'K', L.iconKandang16, L.iconKandang32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new PelangganMaster(), LWindow.pelanggan, 'E', L.iconCustomer16, L.iconCustomer32));
//		
//		getKomponents().add(new Komponent(db, toolWindowManager, new ProduksiFormMaster(), LWindow.produksi, 'I', L.iconProduksi16, L.iconProduksi32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new PenjualanFormMaster(),LWindow.penjualan, 'J', L.iconPenjualan16, L.iconPenjualan32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new KandangdInputPakanMaster(), LWindow.pakan, 'U', L.iconPakan16, L.iconPakan32));
//		
//		
//		getKomponents().add(new Komponent(db, toolWindowManager, new FormatMaster(), LWindow.lap, 'F', L.iconLaporan16, L.iconLaporan32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new LajurdMaster(), LWindow.lajur, 'O', L.iconLajur16, L.iconLajur32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new KandangdMaster(),LWindow.produksi_kandang, 'D', L.iconKandang16, L.iconKandang32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new KandangallMaster(), LWindow.produksi_total, 'T', L.iconTotal16, L.iconTotal32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new PenjualanMaster(), LWindow.lap_penjualan, 'L', L.iconPenjualan16, L.iconPenjualan32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new ProductdMaster(), LWindow.lap_stock, 'S', L.iconStock16, L.iconStock32));
//		getKomponents().add(new Komponent(db, toolWindowManager, new PiutangMaster(), LWindow.lap_piutang, 'G', L.iconPiutang16, L.iconPiutang32));
//		
		
		
		
//		getKomponents().add(new Komponent(db, toolWindowManager, new I18nMaster(), "Bahasa", 'B', "icon pegawai"));
//		getKomponents().add(new Komponent(db, toolWindowManager, new KandangdMaster(), "Laporan Kandang", 'L', "icon pegawai"));
		
		
//		usrMaster=new UsrMaster();
//		usrMaster.build(db);
		
//		kandangMaster=new KandangMaster();
//		kandangMaster.build(db);
		
		for (Komponent komponent : getKomponents()) {
			cangeHakAkses.add(komponent.getHakAksesListener());
//			komponentMaps.put(toolWindowManager.getContentManager()
//								.getContentByComponent(komponent.getComponent()), komponent);
		}
		
//		cangeHakAkses.add(usrMaster);
//		cangeHakAkses.add(kandangMaster);
//		
//		usrComponent=usrMaster.getPanel();
//		kandangComponent=kandangMaster.getPanel();
		
		JPanel panel = new ImagePanel(
				new FlowLayout(FlowLayout.CENTER, 50, 180));
		welcomeComponent = panel;

//		usrAca = factoryContentAction(db, "Pegawai", App.getIcon(db, "icon pegawai 16"),
//				usrComponent, (int) 'P');
//		
//		kandangAca = factoryContentAction(db, "Kandang", App.getIcon(db, "icon kandang 16"),
//				kandangComponent, (int) 'K');
		
		windowAca = factoryContentAction(db, "Welcome", App.getIcon(L.iconApp16),
				welcomeComponent, (int) 'W');
		
	}


	@Override
	public void buildActions(ODatabaseDocumentTx db) {
		for (Komponent komponent : getKomponents()) {
			komponent.setView(db, komponent.getTypeMaster(), myDoggySetContext);
		}
		
		windowVca = new ViewContextAction(
				"Welcome", App.getIcon(L.iconApp16),
				myDoggySetContext, ImagePanel.class);
		
	}
	
	@Override
	public void initContext(ODatabaseDocumentTx db) {
		myDoggySetContext = new Context(this, toolWindowManager, frame);
	}
	

	@Override
	public void initToolbar(ODatabaseDocumentTx db) {
		setToolbar(new Toolbar());
		getToolbar().setWindow(this);
		getToolbar().build(db);
		frame.getContentPane().add(getToolbar().getPanel(), BorderLayout.NORTH);
		cangeHakAkses.add(toolbar);
	}

	@Override
	public void initMenu(ODatabaseDocumentTx db) {
		menu=new Menu();
		menu.setWindow(this);
		menu.build(db);
		frame.setJMenuBar(menu.getMenu());
	}


	@Override
	public void showWelcome() {
		myDoggySetContext.put(ImagePanel.class, null);
		
	}

	@Override
	public Component getComponentWelcome() {
		return welcomeComponent;
	}

	@Override
	public void seta(ODatabaseDocumentTx db) {
		bosOne=new BosOne();
		bosOne.seta(db);
		
	}



	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}


//	public AddContentAction getUsrAca() {
//		return usrAca;
//	}
//	
//	public AddContentAction getKandangAca() {
//		return kandangAca;
//	}
	
	public AddContentAction getWelcomeAca() {
		return windowAca;
	}


	@Override
	public void showToolbar() {
		if (toolbar.getPanel().isVisible()) {
			toolbar.getPanel().setVisible(false);
		}else{
			toolbar.getPanel().setVisible(true);
		}
		
	}


	public List<Komponent> getKomponents() {
		return komponents;
	}


	public void setKomponents(List<Komponent> komponents) {
		this.komponents = komponents;
	}


	public void login() {
		
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			form.buildComponent(db);
			db.close();
			JDialog d = new JDialog(frame);
			d.setModalityType(ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(form.getPanel());
			d.pack();
			setCenterDialog(d);
			d.setVisible(true);
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			closeAllWindow();
			
		}
		DataUser.setAkses();
		for (HakAksesListener hakAksesListener : cangeHakAkses) {
			if (hakAksesListener!=null) {
				hakAksesListener.changeHakAkses();
			}
		}
//		if (DataUser.usr != null) {
//			// open default like welcome
//
//		}
	}
	
	public void closeAllWindow() {
		toolWindowManager.getContentManager().removeAllContents();

	}
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}


	public HashMap<String, Komponent> getKomponentMaps() {
		return komponentMaps;
	}


	public void setKomponentMaps(HashMap<String, Komponent> komponentMaps) {
		this.komponentMaps = komponentMaps;
	}
	
	public Komponent getKomponentSeledcted(){
		return komponentMaps.get(idSelected);
	}
	
	public void actionClose(){
		ContentManager c=toolWindowManager.getContentManager();
		Content co=c.getSelectedContent();
		if (co!=null) {
			c.removeContent(co);
		}
	}


	@Override
	public void actionExit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPrint() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionPrint();
		}
	}


	@Override
	public void actionAdd() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionAdd();
		}
	}


	@Override
	public void actionEdit() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionEdit();
		}
	}


	@Override
	public void actionDel() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionDel();
		}
	}


	@Override
	public void actionView() {
		// TODO Auto-generated method stub
		Komponent komponent=getKomponentSeledcted();
		if (komponent!=null) {
			komponent.getWidgetTop().actionView();
		}
	}
	
	public void actionReg(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		FormRegistrasi form=new FormRegistrasi();
		form.buildComponent(db);
		JDialog d = new JDialog(getWindow(getPanel()), ModalityType.APPLICATION_MODAL);
		d.setIconImage(App.getImage(L.iconApp16).getImage());
		d.getContentPane().add(form.getPanel());
		d.setFocusTraversalPolicy(form.getFocus());
		d.pack();
		setCenterDialog(d);
		d.setVisible(true);
		
		db.close();
	}

	public void actionAbout(){
		AboutDialog form=new AboutDialog();
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		form.buildComponent(db);
		JDialog d = new JDialog(getWindow(getPanel()), ModalityType.APPLICATION_MODAL);
		d.setIconImage(App.getImage(L.iconApp16).getImage());
		d.getContentPane().add(form.getPanel());
		d.setSize(480, 360);
		//d.pack();
		d.setResizable(false);
		setCenterDialog(d);
		d.setVisible(true);
	}
	
	public java.awt.Window getWindow(Object o){
		if (o instanceof Window) {
			return ((java.awt.Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}


	

}
