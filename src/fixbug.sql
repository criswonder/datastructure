CREATE OR REPLACE FUNCTION fix_deductlist(v_deductlistid IN NUMBER, v_correct OUT VARCHAR2) RETURN VARCHAR2 IS
--ids 错误的扣款清单ID
CURSOR ids IS select distinct deductlistid from st_outofservicerequest where expressnum in (
select f.expressnum from st_fee f where f.siteid is null and f.sitename not in('广东分公司承担','总部承担') 
);
BEGIN
    --执行语句段
    --declare   id   varchar2(30);
    declare correctSumValue varchar2(3);--正确的结果
    		deductlistName varchar2(20);--扣款清单的名称
    begin
	    for ids_result in ids loop
	    	begin
		    	if ids_result is null then
		    			dbms_output.put_line('错误的扣款清单ID ids_result为空');
		    		else
				    	select sum(系统扣款金额) into correctSumValue from st_fee f where f.expressnum in (
		    				select expressnum from outofservicerequest oos where oos.deductlistid = ids_result
		    				) and f.sitename not in('广东分公司承担','总部承担');
				    	update st_deductlist d set d.系统扣款合计=correctSumValue where d.id=ids_result;
				    	select name into deductlistName from st_deductlist where id=ids_result;
				    	v_correct:=v_correct||','||ids_result;
		    	end if;
	    	end;
	    END LOOP;
    end;
    RETURN v_correct;
END fix_deductlist;



--调用出入参存储过程
DECLARE 
    v_name VARCHAR2(20);
BEGIN 
    DBMS_OUTPUT.put_line('在PL/SQL中打印的结果：'||fix_deductlist(1, v_name));
END;
--查找错误的扣款清单id
select distinct deductlistid from st_outofservicerequest where expressnum in (
select f.expressnum from st_fee f where f.siteid is null and f.sitename not in('广东分公司承担','总部承担') 
)
