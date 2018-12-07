create table results(
  id      number primary key,
  x_value binary_double,
  y_value binary_double,
  r_value binary_double,
  result  number(1),
  "date"  timestamp
);

create sequence results_seq start with 1;