package org.geogebra.desktop.export;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.util.FileExtensions;
import org.geogebra.desktop.gui.GuiManagerD;
import org.geogebra.desktop.gui.util.AnimatedGifEncoder;
import org.geogebra.desktop.main.AppD;
import org.geogebra.desktop.util.FrameCollector;

/**
 * Dialog to export a slider as animation.
 * 
 * TODO What happens with the slider context menu entry
 */
public class AnimationExportDialog extends JDialog {
	/**	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Application instance.
	 */
	private AppD app;

	/**
	 * List with all sliders in the worksheet.
	 */
	private JComboBox cbSliders;

	/**
	 * Loop in Animation?
	 */
	private JCheckBox cbLoop;

	/**
	 * Time between two frames.
	 */
	private JTextField tfTimeBetweenFrames;

	/**
	 * Buttons to close the dialog or start the actual export.
	 */
	private JButton cancelButton, exportButton;

	/**
	 * Construct dialog.
	 * 
	 * @param app
	 *            App instance
	 */
	public AnimationExportDialog(AppD app) {
		super(app.getFrame(), false);
		this.app = app;

		initGUI();
	}

	/**
	 * Initialize the GUI.
	 */
	private void initGUI() {
		setResizable(false);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// slider selection
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(app.getPlain("Slider") + ":"));

		// combo box with all sliders
		DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
		TreeSet<GeoElement> sortedSet = app.getKernel().getConstruction()
				.getGeoSetNameDescriptionOrder();

		// lists for combo boxes to select input and output objects
		// fill combobox models
		Iterator<GeoElement> it = sortedSet.iterator();
		while (it.hasNext()) {
			GeoElement geo = it.next();
			if (geo.isGeoNumeric() && ((GeoNumeric) geo).isIntervalMinActive()
					&& ((GeoNumeric) geo).isIntervalMaxActive()) {
				comboModel.addElement(geo);
			}
		}
		cbSliders = new JComboBox(comboModel);
		panel.add(cbSliders);

		contentPane.add(panel, BorderLayout.NORTH);

		// options
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createTitledBorder(app.getMenu("Options")));

		panel.add(new JLabel(app.getPlain("TimeBetweenFrames") + ":"));

		tfTimeBetweenFrames = new JTextField(5);
		tfTimeBetweenFrames.setText("500");
		panel.add(tfTimeBetweenFrames);

		panel.add(new JLabel("ms"));

		panel.add(Box.createHorizontalStrut(10));

		cbLoop = new JCheckBox(app.getPlain("AnimationLoop"));
		panel.add(cbLoop);

		contentPane.add(panel, BorderLayout.CENTER);

		// buttons
		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		exportButton = new JButton(app.getMenu("Export"));
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});

		// disable controls if there are no sliders
		if (comboModel.getSize() == 0) {
			cbLoop.setEnabled(false);
			tfTimeBetweenFrames.setEnabled(false);
			exportButton.setEnabled(false);
			// leave cbSliders active to let the user see that there are no
			// sliders
		}

		cancelButton = new JButton(app.getMenu("Cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		panel.add(exportButton);
		panel.add(cancelButton);

		contentPane.add(panel, BorderLayout.SOUTH);

		setTitle(app.getPlain("AnimatedGIFExport"));
		pack();
		setLocationRelativeTo(app.getMainComponent());
		setVisible(true);
	}

	/**
	 * Logic for exporting the selected slider as animation.
	 */
	public void export() {
		int timeBetweenFrames = 500;

		// try to parse textfield value (and check that it is > 0)
		try {
			timeBetweenFrames = Integer.parseInt(tfTimeBetweenFrames.getText());

			// negative values or zero are bad too
			if (timeBetweenFrames <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			app.showError("InvalidInput", tfTimeBetweenFrames.getText());
			return;
		}

		app.getKernel().getAnimatonManager().stopAnimation();

		File file = ((GuiManagerD) app.getGuiManager()).showSaveDialog(
				FileExtensions.GIF,
				null, app.getPlain("gif") + " " + app.getMenu("Files"), true,
				false);

		GeoNumeric num = (GeoNumeric) cbSliders.getSelectedItem();

		int type = num.getAnimationType();
		double min = num.getIntervalMin();
		double max = num.getIntervalMax();

		double val;

		double step;
		int n;

		switch (type) {
		case GeoElement.ANIMATION_DECREASING:
			step = -num.getAnimationStep();
			n = (int) ((max - min) / -step);
			if (Kernel.isZero(((max - min) / -step) - n))
				n++;
			if (n == 0)
				n = 1;
			val = max;
			break;
		case GeoElement.ANIMATION_OSCILLATING:
			step = num.getAnimationStep();
			n = (int) ((max - min) / step) * 2;
			if (Kernel.isZero(((max - min) / step * 2) - n))
				n++;
			if (n == 0)
				n = 1;
			val = min;
			break;
		default: // GeoElement.ANIMATION_INCREASING:
					// GeoElement.ANIMATION_INCREASING_ONCE:
			step = num.getAnimationStep();
			n = (int) ((max - min) / step);
			if (Kernel.isZero(((max - min) / step) - n))
				n++;
			if (n == 0)
				n = 1;
			val = min;
		}

		final AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
		gifEncoder.start(file);

		gifEncoder.setDelay(timeBetweenFrames); // miliseconds
		if (cbLoop.isSelected()) {
			gifEncoder.setRepeat(0);
		}
		FrameCollector collector = new FrameCollector() {

			public void addFrame(BufferedImage img) {
				gifEncoder.addFrame(img);

			}

			public void finish() {
				gifEncoder.finish();

			}
		};
		// hide dialog
		setVisible(false);

		app.setWaitCursor();

		try {
			app.exportAnimatedGIF(app.getActiveEuclidianView(), collector, num,
					n, val, min, max, step);

		} catch (Exception ex) {
			app.showError("SaveFileFailed");
			ex.printStackTrace();
		} finally {
			app.setDefaultCursor();
		}
	}
}
