--liquibase formatted sql
--changeset V1.1-dml-init context:dev
INSERT  INTO  member (username)   VALUES  ('User Name 1');

--changeset V1.1-dml-init-2 context:dev
INSERT  INTO  member (username)   VALUES  ('User Name 2');