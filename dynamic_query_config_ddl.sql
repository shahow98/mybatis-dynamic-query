-- mysql
create table dynamic_query_config
(
    id         bigint auto_increment
        primary key,
    query_name varchar(50)              not null comment '查询名称(唯一)',
    query_sql  text                     not null comment 'sql查询模板',
    collection int(1)     default 1     not null comment '是否集合;0/1(否-对象/是-对象数组)',
    status     varchar(3) default 'USING' not null comment '状态;USING/STOP/MOCK(在用/停用/模拟)',
    remark     varchar(255)             null comment '备注',
    params     varchar(255)             null comment '模板参数名;多个逗号隔开',
    query_mock varchar(255)             null comment '模拟数据(json)'
)
    comment '动态查询';