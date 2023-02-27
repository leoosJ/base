package com.team.base.module.service.sys.menu;

import com.team.base.base.crud.CrudService;
import com.team.base.module.entity.sys.menu.Menu;
import com.team.base.module.mapper.sys.menu.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService extends CrudService<MenuMapper, Menu> {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 获取菜单树形列表
     *
     * @author JLP
     * @return
     */
    public List<Menu> findMenuTree() {
        return this.menuMapper.findMenuTree();
    }

}
