CREATE TABLE IF NOT EXISTS  eg_bpa_buildingplan(
    id character varying(256) NOT NULL,
    applicationno character varying(64),
    tenantid character varying(256),
    status character varying(64),
    landid character varying(256),
    additionaldetails jsonb,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    approvalno character varying(64) DEFAULT NULL,
    approvaldate bigint,
    applicationdate bigint,
    businessService character varying(64) DEFAULT NULL::character varying,
    accountid character varying(256) DEFAULT NULL,
    CONSTRAINT pk_eg_bpa_buildingplan PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS eg_bpa_plotinfo (
    id character varying(256) NOT NULL,
    plotarea numeric(10,2),
    plotnumber character varying(256),
    khatanumber character varying(256),
    additionaldetails jsonb,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    buildingplanid character varying(256),
    CONSTRAINT pk_eg_bpa_plotinfo PRIMARY KEY (id),
    CONSTRAINT fk_eg_bpa_plotinfo FOREIGN KEY (buildingplanid)
        REFERENCES public.eg_bpa_buildingplan (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS eg_bpa_buildinginfo (
    id character varying(256) NOT NULL,
    totalbuiltuparea numeric(10,2),
    numberoffloors integer,
    buildingheight numeric(10,2),
    additionaldetails JSONB,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    buildingplanid character varying(256),
    CONSTRAINT pk_eg_bpa_buildinginfo PRIMARY KEY (id),
    CONSTRAINT fk_eg_bpa_buildinginfo FOREIGN KEY (buildingplanid)
        REFERENCES public.eg_bpa_buildingplan (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS eg_bpa_floorinfo (
    id character varying(256) NOT NULL,
    floorname character varying(256),
    floorlevel integer,
    floorusage character varying(256),
    builtuparea numeric(10,2),
    floorarea numeric(10,2),
    carpetarea numeric(10,2),
    additionaldetails JSONB,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    buildingid character varying(256),
    CONSTRAINT pk_eg_bpa_floorinfo PRIMARY KEY (id),
    CONSTRAINT fk_eg_bpa_floorinfo FOREIGN KEY (buildingid)
        REFERENCES public.eg_bpa_buildinginfo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS  public.eg_bpa_auditdetails(
    id character varying(256)  NOT NULL,
    applicationno character varying(64),
    tenantid character varying(256),
    status character varying(64),
    landid character varying(256),
    additionaldetails jsonb,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    approvalno character varying(64) DEFAULT NULL::character varying,
    approvaldate bigint,
    applicationdate bigint,
    businessService character varying(64) DEFAULT NULL::character varying,
    accountid character varying(256) DEFAULT NULL::character varying
);



CREATE TABLE IF NOT EXISTS  public.eg_bpa_document(
    id character varying(64)  NOT NULL,
    documenttype character varying(64),
    filestoreid character varying(64),
    documentuid character varying(64),
    buildingplanid character varying(64),
    additionaldetails jsonb,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    CONSTRAINT uk_eg_bpa_document PRIMARY KEY (id),
    CONSTRAINT fk_eg_bpa_document FOREIGN KEY (buildingplanid)
        REFERENCES public.eg_bpa_buildingplan (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX bpa_index  ON eg_bpa_buildingplan
(
    applicationno,
    approvalno,
    tenantid,
    landid,
    id,
    status
);
