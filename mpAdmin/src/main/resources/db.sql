drop table  if exists gmall_admin.`ali_goods_info`;
CREATE TABLE `ali_goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `corp` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司',
  `price` decimal(12,2) DEFAULT '0.00' COMMENT '价格',
  `deal` decimal(12,2) DEFAULT '0.00' COMMENT '成交量',
  `rebuy` decimal(12,2) DEFAULT '0.00' COMMENT '复购率',
  `goodsUrl` varchar(256) DEFAULT NULL COMMENT '商品链接',
  `goodsId` bigint(20) NOT NULL COMMENT '',
  `img` varchar(2048) DEFAULT NULL COMMENT '图片链接',
  `name` varchar(256) DEFAULT NULL COMMENT '商品名',
  PRIMARY KEY (`id`),
  KEY `goodsId` (`goodsId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='阿里商品信息';

drop table if exists gmall_admin.`ali_search_goods`;
CREATE TABLE `ali_search_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img` varchar(256) DEFAULT NULL COMMENT '图片链接',
  `name` varchar(256) DEFAULT NULL COMMENT '商品名',
  `price` decimal(12,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='阿里搜索商品历史';



drop table  if exists gmall_admin.`taobao_goods_info`;
CREATE TABLE `taobao_goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(256) DEFAULT NULL COMMENT '商品名',
  `price` decimal(12,2) DEFAULT '0.00' COMMENT '价格',
  `dealCount` decimal(12,2) DEFAULT '0.00' COMMENT '付款人数',
  `goodsUrl` varchar(2048) DEFAULT NULL COMMENT '商品链接',
  `img` varchar(2048) DEFAULT NULL COMMENT '图片链接',
  `shopName` varchar(256) DEFAULT NULL COMMENT '商铺名',
  `shopUrl` varchar(2048) DEFAULT NULL COMMENT '商铺链接',
  `location` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司',
  `goodsId` bigint(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id`),
  KEY `goodsId` (`goodsId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='淘宝商品信息';

drop table if exists gmall_admin.`taobao_search_goods`;
CREATE TABLE `taobao_search_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img` varchar(256) DEFAULT NULL COMMENT '图片链接',
  `name` varchar(256) DEFAULT NULL COMMENT '商品名',
  `price` decimal(12,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='淘宝搜索商品历史';
