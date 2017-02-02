AUI.add(
	'liferay-ddm-form-renderer-util',
	function(A) {
		var FieldTypes = Liferay.DDM.Renderer.FieldTypes;

		var MAP_DATA_TYPES = {
			number: 'integer',
			text: 'string'
		};

		var VALIDATIONS = {
			number: [
				{
					label: Liferay.Language.get('is-greater-than-or-equal-to'),
					name: 'gteq',
					parameterMessage: Liferay.Language.get('this-number'),
					regex: /^(\w+)\>\=(\d+)$/,
					template: '{name}>={parameter}'
				},
				{
					label: Liferay.Language.get('is-greater-than'),
					name: 'gt',
					parameterMessage: Liferay.Language.get('this-number'),
					regex: /^(\w+)\>(\d+)$/,
					template: '{name}>{parameter}'
				},
				{
					label: Liferay.Language.get('is-equal-to'),
					name: 'eq',
					parameterMessage: Liferay.Language.get('this-number'),
					regex: /^(\w+)\=\=(\d+)$/,
					template: '{name}=={parameter}'
				},
				{
					label: Liferay.Language.get('is-less-than-or-equal-to'),
					name: 'lteq',
					parameterMessage: Liferay.Language.get('this-number'),
					regex: /^(\w+)\<\=(\d+)$/,
					template: '{name}<={parameter}'
				},
				{
					label: Liferay.Language.get('is-less-than'),
					name: 'lt',
					parameterMessage: Liferay.Language.get('this-number'),
					regex: /^(\w+)\<(\d+)$/,
					template: '{name}<{parameter}'
				}
			],
			text: [
				{
					label: Liferay.Language.get('contains'),
					name: 'contains',
					parameterMessage: Liferay.Language.get('this-text'),
					regex: /^contains\((\w+), "(\w+)"\)$/,
					template: 'contains({name}, "{parameter}")'
				},
				{
					label: Liferay.Language.get('does-not-contain'),
					name: 'notContains',
					parameterMessage: Liferay.Language.get('this-text'),
					regex: /^NOT\(contains\((\w+), "(\w+)"\)\)$/,
					template: 'NOT(contains({name}, "{parameter}"))'
				},
				{
					label: Liferay.Language.get('url'),
					name: 'url',
					parameterMessage: '',
					regex: /^isURL\((\w+)\)$/,
					template: 'isURL({name})'
				},
				{
					label: Liferay.Language.get('email'),
					name: 'email',
					parameterMessage: '',
					regex: /^isEmailAddress\((\w+)\)$/,
					template: 'isEmailAddress({name})'
				}
			]
		};

		var Util = {
			generateInstanceId: function(length) {
				var instance = this;

				var text = '';

				var possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

				for (var i = 0; i < length; i++) {
					text += possible.charAt(Math.floor(Math.random() * possible.length));
				}

				return text;
			},

			getDataTypeFromValidation: function(dataType, validation) {
				var instance = this;

				var expression = validation.expression;

				var validationTypes = instance.getValidations();

				for (var type in validationTypes) {
					var validations = validationTypes[type];

					for (var i = 0; i < validations.length; i++) {
						var regex = validations[i].regex;

						if (regex.test(expression)) {
							dataType = type;

							break;
						}
					}
				}

				dataType = MAP_DATA_TYPES[dataType] || dataType;

				return dataType;
			},

			getFieldByKey: function(haystack, needle, searchKey) {
				var instance = this;

				return instance.searchFieldsByKey(haystack, needle, searchKey)[0];
			},

			getFieldClass: function(type) {
				var instance = this;

				var fieldType = FieldTypes.get(type);

				var fieldClassName = fieldType.get('className');

				return A.Object.getValue(window, fieldClassName.split('.'));
			},

			getFieldNameFromQualifiedName: function(qualifiedName) {
				var instance = this;

				var name = qualifiedName.split('$$')[1];

				return name.split('$')[0];
			},

			getInstanceIdFromQualifiedName: function(qualifiedName) {
				var instance = this;

				var name = qualifiedName.split('$$')[1];

				return name.split('$')[1];
			},

			getValidations: function() {
				return VALIDATIONS;
			},

			searchFieldsByKey: function(haystack, needle, searchKey) {
				var queue = new A.Queue(haystack);

				var results = [];

				var addToQueue = function(item) {
					queue.add(item);
				};

				searchKey = searchKey || 'name';

				while (queue.size() > 0) {
					var next = queue.next();

					if (next[searchKey] === needle) {
						results.push(next);
					}
					else {
						var children = next.fields || next.nestedFields || next.fieldValues || next.nestedFieldValues;

						if (children) {
							children.forEach(addToQueue);
						}
					}
				}

				return results;
			}
		};

		Liferay.namespace('DDM.Renderer').Util = Util;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-types', 'queue']
	}
);