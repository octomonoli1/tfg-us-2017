alter table Company add homeURL STRING null;

alter table ExpandoColumn add companyId LONG;
alter table ExpandoColumn add defaultData STRING null;
alter table ExpandoColumn add typeSettings TEXT null;

alter table ExpandoRow add companyId LONG;

alter table ExpandoTable add companyId LONG;

alter table ExpandoValue add companyId LONG;

alter table JournalArticleImage add elInstanceId VARCHAR(75) null;

alter table JournalStructure add parentStructureId VARCHAR(75);

create table MBMailingList (
	uuid_ VARCHAR(75) null,
	mailingListId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	categoryId LONG,
	emailAddress VARCHAR(75) null,
	inProtocol VARCHAR(75) null,
	inServerName VARCHAR(75) null,
	inServerPort INTEGER,
	inUseSSL BOOLEAN,
	inUserName VARCHAR(75) null,
	inPassword VARCHAR(75) null,
	inReadInterval INTEGER,
	outEmailAddress VARCHAR(75) null,
	outCustom BOOLEAN,
	outServerName VARCHAR(75) null,
	outServerPort INTEGER,
	outUseSSL BOOLEAN,
	outUserName VARCHAR(75) null,
	outPassword VARCHAR(75) null,
	active_ BOOLEAN
);

alter table Organization_ add type_ VARCHAR(75);

alter table Role_ add title STRING null;
alter table Role_ add subtype VARCHAR(75);

alter table TagsAsset add visible BOOLEAN;

COMMIT_TRANSACTION;

update TagsAsset set visible = TRUE;

alter table TagsEntry add groupId LONG;
alter table TagsEntry add parentEntryId LONG;
alter table TagsEntry add vocabularyId LONG;

COMMIT_TRANSACTION;

update TagsEntry set groupId = 0;
update TagsEntry set parentEntryId = 0;
update TagsEntry set vocabularyId = 0;

create table TagsVocabulary (
	vocabularyId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	folksonomy BOOLEAN
);

alter table User_ add reminderQueryQuestion VARCHAR(75) null;
alter table User_ add reminderQueryAnswer VARCHAR(75) null;

create table WSRPConfiguredProducer (
	configuredProducerId LONG not null primary key,
	name VARCHAR(75) null,
	portalId VARCHAR(75) null,
	namespace VARCHAR(75) null,
	producerURL VARCHAR(256) null,
	producerVersion VARCHAR(75) null,
	producerMarkupURL VARCHAR(256) null,
	status INTEGER,
	registrationData TEXT null,
	registrationContext TEXT null,
	serviceDescription TEXT null,
	userCategoryMapping TEXT null,
	customUserProfile TEXT null,
	identityPropagationType VARCHAR(75) null,
	lifetimeTerminationTime VARCHAR(75) null,
	sdLastModified LONG,
	entityVersion INTEGER
);

create table WSRPConsumerRegistration (
	consumerRegistrationId LONG not null primary key,
	consumerName VARCHAR(100) null,
	status BOOLEAN,
	registrationHandle VARCHAR(75) null,
	registrationData TEXT null,
	lifetimeTerminationTime VARCHAR(75) null,
	producerKey VARCHAR(75) null
);

create table WSRPPortlet (
	portletId LONG not null primary key,
	name VARCHAR(75) null,
	channelName VARCHAR(75) null,
	title VARCHAR(75) null,
	shortTitle VARCHAR(75) null,
	displayName VARCHAR(75) null,
	keywords VARCHAR(75) null,
	status INTEGER,
	producerEntityId VARCHAR(75) null,
	consumerId VARCHAR(75) null,
	portletHandle VARCHAR(75) null,
	mimeTypes STRING null
);

create table WSRPProducer (
	producerId LONG not null primary key,
	portalId VARCHAR(75) null,
	status BOOLEAN,
	namespace VARCHAR(75) null,
	instanceName VARCHAR(75) null,
	requiresRegistration BOOLEAN,
	supportsInbandRegistration BOOLEAN,
	version VARCHAR(75) null,
	offeredPortlets STRING null,
	producerProfileMap VARCHAR(75) null,
	registrationProperties STRING null,
	registrationValidatorClass VARCHAR(200) null
);