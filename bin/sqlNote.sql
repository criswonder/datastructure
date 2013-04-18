------------------------------------------------函数篇----------------------------------------------------
--创建一个简单函数
create or replace function get_hello_msg
return varchar2 as
begin
       return 'hello_world';
end get_hello_msg;

--在数据字典中查看函数的定义信息
select name,type,line,text from user_source where lower(name) = 'get_hello_msg';

select object_name,object_type,status from user_objects where lower(object_name)='get_hello_msg';

--查看函数的返回值,注意要想输出值得先开启服务器端:set serverout on;
declare msg varchar2(20);
begin 
        msg := get_hello_msg;
        dbms_output.put_line(msg);
end;

--函数中的括号,Oracle在遇到变量时,如果该变量可用,则使用该变量,如果该变量不可用,才尝试将其解释为一个函数名
declare 
        msg varchar2(20);
        get_hello_msg varchar2(20);
begin
        get_hello_msg := 'welcome message';
        msg := get_hello_msg;
        dbms_output.put_line(msg);
end;

--X:注意不要声明一个函数同名的变量,否则，系统将无法确定引用该函数
declare 
        msg varchar2(20);
        get_hello_msg varchar2(20);
begin
        get_hello_msg := 'welcome message';
        msg := get_hello_msg();
        dbms_output.put_line(msg);
end;

--函数的参数:本事例演示如何在数据中中计算个人所得税
create or replace
function get_tax(p_salary number)
return number as
begin
       declare tax_salary number;
       begin
               tax_salary := p_salary -2000;
               
               if tax_salary <=0 then
                  return 0;
               end if;
               if tax_salary <=500 then
                  return tax_salary*5/100;
               end if;
               if tax_salary <=2000 then
                  return tax_salary*10/100-25;
               end if;
               if tax_salary <=5000 then
                  return tax_salary*15/100-125;
               end if;
               if tax_salary <=20000 then
                  return tax_salary*20/100-375;
               end if;
               if tax_salary <=40000 then
                  return tax_salary*30/100-3375;
               end if;
        end;
end get_tax;

--查看刚刚创建的函数
select object_name,object_type,status from user_objects where lower(object_name)='get_tax';

--创建函数的调用
select get_tax(6000) tax from dual;

--典型的函数举例:行转列的问题
--创建students表
create table students
(
       student_id      number         primary key,
       student_name    varchar2(20),
       student_age     number
)

--创建序列
create sequence students_seq start with 1 increment by 1;

--插入测试数据
insert into students values(students_seq.nextval,'zhangsan',23);
insert into students values(students_seq.nextval,'lisi',33);
insert into students values(students_seq.nextval,'wangwu',23);
insert into students values(students_seq.nextval,'zhass',43);
insert into students values(students_seq.nextval,'zhan',13);
insert into students values(students_seq.nextval,'zngsan',83);
insert into students values(students_seq.nextval,'zangsan',43);
insert into students values(students_seq.nextval,'angsan',27);
insert into students values(students_seq.nextval,'gsan',43);
insert into students values(students_seq.nextval,'angsan',25);

commit;

select * from students;

--创建函数,该函数是一个行转列的问题:演示如何获取所有学生的姓名
create or replace
function get_student_str
return varchar2      --返回一个字符串(即所有学生的姓名)
as                   --
begin
       declare cursor cu_student is 
               select student_name 
               from students 
               order by student_id;--声明一个游标,该游标用户获取数据库表中所有学生的姓名记录
               student_name        varchar2(20);--该变量用于获取游标记录
               student_name_str    varchar2(500);--返回值
       begin 
               open cu_student;
               fetch cu_student into student_name;--获取游标记录值
               while cu_student%found loop
                     student_name_str := student_name_str || student_name || ',';
                     fetch cu_student into student_name;
               end loop;
               return substr(student_name_str,1,length(student_name_str)-1);
       end;
end get_student_str;

--执行结果
set serverout on;
declare rowString varchar2(500);
begin 
        rowString := get_student_str();
        dbms_output.put_line(rowString);
end;

------------------------------------------------存储过程篇-------------------------------------------
--创建存储过程
create or replace procedure update_students
as
begin
       update students set student_age = 10;
       commit;
end update_students;

--查看存储过程在数据字典中的信息
select object_name,object_type,status from user_objects where lower(object_name)='update_students';
--在user_source中查找存储过程的信息
select * from user_source where lower(name)='update_students';

--执行存储过程
execute update_students;
--在代码块中执行存储过程
begin 
        update_students;
end;

--存储过程中的in,out,in out参数的使用
create or replace procedure update_students_in_out(s_id in number, s_name out varchar2)
as
begin
       update students set student_age = 100 where student_id = s_id;
       select student_name into s_name from students where student_id = 1;
       commit;
end update_students_in_out;

--in out与in,out中间需要注意的地方
--首先,存储过程可能被多个用户调用,那么输出参数的变量,将被频繁且无规律的更新
--控制该变量将变得非常困难。
--其次,in out参数具有可输出的性质,那么,将不能使用常量传入参数,否则,将会出现
--编译错误
--所以,除非必要,应该首先选择单向参数(in/out)

--存储过程的两种传参方式
--1:位置表示法;2:名称标识法
--需要注意的是:一旦使用的名称标识法,那么其后的参数也必须使用名称表示法！否则将会报
--PLS-00312:a positional parameter association may not follow a named association
--名称标识法的事例
declare age number;
begin
        update_students_in_out(in_name=>'zhangsan',out_age=>age);
        dbms_output.put_line(age);
end;

--参数默认值
create or replace
procedure insert_student
            (
                   in_student_id in number,
                   in_student_name in varchar2,
                   in_student_age in number default 20
            )
            as
            begin
                   insert into students values(in_student_id,in_student_name,in_student_age);
                   commit;
            end insert_student;
--需要注意的是:1:默认值只是对in参数而言的,out和in out参数没有默认值.
--             2:有默认值的参数应该置于参数的末尾,因为有时用户需要修给该参数
--             3:没有默认值的参数,参数顺序可以遵循:"IN参数"——"OUT参数"——"IN OUT参数"

--执行存储过程的两种方式
execute pro;--通过execute命令调用存储过程
begin       --在代码块中执行存储过程
        pro;
end;