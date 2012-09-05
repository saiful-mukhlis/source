package org.bmb.app.base.komponen;

import java.awt.Color;

import javax.swing.JToolBar;

import com.jgoodies.forms.layout.FormLayout;

public class ToolbarSmall extends JToolBar {

	public ToolbarSmall() {
		super();
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",

				"p,3dlu");
		setLayout(layout);

		setBackground(Color.WHITE);
	}

}
