SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
create TABLE `sys_user`
(
    `user_id`        bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `account`        varchar(64)  DEFAULT NULL COMMENT '账号',
    `phone`          varchar(11)  DEFAULT NULL COMMENT '手机号',
    `email`          varchar(32)  DEFAULT NULL COMMENT '邮箱地址',
    `password`       varchar(255) DEFAULT NULL COMMENT '用户密码',
    `user_name`      varchar(32)  DEFAULT NULL COMMENT '用户名称',
    `sex`            tinyint(2)   DEFAULT NULL COMMENT '性别 0:女  1:男  2:保密',
    `birthday`       date         DEFAULT NULL COMMENT '出身日期yyyy-MM-dd',
    `nick_name`      varchar(32)  DEFAULT NULL COMMENT '用户昵称',
    `avatar`         varchar(255) DEFAULT NULL COMMENT '头像地址',
    `is_locked`      tinyint(1)   DEFAULT '0' COMMENT '是否锁定 0开放  1锁定',
    `del_flag`       tinyint(1)   DEFAULT '0' COMMENT '删除标志 0未删除 1删除',
    `pwd_reset_time` datetime     DEFAULT NULL COMMENT '修改密码的时间',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间',
    `create_by`      bigint(20)   DEFAULT NULL COMMENT '创建人',
    `update_time`    datetime     DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '修改时间',
    `update_by`      bigint(20)   DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `idx_acc` (`account`) COMMENT '账户唯一索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8 COMMENT ='系统管理员表';

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
create TABLE `sys_post`
(
    `post_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `post_code`   varchar(64) NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50) NOT NULL COMMENT '岗位名称',
    `post_sort`   int(11)     NOT NULL COMMENT '显示顺序',
    `status`      tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   bigint(20)           DEFAULT '0' COMMENT '创建者',
    `create_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20)           DEFAULT NULL COMMENT '更新者',
    `update_time` datetime             DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(128)         DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='岗位信息表';

-- ----------------------------
-- Table structure for sys_admin_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
create TABLE `sys_user_post`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`user_id`, `post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户与岗位关联表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
create TABLE `sys_role`
(
    `role_id`     bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_code`   varchar(100) NOT NULL COMMENT '角色权限字符串',
    `role_type`   varchar(100)          DEFAULT NULL COMMENT '角色类型标识',
    `role_name`   varchar(255) NOT NULL COMMENT '角色名称',
    `description` varchar(255)          DEFAULT NULL COMMENT '描述',
    `data_scope`  varchar(255)          DEFAULT NULL COMMENT '数据权限',
    `order_num`   int(11)      NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `status`      tinyint(1)   NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
    `create_by`   bigint(20)            DEFAULT '0' COMMENT '创建者',
    `create_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20)            DEFAULT '0' COMMENT '更新者',
    `update_time` datetime              DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`      varchar(128)          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`),
    KEY `idx_role_code` (`role_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='角色信息表';

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
create TABLE `sys_admin_role`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户id',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户角色关联表';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
create TABLE `sys_dept`
(
    `dept_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `dept_name`   varchar(30) DEFAULT '' COMMENT '部门名称',
    `order_num`   int(11)     DEFAULT '0' COMMENT '显示顺序',
    `leader`      bigint(20)  DEFAULT NULL COMMENT '负责人',
    `phone`       varchar(11) DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50) DEFAULT NULL COMMENT '邮箱',
    `status`      tinyint(1)  DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `parent_id`   bigint(20)  DEFAULT '0' COMMENT '父部门id',
    `create_by`   bigint(20)  DEFAULT '0' COMMENT '创建者',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   bigint(20)  DEFAULT '0' COMMENT '更新者',
    `update_time` datetime    DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`dept_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='部门表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------

create TABLE `sys_menu`
(
    `menu_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `parent_menu_id` bigint(20)   DEFAULT 0 COMMENT '上级菜单ID',
    `menu_sort`      int(5)       DEFAULT 0 COMMENT '排序',
    `sub_count`      int(5)       DEFAULT 0 COMMENT '子菜单数目',
    `type`           int(11)    NOT NULL COMMENT '菜单类型',
    `title`          varchar(255) DEFAULT NULL COMMENT '菜单标题',
    `name`           varchar(255) DEFAULT NULL COMMENT '组件名称',
    `component`      varchar(255) DEFAULT NULL COMMENT '组件',
    `icon`           varchar(255) DEFAULT NULL COMMENT '图标',
    `path`           varchar(255) DEFAULT NULL COMMENT '链接地址',
    `is_frame`       bit(1)       DEFAULT NULL COMMENT '是否外链',
    `hidden`         bit(1)       DEFAULT b'0' COMMENT '隐藏',
    `permission`     varchar(255) DEFAULT NULL COMMENT '权限',
    `status`         tinyint(1)   DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `create_by`      bigint(20)   DEFAULT '0' COMMENT '创建者',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`      bigint(20)   DEFAULT '0' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`         varchar(128) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`) USING BTREE,
    UNIQUE KEY `uniq_title` (`title`),
    UNIQUE KEY `uniq_name` (`name`),
    KEY `inx_pid` (`parent_menu_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 118
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='系统菜单';


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
create TABLE `sys_role_menu`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='角色和菜单关联表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
create TABLE `sys_permission`
(
    `permission_id` bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `name`          varchar(64)  NOT NULL COMMENT '接口权限名称',
    `code`          varchar(128) NOT NULL COMMENT '权限编码',
    `url`           varchar(128)          DEFAULT NULL COMMENT '接口地址 格式 method:url',
    `description`   varchar(255)          DEFAULT NULL COMMENT '描述',
    `status`        tinyint(1)   NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `del_flag`      tinyint(1)            DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
    `create_by`     bigint(20)            DEFAULT '0' COMMENT '创建者',
    `create_time`   datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`     bigint(20)            DEFAULT NULL COMMENT '更新者',
    `update_time`   datetime              DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`        varchar(128)          DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`permission_id`),
    UNIQUE KEY `uniq_code` (`code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 69
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='权限编码信息表';



-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
create TABLE `sys_dict_type`
(
    `dict_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) DEFAULT '' COMMENT '字典类型',
    `status`      tinyint(1)   DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   bigint(20)   DEFAULT NULL COMMENT '创建人',
    `update_time` datetime     DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '修改时间',
    `update_by`   bigint(20)   DEFAULT NULL COMMENT '更新人',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`),
    UNIQUE KEY `idx_dict_type` (`dict_type`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='字典类型表';

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
create TABLE `sys_dict_data`
(
    `data_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典数据ID',
    `dict_type`      varchar(100)        DEFAULT '' COMMENT '字典类型',
    `dict_sort`      int(11)             DEFAULT '0' COMMENT '字典排序',
    `dict_label`     varchar(100)        DEFAULT '' COMMENT '字典标签',
    `dict_value`     varchar(100)        DEFAULT '' COMMENT '字典键值',
    `is_default`     tinyint(1)          DEFAULT '0' COMMENT '是否默认（0否 1是）',
    `parent_data_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父字典数据ID',
    `list_class`     varchar(100)        DEFAULT NULL COMMENT '表格回显样式',
    `css_class`      varchar(100)        DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `status`         tinyint(1)          DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_time`    datetime            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`      bigint(20)          DEFAULT NULL COMMENT '创建人',
    `update_time`    datetime            DEFAULT NULL ON update CURRENT_TIMESTAMP COMMENT '修改时间',
    `update_by`      bigint(20)          DEFAULT NULL COMMENT '更新人',
    `remark`         varchar(500)        DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`data_id`),
    KEY `idx_type` (`dict_type`) COMMENT '数据类型索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 35
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='字典数据表';


-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
create TABLE `sys_log`
(
    `log_id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `log_type`         varchar(64)  DEFAULT NULL COMMENT '日志类型',
    `log_status`       int(11)      DEFAULT '0' COMMENT '请求状态 0:正常 1:异常',
    `method`           varchar(255) DEFAULT NULL COMMENT '请求方法',
    `path`             varchar(255) DEFAULT NULL COMMENT '请求方法',
    `params`           text COMMENT '请求参数',
    `description`      varchar(255) DEFAULT NULL COMMENT '描叙',
    `request_ip`       varchar(255) DEFAULT NULL COMMENT '请求ip',
    `time`             bigint(20)   DEFAULT NULL COMMENT '请求耗时',
    `address`          varchar(255) DEFAULT NULL COMMENT '地址',
    `browser`          varchar(255) DEFAULT NULL COMMENT '浏览器',
    `exception_detail` text COMMENT '异常详情',
    `account`          varchar(32)  DEFAULT NULL COMMENT '账户',
    `user_id`          bigint(20)   DEFAULT NULL COMMENT '用户id',
    `create_time`      datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`log_id`) USING BTREE,
    KEY `log_create_time_index` (`create_time`),
    KEY `inx_log_type` (`log_type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='系统日志';

