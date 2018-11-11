ALTER TABLE coupon_code ADD INDEX idx_coupcode_cid(coupon_id);  
ALTER TABLE coupon_product ADD INDEX idx_coupprodt_cid(coupon_id);
ALTER TABLE product_order ADD INDEX idx_porder_skunum(sku_id, is_valid, num);
ALTER TABLE address ADD INDEX idx_address_uid(user_id);