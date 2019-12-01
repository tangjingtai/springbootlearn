package com.jt.oauth2.server.service;

import com.jt.oauth2.server.domain.TbPermission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.jt.oauth2.server.mapper.TbPermissionMapper;

@Service
public interface TbPermissionService{

    List<TbPermission> selectByUserId(Long userId);

}
