define("frontend-js-metal-web@1.0.8/metal-button-group/src/ButtonGroup.soy", ['exports', 'metal-component/src/Component', 'metal-soy/src/Soy'], function (exports, _Component2, _Soy) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.templates = exports.ButtonGroup = undefined;

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

    // This file was automatically generated from ButtonGroup.soy.
    // Please don't edit this file by hand.

    /**
     * @fileoverview Templates in namespace ButtonGroup.
     * @public
     */

    goog.module('ButtonGroup.incrementaldom');

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
      ie_open('div', null, null, 'class', 'btn-group component' + (opt_data.elementClasses ? ' ' + opt_data.elementClasses : ''));
      var buttonList26 = opt_data.buttons;
      var buttonListLen26 = buttonList26.length;
      for (var buttonIndex26 = 0; buttonIndex26 < buttonListLen26; buttonIndex26++) {
        var buttonData26 = buttonList26[buttonIndex26];
        var type__soy6 = buttonData26.type ? buttonData26.type : 'button';
        var cssClass__soy7 = buttonData26.cssClass ? buttonData26.cssClass : 'btn btn-default';
        ie_open('button', null, null, 'type', type__soy6, 'class', cssClass__soy7 + $selectedClass({ label: buttonData26.label, selected: opt_data.selected }, null, opt_ijData), 'data-index', buttonIndex26, 'data-onclick', 'handleClick_');
        ie_open('span', null, null, 'class', 'btn-group-label');
        itext((goog.asserts.assert((buttonData26.label ? buttonData26.label : '') != null), buttonData26.label ? buttonData26.label : ''));
        ie_close('span');
        if (buttonData26.icon) {
          ie_void('span', null, null, 'class', buttonData26.icon);
        }
        ie_close('button');
      }
      ie_close('div');
    }
    exports.render = $render;
    if (goog.DEBUG) {
      $render.soyTemplateName = 'ButtonGroup.render';
    }

    /**
     * @param {Object<string, *>=} opt_data
     * @param {(null|undefined)=} opt_ignored
     * @param {Object<string, *>=} opt_ijData
     * @return {string}
     * @suppress {checkTypes}
     */
    function $selectedClass(opt_data, opt_ignored, opt_ijData) {
      var output = '';
      if (opt_data.selected) {
        var selectedValueList35 = opt_data.selected;
        var selectedValueListLen35 = selectedValueList35.length;
        for (var selectedValueIndex35 = 0; selectedValueIndex35 < selectedValueListLen35; selectedValueIndex35++) {
          var selectedValueData35 = selectedValueList35[selectedValueIndex35];
          output += selectedValueData35 == opt_data.label ? ' btn-group-selected' : '';
        }
      }
      return output;
    }
    exports.selectedClass = $selectedClass;
    if (goog.DEBUG) {
      $selectedClass.soyTemplateName = 'ButtonGroup.selectedClass';
    }

    exports.render.params = ["buttons", "elementClasses", "selected"];
    exports.selectedClass.params = ["label", "selected"];
    exports.templates = templates = exports;
    return exports;
  });

  var ButtonGroup = function (_Component) {
    _inherits(ButtonGroup, _Component);

    function ButtonGroup() {
      _classCallCheck(this, ButtonGroup);

      return _possibleConstructorReturn(this, _Component.apply(this, arguments));
    }

    return ButtonGroup;
  }(_Component3.default);

  _Soy2.default.register(ButtonGroup, templates);
  exports.default = templates;
  exports.ButtonGroup = ButtonGroup;
  exports.templates = templates;
});
//# sourceMappingURL=ButtonGroup.soy.js.map