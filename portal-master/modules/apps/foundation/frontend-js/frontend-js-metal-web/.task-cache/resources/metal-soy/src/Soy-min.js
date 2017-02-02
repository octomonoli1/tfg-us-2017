define("frontend-js-metal-web@1.0.8/metal-soy/src/Soy-min", ["exports","metal/src/metal","metal-component/src/all/component","html2incdom/src/withParser","metal-incremental-dom/src/IncrementalDomRenderer","./SoyAop","metal-soy-bundle/build/bundle"], function(t,e,n,o,r,i){"use strict";function a(t){return t&&t.__esModule?t:{"default":t}}function c(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function l(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function s(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0}),t.SoyAop=t.Soy=void 0;var u=a(o),p=a(r),f=a(i),d={},m=function(t){function o(e){c(this,o);var n=l(this,t.call(this,e));return (n.addMissingStateKeys_(), n)}return (s(o,t), o.prototype.addMissingStateKeys_=function(){var t=this.component_.constructor.TEMPLATE;if(e.core.isFunction(t)){t=f["default"].getOriginalFn(t),this.soyParamTypes_=t.types||{};for(var n=t.params||[],o=this.component_,r=0;r<n.length;r++)o.getStateKeyConfig(n[r])||o[n[r]]||o.addToState(n[r],{},o.getInitialConfig()[n[r]])}}, o.prototype.buildTemplateData_=function(t){var n=this,r=this.component_,i=e.object.mixin({},r.config);r.getStateKeys().forEach(function(t){if("element"!==t){var e=r[t];n.isHtmlParam_(t)&&(e=o.toIncDom(e)),i[t]=e}});for(var a=0;a<t.length;a++)!i[t[a]]&&e.core.isFunction(r[t[a]])&&(i[t[a]]=r[t[a]].bind(r));return i}, o.getTemplate=function(t,e){return function(n,o,r){if(!goog.loadedModules_[t])throw new Error('No template with namespace "'+t+'" has been loaded yet.');return goog.loadedModules_[t][e](n,o,r)}}, o.handleInterceptedCall_=function(t){var e=arguments.length<=1||void 0===arguments[1]?{}:arguments[1],n=[t.componentCtor,null,[]];for(var o in e)n.push(o,e[o]);IncrementalDOM.elementVoid.apply(null,n)}, o.prototype.isHtmlParam_=function(t){if(this.component_.getStateKeyConfig(t).isHtml)return!0;var e=this.soyParamTypes_[t]||"";return-1!==e.split("|").indexOf("html")}, o.register=function(t,e){var r=arguments.length<=2||void 0===arguments[2]?"render":arguments[2];t.RENDERER=o,t.TEMPLATE=f["default"].getOriginalFn(e[r]),t.TEMPLATE.componentCtor=t,f["default"].registerForInterception(e,r),n.ComponentRegistry.register(t)}, o.prototype.renderIncDom=function(){var n=this.component_.constructor.TEMPLATE;e.core.isFunction(n)&&!this.component_.render?(n=f["default"].getOriginalFn(n),f["default"].startInterception(o.handleInterceptedCall_),n(this.buildTemplateData_(n.params||[]),null,d),f["default"].stopInterception()):t.prototype.renderIncDom.call(this)}, o.setInjectedData=function(t){d=t||{}}, o.prototype.shouldUpdate=function(e){var n=t.prototype.shouldUpdate.call(this,e);if(!n||this.component_.shouldUpdate)return n;for(var o=this.component_.constructor.TEMPLATE,r=o?f["default"].getOriginalFn(o).params:[],i=0;i<r.length;i++)if(e[r[i]])return!0;return!1}, o.toHtmlString=function(t){var e=document.createElement("div");return (IncrementalDOM.patch(e,t), e.innerHTML)}, o.toIncDom=function(t){return (e.core.isObject(t)&&e.core.isString(t.content)&&"HTML"===t.contentKind&&(t=t.content), e.core.isString(t)&&(t=u["default"].buildFn(t)), t)}, o)}(p["default"]);t["default"]=m,t.Soy=m,t.SoyAop=f["default"]});