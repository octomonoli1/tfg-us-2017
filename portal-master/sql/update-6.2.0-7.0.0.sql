alter table BlogsEntry add subtitle STRING null;
alter table BlogsEntry add coverImageCaption STRING null;
alter table BlogsEntry add coverImageFileEntryId LONG;
alter table BlogsEntry add coverImageURL STRING null;
alter table BlogsEntry add smallImageFileEntryId LONG;

alter table Contact_ drop column aimSn;
alter table Contact_ drop column icqSn;
alter table Contact_ drop column msnSn;
alter table Contact_ drop column mySpaceSn;
alter table Contact_ drop column ymSn;

drop table CyrusUser;

drop table CyrusVirtual;

drop index IX_C803899D on DDMStructureLink;

drop index IX_F8E90438 on DLFileEntryMetadata;

alter table DLFileEntryMetadata drop column fileEntryTypeId;

alter table DLFolder add restrictionType INTEGER;

update DLFolder set restrictionType = 1 where overrideFileEntryTypes = TRUE;

alter table DLFolder drop column overrideFileEntryTypes;

create table ExportImportConfiguration (
	mvccVersion LONG default 0 not null,
	exportImportConfigurationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(200) null,
	description STRING null,
	type_ INTEGER,
	settings_ TEXT null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

alter table Layout drop column iconImage;
alter table Layout drop column wapThemeId;
alter table Layout drop column wapColorSchemeId;

alter table LayoutRevision drop column iconImage;
alter table LayoutRevision drop column wapThemeId;
alter table LayoutRevision drop column wapColorSchemeId;

alter table Organization_ add logoId LONG;

alter table RatingsEntry add uuid_ VARCHAR(75) null;

create table RecentLayoutBranch (
	mvccVersion LONG default 0 not null,
	recentLayoutBranchId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	layoutBranchId LONG,
	layoutSetBranchId LONG,
	plid LONG
);

create table RecentLayoutRevision (
	mvccVersion LONG default 0 not null,
	recentLayoutRevisionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	layoutRevisionId LONG,
	layoutSetBranchId LONG,
	plid LONG
);

create table RecentLayoutSetBranch (
	mvccVersion LONG default 0 not null,
	recentLayoutSetBranchId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	layoutSetBranchId LONG,
	layoutSetId LONG
);

insert into Region (regionId, countryId, regionCode, name, active_) values (33001, 33, 'AT-1', 'Burgenland', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33002, 33, 'AT-2', 'Kärnten', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33003, 33, 'AT-3', 'Niederösterreich', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33004, 33, 'AT-4', 'Oberösterreich', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33005, 33, 'AT-5', 'Salzburg', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33006, 33, 'AT-6', 'Steiermark', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33007, 33, 'AT-7', 'Tirol', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33008, 33, 'AT-8', 'Vorarlberg', TRUE);
insert into Region (regionId, countryId, regionCode, name, active_) values (33009, 33, 'AT-9', 'Vienna', TRUE);

update Region set regionCode = 'BB' where regionId = 4004 and regionCode = 'BR';
update Region set name = 'Monza e Brianza', regionCode = 'MB' where regionId = 8060 and regionCode = 'MZ';

alter table ResourcePermission add primKeyId LONG;
alter table ResourcePermission add viewActionId BOOLEAN;

alter table Subscription add groupId LONG;

alter table Team add uuid_ VARCHAR(75);

alter table User_ add googleUserId VARCHAR(75) null;

alter table UserNotificationEvent add deliveryType INTEGER;
alter table UserNotificationEvent add actionRequired BOOLEAN;