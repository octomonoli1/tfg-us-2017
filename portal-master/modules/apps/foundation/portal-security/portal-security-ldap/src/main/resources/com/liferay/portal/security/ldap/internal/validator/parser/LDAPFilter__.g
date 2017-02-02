lexer grammar LDAPFilter;
options {
  language=Java;

}
@members {
	@Override
	public void reportError(RecognitionException e) {
		throw new RuntimeException(e);
	}
}
@header {
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.security.ldap.internal.validator.parser;
}

T11 : '(' ;
T12 : ')' ;
T13 : '&' ;
T14 : '|' ;
T15 : '!' ;
T16 : '=' ;
T17 : '~=' ;
T18 : '>=' ;
T19 : '<=' ;
T20 : ':dn' ;
T21 : ':=' ;
T22 : '*' ;
T23 : ';' ;

// $ANTLR src "LDAPFilter.g" 151
fragment ASCII_LETTER
	: 'a'..'z'
	|'A'..'Z'
	;
// $ANTLR src "LDAPFilter.g" 155
fragment DIGIT:  '0'..'9';
// $ANTLR src "LDAPFilter.g" 156
fragment DASH: '-';

// $ANTLR src "LDAPFilter.g" 158
DOT: '.';
// $ANTLR src "LDAPFilter.g" 159
COLON: ':';
// $ANTLR src "LDAPFilter.g" 160
UTF: '\u0080'..'\ufffe';
// $ANTLR src "LDAPFilter.g" 161
ASCII_LATIN1: '\u0000'..'\u007f';


