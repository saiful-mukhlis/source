package org.bmb.app.base.komponen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.bmb.app.base.adapter.HakAksesListener;
import org.bmb.app.base.adapter.MasterActionAdapter;
import org.bmb.app.base.adapter.MenuAdapter;
import org.bmb.app.base.adapter.WindowAdapter;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.bmb.app.lang.LWindow;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Menu implements MenuAdapter, HakAksesListener {

	@Override
	public void changeHakAkses() {
		// TODO Auto-generated method stub
		if (DataUser.getUsr()!=null) {
			login.setText(L.logout);
			login.setIcon(LOGOUT);
		}else{
			login.setText(L.login);
			login.setIcon(LOGIN);
		}
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		init();
	}

	@Override
	public JMenuBar getMenu() {
		// TODO Auto-generated method stub
		return menu;
	}

	@Override
	public void setWindow(WindowAdapter window) {
		// TODO Auto-generated method stub
		this.window=window;
	}

	private JMenuBar menu;

	protected JMenu file;
	protected JMenuItem login;
	protected JMenuItem close;
	protected JMenuItem print;
	protected JMenuItem exit;
	protected JMenuItem reload;
	
	public static Icon LOGIN=App.getIcon(L.iconLogin16);
	public static Icon LOGOUT=App.getIcon(L.iconLogout16);
	public static Icon CLOSE=App.getIcon(L.iconClose16);
	public static Icon PRINT=App.getIcon(L.iconPrint16);
	public static Icon EXIT=App.getIcon(L.iconExit16);
	public static Icon RELOAD=App.getIcon(L.iconReload16);

	protected JMenu editMenu;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem view;
	
	public static Icon ADD = App.getIcon(L.iconTambah16);
	public static Icon EDIT = App.getIcon(L.iconEdit16);
	public static Icon DEL = App.getIcon(L.iconHapus16);
	public static Icon VIEW = App.getIcon(L.iconView16);
	

	protected JMenu master;
	protected JMenuItem pegawai;
	protected JMenuItem kandang;
	protected JMenuItem pelanggan;
	
	public static Icon PEGAWAI = App.getIcon(L.iconPegawai16);
	public static Icon KANDANG = App.getIcon(L.iconKandang16);
	public static Icon PELANGGAN = App.getIcon(L.iconCustomer16);
	

	protected JMenu input;
	protected JMenuItem produksi;
	protected JMenuItem penjualan;
	protected JMenuItem pakan;
	
	public static Icon PRODUKSI = App.getIcon(L.iconLajur16);
	public static Icon PENJUALAN = App.getIcon(L.iconPenjualan16);
	public static Icon PAKAN = App.getIcon(L.iconPakan16);
	

	protected JMenu laporan;
	protected JMenuItem llajur;
	protected JMenuItem lkandang;
	protected JMenuItem lall;
	protected JMenuItem lpenjualan;
	protected JMenuItem lproduct;
	protected JMenuItem lpiutang;
	
	public static Icon PRODUCT = App.getIcon(L.iconStock16);
	public static Icon PIUTANG = App.getIcon(L.iconPiutang16);
	
	

	protected JMenu setting;
	protected JMenuItem format;
	protected JMenuItem showToolbar;
	
	public static Icon FORMAT = App.getIcon(L.iconFormat16);

	protected JMenu help;
	protected JMenuItem registrasi;
	protected JMenuItem about;
	
	public static Icon REGISTRASI = App.getIcon(L.iconReg16);
	public static Icon ABAOUT = App.getIcon(L.iconAbout16);

	private WindowAdapter window;
	
	

	public void init() {
		menu = new JMenuBar();

		file = new JMenu();
		login = new JMenuItem();
		close = new JMenuItem();
		print = new JMenuItem();
		exit = new JMenuItem();
		
		file.add(login);
		file.add(close);
		file.add(print);
		file.add(exit);

		editMenu = new JMenu();
		add = new JMenuItem();
		edit = new JMenuItem();
		del = new JMenuItem();
		view = new JMenuItem();
		reload=new JMenuItem();
		
		editMenu.add(add);
		editMenu.add(edit);
		editMenu.add(del);
		editMenu.add(view);
		editMenu.add(reload);

		master = new JMenu();
		pegawai = new JMenuItem();
		kandang = new JMenuItem();
		pelanggan = new JMenuItem();
		
		master.add(pegawai);
		master.add(kandang);
		master.add(pelanggan);

		input = new JMenu();
		produksi = new JMenuItem();
		penjualan = new JMenuItem();
		pakan = new JMenuItem();
		
		input.add(produksi);
		input.add(penjualan);
		input.add(pakan);

		laporan = new JMenu();
		llajur = new JMenuItem();
		lkandang = new JMenuItem();
		lall = new JMenuItem();
		lpenjualan = new JMenuItem();
		lproduct = new JMenuItem();
		lpiutang = new JMenuItem();
		
		laporan.add(llajur);
		laporan.add(lkandang);
		laporan.add(lall);
		laporan.add(lpenjualan);
		laporan.add(lproduct);
		laporan.add(lpiutang);

		setting = new JMenu();
		format = new JMenuItem();
		showToolbar = new JMenuItem();
		
		setting.add(format);
		setting.add(showToolbar);

		help = new JMenu();
		registrasi = new JMenuItem();
		about = new JMenuItem();
		
		help.add(registrasi);
		help.add(about);
		
		menu.add(file);
		menu.add(editMenu);
		menu.add(master);
		menu.add(input);
		menu.add(laporan);
		menu.add(setting);
		menu.add(help);

		initMenu(file, L.file);
		initMenu(login, L.login, KeyEvent.VK_L, KeyEvent.VK_L, ActionEvent.CTRL_MASK, LOGIN);
		initMenu(close, L.close, KeyEvent.VK_C, KeyEvent.VK_W, ActionEvent.CTRL_MASK, CLOSE );
		initMenu(print, L.print, KeyEvent.VK_P, KeyEvent.VK_P, ActionEvent.CTRL_MASK, PRINT);
		initMenu(exit, L.exit, KeyEvent.VK_E, KeyEvent.VK_X,
				ActionEvent.CTRL_MASK, EXIT);
		initMenu(editMenu, L.edit);
		initMenu(add, L.add, KeyEvent.VK_T, KeyEvent.VK_N, ActionEvent.CTRL_MASK, ADD);
		initMenu(edit, L.edit, KeyEvent.VK_E, KeyEvent.VK_E, ActionEvent.CTRL_MASK, EDIT);
		initMenu(del, L.del, KeyEvent.VK_H, KeyEvent.VK_DELETE, 0, DEL);
		initMenu(view, L.view, KeyEvent.VK_L, KeyEvent.VK_W, ActionEvent.CTRL_MASK, VIEW);
		initMenu(reload, L.reload, KeyEvent.VK_R, KeyEvent.VK_F5, 0, RELOAD);
		
		initMenu(master, LWindow.master);
		initMenu(pegawai, LWindow.pegawai, PEGAWAI);
		initMenu(kandang, LWindow.kandang, KANDANG);
		initMenu(pelanggan, LWindow.pelanggan, PELANGGAN);
		
		initMenu(input, LWindow.input);
		initMenu(produksi, LWindow.produksi);
		initMenu(penjualan, LWindow.penjualan, PENJUALAN);
		initMenu(pakan, LWindow.pakan, PAKAN);
		
		initMenu(laporan, LWindow.lap);
		initMenu(llajur, LWindow.produksi);
		initMenu(lkandang, LWindow.produksi_kandang);
		initMenu(lall, LWindow.produksi_total);
		
		initMenu(lpenjualan, LWindow.lap_penjualan);
		initMenu(lpiutang, LWindow.lap_piutang, PIUTANG);
		
		initMenu(setting, LWindow.setting);
		initMenu(format, LWindow.format_lap, FORMAT);
		initMenu(showToolbar, LWindow.hideToolbar);
		initMenu(help, LWindow.help);
		initMenu(registrasi, LWindow.registrasi, REGISTRASI);
		initMenu(about, LWindow.about, ABAOUT);
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.login();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionClose();
			}
		});
		
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionPrint();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionExit();
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionAdd();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionEdit();
			}
		});
		
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionDel();
			}
		});
		
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionView();
			}
		});
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionReload();
			}
		});
		
		pegawai.addActionListener(window.getKomponentMaps().get(Window.PEGAWAI).getAdd());
		kandang.addActionListener(window.getKomponentMaps().get(Window.KANDANG).getAdd());
		pelanggan.addActionListener(window.getKomponentMaps().get(Window.PELANGGAN).getAdd());
		
		produksi.addActionListener(window.getKomponentMaps().get(Window.INPUT_PRODUKSI).getAdd());
		penjualan.addActionListener(window.getKomponentMaps().get(Window.INPUT_PENJUALAN).getAdd());
		pakan.addActionListener(window.getKomponentMaps().get(Window.INPUT_PAKAN).getAdd());
		
		laporan.addActionListener(window.getKomponentMaps().get(Window.FORMAT).getAdd());
		llajur.addActionListener(window.getKomponentMaps().get(Window.PRODUKSI_LAJUR).getAdd());
		lkandang.addActionListener(window.getKomponentMaps().get(Window.PRODUKSI_KANDANG).getAdd());
		lall.addActionListener(window.getKomponentMaps().get(Window.PRODUKSI_ALL).getAdd());
		lpenjualan.addActionListener(window.getKomponentMaps().get(Window.PENJUALAN).getAdd());
		lpiutang.addActionListener(window.getKomponentMaps().get(Window.PIUTANG).getAdd());
		format.addActionListener(window.getKomponentMaps().get(Window.FORMAT).getAdd());
		
		showToolbar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.showToolbar();
				if (showToolbar.getText().equals(LWindow.showToolbar)) {
					showToolbar.setText(LWindow.hideToolbar);
				}else{
					showToolbar.setText(LWindow.showToolbar);
				}
			}
		});
		
		registrasi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionReg();
			}
		});
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.actionAbout();
			}
		});
		
	}

	private void initMenu(JMenu m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	private void initMenu(JMenu m, String nama) {
		m.setText(App.getT(nama));
	}

	private void initMenu(JMenuItem m, String nama) {
		m.setText(App.getT(nama));
	}

	private void initMenu(JMenuItem m, String nama, int key, int ke, int ae) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
	}

	private void initMenu(JMenuItem m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	private void initMenu(JMenuItem m, String nama, String urlIcon16) {
		m.setText(App.getT(nama));
		m.setIcon(App.getIcon(urlIcon16));
	}

	private void initMenu(JMenuItem m, String nama, int key, int ke, int ae,
			String urlIcon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(App.getIcon(urlIcon16));
	}

	private void initMenu(JMenuItem m, String nama, int key, String urlIcon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setIcon(App.getIcon(urlIcon16));
	}
	
	private void initMenu(JMenuItem m, String nama, Icon icon16) {
		m.setText(App.getT(nama));
		m.setIcon(icon16);
	}

	private void initMenu(JMenuItem m, String nama, int key, int ke, int ae,
			Icon icon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(icon16);
	}

	private void initMenu(JMenuItem m, String nama, int key, Icon icon16) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
		m.setIcon(icon16);
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFalseAll() {
		App.showErrSementara("Asdf");
		add.setEnabled(false);
		edit.setEnabled(false);
		view.setEnabled(false);
		del.setEnabled(false);
		print.setEnabled(false);
	}

	@Override
	public void setStateByHakAkses() {
		// TODO Auto-generated method stub
		App.showErrSementara("Asdf");
		if (window.getKomponentSeledcted()!=null) {
			MasterActionAdapter master=window.getKomponentSeledcted().getWidgetTop();
			add.setEnabled(master.getAdd());
			edit.setEnabled(master.getEdit());
			view.setEnabled(master.getLihat());
			del.setEnabled(master.getHapus());
			print.setEnabled(master.getPrint());
		}else{
			setFalseAll();
		}
	}

	//
	//
	//
	// public void init(ODatabaseDocumentTx db){
	// UIManager.put("Menu.borderPainted", true);
	// menu=new JMenuBar();
	// menu.setVisible(false);
	// menu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	// menu.setBackground(Color.RED);
	// menu.setOpaque(true);
	//
	// setting=new JMenu(App.getT(db, "Setting"));
	// help=new JMenu(App.getT(db, "Help"));
	// toolbar=new JMenu(App.getT(db, "Menu"));
	// toolbar.setBackground(Color.WHITE);
	// toolbar.setOpaque(true);
	// toolbar.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,
	// Color.BLACK));
	//
	//
	// toolbar.addMouseListener(new MouseAdapter() {
	// public void mouseClicked(MouseEvent me){
	// window.showToolbar();
	// }
	// });
	// }
	// public void build(ODatabaseDocumentTx db){
	// init(db);
	// menu.add(toolbar);
	// menu.add(setting);
	// menu.add(help);
	//
	// // Component c[]=menu.getComponents();
	// // for (Component x : c) {
	// // x.setBackground(Color.RED);
	// // System.out.println("asdfasdfasdf");
	// // }
	// }
	//
	// @Override
	// public void changeHakAkses() {
	// // TODO Auto-generated method stub
	//
	// }
	// public JMenuBar getMenu() {
	// return menu;
	// }
	// public void setMenu(JMenuBar menu) {
	// this.menu = menu;
	// }
	// public WindowAdapter getWindow() {
	// return window;
	// }
	// public void setWindow(WindowAdapter window) {
	// this.window = window;
	// }
	//
}
