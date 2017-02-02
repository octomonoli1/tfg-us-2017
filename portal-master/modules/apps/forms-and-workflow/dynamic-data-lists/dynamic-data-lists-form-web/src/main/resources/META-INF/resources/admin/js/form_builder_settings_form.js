AUI.add(
	'liferay-ddl-form-builder-settings-form',
	function(A) {
		var Lang = A.Lang;

		var CSS_FIELD_SETTINGS_SAVE = A.getClassName('lfr', 'ddl', 'field', 'settings', 'save');

		var TPL_OPTION = '<option {status} value="{value}">{label}</option>';

		var TPL_SETTINGS_FORM = '<form action="javascript:;"></form>';

		var TPL_SETTINGS_TOGGLER = '<button class="btn settings-toggler" type="button"><span class="settings-toggle-label"></span><span class="settings-toggle-icon"></span></button>';

		var TPL_SUBMIT_BUTTON = '<button class="hide" type="submit" />';

		var FormBuilderSettingsForm = A.Component.create(
			{
				ATTRS: {
					dataProviders: {
					},

					field: {
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Form,

				NAME: 'liferay-ddl-form-builder-settings-form',

				prototype: {
					initializer: function() {
						var instance = this;

						instance._initDataProvider();

						var labelField = instance.getField('label');
						var nameField = instance.getField('name');

						instance._eventHandlers.push(
							instance.after('render', instance._afterSettingsFormRender),
							instance.on('*:addField', instance.alignModal),
							instance.on('*:removeField', instance.alignModal),
							labelField.after(A.bind('_afterLabelFieldNormalizeKey', instance), labelField, 'normalizeKey'),
							labelField.on('keyChange', instance._onLabelFieldKeyChange, instance),
							nameField.on('valueChange', instance._onNameChange, instance)
						);
					},

					alignModal: function() {
						var instance = this;

						var modalSettings = instance.get('field').getSettingsModal();

						modalSettings._modal.align();
					},

					getSubmitButton: function() {
						var instance = this;

						var footerNode = instance._getModalStdModeNode(A.WidgetStdMod.FOOTER);

						return footerNode.one('.' + CSS_FIELD_SETTINGS_SAVE);
					},

					submit: function(callback) {
						var instance = this;

						instance.validateSettings(
							function(hasErrors) {
								if (!hasErrors) {
									var field = instance.get('field');

									var settingsModal = field.getSettingsModal();

									field.saveSettings();

									settingsModal.fire(
										'save',
										{
											field: field
										}
									);

									var builder = field.get('builder');

									builder.appendChild(field);

									settingsModal.hide();
								}

								if (callback) {
									callback.apply(instance, arguments);
								}
							}
						);
					},

					validateSettings: function(callback) {
						var instance = this;

						instance.validate(
							function(hasErrors) {
								hasErrors = instance._handleValidationResponse(hasErrors);

								if (callback) {
									callback.call(instance, hasErrors);
								}
							}
						);
					},

					_afterDataSourceTypeFieldValueChanged: function(event) {
						var instance = this;

						var optionsField = instance.getField('options');

						optionsField.set('required', event.target.getValue() === 'manual');
					},

					_afterDDMDataProviderInstanceIdFieldRender: function(event) {
						var instance = this;

						var ddmDataProviderInstanceIdField = event.target;

						var ddmDataProviderInstanceId = ddmDataProviderInstanceIdField.get('value');

						ddmDataProviderInstanceIdField.getInputNode().html(
							instance.get('dataProviders').map(
								function(item) {
									var status = '';

									if (item.id === ddmDataProviderInstanceId) {
										status = 'selected';
									}

									return Lang.sub(
										TPL_OPTION,
										{
											label: item.name,
											status: status,
											value: item.id
										}
									);
								}
							).join('')
						);

						var dataSourceTypeField = instance.getField('dataSourceType');

						var dataSourceType = dataSourceTypeField.getValue();

						ddmDataProviderInstanceIdField.set('visible', dataSourceType !== 'manual');

						var optionsField = instance.getField('options');

						var manualDataSourceType = dataSourceType === 'manual';

						optionsField.set('required', manualDataSourceType);
						optionsField.set('visible', manualDataSourceType);
					},

					_afterLabelFieldNormalizeKey: function(key) {
						var instance = this;

						return new A.Do.AlterReturn(null, instance._generateFieldName(A.Do.originalRetVal));
					},

					_afterSettingsFormRender: function() {
						var instance = this;

						var container = instance.get('container');

						container.append(TPL_SUBMIT_BUTTON);

						instance._createModeToggler();

						instance._syncModeToggler();

						var formName = A.guid();

						container.attr('id', formName);
						container.attr('name', formName);

						Liferay.Form.register(
							{
								id: formName
							}
						);
					},

					_createModeToggler: function() {
						var instance = this;

						var advancedSettingsNode = instance.getPageNode(2);

						var settingsTogglerNode = A.Node.create(TPL_SETTINGS_TOGGLER);

						advancedSettingsNode.placeBefore(settingsTogglerNode);

						settingsTogglerNode.on('click', A.bind('_onClickModeToggler', instance));

						instance.settingsTogglerNode = settingsTogglerNode;
					},

					_generateFieldName: function(key) {
						var instance = this;

						var counter = 0;

						var field = instance.get('field');

						var builder = field.get('builder');

						var existingField;

						if (!key) {
							key = field.get('type');
						}

						var name = key;

						do {
							if (counter > 0) {
								name = key + counter;
							}

							existingField = builder.getField(name);

							counter++;
						}
						while (existingField !== undefined && existingField !== field);

						return name;
					},

					_getModalStdModeNode: function(mode) {
						var instance = this;

						var field = instance.get('field');

						var settingsModal = field.getSettingsModal();

						return settingsModal._modal.getStdModNode(mode);
					},

					_handleValidationResponse: function(hasErrors) {
						var instance = this;

						var field = instance.get('field');

						var builder = field.get('builder');

						var nameField = instance.getField('name');

						var sameNameField = builder.getField(nameField.getValue());

						if (!!sameNameField && sameNameField !== field) {
							nameField.showErrorMessage(Liferay.Language.get('field-name-is-already-in-use'));
							nameField.showValidationStatus();

							nameField.focus();

							hasErrors = true;
						}

						return hasErrors;
					},

					_initDataProvider: function() {
						var instance = this;

						var ddmDataProviderInstanceIdField = instance.getField('ddmDataProviderInstanceId');

						if (ddmDataProviderInstanceIdField) {
							instance._eventHandlers.push(
								ddmDataProviderInstanceIdField.after('render', A.bind('_afterDDMDataProviderInstanceIdFieldRender', instance))
							);
						}

						var dataSourceTypeField = instance.getField('dataSourceType');

						if (dataSourceTypeField) {
							instance._eventHandlers.push(
								dataSourceTypeField.after('valueChanged', A.bind('_afterDataSourceTypeFieldValueChanged', instance))
							);
						}
					},

					_onClickModeToggler: function(event) {
						var instance = this;

						var advancedSettingsNode = instance.getPageNode(2);

						advancedSettingsNode.toggleClass('active');

						instance.alignModal();

						instance._syncModeToggler();
					},

					_onLabelFieldKeyChange: function(event) {
						var instance = this;

						var nameField = instance.getField('name');

						nameField.setValue(event.newVal);
					},

					_onNameChange: function(event) {
						var instance = this;

						var labelField = instance.getField('label');

						labelField.set('key', event.newVal);
					},

					_onSubmitForm: function(event) {
						var instance = this;

						event.preventDefault();

						instance.submit();
					},

					_syncModeToggler: function() {
						var instance = this;

						var advancedSettingsNode = instance.getPageNode(2);

						var settingsTogglerNode = instance.settingsTogglerNode;

						var settingsTogglerIconNode = settingsTogglerNode.one('.settings-toggle-icon');
						var settingsTogglerLabelNode = settingsTogglerNode.one('.settings-toggle-label');

						var active = advancedSettingsNode.hasClass('active');

						if (active) {
							settingsTogglerIconNode.html(Liferay.Util.getLexiconIconTpl('angle-up'));
							settingsTogglerLabelNode.html(Liferay.Language.get('hide-options'));
						}
						else {
							settingsTogglerIconNode.html(Liferay.Util.getLexiconIconTpl('angle-down'));
							settingsTogglerLabelNode.html(Liferay.Language.get('show-more-options'));
						}

						settingsTogglerNode.toggleClass('active', active);
					},

					_valueContainer: function() {
						var instance = this;

						return A.Node.create(TPL_SETTINGS_FORM);
					}
				}
			}
		);

		Liferay.namespace('DDL').FormBuilderSettingsForm = FormBuilderSettingsForm;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer', 'liferay-form']
	}
);