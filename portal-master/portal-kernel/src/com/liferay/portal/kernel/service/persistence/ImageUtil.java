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

package com.liferay.portal.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the image service. This utility wraps {@link com.liferay.portal.service.persistence.impl.ImagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImagePersistence
 * @see com.liferay.portal.service.persistence.impl.ImagePersistenceImpl
 * @generated
 */
@ProviderType
public class ImageUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Image image) {
		getPersistence().clearCache(image);
	}

	/**
	 * @see BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Image> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Image> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Image> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Image> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Image update(Image image) {
		return getPersistence().update(image);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Image update(Image image, ServiceContext serviceContext) {
		return getPersistence().update(image, serviceContext);
	}

	/**
	* Returns all the images where size &lt; &#63;.
	*
	* @param size the size
	* @return the matching images
	*/
	public static List<Image> findByLtSize(int size) {
		return getPersistence().findByLtSize(size);
	}

	/**
	* Returns a range of all the images where size &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param size the size
	* @param start the lower bound of the range of images
	* @param end the upper bound of the range of images (not inclusive)
	* @return the range of matching images
	*/
	public static List<Image> findByLtSize(int size, int start, int end) {
		return getPersistence().findByLtSize(size, start, end);
	}

	/**
	* Returns an ordered range of all the images where size &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param size the size
	* @param start the lower bound of the range of images
	* @param end the upper bound of the range of images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching images
	*/
	public static List<Image> findByLtSize(int size, int start, int end,
		OrderByComparator<Image> orderByComparator) {
		return getPersistence().findByLtSize(size, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the images where size &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param size the size
	* @param start the lower bound of the range of images
	* @param end the upper bound of the range of images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching images
	*/
	public static List<Image> findByLtSize(int size, int start, int end,
		OrderByComparator<Image> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByLtSize(size, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first image in the ordered set where size &lt; &#63;.
	*
	* @param size the size
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching image
	* @throws NoSuchImageException if a matching image could not be found
	*/
	public static Image findByLtSize_First(int size,
		OrderByComparator<Image> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchImageException {
		return getPersistence().findByLtSize_First(size, orderByComparator);
	}

	/**
	* Returns the first image in the ordered set where size &lt; &#63;.
	*
	* @param size the size
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching image, or <code>null</code> if a matching image could not be found
	*/
	public static Image fetchByLtSize_First(int size,
		OrderByComparator<Image> orderByComparator) {
		return getPersistence().fetchByLtSize_First(size, orderByComparator);
	}

	/**
	* Returns the last image in the ordered set where size &lt; &#63;.
	*
	* @param size the size
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching image
	* @throws NoSuchImageException if a matching image could not be found
	*/
	public static Image findByLtSize_Last(int size,
		OrderByComparator<Image> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchImageException {
		return getPersistence().findByLtSize_Last(size, orderByComparator);
	}

	/**
	* Returns the last image in the ordered set where size &lt; &#63;.
	*
	* @param size the size
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching image, or <code>null</code> if a matching image could not be found
	*/
	public static Image fetchByLtSize_Last(int size,
		OrderByComparator<Image> orderByComparator) {
		return getPersistence().fetchByLtSize_Last(size, orderByComparator);
	}

	/**
	* Returns the images before and after the current image in the ordered set where size &lt; &#63;.
	*
	* @param imageId the primary key of the current image
	* @param size the size
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next image
	* @throws NoSuchImageException if a image with the primary key could not be found
	*/
	public static Image[] findByLtSize_PrevAndNext(long imageId, int size,
		OrderByComparator<Image> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchImageException {
		return getPersistence()
				   .findByLtSize_PrevAndNext(imageId, size, orderByComparator);
	}

	/**
	* Removes all the images where size &lt; &#63; from the database.
	*
	* @param size the size
	*/
	public static void removeByLtSize(int size) {
		getPersistence().removeByLtSize(size);
	}

	/**
	* Returns the number of images where size &lt; &#63;.
	*
	* @param size the size
	* @return the number of matching images
	*/
	public static int countByLtSize(int size) {
		return getPersistence().countByLtSize(size);
	}

	/**
	* Caches the image in the entity cache if it is enabled.
	*
	* @param image the image
	*/
	public static void cacheResult(Image image) {
		getPersistence().cacheResult(image);
	}

	/**
	* Caches the images in the entity cache if it is enabled.
	*
	* @param images the images
	*/
	public static void cacheResult(List<Image> images) {
		getPersistence().cacheResult(images);
	}

	/**
	* Creates a new image with the primary key. Does not add the image to the database.
	*
	* @param imageId the primary key for the new image
	* @return the new image
	*/
	public static Image create(long imageId) {
		return getPersistence().create(imageId);
	}

	/**
	* Removes the image with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imageId the primary key of the image
	* @return the image that was removed
	* @throws NoSuchImageException if a image with the primary key could not be found
	*/
	public static Image remove(long imageId)
		throws com.liferay.portal.kernel.exception.NoSuchImageException {
		return getPersistence().remove(imageId);
	}

	public static Image updateImpl(Image image) {
		return getPersistence().updateImpl(image);
	}

	/**
	* Returns the image with the primary key or throws a {@link NoSuchImageException} if it could not be found.
	*
	* @param imageId the primary key of the image
	* @return the image
	* @throws NoSuchImageException if a image with the primary key could not be found
	*/
	public static Image findByPrimaryKey(long imageId)
		throws com.liferay.portal.kernel.exception.NoSuchImageException {
		return getPersistence().findByPrimaryKey(imageId);
	}

	/**
	* Returns the image with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imageId the primary key of the image
	* @return the image, or <code>null</code> if a image with the primary key could not be found
	*/
	public static Image fetchByPrimaryKey(long imageId) {
		return getPersistence().fetchByPrimaryKey(imageId);
	}

	public static java.util.Map<java.io.Serializable, Image> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the images.
	*
	* @return the images
	*/
	public static List<Image> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of images
	* @param end the upper bound of the range of images (not inclusive)
	* @return the range of images
	*/
	public static List<Image> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of images
	* @param end the upper bound of the range of images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of images
	*/
	public static List<Image> findAll(int start, int end,
		OrderByComparator<Image> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the images.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of images
	* @param end the upper bound of the range of images (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of images
	*/
	public static List<Image> findAll(int start, int end,
		OrderByComparator<Image> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the images from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of images.
	*
	* @return the number of images
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ImagePersistence)PortalBeanLocatorUtil.locate(ImagePersistence.class.getName());

			ReferenceRegistry.registerReference(ImageUtil.class, "_persistence");
		}

		return _persistence;
	}

	private static ImagePersistence _persistence;
}