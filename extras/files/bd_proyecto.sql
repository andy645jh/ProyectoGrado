--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.3
-- Dumped by pg_dump version 9.2.3
-- Started on 2017-06-14 09:29:23

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 211 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 211
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 168 (class 1259 OID 33757)
-- Name: actividades; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actividades (
    codactividad integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion text NOT NULL,
    responsable character varying(100) NOT NULL,
    coddocente integer,
    codtipo integer,
    horas double precision,
    valoracion integer
);


ALTER TABLE public.actividades OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 33763)
-- Name: actividades_codactividad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE actividades_codactividad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actividades_codactividad_seq OWNER TO postgres;

--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 169
-- Name: actividades_codactividad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE actividades_codactividad_seq OWNED BY actividades.codactividad;


--
-- TOC entry 170 (class 1259 OID 33765)
-- Name: actividadmisional; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actividadmisional (
    codactividadmisional integer NOT NULL,
    nombre character varying(100)
);


ALTER TABLE public.actividadmisional OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 33768)
-- Name: actividadmisional_codactividadmisional_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE actividadmisional_codactividadmisional_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actividadmisional_codactividadmisional_seq OWNER TO postgres;

--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 171
-- Name: actividadmisional_codactividadmisional_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE actividadmisional_codactividadmisional_seq OWNED BY actividadmisional.codactividadmisional;


--
-- TOC entry 172 (class 1259 OID 33770)
-- Name: actividadobligatorias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE actividadobligatorias (
    codactividadobligatorias integer NOT NULL,
    nombre character varying(100),
    hora date
);


ALTER TABLE public.actividadobligatorias OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 33773)
-- Name: actividadobligatorias_codactividadobligatorias_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE actividadobligatorias_codactividadobligatorias_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actividadobligatorias_codactividadobligatorias_seq OWNER TO postgres;

--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 173
-- Name: actividadobligatorias_codactividadobligatorias_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE actividadobligatorias_codactividadobligatorias_seq OWNED BY actividadobligatorias.codactividadobligatorias;


--
-- TOC entry 174 (class 1259 OID 33775)
-- Name: asignacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE asignacion (
    codasg integer NOT NULL,
    horasclase double precision,
    codporcentaje integer,
    coddocente integer,
    totalhc double precision,
    preparacion double precision,
    totalprep double precision,
    capacitacion double precision,
    totalcapa double precision,
    colectivo double precision,
    totalcolectivo double precision,
    investigacion double precision,
    totalinv double precision,
    social double precision,
    totalsocial double precision,
    oda double precision,
    totaloda double precision,
    planeacion double precision,
    totalplan double precision,
    virtualidad double precision,
    totalvirt double precision,
    comites double precision,
    totalcom double precision
);


ALTER TABLE public.asignacion OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 33778)
-- Name: asignacion_codasg_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE asignacion_codasg_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.asignacion_codasg_seq OWNER TO postgres;

--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 175
-- Name: asignacion_codasg_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE asignacion_codasg_seq OWNED BY asignacion.codasg;


--
-- TOC entry 176 (class 1259 OID 33780)
-- Name: clases; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE clases (
    codclase integer NOT NULL,
    dia integer NOT NULL,
    nombre character varying(100) NOT NULL,
    coddocente integer,
    codconvencion integer,
    horainicio timestamp without time zone,
    horafinal timestamp without time zone
);


ALTER TABLE public.clases OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 33783)
-- Name: clases_codclase_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE clases_codclase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clases_codclase_seq OWNER TO postgres;

--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 177
-- Name: clases_codclase_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE clases_codclase_seq OWNED BY clases.codclase;


--
-- TOC entry 178 (class 1259 OID 33785)
-- Name: convenciones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE convenciones (
    codconvencion integer NOT NULL,
    nombre character varying(50) NOT NULL,
    color character varying(45) NOT NULL
);


ALTER TABLE public.convenciones OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 33788)
-- Name: convenciones_codconvencion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE convenciones_codconvencion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.convenciones_codconvencion_seq OWNER TO postgres;

--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 179
-- Name: convenciones_codconvencion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE convenciones_codconvencion_seq OWNED BY convenciones.codconvencion;


--
-- TOC entry 180 (class 1259 OID 33790)
-- Name: coordinacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE coordinacion (
    codcoordinacion integer NOT NULL,
    nombre character varying(100),
    codfacultad integer
);


ALTER TABLE public.coordinacion OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 33793)
-- Name: coordinacion_codcoordinacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE coordinacion_codcoordinacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.coordinacion_codcoordinacion_seq OWNER TO postgres;

--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 181
-- Name: coordinacion_codcoordinacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE coordinacion_codcoordinacion_seq OWNED BY coordinacion.codcoordinacion;


--
-- TOC entry 182 (class 1259 OID 33795)
-- Name: docentes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE docentes (
    cedula integer NOT NULL,
    apellidos character varying(100) NOT NULL,
    nombres character varying(100) NOT NULL,
    codigo character varying(50),
    formacion text,
    direccion character varying(100),
    telefono character varying(50),
    correo character varying(100),
    codcoordinacion integer DEFAULT 0 NOT NULL,
    foto character varying(200),
    fechanac date,
    tipo_contrato numeric,
    celular numeric,
    tipo_doc numeric,
    lugar_exp character varying(100),
    municipio character varying(100),
    lugar_nac character varying(200),
    genero numeric,
    fecha_exp date,
    matricula_prof character varying(250)
);


ALTER TABLE public.docentes OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 33802)
-- Name: docentes_cedula_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE docentes_cedula_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.docentes_cedula_seq OWNER TO postgres;

--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 183
-- Name: docentes_cedula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE docentes_cedula_seq OWNED BY docentes.cedula;


--
-- TOC entry 184 (class 1259 OID 33804)
-- Name: experiencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE experiencia (
    cod_experiencia integer NOT NULL,
    coddocente numeric,
    institucion character varying(150),
    actividad character varying(50),
    dependencia character varying(100),
    tiempo numeric,
    tipo_contrato character(1),
    fecha_ultima date,
    tipo numeric
);


ALTER TABLE public.experiencia OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 33810)
-- Name: experiencia_cod_experiencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE experiencia_cod_experiencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.experiencia_cod_experiencia_seq OWNER TO postgres;

--
-- TOC entry 2191 (class 0 OID 0)
-- Dependencies: 185
-- Name: experiencia_cod_experiencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE experiencia_cod_experiencia_seq OWNED BY experiencia.cod_experiencia;


--
-- TOC entry 186 (class 1259 OID 33812)
-- Name: facultad; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE facultad (
    codfacultad integer NOT NULL,
    nombre character varying(100),
    abreviatura character varying(10)
);


ALTER TABLE public.facultad OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 33815)
-- Name: facultad_codfacultad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE facultad_codfacultad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.facultad_codfacultad_seq OWNER TO postgres;

--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 187
-- Name: facultad_codfacultad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE facultad_codfacultad_seq OWNED BY facultad.codfacultad;


--
-- TOC entry 188 (class 1259 OID 33817)
-- Name: horario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE horario (
    codhorario integer NOT NULL,
    dia character varying(45),
    nombre character varying(60),
    hora_inicio date,
    hora_fin date,
    coddocente integer,
    codconvencion integer
);


ALTER TABLE public.horario OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 33820)
-- Name: horario_codhorario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE horario_codhorario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.horario_codhorario_seq OWNER TO postgres;

--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 189
-- Name: horario_codhorario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE horario_codhorario_seq OWNED BY horario.codhorario;


--
-- TOC entry 190 (class 1259 OID 33822)
-- Name: info_academica; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE info_academica (
    nivel character(3),
    titulo character varying(200),
    institucion character varying(100),
    "año_grado" date,
    registro character varying(50),
    pais character varying,
    horas double precision,
    tipo numeric,
    cod_docente numeric,
    codcademico integer NOT NULL
);


ALTER TABLE public.info_academica OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 33828)
-- Name: info_academica_codcademico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE info_academica_codcademico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.info_academica_codcademico_seq OWNER TO postgres;

--
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 191
-- Name: info_academica_codcademico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE info_academica_codcademico_seq OWNED BY info_academica.codcademico;


--
-- TOC entry 192 (class 1259 OID 33830)
-- Name: participacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE participacion (
    codparticipacion integer NOT NULL,
    coddocente numeric,
    tipo_part numeric,
    tipo numeric,
    evento character varying(200),
    tema character varying,
    fecha date,
    ambito character varying(100),
    "desempeño" character varying(3),
    dedicacion double precision,
    activ_culturales character varying(200),
    dedica_cultural character varying(200)
);


ALTER TABLE public.participacion OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 33836)
-- Name: participacion_codparticipacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE participacion_codparticipacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.participacion_codparticipacion_seq OWNER TO postgres;

--
-- TOC entry 2195 (class 0 OID 0)
-- Dependencies: 193
-- Name: participacion_codparticipacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE participacion_codparticipacion_seq OWNED BY participacion.codparticipacion;


--
-- TOC entry 194 (class 1259 OID 33838)
-- Name: permisos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE permisos (
    codpermiso integer NOT NULL,
    usuario character varying(100) NOT NULL,
    clave character(255) NOT NULL,
    rol character varying(100) NOT NULL
);


ALTER TABLE public.permisos OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 33841)
-- Name: permisos_codpermiso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE permisos_codpermiso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permisos_codpermiso_seq OWNER TO postgres;

--
-- TOC entry 2196 (class 0 OID 0)
-- Dependencies: 195
-- Name: permisos_codpermiso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE permisos_codpermiso_seq OWNED BY permisos.codpermiso;


--
-- TOC entry 196 (class 1259 OID 33843)
-- Name: porcentaje; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE porcentaje (
    codporcentaje integer NOT NULL,
    porcentaje double precision,
    codcoordinacion integer,
    codactividadmisional integer
);


ALTER TABLE public.porcentaje OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 33846)
-- Name: porcentaje_codporcentaje_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE porcentaje_codporcentaje_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.porcentaje_codporcentaje_seq OWNER TO postgres;

--
-- TOC entry 2197 (class 0 OID 0)
-- Dependencies: 197
-- Name: porcentaje_codporcentaje_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE porcentaje_codporcentaje_seq OWNED BY porcentaje.codporcentaje;


--
-- TOC entry 210 (class 1259 OID 41948)
-- Name: porcentajeasig; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE porcentajeasig (
    codporcentaje_asig integer NOT NULL,
    investigacion double precision,
    extension double precision,
    comites double precision,
    oda double precision,
    acreditacion double precision,
    virtualidad double precision,
    codcoordinacion integer
);


ALTER TABLE public.porcentajeasig OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 33848)
-- Name: produccion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produccion (
    codproduccion integer NOT NULL,
    coddocente numeric,
    tipo_prod_part numeric,
    nombre character varying,
    editorial_inv character varying(200),
    libros character varying(100),
    "año" date,
    meses date,
    estado character varying,
    tipo numeric
);


ALTER TABLE public.produccion OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 33854)
-- Name: produccion_codproduccion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produccion_codproduccion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produccion_codproduccion_seq OWNER TO postgres;

--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 199
-- Name: produccion_codproduccion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produccion_codproduccion_seq OWNED BY produccion.codproduccion;


--
-- TOC entry 200 (class 1259 OID 33856)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE productos (
    codproducto integer NOT NULL,
    fechacompromiso date,
    fechaentrega date,
    comentarios text,
    codact integer NOT NULL,
    descripcion character varying(300)
);


ALTER TABLE public.productos OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 33862)
-- Name: productos_codproducto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE productos_codproducto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_codproducto_seq OWNER TO postgres;

--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 201
-- Name: productos_codproducto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE productos_codproducto_seq OWNED BY productos.codproducto;


--
-- TOC entry 202 (class 1259 OID 33864)
-- Name: semana; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE semana (
    codsemana integer NOT NULL,
    fecha_inicio date,
    fecha_fin date
);


ALTER TABLE public.semana OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 33867)
-- Name: semana_codsemana_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE semana_codsemana_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.semana_codsemana_seq OWNER TO postgres;

--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 203
-- Name: semana_codsemana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE semana_codsemana_seq OWNED BY semana.codsemana;


--
-- TOC entry 204 (class 1259 OID 33869)
-- Name: socializacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE socializacion (
    codsocializar integer NOT NULL,
    coddocente numeric,
    articulo character varying(200),
    conferencia character varying(200),
    informe text
);


ALTER TABLE public.socializacion OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 33875)
-- Name: socializacion_codsocializar_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE socializacion_codsocializar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.socializacion_codsocializar_seq OWNER TO postgres;

--
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 205
-- Name: socializacion_codsocializar_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE socializacion_codsocializar_seq OWNED BY socializacion.codsocializar;


--
-- TOC entry 206 (class 1259 OID 33877)
-- Name: tiempoasignado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tiempoasignado (
    codtiempoasignado integer NOT NULL,
    horas date,
    codporcentaje integer,
    coddocente integer,
    codobligatoria integer
);


ALTER TABLE public.tiempoasignado OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 33880)
-- Name: tiempoasignado_codtiempoasignado_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiempoasignado_codtiempoasignado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tiempoasignado_codtiempoasignado_seq OWNER TO postgres;

--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 207
-- Name: tiempoasignado_codtiempoasignado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tiempoasignado_codtiempoasignado_seq OWNED BY tiempoasignado.codtiempoasignado;


--
-- TOC entry 208 (class 1259 OID 33882)
-- Name: tipomodalidades; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipomodalidades (
    codtipo integer NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE public.tipomodalidades OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 33885)
-- Name: tipomodalidades_codtipo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipomodalidades_codtipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipomodalidades_codtipo_seq OWNER TO postgres;

--
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 209
-- Name: tipomodalidades_codtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipomodalidades_codtipo_seq OWNED BY tipomodalidades.codtipo;


--
-- TOC entry 2048 (class 2604 OID 33887)
-- Name: codactividad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividades ALTER COLUMN codactividad SET DEFAULT nextval('actividades_codactividad_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 33888)
-- Name: codactividadmisional; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividadmisional ALTER COLUMN codactividadmisional SET DEFAULT nextval('actividadmisional_codactividadmisional_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 33889)
-- Name: codactividadobligatorias; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividadobligatorias ALTER COLUMN codactividadobligatorias SET DEFAULT nextval('actividadobligatorias_codactividadobligatorias_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 33890)
-- Name: codasg; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY asignacion ALTER COLUMN codasg SET DEFAULT nextval('asignacion_codasg_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 33891)
-- Name: codclase; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clases ALTER COLUMN codclase SET DEFAULT nextval('clases_codclase_seq'::regclass);


--
-- TOC entry 2053 (class 2604 OID 33892)
-- Name: codconvencion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY convenciones ALTER COLUMN codconvencion SET DEFAULT nextval('convenciones_codconvencion_seq'::regclass);


--
-- TOC entry 2054 (class 2604 OID 33893)
-- Name: codcoordinacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coordinacion ALTER COLUMN codcoordinacion SET DEFAULT nextval('coordinacion_codcoordinacion_seq'::regclass);


--
-- TOC entry 2056 (class 2604 OID 33894)
-- Name: cedula; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY docentes ALTER COLUMN cedula SET DEFAULT nextval('docentes_cedula_seq'::regclass);


--
-- TOC entry 2057 (class 2604 OID 33895)
-- Name: cod_experiencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY experiencia ALTER COLUMN cod_experiencia SET DEFAULT nextval('experiencia_cod_experiencia_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 33896)
-- Name: codfacultad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY facultad ALTER COLUMN codfacultad SET DEFAULT nextval('facultad_codfacultad_seq'::regclass);


--
-- TOC entry 2059 (class 2604 OID 33897)
-- Name: codhorario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY horario ALTER COLUMN codhorario SET DEFAULT nextval('horario_codhorario_seq'::regclass);


--
-- TOC entry 2060 (class 2604 OID 33898)
-- Name: codcademico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY info_academica ALTER COLUMN codcademico SET DEFAULT nextval('info_academica_codcademico_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 33899)
-- Name: codparticipacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participacion ALTER COLUMN codparticipacion SET DEFAULT nextval('participacion_codparticipacion_seq'::regclass);


--
-- TOC entry 2062 (class 2604 OID 33900)
-- Name: codpermiso; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permisos ALTER COLUMN codpermiso SET DEFAULT nextval('permisos_codpermiso_seq'::regclass);


--
-- TOC entry 2063 (class 2604 OID 33901)
-- Name: codporcentaje; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY porcentaje ALTER COLUMN codporcentaje SET DEFAULT nextval('porcentaje_codporcentaje_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 33902)
-- Name: codproduccion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produccion ALTER COLUMN codproduccion SET DEFAULT nextval('produccion_codproduccion_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 33903)
-- Name: codproducto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos ALTER COLUMN codproducto SET DEFAULT nextval('productos_codproducto_seq'::regclass);


--
-- TOC entry 2066 (class 2604 OID 33904)
-- Name: codsemana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY semana ALTER COLUMN codsemana SET DEFAULT nextval('semana_codsemana_seq'::regclass);


--
-- TOC entry 2067 (class 2604 OID 33905)
-- Name: codsocializar; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY socializacion ALTER COLUMN codsocializar SET DEFAULT nextval('socializacion_codsocializar_seq'::regclass);


--
-- TOC entry 2068 (class 2604 OID 33906)
-- Name: codtiempoasignado; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiempoasignado ALTER COLUMN codtiempoasignado SET DEFAULT nextval('tiempoasignado_codtiempoasignado_seq'::regclass);


--
-- TOC entry 2069 (class 2604 OID 33907)
-- Name: codtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipomodalidades ALTER COLUMN codtipo SET DEFAULT nextval('tipomodalidades_codtipo_seq'::regclass);


--
-- TOC entry 2132 (class 0 OID 33757)
-- Dependencies: 168
-- Data for Name: actividades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actividades (codactividad, nombre, descripcion, responsable, coddocente, codtipo, horas, valoracion) FROM stdin;
22	Participación Comité Trabajos de Grado TSI	Participar en comités para decidir temas, evaluadores y dictaminar evaluaciones	Coordinador de Sistemas	73167775	4	2	\N
20	Tutorías a estudiantes	Asignaturas: POO, Programación en Java y Desarrollo de Aplicaciones Empresariales	Director ODA	73167775	4	3	\N
21	Orientación de Trabajos de Grado TSI	Orientar trabajos de TSI modalidad investigación	Coordinador de Sistemas	73167775	4	4	\N
18	Elaboración Artículo de Computación en la Nube	Escribir artículo de investigación para publicación	Director Investigaciones	73167775	5	6	\N
12	Orientación de clase - Programación Web	TSI013. Grupo D191; Mart SINF: XII(15:00)	Coordinador de Sistemas	73167775	1	4	\N
19	Capacitación a Docentes en la línea de desarrollo softwre	Capacitación a los docentes: Luz E. Gutiérrez L. y Johanna M. Suárez Pedraza	Coordinador de Sistemas	73167775	4	4	\N
13	Orientación de clase - Programación Web	TSI013. Grupo D192; Juev SINF: VII(15:00)	Coordinador de Sistemas	73167775	1	4	\N
16	Orientación de clase - Desarrollo de Aplicaiones Empresariales	TSI016. Grupo E193	Coordinador de Sistemas	73167775	1	6	\N
14	Orientación de clase - Programación Web	TSI013. Grupo E191; Mier SINF: XII(18:00)	Coordinador de Sistemas	73167775	1	4	\N
17	Preparación de Clases	Diseño y evaluación de guías de estudio	Coordinador de Sistemas	73167775	1	4	\N
27	probando123	nuevo cambio	Coordinador de Sistemas	109877	5	\N	\N
31	fsgdfgd	gfgdfgfg	gdsgfdg	109877	1	4	\N
33	prueba2	sgfgdg	gdgf	109877	5	5	3
36	yryrtyrty	hsgsdgfdgds	gfsdgfdg	109877	6	5	2
34	ttt	teye	dgf	109877	5	1	4
35	tyuuty	seg	trey	109877	1	3	1
37	5tewet	fefw	fsafsaf	109877	5	1	7
38	rere	fewf	wre	109877	5	8	2
23	Revisión temas y propuestas Comité Trabajos de Grado	Revisión de temas, propuestas e informes finales de los proyectos presentados en el Comité de Trabajos de Grado	Coordinador de Sistemas	109877	6	2.9999989999999999	\N
24	Participación Comité Curricular TSI	Participar en comités para decidir temas de la carrera	Coordinador de Sistemas	109877	1	2.7999999999999998	6
40	prueba	iuhiuh	hbiuhb	109877	6	6.9999000000000002	7
32	mlkm	gfu mod	okpl,	109877	1	9	\N
42	tesis	estudio	coordinacion	109877	5	3.3999999999999999	0
\.


--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 169
-- Name: actividades_codactividad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('actividades_codactividad_seq', 42, true);


--
-- TOC entry 2134 (class 0 OID 33765)
-- Dependencies: 170
-- Data for Name: actividadmisional; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actividadmisional (codactividadmisional, nombre) FROM stdin;
1	Investigación
4	Extensión
5	Comités
6	ODA
7	Acreditación
8	Virtualidad
\.


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 171
-- Name: actividadmisional_codactividadmisional_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('actividadmisional_codactividadmisional_seq', 8, true);


--
-- TOC entry 2136 (class 0 OID 33770)
-- Dependencies: 172
-- Data for Name: actividadobligatorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY actividadobligatorias (codactividadobligatorias, nombre, hora) FROM stdin;
1	gsgfdgf	2000-02-02
2	fsf	2015-11-07
5	hfgj	1970-01-01
\.


--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 173
-- Name: actividadobligatorias_codactividadobligatorias_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('actividadobligatorias_codactividadobligatorias_seq', 5, true);


--
-- TOC entry 2138 (class 0 OID 33775)
-- Dependencies: 174
-- Data for Name: asignacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY asignacion (codasg, horasclase, codporcentaje, coddocente, totalhc, preparacion, totalprep, capacitacion, totalcapa, colectivo, totalcolectivo, investigacion, totalinv, social, totalsocial, oda, totaloda, planeacion, totalplan, virtualidad, totalvirt, comites, totalcom) FROM stdin;
4	24	\N	\N	\N	5.7999999999999998	\N	\N	\N	\N	\N	\N	46.800000000000004	\N	12	\N	18	\N	24	\N	6	\N	13.199999999999999
6	12	\N	\N	\N	85.299999999999997	\N	\N	\N	\N	\N	2.1000000000000001	46.800000000000004	\N	12	\N	18	\N	24	\N	6	\N	13.199999999999999
7	2.1000000000000001	\N	7576	\N	2.1000000000000001	\N	55.100000000000001	\N	\N	\N	5.2000000000000002	46.800000000000004	\N	12	\N	18	\N	24	\N	6	\N	13.199999999999999
5	5.2999999999999998	\N	73167775	\N	4	\N	4.5	\N	21.100000000000001	\N	7.2000000000000002	46.800000000000004	5.2000000000000002	12	2.1000000000000001	18	4.5	24	2.1000000000000001	6	4	13.199999999999999
1	\N	\N	73167775	\N	\N	\N	\N	\N	\N	\N	\N	48	\N	12	\N	18	\N	24	\N	6	\N	12
2	\N	\N	109877	\N	\N	\N	\N	\N	\N	\N	\N	48	\N	12	\N	18	\N	24	\N	6	\N	12
3	24	\N	654656465	\N	4	\N	\N	\N	\N	\N	\N	48	\N	12	\N	18	\N	24	\N	6	\N	12
\.


--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 175
-- Name: asignacion_codasg_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('asignacion_codasg_seq', 3, true);


--
-- TOC entry 2140 (class 0 OID 33780)
-- Dependencies: 176
-- Data for Name: clases; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY clases (codclase, dia, nombre, coddocente, codconvencion, horainicio, horafinal) FROM stdin;
53	0	prueba	109877	19	2015-11-08 20:36:00	2015-11-08 17:40:00
21	1	Preparación de Clases	73167775	16	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
12	1	Capacitación a Docentes	73167775	21	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
22	1	Capacitación a Docentes	73167775	21	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
20	1	DAE ED B SINF: XII E193	73167775	15	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
23	2	Programacion Web ED B SINF: XII D191	73167775	15	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
24	2	Programacion Web ED B SINF: XVI E192	73167775	15	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
25	3	Artículo	73167775	24	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
27	3	Programacion Web ED B SINF: XII E191	73167775	15	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
28	4	Preparación de Clases	73167775	16	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
29	4	Artículo	73167775	24	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
31	4	Programación Web ED B SINF: VIII D192	73167775	15	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
32	4	DAE dirigido ED C: 503	73167775	15	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
33	5	Doctorado Ingeniería UPB Doctorado	73167775	22	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
47	0	Segunda clase	109877	19	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
48	0	tercera	109877	19	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
49	0	cuarta	109877	17	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
51	0	nueva1	109877	20	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
45	3	Primera Clase	109877	17	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
46	0	prueba	109877	20	2015-11-07 10:30:00.59	2015-11-07 18:30:00.59
54	0	ghn	109877	19	2015-11-15 17:00:00	2015-11-15 16:00:00
\.


--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 177
-- Name: clases_codclase_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('clases_codclase_seq', 54, true);


--
-- TOC entry 2142 (class 0 OID 33785)
-- Dependencies: 178
-- Data for Name: convenciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY convenciones (codconvencion, nombre, color) FROM stdin;
15	Orientación de Clases	#2B4E04
16	Preparación de Clases	#82A857
17	Revisión Curricular	#916AA4
19	Proyecto PAD	#EE6D04
20	Actividades OACA	#DB8787
21	Capacitación a Docentes	#761C1C
22	Participación en Capacitación	#847334
23	Actividades Proyección Social	#F5CFAA
24	Actividades Investigación	#0D80AE
18	Reunión Equipo ODA	#4B1E60
31	Otros	39c4fb
34	yytu6y	b36161
\.


--
-- TOC entry 2209 (class 0 OID 0)
-- Dependencies: 179
-- Name: convenciones_codconvencion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('convenciones_codconvencion_seq', 34, true);


--
-- TOC entry 2144 (class 0 OID 33790)
-- Dependencies: 180
-- Data for Name: coordinacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY coordinacion (codcoordinacion, nombre, codfacultad) FROM stdin;
1	sistemas	1
3	administracion	1
4	gfhhf	5
\.


--
-- TOC entry 2210 (class 0 OID 0)
-- Dependencies: 181
-- Name: coordinacion_codcoordinacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('coordinacion_codcoordinacion_seq', 4, true);


--
-- TOC entry 2146 (class 0 OID 33795)
-- Dependencies: 182
-- Data for Name: docentes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY docentes (cedula, apellidos, nombres, codigo, formacion, direccion, telefono, correo, codcoordinacion, foto, fechanac, tipo_contrato, celular, tipo_doc, lugar_exp, municipio, lugar_nac, genero, fecha_exp, matricula_prof) FROM stdin;
654656465	hfdhfghh	pppppp	\N	\N	\N	\N	\N	3	\N	\N	1	\N	\N	\N	\N	\N	\N	\N	\N
7576	jgj	gjgj	767	utfuuu	68	86787	tfry	3	yht	2015-11-30	1	\N	\N	\N	\N	\N	\N	\N	\N
222	calamidades	lola	\N	\N	\N	\N	\N	1	\N	\N	2	\N	\N	\N	\N	\N	\N	\N	\N
123456	sistemas	coordinador 	\N	\N	\N	\N	\N	1	\N	\N	1	\N	\N	\N	\N	\N	\N	\N	\N
109877	cala	sandy¿icitsa	641	Estudiante	calle 2 A N # 11-10	3157281523	paulin-206@hotmail.com	1	novia1.png	2015-11-30	2	\N	\N	\N	\N	\N	\N	\N	\N
73167775	Guerrero Alarcón	Carlos Andrés	\N	Ingeniero de Sistemas\r\nEspecialista en Seguridad Informática\r\nMagíster en Informática\r\nDoctor en Ingeniería(Candidato)	Suite Cañaveral, Condominio 506 #14-54	3017900437	anguerrco@msn.com	1	\N	2015-11-30	1	\N	\N	\N	\N	\N	\N	\N	\N
546	erqwrwer	twrht	\N	\N	\N	\N	\N	3	\N	\N	1	\N	\N	\N	\N	\N	\N	\N	\N
9887777	otro2	prueba	\N	\N	\N	\N	\N	1	\N	\N	1	\N	\N	\N	\N	\N	\N	\N	\N
555555	sfd	fwe	\N	\N	\N	\N	\N	3	\N	\N	2	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 2211 (class 0 OID 0)
-- Dependencies: 183
-- Name: docentes_cedula_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('docentes_cedula_seq', 4, true);


--
-- TOC entry 2148 (class 0 OID 33804)
-- Dependencies: 184
-- Data for Name: experiencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY experiencia (cod_experiencia, coddocente, institucion, actividad, dependencia, tiempo, tipo_contrato, fecha_ultima, tipo) FROM stdin;
\.


--
-- TOC entry 2212 (class 0 OID 0)
-- Dependencies: 185
-- Name: experiencia_cod_experiencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('experiencia_cod_experiencia_seq', 1, false);


--
-- TOC entry 2150 (class 0 OID 33812)
-- Dependencies: 186
-- Data for Name: facultad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY facultad (codfacultad, nombre, abreviatura) FROM stdin;
1	el primero	first
3	terceo	3ero
5	hfhfg	hgf
\.


--
-- TOC entry 2213 (class 0 OID 0)
-- Dependencies: 187
-- Name: facultad_codfacultad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('facultad_codfacultad_seq', 5, true);


--
-- TOC entry 2152 (class 0 OID 33817)
-- Dependencies: 188
-- Data for Name: horario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY horario (codhorario, dia, nombre, hora_inicio, hora_fin, coddocente, codconvencion) FROM stdin;
1	lunes	ghgf	2017-04-22	2017-04-23	109877	17
2	lunes	ghdhfg	2017-04-23	2017-04-23	109877	22
3	lunes	dgfdg	2017-04-22	2017-04-23	109877	15
5	lunes	pprueba	2017-06-13	2017-06-22	109877	15
\.


--
-- TOC entry 2214 (class 0 OID 0)
-- Dependencies: 189
-- Name: horario_codhorario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('horario_codhorario_seq', 5, true);


--
-- TOC entry 2154 (class 0 OID 33822)
-- Dependencies: 190
-- Data for Name: info_academica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY info_academica (nivel, titulo, institucion, "año_grado", registro, pais, horas, tipo, cod_docente, codcademico) FROM stdin;
\.


--
-- TOC entry 2215 (class 0 OID 0)
-- Dependencies: 191
-- Name: info_academica_codcademico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('info_academica_codcademico_seq', 1, false);


--
-- TOC entry 2156 (class 0 OID 33830)
-- Dependencies: 192
-- Data for Name: participacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY participacion (codparticipacion, coddocente, tipo_part, tipo, evento, tema, fecha, ambito, "desempeño", dedicacion, activ_culturales, dedica_cultural) FROM stdin;
\.


--
-- TOC entry 2216 (class 0 OID 0)
-- Dependencies: 193
-- Name: participacion_codparticipacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('participacion_codparticipacion_seq', 1, false);


--
-- TOC entry 2158 (class 0 OID 33838)
-- Dependencies: 194
-- Data for Name: permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY permisos (codpermiso, usuario, clave, rol) FROM stdin;
3	eva123	55c8079ac96c6a4f6a94e3460c79e4006d62374cce6e9fc8b281938a3abc7627                                                                                                                                                                                               	evaluador
10	109877	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92                                                                                                                                                                                               	docente
11	73167775	13671077b66a29874a2578b5240319092ef2a1043228e433e9b006b5e53e7513                                                                                                                                                                                               	docente
2	123456	7b81eb727ed48055fa55c5e03aaa43f27b01bd9b1c8eb38f37a1ca541a79c1f7                                                                                                                                                                                               	administrador
\.


--
-- TOC entry 2217 (class 0 OID 0)
-- Dependencies: 195
-- Name: permisos_codpermiso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permisos_codpermiso_seq', 11, true);


--
-- TOC entry 2160 (class 0 OID 33843)
-- Dependencies: 196
-- Data for Name: porcentaje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY porcentaje (codporcentaje, porcentaje, codcoordinacion, codactividadmisional) FROM stdin;
66	0.39000000000000001	1	1
67	0.10000000000000001	1	4
68	0.11	1	5
69	0.14999999999999999	1	6
70	0.20000000000000001	1	7
71	0.050000000000000003	1	8
60	0.40000000000000002	1	1
61	0.10000000000000001	1	4
62	0.10000000000000001	1	5
63	0.14999999999999999	1	6
64	0.20000000000000001	1	7
65	0.050000000000000003	1	8
\.


--
-- TOC entry 2218 (class 0 OID 0)
-- Dependencies: 197
-- Name: porcentaje_codporcentaje_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('porcentaje_codporcentaje_seq', 67, true);


--
-- TOC entry 2174 (class 0 OID 41948)
-- Dependencies: 210
-- Data for Name: porcentajeasig; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY porcentajeasig (codporcentaje_asig, investigacion, extension, comites, oda, acreditacion, virtualidad, codcoordinacion) FROM stdin;
\.


--
-- TOC entry 2162 (class 0 OID 33848)
-- Dependencies: 198
-- Data for Name: produccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produccion (codproduccion, coddocente, tipo_prod_part, nombre, editorial_inv, libros, "año", meses, estado, tipo) FROM stdin;
\.


--
-- TOC entry 2219 (class 0 OID 0)
-- Dependencies: 199
-- Name: produccion_codproduccion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produccion_codproduccion_seq', 1, false);


--
-- TOC entry 2164 (class 0 OID 33856)
-- Dependencies: 200
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY productos (codproducto, fechacompromiso, fechaentrega, comentarios, codact, descripcion) FROM stdin;
18	2015-11-30	\N		17	Plan de aula actualizado de las asignaturas: Programación Web y Desarrollo de Aplicaciones Empresariales
7	2015-11-30	\N		12	Documento Plan de aula TSI013, grupo D191 y Acta final de notas TSI101 grupos D191
8	2015-11-30	\N		13	Documento Plan de aula TSI013, grupo D192 y Acta final de notas TSI101 grupos D192
9	2015-11-30	\N		14	Documento Plan de aula TSI013, grupo E191 y Acta final de notas TSI101 grupos E191
11	2015-11-30	\N		16	Documento Plan de aula TSI016, grupo E193 y Acta final de notas TSI016 grupos E193
12	2015-11-30	\N		18	Evidencias(Guías de estudio, parciales y otros)
14	2015-11-30	\N		20	Temas, propuestas e informes fnales revisados de los proyectos a cargo
16	2015-11-30	\N		22	Actas Comités Trabajos de Grado TSI
28	2017-06-01	2017-06-09	dfgdfgdfg	12	edgdsgg
15	2015-11-30	\N		21	Artículo para publicación de Computación en la Nube
13	2015-11-30	\N		19	Formatos de asistencia a tutorías
19	2015-11-30	2017-06-15		20	Actas Comités Curricular TSI
30	2017-06-08	2017-06-15	hbuh	21	pppppppp
31	2017-06-01	2017-06-22	mjumnyby	20	pureba prueba prueba
24	2017-05-01	2017-05-10	ttrtwert	33	rwerwer
25	2017-05-10	2017-05-03	rwerqwe	36	terter
27	2017-03-13	2017-03-23	gsgff	27	actividad modificado
32	2017-06-01	2017-06-08	algo es algo	33	esto es un producto
\.


--
-- TOC entry 2220 (class 0 OID 0)
-- Dependencies: 201
-- Name: productos_codproducto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('productos_codproducto_seq', 32, true);


--
-- TOC entry 2166 (class 0 OID 33864)
-- Dependencies: 202
-- Data for Name: semana; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY semana (codsemana, fecha_inicio, fecha_fin) FROM stdin;
1	2017-04-22	2017-04-26
4	2017-05-01	2017-05-04
5	2017-05-01	2017-05-06
6	2017-05-22	2017-05-30
\.


--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 203
-- Name: semana_codsemana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('semana_codsemana_seq', 3, true);


--
-- TOC entry 2168 (class 0 OID 33869)
-- Dependencies: 204
-- Data for Name: socializacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY socializacion (codsocializar, coddocente, articulo, conferencia, informe) FROM stdin;
\.


--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 205
-- Name: socializacion_codsocializar_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('socializacion_codsocializar_seq', 1, false);


--
-- TOC entry 2170 (class 0 OID 33877)
-- Dependencies: 206
-- Data for Name: tiempoasignado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiempoasignado (codtiempoasignado, horas, codporcentaje, coddocente, codobligatoria) FROM stdin;
9	\N	\N	73167775	\N
10	\N	\N	109877	\N
11	\N	\N	73167775	\N
12	\N	\N	109877	\N
13	\N	\N	73167775	\N
14	\N	\N	109877	\N
15	\N	\N	73167775	\N
16	\N	\N	109877	\N
17	\N	\N	73167775	\N
18	\N	\N	109877	\N
19	\N	\N	73167775	\N
20	\N	\N	109877	\N
\.


--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 207
-- Name: tiempoasignado_codtiempoasignado_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiempoasignado_codtiempoasignado_seq', 20, true);


--
-- TOC entry 2172 (class 0 OID 33882)
-- Dependencies: 208
-- Data for Name: tipomodalidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tipomodalidades (codtipo, nombre) FROM stdin;
1	Docencia
5	Investigación
6	Proyección Social
4	Otros
10	yfyuyj
\.


--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 209
-- Name: tipomodalidades_codtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipomodalidades_codtipo_seq', 10, true);


--
-- TOC entry 2093 (class 2606 OID 33909)
-- Name: info_academica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY info_academica
    ADD CONSTRAINT info_academica_pkey PRIMARY KEY (codcademico);


--
-- TOC entry 2113 (class 2606 OID 33911)
-- Name: pf_tipo; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipomodalidades
    ADD CONSTRAINT pf_tipo PRIMARY KEY (codtipo);


--
-- TOC entry 2071 (class 2606 OID 33913)
-- Name: pk_act; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actividades
    ADD CONSTRAINT pk_act PRIMARY KEY (codactividad);


--
-- TOC entry 2073 (class 2606 OID 33915)
-- Name: pk_actividadmisional; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actividadmisional
    ADD CONSTRAINT pk_actividadmisional PRIMARY KEY (codactividadmisional);


--
-- TOC entry 2075 (class 2606 OID 33917)
-- Name: pk_actividadobligatorias; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY actividadobligatorias
    ADD CONSTRAINT pk_actividadobligatorias PRIMARY KEY (codactividadobligatorias);


--
-- TOC entry 2079 (class 2606 OID 33919)
-- Name: pk_clase; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY clases
    ADD CONSTRAINT pk_clase PRIMARY KEY (codclase);


--
-- TOC entry 2081 (class 2606 OID 33921)
-- Name: pk_conven; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY convenciones
    ADD CONSTRAINT pk_conven PRIMARY KEY (codconvencion);


--
-- TOC entry 2083 (class 2606 OID 33923)
-- Name: pk_coordinacion; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY coordinacion
    ADD CONSTRAINT pk_coordinacion PRIMARY KEY (codcoordinacion);


--
-- TOC entry 2085 (class 2606 OID 33925)
-- Name: pk_docentes; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY docentes
    ADD CONSTRAINT pk_docentes PRIMARY KEY (cedula);


--
-- TOC entry 2087 (class 2606 OID 33927)
-- Name: pk_exp; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY experiencia
    ADD CONSTRAINT pk_exp PRIMARY KEY (cod_experiencia);


--
-- TOC entry 2089 (class 2606 OID 33929)
-- Name: pk_facultad; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY facultad
    ADD CONSTRAINT pk_facultad PRIMARY KEY (codfacultad);


--
-- TOC entry 2091 (class 2606 OID 33931)
-- Name: pk_horario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY horario
    ADD CONSTRAINT pk_horario PRIMARY KEY (codhorario);


--
-- TOC entry 2109 (class 2606 OID 33933)
-- Name: pk_part; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY socializacion
    ADD CONSTRAINT pk_part PRIMARY KEY (codsocializar);


--
-- TOC entry 2095 (class 2606 OID 33935)
-- Name: pk_participacion; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY participacion
    ADD CONSTRAINT pk_participacion PRIMARY KEY (codparticipacion);


--
-- TOC entry 2097 (class 2606 OID 33937)
-- Name: pk_per; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY permisos
    ADD CONSTRAINT pk_per PRIMARY KEY (codpermiso);


--
-- TOC entry 2101 (class 2606 OID 33939)
-- Name: pk_porcentaje; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY porcentaje
    ADD CONSTRAINT pk_porcentaje PRIMARY KEY (codporcentaje);


--
-- TOC entry 2115 (class 2606 OID 41957)
-- Name: pk_porcentajeasig; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY porcentajeasig
    ADD CONSTRAINT pk_porcentajeasig PRIMARY KEY (codporcentaje_asig);


--
-- TOC entry 2105 (class 2606 OID 33941)
-- Name: pk_prod; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT pk_prod PRIMARY KEY (codproducto);


--
-- TOC entry 2103 (class 2606 OID 33943)
-- Name: pk_produccion; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produccion
    ADD CONSTRAINT pk_produccion PRIMARY KEY (codproduccion);


--
-- TOC entry 2107 (class 2606 OID 33945)
-- Name: pk_semana; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY semana
    ADD CONSTRAINT pk_semana PRIMARY KEY (codsemana);


--
-- TOC entry 2077 (class 2606 OID 33947)
-- Name: pk_tiempoasig; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY asignacion
    ADD CONSTRAINT pk_tiempoasig PRIMARY KEY (codasg);


--
-- TOC entry 2111 (class 2606 OID 33949)
-- Name: pk_tiempoasignado; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT pk_tiempoasignado PRIMARY KEY (codtiempoasignado);


--
-- TOC entry 2099 (class 2606 OID 33951)
-- Name: uq_per; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY permisos
    ADD CONSTRAINT uq_per UNIQUE (usuario);


--
-- TOC entry 2116 (class 2606 OID 33952)
-- Name: actividades_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividades
    ADD CONSTRAINT actividades_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2117 (class 2606 OID 33957)
-- Name: actividades_codtipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY actividades
    ADD CONSTRAINT actividades_codtipo_fkey FOREIGN KEY (codtipo) REFERENCES tipomodalidades(codtipo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2120 (class 2606 OID 33962)
-- Name: cla_codclase_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clases
    ADD CONSTRAINT cla_codclase_fkey FOREIGN KEY (codconvencion) REFERENCES convenciones(codconvencion) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2121 (class 2606 OID 33967)
-- Name: clases_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clases
    ADD CONSTRAINT clases_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula) ON UPDATE CASCADE;


--
-- TOC entry 2122 (class 2606 OID 33972)
-- Name: coordinacion_codfacultad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coordinacion
    ADD CONSTRAINT coordinacion_codfacultad_fkey FOREIGN KEY (codfacultad) REFERENCES facultad(codfacultad);


--
-- TOC entry 2123 (class 2606 OID 33977)
-- Name: horario_codconvencion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_codconvencion_fkey FOREIGN KEY (codconvencion) REFERENCES convenciones(codconvencion);


--
-- TOC entry 2124 (class 2606 OID 33982)
-- Name: horario_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);


--
-- TOC entry 2125 (class 2606 OID 33987)
-- Name: porcentaje_codactividadmisional_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY porcentaje
    ADD CONSTRAINT porcentaje_codactividadmisional_fkey FOREIGN KEY (codactividadmisional) REFERENCES actividadmisional(codactividadmisional);


--
-- TOC entry 2126 (class 2606 OID 33992)
-- Name: porcentaje_codcoordinacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY porcentaje
    ADD CONSTRAINT porcentaje_codcoordinacion_fkey FOREIGN KEY (codcoordinacion) REFERENCES coordinacion(codcoordinacion);


--
-- TOC entry 2131 (class 2606 OID 41951)
-- Name: porcentajeasig_coordinacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY porcentajeasig
    ADD CONSTRAINT porcentajeasig_coordinacion_fkey FOREIGN KEY (codcoordinacion) REFERENCES coordinacion(codcoordinacion) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2127 (class 2606 OID 33997)
-- Name: productos_codact_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_codact_fkey FOREIGN KEY (codact) REFERENCES actividades(codactividad) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2118 (class 2606 OID 34002)
-- Name: tiempoasig_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY asignacion
    ADD CONSTRAINT tiempoasig_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);


--
-- TOC entry 2119 (class 2606 OID 34007)
-- Name: tiempoasig_codporcentaje_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY asignacion
    ADD CONSTRAINT tiempoasig_codporcentaje_fkey FOREIGN KEY (codporcentaje) REFERENCES porcentaje(codporcentaje);


--
-- TOC entry 2128 (class 2606 OID 34012)
-- Name: tiempoasignado_coddocente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT tiempoasignado_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);


--
-- TOC entry 2129 (class 2606 OID 34017)
-- Name: tiempoasignado_codobligatoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT tiempoasignado_codobligatoria_fkey FOREIGN KEY (codobligatoria) REFERENCES actividadobligatorias(codactividadobligatorias);


--
-- TOC entry 2130 (class 2606 OID 34022)
-- Name: tiempoasignado_codporcentaje_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT tiempoasignado_codporcentaje_fkey FOREIGN KEY (codporcentaje) REFERENCES porcentaje(codporcentaje);


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-06-14 09:29:23

--
-- PostgreSQL database dump complete
--

