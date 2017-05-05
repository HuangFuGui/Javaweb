package mapper;

import org.apache.ibatis.annotations.Param;
import po.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by huangfugui on 2017/4/4.
 */
public interface RoleMapper {
    Role getRole(Long id);
    int deleteRole(Long id);
    int insertRole(Role role);
    List<Role> findRoleByMap(Map<String,String> params);
    List<Role> findRoleByMap(@Param("roleName") String roleName,@Param("note") String note);
    List<Role> findRoles(@Param("roleName") String roleName);
}