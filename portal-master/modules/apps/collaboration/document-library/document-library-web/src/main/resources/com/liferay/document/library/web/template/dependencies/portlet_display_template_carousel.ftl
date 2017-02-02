<#if entries?has_content>
	<style>
		#<@portlet.namespace />carousel .carousel-item {
			background-color: #000;
			height: 250px;
			overflow: hidden;
			text-align: center;
			width: 700px;
		}

		#<@portlet.namespace />carousel .carousel-item img {
			max-height: 250px;
			max-width: 700px;
		}
	</style>

	<div id="<@portlet.namespace />carousel">
		<#assign imageMimeTypes = propsUtil.getArray("dl.file.entry.preview.image.mime.types") />

		<#list entries as entry>
			<#if imageMimeTypes?seq_contains(entry.getMimeType())>
				<div class="carousel-item image-viewer-base-image">
					<img src="${dlUtil.getPreviewURL(entry, entry.getFileVersion(), themeDisplay, "")}" />
				</div>
			</#if>
		</#list>
	</div>

	<@liferay_aui.script use="aui-carousel">
		var carousel = new A.Carousel(
			{
				after: {
					responsive: function(event) {
						event.stopImmediatePropagation();

						var boundingBox = event.currentTarget.get('boundingBox');

						boundingBox.all('.image-viewer-base-image-list, .image-viewer-base-image').setStyles(
							{
								height: 'auto',
								maxHeight: event.height,
								maxWidth: event.width,
								width: 'auto'
							}
						);
					}
				},

				contentBox: '#<@portlet.namespace />carousel',
				height: 250,
				intervalTime: 2,
				width: 700
			}
		).render();
	</@liferay_aui.script>
</#if>