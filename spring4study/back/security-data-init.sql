DELETE FROM `T_ROLEPERMISSIONS`;
DELETE FROM T_USERPERMISSIONS;
DELETE FROM T_PERMISSIONS;
DELETE FROM `T_USERROLES`;
DELETE FROM T_USER;
DELETE FROM T_USERS;
DELETE FROM T_ROLES;
COMMIT;


------------- 0 ji
insert into `T_PERMISSIONS`
values('1',null,0,'/user\\.do\\?.*'
,'修改/找回密码','URL','PERM_ANONYMOUS','ANONYMOUS',0);
insert into `T_PERMISSIONS`
values('2',null,0,'/user\\.do\\?.*'
,'修改/找回密码','URL','PERM_INDEX','MODIFYPWD',0);
insert into `T_PERMISSIONS`
values('3',null,0,'/(index|top|left|sitespread|customerAndMeber|setting|menu|manageIndex)\\.(do|jsp).*'
,'首页','URL','PERM_INDEX','INDEX',0);
insert into `T_PERMISSIONS`
values('4',null,0,'/pages/login/(login|index)\\.jsp\\??.*'
,'登录界面','URL','PERM_ANONYMOUS','ANONYMOUS',0);
insert into `T_PERMISSIONS`
values('5',null,0,'/pages/login/(login|index)\\.jsp\\??.*'
,'登录界面','URL','PERM_INDEX','INDEX',0);
insert into `T_PERMISSIONS`
values('6',null,0,'#*(/\\w*)*/(\\w+)\\.(do|jsp)\\??.*'
,'所有','URL','PERM_ALL','ALL',0);



COMMIT;

-------------------------------------
----- 权限
------------- 1JI ---------------
--网站维护
insert into `T_PERMISSIONS`
values('10',null,0,'DO'
,'网站维护','URL','PERM_SITE_OPER','SITE_OPER',1);
--网站推广
insert into `T_PERMISSIONS`
values('11',null,0,'/WebStats\\.do'
,'网站推广','URL','PERM_SITE_GENERALIZE','SITE_GENERALIZE',1);
--客户管理
insert into `T_PERMISSIONS`
values('12',null,0,'DO'
,'客户管理','URL','PERM_CLIENT_MANAGER','CLIENT_MANAGER',1);
--企业设置
insert into `T_PERMISSIONS`
values('13',null,0,'/siteConfig/\\w+\\.do\\??.*'
,'企业设置','URL','PERM_SITE_CONFIG','SITE_CONFIG',1);

--------------- 2JI --------------
--招聘
insert into `T_PERMISSIONS`
values('20','10',0,'/(resume|offer)\\.do\\??.*'
,'招聘管理','URL','PERM_ZPGL','ZPGL',2);
--资讯管理
insert into `T_PERMISSIONS`
values('21','10',0,'/(infomanage|infoConVindicate|infocategory|inforelation)\\.do\\??.*'
,'资讯管理','URL','PERM_ZXZZFB','ZXZZFB',2);

--留言管理
insert into `T_PERMISSIONS`
values('22','10',0,'/(search|message)\\.do\\??.*'
,'留言管理','URL','PERM_LYGL','LYGL',2);
--内容管理
insert into `T_PERMISSIONS`
values('23','10',0,'(/\\w+)*/(navigation|specify|commonCategory|pictureCategory|column|picture_list|content_list|commonContent|pictureContent)\\.(do|jsp)\\??.*'
,'内容管理','URL','PERM_NRGL','NRGL',2);
--广告管理
insert into `T_PERMISSIONS`
values('24','10',0,'(/\\w+)*/(advertisesearch|advertise|advertisesave)\\.(do|jsp)\\??.*'
,'广告管理','URL','PERM_GGGL','GGGL',2);
insert into `T_PERMISSIONS`
values('24001','10',0,'(/\\w+)*/product\\.do\\?method=showProducts&inputvalue=prolink2'
,'广告管理','URL','PERM_GGGL','GGGL',9);
--
--企业地图
insert into `T_PERMISSIONS`
values('25','10',0,'/(maplabel|mapguide|mapcard)\\.do\\??.*'
,'企业地图','URL','PERM_QYDT','QYDT',2);

--问卷调查
insert into `T_PERMISSIONS`
values('27','10',0,'/(question|questionnaireMaint|questionnaireLook)\\.do\\??.*'
,'问卷调查','URL','PERM_WSDC','WSDC',2);
--下载中心
insert into `T_PERMISSIONS`
values('28','10',0,'(/\\w*)*/(downloadCategory_main|downloadCategory|downloadFile)\\.(do|jsp)\\??.*'
,'下载中心','URL','PERM_XZZX','XZZX',2);

--商品管理
insert into `T_PERMISSIONS`
values('26','10',0,'(/\\w*)*/(product|pmcategory|product_sort|product_sorttree|product_categoryorder)\\.(do|jsp)\\??.*'
,'商品管理','URL','PERM_SPGL','SPGL',2);
--订购管理
--订单管理
insert into `T_PERMISSIONS`
values('29','10',0,'(/\\w*)*/(order|order_advance)\\.(do|jsp)\\??.*'
,'订单管理','URL','PERM_ORDERMG','DGGL',2);
--网上支付维护
insert into `T_PERMISSIONS`
values('29002','10',0,'/pay\\.do.*'
,'网上支付维护','URL','PERM_PAYBYNET','DGGL',2);
--订购基本信息管理
insert into `T_PERMISSIONS`
values('29001','10',0,'(/\\w*)*/(shippay|orderdiscount|order_advance)\\.(do|jsp)\\??.*'
,'订购基本信息管理','URL','PERM_SHIPPAY','DGGL',2);

--询价管理
insert into `T_PERMISSIONS`
values('30','10',0,'/price\\.do\\?method=(sort|list|preAddPrice|addNewInquiry|quicksearch|advanceSearch|deletefromaAvancesearch|deletefromQuicksearch|deleteOne|showProducts|showInquiry|updateState).*'
,'询价管理','URL','PERM_XJGL','XJGL',2);
insert into `T_PERMISSIONS`
values('30001','10',0,'/price\\.do\\?method=(deletefromlist|updateInquiryTheBack|updateInquiryTheFront|updateInquiryTheBackList|updateInquiryTheFrontList|showInquirylist).*'
,'询价管理','URL','PERM_XJGL','XJGL',9);

--企业设置
insert into `T_PERMISSIONS`
values('31','13',0,'/(userManage|userLog|serviceSetting|rizmind|seticp|spacereport|mailmoduleset|smsmoduleset|yidaba)\\.do\\??.*'
,'企业设置','URL','PERM_QYSZ','QYSZ',2);

--竞价通推广(JJTTG)
insert into `T_PERMISSIONS`
values('32','11',0,'/sitespread_bpis\\.do\\??.*'
,'竞价通推广','URL','PERM_JJTTG','JJTTG',2);
--一大把推广(YDBTG)
insert into `T_PERMISSIONS`
values('33','11',0,'/engine\\.do\\??method=getYidabaString.*'
,'一大把推广','URL','PERM_YDBTG','YDBTG',2);
--其他推广(QTTG)
insert into `T_PERMISSIONS`
values('34','11',0,'/sitespread_otherpromotions\\.do\\??.*'
,'其他推广','URL','PERM_QTTG','QTTG',2);
--搜索引擎优化(SSYQYH)
insert into `T_PERMISSIONS`
values('35','11',0,'/engine\\.do\\??method=getEngineList.*'
,'搜索引擎优化','URL','PERM_SSYQYH','SSYQYH',2);
--推广统计(TGTJ)
insert into `T_PERMISSIONS`
values('36','11',0,'/(VisitAmount|VisitRefer|VisitZone|BrowseModule)\\.do\\??.*'
,'推广统计','URL','PERM_TGTJ','TGTJ',2);
--数商邮件(SSYJ)（邮件推广）
insert into `T_PERMISSIONS`
values('37','11',0,'(/\\w+)*/extend_mail\\.(do|jsp)\\??.*'
,'数商邮件','URL','PERM_SSYJ','SSYJ',2);
insert into `T_PERMISSIONS`
values('37000','11',0,'/mail\\.do\\?method=(cancel|preWriteMail|delete|saveOrSend|getMailHistoryList|doViewMailHistory|doDeleteMailHistorys|getMailDraftList)&?.*'
,'邮件推广','URL','PERM_SSYJ','SSYJ',9);
insert into `T_PERMISSIONS`
values('37001','11',0,'/mail\\.do\\?method=(doDeleteMailDrafts|doMailTemplateupdate|doMailTemplateDelete|doMailTemplateViewByID|doPublicMailTemplateTypeView)&?.*'
,'邮件推广','URL','PERM_SSYJ','SSYJ',9);
insert into `T_PERMISSIONS`
values('37002','11',0,'/mail\\.do\\?method=(doEditMailDraft|preUpdateMailLocalConfig|updateMailLocalConfig|doMailTemplateView|doPublicMailTemplateView|doPublicMailTemplateViewByName)&?.*'
,'邮件推广','URL','PERM_SSYJ','SSYJ',9);
insert into `T_PERMISSIONS`
values('37003','11',0,'/(mailImport|preMailImport)\\.do.*'
,'邮件推广','URL','PERM_SSYJ','SSYJ',9);

--数商短信(SSDX) （短信推广）
insert into `T_PERMISSIONS`
values('38','11',0,'/sms\\.do\\?method=(openSmsIndex|viewSmsEdit|saveAndSend|doEditSmsDraft|getSmsHistoryList|getSmsDraftList|doViewSmsHistory|doDeleteSmsHistorys)&?.*'
,'短信推广','URL','PERM_SSDX','SSDX',2);
insert into `T_PERMISSIONS`
values('38000','11',0,'/sms\\.do\\?method=(doPublicSmsTemplateView|doPublicSmsTemplateViewByName|doSmsTemplateView|doSmsTemplateViewByID|doSmsTemplateEditByID|doSmsTemplateupdate|doSmsTemplateDelete)&?.*'
,'短信推广','URL','PERM_SSDX','SSDX',9);
insert into `T_PERMISSIONS`
values('38001','11',0,'/smsOperation\\.do\\?method=(getSmsPrepaidList|saveSmsServiceAndPrepaid|preSmsService)&?.*'
,'短信推广','URL','PERM_SSDX','SSDX',9);
insert into `T_PERMISSIONS`
values('38002','11',0,'/smsImport\\.do\\?method=(smsImport|openSmsImport)&?.*'
,'短信推广','URL','PERM_SSDX','SSDX',9);

--客户管理(KHGL)
insert into `T_PERMISSIONS`
values('39','12',0,'/customer\\.do\\?method=(customerIndex|preEditCustomer|preEditFollowup|editFollowup|editCustomer|preSearchPotential|preSearchIntent|preAdvanceSearchIntent)&?.*'
,'客户管理','URL','PERM_KHGL','KHGL',2);
insert into `T_PERMISSIONS`
values('39000','12',0,'/customer\\.do\\?method=(preSearchDeal|preAdvanceSearchDeal|searchPotential|searchIntent|searchDeal|deleteCustomer|deleteCustomerBatch|preMergeCustomers)&?.*'
,'客户管理','URL','PERM_KHGL','KHGL',9);
insert into `T_PERMISSIONS`
values('39001','12',0,'/customer\\.do\\?method=(mergeCustomer|viewCustomer|viewCustomerFollowup|assignCustomers|rankCustomers|releaseCustomers|preConfigSource|configSource|preImportCustomer)&?.*'
,'客户管理','URL','PERM_KHGL','KHGL',9);
insert into `T_PERMISSIONS`
values('39003','12',0,'/customer\\.do\\?method=(preExportCustomer|exportCustomer|preCustomerChoose|searchCustomerChoose)&?.*'
,'客户管理','URL','PERM_KHGL','KHGL',9);
insert into `T_PERMISSIONS`
values('39004','12',0,'(/\\w+)*/(preCustomerImport|customerImport|customerCareImport|viewCustomerCareImport|customer_export_download)\\.(do|jsp).*','客户管理','URL','PERM_KHGL','KHGL',9);

--客户分配(KHGL)
insert into `T_PERMISSIONS`
values('61','12',0,'/customer\\.do\\?method=(preSearchWaitAssign|searchWaitAssign)&?.*'
,'客户分配','URL','PERM_CUSTORMER','KHGL',2);


--客户关怀(KHGH)
insert into `T_PERMISSIONS`
values('40','12',0,'/customerCare\\.do\\??.*'
,'客户关怀','URL','PERM_KHGH','KHGH',2);
--会员管理(HYGL)
insert into `T_PERMISSIONS`
values('41','12',0,'/member\\.do\\??.*'
,'会员管理','URL','PERM_HYGL','HYGL',2);
--客户统计(KHTJ)
insert into `T_PERMISSIONS`
values('42','12',0,'/customer\\.do\\?method=(viewCustomerGrowth|viewCustomerGrowthPhoto|viewCustomerGrowthPic|viewCustomerSource|viewCustomerSourcePhoto|viewCustomerSourcePic|viewBuyCycle).*'
,'客户统计','URL','PERM_KHTJ','KHTJ',2);
--销售业绩统计(KHTJ)
insert into `T_PERMISSIONS`
values('62','12',0,'/customer\\.do\\?method=(viewSalesPerformance).*'
,'销售业绩统计','URL','PERM_XSYJTJ','KHTJ',2);
--RSS
insert into `T_PERMISSIONS`
values('43','10',0,'/(rsscategory|rss_content_list)\\.do\\??.*'
,'RSS','URL','PERM_RSS','RSS',2);

--------------------------- 9ji----------------------------
----图片库
insert into `T_PERMISSIONS`
values('500','10',0,'(/\\w+)*/(imageRepository|view_pic)\\.(do|jsp).*'
,'图片库','URL','PERM_NRGL,PERM_GGGL,PERM_SPGL,PERM_SSYJ,PERM_QYDT','PIC',9);
----FLASH库
insert into `T_PERMISSIONS`
values('501','10',0,'(/\\w+)*/(flashRepository|view_flash)\\.(do|jsp).*'
,'FLASH库','URL','PERM_NRGL,PERM_GGGL,PERM_SPGL,PERM_SSYJ','FLASH',9);
---选择商品分类
insert into `T_PERMISSIONS`
values('502','10',0,'(/\\w+)*/product_treepage\\.jsp.*'
,'FLASH库','URL','PERM_NRGL,PERM_GGGL','PRODUCTCATEGORY',9);
---- 选择商品
insert into `T_PERMISSIONS`
values('503','10',0,'/product\\.do\\?method=showProducts.*'
,'FLASH库','URL','PERM_NRGL,PERM_GGGL','PRODUCT_',9);
--网络推广首页
insert into `T_PERMISSIONS`
values('504','11',0,'/WebStats\\.do\\?method=queryIndex.*'
,'网络推广','URL','PERM_SSDX,PERM_SSYJ,PERM_TGTJ,PERM_SSYQYH,PERM_YDBTG,PERM_JJTTG,PERM_QTTG.','WLTG',9);
--客户管理首页
insert into `T_PERMISSIONS`
values('505','12',0,'/customer\\.do\\?method=customerIndex.*'
,'客户管理','URL','PERM_KHGL,PERM_KHGH,PERM_HYGL,PERM_KHTJ','KHTJ',9);
commit;

--------------------- FUN ----------------------
--内容FUN
--com.sitechasia.webshop.navigation.service.impl.NavigationManageServiceImpl
insert into `T_PERMISSIONS`
values('50','23',0,'com\\.sitechasia\\.webshop\\.navigation\\.service\\.impl\\.NavigationManageServiceImpl\\.doSaveColumn'
,'栏目增加','FUN','PERM_ITEM_ADD','NRGL',3);
insert into `T_PERMISSIONS`
values('51','23',0,'com\\.sitechasia\\.webshop\\.navigation\\.service\\.impl\\.NavigationManageServiceImpl\\.updateColumn'
,'栏目修改','FUN','PERM_ITEM_UPDATE','NRGL',3);
insert into `T_PERMISSIONS`
values('52','23',0,'com\\.sitechasia\\.webshop\\.navigation\\.service\\.impl\\.NavigationManageServiceImpl\\.deleteColumn'
,'栏目删除','FUN','PERM_ITEM_DELETE','NRGL',3);
insert into `T_PERMISSIONS`
values('53','23',0,'com\\.sitechasia\\.webshop\\.specificationcontent\\.service\\.impl\\.SpecifyContentManageServiceImpl\\.updateSpecifyContent'
,'说明页内容修改','FUN','PERM_SPCONTENT_UPDATE','NRGL',3);
insert into `T_PERMISSIONS`
values('54','23',0,'com\\.sitechasia\\.webshop\\.picturecontent\\.service\\.impl\\.PicturecContentManageServiceImpl\\.addPictureContent'
,'图片内容增加','FUN','PERM_PICTURE_ADD','NRGL',3);
insert into `T_PERMISSIONS`
values('55','23',0,'com\\.sitechasia\\.webshop\\.picturecontent\\.service\\.impl\\.PicturecContentManageServiceImpl\\.deletePictureContents'
,'图片内容删除','FUN','PERM_PICTURE_DELETE','NRGL',3);
insert into `T_PERMISSIONS`
values('56','23',0,'com\\.sitechasia\\.webshop\\.picturecontent\\.service\\.impl\\.PicturecContentManageServiceImpl\\.updatePictureContent'
,'图片内容修改','FUN','PERM_PICTURE_UPDATE','NRGL',3);
insert into `T_PERMISSIONS`
values('57','23',0,'com\\.sitechasia\\.webshop\\.commoncontent\\.service\\.impl\\.CommonContentManageServiceImpl\\.addContent'
,'通用内容增加','FUN','PERM_CONTENT_ADD','NRGL',3);
insert into `T_PERMISSIONS`
values('58','23',0,'com\\.sitechasia\\.webshop\\.commoncontent\\.service\\.impl\\.CommonContentManageServiceImpl\\.deleteContents'
,'通用内容删除','FUN','PERM_CONTENT_DELETE','NRGL',3);
insert into `T_PERMISSIONS`
values('59','23',0,'com\\.sitechasia\\.webshop\\.commoncontent\\.service\\.impl\\.CommonContentManageServiceImpl\\.updateContent'
,'通用内容修改','FUN','PERM_CONTENT_UPDATE','NRGL',3);


--资讯FUN


--客户管理模块
insert into `T_PERMISSIONS`
values('90','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.exportCustomers'
,'导出','FUN','PERM_CLIENT_EXPORT','KHGL',3);
insert into `T_PERMISSIONS`
values('91','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.deleteCustomerByID'
,'删除','FUN','PERM_CLIENT_DELETE','KHGL',3);
insert into `T_PERMISSIONS`
values('92','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.assaignCustomer'
,'分配','FUN','PERM_CLIENT_DISTRIBUTE','KHGL',3);
insert into `T_PERMISSIONS`
values('95','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.updateCustomer'
,'修改','FUN','PERM_CLIENT_UPDATE','KHGL',3);
insert into `T_PERMISSIONS`
values('96','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.releaseCustomers'
,'抛弃','FUN','PERM_CLIENT_DISCARD','KHGL',3);
insert into `T_PERMISSIONS`
values('97','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.importCustomers'
,'导入','FUN','PERM_CLIENT_IMPORT','KHGL',3);
insert into `T_PERMISSIONS`
values('98','39',0,'com\\.sitechasia\\.webshop\\.customermanage\\.service\\.impl\\. CustomerServiceImpl\\.saveCustomer'
,'添加','FUN','PERM_CLIENT_SAVE','KHGL',3);

--招聘模块
insert into `T_PERMISSIONS`
values('200','20',0,'com\\.sitechasia\\.webshop\\.employ\\.service\\.impl\\.ResumeServiceImpl\\.(deleteResume|deleteResume)'
,'简历删除','FUN','PERM_RESUME_DELETE','ZPGL',3);
insert into `T_PERMISSIONS`
values('201','20',0,'com\\.sitechasia\\.webshop\\.employ\\.service\\.impl\\.ResumeServiceImpl\\.(setResumeHasEmploied|setResumeHasHived|setResumeUnParse|setProcessContext|setForwordAddress)'
,'简历处理','FUN','PERM_RESUME_PROCESS','ZPGL',3);
insert into `T_PERMISSIONS`
values('202','20',0,'com\\.sitechasia\\.webshop\\.employ\\.service\\.impl\\.OfferServiceImpl\\.addOffer'
,'职位新增','FUN','PERM_OFFER_ADD','ZPGL',3);
insert into `T_PERMISSIONS`
values('203','20',0,'com\\.sitechasia\\.webshop\\.employ\\.service\\.impl\\.OfferServiceImpl\\.(updateOffer|publishOffer|publishOffers|unpublishOffer|unPublishOffers)'
,'职位编辑','FUN','PERM_OFFER_UPDATE','ZPGL',3);
insert into `T_PERMISSIONS`
values('204','20',0,'com\\.sitechasia\\.webshop\\.employ\\.service\\.impl\\.OfferServiceImpl\\.(deleteOffer|deleteOffers)'
,'职位删除','FUN','PERM_OFFER_DELETE','ZPGL',3);

--下载中心
insert into `T_PERMISSIONS`
values('210','28',0,'com\\.sitechasia\\.webshop\\.download\\.service\\.impl\\.DownloadCategoryServiceImpl\\.addDownloadCategory'
,'下载分类新增','FUN','PERM_DOWNLOADCATEGORY_ADD','XZZX',3);
insert into `T_PERMISSIONS`
values('211','28',0,'com\\.sitechasia\\.webshop\\.download\\.service\\.impl\\.DownloadCategoryServiceImpl\\.updateDownloadCategory'
,'下载分类修改','FUN','PERM_DOWNLOADCATEGORY_UPDATE','XZZX',3);
insert into `T_PERMISSIONS`
values('212','28',0,'com\\.sitechasia\\.webshop\\.download\\.service\\.impl\\.DownloadCategoryServiceImpl\\.deleteDownloadCategory'
,'下载分类删除','FUN','PERM_DOWNLOADCATEGORY_DELETE','XZZX',3);
insert into `T_PERMISSIONS`
values('213','28',0,'com\\.sitechasia\\.webshop\\.download\\.service\\.impl\\.DownloadFileServiceImpl\\.doAddFile'
,'下载文件新增','FUN','PERM_DOWNLOADFILE_ADD','XZZX',3);
insert into `T_PERMISSIONS`
values('214','28',0,'com\\.sitechasia\\.webshop\\.download\\.service\\.impl\\.DownloadFileServiceImpl\\.doEditFile'
,'下载文件编辑','FUN','PERM_DOWNLOADFILE_UPDATE','XZZX',3);
insert into `T_PERMISSIONS`
values('215','28',0,'com\\.sitechasia\\.webshop\\.download\\.service\\.impl\\.DownloadFileServiceImpl\\.(doDeleteFileByFileId|doDeleteFilesByFileIds)'
,'下载文件删除','FUN','PERM_DOWNLOADFILE_DELETE','XZZX',3);

--问卷模块
insert into `T_PERMISSIONS`
values('220','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.(addQuestionnaire|addQuestionnaireForCopy)'
,'问卷新增','FUN','PERM_QUESTIONNAIRE_ADD','WSDC',3);
insert into `T_PERMISSIONS`
values('221','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.updateQuestionnaire'
,'问卷修改','FUN','PERM_QUESTIONNAIRE_UPDATE','WSDC',3);
insert into `T_PERMISSIONS`
values('222','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.(deleteQuestionnaireById|deleteQuestionnaireByIds)'
,'问卷删除','FUN','PERM_QUESTIONNAIRE_DELETE','WSDC',3);
insert into `T_PERMISSIONS`
values('223','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.(updateQuestionnaireStatusForStopped|updateQuestionnaireStatusForRecover)'
,'问卷中止/恢复','FUN','PERM_QUESTIONNAIRE_STOPPEDORRECOVER','WSDC',3);
insert into `T_PERMISSIONS`
values('224','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.(getQuestionnaireTotalStat|getQuestionnaireStat)'
,'问卷查看结果','FUN','PERM_QUESTIONNAIRE_VIEWRESULT','WSDC',3);
insert into `T_PERMISSIONS`
values('225','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.addQuestion'
,'问卷问题增加','FUN','PERM_QUESTION_ADD','WSDC',3);
insert into `T_PERMISSIONS`
values('226','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.updateQuestion'
,'问卷问题修改','FUN','PERM_QUESTION_UPDATE','WSDC',3);
insert into `T_PERMISSIONS`
values('227','27',0,'com\\.sitechasia\\.webshop\\.question\\.service\\.impl\\.QuestionnaireServiceImpl\\.deleteQuestionById'
,'问卷问题删除','FUN','PERM_QUESTION_DELETE','WSDC',3);

-- 广告 添加，修改，删除
insert  into T_PERMISSIONS values
('130','24',0,'com\\.sitechasia\\.webshop\\.advertisemanage\\.service\\.impl\\.AdvertiseManageServiceImpl\\.addAdvertise','添加广告','FUN','PERM_AD_ADD','GGGL',3);
insert  into `T_PERMISSIONS`
values('131','24',0,'com\\.sitechasia\\.webshop\\.advertisemanage\\.service\\.impl\\.AdvertiseManageServiceImpl\\.updataAdvertise','更新广告','FUN','PERM_AD_UPDATA','GGGL',3);
insert  into `T_PERMISSIONS`
values('132','24',0,'com\\.sitechasia\\.webshop\\.advertisemanage\\.service\\.impl\\.AdvertiseManageServiceImpl\\.(deleteAdvertise|deleteAdvertises)','删除广告','FUN','PERM_AD_DELETE','GGGL',3);

--商品FUN
insert into `T_PERMISSIONS`
values('150','26',0,'com\\.sitechasia\\.webshop\\.product\\.service\\.impl\\.PmcategoryServiceImpl\\.doDelateCategoryById'
,'商品分类删除','FUN','PERM_PRODUCT_CATEGORYDELETE','SPGL',3);
insert into `T_PERMISSIONS`
values('151','26',0,'com\\.sitechasia\\.webshop\\.product\\.service\\.impl\\.PmcategoryServiceImpl\\.doSaveOrUpdateCategory'
,'商品分类编辑','FUN','PERM_PRODUCT_CATEGORYEDIT','SPGL',3);
insert into `T_PERMISSIONS`
values('152','26',0,'com\\.sitechasia\\.webshop\\.product\\.service\\.impl\\.PmcategoryServiceImpl\\.doSaveOrUpdateCategory'
,'商品分类新增','FUN','PERM_PRODUCT_CATEGORYADD','SPGL',3);
insert into `T_PERMISSIONS`
values('153','26',0,'com\\.sitechasia\\.webshop\\.product\\.service\\.impl\\.ProductServiceImpl\\.deleteProduct'
,'商品删除','FUN','PERM_PRODUCT_DELETE','SPGL',3);
insert into `T_PERMISSIONS`
values('154','26',0,'com\\.sitechasia\\.webshop\\.product\\.service\\.impl\\.ProductServiceImpl\\.updateProduct'
,'商品编辑','FUN','PERM_PRODUCT_EDIT','SPGL',3);
insert into `T_PERMISSIONS`
values('155','26',0,'com\\.sitechasia\\.webshop\\.product\\.service\\.impl\\.ProductServiceImpl\\.addProduct'
,'商品新增','FUN','PERM_PRODUCT_ADD','SPGL',3);



--订单FUN
insert into `T_PERMISSIONS`
values('170','29',0,'com\\.sitechasia\\.webshop\\.order\\.service\\.impl\\.OrderServiceImpl\\.doDeleteByOrderid'
,'订单删除','FUN','PERM_ORDER_DELETE','DGGL',3);
insert into `T_PERMISSIONS`
values('171','29',0,'com\\.sitechasia\\.webshop\\.order\\.service\\.impl\\.OrderServiceImpl\\.doSaveOrderAll'
,'订单录入','FUN','PERM_ORDER_IMPORT','DGGL',3);
insert into `T_PERMISSIONS`
values('172','29',0,'com\\.sitechasia\\.webshop\\.order\\.service\\.impl\\.OrderServiceImpl\\.(doSaveOrder|doSaveOrderAll)'
,'订单处理','FUN','PERM_ORDER_EDIT','DGGL',3);
insert into `T_PERMISSIONS`
values('173','29',0,'/order\\.do\\?method=print.*'
,'订单打印','URL','PERM_ORDER_PRINT','DGGL',3);
insert into `T_PERMISSIONS`
values('174','29',0,'/order\\.do\\?method=exportExcel.*'
,'订单导出','URL','PERM_ORDER_EXPORT','DGGL',3);

--询价FUN
insert into `T_PERMISSIONS`
values('185','30',0,'com\\.sitechasia\\.webshop\\.price\\.service\\.impl\\.TInquirysheetServiceImpl\\.doDeleteByInquiryid'
,'询价单删除','FUN','PERM_PRICE_DELETE','XJGL',3);
insert into `T_PERMISSIONS`
values('186','30',0,'com\\.sitechasia\\.webshop\\.price\\.service\\.impl\\.TInquirysheetServiceImpl\\.addInquiry'
,'询价单录入','FUN','PERM_PRICE_IMPORT','XJGL',3);
insert into `T_PERMISSIONS`
values('187','30',0,'com\\.sitechasia\\.webshop\\.price\\.service\\.impl\\.TInquirysheetServiceImpl\\.updateInquiry'
,'询价单处理','FUN','PERM_PRICE_EDIT','XJGL',3);
insert into `T_PERMISSIONS`
values('188','30',0,'/price\\.do\\?method=print.*'
,'询价单打印','URL','PERM_PRICE_PRINT','XJGL',3);

-----------------------------------------
----- 角色
-----------------------------------------
INSERT INTO T_ROLES
VALUES('content_admin',0,'网站内容管理员','网站内容管理员');

INSERT INTO T_ROLES
VALUES('site_generalize_admin',0,'网站推广管理员','网站推广管理员');
INSERT INTO T_ROLES
VALUES('enterprise_crm_admin',0,'企业客户管理员','企业客户管理员');
INSERT INTO T_ROLES
VALUES('net_sell_admin',0,'网络营销管理员','网络营销管理员');

COMMIT;

-------------------------------------------
------ 角色权限关系
-------------------------------------------
----网站内容管理员
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','20',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','21',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','22',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','23',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','24',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','25',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','26',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','27',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','28',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','29',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','29001',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','29002',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','30',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('content_admin','43',0);
--网站推广管理员
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','32',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','33',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','34',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','35',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','36',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','37',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('site_generalize_admin','38',0);
--企业客户管理员
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('enterprise_crm_admin','39',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('enterprise_crm_admin','42',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('enterprise_crm_admin','61',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('enterprise_crm_admin','62',0);

--网络营销管理员
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('net_sell_admin','40',0);
INSERT INTO `T_ROLEPERMISSIONS`
VALUES('net_sell_admin','41',0);

COMMIT;
-------------------------------------
------ 用户
-------------------------------------
INSERT INTO T_USERS
VALUES('1',0,'webmaster@myce.net.cn','1','2007-07-20'
,'2007-07-20','0','FIRST_MAN','FIRST_MAN');
INSERT INTO T_USERS
VALUES('2',0,'admin@myce.net.cn','1','2007-07-20'
,'2007-07-20','0','ADMIN_TEST','ADMIN_TEST');

-------------------------------------
------ 用户角色
-------------------------------------
INSERT INTO `T_USERROLES`
SELECT '2',R.ID,0
FROM T_ROLES R;

-------------------------------------
------ 用户权限
-------------------------------------
INSERT INTO `T_USERPERMISSIONS`
VALUES('1','31',0);
INSERT INTO `T_USERPERMISSIONS`
VALUES('1','3',0);
INSERT INTO `T_USERPERMISSIONS`
SELECT '2',P.ID,0 FROM T_PERMISSIONS P ;

COMMIT;


