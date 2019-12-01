package com.jt.oauth2.server.service;

import com.jt.oauth2.server.domain.TbUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.jt.oauth2.server.mapper.TbUserMapper;

@Service
public interface TbUserService{
    TbUser getByUsername(String username);
}
