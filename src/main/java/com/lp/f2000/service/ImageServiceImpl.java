package com.lp.f2000.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lp.f2000.entity.Image;
import com.lp.f2000.mapper.ImageMapper;

public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public int insert(Image image) {
		// TODO Auto-generated method stub
		return imageMapper.insert(image);
	}

	
	@Override
	public int imageCountByType(int rid, int rtype) {
		// TODO Auto-generated method stub
		return imageMapper.imageCountByType(rid, rtype);
	}


	@Override
	public void updateImageUrl(int id, String post_url) {
		// TODO Auto-generated method stub
		imageMapper.updateImageUrl(id, post_url);
	}


	@Override
	public void deleteImage(int id) {
		// TODO Auto-generated method stub
		imageMapper.deleteImage(id);
	}
	
	

}
