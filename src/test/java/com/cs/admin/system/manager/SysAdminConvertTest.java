package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.system.manager.domain.covert.SysAdminConvert;
import com.cs.admin.system.manager.domain.covert.SysDeptConvert;
import com.cs.admin.system.manager.domain.covert.SysPostConvert;
import com.cs.admin.system.manager.domain.dto.AdminDTO;
import com.cs.admin.system.manager.domain.entity.SysDept;
import com.cs.admin.system.manager.domain.entity.SysPost;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 21:00
 */
@SpringBootTest
class SysAdminConvertTest {

    @Autowired
    private SysAdminConvert sysAdminConvert;

    @Autowired
    private SysPostConvert sysPostConvert;

    @Autowired
    private SysDeptConvert sysDeptConvert;

    @Test
    void toVO() {
        AdminDTO dto = new AdminDTO();
        dto.setAccount("test");
        dto.setUserId(10L);
        dto.setEmail("3069345150@qq.com");
        SysDept dept = new SysDept();
        dept.setDeptId(1L);
        dept.setDeptName("部门名称");
        SysPost post = new SysPost();
        post.setPostId(11L);
        post.setPostCode("java");
        post.setPostName("java开发工程师");
        dto.setDeptList(Collections.singletonList(dept));
        dto.setPostList(Collections.singletonList(post));
        AdminVO adminVO = sysAdminConvert.toVO(dto);
        System.out.println(JSON.toJSONString(adminVO));
        System.out.println(sysPostConvert.toSubVO(post));
        System.out.println(sysDeptConvert.toSubVO(dept));
    }
}