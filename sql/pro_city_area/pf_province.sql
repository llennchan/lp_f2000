-- ----------------------------
-- Table structure for pf_province
-- ----------------------------
DROP TABLE IF EXISTS `pf_province`;
CREATE TABLE `pf_province` (
  `province_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '省份序号',
  `province_name` varchar(50) NOT NULL COMMENT '省份名称',
  `update_user` varchar(50) NOT NULL DEFAULT 'root' COMMENT '修改人',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_user` varchar(50) NOT NULL DEFAULT 'root' COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=820001 DEFAULT CHARSET=utf8 COMMENT='省';

-- ----------------------------
-- Records of pf_province
-- ----------------------------
INSERT INTO `pf_province` VALUES ('110000', '北京市', 'root', '2016-12-05 13:53:06', 'root', '2016-04-12 09:17:56');
INSERT INTO `pf_province` VALUES ('120000', '天津市', 'root', '2016-12-05 13:53:09', 'root', '2016-04-12 09:18:02');
INSERT INTO `pf_province` VALUES ('130000', '河北省', 'root', '2016-12-05 13:53:14', 'root', '2016-04-12 09:18:07');
INSERT INTO `pf_province` VALUES ('140000', '山西省', 'root', '2016-12-05 13:53:18', 'root', '2016-04-12 09:19:04');
INSERT INTO `pf_province` VALUES ('150000', '内蒙古自治区', 'root', '2016-12-05 13:53:28', 'root', '2016-04-12 09:19:43');
INSERT INTO `pf_province` VALUES ('210000', '辽宁省', 'root', '2016-12-05 13:53:32', 'root', '2016-04-12 09:20:17');
INSERT INTO `pf_province` VALUES ('220000', '吉林省', 'root', '2016-12-05 13:53:35', 'root', '2016-04-12 09:20:51');
INSERT INTO `pf_province` VALUES ('230000', '黑龙江省', 'root', '2016-12-05 13:53:38', 'root', '2016-04-12 09:21:11');
INSERT INTO `pf_province` VALUES ('310000', '上海市', 'root', '2016-12-05 13:53:41', 'root', '2016-04-12 09:21:55');
INSERT INTO `pf_province` VALUES ('320000', '江苏省', 'root', '2016-12-05 13:53:44', 'root', '2016-04-12 09:22:00');
INSERT INTO `pf_province` VALUES ('330000', '浙江省', 'root', '2016-12-05 13:53:47', 'root', '2016-04-12 09:22:35');
INSERT INTO `pf_province` VALUES ('340000', '安徽省', 'root', '2016-12-05 13:53:50', 'root', '2016-04-12 09:23:06');
INSERT INTO `pf_province` VALUES ('350000', '福建省', 'root', '2016-12-05 13:53:53', 'root', '2016-04-12 09:23:42');
INSERT INTO `pf_province` VALUES ('360000', '江西省', 'root', '2016-12-05 13:53:56', 'root', '2016-04-12 09:24:12');
INSERT INTO `pf_province` VALUES ('370000', '山东省', 'root', '2016-12-05 13:53:59', 'root', '2016-04-12 09:24:46');
INSERT INTO `pf_province` VALUES ('410000', '河南省', 'root', '2016-12-05 13:54:02', 'root', '2016-04-12 09:25:34');
INSERT INTO `pf_province` VALUES ('420000', '湖北省', 'root', '2016-12-05 13:54:07', 'root', '2016-04-12 09:26:29');
INSERT INTO `pf_province` VALUES ('430000', '湖南省', 'root', '2016-12-05 13:54:10', 'root', '2016-04-12 09:27:07');
INSERT INTO `pf_province` VALUES ('440000', '广东省', 'root', '2016-12-05 13:54:14', 'root', '2016-04-12 09:27:48');
INSERT INTO `pf_province` VALUES ('450000', '广西壮族自治区', 'root', '2016-12-05 13:54:30', 'root', '2016-04-12 09:28:31');
INSERT INTO `pf_province` VALUES ('460000', '海南省', 'root', '2016-12-05 13:54:35', 'root', '2016-04-12 09:29:08');
INSERT INTO `pf_province` VALUES ('500000', '重庆市', 'root', '2016-12-05 13:54:38', 'root', '2016-04-12 09:29:17');
INSERT INTO `pf_province` VALUES ('510000', '四川省', 'root', '2016-12-05 13:54:41', 'root', '2016-04-12 09:29:29');
INSERT INTO `pf_province` VALUES ('520000', '贵州省', 'root', '2016-12-05 13:54:44', 'root', '2016-04-12 09:30:31');
INSERT INTO `pf_province` VALUES ('530000', '云南省', 'root', '2016-12-05 13:54:48', 'root', '2016-04-12 09:31:00');
INSERT INTO `pf_province` VALUES ('540000', '西藏自治区', 'root', '2016-12-05 13:54:53', 'root', '2016-04-12 09:31:44');
INSERT INTO `pf_province` VALUES ('610000', '陕西省', 'root', '2016-12-05 13:54:56', 'root', '2016-04-12 09:32:08');
INSERT INTO `pf_province` VALUES ('620000', '甘肃省', 'root', '2016-12-05 13:55:01', 'root', '2016-04-12 09:32:46');
INSERT INTO `pf_province` VALUES ('630000', '青海省', 'root', '2016-12-05 13:55:06', 'root', '2016-04-12 09:33:16');
INSERT INTO `pf_province` VALUES ('640000', '宁夏回族自治区', 'root', '2016-12-05 13:55:13', 'root', '2016-04-12 09:33:31');
INSERT INTO `pf_province` VALUES ('650000', '新疆维吾尔自治区', 'root', '2018-05-07 11:01:50', 'root', '2016-04-12 09:33:38');
INSERT INTO `pf_province` VALUES ('710000', '台湾省', 'root', '2016-04-12 09:34:12', 'root', '2016-04-12 09:34:12');
INSERT INTO `pf_province` VALUES ('810000', '香港特别行政区', 'root', '2016-04-12 09:34:12', 'root', '2016-04-12 09:34:12');
INSERT INTO `pf_province` VALUES ('820000', '澳门特别行政区', 'root', '2016-04-12 09:34:12', 'root', '2016-04-12 09:34:12');
