package org.bmb.app.base.abstrak.action;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.bmb.app.base.komponen.Komponent;
import org.noos.xing.mydoggy.*;
import org.noos.xing.yasaf.view.ViewContextChangeListener;
import org.noos.xing.yasaf.view.event.ViewContextChangeEvent;


public class AddContentAction implements ActionListener,
		ViewContextChangeListener {

	public AddContentAction(Komponent komponent) {
		this.komponent=komponent;
//		this.toolWindowManager = komponent.getToolWindowManager();
//		this.contentId = komponent.getTypeMaster();
//		this.title = komponent.getTitle();
//		this.icon = komponent.getIcon();
//		this.component = component;
//		this.tooltip = tooltip;
//		this.mnemonic = mnemonic;
	}

	public void contextChange(ViewContextChangeEvent evt) {
		actionPerformed(null);
	}

	public void actionPerformed(ActionEvent e) {
		ContentManager contentManager = komponent.getToolWindowManager().getContentManager();
		Content content = contentManager.getContent(komponent.getTypeMaster());
		if (content == null) {
			komponent=komponent.createComponent(komponent);
			content = contentManager.addContent(komponent.getTypeMaster(), komponent.getTitle(), komponent.getIcon(),
					komponent.getComponent(), komponent.getTitle()); // yang terakhir ini tooltip
			content.getContentUI().setAlwaysOnTop(false);
			if (((int)komponent.getShortCut()) != -1)
				content.setMnemonic(((int)komponent.getShortCut()));
			content.setSelected(true);
		} else {
			content.setSelected(true);
		}
	}
	


	private Komponent komponent;

//	private ToolWindowManager toolWindowManager;
//	private String contentId;
//	private String title;
//	private Icon icon;
//	private Component component;
//	private String tooltip;
//	private int mnemonic;
}
