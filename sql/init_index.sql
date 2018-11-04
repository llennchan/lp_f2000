ALTER TABLE coupon_code ADD INDEX idx_coupcode_cid(coupon_id);  
ALTER TABLE coupon_product ADD INDEX idx_coupprodt_cid(coupon_id);