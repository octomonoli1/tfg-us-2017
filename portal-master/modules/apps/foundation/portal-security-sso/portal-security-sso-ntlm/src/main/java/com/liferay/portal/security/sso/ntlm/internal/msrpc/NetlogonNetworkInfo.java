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
public class NetlogonNetworkInfo extends NdrObject {

	public NetlogonNetworkInfo(
		NetlogonIdentityInfo netlogonIdentityInfo, byte[] lmChallenge,
		byte[] ntChallengeResponse, byte[] lmChallengeResponse) {

		_netlogonIdentityInfo = netlogonIdentityInfo;
		_lmChallenge = lmChallenge;
		_ntChallengeResponse = ntChallengeResponse;
		_lmChallengeResponse = lmChallengeResponse;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		_netlogonIdentityInfo.encode(ndrBuffer);

		int lmChallengeIndex = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer.enc_ndr_short((short)_ntChallengeResponse.length);
		ndrBuffer.enc_ndr_short((short)_ntChallengeResponse.length);
		ndrBuffer.enc_ndr_referent(_ntChallengeResponse, 1);

		ndrBuffer.enc_ndr_short((short)_lmChallengeResponse.length);
		ndrBuffer.enc_ndr_short((short)_lmChallengeResponse.length);
		ndrBuffer.enc_ndr_referent(_lmChallengeResponse, 1);

		_netlogonIdentityInfo.encodeLogonDomainName(ndrBuffer);
		_netlogonIdentityInfo.encodeUserName(ndrBuffer);
		_netlogonIdentityInfo.encodeWorkStationName(ndrBuffer);

		ndrBuffer = ndrBuffer.derive(lmChallengeIndex);

		for (int i = 0; i < 8; i++) {
			ndrBuffer.enc_ndr_small(_lmChallenge[i]);
		}

		encodeChallengeResponse(ndrBuffer, _ntChallengeResponse);
		encodeChallengeResponse(ndrBuffer, _lmChallengeResponse);
	}

	protected void encodeChallengeResponse(
		NdrBuffer ndrBuffer, byte[] challenge) {

		ndrBuffer = ndrBuffer.deferred;

		ndrBuffer.enc_ndr_long(challenge.length);
		ndrBuffer.enc_ndr_long(0);
		ndrBuffer.enc_ndr_long(challenge.length);

		int index = ndrBuffer.index;

		ndrBuffer.advance(challenge.length);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < challenge.length; i++) {
			ndrBuffer.enc_ndr_small(challenge[i]);
		}
	}

	private final byte[] _lmChallenge;
	private final byte[] _lmChallengeResponse;
	private final NetlogonIdentityInfo _netlogonIdentityInfo;
	private final byte[] _ntChallengeResponse;

}