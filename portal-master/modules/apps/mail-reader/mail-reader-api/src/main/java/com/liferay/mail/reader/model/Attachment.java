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

package com.liferay.mail.reader.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Attachment service. Represents a row in the &quot;Mail_Attachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentModel
 * @see com.liferay.mail.reader.model.impl.AttachmentImpl
 * @see com.liferay.mail.reader.model.impl.AttachmentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.mail.reader.model.impl.AttachmentImpl")
@ProviderType
public interface Attachment extends AttachmentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.mail.reader.model.impl.AttachmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Attachment, Long> ATTACHMENT_ID_ACCESSOR = new Accessor<Attachment, Long>() {
			@Override
			public Long get(Attachment attachment) {
				return attachment.getAttachmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Attachment> getTypeClass() {
				return Attachment.class;
			}
		};
}