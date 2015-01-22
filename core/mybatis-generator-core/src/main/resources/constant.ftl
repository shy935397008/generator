package ${package};

public enum ${className}{

	<#list propertites as propertity>
	${propertity.propertity?upper_case}("${propertity.propertity}","${propertity.value}"),
	
	</#list>;
	private String key;
	
	private Object value;
	
	private ${className}(String a, Object b) {
		this.key = a;
		this.value = b;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}