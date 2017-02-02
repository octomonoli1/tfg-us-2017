alter table JournalArticleResource add uuid_ VARCHAR(75) null;

create table SocialEquityGroupSetting (
	equityGroupSettingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	classNameId LONG,
	type_ INTEGER,
	enabled BOOLEAN
);