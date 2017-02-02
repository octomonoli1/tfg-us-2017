AUI.add(
	'liferay-ddl-form-builder-modal-support',
	function(A) {
		var FormBuilderModalSupport = function() {
		};

		FormBuilderModalSupport.ATTRS = {
			centered: {
				valueFn: '_valueCentered'
			},

			dynamicContentHeight: {
				value: false,
				writeOnce: true
			},

			portletNamespace: {
			},

			zIndex: {
				value: Liferay.zIndex.OVERLAY
			}
		};

		FormBuilderModalSupport.prototype = {
			initializer: function() {
				var instance = this;

				instance._eventHandles.push(
					instance.after(instance._bindModalUI, instance, 'bindUI')
				);
			},

			syncHeight: function() {
				var instance = this;

				var bodyNode = instance.getStdModNode(A.WidgetStdMod.BODY);

				bodyNode.setStyle('max-height', A.DOM.winHeight(A.config.doc) - instance._getModalOffset());
			},

			_afterModalRender: function() {
				var instance = this;

				if (instance.get('dynamicContentHeight')) {
					instance._configModalDynamicHeight();

					instance.syncHeight();
				}
			},

			_afterModalVisibleChange: function(event) {
				var instance = this;

				if (event.newVal && instance.get('dynamicContentHeight')) {
					instance.syncHeight();
				}
			},

			_afterWindowResize: function() {
				var instance = this;

				if (instance.get('visible')) {
					if (instance.get('dynamicContentHeight')) {
						instance.syncHeight();
					}

					if (instance.get('centered')) {
						instance.align();
					}
				}
			},

			_bindModalUI: function() {
				var instance = this;

				instance._eventHandles.push(
					instance.after('render', instance._afterModalRender),
					instance.after('visibleChange', instance._afterModalVisibleChange),
					instance.on('xyChange', instance._onModalXYChange)
				);
			},

			_centerYAxis: function(xy) {
				var instance = this;

				var contentBox = instance.get('contentBox');

				xy[1] = (A.config.win.pageYOffset - contentBox.outerHeight(true) / 2) + (A.config.win.innerHeight / 2);

				return xy;
			},

			_configModalDynamicHeight: function() {
				var instance = this;

				instance.get('boundingBox').addClass('dynamic-content-height');
			},

			_getModalOffset: function() {
				var instance = this;

				var bodyNode = instance.getStdModNode(A.WidgetStdMod.BODY);

				var bodyHeight = bodyNode.height();

				var boundingBox = instance.get('boundingBox');

				var outerHeight = boundingBox.outerHeight(true);

				return Math.max(bodyHeight, outerHeight) - Math.min(bodyHeight, outerHeight);
			},

			_onModalXYChange: function(event) {
				var instance = this;

				if (instance.get('centered')) {
					event.newVal = instance._centerYAxis(event.newVal);
				}
			},

			_valueCentered: function() {
				var instance = this;

				var portletNode = A.one('#p_p_id' + instance.get('portletNamespace'));

				instance.set('centered', portletNode);
			}
		};

		Liferay.namespace('DDL').FormBuilderModalSupport = FormBuilderModalSupport;
	},
	'',
	{
		requires: ['aui-modal']
	}
);