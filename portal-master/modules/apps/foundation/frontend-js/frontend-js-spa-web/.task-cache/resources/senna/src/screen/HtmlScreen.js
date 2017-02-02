define("frontend-js-spa-web@1.0.11/senna/src/screen/HtmlScreen", ['exports', 'metal/src/metal', 'metal-dom/src/all/dom', 'metal-promise/src/promise/Promise', '../globals/globals', './RequestScreen', '../surface/Surface', 'metal-useragent/src/UA', 'metal-uri/src/Uri', '../utils/utils'], function (exports, _metal, _dom, _Promise, _globals, _RequestScreen2, _Surface, _UA, _Uri, _utils) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _Promise2 = _interopRequireDefault(_Promise);

	var _globals2 = _interopRequireDefault(_globals);

	var _RequestScreen3 = _interopRequireDefault(_RequestScreen2);

	var _Surface2 = _interopRequireDefault(_Surface);

	var _UA2 = _interopRequireDefault(_UA);

	var _Uri2 = _interopRequireDefault(_Uri);

	var _utils2 = _interopRequireDefault(_utils);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	function _classCallCheck(instance, Constructor) {
		if (!(instance instanceof Constructor)) {
			throw new TypeError("Cannot call a class as a function");
		}
	}

	function _possibleConstructorReturn(self, call) {
		if (!self) {
			throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
		}

		return call && (typeof call === "object" || typeof call === "function") ? call : self;
	}

	function _inherits(subClass, superClass) {
		if (typeof superClass !== "function" && superClass !== null) {
			throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);
		}

		subClass.prototype = Object.create(superClass && superClass.prototype, {
			constructor: {
				value: subClass,
				enumerable: false,
				writable: true,
				configurable: true
			}
		});
		if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
	}

	var HtmlScreen = function (_RequestScreen) {
		_inherits(HtmlScreen, _RequestScreen);

		/**
   * Screen class that perform a request and extracts surface contents from
   * the response content.
   * @constructor
   * @extends {RequestScreen}
   */

		function HtmlScreen() {
			_classCallCheck(this, HtmlScreen);

			var _this = _possibleConstructorReturn(this, _RequestScreen.call(this));

			/**
    * Holds the title selector. Relevant to extract the <code><title></code>
    * element from request fragments to use as the screen title.
    * @type {!string}
    * @default title
    * @protected
    */
			_this.titleSelector = 'title';
			return _this;
		}

		/**
   * @inheritDoc
   */


		HtmlScreen.prototype.activate = function activate() {
			_RequestScreen.prototype.activate.call(this);
			this.releaseVirtualDocument();
			this.pendingStyles = null;
		};

		HtmlScreen.prototype.allocateVirtualDocumentForContent = function allocateVirtualDocumentForContent(htmlString) {
			if (!this.virtualDocument) {
				this.virtualDocument = _globals2.default.document.createElement('html');
			}

			this.copyNodeAttributesFromContent_(htmlString, this.virtualDocument);

			this.virtualDocument.innerHTML = htmlString;
		};

		HtmlScreen.prototype.appendStyleIntoDocument_ = function appendStyleIntoDocument_(newStyle) {
			var isTemporaryStyle = _dom.dom.match(newStyle, HtmlScreen.selectors.stylesTemporary);
			if (isTemporaryStyle) {
				this.pendingStyles.push(newStyle);
				if (_UA2.default.isIe && newStyle.href) {
					newStyle.href = new _Uri2.default(newStyle.href).makeUnique().toString();
				}
			}
			if (newStyle.id) {
				var styleInDoc = _globals2.default.document.getElementById(newStyle.id);
				if (styleInDoc) {
					styleInDoc.parentNode.insertBefore(newStyle, styleInDoc.nextSibling);
					return;
				}
			}
			_globals2.default.document.head.appendChild(newStyle);
		};

		HtmlScreen.prototype.assertSameBodyIdInVirtualDocument = function assertSameBodyIdInVirtualDocument() {
			var bodySurface = this.virtualDocument.querySelector('body');
			if (!_globals2.default.document.body.id) {
				_globals2.default.document.body.id = 'senna_surface_' + _metal.core.getUid();
			}
			if (bodySurface) {
				bodySurface.id = _globals2.default.document.body.id;
			}
		};

		HtmlScreen.prototype.copyNodeAttributesFromContent_ = function copyNodeAttributesFromContent_(content, node) {
			content = content.replace(/[<]\s*html/ig, '<senna');
			content = content.replace(/\/html\s*\>/ig, '/senna>');
			node.innerHTML = content;
			var placeholder = node.querySelector('senna');
			if (placeholder) {
				_utils2.default.clearNodeAttributes(node);
				_utils2.default.copyNodeAttributes(placeholder, node);
			}
		};

		HtmlScreen.prototype.disposeInternal = function disposeInternal() {
			this.disposePendingStyles();
			_RequestScreen.prototype.disposeInternal.call(this);
		};

		HtmlScreen.prototype.disposePendingStyles = function disposePendingStyles() {
			if (this.pendingStyles) {
				this.pendingStyles.forEach(function (style) {
					return _dom.dom.exitDocument(style);
				});
			}
		};

		HtmlScreen.prototype.evaluateScripts = function evaluateScripts(surfaces) {
			var _this2 = this;

			var evaluateTrackedScripts = this.evaluateTrackedResources_(_dom.globalEval.runScriptsInElement, HtmlScreen.selectors.scripts, HtmlScreen.selectors.scriptsTemporary, HtmlScreen.selectors.scriptsPermanent);

			return evaluateTrackedScripts.then(function () {
				return _RequestScreen.prototype.evaluateScripts.call(_this2, surfaces);
			});
		};

		HtmlScreen.prototype.evaluateStyles = function evaluateStyles(surfaces) {
			var _this3 = this;

			this.pendingStyles = [];
			var evaluateTrackedStyles = this.evaluateTrackedResources_(_dom.globalEvalStyles.runStylesInElement, HtmlScreen.selectors.styles, HtmlScreen.selectors.stylesTemporary, HtmlScreen.selectors.stylesPermanent, this.appendStyleIntoDocument_.bind(this));

			return evaluateTrackedStyles.then(function () {
				return _RequestScreen.prototype.evaluateStyles.call(_this3, surfaces);
			});
		};

		HtmlScreen.prototype.evaluateTrackedResources_ = function evaluateTrackedResources_(evaluatorFn, selector, selectorTemporary, selectorPermanent, opt_appendResourceFn) {
			var _this4 = this;

			var tracked = this.virtualQuerySelectorAll_(selector);
			var temporariesInDoc = this.querySelectorAll_(selectorTemporary);
			var permanentsInDoc = this.querySelectorAll_(selectorPermanent);

			// Adds permanent resources in document to cache.
			permanentsInDoc.forEach(function (resource) {
				var resourceKey = _this4.getResourceKey_(resource);
				if (resourceKey) {
					HtmlScreen.permanentResourcesInDoc[resourceKey] = true;
				}
			});

			var frag = _dom.dom.buildFragment();
			tracked.forEach(function (resource) {
				var resourceKey = _this4.getResourceKey_(resource);
				// Do not load permanent resources if already in document.
				if (!HtmlScreen.permanentResourcesInDoc[resourceKey]) {
					frag.appendChild(resource);
				}
				// If resource has key and is permanent add to cache.
				if (resourceKey && _dom.dom.match(resource, selectorPermanent)) {
					HtmlScreen.permanentResourcesInDoc[resourceKey] = true;
				}
			});

			return new _Promise2.default(function (resolve) {
				evaluatorFn(frag, function () {
					temporariesInDoc.forEach(function (resource) {
						return _dom.dom.exitDocument(resource);
					});
					resolve();
				}, opt_appendResourceFn);
			});
		};

		HtmlScreen.prototype.flip = function flip(surfaces) {
			var _this5 = this;

			return _RequestScreen.prototype.flip.call(this, surfaces).then(function () {
				_utils2.default.clearNodeAttributes(document.documentElement);
				_utils2.default.copyNodeAttributes(_this5.virtualDocument, document.documentElement);
			});
		};

		HtmlScreen.prototype.getResourceKey_ = function getResourceKey_(resource) {
			return resource.id || resource.href || resource.src || '';
		};

		HtmlScreen.prototype.getSurfaceContent = function getSurfaceContent(surfaceId) {
			var surface = this.virtualDocument.querySelector('#' + surfaceId);
			if (surface) {
				var defaultChild = surface.querySelector('#' + surfaceId + '-' + _Surface2.default.DEFAULT);
				if (defaultChild) {
					return defaultChild.innerHTML;
				}
				return surface.innerHTML; // If default content not found, use surface content
			}
		};

		HtmlScreen.prototype.getTitleSelector = function getTitleSelector() {
			return this.titleSelector;
		};

		HtmlScreen.prototype.load = function load(path) {
			var _this6 = this;

			return _RequestScreen.prototype.load.call(this, path).then(function (content) {
				_this6.allocateVirtualDocumentForContent(content);
				_this6.resolveTitleFromVirtualDocument();
				_this6.assertSameBodyIdInVirtualDocument();
				return content;
			});
		};

		HtmlScreen.prototype.virtualQuerySelectorAll_ = function virtualQuerySelectorAll_(selector) {
			return Array.prototype.slice.call(this.virtualDocument.querySelectorAll(selector));
		};

		HtmlScreen.prototype.querySelectorAll_ = function querySelectorAll_(selector) {
			return Array.prototype.slice.call(_globals2.default.document.querySelectorAll(selector));
		};

		HtmlScreen.prototype.releaseVirtualDocument = function releaseVirtualDocument() {
			this.virtualDocument = null;
		};

		HtmlScreen.prototype.resolveTitleFromVirtualDocument = function resolveTitleFromVirtualDocument() {
			var title = this.virtualDocument.querySelector(this.titleSelector);
			if (title) {
				this.setTitle(title.innerHTML.trim());
			}
		};

		HtmlScreen.prototype.setTitleSelector = function setTitleSelector(titleSelector) {
			this.titleSelector = titleSelector;
		};

		return HtmlScreen;
	}(_RequestScreen3.default);

	/**
  * Helper selectors for tracking resources.
  * @type {object}
  * @protected
  * @static
  */
	HtmlScreen.selectors = {
		scripts: 'script[data-senna-track]',
		scriptsPermanent: 'script[data-senna-track="permanent"]',
		scriptsTemporary: 'script[data-senna-track="temporary"]',
		styles: 'style[data-senna-track],link[data-senna-track]',
		stylesPermanent: 'style[data-senna-track="permanent"],link[data-senna-track="permanent"]',
		stylesTemporary: 'style[data-senna-track="temporary"],link[data-senna-track="temporary"]'
	};

	/**
  * Caches permanent resource keys.
  * @type {object}
  * @protected
  * @static
  */
	HtmlScreen.permanentResourcesInDoc = {};

	exports.default = HtmlScreen;
});
//# sourceMappingURL=HtmlScreen.js.map