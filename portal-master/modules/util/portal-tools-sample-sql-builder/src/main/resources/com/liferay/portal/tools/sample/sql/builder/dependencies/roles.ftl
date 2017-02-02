<#list dataFactory.roleModels as roleModel>
	insert into Role_ values (${roleModel.mvccVersion}, '${roleModel.uuid}', ${roleModel.roleId}, ${roleModel.companyId}, ${roleModel.userId}, '${roleModel.userName}', '${dataFactory.getDateString(roleModel.createDate)}', '${dataFactory.getDateString(roleModel.modifiedDate)}', ${roleModel.classNameId}, ${roleModel.classPK}, '${roleModel.name}', '${roleModel.title}', '${roleModel.description}', ${roleModel.type}, '${roleModel.subtype}');

	<@insertResourcePermissions
		_entry = roleModel
	/>
</#list>