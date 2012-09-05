package org.bmb.app.base.komponen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.bmb.app.base.adapter.ToolbarAdapter;
import org.bmb.app.base.adapter.WindowAdapter;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Toolbar implements ToolbarAdapter{
	protected JToolBar toolBar1;
	protected JToolBar toolBar2;
	protected JToolBar toolBar3;
	protected JToolBar toolBar4;
	protected JToolBar toolBar5;
	
	

	protected JButton login;
	protected JButton exit;
	
	protected JButton blogin;
	protected JButton bexit;
	
	protected Icon loginIcon;
	protected Icon bloginIcon;
	
	protected Icon logoutIcon;
	protected Icon blogoutIcon;
	
	
	protected JButton reg;
	protected JButton about;
	
	protected JPanel panel;
	
	protected JPanel panel1;
	protected JPanel panel2;
	protected JPanel panel3;
	protected JPanel panel4;
	protected JPanel panel5;
	
	protected JTabbedPane tab;

	private List<JButton> butons=new ArrayList<JButton>();
	
	private WindowAdapter window;
	
	public void inti(ODatabaseDocumentTx db){
		panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.WHITE);
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		
		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);
		panel4.setBackground(Color.WHITE);
		
		toolBar1=new JToolBar();
		toolBar2=new JToolBar();
		toolBar3=new JToolBar();
		toolBar4=new JToolBar();//JToolBar.VERTICAL
		toolBar5=new JToolBar();
		
		
		toolBar1.setBackground(Color.WHITE);
		toolBar2.setBackground(Color.WHITE);
		toolBar3.setBackground(Color.WHITE);
		toolBar4.setBackground(Color.WHITE);
		toolBar5.setBackground(Color.WHITE);
		
		
		
		List<Komponent> ks=window.getKomponents();
		for (final Komponent komponent : ks) {
			JButton b=new JButton();
//			App.info(komponent.getIcon32());
			setButton(db, b, komponent.getTitle(), komponent.getUrlIcon32());
			b.addActionListener(komponent.getAdd());
			b.setBackground(Color.WHITE);
			butons.add(b);
		}
		for (int i = 0; i < 4; i++) {
			toolBar1.add(butons.get(i));
		}
		for (int i = 4; i < 7; i++) {
			toolBar2.add(butons.get(i));
		}
		for (int i = 7; i < 14; i++) {
			toolBar3.add(butons.get(i));
		}
		
		
		
		loginIcon=App.getIcon(L.iconLogin16);
		bloginIcon=App.getIcon(L.iconLogin32);
		
		logoutIcon=App.getIcon(L.iconLogout16);
		blogoutIcon=App.getIcon(L.iconLogout32);
		
		login=new JButton();
		setButton2(db, login, L.iconLogin16);
		login.setBackground(Color.WHITE);
		
		exit=new JButton();
		setButton2(db, exit, L.iconExit16);
		exit.setBackground(Color.WHITE);
		
		toolBar4.add(login);
		toolBar4.add(exit);
		
		login.setVisible(false);
		exit.setVisible(false);
		
		blogin=new JButton();
		setButton2(db, blogin, "Login", L.iconLogin32);
		blogin.setBackground(Color.WHITE);
		
		bexit=new JButton();
		setButton2(db, bexit, "Exit", L.iconExit32);
		bexit.setBackground(Color.WHITE);
		
		toolBar4.add(blogin);
		toolBar4.add(bexit);
		
		reg=new JButton();
		setButton2(db, reg, "Registration", L.iconReg32);
		reg.setBackground(Color.WHITE);
		
		about=new JButton();
		setButton2(db, about, "About", L.iconInfo32);
		about.setBackground(Color.WHITE);
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				window.actionAbout();
			}
		});
		
		reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.actionReg();
			}
		});
		
		toolBar5.add(reg);
		toolBar5.add(about);

//		
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				window.login();
			}
		});
		blogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.login();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void build(ODatabaseDocumentTx db) {
		inti(db);
		
		panel1.setLayout(new CardLayout());
		panel1.add(toolBar1);
		
		panel2.setLayout(new CardLayout());
		panel2.add(toolBar2);
		
		panel3.setLayout(new CardLayout());
		panel3.add(toolBar3);
		
		panel4.setLayout(new CardLayout());
		panel4.add(toolBar4);
		
		panel5.setLayout(new CardLayout());
		panel5.add(toolBar5);
		
		
		
		
		
		getPanel().add(new JSeparator(), BorderLayout.SOUTH);
		tab=new JTabbedPane();
		tab.setBorder(BorderFactory.createEmptyBorder());
		tab.addTab("Menu Master        ",App.getIcon(L.iconMaster16), panel1);
		tab.addTab("Produksi & Penjualan ",App.getIcon(L.iconInputan16), panel2);
		tab.addTab("Menu Laporan        ",App.getIcon(L.iconLap16), panel3);
		tab.addTab("Help                   ",App.getIcon(L.iconHelp16), panel5);
		
		tab.setBorder(BorderFactory.createEmptyBorder(-1, -5, -5, -5));
		
		tab.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					tab.setVisible(false);
					//toolBar4.setOrientation(JToolBar.HORIZONTAL);
					login.setVisible(true);
					exit.setVisible(true);
					
					blogin.setVisible(false);
					bexit.setVisible(false);
				}
				
				
			}
		});
		
		panel.add(tab, BorderLayout.CENTER);
		panel.add(panel4, BorderLayout.EAST);
		
		panel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tab.setVisible(true);
					//toolBar4.setOrientation(JToolBar.VERTICAL);
					blogin.setVisible(true);
					bexit.setVisible(true);
					
					login.setVisible(false);
					exit.setVisible(false);
				}
				
			}
		});
		
		changeHakAkses();
	}
	private void setButton(ODatabaseDocumentTx db,JButton tb,String label, String nameIcon){
		tb.setText(label);
		tb.setVerticalTextPosition(SwingConstants.BOTTOM);
		tb.setHorizontalTextPosition(SwingConstants.CENTER);
		tb.setMargin(new Insets(0, 5, 0, 5));
		tb.setIcon(App.getIcon(nameIcon));
	}
	private void setButton2(ODatabaseDocumentTx db,JButton tb, String nameIcon){
		tb.setMargin(new Insets(0, 5, 0, 5));
		tb.setIcon(App.getIcon(nameIcon));
	}
	private void setButton2(ODatabaseDocumentTx db,JButton tb,String label, String nameIcon){
		tb.setText(label);
		tb.setVerticalTextPosition(SwingConstants.BOTTOM);
		tb.setHorizontalTextPosition(SwingConstants.CENTER);
		tb.setMargin(new Insets(0, 5, 0, 5));
		tb.setIcon(App.getIcon(nameIcon));
	}

	@Override
	public void changeHakAkses() {
		int i=0;
		butons.get(i++).setEnabled(DataUser.USR_VIEW);
		butons.get(i++).setEnabled(DataUser.HAK_AKSES_VIEW);
		butons.get(i++).setEnabled(DataUser.KANDANG_VIEW);
		butons.get(i++).setEnabled(DataUser.PELANGGAN_VIEW);
		
		butons.get(i++).setEnabled(DataUser.PRODUKSI_VIEW);
		butons.get(i++).setEnabled(DataUser.PENJUALAN_VIEW);
		butons.get(i++).setEnabled(DataUser.PAKAN_VIEW);
		
		butons.get(i++).setEnabled(DataUser.FORMAT_VIEW);
		butons.get(i++).setEnabled(DataUser.LAJURD_VIEW);
		butons.get(i++).setEnabled(DataUser.KANDANG_VIEW);
		butons.get(i++).setEnabled(DataUser.KANDANGALL_VIEW);
		butons.get(i++).setEnabled(DataUser.PENJUALAN_VIEW);
		butons.get(i++).setEnabled(DataUser.PRODUCTD_VIEW);
		butons.get(i++).setEnabled(DataUser.PIUTANG_VIEW);
//		butons.get(i++).setEnabled(DataUser.PIUTANG_VIEW);
		
		if (DataUser.getUsr()!=null) {
			blogin.setText("Logout");
			login.setIcon(logoutIcon);
			blogin.setIcon(blogoutIcon);
		}else{
			blogin.setText("Login");
			login.setIcon(loginIcon);
			blogin.setIcon(bloginIcon);
		}
	}
	
	

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public WindowAdapter getWindow() {
		return window;
	}

	public void setWindow(WindowAdapter window) {
		this.window = window;
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
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}
	
	
}
