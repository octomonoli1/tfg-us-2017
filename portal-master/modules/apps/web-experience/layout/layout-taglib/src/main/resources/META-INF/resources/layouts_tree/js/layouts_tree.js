AUI.add(
	'liferay-layouts-tree',
	function(A) {
		var Lang = A.Lang;

		var LString = Lang.String;

		var NODE_ID_TPL = '{treeId}_layout_{layoutId}_plid_{plid}_groupId_{groupId}';

		var NODE_ID_SELECTOR_TPL = '[id^="{treeId}_{layoutId}"]';

		var NODE_LINK_TPL = '<a class="{cssClass}" data-url="{url}" data-uuid="{uuid}" href="{layoutURL}" id="{id}" title="{title}">{label}</a>';

		var STR_BOUNDING_BOX = 'boundingBox';

		var STR_EMPTY = '';

		var STR_ID = 'id';

		var STR_LINK_TEMPLATE = 'linkTemplate';

		var STR_PARENT_NODE = 'parentNode';

		var TREE_CSS_CLASSES = {
			iconCheck: 'tree-icon icon-check',
			iconCollapsed: 'icon-file',
			iconExpanded: 'icon-file',
			iconHitAreaCollapsed: 'tree-hitarea icon-plus',
			iconHitAreaExpanded: 'tree-hitarea icon-minus',
			iconLoading: 'icon-refresh',
			iconUncheck: 'icon-check'
		};

		var TREE_LOADING_EL_TPL = '<div class="lfr-tree-loading">' +
				'<span class="icon icon-loading lfr-tree-loading-icon"></span>' +
			'</div>';

		var LayoutsTreeBase = function() {
		};

		LayoutsTreeBase.ATTRS = {
			incomplete: {
				validator: Lang.isBoolean,
				value: true
			},

			io: {
				getter: '_getNodeIOConfig'
			},

			layouts: {
				validator: Lang.isObject
			},

			linkTemplate: {
				validator: Lang.isString,
				value: NODE_LINK_TPL
			},

			maxChildren: {
				validator: Lang.isNumber,
				value: 20
			},

			root: {
				setter: '_setRootConfig',
				validator: Lang.isObject
			},

			selPlid: {
				validator: Lang.isString
			},

			type: {
				validator: Lang.isString,
				value: 'pages'
			},

			urls: {
				validator: Lang.isArray
			}
		};

		LayoutsTreeBase.prototype = {
			initializer: function() {
				var instance = this;

				var boundingBox = instance.get(STR_BOUNDING_BOX);

				instance._treeLoadingElement = boundingBox.ancestor().insertBefore(
					A.Node.create(TREE_LOADING_EL_TPL),
					boundingBox
				);

				instance._treeId = instance.get(STR_BOUNDING_BOX).attr('data-treeid');

				instance._bindUILTBase();
			},

			destructor: function() {
				var instance = this;

				(new A.EventHandle(instance._eventHandles)).detach();
			},

			renderUI: function() {
				var instance = this;

				instance._parseLayouts(instance.get('layouts'));

				instance.constructor.superclass.renderUI.apply(this, arguments);
			},

			extractGroupId: function(node) {
				var match = node.get(STR_ID).match(/groupId_(\d+)/);

				return match && match[1];
			},

			extractLayoutId: function(node) {
				var match = node.get(STR_ID).match(/layout_(\d+)/);

				return match && match[1];
			},

			extractPlid: function(node) {
				var match = node.get(STR_ID).match(/plid_(\d+)/);

				return match && match[1];
			},

			restoreSelectedNode: function() {
				var instance = this;

				var pendingSelectedNodeId = instance._pendingSelectedNodeId;

				if (pendingSelectedNodeId) {
					instance.getNodeById(pendingSelectedNodeId).select();

					instance._pendingSelectedNodeId = null;
				}
			},

			_afterRenderTree: function(event) {
				var instance = this;

				instance._treeLoadingElement.hide();

				instance.restoreSelectedNode();
			},

			_bindUILTBase: function() {
				var instance = this;

				instance._eventHandles = instance._eventHandles || [];

				instance._eventHandles.push(
					Liferay.on('navigation', instance._onNavigation, instance),
					instance.after('render', instance._afterRenderTree, instance),
					instance.on('dropAppend', instance._onDropAppend, instance),
					instance.on('dropInsert', instance._onDropInsert, instance)
				);
			},

			_createNodeId: function(groupId, layoutId, plid) {
				var instance = this;

				return A.Lang.sub(
					NODE_ID_TPL,
					{
						groupId: groupId,
						layoutId: layoutId,
						plid: plid,
						treeId: instance._treeId
					}
				);
			},

			_createNodeLink: function(data, template) {
				var instance = this;

				var className = 'layout-tree ';

				data.cssClass = data.cssClass ? className + data.cssClass : className;

				var urls = instance.get('urls');

				urls.forEach(
					function(item, index) {
						data[item.name] = A.Lang.sub(
							item.value,
							{
								selPlid: data.plid
							}
						);
					}
				);

				data.id = data.url ? LString.escapeHTML(instance._treeId + '_layout_' + data.url.substring(1)) : STR_EMPTY;

				data.title = data.title ? data.title : STR_EMPTY;

				data.url = data.url ? LString.escapeHTML(data.url) : STR_EMPTY;

				data.uuid = data.uuid ? LString.escapeHTML(data.uuid) : STR_EMPTY;

				return A.Lang.sub(template, data);
			},

			_displayNotice: function(message, type, timeout, useAnimation) {
				new Liferay.Notice(
					{
						closeText: false,
						content: message + '<button class="close" type="button">&times;</button>',
						noticeClass: 'hide',
						timeout: timeout || 10000,
						toggleText: false,
						type: type || 'warning',
						useAnimation: Lang.isValue(useAnimation) ? useAnimation : true
					}
				).show();
			},

			_formatJSONResults: function(json) {
				var instance = this;

				var output = json.layouts.map(
					function(node) {
						return instance._formatNode(node);
					}
				);

				return output;
			},

			_formatNode: function(node) {
				var instance = this;

				var childLayouts = [];
				var cssIcons = {};
				var total = 0;

				var iconCssClassName = 'icon-link';

				var hasChildren = node.hasChildren;
				var nodeChildren = node.children;
				var nodeType = node.type;

				if (nodeType === 'embedded' || nodeType === 'link_to_layout' || nodeType === 'url') {
					cssIcons = {
						iconCollapsed: iconCssClassName,
						iconExpanded: iconCssClassName
					};
				}

				if (nodeChildren) {
					childLayouts = nodeChildren.layouts;
					total = nodeChildren.total;
				}

				var expanded = childLayouts.length > 0;

				var maxChildren = instance.get('maxChildren');

				var id = instance._createNodeId(node.groupId, node.layoutId, node.plid);

				var newNode = {
					alwaysShowHitArea: hasChildren,
					cssClasses: {
						pages: A.merge(TREE_CSS_CLASSES, cssIcons)
					},
					draggable: node.sortable,
					expanded: expanded,
					id: id,
					io: instance._getNodeIOConfig(),
					leaf: false,
					paginator: {
						limit: maxChildren,
						offsetParam: 'start',
						start: Math.max(childLayouts.length - maxChildren, 0),
						total: total
					},
					type: total > 0 ? 'io' : 'node'
				};

				if (nodeChildren && expanded) {
					newNode.children = instance._formatJSONResults(nodeChildren);
				}

				if (instance.get('selPlid') == node.plid) {
					instance._pendingSelectedNodeId = id;
				}

				var cssClass = STR_EMPTY;
				var title = STR_EMPTY;

				var name = LString.escapeHTML(node.name);

				if (node.layoutRevisionId) {
					if (!node.layoutRevisionHead) {
						title = Liferay.Language.get('there-is-not-a-version-of-this-page-marked-as-ready-for-publication');
					}
					else if (node.layoutBranchName) {
						node.layoutBranchName = LString.escapeHTML(node.layoutBranchName);

						name += Lang.sub(' [{layoutBranchName}]', node);

						title = Liferay.Language.get('this-is-the-page-variation-that-is-marked-as-ready-for-publication');
					}

					if (node.incomplete) {
						cssClass = 'incomplete-layout';

						title = Liferay.Language.get('this-page-is-not-enabled-in-this-site-pages-variation,-but-is-available-in-other-variations');
					}
				}

				if (!node.sortable) {
					cssClass = 'lfr-page-locked';
				}

				newNode.label = instance._formatNodeLabel(node, cssClass, name, title);

				return newNode;
			},

			_formatNodeLabel: function(node, cssClass, name, title) {
				var instance = this;

				var data = A.merge(
					{
						cssClass: cssClass,
						label: name,
						plid: node.plid,
						title: title,
						url: node.friendlyURL,
						uuid: node.uuid
					},
					node
				);

				var label = instance._createNodeLink(data, instance.get(STR_LINK_TEMPLATE));

				return label;
			},

			_formatRootNode: function(rootConfig, children) {
				var instance = this;

				var rootLabel = instance._createNodeLink(
					A.merge(
						{
							label: LString.escapeHTML(rootConfig.label),
							plid: rootConfig.defaultParentLayoutId
						},
						rootConfig
					),
					rootConfig.linkTemplate
				);

				var maxChildren = instance.get('maxChildren');

				var layouts = instance.get('layouts');

				var rootNode = {
					alwaysShowHitArea: true,
					children: children,
					cssClasses: {
						pages: TREE_CSS_CLASSES
					},
					draggable: false,
					expanded: rootConfig.expand,
					id: instance._createNodeId(rootConfig.groupId, rootConfig.defaultParentLayoutId, 0),
					label: rootLabel,
					leaf: false,
					paginator: {
						limit: maxChildren,
						offsetParam: 'start',
						start: Math.max(layouts.layouts.length - maxChildren, 0),
						total: layouts.total
					},
					type: 'io'
				};

				return rootNode;
			},

			_getNodeIOConfig: function() {
				var instance = this;

				var ioCfg = {
					cfg: {
						data: function(node) {
							return {
								cmd: 'get',
								doAsGroupId: themeDisplay.getScopeGroupId(),
								groupId: instance.extractGroupId(node),
								incomplete: instance.get('incomplete'),
								p_auth: Liferay.authToken,
								p_l_id: themeDisplay.getPlid(),
								p_p_id: '88',
								parentLayoutId: instance.extractLayoutId(node),
								privateLayout: instance.get('root').privateLayout,
								selPlid: instance.get('selPlid'),
								treeId: instance._treeId
							};
						},
						method: A.config.io.method,
						on: {
							success: function(event, id, xhr) {
								var response;

								try {
									response = JSON.parse(xhr.responseText);

									this.get('paginator').total = response.total;

									this.syncUI();
								}
								catch (e) {
								}

								this.fire('ioSuccess');
							}
						}
					},
					formatter: A.bind(instance._formatJSONResults, instance),
					url: themeDisplay.getPathMain() + '/portal/get_layouts'
				};

				return ioCfg;
			},

			_onDropAppend: function(event) {
				var instance = this;

				var tree = event.tree;

				var index = tree.dragNode.get(STR_PARENT_NODE).getChildrenLength() - 1;

				instance._updateLayoutParent(
					instance.extractPlid(tree.dragNode),
					instance.extractPlid(tree.dropNode),
					index
				);
			},

			_onDropInsert: function(event) {
				var instance = this;

				var tree = event.tree;

				var index = tree.dragNode.get(STR_PARENT_NODE).indexOf(tree.dragNode);

				instance._updateLayoutParent(
					instance.extractPlid(tree.dragNode),
					instance.extractPlid(tree.dropNode.get(STR_PARENT_NODE)),
					index
				);
			},

			_onNavigation: function(event) {
				var instance = this;

				if (event.type === 'delete') {
					var deletedLayoutSelector = Lang.sub(
						NODE_ID_SELECTOR_TPL,
						{
							treeId: instance._treeId,
							layoutId: event.item.getAttribute('id')
						}
					);

					var deletedLayoutDOMNode = A.one(deletedLayoutSelector);

					var deletedLayout = deletedLayoutDOMNode.getData('tree-node');

					instance.removeChild(deletedLayout);
				}
			},

			_parseLayouts: function(value) {
				var instance = this;

				var children = instance._formatJSONResults(value);

				var rootConfig = instance.get('root');

				if (rootConfig) {
					children = [instance._formatRootNode(rootConfig, children)];
				}

				instance.set(
					'children',
					children,
					{
						src: A.Widget.UI_SRC
					}
				);

				instance.getChildren()[0].get('contentBox').addClass('lfr-root-node');

				return value;
			},

			_restoreNodePosition: function(response) {
				var instance = this;

				instance._displayNotice(response.message, 'warning', 10000, true);

				var nodeId = A.Lang.sub(
					NODE_ID_TPL,
					{
						groupId: response.groupId,
						layoutId: response.layoutId,
						plid: response.plid,
						treeId: instance._treeId
					}
				);

				var parentNodeId = A.Lang.sub(
					NODE_ID_TPL,
					{
						groupId: response.groupId,
						layoutId: response.originalParentLayoutId,
						plid: response.originalParentPlid,
						treeId: instance._treeId
					}
				);

				var action = 'append';

				var index = response.originalPriority;

				var node = instance.getNodeById(nodeId);
				var parentNode = instance.getNodeById(parentNodeId);

				var sibling;

				if (index > 0) {
					if (index === parentNode.childrenLength) {
						action = 'append';
					}
					else {
						var siblingIndex = index;

						if (node.get('parentNode').get('id') !== parentNodeId) {
							siblingIndex -= 1;
						}

						sibling = parentNode.item(siblingIndex);

						action = 'after';
					}
				}

				if (sibling) {
					instance.insert(node, sibling, action);
				}
				else {
					parentNode.appendChild(node);
				}
			},

			_setRootConfig: function(val) {
				var defaultRootConfig = {
					linkTemplate: NODE_LINK_TPL
				};

				return A.merge(defaultRootConfig, val);
			},

			_updateLayout: function(data) {
				var instance = this;

				A.io.request(
					themeDisplay.getPathMain() + '/portal/edit_layout',
					{
						data: A.mix(
							data,
							{
								doAsGroupId: themeDisplay.getScopeGroupId(),
								p_auth: Liferay.authToken,
								p_l_id: themeDisplay.getPlid(),
								p_p_id: '88'
							}
						),
						dataType: 'JSON',
						on: {
							success: function(event, id, xhr) {
								var response;

								try {
									response = JSON.parse(xhr.responseText);

									if (response.status === Liferay.STATUS_CODE.BAD_REQUEST) {
										instance._restoreNodePosition(response);
									}
								}
								catch (e) {
								}
							}
						}
					}
				);
			},

			_updateLayoutParent: function(dragPlid, dropPlid, index) {
				var instance = this;

				instance._updateLayout(
					{
						cmd: 'parent_layout_id',
						parentPlid: dropPlid,
						plid: dragPlid,
						priority: index
					}
				);
			}
		};

		Liferay.LayoutsTree = A.Component.create(
			{
				AUGMENTS: LayoutsTreeBase,
				EXTENDS: A.TreeView,
				NAME: 'liferaylayoutstree'
			}
		);

		Liferay.LayoutsTreeDD = A.Component.create(
			{
				AUGMENTS: LayoutsTreeBase,
				EXTENDS: A.TreeViewDD,
				NAME: 'liferaylayoutstreedd'
			}
		);
	},
	'',
	{
		requires: ['aui-tree-view']
	}
);