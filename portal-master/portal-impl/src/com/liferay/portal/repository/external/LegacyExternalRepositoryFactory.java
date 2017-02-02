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

package com.liferay.portal.repository.external;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.RepositoryFactory;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RepositoryEntryLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.repository.liferayrepository.LiferayRepository;
import com.liferay.portal.repository.util.ExternalRepositoryFactoryUtil;

/**
 * @author Adolfo Pérez
 */
public class LegacyExternalRepositoryFactory implements RepositoryFactory {

	@Override
	public LocalRepository createLocalRepository(long repositoryId)
		throws PortalException {

		BaseRepository baseRepository = createBaseRepository(repositoryId);

		return baseRepository.getLocalRepository();
	}

	@Override
	public Repository createRepository(long repositoryId)
		throws PortalException {

		return createBaseRepository(repositoryId);
	}

	protected BaseRepository createBaseRepository(long repositoryId)
		throws PortalException {

		long classNameId = getRepositoryClassNameId(repositoryId);

		return createExternalRepositoryImpl(repositoryId, classNameId);
	}

	protected BaseRepository createExternalRepositoryImpl(
			long repositoryId, long classNameId)
		throws PortalException {

		BaseRepository baseRepository = null;

		com.liferay.portal.kernel.model.Repository repository = null;

		ClassName className = _classNameLocalService.getClassName(classNameId);

		String repositoryImplClassName = className.getValue();

		try {
			repository = _repositoryLocalService.getRepository(repositoryId);

			baseRepository = ExternalRepositoryFactoryUtil.getInstance(
				repositoryImplClassName);
		}
		catch (Exception e) {
			throw new RepositoryException(
				"Unable to find a valid repository for class name ID " +
					classNameId,
				e);
		}

		setupRepository(repositoryId, repository, baseRepository);

		if (!ExportImportThreadLocal.isImportInProcess()) {
			try {
				baseRepository.initRepository();
			}
			catch (Exception e) {
				throw new RepositoryException(e);
			}
		}

		return baseRepository;
	}

	protected long getRepositoryClassNameId(long repositoryId) {
		com.liferay.portal.kernel.model.Repository repository =
			_repositoryLocalService.fetchRepository(repositoryId);

		if (repository != null) {
			return repository.getClassNameId();
		}

		return _classNameLocalService.getClassNameId(
			LiferayRepository.class.getName());
	}

	protected void setupRepository(
		long repositoryId,
		com.liferay.portal.kernel.model.Repository repository,
		BaseRepository baseRepository) {

		baseRepository.setAssetEntryLocalService(_assetEntryLocalService);
		baseRepository.setCompanyId(repository.getCompanyId());
		baseRepository.setCompanyLocalService(_companyLocalService);
		baseRepository.setDLAppHelperLocalService(_dlAppHelperLocalService);
		baseRepository.setDLFolderLocalService(_dlFolderLocalService);
		baseRepository.setGroupId(repository.getGroupId());
		baseRepository.setRepositoryEntryLocalService(
			_repositoryEntryLocalService);
		baseRepository.setRepositoryId(repositoryId);
		baseRepository.setTypeSettingsProperties(
			repository.getTypeSettingsProperties());
		baseRepository.setUserLocalService(_userLocalService);
	}

	@BeanReference(type = AssetEntryLocalService.class)
	private AssetEntryLocalService _assetEntryLocalService;

	@BeanReference(type = ClassNameLocalService.class)
	private ClassNameLocalService _classNameLocalService;

	@BeanReference(type = CompanyLocalService.class)
	private CompanyLocalService _companyLocalService;

	@BeanReference(type = DLAppHelperLocalService.class)
	private DLAppHelperLocalService _dlAppHelperLocalService;

	@BeanReference(type = DLFolderLocalService.class)
	private DLFolderLocalService _dlFolderLocalService;

	@BeanReference(type = RepositoryEntryLocalService.class)
	private RepositoryEntryLocalService _repositoryEntryLocalService;

	@BeanReference(type = RepositoryLocalService.class)
	private RepositoryLocalService _repositoryLocalService;

	@BeanReference(type = UserLocalService.class)
	private UserLocalService _userLocalService;

}