package com.lp.f2000.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lp.f2000.entity.User;


public interface UserMapper {

	@Select("SELECT * FROM user WHERE id=#{id} AND is_valid=#{isValid}")
	public User getUserById(@Param("id") int id, @Param("isValid") int isValid);

	@Select("SELECT * FROM user WHERE wx_openid=#{wxOpenid} AND is_valid=1")
	public User getByWxOpenid(@Param("wxOpenid") String wxOpenid);
	
	@Insert("INSERT INTO user(wx_openid, wx_nicname) "
			+ "VALUES(#{wxOpenid}, #{wxNicname})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertUser(User user);
	
}
