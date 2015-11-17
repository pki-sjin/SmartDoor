INSERT INTO smartdoor.sd_exhibition(
            id, code, subject, html, starttime, endtime, sysrecord, valid, 
            remark)
    VALUES (1,'cosplay','中国第一届cosplay大赛','http://www.baidu.com','2016-01-01 10:00:00','2017-01-01 10:00:00',1,1,'');

    
INSERT INTO smartdoor.sd_join(
            id, code, description, sysrecord, valid, remark)
    VALUES (1,'joining','可以参加',1,1,'');
INSERT INTO smartdoor.sd_join(
            id, code, description, sysrecord, valid, remark)
    VALUES (2,'buying','门票未购买',1,1,'');
INSERT INTO smartdoor.sd_join(
            id, code, description, sysrecord, valid, remark)
    VALUES (3,'bought','门票已购买',1,1,'');
INSERT INTO smartdoor.sd_join(
            id, code, description, sysrecord, valid, remark)
    VALUES (4,'joined','已参加',1,1,'');
INSERT INTO smartdoor.sd_join(
            id, code, description, sysrecord, valid, remark)
    VALUES (5,'doing','展会进行中',1,1,'');

INSERT INTO smartdoor.sd_join(
            id, code, description, sysrecord, valid, remark)
    VALUES (6,'over','展会已结束',1,1,'');


INSERT INTO smartdoor.sd_stage(
            id, code, ex_id, subject, html, sysrecord, valid, remark)
    VALUES (1,'table1',1,'展台1','http://www.qq.com',1,1,'');

    
INSERT INTO smartdoor.sd_stage(
            id, code, ex_id, subject, html, sysrecord, valid, remark)
    VALUES (2,'table2',1,'展台2','http://www.163.com',1,1,'');

INSERT INTO smartdoor.sd_ticket(
            id, code, ex_id, name, description, price, type_id, createtime, 
            sysrecord, valid, remark)
    VALUES (1,'normal',1,'普通票','进出单次，反复无效',0.01,1,'2015-11-15 00:00:00',1,1,'');   
            
INSERT INTO smartdoor.sd_ticket(
            id, code, ex_id, name, description, price, type_id, createtime, 
            sysrecord, valid, remark)
    VALUES (2,'special',1,'单日票','当天可反复进入',0.02,1,'2015-01-01 00:00:00',1,1,'');

INSERT INTO smartdoor.sd_ticket(
            id, code, ex_id, name, description, price, type_id, createtime, 
            sysrecord, valid, remark)
    VALUES (3,'vip',1,'VIP票','可携带一名嘉宾进入',0.03,1,'2015-01-01 00:00:00',1,1,'');

             
INSERT INTO smartdoor.sd_ticket_type(
            id, code, description, sysrecord, valid, remark)
    VALUES (1,'one_time','单次票',1,1,'');
    
INSERT INTO smartdoor.sd_ticket_type(
            id, code, description, sysrecord, valid, remark)
    VALUES (2,'one_day','单日票',1,1,'');
    
INSERT INTO smartdoor.sd_ticket_type(
            id, code, description, sysrecord, valid, remark)
    VALUES (3,'full_time','套票',1,1,'');
