# ruoyi-biz（MyBatis-Plus 新业务模块）

老业务（system / quartz / generator）仍使用原生 MyBatis + XML + PageHelper，本模块使用 MyBatis-Plus。

## 使用前

1. 执行表结构：`sql/demo_sample.sql`
2. （可选）执行同文件中的菜单 SQL，或在「系统管理 - 菜单管理」中自行配置权限：
   - `biz:sample:list` / `query` / `add` / `edit` / `remove`
3. 重新编译启动后端

## 接口示例

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/biz/sample/list` | MP 分页列表 |
| GET | `/biz/sample/{id}` | 详情 |
| POST | `/biz/sample` | 新增 |
| PUT | `/biz/sample` | 修改 |
| DELETE | `/biz/sample/{ids}` | 删除 |
| GET | `/biz/sample/listXml` | 自定义 XML + PageHelper 示例 |

## 新业务开发约定

- 包名：`com.ruoyi.biz.*`（已被 `@MapperScan("com.ruoyi.**.mapper")` 扫描）
- Mapper：`extends BaseMapper<Entity>`
- Service：`extends IService` / `ServiceImpl`（包名：`com.baomidou.mybatisplus.spring.service`）
- 分页：使用 `Page<T>`，**不要**与 `startPage()`（PageHelper）混用在同一次查询
- 复杂 SQL：继续写在 `resources/mapper/biz/*Mapper.xml`

## 依赖说明

- 父工程引入 `mybatis-plus-bom` 3.5.17
- 框架层使用 `MybatisSqlSessionFactoryBean`，兼容老 XML
- Spring Boot 4 使用 `mybatis-plus-spring-boot4-starter`
