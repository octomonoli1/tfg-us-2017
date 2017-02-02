;(function() {
	AUI().applyConfig(
		{
			groups: {
				'field-captcha': {
					base: MODULE_PATH + '/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'liferay-ddm-form-field-captcha': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'captcha_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();