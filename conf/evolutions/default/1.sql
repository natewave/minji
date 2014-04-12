# --- primary db schemas

# --- !Ups

CREATE TYPE buysell   AS ENUM ('buy', 'sell');
CREATE TYPE currency  AS ENUM ('btc', 'usd', 'eur');
CREATE TYPE market    AS ENUM ('btcusd', 'usdeur', 'btceur');

create table if not exists account (
    id                      bigserial NOT NULL PRIMARY KEY,
    username                varchar(255) NOT NULL unique,
    password                varchar(255) NOT NULL,
    email                   varchar(255) unique,
    created_at              timestamp
);

create table if not exists limit_order (
    id                      bigserial NOT NULL PRIMARY KEY,
    qty                     bigint NOT NULL,
    limit_price             bigint NOT NULL,
    market                  market NOT NULL,
    side                    buysell NOT NULL,
    created_at              timestamp NOT NULL,
    created_by              bigint NOT NULL references account(id)
);

create table if not exists balance (
    id                      bigserial NOT NULL  PRIMARY KEY,
    currency                currency NOT NULL,
    balance                 bigint default 0,
    account_id              bigint NOT NULL references account(id)
);

create table if not exists trade (
    id                      bigserial NOT NULL  PRIMARY KEY,
    qty                     bigint NOT NULL,
    price                   bigint NOT NULL,
    market                  market NOT NULL
);

create table if not exists trade_history (
    id                      bigserial NOT NULL,
    trade_id                bigint NOT NULL references trade(id),
    account_id              bigInt NOT NULL references trade(id),
    side                    buysell NOT NULL
);

# --- !Downs

DROP TABLE if exists trade_history;
DROP TABLE if exists trade;
DROP TABLE if exists balance;
DROP TABLE if exists limit_order;
DROP TABLE if exists account;
DROP TYPE buysell;
DROP TYPE currency;
DROP TYPE MARKET;

