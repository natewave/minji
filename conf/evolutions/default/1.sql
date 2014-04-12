# --- primary db schemas

# --- !Ups

CREATE TYPE side        AS ENUM ('buy', 'sell');
CREATE TYPE currency    AS ENUM ('btc', 'usd', 'eur');
CREATE TYPE market    AS ENUM ('btcusd', 'usdeur', 'btceur');

create table if not exists account (
    id                      bigserial not null PRIMARY KEY,
    username                varchar(255) not null unique,
    password                varchar(255) not null,
    email                   varchar(255) unique,
    created_at              timestamp
);

create table if not exists limit_order (
    id                      bigserial NOT NULL PRIMARY KEY,
    qty                     bigint not null,
    limit_price             bigint not null,
    market                  market not null,
    side                    side not null,
    created_at              time_stamp not null,
    created_by              bigint not null references account(id) on delete set cascade
);

create table if not exists balance (
    id                      bigserial not null  PRIMARY KEY,
    currency                currency not null,
    balance                 bigint default 0,
    account_id              bigint not null references account(id) on delete set cascade
);

create table if not exists trade (
    id                      bigserial not null  PRIMARY KEY,
    qty                     bigint not null,
    price                   bigint NOT NULL,
    market                  market NOT NULL
);

create table if not exists trade_history (
    id                      bigserial NOT NULL,
    trade_id                bigint NOT NULL references trade(id) on delete set cascade,
    account_id              bigInt NOT NULL references trade(id) on delete set cascade,
    side                    side NOT NULL
);

# --- !Downs

drop table if exists trade_history;
drop table if exists trade;
drop table if exists balance;
drop table if exists limit_order;
drop table if exists account;
