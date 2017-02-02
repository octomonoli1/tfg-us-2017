AUI.add(
	'liferay-ddm-form-renderer-template',
	function(A) {
		var AObject = A.Object;

		var Lang = A.Lang;

		var TPL_ASTERISK = '<i class="icon-asterisk text-warning"></i>';

		var TPL_DIV = '<div></div>';

		var TPL_REQUIRED_FIELDS_WARNING_MESSAGE = '<label class="required-warning">{message}</label>';

		var FormTemplateSupport = function() {
		};

		FormTemplateSupport.ATTRS = {
			layout: {
				valueFn: '_valueLayout'
			},

			locale: {
				value: themeDisplay.getLanguageId()
			},

			readOnly: {
				value: false
			},

			showRequiredFieldsWarning: {
				value: false
			},

			showSubmitButton: {
				value: true
			},

			requiredFieldsWarningMessageHTML: {
				valueFn: '_getRequiredFieldsWarningMessageHTML'
			},

			submitLabel: {
				value: Liferay.Language.get('submit')
			},

			templateNamespace: {
				value: 'ddm.paginated_form'
			}
		};

		FormTemplateSupport.prototype = {
			getTemplate: function() {
				var instance = this;

				var renderer = instance.getTemplateRenderer();

				return renderer(instance.getTemplateContext());
			},

			getTemplateContext: function() {
				var instance = this;

				var layout = instance.get('layout');

				var normalizedLayout = instance._normalizeLayout(layout);

				return {
					pages: normalizedLayout.pages,
					requiredFieldsWarningMessageHTML: instance.get('requiredFieldsWarningMessageHTML'),
					showSubmitButton: instance.get('showSubmitButton'),
					strings: instance.get('strings'),
					submitLabel: instance.get('submitLabel')
				};
			},

			getTemplateRenderer: function() {
				var instance = this;

				var templateNamespace = instance.get('templateNamespace');

				var renderer = AObject.getValue(window, templateNamespace.split('.'));

				if (!renderer) {
					throw new Error('Form template renderer is not defined: "' + templateNamespace);
				}

				return renderer;
			},

			render: function() {
				var instance = this;

				var container = instance.get('container');

				container.html(instance.getTemplate());

				instance.eachField(
					function(field) {
						field.updateContainer();
					}
				);

				instance.fire('render');

				return instance;
			},

			_getFieldNames: function() {
				var instance = this;

				return instance.get('fields').map(
					function(field) {
						return field.get('name');
					}
				);
			},

			_getRequiredFieldsWarningMessageHTML: function() {
				var instance = this;

				return Lang.sub(
					TPL_REQUIRED_FIELDS_WARNING_MESSAGE,
					{
						message: Lang.sub(
							Liferay.Language.get('all-fields-marked-with-x-are-required'),
							{
								'0': TPL_ASTERISK
							}
						)
					}
				);
			},

			_normalizeLayout: function(layout) {
				var instance = this;

				var pages = layout.pages || [];

				return A.merge(
					layout,
					{
						pages: pages.map(A.bind('_normalizeLayoutPage', instance))
					}
				);
			},

			_normalizeLayoutColumn: function(column) {
				var instance = this;

				return A.merge(
					column,
					{
						fields: column.fieldNames.map(A.bind('_renderFieldTemplate', instance)),
						size: column.size
					}
				);
			},

			_normalizeLayoutPage: function(page) {
				var instance = this;

				var locale = instance.get('locale');

				instance._pageHasRequiredFields = false;

				var rows = page.rows.map(A.bind('_normalizeLayoutRow', instance));

				var showRequiredFieldsWarning = instance.get('showRequiredFieldsWarning');

				return A.merge(
					page,
					{
						description: (page.description && page.description[locale]) || '',
						rows: rows,
						showRequiredFieldsWarning: showRequiredFieldsWarning && instance._pageHasRequiredFields,
						title: (page.title && page.title[locale]) || ''
					}
				);
			},

			_normalizeLayoutRow: function(row) {
				var instance = this;

				return A.merge(
					row,
					{
						columns: row.columns.map(A.bind('_normalizeLayoutColumn', instance))
					}
				);
			},

			_renderFieldTemplate: function(fieldName) {
				var instance = this;

				var field = instance.getField(fieldName);

				if (!instance._pageHasRequiredFields) {
					instance._pageHasRequiredFields = field.get('required');
				}

				return field.getRepeatedSiblings().map(
					function(sibling) {
						var fragment = A.Node.create(TPL_DIV);

						var container = field._createContainer();

						container.html(sibling.getTemplate());

						container.appendTo(fragment);

						return fragment.html();
					}
				).join('');
			},

			_valueLayout: function() {
				var instance = this;

				var fieldNames = instance._getFieldNames();

				var rows = fieldNames.map(
					function(fieldName) {
						return {
							columns: [
								{
									fieldNames: [fieldName],
									size: instance.get('size')
								}
							]
						};
					}
				);

				return {
					pages: [
						{
							rows: rows,
							title: {
								en_US: Liferay.Language.get('title')
							}
						}
					]
				};
			}
		};

		Liferay.namespace('DDM.Renderer').FormTemplateSupport = FormTemplateSupport;
	},
	'',
	{
		requires: ['aui-base']
	}
);