--�����û�
create user gxh
identified by 123
--���û�Ĭ�ϱ�ռ�
DEFAULT TABLESPACE tableSpaceName
--���û�������ʱ��ռ�
TEMPORARY TABLESPACE xxx
--���û����Ȩ��
grant dba to gxh
--�޸�gxh�û�����
alter user gxh identified by orcl;
--���ñ���û����ڵı����µı�(�����ݵ�)
create table emp as select * from scott.emp

--��ѯgxh�û���emp��
select * from emp2

--���ñ���û����ڵı����µı�(�������ݵġ�ֻҪ���������)
create table emp2 as select * from scott.emp where 1=2

-------------------------------------��ҳ��ѯ
select * from (
          /*�Ӳ�ѯ����������������ݱ��ñ����� t �� ��ʾ*/
         select rownum rn,e.* from emp e order by sal
) t where rn between  3 and 6
/*
  t : ��ʱ��
	rownum �� �к�
*/
/************************��ռ�***************************/
--�½���ռ�
create tablespace tableSpaceName
--�����ļ��洢λ��
datafile 'd:/emp.dbf'
--�����ļ���С
size 10M
--�����Ƿ��Զ�����  on������������off:�ر�����
autoExtend on
--ɾ����ռ�
drop tablespace tableSpaceName
--ɾ���û����û���ռ�  cascade����ʾɾ���û��ͱ�ռ�
DROP USER gxh CASCADE 

/*****************************Ȩ��*********************************/

--���ݿ�Ȩ��
-------ϵͳȨ��
          CREATE SESSION �������ݿ�
					CREATE TABLE   ������
					CREATE VIEW ������ͼ
					CREATE SEQUENCE  ��������
      --ʹ��ʾ����
          GRANT CREATE SESSION TO gxh --���û� gxh �����������ݿ�Ȩ��
-------����Ȩ��(���ĳ�ű�Ĳ���Ȩ��)
      --ʹ��ʾ����
			    GRANT INSERT TO emp --�� emp �������Ȩ��
-------�����ɫȨ��
          CONNECT �����ӵ�¼���ݿ�Ľ�ɫ
					RESOURCE �������ݿ���Դ���в����Ľ�ɫ
					DBA ��ӵ������ϵͳȨ��,�������ݿ����Ա,һ�㲻����
-------����Ȩ�޵��﷨
          REVOKE CREATE TABLE FROM gxh  ������gxh�������Ȩ��
					REVOKE CONNECT FROM gxh ������gxh���ӵ�¼���ݿ��Ȩ��
-------�޸��û�����
          --����һ ��ͨ������������ڵķ�ʽ�޸�����
					      ALTER USER �û��� PASSWORD EXPIRE
					--������ ��ֱ���޸�����
					      ALTER USER �û��� IDENTIFIED BY ����
-------ɾ���û�
          --ע��ɾ���û���Ҫ�Ͽ����û�������
					DROP USER �û��� CASCADE;
					
/**************************************���У�sequence(���)***********************************/
 
--����һ������ 
	/*
		CREATE SEQUENCE ��������
		START WITH ��ʼλ��
		INCREMENT by �������
		maxvalue ���ֵ |  nomaxcalue û�����ֵ
		minvalue ��Сֵ |  nomincalue û����Сֵ
		cycle ѭ�� | nocycle ��ѭ��
		cache �������(Ĭ�Ϸ���20)
	*/
      CREATE SEQUENCE seq_emp : seq_emp��������
		        --���ôӶ��ٺſ�ʼ
			    START WITH 100  ����100��ʼ
			    --ÿ�ε�������
				INCREMENT BY 2  ��ÿ�ε���2
				--���ֵ  NOMAXVALUE :���ⶥ
				MAXVALUE 1000  :���ֵΪ1000
				--��Сֵ  nominvalue ��û����Сֵ
                MINVALUE 1  :��Сֵ1 
				--�Ƿ�ѭ��
				CYCLE ��ѭ��   NOCYCLE ����ѭ��
				--�Ƿ񻺴�
				CACHE 1 ������һ�� NOCACHE ��������-Ĭ�ϻ���

--ɾ������
      DROP SEQUENCE sequenceName ��ɾ����ΪsequenceName������

--��ȡ��һ������
      sequenceName.nextVal  : ������.nextVal --һ��������ӵ�����
--��ȡ��ǰ����
      sequenceName.currval :������.currval
--Oracle���ݿⲻ֧�����������Խ������н�������
--��������ֻ��������ֵ����

--�ַ�����������������ͨ��sys_guid()ʵ��
		--sys_guid()������32λ��Ψһ����
		select sys_guid() from dual;

--�޸�����:alter sequence �������� 
--ע�⣺start with�����ǲ����޸ĵ�
		alter sequence seq_emp 
		increment by 2
		nocycle;
/**********************ͬ���****************************/


/*
     ˽��ͬ��ʣ�ֻ���ڵ�ǰ�û��·��ʣ��Ҳ����뵱ǰ�û��µı�ͬ��
     
     ģʽ = �û�
     ģʽ���� = ����
     
     create synonym ͬ������� for  �û���.����
*/
--����˽��ͬ���
create synonym syn_emp for scott.emp;
--ʹ��˽��ͬ���
select * from syn_emp;

/*
   ����ͬ��ʣ��ܹ������е����ݿ��û����ʣ��ҿ�����˽��ͬ��ʡ���ͬ��
   create public synonym ͬ������� for �û���.����
*/
create public synonym dept for scott.dept;--�������ͬ��
create public synonym syn_emp for liang.dept;--������˽��ͬ���ͬ��

/*
 ����˳��
       ������ͬ������ͬ��ʱ������ʹ�ñ�
       ������ͬ�����˽��ͬ���ͬ��ʱ������ʹ��˽��ͬ���
*/

--ɾ��˽��ͬ���
drop synonym syn_emp;
--ɾ������ͬ���
drop public synonym syn_emp;

/*******************************����************************************/
--B����������׼������
create index index_empno on emp(empno);

--����������������ڴ����ڸ߻����У����ݱ������������
create index index_deptno on dept(deptno) reverse;

--λͼ����:�ʺϴ����ڵͻ����У�ֵ���������г��ֵĴ������ޣ�
create bitmap index index_job on emp(job);

/***********************************����**********************************/
/*
   ������(��Χ�������������11g)
       ��Χ������
              partition by range(����)
              (
                   partition ��������  value less than(ֵ),
                   partition ��������  value less than(ֵ),
                   partition ��������  value less than(maxvalue),
              )
              
       ���������
               partition by range(����)
               interval (numtoyminterval(n,'year/month'))
              (
                   partition ��������  value less than(ֵ),
                   partition ��������  value less than(ֵ),
                   partition ��������  value less than(maxvalue),
              )
              numtoyminterval(����1������2)
              ����1:ָ���Ǽ����ֵ
              ����2��year,month
              
*/
--��������ʱ�������Ͻ�����
		create table employee
		partition by range(sal)
		(
				  partition p1 values less than(1000),
				  partition p2 values less than(2000),
				  partition p3 values less than(3000),
				  partition p4 values less than(maxvalue)
		)

		as select * from scott.emp;

--�����ض���������
		select * from employee partition(p2);


--�������
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

	  

/**********************************plsql���***********************************/

begin
  --���
  dbms_output.put_line('hello world');
end;


--��������
declare
    v_name varchar2(20) :='����';--����������ͬʱ��ֵ
    v_age number :=20;
    v_sex constant varchar2(2) :='��';

--ִ�в���
begin
     v_name := 'tom';--��ֵ
     dbms_output.put_line('����:' || v_name);
     dbms_output.put_line('���䣺'||v_age );
     dbms_output.put_line('�Ա�'||v_sex);
end;

/****************************��������*********************************/
--%type:���ñ���ĳһ�е���������
declare
    v_name emp.ename%type :='tom';
    --v_age emp.hiredate%type :=10;
--ִ�в���
begin
     select ename into v_name from emp where empno = 7788;  
     dbms_output.put_line('���ƣ�'||v_name);
      --dbms_output.put_line('���䣺'||v_age);
end;


--%rowtype:���ñ���ĳһ�е���������
declare
   empObj emp%rowtype;
--ִ�в���
begin
     -- ��ֵ
     select * into empObj from emp where empno = 7788;  
     dbms_output.put_line(empObj.empno);
     dbms_output.put_line(empObj.ename);
     dbms_output.put_line(empObj.sal);
     dbms_output.put_line(empObj.hiredate);
end;

/*****************************��������*****************************/

--if
declare 
  score number :=80;
begin
  if(score>=80) then
      dbms_output.put_line('�ɼ�����');          
  end if;
end;

--if -else
declare 
  score number :=50;
begin
  if(score>=60) then
      dbms_output.put_line('�ɼ��ϸ�');  
  else
      dbms_output.put_line('�ɼ����ϸ�');         
  end if;
end;

--if - elsif - else

declare 
  state number :=5;
begin
  if(state=1) then
        dbms_output.put_line('������');   
  elsif(state=2) then
        dbms_output.put_line('���ջ�');   
  elsif(state=3) then
        dbms_output.put_line('��ǩ��'); 
  else
        dbms_output.put_line('������'); 
  end if;
end;

--case
declare 
  state number :=1;
begin
  case state 
  when 1 then
        dbms_output.put_line('������');   
  when 2 then
        dbms_output.put_line('���ջ�');   
  when 3 then
        dbms_output.put_line('��ǩ��');
  else
        dbms_output.put_line('������'); 
  end case;
end;

/********************************ѭ������********************************/

declare 
  i number :=0;--��ʼ����
  result number :=0;--�ۺ�
begin
  --��ʼѭ��
  loop
    i := i + 2;--��������
    result := result + i;--ÿѭ��һ�Σ��ۼ��ܺ�
    exit when i>=100;--�жϣ���i���ڵ���100ʱ���˳�ѭ��
  end loop;
  
  dbms_output.put_line('1��100���ڵ�ż��֮�ͣ�'||result); 
end;

--while
declare 
  i number :=0;--��ʼ����
  result number :=0;--�ۺ�
begin
  --��ʼѭ��
  while(i<=100) loop
    result := result + i;--ÿѭ��һ�Σ��ۼ��ܺ�
    i := i + 2;--��������
  end loop;
  
  dbms_output.put_line('1��100���ڵ�ż��֮�ͣ�'||result); 
end;


--for
declare 
  i number :=0;--��ʼ����
begin
  for i in 1..10 loop
      dbms_output.put_line(i); 
  end loop;
end;


/************************************�쳣����*********************************/
--Ԥ�����쳣����0������
declare
  num1 number :=4;
  num2 number :=0;
  result number;
begin
 result :=num1 /num2;
 dbms_output.put_line(result); 
--�쳣������
exception
  --zero_divide:��0������
  when zero_divide then
      dbms_output.put_line('��������Ϊ0'); 
  --others�������쳣
  when others then
      dbms_output.put_line('���������쳣������ϵ����Ա��');    
end;
 
--�Զ����쳣
declare 
  v_comm emp.comm%type;--����
  e_comm_is_null exception;--�쳣���ͱ���
begin
  
  --�����������ֵ
  select comm into v_comm from emp where empno = 7788;
  --�ж��Ƿ��н���
  if v_comm is null then
    --�׳��쳣
    raise e_comm_is_null;
  end if;
exception
  when no_data_found then
       dbms_output.put_line('��Ա�������ڣ�');  
  when e_comm_is_null then
       dbms_output.put_line('��Ա��û�н���');  
  when others then
      dbms_output.put_line('���������쳣������ϵ����Ա��');    
end;
 
/***********************************�α�************************************/
declare
   --1.�����α�
   cursor cursor_emp is select * from emp;--���α�ָ��һ����ѯ���
   empObj emp%rowtype;--����Ա���������
begin
  --2.���α�
  open cursor_emp;
  --ѭ��
  loop
    --3.��ȡ�α꣨Ŀ�ģ���ȡ���е����ݣ�
    fetch cursor_emp into empObj;
    --����˳�ѭ��������
    exit when cursor_emp%notfound;
    --�������
    dbms_output.put_line('Ա��������' ||empObj.ename);
  end loop;
  --4.�ر��α�
  close cursor_emp;
end;

--�����α����н��
declare
   --1.�����α�
   cursor cursor_emp is select * from emp for update;--���α�ָ��һ����ѯ���
   empObj emp%rowtype;--����Ա���������
begin
  --2.���α�
  open cursor_emp;
  --ѭ��
  loop
    --3.��ȡ�α꣨Ŀ�ģ���ȡ���е����ݣ�
    fetch cursor_emp into empObj;
    --����˳�ѭ��������
    exit when cursor_emp%notfound;
    --��������
    --������ű��Ϊ10���޸ĸò����µ�Ա��н�ʣ�ͳһ��н500��
    if(empObj.deptno=10) then
           if(empObj.sal>=5000) then
                   update emp set sal = sal+500 where current of cursor_emp;--�޸ĵ�ǰ�α�����
           elsif (empObj.sal>=3000) then
                    update emp set sal = sal+1000 where current of cursor_emp;--�޸ĵ�ǰ�α�����
           elsif (empObj.sal>=1000) then
                     update emp set sal = sal+1500 where current of cursor_emp;--�޸ĵ�ǰ�α�����
           end if;
    end if; 
    
  
  end loop;
  --4.�ر��α�
  close cursor_emp;
end;





select * from emp where deptno =10;

