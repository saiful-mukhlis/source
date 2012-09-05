package org.bmb.app.base.abstrak.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import org.bmb.app.base.adapter.WindowAdapterForAction;
import org.bmb.app.base.adapter.action.SaveActionListener;

public class SaveAction extends AbstractAction {

	private List<SaveActionListener> actions=new ArrayList<>();
	private WindowAdapterForAction window;
	
	public SaveAction(WindowAdapterForAction window) {
		super();
		this.window = window;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for (SaveActionListener a : actions) {
			if (a.getId()==window.getIdActive()) {
				a.actionSave();
			}
		}
	}
	public List<SaveActionListener> getActions() {
		return actions;
	}
	public void setActions(List<SaveActionListener> actions) {
		this.actions = actions;
	}
	public WindowAdapterForAction getWindow() {
		return window;
	}
	public void setWindow(WindowAdapterForAction window) {
		this.window = window;
	}

}
