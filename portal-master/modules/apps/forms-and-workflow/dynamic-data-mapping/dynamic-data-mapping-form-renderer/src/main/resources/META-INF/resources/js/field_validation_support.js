AUI.add(
	'liferay-ddm-form-renderer-field-validation',
	function(A) {
		var Lang = A.Lang;

		var Renderer = Liferay.DDM.Renderer;

		var Util = Renderer.Util;

		var FieldValidationSupport = function() {
		};

		FieldValidationSupport.ATTRS = {
			enableEvaluations: {
				value: true
			},

			evaluator: {
				valueFn: '_valueEvaluator'
			},

			strings: {
				value: {
					defaultErrorMessage: Liferay.Language.get('unknown-error'),
					requestErrorMessage: Liferay.Language.get('there-was-an-error-when-trying-to-validate-your-form')
				}
			},

			validation: {
				value: {}
			}
		};

		FieldValidationSupport.prototype = {
			initializer: function() {
				var instance = this;

				var evaluator = instance.get('evaluator');

				instance._eventHandlers.push(
					evaluator.after('evaluationEnded', A.bind('_afterValidationEvaluationEnded', instance)),
					instance.after('parentChange', instance._afterParentChange)
				);
			},

			hasErrors: function() {
				var instance = this;

				return !!instance.get('errorMessage');
			},

			hasValidation: function() {
				var instance = this;

				var required = instance.get('required');

				var validation = instance.get('validation');

				var expression = validation.expression;

				return required || (!!expression && expression !== 'TRUE');
			},

			processEvaluation: function(result) {
				var instance = this;

				if (result && Lang.isObject(result)) {
					instance.processValidation(result);
				}
				else {
					var root = instance.getRoot();

					var strings = instance.get('strings');

					root.showAlert(strings.requestErrorMessage);
				}
			},

			processValidation: function(result) {
				var instance = this;

				var instanceId = instance.get('instanceId');

				var fieldData = Util.getFieldByKey(result, instanceId, 'instanceId');

				if (fieldData) {
					instance.hideErrorMessage();

					if (fieldData.visible) {
						var errorMessage = fieldData.errorMessage;

						if (!errorMessage && !fieldData.valid) {
							var strings = instance.get('strings');

							errorMessage = strings.defaultErrorMessage;
						}

						if (errorMessage) {
							instance.set('errorMessage', errorMessage);
						}

						instance.showValidationStatus();
					}
				}
			},

			validate: function(callback) {
				var instance = this;

				if (instance.hasValidation() && !instance.get('readOnly')) {
					var evaluator = instance.get('evaluator');

					instance.showLoadingFeedback();

					evaluator.evaluate(
						function(result) {
							if (callback) {
								var hasErrors = instance.hasErrors();

								if (!result || !Lang.isObject(result)) {
									hasErrors = true;
								}

								callback.call(instance, hasErrors, result);
							}
						}
					);
				}
				else if (callback) {
					callback.call(instance, true);
				}
			},

			_afterParentChange: function(event) {
				var instance = this;

				var evaluator = instance.get('evaluator');

				evaluator.set('form', event.newVal);
			},

			_afterValidationEvaluationEnded: function(event) {
				var instance = this;

				instance.hideFeedback();

				instance.processEvaluation(event.result);
			},

			_valueEvaluator: function() {
				var instance = this;

				return new Renderer.ExpressionsEvaluator(
					{
						enabled: instance.get('enableEvaluations'),
						form: instance.getRoot()
					}
				);
			}
		};

		Liferay.namespace('DDM.Renderer').FieldValidationSupport = FieldValidationSupport;
	},
	'',
	{
		requires: ['liferay-ddm-form-renderer-expressions-evaluator']
	}
);