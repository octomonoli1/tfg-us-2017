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

package com.liferay.exportimport.lar;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.exportimport.internal.util.ExportImportPermissionUtil;
import com.liferay.exportimport.kernel.lar.ExportImportClassedModelUtil;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerControl;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.lar.UserIdStrategy;
import com.liferay.exportimport.kernel.xstream.XStreamAlias;
import com.liferay.exportimport.kernel.xstream.XStreamConverter;
import com.liferay.exportimport.kernel.xstream.XStreamType;
import com.liferay.exportimport.xstream.ConverterAdapter;
import com.liferay.exportimport.xstream.XStreamStagedModelTypeHierarchyPermission;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.NoSuchTeamException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lock.Lock;
import com.liferay.portal.kernel.lock.LockManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletModel;
import com.liferay.portal.kernel.model.ResourcedModel;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.xstream.configurator.XStreamConfigurator;
import com.liferay.xstream.configurator.XStreamConfiguratorRegistryUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.ClassLoaderReference;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jodd.bean.BeanUtil;

/**
 * <p>
 * Holds context information that is used during exporting and importing portlet
 * data.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Bruno Farache
 * @author Alexander Chow
 * @author Mate Thurzo
 */
public class PortletDataContextImpl implements PortletDataContext {

	public PortletDataContextImpl(LockManager lockManager) {
		initXStream();

		_lockManager = lockManager;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#exportAssetCategories(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void addAssetCategories(Class<?> clazz, long classPK) {
	}

	@Override
	public void addAssetCategories(
		String className, long classPK, long[] assetCategoryIds) {

		_assetCategoryIdsMap.put(
			getPrimaryKeyString(className, classPK), assetCategoryIds);
	}

	public void addAssetLinks(Class<?> clazz, long classPK) {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			clazz.getName(), classPK);

		if (assetEntry == null) {
			return;
		}

		List<AssetLink> assetLinks = AssetLinkLocalServiceUtil.getLinks(
			assetEntry.getEntryId());

		for (AssetLink assetLink : assetLinks) {
			_assetLinkIds.add(assetLink.getLinkId());
		}
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             BaseStagedModelDataHandler#exportAssetTags(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void addAssetTags(Class<?> clazz, long classPK) {
		String[] tagNames = AssetTagLocalServiceUtil.getTagNames(
			clazz.getName(), classPK);

		_assetTagNamesMap.put(getPrimaryKeyString(clazz, classPK), tagNames);
	}

	@Override
	public void addAssetTags(
		String className, long classPK, String[] assetTagNames) {

		_assetTagNamesMap.put(
			getPrimaryKeyString(className, classPK), assetTagNames);
	}

	@Override
	public void addClassedModel(
			Element element, String path, ClassedModel classedModel)
		throws PortalException {

		addClassedModel(
			element, path, classedModel, classedModel.getModelClass());
	}

	@Override
	public void addClassedModel(
			Element element, String path, ClassedModel classedModel,
			Class<?> clazz)
		throws PortalException {

		element.addAttribute("path", path);

		populateClassNameAttribute(classedModel, element);

		if (!hasPrimaryKey(String.class, path)) {
			if (classedModel instanceof AuditedModel) {
				AuditedModel auditedModel = (AuditedModel)classedModel;

				auditedModel.setUserUuid(auditedModel.getUserUuid());
			}

			if (isResourceMain(classedModel)) {
				long classPK = ExportImportClassedModelUtil.getClassPK(
					classedModel);

				addAssetLinks(clazz, classPK);
				addAssetPriority(element, clazz, classPK);
				addExpando(element, path, classedModel, clazz);
				addLocks(clazz, String.valueOf(classPK));
				addPermissions(clazz, classPK);
			}

			_references.add(getReferenceKey(classedModel));
		}

		addZipEntry(path, classedModel);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#exportComments(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void addComments(Class<?> clazz, long classPK) {
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#exportComments(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void addComments(
		String className, long classPK, List<MBMessage> messages) {
	}

	/**
	 * @see #isWithinDateRange(Date)
	 */
	@Override
	public void addDateRangeCriteria(
		DynamicQuery dynamicQuery, String propertyName) {

		Criterion criterion = getDateRangeCriteria(propertyName);

		if (criterion == null) {
			return;
		}

		dynamicQuery.add(criterion);
	}

	@Override
	public void addDeletionSystemEventStagedModelTypes(
		StagedModelType... stagedModelTypes) {

		for (StagedModelType stagedModelType : stagedModelTypes) {
			_deletionSystemEventModelTypes.add(stagedModelType);
		}
	}

	@Override
	public void addExpando(
		Element element, String path, ClassedModel classedModel) {

		addExpando(element, path, classedModel, classedModel.getModelClass());
	}

	@Override
	public void addLocks(Class<?> clazz, String key) throws PortalException {
		if (!_locksMap.containsKey(getPrimaryKeyString(clazz, key)) &&
			_lockManager.isLocked(clazz.getName(), key)) {

			Lock lock = _lockManager.getLock(clazz.getName(), key);

			addLocks(clazz.getName(), key, lock);
		}
	}

	@Override
	public void addLocks(String className, String key, Lock lock) {
		_locksMap.put(getPrimaryKeyString(className, key), lock);
	}

	@Override
	public void addPermissions(Class<?> clazz, long classPK) {
		addPermissions(clazz.getName(), classPK);
	}

	@Override
	public void addPermissions(String resourceName, long resourcePK) {
		if (!MapUtil.getBoolean(
				_parameterMap, PortletDataHandlerKeys.PERMISSIONS)) {

			return;
		}

		Map<Long, Set<String>> roleIdsToActionIds =
			ExportImportPermissionUtil.getRoleIdsToActionIds(
				_companyId, resourceName, resourcePK);

		List<KeyValuePair> permissions = new ArrayList<>();

		for (Map.Entry<Long, Set<String>> entry :
				roleIdsToActionIds.entrySet()) {

			long roleId = entry.getKey();
			Set<String> availableActionIds = entry.getValue();

			Role role = RoleLocalServiceUtil.fetchRole(roleId);

			if (role == null) {
				continue;
			}

			String roleName = role.getName();

			if (role.isTeam()) {
				try {
					roleName = ExportImportPermissionUtil.getTeamRoleName(
						role.getDescriptiveName());
				}
				catch (PortalException pe) {
					_log.error(pe, pe);
				}
			}

			KeyValuePair permission = new KeyValuePair(
				roleName, StringUtil.merge(availableActionIds));

			permissions.add(permission);
		}

		if (permissions.isEmpty()) {
			return;
		}

		_permissionsMap.put(
			getPrimaryKeyString(resourceName, resourcePK), permissions);
	}

	@Override
	public void addPermissions(
		String resourceName, long resourcePK, List<KeyValuePair> permissions) {

		_permissionsMap.put(
			getPrimaryKeyString(resourceName, resourcePK), permissions);
	}

	@Override
	public void addPortalPermissions() {
		addPermissions(PortletKeys.PORTAL, getCompanyId());
	}

	@Override
	public void addPortletPermissions(String resourceName)
		throws PortalException {

		long groupId = getGroupId();

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isStagingGroup()) {
			if (group.isStagedRemotely()) {
				groupId = group.getLiveGroupId();
			}
			else {
				return;
			}
		}

		addPermissions(resourceName, groupId);
	}

	@Override
	public boolean addPrimaryKey(Class<?> clazz, String primaryKey) {
		boolean value = hasPrimaryKey(clazz, primaryKey);

		if (!value) {
			_primaryKeys.add(getPrimaryKeyString(clazz, primaryKey));
		}

		return value;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#exportRatings(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void addRatingsEntries(Class<?> clazz, long classPK) {
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#exportRatings(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void addRatingsEntries(
		String className, long classPK, List<RatingsEntry> ratingsEntries) {
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Element addReferenceElement(
		ClassedModel referrerClassedModel, Element element,
		ClassedModel classedModel, Class<?> clazz, String referenceType,
		boolean missing) {

		return addReferenceElement(
			referrerClassedModel, element, classedModel, clazz.getName(),
			StringPool.BLANK, referenceType, missing);
	}

	@Override
	public Element addReferenceElement(
		ClassedModel referrerClassedModel, Element element,
		ClassedModel classedModel, String referenceType, boolean missing) {

		return addReferenceElement(
			referrerClassedModel, element, classedModel,
			ExportImportClassedModelUtil.getClassName(classedModel),
			StringPool.BLANK, referenceType, missing);
	}

	@Override
	public Element addReferenceElement(
		ClassedModel referrerClassedModel, Element element,
		ClassedModel classedModel, String binPath, String referenceType,
		boolean missing) {

		return addReferenceElement(
			referrerClassedModel, element, classedModel,
			ExportImportClassedModelUtil.getClassName(classedModel), binPath,
			referenceType, missing);
	}

	@Override
	public Element addReferenceElement(
		ClassedModel referrerClassedModel, Element element,
		ClassedModel classedModel, String className, String binPath,
		String referenceType, boolean missing) {

		Element referenceElement = doAddReferenceElement(
			referrerClassedModel, element, classedModel, className, binPath,
			referenceType, false);

		String referenceKey = getReferenceKey(classedModel);

		if (missing) {
			if (_references.contains(referenceKey)) {
				return referenceElement;
			}

			referenceElement.addAttribute("missing", Boolean.TRUE.toString());

			if (!_missingReferences.contains(referenceKey)) {
				_missingReferences.add(referenceKey);

				doAddReferenceElement(
					referrerClassedModel, null, classedModel, className,
					binPath, referenceType, true);
			}
		}
		else {
			_references.add(referenceKey);

			referenceElement.addAttribute("missing", Boolean.FALSE.toString());

			cleanUpMissingReferences(classedModel);
		}

		return referenceElement;
	}

	@Override
	public boolean addScopedPrimaryKey(Class<?> clazz, String primaryKey) {
		boolean value = hasScopedPrimaryKey(clazz, primaryKey);

		if (!value) {
			_scopedPrimaryKeys.add(getPrimaryKeyString(clazz, primaryKey));
		}

		return value;
	}

	@Override
	public void addZipEntry(String path, byte[] bytes) {
		if (isPathProcessed(path)) {
			return;
		}

		try {
			ZipWriter zipWriter = getZipWriter();

			zipWriter.addEntry(path, bytes);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	@Override
	public void addZipEntry(String path, InputStream is) {
		if (isPathProcessed(path)) {
			return;
		}

		try {
			ZipWriter zipWriter = getZipWriter();

			zipWriter.addEntry(path, is);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	@Override
	public void addZipEntry(String path, Object object) {
		addZipEntry(path, toXML(object));
	}

	@Override
	public void addZipEntry(String path, String s) {
		if (isPathProcessed(path)) {
			return;
		}

		try {
			ZipWriter zipWriter = getZipWriter();

			zipWriter.addEntry(path, s);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	@Override
	public void addZipEntry(String path, StringBuilder sb) {
		addZipEntry(path, sb.toString());
	}

	@Override
	public void cleanUpMissingReferences(ClassedModel classedModel) {
		String referenceKey = getReferenceKey(classedModel);

		if (_missingReferences.contains(referenceKey)) {
			_missingReferences.remove(referenceKey);

			Element missingReferenceElement = getMissingReferenceElement(
				classedModel);

			_missingReferencesElement.remove(missingReferenceElement);
		}
	}

	@Override
	public void clearScopedPrimaryKeys() {
		_scopedPrimaryKeys.clear();
	}

	@Override
	public ServiceContext createServiceContext(
		Element element, ClassedModel classedModel) {

		return createServiceContext(
			element, null, classedModel, classedModel.getModelClass());
	}

	@Override
	public ServiceContext createServiceContext(StagedModel stagedModel) {
		return createServiceContext(stagedModel, stagedModel.getModelClass());
	}

	@Override
	public ServiceContext createServiceContext(
		StagedModel stagedModel, Class<?> clazz) {

		return createServiceContext(
			getImportDataStagedModelElement(stagedModel),
			ExportImportPathUtil.getModelPath(stagedModel), stagedModel, clazz);
	}

	@Override
	public ServiceContext createServiceContext(
		String path, ClassedModel classedModel) {

		return createServiceContext(
			null, path, classedModel, classedModel.getModelClass());
	}

	@Override
	public Object fromXML(byte[] bytes) {
		if (ArrayUtil.isEmpty(bytes)) {
			return null;
		}

		return _xStream.fromXML(new String(bytes));
	}

	@Override
	public Object fromXML(String xml) {
		if (Validator.isNull(xml)) {
			return null;
		}

		return _xStream.fromXML(xml);
	}

	@Override
	public long[] getAssetCategoryIds(Class<?> clazz, long classPK) {
		return _assetCategoryIdsMap.get(getPrimaryKeyString(clazz, classPK));
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, long[]> getAssetCategoryIdsMap() {
		return Collections.emptyMap();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, String[]> getAssetCategoryUuidsMap() {
		return Collections.emptyMap();
	}

	@Override
	public Set<Long> getAssetLinkIds() {
		return _assetLinkIds;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getAssetLinkIds()}
	 */
	@Deprecated
	@Override
	public Map<String, List<AssetLink>> getAssetLinksMap() {
		return Collections.emptyMap();
	}

	@Override
	public String[] getAssetTagNames(Class<?> clazz, long classPK) {
		return _assetTagNamesMap.get(getPrimaryKeyString(clazz, classPK));
	}

	@Override
	public String[] getAssetTagNames(String className, long classPK) {
		return _assetTagNamesMap.get(getPrimaryKeyString(className, classPK));
	}

	@Override
	public Map<String, String[]> getAssetTagNamesMap() {
		return _assetTagNamesMap;
	}

	@Override
	public boolean getBooleanParameter(String namespace, String name) {
		return getBooleanParameter(namespace, name, true);
	}

	@Override
	public boolean getBooleanParameter(
		String namespace, String name, boolean useDefaultValue) {

		if (!useDefaultValue) {
			return MapUtil.getBoolean(
				getParameterMap(),
				PortletDataHandlerControl.getNamespacedControlName(
					namespace, name));
		}

		boolean defaultValue = MapUtil.getBoolean(
			getParameterMap(),
			PortletDataHandlerKeys.PORTLET_DATA_CONTROL_DEFAULT, true);

		return MapUtil.getBoolean(
			getParameterMap(),
			PortletDataHandlerControl.getNamespacedControlName(namespace, name),
			defaultValue);
	}

	@Override
	public ClassLoader getClassLoader() {
		return _xStream.getClassLoader();
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, List<MBMessage>> getComments() {
		return Collections.emptyMap();
	}

	@Override
	public long getCompanyGroupId() {
		return _companyGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getDataStrategy() {
		return _dataStrategy;
	}

	@Override
	public DateRange getDateRange() {
		DateRange dateRange = null;

		if (hasDateRange()) {
			dateRange = new DateRange(_startDate, _endDate);
		}

		return dateRange;
	}

	@Override
	public Criterion getDateRangeCriteria(String propertyName) {
		if (!hasDateRange()) {
			return null;
		}

		Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

		Property property = PropertyFactoryUtil.forName(propertyName);

		conjunction.add(property.le(_endDate));
		conjunction.add(property.ge(_startDate));

		return conjunction;
	}

	@Override
	public Set<StagedModelType> getDeletionSystemEventStagedModelTypes() {
		return _deletionSystemEventModelTypes;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public Map<String, List<ExpandoColumn>> getExpandoColumns() {
		return _expandoColumnsMap;
	}

	@Override
	public Element getExportDataElement(ClassedModel classedModel) {
		return getExportDataElement(
			classedModel,
			ExportImportClassedModelUtil.getClassSimpleName(classedModel));
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getExportDataElement(ClassedModel, String)}
	 */
	@Deprecated
	@Override
	public Element getExportDataElement(
		ClassedModel classedModel, Class<?> modelClass) {

		return getExportDataElement(classedModel, modelClass.getSimpleName());
	}

	@Override
	public Element getExportDataElement(
		ClassedModel classedModel, String modelClassSimpleName) {

		Element groupElement = getExportDataGroupElement(modelClassSimpleName);

		Element element = null;

		if (classedModel instanceof StagedModel) {
			StagedModel stagedModel = (StagedModel)classedModel;

			String path = ExportImportPathUtil.getModelPath(stagedModel);

			element = getDataElement(groupElement, "path", path);

			if (element != null) {
				return element;
			}

			element = getDataElement(
				groupElement, "uuid", stagedModel.getUuid());

			if (element != null) {
				return element;
			}
		}

		element = groupElement.addElement("staged-model");

		if (classedModel instanceof StagedGroupedModel) {
			StagedGroupedModel stagedGroupedModel =
				(StagedGroupedModel)classedModel;

			element.addAttribute(
				"group-id", String.valueOf(stagedGroupedModel.getGroupId()));
			element.addAttribute("uuid", stagedGroupedModel.getUuid());
		}
		else if (classedModel instanceof StagedModel) {
			StagedModel stagedModel = (StagedModel)classedModel;

			element.addAttribute("uuid", stagedModel.getUuid());
		}

		return element;
	}

	@Override
	public Element getExportDataGroupElement(
		Class<? extends StagedModel> clazz) {

		return getExportDataGroupElement(clazz.getSimpleName());
	}

	@Override
	public Element getExportDataRootElement() {
		return _exportDataRootElement;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public Element getImportDataElement(StagedModel stagedModel) {
		return getImportDataElement(
			ExportImportClassedModelUtil.getClassSimpleName(stagedModel),
			"uuid", stagedModel.getUuid());
	}

	@Override
	public Element getImportDataElement(
		String name, String attribute, String value) {

		Element groupElement = getImportDataGroupElement(name);

		return getDataElement(groupElement, attribute, value);
	}

	@Override
	public Element getImportDataGroupElement(
		Class<? extends StagedModel> clazz) {

		return getImportDataGroupElement(clazz.getSimpleName());
	}

	@Override
	public Element getImportDataRootElement() {
		return _importDataRootElement;
	}

	@Override
	public Element getImportDataStagedModelElement(StagedModel stagedModel) {
		String path = ExportImportPathUtil.getModelPath(stagedModel);

		return getImportDataElement(
			ExportImportClassedModelUtil.getClassSimpleName(stagedModel),
			"path", path);
	}

	@Override
	public Map<String, Lock> getLocks() {
		return _locksMap;
	}

	@Override
	public ManifestSummary getManifestSummary() {
		return _manifestSummary;
	}

	@Override
	public Element getMissingReferencesElement() {
		return _missingReferencesElement;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getNewPrimaryKeysMap(String)}
	 */
	@Deprecated
	@Override
	public List<Layout> getNewLayouts() {
		return _newLayouts;
	}

	@Override
	public Map<?, ?> getNewPrimaryKeysMap(Class<?> clazz) {
		return getNewPrimaryKeysMap(clazz.getName());
	}

	@Override
	public Map<?, ?> getNewPrimaryKeysMap(String className) {
		Map<?, ?> map = _newPrimaryKeysMaps.get(className);

		if (map == null) {
			map = new HashMap<>();

			_newPrimaryKeysMaps.put(className, map);
		}

		return map;
	}

	@Override
	public Map<String, Map<?, ?>> getNewPrimaryKeysMaps() {
		return _newPrimaryKeysMaps;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public long getOldPlid() {
		return _oldPlid;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return _parameterMap;
	}

	@Override
	public Map<String, List<KeyValuePair>> getPermissions() {
		return _permissionsMap;
	}

	@Override
	public long getPlid() {
		return _plid;
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public Set<String> getPrimaryKeys() {
		return _primaryKeys;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, List<RatingsEntry>> getRatingsEntries() {
		return Collections.emptyMap();
	}

	@Override
	public Element getReferenceDataElement(
		Element parentElement, Class<?> clazz, long classPK) {

		List<Element> referenceElements = getReferenceElements(
			parentElement, clazz.getName(), 0, null, classPK, null);

		List<Element> referenceDataElements = getReferenceDataElements(
			referenceElements, clazz);

		if (referenceDataElements.isEmpty()) {
			return null;
		}

		return referenceDataElements.get(0);
	}

	@Override
	public Element getReferenceDataElement(
		Element parentElement, Class<?> clazz, long groupId, String uuid) {

		List<Element> referenceElements = getReferenceElements(
			parentElement, clazz.getName(), groupId, uuid, 0, null);

		List<Element> referenceDataElements = getReferenceDataElements(
			referenceElements, clazz);

		if (referenceDataElements.isEmpty()) {
			return null;
		}

		return referenceDataElements.get(0);
	}

	@Override
	public Element getReferenceDataElement(
		StagedModel parentStagedModel, Class<?> clazz, long classPK) {

		Element parentElement = getImportDataStagedModelElement(
			parentStagedModel);

		return getReferenceDataElement(parentElement, clazz, classPK);
	}

	@Override
	public Element getReferenceDataElement(
		StagedModel parentStagedModel, Class<?> clazz, long groupId,
		String uuid) {

		Element parentElement = getImportDataStagedModelElement(
			parentStagedModel);

		return getReferenceDataElement(parentElement, clazz, groupId, uuid);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public List<Element> getReferenceDataElements(
		Element parentElement, Class<?> clazz) {

		return getReferenceDataElements(parentElement, clazz, null);
	}

	@Override
	public List<Element> getReferenceDataElements(
		Element parentElement, Class<?> clazz, String referenceType) {

		List<Element> referenceElements = getReferenceElements(
			parentElement, clazz.getName(), 0, null, 0, referenceType);

		return getReferenceDataElements(referenceElements, clazz);
	}

	@Override
	public List<Element> getReferenceDataElements(
		StagedModel parentStagedModel, Class<?> clazz) {

		return getReferenceDataElements(parentStagedModel, clazz, null);
	}

	@Override
	public List<Element> getReferenceDataElements(
		StagedModel parentStagedModel, Class<?> clazz, String referenceType) {

		List<Element> referenceElements = getReferenceElements(
			parentStagedModel, clazz.getName(), 0, referenceType);

		return getReferenceDataElements(referenceElements, clazz);
	}

	@Override
	public Element getReferenceElement(Class<?> clazz, long classPK) {
		return getReferenceElement(clazz.getName(), classPK);
	}

	@Override
	public Element getReferenceElement(
		Element parentElement, Class<?> clazz, long groupId, String uuid,
		String referenceType) {

		List<Element> referenceElements = getReferenceElements(
			parentElement, clazz.getName(), groupId, uuid, 0, referenceType);

		if (!referenceElements.isEmpty()) {
			return referenceElements.get(0);
		}

		return null;
	}

	@Override
	public Element getReferenceElement(
		StagedModel parentStagedModel, Class<?> clazz, long classPK) {

		return getReferenceElement(parentStagedModel, clazz.getName(), classPK);
	}

	@Override
	public Element getReferenceElement(
		StagedModel parentStagedModel, String className, long classPK) {

		List<Element> referenceElements = getReferenceElements(
			parentStagedModel, className, classPK, null);

		if (!referenceElements.isEmpty()) {
			return referenceElements.get(0);
		}

		return null;
	}

	@Override
	public Element getReferenceElement(String className, long classPK) {
		Element parentElement = getImportDataRootElement();

		List<Element> referenceElements = getReferenceElements(
			parentElement, className, 0, null, classPK, null);

		if (ListUtil.isNotEmpty(referenceElements)) {
			return referenceElements.get(0);
		}

		return null;
	}

	@Override
	public List<Element> getReferenceElements(
		StagedModel parentStagedModel, Class<?> clazz) {

		return getReferenceElements(
			parentStagedModel, clazz.getName(), 0, null);
	}

	@Override
	public String getRootPortletId() {
		return _rootPortletId;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Set<String> getScopedPrimaryKeys() {
		return _scopedPrimaryKeys;
	}

	@Override
	public long getScopeGroupId() {
		return _scopeGroupId;
	}

	@Override
	public String getScopeLayoutUuid() {
		return _scopeLayoutUuid;
	}

	@Override
	public String getScopeType() {
		return _scopeType;
	}

	@Override
	public long getSourceCompanyGroupId() {
		return _sourceCompanyGroupId;
	}

	@Override
	public long getSourceCompanyId() {
		return _sourceCompanyId;
	}

	@Override
	public long getSourceGroupId() {
		return _sourceGroupId;
	}

	@Override
	public long getSourceUserPersonalSiteGroupId() {
		return _sourceUserPersonalSiteGroupId;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public long getUserId(String userUuid) {
		return _userIdStrategy.getUserId(userUuid);
	}

	@Override
	public UserIdStrategy getUserIdStrategy() {
		return _userIdStrategy;
	}

	@Override
	public long getUserPersonalSiteGroupId() {
		return _userPersonalSiteGroupId;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public List<String> getZipEntries() {
		return getZipReader().getEntries();
	}

	@Override
	public byte[] getZipEntryAsByteArray(String path) {
		if (!Validator.isFilePath(path, false)) {
			return null;
		}

		return getZipReader().getEntryAsByteArray(path);
	}

	@Override
	public InputStream getZipEntryAsInputStream(String path) {
		if (!Validator.isFilePath(path, false)) {
			return null;
		}

		return getZipReader().getEntryAsInputStream(path);
	}

	@Override
	public Object getZipEntryAsObject(Element element, String path) {
		Object object = fromXML(getZipEntryAsString(path));

		Attribute classNameAttribute = element.attribute("attached-class-name");

		if ((object != null) && (classNameAttribute != null)) {
			BeanPropertiesUtil.setProperty(
				object, "className", classNameAttribute.getText());
		}

		return object;
	}

	@Override
	public Object getZipEntryAsObject(String path) {
		return fromXML(getZipEntryAsString(path));
	}

	@Override
	public String getZipEntryAsString(String path) {
		if (!Validator.isFilePath(path, false)) {
			return null;
		}

		return getZipReader().getEntryAsString(path);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public List<String> getZipFolderEntries() {
		return getZipFolderEntries(StringPool.SLASH);
	}

	@Override
	public List<String> getZipFolderEntries(String path) {
		if (!Validator.isFilePath(path, false)) {
			return null;
		}

		return getZipReader().getFolderEntries(path);
	}

	@Override
	public ZipReader getZipReader() {
		return _zipReader;
	}

	@Override
	public ZipWriter getZipWriter() {
		return _zipWriter;
	}

	@Override
	public boolean hasDateRange() {
		if (_startDate != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasNotUniquePerLayout(String dataKey) {
		return _notUniquePerLayout.contains(dataKey);
	}

	@Override
	public boolean hasPrimaryKey(Class<?> clazz, String primaryKey) {
		return _primaryKeys.contains(getPrimaryKeyString(clazz, primaryKey));
	}

	@Override
	public boolean hasScopedPrimaryKey(Class<?> clazz, String primaryKey) {
		return _scopedPrimaryKeys.contains(
			getPrimaryKeyString(clazz, primaryKey));
	}

	@Override
	public void importClassedModel(
			ClassedModel classedModel, ClassedModel newClassedModel)
		throws PortalException {

		importClassedModel(
			classedModel, newClassedModel, classedModel.getModelClass());
	}

	@Override
	public void importClassedModel(
			ClassedModel classedModel, ClassedModel newClassedModel,
			Class<?> clazz)
		throws PortalException {

		if (!isResourceMain(classedModel)) {
			return;
		}

		long classPK = ExportImportClassedModelUtil.getClassPK(classedModel);

		long newClassPK = ExportImportClassedModelUtil.getClassPK(
			newClassedModel);

		Map<Long, Long> newPrimaryKeysMap =
			(Map<Long, Long>)getNewPrimaryKeysMap(clazz);

		newPrimaryKeysMap.put(classPK, newClassPK);

		if (classedModel instanceof StagedGroupedModel &&
			newClassedModel instanceof StagedGroupedModel) {

			Map<Long, Long> groupIds = (Map<Long, Long>)getNewPrimaryKeysMap(
				Group.class);

			StagedGroupedModel stagedGroupedModel =
				(StagedGroupedModel)classedModel;

			if (!groupIds.containsKey(stagedGroupedModel.getGroupId())) {
				StagedGroupedModel newStagedGroupedModel =
					(StagedGroupedModel)newClassedModel;

				groupIds.put(
					stagedGroupedModel.getGroupId(),
					newStagedGroupedModel.getGroupId());
			}
		}

		importLocks(clazz, String.valueOf(classPK), String.valueOf(newClassPK));
		importPermissions(clazz, classPK, newClassPK);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#importComments(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void importComments(
		Class<?> clazz, long classPK, long newClassPK, long groupId) {
	}

	@Override
	public void importLocks(Class<?> clazz, String key, String newKey)
		throws PortalException {

		Lock lock = _locksMap.get(getPrimaryKeyString(clazz, key));

		if (lock == null) {
			return;
		}

		long userId = getUserId(lock.getUserUuid());

		long expirationTime = 0;

		if (lock.getExpirationDate() != null) {
			Date expirationDate = lock.getExpirationDate();

			expirationTime = expirationDate.getTime();
		}

		_lockManager.lock(
			userId, clazz.getName(), newKey, lock.getOwner(),
			lock.isInheritable(), expirationTime);
	}

	@Override
	public void importPermissions(Class<?> clazz, long classPK, long newClassPK)
		throws PortalException {

		importPermissions(clazz.getName(), classPK, newClassPK);
	}

	@Override
	public void importPermissions(
			String resourceName, long resourcePK, long newResourcePK)
		throws PortalException {

		if (!MapUtil.getBoolean(
				_parameterMap, PortletDataHandlerKeys.PERMISSIONS)) {

			return;
		}

		List<KeyValuePair> permissions = _permissionsMap.get(
			getPrimaryKeyString(resourceName, resourcePK));

		if (permissions == null) {
			return;
		}

		Map<Long, Set<String>> existingRoleIdsToActionIds =
			ExportImportPermissionUtil.getRoleIdsToActionIds(
				_companyId, resourceName, resourcePK);

		Map<Long, String[]> importedRoleIdsToActionIds = new HashMap<>();

		for (KeyValuePair permission : permissions) {
			String roleName = permission.getKey();

			Role role = null;

			Team team = null;

			if (ExportImportPermissionUtil.isTeamRoleName(roleName)) {
				roleName = roleName.substring(
					ExportImportPermissionUtil.ROLE_TEAM_PREFIX.length());

				try {
					team = TeamLocalServiceUtil.getTeam(_groupId, roleName);
				}
				catch (NoSuchTeamException nste) {
					if (_log.isWarnEnabled()) {
						_log.warn("Team " + roleName + " does not exist");
					}

					continue;
				}
			}

			try {
				if (team != null) {
					role = RoleLocalServiceUtil.getTeamRole(
						_companyId, team.getTeamId());
				}
				else {
					role = RoleLocalServiceUtil.getRole(_companyId, roleName);
				}
			}
			catch (NoSuchRoleException nsre) {
				if (_log.isWarnEnabled()) {
					_log.warn("Role " + roleName + " does not exist");
				}

				continue;
			}

			if (isPrivateLayout() &&
				resourceName.equals(Layout.class.getName()) &&
				roleName.equals(RoleConstants.GUEST)) {

				continue;
			}

			String[] actionIds = StringUtil.split(permission.getValue());

			importedRoleIdsToActionIds.put(role.getRoleId(), actionIds);
		}

		Map<Long, String[]> roleIdsToActionIds =
			ExportImportPermissionUtil.
				mergeImportedPermissionsWithExistingPermissions(
					existingRoleIdsToActionIds, importedRoleIdsToActionIds);

		ExportImportPermissionUtil.updateResourcePermissions(
			_companyId, _groupId, resourceName, newResourcePK,
			roleIdsToActionIds);
	}

	@Override
	public void importPortalPermissions() throws PortalException {
		importPermissions(
			PortletKeys.PORTAL, getSourceCompanyId(), getCompanyId());
	}

	@Override
	public void importPortletPermissions(String resourceName)
		throws PortalException {

		importPermissions(resourceName, getSourceGroupId(), getScopeGroupId());
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             om.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler#importRatings(
	 *             PortletDataContext, StagedModel)}
	 */
	@Deprecated
	@Override
	public void importRatingsEntries(
		Class<?> clazz, long classPK, long newClassPK) {
	}

	@Override
	public boolean isCompanyStagedGroupedModel(
		StagedGroupedModel stagedGroupedModel) {

		if ((stagedGroupedModel.getGroupId() == getCompanyGroupId()) &&
			(getGroupId() != getCompanyGroupId())) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isDataStrategyMirror() {
		if (_dataStrategy.equals(PortletDataHandlerKeys.DATA_STRATEGY_MIRROR) ||
			_dataStrategy.equals(
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDataStrategyMirrorWithOverwriting() {
		if (_dataStrategy.equals(
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR_OVERWRITE)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInitialPublication() {
		Group group = null;

		try {
			group = GroupLocalServiceUtil.getGroup(getGroupId());
		}
		catch (Exception e) {
		}

		if (ExportImportThreadLocal.isStagingInProcess() && (group != null) &&
			group.hasStagingGroup()) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isMissingReference(Element referenceElement) {
		Attribute missingAttribute = referenceElement.attribute("missing");

		if ((missingAttribute != null) &&
			!GetterUtil.getBoolean(
				referenceElement.attributeValue("missing"))) {

			return false;
		}

		if (_missingReferences.isEmpty()) {
			List<Element> missingReferenceElements =
				_missingReferencesElement.elements();

			for (Element missingReferenceElement : missingReferenceElements) {
				String missingReferenceClassName =
					missingReferenceElement.attributeValue("class-name");
				String missingReferenceClassPK =
					missingReferenceElement.attributeValue("class-pk");

				String missingReferenceKey = getReferenceKey(
					missingReferenceClassName, missingReferenceClassPK);

				_missingReferences.add(missingReferenceKey);
			}
		}

		String className = referenceElement.attributeValue("class-name");
		String classPK = referenceElement.attributeValue("class-pk");

		String referenceKey = getReferenceKey(className, classPK);

		return _missingReferences.contains(referenceKey);
	}

	@Override
	public boolean isModelCounted(String className, long classPK) {
		String modelCountedPrimaryKey = className.concat(
			StringPool.POUND).concat(String.valueOf(classPK));

		return addPrimaryKey(String.class, modelCountedPrimaryKey);
	}

	@Override
	public boolean isPathExportedInScope(String path) {
		return addScopedPrimaryKey(String.class, path);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isPathNotExportedInScope(String path) {
		return !isPathExportedInScope(path);
	}

	@Override
	public boolean isPathNotProcessed(String path) {
		return !isPathProcessed(path);
	}

	@Override
	public boolean isPathProcessed(String path) {
		addScopedPrimaryKey(String.class, path);

		return addPrimaryKey(String.class, path);
	}

	@Override
	public boolean isPerformDirectBinaryImport() {
		return MapUtil.getBoolean(
			_parameterMap, PortletDataHandlerKeys.PERFORM_DIRECT_BINARY_IMPORT);
	}

	@Override
	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public boolean isStagedModelCounted(StagedModel stagedModel) {
		StagedModelType stagedModelType = stagedModel.getStagedModelType();

		return isModelCounted(
			stagedModelType.getClassName(),
			(Long)stagedModel.getPrimaryKeyObj());
	}

	/**
	 * @see #addDateRangeCriteria(DynamicQuery, String)
	 */
	@Override
	public boolean isWithinDateRange(Date modifiedDate) {
		if (!hasDateRange()) {
			return true;
		}
		else if ((_startDate.compareTo(modifiedDate) <= 0) &&
				 _endDate.after(modifiedDate)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void putNotUniquePerLayout(String dataKey) {
		_notUniquePerLayout.add(dataKey);
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		_xStream.setClassLoader(classLoader);
	}

	@Override
	public void setCompanyGroupId(long companyGroupId) {
		_companyGroupId = companyGroupId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setDataStrategy(String dataStrategy) {
		_dataStrategy = dataStrategy;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@Override
	public void setExportDataRootElement(Element exportDataRootElement) {
		_exportDataRootElement = exportDataRootElement;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public void setImportDataRootElement(Element importDataRootElement) {
		_importDataRootElement = importDataRootElement;
	}

	@Override
	public void setManifestSummary(ManifestSummary manifestSummary) {
		_manifestSummary = manifestSummary;
	}

	@Override
	public void setMissingReferencesElement(Element missingReferencesElement) {
		_missingReferencesElement = missingReferencesElement;
	}

	@Override
	public void setNewLayouts(List<Layout> newLayouts) {
		_newLayouts = newLayouts;
	}

	@Override
	public void setOldPlid(long oldPlid) {
		_oldPlid = oldPlid;
	}

	@Override
	public void setParameterMap(Map<String, String[]> parameterMap) {
		_parameterMap = parameterMap;
	}

	@Override
	public void setPlid(long plid) {
		_plid = plid;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public void setPortetDataContextListener(
		com.liferay.exportimport.kernel.lar.PortletDataContextListener
			portletDataContextListener) {
	}

	@Override
	public void setPortletId(String portletId) {
		_portletId = portletId;

		if (Validator.isNotNull(portletId)) {
			_rootPortletId = PortletConstants.getRootPortletId(portletId);
		}
		else {
			_rootPortletId = null;
		}
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	@Override
	public void setScopeGroupId(long scopeGroupId) {
		_scopeGroupId = scopeGroupId;
	}

	@Override
	public void setScopeLayoutUuid(String scopeLayoutUuid) {
		_scopeLayoutUuid = scopeLayoutUuid;
	}

	@Override
	public void setScopeType(String scopeType) {
		_scopeType = scopeType;
	}

	@Override
	public void setSourceCompanyGroupId(long sourceCompanyGroupId) {
		_sourceCompanyGroupId = sourceCompanyGroupId;
	}

	@Override
	public void setSourceCompanyId(long sourceCompanyId) {
		_sourceCompanyId = sourceCompanyId;
	}

	@Override
	public void setSourceGroupId(long sourceGroupId) {
		_sourceGroupId = sourceGroupId;
	}

	@Override
	public void setSourceUserPersonalSiteGroupId(
		long sourceUserPersonalSiteGroupId) {

		_sourceUserPersonalSiteGroupId = sourceUserPersonalSiteGroupId;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public void setUserIdStrategy(UserIdStrategy userIdStrategy) {
		_userIdStrategy = userIdStrategy;
	}

	@Override
	public void setUserPersonalSiteGroupId(long userPersonalSiteGroupId) {
		_userPersonalSiteGroupId = userPersonalSiteGroupId;
	}

	@Override
	public void setZipReader(ZipReader zipReader) {
		_zipReader = zipReader;
	}

	@Override
	public void setZipWriter(ZipWriter zipWriter) {
		_zipWriter = zipWriter;
	}

	@Override
	public String toXML(Object object) {
		return _xStream.toXML(object);
	}

	protected void addAssetPriority(
		Element element, Class<?> clazz, long classPK) {

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			clazz.getName(), classPK);

		if (assetEntry == null) {
			return;
		}

		element.addAttribute(
			"asset-priority", String.valueOf(assetEntry.getPriority()));
	}

	protected void addExpando(
		Element element, String path, ClassedModel classedModel,
		Class<?> clazz) {

		String className = clazz.getName();

		if (!_expandoColumnsMap.containsKey(className)) {
			List<ExpandoColumn> expandoColumns =
				ExpandoColumnLocalServiceUtil.getDefaultTableColumns(
					_companyId, className);

			for (ExpandoColumn expandoColumn : expandoColumns) {
				addPermissions(
					ExpandoColumn.class, expandoColumn.getColumnId());
			}

			_expandoColumnsMap.put(className, expandoColumns);
		}

		ExpandoBridge expandoBridge = classedModel.getExpandoBridge();

		if (expandoBridge == null) {
			return;
		}

		Map<String, Serializable> expandoBridgeAttributes =
			expandoBridge.getAttributes();

		if (!expandoBridgeAttributes.isEmpty()) {
			String expandoPath = ExportImportPathUtil.getExpandoPath(path);

			element.addAttribute("expando-path", expandoPath);

			addZipEntry(expandoPath, expandoBridgeAttributes);
		}
	}

	protected ServiceContext createServiceContext(
		Element element, String path, ClassedModel classedModel,
		Class<?> clazz) {

		long classPK = ExportImportClassedModelUtil.getClassPK(classedModel);

		ServiceContext serviceContext = new ServiceContext();

		// Theme display

		serviceContext.setCompanyId(getCompanyId());
		serviceContext.setScopeGroupId(getScopeGroupId());

		// Dates

		if (classedModel instanceof AuditedModel) {
			AuditedModel auditedModel = (AuditedModel)classedModel;

			serviceContext.setUserId(getUserId(auditedModel));
			serviceContext.setCreateDate(auditedModel.getCreateDate());
			serviceContext.setModifiedDate(auditedModel.getModifiedDate());
		}

		// Permissions

		if (!MapUtil.getBoolean(
				_parameterMap, PortletDataHandlerKeys.PERMISSIONS)) {

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
		}

		// Asset

		if (isResourceMain(classedModel)) {
			long[] assetCategoryIds = getAssetCategoryIds(clazz, classPK);

			serviceContext.setAssetCategoryIds(assetCategoryIds);

			String[] assetTagNames = getAssetTagNames(clazz, classPK);

			serviceContext.setAssetTagNames(assetTagNames);
		}

		if (element != null) {
			Attribute assetPriorityAttribute = element.attribute(
				"asset-priority");

			if (assetPriorityAttribute != null) {
				double assetPriority = GetterUtil.getDouble(
					assetPriorityAttribute.getValue());

				serviceContext.setAssetPriority(assetPriority);
			}
		}

		// Expando

		String expandoPath = null;

		if (element != null) {
			expandoPath = element.attributeValue("expando-path");
		}
		else {
			expandoPath = ExportImportPathUtil.getExpandoPath(path);
		}

		if (Validator.isNotNull(expandoPath)) {
			try {
				Map<String, Serializable> expandoBridgeAttributes =
					(Map<String, Serializable>)getZipEntryAsObject(expandoPath);

				if (expandoBridgeAttributes != null) {
					serviceContext.setExpandoBridgeAttributes(
						expandoBridgeAttributes);
				}
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		// Workflow

		if (classedModel instanceof WorkflowedModel) {
			WorkflowedModel workflowedModel = (WorkflowedModel)classedModel;

			if (workflowedModel.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) {

				serviceContext.setWorkflowAction(
					WorkflowConstants.ACTION_PUBLISH);
			}
			else if (workflowedModel.getStatus() ==
						WorkflowConstants.STATUS_DRAFT) {

				serviceContext.setWorkflowAction(
					WorkflowConstants.ACTION_SAVE_DRAFT);
			}
		}

		return serviceContext;
	}

	protected Element doAddReferenceElement(
		ClassedModel referrerClassedModel, Element element,
		ClassedModel classedModel, String className, String binPath,
		String referenceType, boolean missing) {

		Element referenceElement = null;

		if (missing) {
			Element referencesElement = _missingReferencesElement;

			referenceElement = referencesElement.addElement(
				"missing-reference");
		}
		else {
			Element referencesElement = element.element("references");

			if (referencesElement == null) {
				referencesElement = element.addElement("references");
			}

			referenceElement = referencesElement.addElement("reference");
		}

		referenceElement.addAttribute("class-name", className);

		referenceElement.addAttribute(
			"class-pk", String.valueOf(classedModel.getPrimaryKeyObj()));

		populateClassNameAttribute(classedModel, referenceElement);

		if (missing) {
			if (classedModel instanceof StagedModel) {
				referenceElement.addAttribute(
					"display-name",
					StagedModelDataHandlerUtil.getDisplayName(
						(StagedModel)classedModel));
			}
			else {
				referenceElement.addAttribute(
					"display-name",
					String.valueOf(classedModel.getPrimaryKeyObj()));
			}
		}

		if (classedModel instanceof StagedGroupedModel) {
			StagedGroupedModel stagedGroupedModel =
				(StagedGroupedModel)classedModel;

			referenceElement.addAttribute(
				"group-id", String.valueOf(stagedGroupedModel.getGroupId()));

			try {
				Group group = GroupLocalServiceUtil.getGroup(
					stagedGroupedModel.getGroupId());

				long liveGroupId = group.getLiveGroupId();

				if (group.isStagedRemotely()) {
					liveGroupId = group.getRemoteLiveGroupId();
				}

				if (liveGroupId == GroupConstants.DEFAULT_LIVE_GROUP_ID) {
					liveGroupId = group.getGroupId();
				}

				referenceElement.addAttribute(
					"live-group-id", String.valueOf(liveGroupId));

				if (group.isLayout()) {
					try {
						Layout scopeLayout = LayoutLocalServiceUtil.getLayout(
							group.getClassPK());

						referenceElement.addAttribute(
							"scope-layout-uuid", scopeLayout.getUuid());
					}
					catch (NoSuchLayoutException nsle) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to find layout " + group.getClassPK());
						}
					}
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to find group " +
							stagedGroupedModel.getGroupId());
				}
			}
		}

		if (Validator.isNotNull(binPath)) {
			referenceElement.addAttribute("path", binPath);
		}

		referenceElement.addAttribute("type", referenceType);

		if (missing) {
			referenceElement.addAttribute(
				"referrer-class-name",
				ExportImportClassedModelUtil.getClassName(
					referrerClassedModel));

			if (referrerClassedModel instanceof PortletModel) {
				Portlet portlet = (Portlet)referrerClassedModel;

				referenceElement.addAttribute(
					"referrer-display-name", portlet.getRootPortletId());
			}
			else if (referrerClassedModel instanceof StagedModel) {
				StagedModel referrerStagedModel =
					(StagedModel)referrerClassedModel;

				referenceElement.addAttribute(
					"referrer-display-name",
					StagedModelDataHandlerUtil.getDisplayName(
						referrerStagedModel));
			}
		}

		if (classedModel instanceof StagedModel) {
			StagedModel stagedModel = (StagedModel)classedModel;

			referenceElement.addAttribute("uuid", stagedModel.getUuid());
			referenceElement.addAttribute(
				"company-id", String.valueOf(stagedModel.getCompanyId()));

			Map<String, String> referenceAttributes =
				StagedModelDataHandlerUtil.getReferenceAttributes(
					this, stagedModel);

			for (Map.Entry<String, String> referenceAttribute :
					referenceAttributes.entrySet()) {

				referenceElement.addAttribute(
					referenceAttribute.getKey(), referenceAttribute.getValue());
			}
		}

		return referenceElement;
	}

	protected Element getDataElement(
		Element parentElement, String attribute, String value) {

		if (parentElement == null) {
			return null;
		}

		StringBundler sb = new StringBundler(5);

		sb.append("staged-model[@");
		sb.append(attribute);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeXPathAttribute(value));
		sb.append(StringPool.CLOSE_BRACKET);

		XPath xPath = SAXReaderUtil.createXPath(sb.toString());

		return (Element)xPath.selectSingleNode(parentElement);
	}

	protected Element getExportDataGroupElement(String name) {
		if (_exportDataRootElement == null) {
			throw new IllegalStateException(
				"Root data element not initialized");
		}

		Element groupElement = _exportDataRootElement.element(name);

		if (groupElement == null) {
			groupElement = _exportDataRootElement.addElement(name);
		}

		return groupElement;
	}

	protected Element getImportDataGroupElement(String name) {
		if (_importDataRootElement == null) {
			throw new IllegalStateException(
				"Root data element not initialized");
		}

		if (Validator.isNull(name)) {
			return SAXReaderUtil.createElement("EMPTY-ELEMENT");
		}

		Element groupElement = _importDataRootElement.element(name);

		if (groupElement == null) {
			return SAXReaderUtil.createElement("EMPTY-ELEMENT");
		}

		return groupElement;
	}

	protected Element getMissingReferenceElement(ClassedModel classedModel) {
		StringBundler sb = new StringBundler(5);

		sb.append("missing-reference[@class-name='");
		sb.append(ExportImportClassedModelUtil.getClassName(classedModel));
		sb.append("' and @class-pk='");
		sb.append(String.valueOf(classedModel.getPrimaryKeyObj()));
		sb.append("']");

		XPath xPath = SAXReaderUtil.createXPath(sb.toString());

		Node node = xPath.selectSingleNode(_missingReferencesElement);

		return (Element)node;
	}

	protected String getPrimaryKeyString(Class<?> clazz, long classPK) {
		return getPrimaryKeyString(clazz.getName(), String.valueOf(classPK));
	}

	protected String getPrimaryKeyString(Class<?> clazz, String primaryKey) {
		return getPrimaryKeyString(clazz.getName(), primaryKey);
	}

	protected String getPrimaryKeyString(String className, long classPK) {
		return getPrimaryKeyString(className, String.valueOf(classPK));
	}

	protected String getPrimaryKeyString(String className, String primaryKey) {
		return className.concat(StringPool.POUND).concat(primaryKey);
	}

	protected List<Element> getReferenceDataElements(
		List<Element> referenceElements, Class<?> clazz) {

		List<Element> referenceDataElements = new ArrayList<>();

		for (Element referenceElement : referenceElements) {
			Element referenceDataElement = null;

			String path = referenceElement.attributeValue("path");

			if (Validator.isNotNull(path)) {
				referenceDataElement = getImportDataElement(
					clazz.getSimpleName(), "path", path);
			}
			else {
				String groupId = referenceElement.attributeValue("group-id");
				String uuid = referenceElement.attributeValue("uuid");

				StringBuilder sb = new StringBuilder(5);

				sb.append("staged-model[@uuid=");
				sb.append(HtmlUtil.escapeXPathAttribute(uuid));

				if (groupId != null) {
					sb.append(" and @group-id=");
					sb.append(HtmlUtil.escapeXPathAttribute(groupId));
				}

				sb.append(StringPool.CLOSE_BRACKET);

				XPath xPath = SAXReaderUtil.createXPath(sb.toString());

				Element groupElement = getImportDataGroupElement(
					clazz.getSimpleName());

				referenceDataElement = (Element)xPath.selectSingleNode(
					groupElement);
			}

			if (referenceDataElement == null) {
				continue;
			}

			referenceDataElements.add(referenceDataElement);
		}

		return referenceDataElements;
	}

	protected List<Element> getReferenceElements(
		Element parentElement, String className, long groupId, String uuid,
		long classPK, String referenceType) {

		if (parentElement == null) {
			return Collections.emptyList();
		}

		Element referencesElement = parentElement.element("references");

		if (referencesElement == null) {
			return Collections.emptyList();
		}

		StringBundler sb = new StringBundler(13);

		sb.append("reference[@class-name=");
		sb.append(HtmlUtil.escapeXPathAttribute(className));

		if (groupId > 0) {
			sb.append(" and @group-id='");
			sb.append(groupId);
			sb.append(StringPool.APOSTROPHE);
		}

		if (Validator.isNotNull(uuid)) {
			sb.append(" and @uuid=");
			sb.append(HtmlUtil.escapeXPathAttribute(uuid));
		}

		if (classPK > 0) {
			sb.append(" and @class-pk='");
			sb.append(classPK);
			sb.append(StringPool.APOSTROPHE);
		}

		if (referenceType != null) {
			sb.append(" and @type=");
			sb.append(HtmlUtil.escapeXPathAttribute(referenceType));
		}

		sb.append(StringPool.CLOSE_BRACKET);

		XPath xPath = SAXReaderUtil.createXPath(sb.toString());

		List<Node> nodes = xPath.selectNodes(referencesElement);

		return ListUtil.fromArray(nodes.toArray(new Element[nodes.size()]));
	}

	protected List<Element> getReferenceElements(
		StagedModel parentStagedModel, String className, long classPK,
		String referenceType) {

		Element stagedModelElement = getImportDataStagedModelElement(
			parentStagedModel);

		return getReferenceElements(
			stagedModelElement, className, 0, null, classPK, referenceType);
	}

	protected String getReferenceKey(ClassedModel classedModel) {
		return getReferenceKey(
			ExportImportClassedModelUtil.getClassName(classedModel),
			String.valueOf(classedModel.getPrimaryKeyObj()));
	}

	protected String getReferenceKey(String className, String classPK) {
		return className.concat(StringPool.POUND).concat(classPK);
	}

	protected long getUserId(AuditedModel auditedModel) {
		try {
			String userUuid = auditedModel.getUserUuid();

			return getUserId(userUuid);
		}
		catch (SystemException se) {
			_log.error(se, se);
		}

		return 0;
	}

	protected void initXStream() {
		_xStream = new XStream(
			null, new XppDriver(),
			new ClassLoaderReference(
				XStreamConfiguratorRegistryUtil.getConfiguratorsClassLoader(
					XStream.class.getClassLoader())));

		_xStream.omitField(HashMap.class, "cache_bitmask");

		Set<XStreamConfigurator> xStreamConfigurators =
			XStreamConfiguratorRegistryUtil.getXStreamConfigurators();

		if (SetUtil.isEmpty(xStreamConfigurators)) {
			return;
		}

		List<String> allowedTypeNames = new ArrayList<>();

		for (XStreamConfigurator xStreamConfigurator : xStreamConfigurators) {
			List<XStreamAlias> xStreamAliases =
				xStreamConfigurator.getXStreamAliases();

			if (ListUtil.isNotEmpty(xStreamAliases)) {
				for (XStreamAlias xStreamAlias : xStreamAliases) {
					_xStream.alias(
						xStreamAlias.getName(), xStreamAlias.getClazz());
				}
			}

			List<XStreamConverter> xStreamConverters =
				xStreamConfigurator.getXStreamConverters();

			if (ListUtil.isNotEmpty(xStreamConverters)) {
				for (XStreamConverter xStreamConverter : xStreamConverters) {
					_xStream.registerConverter(
						new ConverterAdapter(xStreamConverter),
						XStream.PRIORITY_VERY_HIGH);
				}
			}

			List<XStreamType> xStreamTypes =
				xStreamConfigurator.getAllowedXStreamTypes();

			if (ListUtil.isNotEmpty(xStreamTypes)) {
				for (XStreamType xStreamType : xStreamTypes) {
					allowedTypeNames.add(xStreamType.getTypeExpression());
				}
			}
		}

		// For default permissions, first wipe than add default

		_xStream.addPermission(NoTypePermission.NONE);

		// Add permissions

		_xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		_xStream.addPermission(
			XStreamStagedModelTypeHierarchyPermission.STAGED_MODELS);

		_xStream.allowTypes(_XSTREAM_DEFAULT_ALLOWED_TYPES);

		_xStream.allowTypeHierarchy(List.class);
		_xStream.allowTypeHierarchy(Map.class);
		_xStream.allowTypeHierarchy(Timestamp.class);
		_xStream.allowTypeHierarchy(Set.class);

		_xStream.allowTypes(allowedTypeNames.toArray(new String[0]));

		_xStream.allowTypesByWildcard(
			new String[] {
				"com.thoughtworks.xstream.mapper.DynamicProxyMapper*"
			});
	}

	protected boolean isResourceMain(ClassedModel classedModel) {
		if (classedModel instanceof ResourcedModel) {
			ResourcedModel resourcedModel = (ResourcedModel)classedModel;

			return resourcedModel.isResourceMain();
		}

		return true;
	}

	protected void populateClassNameAttribute(
		ClassedModel classedModel, Element element) {

		String attachedClassName = null;

		if (classedModel instanceof AttachedModel) {
			AttachedModel attachedModel = (AttachedModel)classedModel;

			attachedClassName = attachedModel.getClassName();
		}
		else if (BeanUtil.hasProperty(classedModel, "className")) {
			attachedClassName = BeanPropertiesUtil.getStringSilent(
				classedModel, "className");
		}

		if (Validator.isNotNull(attachedClassName)) {
			element.addAttribute("attached-class-name", attachedClassName);
		}
	}

	private static final Class<?>[] _XSTREAM_DEFAULT_ALLOWED_TYPES = {
		boolean[].class, byte[].class, Date.class, Date[].class, double[].class,
		float[].class, int[].class, Locale.class, long[].class, Number.class,
		Number[].class, short[].class, String.class, String[].class
	};

	private static final Log _log = LogFactoryUtil.getLog(
		PortletDataContextImpl.class);

	private final Map<String, long[]> _assetCategoryIdsMap = new HashMap<>();
	private final Set<Long> _assetLinkIds = new HashSet<>();
	private final Map<String, String[]> _assetTagNamesMap = new HashMap<>();
	private long _companyGroupId;
	private long _companyId;
	private String _dataStrategy;
	private final Set<StagedModelType> _deletionSystemEventModelTypes =
		new HashSet<>();
	private Date _endDate;
	private final Map<String, List<ExpandoColumn>> _expandoColumnsMap =
		new HashMap<>();
	private transient Element _exportDataRootElement;
	private long _groupId;
	private transient Element _importDataRootElement;
	private final transient LockManager _lockManager;
	private final transient Map<String, Lock> _locksMap = new HashMap<>();
	private transient ManifestSummary _manifestSummary = new ManifestSummary();
	private final transient Set<String> _missingReferences = new HashSet<>();
	private transient Element _missingReferencesElement;
	private transient List<Layout> _newLayouts;
	private final Map<String, Map<?, ?>> _newPrimaryKeysMaps = new HashMap<>();
	private final Set<String> _notUniquePerLayout = new HashSet<>();
	private long _oldPlid;
	private Map<String, String[]> _parameterMap;
	private final Map<String, List<KeyValuePair>> _permissionsMap =
		new HashMap<>();
	private long _plid;
	private String _portletId;
	private final Set<String> _primaryKeys = new HashSet<>();
	private boolean _privateLayout;
	private final Set<String> _references = new HashSet<>();
	private String _rootPortletId;
	private final Set<String> _scopedPrimaryKeys = new HashSet<>();
	private long _scopeGroupId;
	private String _scopeLayoutUuid;
	private String _scopeType;
	private long _sourceCompanyGroupId;
	private long _sourceCompanyId;
	private long _sourceGroupId;
	private long _sourceUserPersonalSiteGroupId;
	private Date _startDate;
	private transient UserIdStrategy _userIdStrategy;
	private long _userPersonalSiteGroupId;
	private transient XStream _xStream;
	private transient ZipReader _zipReader;
	private transient ZipWriter _zipWriter;

}