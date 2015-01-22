package ${package};
<#list imports as import>
import ${import}
</#list>
import java.util.List;
import java.util.Map;
import com.pahaoche.base.page.Page;
import com.pahaoche.base.page.QueryResult;
/**
 * dao
 * @author ${user}
 *
 */
public interface ${bean}Dao {

	${bean} queryById(Object id);

	List<${bean}> list(Map<?,?> map);

	QueryResult<${bean}> listPage(Map<?,?> map, Page<${bean}> page);

	int update(${bean} entity);
	
	int create(${bean} entity);
}