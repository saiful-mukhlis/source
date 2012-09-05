package org.bmb.app.base.komponen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.bmb.app.base.adapter.MasterAdapterForToolbar;

import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.jgoodies.forms.layout.CellConstraints;


public class ToolbarSmallRLE extends ToolbarSmall {

	protected JButton reload;
	protected JButton edit;
	protected JButton view;
	
	protected MasterAdapterForToolbar master;
	
	public ToolbarSmallRLE(MasterAdapterForToolbar master) {
		super();
		this.master = master;
		build();
		buildActions();
	}

//	public ToolbarSmallRLE() {
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
		add(edit, cc.xy(18, 1));
	}
	
	public void initComponent(){
		reload = new JButton(App.getIcon(L.iconReload16));
		edit = new JButton(App.getIcon(L.iconEdit16));
		view = new JButton(App.getIcon(L.iconView16));
	}
	
	public void buildActions(){


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
		
		
		edit.setEnabled(false);
		view.setEnabled(false);
	}

	public JButton getReload() {
		return reload;
	}

	public void setReload(JButton reload) {
		this.reload = reload;
	}


	public JButton getEdit() {
		return edit;
	}

	public void setEdit(JButton edit) {
		this.edit = edit;
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
