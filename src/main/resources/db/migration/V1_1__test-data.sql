INSERT INTO COMPANY.DEPARTMENT(ID, NAME)
VALUES ('924f79b8-d0a8-401b-a1b9-942993a39259', 'Finance'),
       ('b8767338-cfb8-4857-aca2-863d861f42b0', 'IT'),
       ('e3ed2366-44a7-437c-866b-dac5a3f3f3e6', 'Delivery'); -- empty - no employees

INSERT INTO COMPANY.EMPLOYEE(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID)
VALUES ('Jan', 'Kowalski', 10500.00, '924f79b8-d0a8-401b-a1b9-942993a39259'),
       ('Jan', 'Nowak', 10000.00, '924f79b8-d0a8-401b-a1b9-942993a39259'),
       ('Adam', 'Anioł', 15000.00, '924f79b8-d0a8-401b-a1b9-942993a39259'),
       ('Maciej', 'Wesoły', 15500.00, '924f79b8-d0a8-401b-a1b9-942993a39259'),
       ('Tomasz', 'Wróbel', 12000.00, 'b8767338-cfb8-4857-aca2-863d861f42b0');