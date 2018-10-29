SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `brand_en` varchar(100) NOT NULL COMMENT '公司英文名称',
  `brand_cn` varchar(100) NOT NULL COMMENT '公司中文名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------

-- ----------------------------
-- Table structure for logdata
-- ----------------------------
DROP TABLE IF EXISTS `logdata`;
CREATE TABLE `logdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP',
  `cn_msg` varchar(2000) DEFAULT NULL COMMENT '操作信息中文',
  `en_msg` varchar(2000) DEFAULT NULL COMMENT '操作信息英文',
  `status` int(1) DEFAULT '0' COMMENT '0-登陆,1-查询,2-添加,3-修改,4-删除',
  `result` int(1) DEFAULT '1' COMMENT '0-失败,1-成功',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `logdata_key_1` (`user_id`,`user_name`,`status`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logdata
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cn_name` varchar(100) NOT NULL COMMENT '权限中文名',
  `en_name` varchar(100) NOT NULL COMMENT '权限英文名',
  `describe` varchar(255) DEFAULT '' COMMENT '描述',
  `icon` varchar(100) NOT NULL DEFAULT 'fa-superpowers' COMMENT '图标',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '是否有子节点：0-否 1-是',
  `flevel` int(11) NOT NULL DEFAULT '1' COMMENT '父节点',
  `url` varchar(255) DEFAULT '/userController/index_v1' COMMENT 'url',
  `ptype` int(11) NOT NULL DEFAULT '0' COMMENT '权限类型：0-菜单权限 1-操作权限',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `permission_key_1` (`flevel`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '权限列表', 'Permission List', '权限列表', 'fa-superpowers', '1', '0', '', '0', '2018-05-08 11:40:00', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('2', '概况', 'Overall Situation', '概况', 'fa-home', '1', '1', '/analysisController/dashboard', '0', '2018-05-08 11:35:20', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('11', '账号管理', 'User Mngt', '账号管理', 'fa-superpowers', '1', '4', '/userController/user', '0', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('12', '角色管理', 'Role Mngt', '角色管理', 'fa-superpowers', '1', '4', '/roleController/role', '0', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('13', '公司管理', 'Brand Mngt', '公司管理', 'fa-superpowers', '1', '4', '/brandController/brand', '0', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('15', '查询账号', 'Search User', '查询账号', 'fa-superpowers', '0', '11', 'searchUserClick', '1', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('16', '添加账号', 'Add User', '添加账号', 'fa-superpowers', '0', '11', 'addUserClick', '1', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('17', '冻结账号', 'Freeze User', '冻结账号', 'fa-superpowers', '0', '11', 'freezeUserClick', '1', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('18', '分配角色', 'Asign Role', '分配角色', 'fa-superpowers', '0', '11', 'asignRoleClick', '1', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('19', '删除账号', 'Delete User', '删除账号', 'fa-superpowers', '0', '11', 'deleteUserClick', '1', '2018-05-08 11:44:07', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('20', '添加角色', 'Add Role', '添加角色', 'fa-superpowers', '0', '12', 'addRoleClick', '1', '2018-05-08 12:01:46', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('21', '分配权限', 'Asign Permission', '分配权限', 'fa-superpowers', '0', '12', 'asignPermissionClick', '1', '2018-05-08 12:01:46', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('22', '删除角色', 'Delete Role', '删除角色', 'fa-superpowers', '0', '12', 'deleteRoleClick', '1', '2018-05-08 12:01:46', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('23', '查询公司', 'Search Brand', '查询公司', 'fa-superpowers', '0', '13', 'searchBrandClick', '1', '2018-05-08 12:01:46', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('24', '添加公司', 'Add Brand', '添加公司', 'fa-superpowers', '0', '13', 'addBrandClick', '1', '2018-05-08 12:01:46', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('25', '修改公司', 'Edit Brand', '修改公司', 'fa-superpowers', '0', '13', 'editBrandClick', '1', '2018-05-08 12:01:46', '0000-00-00 00:00:00');
INSERT INTO `permission` VALUES ('26', '删除公司', 'Delete Brand', '删除公司', 'fa-superpowers', '0', '13', 'deleteBrandClick', '1', '2018-05-08 13:30:34', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '角色名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '2018-05-08 13:49:37', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `rid` int(11) NOT NULL COMMENT '角色ID',
  `pid` int(11) NOT NULL COMMENT '权限ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `role_permission_key_1` (`pid`),
  KEY `role_permission_key_2` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1', '2018-05-08 13:49:51');
INSERT INTO `role_permission` VALUES ('2', '1', '2', '2018-05-08 13:49:51');
INSERT INTO `role_permission` VALUES ('3', '1', '11', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('4', '1', '12', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('5', '1', '13', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('6', '1', '15', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('7', '1', '16', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('8', '1', '17', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('9', '1', '18', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('10', '1', '19', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('11', '1', '20', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('12', '1', '21', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('13', '1', '22', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('14', '1', '23', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('15', '1', '24', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('16', '1', '25', '2018-05-08 13:55:39');
INSERT INTO `role_permission` VALUES ('17', '1', '26', '2018-05-08 13:55:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `pwd` varchar(100) NOT NULL COMMENT '密码',
  `brand_id` int(11) DEFAULT NULL COMMENT '公司CODE',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `contact` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-未激活 1-激活 2-冻结 3-删除',
  `fuser` int(11) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '0eaf6ed83b2d3dd7b33477ba2d343ae9', '0', '88192130@qq.com', '苏伟全', '18801628152', '1', '1', '2018-05-08 13:49:08', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `roleid` int(11) NOT NULL COMMENT '角色ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_role_key_1` (`userid`),
  KEY `user_role_key_2` (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2018-05-08 13:56:13');


CREATE TABLE `subaccount_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `model` varchar(100) NOT NULL COMMENT '机型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `subaccount_permission_key_1` (`uid`),
  KEY `subaccount_permission_key_2` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subaccount_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `pid` int(11) NOT NULL COMMENT '权限ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `subaccount_permission_key_1` (`uid`),
  KEY `subaccount_permission_key_2` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `brandid` int(11) NOT NULL COMMENT '厂商ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_brand_key_1` (`userid`),
  KEY `user_brand_key_2` (`brandid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
