alter table employee
    add foreign key (company_id) references company(id);