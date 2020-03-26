package com.bravechen.spring.security.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bravechen.spring.security.demo.entity.Account;
import com.bravechen.spring.security.demo.entity.Role;
import com.bravechen.spring.security.demo.mapper.AccountMapper;
import com.bravechen.spring.security.demo.mapper.RoleMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class AccountService implements UserDetailsService {
    private final AccountMapper accountMapper;
    private final RoleMapper roleMapper;

    public AccountService(AccountMapper accountMapper, RoleMapper roleMapper) {
        this.accountMapper = accountMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Account> wrapper = Wrappers.lambdaQuery();
        wrapper.ge(Account::getUsername, username);
        Account account = this.accountMapper.selectOne(wrapper);
        if (account != null) {
            LambdaQueryWrapper<Role> roleWrapper = Wrappers.lambdaQuery();
            roleWrapper.inSql(Role::getId, "select role_id from account_role_relation where account_id = " + account.getId());
            List<Role> roles = roleMapper.selectList(roleWrapper);
            account.setRoles(roles);
        }
        return account;
    }
}
