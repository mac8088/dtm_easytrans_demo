CREATE SEQUENCE order_seq START 1 INCREMENT 1;

DROP TABLE IF EXISTS "order";
CREATE TABLE "order" (
    "order_id" numeric(11)          NOT NULL DEFAULT nextval('order_seq'),
    "user_id"  numeric(11)          NOT NULL,
    "money"    numeric(20)          NOT NULL,
    "create_time" date              NOT NULL,
    constraint pk_order primary key ("order_id")
)

DROP TABLE IF EXISTS "executed_trans";
CREATE TABLE "executed_trans" (
    "app_id" numeric(5)             NOT NULL,
    "bus_code" numeric(5)           NOT NULL,
    "trx_id" numeric(20)            NOT NULL,
    "p_app_id" numeric(5)           NULL,
    "p_bus_code" numeric(5)         NULL,
    "p_trx_id" numeric(20)          NULL,
    "status" numeric(1)             NOT NULL,
    constraint pk_executed_trans primary key ("app_id", "bus_code", "trx_id"),
	constraint parent unique ("p_app_id", "p_bus_code", "p_trx_id")
)

DROP TABLE IF EXISTS "idempotent";
CREATE TABLE "idempotent" (
    "src_app_id" numeric(5)         NOT NULL,
    "src_bus_code" numeric(5)       NOT NULL,
    "src_trx_id" numeric(20)        NOT NULL,
    "app_id" numeric(5)             NOT NULL,
    "bus_code" numeric(5)           NOT NULL,
    "call_seq" numeric(5)           NOT NULL,
    "handler" numeric(5)            NOT NULL,
    "called_methods" varchar(64)    NOT NULL,
    "md5" varchar(16)               NOT NULL,
    "sync_method_result" TEXT       NULL,
    "create_time" date              NOT NULL,
    "update_time" date              NOT NULL,
    "lock_version" numeric(32)      NOT NULL,
    constraint pk_idempotent primary key ("src_app_id","src_bus_code","src_trx_id","app_id","bus_code","call_seq","handler")
)

=================================================================================================

DROP TABLE IF EXISTS "wallet";
CREATE TABLE "wallet" (
    "user_id" numeric(11)           NOT NULL,
    "total_amount" numeric(20)      NOT NULL,
    "freeze_amount" numeric(20)     NOT NULL,
    constraint pk_wallet primary key ("user_id")
)

delete from wallet;
INSERT INTO wallet (user_id, total_amount, freeze_amount) VALUES ('1', '10000000', '0');


=================================================================================================

CREATE SEQUENCE trans_log_detail_seq START 1 INCREMENT 2;

DROP TABLE IF EXISTS "trans_log_detail";
CREATE TABLE "trans_log_detail" (
    "log_detail_id" numeric(11)     NOT NULL DEFAULT nextval('trans_log_detail_seq'),
    "trans_log_id" varchar(12)      NOT NULL,
    "log_detail" TEXT               NULL,
    "create_time" date              NOT NULL,
    constraint pk_trans_log_detail primary key ("log_detail_id"),
    constraint app_id unique (trans_log_id)
)

DROP TABLE IF EXISTS "trans_log_unfinished";
CREATE TABLE "trans_log_unfinished" (
    "trans_log_id" varchar(12)      NOT NULL,
    "create_time" date              NOT NULL,
    constraint pk_trans_log_unfinished primary key ("trans_log_id")
)
