insert into TFCT_FLIGHT
(
YEAR,
MONTH,
DAYOFMONTH,
DAYOFWEEK,
DEPTIME,
CRSDEPTIME,
ARRTIME,
CRSARRTIME,
UNIQUECARRIER,
FLIGHTNUM,
TAILNUM,
ACTUALELAPSEDTIME,
CRSELAPSEDTIME,
AIRTIME,
ARRDELAY,
DEPDELAY,
ORIGIN,
DEST,
DISTANCE,
TAXIIN,
TAXIOUT,
CANCELLED,
CANCELLATIONCODE,
DIVERTED,
CARRIERDELAY,
WEATHERDELAY,
NASDELAY,
SECURITYDELAY,
LATEAIRCRAFTDELAY,
ORIGINAIRPORT,
ORIGINCITY,
ORIGINSTATE,
ORIGINCOUNTRY,
ORIGINLAT,
ORIGINFILD,
DESTAIRPORT,
DESTCITY,
DESTSTATE,
DESTCOUNTRY,
DESTLAT,
DESTFILD,
DESCRIPTION
)
VALUES (
:YEAR,
:MONTH,
:DAYOFMONTH,
:DAYOFWEEK,
:DEPTIME,
:CRSDEPTIME,
:ARRTIME,
:CRSARRTIME,
:UNIQUECARRIER,
:FLIGHTNUM,
:TAILNUM,
:ACTUALELAPSEDTIME,
:CRSELAPSEDTIME,
:AIRTIME,
:ARRDELAY,
:DEPDELAY,
:ORIGIN,
:DEST,
:DISTANCE,
:TAXIIN,
:TAXIOUT,
:CANCELLED,
:CANCELLATIONCODE,
:DIVERTED,
:CARRIERDELAY,
:WEATHERDELAY,
:NASDELAY,
:SECURITYDELAY,
:LATEAIRCRAFTDELAY,
:ORIGINAIRPORT,
:ORIGINCITY,
:ORIGINSTATE,
:ORIGINCOUNTRY,
:ORIGINLAT,
:ORIGINFILD,
:DESTAIRPORT,
:DESTCITY,
:DESTSTATE,
:DESTCOUNTRY,
:DESTLAT,
:DESTFILD,
:DESCRIPTION

);