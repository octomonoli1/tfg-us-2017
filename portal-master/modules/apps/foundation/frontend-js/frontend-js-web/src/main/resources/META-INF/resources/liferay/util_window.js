AUI.add(
	'liferay-util-window',
	function(A) {
		var DOM = A.DOM;
		var Lang = A.Lang;
		var UA = A.UA;

		var Util = Liferay.Util;
		var Window = Util.Window;

		var IE9 = UA.ie == 9;

		var setWidth = function(modal, width) {
			if (IE9) {
				modal.set('width', width + 1);
				modal.set('width', width);
			}
		};

		var LiferayModal = A.Component.create(
			{
				ATTRS: {
					autoHeight: {
						value: false
					},

					autoHeightRatio: {
						value: 0.95
					},

					autoSizeNode: {
						setter: A.one
					},

					autoWidth: {
						value: false
					},

					autoWidthRatio: {
						value: 0.95
					},

					toolbars: {
						valueFn: function() {
							var instance = this;

							return {
								header: [
									{
										cssClass: 'close',
										discardDefaultButtonCssClasses: true,
										labelHTML: '<svg class="lexicon-icon"><use xlink:href="' + Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg#times" /></svg>',
										on: {
											click: function(event) {
												instance.hide();

												event.domEvent.stopPropagation();
											}
										},
										render: true
									}
								]
							};
						}
					}
				},

				EXTENDS: A.Modal,

				NAME: A.Modal.NAME,

				prototype: {
				}
			}
		);

		A.mix(
			Window,
			{
				DEFAULTS: {
					centered: true,
					modal: true,
					visible: true,
					zIndex: Liferay.zIndex.WINDOW
				},

				IFRAME_SUFFIX: '_iframe_',

				TITLE_TEMPLATE: '<h3 class="modal-title" />',

				getByChild: function(child) {
					var instance = this;

					var node = A.one(child).ancestor('.modal', true);

					return A.Widget.getByNode(node);
				},

				getById: function(id) {
					var instance = this;

					return instance._map[id];
				},

				getWindow: function(config) {
					var instance = this;

					instance._ensureDefaultId(config);

					var modal = instance._getWindow(config);

					instance._bindDOMWinResizeIfNeeded();

					modal.render();

					instance._setWindowDefaultSizeIfNeeded(modal);

					modal.align();

					return modal;
				},

				hideByChild: function(child) {
					var instance = this;

					return instance.getByChild(child).hide();
				},

				refreshByChild: function(child) {
					var instance = this;

					var dialog = instance.getByChild(child);

					if (dialog && dialog.io) {
						dialog.io.start();
					}
				},

				_bindDOMWinResizeIfNeeded: function() {
					var instance = this;

					if (!instance._winResizeHandler) {
						instance._winResizeHandler = A.getWin().after('windowresize', instance._syncWindowsUI, instance);
					}
				},

				_bindWindowHooks: function(modal, config) {
					var instance = this;

					var id = modal.get('id');

					var openingWindow = config.openingWindow;

					var refreshWindow = config.refreshWindow;

					modal._opener = openingWindow;
					modal._refreshWindow = refreshWindow;

					modal.after(
						'destroy',
						function(event) {
							if (modal._opener) {
								var openerInFrame = !!modal._opener.frameElement;

								if (IE9 && openerInFrame) {
									instance._syncWindowsUI();
								}
							}

							instance._unregister(modal);

							modal = null;
						}
					);

					var liferayHandles = modal._liferayHandles;

					liferayHandles.push(
						Liferay.after(
							'hashChange',
							function(event) {
								modal.iframe.set('uri', event.uri);
							}
						)
					);

					liferayHandles.push(
						Liferay.after(
							'popupReady',
							function(event) {
								var iframeId = id + instance.IFRAME_SUFFIX;

								if (event.windowName === iframeId) {
									event.dialog = modal;
									event.details[0].dialog = modal;

									if (event.doc) {
										Util.afterIframeLoaded(event);

										var modalUtil = event.win.Liferay.Util;

										modalUtil.Window._opener = modal._opener;

										modalUtil.Window._name = id;
									}

									var iframeNode = modal.iframe.node;

									iframeNode.focus();

									if (UA.ios) {
										iframeNode.attr('scrolling', 'no');
									}
								}
							}
						)
					);
				},

				_ensureDefaultId: function(config) {
					var instance = this;

					if (!Lang.isValue(config.id)) {
						config.id = A.guid();
					}

					if (!config.iframeId) {
						config.iframeId = config.id + instance.IFRAME_SUFFIX;
					}
				},

				_getDialogIframeConfig: function(config) {
					var instance = this;

					var dialogIframeConfig;

					var iframeId = config.iframeId;

					var uri = config.uri;

					if (uri) {
						if (config.cache === false) {
							uri = Liferay.Util.addParams(A.guid() + '=' + Date.now(), uri);
						}

						var defaultDialogIframeConfig = {
							bodyCssClass: ''
						};

						dialogIframeConfig = A.merge(
							defaultDialogIframeConfig,
							config.dialogIframe,
							{
								bindLoadHandler: function() {
									var instance = this;

									var modal = instance.get('host');

									var popupReady = false;

									var liferayHandles = modal._liferayHandles;

									liferayHandles.push(
										Liferay.on(
											'popupReady',
											function(event) {
												instance.fire('load', event);

												popupReady = true;
											}
										)
									);

									liferayHandles.push(
										instance.node.on(
											'load',
											function(event) {
												if (!popupReady) {
													Liferay.fire(
														'popupReady',
														{
															windowName: iframeId
														}
													);
												}

												popupReady = false;
											}
										)
									);
								},

								iframeId: iframeId,
								uri: uri
							}
						);
					}

					return dialogIframeConfig;
				},

				_getWindow: function(config) {
					var instance = this;

					var id = config.id;

					var modalConfig = instance._getWindowConfig(config);

					var dialogIframeConfig = instance._getDialogIframeConfig(config);

					var modal = instance.getById(id);

					if (!modal) {
						var titleNode = A.Node.create(instance.TITLE_TEMPLATE);

						if (config.stack !== false) {
							A.mix(
								modalConfig,
								{
									plugins: [Liferay.WidgetZIndex]
								}
							);
						}

						modal = new LiferayModal(
							A.merge(
								{
									headerContent: titleNode,
									id: id
								},
								modalConfig
							)
						);

						Liferay.once(
							'screenLoad',
							function() {
								modal.destroy();
							}
						);

						modal.titleNode = titleNode;

						instance._register(modal);

						instance._bindWindowHooks(modal, config);
					}
					else {
						if (!config.zIndex && modal.hasPlugin('zindex')) {
							delete modalConfig.zIndex;
						}

						var openingWindow = config.openingWindow;

						modal._opener = openingWindow;
						modal._refreshWindow = config.refreshWindow;

						instance._map[id]._opener = openingWindow;

						modal.setAttrs(modalConfig);
					}

					if (dialogIframeConfig) {
						modal.iframeConfig = dialogIframeConfig;
						modal.plug(A.Plugin.DialogIframe, dialogIframeConfig);

						modal.get('boundingBox').addClass('dialog-iframe-modal');
					}

					if (!Lang.isValue(config.title)) {
						config.title = '&nbsp;';
					}

					modal.titleNode.html(config.title);

					modal.fillHeight(modal.bodyNode);

					return modal;
				},

				_getWindowConfig: function(config) {
					var instance = this;

					var modalConfig = A.merge(instance.DEFAULTS, config.dialog);

					var height = modalConfig.height;
					var width = modalConfig.width;

					if (height === 'auto' || height === '' || height === undefined || height > DOM.winHeight()) {
						modalConfig.autoHeight = true;
					}

					if (width === 'auto' || width === '' || width === undefined || width > DOM.winWidth()) {
						modalConfig.autoWidth = true;
					}

					modalConfig.id = config.id;

					return modalConfig;
				},

				_register: function(modal) {
					var instance = this;

					var id = modal.get('id');

					modal._liferayHandles = [];

					instance._map[id] = modal;
					instance._map[id + instance.IFRAME_SUFFIX] = modal;
				},

				_setWindowDefaultSizeIfNeeded: function(modal) {
					var instance = this;

					var autoSizeNode = modal.get('autoSizeNode');

					if (modal.get('autoHeight')) {
						var height;

						if (autoSizeNode) {
							height = autoSizeNode.get('offsetHeight');
						}
						else {
							height = DOM.winHeight();
						}

						height *= modal.get('autoHeightRatio');

						if (modal.get('height') === 'auto') {
							modal._fillMaxHeight(height);
						}
						else {
							modal.set('height', height);
						}
					}

					var widthInitial = modal.get('width');

					if (widthInitial !== 'auto') {
						if (modal.get('autoWidth')) {
							var width;

							if (autoSizeNode) {
								width = autoSizeNode.get('offsetWidth');
							}
							else {
								width = DOM.winWidth();
							}

							width *= modal.get('autoWidthRatio');

							if (width != widthInitial) {
								modal.set('width', width);
							}
							else {
								setWidth(modal, widthInitial);
							}
						}
						else {
							setWidth(modal, modal.get('width'));
						}
					}
				},

				_syncWindowsUI: function() {
					var instance = this;

					var modals = instance._map;

					A.each(
						modals,
						function(modal) {
							if (modal.get('visible')) {
								instance._setWindowDefaultSizeIfNeeded(modal);

								modal.align();
							}
						}
					);
				},

				_unregister: function(modal) {
					var instance = this;

					var id = modal.get('id');

					delete instance._map[id];
					delete instance._map[id + instance.IFRAME_SUFFIX];

					A.Array.invoke(modal._liferayHandles, 'detach');
				},

				_winResizeHandler: null
			}
		);
	},
	'',
	{
		requires: ['aui-dialog-iframe-deprecated', 'aui-modal', 'event-resize', 'liferay-widget-zindex']
	}
);