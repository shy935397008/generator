package ${package};

public class ${className} {

	<#list propertites as propertity>
	private ${propertity.className} ${propertity.propertity};
	
	public ${propertity.className} get${propertity.propertity?cap_first}() {
		return ${propertity.propertity};
	}

	public void set${propertity.propertity?cap_first}(${propertity.className} ${propertity.propertity}) {
		this.${propertity.propertity} = ${propertity.propertity};
	}
	</#list>
}
