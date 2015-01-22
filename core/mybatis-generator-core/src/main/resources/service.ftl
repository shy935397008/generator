package ${package};
<#list imports as import>
import ${import}
</#list>
import java.util.List;
import java.util.Map;
import com.pahaoche.base.page.Page;
import com.pahaoche.base.page.QueryResult;
/**
 * service
 * @author ${user}
 *
 */
public class ${bean}Service {
	@Resource
	${dao} dao;
	${bean} queryById(Object id){
		rerturn dao.queryById(id);
	};

	List<${bean}> list(Map<?,?> map){
		return dao.list(map);
	};

	QueryResult<${bean}> listPage(Map<?,?> map, Page<${bean}> page){
		return dao.listPage(map,page);
	};

	int update(${bean} entity){
		return dao.update(entity);
	};
	
	int create(${bean} entity){
		dao.create(entity);
	}
}