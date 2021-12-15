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




/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

DROP TABLE IF EXISTS `available_room`;
CREATE TABLE `available_room`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `room_id`  int NOT NULL,
    `date`     int NOT NULL,
    `hotel_id` int NOT NULL,
    `num`      int NOT NULL,
    `value`    int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `room_id` (`room_id`),
    KEY `hotel_id` (`hotel_id`),
    CONSTRAINT `available_room_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
    CONSTRAINT `available_room_ibfk_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `userId`         int                                                           DEFAULT NULL,
    `hotelId`        int                                                           DEFAULT NULL,
    `orderId`        int                                                           DEFAULT NULL,
    `createDate`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `commentContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `rate`           double                                                        DEFAULT NULL,
    `userName`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`
(
    `id`             int                                                     NOT NULL AUTO_INCREMENT,
    `description`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hotelId`        int                                                     DEFAULT '-1',
    `couponType`     int                                                     NOT NULL,
    `couponName`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `target_roomNum` int                                                     DEFAULT NULL,
    `target_money`   int                                                     DEFAULT NULL,
    `discount`       double                                                  DEFAULT NULL,
    `status`         int                                                     DEFAULT NULL,
    `start_time`     datetime                                                DEFAULT NULL,
    `end_time`       datetime                                                DEFAULT NULL,
    `discount_money` int                                                     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 67
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`
(
    `id`               int                                                     NOT NULL AUTO_INCREMENT,
    `hotelName`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `hotelDescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `address`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `bizRegion`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `hotelStar`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `phoneNum`         varchar(11)                                             DEFAULT NULL,
    `rate`             double                                                  DEFAULT NULL,
    `manager_id`       int                                                     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `userId`       int                                                     DEFAULT NULL,
    `hotelId`      int                                                     DEFAULT NULL,
    `hotelName`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `checkInDate`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `checkOutDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `roomId`       int                                                     DEFAULT NULL,
    `roomType`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `roomNum`      int                                                     DEFAULT NULL,
    `peopleNum`    int                                                     DEFAULT NULL,
    `haveChild`    tinytext CHARACTER SET utf8 COLLATE utf8_general_ci,
    `createDate`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `price`        decimal(65, 0)                                          DEFAULT NULL,
    `clientName`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `phoneNumber`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `orderState`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `price`    double                                                   DEFAULT NULL,
    `curNum`   int                                                      DEFAULT NULL,
    `total`    int                                                      DEFAULT NULL,
    `hotel_id` int                                                      DEFAULT NULL,
    `roomType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `detail`   varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int                                                    NOT NULL AUTO_INCREMENT,
    `email`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `password`    varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `username`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `credit`      double(255, 0)                                          DEFAULT NULL,
    `usertype`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 34
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = DYNAMIC;

INSERT INTO `comment` (`id`, `userId`, `hotelId`, `orderId`, `createDate`, `commentContent`, `rate`, `userName`)
VALUES (1, 6, 3, 22, '2020-07-02', 'qerqrqerqre', 5, '测试名'),
       (2, 6, 1, 22, '2020-07-02', 'qerqrqerqre', 5, '测试名');

INSERT INTO `coupon` (`id`, `description`, `hotelId`, `couponType`, `couponName`, `target_roomNum`, `target_money`,
                      `discount`, `status`, `start_time`, `end_time`, `discount_money`)
VALUES (55, '一次性预定两件及以上房间打8折', 1, 2, '多间优惠', 2, 0, 0.8, 1, '2021-11-10 00:00:00', '2022-01-31 00:00:00', 0),
       (56, '满400-39', 1, 3, '满减优惠', 1, 400, 0, 1, '2021-11-10 00:00:00', '2022-01-31 00:00:00', 39),
       (57, '活动期间订房立减29元', 1, 4, '限时优惠', 1, 0, 0, 1, '2021-11-10 00:00:00', '2022-01-31 00:00:00', 29),
       (58, '一次性预定三间及以上房间打6折', 2, 2, '多间优惠', 3, 0, 0.6, 1, '2021-11-10 00:00:00', '2022-01-31 00:00:00', 0),
       (59, '满1000-200', 2, 3, '满减优惠', 1, 1000, 0, 1, '2021-11-10 00:00:00', '2022-01-31 00:00:00', 200),
       (60, '活动期间酒店所有房型85折', 3, 4, '限时优惠', 1, 0, 0.85, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 0),
       (61, '满1000-100', 19, 3, '满减优惠', 1, 1000, 0, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 100),
       (62, '满2000-300', 19, 3, '满减优惠', 1, 2000, 0, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 300),
       (63, '满3000-600', 19, 3, '满减优惠', 1, 3000, 0, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 600),
       (64, '活动期间预定房间立减66元', 20, 4, '限时优惠', 1, 0, 0, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 66),
       (65, '一次性预定三间及以上房间打七折', 20, 2, '多间优惠', 3, 0, 0.7, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 0),
       (66, '满800-100', 21, 3, '满减优惠', 1, 800, 0, 1, '2021-11-10 00:00:00', '2021-12-31 00:00:00', 100);

INSERT INTO `hotel` (`id`, `hotelName`, `hotelDescription`, `address`, `bizRegion`, `hotelStar`, `phoneNum`, `rate`,
                     `manager_id`)
VALUES (1, '汉庭酒店', '欢迎您入住', '南京市西单', 'XiDan', 'Five', '1829373819', 4.8, 33),
       (2, '儒家酒店', '欢迎您入住', '南京市鼓楼区珠江路268号', 'XiDan', 'Three', '1829263819', 4.6, 33),
       (3, '桂圆酒店', '欢迎您入住', '南京市栖霞区学则路268号', 'XueZeLu', 'Four', '1829553719', 4.8, 33),
       (4, '有间酒店', '客官这边请', '南京市鼓楼区新街口2号', 'XinJieKou', 'Five', '1735564519', 5, 33),
       (19, '亚朵酒店', '高品质酒店', '南京市秦淮区夫子庙', 'FuZiMiao', 'Four', '18491718494', 5, 33),
       (20, '四季酒店', '高品质酒店', '南京市奥体中心', 'AoTiZhongXin', 'Four', '18491718494', 4.6, 33),
       (21, '桔子酒店', '高品质酒店', '南京市新街口', 'XinJieKou', 'Four', '18491718494', 4.7, 33);

INSERT INTO `orderlist` (`id`, `userId`, `hotelId`, `hotelName`, `checkInDate`, `checkOutDate`, `roomId`, `roomType`,
                         `roomNum`, `peopleNum`, `haveChild`, `createDate`, `price`, `clientName`, `phoneNumber`,
                         `orderState`)
VALUES (1, 6, 1, '汉庭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '已撤销'),
       (14, 6, 1, '汉庭酒店', '2020-04-20', '2020-04-22', NULL, 'Family', 1, 1, '0', '2020-04-20', 798, NULL, NULL, '已撤销'),
       (15, 6, 1, '汉庭酒店', '2020-04-21', '2020-04-22', NULL, 'DoubleBed', 1, 1, '0', '2020-04-20', 199, NULL, NULL,
        '已撤销'),
       (16, 6, 1, '汉庭酒店', '2020-04-11', '2020-04-12', NULL, 'DoubleBed', 2, 1, '0', '2020-04-20', 598, NULL, NULL,
        '已撤销'),
       (17, 6, 1, '汉庭酒店', '2020-04-21', '2020-04-22', NULL, 'DoubleBed', 1, 1, '0', '2020-04-20', 299, NULL, NULL,
        '已撤销'),
       (18, 6, 1, '汉庭酒店', '2020-04-20', '2020-04-22', NULL, 'DoubleBed', 3, 4, '0', '2020-04-20', 1794, NULL, NULL,
        '已撤销'),
       (19, 6, 2, '儒家酒店', '2020-04-20', '2020-04-21', NULL, 'Family', 2, 1, '0', '2020-04-20', 798, NULL, NULL, '已撤销'),
       (20, 6, 1, '汉庭酒店', '2020-04-20', '2020-04-22', NULL, 'DoubleBed', 2, 1, '0', '2020-04-20', 1196, NULL, NULL,
        '已撤销'),
       (21, 6, 1, '汉庭酒店', '2020-06-15', '2020-06-20', 3, 'DoubleBed', 1, 1, '0', '2020-06-18', 1384, '测试', '1234567891',
        '已预订'),
       (31, 1, 20, '四季酒店', '2021-12-12', '2021-12-13', 43, 'Family', 1, 2, '0', '2021-11-10', 833, '有间酒店管理',
        '12345654321', '已预订');

INSERT INTO `room` (`id`, `price`, `curNum`, `total`, `hotel_id`, `roomType`, `detail`)
VALUES (1, 599, 28, 30, 4, 'DoubleBed',
        '{\"area\":50,\"level\":\"8-16\",\"bedType\":\"两张1.8米床\",\"addBed\":true,\"facility\":\"<span style=\'color:green\'>测试</span>\"}\r\n\r\n '),
       (2, 199, 20, 20, 1, 'BigBed',
        '{\"area\":33.3,\"level\":5,\"bedType\":\"1.5米\",\"addBed\":false,\"facility\":\"便利设施： <span style=\'font-weight:bold\'>雨伞<span style=\'color:green\'>免费</span>、书桌、熨衣设备<span style=\'color:green\'>免费</span>、多种规格电源插座、空调<span style=\'color:green\'>免费</span>、洗衣机、衣柜/衣橱、闹钟、针线包<span style=\'color:green\'>免费</span>、220V电压插座、遮光窗帘、手动窗帘、床具:毯子或被子、沙发、开夜床、房间内高速上网、客房WIFI<span style=\'color:green\'>免费</span>、空气净化器、熨裤机、衣架、餐桌、空调-仅制冷、洗衣用品<span style=\'color:green\'>免费</span>、地毯</span><br/>媒体科技： <span style=\'font-weight:bold\'>国内长途电话<span style=\'color:green\'>免费</span>、国际长途电话、有线频道、卫星频道、液晶电视机、电话、智能门锁</span><br/>食品饮品： <span style=\'font-weight:bold\'>电热水壶、咖啡壶/茶壶<span style=\'color:green\'>免费</span>、瓶装水<span style=\'color:green\'>免费</span>、用餐区、软饮、水果、小食<br/>浴室： 拖鞋、浴室化妆放大镜、24小时热水、独立淋浴间、吹风机、独立卫生间</span>\"}'),
       (3, 299, 29, 30, 1, 'DoubleBed',
        '{\"area\":27.5,\"level\":\"5-11\",\"bedType\":\"两张1.5米床\",\"addBed\":true,\"facility\":\"<span style=\'color:green\'>测试</span>\"}\r\n\r\n '),
       (4, 399, 8, 10, 1, 'Family',
        '{\"area\":15,\"level\":5,\"bedType\":\"两张1.5m床\",\"addBed\":false,\"facility\":\"便利措施:雨伞、书桌、多种规格电源插座、110V电压插座、空调、衣柜衣橱、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具毯子或被子、国内长途电话、国际长途电话、有线频道、卫星频道、液晶电视机\"}'),
       (34, 249, 30, 30, 2, 'BigBed', '{\"area\":\"30\",\"level\":\"3-10\",\"bedType\":\"2*2\",\"addBed\":false}'),
       (35, 249, 30, 30, 2, 'DoubleBed',
        '{\"area\":\"30\",\"level\":\"3-10\",\"bedType\":\"两张1.8*2\",\"addBed\":false}'),
       (36, 500, 10, 10, 2, 'Family', '{\"area\":\"80\",\"level\":\"3-10\",\"bedType\":\"三张1.8*2\",\"addBed\":false}'),
       (37, 699, 10, 10, 3, 'Family', '{\"area\":\"80\",\"level\":\"3-10\",\"bedType\":\"三张1.8*2\",\"addBed\":false}'),
       (38, 399, 40, 40, 3, 'BigBed', '{\"area\":\"40\",\"level\":\"15-20\",\"bedType\":\"2*2\",\"addBed\":false}'),
       (39, 399, 40, 40, 3, 'DoubleBed',
        '{\"area\":\"40\",\"level\":\"15-20\",\"bedType\":\"两张1.8*2\",\"addBed\":false}'),
       (40, 499, 35, 35, 19, 'BigBed', '{\"area\":\"50\",\"level\":\"3-6\",\"bedType\":\"2.2*2.2\",\"addBed\":false}'),
       (41, 529, 30, 30, 19, 'DoubleBed',
        '{\"area\":\"50\",\"level\":\"3-6\",\"bedType\":\"两张1.8*2\",\"addBed\":false}'),
       (42, 1099, 25, 25, 19, 'Family', '{\"area\":\"100\",\"level\":\"3-6\",\"bedType\":\"三张2*2\",\"addBed\":false}'),
       (43, 899, 24, 25, 20, 'Family', '{\"area\":\"100\",\"level\":\"3-6\",\"bedType\":\"三张2*2\",\"addBed\":false}'),
       (44, 399, 50, 50, 20, 'DoubleBed',
        '{\"area\":\"50\",\"level\":\"3-6\",\"bedType\":\"两张1.8*2\",\"addBed\":false}'),
       (45, 450, 50, 50, 20, 'BigBed', '{\"area\":\"40\",\"level\":\"3-6\",\"bedType\":\"2*2\",\"addBed\":false}'),
       (46, 450, 50, 50, 21, 'BigBed', '{\"area\":\"40\",\"level\":\"3-6\",\"bedType\":\"2*2\",\"addBed\":false}'),
       (47, 500, 40, 40, 21, 'DoubleBed', '{\"area\":\"40\",\"level\":\"3-6\",\"bedType\":\"两张2*2\",\"addBed\":false}');

INSERT INTO `user` (`id`, `email`, `password`, `username`, `phonenumber`, `credit`, `usertype`)
VALUES (1, '111@qq.com', '123456', '有间酒店管理', '12345654321', 100, 'HotelManager'),
       (4, '1012681@qq.com', '123456', '测试一号', '12345678919', 100, 'Client'),
       (5, '123@qq.com', '123456', '测试二号', '12345678911', 100, 'Client'),
       (6, '333@qq.com', '123456', '测试名', '99912345678', 100, 'Admin'),
       (33, '222@qq.com', '123456', '超级号', '110', 100, 'HotelManager');



/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

SET FOREIGN_KEY_CHECKS = 1;