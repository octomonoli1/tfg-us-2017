define("frontend-js-spa-web@1.0.11/metal-uri/src/parseFromAnchor", ['exports'], function (exports) {
	'use strict';

	/**
  * Parses the given uri string into an object.
  * @param {*=} opt_uri Optional string URI to parse
  */

	Object.defineProperty(exports, "__esModule", {
		value: true
	});
	function parseFromAnchor(opt_uri) {
		var link = document.createElement('a');
		link.href = opt_uri;
		return {
			hash: link.hash,
			hostname: link.hostname,
			password: link.password,
			pathname: link.pathname[0] === '/' ? link.pathname : '/' + link.pathname,
			port: link.port,
			protocol: link.protocol,
			search: link.search,
			username: link.username
		};
	}

	exports.default = parseFromAnchor;
});
//# sourceMappingURL=parseFromAnchor.js.map