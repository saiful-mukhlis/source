package com.bmb.app.impl.view.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.bmb.app.base.abstrak.FormAbstract;
import org.bmb.app.base.adapter.FormAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.builder.FormBuilder;
import org.bmb.app.base.komponen.ComboBox;
import org.bmb.app.base.komponen.DatePicker;
import org.bmb.app.base.komponen.FormattedTextField;
import org.bmb.app.base.komponen.TextField;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.ProductDao;
import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.bmb.app.view.table.model.LajurTableModelSearch;
import com.bmb.app.view.table.model.PelangganTableModelSearch;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProduksiForm extends FormAbstract {
	private TextField tBb;
	private TextField tBr;
	private FormattedTextField tKb;
	private FormattedTextField tKr;
	private TextField tOff;
	private DatePicker tWorkDate;
	
	private ComboBox cWaktu;
	private TextField tLajur;
	private DefaultComboBoxModel modelWaktu;

//	private JXList lajurList;
//	private DefaultListModel listModel;
	
	private JXTable tPopup;
	private LajurTableModelSearch tModel;
	private JScrollPane ss;
	
	
	private TextField tStartPopulasi;
	private JPopupMenu popup;
	private ODocument lajur;
	private ODocument workspacel;
	
	
	
	private Date tgla;
	private long pupulasia;
	private int matia;
	private int butirpagibagusa=0;
	private int butirpagiretaka=0;
	private int butirsorebagusa=0;
	private int butirsoreretaka=0;
	
	private double kgpagibagusa=0;
	private double kgpagiretaka=0;
	private double kgsorebagusa=0;
	private double kgsoreretaka=0;
	
    
	

	
	private Font f2;
	
	
	
	protected JButton save;
	protected JButton reset;
	
	
	
	
	
	
	public boolean validate(ODatabaseDocumentTx db){
		Date tmp=tWorkDate.getDate();
		if (tmp!=null) {
			tgla=tmp;
		}else{
			App.showErrorFieldEmpty(db, LajurdDao.ftgl);
			return false;
		}
		
		
		if (lajur==null) {
			tLajur.setText("");
			App.showErrorFieldEmpty(db, LajurdDao.flajur);
			return false;
		}
		
		if (workspacel!=null) {
			if (lajurdPertama) {
				//awal
				try {
					int tmp2=Integer.parseInt(tStartPopulasi.getText());
					pupulasia=tmp2;
				} catch (Exception e) {
					tStartPopulasi.setText(0+"");
					App.printErr(e);
					App.showErrorFieldEmpty(db, LajurdDao.pupulasi);
					return false;
				}
				
			}else{
				//tidak awal
				
			}
		}else{
			// data baru
			long id=App.getLajurdDao().getCountByColumn(db, LajurdDao.lajur, lajur.getIdentity());
					//dao.count("lajur.id", lajur.getId());
			
			if (id==0) {
				//awal
				try {
					int tmp2=Integer.parseInt(tStartPopulasi.getText());
					pupulasia=tmp2;
				} catch (Exception e) {
					tStartPopulasi.setText(0+"");
					App.printErr(e);
					App.showErrorFieldEmpty(db, LajurdDao.pupulasi);
					return false;
				}
				
			}
		}
		
		try {
			matia=Integer.parseInt(tOff.getText());
			if (matia<0) {
				App.showErrorNotValid(db, LajurdDao.fmati);
				return false;
			}
		} catch (Exception e) {
			tOff.setText(0+"");
			App.printErr(e);
			App.showErrorFieldEmpty(db, LajurdDao.fmati);
			return false;
		}
		boolean pagi=true;
		if (cWaktu.getSelectedIndex()==1) {
			pagi=false;
		}
		try {
			if (pagi) {
				butirpagibagusa=Integer.parseInt(tBb.getText());
				if (butirpagibagusa<0) {
					App.showErrorNotValid(db, "Bagus[btr]");
					return false;
				}
			}else{
				butirsorebagusa=Integer.parseInt(tBb.getText());
				if (butirsorebagusa<0) {
					App.showErrorNotValid(db, "Bagus[btr]");
					return false;
				}
			}
		} catch (Exception e) {
			tBb.setText(0+"");
			App.printErr(e);
			App.showErrorFieldEmpty(db, "Bagus[btr]");
			
			return false;
		}
		
		try {
			if (pagi) {
				kgpagibagusa=Double.parseDouble(tKb.getText());
				if (kgpagibagusa<0) {
					App.showErrorNotValid(db, "Bagus[kg]");
				}
			}else{
				kgsorebagusa=Double.parseDouble(tKb.getText());
				if (kgsorebagusa<0) {
					App.showErrorNotValid(db, "Bagus[kg]");
				}
			}
		} catch (Exception e) {
			tKb.setText(0+"");
			App.printErr(e);
			App.showErrorFieldEmpty(db, "Bagus[kg]");
			return false;
		}
		
		try {
			if (pagi) {
				butirpagiretaka=Integer.parseInt(tBr.getText());
				if (butirpagiretaka<0) {
					App.showErrorNotValid(db, "Retak[btr]");
				}
			}else{
				butirsoreretaka=Integer.parseInt(tBr.getText());
				if (butirsoreretaka<0) {
					App.showErrorNotValid(db, "Retak[btr]");
				}
			}
		} catch (Exception e) {
			tBr.setText(0+"");
			App.printErr(e);
			App.showErrorFieldEmpty(db,"Retak[btr]");
			return false;
		}
		
		
		
		try {
			if (pagi) {
				kgpagiretaka=Double.parseDouble(tKr.getText());
				if (kgpagiretaka<0) {
					App.showErrorNotValid(db, "Retak[kg]");
				}
			}else{
				kgsoreretaka=Double.parseDouble(tKr.getText());
				if (kgsoreretaka<0) {
					App.showErrorNotValid(db, "Retak[kg]");
				}
			}
		} catch (Exception e) {
			tKr.setText(0+"");
			App.printErr(e);
			App.showErrorFieldEmpty(db, "Retak[kg]");
			return false;
		}
		
		return true;
		
		
	}
	
	
	@Override
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		initComponent(db);
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		super.build(db);
		buildForm(db);
		actionReset();
	}

	public void initComponent(ODatabaseDocumentTx db){
		Font f=UIManager.getFont("TextField.font");
		f2=f.deriveFont((float) 24.0);
		
		tStartPopulasi=new TextField();
		tStartPopulasi.setEditable(false);
		tBb = new TextField();
		tBr = new TextField();
		tKb=new FormattedTextField(App.paymentFormat);
		tKr=new FormattedTextField(App.paymentFormat);
		tOff = new TextField();
		tWorkDate = new DatePicker();
		tWorkDate.setFormats(App.dateFormat);
		
		
//		listModel=new DefaultListModel();
//		lajurList=new JXList(listModel);
//		lajurList.setHighlighters(HighlighterFactory
//				.createAlternateStriping());
		tLajur=new TextField();
//		popup = new JPopupMenu();
//		popup.add(new JScrollPane(lajurList), BorderLayout.CENTER);

		tModel=new LajurTableModelSearch(db);
		tPopup=new JXTable(tModel);
		
		tPopup.setHighlighters(HighlighterFactory
				.createAlternateStriping());
		tPopup.setShowHorizontalLines(false);
		tPopup.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopup.setTableHeader(null);
//		tPopup.setFont(f2);
		popup = new JPopupMenu();
		popup.setBackground(Color.WHITE);

		
		ss=new JScrollPane(tPopup);
		ss.setBorder(BorderFactory.createLineBorder(App.selected));
		
		popup.setBorder(BorderFactory.createEmptyBorder());
		popup.add(ss, BorderLayout.CENTER);
		
	
		
		
		
		
		String waktu[]={"Pagi", "Sore"};
		modelWaktu=new  DefaultComboBoxModel(waktu);
		cWaktu=new ComboBox(modelWaktu);
		
		// ===========================
		
		tLajur.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (lajur!=null) {
						String pel=tLajur.getText();
						if (pel.equalsIgnoreCase("")) {
							lajur=null;
						}else{
							String [] tmp=pel.split(" | ");
							if (tmp.length<2) {
								lajur=null;
							}else{
								String codep=lajur.field(LajurDao.code);
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
									tStartPopulasi.requestFocus();
								}else{
									lajur=null;
									showPopup(e);
								}
							}
						}
					}
				}else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
					if (tPopup.isVisible()) {
						tPopup.requestFocus();
						if (tPopup.getRowCount()>0) {
							tPopup.getSelectionModel().setSelectionInterval(0, 0);
						}
					}
					
				}else{
					
				}
				
				
			}
			
			

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}



			@Override
			public void keyTyped(KeyEvent e) {
				if (lajur==null) {
					showPopup(e);
				}else{
					String pel=tLajur.getText();
					String [] tmp=pel.split(" | ");
					if (tmp.length<2) {
						lajur=null;
						showPopup(e);
					}
				}
			}

			
			
			
			
		});
		

		
		tPopup.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String tmp1=e.getKeyChar()+"";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp=tLajur.getText()+e.getKeyChar();
					tLajur.setText(tmp);
					tModel.setTextSearch(tLajur.getText().toLowerCase());
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
					tModel.reload(db);
					db.close();
					lajur=null;
					tLajur.requestFocus();
				}else{
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {
						if (tPopup.getSelectedRow()!=-1) {
							ODocument o=(ODocument) tModel.getModel().get(
									tPopup.convertRowIndexToModel(tPopup.getSelectedRow()));
							if (o!=null) {
								String codex=o.field(LajurDao.code);
								String namex=o.field(LajurDao.name);
								tLajur.setText(codex+" | "+namex);
								lajur=o;
							}else{
								lajur=null;
							}
							
							
						}
						popup.setVisible(false);
						tLajur.requestFocus();
					}
					
				}
					

					
				
			}
			
		});
		
		tLajur.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (lajur!=null) {
					String pel=tLajur.getText();
					if (pel.equalsIgnoreCase("")) {
						lajur=null;
					}else{
						String [] tmp=pel.split(" | ");
						if (tmp.length==0) {
							lajur=null;
						}else{
							String codep=lajur.field(LajurDao.code);
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								lajur=null;
								tLajur.setText("");
							}
						}
					}
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
					aksiSetUi(db);
					db.close();
				}
				
			}

			@Override
			public void focusGained(FocusEvent e) {
//				harga.setValue(DataUser.getProduct().field(ProductDao.harga));
			}
		});

		
		
		
		//==================
		
//		tLajur.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE || e.getKeyCode()==KeyEvent.VK_UNDEFINED) {
//					lajur=null;
//				}
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		tLajur.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				// bila pelanggan null maka menampilkan popup
//				// bila tidak null berarti tidak menampilkan popup
//				// karena dianggap sudah benar, sehingga bila inggin mengganti
//				// menggani harus menghapus terlebih dahulu
//				if (lajur == null) {
//
//					// hapus semua elemaent yang terdapat pada list
//					listModel.removeAllElements();
//
//					ODatabaseDocumentTx db = App.getDbd();
//				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//					
//					// ambil dari database dengan batasan 0-10
//					List<ODocument> ps=null;
//					try {
//						ps=App.getLajurDao().getAllByColumnLike(db, LajurDao.name,
//								tLajur.getText()+"%", 0, 50);
//					} catch (Exception e2) {
//					}
//					
//					
//
//					// cek apakah ps null atau hasilnya kosong
//					// bila kosong maka mengambil 10 default
//					if (ps == null || ps.size() == 0) {
//						ps = App.getLajurDao().getAll(db, 0, 50);
//					}
//
//					// masukkan list ps kedalam model
//					// untuk split menggunakan ":    "
//					for (ODocument lajur : ps) {
//						listModel.addElement(lajur.field(LajurDao.code) + ", "
//								+ lajur.field(PelangganDao.name));
//					}
//
//					// pengaturan preferred size pada jlist
//					// - 19 digunakan untuk scrol
//					lajurList.setPreferredSize(new Dimension(tLajur
//							.getSize().width - 4, lajurList
//							.getPreferredSize().height));
//
//					// +8 digunakan untuk meletakkan di bawah persis pd
//					// jtextfiled
//					popup.show(tLajur, 0, 24);
//
//					// pengaturan default
//					lajurList.requestFocus();
//					lajurList.setSelectedIndex(0);
//				}
//
//			}
//		});
//		
//		
//		lajurList.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == 10) {
//					popup.setVisible(false);
//					if (lajurList.getSelectedIndex()!=-1) {
//						String tmp =(String) listModel.getElementAt(lajurList
//								.getSelectedIndex());
//						if (tmp!=null) {
//							
//							tLajur.setText( tmp);
//							tStartPopulasi.requestFocus();
//						}
//					}
//					
//					listModel.removeAllElements();
//				}
//			}
//
//			
//			
//		});
//		
//		
//		tLajur.addFocusListener(new FocusListener() {
//
//			@Override
//			public void focusLost(FocusEvent e) {
//				String value = tLajur.getText();
//				String[] split = ((String) value).split(", ");
//				ODatabaseDocumentTx db = App.getDbd();
//			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//				try {
//					String id = split[0];
//					lajur = App.getLajurDao().getOne(db, LajurDao.code, id);
//				} catch (Exception ex) {
//					lajur=null;
//				}
//				
//				aksiSetUi(db);
//				db.close();
//			}
//
//			@Override
//			public void focusGained(FocusEvent e) {
////				JFrame f=getFrame(tLajur);
////				f.setFolajurraversalPolicy(getFocus());
//				tLajur.selectAll();
//			}
//		});
		
		
		
		
//		cancelButton.setVisible(false);
		
		
		
		tBb.setFont(f2);
		tBr.setFont(f2);
		tKb.setFont(f2);
		tKr.setFont(f2);
		tOff.setFont(f2);
		tWorkDate.setFont(f2);
		cWaktu.setFont(f2);
		tLajur.setFont(f2);
//		lajurList.setFont(f2);
		tStartPopulasi.setFont(f2);
		
//		tWorkDate.getEditor().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				cWaktu.requestFocus();
//			}
//		});
		tWorkDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				aksiSetUi(db);
				db.close();
				cWaktu.requestFocus();
			}
		});
		
		
		cWaktu.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					setDataToUI();
					tLajur.requestFocus();
					tLajur.selectAll();
				}
			}
		});
		cWaktu.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				setDataToUI();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
//		cWaktu.getEditor().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("Asdf");
//				tLajur.requestFocus();
//				tLajur.selectAll();
//			}
//		});
//		cWaktu.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("Asdf");
//				tLajur.requestFocus();
//				tLajur.selectAll();
//			}
//		});
		setFocusEnter(tStartPopulasi, tOff);
		setFocusEnter(tOff, tBb);
		setFocusEnter(tBb, tKb);
		setFocusEnter(tKb, tBr);
		setFocusEnter(tBr, tKr);
		
		tKr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					save.requestFocus();
				}
			}
		});
		
		save=new JButton(L.save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiSave();
				
			}
		})	;
		
		reset=new JButton(L.reset);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionReset();
				
			}
		})	;
		tStartPopulasi.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if (lajurdPertama) {
					tStartPopulasi.setEditable(true);
				}else{
					tStartPopulasi.setEditable(false);
				}
			}
		});
	}
	

	public void showPopup(KeyEvent e){
		String tmp=tLajur.getText()+e.getKeyChar();
		tmp=tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp=null;
		}
		tModel.setTextSearch(tmp);
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		tModel.reload(db);
		db.close();
		Dimension d1=ss.getPreferredSize();
		Dimension d2=tLajur.getSize();//.getPreferredSize();
		d1.width=d2.width;
		d1.height=150;
		ss.setPreferredSize(d1);
		tPopup.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopup.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		popup.show(tLajur, 0, 30);
		tLajur.requestFocus();
	}
	
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			ODocument tmp=null;
			if (workspacel==null) {
				//baru
				if (cWaktu.getSelectedIndex()==0) {
					//pagi
					tmp=App.getLajurdDao().modelSave(lajur, tgla, pupulasia, matia, butirpagibagusa, butirpagiretaka, 0, 0, 
							kgpagibagusa, kgpagiretaka, 0, 0, workspacel, true, lajurdPertama, true, db);
				}else{
					//sore
					tmp=App.getLajurdDao().modelSave(lajur, tgla, pupulasia, matia, 0, 0, butirsorebagusa, butirsoreretaka, 
							0, 0, kgsorebagusa, kgsoreretaka, workspacel, true, lajurdPertama, false, db);
				}
			}else{
				if (cWaktu.getSelectedIndex()==0) {
					//pagi
					tmp=App.getLajurdDao().modelSave(lajur, tgla, pupulasia, matia, butirpagibagusa, butirpagiretaka,  butirsorebagusa, butirsoreretaka, 
							kgpagibagusa, kgpagiretaka, 0, 0, workspacel, false, lajurdPertama, true, db);
				}else{
					//sore
					tmp=App.getLajurdDao().modelSave(lajur, tgla, pupulasia, matia, butirpagibagusa, butirpagiretaka,  butirsorebagusa, butirsoreretaka, 
							kgpagibagusa, kgpagiretaka, kgsorebagusa, kgsoreretaka, workspacel, false, lajurdPertama, false, db);
				}
			}
			for (WidgetAdapter w: widgeds) {
				w.modelWidgetChange(tmp);
			}
			actionReset();
			App.showSaveOk();
			

			
		}
		db.close();
		
	}


	public void buildForm(ODatabaseDocumentTx db) {
		JPanel p1=new JPanel(new BorderLayout());
		JPanel p2=new JPanel(new BorderLayout());
		
//		p1.setBorder(new TitledBorder("Identifer"));
//		p2.setBorder(new TitledBorder("Produksi"));
		
		
		FormLayout layout = new FormLayout(
				"l:100dlu,   	4dlu,   	f:max(180dlu;p):g(.4),    	2dlu," +
				"",
				"p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu");

		FormBuilder builder = new FormBuilder(layout, false);
		
		int i=-1;
		
		
		
		

		builder.append( f2, LajurdDao.ftgl, tWorkDate, 1, i=i+2, 1);
		builder.append( f2, "Waktu", cWaktu, 1, i=i+2, 1);
		builder.append( f2,LajurdDao.flajur, tLajur, 1, i=i+2, 1);
		
		

		
		builder.append( f2, LajurdDao.fpupulasi, tStartPopulasi, 1,i=i+2, 1);
		
		builder.append( f2, LajurdDao.fmati, tOff, 1, i=i+2, 1);
		
		JPanel ptmp0=builder.getPanel();
		ptmp0.setBackground(Color.WHITE);
		p1.add(ptmp0,  BorderLayout.CENTER);
		layout = new FormLayout(
				"l:100dlu,   	4dlu,   	f:max(100dlu;p):g(.4),4dlu, f:max(100dlu;p):g(.4),    	5dlu," +
				"",
				"p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   f:24dlu,3dlu");
		builder = new FormBuilder(layout, true);
		i=-1;
		
		builder.append( f2,"Bagus[btr]", tBb, 1, i=i+2, 3);
		builder.append( f2, "Bagus[kg]", tKb, 1, i=i+2, 3);
		
		builder.append( f2, "Retak[btr]", tBr, 1, i=i+2,
				3);
		builder.append( f2, "Retak[kg]", tKr, 1, i=i+2,
				3);
		
		builder.append( save, 3, i=i+2);
		
		builder.append( reset, 5, i);
		
		JPanel ptmp1=builder.getPanel();
		ptmp1.setBackground(Color.WHITE);
		p2.add(ptmp1,  BorderLayout.CENTER);
		builder = new FormBuilder(layout, false);
		
		 layout = new FormLayout(
					"r:p:g,   	 r:p:g,   	 15dlu,",

					"p,3dlu");

			builder = new FormBuilder(layout, false);
			
			
			builder.append( p1, 1, 1);
			builder.append( p2, 2, 1);
			

		


		
		
		JPanel p = builder.getPanel();
		p.setBackground(Color.WHITE);
//		JScrollPane pane=new JScrollPane(p);
//		pane.setBackground(Color.WHITE);
		JLabel label=new JLabel(App.getIcon(L.iconEdit16));
		label.setText("   Masukkan Data Produksi");
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(15,30, 5, 5)));
		panel.add(p, BorderLayout.CENTER);
		panel.add(label, BorderLayout.NORTH);
		//panel.setBackground(Color.WHITE);
	}

	@Override
	public void load(ODocument model) {
//		if (model==null) {
//			code.setText("");
//			name.setText("");
//			note.setText("");
//		}else
//		if (model.field("@class").equals("Kandang")) {
//			this.model=model;
//			code.setText(model.field(KandangDao.code)+"");
//			name.setText(model.field(KandangDao.name)+"");
//			note.setText(model.field(KandangDao.note)+"");
//			
//		}
		
	}


	@Override
	public void modelWidgetChange(ODocument model) {
		load(model);
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	


	@Override
	public void actionReset() {
		clearText(tBb);
		clearText(tBr);
		clearText(tKb);
		clearText(tKr);
		clearText(tOff);
		tWorkDate.setDate(new Date());
		tStartPopulasi.setEditable(false);
		//saveButton.setText("Save");
		workspacel=null;
		lajur=null;
		clearText(tLajur);
		clearText(tStartPopulasi);
		setCursor();

	}
	public void setCursor() {
		tLajur.requestFocus();

	}
	
	private boolean lajurdPertama=false;
	public void aksiSetUi(ODatabaseDocumentTx db){
		if (lajur==null) {
			tLajur.setText("");
			lajurdPertama=false;
			setDataToUI();
		}else{
				Date tmp=tWorkDate.getDate();
				if (tmp==null) {
					tWorkDate.setDate(new Date());
					tmp=tWorkDate.getDate();
				}
				if (tmp!=null) {
					ODocument wtmp=App.getLajurdDao().getOne(db, LajurdDao.tgl, tmp, LajurdDao.lajur, lajur.getIdentity());
					if (wtmp!=null) {
						if (workspacel==null) {
							workspacel=wtmp;
							setDataToUI();
						}else{
							if (!workspacel.getIdentity().equals(wtmp.getIdentity())) {
								setDataToUI();
							}
						}
					}else{
						workspacel=null;
						lajurdPertama=false;
						setDataToUI();
					}
					
					long jml=App.getLajurdDao().getCountByColumn(db, LajurdDao.lajur, lajur.getIdentity());
					
					if (jml==0) {
						//lajurd baru
						tStartPopulasi.setEditable(true);
						lajurdPertama=true;
					}else{
						
						if (workspacel!=null) {
							ODocument otmp=App.getLajurdDao().getOneOrderByTgl(db, LajurdDao.lajur, lajur.getIdentity());
//							long id=dao.idFirst(workspacel.getLajur());
							if (otmp.getIdentity().equals(workspacel.getIdentity())) {
								tStartPopulasi.setEditable(true);
								lajurdPertama=true;
							}else{
								tStartPopulasi.setEditable(false);
								lajurdPertama=false;
							}
						}else{
							tStartPopulasi.setEditable(false);
							lajurdPertama=false;
						}
					}
					
				}else{
					lajurdPertama=false;
				}
				
				

		}
	}
	
	public void setDataToUI() {
		if (workspacel!=null) {
			tStartPopulasi.setText(workspacel.field(LajurdDao.pupulasi)+"");
			tOff.setText(workspacel.field(LajurdDao.mati)+"");
			tWorkDate.setDate((Date) workspacel.field(LajurdDao.tgl));
			
			if (cWaktu.getSelectedIndex()==0) {
				//pagi
				tBb.setText(workspacel.field(LajurdDao.butirpagibagus)+"");
				tBr.setText(workspacel.field(LajurdDao.butirpagiretak)+"");
				tKb.setText(workspacel.field(LajurdDao.kgpagibagus)+"");
				tKr.setText(workspacel.field(LajurdDao.kgpagiretak)+"");
			}else{
				//sore
				tBb.setText(workspacel.field(LajurdDao.butirsorebagus)+"");
				tBr.setText(workspacel.field(LajurdDao.butirsoreretak)+"");
				tKb.setText(workspacel.field(LajurdDao.kgsorebagus)+"");
				tKr.setText(workspacel.field(LajurdDao.kgsoreretak)+"");
			}
		}else{
			tBb.setText(0+"");
			tBr.setText(0+"");
			tKb.setText(0+"");
			tKr.setText(0+"");
			tStartPopulasi.setText("");
			tOff.setText("0");
		}
		
		
	}


//	@Override
//	public Component getPanelForm() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public Component getLabelTitle() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
