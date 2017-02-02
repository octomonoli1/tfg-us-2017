AUI.add(
	'liferay-product-navigation-control-menu',
	function(A) {
		var ControlMenu = {
			init: function(containerId) {
				var instance = this;

				var controlMenu = A.one(containerId);

				if (controlMenu) {
					var namespace = controlMenu.attr('data-namespace');

					instance._namespace = namespace;

					Liferay.Util.toggleControls(controlMenu);

					var eventHandle = controlMenu.on(
						['focus', 'mousemove', 'touchstart'],
						function(event) {
							Liferay.fire('initLayout');

							eventHandle.detach();
						}
					);
				}
			}
		};

		Liferay.ControlMenu = ControlMenu;
	},
	'',
	{
		requires: ['aui-node', 'aui-overlay-mask-deprecated', 'event-move', 'event-touch', 'liferay-menu-toggle']
	}
);