/********************************************************************************* 
* Ephesoft is a Intelligent Document Capture and Mailroom Automation program 
* developed by Ephesoft, Inc. Copyright (C) 2010-2011 Ephesoft Inc. 
* 
* This program is free software; you can redistribute it and/or modify it under 
* the terms of the GNU Affero General Public License version 3 as published by the 
* Free Software Foundation with the addition of the following permission added 
* to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK 
* IN WHICH THE COPYRIGHT IS OWNED BY EPHESOFT, EPHESOFT DISCLAIMS THE WARRANTY 
* OF NON INFRINGEMENT OF THIRD PARTY RIGHTS. 
* 
* This program is distributed in the hope that it will be useful, but WITHOUT 
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
* FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more 
* details. 
* 
* You should have received a copy of the GNU Affero General Public License along with 
* this program; if not, see http://www.gnu.org/licenses or write to the Free 
* Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
* 02110-1301 USA. 
* 
* You can contact Ephesoft, Inc. headquarters at 111 Academy Way, 
* Irvine, CA 92617, USA. or at email address info@ephesoft.com. 
* 
* The interactive user interfaces in modified source and object code versions 
* of this program must display Appropriate Legal Notices, as required under 
* Section 5 of the GNU Affero General Public License version 3. 
* 
* In accordance with Section 7(b) of the GNU Affero General Public License version 3, 
* these Appropriate Legal Notices must retain the display of the "Ephesoft" logo. 
* If the display of the logo is not reasonably feasible for 
* technical reasons, the Appropriate Legal Notices must display the words 
* "Powered by Ephesoft". 
********************************************************************************/ 

package com.ephesoft.dcma.gwt.admin.bm.client.view.module;

import com.ephesoft.dcma.gwt.admin.bm.client.presenter.module.ConfigureModulePluginsPresenter;
import com.ephesoft.dcma.gwt.core.client.View;
import com.ephesoft.dcma.gwt.core.client.view.MultipleSelectTwoSidedListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ConfigureModulesPluginSelectView extends View<ConfigureModulePluginsPresenter> {

	private static final String SELECTED_PLUGINS = "Selected Plugins";

	private static final String AVAILABLE_PLUGINS = "Available Plugins";


	private static final String CANCEL = "Cancel";

	private static final String OK = "Ok";
	
	
	interface Binder extends UiBinder<DockLayoutPanel, ConfigureModulesPluginSelectView> {
	}

	MultipleSelectTwoSidedListBox multipleSelectTwoSidedListBox;
	
	HorizontalPanel twoSidedListBoxHorizontalPanel;

	@UiField
	HorizontalPanel buttonsHorizontalPanel;

	@UiField
	Button okButton;

	@UiField
	Button cancelButton;

	@UiField
	DockLayoutPanel customWorkflowVerticalPanel;

	private static final Binder binder = GWT.create(Binder.class);

	private HandlerManager eventBus;

	public ConfigureModulesPluginSelectView()
	{
		this(null);
	}
	public ConfigureModulesPluginSelectView(HandlerManager eventBus) {

		initWidget(binder.createAndBindUi(this));
		multipleSelectTwoSidedListBox = new MultipleSelectTwoSidedListBox(eventBus);
		customWorkflowVerticalPanel.addNorth(multipleSelectTwoSidedListBox, 300);

		addCSSStyle();
		addFieldText();
		multipleSelectTwoSidedListBox.getLeftHandSideListBox().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent changeEvent) {
				presenter.onPluginSelect();
			}
		});
		
		multipleSelectTwoSidedListBox.getRightHandSideListBox().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent changeEvent) {
			}
		});

	}

	
	
	private void addFieldText() {
		okButton.setText(OK);
		cancelButton.setText(CANCEL);
	}

	private void addCSSStyle() {

		buttonsHorizontalPanel.setSpacing(30);
		okButton.addStyleName("button-style");
		cancelButton.addStyleName("button-style");

		buttonsHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		buttonsHorizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		customWorkflowVerticalPanel.addStyleName("mainPanelLayout");
		multipleSelectTwoSidedListBox.getAvailableLabel().setText(AVAILABLE_PLUGINS);
		multipleSelectTwoSidedListBox.getSelectedLabel().setText(SELECTED_PLUGINS);
		
	}

	/**
	 * @return the customWorkflowVerticalPanel
	 */
	public DockLayoutPanel getCustomWorkflowVerticalPanel() {
		return customWorkflowVerticalPanel;
	}

	/**
	 * @return the multipleSelectTwoSidedListBox
	 */
	public MultipleSelectTwoSidedListBox getMultipleSelectTwoSidedListBox() {
		return multipleSelectTwoSidedListBox;
	}

	@UiHandler("okButton")
	public void onOkClicked(ClickEvent clickEvent) {
		presenter.setBatchClassDTOPluginsList(getMultipleSelectTwoSidedListBox().getAllValuesMapFromList(
				getMultipleSelectTwoSidedListBox().getRightHandSideListBox()));
	}

	@UiHandler("cancelButton")
	public void onCancelClicked(ClickEvent clickEvent) {
		presenter.bind();
	}

	/**
	 * @return the eventBus
	 */
	public HandlerManager getEventBus() {
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(HandlerManager eventBus) {
		this.eventBus = eventBus;
	}

}
