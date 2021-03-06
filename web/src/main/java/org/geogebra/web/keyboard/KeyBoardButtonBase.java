package org.geogebra.web.keyboard;

import org.geogebra.common.euclidian.event.PointerEventType;
import org.geogebra.web.html5.gui.util.ClickEndHandler;
import org.geogebra.web.html5.gui.util.ClickStartHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * A button of the {@link OnScreenKeyBoardBase}.
 */
public class KeyBoardButtonBase extends SimplePanel {

	private String caption;

	/**
	 * the feedback that is returned when the button is clicked
	 */
	protected String feedback;

	/**
	 * the label that is displayed on the button
	 */
	protected Label label;

	/**
	 * @param caption
	 *            text of the button
	 * @param feedback
	 *            String to send if click occurs
	 * @param handler
	 *            {@link ClickHandler}
	 */
	public KeyBoardButtonBase(String caption, String feedback, KBBase handler) {
		this(handler);
		this.label = new Label();
		setCaption(caption);
		this.feedback = feedback;

		this.setWidget(label);
	}

	/**
	 * Constructor for subclass {@link KeyBoardButtonFunctionalBase}
	 * 
	 * @param handler
	 *            {@link ClickHandler}
	 */
	protected KeyBoardButtonBase(final KBBase handler) {
		ClickStartHandler.init(this, new ClickStartHandler(true, true) {
			@Override
			public void onClickStart(int x, int y, PointerEventType type) {
				// do nothing
			}
		});

		// only used for preventDefault and stopPropagation
		ClickEndHandler.init(this, new ClickEndHandler(true, true) {
			@Override
			public void onClickEnd(int x, int y, PointerEventType type) {
				handler.onClick(KeyBoardButtonBase.this, type);
			}
		});
		addStyleName("KeyBoardButton");
		addStyleName("MouseDownDoesntExitEditingFeature");
	}

	/**
	 * @return text of the button
	 */
	public String getCaption() {
		return this.caption;
	}

	/**
	 * @param caption
	 *            text of the button
	 * @param setFeedback
	 *            whether or not the feedback of the button should be changed to
	 *            the new caption
	 */
	public void setCaption(String caption, boolean setFeedback) {
		this.caption = caption;

		if (setFeedback) {
			this.feedback = caption;
		}

		if (caption.length() > 1 && caption.indexOf('^') > -1) {
			int index = caption.indexOf('^');
			this.label.setText(caption.substring(0, index));
			Element sup = Document.get().createElement("sup");
			sup.appendChild(Document.get().createTextNode(
			        caption.substring(index + 1)));
			this.label.getElement().appendChild(sup);
		} else if (caption.length() > 1 && caption.indexOf('_') > -1) {
			int index = caption.indexOf('_');
			this.label.setText(caption.substring(0, index));
			Element sub = Document.get().createElement("sub");
			sub.appendChild(Document.get().createTextNode(
			        caption.substring(index + 1)));
			this.label.getElement().appendChild(sub);
		} else {
			this.label.setText(caption);
		}
	}

	/**
	 * @param caption
	 *            text of the button (also used as new feedback)
	 */
	public void setCaption(String caption) {
		setCaption(caption, true);
	}

	/**
	 * @return the String to be sent if a click occurs
	 */
	public String getFeedback() {
		return this.feedback;
	}
}
