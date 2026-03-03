alter table medicos add column activo tinyint;
alter table pacientes add column activo tinyint;

update medicos set activo = 1;
update pacientes set activo = 1;