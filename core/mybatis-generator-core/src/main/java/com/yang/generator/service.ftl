package ${package};
import java.util.List;
import java.util.Map;
<#list import as imp>
import ${imp};
</#list>
public class ${className}Service {
	//@Resource
	${className}Dao dao;
	
	${className} queryById(Object id){
		return dao.queryById(id);
	}

	List<${className}> list(Map<?,?> map){
		return dao.list(map);
	}


	int update(${className} entity){
		return dao.update(entity);
	}
	
	int insert(${className} entity){
		return dao.insert(entity);
	}
}