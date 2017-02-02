<html>

<body>

<h1>${path}</h1>

<table>
<tr>
	<td>
		<strong>Name</strong>
	</td>
	<td>
		<strong>Modified</strong>
	</td>
	<td>
		<strong>Size</strong>
	</td>
	<td>
		<strong>Description</strong>
	</td>
</tr>

<#list entries as entry>
	<tr>
		<td>
			<a href="${entry.path}">${entry.name}</a>
		</td>
		<td>
			<#if entry.getModifiedDate()??>
				${dateFormat.format(entry.modifiedDate)}
			<#else>
				-
			</#if>
		</td>
		<td>
			${entry.size}
		</td>
		<td>
			${entry.description}
		</td>
	</tr>
</#list>

</table>

<#if releaseInfo??>
	<hr />

	<i>${releaseInfo}</i>
</#if>

</body>

</html>