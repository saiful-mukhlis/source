package org.bmb.app.base.komponen;

import java.awt.Color;

import javax.swing.JToolBar;

import org.bmb.app.base.adapter.MasterActionAdapter;
import org.bmb.app.base.adapter.ToolbarSmallAdapter;

import com.jgoodies.forms.layout.FormLayout;

public class ToolbarSmall extends JToolBar implements ToolbarSmallAdapter {
	public MasterActionAdapter master;
	public ToolbarSmall(MasterActionAdapter master) {
		super();
		this.master=master;
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",

				"p,3dlu");
		setLayout(layout);

		setBackground(Color.WHITE);
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFalseAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStateByHakAkses() {
		// TODO Auto-generated method stub
		
	}

	public MasterActionAdapter getMaster() {
		return master;
	}

	public void setMaster(MasterActionAdapter master) {
		this.master = master;
	}

	
	
}
