package com.team.base.module.service.sys.roleMenu;

import cn.hutool.core.collection.CollectionUtil;
import com.team.base.base.crud.CrudService;
import com.team.base.module.entity.sys.roleMenu.AssignmentMenu;
import com.team.base.module.entity.sys.roleMenu.RoleMenu;
import com.team.base.module.mapper.sys.roleMenu.RoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleMenuService extends CrudService<RoleMenuMapper, RoleMenu> {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 根据角色ID查询已分配菜单列表
     *
     * @author JLP
     * @param roleId
     * @return
     * @date 2023-02-20
     */
    public List<String> getMenuIdListByRoleId(String roleId) {
        return this.roleMenuMapper.getMenuIdListByRoleId(roleId);
    }

    /**
     * 分配菜单
     *
     * @author JLP
     * @param assignmentMenu
     * @return
     * @date 2023-02-20
     */
    @Transactional(readOnly = false)
    public Boolean assignmentMenu(AssignmentMenu assignmentMenu) {
        try{
            // 删除原有角色菜单关联（前端列表变化，方便处理，先删除再统一新增）
            this.roleMenuMapper.deleteByRoleId(assignmentMenu.getRoleId());

            // 新增关联关系
            List<String> menuIdList = assignmentMenu.getMenuIdList();
            if (CollectionUtil.isNotEmpty(menuIdList)) {
                for (String menuId: menuIdList) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setRoleId(assignmentMenu.getRoleId());
                    roleMenu.setMenuId(menuId);
                    this.save(roleMenu);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
