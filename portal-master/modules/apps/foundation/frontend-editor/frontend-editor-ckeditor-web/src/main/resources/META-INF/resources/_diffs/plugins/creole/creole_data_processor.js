(function() {
	var CKTools = CKEDITOR.tools;

	var NEW_LINE = '\n';

	var REGEX_CREOLE_RESERVED_CHARACTERS = /(\/{1,2}|={1,6}|\[{1,2}|\]{1,2}|\\{1,2}|\*{1,}|----|{{2,3}|}{2,3}|#{1,})/g;

	var REGEX_HEADER = /^h([1-6])$/i;

	var REGEX_LASTCHAR_NEWLINE = /(\r?\n\s*)$/;

	var REGEX_NEWLINE = /\r?\n/g;

	var REGEX_NOT_WHITESPACE = /[^\t\n\r ]/;

	var REGEX_URL_PREFIX = /^(?:\/|https?|ftp):\/\//i;

	var STR_BLANK = '';

	var STR_EQUALS = '=';

	var STR_LIST_ITEM_ESCAPE_CHARACTERS = '\\\\';

	var STR_PIPE = '|';

	var STR_SPACE = ' ';

	var TAG_BOLD = '**';

	var TAG_EMPHASIZE = '//';

	var TAG_LIST_ITEM = 'li';

	var TAG_ORDERED_LIST = 'ol';

	var TAG_ORDERED_LIST_ITEM = '#';

	var TAG_PARAGRAPH = 'p';

	var TAG_PRE = 'pre';

	var TAG_TELETYPETEXT = 'tt';

	var TAG_UNORDERED_LIST = 'ul';

	var TAG_UNORDERED_LIST_ITEM = '*';

	var attachmentURLPrefix;

	var CreoleDataProcessor = function() {
	};

	CreoleDataProcessor.prototype = {
		constructor: CreoleDataProcessor,

		toDataFormat: function(html, config) {
			var instance = this;

			var data = instance._convert(html);

			return data;
		},

		toHtml: function(data, config) {
			var instance = this;

			if (config) {
				var fragment = CKEDITOR.htmlParser.fragment.fromHtml(data);

				var writer = new CKEDITOR.htmlParser.basicWriter();

				config.filter.applyTo(fragment);

				fragment.writeHtml(writer);

				data = writer.getHtml();
			}
			else {
				var div = document.createElement('div');

				if (!instance._creoleParser) {
					instance._creoleParser = new CKEDITOR.CreoleParser(
						{
							imagePrefix: attachmentURLPrefix
						}
					);
				}

				instance._creoleParser.parse(div, data);

				data = div.innerHTML;
			}

			return data;
		},

		_appendNewLines: function(total) {
			var instance = this;

			var count = 0;

			var endResult = instance._endResult;

			var newLinesAtEnd = REGEX_LASTCHAR_NEWLINE.exec(endResult.slice(-2).join(STR_BLANK));

			if (newLinesAtEnd) {
				count = newLinesAtEnd[1].length;
			}

			while (count++ < total) {
				endResult.push(NEW_LINE);
			}
		},

		_convert: function(data) {
			var instance = this;

			var node = document.createElement('div');

			node.innerHTML = data;

			instance._handle(node);

			var endResult = instance._endResult.join(STR_BLANK);

			instance._endResult = null;

			instance._listsStack.length = 0;

			return endResult;
		},

		_handle: function(node) {
			var instance = this;

			if (!instance._endResult) {
				instance._endResult = [];
			}

			var children = node.childNodes;

			var pushTagList = instance._pushTagList;

			for (var i = 0; i < children.length; i++) {
				var listTagsIn = [];
				var listTagsOut = [];
				var stylesTagsIn = [];
				var stylesTagsOut = [];

				var child = children[i];

				if (instance._skipParse) {
					instance._handleData(child.data || child.outerHTML, node);

					continue;
				}
				else if (instance._isIgnorable(child)) {
					continue;
				}

				instance._handleElementStart(child, listTagsIn, listTagsOut);
				instance._handleStyles(child, stylesTagsIn, stylesTagsOut);

				pushTagList.call(instance, listTagsIn);
				pushTagList.call(instance, stylesTagsIn);

				instance._handle(child);

				instance._handleElementEnd(child, listTagsIn, listTagsOut);

				pushTagList.call(instance, stylesTagsOut.reverse());
				pushTagList.call(instance, listTagsOut);
			}

			instance._handleData(node.data, node);
		},

		_handleBreak: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			var newLineCharacter = STR_LIST_ITEM_ESCAPE_CHARACTERS;

			var nextSibling = element.nextSibling;

			if (instance._skipParse) {
				newLineCharacter = NEW_LINE;

				listTagsIn.push(newLineCharacter);
			}
			else if (element.previousSibling && nextSibling && nextSibling !== NEW_LINE) {
				listTagsIn.push(newLineCharacter);
			}
		},

		_handleData: function(data, element) {
			var instance = this;

			if (data) {
				if (!instance._skipParse) {
					data = data.replace(REGEX_NEWLINE, STR_BLANK);

					if (!instance._verbatim) {
						data = data.replace(
							REGEX_CREOLE_RESERVED_CHARACTERS,
							function(match, p1, offset, string) {
								var res = '';

								if (!instance._endResult.length) {
									res += '~' + p1;
								}
								else {
									var lastResultString = instance._endResult[instance._endResult.length - 1];

									var lastResultCharacter = lastResultString[lastResultString.length - 1];

									if (lastResultCharacter !== '~' && lastResultCharacter !== p1[0]) {
										res += '~';
									}

									res += p1;

								}

								return res;
							}
						);
					}
				}

				instance._endResult.push(data);
			}
		},

		_handleElementEnd: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			var tagName = element.tagName;

			if (tagName) {
				tagName = tagName.toLowerCase();
			}

			if (tagName == TAG_PARAGRAPH) {
				if (!instance._isLastItemNewLine()) {
					instance._endResult.push(NEW_LINE);
				}
			}
			else if (tagName == TAG_UNORDERED_LIST || tagName == TAG_ORDERED_LIST) {
				instance._listsStack.pop();

				var newLinesCount = 1;

				if (!instance._hasParentNode(element, TAG_LIST_ITEM)) {
					newLinesCount = 2;
				}

				instance._appendNewLines(newLinesCount);
			}
			else if (tagName == TAG_PRE) {
				if (!instance._isLastItemNewLine()) {
					instance._endResult.push(NEW_LINE);
				}
			}
			else if (tagName == 'table') {
				listTagsOut.push(NEW_LINE);
			}

			instance._skipParse = false;
			instance._verbatim = false;
		},

		_handleElementStart: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			var tagName = element.tagName;

			if (tagName) {
				tagName = tagName.toLowerCase();

				var regexHeader = REGEX_HEADER.exec(tagName);

				if (tagName == TAG_PARAGRAPH) {
					instance._handleParagraph(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'br') {
					instance._handleBreak(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'a') {
					instance._handleLink(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'strong' || tagName == 'b') {
					instance._handleStrong(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'em' || tagName == 'i') {
					instance._handleEm(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'img') {
					instance._handleImage(element, listTagsIn, listTagsOut);
				}
				else if (tagName == TAG_UNORDERED_LIST) {
					instance._handleUnorderedList(element, listTagsIn, listTagsOut);
				}
				else if (tagName == TAG_LIST_ITEM) {
					instance._handleListItem(element, listTagsIn, listTagsOut);
				}
				else if (tagName == TAG_ORDERED_LIST) {
					instance._handleOrderedList(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'hr') {
					instance._handleHr(element, listTagsIn, listTagsOut);
				}
				else if (tagName == TAG_PRE) {
					instance._handlePre(element, listTagsIn, listTagsOut);
				}
				else if (tagName == TAG_TELETYPETEXT) {
					instance._handleTT(element, listTagsIn, listTagsOut);
				}
				else if (regexHeader) {
					instance._handleHeader(element, listTagsIn, listTagsOut, regexHeader);
				}
				else if (tagName == 'th') {
					instance._handleTableHeader(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'tr') {
					instance._handleTableRow(element, listTagsIn, listTagsOut);
				}
				else if (tagName == 'td') {
					instance._handleTableCell(element, listTagsIn, listTagsOut);
				}
			}
		},

		_handleEm: function(element, listTagsIn, listTagsOut) {
			listTagsIn.push(TAG_EMPHASIZE);
			listTagsOut.push(TAG_EMPHASIZE);
		},

		_handleHeader: function(element, listTagsIn, listTagsOut, params) {
			var instance = this;

			var res = new Array(parseInt(params[1], 10) + 1);

			res = res.join(STR_EQUALS);

			if (instance._isDataAvailable() && !instance._isLastItemNewLine()) {
				listTagsIn.push(NEW_LINE);
			}

			listTagsIn.push(res, STR_SPACE);
			listTagsOut.push(STR_SPACE, res, NEW_LINE);

			instance._verbatim = true;
		},

		_handleHr: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			if (instance._isDataAvailable() && !instance._isLastItemNewLine()) {
				listTagsIn.push(NEW_LINE);
			}

			listTagsIn.push('----', NEW_LINE);
		},

		_handleImage: function(element, listTagsIn, listTagsOut) {
			var attrAlt = element.getAttribute('alt');
			var attrSrc = element.getAttribute('src');

			attrSrc = attrSrc.replace(attachmentURLPrefix, STR_BLANK);

			listTagsIn.push('{{', attrSrc);

			if (attrAlt) {
				listTagsIn.push(STR_PIPE, attrAlt);
			}

			listTagsOut.push('}}');
		},

		_handleLink: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			var hrefAttribute = element.getAttribute('href');

			if (hrefAttribute) {
				if (!REGEX_URL_PREFIX.test(hrefAttribute)) {
					hrefAttribute = decodeURIComponent(hrefAttribute);
				}

				var linkText = element.textContent || element.innerText;

				listTagsIn.push('[[');

				if (linkText === hrefAttribute) {
					instance._verbatim = true;
				}
				else {
					listTagsIn.push(hrefAttribute, STR_PIPE);
				}

				listTagsOut.push(']]');
			}
		},

		_handleListItem: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			if (instance._isDataAvailable() && !instance._isLastItemNewLine()) {
				listTagsIn.push(NEW_LINE);
			}

			var listsStack = instance._listsStack;

			var listsStackLength = listsStack.length;

			listTagsIn.push(new Array(listsStackLength + 1).join(listsStack[listsStackLength - 1]));
		},

		_handleOrderedList: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			instance._listsStack.push(TAG_ORDERED_LIST_ITEM);
		},

		_handleParagraph: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			if (instance._isDataAvailable()) {
				instance._appendNewLines(2);
			}

			listTagsOut.push(NEW_LINE);
		},

		_handlePre: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			instance._skipParse = true;

			if (instance._isDataAvailable() && !instance._isLastItemNewLine()) {
				instance._endResult.push(NEW_LINE);
			}

			listTagsIn.push('{{{', NEW_LINE);
			listTagsOut.push('}}}', NEW_LINE);
		},

		_handleStrong: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			var previousSibling = element.previousSibling;

			if (instance._isParentNode(element, TAG_LIST_ITEM) && (!previousSibling || instance._isIgnorable(previousSibling))) {
				listTagsIn.push(STR_SPACE);
			}

			listTagsIn.push(TAG_BOLD);
			listTagsOut.push(TAG_BOLD);
		},

		_handleStyles: function(element, stylesTagsIn, stylesTagsOut) {
			var style = element.style;

			if (style) {
				if (style.fontWeight.toLowerCase() == 'bold') {
					stylesTagsIn.push(TAG_BOLD);
					stylesTagsOut.push(TAG_BOLD);
				}

				if (style.fontStyle.toLowerCase() == 'italic') {
					stylesTagsIn.push(TAG_EMPHASIZE);
					stylesTagsOut.push(TAG_EMPHASIZE);
				}
			}
		},

		_handleTableCell: function(element, listTagsIn, listTagsOut) {
			listTagsIn.push(STR_PIPE);
		},

		_handleTableHeader: function(element, listTagsIn, listTagsOut) {
			listTagsIn.push(STR_PIPE, STR_EQUALS);
		},

		_handleTableRow: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			if (instance._isDataAvailable()) {
				listTagsIn.push(NEW_LINE);
			}

			listTagsOut.push(STR_PIPE);
		},

		_handleTT: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			instance._skipParse = true;

			listTagsIn.push('{{{');
			listTagsOut.push('}}}');
		},

		_handleUnorderedList: function(element, listTagsIn, listTagsOut) {
			var instance = this;

			instance._listsStack.push(TAG_UNORDERED_LIST_ITEM);
		},

		_hasClass: function(element, className) {
			return (STR_SPACE + element.className + STR_SPACE).indexOf(STR_SPACE + className + STR_SPACE) > -1;
		},

		_hasParentNode: function(element, tags, level) {
			var instance = this;

			if (!CKTools.isArray(tags)) {
				tags = [tags];
			}

			var result = false;

			var parentNode = element.parentNode;

			var tagName = parentNode && parentNode.tagName && parentNode.tagName.toLowerCase();

			if (tagName) {
				var length = tags.length;

				for (var i = 0; i < length; i++) {
					result = instance._tagNameMatch(tagName, tags[i]);

					if (result) {
						break;
					}
				}
			}

			if (!result && parentNode && (!isFinite(level) || --level)) {
				result = instance._hasParentNode(parentNode, tags, level);
			}

			return result;
		},

		_isDataAvailable: function() {
			var instance = this;

			var endResult = instance._endResult;

			return endResult && endResult.length;
		},

		_isIgnorable: function(node) {
			var instance = this;

			var nodeType = node.nodeType;

			return node.isElementContentWhitespace || nodeType == 8 || nodeType == 3 && instance._isWhitespace(node);
		},

		_isLastItemNewLine: function(node) {
			var instance = this;

			var endResult = instance._endResult;

			return endResult && REGEX_LASTCHAR_NEWLINE.test(endResult.slice(-1));
		},

		_isParentNode: function(element, tagName) {
			var instance = this;

			return instance._hasParentNode(element, tagName, 1);
		},

		_isWhitespace: function(node) {
			return node.isElementContentWhitespace || !REGEX_NOT_WHITESPACE.test(node.data);
		},

		_pushTagList: function(tagsList) {
			var instance = this;

			var endResult;
			var i;
			var length;
			var tag;

			endResult = instance._endResult;
			length = tagsList.length;

			for (i = 0; i < length; i++) {
				tag = tagsList[i];

				endResult.push(tag);
			}
		},

		_tagNameMatch: function(tagSrc, tagDest) {
			return tagDest instanceof RegExp && tagDest.test(tagSrc) || tagSrc === tagDest;
		},

		_endResult: null,

		_listsStack: [],

		_skipParse: false,

		_verbatim: true
	};

	CKEDITOR.plugins.add(
		'creole_data_processor',
		{
			requires: ['htmlwriter'],

			init: function(editor) {
				attachmentURLPrefix = editor.config.attachmentURLPrefix;

				editor.dataProcessor = new CreoleDataProcessor(editor);

				editor.fire('customDataProcessorLoaded');
			}
		}
	);
})();