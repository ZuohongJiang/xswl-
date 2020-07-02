/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 30/06/2020 03:36:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotelId` int(11) NULL DEFAULT -1,
  `couponType` int(11) NOT NULL,
  `couponName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `target_roomNum` int(11) NULL DEFAULT NULL,
  `target_money` int(11) NULL DEFAULT NULL,
  `discount` double NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `discount_money` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (2, '满500-100优惠', 1, 3, '满减优惠券', NULL, 500, 0, 1, '2020-04-22 00:00:00', '2021-01-24 00:00:00', 100);
INSERT INTO `coupon` VALUES (34, 'test', 2, 3, 'test', NULL, 0, 0, 1, '2020-04-22 00:00:00', '2021-01-24 00:00:00', 111);
INSERT INTO `coupon` VALUES (35, 'test', 1, 4, 'test', NULL, 0, 0, 1, '2020-04-22 00:00:00', '2021-01-24 00:00:00', 111);
INSERT INTO `coupon` VALUES (36, 'test', 3, 2, 'test', 3, 0, 0, 1, '2020-04-22 00:00:00', '2021-01-24 00:00:00', 111);
INSERT INTO `coupon` VALUES (37, 'nulltest', -1, 3, 'nulltest', NULL, 0, 0, 1, '2020-04-22 00:00:00', '2021-01-24 00:00:00', 33);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hotelDescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bizRegion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotelStar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` int(11) NULL DEFAULT NULL,
  `rate` double NULL DEFAULT NULL,
  `manager_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1, '汉庭酒店', '欢迎您入住', NULL, 'XiDan', 'Four', 1829373819, 4.8, 1);
INSERT INTO `hotel` VALUES (2, '儒家酒店', '欢迎您入住', '南京市鼓楼区珠江路268号', 'XiDan', 'Four', 1829373819, 4.8, 2);
INSERT INTO `hotel` VALUES (3, '桂圆酒店', '欢迎您入住', '南京市栖霞区珠江路268号', 'XiDan', 'Four', 1829553719, 4.8, 6);

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `hotelId` int(11) NULL DEFAULT NULL,
  `hotelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checkInDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checkOutDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomId` int(11) NULL DEFAULT NULL,
  `roomType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roomNum` int(255) NULL DEFAULT NULL,
  `peopleNum` int(255) NULL DEFAULT NULL,
  `haveChild` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `createDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(65, 0) NULL DEFAULT NULL,
  `clientName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES (1, 6, 1, '汉庭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (14, 6, 1, '汉庭酒店', '2020-04-20', '2020-04-22', NULL, 'Family', 1, 1, '0', '2020-04-20', 798, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (15, 6, 1, '汉庭酒店', '2020-04-21', '2020-04-22', NULL, 'DoubleBed', 1, 1, '0', '2020-04-20', 199, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (16, 6, 1, '汉庭酒店', '2020-04-11', '2020-04-12', NULL, 'DoubleBed', 2, 1, '0', '2020-04-20', 598, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (17, 6, 1, '汉庭酒店', '2020-04-21', '2020-04-22', NULL, 'DoubleBed', 1, 1, '0', '2020-04-20', 299, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (18, 6, 1, '汉庭酒店', '2020-04-20', '2020-04-22', NULL, 'DoubleBed', 3, 4, '0', '2020-04-20', 1794, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (19, 6, 2, '儒家酒店', '2020-04-20', '2020-04-21', NULL, 'Family', 2, 1, '0', '2020-04-20', 798, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (20, 6, 1, '汉庭酒店', '2020-04-20', '2020-04-22', NULL, 'DoubleBed', 2, 1, '0', '2020-04-20', 1196, NULL, NULL, '已撤销');
INSERT INTO `orderlist` VALUES (21, 6, 1, '汉庭酒店', '2020-06-15', '2020-06-20', 3, 'DoubleBed', 1, 1, '0', '2020-06-18', 1384, '测试', '1234567891', '已预订');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NULL DEFAULT NULL,
  `curNum` int(11) NULL DEFAULT NULL,
  `total` int(11) NULL DEFAULT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `roomType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (2, 199, 20, 20, 1, 'BigBed', '{\"area\":33.3,\"level\":5,\"bedType\":\"1.5米\",\"addBed\":false,\"facility\":\"便利设施： <span style=\'font-weight:bold\'>雨伞<span style=\'color:green\'>免费</span>、书桌、熨衣设备<span style=\'color:green\'>免费</span>、多种规格电源插座、空调<span style=\'color:green\'>免费</span>、洗衣机、衣柜/衣橱、闹钟、针线包<span style=\'color:green\'>免费</span>、220V电压插座、遮光窗帘、手动窗帘、床具:毯子或被子、沙发、开夜床、房间内高速上网、客房WIFI<span style=\'color:green\'>免费</span>、空气净化器、熨裤机、衣架、餐桌、空调-仅制冷、洗衣用品<span style=\'color:green\'>免费</span>、地毯</span><br/>媒体科技： <span style=\'font-weight:bold\'>国内长途电话<span style=\'color:green\'>免费</span>、国际长途电话、有线频道、卫星频道、液晶电视机、电话、智能门锁</span><br/>食品饮品： <span style=\'font-weight:bold\'>电热水壶、咖啡壶/茶壶<span style=\'color:green\'>免费</span>、瓶装水<span style=\'color:green\'>免费</span>、用餐区、软饮、水果、小食<br/>浴室： 拖鞋、浴室化妆放大镜、24小时热水、独立淋浴间、吹风机、独立卫生间</span>\"}');
INSERT INTO `room` VALUES (3, 299, 29, 30, 1, 'DoubleBed', '{\"area\":27.5,\"level\":\"5-11\",\"bedType\":\"两张1.5米床\",\"addBed\":true,\"facility\":\"<span style=\'color:green\'>测试</span>\"}\r\n\r\n ');
INSERT INTO `room` VALUES (4, 399, 8, 10, 1, 'Family', '{\"area\":15,\"level\":5,\"bedType\":\"两张1.5m床\",\"addBed\":false,\"facility\":\"便利措施:雨伞、书桌、多种规格电源插座、110V电压插座、空调、衣柜衣橱、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具毯子或被子、国内长途电话、国际长途电话、有线频道、卫星频道、液晶电视机\"}');
INSERT INTO `room` VALUES (5, 122, 7, 7, 1, 'BigBed', NULL);
INSERT INTO `room` VALUES (6, 399, 10, 10, 2, 'Family', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` double(255, 0) NULL DEFAULT NULL,
  `usertype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, '1012681@qq.com', '123456', '测试一号', '12345678919', 100, 'Client');
INSERT INTO `user` VALUES (5, '123@qq.com', '123456', '测试二号', '12345678911', 100, 'Client');
INSERT INTO `user` VALUES (6, '333@qq.com', '123456', '测试名', '99912345678', 99, 'Admin');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `hotel`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orderId` INT NOT NULL,
  `userId` INT NOT NULL,
  `hotelId` INT NOT NULL,
  `createDate` VARCHAR(255) NOT NULL,
  `commentContent` VARCHAR(1000) CHARACTER SET 'utf8' NOT NULL,
  `rate` DOUBLE NOT NULL,
  PRIMARY KEY (`id`, `orderId`))
ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 1;
