

```sql

CREATE TABLE t_agn_unfinished_task (
  unfinished_task_id varchar(64)  NOT NULL COMMENT '未决任务主键ID',
  task_name varchar(255)  NOT NULL COMMENT '任务名称',
  task_code varchar(32) comment '任务编码',
  task_status varchar(2) DEFAULT NULL COMMENT '任务状态',
  standard_prescription int DEFAULT NULL COMMENT '标准时效',
  exception_prescription int DEFAULT NULL COMMENT '异常时效',
  average_prescription int DEFAULT NULL COMMENT '平均时效',                              
  used_time int NOT NULL COMMENT '已耗时',
  assignee_name varchar(100)  NOT NULL COMMENT '任务处理人姓名',
  assignee_code varchar(32)  NOT NULL COMMENT '任务处理人账号',
  is_valid varchar(2)  DEFAULT NULL COMMENT '是否有效',
  creator_name               varchar(255) COMMENT '创建人姓名',
  creator                    varchar(32) COMMENT '创建人',
  modifier_name              varchar(255) COMMENT '修改人姓名',
  modifier                   varchar(32) COMMENT '修改人',
  tenant_code varchar(50)  DEFAULT NULL COMMENT '租户',
  gmt_create           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  gmt_modified         timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  gmt_delete           timestamp DEFAULT CURRENT_TIMESTAMP comment '删除时间',
  claim_tag_code		varchar(2) comment '案件分类编码',
  aging_type_cd varchar(2) comment '时效类型, 如：整案查勘、人伤查勘、车物查勘等',
  flag varchar(32) comment '标识位',
  PRIMARY KEY (unfinished_task_id)
) comment '未决任务表';

CREATE TABLE t_agn_delay_claim (
  delay_claim_id varchar(64) NOT NULL COMMENT '延迟案件主键ID',
  claim_apply_no varchar(64) NOT NULL COMMENT '索赔申请号',
  notify_dt datetime NULL DEFAULT NULL COMMENT '报案时间',
  is_common_indemnity varchar(2) DEFAULT NULL COMMENT '是否通赔',
  delay_step_name varchar(255) DEFAULT NULL COMMENT '滞留环节名称',
  delay_step_code varchar(32) DEFAULT NULL COMMENT '滞留环节代码',
  used_time int NOT NULL COMMENT '已耗时, 单位: 秒',
  last_track_time timestamp NULL DEFAULT NULL COMMENT '最近跟踪时间',
  claim_tracker_name varchar(255) DEFAULT NULL COMMENT '索赔跟踪人姓名',
  claim_tracker_code varchar(32) DEFAULT NULL COMMENT '索赔跟踪人代码',
  unsettlement_tag_code varchar(32) DEFAULT NULL COMMENT '未决标签代码',
  unsettlement_tag_name varchar(255) DEFAULT NULL COMMENT '未决标签名称',
  insured_org_name varchar(255)  NOT NULL COMMENT '承保机构名称',
  insured_org_code varchar(32)  NOT NULL COMMENT '承保机构代码',
  is_valid varchar(2)  DEFAULT NULL COMMENT '是否有效',
  creator_name               varchar(255) COMMENT '创建人姓名',
  creator                    varchar(32) COMMENT '创建人',
  modifier_name              varchar(255) COMMENT '修改人姓名',
  modifier                   varchar(32) COMMENT '修改人',
  tenant_code varchar(50)  DEFAULT NULL COMMENT '租户',
  gmt_create           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  gmt_modified         timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  gmt_delete           timestamp DEFAULT CURRENT_TIMESTAMP comment '删除时间',
  flag varchar(32) comment '标识位',
  PRIMARY KEY (delay_claim_id)
) comment '延迟案件';

create table t_agn_delay_claim_remind
(
   delay_claim_remind_id varchar(32) comment '服务单催办记录主键',
   delay_claim_code     varchar(32) comment '延迟案件编码',
   plan_id varchar(32) not null comment '与客户接触域对接使用',
   delay_claim_remind_type_cd varchar(2) comment '服务单提醒方式, 钉钉、短信等',
   delay_claim_remind_content varchar(1024) comment '服务单提醒内容',
   delay_claim_remind_result_cd varchar(2) comment '服务单提醒结果',
   creator_name               varchar(255) COMMENT '创建人姓名',
   creator                    varchar(32) COMMENT '创建人',
   modifier_name              varchar(255) COMMENT '修改人姓名',
   modifier                   varchar(32) COMMENT '修改人',
   gmt_create           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   gmt_modified         timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   gmt_delete           timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '删除时间',
   is_valid               tinyint comment '有效标识，0: 数据无效, 1: 数据有效',
   tenant_code           varchar(32) comment '租户'，
   flag varchar(32) comment '标识位',
 primary key (service_order_remind_id)
) comment '延迟案件催办';

CREATE TABLE t_agn_track_company (
  track_company_id varchar(64) comment '未决跟踪涉及机构主键',
  track_company_name varchar(255) NOT NULL COMMENT '涉及机构名称',
  track_company_code varchar(32) NOT NULL COMMENT '涉及机构代码',
  to_be_clear_nums int DEFAULT NULL COMMENT '待清理数量',
  to_be_track_nums int DEFAULT NULL COMMENT '已跟踪数量',
  cleared_nums int DEFAULT NULL COMMENT '已清理数量',
  to_be_track_amount decimal(18,6) DEFAULT NULL COMMENT '待清理金额',
  cleared_amount decimal(18,6) DEFAULT NULL COMMENT '已清理金额',
  is_valid varchar(2)  DEFAULT NULL COMMENT '是否有效',
  creator_name               varchar(255) COMMENT '创建人姓名',
  creator                    varchar(32) COMMENT '创建人',
  modifier_name              varchar(255) COMMENT '修改人姓名',
  modifier                   varchar(32) COMMENT '修改人',
  tenant_code varchar(50)  DEFAULT NULL COMMENT '租户',
  gmt_create           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  gmt_modified         timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  gmt_delete           timestamp DEFAULT CURRENT_TIMESTAMP comment '删除时间',
  flag varchar(32) comment '标识位',
  PRIMARY KEY (track_company_id)
) comment '未决跟踪涉及机构';

CREATE TABLE t_agn_track_topic_approve (
  track_topic_approve_id varchar(32) comment '未决跟踪主题审核主键',
  track_topic_approve_code varchar(32) comment '未决跟踪主题审核编码',
  track_topic_approve_result_cd varchar(2) NOT NULL COMMENT '审核结论',
  track_topic_approve_remark varchar(1024) DEFAULT NULL COMMENT '审核说明',
  is_valid varchar(2)  DEFAULT NULL COMMENT '是否有效',
  creator_name               varchar(255) COMMENT '创建人姓名',
  creator                    varchar(32) COMMENT '创建人',
  modifier_name              varchar(255) COMMENT '修改人姓名',
  modifier                   varchar(32) COMMENT '修改人',
  tenant_code varchar(50)  DEFAULT NULL COMMENT '租户',
  gmt_create           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  gmt_modified         timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  gmt_delete           timestamp DEFAULT CURRENT_TIMESTAMP comment '删除时间',
  unsettlement_track_topic_code varchar(32)  NOT NULL COMMENT '未决跟踪主题编码',
  flag varchar(32) comment '标识位',
  track_status_cd varchar(2) comment '未决跟踪主题状态, 0:暂存, 1:待审核, 2:审核通过, 3:审核拒绝， 4:执行中, 5:关闭'
  PRIMARY KEY (track_topic_approve)
) comment '未决跟踪主题审核表';

CREATE TABLE t_agn_unsettlement_track_topic (
  unsettlement_track_topic_id varchar(64)  NOT NULL COMMENT '未决跟踪主题主键ID',
  unsettlement_track_topic_code varchar(32)  NOT NULL COMMENT '未决跟踪主题编码',
  unsettlement_track_topic_name varchar(255)  NOT NULL COMMENT '未决跟踪主题名称',
  serial_number bigint(32) NOT NULL COMMENT '编号',
  track_start_dt datetime DEFAULT NULL COMMENT '跟踪主题开始时间',
  track_end_dt datetime DEFAULT NULL COMMENT '跟踪主题结束时间',
  relation_claims int(10) DEFAULT NULL COMMENT '跟踪涉及案件数',
  relation_amount decimal(18,6) DEFAULT NULL COMMENT '跟踪涉及未决金额',
  track_status_cd varchar(2) DEFAULT NULL COMMENT '未决跟踪状态',
  dest_clear_percent decimal(10,5) DEFAULT NULL COMMENT '目标清理百分比',
  dest_clear_nums int(10) DEFAULT NULL COMMENT '目标清理数量',
  dest_clear_remark varchar(1024) DEFAULT NULL COMMENT '目标清理说明',
  notify_start_dt datetime DEFAULT NULL COMMENT '报案起始日期',
  notify_end_dt datetime DEFAULT NULL COMMENT '报案结束日期',
  risk_start_dt datetime DEFAULT NULL COMMENT '出险起始日期',
  risk_end_dt datetime DEFAULT NULL COMMENT '出险结束日期',
  insured_org_code varchar(64)  NOT NULL COMMENT '承保机构代码',
  insured_org_name varchar(255)  NOT NULL COMMENT '承保机构名称',
  track_claim_tags json COMMENT '未决跟踪主题案件标签',
  track_unsettlement_tags json COMMENT '未决跟踪主题未决标签',
  claimitem_types json COMMENT '索赔项目列表',
  is_valid varchar(2)  DEFAULT NULL COMMENT '是否有效',
  creator_name               varchar(255) COMMENT '创建人姓名',
  creator                    varchar(32) COMMENT '创建人',
  modifier_name              varchar(255) COMMENT '修改人姓名',
  modifier                   varchar(32) COMMENT '修改人',
  tenant_code varchar(50)  DEFAULT NULL COMMENT '租户',
  gmt_create           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  gmt_modified         timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  gmt_delete           timestamp DEFAULT CURRENT_TIMESTAMP comment '删除时间',
  lead_track_comcode			varchar(32) comment '主题跟踪发起机构代码',
  lead_track_comname			varchar(32) comment '主题跟踪发起机构名称',
  flag varchar(32) comment '标识位',
  PRIMARY KEY (unsettlement_track_topic_id)
) comment '未决跟踪主题表';

```

