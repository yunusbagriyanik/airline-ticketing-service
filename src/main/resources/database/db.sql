INSERT INTO STATUS VALUES(1, null, null, null, null, 'active customer', 'CUST', 'CUST', 1, 'active', 'ACTIVE');
INSERT INTO STATUS VALUES(2, null, null, null, null, 'vip customer', 'CUST', 'CUST', 1, 'vip', 'VIP_CUSTOMER');

INSERT INTO STATUS VALUES(3, null, null, null, null, 'confirm user', 'USER', 'USER', 1, 'confirm', 'confirm');
INSERT INTO STATUS VALUES(4, null, null, null, null, 'active user', 'USER', 'USER', 1, 'active', 'active');

INSERT INTO STATUS VALUES(5, null, null, null, null, 'prepare take off', 'FLIGHT', 'FLIGHT', 1, 'flight status', 'PREPARE_TAKE_OFF');
INSERT INTO STATUS VALUES(6, null, null, null, null, 'will be delayed', 'FLIGHT', 'FLIGHT', 1, 'flight status', 'WILL_BE_DELAYED');
INSERT INTO STATUS VALUES(7, null, null, null, null, 'completed', 'FLIGHT', 'FLIGHT', 1, 'flight status', 'FLIGHT_COMPLETED');
INSERT INTO STATUS VALUES(8, null, null, null, null, 'cancelled', 'FLIGHT', 'FLIGHT', 1, 'flight status', 'CANCELLED');

INSERT INTO STATUS VALUES(9, null, null, null, null, 'reserved', 'FLIGHT_RESERVATION', 'FLIGHT_RESERVATION', 1, 'flight operation', 'RESERVED');
INSERT INTO STATUS VALUES(10, null, null, null, null, 'cancelled', 'FLIGHT_RESERVATION', 'FLIGHT_RESERVATION', 1, 'flight operation', 'CANCELLED');
INSERT INTO STATUS VALUES(11, null, null, null, null, 'return process', 'FLIGHT_RESERVATION', 'FLIGHT_RESERVATION', 1, 'flight operation', 'RETURN_PROCESS');
INSERT INTO STATUS VALUES(12, null, null, null, null, 'return made', 'FLIGHT_RESERVATION', 'FLIGHT_RESERVATION', 1, 'flight operation', 'RETURN_MADE');
INSERT INTO STATUS VALUES(13, null, null, null, null, 'payment not success', 'FLIGHT_RESERVATION', 'FLIGHT_RESERVATION', 1, 'flight operation', 'PAYMENT_NOT_CONFIRMED');

INSERT INTO STATUS VALUES(14, null, null, null, null, 'pending', 'PAYMENT_HISTORY', 'PAYMENT_HISTORY', 1, 'payment status', 'PENDING');
INSERT INTO STATUS VALUES(15, null, null, null, null, 'success', 'PAYMENT_HISTORY', 'PAYMENT_HISTORY', 1, 'payment status', 'SUCCESS');
INSERT INTO STATUS VALUES(16, null, null, null, null, 'complete', 'PAYMENT_HISTORY', 'PAYMENT_HISTORY', 1, 'payment status', 'COMPLETE');
INSERT INTO STATUS VALUES(17, null, null, null, null, 'cancelled', 'PAYMENT_HISTORY', 'PAYMENT_HISTORY', 1, 'payment status', 'CANCELLED');
INSERT INTO STATUS VALUES(18, null, null, null, null, 'rejected', 'PAYMENT_HISTORY', 'PAYMENT_HISTORY', 1, 'payment status', 'REJECTED');



INSERT INTO ROUTE VALUES(1, null, null, null, null, 'Sabiha Gökçen Havalimanı', 1, 'Dalaman Havalimanı');
INSERT INTO ROUTE VALUES(2, null, null, null, null, 'Adnan Menderes Havalimanı', 1, 'Atatürk Havalimanı');
INSERT INTO ROUTE VALUES(3, null, null, null, null, 'Çarşamba Havalimanı', 1, 'Atatürk Havalimanı');
INSERT INTO ROUTE VALUES(4, null, null, null, null, 'Sabiha Gökçen Havalimanı', 1, 'Diyarbakır Havalimanı');
INSERT INTO ROUTE VALUES(5, null, null, null, null, 'Milas Bodrum Havaalanı',1, 'Atatürk Havalimanı');
INSERT INTO ROUTE VALUES(6, null, null, null, null, 'Adnan Menderes Havalimanı', 1, 'Esenboğa Havalimanı');
INSERT INTO ROUTE VALUES(7, null, null, null, null, 'Sabiha Gökçen Havalimanı', 1, 'Esenboğa Havalimanı');
INSERT INTO ROUTE VALUES(8, null, null, null, null, 'Dalaman Havalimanı', 1, 'Atatürk Havalimanı');

INSERT INTO AIRLINE_COMPANY VALUES(1, null, null, null, null, 1, 'Pegasus Hava Yolları');
INSERT INTO AIRLINE_COMPANY VALUES(2, null, null, null, null, 1, 'Türk Hava Yolları');
INSERT INTO AIRLINE_COMPANY VALUES(3, null, null, null, null, 1, 'AnadoluJet');
INSERT INTO AIRLINE_COMPANY VALUES(4, null, null, null, null, 1, 'SunExpress');
INSERT INTO AIRLINE_COMPANY VALUES(5, null, null, null, null,1, 'Atatürk Havalimanı');
INSERT INTO AIRLINE_COMPANY VALUES(6, null, null, null, null, 1, 'Air Canada');
INSERT INTO AIRLINE_COMPANY VALUES(7, null, null, null, null, 1, 'American Airlines');
INSERT INTO AIRLINE_COMPANY VALUES(8, null, null, null, null, 1, 'Emirates EK');