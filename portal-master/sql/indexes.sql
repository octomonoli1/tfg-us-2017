create index IX_923BD178 on Address (companyId, classNameId, classPK, mailing);
create index IX_9226DBB4 on Address (companyId, classNameId, classPK, primary_);
create index IX_5BC8B0D4 on Address (userId);
create index IX_8FCB620E on Address (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_BA4413D5 on AnnouncementsDelivery (userId, type_[$COLUMN_LENGTH:75$]);

create index IX_14F06A6B on AnnouncementsEntry (classNameId, classPK, alert);
create index IX_D49C2E66 on AnnouncementsEntry (userId);
create index IX_F2949120 on AnnouncementsEntry (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_9C7EB9F on AnnouncementsFlag (entryId);
create unique index IX_4539A99C on AnnouncementsFlag (userId, entryId, value);

create index IX_C7F39FCA on AssetCategory (groupId, name[$COLUMN_LENGTH:75$], vocabularyId);
create index IX_852EA801 on AssetCategory (groupId, parentCategoryId, name[$COLUMN_LENGTH:75$], vocabularyId);
create index IX_87603842 on AssetCategory (groupId, parentCategoryId, vocabularyId);
create index IX_2008FACB on AssetCategory (groupId, vocabularyId);
create index IX_D61ABE08 on AssetCategory (name[$COLUMN_LENGTH:75$], vocabularyId);
create unique index IX_BE4DF2BF on AssetCategory (parentCategoryId, name[$COLUMN_LENGTH:75$], vocabularyId);
create index IX_B185E980 on AssetCategory (parentCategoryId, vocabularyId);
create index IX_BBAF6928 on AssetCategory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E8D019AA on AssetCategory (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_287B1F89 on AssetCategory (vocabularyId);

create unique index IX_DBD111AA on AssetCategoryProperty (categoryId, key_[$COLUMN_LENGTH:75$]);
create index IX_52340033 on AssetCategoryProperty (companyId, key_[$COLUMN_LENGTH:75$]);

create index IX_A188F560 on AssetEntries_AssetCategories (categoryId);
create index IX_38A65B55 on AssetEntries_AssetCategories (companyId);
create index IX_E119938A on AssetEntries_AssetCategories (entryId);

create index IX_112337B8 on AssetEntries_AssetTags (companyId);
create index IX_2ED82CAD on AssetEntries_AssetTags (entryId);
create index IX_B2A61B55 on AssetEntries_AssetTags (tagId);

create unique index IX_1E9D371D on AssetEntry (classNameId, classPK);
create index IX_7306C60 on AssetEntry (companyId);
create index IX_75D42FF9 on AssetEntry (expirationDate);
create index IX_1EBA6821 on AssetEntry (groupId, classUuid[$COLUMN_LENGTH:75$]);
create index IX_FEC4A201 on AssetEntry (layoutUuid[$COLUMN_LENGTH:75$]);
create index IX_2E4E3885 on AssetEntry (publishDate);
create index IX_9029E15A on AssetEntry (visible);

create unique index IX_8F542794 on AssetLink (entryId1, entryId2, type_);
create index IX_14D5A20D on AssetLink (entryId1, type_);
create index IX_91F132C on AssetLink (entryId2, type_);

create unique index IX_D63322F9 on AssetTag (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_84C501E4 on AssetTag (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B6ACB166 on AssetTag (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_50702693 on AssetTagStats (classNameId);
create unique index IX_56682CC4 on AssetTagStats (tagId, classNameId);

create index IX_B22D908C on AssetVocabulary (companyId);
create unique index IX_C0AAD74D on AssetVocabulary (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_C4E6FD10 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B2B8792 on AssetVocabulary (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BB0C2905 on BlogsEntry (companyId, displayDate, status);
create index IX_EB2DCE27 on BlogsEntry (companyId, status);
create index IX_A5F57B61 on BlogsEntry (companyId, userId, status);
create index IX_2672F77F on BlogsEntry (displayDate, status);
create index IX_F0E73383 on BlogsEntry (groupId, displayDate, status);
create index IX_1EFD8EE9 on BlogsEntry (groupId, status);
create unique index IX_DB780A20 on BlogsEntry (groupId, urlTitle[$COLUMN_LENGTH:150$]);
create index IX_DA04F689 on BlogsEntry (groupId, userId, displayDate, status);
create index IX_49E15A23 on BlogsEntry (groupId, userId, status);
create index IX_5E8307BB on BlogsEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1B1040FD on BlogsEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_90CDA39A on BlogsStatsUser (companyId, entryCount);
create index IX_28C78D5C on BlogsStatsUser (groupId, entryCount);
create unique index IX_82254C25 on BlogsStatsUser (groupId, userId);
create index IX_507BA031 on BlogsStatsUser (userId, lastPostDate);

create unique index IX_E7B95510 on BrowserTracker (userId);

create unique index IX_B27A301F on ClassName_ (value[$COLUMN_LENGTH:200$]);

create index IX_38EFE3FD on Company (logoId);
create index IX_12566EC2 on Company (mx[$COLUMN_LENGTH:75$]);
create index IX_35E3E7C6 on Company (system);
create unique index IX_EC00543C on Company (webId[$COLUMN_LENGTH:75$]);

create index IX_B8C28C53 on Contact_ (accountId);
create index IX_791914FA on Contact_ (classNameId, classPK);
create index IX_66D496A3 on Contact_ (companyId);

create unique index IX_717B97E1 on Country (a2[$COLUMN_LENGTH:75$]);
create unique index IX_717B9BA2 on Country (a3[$COLUMN_LENGTH:75$]);
create index IX_25D734CD on Country (active_);
create unique index IX_19DA007B on Country (name[$COLUMN_LENGTH:75$]);

create unique index IX_FDD1AAA8 on DLContent (companyId, repositoryId, path_[$COLUMN_LENGTH:255$], version[$COLUMN_LENGTH:75$]);

create index IX_5444C427 on DLFileEntry (companyId, fileEntryTypeId);
create index IX_772ECDE7 on DLFileEntry (fileEntryTypeId);
create index IX_8F6C75D0 on DLFileEntry (folderId, name[$COLUMN_LENGTH:255$]);
create index IX_BAF654E5 on DLFileEntry (groupId, fileEntryTypeId);
create index IX_29D0AF28 on DLFileEntry (groupId, folderId, fileEntryTypeId);
create unique index IX_DF37D92E on DLFileEntry (groupId, folderId, fileName[$COLUMN_LENGTH:255$]);
create unique index IX_5391712 on DLFileEntry (groupId, folderId, name[$COLUMN_LENGTH:255$]);
create unique index IX_ED5CA615 on DLFileEntry (groupId, folderId, title[$COLUMN_LENGTH:255$]);
create index IX_D20C434D on DLFileEntry (groupId, userId, folderId);
create index IX_D9492CF6 on DLFileEntry (mimeType[$COLUMN_LENGTH:75$]);
create index IX_1B352F4A on DLFileEntry (repositoryId, folderId);
create index IX_25F5CAB9 on DLFileEntry (smallImageId, largeImageId, custom1ImageId, custom2ImageId);
create index IX_31079DE8 on DLFileEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BC2E7E6A on DLFileEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_7332B44F on DLFileEntryMetadata (DDMStructureId, fileVersionId);
create index IX_4F40FE5E on DLFileEntryMetadata (fileEntryId);
create index IX_1FE9C04 on DLFileEntryMetadata (fileVersionId);
create index IX_E69431B7 on DLFileEntryMetadata (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_5B6BEF5F on DLFileEntryType (groupId, fileEntryTypeKey[$COLUMN_LENGTH:75$]);
create index IX_5B03E942 on DLFileEntryType (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1399D844 on DLFileEntryType (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2E64D9F9 on DLFileEntryTypes_DLFolders (companyId);
create index IX_5BB6AD6C on DLFileEntryTypes_DLFolders (fileEntryTypeId);
create index IX_6E00A2EC on DLFileEntryTypes_DLFolders (folderId);

create index IX_38F0315 on DLFileRank (companyId, userId, fileEntryId);
create index IX_A65A1F8B on DLFileRank (fileEntryId);
create index IX_4E96195B on DLFileRank (groupId, userId, active_);
create index IX_EED06670 on DLFileRank (userId);

create index IX_8571953E on DLFileShortcut (companyId, status);
create index IX_17EE3098 on DLFileShortcut (groupId, folderId, active_, status);
create index IX_4B7247F6 on DLFileShortcut (toFileEntryId);
create index IX_29AE81C4 on DLFileShortcut (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FDB4A946 on DLFileShortcut (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_A0A283F4 on DLFileVersion (companyId, status);
create index IX_D47BB14D on DLFileVersion (fileEntryId, status);
create unique index IX_E2815081 on DLFileVersion (fileEntryId, version[$COLUMN_LENGTH:75$]);
create index IX_DFD809D3 on DLFileVersion (groupId, folderId, status);
create index IX_9BE769ED on DLFileVersion (groupId, folderId, title[$COLUMN_LENGTH:255$], version[$COLUMN_LENGTH:75$]);
create index IX_FFB3395C on DLFileVersion (mimeType[$COLUMN_LENGTH:75$]);
create index IX_95E9E44E on DLFileVersion (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_C99B2650 on DLFileVersion (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E79BE432 on DLFolder (companyId, status);
create index IX_C88430AB on DLFolder (groupId, mountPoint, parentFolderId, hidden_, status);
create index IX_CE360BF6 on DLFolder (groupId, parentFolderId, hidden_, status);
create unique index IX_902FD874 on DLFolder (groupId, parentFolderId, name[$COLUMN_LENGTH:255$]);
create index IX_51556082 on DLFolder (parentFolderId, name[$COLUMN_LENGTH:255$]);
create index IX_6F63F140 on DLFolder (repositoryId, mountPoint);
create index IX_6747B2BC on DLFolder (repositoryId, parentFolderId);
create index IX_DA448450 on DLFolder (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_3CC1DED2 on DLFolder (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3D8E1607 on DLSyncEvent (modifiedTime);
create unique index IX_57D82B06 on DLSyncEvent (typePK);

create index IX_2A2CB130 on EmailAddress (companyId, classNameId, classPK, primary_);
create index IX_7B43CD8 on EmailAddress (userId);
create index IX_F74AB912 on EmailAddress (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_FEFC8DA7 on ExpandoColumn (tableId, name[$COLUMN_LENGTH:75$]);

create index IX_49EB3118 on ExpandoRow (classPK);
create unique index IX_81EFBFF5 on ExpandoRow (tableId, classPK);

create unique index IX_37562284 on ExpandoTable (companyId, classNameId, name[$COLUMN_LENGTH:75$]);

create index IX_B29FEF17 on ExpandoValue (classNameId, classPK);
create unique index IX_9DDD21E5 on ExpandoValue (columnId, rowId_);
create index IX_9112A7A0 on ExpandoValue (rowId_);
create index IX_1BD3F4C on ExpandoValue (tableId, classPK);
create unique index IX_D27B03E7 on ExpandoValue (tableId, columnId, classPK);
create index IX_B71E92D5 on ExpandoValue (tableId, rowId_);

create index IX_1827A2E5 on ExportImportConfiguration (companyId);
create index IX_38FA468D on ExportImportConfiguration (groupId, status);
create index IX_47CC6234 on ExportImportConfiguration (groupId, type_, status);

create index IX_8257E37B on Group_ (classNameId, classPK);
create index IX_BD3CB13A on Group_ (classNameId, groupId, companyId, parentGroupId);
create index IX_DDC91A87 on Group_ (companyId, active_);
create unique index IX_D0D5E397 on Group_ (companyId, classNameId, classPK);
create unique index IX_A729E3A6 on Group_ (companyId, classNameId, liveGroupId, groupKey[$COLUMN_LENGTH:150$]);
create index IX_ABE2D54 on Group_ (companyId, classNameId, parentGroupId);
create unique index IX_5BDDB872 on Group_ (companyId, friendlyURL[$COLUMN_LENGTH:255$]);
create unique index IX_ACD2B296 on Group_ (companyId, groupKey[$COLUMN_LENGTH:150$]);
create unique index IX_AACD15F0 on Group_ (companyId, liveGroupId, groupKey[$COLUMN_LENGTH:150$]);
create index IX_D4BFF38B on Group_ (companyId, parentGroupId, site, inheritContent);
create index IX_63A2AABD on Group_ (companyId, site);
create index IX_16218A38 on Group_ (liveGroupId);
create index IX_7B590A7A on Group_ (type_, active_);
create index IX_26CC761A on Group_ (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_754FBB1C on Group_ (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_8BFD4548 on Groups_Orgs (companyId);
create index IX_75267DCA on Groups_Orgs (groupId);
create index IX_6BBB7682 on Groups_Orgs (organizationId);

create index IX_557D8550 on Groups_Roles (companyId);
create index IX_84471FD2 on Groups_Roles (groupId);
create index IX_3103EF3D on Groups_Roles (roleId);

create index IX_676FC818 on Groups_UserGroups (companyId);
create index IX_31FB749A on Groups_UserGroups (groupId);
create index IX_3B69160F on Groups_UserGroups (userGroupId);

create index IX_6A925A4D on Image (size_);

create index IX_C7FBC998 on Layout (companyId);
create unique index IX_BC2C4231 on Layout (groupId, privateLayout, friendlyURL[$COLUMN_LENGTH:255$]);
create unique index IX_7162C27C on Layout (groupId, privateLayout, layoutId);
create index IX_7399B71E on Layout (groupId, privateLayout, parentLayoutId, priority);
create index IX_8CE8C0D9 on Layout (groupId, privateLayout, sourcePrototypeLayoutUuid[$COLUMN_LENGTH:75$]);
create index IX_1A1B61D2 on Layout (groupId, privateLayout, type_[$COLUMN_LENGTH:75$]);
create index IX_23922F7D on Layout (iconImageId);
create index IX_B529BFD3 on Layout (layoutPrototypeUuid[$COLUMN_LENGTH:75$]);
create index IX_39A18ECC on Layout (sourcePrototypeLayoutUuid[$COLUMN_LENGTH:75$]);
create index IX_2CE4BE84 on Layout (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E118C537 on Layout (uuid_[$COLUMN_LENGTH:75$], groupId, privateLayout);

create index IX_A705FF94 on LayoutBranch (layoutSetBranchId, plid, master);
create unique index IX_FD57097D on LayoutBranch (layoutSetBranchId, plid, name[$COLUMN_LENGTH:75$]);

create index IX_EAB317C8 on LayoutFriendlyURL (companyId);
create unique index IX_A6FC2B28 on LayoutFriendlyURL (groupId, privateLayout, friendlyURL[$COLUMN_LENGTH:255$], languageId[$COLUMN_LENGTH:75$]);
create index IX_59051329 on LayoutFriendlyURL (plid, friendlyURL[$COLUMN_LENGTH:255$]);
create unique index IX_C5762E72 on LayoutFriendlyURL (plid, languageId[$COLUMN_LENGTH:75$]);
create index IX_F4321A54 on LayoutFriendlyURL (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_326525D6 on LayoutFriendlyURL (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_557A639F on LayoutPrototype (companyId, active_);
create index IX_63ED2532 on LayoutPrototype (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_43E8286A on LayoutRevision (head, plid);
create index IX_E10AC39 on LayoutRevision (layoutSetBranchId, head, plid);
create index IX_13984800 on LayoutRevision (layoutSetBranchId, layoutBranchId, plid);
create index IX_4A84AF43 on LayoutRevision (layoutSetBranchId, parentLayoutRevisionId, plid);
create index IX_70DA9ECB on LayoutRevision (layoutSetBranchId, plid, status);
create index IX_7FFAE700 on LayoutRevision (layoutSetBranchId, status);
create index IX_8EC3D2BC on LayoutRevision (plid, status);

create unique index IX_48550691 on LayoutSet (groupId, privateLayout);
create index IX_72BBA8B7 on LayoutSet (layoutSetPrototypeUuid[$COLUMN_LENGTH:75$]);

create index IX_CCF0DA29 on LayoutSetBranch (groupId, privateLayout, master);
create unique index IX_5FF18552 on LayoutSetBranch (groupId, privateLayout, name[$COLUMN_LENGTH:75$]);

create index IX_9178FC71 on LayoutSetPrototype (companyId, active_);
create index IX_D9FFCA84 on LayoutSetPrototype (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_77729718 on ListType (name[$COLUMN_LENGTH:75$], type_[$COLUMN_LENGTH:75$]);
create index IX_2932DD37 on ListType (type_[$COLUMN_LENGTH:75$]);

create index IX_69951A25 on MBBan (banUserId);
create unique index IX_8ABC4E3B on MBBan (groupId, banUserId);
create index IX_48814BBA on MBBan (userId);
create index IX_4F841574 on MBBan (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2A3B68F6 on MBBan (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D1642361 on MBCategory (categoryId, groupId, parentCategoryId, status);
create index IX_E15A5DB5 on MBCategory (companyId, status);
create index IX_C295DBEE on MBCategory (groupId, parentCategoryId, status);
create index IX_DA84A9F7 on MBCategory (groupId, status);
create index IX_13DF4E6D on MBCategory (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F7D28C2F on MBCategory (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_33A4DE38 on MBDiscussion (classNameId, classPK);
create unique index IX_B5CA2DC on MBDiscussion (threadId);
create index IX_7E965757 on MBDiscussion (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F7AAC799 on MBDiscussion (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BFEB984F on MBMailingList (active_);
create unique index IX_76CE9CDD on MBMailingList (groupId, categoryId);
create index IX_FC61676E on MBMailingList (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E858F170 on MBMailingList (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_F6687633 on MBMessage (classNameId, classPK, status);
create index IX_1AD93C16 on MBMessage (companyId, status);
create index IX_4257DB85 on MBMessage (groupId, categoryId, status);
create index IX_CBFDBF0A on MBMessage (groupId, categoryId, threadId, answer);
create index IX_385E123E on MBMessage (groupId, categoryId, threadId, status);
create index IX_ED39AC98 on MBMessage (groupId, status);
create index IX_377858D2 on MBMessage (groupId, userId, status);
create index IX_9D7C3B23 on MBMessage (threadId, answer);
create index IX_A7038CD7 on MBMessage (threadId, parentMessageId);
create index IX_9DC8E57 on MBMessage (threadId, status);
create index IX_4A4BB4ED on MBMessage (userId, classNameId, classPK, status);
create index IX_3321F142 on MBMessage (userId, classNameId, status);
create index IX_57CA9FEC on MBMessage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8D12316E on MBMessage (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_9168E2C9 on MBStatsUser (groupId, userId);
create index IX_847F92B5 on MBStatsUser (userId);

create index IX_41F6DC8A on MBThread (categoryId, priority);
create index IX_50F1904A on MBThread (groupId, categoryId, lastPostDate);
create index IX_485F7E98 on MBThread (groupId, categoryId, status);
create index IX_E1E7142B on MBThread (groupId, status);
create index IX_AEDD9CB5 on MBThread (lastPostDate, priority);
create index IX_CC993ECB on MBThread (rootMessageId);
create index IX_F8CA2AB9 on MBThread (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_3A200B7B on MBThread (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_8CB0A24A on MBThreadFlag (threadId);
create unique index IX_33781904 on MBThreadFlag (userId, threadId);
create index IX_DCE308C5 on MBThreadFlag (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_FEB0FC87 on MBThreadFlag (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_C28C72EC on MembershipRequest (groupId, statusId);
create index IX_35AA8FA6 on MembershipRequest (groupId, userId, statusId);
create index IX_66D70879 on MembershipRequest (userId);

create index IX_4A527DD3 on OrgGroupRole (groupId);
create index IX_AB044D1C on OrgGroupRole (roleId);

create index IX_6AF0D434 on OrgLabor (organizationId);

create unique index IX_E301BDF5 on Organization_ (companyId, name[$COLUMN_LENGTH:100$]);
create index IX_418E4522 on Organization_ (companyId, parentOrganizationId);
create index IX_A9D85BA6 on Organization_ (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_2C1142E on PasswordPolicy (companyId, defaultPolicy);
create unique index IX_3FBFA9F4 on PasswordPolicy (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_E4D7EF87 on PasswordPolicy (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_C3A17327 on PasswordPolicyRel (classNameId, classPK);
create index IX_CD25266E on PasswordPolicyRel (passwordPolicyId);

create index IX_326F75BD on PasswordTracker (userId);

create index IX_812CE07A on Phone (companyId, classNameId, classPK, primary_);
create index IX_F202B9CE on Phone (userId);
create index IX_B271FA88 on Phone (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_7171B2E8 on PluginSetting (companyId, pluginId[$COLUMN_LENGTH:75$], pluginType[$COLUMN_LENGTH:75$]);

create index IX_D1F795F1 on PortalPreferences (ownerId, ownerType);

create unique index IX_12B5E51D on Portlet (companyId, portletId[$COLUMN_LENGTH:200$]);

create index IX_96BDD537 on PortletItem (groupId, classNameId);
create index IX_D699243F on PortletItem (groupId, name[$COLUMN_LENGTH:75$], portletId[$COLUMN_LENGTH:200$], classNameId);
create index IX_E922D6C0 on PortletItem (groupId, portletId[$COLUMN_LENGTH:200$], classNameId);

create unique index IX_C7057FF7 on PortletPreferences (ownerId, ownerType, plid, portletId[$COLUMN_LENGTH:200$]);
create index IX_C9A3FCE2 on PortletPreferences (ownerId, ownerType, portletId[$COLUMN_LENGTH:200$]);
create index IX_D5EDA3A1 on PortletPreferences (ownerType, plid, portletId[$COLUMN_LENGTH:200$]);
create index IX_A3B2A80C on PortletPreferences (ownerType, portletId[$COLUMN_LENGTH:200$]);
create index IX_D340DB76 on PortletPreferences (plid, portletId[$COLUMN_LENGTH:200$]);
create index IX_8E6DA3A1 on PortletPreferences (portletId[$COLUMN_LENGTH:200$]);

create index IX_A1A8CB8B on RatingsEntry (classNameId, classPK, score);
create unique index IX_B47E3C11 on RatingsEntry (userId, classNameId, classPK);
create index IX_9F242DF6 on RatingsEntry (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_A6E99284 on RatingsStats (classNameId, classPK);

create index IX_B91F79BD on RecentLayoutBranch (groupId);
create index IX_351E86E8 on RecentLayoutBranch (layoutBranchId);
create unique index IX_C27D6369 on RecentLayoutBranch (userId, layoutSetBranchId, plid);

create index IX_8D8A2724 on RecentLayoutRevision (groupId);
create index IX_DA0788DA on RecentLayoutRevision (layoutRevisionId);
create unique index IX_4C600BD0 on RecentLayoutRevision (userId, layoutSetBranchId, plid);

create index IX_711995A5 on RecentLayoutSetBranch (groupId);
create index IX_23FF0700 on RecentLayoutSetBranch (layoutSetBranchId);
create unique index IX_4654D204 on RecentLayoutSetBranch (userId, layoutSetId);

create index IX_2D9A426F on Region (active_);
create index IX_11FB3E42 on Region (countryId, active_);
create unique index IX_A2635F5C on Region (countryId, regionCode[$COLUMN_LENGTH:75$]);

create unique index IX_8BD6BCA7 on Release_ (servletContextName[$COLUMN_LENGTH:75$]);

create unique index IX_60C8634C on Repository (groupId, name[$COLUMN_LENGTH:75$], portletId[$COLUMN_LENGTH:200$]);
create index IX_F543EA4 on Repository (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_11641E26 on Repository (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_9BDCF489 on RepositoryEntry (repositoryId, mappedId[$COLUMN_LENGTH:255$]);
create index IX_D3B9AF62 on RepositoryEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_354AA664 on RepositoryEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_EDB9986E on ResourceAction (name[$COLUMN_LENGTH:255$], actionId[$COLUMN_LENGTH:75$]);

create unique index IX_AEEA209C on ResourceBlock (companyId, groupId, name[$COLUMN_LENGTH:75$], permissionsHash[$COLUMN_LENGTH:75$]);
create index IX_2D4CC782 on ResourceBlock (companyId, name[$COLUMN_LENGTH:75$]);

create unique index IX_D63D20BB on ResourceBlockPermission (resourceBlockId, roleId);
create index IX_20A2E3D9 on ResourceBlockPermission (roleId);

create unique index IX_8D83D0CE on ResourcePermission (companyId, name[$COLUMN_LENGTH:255$], scope, primKey[$COLUMN_LENGTH:255$], roleId);
create index IX_49AEC6F3 on ResourcePermission (companyId, name[$COLUMN_LENGTH:255$], scope, primKeyId, roleId, viewActionId);
create index IX_26284944 on ResourcePermission (companyId, primKey[$COLUMN_LENGTH:255$]);
create index IX_F6BAE86A on ResourcePermission (companyId, scope, primKey[$COLUMN_LENGTH:255$]);
create index IX_D5F1E2A2 on ResourcePermission (name[$COLUMN_LENGTH:255$]);
create index IX_A37A0588 on ResourcePermission (roleId);
create index IX_F4555981 on ResourcePermission (scope);

create unique index IX_BA497163 on ResourceTypePermission (companyId, groupId, name[$COLUMN_LENGTH:75$], roleId);
create index IX_7D81F66F on ResourceTypePermission (companyId, name[$COLUMN_LENGTH:75$], roleId);
create index IX_A82690E2 on ResourceTypePermission (roleId);

create unique index IX_A88E424E on Role_ (companyId, classNameId, classPK);
create unique index IX_EBC931B8 on Role_ (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_F3E1C6FC on Role_ (companyId, type_);
create index IX_F436EC8E on Role_ (name[$COLUMN_LENGTH:75$]);
create index IX_5EB4E2FB on Role_ (subtype[$COLUMN_LENGTH:75$]);
create index IX_CBE204 on Role_ (type_, subtype[$COLUMN_LENGTH:75$]);
create index IX_B9FF6043 on Role_ (uuid_[$COLUMN_LENGTH:75$], companyId);

create unique index IX_4F0315B8 on ServiceComponent (buildNamespace[$COLUMN_LENGTH:75$], buildNumber);

create index IX_F542E9BC on SocialActivity (activitySetId);
create index IX_D0E9029E on SocialActivity (classNameId, classPK, type_);
create index IX_64B1BC66 on SocialActivity (companyId);
create index IX_FB604DC7 on SocialActivity (groupId, userId, classNameId, classPK, type_, receiverUserId);
create unique index IX_8F32DEC9 on SocialActivity (groupId, userId, createDate, classNameId, classPK, type_, receiverUserId);
create index IX_1F00C374 on SocialActivity (mirrorActivityId, classNameId, classPK);
create index IX_121CA3CB on SocialActivity (receiverUserId);
create index IX_3504B8BC on SocialActivity (userId);

create index IX_83E16F2F on SocialActivityAchievement (groupId, firstInGroup);
create index IX_8F6408F0 on SocialActivityAchievement (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_AABC18E9 on SocialActivityAchievement (groupId, userId, firstInGroup);
create unique index IX_D4390CAA on SocialActivityAchievement (groupId, userId, name[$COLUMN_LENGTH:75$]);

create index IX_A4B9A23B on SocialActivityCounter (classNameId, classPK);
create unique index IX_1B7E3B67 on SocialActivityCounter (groupId, classNameId, classPK, name[$COLUMN_LENGTH:75$], ownerType, endPeriod);
create unique index IX_374B35AE on SocialActivityCounter (groupId, classNameId, classPK, name[$COLUMN_LENGTH:75$], ownerType, startPeriod);
create index IX_926CDD04 on SocialActivityCounter (groupId, classNameId, classPK, ownerType);

create index IX_B15863FA on SocialActivityLimit (classNameId, classPK);
create unique index IX_F1C1A617 on SocialActivityLimit (groupId, userId, classNameId, classPK, activityType, activityCounterName[$COLUMN_LENGTH:75$]);
create index IX_6F9EDE9F on SocialActivityLimit (userId);

create index IX_4460FA14 on SocialActivitySet (classNameId, classPK, type_);
create index IX_9BE30DDF on SocialActivitySet (groupId, userId, classNameId, type_);
create index IX_F71071BD on SocialActivitySet (groupId, userId, type_);
create index IX_62AC101A on SocialActivitySet (userId, classNameId, classPK, type_);

create index IX_384788CD on SocialActivitySetting (groupId, activityType);
create index IX_D984AABA on SocialActivitySetting (groupId, classNameId, activityType, name[$COLUMN_LENGTH:75$]);

create index IX_95135D1C on SocialRelation (companyId, type_);
create index IX_C31A64C6 on SocialRelation (type_);
create index IX_4B52BE89 on SocialRelation (userId1, type_);
create unique index IX_12A92145 on SocialRelation (userId1, userId2, type_);
create index IX_3F9C2FA8 on SocialRelation (userId2, type_);
create index IX_5B30F663 on SocialRelation (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_D3425487 on SocialRequest (classNameId, classPK, type_, receiverUserId, status);
create index IX_A90FE5A0 on SocialRequest (companyId);
create index IX_D9380CB7 on SocialRequest (receiverUserId, status);
create unique index IX_36A90CA7 on SocialRequest (userId, classNameId, classPK, type_, receiverUserId);
create index IX_CC86A444 on SocialRequest (userId, classNameId, classPK, type_, status);
create index IX_AB5906A8 on SocialRequest (userId, status);
create index IX_8D42897C on SocialRequest (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4F973EFE on SocialRequest (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_786D171A on Subscription (companyId, classNameId, classPK);
create unique index IX_2E1A92D4 on Subscription (companyId, userId, classNameId, classPK);
create index IX_1290B81 on Subscription (groupId, userId);
create index IX_E8F34171 on Subscription (userId, classNameId);

create index IX_FFCBB747 on SystemEvent (groupId, classNameId, classPK, type_);
create index IX_A19C89FF on SystemEvent (groupId, systemEventSetKey);

create unique index IX_143DC786 on Team (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_5D47F637 on Team (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_39F69E79 on Team (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_1E8DFB2E on Ticket (classNameId, classPK, type_);
create index IX_B2468446 on Ticket (key_[$COLUMN_LENGTH:75$]);

create unique index IX_B35F73D5 on TrashEntry (classNameId, classPK);
create index IX_2674F2A8 on TrashEntry (companyId);
create index IX_FC4EEA64 on TrashEntry (groupId, classNameId);
create index IX_6CAAE2E8 on TrashEntry (groupId, createDate);

create unique index IX_630A643B on TrashVersion (classNameId, classPK);
create index IX_72D58D37 on TrashVersion (entryId, classNameId);

create unique index IX_23EAD0D on UserGroup (companyId, name[$COLUMN_LENGTH:75$]);
create index IX_69771487 on UserGroup (companyId, parentUserGroupId);
create index IX_72394F8E on UserGroup (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_CAB0CCC8 on UserGroupGroupRole (groupId, roleId);
create index IX_1CDF88C on UserGroupGroupRole (roleId);
create index IX_73C52252 on UserGroupGroupRole (userGroupId, groupId);

create index IX_871412DF on UserGroupRole (groupId, roleId);
create index IX_887A2C95 on UserGroupRole (roleId);
create index IX_4D040680 on UserGroupRole (userId, groupId);

create index IX_2AC5356C on UserGroups_Teams (companyId);
create index IX_31FB0B08 on UserGroups_Teams (teamId);
create index IX_7F187E63 on UserGroups_Teams (userGroupId);

create unique index IX_41A32E0D on UserIdMapper (type_[$COLUMN_LENGTH:75$], externalUserId[$COLUMN_LENGTH:75$]);
create unique index IX_D1C44A6E on UserIdMapper (userId, type_[$COLUMN_LENGTH:75$]);

create unique index IX_8B6E3ACE on UserNotificationDelivery (userId, portletId[$COLUMN_LENGTH:200$], classNameId, notificationType, deliveryType);

create index IX_BF29100B on UserNotificationEvent (type_[$COLUMN_LENGTH:200$]);
create index IX_5CE95F03 on UserNotificationEvent (userId, actionRequired, archived);
create index IX_3DBB361A on UserNotificationEvent (userId, archived);
create index IX_E32CC19 on UserNotificationEvent (userId, delivered, actionRequired);
create index IX_C4EFBD45 on UserNotificationEvent (userId, deliveryType, actionRequired, archived);
create index IX_A87A585C on UserNotificationEvent (userId, deliveryType, archived);
create index IX_A6F83617 on UserNotificationEvent (userId, deliveryType, delivered, actionRequired);
create index IX_8FB65EC1 on UserNotificationEvent (userId, type_[$COLUMN_LENGTH:200$], deliveryType, delivered);
create index IX_A6BAFDFE on UserNotificationEvent (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_29BA1CF5 on UserTracker (companyId);
create index IX_46B0AE8E on UserTracker (sessionId[$COLUMN_LENGTH:200$]);
create index IX_E4EFBA8D on UserTracker (userId);

create index IX_14D8BCC0 on UserTrackerPath (userTrackerId);

create index IX_BCFDA257 on User_ (companyId, createDate, modifiedDate);
create index IX_C6EA4F34 on User_ (companyId, defaultUser, status);
create unique index IX_615E9F7A on User_ (companyId, emailAddress[$COLUMN_LENGTH:75$]);
create index IX_1D731F03 on User_ (companyId, facebookId);
create index IX_B6E3AE1 on User_ (companyId, googleUserId[$COLUMN_LENGTH:75$]);
create index IX_EE8ABD19 on User_ (companyId, modifiedDate);
create index IX_89509087 on User_ (companyId, openId[$COLUMN_LENGTH:1024$]);
create unique index IX_C5806019 on User_ (companyId, screenName[$COLUMN_LENGTH:75$]);
create index IX_F6039434 on User_ (companyId, status);
create unique index IX_9782AD88 on User_ (companyId, userId);
create unique index IX_5ADBE171 on User_ (contactId);
create index IX_762F63C6 on User_ (emailAddress[$COLUMN_LENGTH:75$]);
create index IX_A18034A4 on User_ (portraitId);
create index IX_405CC0E on User_ (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_3499B657 on Users_Groups (companyId);
create index IX_C4F9E699 on Users_Groups (groupId);
create index IX_F10B6C6B on Users_Groups (userId);

create index IX_5FBB883C on Users_Orgs (companyId);
create index IX_7EF4EC0E on Users_Orgs (organizationId);
create index IX_FB646CA6 on Users_Orgs (userId);

create index IX_F987A0DC on Users_Roles (companyId);
create index IX_C19E5F31 on Users_Roles (roleId);
create index IX_C1A01806 on Users_Roles (userId);

create index IX_799F8283 on Users_Teams (companyId);
create index IX_4D06AD51 on Users_Teams (teamId);
create index IX_A098EFBF on Users_Teams (userId);

create index IX_BB65040C on Users_UserGroups (companyId);
create index IX_66FF2503 on Users_UserGroups (userGroupId);
create index IX_BE8102D6 on Users_UserGroups (userId);

create unique index IX_A083D394 on VirtualHost (companyId, layoutSetId);
create unique index IX_431A3960 on VirtualHost (hostname[$COLUMN_LENGTH:75$]);

create unique index IX_97DFA146 on WebDAVProps (classNameId, classPK);

create index IX_1AA07A6D on Website (companyId, classNameId, classPK, primary_);
create index IX_F75690BB on Website (userId);
create index IX_712BCD35 on Website (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_A4DB1F0F on WorkflowDefinitionLink (companyId, workflowDefinitionName[$COLUMN_LENGTH:75$], workflowDefinitionVersion);
create index IX_705B40EE on WorkflowDefinitionLink (groupId, companyId, classNameId, classPK, typePK);

create index IX_415A7007 on WorkflowInstanceLink (groupId, companyId, classNameId, classPK);