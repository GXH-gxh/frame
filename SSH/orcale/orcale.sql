--创建用户
create user gxh
identified by 123
--给用户默认表空间
DEFAULT TABLESPACE tableSpaceName
--给用户设置临时表空间
TEMPORARY TABLESPACE xxx
--给用户最高权限
grant dba to gxh
--修改gxh用户密码
alter user gxh identified by orcl;
--利用别的用户存在的表创建新的表(带数据的)
create table emp as select * from scott.emp

--查询gxh用户的emp表
select * from emp2

--利用别的用户存在的表创建新的表(不带数据的、只要条件不相等)
create table emp2 as select * from scott.emp where 1=2

-------------------------------------分页查询
select * from (
          /*子查询条件、查出来的数据表用别名“ t ” 表示*/
         select rownum rn,e.* from emp e order by sal
) t where rn between  3 and 6
/*
  t : 临时表
	rownum ： 行号
*/
/************************表空间***************************/
--新建表空间
create tablespace tableSpaceName
--设置文件存储位置
datafile 'd:/emp.dbf'
--设置文件大小
size 10M
--设置是否自动扩增  on：开启扩增、off:关闭自增
autoExtend on
--删除标空间
drop tablespace tableSpaceName
--删除用户和用户表空间  cascade：表示删除用户和标空间
DROP USER gxh CASCADE 

/*****************************权限*********************************/

--数据库权限
-------系统权限
          CREATE SESSION 连接数据库
					CREATE TABLE   创建表
					CREATE VIEW 创建视图
					CREATE SEQUENCE  创建序列
      --使用示例：
          GRANT CREATE SESSION TO gxh --给用户 gxh 赋予连接数据库权限
-------对象权限(针对某张表的操作权限)
      --使用示例：
			    GRANT INSERT TO emp --给 emp 表赋予插入权限
-------授予角色权限
          CONNECT ：连接登录数据库的角色
					RESOURCE ：对数据库资源进行操作的角色
					DBA ：拥有所以系统权限,属于数据库管理员,一般不授予
-------撤销权限的语法
          REVOKE CREATE TABLE FROM gxh  ：撤销gxh创建表的权限
					REVOKE CONNECT FROM gxh ：撤销gxh连接登录数据库的权限
-------修改用户密码
          --方法一 ：通过设置密码过期的方式修改密码
					      ALTER USER 用户名 PASSWORD EXPIRE
					--方法二 ：直接修改密码
					      ALTER USER 用户名 IDENTIFIED BY 密码
-------删除用户
          --注：删除用户需要断开该用户的连接
					DROP USER 用户名 CASCADE;
					
/**************************************序列：sequence(序号)***********************************/
 
--创建一个序列 
	/*
		CREATE SEQUENCE 序列名称
		START WITH 开始位置
		INCREMENT by 间隔数量
		maxvalue 最大值 |  nomaxcalue 没有最大值
		minvalue 最小值 |  nomincalue 没有最小值
		cycle 循环 | nocycle 不循环
		cache 缓存个数(默认分配20)
	*/
      CREATE SEQUENCE seq_emp : seq_emp、序列名
		        --设置从多少号开始
			    START WITH 100  ：从100开始
			    --每次递增多少
				INCREMENT BY 2  ：每次递增2
				--最大值  NOMAXVALUE :不封顶
				MAXVALUE 1000  :最大值为1000
				--最小值  nominvalue ：没有最小值
                MINVALUE 1  :最小值1 
				--是否循环
				CYCLE ：循环   NOCYCLE ：不循环
				--是否缓存
				CACHE 1 ：缓存一个 NOCACHE ：不缓存-默认缓存

--删除序列
      DROP SEQUENCE sequenceName ：删除名为sequenceName的序列

--获取下一个序列
      sequenceName.nextVal  : 序列名.nextVal --一般用于添加的主键
--获取当前序列
      sequenceName.currval :序列名.currval
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
/**********************同义词****************************/


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

/*******************************索引************************************/
--B树索引（标准索引）
create index index_empno on emp(empno);

--反向键索引：适用于创建在高基数列（数据编号连续增长）
create index index_deptno on dept(deptno) reverse;

--位图索引:适合创建在低基数列（值在整个表中出现的次数有限）
create bitmap index index_job on emp(job);

/***********************************分区**********************************/
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

	  

/**********************************plsql编程***********************************/

begin
  --输出
  dbms_output.put_line('hello world');
end;


--声明部分
declare
    v_name varchar2(20) :='张三';--声明变量的同时赋值
    v_age number :=20;
    v_sex constant varchar2(2) :='男';

--执行部分
begin
     v_name := 'tom';--赋值
     dbms_output.put_line('姓名:' || v_name);
     dbms_output.put_line('年龄：'||v_age );
     dbms_output.put_line('性别：'||v_sex);
end;

/****************************属性类型*********************************/
--%type:引用表中某一列的数据类型
declare
    v_name emp.ename%type :='tom';
    --v_age emp.hiredate%type :=10;
--执行部分
begin
     select ename into v_name from emp where empno = 7788;  
     dbms_output.put_line('名称：'||v_name);
      --dbms_output.put_line('年龄：'||v_age);
end;


--%rowtype:引用表中某一行的数据类型
declare
   empObj emp%rowtype;
--执行部分
begin
     -- 赋值
     select * into empObj from emp where empno = 7788;  
     dbms_output.put_line(empObj.empno);
     dbms_output.put_line(empObj.ename);
     dbms_output.put_line(empObj.sal);
     dbms_output.put_line(empObj.hiredate);
end;

/*****************************条件控制*****************************/

--if
declare 
  score number :=80;
begin
  if(score>=80) then
      dbms_output.put_line('成绩优秀');          
  end if;
end;

--if -else
declare 
  score number :=50;
begin
  if(score>=60) then
      dbms_output.put_line('成绩合格');  
  else
      dbms_output.put_line('成绩不合格');         
  end if;
end;

--if - elsif - else

declare 
  state number :=5;
begin
  if(state=1) then
        dbms_output.put_line('待发货');   
  elsif(state=2) then
        dbms_output.put_line('待收货');   
  elsif(state=3) then
        dbms_output.put_line('已签收'); 
  else
        dbms_output.put_line('待评价'); 
  end if;
end;

--case
declare 
  state number :=1;
begin
  case state 
  when 1 then
        dbms_output.put_line('待发货');   
  when 2 then
        dbms_output.put_line('待收货');   
  when 3 then
        dbms_output.put_line('已签收');
  else
        dbms_output.put_line('待评价'); 
  end case;
end;

/********************************循环控制********************************/

declare 
  i number :=0;--初始变量
  result number :=0;--综合
begin
  --开始循环
  loop
    i := i + 2;--迭代变量
    result := result + i;--每循环一次，累加总和
    exit when i>=100;--判断：当i大于等于100时，退出循环
  end loop;
  
  dbms_output.put_line('1到100以内的偶数之和：'||result); 
end;

--while
declare 
  i number :=0;--初始变量
  result number :=0;--综合
begin
  --开始循环
  while(i<=100) loop
    result := result + i;--每循环一次，累加总和
    i := i + 2;--迭代变量
  end loop;
  
  dbms_output.put_line('1到100以内的偶数之和：'||result); 
end;


--for
declare 
  i number :=0;--初始变量
begin
  for i in 1..10 loop
      dbms_output.put_line(i); 
  end loop;
end;


/************************************异常处理*********************************/
--预定义异常，以0作除数
declare
  num1 number :=4;
  num2 number :=0;
  result number;
begin
 result :=num1 /num2;
 dbms_output.put_line(result); 
--异常处理部分
exception
  --zero_divide:以0作除数
  when zero_divide then
      dbms_output.put_line('除数不能为0'); 
  --others：其他异常
  when others then
      dbms_output.put_line('出现其他异常，请联系管理员！');    
end;
 
--自定义异常
declare 
  v_comm emp.comm%type;--奖金
  e_comm_is_null exception;--异常类型变量
begin
  
  --给奖金变量赋值
  select comm into v_comm from emp where empno = 7788;
  --判断是否有奖金
  if v_comm is null then
    --抛出异常
    raise e_comm_is_null;
  end if;
exception
  when no_data_found then
       dbms_output.put_line('该员工不存在！');  
  when e_comm_is_null then
       dbms_output.put_line('该员工没有奖金！');  
  when others then
      dbms_output.put_line('出现其他异常，请联系管理员！');    
end;
 
/***********************************游标************************************/
declare
   --1.声明游标
   cursor cursor_emp is select * from emp;--给游标指定一个查询结果
   empObj emp%rowtype;--声明员工对象变量
begin
  --2.打开游标
  open cursor_emp;
  --循环
  loop
    --3.提取游标（目的：读取表中的数据）
    fetch cursor_emp into empObj;
    --检查退出循环的条件
    exit when cursor_emp%notfound;
    --输出姓名
    dbms_output.put_line('员工姓名：' ||empObj.ename);
  end loop;
  --4.关闭游标
  close cursor_emp;
end;

--利用游标调整薪资
declare
   --1.声明游标
   cursor cursor_emp is select * from emp for update;--给游标指定一个查询结果
   empObj emp%rowtype;--声明员工对象变量
begin
  --2.打开游标
  open cursor_emp;
  --循环
  loop
    --3.提取游标（目的：读取表中的数据）
    fetch cursor_emp into empObj;
    --检查退出循环的条件
    exit when cursor_emp%notfound;
    --加入条件
    --如果部门编号为10，修改该部门下的员工薪资（统一涨薪500）
    if(empObj.deptno=10) then
           if(empObj.sal>=5000) then
                   update emp set sal = sal+500 where current of cursor_emp;--修改当前游标数据
           elsif (empObj.sal>=3000) then
                    update emp set sal = sal+1000 where current of cursor_emp;--修改当前游标数据
           elsif (empObj.sal>=1000) then
                     update emp set sal = sal+1500 where current of cursor_emp;--修改当前游标数据
           end if;
    end if; 
    
  
  end loop;
  --4.关闭游标
  close cursor_emp;
end;





select * from emp where deptno =10;

