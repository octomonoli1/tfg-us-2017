create table SyncDLFileVersionDiff (
	syncDLFileVersionDiffId LONG not null primary key,
	fileEntryId LONG,
	sourceFileVersionId LONG,
	targetFileVersionId LONG,
	dataFileEntryId LONG,
	size_ LONG,
	expirationDate DATE null
);

create table SyncDLObject (
	syncDLObjectId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createTime LONG,
	modifiedTime LONG,
	repositoryId LONG,
	parentFolderId LONG,
	treePath STRING null,
	name VARCHAR(255) null,
	extension VARCHAR(75) null,
	mimeType VARCHAR(75) null,
	description STRING null,
	changeLog VARCHAR(75) null,
	extraSettings TEXT null,
	version VARCHAR(75) null,
	versionId LONG,
	size_ LONG,
	checksum VARCHAR(75) null,
	event VARCHAR(75) null,
	lastPermissionChangeDate DATE null,
	lockExpirationDate DATE null,
	lockUserId LONG,
	lockUserName VARCHAR(75) null,
	type_ VARCHAR(75) null,
	typePK LONG,
	typeUuid VARCHAR(75) null
);

create table SyncDevice (
	uuid_ VARCHAR(75) null,
	syncDeviceId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	type_ VARCHAR(75) null,
	buildNumber LONG,
	featureSet INTEGER,
	hostname VARCHAR(75) null,
	status INTEGER
);