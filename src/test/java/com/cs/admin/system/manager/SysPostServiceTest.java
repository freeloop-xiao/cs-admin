package com.cs.admin.system.manager;

import com.alibaba.fastjson.JSON;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.dto.PostAddDTO;
import com.cs.admin.system.manager.domain.dto.PostEditDTO;
import com.cs.admin.system.manager.domain.dto.PostPageDTO;
import com.cs.admin.system.manager.domain.vo.PostVO;
import com.cs.admin.system.manager.service.SysPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/1 18:02
 */
@SpringBootTest
class SysPostServiceTest {

    @Autowired
    private SysPostService sysPostService;

    @Test
    void add() {
        PostAddDTO addDTO = new PostAddDTO();
        addDTO.setPostCode("java");
        addDTO.setPostName("java程序员");
        sysPostService.add(addDTO);
        sysPostService.add(addDTO);
    }

    @Test
    void del() {
        sysPostService.del(1L);
    }

    @Test
    void edit() {
        PostEditDTO editDTO = new PostEditDTO();
        editDTO.setPostId(1L);
        editDTO.setPostCode("php");
        editDTO.setPostName("php程序员");
        sysPostService.edit(editDTO);
    }

    @Test
    void getVO() {
        System.out.println(JSON.toJSONString(sysPostService.getVO(1L)));
    }

    @Test
    void page() {
        PostPageDTO pageDTO = new PostPageDTO();
        pageDTO.setKey("php");
        PageVO<PostVO> pageVO = sysPostService.page(pageDTO);
        System.out.println(JSON.toJSONString(pageVO));
    }

    @Test
    void getByUserId() {
        System.out.println(JSON.toJSONString(sysPostService.getByUserId(1L)));
    }
}