var dependencies = require('./util/dependencies');
var replacer = require('./util/replacer');

module.exports = function(karmaConfig) {
	karmaConfig.set(
		{
			browsers: ['Chrome'],
			files: dependencies.concat(
				[
					'tests/**/*.js'
				]
			),
			frameworks: ['chai', 'commonjs', 'mocha', 'sinon'],
			preprocessors: {
				'/**/*.js': ['replacer'],
				'mocks/*.js': ['replacer'],
				'tests/**/*.js': ['commonjs']
			},
			replacerPreprocessor: {
				replacer: replacer.replaceModulePath
			},
			reporters: ['mocha']
		}
	);
};