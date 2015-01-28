package ${package};
<#list import as imp>
import ${imp};
</#list>
public class ${className} {

	<#list properties as property>
	private ${property.className} ${property.property};
	
	public ${property.className} get${property.property?cap_first}() {
		return ${property.property};
	}

	public void set${property.property?cap_first}(${property.className} ${property.property}) {
		this.${property.property} = ${property.property};
	}
	</#list>
}
