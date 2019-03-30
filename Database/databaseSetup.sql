CREATE TABLE 'products_info' (
    'product_id' VARCHAR(32) NOT NULL,
    'product_name' VARCHAR(64) NOT NULL,
    'product_price' DECIMAL(8,2) NOT NULL,
    'product_stock' INT NOT NULL,
    'product_description' VARCHAR(512),
    'product_icon'  VARCHAR(512) COMMENT 'icon of the product',
    'category_type' INT NOT NULL COMMENT 'typeId',
    'create_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    'update_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY('product_id')
) COMMENT 'prodcut list';

CREATE TABLE 'product_category'(

    'category_id' INT NOT NULL AUTO_INCREMENT,
    'category_name' VARCHAR(64) NOT NULL ,
    'category_type' INT NOT NULL COMMENT 'typeId',
    'create_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time created',
    'update_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
    COMMENT 'update time',
    PRIMARY KEY('category_id'),
    UNIQUE KEY 'uqe_category_type'('category_type')
);

CREATE TABLE 'order_manager'(

    'order_id' VARCHAR(32) NOT NULL,
    'buyer_name' VARCHAR(32) NOT NULL ,
    'buyer_phone' VARCHAR(32) NOT NULL ,
    'buyer_address' VARCHAR(128) NOT NULL,
    'buyer_openid' VARCHAR(64) NOT NULL COMMENT 'buyers wechatID',
    'order_amount' DECIMAL(8,2) NOT NULL,
    'order_status' TINYINT(3) NOT NULL DEFAULT '0' COMMENT 'status of the order, default is 0',
    'pay_status' TINYINT(3) NOT NULL DEFAULT '0' COMMENT 'status of the payment, default is 0 unpaid',
    'create_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time created',
    'update_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY('order_id'),
    KEY 'idx_buyer_openid' ('buyer_openid')
) COMMENT 'ordertable';

CREATE TABLE 'order_detail'(
    'detail_id' VARCHAR(32) NOT NULL, 
    'order_id'  VARCHAR(32) NOT NULL,
    'product_id' VARCHAR(32) NOT NULL,
    'product_name' VARCHAR(64)  NOT NULL,
    'product_price' DECIMAL(8,2) NOT NULL,
    'product_quantity' INT NOT NULL,
    'product_icon' VARCHAR(512) COMMENT 'icon of the product',
    'create_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time created',
    'update_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
    PRIMARY KEY('detail_id'),
    KEY 'idx_order_id' (order_id)

);