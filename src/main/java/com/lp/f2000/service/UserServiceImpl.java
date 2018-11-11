package com.lp.f2000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.Address;
import com.lp.f2000.entity.CartProduct;
import com.lp.f2000.entity.PfArea;
import com.lp.f2000.entity.PfCity;
import com.lp.f2000.entity.PfProvince;
import com.lp.f2000.entity.User;
import com.lp.f2000.mapper.CartProductMapper;
import com.lp.f2000.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CartProductMapper cartProductMapper;
	
	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id, 1);
	}
	
	@Override
	public User getByWxOpenid(String wxOpenid) {
		return userMapper.getByWxOpenid(wxOpenid);
	}
	
	@Override
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public int addCartProduct(CartProduct cartProduct) {
		// TODO Auto-generated method stub
		return cartProductMapper.insertCartProduct(cartProduct);
	}
	
	@Override
	public CartProduct getCartProductById(int cartProductId) {
		return cartProductMapper.getCartProductById(cartProductId);
	}

	@Override
	public void deleteCartProduct(int cpid) {
		// TODO Auto-generated method stub
		cartProductMapper.deleteCartProduct(cpid);
	}

	@Override
	public List<CartProduct> ListCartProducts(int uid) {
		// TODO Auto-generated method stub
		return cartProductMapper.getCartProductsByUid(uid);
	}
	
	@Override
	public void updateCartProductNum(int cpid, int num) {
		cartProductMapper.updateCartProductNum(cpid, num);
	}
	
	@Override
	public int insertAddress(Address address) {
		return userMapper.insertAddress(address);
	}
	
	@Override
	public void updateAddress(Address address) {
		userMapper.updateAddress(address);
	}
	
	@Override
	public List<Address> getAddressesByuid(int uid) {
		return userMapper.getAddressesByuid(uid);
	}
	
	@Override
	public Address getDefaultAddressByuid(int uid) {
		return userMapper.getDefaultAddressByuid(uid);
	}
	
	@Override
	public Address getAddressByid(int id) {
		return userMapper.getAddressByid(id);
	}
	
	@Override
	public void setAddressDefault(int aid) {
		userMapper.setAddressDefault(aid);
	}
	
	@Override
	public void cancelAddressesDefaultByUid(int uid) {
		userMapper.cancelAddressesDefaultByUid(uid);
	}
	
	@Override
	public void deleteAddress(int aid) {
		userMapper.deleteAddress(aid);
	}
	
	@Override
	public List<PfProvince> getPfProvincesAll(){
		List<PfProvince> ps = userMapper.getPfProvinces();
		List<PfCity> pcs = null;
		List<PfArea> pas = null;
		for(PfProvince p : ps) {
			pcs = userMapper.getPfCities(p.getProvinceId());
			for(PfCity c : pcs) {
				pas = userMapper.getPfareas(c.getCityId());
				c.setPfAreas(pas);
			}
			p.setPfCities(pcs);
		}
		
		return ps;
	}

}
