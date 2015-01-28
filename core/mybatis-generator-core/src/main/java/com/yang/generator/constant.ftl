package ${package};

public enum ${className}{

	<#list properties as property>
	${property.property?upper_case}("${property.property}","${property.value}"),
	
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