package org.bmb.app.base.abstrak.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import org.bmb.app.base.adapter.WindowAdapterForAction;
import org.bmb.app.base.adapter.action.DeleteActionListener;

public class DeleteAction extends AbstractAction {

	private List<DeleteActionListener> actions=new ArrayList<>();
	private WindowAdapterForAction window;
	
	public DeleteAction(WindowAdapterForAction window) {
		super();
		this.window = window;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		for (DeleteActionListener a : actions) {
			if (a.getId()==window.getIdActive()) {
				a.actionDelete();
			}
		}
	}
	public List<DeleteActionListener> getActions() {
		return actions;
	}
	public void setActions(List<DeleteActionListener> actions) {
		this.actions = actions;
	}
	public WindowAdapterForAction getWindow() {
		return window;
	}
	public void setWindow(WindowAdapterForAction window) {
		this.window = window;
	}

}
