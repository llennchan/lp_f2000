package com.lp.f2000.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Address;
import com.lp.f2000.entity.PfArea;
import com.lp.f2000.entity.PfCity;
import com.lp.f2000.entity.PfProvince;
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
	
	
	@Insert("INSERT INTO address(user_id, rec_province, rec_city, rec_area, rec_address, contact_name, contact_phone) "
			+ "VALUES(#{userId}, #{recProvince}, #{recCity}, #{recArea}, #{recAddress}, #{contactName}, #{contactPhone})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertAddress(Address address);
	
	@Update("UPDATE coupon SET user_id=#{userId}, rec_province=#{recProvince}, rec_city=#{recCity}, rec_area=#{recArea}, rec_address=#{recAddress}, contact_name=#{contactName}, contact_phone=#{contactPhone}, update_time=#{updateTime} WHERE id=#{id}")
	public void updateAddress(Address address);
	
	@Select("SELECT * FROM address WHERE user_id=#{uid} AND is_valid=1 order id desc")
	public List<Address> getAddressesByuid(int uid);
	
	@Select("SELECT * FROM address WHERE id=#{id} AND is_valid=1")
	public Address getAddressByid(int id);
	
	@Select("SELECT * FROM address WHERE user_id=#{uid} AND is_default=1 AND is_valid=1")
	public Address getDefaultAddressByuid(int uid);
	
	@Update("update address set is_default = 1 WHERE id=#{aid} AND is_valid=1")
	public void setAddressDefault(int aid);
	
	@Update("update address set is_default = 0 WHERE user_id=#{uid} AND is_valid=1")
	public void cancelAddressesDefaultByUid(int uid);
	
	@Update("update address set is_valid = 0 WHERE id=#{aid} AND is_valid=1")
	public void deleteAddress(int aid);
	
	@Select("SELECT * FROM pf_province order by province_id")
	public List<PfProvince> getPfProvinces();
	
	@Select("SELECT * FROM pf_city where province_id = #{province_id} order by city_id")
	public List<PfCity> getPfCities(int province_id);
	
	@Select("SELECT * FROM pf_area where city_id = #{city_id} order by area_id")
	public List<PfArea> getPfareas(int city_id);
	
}
