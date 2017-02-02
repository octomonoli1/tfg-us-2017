AUI.add(
	'liferay-search-container-move',
	function(A) {
		var Lang = A.Lang;

		var STR_BLANK = '';

		var STR_CONTENT_BOX = 'contentBox';

		var STR_DATA = 'data';

		var STR_DRAG_NODE = 'dragNode';

		var STR_HOST = 'host';

		var STR_NODE = 'node';

		var SearchContainerMove = A.Component.create(
			{
				ATTRS: {
					dropTargets: {
						validator: Lang.isArray
					},

					rowSelector: {
						validator: Lang.isString,
						value: 'li,tr'
					},

					tooltipClass: {
						validator: Lang.isString,
						value: 'btn btn-group'
					}
				},

				EXTENDS: A.Plugin.Base,

				NAME: 'searchcontainermove',

				NS: 'move',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._initDragAndDrop();

						instance._initDragAndDropToggle();

						instance._initDropTargets();
					},

					destructor: function() {
						var instance = this;

						(new A.EventHandle(instance._eventHandles)).detach();
					},

					_getMoveText: function(selectedItemsCount, targetAvailable) {
						var moveText = STR_BLANK;

						if (targetAvailable) {
							moveText = Liferay.Language.get('x-item-is-ready-to-be-moved-to-x');

							if (selectedItemsCount > 1) {
								moveText = Liferay.Language.get('x-items-are-ready-to-be-moved-to-x');
							}
						}
						else {
							moveText = Liferay.Language.get('x-item-is-ready-to-be-moved');

							if (selectedItemsCount > 1) {
								moveText = Liferay.Language.get('x-items-are-ready-to-be-moved');
							}
						}

						return moveText;
					},

					_initDragAndDrop: function() {
						var instance = this;

						var host = instance.get(STR_HOST);

						instance._ddHandler = new A.DD.Delegate(
							{
								container: host.get(STR_CONTENT_BOX),
								nodes: instance.get('rowSelector'),
								on: {
									'drag:drophit': A.bind('_onDragDropHit', instance),
									'drag:enter': A.bind('_onDragEnter', instance),
									'drag:exit': A.bind('_onDragExit', instance),
									'drag:start': A.bind('_onDragStart', instance)
								}
							}
						);

						var dd = instance._ddHandler.dd;

						dd.set('groups', [host.get('id')]);
						dd.set('offsetNode', false);

						dd.plug(
							[
								{
									cfg: {
										moveOnEnd: false
									},
									fn: A.Plugin.DDProxy
								}
							]
						);
					},

					_initDragAndDropToggle: function() {
						var instance = this;

						var host = instance.get(STR_HOST);
						var container = host.get(STR_CONTENT_BOX);
						var searchContainerWrapper = container.get('parentNode');
						var toggle = searchContainerWrapper.one('.search-container-dd-toggle input[type="checkbox"]');

						var checked = toggle ? toggle.get('checked') : false;

						instance._ddHandler.dd.set('lock', !checked);

						searchContainerWrapper.delegate(
							'change',
							function(event) {
								checked = event.currentTarget.get('checked');

								instance._ddHandler.dd.set('lock', !checked);

								Liferay.Store(host.get('id') + '_searchContainerMove', checked ? 'checked' : '');
							},
							'.search-container-dd-toggle input[type="checkbox]'
						);
					},

					_initDropTargets: function() {
						var instance = this;

						var dropTargets = instance.get('dropTargets');

						if (dropTargets && themeDisplay.isSignedIn()) {
							var host = instance.get(STR_HOST);

							dropTargets.forEach(
								function(target) {
									var container = A.one(target.container) || host.get(STR_CONTENT_BOX);

									var targetNodes = container.all(target.selector);

									targetNodes.each(
										function(item, index) {
											item.plug(
												A.Plugin.Drop,
												{
													groups: [host.get('id')]
												}
											).drop.on(
												{
													'drop:enter': function(event) {
														item.addClass(target.activeCssClass);
													},

													'drop:exit': function(event) {
														item.removeClass(target.activeCssClass);
													},

													'drop:hit': function(event) {
														item.removeClass(target.activeCssClass);

														var selectedItems = instance._ddHandler.dd.get(STR_DATA).selectedItems;

														var dropTarget = event.drop.get(STR_NODE);

														host.executeAction(
															target.action,
															{
																selectedItems: selectedItems,
																targetItem: dropTarget
															}
														);
													}
												}
											);
										}
									);

									if (target.infoCssClass) {
										instance._ddHandler.on(
											['drag:start', 'drag:end'],
											function(event) {
												targetNodes.toggleClass(target.infoCssClass, event.type == 'drag:start');
											}
										);
									}
								}
							);
						}
					},

					_onDragDropHit: function(event) {
						var instance = this;

						var proxyNode = event.target.get(STR_DRAG_NODE);

						proxyNode.removeClass(instance.get('tooltipClass'));

						proxyNode.empty();
					},

					_onDragEnter: function(event) {
						var instance = this;

						var dragNode = event.drag.get(STR_NODE);

						var dropTarget = event.drop.get(STR_NODE);

						if (!dragNode.compareTo(dropTarget)) {
							var proxyNode = event.target.get(STR_DRAG_NODE);

							var dd = instance._ddHandler.dd;

							var selectedItemsCount = dd.get(STR_DATA).selectedItemsCount;

							var moveText = instance._getMoveText(selectedItemsCount, true);

							var itemTitle = dropTarget.attr('data-title').trim();

							proxyNode.html(Lang.sub(moveText, [selectedItemsCount, A.Lang.String.escapeHTML(itemTitle)]));
						}
					},

					_onDragExit: function(event) {
						var instance = this;

						var proxyNode = event.target.get(STR_DRAG_NODE);

						var selectedItemsCount = instance._ddHandler.dd.get(STR_DATA).selectedItemsCount;

						var moveText = instance._getMoveText(selectedItemsCount);

						proxyNode.html(Lang.sub(moveText, [selectedItemsCount]));
					},

					_onDragStart: function(event) {
						var instance = this;

						var target = event.target;

						var node = target.get(STR_NODE);

						var selectedItems = new A.NodeList(node);

						var host = instance.get(STR_HOST);

						if (host.hasPlugin('select')) {
							var searchContainerSelect = host.select;

							var selected = searchContainerSelect.isSelected(node);

							if (!selected) {
								searchContainerSelect.toggleAllRows(false);
								searchContainerSelect.toggleRow(
									{
										toggleCheckbox: true
									},
									node
								);
							}
							else {
								selectedItems = searchContainerSelect.getCurrentPageSelectedElements();
							}
						}

						var selectedItemsCount = selectedItems.size();

						var dd = instance._ddHandler.dd;

						dd.set(
							STR_DATA,
							{
								selectedItems: selectedItems,
								selectedItemsCount: selectedItemsCount
							}
						);

						var proxyNode = target.get(STR_DRAG_NODE);

						proxyNode.setStyles(
							{
								height: STR_BLANK,
								width: STR_BLANK
							}
						);

						var moveText = instance._getMoveText(selectedItemsCount);

						proxyNode.html(Lang.sub(moveText, [selectedItemsCount]));

						proxyNode.addClass(instance.get('tooltipClass'));
					}
				}
			}
		);

		A.Plugin.SearchContainerMove = SearchContainerMove;
	},
	'',
	{
		requires: ['aui-component', 'dd-constrain', 'dd-delegate', 'dd-drag', 'dd-drop', 'dd-proxy', 'liferay-store', 'plugin']
	}
);