package org.bmb.app.base.adapter;

import java.awt.Component;

import javax.swing.JPanel;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface ComponetNewAdapter {
public void addWidgetModel(WidgetAdapter widget);
public JPanel getPanel();
public void actionReset();
public void init(ODatabaseDocumentTx db);
public void modelWidgetChange(ODocument model);
public Component getPanelForm();
public Component getLabelTitle();

}
