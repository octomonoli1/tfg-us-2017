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

package com.liferay.portal.kernel.nio.intraband.nonblocking;

import com.liferay.portal.kernel.nio.intraband.BaseIntraband;
import com.liferay.portal.kernel.nio.intraband.BaseIntrabandHelper;
import com.liferay.portal.kernel.nio.intraband.ChannelContext;
import com.liferay.portal.kernel.nio.intraband.ClosedIntrabandException;
import com.liferay.portal.kernel.nio.intraband.CompletionHandler.CompletionType;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.DatagramHelper;
import com.liferay.portal.kernel.nio.intraband.IntrabandTestUtil;
import com.liferay.portal.kernel.nio.intraband.RecordCompletionHandler;
import com.liferay.portal.kernel.nio.intraband.RecordDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.AdviseWith;
import com.liferay.portal.test.rule.AspectJNewEnvTestRule;

import java.io.IOException;

import java.net.Socket;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;

import java.util.EnumSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.aspectj.lang.annotation.Aspect;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SelectorIntrabandTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new CodeCoverageAssertor() {

				@Override
				public void appendAssertClasses(List<Class<?>> assertClasses) {
					assertClasses.add(SelectionKeyRegistrationReference.class);
				}

			},
			AspectJNewEnvTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_selectorIntraband = new SelectorIntraband(_DEFAULT_TIMEOUT);
	}

	@After
	public void tearDown() throws Exception {
		_selectorIntraband.close();
	}

	@Test
	public void testCreateAndDestroy() throws Exception {
		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					SelectorIntraband.class.getName(), Level.INFO)) {

			// Close selector, with log

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Thread wakeUpThread = new Thread(
				new WakeUpRunnable(_selectorIntraband));

			wakeUpThread.start();

			Thread pollingThread = _selectorIntraband.pollingThread;

			Selector selector = _selectorIntraband.selector;

			synchronized (selector) {
				wakeUpThread.interrupt();

				wakeUpThread.join();

				while (pollingThread.getState() != Thread.State.BLOCKED);

				selector.close();
			}

			pollingThread.join();

			Assert.assertEquals(1, logRecords.size());

			String pollingThreadName = pollingThread.getName();

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				pollingThreadName.concat(
					" exiting gracefully on selector closure"),
				logRecord.getMessage());

			// Close selector, without log

			_selectorIntraband = new SelectorIntraband(_DEFAULT_TIMEOUT);

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			wakeUpThread = new Thread(new WakeUpRunnable(_selectorIntraband));

			wakeUpThread.start();

			pollingThread = _selectorIntraband.pollingThread;

			selector = _selectorIntraband.selector;

			synchronized (selector) {
				wakeUpThread.interrupt();

				wakeUpThread.join();

				while (pollingThread.getState() != Thread.State.BLOCKED);

				selector.close();
			}

			pollingThread.join();

			Assert.assertTrue(logRecords.isEmpty());
		}
	}

	@AdviseWith(adviceClasses = {Jdk14LogImplAdvice.class})
	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testReceiveDatagram() throws Exception {
		Pipe readPipe = Pipe.open();
		Pipe writePipe = Pipe.open();

		GatheringByteChannel gatheringByteChannel = writePipe.sink();
		ScatteringByteChannel scatteringByteChannel = readPipe.source();

		SelectionKeyRegistrationReference registrationReference =
			(SelectionKeyRegistrationReference)
				_selectorIntraband.registerChannel(
					writePipe.source(), readPipe.sink());

		long sequenceId = 100;

		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					BaseIntraband.class.getName(), Level.WARNING)) {

			// Receive ACK response, no ACK request, with log

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(
					DatagramHelper.createACKResponseDatagram(sequenceId),
					gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilWarnCalled();
			}

			Assert.assertEquals(1, logRecords.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords.get(0), "Dropped ownerless ACK response ");

			// Receive ACK response, no ACK request, without log

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(
					DatagramHelper.createACKResponseDatagram(sequenceId),
					gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilIsWarnEnableCalled();
			}

			Assert.assertTrue(logRecords.isEmpty());

			// Receive ACK response, with ACK request

			Datagram requestDatagram = Datagram.createRequestDatagram(
				_TYPE, _data);

			DatagramHelper.setAttachment(requestDatagram, new Object());

			RecordCompletionHandler<Object> recordCompletionHandler =
				new RecordCompletionHandler<>();

			DatagramHelper.setCompletionHandler(
				requestDatagram, recordCompletionHandler);

			DatagramHelper.setSequenceId(requestDatagram, sequenceId);
			DatagramHelper.setTimeout(requestDatagram, 10000);

			BaseIntrabandHelper.addResponseWaitingDatagram(
				_selectorIntraband, requestDatagram);

			DatagramHelper.writeTo(
				DatagramHelper.createACKResponseDatagram(sequenceId),
				gatheringByteChannel);

			recordCompletionHandler.waitUntilDelivered();

			Assert.assertSame(
				DatagramHelper.getAttachment(requestDatagram),
				recordCompletionHandler.getAttachment());

			// Receive response, no request, with log

			logRecords = captureHandler.resetLogLevel(Level.WARNING);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(
					Datagram.createResponseDatagram(requestDatagram, _data),
					gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilWarnCalled();
			}

			Assert.assertEquals(1, logRecords.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords.get(0), "Dropped ownerless response ");

			// Receive response, no request, without log

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			Jdk14LogImplAdvice.reset();

			try {
				requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

				DatagramHelper.setSequenceId(requestDatagram, sequenceId);

				DatagramHelper.writeTo(
					Datagram.createResponseDatagram(requestDatagram, _data),
					gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilIsWarnEnableCalled();
			}

			Assert.assertTrue(logRecords.isEmpty());

			// Receive response, with request, with replied completion handler

			requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

			DatagramHelper.setAttachment(requestDatagram, new Object());

			recordCompletionHandler = new RecordCompletionHandler<>();

			DatagramHelper.setCompletionHandler(
				requestDatagram, recordCompletionHandler);

			DatagramHelper.setCompletionTypes(
				requestDatagram, EnumSet.of(CompletionType.REPLIED));
			DatagramHelper.setSequenceId(requestDatagram, sequenceId);
			DatagramHelper.setTimeout(requestDatagram, 10000);

			BaseIntrabandHelper.addResponseWaitingDatagram(
				_selectorIntraband, requestDatagram);

			DatagramHelper.writeTo(
				Datagram.createResponseDatagram(requestDatagram, _data),
				gatheringByteChannel);

			recordCompletionHandler.waitUntilReplied();

			Assert.assertSame(
				DatagramHelper.getAttachment(requestDatagram),
				recordCompletionHandler.getAttachment());

			// Receive response, with request, without replied completion
			// handler, with log

			logRecords = captureHandler.resetLogLevel(Level.WARNING);

			requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

			DatagramHelper.setCompletionTypes(
				requestDatagram, EnumSet.noneOf(CompletionType.class));

			recordCompletionHandler = new RecordCompletionHandler<>();

			DatagramHelper.setCompletionHandler(
				requestDatagram, recordCompletionHandler);

			DatagramHelper.setSequenceId(requestDatagram, sequenceId);
			DatagramHelper.setTimeout(requestDatagram, 10000);

			BaseIntrabandHelper.addResponseWaitingDatagram(
				_selectorIntraband, requestDatagram);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(
					Datagram.createResponseDatagram(requestDatagram, _data),
					gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilWarnCalled();
			}

			Assert.assertEquals(1, logRecords.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords.get(0), "Dropped unconcerned response ");

			// Receive response, with request, without replied completion
			// handler, without log

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

			DatagramHelper.setCompletionTypes(
				requestDatagram, EnumSet.noneOf(CompletionType.class));

			recordCompletionHandler = new RecordCompletionHandler<>();

			DatagramHelper.setCompletionHandler(
				requestDatagram, recordCompletionHandler);

			DatagramHelper.setSequenceId(requestDatagram, sequenceId);
			DatagramHelper.setTimeout(requestDatagram, 10000);

			BaseIntrabandHelper.addResponseWaitingDatagram(
				_selectorIntraband, requestDatagram);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(
					Datagram.createResponseDatagram(requestDatagram, _data),
					gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilIsWarnEnableCalled();
			}

			Assert.assertTrue(logRecords.isEmpty());

			// Receive request, requires ACK, no datagram receive handler,
			// with log

			logRecords = captureHandler.resetLogLevel(Level.WARNING);

			requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

			DatagramHelper.setAckRequest(requestDatagram);
			DatagramHelper.setSequenceId(requestDatagram, sequenceId);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(requestDatagram, gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilWarnCalled();
			}

			Datagram ackResponseDatagram = IntrabandTestUtil.readDatagramFully(
				scatteringByteChannel);

			Assert.assertEquals(
				sequenceId, DatagramHelper.getSequenceId(ackResponseDatagram));
			Assert.assertTrue(
				DatagramHelper.isAckResponse(ackResponseDatagram));

			ByteBuffer dataByteBuffer = ackResponseDatagram.getDataByteBuffer();

			Assert.assertEquals(0, dataByteBuffer.capacity());

			Assert.assertEquals(1, logRecords.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords.get(0), "Dropped ownerless request ");

			// Receive request, no datagram receive handler, without log

			logRecords = captureHandler.resetLogLevel(Level.OFF);

			requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

			DatagramHelper.setSequenceId(requestDatagram, sequenceId);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(requestDatagram, gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilIsWarnEnableCalled();
			}

			Assert.assertTrue(logRecords.isEmpty());

			// Receive request, with datagram receive handler,

			logRecords = captureHandler.resetLogLevel(Level.SEVERE);

			requestDatagram = Datagram.createRequestDatagram(_TYPE, _data);

			DatagramHelper.setSequenceId(requestDatagram, sequenceId);

			RecordDatagramReceiveHandler recordDatagramReceiveHandler =
				new RecordDatagramReceiveHandler();

			_selectorIntraband.registerDatagramReceiveHandler(
				_TYPE, recordDatagramReceiveHandler);

			Jdk14LogImplAdvice.reset();

			try {
				DatagramHelper.writeTo(requestDatagram, gatheringByteChannel);
			}
			finally {
				Jdk14LogImplAdvice.waitUntilErrorCalled();
			}

			Datagram receiveDatagram =
				recordDatagramReceiveHandler.getReceiveDatagram();

			Assert.assertEquals(
				sequenceId, DatagramHelper.getSequenceId(receiveDatagram));
			Assert.assertEquals(_TYPE, receiveDatagram.getType());

			dataByteBuffer = receiveDatagram.getDataByteBuffer();

			Assert.assertArrayEquals(_data, dataByteBuffer.array());
			Assert.assertEquals(1, logRecords.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords.get(0), "Unable to dispatch");

			_unregisterChannels(registrationReference);

			gatheringByteChannel.close();
			scatteringByteChannel.close();
		}
	}

	@Test
	public void testRegisterChannelDuplex() throws Exception {

		// Channel is null

		try {
			_selectorIntraband.registerChannel(null);

			Assert.fail();
		}
		catch (NullPointerException npe) {
			Assert.assertEquals("Channel is null", npe.getMessage());
		}

		// Channel is not of type GatheringByteChannel

		try {
			_selectorIntraband.registerChannel(
				IntrabandTestUtil.<Channel>createProxy(Channel.class));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Channel is not of type GatheringByteChannel",
				iae.getMessage());
		}

		// Channel is not of type ScatteringByteChannel

		try {
			_selectorIntraband.registerChannel(
				IntrabandTestUtil.<Channel>createProxy(
					GatheringByteChannel.class));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Channel is not of type ScatteringByteChannel",
				iae.getMessage());
		}

		// Channel is not of type SelectableChannel

		try {
			_selectorIntraband.registerChannel(
				IntrabandTestUtil.<Channel>createProxy(
					ScatteringByteChannel.class, GatheringByteChannel.class));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Channel is not of type SelectableChannel", iae.getMessage());
		}

		// Channel is not valid for reading

		try {
			_selectorIntraband.registerChannel(
				new MockDuplexSelectableChannel(false, true));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Channel is not valid for reading", iae.getMessage());
		}

		// Channel is not valid for writing

		try {
			_selectorIntraband.registerChannel(
				new MockDuplexSelectableChannel(true, false));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Channel is not valid for writing", iae.getMessage());
		}

		SocketChannel[] peerSocketChannels =
			IntrabandTestUtil.createSocketChannelPeers();

		try {
			SocketChannel socketChannel = peerSocketChannels[0];

			// Interruptted on register

			final Thread mainThread = Thread.currentThread();

			Thread wakeUpThread = new Thread(
				new WakeUpRunnable(_selectorIntraband));

			Thread interruptThread = new Thread() {

				@Override
				public void run() {
					while (mainThread.getState() != Thread.State.WAITING);

					mainThread.interrupt();
				}

			};

			wakeUpThread.start();

			Selector selector = _selectorIntraband.selector;

			synchronized (selector) {
				wakeUpThread.interrupt();
				wakeUpThread.join();

				interruptThread.start();

				try {
					_selectorIntraband.registerChannel(socketChannel);

					Assert.fail();
				}
				catch (IOException ioe) {
					Throwable cause = ioe.getCause();

					Assert.assertTrue(cause instanceof InterruptedException);
				}

				interruptThread.join();
			}

			_selectorIntraband.close();

			_selectorIntraband = new SelectorIntraband(_DEFAULT_TIMEOUT);

			// Normal register

			SelectionKeyRegistrationReference
				selectionKeyRegistrationReference =
					(SelectionKeyRegistrationReference)
						_selectorIntraband.registerChannel(socketChannel);

			Assert.assertNotNull(selectionKeyRegistrationReference);
			Assert.assertSame(
				selectionKeyRegistrationReference.readSelectionKey,
				selectionKeyRegistrationReference.writeSelectionKey);

			SelectionKey selectionKey =
				selectionKeyRegistrationReference.readSelectionKey;

			Assert.assertTrue(selectionKey.isValid());
			Assert.assertEquals(
				SelectionKey.OP_READ, selectionKey.interestOps());
			Assert.assertNotNull(selectionKey.attachment());

			selectionKey.interestOps(
				SelectionKey.OP_READ | SelectionKey.OP_WRITE);

			selector = _selectorIntraband.selector;

			selector.wakeup();

			while (selectionKey.interestOps() != SelectionKey.OP_READ);

			// Concurrent cancelling

			wakeUpThread = new Thread(new WakeUpRunnable(_selectorIntraband));

			wakeUpThread.start();

			synchronized (selector) {
				wakeUpThread.interrupt();
				wakeUpThread.join();

				selectionKey.interestOps(
					SelectionKey.OP_READ | SelectionKey.OP_WRITE);

				SocketChannel peerSocketChannel = peerSocketChannels[1];

				peerSocketChannel.write(ByteBuffer.allocate(1));

				Socket socket = peerSocketChannel.socket();

				socket.shutdownOutput();
			}

			while (selectionKey.isValid());

			// Register after close

			_selectorIntraband.close();

			try {
				_selectorIntraband.registerChannel(socketChannel);

				Assert.fail();
			}
			catch (ClosedIntrabandException cie) {
			}
		}
		finally {
			peerSocketChannels[0].close();
			peerSocketChannels[1].close();
		}
	}

	@Test
	public void testRegisterChannelReadWrite() throws Exception {

		// Scattering byte channel is null

		try {
			_selectorIntraband.registerChannel(null, null);

			Assert.fail();
		}
		catch (NullPointerException npe) {
			Assert.assertEquals(
				"Scattering byte channel is null", npe.getMessage());
		}

		// Gathering byte channel is null

		try {
			_selectorIntraband.registerChannel(
				IntrabandTestUtil.<ScatteringByteChannel>createProxy(
					ScatteringByteChannel.class),
				null);

			Assert.fail();
		}
		catch (NullPointerException npe) {
			Assert.assertEquals(
				"Gathering byte channel is null", npe.getMessage());
		}

		// Scattering byte channel is not of type SelectableChannel

		try {
			_selectorIntraband.registerChannel(
				IntrabandTestUtil.<ScatteringByteChannel>createProxy(
					ScatteringByteChannel.class),
				IntrabandTestUtil.<GatheringByteChannel>createProxy(
					GatheringByteChannel.class));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Scattering byte channel is not of type SelectableChannel",
				iae.getMessage());
		}

		// Gathering byte channel is not of type SelectableChannel

		try {
			_selectorIntraband.registerChannel(
				new MockDuplexSelectableChannel(false, false),
				IntrabandTestUtil.<GatheringByteChannel>createProxy(
					GatheringByteChannel.class));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Gathering byte channel is not of type SelectableChannel",
				iae.getMessage());
		}

		// Scattering byte channel is not valid for reading

		try {
			_selectorIntraband.registerChannel(
				new MockDuplexSelectableChannel(false, true),
				new MockDuplexSelectableChannel(true, true));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Scattering byte channel is not valid for reading",
				iae.getMessage());
		}

		// Gathering byte channel is not valid for writing

		try {
			_selectorIntraband.registerChannel(
				new MockDuplexSelectableChannel(true, true),
				new MockDuplexSelectableChannel(true, false));

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
			Assert.assertEquals(
				"Gathering byte channel is not valid for writing",
				iae.getMessage());
		}

		// Interruptted on register

		Pipe pipe = Pipe.open();

		try (SourceChannel sourceChannel = pipe.source();
			SinkChannel sinkChannel = pipe.sink()) {

			final Thread mainThread = Thread.currentThread();

			Thread wakeUpThread = new Thread(
				new WakeUpRunnable(_selectorIntraband));

			Thread interruptThread = new Thread() {

				@Override
				public void run() {
					while (mainThread.getState() != Thread.State.WAITING);

					mainThread.interrupt();
				}

			};

			wakeUpThread.start();

			Selector selector = _selectorIntraband.selector;

			synchronized (selector) {
				wakeUpThread.interrupt();
				wakeUpThread.join();

				interruptThread.start();

				try {
					_selectorIntraband.registerChannel(
						sourceChannel, sinkChannel);

					Assert.fail();
				}
				catch (IOException ioe) {
					Throwable cause = ioe.getCause();

					Assert.assertTrue(cause instanceof InterruptedException);
				}

				interruptThread.join();
			}

			_selectorIntraband.close();

			// Normal register

			_selectorIntraband = new SelectorIntraband(_DEFAULT_TIMEOUT);

			SelectionKeyRegistrationReference
				selectionKeyRegistrationReference =
					(SelectionKeyRegistrationReference)
						_selectorIntraband.registerChannel(
							sourceChannel, sinkChannel);

			Assert.assertNotNull(selectionKeyRegistrationReference);

			SelectionKey readSelectionKey =
				selectionKeyRegistrationReference.readSelectionKey;

			Assert.assertTrue(readSelectionKey.isValid());
			Assert.assertEquals(
				SelectionKey.OP_READ, readSelectionKey.interestOps());
			Assert.assertNotNull(readSelectionKey.attachment());

			SelectionKey writeSelectionKey =
				selectionKeyRegistrationReference.writeSelectionKey;

			Assert.assertTrue(writeSelectionKey.isValid());
			Assert.assertEquals(0, writeSelectionKey.interestOps());
			Assert.assertNotNull(writeSelectionKey.attachment());
			Assert.assertSame(
				readSelectionKey.attachment(), writeSelectionKey.attachment());

			writeSelectionKey.interestOps(SelectionKey.OP_WRITE);

			selector = _selectorIntraband.selector;

			selector.wakeup();

			while (writeSelectionKey.interestOps() != 0);

			_unregisterChannels(selectionKeyRegistrationReference);

			// Register after close

			_selectorIntraband.close();

			try {
				_selectorIntraband.registerChannel(sourceChannel, sinkChannel);

				Assert.fail();
			}
			catch (ClosedIntrabandException cie) {
			}
		}
	}

	@AdviseWith(adviceClasses = {Jdk14LogImplAdvice.class})
	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testSendDatagramWithCallback() throws Exception {

		// Submitted callback

		Pipe readPipe = Pipe.open();
		Pipe writePipe = Pipe.open();

		GatheringByteChannel gatheringByteChannel = writePipe.sink();
		ScatteringByteChannel scatteringByteChannel = readPipe.source();

		RegistrationReference registrationReference =
			_selectorIntraband.registerChannel(
				writePipe.source(), readPipe.sink());

		Object attachment = new Object();

		RecordCompletionHandler<Object> recordCompletionHandler =
			new RecordCompletionHandler<>();

		_selectorIntraband.sendDatagram(
			registrationReference, Datagram.createRequestDatagram(_TYPE, _data),
			attachment, EnumSet.of(CompletionType.SUBMITTED),
			recordCompletionHandler);

		Datagram receiveDatagram = IntrabandTestUtil.readDatagramFully(
			scatteringByteChannel);

		recordCompletionHandler.waitUntilSubmitted();

		Assert.assertSame(attachment, recordCompletionHandler.getAttachment());
		Assert.assertEquals(_TYPE, receiveDatagram.getType());

		ByteBuffer dataByteBuffer = receiveDatagram.getDataByteBuffer();

		Assert.assertArrayEquals(_data, dataByteBuffer.array());

		try (CaptureHandler captureHandler1 =
				JDKLoggerTestUtil.configureJDKLogger(
					BaseIntraband.class.getName(), Level.WARNING)) {

			// Callback timeout, with log

			List<LogRecord> logRecords = captureHandler1.getLogRecords();

			recordCompletionHandler = new RecordCompletionHandler<>();

			_selectorIntraband.sendDatagram(
				registrationReference,
				Datagram.createRequestDatagram(_TYPE, _data), attachment,
				EnumSet.of(CompletionType.DELIVERED), recordCompletionHandler,
				10, TimeUnit.MILLISECONDS);

			Selector selector = _selectorIntraband.selector;

			recordCompletionHandler.waitUntilTimeouted(selector);

			Assert.assertSame(
				attachment, recordCompletionHandler.getAttachment());
			Assert.assertEquals(1, logRecords.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords.get(0), "Removed timeout response waiting datagram");

			// Callback timeout, without log

			logRecords = captureHandler1.resetLogLevel(Level.OFF);

			recordCompletionHandler = new RecordCompletionHandler<>();

			_selectorIntraband.sendDatagram(
				registrationReference,
				Datagram.createRequestDatagram(_TYPE, _data), attachment,
				EnumSet.of(CompletionType.DELIVERED), recordCompletionHandler,
				10, TimeUnit.MILLISECONDS);

			recordCompletionHandler.waitUntilTimeouted(selector);

			Assert.assertSame(
				attachment, recordCompletionHandler.getAttachment());
			Assert.assertTrue(logRecords.isEmpty());
		}

		// Callback timeout, completion handler causes NPE

		try (CaptureHandler captureHandler1 =
				JDKLoggerTestUtil.configureJDKLogger(
					SelectorIntraband.class.getName(), Level.SEVERE)) {

			List<LogRecord> logRecords1 = captureHandler1.getLogRecords();

			recordCompletionHandler = new RecordCompletionHandler<Object>() {

				@Override
				public void timedOut(Object attachment) {
					super.timedOut(attachment);

					throw new NullPointerException();
				}

			};

			Jdk14LogImplAdvice.reset();

			Selector selector = _selectorIntraband.selector;

			Datagram datagram = Datagram.createRequestDatagram(_TYPE, _data);

			try {
				_selectorIntraband.sendDatagram(
					registrationReference, datagram, attachment,
					EnumSet.of(CompletionType.DELIVERED),
					recordCompletionHandler, 10, TimeUnit.MILLISECONDS);
			}
			finally {
				try (CaptureHandler captureHandler2 =
						JDKLoggerTestUtil.configureJDKLogger(
							BaseIntraband.class.getName(), Level.WARNING)) {

					recordCompletionHandler.waitUntilTimeouted(selector);

					List<LogRecord> logRecords2 =
						captureHandler2.getLogRecords();

					Assert.assertEquals(1, logRecords2.size());

					LogRecord logRecord = logRecords2.get(0);

					Assert.assertEquals(
						"Removed timeout response waiting datagram " + datagram,
						logRecord.getMessage());
				}

				Jdk14LogImplAdvice.waitUntilErrorCalled();
			}

			Assert.assertFalse(selector.isOpen());
			Assert.assertEquals(1, logRecords1.size());

			IntrabandTestUtil.assertMessageStartWith(
				logRecords1.get(0),
				SelectorIntraband.class +
					".threadFactory-1 exiting exceptionally");

			gatheringByteChannel.close();
			scatteringByteChannel.close();
		}
	}

	@Test
	public void testSendDatagramWithoutCallback() throws Exception {

		// Single datagram sending

		Pipe readPipe = Pipe.open();
		Pipe writePipe = Pipe.open();

		try (GatheringByteChannel gatheringByteChannel = writePipe.sink();
			ScatteringByteChannel scatteringByteChannel = readPipe.source()) {

			SelectionKeyRegistrationReference registrationReference =
				(SelectionKeyRegistrationReference)
					_selectorIntraband.registerChannel(
						writePipe.source(), readPipe.sink());

			Thread wakeUpThread = new Thread(
				new WakeUpRunnable(_selectorIntraband));

			wakeUpThread.start();

			Selector selector = _selectorIntraband.selector;

			synchronized (selector) {
				wakeUpThread.interrupt();
				wakeUpThread.join();

				Datagram requestDatagram = Datagram.createRequestDatagram(
					_TYPE, _data);

				_selectorIntraband.sendDatagram(
					registrationReference, requestDatagram);

				SelectionKey writeSelectionKey =
					registrationReference.writeSelectionKey;

				ChannelContext channelContext =
					(ChannelContext)writeSelectionKey.attachment();

				Queue<Datagram> sendingQueue = channelContext.getSendingQueue();

				Assert.assertEquals(1, sendingQueue.size());
				Assert.assertSame(requestDatagram, sendingQueue.peek());
			}

			Datagram receiveDatagram = IntrabandTestUtil.readDatagramFully(
				scatteringByteChannel);

			Assert.assertEquals(_TYPE, receiveDatagram.getType());

			ByteBuffer dataByteBuffer = receiveDatagram.getDataByteBuffer();

			Assert.assertArrayEquals(_data, dataByteBuffer.array());

			// Two datagrams continuous sending

			Datagram requestDatagram1 = Datagram.createRequestDatagram(
				_TYPE, _data);
			Datagram requestDatagram2 = Datagram.createRequestDatagram(
				_TYPE, _data);

			wakeUpThread = new Thread(new WakeUpRunnable(_selectorIntraband));

			wakeUpThread.start();

			synchronized (selector) {
				wakeUpThread.interrupt();
				wakeUpThread.join();

				_selectorIntraband.sendDatagram(
					registrationReference, requestDatagram1);
				_selectorIntraband.sendDatagram(
					registrationReference, requestDatagram2);

				SelectionKey writeSelectionKey =
					registrationReference.writeSelectionKey;

				ChannelContext channelContext =
					(ChannelContext)writeSelectionKey.attachment();

				Queue<Datagram> sendingQueue = channelContext.getSendingQueue();

				Assert.assertEquals(2, sendingQueue.size());

				Datagram[] datagrams = sendingQueue.toArray(new Datagram[2]);

				Assert.assertSame(requestDatagram1, datagrams[0]);
				Assert.assertSame(requestDatagram2, datagrams[1]);
			}

			Datagram receiveDatagram1 = IntrabandTestUtil.readDatagramFully(
				scatteringByteChannel);

			Assert.assertEquals(_TYPE, receiveDatagram1.getType());

			dataByteBuffer = receiveDatagram1.getDataByteBuffer();

			Assert.assertArrayEquals(_data, dataByteBuffer.array());

			Datagram receiveDatagram2 = IntrabandTestUtil.readDatagramFully(
				scatteringByteChannel);

			Assert.assertEquals(_TYPE, receiveDatagram2.getType());

			dataByteBuffer = receiveDatagram2.getDataByteBuffer();

			Assert.assertArrayEquals(_data, dataByteBuffer.array());

			// Two datagrams delay sending

			requestDatagram1 = Datagram.createRequestDatagram(_TYPE, _data);
			requestDatagram2 = Datagram.createRequestDatagram(_TYPE, _data);

			wakeUpThread = new Thread(new WakeUpRunnable(_selectorIntraband));

			wakeUpThread.start();

			SelectionKey writeSelectionKey =
				registrationReference.writeSelectionKey;

			ChannelContext channelContext =
				(ChannelContext)writeSelectionKey.attachment();

			Queue<Datagram> sendingQueue = channelContext.getSendingQueue();

			while ((writeSelectionKey.interestOps() & SelectionKey.OP_WRITE) !=
				0);

			synchronized (writeSelectionKey) {
				synchronized (selector) {
					wakeUpThread.interrupt();
					wakeUpThread.join();

					_selectorIntraband.sendDatagram(
						registrationReference, requestDatagram1);

					Assert.assertEquals(1, sendingQueue.size());
					Assert.assertSame(requestDatagram1, sendingQueue.peek());
				}

				receiveDatagram1 = IntrabandTestUtil.readDatagramFully(
					scatteringByteChannel);

				Assert.assertEquals(_TYPE, receiveDatagram1.getType());

				dataByteBuffer = receiveDatagram1.getDataByteBuffer();

				Assert.assertArrayEquals(_data, dataByteBuffer.array());

				Thread pollingThread = _selectorIntraband.pollingThread;

				while (pollingThread.getState() == Thread.State.RUNNABLE);

				_selectorIntraband.sendDatagram(
					registrationReference, requestDatagram2);

				Assert.assertEquals(1, sendingQueue.size());
				Assert.assertSame(requestDatagram2, sendingQueue.peek());
			}

			receiveDatagram2 = IntrabandTestUtil.readDatagramFully(
				scatteringByteChannel);

			Assert.assertEquals(_TYPE, receiveDatagram2.getType());

			dataByteBuffer = receiveDatagram2.getDataByteBuffer();

			Assert.assertArrayEquals(_data, dataByteBuffer.array());

			// Huge datagram sending

			int hugeBufferSize = 1024 * 1024 * 10;

			ByteBuffer hugeBuffer = ByteBuffer.allocate(hugeBufferSize);

			for (int i = 0; i < hugeBufferSize; i++) {
				hugeBuffer.put(i, (byte)i);
			}

			_selectorIntraband.sendDatagram(
				registrationReference,
				Datagram.createRequestDatagram(_TYPE, hugeBuffer));

			receiveDatagram = DatagramHelper.createReceiveDatagram();

			channelContext = (ChannelContext)writeSelectionKey.attachment();

			int count = 0;

			while (!DatagramHelper.readFrom(
						receiveDatagram, scatteringByteChannel)) {

				count++;
			}

			Assert.assertTrue(count > 0);

			sendingQueue = channelContext.getSendingQueue();

			Assert.assertTrue(sendingQueue.isEmpty());

			dataByteBuffer = receiveDatagram.getDataByteBuffer();

			Assert.assertArrayEquals(
				hugeBuffer.array(), dataByteBuffer.array());

			_unregisterChannels(registrationReference);
		}
	}

	@Aspect
	public static class Jdk14LogImplAdvice {

		public static volatile CountDownLatch errorCalledCountDownLatch =
			new CountDownLatch(1);
		public static volatile CountDownLatch
			isWarnEnabledCalledCountDownLatch = new CountDownLatch(1);
		public static volatile CountDownLatch warnCalledCountDownLatch =
			new CountDownLatch(1);

		public static void reset() {
			errorCalledCountDownLatch = new CountDownLatch(1);
			isWarnEnabledCalledCountDownLatch = new CountDownLatch(1);
			warnCalledCountDownLatch = new CountDownLatch(1);
		}

		public static void waitUntilErrorCalled() throws InterruptedException {
			errorCalledCountDownLatch.await();
		}

		public static void waitUntilIsWarnEnableCalled()
			throws InterruptedException {

			isWarnEnabledCalledCountDownLatch.await();
		}

		public static void waitUntilWarnCalled() throws InterruptedException {
			warnCalledCountDownLatch.await();
		}

		@org.aspectj.lang.annotation.After(
			"execution(* com.liferay.portal.kernel.log.Jdk14LogImpl.error(" +
				"Object, Throwable))"
		)
		public void error() {
			errorCalledCountDownLatch.countDown();
		}

		@org.aspectj.lang.annotation.After(
			"execution(* com.liferay.portal.kernel.log.Jdk14LogImpl." +
				"isWarnEnabled())"
		)
		public void isWarnEnabled() {
			isWarnEnabledCalledCountDownLatch.countDown();
		}

		@org.aspectj.lang.annotation.After(
			"execution(* com.liferay.portal.kernel.log.Jdk14LogImpl.warn(" +
				"Object))"
		)
		public void warn1() {
			warnCalledCountDownLatch.countDown();
		}

		@org.aspectj.lang.annotation.After(
			"execution(* com.liferay.portal.kernel.log.Jdk14LogImpl.warn(" +
				"Object, Throwable))"
		)
		public void warn2() {
			warnCalledCountDownLatch.countDown();
		}

	}

	private void _unregisterChannels(
			SelectionKeyRegistrationReference registrationReference)
		throws Exception {

		registrationReference.cancelRegistration();

		SelectorIntraband defaultIntraband =
			(SelectorIntraband)registrationReference.getIntraband();

		Selector selector = defaultIntraband.selector;

		Set<SelectionKey> keys = selector.keys();

		while (!keys.isEmpty()) {
			selector.wakeup();
		}

		SelectionKey readSelectionKey = registrationReference.readSelectionKey;
		SelectionKey writeSelectionKey =
			registrationReference.writeSelectionKey;

		try (SelectableChannel readSelectableChannel =
				readSelectionKey.channel();
			SelectableChannel writeSelectableChannel =
				writeSelectionKey.channel()) {

			while (readSelectableChannel.keyFor(selector) != null);
			while (writeSelectableChannel.keyFor(selector) != null);
		}
	}

	private static final String _DATA_STRING =
		SelectorIntrabandTest.class.getName();

	private static final long _DEFAULT_TIMEOUT = Time.SECOND;

	private static final byte _TYPE = 1;

	private final byte[] _data = _DATA_STRING.getBytes(
		Charset.defaultCharset());
	private SelectorIntraband _selectorIntraband;

	private static class MockDuplexSelectableChannel
		extends SelectableChannel
		implements GatheringByteChannel, ScatteringByteChannel {

		public MockDuplexSelectableChannel(boolean readable, boolean writable) {
			_readable = readable;
			_writable = writable;
		}

		@Override
		public Object blockingLock() {
			throw new UnsupportedOperationException();
		}

		@Override
		public SelectableChannel configureBlocking(boolean block) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isBlocking() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isRegistered() {
			throw new UnsupportedOperationException();
		}

		@Override
		public SelectionKey keyFor(Selector selector) {
			throw new UnsupportedOperationException();
		}

		@Override
		public SelectorProvider provider() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int read(ByteBuffer byteBuffer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long read(ByteBuffer[] byteBuffers) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long read(ByteBuffer[] byteBuffers, int offset, int length) {
			throw new UnsupportedOperationException();
		}

		@Override
		public SelectionKey register(
			Selector selector, int ops, Object attachment) {

			throw new UnsupportedOperationException();
		}

		@Override
		public int validOps() {
			int ops = 0;

			if (_readable) {
				ops |= SelectionKey.OP_READ;
			}

			if (_writable) {
				ops |= SelectionKey.OP_WRITE;
			}

			return ops;
		}

		@Override
		public int write(ByteBuffer byteBuffer) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long write(ByteBuffer[] byteBuffers) {
			throw new UnsupportedOperationException();
		}

		@Override
		public long write(ByteBuffer[] byteBuffers, int offset, int length) {
			throw new UnsupportedOperationException();
		}

		@Override
		protected void implCloseChannel() {
			throw new UnsupportedOperationException();
		}

		private final boolean _readable;
		private final boolean _writable;

	}

	private static class WakeUpRunnable implements Runnable {

		public WakeUpRunnable(SelectorIntraband selectorIntraband) {
			_selectorIntraband = selectorIntraband;
		}

		@Override
		public void run() {
			Thread currentThread = Thread.currentThread();

			while (!currentThread.isInterrupted()) {
				_selectorIntraband.selector.wakeup();
			}
		}

		private final SelectorIntraband _selectorIntraband;

	}

}