package org.bmb.app.base.komponen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.bmb.app.base.adapter.MasterAdapterForToolbar;

import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.jgoodies.forms.layout.CellConstraints;


public class ToolbarSmallRLTED extends ToolbarSmall {

	protected JButton reload;
	protected JButton add;
	protected JButton edit;
	protected JButton del;
	protected JButton view;
	
	protected MasterAdapterForToolbar master;
	
	public ToolbarSmallRLTED(MasterAdapterForToolbar master) {
		super();
		this.master = master;
		build();
		buildActions();
	}

//	public ToolbarSmallRLTED() {
//		super();
//		build();
//	}
	
	public void build(){
		initComponent();
		JLabel label = new JLabel(App.getIcon(master.getUrlIcon()));
		label.setText(master.getTitle());
		CellConstraints cc = new CellConstraints();
		add(label, cc.xy(2, 1));
		add(reload, cc.xy(12, 1));
		add(view, cc.xy(14, 1));
		add(add, cc.xy(16, 1));
		add(edit, cc.xy(18, 1));
		add(del, cc.xy(20, 1));
		
		setBorder(App.borderBlackBottom);
	}
	
	public void initComponent(){
		reload = new JButton(App.getIcon(L.iconReload16));
		add = new JButton(App.getIcon(L.iconTambah16));
		edit = new JButton(App.getIcon(L.iconEdit16));
		del = new JButton(App.getIcon(L.iconHapus16));
		view = new JButton(App.getIcon(L.iconView16));
		
		reload.setBackground(Color.WHITE);
		add.setBackground(Color.WHITE);
		edit.setBackground(Color.WHITE);
		view.setBackground(Color.WHITE);
		del.setBackground(Color.WHITE);
	}
	
	public void buildActions(){


		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionAdd();
			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionEdit();
			}
		});

		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionView();
			}
		});
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionReload();
			}
		});
		
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionDel();
				
			}
		});
		
		edit.setEnabled(false);
		view.setEnabled(false);
		del.setEnabled(false);
	}

	public JButton getReload() {
		return reload;
	}

	public void setReload(JButton reload) {
		this.reload = reload;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public JButton getEdit() {
		return edit;
	}

	public void setEdit(JButton edit) {
		this.edit = edit;
	}

	public JButton getDel() {
		return del;
	}

	public void setDel(JButton del) {
		this.del = del;
	}

	public JButton getView() {
		return view;
	}

	public void setView(JButton view) {
		this.view = view;
	}


	public MasterAdapterForToolbar getMaster() {
		return master;
	}

	public void setMaster(MasterAdapterForToolbar master) {
		this.master = master;
	}

	
}
