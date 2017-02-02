<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />simulationDeviceContainer">
	<div class="list-group-panel">
		<div class="devices">
			<div class="container-fluid default-devices flex-container">
				<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item selected text-center" data-device="desktop">
					<aui:icon cssClass="icon icon-monospaced" image="desktop" markupView="lexicon" />

					<small><%= LanguageUtil.get(resourceBundle, "desktop") %></small>
				</div>

				<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item text-center" data-device="tablet">
					<aui:icon cssClass="icon icon-monospaced" image="tablet-portrait" markupView="lexicon" />

					<aui:icon cssClass="hide icon icon-monospaced icon-rotate" image="tablet-landscape" markupView="lexicon" />

					<small><%= LanguageUtil.get(resourceBundle, "tablet") %></small>
				</div>

				<div class="flex-item-expand lfr-device-item text-center" data-device="smartphone">
					<aui:icon cssClass="icon icon-monospaced" image="mobile-portrait" markupView="lexicon" />

					<aui:icon cssClass="hide icon icon-monospaced icon-rotate" image="mobile-landscape" markupView="lexicon" />

					<small><%= LanguageUtil.get(resourceBundle, "mobile") %></small>
				</div>

				<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item text-center" data-device="autosize">
					<aui:icon cssClass="icon icon-monospaced" image="full-size" markupView="lexicon" />

					<small><%= LanguageUtil.get(resourceBundle, "autosize") %></small>
				</div>

				<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item text-center" data-device="custom">
					<aui:icon cssClass="icon icon-monospaced" image="cog" markupView="lexicon" />

					<small><liferay-ui:message key="custom" /></small>
				</div>
			</div>

			<div class="container-fluid custom-devices flex-container hidden-sm hidden-xs hide" id="<portlet:namespace />customDeviceContainer">
				<aui:input cssClass="input-sm" inlineField="<%= true %>" inlineLabel="left" label='<%= LanguageUtil.get(request, "height") + " (px):" %>' name="height" size="4" value="600" wrapperCssClass="flex-item-expand" />

				<aui:input cssClass="input-sm" inlineField="<%= true %>" inlineLabel="left" label='<%= LanguageUtil.get(request, "width") + " (px):" %>' name="width" size="4" value="600" wrapperCssClass="flex-item-expand" />
			</div>
		</div>
	</div>
</div>

<aui:script use="liferay-product-navigation-simulation-device">
	var simulationDevice = new Liferay.SimulationDevice(
		{
			devices: {
				autosize: {
					skin: 'autosize'
				},
				custom: {
					height: '#<portlet:namespace />height',
					resizable: true,
					width: '#<portlet:namespace />width'
				},
				desktop: {
					height: 1050,
					selected: true,
					width: 1300
				},
				smartphone: {
					height: 640,
					preventTransition: true,
					rotation: true,
					skin: 'smartphone',
					width: 400
				},
				tablet: {
					height: 900,
					preventTransition: true,
					rotation: true,
					skin: 'tablet',
					width: 760
				}
			},
			inputHeight: '#<portlet:namespace />height',
			inputWidth: '#<portlet:namespace />width',
			namespace: '<portlet:namespace />'
		}
	);

	Liferay.once(
		'screenLoad',
		function() {
			simulationDevice.destroy();
		}
	);

	A.one('.devices').delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var dataDevice = currentTarget.attr('data-device');

			var customDeviceContainer = A.one('#<portlet:namespace />customDeviceContainer');

			if (dataDevice === 'custom') {
				customDeviceContainer.show();
			}
			else {
				customDeviceContainer.hide();
			}
		},
		'.lfr-device-item'
	);
</aui:script>