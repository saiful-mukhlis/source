package org.bmb.app.base.adapter;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;

import org.bmb.app.base.komponen.Komponent;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface WindowAdapter extends WidgetAdapter {
public void initToolbar(ODatabaseDocumentTx db);
public void initMenu(ODatabaseDocumentTx db);
public void initContext(ODatabaseDocumentTx db);
public void showWelcome();
public Component getComponentWelcome();
public void seta(ODatabaseDocumentTx db);
public void buildMaster(ODatabaseDocumentTx db);
public void buildActions(ODatabaseDocumentTx db);
public ToolWindowManager getToolWindowManager();

//public AddContentAction getUsrAca();
public AddContentAction getWelcomeAca();
//public AddContentAction getKandangAca();

public List<Komponent> getKomponents();
public HashMap<String, Komponent> getKomponentMaps();

public void showToolbar();

public void login();

public void actionClose();
public void actionExit();
public void actionPrint();
public void actionAdd();
public void actionEdit();
public void actionDel();
public void actionView();

public void actionReg();
public void actionAbout();

}
