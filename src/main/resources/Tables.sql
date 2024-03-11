DROP TABLE pantry IF EXISTS;
DROP TABLE pantry IF EXISTS;

CREATE TABLE pantry (
    ingredient varchar(255) primary key
    ,amount double
    ,unit varchar(255)
    ,source int
);

CREATE TABLE market (
    market_id int auto_increment primary key
    ,market_name varchar(255)
);