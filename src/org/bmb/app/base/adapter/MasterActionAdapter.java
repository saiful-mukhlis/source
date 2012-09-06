package org.bmb.app.base.adapter;

public interface MasterActionAdapter extends WidgetAdapter {
	public void actionAdd();
	public void actionEdit();
	public void actionView();
	public void actionReload();
	public void actionDel();
	public void actionPrint();
	
	public boolean getAdd();
	public boolean getHapus();
	public boolean getLihat();
	public boolean getEdit();
	public boolean getPrint();
	
	public String getTitle();
	public String getUrlIcon();
}
