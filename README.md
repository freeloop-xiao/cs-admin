## 🐯 平台简介

**FreeLoop**，以开发者为中心，打造通用用户管理快速开发平台，全部开源，个人与企业可 100% 免费使用。

| 项目名        | 说明          | 传送门                                                     |
|------------|-------------|---------------------------------------------------------|
| `cs-admin` | Spring Boot | [Github](https://github.com/freeloop-xiao/cs-admin.git) |

## 🐼 内置功能

系统内置多种多种业务功能，可以用于快速你的业务系统：


* 系统功能
* 基础设施


🙂 所有功能，都通过 **单元测试** 保证高质量。

### 系统功能

|     | 功能    | 描述                              |
|-----|-------|---------------------------------|
|     | 用户管理  | 用户是系统操作者，该功能主要完成系统用户配置          |
| ⭐️  | 在线用户  | 当前系统中活跃用户状态监控，支持手动踢下线           |
|     | 角色管理  | 角色菜单权限分配、设置角色按机构进行数据范围权限划分      |
|     | 菜单管理  | 配置系统菜单、操作权限、按钮权限标识等，本地缓存提供性能    |
|     | 部门管理  | 配置系统组织机构（公司、部门、小组），树结构展现支持数据权限  |
|     | 岗位管理  | 配置系统用户所属担任职务                    |
|     | 字典管理  | 对系统中经常使用的一些较为固定的数据进行维护          |
| 🚀  | 操作日志  | 系统正常操作日志记录和查询，集成 Swagger 生成日志内容 |
| ⭐️  | 登录日志  | 系统登录日志记录查询，包含登录异常               |
| 🚀  | 错误码管理 | 系统所有错误码的管理，可在线修改错误提示，无需重启服务     |



> 前端项目的地址：

### 后端

| 框架                                                                                          | 说明              | 版本         | 学习指南                                                           |
|---------------------------------------------------------------------------------------------|-----------------|------------|----------------------------------------------------------------|
| [Spring Boot](https://spring.io/projects/spring-boot)                                       | 应用开发框架          | 2.7.8      | [文档](https://github.com/YunaiV/SpringBoot-Labs)                |
| [MySQL](https://www.mysql.com/cn/)                                                          | 数据库服务器          | 5.7 / 8.0+ |                                                                |
| [MyBatis Plus](https://mp.baomidou.com/)                                                    | MyBatis 增强工具包   | 3.5.3.1    | [文档](http://www.iocoder.cn/Spring-Boot/MyBatis/?yudao)         |
| [Redis](https://redis.io/)                                                                  | key-value 数据库   | 5.0 / 6.0  |                                                                |
| [Spring Security](https://github.com/spring-projects/spring-security)                       | Spring 安全框架     | 5.7.6      | [文档](http://www.iocoder.cn/Spring-Boot/Spring-Security/?yudao) |
| [Hibernate Validator](https://github.com/hibernate/hibernate-validator)                     | 参数校验组件          | 6.2.5      | [文档](http://www.iocoder.cn/Spring-Boot/Validation/?yudao)      |

