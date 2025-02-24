DROP INDEX IF EXISTS bpa_index;

ALTER TABLE eg_bpa_buildingplan DROP COLUMN edcrnumber;

CREATE INDEX bpa_index ON eg_bpa_buildingplan 
(
    applicationno,
    approvalno,
    tenantid,
    landid,
    id,
    status
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
    level integer,
    usage character varying(256),
    builduparea numeric(10,2),
    floorarea numeric(10,2),
    carpetarea numeric(10,2),
    additionaldetails JSONB,
    createdby character varying(64),
    lastmodifiedby character varying(64),
    createdtime bigint,
    lastmodifiedtime bigint,
    buildinginfoid character varying(256),
    CONSTRAINT pk_eg_bpa_floorinfo PRIMARY KEY (id),
    CONSTRAINT fk_eg_bpa_floorinfo FOREIGN KEY (buildinginfoid)
        REFERENCES public.eg_bpa_buildinginfo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
