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

package com.liferay.portal.tools.db.upgrade.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gregory Amerson
 */
public class GogoTelnetClient implements AutoCloseable {

	public GogoTelnetClient() throws IOException {
		this("localhost", 11311);
	}

	public GogoTelnetClient(String host, int port) throws IOException {
		_socket = new Socket(host, port);

		_dataInputStream = new DataInputStream(_socket.getInputStream());
		_dataOutputStream = new DataOutputStream(_socket.getOutputStream());

		_doHandshake();
	}

	@Override
	public void close() {
		try {
			_socket.close();

			_dataInputStream.close();
			_dataOutputStream.close();
		}
		catch (IOException ioe) {
		}
	}

	public String send(String command) throws IOException {
		byte[] bytes = command.getBytes();

		int[] codes = new int[bytes.length + 2];

		for (int i = 0; i < bytes.length; i++) {
			codes[i] = bytes[i];
		}

		codes[bytes.length] = '\r';
		codes[bytes.length + 1] = '\n';

		_sendCommand(codes);

		return _readUntilNextGogoPrompt();
	}

	private static void _assertCondition(boolean condition) {
		if (!condition) {
			throw new AssertionError();
		}
	}

	private void _doHandshake() throws IOException {

		// Gogo server first sends 4 commands

		_readOneCommand();
		_readOneCommand();
		_readOneCommand();
		_readOneCommand();

		// First we negotiate the terminal type

		// 255 (IAC), 251 (WILL), 24 (terminal type)

		_sendCommand(255, 251, 24);

		// Server should respond

		// 255 (IAC), 250 (SB), 24, 1, 255 (IAC), 240 (SE)

		_readOneCommand();

		// Send the terminal type

		// 255 (IAC), 250 (SB), 24, 0, 'V', 'T', '2', '2', '0', 255 (IAC),
		// 240(SE)

		_sendCommand(255, 250, 24, 0, 'V', 'T', '2', '2', '0', 255, 240);

		// Read Gogo Shell prompt

		_readUntilNextGogoPrompt();
	}

	private int[] _readOneCommand() throws IOException {
		List<Integer> bytes = new ArrayList<>();

		int iac = _dataInputStream.read();

		_assertCondition(iac == 255);

		bytes.add(iac);

		int second = _dataInputStream.read();

		bytes.add(second);

		if (second == 250) {
			int option = _dataInputStream.read();

			bytes.add(option);

			int code = _dataInputStream.read(); // 1 or 0

			_assertCondition(code == 0 || code == 1);

			bytes.add(code);

			if (code == 0) {
				throw new IllegalStateException();
			}
			else if (code == 1) {
				iac = _dataInputStream.read();

				_assertCondition(iac == 255);

				bytes.add(iac);

				int se = _dataInputStream.read(); // SE

				_assertCondition(se == 240);

				bytes.add(se);
			}
		}
		else {
			bytes.add(_dataInputStream.read());
		}

		return _toIntArray(bytes);
	}

	private String _readUntilNextGogoPrompt() throws IOException {
		StringBuilder sb = new StringBuilder();

		int c = _dataInputStream.read();

		while (c != -1) {
			sb.append((char)c);

			String s = sb.toString();

			if (s.endsWith("g! ")) {
				break;
			}

			c = _dataInputStream.read();
		}

		String output = sb.substring(0, sb.length() - 3);

		return output.trim();
	}

	private void _sendCommand(int... codes) throws IOException {
		for (int code : codes) {
			_dataOutputStream.write(code);
		}
	}

	private int[] _toIntArray(List<Integer> list) {
		int[] array = new int[list.size()];

		int i = 0;

		for (Integer integer : list) {
			array[i++] = integer.intValue();
		}

		return array;
	}

	private final DataInputStream _dataInputStream;
	private final DataOutputStream _dataOutputStream;
	private final Socket _socket;

}