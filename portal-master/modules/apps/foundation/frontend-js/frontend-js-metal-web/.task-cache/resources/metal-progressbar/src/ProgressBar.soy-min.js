define("frontend-js-metal-web@1.0.8/metal-progressbar/src/ProgressBar.soy-min", ["exports","metal-component/src/Component","metal-soy/src/Soy"], function(e,r,t){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function n(e,r){if(!(e instanceof r))throw new TypeError("Cannot call a class as a function")}function a(e,r){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!r||"object"!=typeof r&&"function"!=typeof r?e:r}function l(e,r){if("function"!=typeof r&&null!==r)throw new TypeError("Super expression must either be null or a function, not "+typeof r);e.prototype=Object.create(r&&r.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),r&&(Object.setPrototypeOf?Object.setPrototypeOf(e,r):e.__proto__=r)}Object.defineProperty(e,"__esModule",{value:!0}),e.templates=e.ProgressBar=void 0;var s,i=o(r),u=o(t);goog.loadModule(function(e){function r(e,r,t){e=e||{};var l=e.max?e.max:100,s=e.min?e.min:0,i=e.value?e.value:0;o("div",null,null,"class","progress "+(e.elementClasses?" "+e.elementClasses:""),"role","progressbar","aria-valuemax",l,"aria-valuemin",s,"aria-valuenow",i,"tabindex","0");var u=Math.floor(100*(i-s)/(l-s));o("div",null,null,"class","progress-bar"+(e.barClass?" "+e.barClass:""),"style","width: "+u+"%"),a((goog.asserts.assert(null!=(e.label?e.label:"")),e.label?e.label:"")),n("div"),n("div")}goog.module("ProgressBar.incrementaldom");goog.require("soy"),goog.require("soydata");goog.require("goog.i18n.bidi"),goog.require("goog.asserts");var t=goog.require("incrementaldom"),o=t.elementOpen,n=t.elementClose,a=(t.elementVoid,t.elementOpenStart,t.elementOpenEnd,t.text);t.attr;return (e.render=r, goog.DEBUG&&(r.soyTemplateName="ProgressBar.render"), e.render.params=["barClass","elementClasses","label","max","min","value"], e.templates=s=e, e)});var c=function(e){function r(){return (n(this,r), a(this,e.apply(this,arguments)))}return (l(r,e), r)}(i["default"]);u["default"].register(c,s),e["default"]=s,e.ProgressBar=c,e.templates=s});