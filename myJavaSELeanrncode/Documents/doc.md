```sql

drop table if exists t_agn_collection_template;
create table t_agn_collection_template
(
    collection_template_id     VARCHAR(64) NOT NULL COMMENT '主键ID',
    collection_template_code   VARCHAR(32) COMMENT '模板唯一标识，用于关联',
    template_name              varchar(255) COMMENT '模板名称',
    template_type              varchar(32) COMMENT '模板种类：整案单证，损失单证',
    accept_channel_code        varchar(32) COMMENT '受理渠道代码',
    accept_channel_name        varchar(255) COMMENT '受理渠道名称',
    customer_segmentation_code varchar(32) COMMENT '客户分群代码',
    customer_segmentation_name varchar(255) COMMENT '客户分群名称',
    product_type_code          varchar(32) COMMENT '产品分类代码',
    product_type_name          varchar(255) COMMENT '产品分类名称',
    template_status            varchar(2) COMMENT '模板状态',
    template_remark            varchar(1024) COMMENT '模板说明',
    quoted                     varchar(2) COMMENT '是否被引用：0代表未引用，1代表被引用',
    is_instance                varchar(2) COMMENT '是否是实例模板，用底板和案件進行匹配：0代表底板，1代表实例模板',
    complete_condition         varchar(2000) COMMENT '齐全条件',
    claim_item_no              VARCHAR(64) COMMENT '索赔项目编号',
    instance_no                VARCHAR(64) COMMENT '关联号：如履约域索赔号',
--collection_rule            varchar(2000) COMMENT '收单规则JSON，模板基础信息:模板名称，模板种类，产品分类，受理渠道，客群分类，出险原因，索赔项目类型，模板说明等',
    accident_reason            varchar(2) COMMENT '出险原因，事故性质',
    human_state                varchar(64)  COMMENT '出险人状态',
    claimed_item_type          varchar(32) COMMENT '损失单证索赔项目类型',
    claimed_item_extend_info   varchar(1024) COMMENT '损失单证索赔项目类型扩展',
    status                     varchar(32) COMMENT '状态，用來表示是否被禁用，0启动，1禁用',
    creator                    varchar(32) COMMENT '创建人',
    modifier                   varchar(32) COMMENT '修改人',
    gmt_create                 timestamp COMMENT '创建时间',
    gmt_modified               timestamp COMMENT '修改时间',
    is_valid                   tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code                varchar(32) COMMENT '租户',
    PRIMARY KEY (`id`),
    INDEX                      idx_gmt_modified (gmt_modified)
)ENGINE=InnoDB COMMENT='收单模板';


drop table if exists t_agn_template_dir;
create table t_agn_template_dir
(
    template_dir_id          VARCHAR(64) NOT NULL COMMENT '主键ID',
    template_dir_code        VARCHAR(32) NOT NULL COMMENT '唯一标识，用于关联',
    collection_template_code VARCHAR(32) COMMENT '所在模板',
    document_code            varchar(32) COMMENT '所在单证',
    document_directory_code  varchar(32) COMMENT '所在单证目录',
    -- status                    varchar(32) COMMENT '状态',
    creator                  varchar(32) COMMENT '创建人',
    modifier                 varchar(32) COMMENT '修改人',
    gmt_create               timestamp COMMENT '创建时间',
    gmt_modified             timestamp COMMENT '修改时间',
    is_valid                 tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code              varchar(32) COMMENT '租户',
    PRIMARY KEY (`id`),
    INDEX                    idx_gmt_modified (gmt_modified)
)ENGINE=InnoDB COMMENT='模板目录关系表';



drop table if exists t_agn_document_directory;
create table t_agn_document_directory
(
    document_directory_id    VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_directory_code  varchar(32) COMMENT '目录标识，用于关联',
    parent_id                varchar(64) COMMENT '上级目录ID',
    sort                     varchar(64) COMMENT '顺序位置',
    directory_name           varchar(255) COMMENT '目录名称',
    collection_template_code VARCHAR(32) COMMENT '所在模板',
    parent_directory_code    varchar(32) COMMENT '上级目录编码',
    -- status                varchar(32) COMMENT '状态',
    --is_instance_document_directory varchar(2) COMMENT '是否实例单证目录',
    creator                  varchar(32) COMMENT '创建人',
    modifier                 varchar(32) COMMENT '修改人',
    gmt_create               timestamp COMMENT '创建时间',
    gmt_modified             timestamp COMMENT '修改时间',
    is_valid                 tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code              varchar(32) COMMENT '租户',
    PRIMARY KEY (`id`),
    INDEX                    idx_gmt_modified (gmt_modified)
)ENGINE=InnoDB COMMENT='单证目录';



drop table if exists t_agn_document;
create table t_agn_document
(
    document_id              VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_code            varchar(32) COMMENT '单证唯一标识，用于关联',
    document_directory_code  varchar(32) COMMENT '所在单证目录',
    collection_template_code VARCHAR(32) COMMENT '所在模板',
    claim_item_no            VARCHAR(64) COMMENT '索赔项目编号',
    document_name            varchar(255) COMMENT '单证名称',
    document_type_code       varchar(32) COMMENT '单证种类代码，单证种类如证件，图像，发票等',
    document_type_name       varchar(32) COMMENT '单证种类名称',
    document_feature         varchar(255) COMMENT '单证形式，多个以逗号隔开：原件，复印件，影像件。。。',
    refine_collection        varchar(2) COMMENT '是否需要细化收单列表',
    rcpt_list                varchar(1024) COMMENT '收单列表：身份证，护照等，以逗号隔开',
    rcpt_acquiring_cond      VARCHAR(255) COMMENT '收单完备条件：全选，任选其一，按实收取',
    collection_type          varchar(2) COMMENT '收单类型',
    sort                     varchar(64) COMMENT '顺序位置',
    quoted                   varchar(2) COMMENT '是否被收单模板引用',
    -- is_instance_document varchar(2) COMMENT '是否实例单证',
    org_code                 varchar(32) COMMENT '机构代码',
    org_name                 varchar(255) COMMENT '机构名称',
    document_example         TEXT COMMENT '单证样例：json格式，多个',
    -- status              varchar(32) COMMENT '状态',
    creator                  varchar(32) COMMENT '创建人',
    modifier                 varchar(32) COMMENT '修改人',
    gmt_create               timestamp COMMENT '创建时间',
    gmt_modified             timestamp COMMENT '修改时间',
    is_valid                 tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code              varchar(32) COMMENT '租户',
    PRIMARY KEY (`id`),
    INDEX                    idx_gmt_modified (gmt_modified)
)ENGINE=InnoDB COMMENT='单证';



drop table if exists t_agn_document_rule;
create table t_agn_document_rule
(
    document_rule_id         VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_rule_code       varchar(32) COMMENT '规则唯一标识，用于关联',
    is_manual_authority      varchar(2) COMMENT '是否允许手工置全',
    complete_conditions      varchar(32) COMMENT '齐全条件',
    collection_template_code VARCHAR(32) COMMENT '所在模板',
    document_code            varchar(32) COMMENT '所在单证',
    document_directory_code  varchar(32) COMMENT '所在单证目录',
    rule_content             varchar(2000) COMMENT '规则内容：json格式',
    rule_name                varchar(32) COMMENT '规则名称',
    status                   varchar(32) COMMENT '状态',
    creator                  varchar(32) COMMENT '创建人',
    modifier                 varchar(32) COMMENT '修改人',
    gmt_create               timestamp COMMENT '创建时间',
    gmt_modified             timestamp COMMENT '修改时间',
    is_valid                 tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code              varchar(32) COMMENT '租户',
    PRIMARY KEY (`id`),
    INDEX                    idx_gmt_modified (gmt_modified)
)ENGINE=InnoDB COMMENT='单证规则';


-- drop table if exists t_agn_document_content;
-- create table t_agn_document_content
-- (
--     id                    VARCHAR(64) NOT NULL COMMENT '主键ID',
--     document_id           VARCHAR(64) NOT NULL COMMENT '所在单证ID',
--     code                  varchar(32) COMMENT '单证内容编码',
--     name                  varchar(255) COMMENT '单证内容名称',
--     type                  varchar(2) COMMENT '单证归属类别',
--     document_account_code varchar(32) COMMENT '操作人员账号',
--     document_account_name varchar(255) COMMENT '操作人员名称',
--     status                varchar(32) COMMENT '状态',
--     creator               varchar(32) COMMENT '创建人',
--     modifier              varchar(32) COMMENT '修改人',
--     gmt_create            timestamp COMMENT '创建时间',
--     gmt_modified          timestamp COMMENT '修改时间',
--     is_valid              tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
--     tenant_code           varchar(32) COMMENT '租户',
--     PRIMARY KEY (`id`),
--     INDEX                 idx_gmt_modified (gmt_modified)
-- )ENGINE=InnoDB COMMENT='单证内容';


drop table if exists t_agn_document_proof;
create table t_agn_document_proof
(
    document_proof_id        VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_proof_code      varchar(32) COMMENT '文件唯一标识，用于关联',
    document_code            VARCHAR(32) NOT NULL COMMENT '所在单证',
    document_directory_code  varchar(32) COMMENT '所在单证目录',
    collection_template_code VARCHAR(32) COMMENT '所在模板',
    claim_item_no            VARCHAR(64) COMMENT '索赔项目编号',
    instance_no              VARCHAR(64) COMMENT '关联号：如履约域索赔号',
    -- document_content_id   VARCHAR(64) NOT NULL COMMENT '所在单证内容id',
    -- storage_path          varchar(1024) COMMENT '单证存储路径',
    file_name                varchar(255) COMMENT '单证文件名',
    file_type                VARCHAR(100) COMMENT '单证文件类型',
    is_need                  tinyint(2) COMMENT '是否必收：0: 是, 1: 否',
    file_size                bigint(19) COMMENT '单证文件大小',
    --  upload_time           DATETIME COMMENT '单证上传时间',
    is_qualified             varchar(2) COMMENT '是否合格：0默认未打标，1是，2否',
    unqualified_reason       varchar(1024) COMMENT '不合格原因',
    remark                   varchar(1024) COMMENT '备注',
    document_account_code    varchar(32) COMMENT '操作人员账号',
    document_account_name    varchar(255) COMMENT '操作人员名称',
    -- status                varchar(32) COMMENT '状态',
    creator                  varchar(32) COMMENT '创建人',
    modifier                 varchar(32) COMMENT '修改人',
    gmt_create               timestamp COMMENT '创建时间',
    gmt_modified             timestamp COMMENT '修改时间',
    is_valid                 tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code              varchar(32) COMMENT '租户',
    PRIMARY KEY (`id`),
    INDEX                    idx_gmt_modified (gmt_modified)
)ENGINE=InnoDB COMMENT='单证证据';


```