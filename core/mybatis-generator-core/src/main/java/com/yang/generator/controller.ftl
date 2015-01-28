package ${package};
import java.util.List;
import java.util.Map;
<#list import as imp>
import ${imp};
</#list>
public class ${className}Controller {
	//@Resource
	${className}Service service;
	
	${className} queryById(Object id){
		return service.queryById(id);
	}

	List<${className}> list(Map<?,?> map){
		return service.list(map);
	}


	int update(${className} entity){
		return service.update(entity);
	}
	
	int insert(${className} entity){
		return service.insert(entity);
	}
}