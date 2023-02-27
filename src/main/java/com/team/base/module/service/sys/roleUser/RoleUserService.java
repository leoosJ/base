package com.team.base.module.service.sys.roleUser;

import cn.hutool.core.collection.CollectionUtil;
import com.team.base.base.crud.CrudService;
import com.team.base.module.entity.sys.roleUser.AssignmentUser;
import com.team.base.module.entity.sys.roleUser.RoleUser;
import com.team.base.module.mapper.sys.roleUser.RoleUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleUserService extends CrudService<RoleUserMapper, RoleUser> {

    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 根据角色ID查询已分配用户列表
     *
     * @author JLP
     * @param roleId
     * @return
     * @date 2023-02-20
     */
    public List<String> getUserIdListByRoleId(String roleId) {
        return this.roleUserMapper.getUserIdListByRoleId(roleId);
    }

    /**
     * 分配用户
     *
     * @author JLP
     * @param assignmentUser
     * @return
     * @date 2023-02-20
     */
    @Transactional(readOnly = false)
    public Boolean assignmentUser(AssignmentUser assignmentUser) {
        try{
            // 删除原有角色用户关联（前端列表变化，方便处理，先删除再统一新增）
            this.roleUserMapper.deleteByRoleId(assignmentUser.getRoleId());

            // 新增关联关系
            List<String> userIdList = assignmentUser.getUserIdList();
            if (CollectionUtil.isNotEmpty(userIdList)) {
                for (String userId: userIdList) {
                    RoleUser roleUser = new RoleUser();
                    roleUser.setRoleId(assignmentUser.getRoleId());
                    roleUser.setUserId(userId);
                    this.save(roleUser);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存用户角色信息
     *
     * @param roleId
     * @param userId
     * @return
     */
    public RoleUser saveRoleUser(String roleId, String userId) {
        RoleUser roleUser = this.roleUserMapper.getRoleUserByUserId(userId);
        if (roleUser == null) {
            roleUser = new RoleUser();
        }
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        this.save(roleUser);
        return roleUser;
    }

}
