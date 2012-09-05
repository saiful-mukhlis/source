package org.bmb.app.base.abstrak.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import org.bmb.app.base.adapter.WindowAdapterForAction;
import org.bmb.app.base.adapter.action.EditActionListener;

public class EditAction extends AbstractAction {

	private List<EditActionListener> actions=new ArrayList<>();
	private WindowAdapterForAction window;
	
	public EditAction(WindowAdapterForAction window) {
		super();
		this.window = window;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for (EditActionListener a : actions) {
			if (a.getId()==window.getIdActive()) {
				a.actionEdit();
			}
		}
	}
	public List<EditActionListener> getActions() {
		return actions;
	}
	public void setActions(List<EditActionListener> actions) {
		this.actions = actions;
	}
	public WindowAdapterForAction getWindow() {
		return window;
	}
	public void setWindow(WindowAdapterForAction window) {
		this.window = window;
	}

}
