
--������ռ� tp_orders
create tablespace tp_orders
       datafile 'D:\\OracleTableSpace\\tp_orders.dbf'
       size 10M
       autoextend on;
       
-- ����A_oe�û�,Ĭ�ϱ�ռ�tp_orders,����Ϊbdqn
create user A_oe
       identified by bdqn
       default tablespace tp_orders;
     
-- ����connect��resourceȨ��
grant connect,resource to A_oe;

-- �������scott�û��µ�emp���Ȩ��
grant select on scott.emp to A_oe;
