-- ----------------------------
-- Table structure for mybatis
-- ----------------------------
DROP TABLE IF EXISTS `mybatis`;
CREATE TABLE `mybatis` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `info` varchar(32) DEFAULT NULL COMMENT '信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='信息表';

-- ----------------------------
-- Records of mybaits
-- ----------------------------
INSERT INTO `mybatis` VALUES ('1', '测试数据');
INSERT INTO `mybatis` VALUES ('2', '测试数据');
INSERT INTO `mybatis` VALUES ('3', '测试数据');
