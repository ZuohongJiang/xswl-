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
    `manager_id`       int                                                     DEFAULT NULL,
    `rate`        double                                                  DEFAULT 0,
    `commentsCount`    int                                                     DEFAULT 0,
    `lowestPrice`      int                                                     DEFAULT 200,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
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
    KEY `hotel_id` (`hotel_id`)
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
    `createDate`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `commentContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `rate`           double                                                        DEFAULT NULL,
    `userName`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb3
--  COLLATE = utf8 COLLATE utf8_general_ci
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
VALUES (1, 6, 3, 22, '2020-07-02', '入住体验很不错！', 5, 'LongIsland'),

       (2, 2, 1, 5, '2021-12-10', 'Test comment1', 4.5, 'Johnscript');

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

INSERT INTO `hotel` (`id`, `hotelName`, `hotelDescription`, `address`, `bizRegion`, `hotelStar`, `phoneNum`,
                     `manager_id`, `lowestPrice`, `rate`, `commentsCount`)
VALUES (1, '全季南京新街口王府大街酒店', '全季酒店成立于2010年，隶属华住集团，中国领先的中档酒店品牌。目前已覆盖中国31个省级行政区，开业已超过1100家。 从东方智慧中汲取人文精神，从当代生活中提炼价值内涵，全季通过亲朋服务创造优质体验，在东方土地上，让更多人感受东方的自然得体，为4亿中产人群提升旅途生活品质。',
        '江苏省南京市秦淮区王府大街63号-19', 'XinJieKou', 'Three', '025-57711555',  33, 199, 4.5, 1),
       (2, '桔子南京新街口张府园地铁站酒店', '桔子南京新街口酒店（2017年重新装修）坐落于具有历史悠久的明朝古巷张府园巷内，离繁华的新街口商业街步行约8分钟，距夫子庙约5分钟车程，紧邻地铁张府园站（50米），交通便利，地理位置得天独厚，出行方便；除此之外，酒店更是闹中取静，能保证您安静睡眠！作为知名设计师酒店品牌旗下酒店，桔子南京新街口酒店由桔子集团专属设计师团队以“梦回金陵”为设计理念精心打造。',
       '南京市秦淮区中山南路288号', 'XinJieKou', 'Three', '025-66628850', 33, 313, 4.6, 3),
       (3, '南京金鹰珠江壹号国际酒店', '南京金鹰珠江壹号国际酒店地处南京新街口商圈内，傲居城市中轴中山路与珠江路黄金交汇点，城市CBD中央，省市政府两大政务中心聚集地，紧邻南京大学、东南大学两大著名学府，距总统府、玄武湖等主要景点约5分钟车程；同时酒店与地铁1号线及金鹰国际购物中心（珠江路店）紧密直连，购物中心汇聚众多国际一线名品、G-Mart超市、亲子乐园、特色餐饮等。',
       '江苏省南京市秦淮区新街口汉中路101号金鹰中心B座', 'XinJieKou', 'Five', '025-86678888',33, 412, 4.9, 2),
       (4, '南京泊金花间堂·琵琶山居', '南京泊金花间堂·琵琶山居位于钟山风景区脚下，与景色优美的植物园、琵琶湖相隔不远。酒店以“依山湖而建，反璞自然之趣的设计理念”，“随遇而安因树为屋，会心不远开门见山”的美好意境命名。其所描述刚好满足时下人们对生活的一种返璞归真的追求，人们越来越希望所居之所被自然风光所环绕。可望、可行、可游、可居，正是东方人精神上始终追求的理想居所。酒店共有67间客房，房间布局均以现代居住形式规划空间，客房四周环山林、靠湖泊，优美的景色为忙碌、焦虑的城市人营造了一处休闲放松的桃花源。此外，无边嬉水池、茴香餐厅、会议室、宴会厅、露天平台、天然湖泊一应俱全。在这里，每个人都能在美与欢乐中发现生活的新乐趣。花间堂是华住集团旗下的高端精品酒店，拥有城市酒店、度假酒店及民宿三种不同规模的酒店产品。品牌自2009年诞生于丽江，以独特的花间美学，将高端精品酒店的服务理念与地方民居、民俗等人文特色完美融合，期望为大家打造一个欢乐而美好的现代人间桃花源。', '江苏省南京市玄武区琵琶洲16号',
       'ZhongShan', 'Five', '025-83151088', 33, 597, 4.8, 3),
       (5, '南京仙林大学城漫心酒店', '漫心南京仙林大学城酒店位于南京市栖霞区兰德地理信息产业园区，坐落在风景秀丽的大学城内。步行5分钟可至六朝辟邪石刻-萧宏石刻公园。酒店附近文化气息浓厚，有南京大学、南京财经学院、南京外国语学校仙林分校、南京中医药大学、南京金审学院等， “活色生香，live lively”是漫心希望传递给客人的生活理念，作为华住酒店集团旗下中高端设计酒店品牌，漫心为您提供轻松的开放式社交平台--漫趣大堂，客房全部采用轻松明快的色彩，并配备香凡罗蒂-胡椒佛手山茶花系列洗护产品、热带雨林花洒、电加热马桶、情景灯光、蓝牙音箱、电子香薰等高品质客房用品。 漫趣大堂、漫心M.bar、咖啡、健身房，一切只为您实现完美时尚的漫心之旅。',
       '栖霞区灵山北路18号B区9幢', 'XianLin', 'Four', '025-85302929', 33, 362, 4.7, 2),
       (6, '汉庭南京禄口机场酒店', '汉庭南京禄口机场酒店位于南京禄口来凤路9号百利广场，酒店临近机场高速，交通便捷，广场购物，美食，娱乐设施齐全。 酒店环境优雅，简约时尚，提供优质的床上用品，高速的宽带网络，宽大的液晶电视，舒适的淋浴、可口的自助早餐等一系列服务，让入住汉庭的您真正感受人在旅途，家在汉庭的温馨！',
       '江宁区禄口街道来凤路9号（百利广场2栋）(百利广场2栋)', 'LuKouAirport', 'Two', '025-52696789', 33, 224, 3.9, 3),
       (7, '南京老门东花间堂·朱雀里', '南京老门东花间堂·朱雀里位于秦淮风光中心地带，居闹市却不喧杂。因坐落于南京城南秦淮区，为“朱雀象”，取“笙歌浓酒盈朱雀，古籍奇珍满乌衣”的美好意念命名，引领旅人在探寻城南往日生活的同时，将念古情怀流淌在一砖一瓦一桥一井之间。「朱雀」，作为昔日胜景的象征，给这里带来更多“旧”的气息。设计师秉持时间痕迹与自然肌理不变的原则，由整体的叙述到边角的打磨，经过修缮、创新，衍生出现代审美和舒适体验。原始的木质结构在怀旧气息的空间场景中，辅以浸入南京肌骨里的梧桐景观，一触碰便可感受到过去的温度，新与旧、动与静都藏在这里。',
       '江苏省南京市秦淮区马道街47号', 'LaoMenDong', 'Five', '025-83151088', 33, 512, 5.0, 2);


UPDATE `hotel` SET rate = 4.5, commentsCount = 1 WHERE id = 1;

INSERT INTO `orderlist` (`id`, `userId`, `hotelId`, `hotelName`, `checkInDate`, `checkOutDate`, `roomId`, `roomType`,
                         `roomNum`, `peopleNum`, `haveChild`, `createDate`, `price`, `clientName`, `phoneNumber`,
                         `orderState`)
VALUES (1, 2, 1, '全季南京新街口王府大街酒店','2021-12-01', '2021-12-04', 1, '沧海大床房', 1, 2, '0', '2021-11-30', 912, "李泳劭", '13377084242', '已撤销'),
       (2, 2, 2, '桔子南京新街口张府园地铁站酒店', '2021-12-01', '2021-12-03', 3, '大床房A', 1, 1, '0', '2021-11-30', 648, '蒋祚竑', '13377084245', '已执行'),
       (3, 2, 3, '南京金鹰珠江壹号国际酒店', '2021-12-05', '2021-12-07', 6, '全景套间', 1, 1, '0', '2021-12-04', 2132, '蒋祚竑', '13377084245', '已执行'),
       (4, 2, 4, '南京泊金花间堂·琵琶山居', '2021-12-08', '2021-12-11', 8, '听雨套房', 1, 1, '0', '2021-11-30', 2700, '冯波云', '13377084246', '已执行'),
       (5, 2, 5, '南京仙林大学城漫心酒店', '2021-12-01', '2021-12-03', 10, '心悦高级标准房', 1, 1, '0', '2021-11-30', 700, '陈峙宇', '13377084243', '已评价'),
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
VALUES (1, 293, 28, 30, 1, '沧海大床房',
        '{\"area\":20,\"level\":5-10,\"bedType\":\"一张2*2\",\"addBed\":false,\"facility\":\"便利措施:雨伞、书桌、多种规格电源插座、110V电压插座、空调、衣柜衣橱、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具毯子或被子、国内长途电话、国际长途电话、有线频道、卫星频道、液晶电视机\"}'),
       (2, 199, 20, 20, 1, '都市榻榻米大床房',
        '{\"area\":15,\"level\":2-3,\"bedType\":\"一张2*2\",\"addBed\":false,\"facility\":\"便利措施:雨伞、书桌、多种规格电源插座、110V电压插座、空调、衣柜衣橱、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具毯子或被子、国内长途电话、国际长途电话、有线频道、卫星频道、液晶电视机\"}'),
       (3, 313, 30, 30, 2, '大床房A', '{\"area\":\"30\",\"level\":\"3-10\",\"bedType\":\"一张2*2\",\"addBed\":false, \"facility\":\"便利措施:浴缸 冰箱 音响 保险箱 电视 电吹风 独立卫生间 24小时热水 分体式（中央）空调 免费洗漱用品 有线/卫星电视接收 电水壶 免费瓶装水 拖鞋 免费咖啡包 免费茶包 衣柜 收费电影 针线包 客房Wifi覆盖 110V电压插座 免费文具用品\"}'),
       (4, 352, 30, 30, 2, '双床房',  '{\"area\":\"30\",\"level\":\"3-10\",\"bedType\":\"两张1.8*2\",\"addBed\":false, \"facility\":\"便利措施:浴缸 冰箱 音响 保险箱 电视 电吹风 独立卫生间 24小时热水 分体式（中央）空调 免费洗漱用品 有线/卫星电视接收 电水壶 免费瓶装水 拖鞋 免费咖啡包 免费茶包 衣柜 收费电影 针线包 客房Wifi覆盖 110V电压插座 免费文具用品\"}'),
       (5, 457, 10, 10, 3, '高级大床房', '{\"area\":\"40\",\"level\":\"3-10\",\"bedType\":\"一张1.8*2\",\"addBed\":false,\"facility\":\"全景落地窗、48寸LED液晶电视、BOSE音响系统、现磨咖啡机、超大洗漱空间、欧舒丹洗浴用品以及思涟床具等。\"}'),
       (6, 788, 10, 10, 3, '全景套间', '{\"area\":\"80\",\"level\":\"13-15\",\"bedType\":\"两张2*2\",\"addBed\":false,\"facility\":\"全景落地窗、48寸LED液晶电视、BOSE音响系统、现磨咖啡机、超大洗漱空间、欧舒丹洗浴用品以及思涟床具等。\"}'),
       (7, 597, 40, 40, 4, '紫金大床房', '{\"area\":\"50\",\"level\":\"1-2\",\"bedType\":\"一张2*2\",\"addBed\":false, \"facility\":\"浴缸 Mini Bar 电视 电吹风 独立卫生间 24小时热水 分体式（中央）空调 免费洗漱用品 电水壶 免费瓶装水 拖鞋 免费茶包 免费文具用品 衣柜 浴室化妆放大镜 110V电压插座 针线包 客房Wifi覆盖\"}'),
       (8, 940, 40, 40, 4, '听雨套房',
        '{\"area\":\"90\",\"level\":\"1-3\",\"bedType\":\"两张2*2\",\"addBed\":false, \"facility\":\"浴缸 Mini Bar 电视 电吹风 独立卫生间 24小时热水 分体式（中央）空调 免费洗漱用品 电水壶 免费瓶装水 拖鞋 免费茶包 免费文具用品 衣柜 浴室化妆放大镜 110V电压插座 针线包 客房Wifi覆盖\"}'),
       (9, 362, 35, 35, 5, '心悦高级大床房', '{\"area\":\"30\",\"level\":\"3-6\",\"bedType\":\"一张2*2\",\"addBed\":false, \"facility\":\"冰箱 音响 Mini Bar 电视 电吹风 独立卫生间 24小时热水 免费洗漱用品 国内长途电话 国际长途电话 有线/卫星电视接收 电水壶 免费瓶装水 拖鞋 免费茶包 免费文具用品 宽带上网接口 备用床具 浴室化妆放大镜 雨伞 收费电影 欢迎礼品 110V电压插座 针线包 客房Wifi覆盖 分体式（中央）空调\"}'),
       (10, 420, 30, 30, 5, '心悦高级标准房',
        '{\"area\":\"30\",\"level\":\"3-6\",\"bedType\":\"两张1.8*2\",\"addBed\":false, \"facility\":\"冰箱 音响 Mini Bar 电视 电吹风 独立卫生间 24小时热水 免费洗漱用品 国内长途电话 国际长途电话 有线/卫星电视接收 电水壶 免费瓶装水 拖鞋 免费茶包 免费文具用品 宽带上网接口 备用床具 浴室化妆放大镜 雨伞 收费电影 欢迎礼品 110V电压插座 针线包 客房Wifi覆盖 分体式（中央）空调\"}'),
       (11, 224, 25, 25, 6, '高级双床房', '{\"area\":\"25\",\"level\":\"2-8\",\"bedType\":\"两张1.8*2\",\"addBed\":false, \"facility\":\"便利措施:雨伞、书桌、多种规格电源插座、110V电压插座、空调、衣柜衣橱、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具毯子或被子、国内长途电话、国际长途电话、有线频道、卫星频道、液晶电视机\"}'),
       (12, 235, 24, 25, 6, '高级大床房', '{\"area\":\"25\",\"level\":\"2-8\",\"bedType\":\"一张2*2\",\"addBed\":false, \"facility\":\"便利措施:雨伞、书桌、多种规格电源插座、110V电压插座、空调、衣柜衣橱、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具毯子或被子、国内长途电话、国际长途电话、有线频道、卫星频道、液晶电视机\"}'),
       (13, 401, 50, 50, 7, '浣花大床房',
        '{\"area\":\"50\",\"level\":\"1-3\",\"bedType\":\"一张2*2\",\"addBed\":false, \"facility\":\"浴缸 冰箱 Mini Bar 电视 电吹风 独立卫生间 24小时热水 分体式（中央）空调 免费洗漱用品 电水壶 免费瓶装水 拖鞋 免费茶包 免费文具用品 衣柜 欢迎礼品 针线包 客房Wifi覆盖\"}'),
       (14, 690, 50, 50, 20, '吟桐高级套房', '{\"area\":\"60\",\"level\":\"1-3\",\"bedType\":\"两张2*2\",\"addBed\":false, \"facility\":\"浴缸 冰箱 Mini Bar 电视 电吹风 独立卫生间 24小时热水 分体式（中央）空调 免费洗漱用品 电水壶 免费瓶装水 拖鞋 免费茶包 免费文具用品 衣柜 欢迎礼品 针线包 客房Wifi覆盖\"}'),
       (46, 450, 50, 50, 21, 'BigBed', '{\"area\":\"40\",\"level\":\"3-6\",\"bedType\":\"2*2\",\"addBed\":false}'),
       (47, 500, 40, 40, 21, 'DoubleBed', '{\"area\":\"40\",\"level\":\"3-6\",\"bedType\":\"两张2*2\",\"addBed\":false}');

INSERT INTO `user` (`id`, `email`, `password`, `username`, `phonenumber`, `credit`, `usertype`)
VALUES (1, '111@qq.com', '123456', '有间酒店管理', '12345654321', 100, 'HotelManager'),
       (2, '1273641590@qq.com', '123456', 'Johnscript', '13377084245', 100, 'Client'),
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
