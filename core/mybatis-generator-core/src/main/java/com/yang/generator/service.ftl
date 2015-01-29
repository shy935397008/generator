package ${package};
import java.util.List;
import java.util.Map;
<#list import as imp>
import ${imp};
</#list>
public class ${className}Service {
	//@Resource
	${className}Dao dao;
	
	public ${className} queryById(Object id){
		return dao.queryById(id);
	}

	public List<${className}> list(Map<?,?> map){
		return dao.list(map);
	}


	public int update(${className} entity){
		return dao.update(entity);
	}
	
	public int insert(${className} entity){
		return dao.insert(entity);
	}
}