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

package com.liferay.portal.security.sso.ntlm.internal.msrpc;

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrObject;

/**
 * @author Marcellus Tavares
 */
public class NetlogonAuthenticator extends NdrObject {

	public NetlogonAuthenticator() {
		_credential = new byte[8];
	}

	public NetlogonAuthenticator(byte[] credential, int timestamp) {
		_credential = credential;
		_timestamp = timestamp;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		_timestamp = ndrBuffer.dec_ndr_long();

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			_credential[i] = (byte)ndrBuffer.dec_ndr_small();
		}
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer.enc_ndr_long(_timestamp);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			ndrBuffer.enc_ndr_small(_credential[i]);
		}
	}

	private final byte[] _credential;
	private int _timestamp;

}