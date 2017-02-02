AUI.add(
	'liferay-calendar-simple-color-picker',
	function(A) {
		var	AArray = A.Array;
		var Lang = A.Lang;

		var STR_BLANK = '';

		var STR_DOT = '.';

		var	getClassName = A.getClassName;

		var	CSS_SIMPLE_COLOR_PICKER_ITEM = getClassName('simple-color-picker', 'item');

		var	CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED = getClassName('simple-color-picker', 'item', 'selected');

		var TPL_SIMPLE_COLOR_PICKER_ITEM = new A.Template(
			'<tpl for="pallete">',
				'<div class="', CSS_SIMPLE_COLOR_PICKER_ITEM, '" style="background-color: {.}', '; border-color:', '{.};', '"></div>',
			'</tpl>'
		);

		var SimpleColorPicker = A.Component.create(
			{
				ATTRS: {
					color: {
						setter: function(val) {
							return val.toUpperCase();
						},
						validator: Lang.isString,
						value: STR_BLANK
					},

					host: {
						value: null
					},

					pallete: {
						setter: function(val) {
							return AArray.invoke(val, 'toUpperCase');
						},
						validator: Lang.isArray,
						value: ['#d96666', '#e67399', '#b373b3', '#8c66d9', '#668cb3', '#668cd9', '#59bfb3', '#65ad89', '#4cb052', '#8cbf40', '#bfbf4d', '#e0c240', '#f2a640', '#e6804d', '#be9494', '#a992a9', '#8997a5', '#94a2be', '#85aaa5', '#a7a77d', '#c4a883', '#c7561e', '#b5515d', '#c244ab']
					}
				},

				NAME: 'simple-color-picker',

				UI_ATTRS: ['color', 'pallete'],

				prototype: {
					renderUI: function() {
						var instance = this;

						instance._renderPallete();
					},

					bindUI: function() {
						var instance = this;

						var contentBox = instance.get('contentBox');

						contentBox.delegate('click', instance._onClickColor, STR_DOT + CSS_SIMPLE_COLOR_PICKER_ITEM, instance);
					},

					_onClickColor: function(event) {
						var instance = this;

						var pallete = instance.get('pallete');

						instance.set('color', pallete[instance.items.indexOf(event.currentTarget)]);
					},

					_renderPallete: function() {
						var instance = this;

						instance.items = A.NodeList.create(
							TPL_SIMPLE_COLOR_PICKER_ITEM.parse(
								{
									pallete: instance.get('pallete')
								}
							)
						);

						instance.get('contentBox').setContent(instance.items);
					},

					_uiSetColor: function(val) {
						var instance = this;

						var pallete = instance.get('pallete');

						instance.items.removeClass(CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED);

						var newNode = instance.items.item(pallete.indexOf(val));

						if (newNode) {
							newNode.addClass(CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED);
						}
					},

					_uiSetPallete: function(val) {
						var instance = this;

						if (instance.get('rendered')) {
							instance._renderPallete();
						}
					}
				}
			}
		);

		Liferay.SimpleColorPicker = SimpleColorPicker;
	},
	'',
	{
		requires: ['aui-base', 'aui-template-deprecated']
	}
);