```sql

create table t_agn_document
(
    document_id                VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_code              varchar(32) COMMENT '单证编码',
    document_directory_code    varchar(32) COMMENT '所在单证目录',
    document_name              varchar(255) COMMENT '单证名称',
    document_type_cd           varchar(2) COMMENT '单证种类代码，单证种类, 0:模版单证, 1:证件, 2:图像, 3:发票, 4:其他',
    document_type_name         varchar(32) COMMENT '单证种类名称',
    document_feature           varchar(255) COMMENT '单证形式，多个以逗号隔开：0: 原件，1: 复印件，2: 复印件加盖公章, 3:影像件, 4:实物',
    is_refine_collection       varchar(2) COMMENT '是否需要细化收单列表',
    refine_collections         varchar(255) COMMENT '收单列表：身份证，护照、营业执照、事故责任认定书、自定义等，以逗号隔开',
    collection_type_cd         varchar(2) COMMENT '收单完备条件：0:全选，1:任选其一，2:按实收取',
    is_quoted                  varchar(2) COMMENT '是否被收单模板引用',
    document_provider_org_cd   varchar(2) COMMENT '出具机构, 包括保险公司、就诊医院、被保险人',
    document_provider_org_name varchar(255) COMMENT '出具机构名称',
    creator                    varchar(32) COMMENT '创建人',
    creator_name               varchar(255) comment '创建人姓名',
    modifier                   varchar(32) COMMENT '修改人',
    modifier_name              varchar(32) COMMENT '修改人姓名',
    gmt_create                 timestamp COMMENT '创建时间',
    gmt_modified               timestamp COMMENT '修改时间',
    gmt_delete                 timestamp comment '删除时间',
    is_valid                   tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code                varchar(32) COMMENT '租户',
    flag                       varchar(16) comment '标识位',
    PRIMARY KEY (document_id)
) COMMENT='单证';


create table t_agn_document_directory
(
    document_directory_id          VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_directory_code        varchar(32) COMMENT '单证目录编码',
    parent_document_directory_code varchar(32) COMMENT '上级目录code',
    document_directory_sort        int COMMENT '顺序位置',
    directory_name                 varchar(255) COMMENT '目录名称',
    is_quoted                      varchar(2) COMMENT '是否被收单模板引用',
    creator                        varchar(32) COMMENT '创建人',
    creator_name                   varchar(255) comment '创建人姓名',
    modifier                       varchar(32) COMMENT '修改人',
    modifier_name                  varchar(32) COMMENT '修改人姓名',
    gmt_create                     timestamp COMMENT '创建时间',
    gmt_modified                   timestamp COMMENT '修改时间',
    gmt_delete                     timestamp comment '删除时间',
    is_valid                       tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code                    varchar(32) COMMENT '租户',
    flag                           varchar(16) comment '标识位',
    PRIMARY KEY (document_directory_id)
) COMMENT='单证目录';

create table t_agn_collection_template
(
    collection_template_id   VARCHAR(64) NOT NULL COMMENT '主键ID',
    collection_template_code VARCHAR(32) COMMENT '收单模版编码',
    template_name            varchar(255) COMMENT '模板名称',
    template_type_cd         varchar(2) COMMENT '模板种类：整案单证，损失单证',
    report_accept_channel_cd varchar(2) COMMENT '报案受理渠道代码, 如：互联网、电话、柜台',
    customer_segmentation_cd varchar(2) COMMENT '客户分群代码, 0:集群客户, 1:股东客户, 2:政府机构',
    product_type_code        varchar(32) COMMENT '产品分类代码',
    product_type_name        varchar(255) COMMENT '产品分类名称',
    template_status_cd       varchar(2) COMMENT '模板状态，启用、停用',
    template_remark          varchar(1024) COMMENT '模板说明',
    is_quoted                varchar(2) COMMENT '是否被引用：0代表未引用，1代表被引用',
    accident_reason_cd       varchar(2) COMMENT '出险原因，事故性质, 如：火灾、暴露、暴雨等',
    damage_person_status_cd  varchar(2) COMMENT '出险人状态, 0:死亡, 1:伤残, 2:一般医疗',
    claimed_item_type_cd     varchar(2) COMMENT '损失单证索赔项目类型, 包括：标的人、三者人、标的物等',
    creator                  varchar(32) COMMENT '创建人',
    creator_name             varchar(255) comment '创建人姓名',
    modifier                 varchar(32) COMMENT '修改人',
    modifier_name            varchar(32) COMMENT '修改人姓名',
    gmt_create               timestamp COMMENT '创建时间',
    gmt_modified             timestamp COMMENT '修改时间',
    gmt_delete               timestamp comment '删除时间',
    is_valid                 tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code              varchar(32) COMMENT '租户',
    flag                     varchar(16) comment '标识位',
    PRIMARY KEY (collection_template_id)
) COMMENT='收单模板';

create table t_agn_document_relation
(
    document_relation_id       varchar(32) comment '主键ID',
    document_relation_code     varchar(32) comment '收单模版关系表编码',
    document_relation_type_cd  varchar(2) comment '关系起点节点的类型, 如：收单模版，单证目录',
    document_relation_biz_code varchar(32) comment '关系表起点节点的业务代码编号，目前只有收单模版编码',
    document_relation_content  varchar(2048) comment '关系表内容，形如: {code:"唯一编码",name:"目录或者单证名称",bizCode:"目录或者单证的业务编码",children:[]}',
    creator                    varchar(32) COMMENT '创建人',
    creator_name               varchar(255) comment '创建人姓名',
    modifier                   varchar(32) COMMENT '修改人',
    modifier_name              varchar(32) COMMENT '修改人姓名',
    gmt_create                 timestamp COMMENT '创建时间',
    gmt_modified               timestamp COMMENT '修改时间',
    gmt_delete                 timestamp comment '删除时间',
    flag                       varchar(16) comment '标识位',
    primary key (document_relation_id)
) comment='收单模版-单证目录-单证关系表';

create table t_agn_document_relation_rule
(
    document_relation_rule_id   VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_relation_rule_code varchar(32) COMMENT '收单关系映射规则编码',
    collection_template_code    VARCHAR(32) COMMENT '收单模版编码',
    document_relation_code      varchar(32) comment '收单模版规则的json中的唯一code的编码',
    is_manual_authority         varchar(2) COMMENT '是否允许手工置全',
    complete_conditions         varchar(32) COMMENT '齐全条件',
    rule_content                varchar(2048) COMMENT '规则内容：json格式',
    creator                     varchar(32) COMMENT '创建人',
    creator_name                varchar(255) comment '创建人姓名',
    modifier                    varchar(32) COMMENT '修改人',
    modifier_name               varchar(32) COMMENT '修改人姓名',
    gmt_create                  timestamp COMMENT '创建时间',
    gmt_modified                timestamp COMMENT '修改时间',
    gmt_delete                  timestamp comment '删除时间',
    is_valid                    tinyint(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code                 varchar(32) COMMENT '租户',
    flag                        varchar(16) comment '标识位',
    PRIMARY KEY (document_relation_rule_id)
) COMMENT='收单关系映射规则';

create table t_agn_document_proof
(
    document_proof_id                 VARCHAR(64) NOT NULL COMMENT '主键ID',
    document_proof_code               varchar(32) COMMENT '单证证据编码',
    document_code                     VARCHAR(32) NOT NULL COMMENT '所在单证编码',
    document_directory_code           varchar(32) COMMENT '所在单证目录编码',
    collection_template_code          VARCHAR(32) COMMENT '所在模板编码',
    document_relation_rule_code       varchar(32) COMMENT '收单关系映射规则编码',
    biz_code                          varchar(32) comment '业务编号',
    biz_code_type_cd                  varchar(2) comment '业务编号类型，索赔申请号、损失项号、赔案号',
    refine_collection_cd              varchar(2) COMMENT '收单类型编码，如：身份证正面、身份证反面、护照',
    is_example                        varchar(2) comment '是否单证样例',
    storage_path                      varchar(1024) COMMENT '单证存储路径, 也包括oss的id',
    file_name                         varchar(255) COMMENT '单证文件名',
    file_type_cd                      VARCHAR(2) COMMENT '单证文件类型, 如：照片',
    is_need                           varchar(2) COMMENT '是否必收：0: 是, 1: 否',
    is_qualified                      varchar(2) COMMENT '是否合格：0默认未打标，1是，2否',
    unqualified_reason_cd             varchar(2) COMMENT '不合格原因,1:图片拍摄不清晰,2:资料部分被遮挡,3:资料拍摄不完整,4:同类资料有缺失,5:图中资料未盖章,6:图中资料非原件',
    document_proof_approve_supplement varchar(1024) COMMENT '单证打标补充说明',
    document_account_code             varchar(32) COMMENT '操作人员账号',
    document_account_name             varchar(255) COMMENT '操作人员名称',
    creator                           varchar(32) COMMENT '创建人',
    creator_name                      varchar(255) comment '创建人姓名',
    modifier                          varchar(32) COMMENT '修改人',
    modifier_name                     varchar(32) COMMENT '修改人姓名',
    gmt_create                        timestamp COMMENT '创建时间',
    gmt_modified                      timestamp COMMENT '修改时间',
    gmt_delete                        timestamp comment '删除时间',
    is_valid                          varchar(2) COMMENT '数据标识：0: 数据无效, 1: 数据有效',
    tenant_code                       varchar(32) COMMENT '租户',
    flag                              varchar(16) comment '标识位',
    PRIMARY KEY (document_proof_id)
)COMMENT='单证证据';
```