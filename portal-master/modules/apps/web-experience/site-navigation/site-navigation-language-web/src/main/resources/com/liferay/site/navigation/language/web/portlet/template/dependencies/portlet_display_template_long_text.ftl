<style>
	.language-entry-long-text {
		display: inline-block;
		padding: 0 0.5em;
	}
</style>

<#if entries?has_content>
	<#list entries as entry>
		<#if !entry.isDisabled()>
			<@liferay_aui["a"]
				cssClass="language-entry-long-text"
				href=entry.getURL()
				label=entry.getLongDisplayName()
				lang=entry.getW3cLanguageId()
			/>
		</#if>
	</#list>
</#if>