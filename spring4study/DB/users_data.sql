

INSERT INTO `P_USERS` (`ID`,`LOGINNAME`,`PASSWORD`,`ENABLED`,`LAST_UPDATE_DATE`,`LAST_UPDATED_BY`,`CREATION_DATE`,`CREATED_BY`) VALUES (1,'shunwukong','$2a$10$nMC50BnTLaFCensnM131yuA4b48vktThRrYZ89uUivpjFtneVLEaS','Y','2018-01-17 16:00:00','1','2018-01-17 16:00:00','1');
INSERT INTO `P_USERS` (`ID`,`LOGINNAME`,`PASSWORD`,`ENABLED`,`LAST_UPDATE_DATE`,`LAST_UPDATED_BY`,`CREATION_DATE`,`CREATED_BY`) VALUES (2,'zhubajie','$2a$10$nMC50BnTLaFCensnM131yuA4b48vktThRrYZ89uUivpjFtneVLEaS','Y','2018-01-17 16:00:00','1','2018-01-17 16:00:00','1');
INSERT INTO `P_USERS` (`ID`,`LOGINNAME`,`PASSWORD`,`ENABLED`,`LAST_UPDATE_DATE`,`LAST_UPDATED_BY`,`CREATION_DATE`,`CREATED_BY`) VALUES (3,'god','$2a$10$nMC50BnTLaFCensnM131yuA4b48vktThRrYZ89uUivpjFtneVLEaS','Y','2018-01-17 16:00:00','1','2018-01-17 16:00:00','1');


INSERT INTO `p_res_attr_map` (`ID`,`ATTRIBUTE_ID`,`RESOURCE_ID`) VALUES (1,1,1);
INSERT INTO `p_res_attr_map` (`ID`,`ATTRIBUTE_ID`,`RESOURCE_ID`) VALUES (3,2,2);
INSERT INTO `p_res_attr_map` (`ID`,`ATTRIBUTE_ID`,`RESOURCE_ID`) VALUES (4,2,3);
INSERT INTO `p_res_attr_map` (`ID`,`ATTRIBUTE_ID`,`RESOURCE_ID`) VALUES (2,1,4);


INSERT INTO `p_res_attribute` (`ID`,`NAME`,`A_TYPE`,`ENABLE`,`MEMO`) VALUES (1,'模块module01下的所有功能','ROLE_','Y',NULL);
INSERT INTO `p_res_attribute` (`ID`,`NAME`,`A_TYPE`,`ENABLE`,`MEMO`) VALUES (2,'模块module02下的注册功能','ROLE_','Y',NULL);


INSERT INTO `p_resources` (`ID`,`IDENTIFICATION`,`R_TYPE`,`ENABLE`,`DESCRIPTION`) VALUES (1,'/jsp/modules/module01/**','URL','Y','模块一下所有资源');
INSERT INTO `p_resources` (`ID`,`IDENTIFICATION`,`R_TYPE`,`ENABLE`,`DESCRIPTION`) VALUES (2,'/jsp/modules/module02/registration.jsp','URL','Y','模块二下用户注册页面');
INSERT INTO `p_resources` (`ID`,`IDENTIFICATION`,`R_TYPE`,`ENABLE`,`DESCRIPTION`) VALUES (3,'org.example.module02.services.IBizTwo.addBiz01','Method','Y',NULL);
INSERT INTO `p_resources` (`ID`,`IDENTIFICATION`,`R_TYPE`,`ENABLE`,`DESCRIPTION`) VALUES (4,'org.example.module01.services.IServiceOne.*','Method','Y',NULL);


INSERT INTO `p_user_permission` (`ID`,`USER_ID`,`RES_ATTRIBUTE_ID`) VALUES (1,1,1);
INSERT INTO `p_user_permission` (`ID`,`USER_ID`,`RES_ATTRIBUTE_ID`) VALUES (2,1,2);
INSERT INTO `p_user_permission` (`ID`,`USER_ID`,`RES_ATTRIBUTE_ID`) VALUES (3,2,1);

INSERT INTO `spring`.`p_users_detail` (`ID`, `UID`, `NICKNAME`, `EMAIL`, `MOBILE_NUMBER`, `ADDRESS`) VALUES ('1', '1', '孙悟空', '', '13800001111', '花果山');
INSERT INTO `spring`.`p_users_detail` (`ID`, `UID`, `NICKNAME`, `MOBILE_NUMBER`, `ADDRESS`) VALUES ('2', '2', '猪八戒', '13800002222', '高老庄');
INSERT INTO `spring`.`p_users_detail` (`ID`, `UID`, `NICKNAME`, `MOBILE_NUMBER`, `ADDRESS`) VALUES ('3', '3', '上帝', '13800003333', '天堂');


commit;








