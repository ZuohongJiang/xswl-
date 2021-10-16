
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
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `hotelId` int(11) NULL DEFAULT NULL,
  `orderId` int(11) NULL DEFAULT NULL,
  `createDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `commentContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rate` double NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 6, 3, 22, '2020-07-02', 'qerqrqerqre', 5, '测试名');
INSERT INTO `comment` VALUES (2, 6, 1, 22, '2020-07-02', 'qerqrqerqre', 5, '测试名');
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
INSERT INTO `hotel` VALUES (1, '汉庭酒店', '欢迎您入住', NULL, 'XiDan', 'Four', 1829373819, 4.8, 3);
INSERT INTO `hotel` VALUES (2, '儒家酒店', '欢迎您入住', '南京市鼓楼区珠江路268号', 'XiDan', 'Three', 1829263819, 4.6, 2);
INSERT INTO `hotel` VALUES (3, '桂圆酒店', '欢迎您入住', '南京市栖霞区学则路268号', 'XueZeLu', 'Four', 1829553719, 4.8, 6);
INSERT INTO `hotel` VALUES (4, '有间酒店', '客官这边请', '南京市鼓楼区新街口2号', 'XinJieKou', 'Five', 1735564519, 5.0, 1);
INSERT INTO `hotel` VALUES (5, '桔子水晶', '欢迎您入住', NULL, 'XinJieKou', 'Four', 13377084245, 4.8, 5);
INSERT INTO `hotel` VALUES (6, '中山大厦', '欢迎您入住', NULL, 'XinJieKou', 'Four', 13377084245, 4.7, 5);
INSERT INTO `hotel` VALUES (7, '金陵饭店', '欢迎您入住', NULL, 'XinJieKou', 'Four', 13377084245, 4.9, 5);

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
  `total` int(11) NULL DEFAULT NULL,
  `hotel_id` int(11) NULL DEFAULT NULL,
  `roomType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 50, 1, 标间);
INSERT INTO `room` VALUES (2, 50, 1, 大床);
INSERT INTO `room` VALUES (3, 50, 2, 标间);
INSERT INTO `room` VALUES (4, 50, 2, 大床);
INSERT INTO `room` VALUES (5, 50, 3, 标间);
INSERT INTO `room` VALUES (6, 50, 3, 大床);
INSERT INTO `room` VALUES (7, 50, 4, 标间);
INSERT INTO `room` VALUES (8, 50, 4, 大床);
INSERT INTO `room` VALUES (9, 50, 5, 标间);
INSERT INTO `room` VALUES (10, 50, 5, 大床);
INSERT INTO `room` VALUES (11, 50, 6, 标间);
INSERT INTO `room` VALUES (12, 50, 6, 大床);
INSERT INTO `room` VALUES (13, 50, 7, 标间);
INSERT INTO `room` VALUES (14, 50, 7, 大床);

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
INSERT INTO `user` VALUES (6, '333@qq.com', '123456', '测试名', '99912345678', 100, 'Admin');
INSERT INTO `user` VALUES (1, '111@qq.com', '123456', '有间酒店管理', '12345654321', 100, 'HotelManager');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `available_room`;
CREATE TABLE `available_room` (
`id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
`room_id` int NOT NULL,
`date` int NOT NULL,
`hotel_id` int NOT NULL,
`num` int not NULL,
`value` int not NULL,
FOREIGN KEY(room_id) REFERENCES room(id),
FOREIGN KEY(hotel_id) REFERENCES hotel(id)

) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;