AUI.add(
	'liferay-search-container-select',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var REGEX_MATCH_EVERYTHING = /.*/;

		var REGEX_MATCH_NOTHING = /^[]/;

		var STR_CHECKBOX_SELECTOR = 'input[type=checkbox]:enabled';

		var STR_CHECKED = 'checked';

		var STR_CLICK = 'click';

		var STR_CONTENT_BOX = 'contentBox';

		var STR_HOST = 'host';

		var STR_ROW_CLASS_NAME_ACTIVE = 'rowClassNameActive';

		var STR_ROW_SELECTOR = 'rowSelector';

		var TPL_HIDDEN_INPUT = '<input class="hide" name="{name}" value="{value}" type="checkbox" ' + STR_CHECKED + ' />';

		var TPL_INPUT_SELECTOR = 'input[type="checkbox"][value="{value}"]';

		var SearchContainerSelect = A.Component.create(
			{
				ATTRS: {
					keepSelection: {
						setter: function(keepSelection) {
							if (Lang.isString(keepSelection)) {
								keepSelection = new RegExp(keepSelection);
							}
							else if (!Lang.isRegExp(keepSelection)) {
								keepSelection = keepSelection ? REGEX_MATCH_EVERYTHING : REGEX_MATCH_NOTHING;
							}

							return keepSelection;
						},
						value: REGEX_MATCH_EVERYTHING
					},

					rowCheckerSelector: {
						validator: Lang.isString,
						value: '.click-selector'
					},

					rowClassNameActive: {
						validator: Lang.isString,
						value: 'active'
					},

					rowSelector: {
						validator: Lang.isString,
						value: 'li[data-selectable="true"],tr[data-selectable="true"]'
					}
				},

				EXTENDS: A.Plugin.Base,

				NAME: 'searchcontainerselect',

				NS: 'select',

				prototype: {
					initializer: function() {
						var instance = this;

						var host = instance.get(STR_HOST);

						var toggleRowFn = A.bind(
							'_onClickRowSelector',
							instance,
							{
								toggleCheckbox: true
							}
						);

						var toggleRowCSSFn = A.bind('_onClickRowSelector', instance, {});

						instance._eventHandles = [
							host.get(STR_CONTENT_BOX).delegate(STR_CLICK, toggleRowCSSFn, instance.get(STR_ROW_SELECTOR) + ' ' + STR_CHECKBOX_SELECTOR, instance),
							host.get(STR_CONTENT_BOX).delegate(STR_CLICK, toggleRowFn, instance.get(STR_ROW_SELECTOR) + ' ' + instance.get('rowCheckerSelector'), instance),
							Liferay.on('startNavigate', instance._onStartNavigate, instance)
						];
					},

					destructor: function() {
						var instance = this;

						(new A.EventHandle(instance._eventHandles)).detach();
					},

					getAllSelectedElements: function() {
						var instance = this;

						return instance._getAllElements(true);
					},

					getCurrentPageSelectedElements: function() {
						var instance = this;

						return instance._getCurrentPageElements(true);
					},

					isSelected: function(element) {
						return element.one(STR_CHECKBOX_SELECTOR).attr(STR_CHECKED);
					},

					toggleAllRows: function(selected) {
						var instance = this;

						instance._getCurrentPageElements().attr(STR_CHECKED, selected);

						instance.get(STR_HOST).get(STR_CONTENT_BOX).all(instance.get(STR_ROW_SELECTOR)).toggleClass(instance.get(STR_ROW_CLASS_NAME_ACTIVE), selected);

						instance._notifyRowToggle();
					},

					toggleRow: function(config, row) {
						var instance = this;

						if (config && config.toggleCheckbox) {
							var checkbox = row.one(STR_CHECKBOX_SELECTOR);

							checkbox.attr(STR_CHECKED, !checkbox.attr(STR_CHECKED));
						}

						row.toggleClass(instance.get(STR_ROW_CLASS_NAME_ACTIVE));

						instance._notifyRowToggle();
					},

					_addRestoreTask: function() {
						var instance = this;

						var host = instance.get(STR_HOST);

						Liferay.DOMTaskRunner.addTask(
							{
								action: A.Plugin.SearchContainerSelect.restoreTask,
								condition: A.Plugin.SearchContainerSelect.testRestoreTask,
								params: {
									containerId: host.get(STR_CONTENT_BOX).attr('id'),
									rowClassNameActive: instance.get(STR_ROW_CLASS_NAME_ACTIVE),
									rowSelector: instance.get(STR_ROW_SELECTOR),
									searchContainerId: host.get('id')
								}
							}
						);
					},

					_addRestoreTaskState: function() {
						var instance = this;

						var host = instance.get(STR_HOST);

						var elements = [];

						var selectedElements = instance.getAllSelectedElements();

						selectedElements.each(
							function(item, index) {
								elements.push(
									{
										name: item.attr('name'),
										value: item.val()
									}
								);
							}
						);

						Liferay.DOMTaskRunner.addTaskState(
							{
								data: {
									elements: elements
								},
								owner: host.get('id')
							}
						);
					},

					_getAllElements: function(onlySelected) {
						var instance = this;

						return instance._getElements(STR_CHECKBOX_SELECTOR, onlySelected);
					},

					_getCurrentPageElements: function(onlySelected) {
						var instance = this;

						return instance._getElements(instance.get(STR_ROW_SELECTOR) + ' ' + STR_CHECKBOX_SELECTOR, onlySelected);
					},

					_getElements: function(selector, onlySelected) {
						var instance = this;

						var host = instance.get(STR_HOST);

						var checked = onlySelected ? ':' + STR_CHECKED : '';

						return host.get(STR_CONTENT_BOX).all(selector + checked);
					},

					_isActionUrl: function(url) {
						var uri = new A.Url(url);

						return uri.getParameter('p_p_lifecycle') === 1;
					},

					_notifyRowToggle: function() {
						var instance = this;

						instance.get(STR_HOST).fire(
							'rowToggled',
							{
								elements: {
									allElements: instance._getAllElements(),
									allSelectedElements: instance.getAllSelectedElements(),
									currentPageElements: instance._getCurrentPageElements(),
									currentPageSelectedElements: instance.getCurrentPageSelectedElements()
								}
							}
						);
					},

					_onClickRowSelector: function(config, event) {
						var instance = this;

						var row = event.currentTarget.ancestor(instance.get(STR_ROW_SELECTOR));

						instance.toggleRow(config, row);
					},

					_onStartNavigate: function(event) {
						var instance = this;

						if (!instance._isActionUrl(event.path) && instance.get('keepSelection').test(unescape(event.path))) {
							instance._addRestoreTask();
							instance._addRestoreTaskState();
						}
					}
				},

				restoreTask: function(state, params, node) {
					var container = A.one(node).one('#' + params.containerId);

					var offScreenElementsHtml = '';

					AArray.each(
						state.data.elements,
						function(item) {
							var input = container.one(Lang.sub(TPL_INPUT_SELECTOR, item));

							if (input) {
								input.attr(STR_CHECKED, true);
								input.ancestor(params.rowSelector).addClass(params.rowClassNameActive);
							}
							else {
								offScreenElementsHtml += Lang.sub(TPL_HIDDEN_INPUT, item);
							}
						}
					);

					container.append(offScreenElementsHtml);
				},

				testRestoreTask: function(state, params, node) {
					return state.owner === params.searchContainerId && A.one(node).one('#' + params.containerId);
				}
			}
		);

		A.Plugin.SearchContainerSelect = SearchContainerSelect;
	},
	'',
	{
		requires: ['aui-component', 'aui-url', 'plugin']
	}
);