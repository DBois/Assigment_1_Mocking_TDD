drop table if exists customer, bank, account, movement cascade;

create table customer
(
    cpr varchar(10) not null
        constraint customer_pk
            primary key,
    name varchar(60) default 'defaultname'::character varying not null
);

alter table customer owner to postgres;

create table bank
(
    cvr varchar(8) not null
        constraint bank_pk
            primary key,
    name varchar(50) default 'defaultname'::character varying not null
);

alter table bank owner to postgres;

create table account
(
    number varchar(10) not null
        constraint account_pk
            primary key,
    balance bigint default 0 not null,
    customer_cpr varchar(10)
        constraint account_customer_cpr_fk
            references customer
                on update cascade on delete cascade,
    bank_cvr varchar(8)
        constraint account_bank_cvr_fk
            references bank
                on update cascade on delete cascade
);

alter table account owner to postgres;

create table movement
(
    id serial not null
        constraint movement_pk
            primary key,
    time timestamp default CURRENT_TIMESTAMP not null,
    amount bigint not null,
    account_source varchar(10)
        constraint movement_account_number_fk
            references account
                on update cascade on delete cascade,
    account_target varchar(10)
        constraint movement_account_number_fk_2
            references account
                on update cascade on delete cascade
);

alter table movement owner to postgres;