define("frontend-image-editor-capability-resize@1.0.4/ResizeComponent.soy", ['exports', 'metal-component/src/Component', 'metal-soy/src/Soy'], function (exports, _Component2, _Soy) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.templates = exports.ImageEditorResizeComponent = undefined;

  var _Component3 = _interopRequireDefault(_Component2);

  var _Soy2 = _interopRequireDefault(_Soy);

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

  var templates;
  goog.loadModule(function (exports) {

    // This file was automatically generated from ResizeComponent.soy.
    // Please don't edit this file by hand.

    /**
     * @fileoverview Templates in namespace ImageEditorResizeComponent.
     * @public
     */

    goog.module('ImageEditorResizeComponent.incrementaldom');

    /** @suppress {extraRequire} */
    var soy = goog.require('soy');
    /** @suppress {extraRequire} */
    var soydata = goog.require('soydata');
    /** @suppress {extraRequire} */
    goog.require('goog.i18n.bidi');
    /** @suppress {extraRequire} */
    goog.require('goog.asserts');
    var IncrementalDom = goog.require('incrementaldom');
    var ie_open = IncrementalDom.elementOpen;
    var ie_close = IncrementalDom.elementClose;
    var ie_void = IncrementalDom.elementVoid;
    var ie_open_start = IncrementalDom.elementOpenStart;
    var ie_open_end = IncrementalDom.elementOpenEnd;
    var itext = IncrementalDom.text;
    var iattr = IncrementalDom.attr;

    /**
     * @param {Object<string, *>=} opt_data
     * @param {(null|undefined)=} opt_ignored
     * @param {Object<string, *>=} opt_ijData
     * @return {void}
     * @suppress {checkTypes}
     */
    function $render(opt_data, opt_ignored, opt_ijData) {
      ie_open('div', null, null, 'id', opt_data.key);
      ie_open('div', null, null, 'class', 'input-group');
      ie_void('input', null, null, 'class', 'form-control', 'data-oninput', 'syncDimensions', 'id', opt_data.key + 'Width', 'type', 'number', 'value', opt_data.imageWidth);
      ie_open('a', null, null, 'class', 'btn btn-link', 'data-onclick', 'toggleLockProportions', 'href', 'javascript:;');
      ie_open('svg', null, null, 'class', 'lexicon-icon', 'style', 'width: 14px;');
      ie_void('use', null, null, 'xlink:href', opt_data.pathThemeImages + '/lexicon/icons.svg#' + (opt_data.lockProportions ? 'lock' : 'unlock'));
      ie_close('svg');
      ie_close('a');
      ie_void('input', null, null, 'class', 'form-control', 'data-oninput', 'syncDimensions', 'id', opt_data.key + 'Height', 'type', 'number', 'value', opt_data.imageHeight);
      ie_close('div');
      ie_close('div');
    }
    exports.render = $render;
    if (goog.DEBUG) {
      $render.soyTemplateName = 'ImageEditorResizeComponent.render';
    }

    exports.render.params = ["imageHeight", "imageWidth", "key", "lockProportions", "pathThemeImages"];
    exports.render.types = { "imageHeight": "any", "imageWidth": "any", "key": "any", "lockProportions": "any", "pathThemeImages": "any" };
    exports.templates = templates = exports;
    return exports;
  });

  var ImageEditorResizeComponent = function (_Component) {
    _inherits(ImageEditorResizeComponent, _Component);

    function ImageEditorResizeComponent() {
      _classCallCheck(this, ImageEditorResizeComponent);

      return _possibleConstructorReturn(this, _Component.apply(this, arguments));
    }

    return ImageEditorResizeComponent;
  }(_Component3.default);

  _Soy2.default.register(ImageEditorResizeComponent, templates);
  exports.default = templates;
  exports.ImageEditorResizeComponent = ImageEditorResizeComponent;
  exports.templates = templates;
});
//# sourceMappingURL=ResizeComponent.soy.js.map