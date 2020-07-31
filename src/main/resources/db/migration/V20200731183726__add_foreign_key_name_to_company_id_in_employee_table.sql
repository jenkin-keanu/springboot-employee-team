alter table employee
    add constraint fk_company foreign key (company_id) references company(id);