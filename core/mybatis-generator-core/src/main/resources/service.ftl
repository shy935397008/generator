package ${package}.service;
import java.util.List;
import java.util.Map;
import 
public class ${className}Service {
	@Resource
	${package}.dao.${className}Dao;
	
	${className} queryById(Object id){
		rerturn dao.queryById(id);
	};

	List<${className}> list(Map<?,?> map){
		return dao.list(map);
	};


	int update(${className} entity){
		return dao.update(entity);
	};
	
	int create(${className} entity){
		dao.create(entity);
	}
}