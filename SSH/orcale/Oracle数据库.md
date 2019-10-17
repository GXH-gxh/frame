# Oracle数据库

## SQL

### sql介绍

- 结构化查询语言

### sql的分类以及常用的操作符

​	分类					常用操作符

- DDL:数据定义语言			creat alter  drop truncate
- DML:数据操纵语言                        insert  delete   update
- DCL:数据控制语言                          grant  revoke
- DQL:数据查询语言                         select   from子句 where子句

### 查询语句的结构

```
select [列名] [*] from 表明	[where	条件]	[group by	分组条件]	[having	过滤]	[order by	排序]
```

## 创建用户以及分配权限

```
/*
  创建用户(必须指定密码)
  gj:是用户名
  123:是密码
*/
create user gj
identified by 123;

/*
  给用户分配权限
*/
grant dba to gj;
```

### 创建用户

```
/*
  创建用户
     create user 用户名
     identified by 密码
       [DEFAULT  TABLESPACE tablespace]  -- 指定默认表空间
       [TEMPORARY  TABLESPACE tablespace]   --指定临时表空间
       [QUOTA {integer [K|M] | UNLIMITED}ON tablespace
       [QUOTA {integer [K|M] | UNLIMITED}ON tablespace ] ...]
       [PASSWORD EXPIRE ]    --密码过期
*/

案列
	--需求：创建用户
create user t06
identified by 123
password expire;
```

### 分配权限

```

--授予登录权限
grant create session to t06;
--撤销权限
revoke create session from t06;

--授予角色
grant connect,resource to t06;
--撤销角色
revoke connect,resource from t06;
```

### 修改密码

```
/*修改密码

     方法一：通过设置密码过期的方式修改密码
     alter user 用户名 password expire
     
     方法二：直接修改密码
     alter user 用户名
     identified by 密码
*/
--方法一：通过设置密码过期的方式修改密码
alter user liang password expire;

--方法二：直接修改密码
alter user liang
identified by 123;
```

### 删除用户

```

--删除用户  drop user 用户名 cascade
--删除用户需要断开该用户的连接
drop user t06 cascade;
```

## Oracle体系结构

```
/*
     Oracle体系结构:
      数据库 ---> 数据库实例ORCL ---> 表空间 (用户里面的创建表) ---> 数据文件 
      地球   ---> 中国           ---> 省份  (人民)              ---> 土地山川河流
     
     雄安新区 ---> 人(开发荒地,种地)
                
     
     创建表空间: 逻辑单位, 通常我们新建一个项目,就会去新建表空间,在表空间中创建用户来创建表
           语法:
               create tablespace 表空间的名称
               datafile '文件的路径(服务器上)'
               size 大小
               autoextend on  自动扩展
               next 每次扩展的大小
*/
```

## 表的五大约束

```
/*
   表的五大约束
   列的约束: 约束主要是用来约束表中数据的规则
     主键约束: primary key 不能为空, 必须唯一
     非空约束
     唯一约束
     检查约束 check(条件)  在mysql中是可以写的,但是mysql直接忽略了检查约束
     
     外键约束:
          主要是用来约束从表A中的记录,必须是存在于主表B中
*/
--男,女,人妖
create table student(
    stuid number primary key,
    sname varchar2(10) unique,
    age   varchar2(10) not null,
    gender varchar2(4) check( gender in ('男','女','人妖'))
);
--主键约束违反
insert into student values(1,'张三','31','男');
insert into student values(1,'李四','31','男');
--唯一约束违反
insert into student values(1,'徐立','31','男');
insert into student values(2,'徐立','31','男');
--非空约束
insert into student values(1,'徐立',null,'男');
--检查约束
insert into student values(1,'徐立','31','男');

insert into student values(1,'徐立','31','妖');

select * from student;



--添加外键约束
alter table product add foreign key(cno) references category(cid);
insert into product values(10,'锤子',11);--插入失败

--1.首先主表中必须存在11号, 先往主表中插入数据,再往从表中插入数据
insert into category values(2,'电脑办公');
insert into product values(11,'外星人',2);
```

## 表空间

注意：
           1.表空间名称唯一
           2.一个表空间由一个或多个数据文件组成，但一个数据文件只能属于一个表空间
           3.在同一个表空间下，数据文件名称不能同名



创建表时没有指定表空间，表会自动放到默认的表空间（Users表空间）

### 创建表空间

```
--创建表空间
/*
  create tablespace 表空间名称
  datafile '数据文件的位置.dbf'
  size 大小（K,M）
  autoextend [off、on]
*/

--需求1：创建超市管理系统的表空间
create tablespace tp_smbms
datafile 'E:\\oracle\\tp_smbms.dbf'
size 50M
autoextend on;
```

### 修改表空间

```
/*
修改表空间大小
     方法一：修改原表空间数据文件的大小
     alter database 
     datafile '数据文件的位置.dbf'
     resize 大小
     
     
     方法二：给表空间添加多个数据文件
     alter tablespace 表空间名称
     add datafile '数据文件的位置.dbf'
     size 大小（K,M）
     autoextend [off、on]
*/
--需求2：修改超市管理系统表空间大小
alter database 
datafile 'E:\\oracle\\tp_smbms.dbf'
resize 80M;

alter tablespace tp_smbms
add datafile 'E:\\oracle\\tp_smbms02.dbf'
size 20M
autoextend on;
```

### 使用表空间

```
--使用表空间
--需求：创建表时指定表空间
create table smbms_user(
   id number primary key,
   userCode varchar2(50) not null,
   userName varchar2(50) not null
) tablespace tp_smbms;

```

### 删除表空间

```
--删除表空间
drop tablespace tp_smbms including contents;
```

### 设置表空间的读写

```
--将表空间设置成只读
alter tablespace tp_smbms read only;
--将表空设置成可读写
alter tablespace tp_smbms read write;
```



## Oracle中的虚表,伪表

- dual:Oracle中的虚表,伪表,主要用来补齐语法结构的

## 别名查询

- 别名查询
  - 使用as关键字,可以省略as关键字
  - 别名中不能有特殊字符或关键字,有就加双引号

```
/*
       别名查询
       关键字:as
*/
-- 不省略as关键字
select ename as 姓名, Sal as 工资 from emp;
-- 省略as关键字
select ename 姓名, Sal 工资 from emp;
-- 有特殊字符
select ename "like 姓名", Sal 工资 from emp;

```

## 去除重复数据

- 多列去除重复:每一列的数据都一样才能算作重复

```

/*
       去除重复数据
       关键字:distinct
       使用语法:在要查询的列前加入关键字distinct
*/
--     去除单列重复
select distinct job from emp;
--     去除多列重复数据
select distinct job,deptno from emp;
```

## sql语句中的四则运算

- 四则运算

  - 即加,减,乘,除

- 字段中的null值代表不确定的,不可预知的内容,不可以做四则运算

  - 解决方案

    - 使用函数nvl(参数1,参数2):如果参数1为空,就返回参数2

    - 案列

      ```
      /*
      	如果comm字段为空值,那么comm字段值就为0
      */
      select sal*12+nvl(comm,0) from emp;
      ```

      

## 字符串的拼接符

- 使用字符串拼接前

  ![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\使用字符串拼接前.PNG)

- 使用字符串拼接后

  ![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\使用字符串拼接后.PNG)

  - 使用字符串品拼接后,虚拟表会修改字段列名

```
/*
       字符串拼接
       方式1:使用Orcale特有拼接符单引号+||
             在Oracle中双引号主要是在使用别名的时候使用,单引号是使用的值,是字符
       方式2:使用concat("拼接字符串","要拼接的字段")函数
*/
-- 使用拼接符
select '姓名' || ename from emp;
-- 使用函数拼接
select concat('姓名:',ename) from emp;
```

## 条件查询

- 关系运算符:>	>=	==	<	<= 	!=	<>
- 逻辑运算符:and   or    not
- 其他运算符
  - like		模糊查询
  - in(set)     在某个集合内
  - between...and...         在某个区间内
  - is null      判断为空
  - is not null   判断不为空

## 模糊查询和转义关键字escape

- like		模糊查询
- _               匹配单个字符
- %              匹配多个字符
- escape     转义字符,如果like模糊查询条件中有关键字需要进行转义

![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\转义关键字escape.PNG)

## 排序

```
/*
	排序:	order by
		升序:	asc(从小到大)
		降序:	desc(从大到小)
	排序注意空值问题:使用关键字 nulls first | last,空值排在最前面或者最后面	
*/
```

![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\排序案列.PNG)

## 函数(具体查看Orcale函数帮助文档)

### 单行函数

- 数值函数			ceil():向上取整                  floor:向下取整
  - ![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\数值函数.PNG)
- 四舍五入                round(参数1,参数2)
  - ![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\四舍五入.PNG)
- 截断函数                trunc(参数1,参数2)
  - ![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\截断函数.PNG)
- 求余函数               mod(参数1,参数2)
  - ![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\求余函数.PNG)

### 多行函数

- sum():	求和
- count():    统计
- max():      最大值
- min():       最小值
- avg():       平均值

## 条件表达式

![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\捕获.PN条件表达式语法.PNG)

- 案列(条件表达式方式1和方式2)

![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\条件表达式案列.PNG)

## 分组表达式

![avatar](H:\开发工具\青鸟开发笔记\Oracle\img\分组表达式.PNG)

## 多表查询

### 笛卡尔积

```
/*
     多表查询:
       笛卡尔积: 实际上是两张表的乘积,但是在实际开发中没有太大意义
      
     格式: select * from 表1,表2   
*/
select * from emp;
select * from dept;

select * from emp, dept;

select * from emp e1, dept d1 where e1.deptno = d1.deptno;
```

### 内连接

```
/*
     内联接:
       隐式内联接: 
           等值内联接:   where e1.deptno = d1.deptno;
           不等值内联接:  where e1.deptno <> d1.deptno;
           自联接: 自己连接自己
       显示内联接:
           select * from 表1 inner join 表2 on 连接条件
           
           inner 关键字可以省略
*/
```

### 外链接

```
/*
    外连接: (标准,通用写法)
       左外连接: left outer join 左表中所有的记录,如果右表没有对应记录,就显示空
       右外连接: right outer join 右表中的所有记录,如果左表没有对应记录,就显示空
       outer 关键字可以省略  
       
    Oracle中的外连接: (+) 实际上是如果没有对应的记录就加上空值
          select * from emp e1,dept d1 where e1.deptno = d1.deptno(+);            
*/
```

### 子查询

子查询:查询语句中嵌套查询语句; 用来解决复杂的查询语句

```
/*
      内联接, 单行子查询, 多行子查询
      
      in 
      not in
      any 
      all
      exists 
      
      通常情况下, 数据库中不要出现null  最好的做法加上Not null
      null值并不代表不占空间, char(100) null 100个字符
*/
```



#### 单行子查询

```
单行子查询: > >= = < <= <> !=
```



#### 多行子查询

```
 多行子查询: in not in  >any >all exists not exists
```

## exists的使用

```
/*
     exists(查询语句) : 存在的意思,判断一张表里面的记录是否存在与另外一张表中
                当作布尔值来处理:
                    当查询语句有结果的时候, 就是返回true
                                            否则返回的是false
     数据量比较大的时候是非常高效的   
*/
```

## rownum伪列的使用

```
/*
       rownum : 伪列, 系统自动生成的一列, 用来表示行号
       
       rownum是Oracle中特有的用来表示行号的, 默认值/起始值是 1 ,在每查询出结果之后,再添加1
       
       rownum最好不能做大于号判断,可以做小于号判断
       
       SQL执行顺序
       from .. where ..group by..having .. select..rownum..order by
*/
```

## 查询其他用户下的表

```
/*
  查询其他用户的表
  格式:用户名.表名
*/  
select * from scott.emp;

/*     
       使用存在的表创建新表(带数据)
*/
create table emp as select * from scott.emp;
-- 不带数据,sql语句中where条件中加入不成立的条件即可
create table emp2 as select * from scott.emp where 1=2;

select * from emp2;
```

## 分页查询

```
/****************************分页查询***********************************/
--查询1到5条数据
select * from emp where rownum <=5;

--查询6到10条数据
select * from emp where rownum between 6 and 10;

/*
    1、第一层：加限制条件
	  2、第二层：给结果集过滤最大的范围(每页显示多少条),借助rownum
    3、第三层：给结果过滤出最小的范围（从第几条开始）
*/
--1.加限制条件
select * from emp where sal is not null

--2.第二层：查询的表是第一层的内容，同时借助rownum
select rownum rn ,e.* from (select * from emp where sal is not null) e 

--3.第三层：从哪里开始，到哪里结束
select *
  from (select rownum rn, e.*
          from (select * from emp where sal is not null) e)
 where rn between 6 and 10;
```

## 获取当前系统时间

```
/***************获取当前系统时间********************/
select sysdate from dual;
--sysdate:当前系统时间，与mysql中的now()类似
--dual：Oracle数据库自带的虚拟表

/******************单行函数*************************/
--to_date():将结果转成日期类型
select to_date('2018-02-03','yyyy/MM/dd') from dual

--to_char():将结果转成字符串
select to_char(sysdate,'yyyy-MM-dd hh:mi:ss') from dual;--12小时（默认）
select to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') from dual;--24小时制
select to_char(sysdate,'yyyy"年"MM"月"dd"日" hh24:mi:ss') from dual;

select to_char('123','$9999.00') from dual;

--to_number()：:将结果转成数值
select to_number('123') from dual;

--nvl()

--需求：查询员工的总工资（基本工资+奖金）
--select ename,sal + comm from emp;
select ename,sal + nvl(comm,0) 总薪资 from emp;

--nvl2()

SELECT ename,
                sal+NVL(comm,0) sal1,
                NVL2(comm,sal+comm,sal) sal2
  FROM emp;
  
--decode():类似java中的switch选择结构
  
SELECT ename,
       DECODE(to_char(hiredate, 'MM'),
              '01',
              '一月',
              '02',
              '二月',
              '03',
              '三月',
              '04',
              '四月',
              '05',
              '五月',
              '06',
              '六月',
              '下半年') mon
  FROM emp;
```

## 分析函数

```
--分析函数：用于排名
--rank()
--dense_rank()
--row_number()

/*
      row_number():无论值是否相同，排名序号连续递增不变，1,2,3,4 
      rank()：值相同，排名并列（相同），但是序号会跳跃，值不相同递增
      dense_rank()：值相同，排名并列（相同），序号不会跳跃，排名递增
      
*/
select ename,deptno,sal,
       rank() over (order by sal desc) "rank",
       dense_rank()  over (order by sal desc) "dense_rank",
       row_number()  over (order by sal desc) "row_number"
from emp;
```

## 集合运算

```
/*******************************************/

--创建退休员工表
CREATE TABLE retireEmp AS SELECT * FROM emp;
--将退休员工表的工号列名修改成rempno
ALTER TABLE  retireEmp RENAME COLUMN empno TO rempno;  
--将数据修改成与emp表不一样的数据
UPDATE retireEmp  SET rempno=8888 WHERE rempno=7788;

--union：并集，联合查询（不会取重复数据）
select * from emp
union 
select * from retireEmp;

--union all：并集，联合查询（会取重复数据）
select * from emp
union all
select * from retireEmp;

--intersect交集，只选中两表之间公有的数据
select * from emp
intersect
select * from retireEmp;


--minus减集，筛选的是第一个结果集，查询出的结果（第一个结果集减去第二个结果集数据）
select * from emp
minus
select * from retireEmp;
```

## 事物

```
/*
   事务: 就是一系列的操作,要么都成功,要么都失败
       四大特性: 原子性,隔离性,持久性,一致性
          
       如果不考虑隔离级别: 脏读,虚读,不可重复读
            MYSQL隔离级别: READ UNCOMMITTED , READ COMMITTED, REPEATABLE READ, SERIALIAZABLE
            ORACLE隔离级别: READ COMMITTED SERIALIZABLE READ ONLY 
                        默认隔离级别: READ COMMITTED
                        
      提交 : commit
      事务的保存点/回滚点: savepoint 保存点的名称
      回滚: rollback
*/
create table louti(
   lou number primary key    
);

insert into louti values(1);
insert into louti values(2);
insert into louti values(3);
insert into louti values(4);
insert into louti values(5);
savepoint dangban;
insert into louti values(5); --主键约束会发生异常
insert into louti values(6);
rollback to dangban
commit;


declare

begin
  insert into louti values(1);
  insert into louti values(2);
  insert into louti values(3);
  insert into louti values(4);
  insert into louti values(5);
  savepoint dangban;
  insert into louti values(5);  --这行代码会发生异常
  insert into louti values(6);
  commit;
exception  --捕获异常
  when others then
     rollback to dangban;
     commit;
end;

select * from louti;
```

## 视图

```
/*
      视图: 是对查询结果的一个封装
              视图里面所有的数据,都是来自于它查询的那张表,视图本身不存储任何数据
          1.能够封装复杂的查询结果
          2.屏蔽表中的细节
       语法: 
          create [or replace] view 视图的名称 as 查询语句 [ with read only]
          
       注意: 通常不要通过视图去修改,视图创建的时候,通常要加上with read only
*/
select * from emp;

--创建一个视图
create or replace view view_test1 as select ename,job,mgr from emp;

--通过视图修改数据
update view_test1 set ename='SMITH2' where ename = 'SMITH';

--创建一个只读视图
create or replace view view_test2 as select ename,job,mgr from emp with read only;

update view_test2 set ename='SMITH3' where ename = 'SMITH2';

--视图封装复杂的查询语句
create view view_test3 as select
      sum(cc) "TOTAL",
      sum(case yy when '1980' then cc end) "1980",
      sum(case yy when '1981' then cc end) "1981",
      sum(case yy when '1982' then cc end) "1982",
      sum(case yy when '1987' then cc end) "1987"
from
      (select  to_char(hiredate,'yyyy') yy,count(1) cc from emp group by  to_char(hiredate,'yyyy')) tt;

```

## 序列

```
/*
	来源黑马
    序列: 生成类似于 auto_increment 这种ID自动增长 1,2,3,4,5....
       auto_increment 这个是mysql  
       
       语法:
           create sequence 序列的名称
           start with 从几开始
           increment by 每次增长多少
           maxvalue 最大值 | nomaxvalue
           minvalue 最小值 | nominvalue
           cycle | nocycle  是否循环    1,2,3,1,2,3
           cache 缓存的数量3 | nocache  1,2,3,4,5,6 
           
      如何从序列获取值
          currval : 当前值
          nextval : 下一个值
          
               注意: currval 需要在调用nextval之后才能使用      
               
               永不回头,往下取数据, 无论发生异常, 回滚   
*/
--创建一个 1,3,5,7,9......30 
create sequence seq_test1
start with 1
increment by 2
maxvalue 30
cycle
cache 10;

select seq_test1.nextval from dual;
select seq_test1.currval from dual;

--序列用的最多的一种写法
create sequence seq_test2;
select seq_test2.nextval from dual;


create sequence seq_test3
start with 1
increment by 2
maxvalue 30
minvalue 0
cycle
cache 10;

select seq_test3.nextval from dual;
```

```
来源北大青鸟
/******************序列**********************/

/*
     创建序列
          create sequence 序列名称
          start with 开始位置
          increment by 间隔数量
          maxvalue 最大值
          minvalue 最小值
          cycle|nocycle
          cache 缓存个数（默认分配20）
*/
create sequence seq_emp
start with 1
increment by 1
nomaxvalue
minvalue 1
nocycle
cache 10

--使用序列
--下一个：序列名.nextval
--当前序列：序列名.currval
select * from dept;

insert into dept (deptno,dname,loc) values(seq_emp.nextval,'技术部',null);

select seq_emp.currval from dual;
select seq_emp.nextval from dual;

--Oracle数据库不支持自增，可以借助序列进行自增
--序列自增只适用于数值类型

--字符串类型主键，可以通过sys_guid()实现
--sys_guid()：生成32位的唯一编码
select sys_guid() from dual;

--修改序列:alter sequence 序列名称 
--注意：start with参数是不能修改的
alter sequence seq_emp 
increment by 2
nocycle;

--删除序列 drop sequence 序列名称
drop sequence seq_emp;
```

## 同义词

```
/*
     私有同义词：只能在当前用户下访问，且不能与当前用户下的表同名
     
     模式 = 用户
     模式对象 = 表名
     
     create synonym 同义词名称 for  用户名.表名
*/
--创建私有同义词
create synonym syn_emp for scott.emp;
--使用私有同义词
select * from syn_emp;

/*
   公有同义词：能够被所有的数据库用户访问，且可以与私有同义词、表同名
   create public synonym 同义词名称 for 用户名.表名
*/
create public synonym dept for scott.dept;--可以与表同名
create public synonym syn_emp for liang.dept;--可以与私有同义词同名

/*
 访问顺序：
       当公有同义词与表同名时，优先使用表
       当公有同义词与私有同义词同名时，优先使用私有同义词
*/

--删除私有同义词
drop synonym syn_emp;
--删除公有同义词
drop public synonym syn_emp;
```



## 索引

```
/*
    索引:相当于是一本书的目录,能够提高我们的查询效率
       如果某一列,你经常用来作为查询条件,那么就有必要创建索引,数据量比较的情况
       
       语法: 
             create index 索引的名称 on 表名(列)   
        
       注意:主键约束自带主键索引, 唯一约束自带唯一索引
       
       索引原理: btree   balance Tree 平衡二叉树
       
             如果某列作为查询条件的时候,可以提高查询效率,但是修改的时候,会变慢
             
             索引创建好之后,过了一段,DBA都会去做重构索引
             
       SQL调优:
             1.查看执行计划F5
             2. 分析里面的cost 和 影响行数, 想办法降低            
*/
--五百万数据测试
create table wubaiwan(
      name varchar2(30),
      address varchar2(20) 
);

insert into wubaiwan values('')

--插入500000万条数据
declare

begin
     for i in 1..5000000 loop
       insert into wubaiwan values('姓名'||i,'地址'||i);
     end loop;
     commit;  
end;

--在没有添加索引的情况下,去查询  name='姓名3000000'  --2.985
select * from wubaiwan where name='姓名3000000';

--创建索引 name 再去查询 name='姓名3000000'
create index ind_wubaiwan on wubaiwan(name);
select * from wubaiwan where name='姓名3000000';  --0.016

--在没有添加复合索引的情况下,再去查询 name='姓名3000000' and '地址3000000'
select * from wubaiwan where name='姓名3000000' and address='地址3000000'; --0.032

--创建复合索引的情况下, 再去查询
create index ind_wubaiwan2 on wubaiwan(name,address);
select * from wubaiwan where name='姓名3000000' and address='地址3000000'; --0.015







/******************索引*********************/
--B树索引（标准索引）
create index index_empno on emp(empno);

--反向键索引：适用于创建在高基数列（数据编号连续增长）
create index index_deptno on dept(deptno) reverse;

--位图索引:适合创建在低基数列（值在整个表中出现的次数有限）
create bitmap index index_job on emp(job);


/*
   分区：(范围分区、间隔分区11g)
       范围分区：
              partition by range(列名)
              (
                   partition 分区名称  value less than(值),
                   partition 分区名称  value less than(值),
                   partition 分区名称  value less than(maxvalue),
              )
              
       间隔分区：
               partition by range(列名)
               interval (numtoyminterval(n,'year/month'))
              (
                   partition 分区名称  value less than(值),
                   partition 分区名称  value less than(值),
                   partition 分区名称  value less than(maxvalue),
              )
              numtoyminterval(参数1，参数2)
              参数1:指的是间隔的值
              参数2：year,month
              
*/
--创建分区时，数据严禁交叉
create table employee
partition by range(sal)
(
          partition p1 values less than(1000),
          partition p2 values less than(2000),
          partition p3 values less than(3000),
          partition p4 values less than(maxvalue)
)

as select * from scott.emp;

--访问特定分区数据
select * from employee partition(p2);


--间隔分区
create table employee2
partition by range(hiredate)
interval (numtoyminterval(3,'year'))
(
    partition p1 values less than (to_date('1985-01-01','yyyy-MM-dd')),
    partition p2 values less than (to_date('1988-01-01','yyyy-MM-dd')),
    partition p3 values less than (to_date('1991-01-01','yyyy-MM-dd'))
)
as select * from scott.emp;

select * from employee2 order by hiredate asc;

select * from employee2 partition (p3)
```

## PLSQL编程

 PLSQL编程 : procedure Language 过程语言 Oracle对SQL的一个扩展
             让我们能够像在java中一样写 if else else if 条件, 还可以编写循环逻辑 for while
             

             declare
                --声明变量
                变量名 变量类型;
                变量名 变量类型 := 初始值;
                  vsal emp.sal%type;  --引用型的变量  
                  vrow emp%rowtype;   --声明记录型变量          
             begin
                --业务逻辑
             end;
             
             dbms_output.put_line()相当于java中 syso 
```
declare
   i varchar2(10) := '张三';          
begin
  dbms_output.put_line(i);
end;

--查询7369的工资,并且打印出来
declare
  vsal emp.sal%type;
begin
  --将查询出的结果赋值给vsal
  select sal into vsal from emp where empno = 7369;
  
  dbms_output.put_line(vsal);
end;

--查询7369的员工信息,并且打印出来
 select * from emp where empno = 7369;

declare
  vrow emp%rowtype;      
begin
  select * into vrow from emp where empno = 7369;
  
  dbms_output.put_line('姓名:'||vrow.ename || '工资'|| vrow.sal);
end;
```

### PL条件判断

```
/*
  PL条件判断
     
     if then
     
     elsif then
       
     else 
     
     end if;
*/
--根据不同年纪,输出相关内容
declare
   age number := &aaa;
begin
  if age < 18 then
     dbms_output.put_line('小屁孩');
  elsif age>=18 and age <=24 then
     dbms_output.put_line('年轻人');
  elsif age>24 and age < 40 then
    dbms_output.put_line('老司机');
  else 
      dbms_output.put_line('老年人');    
  end if;
end;
```

### 循环操作

```
/*
  循环操作
  while 循环
      while 条件 loop
        
      end loop;
    
  for循环
      for 变量  in [reverse] 起始值..结束值 loop
        
      end loop;
  
  loop循环  
      loop
        exit when 条件
      end loop;
      
*/
--输出1~10
declare
  i number :=1;
begin
  while i<=10 loop
    dbms_output.put_line(i);
    i := i+1;    
  end loop;
end;

--输出1~10
declare

begin
  for i in reverse 1..10 loop
    dbms_output.put_line(i);
  end loop;
end;

--输出1~10
declare
   i number :=1;
begin
   loop
     exit when i>10;
      dbms_output.put_line(i);  
     i := i+1;
   end loop;
end;

```

### plsql案列

```
/*

   *
  ***
 *****
  ***
   *   
输出 m  
   x : [-m,m]
   y : [-m,m]
   
   输出所有满足条件的 : abs(y)+abs(x) <=m
   
   m取值
*/
--使用PLSQL输出菱形
declare
   m number := 10;
begin
   for x in -m..m loop
     for y in -m..m loop
       if abs(y) + abs(x) <= m then
         dbms_output.put('*');
       else
         dbms_output.put(' ');
       end if;      
     end loop;
     dbms_output.new_line();
   end loop;  
end;

--使用PLSQL输出三角形,只要是三个角
declare
   m number := 10;
begin
   for x in reverse -m..m loop
     for y in -m..m loop
       if abs(y) + abs(x) <= m and x>=0 then
         dbms_output.put('*');
       else
         dbms_output.put(' ');
       end if;      
     end loop;
     dbms_output.new_line();
   end loop;  
end;
```

### 序列

```
/*
  序列: ORACLE使用来模拟ID自动增长的
  
*/
create sequence seq_test4;

create table test2(
   tid number primary key,
   tname varchar2(10)    
);
insert into test2 values(seq_test4.nextval,'张三');
select * from test2;
```



### 游标

```
/*
   游标(光标): 是用来操作查询结果集,相当于是JDBC中ResultSet
       
       语法: cursor 游标名[(参数名 参数类型)] is 查询结果集
       
       开发步骤:
           1. 声明游标
           2. 打开游标       open 游标名
           3. 从游标中取数据  fetch 游标名 into 变量
                         游标名%found :找到数据
                         游标名%notfound : 没有找到数据 
           4. 关闭游标       close 游标名
           
      系统引用游标
           1. 声明游标 : 游标名 sys_refcursor
           2. 打开游标: open 游标名 for 结果集
           3. 从游标中取数据
           4. 关闭游标
                
     for循环遍历游标:
           不需要声明额外变量
           不需要打开游标
           不需要关闭游标      
*/
--输出员工表中所有的员工姓名和工资(不带参数游标)
/*
   游标:所有员工
   声明一个变量,用来记录一行数据  %rowtype
*/
declare
   --游标
   cursor vrows is select * from emp;
   --s声明变量,记录一行数据
   vrow emp%rowtype;
begin
   --1.打开游标  
   open vrows;
   --2.从游标提取数据
   --循环取数据
   loop
       fetch vrows into vrow; 
       exit when vrows%notfound;  
       dbms_output.put_line('姓名:'||vrow.ename ||' 工资: ' || vrow.sal);
   end loop;
   --3.关闭游标
   close vrows;
end;

--输出指定部门下的员工姓名和工资
/*
   游标: 指定部门的所有员工
   声明一个变量记录一行数据
*/
declare
   --声明游标
   cursor vrows(dno number) is select * from emp where deptno = dno;
   --声明变量
   vrow emp%rowtype;
begin
  --1.打开游标 , 指定10号部门
  open vrows(10);
  --2. 循环遍历,取数据
  loop
     fetch vrows into vrow;
     exit when vrows%notfound;    
      dbms_output.put_line('姓名:'||vrow.ename ||' 工资: ' || vrow.sal);
  end loop;
  close vrows;
end;

--系统引用游标
--输出员工表中所有的员工姓名和工资
declare
  --声明系统引用游标
  vrows sys_refcursor;
  --声明一个变量
  vrow emp%rowtype;
begin
  --1.打开游标
  open vrows for select * from emp;
  --2.取数据
  loop
    fetch vrows into vrow;
    exit when vrows%notfound;
     dbms_output.put_line('姓名:'||vrow.ename ||' 工资: ' || vrow.sal);
  end loop;
  close vrows;
end;

--扩展内容----使用for循环遍历游标
declare
  --声明一个游标
  cursor vrows is select * from emp;
begin
  for vrow in vrows loop
     dbms_output.put_line('姓名:'||vrow.ename ||' 工资: ' || vrow.sal || '工作:'|| vrow.job);
  end loop;
end;

select * from emp;



--按照员工工作给所有员工涨工资,总裁涨1000,经理涨800,其他人涨400
/*
    游标 : 所有员工
    声明一个记录一行数据   
*/
declare
   --声明游标
   cursor vrows is select * from emp;
   --声明一个变量
   vrow emp%rowtype; 
begin
  --1.打开游标
  open vrows;
  --2.循环取数据
  loop
       --取数据
       fetch vrows into vrow;
       --退出条件
       exit when vrows%notfound;  
       --根据不同的职位,涨工资 总裁涨1000,经理涨800,其他人涨400
       if vrow.job = 'PRESIDENT' then
          update emp set sal = sal + 1000 where empno = vrow.empno;
       elsif vrow.job = 'MANAGER' then
          update emp set sal = sal + 800 where empno = vrow.empno;
       else
          update emp set sal = sal + 400 where empno = vrow.empno; 
       end if;       
  end loop;
  --3.关闭游标
  close vrows;
  --4.提交事务
  commit;
end;


select * from emp;
```

### 异常

```
/*
   例外:(意外)程序运行的过程发生异常,相当于是JAVA中的异常
   
   declare
       --声明变量
   begin
       --业务逻辑
   exception
       --处理异常
       when 异常1 then
         ...
       when 异常2 then
         ...
       when others then
         ...处理其它异常
   end;
   
   zero_divide : 除零异常
   value_error : 类型转换异常
   too_many_rows : 查询出多行记录,但是赋值给了rowtype记录一行数据变量
   no_data_found : 没有找到数据
       
   
   自定义异常:
       异常名  exception;
       raise 异常名          
*/
declare
   vi number;
   vrow emp%rowtype;
begin
   --vi := 8/0;  
   --vi := 'aaa';
   --select * into vrow from emp;
   select * into vrow from emp where empno=1234567;
exception
  when zero_divide then
    dbms_output.put_line('发生了除零异常');
  when value_error then
     dbms_output.put_line('发生了类型转换异常');
  when too_many_rows then
    dbms_output.put_line(' 查询出多行记录,但是赋值给了rowtype记录一行数据变量');
  when no_data_found then
    dbms_output.put_line('没有找到数据异常');
  when others then
     dbms_output.put_line('发生了其它异常' || sqlerrm);     
end;

--查询指定编号的员工,如果没有找到,则抛出自定义的异常
/*
     --错误的演示
     
     1.声明一个变量 %rowtype
     2.查询员工信息,保存起来
     3.判断员工信息是否为空
     4. 如果是 则抛出异常
*/
declare
  --   1.声明一个变量 %rowtype
  vrow emp%rowtype;
  --2 .声明一个自定义的异常
  no_emp exception;  
begin
  --查询员工信息,保存起来
  select * into vrow from emp where empno = 8888;   --抛出异常
  
  if vrow.sal is null then
    raise no_emp; --抛出自定义的异常
  end if;
exception
  when no_emp then
     dbms_output.put_line('输出了自定义的异常');  
  when others then
     dbms_output.put_line('输出了其它异常'||sqlerrm);  
end;

--查询指定编号的员工,如果没有找到,则抛出自定义的异常
/*
     游标来判断
       %found %notfound
    声明一个游标
    声明一个变量,记录数据
    从游标中取记录
       如果有,则不管它
       如果没有就抛出自定义的异常
*/
declare
  --声明游标
  cursor vrows is select * from emp where empno=8888;   
  --声明一个记录型变量
  vrow emp%rowtype;
  --声明一个自定义异常
  no_emp exception;  
begin
  --1.打开游标
  open vrows;
  --2.取数据
  fetch vrows into vrow;
  --3.判断游标是否有数据
  if vrows%notfound then
    raise no_emp;
  end if;
  close vrows;
exception
  when no_emp then
    dbms_output.put_line('发生了自定义的异常');
end;
```

### 存储过程

```
/*
    存储过程: 实际上是封装在服务器上一段PLSQL代码片断,已经编译好了的代码
              1.客户端取调用存储过程,执行效率就会非常高效
         语法:
              create [or replace] procedure 存储过程的名称(参数名 in|out 参数类型,参数名 in|out 参数类型)
              is | as
               --声明部分
              begin
               --业务逻辑 
              end; 
             
              
*/
--给指定员工涨薪,并打印涨薪前和涨薪后的工资
/*
    参数 : in 员工编号
    参数 : in 涨多少
    
    声明一个变量 : 存储涨工资前的工资
    
    查询出当前是多少
    打印涨薪前的工资
    更新工资
    打印涨薪后的工资          
*/
create or replace procedure proc_updatesal(vempno in number,vnum in number)
is
   --声明变量.记录当前工资
   vsal number;    
begin
  --查询当前的工资
  select sal into vsal from emp where empno = vempno;
  --输出涨薪前的工资
  dbms_output.put_line('涨薪前:'||vsal);
  --更新工资
  update emp set sal = vsal + vnum where empno = vempno;
  --输出涨薪后的工资
  dbms_output.put_line('涨薪后:'||(vsal+vnum));
  --提交
  commit;
end;

--方式1
call proc_updatesal(7788,10);

--方式2 用的最多的方式
declare

begin
  proc_updatesal(7788,-100);
end;


/*
  存储函数: 实际上是一段封装是Oracle服务器中的一段PLSQL代码片断,它是已经编译好了的代码片段
        
        语法: 
             create [or replace] function 存储函数的名称(参数名 in|out 参数类型,参数名 in|out 参数类型) return 参数类型
             is | as
             
             begin
               
             end;
        存储过程和函数的区别:
             1.它们本质上没有区别
             2.函数存在的意义是给过程调用   存储过程里面调用存储函数
             3.函数可以在sql语句里面直接调用
             4.存储过程能实现的,存储函数也能实现,存储函数能实现的,过程也能实现
             
        默认是 in       
*/
--查询指定员工的年薪
/*
    参数 : 员工的编号
    返回 : 年薪          
*/
create or replace function func_getsal(vempno number) return number
is
  --声明变量.保存年薪
  vtotalsal number;     
begin
  select sal*12 + nvl(comm,0) into vtotalsal from emp where empno = vempno;
  return vtotalsal;
end;

--调用存储函数
declare
  vsal number;
begin
  vsal := func_getsal(7788);
  dbms_output.put_line(vsal);
end;


--查询员工的姓名,和他的年薪
select ename,func_getsal(empno) from emp;
--查询员工的姓名和部门的名称


--查询指定员工的年薪--存储过程来实现
--参数: 员工编号
--输出: 年薪
create or replace procedure proc_gettotalsal(vempno in number,vtotalsal out number)
is
       
begin
  select sal*12 + nvl(comm,0) into vtotalsal from emp where empno = vempno;
end;


declare
  vtotal number;
begin
  proc_gettotalsal(7788,vtotal);
  dbms_output.put_line('年薪:'||vtotal);
end;

select *  from emp where empno = 8888; 

/*
    JAVA调用存储过程
       JDBC的开发步骤:
          1.导入驱动包
          2.注册驱动
          3.获取连接
          4.获取执行SQL的statement
          5.封装参数
          6.执行SQL
          7.获取结果
          8.释放资源   
*/

/*
   封装一个存储过程 : 输出所有表中的记录
   
   输出类型 : 游标  
*/
create or replace procedure proc_getemps(vrows out sys_refcursor)
is

begin
  --1.打开游标, 给游标赋值
  open vrows for select * from emp;
end;

```

### 触发器

```
/*
   触发器: 当用户执行了 insert | update | delete 这些操作之后, 可以触发一系列其它的动作/业务逻辑
       作用 : 
            在动作执行之前或者之后,触发业务处理逻辑
            插入数据,做一些校验
            
       语法:
           create [or replace] trigger 触发器的名称
           before | after
           insert | update | delete 
           on 表名
           [for each row]
           declare
           
           begin
             
           end;
           
       触发器的分类:
           语句级触发器:   不管影响多少行, 都只会执行一次
           
           行级触发器:     影响多少行,就触发多少次
                  :old  代表旧的记录, 更新前的记录
                  :new  代表的是新的记录
       
*/
--新员工入职之后,输出一句话: 欢迎加入黑马程序员
create or replace trigger tri_test1
after
insert
on emp
declare

begin
  dbms_output.put_line('欢迎加入黑马程序员');
end;

insert into emp(empno,ename) values(9527,'HUAAN');

--数据校验, 星期六老板不在, 不能办理新员工入职
--在插入数据之前
--判断当前日期是否是周六
--如果是周六,就不能插入
create or replace trigger tri_test2
before
insert 
on emp
declare
 --声明变量
 vday varchar2(10);
begin
  --查询当前
  select trim(to_char(sysdate,'day')) into vday from dual;
  --判断当前日期:
  if vday = 'saturday' then
     dbms_output.put_line('老板不在,不能办理入职');
     --抛出系统异常
     raise_application_error(-20001,'老板不在,不能办理入职');
  end if;
end;

insert into emp(empno,ename) values(9528,'HUAAN2');

--更新所有的工资 输出一句话
create or replace trigger tri_test3
after
update
on emp 
for each row
declare

begin
  dbms_output.put_line('更新了数据');
end;

update emp set sal = sal+10;



--判断员工涨工资后的工资一定要大于涨工资前的工资
/*
   200 --> 100
   触发器 : before
      旧的工资 
      新的工资
      如果旧的工资大于新的工资 , 抛出异常,不让它执行成功   
      
      
   触发器中不能提交事务,也不能回滚事务 
*/
create or replace trigger tri_updatesal
before
update
on emp
for each row
declare

begin
  if :old.sal > :new.sal then
    raise_application_error(-20002,'旧的工资不能大于新的工资');
  end if;
end;

update emp set sal = sal + 10;
select * from emp;

update emp set sal = sal - 100;


/*
   模拟mysql中ID的自增属性 auto_increment 
   insert into person(null,'张三');  
   
   触发器:
   
   pid=1  insert  pid=1
   
   序列 : create sequence seq_person_pid;       
*/
create table person(
    pid number primary key,
    pname varchar2(20)   
);

insert into person values(null,'张三'); 

create sequence seq_person_pid;

--触发器
create or replace trigger tri_add_person_pid
before
insert
on person
for each row
declare

begin
  dbms_output.put_line(:new.pname);
  --给新记录 pid 赋值
  select seq_person_pid.nextval into :new.pid from dual;
end;

insert into person values(null,'张三'); 


select * from person;
```

