package com.lp.f2000.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lp.f2000.entity.User;


public interface UserMapper {

	@Select("SELECT * FROM user WHERE id=#{id} AND is_valid=#{isValid}")
	public User getUserById(@Param("id") int id, @Param("isValid") int isValid);

}
