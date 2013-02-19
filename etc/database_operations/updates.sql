create table `OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP` (`OID` bigint unsigned, `OID_ROOT_DOMAIN_OBJECT` bigint unsigned, `ID_INTERNAL` int(11) NOT NULL auto_increment, primary key (ID_INTERNAL), index (OID), index (OID_ROOT_DOMAIN_OBJECT)) ENGINE=InnoDB, character set utf8;
create table `OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP_EXECUTION_DEGREE` (`OID_EXECUTION_DEGREE` bigint unsigned, `OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP` bigint unsigned, primary key (OID_EXECUTION_DEGREE, OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP), index (OID_EXECUTION_DEGREE), index (OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP)) ENGINE=InnoDB, character set utf8;
create table `OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP_MOBILITY_COORDINATOR` (`OID_PERSON` bigint unsigned, `OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP` bigint unsigned, primary key (OID_PERSON, OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP), index (OID_PERSON), index (OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP)) ENGINE=InnoDB, character set utf8;
alter table `OUTBOUND_MOBILITY_CANDIDACY_CONTEST` drop key OID_UNIT, drop key OID_EXECUTION_DEGREE, drop OID_EXECUTION_DEGREE, drop OID_UNIT, add `OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP` bigint unsigned, add index (OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP);

-- Inserted at 2013-02-19T12:19:35.838Z

alter table `OUTBOUND_MOBILITY_CANDIDACY_CONTEST` drop key OID_UNIT, drop key OID_EXECUTION_DEGREE, drop OID_EXECUTION_DEGREE, drop OID_UNIT, add `OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP` bigint unsigned, add index (OID_OUTBOUND_MOBILITY_CANDIDACY_CONTEST_GROUP);
