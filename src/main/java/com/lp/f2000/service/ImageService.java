package com.lp.f2000.service;

import com.lp.f2000.entity.Image;

public interface ImageService {
	public int insert(Image image);
	
	public int imageCountByType(int rid, int rtype);
	
	public void updateImageUrl(int id, String post_url);
	
	public void deleteImage(int id);
	
}
