create table results(
  id      number primary key,
  x_value number(16, 15),
  y_value number(16, 15),
  r_value number(16, 15),
  result  number(1),
  result_date  timestamp
);

create sequence results_seq start with 1 increment by 10;