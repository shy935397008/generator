package ${package};
<#list import as imp>
import ${imp};
</#list>
import java.util.List;
import java.util.Map;

public interface ${className}Dao {

	${className} queryById(Object id);

	List<${className}> list(Map<?,?> map);

	int update(${className} entity);
	
	int insert(${className} entity);
}