
--创建表空间 tp_orders
create tablespace tp_orders
       datafile 'D:\\OracleTableSpace\\tp_orders.dbf'
       size 10M
       autoextend on;
       
-- 创建A_oe用户,默认表空间tp_orders,密码为bdqn
create user A_oe
       identified by bdqn
       default tablespace tp_orders;
     
-- 授予connect和resource权限
grant connect,resource to A_oe;

-- 授予访问scott用户下的emp表的权限
grant select on scott.emp to A_oe;
