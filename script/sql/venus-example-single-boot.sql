-- venus-example-single-boot
create table single_boot_table
(
    id          bigint unsigned auto_increment primary key,
    name        varchar(20)      null,
    code        varchar(64)      null,
    secret      varchar(128)     null,
    description varchar(255)     null,
    icon        varchar(50)      null,
    banner      varchar(200)     null,
    sort        int unsigned     null,
    is_deleted  tinyint unsigned null,
    create_time datetime         null,
    update_time datetime         null
) comment 'venus-example-single-boot 项目测试表';

