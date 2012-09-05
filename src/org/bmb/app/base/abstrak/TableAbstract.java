package org.bmb.app.base.abstrak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.bmb.app.base.adapter.MasterAdapterForEfectWidget;
import org.bmb.app.base.adapter.PagingAdapter;
import org.bmb.app.base.adapter.TableAdapter;
import org.bmb.app.base.adapter.TableModelAdapter;
import org.bmb.app.base.adapter.WidgetAdapter;
import org.bmb.app.base.standart.PagingDefault;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public abstract class TableAbstract implements TableAdapter {
	
	public final static int WIDGET_1=0;
	public final static int WIDGET_2=1;
	public final static int WIDGET_3=2;
	public final static int WIDGET_4=3;
	
	protected JPanel panel;
	protected JTable table;
	protected TableModelAdapter tableModel;
	protected MasterAdapterForEfectWidget master;

	protected int typeEfectWidget=WIDGET_1;

	// protected WindowAdapter window;
	protected List<WidgetAdapter> widgets = new ArrayList<WidgetAdapter>();

	protected PagingAdapter paging;

	public void init(ODatabaseDocumentTx db) {
		initTableModel(db);
		tableModel.load(db);
		initPaging();
		initComponent();
		initTable();
		setAksiListenerTable();
	}

	public void build(ODatabaseDocumentTx db) {
		init(db);

		paging.loadFirst(db);
		setLayout();
	}

	public void initComponent() {
		panel = new JPanel();
	}

	public void initPaging() {
		paging = new PagingDefault(tableModel);
	}

	public void initTable() {
		setTable(new JXTable(tableModel));
		if (getTable() instanceof JXTable) {
			setJXTable((JXTable) getTable());
			JXTable t=(JXTable) getTable();
			t.setShowHorizontalLines(false);
			t.setBorder(App.borderWhite);
		}
		
		
		if (master!=null) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
		}
	}


	public void setJXTable(JXTable table) {
		table.setHorizontalScrollEnabled(true);
		table.setColumnControlVisible(true);
		table.setHighlighters(HighlighterFactory.createSimpleStriping());

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(App.selected);
		// table.setSelectionForeground(SystemColor.BLACK);
	}

	private boolean isDoubleClick = true;

	public void setAksiListenerTable() {
		getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickedOnTable(e);
				if (e.getClickCount() == 2) {
					if (isDoubleClick) {
						doubleClickedOnTable(e);
						isDoubleClick = false;
					} else {
						isDoubleClick = true;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				mouseReleasedOnTable(e);
			}

		});
		getTable().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				keyPressedOnTable(e);
			}
		});
	}

	protected void keyPressedOnTable(KeyEvent e) {
		selected();

	}

	protected void mouseReleasedOnTable(MouseEvent e) {
		 selected();

	}

	protected void doubleClickedOnTable(MouseEvent e) {
		selected();

	}

	protected void clickedOnTable(MouseEvent e) {
		selected();
	}

	public void setLayout() {
		panel.setLayout(new BorderLayout());
		JScrollPane ss=new JScrollPane(getTable());
		ss.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panel.add(ss, BorderLayout.CENTER);
		panel.add(paging.getPanelPaging(), BorderLayout.SOUTH);
	}

	public void selected() {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				selectedValid(i);
			} else {
				selectedNotValid();
			}

		}
	}

	public void aksiDelete(ODatabaseDocumentTx db) {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				if (JOptionPane.showConfirmDialog(null,
						"Apakah Anda yakin akan menghapus?",
						 "Konfirmasi Delete",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					int itmp=table.convertRowIndexToModel(i);
					ODocument tmp=(ODocument) tableModel.getModel().get(itmp	);
					try {
//						tmp.delete();
						tableModel.getDao().delete(db, tmp);
						tableModel.getModel().remove(itmp);
						tableModel.fireTableDataChanged();
						selected();
					} catch (Exception e) {
						App.printErr(e);
					}
				}
			} 
		}

	}

	public void selectedNotValid() {
		if (widgets.size() != 0) {
			for (WidgetAdapter view : widgets) {
				if (view!=null) {
					view.modelWidgetChange(null);
				}
			}
		}
	}

	public void selectedValid(int i) {
		if (widgets.size() != 0) {
			for (WidgetAdapter view : widgets) {
				if (view!=null) {
					view.modelWidgetChange((ODocument) tableModel.getModel().get(
							table.convertRowIndexToModel(i)));
				}
			}
		}
	}

	public void addObjOnTableModel(ODocument model) {
		tableModel.addObjModel(model);
		selected();
	}

	public void editObjOnTableModel(ODocument model) {
		tableModel.editObjModel(model);
		selected();
	}

	public void load(ODatabaseDocumentTx db) {
		tableModel.load(db);
		selected();
	}

	public void reload(ODatabaseDocumentTx db) {
		tableModel.reload(db);
		selected();
	}

	public Window getWindow(Object o) {
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return getWindow(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	public JFrame getFrame(Object o) {
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return getFrame(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	public Container getContainer(Object o) {
		if (o instanceof Container) {
			return ((Container) o);
		} else {
			if (o instanceof Component) {
				return getContainer(((Component) o).getParent());
			} else {
				return null;
			}
		}
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void addWidgetChange(WidgetAdapter widget) {
		widgets.add(widget);
	}
	
	@Override
	public void modelWidgetAdd(ODocument model) {
			tableModel.addObjModel(model);
		
	}
	@Override
	public void modelWidgetChange(ODocument model) {
		tableModel.fireTableDataChanged();
	}

	public MasterAdapterForEfectWidget getMaster() {
		return master;
	}

	public void setMaster(MasterAdapterForEfectWidget master) {
		this.master = master;
	}

	public int getTypeEfectWidget() {
		return typeEfectWidget;
	}

	public void setTypeEfectWidget(int typeEfectWidget) {
		this.typeEfectWidget = typeEfectWidget;
	}
}
