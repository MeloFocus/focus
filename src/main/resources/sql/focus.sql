/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50543
Source Host           : localhost:3306
Source Database       : focus

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2018-04-07 18:25:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` varchar(50) NOT NULL,
  `name` varchar(202) DEFAULT NULL,
  `code` varchar(202) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  `controller` varchar(200) DEFAULT NULL,
  `des` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('047b2f11-3a43-11e8-af4b-507b9dc8fad2', '校验用户名唯一性，账号', 'user_isExist', '/focus/user/isExist', 'GET', 'UserController', '');
INSERT INTO `authority` VALUES ('12495b97-3a43-11e8-af4b-507b9dc8fad2', '查单个用户所拥有的角色id', 'user_getRoleByUser', '/focus/user/getRoleByUser', 'GET', 'UserController', '');
INSERT INTO `authority` VALUES ('18230089-3a42-11e8-af4b-507b9dc8fad2', '新增角色', 'role_saveRole', '/focus/role', 'POST', 'RoleController', '');
INSERT INTO `authority` VALUES ('1c68960f-3a41-11e8-af4b-507b9dc8fad2', '按controller分组，得到authority列表', 'authority_getAuthority', '/focus/authority', 'GET', 'AuthorityController', '');
INSERT INTO `authority` VALUES ('278e9498-3a42-11e8-af4b-507b9dc8fad2', '删除角色', 'role_deleteRole', '/focus/role/{roleId}', 'DELETE', 'RoleController', '');
INSERT INTO `authority` VALUES ('331a840d-3a42-11e8-af4b-507b9dc8fad2', '修改角色', 'role_updateRole', '/focus/role/{roleId}', 'PUT', 'RoleController', '');
INSERT INTO `authority` VALUES ('3aa50cdc-3a41-11e8-af4b-507b9dc8fad2', '根据请求方式，按关键字模糊查询', 'authority_getLikeAuthority', '/focus/getLikeAuthority', 'GET', 'AuthorityController', '');
INSERT INTO `authority` VALUES ('46d1ae66-3a42-11e8-af4b-507b9dc8fad2', '查询角色列表', 'role_getRoleList', '/focus/role', 'GET', 'RoleController', '');
INSERT INTO `authority` VALUES ('6bd80bdb-3a42-11e8-af4b-507b9dc8fad2', '给角色授权', 'role_authorize', '/focus/role/authorize', 'POST', 'RoleController', '');
INSERT INTO `authority` VALUES ('78e52b25-3a41-11e8-af4b-507b9dc8fad2', '新增资源', 'resource_addResource', '/focus/resource', 'POST', 'ResourceController', '');
INSERT INTO `authority` VALUES ('7eee4505-3a42-11e8-af4b-507b9dc8fad2', '校验code唯一性', 'role_isExist', '/focus/role/isExist', 'GET', 'RoleController', '');
INSERT INTO `authority` VALUES ('880e79f3-3a41-11e8-af4b-507b9dc8fad2', '修改资源', 'resource_updateResource', '/focus/resource', 'PUT', 'ResourceController', '');
INSERT INTO `authority` VALUES ('8bc0323d-3a42-11e8-af4b-507b9dc8fad2', '得到某角色拥有的资源', 'role_getRoleResource', '/focus/role/getRoleResource', 'GET', 'RoleController', '');
INSERT INTO `authority` VALUES ('9e0c17e4-3a41-11e8-af4b-507b9dc8fad2', '删除资源', 'resource_deleteResource', '/focus/resource/{resourceId}', 'DELETE', 'ResourceController', '');
INSERT INTO `authority` VALUES ('a516aa5f-3a42-11e8-af4b-507b9dc8fad2', '新增用户', 'user_saveUser', '/focus/user', 'POST', 'UserController', '');
INSERT INTO `authority` VALUES ('b07a1165-3a41-11e8-af4b-507b9dc8fad2', '查询资源列表', 'resource_getAllResource', '/focus/resource', 'GET', 'ResourceController', '');
INSERT INTO `authority` VALUES ('b75a7c8b-3a42-11e8-af4b-507b9dc8fad2', '修改用户', 'user_updateUser', '/focus/user', 'PUT', 'UserController', '');
INSERT INTO `authority` VALUES ('c5951c74-3a42-11e8-af4b-507b9dc8fad2', '删除用户', 'user_deleteUser', '/focus/user/{id}', 'DELETE', 'UserController', '');
INSERT INTO `authority` VALUES ('c877f065-3a41-11e8-af4b-507b9dc8fad2', '查单个资源 拥有的操作码', 'resource_getAuthorityByResource', '/focus/resource/getAuthorityByResource', 'GET', 'ResourceController', '');
INSERT INTO `authority` VALUES ('d742bcfa-3a42-11e8-af4b-507b9dc8fad2', '将角色授予用户', 'user_delegate', '/focus/user/delegate', 'POST', 'UserController', '');
INSERT INTO `authority` VALUES ('de481f75-3a41-11e8-af4b-507b9dc8fad2', '取消授权,取消一个资源的某个 或一些 权限', 'resource_deleteResourceAuthority', '/focus/resource/deleteResourceAuthority', 'POST', 'ResourceController', '');
INSERT INTO `authority` VALUES ('f0853008-3a41-11e8-af4b-507b9dc8fad2', '给资源授权,单个操作码给单个资源', 'resource_authorize', '/focus/resource/authorize', 'POST', 'ResourceController', '');
INSERT INTO `authority` VALUES ('f927cf1e-3a42-11e8-af4b-507b9dc8fad2', '查所有用户', 'user_findAllUser', '/focus/user', 'GET', 'UserController', '');

-- ----------------------------
-- Table structure for resouce_authority
-- ----------------------------
DROP TABLE IF EXISTS `resouce_authority`;
CREATE TABLE `resouce_authority` (
  `id` varchar(50) NOT NULL,
  `resource_id` varchar(50) DEFAULT NULL,
  `authority_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resouce_authority
-- ----------------------------
INSERT INTO `resouce_authority` VALUES ('0f7b28c9-38a3-4121-a0aa-eb4e7a5151f9', '0451e108-da3b-4b87-ae62-f2ee1beb7c9b', '12495b97-3a43-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('133057d7-de6b-49ad-9a6e-16158c5a01c0', '0451e108-da3b-4b87-ae62-f2ee1beb7c9b', 'f927cf1e-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('13f71763-01e6-4e4e-927a-c0154f3d224a', '2fc56a7f-b29c-42f7-8ade-d26ea3eaa0dc', '18230089-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('170b5874-edf3-4d50-92b1-bed9df58e968', '0d2adeca-08c7-4c3c-a332-6ec7262fb562', 'b07a1165-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('175005d6-4d57-4eb7-97e3-68c18e971524', '1efea1bc-aecd-4787-823d-55d63fe54abf', '331a840d-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('1dde580a-0712-4dd4-80b5-866d3977701c', '47e1a143-0fee-4a1e-8bdc-8d659497aec4', '278e9498-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('1f7569d6-1054-4e9f-a1f3-f84e6413fd25', 'ee83c7b6-d22d-4013-8172-b397f0f31b51', 'f0853008-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('2b6ed9cf-2fc1-4fc9-88f5-ef48d1ad7fb0', '376ccf3e-a713-4738-95fd-41e071b3e891', 'b75a7c8b-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('458e0f1d-5ba5-4be1-81af-e13b9ae7d2c7', '0d2adeca-08c7-4c3c-a332-6ec7262fb562', '8bc0323d-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('56f13076-7ad1-4c8e-9fee-55bbcd8e26cc', '5a9a6b50-789c-4b6c-83ae-7701b6259c51', '1c68960f-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('5e4e8229-deed-47ec-8d7e-28ff58644f36', 'c07f19e5-df3e-4893-b9dd-42ee09705b43', 'c5951c74-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('6aad5d47-f126-4727-9e29-0bee16a0ee3d', '03f1c9d6-b952-4c77-92d6-4ada566567c5', '46d1ae66-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('6bd89b1f-5112-4cbd-a9bf-b703493f6d7f', '0d2adeca-08c7-4c3c-a332-6ec7262fb562', '6bd80bdb-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('80dc6290-9817-423a-90e0-d571a7925df6', '376ccf3e-a713-4738-95fd-41e071b3e891', 'f927cf1e-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('87879b76-1303-4b30-a3b5-2fef65a2aeee', 'ee83c7b6-d22d-4013-8172-b397f0f31b51', 'c877f065-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('891b5331-576f-4919-8f42-702d4cad96ab', 'ee83c7b6-d22d-4013-8172-b397f0f31b51', 'de481f75-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('be5cc5b6-0658-4d79-9203-54462bdda829', '6d8cb0bc-c43e-4bf3-a14b-db0141174071', '880e79f3-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('c1317e64-4c4c-4c8b-a255-6d38f91597c9', 'c07f19e5-df3e-4893-b9dd-42ee09705b43', 'f927cf1e-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('cddeffa9-c790-4bee-afc3-b572b5348f07', '0451e108-da3b-4b87-ae62-f2ee1beb7c9b', 'd742bcfa-3a42-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('d3edb56b-a90f-4c44-85e9-28825d3be57b', '7a6fdedc-80c8-486a-b545-f7aab65be9c2', '9e0c17e4-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('e6303f91-41c4-48e7-ba04-1ec5109642dc', 'ee83c7b6-d22d-4013-8172-b397f0f31b51', '78e52b25-3a41-11e8-af4b-507b9dc8fad2');
INSERT INTO `resouce_authority` VALUES ('e863ab45-7c6e-49ad-a324-7f2616a083d6', 'e7307f87-2405-11e8-8806-3ef40d61c1a6', 'b07a1165-3a41-11e8-af4b-507b9dc8fad2');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `code` varchar(200) DEFAULT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `des` varchar(200) DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `menu_route` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('03f1c9d6-b952-4c77-92d6-4ada566567c5', '角色管理', '2', 'category_role', 'a4afe6f7-2405-11e8-8806-3ef40d61c1a6', '角色管理，对角色增删改、授权', '1', '/category_authorization/category_role', 'role_manage');
INSERT INTO `resource` VALUES ('0451e108-da3b-4b87-ae62-f2ee1beb7c9b', '用户授权', '3', 'btn_user_authorize', '6bb173da-2405-11e8-8806-3ef40d61c1a6', '用户授权', '2', '/category_usermanage/btn_user_authorize', '');
INSERT INTO `resource` VALUES ('0d2adeca-08c7-4c3c-a332-6ec7262fb562', '角色授权', '3', 'btn_role_authorize', '03f1c9d6-b952-4c77-92d6-4ada566567c5', '角色授权按钮', '4', '/category_authorization/category_role/btn_role_authorize', '');
INSERT INTO `resource` VALUES ('1efea1bc-aecd-4787-823d-55d63fe54abf', '修改角色', '3', 'btn_role_update', '03f1c9d6-b952-4c77-92d6-4ada566567c5', '修改角色按钮', '2', '/category_authorization/category_role/btn_role_update', null);
INSERT INTO `resource` VALUES ('200b5315-2406-11e8-8806-3ef40d61c1a6', '博客', '2', 'category_blog', 'pid', '博客栏目', '3', '/category_blog', 'blog');
INSERT INTO `resource` VALUES ('2fc56a7f-b29c-42f7-8ade-d26ea3eaa0dc', '新增角色', '3', 'btn_roeladd', '03f1c9d6-b952-4c77-92d6-4ada566567c5', '新增角色', '1', '/category_authorization/category_role/btn_roeladd', null);
INSERT INTO `resource` VALUES ('376ccf3e-a713-4738-95fd-41e071b3e891', '修改用户', '3', 'btn_user_update', '6bb173da-2405-11e8-8806-3ef40d61c1a6', '修改用户', '4', '/category_usermanage/btn_user_update', '');
INSERT INTO `resource` VALUES ('3be78ae4-2406-11e8-8806-3ef40d61c1a6', '项目介绍', '2', 'category_introductio', 'pid', '项目介绍栏目', '4', '/category_introduction', 'introduction');
INSERT INTO `resource` VALUES ('47e1a143-0fee-4a1e-8bdc-8d659497aec4', '删除角色', '3', 'btn_role_delete', '03f1c9d6-b952-4c77-92d6-4ada566567c5', '删除角色，按钮', '3', '/category_authorization/category_role/btn_role_delete', null);
INSERT INTO `resource` VALUES ('5a9a6b50-789c-4b6c-83ae-7701b6259c51', '权限码管理', '2', 'category_authority', 'a4afe6f7-2405-11e8-8806-3ef40d61c1a6', '权限码管理', '3', null, 'authority_manage');
INSERT INTO `resource` VALUES ('6bb173da-2405-11e8-8806-3ef40d61c1a6', '用户管理', '2', 'category_usermanage', 'pid', '用户管理栏目，可以对用户增删改', '1', '/category_usermanage', 'user_manage');
INSERT INTO `resource` VALUES ('6d8cb0bc-c43e-4bf3-a14b-db0141174071', '修改资源', '3', 'btn_resource_update', 'e7307f87-2405-11e8-8806-3ef40d61c1a6', '修改资源，按钮', '3', '/category_authorization/category_resource/btn_resource_update', null);
INSERT INTO `resource` VALUES ('7a6fdedc-80c8-486a-b545-f7aab65be9c2', '删除资源', '3', 'btn_resource_delete', 'e7307f87-2405-11e8-8806-3ef40d61c1a6', '删除资源', '2', '/category_authorization/category_resource/btn_resource_delete', null);
INSERT INTO `resource` VALUES ('a4afe6f7-2405-11e8-8806-3ef40d61c1a6', '功能权限', '2', 'category_authorization', 'pid', '功能权限栏目，可以操作角色、资源、权限码', '2', '/category_authorization', null);
INSERT INTO `resource` VALUES ('c07f19e5-df3e-4893-b9dd-42ee09705b43', '删除用户', '3', 'btn_user_delete', '6bb173da-2405-11e8-8806-3ef40d61c1a6', '删除用户', '3', '/category_usermanage/btn_user_delete', '');
INSERT INTO `resource` VALUES ('e7307f87-2405-11e8-8806-3ef40d61c1a6', '资源管理', '2', 'category_resource', 'a4afe6f7-2405-11e8-8806-3ef40d61c1a6', '资源管理栏目，可以操作资源增删改、授权', '2', '/category_authorization/category_resource', 'resource_manage');
INSERT INTO `resource` VALUES ('e7fdcb7c-43d9-423b-9c00-4847c21cfa35', '新增资源ok', '3', 'btn_resource_add', 'e7307f87-2405-11e8-8806-3ef40d61c1a6', '新增资源按钮', '1', '/category_authorization/category_resource/btn_resource_add', null);
INSERT INTO `resource` VALUES ('ee83c7b6-d22d-4013-8172-b397f0f31b51', '资源授权', '3', 'btn_resource_authorize', 'e7307f87-2405-11e8-8806-3ef40d61c1a6', '资源授权', '4', '/category_authorization/category_resource/btn_resource_authorize', '');
INSERT INTO `resource` VALUES ('f1e4ec99-2800-4d09-a622-632a05e28f52', '用户新增', '3', 'btn_user_add', '6bb173da-2405-11e8-8806-3ef40d61c1a6', '', '1', '/category_usermanage/btn_user_add', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL,
  `des` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1ed77c6f-93a5-46c0-a865-29ec8901287c', '权限码管理员', 'MANAGER_AUTHORITY', '2', '2', '权限码');
INSERT INTO `role` VALUES ('9bd9b381-dbcc-4a48-bf0a-6951f569d686', '资源管理员', 'MANAGER_RESOURCE', '4', '2', '资源管理员');
INSERT INTO `role` VALUES ('a8bfc2b4-ff73-4c35-9a63-ae2c049d3b01', '普通用户', 'NORMAL', '5', '1', '普通用户');
INSERT INTO `role` VALUES ('eec145c6-59b4-488f-b597-3e3978566492', '角色管理员', 'MANAGER_ROLE', '3', '2', '角色管理员IOOO');
INSERT INTO `role` VALUES ('sdfdsfsdfadsfsa', '用户管理员', 'MANAGER_USER', '1', '1', '用户管理员ok');

-- ----------------------------
-- Table structure for role_business
-- ----------------------------
DROP TABLE IF EXISTS `role_business`;
CREATE TABLE `role_business` (
  `id` varchar(50) NOT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `class_pk` varchar(50) DEFAULT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_business
-- ----------------------------

-- ----------------------------
-- Table structure for role_resource_r
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_r`;
CREATE TABLE `role_resource_r` (
  `id` varchar(50) NOT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `resource_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource_r
-- ----------------------------
INSERT INTO `role_resource_r` VALUES ('1d248c91-59c9-4802-a163-b48b3c541804', '1ed77c6f-93a5-46c0-a865-29ec8901287c', '5a9a6b50-789c-4b6c-83ae-7701b6259c51');
INSERT INTO `role_resource_r` VALUES ('3dcf7693-c063-4864-9b42-8504a1f4593d', '9bd9b381-dbcc-4a48-bf0a-6951f569d686', 'e7fdcb7c-43d9-423b-9c00-4847c21cfa35');
INSERT INTO `role_resource_r` VALUES ('46873177-8b52-4e8e-85b8-f192d06d2e3f', '9bd9b381-dbcc-4a48-bf0a-6951f569d686', '7a6fdedc-80c8-486a-b545-f7aab65be9c2');
INSERT INTO `role_resource_r` VALUES ('4ba1c852-cd54-48f8-8b3b-bb4d7e940e50', '9bd9b381-dbcc-4a48-bf0a-6951f569d686', '6d8cb0bc-c43e-4bf3-a14b-db0141174071');
INSERT INTO `role_resource_r` VALUES ('5caa5cfb-af72-4148-a935-757e722a621e', 'a8bfc2b4-ff73-4c35-9a63-ae2c049d3b01', '3be78ae4-2406-11e8-8806-3ef40d61c1a6');
INSERT INTO `role_resource_r` VALUES ('609c0f87-3a2b-4101-8fd3-2e3eb2d71629', 'eec145c6-59b4-488f-b597-3e3978566492', '2fc56a7f-b29c-42f7-8ade-d26ea3eaa0dc');
INSERT INTO `role_resource_r` VALUES ('7a23aae5-02d5-4283-8266-bc663d72ad87', 'eec145c6-59b4-488f-b597-3e3978566492', '03f1c9d6-b952-4c77-92d6-4ada566567c5');
INSERT INTO `role_resource_r` VALUES ('7e6203d9-9c37-41d1-a3c1-218fed052dea', '9bd9b381-dbcc-4a48-bf0a-6951f569d686', 'e7307f87-2405-11e8-8806-3ef40d61c1a6');
INSERT INTO `role_resource_r` VALUES ('81835f28-031e-4c43-ba6e-b87ace85b427', 'eec145c6-59b4-488f-b597-3e3978566492', '1efea1bc-aecd-4787-823d-55d63fe54abf');
INSERT INTO `role_resource_r` VALUES ('83292177-46b8-4fce-a4af-ac8152a1b298', 'a8bfc2b4-ff73-4c35-9a63-ae2c049d3b01', '200b5315-2406-11e8-8806-3ef40d61c1a6');
INSERT INTO `role_resource_r` VALUES ('a1ac4f7d-87ea-4ccd-a483-b417e4cf52cd', 'eec145c6-59b4-488f-b597-3e3978566492', '47e1a143-0fee-4a1e-8bdc-8d659497aec4');
INSERT INTO `role_resource_r` VALUES ('eace25e1-99a2-42b7-abfc-5956d1e7a013', 'sdfdsfsdfadsfsa', '6bb173da-2405-11e8-8806-3ef40d61c1a6');
INSERT INTO `role_resource_r` VALUES ('f7296a9d-fa67-4f7a-9647-fad03e0aae3a', 'eec145c6-59b4-488f-b597-3e3978566492', '0d2adeca-08c7-4c3c-a332-6ec7262fb562');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `login_name` varchar(20) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `superman` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('05068cb0-131b-11e8-8806-3ef40d61c1a6', 'melo', 'melo', '12345678', '');
INSERT INTO `user` VALUES ('76fbd9f0-cc74-4314-b007-e66bed436b33', 'james', '勒布朗', '12345678', '\0');
INSERT INTO `user` VALUES ('8127a2e6-69d9-4622-98c2-b81d1942080c', 'curry', '库里', '12345678', '\0');
INSERT INTO `user` VALUES ('cd3d796e-1ddc-473f-9004-76bc65e19fd3', 'kobe', 'Bryenttttttt', '12345678', '\0');

-- ----------------------------
-- Table structure for user_role_r
-- ----------------------------
DROP TABLE IF EXISTS `user_role_r`;
CREATE TABLE `user_role_r` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `yw_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_r
-- ----------------------------
INSERT INTO `user_role_r` VALUES ('50b403e7-72b2-42f5-a0a7-8271e9b8342f', 'cd3d796e-1ddc-473f-9004-76bc65e19fd3', 'a8bfc2b4-ff73-4c35-9a63-ae2c049d3b01', null);
INSERT INTO `user_role_r` VALUES ('ae0b234b-43f6-4cd5-9733-d7b8d1e19434', '8127a2e6-69d9-4622-98c2-b81d1942080c', 'eec145c6-59b4-488f-b597-3e3978566492', null);
INSERT INTO `user_role_r` VALUES ('eaf561d9-8fa8-4a14-9c83-9c1b35eac5b2', '8127a2e6-69d9-4622-98c2-b81d1942080c', 'a8bfc2b4-ff73-4c35-9a63-ae2c049d3b01', null);
