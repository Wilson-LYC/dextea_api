/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : dextea

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 04/12/2023 09:33:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for black_ip
-- ----------------------------
DROP TABLE IF EXISTS `black_ip`;
CREATE TABLE `black_ip`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of black_ip
-- ----------------------------

-- ----------------------------
-- Table structure for cate2comm
-- ----------------------------
DROP TABLE IF EXISTS `cate2comm`;
CREATE TABLE `cate2comm`  (
  `cate_id` int NOT NULL COMMENT '品类id',
  `comm_id` int NOT NULL COMMENT '商品id',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cate_id`, `comm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cate2comm
-- ----------------------------
INSERT INTO `cate2comm` VALUES (1, 1, '2023-12-03 00:47:56');
INSERT INTO `cate2comm` VALUES (1, 2, '2023-11-28 12:34:41');
INSERT INTO `cate2comm` VALUES (2, 1, '2023-12-03 00:47:57');
INSERT INTO `cate2comm` VALUES (2, 2, '2023-11-28 12:34:41');
INSERT INTO `cate2comm` VALUES (2, 3, '2023-12-03 00:48:09');
INSERT INTO `cate2comm` VALUES (4, 5, '2023-12-03 01:16:12');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '品类id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '🍓 新品上市', '2023-11-23 11:57:41', '2023-12-02 02:50:26');
INSERT INTO `category` VALUES (2, '🍇 清爽果茶', '2023-11-23 18:21:55', '2023-11-30 14:33:46');
INSERT INTO `category` VALUES (4, '🍼 真牛乳茶', '2023-11-25 11:33:44', '2023-11-30 14:33:22');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '详细介绍',
  `brief_intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '简介',
  `custom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '定制选项',
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '销售状态',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (1, '酷黑莓桑', 19.00, '喜茶年度口碑王回归。优选桑茸品种，每日手剥去蒂果肉紧致爆汁。新鲜草莓果肉与粒粒桑果肉口感层次叠加，甄选茶园定制绿妍茶汤与桑茸汁、草莓汁一起融合打成冰沙，酸甜果香与清雅茶香轻柔交织。', '喜茶年度口碑王回归。优选桑茸品种，每日手剥去蒂果肉紧致爆汁。', '[{\"title\":\"杯型\",\"option\":[{\"text\":\"酷黑定制杯\",\"cost\":0},{\"text\":\"常规杯\",\"cost\":0}],\"opt\":0},{\"title\":\"状态\",\"option\":[{\"text\":\"冰沙(推荐)\",\"cost\":0},{\"text\":\"非冰沙\",\"cost\":0}],\"opt\":0},{\"title\":\"冰量\",\"option\":[{\"text\":\"推荐\",\"cost\":0},{\"text\":\"少冰\",\"cost\":0},{\"text\":\"少少冰\",\"cost\":0},{\"text\":\"去冰(不推荐)\",\"cost\":0}],\"opt\":0},{\"title\":\"甜度\",\"option\":[{\"text\":\"推荐甜度(少甜)\",\"cost\":0},{\"text\":\"少少甜\",\"cost\":0},{\"text\":\"少少少甜\",\"cost\":0},{\"text\":\"不另外加糖(不推荐)\",\"cost\":0},{\"text\":\"多甜\",\"cost\":0}],\"opt\":0},{\"title\":\"茶底\",\"option\":[{\"text\":\"绿妍(推荐)\",\"cost\":0},{\"text\":\"去茶底\",\"cost\":0}],\"opt\":0},{\"title\":\"加料\",\"option\":[{\"text\":\"不加料\",\"cost\":0},{\"text\":\"加倍桑葚果肉￥4\",\"cost\":4}],\"opt\":0}]', '1', '2023-11-23 17:11:05', '2023-12-03 00:44:14', 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff36-酷黑莓桑.png');
INSERT INTO `commodity` VALUES (2, '喜柿多多', 19.00, '时令喜柿第二年回归。优选当季甜糯柿子，加入无香精绿妍茶与Q弹的脆波波。入口满满真柿子果肉，果肉软糯拉丝，软籽晶莹弹韧，茶香绵延清雅。喝喜柿，喜柿多。', '喜柿第二年回归。优选当季甜糯柿子，加入绿妍茶与Q弹的脆波波。', '[{\"title\":\"状态\",\"option\":[{\"text\":\"冰沙(推荐)\",\"cost\":0},{\"text\":\"非冰沙\",\"cost\":0}],\"opt\":0},{\"title\":\"冰量\",\"option\":[{\"text\":\"推荐\",\"cost\":0},{\"text\":\"少冰\",\"cost\":0},{\"text\":\"少少冰\",\"cost\":0},{\"text\":\"去冰(不推荐)\",\"cost\":0}],\"opt\":0},{\"title\":\"甜度\",\"option\":[{\"text\":\"推荐甜度(少甜)\",\"cost\":0},{\"text\":\"少少甜\",\"cost\":0},{\"text\":\"少少少甜\",\"cost\":0},{\"text\":\"不另外加糖(不推荐)\",\"cost\":0},{\"text\":\"多甜\",\"cost\":0}],\"opt\":0},{\"title\":\"小料\",\"option\":[{\"text\":\"标准（含脆波波）\",\"cost\":0},{\"text\":\"去脆波波\",\"cost\":0}],\"opt\":0},{\"title\":\"加料\",\"option\":[{\"text\":\"不加料\",\"cost\":0},{\"text\":\"加倍柿子果肉￥4\",\"cost\":4}],\"opt\":0}]', '1', '2023-11-23 20:22:54', '2023-12-02 14:07:05', 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656dde152cfcac457bff37-喜柿多多.png');
INSERT INTO `commodity` VALUES (3, '轻芝多肉葡萄', 19.00, '2022年首创轻芝多肉葡萄。优选当季巨峰葡萄，来自北纬40度黄金产区。鲜果颗颗手剥软嫩多汁，搭配清雅绿妍茶底及弹嫩葡萄冻，定制无奶精芝士，清爽不腻。默认标准杯500ml，可根据个人喜爱选择加大杯650ml.', '2022年首创轻芝多肉葡萄。优选当季巨峰葡萄，来自北纬40度黄金产区。', NULL, '1', '2023-11-23 20:32:27', '2023-12-02 13:50:15', 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff33-轻多肉葡萄.png');
INSERT INTO `commodity` VALUES (5, '烤黑糖波波牛乳茶', 19.00, '定制嫣红真茗茶底与无奶精真牛乳灵感特调，加入采用270天自然熟成木薯加工制成的黑糖波波，65分钟慢熬，口感软糯Q弹，不同于普通珍珠;甘醇饱满，入口丝滑。', '定制嫣红真茗茶底与无奶精真牛乳灵感特调', '[{\"title\":\"冰量\",\"option\":[{\"text\":\"推荐\",\"cost\":0},{\"text\":\"少冰\",\"cost\":0},{\"text\":\"少少冰\",\"cost\":0},{\"text\":\"去冰(不推荐)\",\"cost\":0}],\"opt\":0},{\"title\":\"甜度\",\"option\":[{\"text\":\"推荐甜度(少甜)\",\"cost\":0},{\"text\":\"少少甜\",\"cost\":0},{\"text\":\"少少少甜\",\"cost\":0},{\"text\":\"不另外加糖(不推荐)\",\"cost\":0},{\"text\":\"多甜\",\"cost\":0}],\"opt\":0},{\"title\":\"做法\",\"option\":[{\"text\":\"标准(推荐)\",\"cost\":0},{\"text\":\"去芝士(热饮默认去芝士)\",\"cost\":0}],\"opt\":0}]', '1', '2023-11-24 00:19:26', '2023-12-03 00:48:47', 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff35-烤黑糖波波牛乳.png');

-- ----------------------------
-- Table structure for commodity_his
-- ----------------------------
DROP TABLE IF EXISTS `commodity_his`;
CREATE TABLE `commodity_his`  (
  `id` int NOT NULL COMMENT '商品id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '详细介绍',
  `brief_intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '简介',
  `custom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '定制选项',
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售状态',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_his
-- ----------------------------
INSERT INTO `commodity_his` VALUES (10, '测试商品', 5.00, NULL, NULL, NULL, '0', '2023-11-26 20:45:18', '2023-11-26 20:45:18', NULL);
INSERT INTO `commodity_his` VALUES (11, '123', 123.00, '123', '123', '123', '1', '2023-12-02 13:38:59', '2023-12-02 13:38:59', NULL);
INSERT INTO `commodity_his` VALUES (12, '测试商品', 123.00, NULL, NULL, NULL, '0', '2023-12-02 13:54:09', '2023-12-02 13:54:09', NULL);
INSERT INTO `commodity_his` VALUES (13, '测试商品', 123.00, NULL, NULL, NULL, '0', '2023-12-02 13:54:22', '2023-12-02 13:54:22', NULL);
INSERT INTO `commodity_his` VALUES (14, '测试商品', 15.00, NULL, NULL, NULL, '0', '2023-12-02 13:54:46', '2023-12-02 13:54:46', NULL);
INSERT INTO `commodity_his` VALUES (15, '测试商品', 15.00, NULL, NULL, NULL, '0', '2023-12-02 13:55:20', '2023-12-02 13:55:20', NULL);
INSERT INTO `commodity_his` VALUES (16, '测试', 123.00, NULL, NULL, NULL, '0', '2023-12-02 13:57:34', '2023-12-02 13:57:38', NULL);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '顾客id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `open_id` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信openid',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'wilson', 'oblyy6-H1YYvE9MBYV3Kzt9BT_eQ', '2023-11-30 11:34:57', '2023-12-03 01:53:06', '13570790338');

-- ----------------------------
-- Table structure for customer_his
-- ----------------------------
DROP TABLE IF EXISTS `customer_his`;
CREATE TABLE `customer_his`  (
  `id` int NOT NULL COMMENT '顾客id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `open_id` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信openid',
  `createtime` datetime NULL DEFAULT NULL,
  `updatetime` datetime NULL DEFAULT NULL,
  `phone` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `deletetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_his
-- ----------------------------
INSERT INTO `customer_his` VALUES (2, 'test', NULL, '2023-12-03 02:01:48', '2023-12-03 02:01:48', NULL, '2023-12-03 02:08:24');
INSERT INTO `customer_his` VALUES (3, '123', NULL, '2023-12-03 02:09:32', '2023-12-03 02:09:34', '123', '2023-12-03 02:09:38');

-- ----------------------------
-- Table structure for img_db
-- ----------------------------
DROP TABLE IF EXISTS `img_db`;
CREATE TABLE `img_db`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_db
-- ----------------------------
INSERT INTO `img_db` VALUES (8, 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff36-酷黑莓桑.png', '2023-11-28 12:33:59');
INSERT INTO `img_db` VALUES (9, 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff33-轻多肉葡萄.png', '2023-11-28 12:33:59');
INSERT INTO `img_db` VALUES (10, 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff35-烤黑糖波波牛乳.png', '2023-11-28 12:33:59');
INSERT INTO `img_db` VALUES (11, 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff34-多肉桃李.png', '2023-11-28 12:33:59');
INSERT INTO `img_db` VALUES (12, 'https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656dde152cfcac457bff37-喜柿多多.png', '2023-11-28 12:34:39');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES (1, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-27 20:13:17');
INSERT INTO `login_log` VALUES (2, '192.168.205.116', '{\"role\":\"staff\",\"account\":\"lzh\"}', '2023-11-27 21:02:31');
INSERT INTO `login_log` VALUES (3, '192.168.205.116', '{\"role\":\"staff\",\"account\":\"lzh\"}', '2023-11-27 21:03:13');
INSERT INTO `login_log` VALUES (4, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-27 22:07:40');
INSERT INTO `login_log` VALUES (5, '192.168.205.116', '{\"role\":\"staff\",\"account\":\"lzh\"}', '2023-11-27 22:31:03');
INSERT INTO `login_log` VALUES (6, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-27 22:34:48');
INSERT INTO `login_log` VALUES (7, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:29:14');
INSERT INTO `login_log` VALUES (8, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:32:29');
INSERT INTO `login_log` VALUES (9, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:33:53');
INSERT INTO `login_log` VALUES (10, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:34:54');
INSERT INTO `login_log` VALUES (11, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:37:24');
INSERT INTO `login_log` VALUES (12, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:42:55');
INSERT INTO `login_log` VALUES (13, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 00:49:11');
INSERT INTO `login_log` VALUES (14, '192.168.205.116', '{\"role\":\"staff\",\"account\":\"lzh\"}', '2023-11-28 00:51:44');
INSERT INTO `login_log` VALUES (15, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 11:46:56');
INSERT INTO `login_log` VALUES (16, '192.168.205.116', '{\"role\":\"staff\",\"account\":\"lzh\"}', '2023-11-28 12:09:13');
INSERT INTO `login_log` VALUES (17, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-28 12:27:53');
INSERT INTO `login_log` VALUES (18, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-11-28 23:08:43');
INSERT INTO `login_log` VALUES (19, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-11-28 23:52:17');
INSERT INTO `login_log` VALUES (20, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-11-29 00:42:06');
INSERT INTO `login_log` VALUES (21, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-30 00:02:04');
INSERT INTO `login_log` VALUES (22, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-11-30 00:49:59');
INSERT INTO `login_log` VALUES (23, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-01 13:49:15');
INSERT INTO `login_log` VALUES (24, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-01 14:34:09');
INSERT INTO `login_log` VALUES (25, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-01 23:33:54');
INSERT INTO `login_log` VALUES (26, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 00:03:18');
INSERT INTO `login_log` VALUES (27, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 13:06:35');
INSERT INTO `login_log` VALUES (28, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 17:50:55');
INSERT INTO `login_log` VALUES (29, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-02 17:51:21');
INSERT INTO `login_log` VALUES (30, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 17:54:12');
INSERT INTO `login_log` VALUES (31, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 17:54:36');
INSERT INTO `login_log` VALUES (32, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 17:55:02');
INSERT INTO `login_log` VALUES (33, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-02 17:56:02');
INSERT INTO `login_log` VALUES (34, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-03 00:01:28');
INSERT INTO `login_log` VALUES (35, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-03 00:17:33');
INSERT INTO `login_log` VALUES (36, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-03 13:08:17');
INSERT INTO `login_log` VALUES (37, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 14:05:04');
INSERT INTO `login_log` VALUES (38, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 14:43:24');
INSERT INTO `login_log` VALUES (39, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-03 15:18:03');
INSERT INTO `login_log` VALUES (40, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 15:23:47');
INSERT INTO `login_log` VALUES (41, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 17:10:58');
INSERT INTO `login_log` VALUES (42, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 17:23:56');
INSERT INTO `login_log` VALUES (43, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 17:26:37');
INSERT INTO `login_log` VALUES (44, '127.0.0.1', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 18:30:28');
INSERT INTO `login_log` VALUES (45, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 19:59:23');
INSERT INTO `login_log` VALUES (46, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-03 20:00:42');
INSERT INTO `login_log` VALUES (47, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 20:03:11');
INSERT INTO `login_log` VALUES (48, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 20:04:28');
INSERT INTO `login_log` VALUES (49, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 20:09:18');
INSERT INTO `login_log` VALUES (50, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-03 23:26:35');
INSERT INTO `login_log` VALUES (51, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-04 00:04:28');
INSERT INTO `login_log` VALUES (52, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-04 00:50:09');
INSERT INTO `login_log` VALUES (53, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzwsgc1\"}', '2023-12-04 00:50:24');
INSERT INTO `login_log` VALUES (54, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzwsgc1\"}', '2023-12-04 00:55:20');
INSERT INTO `login_log` VALUES (55, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-04 00:56:04');
INSERT INTO `login_log` VALUES (56, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-04 01:01:07');
INSERT INTO `login_log` VALUES (57, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-04 01:03:16');
INSERT INTO `login_log` VALUES (58, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-04 01:03:28');
INSERT INTO `login_log` VALUES (59, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"wilson\"}', '2023-12-04 01:14:33');
INSERT INTO `login_log` VALUES (60, '192.168.205.57', '{\"role\":\"staff\",\"account\":\"gzgogo1\"}', '2023-12-04 01:14:46');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `cust_id` int NOT NULL COMMENT '顾客id',
  `store_id` int NOT NULL COMMENT '店铺id',
  `state` int NOT NULL COMMENT '订单状态',
  `price` decimal(10, 2) NOT NULL COMMENT '总价',
  `num` int NOT NULL COMMENT '总数量',
  `commodity` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '购物清单',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `note` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '取餐码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (4, 1, 1, 2, 38.00, 2, '[{\"id\":1,\"name\":\"酷黑莓桑\",\"price\":19,\"num\":1,\"img\":\"https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff36-酷黑莓桑.png\",\"custom\":[{\"item\":\"杯型\",\"value\":\"酷黑定制杯\"},{\"item\":\"状态\",\"value\":\"冰沙(推荐)\"},{\"item\":\"冰量\",\"value\":\"推荐\"},{\"item\":\"甜度\",\"value\":\"推荐甜度(少甜)\"},{\"item\":\"茶底\",\"value\":\"绿妍(推荐)\"},{\"item\":\"加料\",\"value\":\"不加料\"}]},{\"id\":2,\"name\":\"喜柿多多\",\"price\":19,\"num\":1,\"img\":\"https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656dde152cfcac457bff37-喜柿多多.png\",\"custom\":[{\"item\":\"杯型\",\"value\":\"酷黑定制杯\"},{\"item\":\"状态\",\"value\":\"冰沙(推荐)\"},{\"item\":\"冰量\",\"value\":\"推荐\"},{\"item\":\"甜度\",\"value\":\"推荐甜度(少甜)\"},{\"item\":\"茶底\",\"value\":\"绿妍(推荐)\"},{\"item\":\"加料\",\"value\":\"不加料\"}]}]', '2023-12-04 00:42:15', '2023-12-04 00:42:15', '13570790338', '备注', '8003');
INSERT INTO `order` VALUES (5, 1, 1, 2, 38.00, 2, '[{\"id\":1,\"name\":\"酷黑莓桑\",\"price\":19,\"num\":1,\"img\":\"https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656db7152cfcac457bff36-酷黑莓桑.png\",\"custom\":[{\"item\":\"杯型\",\"value\":\"酷黑定制杯\"},{\"item\":\"状态\",\"value\":\"冰沙(推荐)\"},{\"item\":\"冰量\",\"value\":\"推荐\"},{\"item\":\"甜度\",\"value\":\"推荐甜度(少甜)\"},{\"item\":\"茶底\",\"value\":\"绿妍(推荐)\"},{\"item\":\"加料\",\"value\":\"不加料\"}]},{\"id\":2,\"name\":\"喜柿多多\",\"price\":19,\"num\":1,\"img\":\"https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/customer/65656dde152cfcac457bff37-喜柿多多.png\",\"custom\":[{\"item\":\"杯型\",\"value\":\"酷黑定制杯\"},{\"item\":\"状态\",\"value\":\"冰沙(推荐)\"},{\"item\":\"冰量\",\"value\":\"推荐\"},{\"item\":\"甜度\",\"value\":\"推荐甜度(少甜)\"},{\"item\":\"茶底\",\"value\":\"绿妍(推荐)\"},{\"item\":\"加料\",\"value\":\"不加料\"}]}]', '2023-12-04 00:42:18', '2023-12-04 00:42:18', '13570790338', '备注', '8004');

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '键',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '值',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES (1, 'open_area', '[{\"value\":\"广东省\",\"num\":6,\"children\":[{\"value\":\"广州市\",\"children\":[],\"num\":5},{\"value\":\"深圳市\",\"children\":[],\"num\":0},{\"value\":\"韶关市\",\"children\":[],\"num\":1}]},{\"value\":\"上海市\",\"num\":1,\"children\":[]}]', '2023-11-17 23:45:56', '2023-12-02 01:14:39');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '工作人员id',
  `account` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工类型',
  `store_id` int NULL DEFAULT NULL COMMENT '所属门店id',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'wilson', 'd9b1d7db4cd6e70935368a1efb10e377', '0', NULL, '2023-11-20 21:25:18', '2023-11-26 14:18:25', '赖永超');
INSERT INTO `staff` VALUES (2, 'admin', 'd9b1d7db4cd6e70935368a1efb10e377', '0', NULL, '2023-11-20 21:26:39', '2023-11-26 14:23:06', '超级管理员');
INSERT INTO `staff` VALUES (3, 'gzgogo1', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 1, '2023-11-22 18:16:26', '2023-11-26 14:23:37', '广州gogo新天地店01');
INSERT INTO `staff` VALUES (4, 'gzwsgc1', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 2, '2023-11-22 18:34:00', '2023-11-26 14:20:47', '广州万胜广场店01');
INSERT INTO `staff` VALUES (5, 'gzthlab1', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 3, '2023-11-22 23:43:52', '2023-11-26 14:21:00', '广州天环广场lab店01');
INSERT INTO `staff` VALUES (6, 'gzpzblgc1', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 4, '2023-11-23 00:16:53', '2023-11-26 14:21:59', '广州琶洲保利广场店01');
INSERT INTO `staff` VALUES (8, 'gzhzwd1', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 5, '2023-11-23 00:18:29', '2023-11-26 14:21:41', '广州海珠万达店01');
INSERT INTO `staff` VALUES (10, 'op1', 'd9b1d7db4cd6e70935368a1efb10e377', '1', NULL, '2023-11-25 23:13:35', '2023-11-26 14:22:59', '公司运营01');
INSERT INTO `staff` VALUES (12, 'lzh', 'd9b1d7db4cd6e70935368a1efb10e377', '0', NULL, '2023-11-26 13:17:21', '2023-11-26 14:22:35', '罗梓浩');
INSERT INTO `staff` VALUES (16, 'sgbndj1', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 10, '2023-12-02 02:21:10', '2023-12-02 02:27:12', '韶关百年东街店01');
INSERT INTO `staff` VALUES (18, 'gzgogo2', '', '2', 1, '2023-12-04 01:16:39', '2023-12-04 01:24:06', '广州gogo新天地店02');
INSERT INTO `staff` VALUES (19, 'gzgogo3', '', '2', 1, '2023-12-04 01:23:59', '2023-12-04 01:24:37', '广州gogo新天地店03');

-- ----------------------------
-- Table structure for staff_his
-- ----------------------------
DROP TABLE IF EXISTS `staff_his`;
CREATE TABLE `staff_his`  (
  `id` int NOT NULL COMMENT '工作人员id',
  `account` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工类型',
  `store_id` int NULL DEFAULT NULL COMMENT '所属门店id',
  `createtime` datetime NULL DEFAULT NULL,
  `updatetime` datetime NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `deletetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff_his
-- ----------------------------
INSERT INTO `staff_his` VALUES (13, 'op2', 'd9b1d7db4cd6e70935368a1efb10e377', '1', NULL, '2023-11-26 14:08:01', '2023-11-26 14:22:54', '公司运营02', '2023-12-02 02:16:29');
INSERT INTO `staff_his` VALUES (14, 'test123123', 'e10adc3949ba59abbe56e057f20f883e', '2', 2, '2023-12-02 01:37:11', '2023-12-02 01:37:11', '广州万胜广场店1', '2023-12-02 02:17:20');
INSERT INTO `staff_his` VALUES (15, '123123', 'f53d276f50d10a527e35b945260226a1', '0', 0, '2023-12-02 02:17:30', '2023-12-02 02:17:30', '312312312312', '2023-12-02 02:17:33');
INSERT INTO `staff_his` VALUES (17, 'gzgogo2', 'd9b1d7db4cd6e70935368a1efb10e377', '2', 1, '2023-12-04 01:10:47', '2023-12-04 01:13:15', '广州gogo新天地02', '2023-12-04 01:16:21');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '门店ID',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门店名称',
  `area` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所在省/市区',
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `phone` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `open_time` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营业时间',
  `open_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营业状态',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '广州gogo新天地店', '[\"广东省\",\"广州市\"]', '小谷围街贝岗村贝岗村大街1号高高新天地商业广场一期一层第103、104、141、142号商铺', '020-85202718', '周一至周日 10:00-22:00', '1', '2023-11-02 23:47:28', '2023-12-02 00:39:24');
INSERT INTO `store` VALUES (2, '广州万胜广场店', '[\"广东省\", \"广州市\"]', '广东省广州市海珠区新港东路1236号101铺', '020-80927484', '周一至周四 10:00-21:00，周五至周日 10:00-22:00', '2', '2023-11-04 02:03:11', '2023-12-04 00:50:15');
INSERT INTO `store` VALUES (3, '广州天环广场LAB店', '[\"广东省\",\"广州市\"]', '广州市天河区天河路218号天环广场地上一层 址L105、地上二层L205号商铺', '020-85201268', '周一至周四 09:00-22:00，周五至周日 09:00-22:30', '1', '2023-11-20 23:55:10', '2023-12-02 00:39:24');
INSERT INTO `store` VALUES (4, '广州琶洲保利广场店', '[\"广东省\",\"广州市\"]', '琶洲蟠龙新街2号保利广 场购物中心1层1005、1006商铺', '020-85208728', '周一至周日 10:00-23:00', '1', '2023-11-21 00:04:25', '2023-12-02 00:39:24');
INSERT INTO `store` VALUES (5, '广州海珠万达店', '[\"广东省\",\"广州市\"]', '逸景路236号、238号、广州大道南976、978号天雄(广场室内步行街一层1021A-1021B号商铺', '020-85202578', '周一至周日 00:00-23:59', '1', '2023-11-21 00:09:17', '2023-12-02 00:39:24');
INSERT INTO `store` VALUES (8, '上海BFC外滩金融中心店', '[\"上海市\"]', '中山东二路588号外滩国际金融服务中心1层S117单元', '021-63670078', '周一至周日 10:00-22:00', '1', '2023-11-21 12:39:49', '2023-12-02 00:39:24');
INSERT INTO `store` VALUES (10, '韶关百年东街店', '[\"广东省\",\"韶关市\"]', '这是韶关百年东街店的详细地址', '0751-8536966', '周一至周日 00:00-23:59', '1', '2023-11-25 22:58:35', '2023-12-02 00:39:24');

-- ----------------------------
-- Table structure for store_comm
-- ----------------------------
DROP TABLE IF EXISTS `store_comm`;
CREATE TABLE `store_comm`  (
  `store_id` int NOT NULL COMMENT '门店ID',
  `comm_id` int NOT NULL COMMENT '商品ID',
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`store_id`, `comm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_comm
-- ----------------------------
INSERT INTO `store_comm` VALUES (1, 1, '2023-12-04 01:03:21');
INSERT INTO `store_comm` VALUES (1, 2, '2023-12-04 01:03:21');
INSERT INTO `store_comm` VALUES (1, 3, '2023-12-04 01:03:21');
INSERT INTO `store_comm` VALUES (1, 5, '2023-12-04 01:03:21');
INSERT INTO `store_comm` VALUES (2, 1, '2023-12-03 00:34:58');
INSERT INTO `store_comm` VALUES (2, 2, '2023-12-03 00:34:58');
INSERT INTO `store_comm` VALUES (2, 3, '2023-12-03 00:34:58');
INSERT INTO `store_comm` VALUES (2, 5, '2023-12-03 00:34:58');

-- ----------------------------
-- View structure for category_view
-- ----------------------------
DROP VIEW IF EXISTS `category_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `category_view` AS select `category`.`id` AS `id`,`category`.`name` AS `name`,count(`cate2comm`.`cate_id`) AS `num` from (`category` left join `cate2comm` on((`category`.`id` = `cate2comm`.`cate_id`))) group by `category`.`id`;

-- ----------------------------
-- View structure for commmodity_unwind
-- ----------------------------
DROP VIEW IF EXISTS `commmodity_unwind`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `commmodity_unwind` AS select `commodity`.`id` AS `id`,`commodity`.`name` AS `name`,`commodity`.`price` AS `price`,`commodity`.`state` AS `state`,`commodity`.`introduce` AS `introduce`,`commodity`.`brief_intro` AS `brief_intro`,`commodity`.`custom` AS `custom`,`category`.`id` AS `cate_id`,`category`.`name` AS `cate_name`,`commodity`.`img` AS `img`,`commodity`.`updatetime` AS `updatetime`,`commodity`.`createtime` AS `createtime` from ((`commodity` left join `cate2comm` on((`commodity`.`id` = `cate2comm`.`comm_id`))) left join `category` on((`cate2comm`.`cate_id` = `category`.`id`)));

-- ----------------------------
-- View structure for commmodity_view
-- ----------------------------
DROP VIEW IF EXISTS `commmodity_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `commmodity_view` AS select `commodity`.`id` AS `id`,`commodity`.`name` AS `name`,`commodity`.`price` AS `price`,`commodity`.`state` AS `state`,`commodity`.`introduce` AS `introduce`,`commodity`.`brief_intro` AS `brief_intro`,`commodity`.`custom` AS `custom`,group_concat(`category`.`name` separator ',') AS `category`,`commodity`.`img` AS `img`,`commodity`.`createtime` AS `createtime`,`commodity`.`updatetime` AS `updatetime` from ((`commodity` left join `cate2comm` on((`commodity`.`id` = `cate2comm`.`comm_id`))) left join `category` on((`cate2comm`.`cate_id` = `category`.`id`))) group by `commodity`.`id`;

-- ----------------------------
-- View structure for menu
-- ----------------------------
DROP VIEW IF EXISTS `menu`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `menu` AS select `store`.`id` AS `store_id`,`store`.`name` AS `store_name`,`commmodity_view`.`id` AS `id`,`commmodity_view`.`name` AS `name`,`commmodity_view`.`price` AS `price`,`commmodity_view`.`introduce` AS `introduce`,`commmodity_view`.`brief_intro` AS `brief_intro`,`commmodity_view`.`custom` AS `custom`,`commmodity_view`.`category` AS `category`,count(`store_comm`.`store_id`) AS `state`,`commmodity_view`.`img` AS `img` from ((`store` join `commmodity_view`) left join `store_comm` on(((`commmodity_view`.`id` = `store_comm`.`comm_id`) and (`store`.`id` = `store_comm`.`store_id`)))) where (`commmodity_view`.`state` = 1) group by `store`.`id`,`commmodity_view`.`id`;

-- ----------------------------
-- View structure for menu_unwind
-- ----------------------------
DROP VIEW IF EXISTS `menu_unwind`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `menu_unwind` AS select `menu`.`store_id` AS `store_id`,`menu`.`store_name` AS `store_name`,`menu`.`id` AS `id`,`menu`.`name` AS `name`,`menu`.`price` AS `price`,`menu`.`introduce` AS `introduce`,`menu`.`brief_intro` AS `brief_intro`,`menu`.`custom` AS `custom`,`menu`.`img` AS `img`,`menu`.`sell_state` AS `sell_state`,`category`.`id` AS `cate_id`,`category`.`name` AS `cate_name` from ((`menu` left join `cate2comm` on((`menu`.`id` = `cate2comm`.`comm_id`))) left join `category` on((`cate2comm`.`cate_id` = `category`.`id`)));

-- ----------------------------
-- View structure for order_view
-- ----------------------------
DROP VIEW IF EXISTS `order_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `order_view` AS select `order`.`id` AS `id`,`order`.`cust_id` AS `cust_id`,`customer`.`name` AS `cust_name`,`order`.`store_id` AS `store_id`,`store`.`name` AS `store_name`,`order`.`code` AS `code`,`order`.`state` AS `state`,`order`.`price` AS `price`,`order`.`num` AS `num`,`order`.`commodity` AS `commodity`,`order`.`phone` AS `phone`,`order`.`note` AS `note`,`order`.`createtime` AS `createtime`,`order`.`updatetime` AS `updatetime` from ((`order` join `customer` on((`order`.`cust_id` = `customer`.`id`))) join `store` on((`store`.`id` = `order`.`store_id`)));

-- ----------------------------
-- View structure for staff_store
-- ----------------------------
DROP VIEW IF EXISTS `staff_store`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `staff_store` AS select `staff`.`id` AS `id`,`staff`.`account` AS `account`,`staff`.`password` AS `password`,`staff`.`role` AS `role`,`staff`.`store_id` AS `store_id`,`staff`.`name` AS `name`,`store`.`name` AS `store_name`,`staff`.`createtime` AS `createtime`,`staff`.`updatetime` AS `updatetime` from (`staff` left join `store` on((`staff`.`store_id` = `store`.`id`)));

-- ----------------------------
-- Procedure structure for get_store_menu
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_store_menu`;
delimiter ;;
CREATE PROCEDURE `get_store_menu`(IN `sid` int)
BEGIN
	SELECT id,name,price,introduce,brief_intro,custom,category,sell_state from menu where store_id=sid;
	
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table commodity
-- ----------------------------
DROP TRIGGER IF EXISTS `commodity_delete_trigger`;
delimiter ;;
CREATE TRIGGER `commodity_delete_trigger` BEFORE DELETE ON `commodity` FOR EACH ROW BEGIN
    INSERT INTO commodity_his
    VALUES (OLD.id, OLD.name, OLD.price, OLD.introduce,OLD.brief_intro,OLD.custom,OLD.state,OLD.createtime,OLD.updatetime,OLD.img);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table customer
-- ----------------------------
DROP TRIGGER IF EXISTS `customer_delete_trigger`;
delimiter ;;
CREATE TRIGGER `customer_delete_trigger` BEFORE DELETE ON `customer` FOR EACH ROW BEGIN
    INSERT INTO customer_his (id,name,phone,createtime,updatetime)
    VALUES (OLD.id, OLD.name, OLD.phone, OLD.createtime,OLD.updatetime);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table staff
-- ----------------------------
DROP TRIGGER IF EXISTS `staff_delete_trigger`;
delimiter ;;
CREATE TRIGGER `staff_delete_trigger` BEFORE DELETE ON `staff` FOR EACH ROW BEGIN
    INSERT INTO staff_his (id,account,`password`,role,store_id,createtime,updatetime,`name`)
    VALUES (OLD.id, OLD.account, OLD.password, OLD.role, OLD.store_id, OLD.createtime, OLD.updatetime, OLD.name);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
