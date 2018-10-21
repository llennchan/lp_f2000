package com.lp.f2000.service;

import java.util.List;

import com.lp.f2000.entity.Image;

public interface ImageService {
	public int insert(Image image);
	
	public List<Image> getImagesByType(int rid, int rtype);
	
	public int imageCountByType(int rid, int rtype);
	
	public void addImageUrl(Image img);
	
	public void updateImageUrl(int id, String post_url);
	
	public void deleteImage(int id);
	
}
