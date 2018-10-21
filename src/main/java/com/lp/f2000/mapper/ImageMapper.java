package com.lp.f2000.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Image;

public interface ImageMapper {
	
	@Insert("INSERT INTO image(post_url, resource_id, resource_type) "
			+ "VALUES(#{postUrl}, #{resourceId}, #{resourceType})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Image image);

	@Select("SELECT * FROM image WHERE resource_id = #{rid} and resource_type = #{rtype} and is_valid=1 ")
	public List<Image> getImagesByType(int rid, int rtype);
	
	@Select("SELECT count(*) FROM image WHERE resource_id = #{rid} and resource_type = #{rtype} and is_valid=1 ")
	public int imageCountByType(int rid, int rtype);
	
	@Update("UPDATE image SET post_url=#{post_url} WHERE id=#{id}")
	public void updateImageUrl(int id, String post_url);
	
	@Update("UPDATE image SET is_valid = 0  WHERE id=#{id} ")
	public void deleteImage(int id);
}
