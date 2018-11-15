package com.lp.f2000.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Image;

public interface ImageMapper {
	
	@Insert("INSERT INTO image(post_url, resource_id, resource_type) "
			+ "VALUES(#{postUrl}, #{resourceId}, #{resourceType})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Image image);

	@Select("SELECT * FROM image WHERE resource_id = #{rid} and resource_type = #{rtype} and is_valid=1 ")
	public List<Image> getImagesByType(@Param("rid") int rid, @Param("rtype") int rtype);
	
	@Select("SELECT count(*) FROM image WHERE resource_id = #{rid} and resource_type = #{rtype} and is_valid=1 ")
	public int imageCountByType(@Param("rid") int rid,  @Param("rtype") int rtype);
	
	@Update("UPDATE image SET post_url=#{post_url} WHERE id=#{id}")
	public void updateImageUrl(@Param("id") int id, @Param("post_url") String post_url);
	
	@Update("UPDATE image SET is_valid = 0  WHERE id=#{id} ")
	public void deleteImage(@Param("id") int id);
}
