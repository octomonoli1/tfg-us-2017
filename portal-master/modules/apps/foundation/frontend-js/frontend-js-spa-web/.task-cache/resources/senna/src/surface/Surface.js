define("frontend-js-spa-web@1.0.11/senna/src/surface/Surface", ['exports', '../globals/globals', 'metal/src/metal', 'metal-dom/src/all/dom', 'metal-promise/src/promise/Promise'], function (exports, _globals, _metal, _dom, _Promise) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _globals2 = _interopRequireDefault(_globals);

	var _dom2 = _interopRequireDefault(_dom);

	var _Promise2 = _interopRequireDefault(_Promise);

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

	var Surface = function (_Disposable) {
		_inherits(Surface, _Disposable);

		/**
   * Surface class representing the references to elements on the page that
   * can potentially be updated by <code>App</code>.
   * @param {string} id
   * @constructor
   */

		function Surface(id) {
			_classCallCheck(this, Surface);

			var _this = _possibleConstructorReturn(this, _Disposable.call(this));

			if (!id) {
				throw new Error('Surface element id not specified. A surface element requires a valid id.');
			}

			/**
    * Holds the active child element.
    * @type {Element}
    * @default null
    * @protected
    */
			_this.activeChild = null;

			/**
    * Holds the default child element.
    * @type {Element}
    * @default null
    * @protected
    */
			_this.defaultChild = null;

			/**
    * Holds the element with the specified surface id, if not found creates a
    * new element with the specified id.
    * @type {Element}
    * @default null
    * @protected
    */
			_this.element = null;

			/**
    * Holds the surface id.
    * @type {String}
    * @default null
    * @protected
    */
			_this.id = id;

			/**
    * Holds the default transitionFn for the surfaces.
    * @param {?Element=} from The visible surface element.
    * @param {?Element=} to The surface element to be flipped.
    * @default null
    */
			_this.transitionFn = null;

			_this.defaultChild = _this.getChild(Surface.DEFAULT);
			_this.maybeWrapContentAsDefault_();
			_this.activeChild = _this.defaultChild;
			return _this;
		}

		/**
   * Adds screen content to a surface. If content hasn't been passed, see if
   * an element exists in the DOM that matches the id. By convention, the
   * element should already be nested in the right element and should have an
   * id that is a concatentation of the surface id + '-' + the screen id.
   * @param {!string} screenId The screen id the content belongs too.
   * @param {?string|Element=} opt_content The string content or element to
   *     add be added as surface content.
   * @return {Element}
   */


		Surface.prototype.addContent = function addContent(screenId, opt_content) {
			var child = this.defaultChild;

			if (_metal.core.isDefAndNotNull(opt_content)) {
				child = this.getChild(screenId);
				if (child) {
					_dom2.default.removeChildren(child);
				} else {
					child = this.createChild(screenId);
					this.transition(child, null);
				}
				_dom2.default.append(child, opt_content);
			}

			var element = this.getElement();

			if (element && child) {
				_dom2.default.append(element, child);
			}

			return child;
		};

		Surface.prototype.createChild = function createChild(screenId) {
			var child = _globals2.default.document.createElement('div');
			child.setAttribute('id', this.makeId_(screenId));
			return child;
		};

		Surface.prototype.getChild = function getChild(screenId) {
			return _globals2.default.document.getElementById(this.makeId_(screenId));
		};

		Surface.prototype.getElement = function getElement() {
			if (this.element) {
				return this.element;
			}
			this.element = _globals2.default.document.getElementById(this.id);
			return this.element;
		};

		Surface.prototype.getId = function getId() {
			return this.id;
		};

		Surface.prototype.getTransitionFn = function getTransitionFn() {
			return this.transitionFn;
		};

		Surface.prototype.makeId_ = function makeId_(screenId) {
			return this.id + '-' + screenId;
		};

		Surface.prototype.maybeWrapContentAsDefault_ = function maybeWrapContentAsDefault_() {
			var element = this.getElement();
			if (element && !this.defaultChild) {
				var fragment = _globals2.default.document.createDocumentFragment();
				while (element.firstChild) {
					fragment.appendChild(element.firstChild);
				}
				this.defaultChild = this.addContent(Surface.DEFAULT, fragment);
				this.transition(null, this.defaultChild);
			}
		};

		Surface.prototype.setId = function setId(id) {
			this.id = id;
		};

		Surface.prototype.setTransitionFn = function setTransitionFn(transitionFn) {
			this.transitionFn = transitionFn;
		};

		Surface.prototype.show = function show(screenId) {
			var from = this.activeChild;
			var to = this.getChild(screenId);
			if (!to) {
				to = this.defaultChild;
			}
			this.activeChild = to;
			return this.transition(from, to).thenAlways(function () {
				if (from && from !== to) {
					_dom2.default.exitDocument(from);
				}
			});
		};

		Surface.prototype.remove = function remove(screenId) {
			var child = this.getChild(screenId);
			if (child) {
				_dom2.default.exitDocument(child);
			}
		};

		Surface.prototype.toString = function toString() {
			return this.id;
		};

		Surface.prototype.transition = function transition(from, to) {
			var transitionFn = this.transitionFn || Surface.defaultTransition;
			return _Promise2.default.resolve(transitionFn.call(this, from, to));
		};

		return Surface;
	}(_metal.Disposable);

	/**
    * Holds the default surface name. Elements on the page must contain a child
    * element containing the default content, this element must be as following:
    *
    * Example:
    * <code>
    *   <div id="mysurface">
    *     <div id="mysurface-default">Default surface content.</div>
    *   </div>
    * </code>
    *
    * The default content is relevant for the initial page content. When a
    * screen doesn't provide content for the surface the default content is
    * restored into the page.
    *
    * @type {!String}
    * @default default
    * @static
    */
	Surface.DEFAULT = 'default';

	/**
  * Holds the default transition for all surfaces. Each surface could have its
  * own transition.
  *
  * Example:
  *
  * <code>
  * surface.setTransitionFn(function(from, to) {
  *   if (from) {
  *     from.style.display = 'none';
  *     from.classList.remove('flipped');
  *   }
  *   if (to) {
  *     to.style.display = 'block';
  *     to.classList.add('flipped');
  *   }
  *   return null;
  * });
  * </code>
  *
  * @param {?Element=} from The visible surface element.
  * @param {?Element=} to The surface element to be flipped.
  * @static
  */
	Surface.defaultTransition = function (from, to) {
		if (from) {
			from.style.display = 'none';
			from.classList.remove('flipped');
		}
		if (to) {
			to.style.display = 'block';
			to.classList.add('flipped');
		}
	};

	exports.default = Surface;
});
//# sourceMappingURL=Surface.js.map