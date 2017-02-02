<#assign defaultLatitude = -3.6833 />
<#assign defaultLongitude = 40.40 />

<#assign group = themeDisplay.getScopeGroup() />

<#assign mapsAPIProvider = group.getLiveParentTypeSettingsProperty("mapsAPIProvider")!"" />

<#assign companyPortletPreferences = prefsPropsUtil.getPreferences(companyId) />

<#if mapsAPIProvider = "">
	<#assign mapsAPIProvider = companyPortletPreferences.getValue("mapsAPIProvider", "Google") />
</#if>

<#assign featureCollectionJSONObject = jsonFactoryUtil.createJSONObject() />

<@liferay.silently featureCollectionJSONObject.put("type", "FeatureCollection") />

<#assign featureJSONArray = jsonFactoryUtil.createJSONArray() />

<#list entries as entry>
	<#assign assetRenderer = entry.getAssetRenderer() />

	<#assign ddmFormValuesReader = assetRenderer.getDDMFormValuesReader() />

	<#assign ddmFormFieldValues = ddmFormValuesReader.getDDMFormFieldValues("ddm-geolocation") />

	<#assign coordinatesJSONObjects = [] />

	<#list ddmFormFieldValues as ddmFormFieldValue>
		<#assign value = ddmFormFieldValue.getValue() />

		<#assign coordinatesJSONObject = jsonFactoryUtil.createJSONObject(value.getString(locale)) />

		<#assign coordinatesJSONObjects = coordinatesJSONObjects + [coordinatesJSONObject] />
	</#list>

	<#list coordinatesJSONObjects as coordinatesJSONObject>
		<#assign featureJSONObject = jsonFactoryUtil.createJSONObject() />

		<@liferay.silently featureJSONObject.put("type", "Feature") />

		<#assign geometryJSONObject = jsonFactoryUtil.createJSONObject() />

		<@liferay.silently geometryJSONObject.put("type", "Point") />

		<#assign coordinatesJSONArray = [coordinatesJSONObject.getDouble("longitude"), coordinatesJSONObject.getDouble("latitude")] />

		<@liferay.silently geometryJSONObject.put("coordinates", coordinatesJSONArray) />

		<@liferay.silently featureJSONObject.put("geometry", geometryJSONObject) />

		<#assign propertiesJSONObject = jsonFactoryUtil.createJSONObject() />

		<@liferay.silently propertiesJSONObject.put("title", assetRenderer.getTitle(locale)) />

		<#assign entryAbstract>
			<@getAbstract asset=entry />
		</#assign>

		<@liferay.silently propertiesJSONObject.put("abstract", entryAbstract) />

		<#if mapsAPIProvider = "Google">
			<#assign
				images = {
					"com.liferay.document.library.kernel.model.DLFileEntry": "${themeDisplay.getProtocol()}://maps.google.com/mapfiles/ms/icons/green-dot.png",
					"com.liferay.portlet.dynamicdatalists.model.DDLRecord": "${themeDisplay.getProtocol()}://maps.google.com/mapfiles/ms/icons/red-dot.png",
					"com.liferay.journal.model.JournalArticle": "${themeDisplay.getProtocol()}://maps.google.com/mapfiles/ms/icons/blue-dot.png",
					"default": "${themeDisplay.getProtocol()}://maps.google.com/mapfiles/ms/icons/yellow-dot.png"
				}
			/>

			<#if images?keys?seq_contains(entry.getClassName())>
				<@liferay.silently propertiesJSONObject.put("icon", images[entry.getClassName()]) />
			<#else>
				<@liferay.silently propertiesJSONObject.put("icon", images["default"]) />
			</#if>
		</#if>

		<@liferay.silently featureJSONObject.put("properties", propertiesJSONObject) />

		<@liferay.silently featureJSONArray.put(featureJSONObject) />
	</#list>
</#list>

<@liferay.silently featureCollectionJSONObject.put("features", featureJSONArray) />

<style type="text/css">
	.asset-entry-abstract {
		min-width: 400px;
	}

	.leaflet-popup .asset-entry-abstract {
		display: inline-block;
	}

	.asset-entry-abstract .asset-entry-abstract-image {
		display: block;
		float: left;
		height: 128px;
		margin-right: 1em;
		text-align: center;
	}

	.asset-entry-abstract .asset-entry-abstract-image img {
		display: block;
		margin: 0 auto;
		max-height: 100%;
	}

	.asset-entry-abstract .taglib-icon {
		float: right;
	}
</style>

<@liferay_map["map-display"]
	name='Map'
	points="${featureCollectionJSONObject}"
/>

<@liferay_aui.script use="liferay-map-base">
	var map = Liferay.component('<@portlet.namespace />Map');

	map.on(
		'featureClick',
		function(event) {
			var feature = event.feature;

			map.openDialog(
				{
					content: feature.getProperty('abstract'),
					marker: feature.getMarker(),
					position: feature.getGeometry().get('location')
				}
			);
		}
	);
</@liferay_aui.script>

<#macro getAbstract asset>
	<div class="asset-entry-abstract" id="<@portlet.namespace />assetEntryAbstract">
		<#assign showEditURL = paramUtil.getBoolean(renderRequest, "showEditURL", true) />

		<#assign assetRenderer = asset.getAssetRenderer() />

		<#if showEditURL && assetRenderer.hasEditPermission(permissionChecker)>
			<#assign redirectURL = renderResponse.createLiferayPortletURL(themeDisplay.getPlid(), themeDisplay.getPortletDisplay().getId(), "RENDER_PHASE", false) />

			${redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp")}

			<#assign editPortletURL = assetRenderer.getURLEdit(renderRequest, renderResponse, windowStateFactory.getWindowState("POP_UP"), redirectURL) />

			<#assign taglibEditURL = "javascript:Liferay.Util.openWindow({id: '" + renderResponse.getNamespace() + "editAsset', title: '" + htmlUtil.escapeJS(languageUtil.format(locale, "edit-x", htmlUtil.escape(assetRenderer.getTitle(locale)), false)) + "', uri:'" + htmlUtil.escapeJS(editPortletURL.toString()) + "'});" />

			<@liferay_ui.icon
				image = "edit"
				label = true
				message = "edit"
				url = taglibEditURL
			/>
		</#if>

		<#if assetRenderer.getThumbnailPath(renderRequest)??>
			<div class="asset-entry-abstract-image">
				<img src="${assetRenderer.getThumbnailPath(renderRequest)}" />
			</div>
		</#if>

		<#assign assetURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, asset) />

		<div class="asset-entry-abstract-content">
			<h3><a href="${assetURL}">${assetRenderer.getTitle(locale)}</a></h3>

			<div>
				${assetRenderer.getSummary(renderRequest, renderResponse)}
			</div>
		</div>

		<div class="asset-entry-abstract-footer">
			<a href="${assetURL}">${languageUtil.get(locale, "read-more")} &raquo;</a>
		</div>
	</div>
</#macro>