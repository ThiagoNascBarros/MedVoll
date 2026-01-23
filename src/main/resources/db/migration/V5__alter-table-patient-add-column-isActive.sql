alter table pacientes add is_active tinyint;

update pacientes set is_active = 1;