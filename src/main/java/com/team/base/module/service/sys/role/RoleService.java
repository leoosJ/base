package com.team.base.module.service.sys.role;

import com.team.base.base.crud.CrudService;
import com.team.base.module.entity.sys.role.Role;
import com.team.base.module.entity.sys.role.RoleSelect;
import com.team.base.module.mapper.sys.role.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService extends CrudService<RoleMapper, Role> {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 获取角色下拉列表
     *
     * @return
     */
    public List<RoleSelect> getRoleSelectList() {
        return this.roleMapper.getRoleSelectList();
    }

}
