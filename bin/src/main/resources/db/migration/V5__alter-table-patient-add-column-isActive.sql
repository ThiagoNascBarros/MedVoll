alter table patient add is_active tinyint;

update patient set is_active = 1;