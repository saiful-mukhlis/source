package org.bmb.app.base.komponen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.bmb.app.base.adapter.MasterActionAdapter;


import com.bmb.app.global.App;
import com.bmb.app.lang.L;
import com.jgoodies.forms.layout.CellConstraints;


public class ToolbarSmallR extends ToolbarSmall {

	protected JButton reload;
	
	
	public ToolbarSmallR(MasterActionAdapter master) {
		super(master);
		build();
		buildActions();
	}

//	public ToolbarSmallRP() {
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
	}
	
	public void initComponent(){
		reload = new JButton(App.getIcon(L.iconReload16));
	}
	
	public void buildActions(){



		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionReload();
			}
		});
		
		
	}

	public JButton getReload() {
		return reload;
	}

	public void setReload(JButton reload) {
		this.reload = reload;
	}



	
}
