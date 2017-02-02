alter table AssetEntry add socialInformationEquity DOUBLE;

alter table Company add maxUsers INTEGER;

COMMIT_TRANSACTION;

update Company set maxUsers = 0;

update ExpandoTable set name = 'CUSTOM_FIELDS' where name = 'DEFAULT_TABLE';

alter table LayoutSet add settings_ TEXT null;

update MBMessage set categoryId = -1 where groupId = 0;

update MBThread set categoryId = -1 where groupId = 0;

alter table PasswordPolicy add minAlphanumeric INTEGER;
alter table PasswordPolicy add minLowerCase INTEGER;
alter table PasswordPolicy add minNumbers INTEGER;
alter table PasswordPolicy add minSymbols INTEGER;
alter table PasswordPolicy add minUpperCase INTEGER;
alter table PasswordPolicy add resetTicketMaxAge LONG;

COMMIT_TRANSACTION;

update PasswordPolicy set minAlphanumeric = 0;
update PasswordPolicy set minLowerCase = 0;
update PasswordPolicy set minNumbers = 0;
update PasswordPolicy set minSymbols = 0;
update PasswordPolicy set minUpperCase = 0;
update PasswordPolicy set resetTicketMaxAge = 86400;

create table SocialEquityAssetEntry (
	equityAssetEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	assetEntryId LONG,
	informationK DOUBLE,
	informationB DOUBLE,
	informationEquity DOUBLE
);

create table SocialEquityHistory (
	equityHistoryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	personalEquity INTEGER
);

create table SocialEquityLog (
	equityLogId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	assetEntryId LONG,
	actionId VARCHAR(75) null,
	actionDate INTEGER,
	type_ INTEGER,
	value INTEGER,
	validity INTEGER,
	active_ BOOLEAN
);

create table SocialEquitySetting (
	equitySettingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	classNameId LONG,
	actionId VARCHAR(75) null,
	type_ INTEGER,
	value INTEGER,
	validity INTEGER
);

create table SocialEquityUser (
	equityUserId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	contributionEquity DOUBLE,
	participationK DOUBLE,
	participationB DOUBLE,
	participationEquity DOUBLE,
	personalEquity DOUBLE
);

create table Ticket (
	ticketId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	key_ VARCHAR(75) null,
	expirationDate DATE null
);

alter table User_ add socialContributionEquity DOUBLE;
alter table User_ add socialParticipationEquity DOUBLE;
alter table User_ add socialPersonalEquity DOUBLE;