AUI.add(
	'liferay-ddm-form-field-radio',
	function(A) {
		var RadioField = A.Component.create(
			{
				ATTRS: {
					inline: {
						value: true
					},

					options: {
						validator: Array.isArray,
						value: []
					},

					type: {
						value: 'radio'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-radio',

				prototype: {
					getContextValue: function() {
						var instance = this;

						var value = RadioField.superclass.getContextValue.apply(instance, arguments);

						if (!Array.isArray(value)) {
							try {
								value = JSON.parse(value);
							}
							catch (e) {
								value = [value];
							}
						}

						return value[0];
					},

					getInputNode: function() {
						var instance = this;

						var container = instance.get('container');

						var inputNode = container.one('input[type="radio"]:checked');

						if (inputNode === null) {
							inputNode = container.one('input[type="radio"]');
						}

						return inputNode;
					},

					getOptions: function() {
						var instance = this;

						var value = instance.getContextValue();

						return A.map(
							instance.get('options'),
							function(item) {
								return {
									label: item.label[instance.get('locale')],
									status: value === item.value ? 'checked' : '',
									value: item.value
								};
							}
						);
					},

					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							RadioField.superclass.getTemplateContext.apply(instance, arguments),
							{
								inline: instance.get('inline'),
								options: instance.getOptions()
							}
						);
					},

					getValue: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						var value = '';

						if (inputNode.attr('checked')) {
							value = inputNode.val();
						}

						return value;
					},

					setValue: function(value) {
						var instance = this;

						var container = instance.get('container');

						var radiosNodeList = container.all('input[type="radio"]');

						radiosNodeList.removeAttribute('checked');

						var radioToCheck = radiosNodeList.filter(
							function(node) {
								return node.val() === value;
							}
						).item(0);

						if (radioToCheck) {
							radioToCheck.attr('checked', true);

							instance.fire(
								'valueChanged',
								{
									field: instance,
									value: value
								}
							);
						}
					},

					_renderErrorMessage: function() {
						var instance = this;

						var container = instance.get('container');

						RadioField.superclass._renderErrorMessage.apply(instance, arguments);

						container.all('.help-block').appendTo(container.one('.form-group'));
					},

					_showFeedback: function() {
						var instance = this;

						RadioField.superclass._showFeedback.apply(instance, arguments);

						var container = instance.get('container');

						var feedBack = container.one('.form-control-feedback');

						feedBack.appendTo(container);
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').Radio = RadioField;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-field']
	}
);