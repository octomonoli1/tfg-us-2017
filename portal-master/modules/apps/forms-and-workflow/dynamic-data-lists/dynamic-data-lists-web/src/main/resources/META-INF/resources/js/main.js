AUI.add(
	'liferay-portlet-dynamic-data-lists',
	function(A) {
		var AArray = A.Array;

		var DateMath = A.DataType.DateMath;

		var FormBuilder = Liferay.FormBuilder;

		var Lang = A.Lang;

		var EMPTY_FN = A.Lang.emptyFn;

		var FIELDS_DISPLAY_INSTANCE_SEPARATOR = '_INSTANCE_';

		var FIELDS_DISPLAY_NAME = '_fieldsDisplay';

		var STR_EMPTY = '';

		var isArray = Array.isArray;
		var isNumber = Lang.isNumber;

		var SpreadSheet = A.Component.create(
			{
				ATTRS: {
					portletNamespace: {
						validator: Lang.isString,
						value: STR_EMPTY
					},

					recordsetId: {
						validator: isNumber,
						value: 0
					},

					structure: {
						validator: isArray,
						value: []
					}
				},

				CSS_PREFIX: 'table',

				DATATYPE_VALIDATOR: {
					'double': 'number',
					'integer': 'digits',
					'long': 'digits'
				},

				EXTENDS: A.DataTable,

				NAME: A.DataTable.Base.NAME,

				TYPE_EDITOR: {
					'checkbox': A.CheckboxCellEditor,
					'ddm-date': A.DateCellEditor,
					'ddm-decimal': A.TextCellEditor,
					'ddm-documentlibrary': FormBuilder.CUSTOM_CELL_EDITORS['document-library-file-entry-cell-editor'],
					'ddm-integer': A.TextCellEditor,
					'ddm-link-to-page': FormBuilder.CUSTOM_CELL_EDITORS['link-to-page-cell-editor'],
					'ddm-number': A.TextCellEditor,
					'radio': A.RadioCellEditor,
					'select': A.DropDownCellEditor,
					'text': A.TextCellEditor,
					'textarea': A.TextAreaCellEditor
				},

				prototype: {
					initializer: function() {
						var instance = this;

						instance._setDataStableSort(instance.get('data'));

						instance.set('scrollable', true);

						instance.on('dataChange', instance._onDataChange);
						instance.on('model:change', instance._onRecordUpdate);
					},

					addEmptyRows: function(num) {
						var instance = this;

						var columns = instance.get('columns');
						var data = instance.get('data');

						var keys = columns.map(
							function(item, index) {
								return item.key;
							}
						);

						data.add(SpreadSheet.buildEmptyRecords(num, keys));
					},

					updateMinDisplayRows: function(minDisplayRows, callback) {
						var instance = this;

						callback = callback && A.bind(callback, instance) || EMPTY_FN;

						var recordsetId = instance.get('recordsetId');

						Liferay.Service(
							'/ddl.ddlrecordset/update-min-display-rows',
							{
								minDisplayRows: minDisplayRows,
								recordSetId: recordsetId,
								serviceContext: JSON.stringify(
									{
										scopeGroupId: themeDisplay.getScopeGroupId(),
										userId: themeDisplay.getUserId()
									}
								)
							},
							callback
						);
					},

					_afterActiveCellIndexChange: function(event) {
						var instance = this;

						var activeCell = instance.get('activeCell');
						var boundingBox = instance.get('boundingBox');

						var scrollableElement = boundingBox.one('.table-x-scroller');

						var tableHighlightBorder = instance.highlight.get('activeBorderWidth')[0];

						var activeCellWidth = activeCell.outerWidth() + tableHighlightBorder;
						var scrollableWidth = scrollableElement.outerWidth();

						var activeCellOffsetLeft = activeCell.get('offsetLeft');
						var scrollLeft = scrollableElement.get('scrollLeft');

						var activeCellOffsetRight = activeCellOffsetLeft + activeCellWidth;

						var scrollTo = scrollLeft;

						if (scrollLeft + scrollableWidth < activeCellOffsetRight) {
							scrollTo = activeCellOffsetRight - scrollableWidth;
						}
						else if (activeCellOffsetLeft < scrollLeft) {
							scrollTo = activeCellOffsetLeft;
						}

						scrollableElement.set('scrollLeft', scrollTo);
					},

					_normalizeFieldData: function(item, record, fieldsDisplayValues, normalized) {
						var instance = this;

						var type = item.type;
						var value = record.get(item.name);

						if (type === 'ddm-link-to-page') {
							value = FormBuilder.Util.parseJSON(value);

							delete value.name;

							value = JSON.stringify(value);
						}
						else if (type === 'radio' || type === 'select') {
							if (!isArray(value)) {
								value = AArray(value);
							}

							value = JSON.stringify(value);
						}

						normalized[item.name] = instance._normalizeValue(value);

						fieldsDisplayValues.push(item.name + FIELDS_DISPLAY_INSTANCE_SEPARATOR + instance._randomString(8));

						if (isArray(item.fields)) {
							item.fields.forEach(
								function(item) {
									instance._normalizeFieldData(item, record, fieldsDisplayValues, normalized);
								}
							);
						}

					},

					_normalizeRecordData: function(record) {
						var instance = this;

						var structure = instance.get('structure');

						var fieldsDisplayValues = [];
						var normalized = {};

						structure.forEach(
							function(item) {
								instance._normalizeFieldData(item, record, fieldsDisplayValues, normalized);
							}
						);

						normalized[FIELDS_DISPLAY_NAME] = fieldsDisplayValues.join(',');

						delete normalized.displayIndex;
						delete normalized.recordId;

						return normalized;
					},

					_normalizeValue: function(value) {
						var instance = this;

						return String(value);
					},

					_onDataChange: function(event) {
						var instance = this;

						instance._setDataStableSort(event.newVal);
					},

					_onEditCell: function(event) {
						var instance = this;

						SpreadSheet.superclass._onEditCell.apply(instance, arguments);

						var activeCell = instance.get('activeCell');

						var alignNode = event.alignNode || activeCell;

						var column = instance.getColumn(alignNode);
						var record = instance.getRecord(alignNode);

						var data = instance.get('data');
						var portletNamespace = instance.get('portletNamespace');
						var recordsetId = instance.get('recordsetId');
						var structure = instance.get('structure');

						var editor = instance.getEditor(record, column);

						if (editor) {
							editor.setAttrs(
								{
									data: data,
									portletNamespace: portletNamespace,
									record: record,
									recordsetId: recordsetId,
									structure: structure,
									zIndex: Liferay.zIndex.OVERLAY
								}
							);
						}
					},

					_onRecordUpdate: function(event) {
						var instance = this;

						if (!event.changed.hasOwnProperty('recordId')) {
							var data = instance.get('data');
							var recordsetId = instance.get('recordsetId');

							var record = event.target;

							var recordId = record.get('recordId');

							var fieldsMap = instance._normalizeRecordData(record);

							var recordIndex = data.indexOf(record);

							if (recordId > 0) {
								SpreadSheet.updateRecord(recordId, recordIndex, fieldsMap, true);
							}
							else {
								SpreadSheet.addRecord(
									recordsetId,
									recordIndex,
									fieldsMap,
									function(json) {
										if (json.recordId > 0) {
											record.set(
												'recordId',
												json.recordId,
												{
													silent: true
												}
											);
										}
									}
								);
							}
						}
					},

					_randomString: function(length) {
						var random = Math.random();

						var randomString = random.toString(36);

						return randomString.substring(length);
					},

					_setDataStableSort: function(data) {
						var instance = this;

						data.sort = function(options) {
							if (this.comparator) {
								options = options || {};

								var models = this._items.concat();

								A.ArraySort.stableSort(models, A.bind(this._sort, this));

								var facade = A.merge(
									options,
									{
										models: models,
										src: 'sort'
									}
								);

								if (options.silent) {
									this._defResetFn(facade);
								}
								else {
									this.fire('reset', facade);
								}
							}

							return this;
						};
					}
				},

				addRecord: function(recordsetId, displayIndex, fieldsMap, callback) {
					var instance = this;

					callback = callback && A.bind(callback, instance) || EMPTY_FN;

					Liferay.Service(
						'/ddl.ddlrecord/add-record',
						{
							displayIndex: displayIndex,
							fieldsMap: JSON.stringify(fieldsMap),
							groupId: themeDisplay.getScopeGroupId(),
							recordSetId: recordsetId,
							serviceContext: JSON.stringify(
								{
									scopeGroupId: themeDisplay.getScopeGroupId(),
									userId: themeDisplay.getUserId(),
									workflowAction: Liferay.Workflow.ACTION_PUBLISH
								}
							)
						},
						callback
					);
				},

				buildDataTableColumns: function(columns, structure, editable) {
					var instance = this;

					columns.forEach(
						function(item, index) {
							var dataType = item.dataType;
							var label = item.label;
							var name = item.name;
							var type = item.type;

							item.key = name;

							var EditorClass = instance.TYPE_EDITOR[type] || A.TextCellEditor;

							var config = {
								elementName: name,
								strings: {
									cancel: Liferay.Language.get('cancel'),
									edit: Liferay.Language.get('edit'),
									save: Liferay.Language.get('save')
								},
								validator: {
									rules: {}
								}
							};

							var required = item.required;

							var structureField;

							if (required) {
								label += ' (' + Liferay.Language.get('required') + ')';
							}

							label = A.Escape.html(label);

							item.label = label;

							if (type === 'checkbox') {
								config.options = {
									'true': Liferay.Language.get('true')
								};

								config.inputFormatter = function(value) {
									return String(value.length > 0);
								};

								item.formatter = function(obj) {
									var data = obj.data;

									var value = data[name];

									if (value === 'true') {
										value = Liferay.Language.get('true');
									}
									else if (value === 'false') {
										value = Liferay.Language.get('false');
									}

									return value;
								};
							}
							else if (type === 'ddm-date') {
								config.inputFormatter = function(val) {
									return val.map(
										function(item, index) {
											return A.DataType.Date.format(item);
										}
									);
								};

								config.outputFormatter = function(val) {
									return val.map(
										function(item, index) {
											var date;

											if (item !== STR_EMPTY) {
												date = A.DataType.Date.parse(item);
											}
											else {
												date = new Date();
											}

											date = DateMath.add(date, DateMath.MINUTES, date.getTimezoneOffset());

											return date;
										}
									);
								};

								item.formatter = function(obj) {
									var data = obj.data;

									var value = data[name];

									if (isArray(value)) {
										value = value[0];
									}

									return value;
								};
							}
							else if (type === 'ddm-decimal' || type === 'ddm-integer' || type === 'ddm-number') {
								config.outputFormatter = function(value) {
									var number = A.DataType.Number.parse(value);

									var numberValue = STR_EMPTY;

									if (isNumber(number)) {
										numberValue = number;
									}

									return numberValue;
								};

								item.formatter = function(obj) {
									var data = obj.data;

									var value = A.DataType.Number.parse(data[name]);

									if (!isNumber(value)) {
										value = STR_EMPTY;
									}

									return value;
								};
							}
							else if (type === 'ddm-documentlibrary') {
								item.formatter = function(obj) {
									var data = obj.data;

									var label = STR_EMPTY;
									var value = data[name];

									if (value !== STR_EMPTY) {
										var fileData = FormBuilder.Util.parseJSON(value);

										if (fileData.title) {
											label = fileData.title;
										}
									}

									return label;
								};
							}
							else if (type === 'ddm-link-to-page') {
								item.formatter = function(obj) {
									var data = obj.data;

									var label = STR_EMPTY;
									var value = data[name];

									if (value !== STR_EMPTY) {
										var linkToPageData = FormBuilder.Util.parseJSON(value);

										if (linkToPageData.name) {
											label = linkToPageData.name;
										}
									}

									return label;
								};
							}
							else if (type === 'radio' || type === 'select') {
								structureField = instance.findStructureFieldByAttribute(structure, 'name', name);

								var multiple = A.DataType.Boolean.parse(structureField.multiple);
								var options = instance.getCellEditorOptions(structureField.options);

								item.formatter = function(obj) {
									var data = obj.data;

									var label = [];
									var value = data[name];

									if (isArray(value)) {
										value.forEach(
											function(item1, index1) {
												label.push(options[item1]);
											}
										);
									}

									return label.join(', ');
								};

								config.inputFormatter = AArray;
								config.multiple = multiple;
								config.options = options;
							}

							var validatorRuleName = instance.DATATYPE_VALIDATOR[dataType];

							var validatorRules = config.validator.rules;

							validatorRules[name] = A.mix(
								{
									required: required
								},
								validatorRules[name]
							);

							if (validatorRuleName) {
								validatorRules[name][validatorRuleName] = true;
							}

							if (editable && item.editable) {
								item.editor = new EditorClass(config);
							}
						}
					);

					return columns;
				},

				buildEmptyRecords: function(num, keys) {
					var instance = this;

					var emptyRows = [];

					for (var i = 0; i < num; i++) {
						emptyRows.push(instance.getRecordModel(keys));
					}

					return emptyRows;
				},

				findStructureFieldByAttribute: function(fieldsArray, attributeName, attributeValue) {
					var instance = this;

					var	structureField;

					AArray.some(
						fieldsArray,
						function(item) {
							var nestedFieldsArray = item.fields;

							if (item[attributeName] === attributeValue) {
								structureField = item;
							}
							else if (nestedFieldsArray) {
								structureField = instance.findStructureFieldByAttribute(nestedFieldsArray, attributeName, attributeValue);
							}

							return structureField !== undefined;
						}
					);

					return structureField;
				},

				getCellEditorOptions: function(options) {
					var normalized = {};

					options.forEach(
						function(item, index) {
							normalized[item.value] = item.label;
						}
					);

					return normalized;
				},

				getRecordModel: function(keys) {
					var instance = this;

					var recordModel = {};

					keys.forEach(
						function(item, index) {
							recordModel[item] = STR_EMPTY;
						}
					);

					return recordModel;
				},

				updateRecord: function(recordId, displayIndex, fieldsMap, merge, callback) {
					var instance = this;

					callback = callback && A.bind(callback, instance) || EMPTY_FN;

					Liferay.Service(
						'/ddl.ddlrecord/update-record',
						{
							displayIndex: displayIndex,
							fieldsMap: JSON.stringify(fieldsMap),
							mergeFields: merge,
							recordId: recordId,
							serviceContext: JSON.stringify(
								{
									scopeGroupId: themeDisplay.getScopeGroupId(),
									userId: themeDisplay.getUserId(),
									workflowAction: Liferay.Workflow.ACTION_PUBLISH
								}
							)
						},
						callback
					);
				}
			}
		);

		Liferay.SpreadSheet = SpreadSheet;

		var DDLUtil = {
			previewDialog: null,

			openPreviewDialog: function(content) {
				var instance = this;

				var previewDialog = instance.previewDialog;

				if (!previewDialog) {
					previewDialog = Liferay.Util.Window.getWindow(
						{
							dialog: {
								bodyContent: content
							},
							title: Liferay.Language.get('preview')
						}
					);

					instance.previewDialog = previewDialog;
				}
				else {
					previewDialog.show();

					previewDialog.set('bodyContent', content);
				}
			}
		};

		Liferay.DDLUtil = DDLUtil;
	},
	'',
	{
		requires: ['aui-arraysort', 'aui-datatable', 'datatable-sort', 'json', 'liferay-portlet-dynamic-data-mapping-custom-fields', 'liferay-portlet-url', 'liferay-util-window']
	}
);