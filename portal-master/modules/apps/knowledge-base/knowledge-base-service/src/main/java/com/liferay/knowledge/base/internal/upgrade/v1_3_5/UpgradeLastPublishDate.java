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

package com.liferay.knowledge.base.internal.upgrade.v1_3_5;

import com.liferay.portal.kernel.upgrade.BaseUpgradeLastPublishDate;

/**
 * @author Mate Thurzo
 */
public class UpgradeLastPublishDate extends BaseUpgradeLastPublishDate {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table KBArticle add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBArticle");

		runSQL("alter table KBComment add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBComment");

		runSQL("alter table KBFolder add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBFolder");

		runSQL("alter table KBTemplate add lastPublishDate DATE null");

		updateLastPublishDates("1_WAR_knowledgebaseportlet", "KBTemplate");
	}

}