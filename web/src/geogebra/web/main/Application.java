package geogebra.web.main;

import com.google.gwt.canvas.client.Canvas;

import geogebra.common.euclidian.EuclidianViewInterface2D;
import geogebra.common.euclidian.EuclidianViewInterfaceSlim;
import geogebra.common.gui.GuiManager;
import geogebra.common.kernel.AbstractKernel;
import geogebra.common.main.AbstractApplication;
import geogebra.common.util.ResourceBundleAdapter;
import geogebra.web.euclidian.EuclidianController;
import geogebra.web.euclidian.EuclidianView;
import geogebra.web.io.MyXMLio;
import geogebra.web.kernel.Kernel;
public class Application extends AbstractApplication {
	
	private Kernel kernel;
	private EuclidianView euclidianview;
	private EuclidianController euclidiancontroller;
	
	MyXMLio myXMLio;
	
	private boolean[] showAxes = {true,true};
	private boolean showGrid = false;


	@Override
	public ResourceBundleAdapter initAlgo2IntergeoBundle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceBundleAdapter initAlgo2CommandBundle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommand(String cmdName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlain(String cmdName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlain(String cmdName, String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlain(String cmdName, String param, String param2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlain(String cmdName, String param, String param2,
	        String param3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlain(String cmdName, String param, String param2,
	        String param3, String param4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlain(String cmdName, String param, String param2,
	        String param3, String param4, String param5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenu(String cmdName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getError(String cmdName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRightToLeftReadingOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTooltipFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearTooltipFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isApplet() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void storeUndoInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUsingFullGui() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean showView(int view) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void callJavaScript(String jsFunction, Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUsingLocalizedLabels() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean languageIs(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean letRedefine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String translationFix(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void traceToSpreadsheet(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetTraceColumn(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isReverseNameDescriptionLanguage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockUpdateScripts() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBlockUpdateScripts(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getScriptingLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScriptingLanguage(String lang) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInternalCommand(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showError(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isScriptingDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean useBrowserForJavaScript() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initJavaScriptViewWithoutJavascript() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getTraceXML(Object geoElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSelectedGeo(Object geoElement, boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeLayer(Object ge, int layer, int layer2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getExternalImage(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean freeMemoryIsCritical() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long freeMemory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLabelingStyle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOrdinalNumber(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getXmin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getXmax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getXminForFunctions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getXmaxForFunctions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double countPixels(double min, double max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxLayerUsed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getAlgebraView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EuclidianViewInterfaceSlim getEuclidianView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EuclidianViewInterfaceSlim getActiveEuclidianView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EuclidianViewInterface2D createEuclidianViewForPlane(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRightToLeftDigits() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShowingEuclidianView2() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AbstractKernel getKernel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiManager getImageManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String translateCommand(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evalScript(AbstractApplication app, String script, String arg) {
		// TODO Auto-generated method stub

	}

	public void init(Canvas canvas) {
		kernel = new Kernel(this);
		
		euclidiancontroller = new EuclidianController(kernel);
		euclidianview = new EuclidianView(canvas, euclidiancontroller, showAxes, showGrid);
		
		myXMLio = new MyXMLio(kernel, kernel.getConstruction());
	    // TODO Auto-generated method stub
	    
    }

}
