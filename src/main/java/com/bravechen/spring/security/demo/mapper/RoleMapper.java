package com.bravechen.spring.security.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bravechen.spring.security.demo.entity.Account;
import com.bravechen.spring.security.demo.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
}
