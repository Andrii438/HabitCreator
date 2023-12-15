drop table habits;


CREATE TABLE habits (
                        id serial PRIMARY KEY,
                        name varchar(255),
                        day_duration int,
                        owner_id BIGINT
);

insert into habits( name, day_duration, owner_id ) values
                                                       ( 'Eat healthy food', 100, null),
                                                       ( 'Study English', 600, null),
                                                       ( 'Watch useful podcast', 20, null);