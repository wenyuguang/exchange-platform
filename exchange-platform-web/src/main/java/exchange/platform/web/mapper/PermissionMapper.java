package exchange.platform.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import exchange.platform.web.domain.Permission;

@Mapper
public interface PermissionMapper {

	public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
