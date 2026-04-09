create table profiles
(
    id             bigint auto_increment
        primary key,
    bio            text                   null,
    phone_number   varchar(15)            null,
    date_of_birth  date                   null,
    loyalty_points int unsigned default 0 null,
    foreign key (id) references users (id)
);

