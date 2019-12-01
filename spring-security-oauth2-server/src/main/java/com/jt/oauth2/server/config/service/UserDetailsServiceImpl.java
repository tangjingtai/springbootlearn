package com.jt.oauth2.server.config.service;

import com.jt.oauth2.server.domain.TbPermission;
import com.jt.oauth2.server.domain.TbUser;
import com.jt.oauth2.server.service.TbPermissionService;
import com.jt.oauth2.server.service.TbUserService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = tbUserService.getByUsername(username);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if (tbUser == null) {
            throw new UsernameNotFoundException("User not find.");
        }
        // 获取用户授权
        List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());
        // 声明用户授权
        tbPermissions.forEach(tbPermission -> {
            if (tbPermission != null && tbPermission.getEnname() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                grantedAuthorities.add(grantedAuthority);
            }
        });
        return new User(tbUser.getUsername(), tbUser.getPassword(), grantedAuthorities);
    }
}
