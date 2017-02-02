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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalFilePermission;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import java.util.List;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class FileUtil {

	public static String appendParentheticalSuffix(
		String fileName, String suffix) {

		return getFile().appendParentheticalSuffix(fileName, suffix);
	}

	public static String appendSuffix(String fileName, String suffix) {
		return getFile().appendSuffix(fileName, suffix);
	}

	public static void copyDirectory(File source, File destination)
		throws IOException {

		PortalFilePermission.checkCopy(_getPath(source), _getPath(destination));

		getFile().copyDirectory(source, destination);
	}

	public static void copyDirectory(
			String sourceDirName, String destinationDirName)
		throws IOException {

		PortalFilePermission.checkCopy(sourceDirName, destinationDirName);

		getFile().copyDirectory(sourceDirName, destinationDirName);
	}

	public static void copyFile(File source, File destination)
		throws IOException {

		PortalFilePermission.checkCopy(_getPath(source), _getPath(destination));

		getFile().copyFile(source, destination);
	}

	public static void copyFile(File source, File destination, boolean lazy)
		throws IOException {

		PortalFilePermission.checkCopy(_getPath(source), _getPath(destination));

		getFile().copyFile(source, destination, lazy);
	}

	public static void copyFile(String source, String destination)
		throws IOException {

		PortalFilePermission.checkCopy(source, destination);

		getFile().copyFile(source, destination);
	}

	public static void copyFile(String source, String destination, boolean lazy)
		throws IOException {

		PortalFilePermission.checkCopy(source, destination);

		getFile().copyFile(source, destination, lazy);
	}

	public static File createTempFile() {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFile();
	}

	public static File createTempFile(byte[] bytes) throws IOException {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFile(bytes);
	}

	public static File createTempFile(InputStream is) throws IOException {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFile(is);
	}

	public static File createTempFile(String extension) {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFile(extension);
	}

	public static File createTempFile(String prefix, String extension) {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFile(prefix, extension);
	}

	public static String createTempFileName() {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFileName();
	}

	public static String createTempFileName(String extension) {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFileName(extension);
	}

	public static String createTempFileName(String prefix, String extension) {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFileName(prefix, extension);
	}

	public static File createTempFolder() throws IOException {
		PortalFilePermission.checkWrite(
			SystemProperties.get(SystemProperties.TMP_DIR));

		return getFile().createTempFolder();
	}

	public static String decodeSafeFileName(String fileName) {
		return getFile().decodeSafeFileName(fileName);
	}

	public static boolean delete(File file) {
		PortalFilePermission.checkDelete(_getPath(file));

		return getFile().delete(file);
	}

	public static boolean delete(String file) {
		PortalFilePermission.checkDelete(file);

		return getFile().delete(file);
	}

	public static void deltree(File directory) {
		PortalFilePermission.checkDelete(_getPath(directory));

		getFile().deltree(directory);
	}

	public static void deltree(String directory) {
		PortalFilePermission.checkDelete(directory);

		getFile().deltree(directory);
	}

	public static String encodeSafeFileName(String fileName) {
		return getFile().encodeSafeFileName(fileName);
	}

	public static boolean exists(File file) {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().exists(file);
	}

	public static boolean exists(String fileName) {
		PortalFilePermission.checkRead(fileName);

		return getFile().exists(fileName);
	}

	/**
	 * Extracts the text from the input stream and file name.
	 *
	 * @param  is the file's input stream
	 * @param  fileName the file's full name or extension (e.g., "Test.doc" or
	 *         ".doc")
	 * @return the extracted text if it is a supported format or an empty string
	 *         if it is an unsupported format
	 */
	public static String extractText(InputStream is, String fileName) {
		return getFile().extractText(is, fileName);
	}

	public static String extractText(
		InputStream is, String fileName, int maxStringLength) {

		return getFile().extractText(is, fileName, maxStringLength);
	}

	public static String[] find(
		String directory, String includes, String excludes) {

		PortalFilePermission.checkRead(directory);

		return getFile().find(directory, includes, excludes);
	}

	public static String getAbsolutePath(File file) {
		return getFile().getAbsolutePath(file);
	}

	public static byte[] getBytes(Class<?> clazz, String fileName)
		throws Exception {

		return getFile().getBytes(clazz, fileName);
	}

	public static byte[] getBytes(File file) throws IOException {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().getBytes(file);
	}

	public static byte[] getBytes(InputStream is) throws IOException {
		return getFile().getBytes(is);
	}

	public static byte[] getBytes(InputStream is, int bufferSize)
		throws IOException {

		return getFile().getBytes(is);
	}

	public static byte[] getBytes(
			InputStream is, int bufferSize, boolean cleanUpStream)
		throws IOException {

		return getFile().getBytes(is, bufferSize, cleanUpStream);
	}

	public static String getExtension(String fileName) {
		return getFile().getExtension(fileName);
	}

	public static com.liferay.portal.kernel.util.File getFile() {
		PortalRuntimePermission.checkGetBeanProperty(FileUtil.class);

		return _file;
	}

	public static String getMD5Checksum(File file) throws IOException {
		return getFile().getMD5Checksum(file);
	}

	public static String getPath(String fullFileName) {
		return getFile().getPath(fullFileName);
	}

	public static String getShortFileName(String fullFileName) {
		return getFile().getShortFileName(fullFileName);
	}

	public static boolean isAscii(File file) throws IOException {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().isAscii(file);
	}

	public static boolean isSameContent(File file, byte[] bytes, int length) {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().isSameContent(file, bytes, length);
	}

	public static boolean isSameContent(File file, String s) {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().isSameContent(file, s);
	}

	public static String[] listDirs(File file) {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().listDirs(file);
	}

	public static String[] listDirs(String fileName) {
		PortalFilePermission.checkRead(fileName);

		return getFile().listDirs(fileName);
	}

	public static String[] listFiles(File file) {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().listFiles(file);
	}

	public static String[] listFiles(String fileName) {
		PortalFilePermission.checkRead(fileName);

		return getFile().listFiles(fileName);
	}

	public static void mkdirs(File file) throws IOException {
		PortalFilePermission.checkCopy(_getPath(file), _getPath(file));

		getFile().mkdirs(file);
	}

	public static void mkdirs(String pathName) {
		PortalFilePermission.checkCopy(pathName, pathName);

		getFile().mkdirs(pathName);
	}

	public static boolean move(File source, File destination) {
		PortalFilePermission.checkMove(_getPath(source), _getPath(destination));

		return getFile().move(source, destination);
	}

	public static boolean move(
		String sourceFileName, String destinationFileName) {

		PortalFilePermission.checkMove(sourceFileName, destinationFileName);

		return getFile().move(sourceFileName, destinationFileName);
	}

	public static String read(File file) throws IOException {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().read(file);
	}

	public static String read(File file, boolean raw) throws IOException {
		PortalFilePermission.checkRead(_getPath(file));

		return getFile().read(file, raw);
	}

	public static String read(String fileName) throws IOException {
		PortalFilePermission.checkRead(fileName);

		return getFile().read(fileName);
	}

	public static String replaceSeparator(String fileName) {
		return getFile().replaceSeparator(fileName);
	}

	public static File[] sortFiles(File[] files) {
		return getFile().sortFiles(files);
	}

	public static String stripExtension(String fileName) {
		return getFile().stripExtension(fileName);
	}

	public static String stripParentheticalSuffix(String fileName) {
		return getFile().stripParentheticalSuffix(fileName);
	}

	public static List<String> toList(Reader reader) {
		return getFile().toList(reader);
	}

	public static List<String> toList(String fileName) {
		return getFile().toList(fileName);
	}

	public static Properties toProperties(FileInputStream fis) {
		return getFile().toProperties(fis);
	}

	public static Properties toProperties(String fileName) {
		PortalFilePermission.checkRead(fileName);

		return getFile().toProperties(fileName);
	}

	public static void touch(File file) throws IOException {
		PortalFilePermission.checkWrite(_getPath(file));

		getFile().touch(file);
	}

	public static void touch(String fileName) throws IOException {
		PortalFilePermission.checkWrite(fileName);

		getFile().touch(fileName);
	}

	public static void unzip(File source, File destination) {
		PortalFilePermission.checkCopy(_getPath(source), _getPath(destination));

		getFile().unzip(source, destination);
	}

	public static void write(File file, byte[] bytes) throws IOException {
		write(file, bytes, false);
	}

	public static void write(File file, byte[] bytes, boolean append)
		throws IOException {

		PortalFilePermission.checkWrite(_getPath(file));

		getFile().write(file, bytes, append);
	}

	public static void write(File file, byte[] bytes, int offset, int length)
		throws IOException {

		write(file, bytes, offset, length, false);
	}

	public static void write(
			File file, byte[] bytes, int offset, int length, boolean append)
		throws IOException {

		PortalFilePermission.checkWrite(_getPath(file));

		getFile().write(file, bytes, offset, length, append);
	}

	public static void write(File file, InputStream is) throws IOException {
		PortalFilePermission.checkWrite(_getPath(file));

		getFile().write(file, is);
	}

	public static void write(File file, String s) throws IOException {
		PortalFilePermission.checkWrite(_getPath(file));

		getFile().write(file, s);
	}

	public static void write(File file, String s, boolean lazy)
		throws IOException {

		PortalFilePermission.checkWrite(_getPath(file));

		getFile().write(file, s, lazy);
	}

	public static void write(File file, String s, boolean lazy, boolean append)
		throws IOException {

		PortalFilePermission.checkWrite(_getPath(file));

		getFile().write(file, s, lazy, append);
	}

	public static void write(String fileName, byte[] bytes) throws IOException {
		PortalFilePermission.checkWrite(fileName);

		getFile().write(fileName, bytes);
	}

	public static void write(String fileName, InputStream is)
		throws IOException {

		PortalFilePermission.checkWrite(fileName);

		getFile().write(fileName, is);
	}

	public static void write(String fileName, String s) throws IOException {
		PortalFilePermission.checkWrite(fileName);

		getFile().write(fileName, s);
	}

	public static void write(String fileName, String s, boolean lazy)
		throws IOException {

		PortalFilePermission.checkWrite(fileName);

		getFile().write(fileName, s, lazy);
	}

	public static void write(
			String fileName, String s, boolean lazy, boolean append)
		throws IOException {

		PortalFilePermission.checkWrite(fileName);

		getFile().write(fileName, s, lazy, append);
	}

	public static void write(String pathName, String fileName, String s)
		throws IOException {

		PortalFilePermission.checkWrite(pathName);

		getFile().write(pathName, fileName, s);
	}

	public static void write(
			String pathName, String fileName, String s, boolean lazy)
		throws IOException {

		PortalFilePermission.checkWrite(pathName);

		getFile().write(pathName, fileName, s, lazy);
	}

	public static void write(
			String pathName, String fileName, String s, boolean lazy,
			boolean append)
		throws IOException {

		PortalFilePermission.checkWrite(pathName);

		getFile().write(pathName, fileName, s, lazy, append);
	}

	public void setFile(com.liferay.portal.kernel.util.File file) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_file = file;
	}

	private static String _getPath(File file) {
		if (file == null) {
			return null;
		}

		return file.getPath();
	}

	private static com.liferay.portal.kernel.util.File _file;

}