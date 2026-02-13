--jdbc:postgresql://localhost:5432/postgres
--jdbc:postgresql://localhost:5432/InversionesPrestamos
-- base de datos inversionesPrestamos creacion de tablas
create table clientes(
ceducli varchar(10) not null primary key,
nomcli varchar(30),
apellicli varchar(30),
dircli varchar(50),
telecli varchar(10)
);

create table garantias(
codgarantia varchar(10) not null primary key,
tipogarantia varchar(30),
valor numeric(11,2),
ubicacion varchar(30)
);

create table prestamos(
ceduprestatario varchar(10) references clientes (ceducli),
codprestamo varchar(10) not null primary key,
fechsolicitudpre date,
fechaprobacionpre date,
fechiniciopre date,
fechterminopre date,
interesprestamo numeric(5,2),
montoprestamo numeric(11,2),
estadoprestamo varchar(9),
garantia varchar(10) references garantias(codgarantia),
fiador varchar(10) references clientes (ceducli),
numerocuotas numeric(2)
);

create table cuentasbancarias(
numerocuenta varchar(10) not null primary key,
banco varchar (30),
tipocuenta varchar (9)
);

create table inversiones(
ceduinversionista varchar(10) references clientes (ceducli),
codinversion varchar(10) not null primary key,
fechinicioinv date,
fechterminoinv date,
interesinv numeric(5,2),
montoinv numeric(11,2),
numerocuentabancaria varchar(10) references cuentasbancarias(numerocuenta),
numerocuotas numeric(2)
);
/*
create table cronogramaprestamo(
codcronograma varchar(10) not null primary key,
codprestamo varchar(10) references prestamos(codprestamo) ,
numerocuotas numeric(2)
);
*/
/*create table cronogramainversion(
codcronograma varchar(10) not null primary key,
codinversion varchar(10) references inversiones(codinversion),
numerocuotas numeric(2)
);*/

create table cuotaprestamo(
numerocuota varchar(2),
fechpago date,
montocuota numeric(11,2),
estadocuota varchar(9),
tipopago varchar(30),
codcomprobante varchar(10),
fechefectiva date,
codprestamo varchar(10) references prestamos(codprestamo)
);

create table cuotainversion(
numerocuota varchar(2),
fechpago date,
montocuota numeric(11,2),
estadocuota varchar(9),
tipopago varchar(30),
codcomprobante varchar(10),
fechefectiva date,
codinversion varchar(10) references inversiones(codinversion),
cuentabanco varchar(10) references cuentasbancarias(numerocuenta)
);

create table usuarios(
codusuario varchar(10) not null primary key,
usuario varchar(20),
contrasenia varchar(20),
cargo varchar (21)
);
--drop table usuarios;

--update prestamos set numerocuotas = (select count(*)from cuotaprestamo where codcronograma = cronograma);
--update inversiones set numerocuotas = (select count(*)from cuotaprestamo where codcronograma = cronograma);
/*update cuotainversion set estadocuota = 'Pagada' where codinversion like '0000000002';
update cuotainversion set tipopago = 'deposito bancario' where codinversion like '0000000002';
update cuotainversion set codcomprobante = '0000000001' where codinversion like '0000000002' and numerocuota like '01';
update cuotainversion set codcomprobante = '0000000002' where codinversion like '0000000002' and numerocuota like '02';
update cuotainversion set codcomprobante = '0000000003' where codinversion like '0000000002' and numerocuota like '03';
update cuotainversion set codcomprobante = '0000000004' where codinversion like '0000000002' and numerocuota like '04';
update cuotainversion set codcomprobante = '0000000005' where codinversion like '0000000002' and numerocuota like '05';
update cuotainversion set codcomprobante = '0000000006' where codinversion like '0000000002' and numerocuota like '06';
update cuotainversion set codcomprobante = '0000000007' where codinversion like '0000000002' and numerocuota like '07';
update cuotainversion set codcomprobante = '0000000008' where codinversion like '0000000002' and numerocuota like '08';
update cuotainversion set codcomprobante = '0000000009' where codinversion like '0000000002' and numerocuota like '09';
update cuotainversion set codcomprobante = '0000000010' where codinversion like '0000000002' and numerocuota like '10';
update cuotainversion set codcomprobante = '0000000011' where codinversion like '0000000002' and numerocuota like '11';
update cuotainversion set codcomprobante = '0000000012' where codinversion like '0000000002' and numerocuota like '12';
update cuotainversion set codcomprobante = '0000000013' where codinversion like '0000000002' and numerocuota like '13';
update cuotainversion set codcomprobante = '0000000014' where codinversion like '0000000002' and numerocuota like '14';
update cuotainversion set codcomprobante = '0000000015' where codinversion like '0000000002' and numerocuota like '15';
update cuotainversion set codcomprobante = '0000000016' where codinversion like '0000000002' and numerocuota like '16';
update cuotainversion set codcomprobante = '0000000017' where codinversion like '0000000002' and numerocuota like '17';
update cuotainversion set codcomprobante = '0000000018' where codinversion like '0000000002' and numerocuota like '18';
update cuotainversion set codcomprobante = '0000000019' where codinversion like '0000000002' and numerocuota like '19';
update cuotainversion set codcomprobante = '0000000020' where codinversion like '0000000002' and numerocuota like '20';
update cuotainversion set codcomprobante = '0000000021' where codinversion like '0000000002' and numerocuota like '21';
update cuotainversion set codcomprobante = '0000000022' where codinversion like '0000000002' and numerocuota like '22';
update cuotainversion set codcomprobante = '0000000023' where codinversion like '0000000002' and numerocuota like '23';
update cuotainversion set codcomprobante = '0000000024' where codinversion like '0000000002' and numerocuota like '24';

update cuotainversion set fechefectiva = '2005-04-09' where codinversion like '0000000002' and numerocuota like '01';
update cuotainversion set fechefectiva = '2005-05-09' where codinversion like '0000000002' and numerocuota like '02';
update cuotainversion set fechefectiva = '2005-06-08' where codinversion like '0000000002' and numerocuota like '03';
update cuotainversion set fechefectiva = '2005-07-08' where codinversion like '0000000002' and numerocuota like '04';
update cuotainversion set fechefectiva = '2005-08-07' where codinversion like '0000000002' and numerocuota like '05';
update cuotainversion set fechefectiva = '2005-09-06' where codinversion like '0000000002' and numerocuota like '06';
update cuotainversion set fechefectiva = '2005-10-06' where codinversion like '0000000002' and numerocuota like '07';
update cuotainversion set fechefectiva = '2005-11-05' where codinversion like '0000000002' and numerocuota like '08';
update cuotainversion set fechefectiva = '2005-12-05' where codinversion like '0000000002' and numerocuota like '09';
update cuotainversion set fechefectiva = '2006-01-04' where codinversion like '0000000002' and numerocuota like '10';
update cuotainversion set fechefectiva = '2006-02-03' where codinversion like '0000000002' and numerocuota like '11';
update cuotainversion set fechefectiva = '2006-03-05' where codinversion like '0000000002' and numerocuota like '12';
update cuotainversion set fechefectiva = '2006-04-04' where codinversion like '0000000002' and numerocuota like '13';
update cuotainversion set fechefectiva = '2006-05-04' where codinversion like '0000000002' and numerocuota like '14';
update cuotainversion set fechefectiva = '2006-06-03' where codinversion like '0000000002' and numerocuota like '15';
update cuotainversion set fechefectiva = '2006-07-03' where codinversion like '0000000002' and numerocuota like '16';
update cuotainversion set fechefectiva = '2006-08-02' where codinversion like '0000000002' and numerocuota like '17';
update cuotainversion set fechefectiva = '2006-09-01' where codinversion like '0000000002' and numerocuota like '18';
update cuotainversion set fechefectiva = '2006-10-01' where codinversion like '0000000002' and numerocuota like '19';
update cuotainversion set fechefectiva = '2006-10-31' where codinversion like '0000000002' and numerocuota like '20';
update cuotainversion set fechefectiva = '2006-11-30' where codinversion like '0000000002' and numerocuota like '21';
update cuotainversion set fechefectiva = '2006-12-30' where codinversion like '0000000002' and numerocuota like '22';
update cuotainversion set fechefectiva = '2007-01-29' where codinversion like '0000000002' and numerocuota like '23';
update cuotainversion set fechefectiva = '2007-02-28' where codinversion like '0000000002' and numerocuota like '24';
CAST ( tipocuenta [ ( 9 ) ] ) 
*/

alter table prestamos add check(ceduprestatario <> fiador);
alter table prestamos alter column ceduprestatario set not null;
alter table prestamos alter column fiador set default null;
alter table prestamos alter column garantia set default null;
alter table prestamos alter column fechiniciopre set default null;
alter table prestamos alter column fechterminopre set default null;
alter table prestamos alter column fechaprobacionpre set default null;
alter table prestamos add check(estadoprestamo='aprobado' or estadoprestamo='rechazado' or estadoprestamo ='pendiente');

--alter table inversiones alter column fechterminoinv set default null;

alter table cuentasbancarias add check(tipocuenta='ahorros' or tipocuenta='corriente');
alter table cuotaprestamo add check(estadocuota='Espera' or estadocuota='Pagada' or estadocuota='No Pagada');
alter table cuotainversion add check(estadocuota='Espera' or estadocuota='Pagada' or estadocuota='No Pagada');
SET datestyle = 'DMY';
insert into usuarios values 
('ADMIN001','admin','admin123','Administrador'),
('EMP001','cajero','cajero123','Cajero Bancario'),
('EMP002','supervisor','super123','Supervisor'),
('EMP003','gerente','gerente123','Gerente');

ALTER USER postgres PASSWORD '123';
--alter table prestamos alter column fiador set not null;
--alter table prestamos alter column garantia set not null;
--copy clientes from 'E:\Ruta\Ruta\Ruta\Clientes.csv' with (delimiter '|',encoding 'UTF-8',format 'csv');
--copy prestamos from 'E:\Ruta\Ruta\Ruta\Prestamos.csv' with (delimiter '|',encoding 'UTF-8',format 'csv');
--copy prestamos(ceduprestatario,codprestamo,fechsolicitudpre,fechaprobacionpre,fechiniciopre,interesprestamo,montoprestamo,estadoprestamo,fiador,numerocuotas) from 'E:\Ruta\Ruta\Ruta\PrestamosEnProceso.csv' with (delimiter '|',encoding 'UTF-8',format 'csv');
--copy garantias from 'E:\Ruta\Ruta\Ruta\Garantias.csv' with (delimiter '|',encoding 'UTF-8',format 'csv');
--copy cuotaprestamo from 'E:\Ruta\Ruta\Ruta\Cuotas.csv' with (delimiter '|',encoding 'UTF-8',format 'csv');
--COPY cuentasbancarias FROM 'E:\Ruta\Ruta\Ruta\CuentasBancarias.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
--COPY inversiones FROM 'E:\Ruta\Ruta\Ruta\Inversiones.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
--COPY cuotainversion FROM 'E:\Ruta\Ruta\Ruta\CuotasInversion.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
/*6724444.121
125000
253125		5128125
384453.125	5131328.125
519064.453	5134611.328
657041.064	5137976.611
798467.091	5141426.027
943428.768	5144961.677
1092014.488	5148585.72
1244314.85	5152300.362
1400422.721	5156107.871
1560433.289	5160010.568
1724444.121	5164010.832*/
--insert into prestamos values (1080901337,'218034156','25/05/2020',null,null,null,'2.0','1000000.0','pendiente','9898',null);
--insert into prestamos()
--drop table prestamos;
--drop table garantias;
--drop table inversiones;
--drop table cuentasbancarias;
--drop table clientes;
--drop table cronogramaprestamo;
--drop table cronogramainversion;
--drop table cuotaprestamo;
--drop table cuotainversion;
--select * from clientes;
--delete from clientes;
--delete from garantias;
--delete from prestamos;
--drop table inversiones;
--select * from cuotainversion;
--select * from inversiones;
--select count(*) as cuotas from cuotainversion where codinversion like '0000000002' and estadocuota like 'Pagada';
--(select count(*) as cuotas from cuotainversion join inversiones on cuotainversion.codinversion=inversiones.codinversion where ceduinversionista like '1085347901' and estadocuota like 'Pagada' ) =  ;