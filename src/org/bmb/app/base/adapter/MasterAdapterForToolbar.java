package org.bmb.app.base.adapter;

public interface MasterAdapterForToolbar {
	public void actionAdd();
	public void actionEdit();
	public void actionView();
	public void actionReload();
	public void actionDel();
	public void actionPrint();
	public String getTitle();
	public String getUrlIcon();
}
