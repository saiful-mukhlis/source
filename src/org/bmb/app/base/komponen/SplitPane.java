package org.bmb.app.base.komponen;

import java.awt.Component;

import javax.swing.JSplitPane;

import com.bmb.app.global.App;

public class SplitPane extends JSplitPane {

	public SplitPane(int newOrientation, Component newLeftComponent,
			Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		setDividerSize(1);
		setOneTouchExpandable(true);
		setBorder(App.borderWhite);
	}


}
