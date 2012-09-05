package org.bmb.app.base.adapter;

//import java.awt.Component;

public interface FormAdapter extends WidgetAdapter{
	public void actionReset();
	public void addWidgetModel(WidgetAdapter table);
//	public Component getPanelForm();
//	public Component getLabelTitle();
	public void setMaster(MasterAdapterForEfectWidget master) ;
	public void setTypeEfectWidget(int typeEfectWidget);
	public void requestDefaultFocus();
}
