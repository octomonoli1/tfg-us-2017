AUI.add(
	'liferay-portlet-dynamic-data-mapping-custom-fields',
	function(A) {
		var AArray = A.Array;

		var FormBuilderTextField = A.FormBuilderTextField;
		var FormBuilderTypes = A.FormBuilderField.types;

		var LiferayFormBuilderUtil = Liferay.FormBuilder.Util;

		var Lang = A.Lang;

		var LString = Lang.String;

		var booleanParse = A.DataType.Boolean.parse;
		var camelize = Lang.String.camelize;
		var instanceOf = A.instanceOf;
		var isNull = Lang.isNull;
		var isObject = Lang.isObject;
		var isUndefined = Lang.isUndefined;
		var isValue = Lang.isValue;

		var DEFAULTS_FORM_VALIDATOR = A.config.FormValidator;

		var LOCALIZABLE_FIELD_ATTRS = Liferay.FormBuilder.LOCALIZABLE_FIELD_ATTRS;

		var STR_BLANK = '';

		var STR_DASH = '-';

		var STR_SPACE = ' ';

		var TPL_GEOLOCATION = '<div class="field-labels-inline">' +
				'<img src="' + themeDisplay.getPathThemeImages() + '/common/geolocation.png" title="' + A.Escape.html(Liferay.Language.get('geolocate')) + '" />' +
			'<div>';

		var TPL_INPUT_BUTTON = '<div class="form-group">' +
				'<input class="field form-control" type="text" value="" readonly="readonly">' +
				'<div class="button-holder">' +
					'<button class="btn select-button btn-default" type="button">' +
						'<span class="lfr-btn-label">' + A.Escape.html(Liferay.Language.get('select')) + '</span>' +
					'</button>' +
				'</div>' +
			'</div>';

		var TPL_PARAGRAPH = '<p></p>';

		var TPL_SEPARATOR = '<div class="separator"></div>';

		var TPL_TEXT_HTML = '<textarea class="form-builder-field-node lfr-ddm-text-html"></textarea>';

		var TPL_WCM_IMAGE = '<div class="form-group">' +
				'<input class="field form-control" type="text" value="" readonly="readonly">' +
				'<div class="button-holder">' +
					'<button class="btn select-button btn-default" type="button">' +
						'<span class="lfr-btn-label">' + A.Escape.html(Liferay.Language.get('select')) + '</span>' +
					'</button>' +
				'</div>' +
				'<label class="control-label">' + A.Escape.html(Liferay.Language.get('image-description')) + '</label>' +
				'<input class="field form-control" type="text" value="" disabled>' +
			'</div>';

		var UNIQUE_FIELD_NAMES_MAP = Liferay.FormBuilder.UNIQUE_FIELD_NAMES_MAP;

		var UNLOCALIZABLE_FIELD_ATTRS = Liferay.FormBuilder.UNLOCALIZABLE_FIELD_ATTRS;

		DEFAULTS_FORM_VALIDATOR.STRINGS.structureDuplicateFieldName = Liferay.Language.get('please-enter-a-unique-field-name');

		DEFAULTS_FORM_VALIDATOR.RULES.structureDuplicateFieldName = function(value, editorNode) {
			var instance = this;

			var editingField = UNIQUE_FIELD_NAMES_MAP.getValue(value);

			var duplicate = editingField && !editingField.get('selected');

			if (duplicate) {
				editorNode.selectText(0, value.length);

				instance.resetField(editorNode);
			}

			return !duplicate;
		};

		DEFAULTS_FORM_VALIDATOR.STRINGS.structureFieldName = Liferay.Language.get('please-enter-only-alphanumeric-characters');

		DEFAULTS_FORM_VALIDATOR.RULES.structureFieldName = function(value) {
			return LiferayFormBuilderUtil.validateFieldName(value);
		};

		var applyStyles = function(node, styleContent) {
			var styles = styleContent.replace(/\n/g, STR_BLANK).split(';');

			node.setStyle(STR_BLANK);

			styles.forEach(
				function(item, index) {
					var rule = item.split(':');

					if (rule.length == 2) {
						var key = camelize(rule[0]);
						var value = rule[1].trim();

						node.setStyle(key, value);
					}
				}
			);
		};

		var DLFileEntryCellEditor = A.Component.create(
			{
				EXTENDS: A.BaseCellEditor,

				NAME: 'document-library-file-entry-cell-editor',

				prototype: {
					ELEMENT_TEMPLATE: '<input type="hidden" />',

					getElementsValue: function() {
						var instance = this;

						return instance.get('value');
					},

					_defInitToolbarFn: function() {
						var instance = this;

						DLFileEntryCellEditor.superclass._defInitToolbarFn.apply(instance, arguments);

						instance.toolbar.add(
							{
								label: Liferay.Language.get('select'),
								on: {
									click: A.bind('_onClickChoose', instance)
								}
							},
							1
						);

						instance.toolbar.add(
							{
								label: Liferay.Language.get('clear'),
								on: {
									click: A.bind('_onClickClear', instance)
								}
							},
							2
						);
					},

					_getDocumentLibrarySelectorURL: function() {
						var instance = this;

						var scopeGroupId = Liferay.ThemeDisplay.getScopeGroupId();

						var portletNamespace = instance.get('portletNamespace');

						var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

						portletURL.setDoAsGroupId(scopeGroupId);
						portletURL.setParameter('criteria', 'com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion');
						portletURL.setParameter('itemSelectedEventName', portletNamespace + 'selectDocumentLibrary');

						var criterionJSON = {
							desiredItemSelectorReturnTypes: 'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType'
						};

						portletURL.setParameter('0_json', JSON.stringify(criterionJSON));
						portletURL.setParameter('1_json', JSON.stringify(criterionJSON));

						var uploadCriterionJSON = {
							desiredItemSelectorReturnTypes: 'com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType',
							URL: instance._getUploadURL()
						};

						portletURL.setParameter('2_json', JSON.stringify(uploadCriterionJSON));
						portletURL.setPortletId(Liferay.PortletKeys.ITEM_SELECTOR);
						portletURL.setPortletMode('view');
						portletURL.setWindowState('pop_up');

						return portletURL.toString();
					},

					_getUploadURL: function() {
						var instance = this;

						var scopeGroupId = Liferay.ThemeDisplay.getScopeGroupId();

						var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

						portletURL.setDoAsGroupId(scopeGroupId);
						portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
						portletURL.setParameter('cmd', 'add_temp');
						portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
						portletURL.setParameter('p_auth', Liferay.authToken);

						portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);

						return portletURL.toString();
					},

					_isDocumentLibraryDialogOpen: function() {
						var instance = this;

						var portletNamespace = instance.get('portletNamespace');

						return !!Liferay.Util.getWindow(portletNamespace + 'selectDocumentLibrary');
					},

					_onClickChoose: function() {
						var instance = this;

						var portletNamespace = instance.get('portletNamespace');

						var itemSelectorDialog = new A.LiferayItemSelectorDialog(
							{
								eventName: portletNamespace + 'selectDocumentLibrary',
								on: {
									selectedItemChange: function(event) {
										var selectedItem = event.newVal;

										if (selectedItem) {
											var itemValue = JSON.parse(selectedItem.value);

											instance._selectFileEntry(itemValue.groupId, itemValue.title, itemValue.uuid);
										}
									}
								},
								url: instance._getDocumentLibrarySelectorURL()
							}
						);

						itemSelectorDialog.open();

					},

					_onClickClear: function() {
						var instance = this;

						instance.set('value', STR_BLANK);
					},

					_onDocMouseDownExt: function(event) {
						var instance = this;

						var boundingBox = instance.get('boundingBox');

						var documentLibraryDialogOpen = instance._isDocumentLibraryDialogOpen();

						if (!documentLibraryDialogOpen && !boundingBox.contains(event.target)) {
							instance.set('visible', false);
						}
					},

					_selectFileEntry: function(groupId, title, uuid) {
						var instance = this;

						instance.set(
							'value',
							JSON.stringify(
								{
									groupId: groupId,
									title: title,
									uuid: uuid
								}
							)
						);
					},

					_syncElementsFocus: function() {
						var instance = this;

						var boundingBox = instance.toolbar.get('boundingBox');

						var button = boundingBox.one('button');

						if (button) {
							button.focus();
						}
						else {
							DLFileEntryCellEditor.superclass._syncElementsFocus.apply(instance, arguments);
						}
					},

					_syncFileLabel: function(title, url) {
						var instance = this;

						var contentBox = instance.get('contentBox');

						var linkNode = contentBox.one('a');

						if (!linkNode) {
							linkNode = A.Node.create('<a></a>');

							contentBox.prepend(linkNode);
						}

						linkNode.setAttribute('href', url);
						linkNode.setContent(LString.escapeHTML(title));
					},

					_uiSetValue: function(val) {
						var instance = this;

						if (val) {
							LiferayFormBuilderUtil.getFileEntry(
								val,
								function(fileEntry) {
									var url = LiferayFormBuilderUtil.getFileEntryURL(fileEntry);

									instance._syncFileLabel(fileEntry.title, url);
								}
							);
						}
						else {
							instance._syncFileLabel(STR_BLANK, STR_BLANK);

							val = STR_BLANK;
						}

						instance.elements.val(val);
					}
				}
			}
		);

		var LinkToPageCellEditor = A.Component.create(
			{
				EXTENDS: A.DropDownCellEditor,

				NAME: 'link-to-page-cell-editor',

				prototype: {
					OPT_GROUP_TEMPLATE: '<optgroup label="{label}">{options}</optgroup>',

					renderUI: function(val) {
						var instance = this;

						var options = {};

						LinkToPageCellEditor.superclass.renderUI.apply(instance, arguments);

						A.io.request(
							themeDisplay.getPathMain() + '/portal/get_layouts',
							{
								after: {
									success: function() {
										var	response = JSON.parse(this.get('responseData'));

										if (response && response.layouts) {
											instance._createOptionElements(response.layouts, options, STR_BLANK);

											instance.set('options', options);
										}
									}
								},
								data: {
									cmd: 'getAll',
									expandParentLayouts: true,
									groupId: themeDisplay.getScopeGroupId(),
									p_auth: Liferay.authToken,
									paginate: false
								}
							}
						);
					},

					_createOptionElements: function(layouts, options, prefix) {
						var instance = this;

						layouts.forEach(
							function(item, index) {
								options[prefix + item.name] = {
									groupId: item.groupId,
									layoutId: item.layoutId,
									name: item.name,
									privateLayout: item.privateLayout
								};

								if (item.hasChildren) {
									instance._createOptionElements(
										item.children.layouts,
										options,
										prefix + STR_DASH + STR_SPACE
									);
								}
							}
						);
					},

					_createOptions: function(val) {
						var instance = this;

						var privateOptions = [];
						var publicOptions = [];

						A.each(
							val,
							function(item, index) {
								var values = {
									id: A.guid(),
									label: index,
									value: LString.escapeHTML(JSON.stringify(item))
								};

								var optionsArray = publicOptions;

								if (item.privateLayout) {
									optionsArray = privateOptions;
								}

								optionsArray.push(
									Lang.sub(instance.OPTION_TEMPLATE, values)
								);
							}
						);

						var optGroupTemplate = instance.OPT_GROUP_TEMPLATE;

						var publicOptGroup = Lang.sub(
							optGroupTemplate,
							{
								label: Liferay.Language.get('public-pages'),
								options: publicOptions.join(STR_BLANK)
							}
						);

						var privateOptGroup = Lang.sub(
							optGroupTemplate,
							{
								label: Liferay.Language.get('private-pages'),
								options: privateOptions.join(STR_BLANK)
							}
						);

						var elements = instance.elements;

						elements.setContent(publicOptGroup + privateOptGroup);

						instance.options = elements.all('option');
					},

					_uiSetValue: function(val) {
						var instance = this;

						var options = instance.options;

						if (options && options.size()) {
							options.set('selected', false);

							if (isValue(val)) {
								var selLayout = LiferayFormBuilderUtil.parseJSON(val);

								options.each(
									function(item, index) {
										var curLayout = LiferayFormBuilderUtil.parseJSON(item.attr('value'));

										if (curLayout.groupId === selLayout.groupId && curLayout.layoutId === selLayout.layoutId && curLayout.privateLayout === selLayout.privateLayout) {
											item.set('selected', true);
										}
									}
								);
							}
						}

						return val;
					}
				}
			}
		);

		Liferay.FormBuilder.CUSTOM_CELL_EDITORS = {};

		var customCellEditors = [
			DLFileEntryCellEditor,
			LinkToPageCellEditor
		];

		customCellEditors.forEach(
			function(item, index) {
				Liferay.FormBuilder.CUSTOM_CELL_EDITORS[item.NAME] = item;
			}
		);

		var LiferayFieldSupport = function() {
		};

		LiferayFieldSupport.ATTRS = {
			autoGeneratedName: {
				setter: booleanParse,
				value: true
			},

			indexType: {
				value: 'keyword'
			},

			localizable: {
				setter: booleanParse,
				value: true
			},

			name: {
				setter: LiferayFormBuilderUtil.normalizeKey,
				validator: function(val) {
					return !UNIQUE_FIELD_NAMES_MAP.has(val);
				},
				valueFn: function() {
					var instance = this;

					var name = LiferayFormBuilderUtil.normalizeKey(instance.get('label')) + instance._randomString(4);

					while (UNIQUE_FIELD_NAMES_MAP.has(name)) {
						name = A.FormBuilderField.buildFieldName(name);
					}

					return name;
				}
			},

			repeatable: {
				setter: booleanParse,
				value: false
			}
		};

		LiferayFieldSupport.prototype.initializer = function() {
			var instance = this;

			instance.after('nameChange', instance._afterNameChange);
		};

		LiferayFieldSupport.prototype._afterNameChange = function(event) {
			var instance = this;

			UNIQUE_FIELD_NAMES_MAP.remove(event.prevVal);
			UNIQUE_FIELD_NAMES_MAP.put(event.newVal, instance);
		};

		LiferayFieldSupport.prototype._handleDeleteEvent = function(event) {
			var instance = this;

			var strings = instance.getStrings();

			var deleteModal = Liferay.Util.Window.getWindow(
				{
					dialog:	{
						bodyContent: strings.deleteFieldsMessage,
						destroyOnHide: true,
						height: 200,
						resizable: false,
						toolbars: {
							footer: [
								{
									cssClass: 'btn-primary',
									label: Liferay.Language.get('ok'),
									on: {
										click: function() {
											instance.destroy();

											deleteModal.hide();
										}
									}
								},
								{
									label: Liferay.Language.get('cancel'),
									on: {
										click: function() {
											deleteModal.hide();
										}
									}
								}
							]
						},
						width: 700
					},
					title: instance.get('label')
				}
			).render().show();

			event.stopPropagation();
		};

		LiferayFieldSupport.prototype._randomString = function(length) {
			var randomString = Liferay.Util.randomInt().toString(36);

			return randomString.substring(0, length);
		};

		var LocalizableFieldSupport = function() {
		};

		LocalizableFieldSupport.ATTRS = {
			localizationMap: {
				setter: A.clone,
				value: {}
			},

			readOnlyAttributes: {
				getter: '_getReadOnlyAttributes'
			}
		};

		LocalizableFieldSupport.prototype.initializer = function() {
			var instance = this;

			var builder = instance.get('builder');

			instance.after('render', instance._afterLocalizableFieldRender);

			LOCALIZABLE_FIELD_ATTRS.forEach(
				function(localizableField) {
					instance.after(localizableField + 'Change', instance._afterLocalizableFieldChange);
				}
			);

			builder.translationManager.after('editingLocaleChange', instance._afterEditingLocaleChange, instance);
		};

		LocalizableFieldSupport.prototype._afterEditingLocaleChange = function(event) {
			var instance = this;

			instance._syncLocaleUI(event.newVal);
		};

		LocalizableFieldSupport.prototype._afterLocalizableFieldChange = function(event) {
			var instance = this;

			var builder = instance.get('builder');

			var translationManager = builder.translationManager;

			var editingLocale = translationManager.get('editingLocale');

			instance._updateLocalizationMapAttribute(editingLocale, event.attrName);
		};

		LocalizableFieldSupport.prototype._afterLocalizableFieldRender = function(event) {
			var instance = this;

			var builder = instance.get('builder');

			var translationManager = builder.translationManager;

			var editingLocale = translationManager.get('editingLocale');

			instance._updateLocalizationMap(editingLocale);
		};

		LocalizableFieldSupport.prototype._getReadOnlyAttributes = function(val) {
			var instance = this;

			var builder = instance.get('builder');

			var translationManager = builder.translationManager;

			var defaultLocale = translationManager.get('defaultLocale');
			var editingLocale = translationManager.get('editingLocale');

			if (defaultLocale !== editingLocale) {
				val = UNLOCALIZABLE_FIELD_ATTRS.concat(val);
			}

			return AArray.dedupe(val);
		};

		LocalizableFieldSupport.prototype._syncLocaleUI = function(locale) {
			var instance = this;

			var builder = instance.get('builder');

			var localizationMap = instance.get('localizationMap');

			var translationManager = builder.translationManager;

			var defaultLocale = themeDisplay.getDefaultLanguageId();

			if (translationManager) {
				defaultLocale = translationManager.get('defaultLocale');
			}

			var localeMap = localizationMap[locale] || localizationMap[defaultLocale];

			if (isObject(localeMap)) {
				LOCALIZABLE_FIELD_ATTRS.forEach(
					function(item, index) {
						if (item !== 'options') {
							var localizedItem = localeMap[item];

							if (!isUndefined(localizedItem) && !isNull(localizedItem)) {
								instance.set(item, localizedItem);
							}
						}
					}
				);

				builder._syncUniqueField(instance);
			}

			if (instanceOf(instance, A.FormBuilderMultipleChoiceField)) {
				instance._syncOptionsLocaleUI(locale);
			}

			if (builder.editingField === instance) {
				builder.propertyList.set('data', instance.getProperties());
			}
		};

		LocalizableFieldSupport.prototype._syncOptionsLocaleUI = function(locale) {
			var instance = this;

			var options = instance.get('options');

			options.forEach(
				function(item, index) {
					var localizationMap = item.localizationMap;

					if (isObject(localizationMap)) {
						var localeMap = localizationMap[locale];

						if (isObject(localeMap)) {
							item.label = localeMap.label;
						}
					}
				}
			);

			instance.set('options', options);
		};

		LocalizableFieldSupport.prototype._updateLocalizationMap = function(locale) {
			var instance = this;

			LOCALIZABLE_FIELD_ATTRS.forEach(
				function(item, index) {
					instance._updateLocalizationMapAttribute(locale, item);
				}
			);
		};

		LocalizableFieldSupport.prototype._updateLocalizationMapAttribute = function(locale, attributeName) {
			var instance = this;

			if (attributeName === 'options') {
				instance._updateLocalizationMapOptions(locale);
			}
			else {
				var localizationMap = instance.get('localizationMap');

				var localeMap = localizationMap[locale] || {};

				localeMap[attributeName] = instance.get(attributeName);

				localizationMap[locale] = localeMap;

				instance.set('localizationMap', localizationMap);
			}
		};

		LocalizableFieldSupport.prototype._updateLocalizationMapOptions = function(locale) {
			var instance = this;

			var options = instance.get('options');

			if (options) {
				options.forEach(
					function(item, index) {
						var localizationMap = item.localizationMap;

						if (!isObject(localizationMap)) {
							localizationMap = {};
						}

						localizationMap[locale] = {
							label: item.label
						};

						item.localizationMap = localizationMap;
					}
				);
			}
		};

		var SerializableFieldSupport = function() {
		};

		SerializableFieldSupport.prototype._addDefinitionFieldLocalizedAttributes = function(fieldJSON) {
			var instance = this;

			LOCALIZABLE_FIELD_ATTRS.forEach(
				function(attr) {
					if (attr === 'options') {
						if (instanceOf(instance, A.FormBuilderMultipleChoiceField)) {
							instance._addDefinitionFieldOptions(fieldJSON);
						}
					}
					else {
						fieldJSON[attr] = instance._getLocalizedValue(attr);
					}
				}
			);
		};

		SerializableFieldSupport.prototype._addDefinitionFieldUnlocalizedAttributes = function(fieldJSON) {
			var instance = this;

			UNLOCALIZABLE_FIELD_ATTRS.forEach(
				function(attr) {
					fieldJSON[attr] = instance.get(attr);
				}
			);
		};

		SerializableFieldSupport.prototype._addDefinitionFieldOptions = function(fieldJSON) {
			var instance = this;

			var options = instance.get('options');

			var fieldOptions = [];

			if (options) {
				options.forEach(
					function(option) {
						var fieldOption = {};

						var localizationMap = option.localizationMap;

						fieldOption.value = option.value;
						fieldOption.label = {};

						A.each(
							localizationMap,
							function(item, index, collection) {
								fieldOption.label[index] = LiferayFormBuilderUtil.normalizeValue(item.label);
							}
						);

						fieldOptions.push(fieldOption);
					}
				);

				fieldJSON.options = fieldOptions;
			}
		};

		SerializableFieldSupport.prototype._addDefinitionFieldNestedFields = function(fieldJSON) {
			var instance = this;

			var nestedFields = [];

			instance.get('fields').each(
				function(childField) {
					nestedFields.push(
						childField.serialize()
					);
				}
			);

			if (nestedFields.length > 0) {
				fieldJSON.nestedFields = nestedFields;
			}
		};

		SerializableFieldSupport.prototype._getLocalizedValue = function(attribute) {
			var instance = this;

			var builder = instance.get('builder');

			var localizationMap = instance.get('localizationMap');

			var localizedValue = {};

			var translationManager = builder.translationManager;

			var defaultLocale = translationManager.get('defaultLocale');

			translationManager.get('availableLocales').forEach(
				function(locale) {
					var value = A.Object.getValue(localizationMap, [locale, attribute]);

					if (!isValue(value)) {
						value = A.Object.getValue(localizationMap, [defaultLocale, attribute]);

						if (!isValue(value)) {
							value = STR_BLANK;
						}
					}

					localizedValue[locale] = LiferayFormBuilderUtil.normalizeValue(value);
				}
			);

			return localizedValue;
		};

		SerializableFieldSupport.prototype.serialize = function() {
			var instance = this;

			var fieldJSON = {};

			instance._addDefinitionFieldLocalizedAttributes(fieldJSON);
			instance._addDefinitionFieldUnlocalizedAttributes(fieldJSON);
			instance._addDefinitionFieldNestedFields(fieldJSON);

			return fieldJSON;
		};

		A.Base.mix(A.FormBuilderField, [LiferayFieldSupport, LocalizableFieldSupport, SerializableFieldSupport]);

		var FormBuilderProto = A.FormBuilderField.prototype;

		var originalGetPropertyModel = FormBuilderProto.getPropertyModel;

		FormBuilderProto.getPropertyModel = function() {
			var instance = this;

			var model = originalGetPropertyModel.call(instance);

			var type = instance.get('type');

			var indexTypeOptions = {
				'': Liferay.Language.get('no'),
				'keyword': Liferay.Language.get('yes')
			};

			if (type == 'ddm-text-html' || type == 'text' || type == 'textarea') {
				indexTypeOptions = {
					'': Liferay.Language.get('not-indexable'),
					'keyword': Liferay.Language.get('indexable-keyword'),
					'text': Liferay.Language.get('indexable-text')
				};
			}

			var booleanOptions = {
				'false': Liferay.Language.get('no'),
				'true': Liferay.Language.get('yes')
			};

			model.forEach(
				function(item, index) {
					if (item.attributeName == 'name') {
						item.editor = new A.TextCellEditor(
							{
								validator: {
									rules: {
										value: {
											required: true,
											structureDuplicateFieldName: true,
											structureFieldName: true
										}
									}
								}
							}
						);
					}
				}
			);

			return model.concat(
				[
					{
						attributeName: 'indexType',
						editor: new A.RadioCellEditor(
							{
								options: indexTypeOptions
							}
						),
						formatter: function(val) {
							return indexTypeOptions[val.data.value];
						},
						name: Liferay.Language.get('indexable')
					},
					{
						attributeName: 'localizable',
						editor: new A.RadioCellEditor(
							{
								options: booleanOptions
							}
						),
						formatter: function(val) {
							return booleanOptions[val.data.value];
						},
						name: Liferay.Language.get('localizable')
					},
					{
						attributeName: 'repeatable',
						editor: new A.RadioCellEditor(
							{
								options: booleanOptions
							}
						),
						formatter: function(val) {
							return booleanOptions[val.data.value];
						},
						name: Liferay.Language.get('repeatable')
					}
				]
			);
		};

		var DDMDateField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'date'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-date',

				prototype: {
					renderUI: function() {
						var instance = this;

						DDMDateField.superclass.renderUI.apply(instance, arguments);

						instance.datePicker = new A.DatePicker(
							{
								calendar: {
									locale: Liferay.ThemeDisplay.getLanguageId()
								},
								trigger: instance.get('templateNode')
							}
						).render();

						instance.datePicker.calendar.set(
							'strings',
							{
								next: Liferay.Language.get('next'),
								none: Liferay.Language.get('none'),
								previous: Liferay.Language.get('previous'),
								today: Liferay.Language.get('today')
							}
						);
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMDateField.superclass.getPropertyModel.apply(instance, arguments);

						model.forEach(
							function(item, index, collection) {
								var attributeName = item.attributeName;

								if (attributeName === 'predefinedValue') {
									collection[index] = {
										attributeName: attributeName,
										editor: new A.DateCellEditor(
											{
												dateFormat: '%m/%d/%Y',
												inputFormatter: function(val) {
													var instance = this;

													var value = STR_BLANK;

													if (val && val.length) {
														value = instance.formatDate(val[0]);
													}

													return value;
												}
											}
										),
										name: Liferay.Language.get('predefined-value')
									};
								}
							}
						);

						return model;
					}
				}
			}
		);

		var DDMDecimalField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'double'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-decimal'
			}
		);

		var DDMDocumentLibraryField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'document-library'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-documentlibrary',

				prototype: {
					getHTML: function() {
						return TPL_INPUT_BUTTON;
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMDocumentLibraryField.superclass.getPropertyModel.apply(instance, arguments);

						model.forEach(
							function(item, index) {
								var attributeName = item.attributeName;

								if (attributeName === 'predefinedValue') {
									item.editor = new DLFileEntryCellEditor();

									item.formatter = function(obj) {
										var data = obj.data;

										var label = STR_BLANK;

										var value = data.value;

										if (value !== STR_BLANK) {
											label = '(' + Liferay.Language.get('file') + ')';
										}

										return label;
									};
								}
								else if (attributeName === 'type') {
									item.formatter = instance._defaultFormatter;
								}
							}
						);

						return model;
					},

					_defaultFormatter: function() {
						var instance = this;

						return 'documents-and-media';
					},

					_uiSetValue: function() {
						return Liferay.Language.get('select');
					}

				}

			}
		);

		var DDMGeolocationField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'geolocation'
					},

					fieldNamespace: {
						value: 'ddm'
					},

					localizable: {
						setter: booleanParse,
						value: false
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-geolocation',

				prototype: {
					getHTML: function() {
						return TPL_GEOLOCATION;
					},

					getPropertyModel: function() {
						var instance = this;

						return DDMGeolocationField.superclass.getPropertyModel.apply(instance, arguments).filter(
							function(item, index) {
								return item.attributeName !== 'predefinedValue';
							}
						);
					}
				}
			}
		);

		var DDMImageField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'image'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-image',

				prototype: {
					getHTML: function() {
						return TPL_WCM_IMAGE;
					}
				}
			}
		);

		var DDMIntegerField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'integer'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-integer'
			}
		);

		var DDMNumberField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'number'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderTextField,

				NAME: 'ddm-number'
			}
		);

		var DDMParagraphField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: undefined
					},

					fieldNamespace: {
						value: 'ddm'
					},

					showLabel: {
						readOnly: true,
						value: true
					},

					style: {
						value: STR_BLANK
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-paragraph',

				UI_ATTRS: ['label', 'style'],

				prototype: {
					getHTML: function() {
						return TPL_PARAGRAPH;
					},

					getPropertyModel: function() {
						var instance = this;

						return [
							{
								attributeName: 'type',
								editor: false,
								name: Liferay.Language.get('type')
							},
							{
								attributeName: 'label',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('text')
							},
							{
								attributeName: 'style',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('style')
							}
						];
					},

					_uiSetLabel: function(val) {
						var instance = this;

						instance.get('templateNode').setContent(val);
					},

					_uiSetStyle: function(val) {
						var instance = this;

						var templateNode = instance.get('templateNode');

						applyStyles(templateNode, val);
					}
				}
			}
		);

		var DDMSeparatorField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: undefined
					},

					fieldNamespace: {
						value: 'ddm'
					},

					showLabel: {
						value: false
					},

					style: {
						value: STR_BLANK
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-separator',

				UI_ATTRS: ['style'],

				prototype: {
					getHTML: function() {
						return TPL_SEPARATOR;
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMSeparatorField.superclass.getPropertyModel.apply(instance, arguments);

						model.push(
							{
								attributeName: 'style',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('style')
							}
						);

						return model;
					},

					_uiSetStyle: function(val) {
						var instance = this;

						var templateNode = instance.get('templateNode');

						applyStyles(templateNode, val);
					}
				}
			}
		);

		var DDMHTMLTextField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'html'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: FormBuilderTextField,

				NAME: 'ddm-text-html',

				prototype: {
					getHTML: function() {
						return TPL_TEXT_HTML;
					}
				}
			}
		);

		var DDMJournalArticleField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'journal-article'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-journal-article',

				prototype: {
					getHTML: function() {
						return TPL_INPUT_BUTTON;
					},

					getPropertyModel: function() {
						var instance = this;

						var model = DDMJournalArticleField.superclass.getPropertyModel.apply(instance, arguments);

						model.push(
							{
								attributeName: 'style',
								editor: new A.TextAreaCellEditor(),
								name: Liferay.Language.get('style')
							}
						);

						return model;
					}
				}
			}
		);

		var DDMLinkToPageField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'link-to-page'
					},

					fieldNamespace: {
						value: 'ddm'
					}
				},

				EXTENDS: A.FormBuilderField,

				NAME: 'ddm-link-to-page',

				prototype: {
					getHTML: function() {
						return TPL_INPUT_BUTTON;
					}
				}
			}
		);

		var plugins = [
			DDMDateField,
			DDMDecimalField,
			DDMDocumentLibraryField,
			DDMGeolocationField,
			DDMImageField,
			DDMIntegerField,
			DDMJournalArticleField,
			DDMLinkToPageField,
			DDMNumberField,
			DDMParagraphField,
			DDMSeparatorField,
			DDMHTMLTextField
		];

		plugins.forEach(
			function(item, index) {
				FormBuilderTypes[item.NAME] = item;
			}
		);
	},
	'',
	{
		requires: ['liferay-item-selector-dialog', 'liferay-portlet-dynamic-data-mapping']
	}
);