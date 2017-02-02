YUI.add(
	'liferay-qa-poshi-logger',
	function(A) {
		var Lang = A.Lang;

		var ATTR_DATA_BUTTON_LINK_ID = 'btnLinkId';

		var ATTR_DATA_ERROR_LINK_ID = 'errorLinkId';

		var ATTR_DATA_FUNCTION_LINK_ID = 'functionLinkId';

		var ATTR_DATA_LOG_ID = 'logId';

		var CSS_COLLAPSE = 'collapse';

		var CSS_CURRENT_SCOPE = 'current-scope';

		var CSS_FAIL = 'fail';

		var CSS_FAILED = 'failed';

		var CSS_HIDDEN = 'hidden';

		var CSS_PASS = 'pass';

		var CSS_PENDING = 'pending';

		var CSS_RUNNING = 'running';

		var CSS_TOGGLE = 'toggle';

		var CSS_TRANSITIONING = 'transitioning';

		var CSS_WARNING = 'warning';

		var STR_BLANK = '';

		var STR_COMMAND_LOG_ID = 'commandLogId';

		var STR_COMMAND_LOG_SCOPE = 'commandLogScope';

		var STR_CONTENT_BOX = 'contentBox';

		var STR_CURRENT_SCOPE = 'currentScope';

		var STR_DOT = '.';

		var STR_ERRORS = 'errors';

		var STR_HEIGHT = 'height';

		var STR_PAUSED = 'paused';

		var STR_RUNNING = 'running';

		var STR_SIDEBAR = 'sidebar';

		var STR_SRC = 'src';

		var STR_STATUS = 'status';

		var STR_TRANSITIONING = 'transitioning';

		var STR_XML_LOG = 'xmlLog';

		var SELECTOR_FAIL = STR_DOT + CSS_FAIL;

		var SELECTOR_FAILED = STR_DOT + CSS_FAILED;

		var SELECTOR_WARNING = STR_DOT + CSS_WARNING;

		var TPL_ERROR_BUTTONS = '<button class="btn {cssClass}" data-errorlinkid="{linkId}" onclick="loggerInterface.handleErrorBtns">' +
				'<div class="btn-content"></div>' +
			'</button>';

		var TPL_PARAMETER = '<li class="{cssClass}">{parameter}</li>';

		var WIN = A.getWin();

		var PoshiLogger = A.Component.create(
			{
				ATTRS: {
					commandLogId: {
						value: null
					},

					commandLogScope: {
						value: new A.NodeList()
					},

					currentScope: {
						setter: A.one
					},

					errors: {
						setter: function() {
							var instance = this;

							var xmlLog = instance.get(STR_XML_LOG);

							return xmlLog.all(SELECTOR_FAIL + ', ' + SELECTOR_WARNING);
						}
					},

					paused: {
						value: false
					},

					running: {
						value: false
					},

					sidebar: {
						setter: A.one
					},

					status: {
						validator: Lang.isArray,
						value: [CSS_FAIL, CSS_PASS, CSS_PENDING, CSS_WARNING]
					},

					transitioning: {
						value: null
					},

					xmlLog: {
						setter: A.one
					}
				},

				NAME: 'poshilogger',

				prototype: {
					initializer: function() {
						var instance = this;

						var sidebar = instance.get(STR_SIDEBAR);

						var commandLog = sidebar.one('.command-log');

						instance._toggleCommandLog(commandLog);
					},

					bindUI: function() {
						var instance = this;

						instance._bindSidebar();
						instance._bindXMLLog();
					},

					handleCommandCompleted: function(id) {
						var instance = this;

						var logIdSelector = '.command-log[data-logId="' + instance.get(STR_COMMAND_LOG_ID) + '"]';

						var commandLog = instance.get(STR_SIDEBAR).one(logIdSelector);

						var latestCommand = commandLog.one('.line-group:last-child');

						if (latestCommand) {
							var linkedFunction = instance.get(STR_XML_LOG).one('#' + id);

							instance._displayNode(linkedFunction);
							instance._setXmlNodeClass(linkedFunction);

							if (latestCommand.hasClass(CSS_FAILED) || latestCommand.hasClass(CSS_WARNING)) {
								instance._injectXmlError(latestCommand);
							}
						}
					},

					handleCurrentCommandSelect: function(event) {
						var instance = this;

						var currentTargetAncestor = event.currentTarget.ancestor();

						if (currentTargetAncestor) {
							if (!currentTargetAncestor.hasClass('current-scope')) {
								event.halt(true);
							}

							instance._parseCommandLog(currentTargetAncestor);

							var functionLinkId = currentTargetAncestor.getData(ATTR_DATA_FUNCTION_LINK_ID);

							var xmlLog = instance.get(STR_XML_LOG);

							var linkedFunction = xmlLog.one('.line-group[data-functionLinkId="' + functionLinkId + '"]');

							instance._displayNode(linkedFunction, true);
							instance._selectCurrentScope(linkedFunction);
						}
					},

					handleCurrentScopeSelect: function(event) {
						var instance = this;

						var currentTargetAncestor = event.currentTarget.ancestor();

						if (currentTargetAncestor) {
							if (!currentTargetAncestor.hasClass('current-scope')) {
								event.halt(true);
							}

							instance._displayNode(currentTargetAncestor, true);
							instance._selectCurrentScope(currentTargetAncestor);
						}
					},

					handleErrorBtns: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						currentTarget.toggleClass(CSS_TOGGLE);

						var xmlLog = instance.get(STR_XML_LOG);

						var errorLinkId = currentTarget.getData(ATTR_DATA_ERROR_LINK_ID);

						var errorPanel = xmlLog.one('.errorPanel[data-errorLinkId="' + errorLinkId + '"]');

						if (errorPanel) {
							errorPanel.toggleClass(CSS_TOGGLE);
						}
					},

					handleFullScreenImageClick: function(event) {
						var instance = this;

						var fullScreenImageContainer = A.one('#fullScreenImage');

						if (fullScreenImageContainer) {
							var src;

							var fullScreenImage = fullScreenImageContainer.one('#image');

							if (fullScreenImage) {
								var currentTarget = event.currentTarget;

								src = currentTarget.attr(STR_SRC);

								if (src) {
									fullScreenImage.attr(STR_SRC, src);
								}

								fullScreenImage.toggleClass('hide', !src);
							}

							fullScreenImageContainer.toggleClass(CSS_TOGGLE, !src);
						}
					},

					handleGoToErrorBtn: function(event) {
						var instance = this;

						var currentScope = instance.get(STR_CURRENT_SCOPE);
						var errorNodes = instance.get(STR_ERRORS);

						var lastIndex = errorNodes.size() - 1;

						var newIndex = lastIndex;

						if (currentScope) {
							var index = errorNodes.indexOf(currentScope);

							if (index > -1) {
								if (index < lastIndex) {
									newIndex = index + 1;
								}
								else {
									newIndex = 0;
								}
							}
						}

						var failure = errorNodes.item(newIndex);

						instance._selectCurrentScope(failure);
						instance._displayNode(failure, true);
					},

					handleLineTrigger: function(id, starting) {
						var instance = this;

						var linkedLine = instance.get(STR_XML_LOG).one('#' + id);

						instance._setXmlNodeClass(linkedLine);

						var container = linkedLine.one('> .child-container');

						if (container) {
							if (starting && container.hasClass(CSS_COLLAPSE)) {
								instance._toggleContainer(container, false);
							}
							else if (linkedLine.hasClass('conditional-fail')) {
								instance._toggleContainer(container, false);
							}
						}
					},

					handleMinimizeSidebarBtn: function(event) {
						var instance = this;

						instance._minimizeSidebar(event.currentTarget);
					},

					handlePauseBtn: function(event) {
						var instance = this;

						instance._togglePauseTest();
					},

					handleToggleCollapseBtn: function(event, inSidebar) {
						var instance = this;

						var currentTarget = event.currentTarget;

						if (!currentTarget.hasClass('btn')) {
							currentTarget = currentTarget.previous();

							if (!inSidebar) {
								currentTarget = currentTarget.one('.btn-collapse');
							}
						}

						if (currentTarget) {
							var lookUpScope = instance.get(STR_XML_LOG);

							if (inSidebar) {
								lookUpScope = instance.get(STR_SIDEBAR);
							}

							var linkId = currentTarget.getData(ATTR_DATA_BUTTON_LINK_ID);

							var collapsibleNode = lookUpScope.one('.child-container[data-btnLinkId="' + linkId + '"]');

							instance._toggleContainer(collapsibleNode, inSidebar);
						}
					},

					handleToggleCommandLogBtn: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var logId = currentTarget.getData(ATTR_DATA_LOG_ID);

						var commandLog = instance._getCommandLogNode(logId);

						instance._toggleCommandLog(commandLog, currentTarget);
					},

					_bindSidebar: function() {
						var instance = this;

						var sidebar = instance.get(STR_SIDEBAR);

						sidebar.delegate(
							'click',
							A.bind('handleCurrentCommandSelect', instance),
							'.linkable .line-container'
						);

						sidebar.delegate(
							'click',
							A.rbind('handleToggleCollapseBtn', instance, true),
							'.expand-toggle, .linkable.current-scope .line-container'
						);

						var logBtn = sidebar.all('.btn-command-log');

						logBtn.on(
							'click',
							A.bind('handleToggleCommandLogBtn', instance)
						);

						var sidebarBtn = sidebar.one('.btn-sidebar');

						if (sidebarBtn) {
							sidebarBtn.on(
								'click',
								A.bind('handleMinimizeSidebarBtn', instance)
							);
						}

						var jumpToError = sidebar.one('.btn-jump-to-error');

						if (jumpToError) {
							jumpToError.on(
								'click',
								A.bind('handleGoToErrorBtn', instance)
							);
						}

						var pause = sidebar.one('.btn-pause');

						if (pause) {
							pause.on(
								'click',
								A.bind('handlePauseBtn', instance)
							);
						}
					},

					_bindXMLLog: function() {
						var instance = this;

						var xmlLog = instance.get(STR_XML_LOG);

						xmlLog.delegate(
							'click',
							A.bind('handleCurrentScopeSelect', instance),
							'.conditional-function > .line-container, .function > .line-container, .macro > .line-container, .test-group > .line-container'
						);

						xmlLog.delegate(
							'click',
							A.bind('handleFullScreenImageClick', instance),
							'.fullscreen-image, .screenshot-container img'
						);

						xmlLog.delegate(
							'click',
							A.rbind('handleToggleCollapseBtn', instance, false),
							'.btn-collapse, .btn-var, .current-scope > .line-container'
						);

						xmlLog.delegate(
							'click',
							A.bind('handleErrorBtns', instance),
							'.btn-error, .btn-screenshot'
						);
					},

					_clearXmlErrors: function(command) {
						command.all('.errorPanel').remove();

						var btnContainer = command.one('.btn-container');

						btnContainer.all('[data-' + ATTR_DATA_ERROR_LINK_ID + ']').remove();
					},

					_collapseTransition: function(targetNode) {
						var instance = this;

						var returnVal = false;

						var transitioning = instance.get(STR_TRANSITIONING);

						if (targetNode && (!transitioning || !transitioning.contains(targetNode))) {
							var height;

							var collapsing = targetNode.getStyle(STR_HEIGHT) != '0px';

							if (collapsing) {
								height = targetNode.outerHeight();

								targetNode.height(height);

								instance.set(STR_TRANSITIONING, targetNode);

								targetNode.addClass(CSS_TRANSITIONING);
							}
							else {
								var lastChild = targetNode.getDOMNode().lastElementChild;

								lastChild = A.one(lastChild);

								targetNode.removeClass(CSS_COLLAPSE);
								targetNode.addClass(CSS_TRANSITIONING);

								var lastChildHeight = lastChild.innerHeight();
								var lastChildY = lastChild.getY();

								var lastChildBottomY = lastChildY + lastChildHeight + 1;

								height = lastChildBottomY - targetNode.getY();
							}

							instance._getTransition(targetNode, height, collapsing);

							returnVal = true;
						}

						return returnVal;
					},

					_displayNode: function(node, scrollTo) {
						var instance = this;

						node = node || instance.get(STR_ERRORS).last();

						if (node) {
							var parentContainers = node.ancestors('.child-container');

							if (parentContainers) {
								instance._expandParentContainers(parentContainers, node, scrollTo);
							}
						}
					},

					_expandParentContainers: function(parentContainers, node, scrollTo) {
						var instance = this;

						var timeout = 0;

						var container = parentContainers.shift();

						if (container.hasClass(CSS_COLLAPSE)) {
							instance._toggleContainer(container, false);

							timeout = 10;
						}

						A.later(
							timeout,
							instance,
							function() {
								if (parentContainers.size()) {
									instance._expandParentContainers(parentContainers, node, scrollTo);
								}
								else if (scrollTo) {
									instance._scrollToNode(node);
								}
							}
						);
					},

					_getCommandLogNode: function(logId) {
						var instance = this;

						logId = logId || instance.get(STR_COMMAND_LOG_ID);

						var logIdSelector = '.command-log[data-logId="' + logId + '"]';

						return instance.get(STR_SIDEBAR).one(logIdSelector);
					},

					_getTransition: function(targetNode, height, collapsing) {
						var instance = this;

						var duration = Math.pow(height, 0.15) / 15;

						var ease = 'ease-in';

						var margin = 0;

						if (collapsing) {
							ease = 'ease-out';

							height = 0;
						}

						else {
							targetNode.addClass('in');

							margin = targetNode.getStyle('marginTop');

							targetNode.removeClass('in');
						}

						targetNode.transition(
							{
								height: {
									duration: duration,
									easing: ease,
									value: height
								},

								marginTop: {
									duration: 0.05,
									easing: ease,
									value: margin
								},

								marginBottom: {
									duration: 0.05,
									easing: ease,
									value: margin
								}
							},
							function() {
								if (collapsing) {
									targetNode.addClass(CSS_COLLAPSE);

									targetNode.removeAttribute('style');
								}
								else {
									targetNode.height('auto');
								}

								instance.set(STR_TRANSITIONING, null);

								targetNode.removeClass(CSS_TRANSITIONING);
							}
						);
					},

					_injectXmlError: function(command) {
						var instance = this;

						var consoleLog = command.one('.console');
						var screenshots = command.one('.screenshots');

						if (screenshots) {
							screenshots.attr('class', 'screenshots-log');

							consoleLog.append(screenshots);
						}

						var functionLinkId = command.getData(ATTR_DATA_FUNCTION_LINK_ID);

						var functionLinkIdSelector = '.line-group[data-functionLinkId="' + functionLinkId + '"]';

						var failedFunction = instance.get(STR_XML_LOG).one(functionLinkIdSelector);

						if (consoleLog && failedFunction) {
							var buffer = [];

							var consoleBtn = A.Lang.sub(
								TPL_ERROR_BUTTONS,
								{
									cssClass: 'btn-error',
									linkId: consoleLog.getData(ATTR_DATA_ERROR_LINK_ID)
								}
							);

							buffer.push(consoleBtn);

							buffer = buffer.join(STR_BLANK);

							var btnContainer = failedFunction.one('.btn-container');

							btnContainer.append(buffer);

							failedFunction.append(consoleLog.clone());
						}
					},

					_minimizeSidebar: function(button) {
						var instance = this;

						instance.get(STR_CONTENT_BOX).toggleClass('minimized-sidebar');

						button = button || instance.get(STR_SIDEBAR).one('.btn-sidebar');

						button.toggleClass(CSS_TOGGLE);
					},

					_parseCommandLog: function(node) {
						var instance = this;

						var commandLogScope = instance.get(STR_COMMAND_LOG_SCOPE);

						if (commandLogScope) {
							commandLogScope.removeClass(CSS_CURRENT_SCOPE);
						}

						instance.set(
							STR_COMMAND_LOG_SCOPE,
							new A.NodeList()
						);

						if (node.hasClass('macro')) {
							var macroScope = node.all('[data-functionLinkId]');

							macroScope.each(instance._scopeCommandLog, instance);
						}
						else {
							instance._scopeCommandLog(node);
						}

						var commandLogScopeFirst = instance.get(STR_COMMAND_LOG_SCOPE).first();

						instance._scrollToNode(commandLogScopeFirst, true);
					},

					_refreshEditMenu: function() {
						var instance = this;

						var currentScope = instance.get(STR_CURRENT_SCOPE);

						if (currentScope) {
							var sidebar = instance.get(STR_SIDEBAR);

							var scopeNames = currentScope.all('> .line-container .name');
							var scopeTypes = currentScope.all('> .line-container .tag-type');

							var scopeName = scopeNames.first();

							scopeName = scopeName.html();

							var scopeType = scopeTypes.first();

							scopeType = scopeType.html();

							sidebar.one('.scope-type .scope-name').html(scopeName);
							sidebar.one('.scope-type .title').html(scopeType);

							var sidebarParameterList = sidebar.one('.parameter .parameter-list');

							sidebarParameterList.empty();

							var sidebarParameterTitle = sidebar.one('.parameter .title');

							sidebarParameterTitle.removeClass(CSS_HIDDEN);

							if (scopeType !== 'function' && scopeType !== 'macro') {
								sidebarParameterTitle.addClass(CSS_HIDDEN);
							}
							else {
								var buffer = [];

								if (scopeType === 'macro' || scopeType === 'function') {
									var increment = 2;
									var start = 0;
									var valueIncrement = 1;

									var paramCollection = currentScope.all('> .line-container .child-container .name');

									if (scopeType === 'function') {
										increment = 1;
										start = 1;
										valueIncrement = 0;

										paramCollection = scopeNames;
									}

									var limit = paramCollection.size();

									for (var i = start; i < limit; i += increment) {
										buffer.push(
											A.Lang.sub(
												TPL_PARAMETER,
												{
													cssClass: 'parameter-name',
													parameter: scopeTypes.item(i).html()
												}
											),

											A.Lang.sub(
												TPL_PARAMETER,
												{
													cssClass: 'parameter-value',
													parameter: scopeNames.item(i + valueIncrement).html()
												}
											)
										);
									}
								}

								buffer = buffer.join(STR_BLANK);

								sidebarParameterList.append(buffer);
							}
						}
					},

					_scopeCommandLog: function(node) {
						var instance = this;

						if (node) {
							var functionLinkId = node.getData(ATTR_DATA_FUNCTION_LINK_ID);

							var functionLinkIdSelector = '.linkable[data-functionLinkId="' + functionLinkId + '"]';

							var nodes = instance.get(STR_SIDEBAR).all(functionLinkIdSelector);

							var buffer = nodes.getDOMNodes().reverse();

							var commandLogScope = instance.get(STR_COMMAND_LOG_SCOPE);

							commandLogScope = commandLogScope.concat(buffer);

							instance.set(STR_COMMAND_LOG_SCOPE, commandLogScope);

							commandLogScope.addClass(CSS_CURRENT_SCOPE);
						}
					},

					_scrollToNode: function(node, inSidebar) {
						var instance = this;

						var scrollNode = WIN;

						if (node) {
							var lineContainer = node.one('> .line-container');

							if (lineContainer) {
								var halfNodeHeight = lineContainer.innerHeight() / 2;
								var halfWindowHeight = WIN.height() / 2;

								var offsetHeight = halfWindowHeight - halfNodeHeight;

								var nodeY = lineContainer.getY();

								if (inSidebar) {
									scrollNode = instance._getCommandLogNode();

									var dividerLine = scrollNode.one('.divider-line');

									if (dividerLine) {
										nodeY -= dividerLine.getY();
									}
								}

								var yDistance = nodeY - offsetHeight;

								new A.Anim(
									{
										duration: 0.12,
										easing: 'easeOutStrong',
										node: scrollNode,
										to: {
											scroll: [0, yDistance]
										}
									}
								).run();
							}
						}
					},

					_selectCurrentScope: function(node) {
						var instance = this;

						var currentScope = instance.get(STR_CURRENT_SCOPE);

						if (currentScope) {
							currentScope.removeClass(CSS_CURRENT_SCOPE);
						}

						node.addClass(CSS_CURRENT_SCOPE);

						instance.set(STR_CURRENT_SCOPE, node);

						if (instance.get(STR_COMMAND_LOG_ID)) {
							instance._parseCommandLog(node);
						}

						instance._refreshEditMenu();
					},

					_setXmlNodeClass: function(node) {
						var instance = this;

						var status = instance.get(STR_STATUS);

						var statusLength = status.length;

						for (var i = 0; i < statusLength; i++) {
							node.removeClass(status[i]);
						}

						var selector = 'status' + instance.get(STR_COMMAND_LOG_ID);

						var currentStatus = node.getData(selector);

						node.addClass(currentStatus);
					},

					_toggleCommandLog: function(commandLog, button) {
						var instance = this;

						var commandLogId = instance.get(STR_COMMAND_LOG_ID);
						var sidebar = instance.get(STR_SIDEBAR);

						var logId = commandLog.getData(ATTR_DATA_LOG_ID);

						var logIdSelector = '.btn-command-log[data-logId="' + logId + '"]';

						button = button || sidebar.one(logIdSelector);

						button.toggleClass(CSS_TOGGLE);

						var newLogId;

						if (commandLogId !== logId) {
							if (commandLogId) {
								var currentActiveLog = instance._getCommandLogNode();

								instance._toggleCommandLog(currentActiveLog);
							}

							newLogId = logId;

							var commandErrors = commandLog.all(SELECTOR_FAILED + ', ' + SELECTOR_WARNING);

							commandErrors.each(instance._injectXmlError, instance);
						}
						else {
							newLogId = null;

							var errors = instance.get(STR_XML_LOG).all(SELECTOR_FAIL + ', ' + SELECTOR_WARNING);

							if (errors.size()) {
								errors.each(instance._clearXmlErrors);
							}
						}

						instance.set(STR_COMMAND_LOG_ID, newLogId);

						instance._toggleXmlLogClasses(logId);

						var errorNodes = instance.get(STR_XML_LOG).all(SELECTOR_FAIL + ', ' + SELECTOR_WARNING);

						instance.set(STR_ERRORS, errorNodes);

						instance._transitionCommandLog(commandLog);

						var running = commandLog.hasClass(CSS_RUNNING);

						instance.set(STR_RUNNING, running);

						instance.get(STR_CONTENT_BOX).toggleClass(CSS_RUNNING, running);

						if (errorNodes.size() > 0) {
							errorNodes.each(instance._displayNode, instance);

							var lastFailNode = errorNodes.first();

							instance._selectCurrentScope(lastFailNode);
							instance._scrollToNode(lastFailNode);
						}
					},

					_toggleContainer: function(collapsibleContainer, inSidebar) {
						var instance = this;

						var lookUpScope = instance.get(STR_XML_LOG);

						if (inSidebar) {
							lookUpScope = instance.get(STR_SIDEBAR);
						}

						var linkId = collapsibleContainer.getData(ATTR_DATA_BUTTON_LINK_ID);

						var collapsibleBtn = lookUpScope.one('.btn[data-btnLinkId="' + linkId + '"]');

						var collapsed = instance._collapseTransition(collapsibleContainer);

						if (collapsed && collapsibleBtn) {
							collapsibleBtn.toggleClass(CSS_TOGGLE);
						}
					},

					_togglePauseTest: function() {
						var instance = this;

						var commandLogId = instance.get(STR_COMMAND_LOG_ID);
						var sidebar = instance.get(STR_SIDEBAR);

						var commandLogNode = sidebar.one('.command-log[data-logId="' + commandLogId + '"]');

						if (commandLogNode) {
							commandLogNode.toggleClass('paused');
						}

						var pauseBtn = sidebar.one('.btn-pause');

						if (pauseBtn) {
							pauseBtn.toggleClass(CSS_TOGGLE);
						}

						instance.set(STR_PAUSED, !instance.get(STR_PAUSED));
					},

					_toggleXmlLogClasses: function(logId) {
						var instance = this;

						var status = instance.get(STR_STATUS);

						var selector = 'data-status' + logId;

						var statusLength = status.length;

						for (var i = 0; i < statusLength; i++) {
							var currentStatus = status[i];

							var currentStatusSelector = '[' + selector + '="' + currentStatus + '"]';

							var currentStatusNodes = A.all(currentStatusSelector);

							currentStatusNodes.toggleClass(currentStatus);
						}
					},

					_transitionCommandLog: function(commandLog) {
						var instance = this;

						commandLog.toggleClass(CSS_COLLAPSE);

						instance.get(STR_CONTENT_BOX).toggleClass('command-logger');

						var lastChildLog = commandLog.one('.line-group:last-child');

						if (lastChildLog && lastChildLog.hasClass(CSS_COLLAPSE)) {
							instance._toggleContainer(lastChildLog, true);
						}

						var commandLogId = instance.get(STR_COMMAND_LOG_ID);
						var commandLogScope = instance.get(STR_COMMAND_LOG_SCOPE);

						if (commandLogId && commandLogScope) {
							instance._scrollToNode(commandLogScope.first(), true);
						}
					}
				}
			}
		);

		A.PoshiLogger = PoshiLogger;
	},
	'',
	{
		requires: ['anim', 'aui-base', 'aui-component', 'aui-node', 'event', 'resize', 'transition', 'widget']
	}
);