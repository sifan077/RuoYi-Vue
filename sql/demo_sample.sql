-- ----------------------------
-- 新业务示例表（MyBatis-Plus 演示）
-- ----------------------------
drop table if exists demo_sample;
create table demo_sample (
  sample_id           bigint(20)      not null auto_increment    comment '主键',
  sample_name         varchar(100)    default ''                 comment '名称',
  status              char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)    default null               comment '备注',
  primary key (sample_id)
) engine=innodb auto_increment=1 comment = '示例业务表';

insert into demo_sample values(1, '示例数据1', '0', 'admin', sysdate(), '', null, 'MP 演示');
insert into demo_sample values(2, '示例数据2', '0', 'admin', sysdate(), '', null, 'MP 演示');

-- 菜单（可选：按需执行，parent_id=0 为顶级；权限标识对应 Controller 上 @PreAuthorize）
-- 一级菜单
insert into sys_menu values('2100', '示例业务', '0', '10', 'biz', null, '', '', 1, 0, 'M', '0', '0', '', 'shopping', 'admin', sysdate(), '', null, 'MyBatis-Plus 示例目录');
-- 二级菜单
insert into sys_menu values('2101', '示例管理', '2100', '1', 'sample', 'biz/sample/index', '', '', 1, 0, 'C', '0', '0', 'biz:sample:list', 'list', 'admin', sysdate(), '', null, '示例业务菜单');
-- 按钮
insert into sys_menu values('2102', '示例查询', '2101', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'biz:sample:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2103', '示例新增', '2101', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'biz:sample:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2104', '示例修改', '2101', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'biz:sample:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2105', '示例删除', '2101', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'biz:sample:remove', '#', 'admin', sysdate(), '', null, '');
