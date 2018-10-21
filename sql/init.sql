--  商品表 product
DROP TABLE IF EXISTS `product`;
create table `product`
(
    `id` INT(11) NOT NULL auto_increment,
    `name` VARCHAR(255) COMMENT '名称',
	`desc1` VARCHAR(255) COMMENT '描述1',
	`desc2` VARCHAR(255) COMMENT '描述2',
	`desc3` VARCHAR(255) COMMENT '描述3',
	`is_valid` TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 图片表
DROP TABLE IF EXISTS `image`;
create table `image`
(
    `id` INT(11) NOT NULL auto_increment,
	`post_url` VARCHAR(255) COMMENT '地址',
	`resource_id` INT(11) DEFAULT 0 COMMENT '资源id',
	`resource_type` TINYINT(4) DEFAULT 0 COMMENT '资源类型',
	`is_valid` TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

--  商品分类表 category
DROP TABLE IF EXISTS `category`;
create table category
(
    id INT(11) NOT NULL auto_increment,
	name VARCHAR(255) COMMENT '分类名称',
	cat_type TINYINT(4) DEFAULT 0 COMMENT '分类类型',
	parent_id INT(11) NOT NULL DEFAULT 0,
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

--  分类关联表 categorize
DROP TABLE IF EXISTS `categorize`;
create table categorize
(
    id INT(11) NOT NULL auto_increment,
	resource_id INT(11) DEFAULT 0 COMMENT '资源id',
	resource_type TINYINT(4) DEFAULT 0 COMMENT '资源类型',
	category_id INT(11) NOT NULL DEFAULT 0 COMMENT '分类ID',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品分类关联表';


--  商品规格表 sku
DROP TABLE IF EXISTS `sku`;
create table sku
(
    id INT(11) NOT NULL auto_increment,
	product_id INT(11) NOT NULL DEFAULT 0,
	name VARCHAR(255) COMMENT 'sku名称',
	price DECIMAL(18,2) COMMENT '价格',
	num INT(11) NOT NULL DEFAULT 0 COMMENT '库存',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格表';

--  优惠券表 coupon
DROP TABLE IF EXISTS `coupon`;
create table coupon
(
    id INT(11) NOT NULL auto_increment,
	name VARCHAR(255) COMMENT '优惠券名称',
	coupon_type TINYINT(1) DEFAULT 0 COMMENT '优惠券类型直减券：1；满减券：2；折扣券：3',
	cut_money DECIMAL(18,2) COMMENT '优惠金额',
	discount_rate float COMMENT '优惠折扣率',
	min_cost DECIMAL(18,2) COMMENT '门槛金额',
	max_discount_price DECIMAL(18,2) COMMENT '最高折扣金额',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

--  优惠券适用商品表 coupon_product
DROP TABLE IF EXISTS `coupon_product`;
create table coupon_product
(
    id INT(11) NOT NULL auto_increment,
	coupon_id INT(11) NOT NULL DEFAULT 0 COMMENT '优惠券id',
	product_id INT(11) NOT NULL DEFAULT 0 COMMENT '商品id',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券适用商品表';

--  支付订单表 pay_order
DROP TABLE IF EXISTS `pay_order`;
create table pay_order
(
    id INT(11) NOT NULL auto_increment,
    user_id INT(11) NOT NULL DEFAULT 0 COMMENT '用户id',
	wx_pay_no VARCHAR(255) COMMENT '微信支付编号',
	status TINYINT(4) DEFAULT 0 COMMENT '支付订单状态',
	rec_phone varchar(11) COMMENT  '收货人手机号',
	rec_name varchar(50) COMMENT  '收货人姓名',
	rec_province varchar(50) COMMENT  '省',
	rec_city varchar(50) COMMENT  '市',
	rec_area varchar(50) COMMENT  '区',
	rec_address varchar(50) COMMENT  '详细地址',
	logistics_status TINYINT(4) DEFAULT 0 COMMENT '物流状态',
	order_price DECIMAL(18,2) COMMENT '订单金额',
	discount_price DECIMAL(18,2) COMMENT '优惠金额',
	real_price DECIMAL(18,2) COMMENT '实付金额',
	pay_time timestamp COMMENT '支付时间',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='支付订单表';

--  商品订单表 product_order
DROP TABLE IF EXISTS `product_order`;
create table product_order
(
    id INT(11) NOT NULL auto_increment,
    pay_order_id INT(11) NOT NULL DEFAULT 0 COMMENT '支付订单id',
	user_id INT(11) NOT NULL DEFAULT 0 COMMENT '用户id',
	sku_id INT(11) NOT NULL DEFAULT 0 COMMENT 'sku id',
	price DECIMAL(18,2) COMMENT '价格',	
	status TINYINT(4) DEFAULT 0 COMMENT '状态',
	logistics_status TINYINT(4) DEFAULT 0 COMMENT '物流状态',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品订单表';

--  用户表 user
DROP TABLE IF EXISTS `user`;
create table user
(
    id INT(11) NOT NULL auto_increment,
    wx_openid VARCHAR(255) COMMENT '微信openid',
	wx_nicname VARCHAR(255) COMMENT '微信昵称',
	mobile_number varchar(11) COMMENT '手机号',	
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

--  收货地址表 address
DROP TABLE IF EXISTS `address`;
create table address
(
    id INT(11) NOT NULL auto_increment,
	user_id INT(11) NOT NULL DEFAULT 0 COMMENT '用户id',
	rec_province varchar(50) COMMENT  '省',
	rec_city varchar(50) COMMENT  '市',
	rec_area varchar(50) COMMENT  '区',
	rec_address varchar(50) COMMENT  '详细地址',
    is_default TINYINT(1) DEFAULT 0 COMMENT '是否默认',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

--  用户优惠券表 user_coupon
DROP TABLE IF EXISTS `user_coupon`;
create table user_coupon
(
    id INT(11) NOT NULL auto_increment,
	user_id INT(11) NOT NULL DEFAULT 0 COMMENT '用户id',
    coupon_id INT(11) NOT NULL DEFAULT 0 COMMENT '优惠券id',
	is_used TINYINT(1) DEFAULT 0 COMMENT '是否使用',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

--  订单优惠券使用表 order_coupon
DROP TABLE IF EXISTS `order_coupon`;
create table order_coupon
(
    id INT(11) NOT NULL auto_increment,
	pay_order_id INT(11) NOT NULL DEFAULT 0 COMMENT '支付订单id',
	user_id INT(11) NOT NULL DEFAULT 0 COMMENT '用户id',
    coupon_id INT(11) NOT NULL DEFAULT 0 COMMENT '优惠券id',
	is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='订单优惠券使用表';