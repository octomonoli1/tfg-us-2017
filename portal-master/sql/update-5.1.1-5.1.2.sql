alter table WikiPage add minorEdit BOOLEAN;

COMMIT_TRANSACTION;

update WikiPage set minorEdit = FALSE;