package geogebra.touch.gui.laf;

import geogebra.common.main.SavedStateListener;
import geogebra.touch.gui.BrowseGUI;
import geogebra.touch.gui.TabletGUI;
import geogebra.touch.gui.WorksheetGUI;
import geogebra.touch.gui.elements.StandardImageButton;
import geogebra.touch.gui.elements.header.BrowseHeaderPanel;
import geogebra.touch.gui.elements.header.TabletHeaderPanel;
import geogebra.touch.gui.elements.header.WorksheetHeader;
import geogebra.touch.gui.elements.stylebar.StyleBar;
import geogebra.touch.model.TouchModel;
import geogebra.touch.utils.OptionType;

public interface LookAndFeel extends SavedStateListener {

	public void buildTabletHeader(TouchModel touchModel);

	public WorksheetHeader buildWorksheetHeader(WorksheetGUI worksheetGUI);
	
	public BrowseHeaderPanel buildBrowseHeader(BrowseGUI browseGUI);

	public int getTabletHeaderHeight();
	
	public int getBrowseHeaderHeight();

	public int getToolBarHeight();

	public int getContentWidgetHeight();

	public DefaultResources getIcons();

	public int getPaddingLeftOfDialog();

	public TabletHeaderPanel getTabletHeaderPanel();

	public boolean isMouseDownIgnored();

	public void setTitle(String title);

	public boolean isShareSupported();

	public void updateUndoSaveButtons();

	public StandardImageButton setStyleBarButtonHandler(
			StandardImageButton button, StyleBar styleBar, String process);

	public StandardImageButton setOptionalButtonHandler(
			StandardImageButton button, StyleBar styleBar,
			OptionType captionstyle);

	public StandardImageButton setStyleBarShowHideHandler(
			StandardImageButton button, StyleBar styleBar);

	public StandardImageButton setAlgebraButtonHandler(
			StandardImageButton arrow, TabletGUI gui);
}
