PGDMP     +                    u            bd_proyecto    9.4.12    9.4.12 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    16606    bd_proyecto    DATABASE     �   CREATE DATABASE bd_proyecto WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE bd_proyecto;
          	   user_java    false                        2615    18102    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    7                        3079    11855    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    18103    actividades    TABLE       CREATE TABLE actividades (
    codactividad integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion text NOT NULL,
    responsable character varying(100) NOT NULL,
    coddocente integer,
    codtipo integer,
    horas double precision,
    valoracion integer
);
    DROP TABLE public.actividades;
       public         postgres    false    7            �            1259    18109    actividades_codactividad_seq    SEQUENCE     ~   CREATE SEQUENCE actividades_codactividad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.actividades_codactividad_seq;
       public       postgres    false    173    7            �           0    0    actividades_codactividad_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE actividades_codactividad_seq OWNED BY actividades.codactividad;
            public       postgres    false    174            �            1259    18111    actividadmisional    TABLE     q   CREATE TABLE actividadmisional (
    codactividadmisional integer NOT NULL,
    nombre character varying(100)
);
 %   DROP TABLE public.actividadmisional;
       public         postgres    false    7            �            1259    18114 *   actividadmisional_codactividadmisional_seq    SEQUENCE     �   CREATE SEQUENCE actividadmisional_codactividadmisional_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 A   DROP SEQUENCE public.actividadmisional_codactividadmisional_seq;
       public       postgres    false    7    175            �           0    0 *   actividadmisional_codactividadmisional_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE actividadmisional_codactividadmisional_seq OWNED BY actividadmisional.codactividadmisional;
            public       postgres    false    176            �            1259    18116    actividadobligatorias    TABLE     �   CREATE TABLE actividadobligatorias (
    codactividadobligatorias integer NOT NULL,
    nombre character varying(100),
    hora date
);
 )   DROP TABLE public.actividadobligatorias;
       public         postgres    false    7            �            1259    18119 2   actividadobligatorias_codactividadobligatorias_seq    SEQUENCE     �   CREATE SEQUENCE actividadobligatorias_codactividadobligatorias_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 I   DROP SEQUENCE public.actividadobligatorias_codactividadobligatorias_seq;
       public       postgres    false    7    177            �           0    0 2   actividadobligatorias_codactividadobligatorias_seq    SEQUENCE OWNED BY     {   ALTER SEQUENCE actividadobligatorias_codactividadobligatorias_seq OWNED BY actividadobligatorias.codactividadobligatorias;
            public       postgres    false    178            �            1259    18121 
   asignacion    TABLE     �  CREATE TABLE asignacion (
    codasg integer NOT NULL,
    horasclase double precision,
    coddocente integer,
    preparacion double precision,
    capacitacion double precision,
    colectivo double precision,
    investigacion double precision,
    social double precision,
    oda double precision,
    planeacion double precision,
    virtualidad double precision,
    comites double precision,
    codcoord integer,
    sumatoria double precision
);
    DROP TABLE public.asignacion;
       public         postgres    false    7            �            1259    18124    asignacion_codasg_seq    SEQUENCE     w   CREATE SEQUENCE asignacion_codasg_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.asignacion_codasg_seq;
       public       postgres    false    7    179            �           0    0    asignacion_codasg_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE asignacion_codasg_seq OWNED BY asignacion.codasg;
            public       postgres    false    180            �            1259    18126    clases    TABLE       CREATE TABLE clases (
    codclase integer NOT NULL,
    dia integer NOT NULL,
    nombre character varying(100) NOT NULL,
    coddocente integer,
    codconvencion integer,
    horainicio timestamp without time zone,
    horafinal timestamp without time zone
);
    DROP TABLE public.clases;
       public         postgres    false    7            �            1259    18129    clases_codclase_seq    SEQUENCE     u   CREATE SEQUENCE clases_codclase_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.clases_codclase_seq;
       public       postgres    false    181    7            �           0    0    clases_codclase_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE clases_codclase_seq OWNED BY clases.codclase;
            public       postgres    false    182            �            1259    18131    convenciones    TABLE     �   CREATE TABLE convenciones (
    codconvencion integer NOT NULL,
    nombre character varying(50) NOT NULL,
    color character varying(45) NOT NULL
);
     DROP TABLE public.convenciones;
       public         postgres    false    7            �            1259    18134    convenciones_codconvencion_seq    SEQUENCE     �   CREATE SEQUENCE convenciones_codconvencion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.convenciones_codconvencion_seq;
       public       postgres    false    183    7            �           0    0    convenciones_codconvencion_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE convenciones_codconvencion_seq OWNED BY convenciones.codconvencion;
            public       postgres    false    184            �            1259    18136    coordinacion    TABLE     �  CREATE TABLE coordinacion (
    codcoordinacion integer NOT NULL,
    nombre character varying(100),
    codfacultad integer,
    investigacion double precision DEFAULT 0,
    extension double precision DEFAULT 0,
    comites double precision DEFAULT 0,
    oda double precision DEFAULT 0,
    acreditacion double precision DEFAULT 0,
    virtualidad double precision DEFAULT 0,
    asignado boolean DEFAULT false
);
     DROP TABLE public.coordinacion;
       public         postgres    false    7            �           0    0    COLUMN coordinacion.extension    COMMENT     1   COMMENT ON COLUMN coordinacion.extension IS '
';
            public       postgres    false    185            �            1259    18146     coordinacion_codcoordinacion_seq    SEQUENCE     �   CREATE SEQUENCE coordinacion_codcoordinacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.coordinacion_codcoordinacion_seq;
       public       postgres    false    185    7            �           0    0     coordinacion_codcoordinacion_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE coordinacion_codcoordinacion_seq OWNED BY coordinacion.codcoordinacion;
            public       postgres    false    186            �            1259    18148    docentes    TABLE     �  CREATE TABLE docentes (
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
    matricula_prof character varying
);
    DROP TABLE public.docentes;
       public         postgres    false    7            �            1259    18155    docentes_cedula_seq    SEQUENCE     u   CREATE SEQUENCE docentes_cedula_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.docentes_cedula_seq;
       public       postgres    false    7    187            �           0    0    docentes_cedula_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE docentes_cedula_seq OWNED BY docentes.cedula;
            public       postgres    false    188            �            1259    18157    experiencia    TABLE     -  CREATE TABLE experiencia (
    cod_experiencia integer NOT NULL,
    coddocente numeric,
    institucion character varying(150),
    actividad character varying(50),
    dependencia character varying(100),
    tiempo numeric,
    fecha_ultima date,
    tipo numeric,
    tipo_contrato character(3)
);
    DROP TABLE public.experiencia;
       public         postgres    false    7            �            1259    18163    experiencia_cod_experiencia_seq    SEQUENCE     �   CREATE SEQUENCE experiencia_cod_experiencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.experiencia_cod_experiencia_seq;
       public       postgres    false    7    189            �           0    0    experiencia_cod_experiencia_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE experiencia_cod_experiencia_seq OWNED BY experiencia.cod_experiencia;
            public       postgres    false    190            �            1259    18165    facultad    TABLE     �   CREATE TABLE facultad (
    codfacultad integer NOT NULL,
    nombre character varying(100),
    abreviatura character varying(10)
);
    DROP TABLE public.facultad;
       public         postgres    false    7            �            1259    18168    facultad_codfacultad_seq    SEQUENCE     z   CREATE SEQUENCE facultad_codfacultad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.facultad_codfacultad_seq;
       public       postgres    false    191    7            �           0    0    facultad_codfacultad_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE facultad_codfacultad_seq OWNED BY facultad.codfacultad;
            public       postgres    false    192            �            1259    18170    horario    TABLE     �   CREATE TABLE horario (
    codhorario integer NOT NULL,
    nombre character varying(60),
    coddocente integer,
    codconvencion integer,
    dia integer,
    hora integer
);
    DROP TABLE public.horario;
       public         postgres    false    7            �            1259    18173    horario_codhorario_seq    SEQUENCE     x   CREATE SEQUENCE horario_codhorario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.horario_codhorario_seq;
       public       postgres    false    193    7            �           0    0    horario_codhorario_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE horario_codhorario_seq OWNED BY horario.codhorario;
            public       postgres    false    194            �            1259    18175    info_academica    TABLE     @  CREATE TABLE info_academica (
    nivel character(3),
    titulo character varying(200),
    institucion character varying(100),
    registro character varying(50),
    pais character varying,
    horas double precision,
    tipo numeric,
    cod_docente numeric,
    codcademico integer NOT NULL,
    ano_grado date
);
 "   DROP TABLE public.info_academica;
       public         postgres    false    7            �            1259    18181    info_academica_codcademico_seq    SEQUENCE     �   CREATE SEQUENCE info_academica_codcademico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.info_academica_codcademico_seq;
       public       postgres    false    7    195            �           0    0    info_academica_codcademico_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE info_academica_codcademico_seq OWNED BY info_academica.codcademico;
            public       postgres    false    196            �            1259    18183    participacion    TABLE     �  CREATE TABLE participacion (
    codparticipacion integer NOT NULL,
    coddocente numeric,
    tipo_part numeric,
    tipo numeric,
    evento character varying(200),
    tema character varying,
    fecha date,
    ambito character varying(100),
    dedicacion double precision,
    activ_culturales character varying(200),
    dedica_cultural character varying(200),
    desempeno character varying(3)
);
 !   DROP TABLE public.participacion;
       public         postgres    false    7            �            1259    18189 "   participacion_codparticipacion_seq    SEQUENCE     �   CREATE SEQUENCE participacion_codparticipacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.participacion_codparticipacion_seq;
       public       postgres    false    197    7            �           0    0 "   participacion_codparticipacion_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE participacion_codparticipacion_seq OWNED BY participacion.codparticipacion;
            public       postgres    false    198            �            1259    18191    permisos    TABLE     �   CREATE TABLE permisos (
    codpermiso integer NOT NULL,
    usuario character varying(100) NOT NULL,
    clave character(255) NOT NULL,
    rol character varying(100) NOT NULL
);
    DROP TABLE public.permisos;
       public         postgres    false    7            �            1259    18194    permisos_codpermiso_seq    SEQUENCE     y   CREATE SEQUENCE permisos_codpermiso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.permisos_codpermiso_seq;
       public       postgres    false    7    199            �           0    0    permisos_codpermiso_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE permisos_codpermiso_seq OWNED BY permisos.codpermiso;
            public       postgres    false    200            �            1259    18196 
   porcentaje    TABLE     �   CREATE TABLE porcentaje (
    codporcentaje integer NOT NULL,
    porcentaje double precision,
    codcoordinacion integer,
    codactividadmisional integer
);
    DROP TABLE public.porcentaje;
       public         postgres    false    7            �            1259    18199    porcentaje_codporcentaje_seq    SEQUENCE     ~   CREATE SEQUENCE porcentaje_codporcentaje_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.porcentaje_codporcentaje_seq;
       public       postgres    false    7    201            �           0    0    porcentaje_codporcentaje_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE porcentaje_codporcentaje_seq OWNED BY porcentaje.codporcentaje;
            public       postgres    false    202            �            1259    18201    porcentajeasig    TABLE     '  CREATE TABLE porcentajeasig (
    codporcentaje_asig integer NOT NULL,
    investigacion double precision,
    extension double precision,
    comites double precision,
    oda double precision,
    acreditacion double precision,
    virtualidad double precision,
    codcoordinacion integer
);
 "   DROP TABLE public.porcentajeasig;
       public         postgres    false    7            �            1259    18204 %   porcentajeasig_codporcentaje_asig_seq    SEQUENCE     �   CREATE SEQUENCE porcentajeasig_codporcentaje_asig_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.porcentajeasig_codporcentaje_asig_seq;
       public       postgres    false    203    7            �           0    0 %   porcentajeasig_codporcentaje_asig_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE porcentajeasig_codporcentaje_asig_seq OWNED BY porcentajeasig.codporcentaje_asig;
            public       postgres    false    204            �            1259    18206 
   produccion    TABLE     F  CREATE TABLE produccion (
    codproduccion integer NOT NULL,
    coddocente numeric,
    tipo_prod_part numeric,
    nombre character varying,
    editorial_inv character varying(200),
    libros character varying(100),
    estado character varying,
    tipo numeric,
    ano date,
    meses integer,
    num_anos integer
);
    DROP TABLE public.produccion;
       public         postgres    false    7            �            1259    18212    produccion_codproduccion_seq    SEQUENCE     ~   CREATE SEQUENCE produccion_codproduccion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.produccion_codproduccion_seq;
       public       postgres    false    205    7            �           0    0    produccion_codproduccion_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE produccion_codproduccion_seq OWNED BY produccion.codproduccion;
            public       postgres    false    206            �            1259    18214 	   productos    TABLE     �   CREATE TABLE productos (
    codproducto integer NOT NULL,
    fechacompromiso date,
    fechaentrega date,
    comentarios text,
    codact integer NOT NULL,
    descripcion character varying(300)
);
    DROP TABLE public.productos;
       public         postgres    false    7            �            1259    18220    productos_codproducto_seq    SEQUENCE     {   CREATE SEQUENCE productos_codproducto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.productos_codproducto_seq;
       public       postgres    false    207    7            �           0    0    productos_codproducto_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE productos_codproducto_seq OWNED BY productos.codproducto;
            public       postgres    false    208            �            1259    18222    semana    TABLE     c   CREATE TABLE semana (
    codsemana integer NOT NULL,
    fecha_inicio date,
    fecha_fin date
);
    DROP TABLE public.semana;
       public         postgres    false    7            �            1259    18225    semana_codsemana_seq    SEQUENCE     v   CREATE SEQUENCE semana_codsemana_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.semana_codsemana_seq;
       public       postgres    false    7    209            �           0    0    semana_codsemana_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE semana_codsemana_seq OWNED BY semana.codsemana;
            public       postgres    false    210            �            1259    18227    socializacion    TABLE     �   CREATE TABLE socializacion (
    codsocializar integer NOT NULL,
    coddocente numeric,
    articulo character varying(200),
    conferencia character varying(200),
    informe text
);
 !   DROP TABLE public.socializacion;
       public         postgres    false    7            �            1259    18233    socializacion_codsocializar_seq    SEQUENCE     �   CREATE SEQUENCE socializacion_codsocializar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.socializacion_codsocializar_seq;
       public       postgres    false    211    7            �           0    0    socializacion_codsocializar_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE socializacion_codsocializar_seq OWNED BY socializacion.codsocializar;
            public       postgres    false    212            �            1259    18235    tiempoasignado    TABLE     �   CREATE TABLE tiempoasignado (
    codtiempoasignado integer NOT NULL,
    horas date,
    codporcentaje integer,
    coddocente integer,
    codobligatoria integer
);
 "   DROP TABLE public.tiempoasignado;
       public         postgres    false    7            �            1259    18238 $   tiempoasignado_codtiempoasignado_seq    SEQUENCE     �   CREATE SEQUENCE tiempoasignado_codtiempoasignado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.tiempoasignado_codtiempoasignado_seq;
       public       postgres    false    7    213            �           0    0 $   tiempoasignado_codtiempoasignado_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE tiempoasignado_codtiempoasignado_seq OWNED BY tiempoasignado.codtiempoasignado;
            public       postgres    false    214            �            1259    18240    tipomodalidades    TABLE     k   CREATE TABLE tipomodalidades (
    codtipo integer NOT NULL,
    nombre character varying(100) NOT NULL
);
 #   DROP TABLE public.tipomodalidades;
       public         postgres    false    7            �            1259    18243    tipomodalidades_codtipo_seq    SEQUENCE     }   CREATE SEQUENCE tipomodalidades_codtipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.tipomodalidades_codtipo_seq;
       public       postgres    false    7    215            �           0    0    tipomodalidades_codtipo_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE tipomodalidades_codtipo_seq OWNED BY tipomodalidades.codtipo;
            public       postgres    false    216            �           2604    18245    codactividad    DEFAULT     v   ALTER TABLE ONLY actividades ALTER COLUMN codactividad SET DEFAULT nextval('actividades_codactividad_seq'::regclass);
 G   ALTER TABLE public.actividades ALTER COLUMN codactividad DROP DEFAULT;
       public       postgres    false    174    173            �           2604    18246    codactividadmisional    DEFAULT     �   ALTER TABLE ONLY actividadmisional ALTER COLUMN codactividadmisional SET DEFAULT nextval('actividadmisional_codactividadmisional_seq'::regclass);
 U   ALTER TABLE public.actividadmisional ALTER COLUMN codactividadmisional DROP DEFAULT;
       public       postgres    false    176    175            �           2604    18247    codactividadobligatorias    DEFAULT     �   ALTER TABLE ONLY actividadobligatorias ALTER COLUMN codactividadobligatorias SET DEFAULT nextval('actividadobligatorias_codactividadobligatorias_seq'::regclass);
 ]   ALTER TABLE public.actividadobligatorias ALTER COLUMN codactividadobligatorias DROP DEFAULT;
       public       postgres    false    178    177            �           2604    18248    codasg    DEFAULT     h   ALTER TABLE ONLY asignacion ALTER COLUMN codasg SET DEFAULT nextval('asignacion_codasg_seq'::regclass);
 @   ALTER TABLE public.asignacion ALTER COLUMN codasg DROP DEFAULT;
       public       postgres    false    180    179            �           2604    18249    codclase    DEFAULT     d   ALTER TABLE ONLY clases ALTER COLUMN codclase SET DEFAULT nextval('clases_codclase_seq'::regclass);
 >   ALTER TABLE public.clases ALTER COLUMN codclase DROP DEFAULT;
       public       postgres    false    182    181            �           2604    18250    codconvencion    DEFAULT     z   ALTER TABLE ONLY convenciones ALTER COLUMN codconvencion SET DEFAULT nextval('convenciones_codconvencion_seq'::regclass);
 I   ALTER TABLE public.convenciones ALTER COLUMN codconvencion DROP DEFAULT;
       public       postgres    false    184    183            �           2604    18251    codcoordinacion    DEFAULT     ~   ALTER TABLE ONLY coordinacion ALTER COLUMN codcoordinacion SET DEFAULT nextval('coordinacion_codcoordinacion_seq'::regclass);
 K   ALTER TABLE public.coordinacion ALTER COLUMN codcoordinacion DROP DEFAULT;
       public       postgres    false    186    185            �           2604    18252    cedula    DEFAULT     d   ALTER TABLE ONLY docentes ALTER COLUMN cedula SET DEFAULT nextval('docentes_cedula_seq'::regclass);
 >   ALTER TABLE public.docentes ALTER COLUMN cedula DROP DEFAULT;
       public       postgres    false    188    187            �           2604    18253    cod_experiencia    DEFAULT     |   ALTER TABLE ONLY experiencia ALTER COLUMN cod_experiencia SET DEFAULT nextval('experiencia_cod_experiencia_seq'::regclass);
 J   ALTER TABLE public.experiencia ALTER COLUMN cod_experiencia DROP DEFAULT;
       public       postgres    false    190    189            �           2604    18254    codfacultad    DEFAULT     n   ALTER TABLE ONLY facultad ALTER COLUMN codfacultad SET DEFAULT nextval('facultad_codfacultad_seq'::regclass);
 C   ALTER TABLE public.facultad ALTER COLUMN codfacultad DROP DEFAULT;
       public       postgres    false    192    191            �           2604    18255 
   codhorario    DEFAULT     j   ALTER TABLE ONLY horario ALTER COLUMN codhorario SET DEFAULT nextval('horario_codhorario_seq'::regclass);
 A   ALTER TABLE public.horario ALTER COLUMN codhorario DROP DEFAULT;
       public       postgres    false    194    193            �           2604    18256    codcademico    DEFAULT     z   ALTER TABLE ONLY info_academica ALTER COLUMN codcademico SET DEFAULT nextval('info_academica_codcademico_seq'::regclass);
 I   ALTER TABLE public.info_academica ALTER COLUMN codcademico DROP DEFAULT;
       public       postgres    false    196    195            �           2604    18257    codparticipacion    DEFAULT     �   ALTER TABLE ONLY participacion ALTER COLUMN codparticipacion SET DEFAULT nextval('participacion_codparticipacion_seq'::regclass);
 M   ALTER TABLE public.participacion ALTER COLUMN codparticipacion DROP DEFAULT;
       public       postgres    false    198    197            �           2604    18258 
   codpermiso    DEFAULT     l   ALTER TABLE ONLY permisos ALTER COLUMN codpermiso SET DEFAULT nextval('permisos_codpermiso_seq'::regclass);
 B   ALTER TABLE public.permisos ALTER COLUMN codpermiso DROP DEFAULT;
       public       postgres    false    200    199            �           2604    18259    codporcentaje    DEFAULT     v   ALTER TABLE ONLY porcentaje ALTER COLUMN codporcentaje SET DEFAULT nextval('porcentaje_codporcentaje_seq'::regclass);
 G   ALTER TABLE public.porcentaje ALTER COLUMN codporcentaje DROP DEFAULT;
       public       postgres    false    202    201            �           2604    18260    codporcentaje_asig    DEFAULT     �   ALTER TABLE ONLY porcentajeasig ALTER COLUMN codporcentaje_asig SET DEFAULT nextval('porcentajeasig_codporcentaje_asig_seq'::regclass);
 P   ALTER TABLE public.porcentajeasig ALTER COLUMN codporcentaje_asig DROP DEFAULT;
       public       postgres    false    204    203            �           2604    18261    codproduccion    DEFAULT     v   ALTER TABLE ONLY produccion ALTER COLUMN codproduccion SET DEFAULT nextval('produccion_codproduccion_seq'::regclass);
 G   ALTER TABLE public.produccion ALTER COLUMN codproduccion DROP DEFAULT;
       public       postgres    false    206    205            �           2604    18262    codproducto    DEFAULT     p   ALTER TABLE ONLY productos ALTER COLUMN codproducto SET DEFAULT nextval('productos_codproducto_seq'::regclass);
 D   ALTER TABLE public.productos ALTER COLUMN codproducto DROP DEFAULT;
       public       postgres    false    208    207            �           2604    18263 	   codsemana    DEFAULT     f   ALTER TABLE ONLY semana ALTER COLUMN codsemana SET DEFAULT nextval('semana_codsemana_seq'::regclass);
 ?   ALTER TABLE public.semana ALTER COLUMN codsemana DROP DEFAULT;
       public       postgres    false    210    209            �           2604    18264    codsocializar    DEFAULT     |   ALTER TABLE ONLY socializacion ALTER COLUMN codsocializar SET DEFAULT nextval('socializacion_codsocializar_seq'::regclass);
 J   ALTER TABLE public.socializacion ALTER COLUMN codsocializar DROP DEFAULT;
       public       postgres    false    212    211            �           2604    18265    codtiempoasignado    DEFAULT     �   ALTER TABLE ONLY tiempoasignado ALTER COLUMN codtiempoasignado SET DEFAULT nextval('tiempoasignado_codtiempoasignado_seq'::regclass);
 O   ALTER TABLE public.tiempoasignado ALTER COLUMN codtiempoasignado DROP DEFAULT;
       public       postgres    false    214    213            �           2604    18266    codtipo    DEFAULT     t   ALTER TABLE ONLY tipomodalidades ALTER COLUMN codtipo SET DEFAULT nextval('tipomodalidades_codtipo_seq'::regclass);
 F   ALTER TABLE public.tipomodalidades ALTER COLUMN codtipo DROP DEFAULT;
       public       postgres    false    216    215            �          0    18103    actividades 
   TABLE DATA               v   COPY actividades (codactividad, nombre, descripcion, responsable, coddocente, codtipo, horas, valoracion) FROM stdin;
    public       postgres    false    173   s�       �           0    0    actividades_codactividad_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('actividades_codactividad_seq', 41, true);
            public       postgres    false    174            �          0    18111    actividadmisional 
   TABLE DATA               B   COPY actividadmisional (codactividadmisional, nombre) FROM stdin;
    public       postgres    false    175   ��       �           0    0 *   actividadmisional_codactividadmisional_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('actividadmisional_codactividadmisional_seq', 8, true);
            public       postgres    false    176            �          0    18116    actividadobligatorias 
   TABLE DATA               P   COPY actividadobligatorias (codactividadobligatorias, nombre, hora) FROM stdin;
    public       postgres    false    177   T�       �           0    0 2   actividadobligatorias_codactividadobligatorias_seq    SEQUENCE SET     Y   SELECT pg_catalog.setval('actividadobligatorias_codactividadobligatorias_seq', 5, true);
            public       postgres    false    178            �          0    18121 
   asignacion 
   TABLE DATA               �   COPY asignacion (codasg, horasclase, coddocente, preparacion, capacitacion, colectivo, investigacion, social, oda, planeacion, virtualidad, comites, codcoord, sumatoria) FROM stdin;
    public       postgres    false    179   ��       �           0    0    asignacion_codasg_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('asignacion_codasg_seq', 10, true);
            public       postgres    false    180            �          0    18126    clases 
   TABLE DATA               b   COPY clases (codclase, dia, nombre, coddocente, codconvencion, horainicio, horafinal) FROM stdin;
    public       postgres    false    181   p�       �           0    0    clases_codclase_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('clases_codclase_seq', 54, true);
            public       postgres    false    182            �          0    18131    convenciones 
   TABLE DATA               =   COPY convenciones (codconvencion, nombre, color) FROM stdin;
    public       postgres    false    183   �       �           0    0    convenciones_codconvencion_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('convenciones_codconvencion_seq', 34, true);
            public       postgres    false    184            �          0    18136    coordinacion 
   TABLE DATA               �   COPY coordinacion (codcoordinacion, nombre, codfacultad, investigacion, extension, comites, oda, acreditacion, virtualidad, asignado) FROM stdin;
    public       postgres    false    185   *�       �           0    0     coordinacion_codcoordinacion_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('coordinacion_codcoordinacion_seq', 5, true);
            public       postgres    false    186            �          0    18148    docentes 
   TABLE DATA               �   COPY docentes (cedula, apellidos, nombres, codigo, formacion, direccion, telefono, correo, codcoordinacion, foto, fechanac, tipo_contrato, celular, tipo_doc, lugar_exp, municipio, lugar_nac, genero, fecha_exp, matricula_prof) FROM stdin;
    public       postgres    false    187   ��       �           0    0    docentes_cedula_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('docentes_cedula_seq', 4, true);
            public       postgres    false    188            �          0    18157    experiencia 
   TABLE DATA               �   COPY experiencia (cod_experiencia, coddocente, institucion, actividad, dependencia, tiempo, fecha_ultima, tipo, tipo_contrato) FROM stdin;
    public       postgres    false    189   _�       �           0    0    experiencia_cod_experiencia_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('experiencia_cod_experiencia_seq', 3, true);
            public       postgres    false    190            �          0    18165    facultad 
   TABLE DATA               =   COPY facultad (codfacultad, nombre, abreviatura) FROM stdin;
    public       postgres    false    191   ��       �           0    0    facultad_codfacultad_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('facultad_codfacultad_seq', 5, true);
            public       postgres    false    192            �          0    18170    horario 
   TABLE DATA               T   COPY horario (codhorario, nombre, coddocente, codconvencion, dia, hora) FROM stdin;
    public       postgres    false    193   ��       �           0    0    horario_codhorario_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('horario_codhorario_seq', 12, true);
            public       postgres    false    194            �          0    18175    info_academica 
   TABLE DATA                  COPY info_academica (nivel, titulo, institucion, registro, pais, horas, tipo, cod_docente, codcademico, ano_grado) FROM stdin;
    public       postgres    false    195   ��       �           0    0    info_academica_codcademico_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('info_academica_codcademico_seq', 5, true);
            public       postgres    false    196            �          0    18183    participacion 
   TABLE DATA               �   COPY participacion (codparticipacion, coddocente, tipo_part, tipo, evento, tema, fecha, ambito, dedicacion, activ_culturales, dedica_cultural, desempeno) FROM stdin;
    public       postgres    false    197   .�       �           0    0 "   participacion_codparticipacion_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('participacion_codparticipacion_seq', 2, true);
            public       postgres    false    198            �          0    18191    permisos 
   TABLE DATA               <   COPY permisos (codpermiso, usuario, clave, rol) FROM stdin;
    public       postgres    false    199   ��        	           0    0    permisos_codpermiso_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('permisos_codpermiso_seq', 11, true);
            public       postgres    false    200            �          0    18196 
   porcentaje 
   TABLE DATA               _   COPY porcentaje (codporcentaje, porcentaje, codcoordinacion, codactividadmisional) FROM stdin;
    public       postgres    false    201   ��       	           0    0    porcentaje_codporcentaje_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('porcentaje_codporcentaje_seq', 87, true);
            public       postgres    false    202            �          0    18201    porcentajeasig 
   TABLE DATA               �   COPY porcentajeasig (codporcentaje_asig, investigacion, extension, comites, oda, acreditacion, virtualidad, codcoordinacion) FROM stdin;
    public       postgres    false    203   .�       	           0    0 %   porcentajeasig_codporcentaje_asig_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('porcentajeasig_codporcentaje_asig_seq', 4, true);
            public       postgres    false    204            �          0    18206 
   produccion 
   TABLE DATA               �   COPY produccion (codproduccion, coddocente, tipo_prod_part, nombre, editorial_inv, libros, estado, tipo, ano, meses, num_anos) FROM stdin;
    public       postgres    false    205   a�       	           0    0    produccion_codproduccion_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('produccion_codproduccion_seq', 2, true);
            public       postgres    false    206            �          0    18214 	   productos 
   TABLE DATA               j   COPY productos (codproducto, fechacompromiso, fechaentrega, comentarios, codact, descripcion) FROM stdin;
    public       postgres    false    207   ��       	           0    0    productos_codproducto_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('productos_codproducto_seq', 31, true);
            public       postgres    false    208            �          0    18222    semana 
   TABLE DATA               =   COPY semana (codsemana, fecha_inicio, fecha_fin) FROM stdin;
    public       postgres    false    209   �       	           0    0    semana_codsemana_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('semana_codsemana_seq', 3, true);
            public       postgres    false    210            �          0    18227    socializacion 
   TABLE DATA               [   COPY socializacion (codsocializar, coddocente, articulo, conferencia, informe) FROM stdin;
    public       postgres    false    211   G�       	           0    0    socializacion_codsocializar_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('socializacion_codsocializar_seq', 2, true);
            public       postgres    false    212            �          0    18235    tiempoasignado 
   TABLE DATA               f   COPY tiempoasignado (codtiempoasignado, horas, codporcentaje, coddocente, codobligatoria) FROM stdin;
    public       postgres    false    213   ��       	           0    0 $   tiempoasignado_codtiempoasignado_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('tiempoasignado_codtiempoasignado_seq', 20, true);
            public       postgres    false    214            �          0    18240    tipomodalidades 
   TABLE DATA               3   COPY tipomodalidades (codtipo, nombre) FROM stdin;
    public       postgres    false    215   ��       	           0    0    tipomodalidades_codtipo_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('tipomodalidades_codtipo_seq', 11, true);
            public       postgres    false    216                       2606    18268    info_academica_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY info_academica
    ADD CONSTRAINT info_academica_pkey PRIMARY KEY (codcademico);
 L   ALTER TABLE ONLY public.info_academica DROP CONSTRAINT info_academica_pkey;
       public         postgres    false    195    195            +           2606    18270    pf_tipo 
   CONSTRAINT     S   ALTER TABLE ONLY tipomodalidades
    ADD CONSTRAINT pf_tipo PRIMARY KEY (codtipo);
 A   ALTER TABLE ONLY public.tipomodalidades DROP CONSTRAINT pf_tipo;
       public         postgres    false    215    215            �           2606    18272    pk_act 
   CONSTRAINT     S   ALTER TABLE ONLY actividades
    ADD CONSTRAINT pk_act PRIMARY KEY (codactividad);
 <   ALTER TABLE ONLY public.actividades DROP CONSTRAINT pk_act;
       public         postgres    false    173    173                        2606    18274    pk_actividadmisional 
   CONSTRAINT     o   ALTER TABLE ONLY actividadmisional
    ADD CONSTRAINT pk_actividadmisional PRIMARY KEY (codactividadmisional);
 P   ALTER TABLE ONLY public.actividadmisional DROP CONSTRAINT pk_actividadmisional;
       public         postgres    false    175    175                       2606    18276    pk_actividadobligatorias 
   CONSTRAINT     {   ALTER TABLE ONLY actividadobligatorias
    ADD CONSTRAINT pk_actividadobligatorias PRIMARY KEY (codactividadobligatorias);
 X   ALTER TABLE ONLY public.actividadobligatorias DROP CONSTRAINT pk_actividadobligatorias;
       public         postgres    false    177    177                       2606    18278    pk_clase 
   CONSTRAINT     L   ALTER TABLE ONLY clases
    ADD CONSTRAINT pk_clase PRIMARY KEY (codclase);
 9   ALTER TABLE ONLY public.clases DROP CONSTRAINT pk_clase;
       public         postgres    false    181    181            	           2606    18280 	   pk_conven 
   CONSTRAINT     X   ALTER TABLE ONLY convenciones
    ADD CONSTRAINT pk_conven PRIMARY KEY (codconvencion);
 @   ALTER TABLE ONLY public.convenciones DROP CONSTRAINT pk_conven;
       public         postgres    false    183    183                       2606    18282    pk_coordinacion 
   CONSTRAINT     `   ALTER TABLE ONLY coordinacion
    ADD CONSTRAINT pk_coordinacion PRIMARY KEY (codcoordinacion);
 F   ALTER TABLE ONLY public.coordinacion DROP CONSTRAINT pk_coordinacion;
       public         postgres    false    185    185                       2606    18284    pk_docentes 
   CONSTRAINT     O   ALTER TABLE ONLY docentes
    ADD CONSTRAINT pk_docentes PRIMARY KEY (cedula);
 >   ALTER TABLE ONLY public.docentes DROP CONSTRAINT pk_docentes;
       public         postgres    false    187    187                       2606    18286    pk_exp 
   CONSTRAINT     V   ALTER TABLE ONLY experiencia
    ADD CONSTRAINT pk_exp PRIMARY KEY (cod_experiencia);
 <   ALTER TABLE ONLY public.experiencia DROP CONSTRAINT pk_exp;
       public         postgres    false    189    189                       2606    18288    pk_facultad 
   CONSTRAINT     T   ALTER TABLE ONLY facultad
    ADD CONSTRAINT pk_facultad PRIMARY KEY (codfacultad);
 >   ALTER TABLE ONLY public.facultad DROP CONSTRAINT pk_facultad;
       public         postgres    false    191    191                       2606    18290 
   pk_horario 
   CONSTRAINT     Q   ALTER TABLE ONLY horario
    ADD CONSTRAINT pk_horario PRIMARY KEY (codhorario);
 <   ALTER TABLE ONLY public.horario DROP CONSTRAINT pk_horario;
       public         postgres    false    193    193            '           2606    18292    pk_part 
   CONSTRAINT     W   ALTER TABLE ONLY socializacion
    ADD CONSTRAINT pk_part PRIMARY KEY (codsocializar);
 ?   ALTER TABLE ONLY public.socializacion DROP CONSTRAINT pk_part;
       public         postgres    false    211    211                       2606    18294    pk_participacion 
   CONSTRAINT     c   ALTER TABLE ONLY participacion
    ADD CONSTRAINT pk_participacion PRIMARY KEY (codparticipacion);
 H   ALTER TABLE ONLY public.participacion DROP CONSTRAINT pk_participacion;
       public         postgres    false    197    197                       2606    18296    pk_per 
   CONSTRAINT     N   ALTER TABLE ONLY permisos
    ADD CONSTRAINT pk_per PRIMARY KEY (codpermiso);
 9   ALTER TABLE ONLY public.permisos DROP CONSTRAINT pk_per;
       public         postgres    false    199    199                       2606    18298    pk_porcentaje 
   CONSTRAINT     Z   ALTER TABLE ONLY porcentaje
    ADD CONSTRAINT pk_porcentaje PRIMARY KEY (codporcentaje);
 B   ALTER TABLE ONLY public.porcentaje DROP CONSTRAINT pk_porcentaje;
       public         postgres    false    201    201                       2606    18300    pk_porcentajeasig 
   CONSTRAINT     g   ALTER TABLE ONLY porcentajeasig
    ADD CONSTRAINT pk_porcentajeasig PRIMARY KEY (codporcentaje_asig);
 J   ALTER TABLE ONLY public.porcentajeasig DROP CONSTRAINT pk_porcentajeasig;
       public         postgres    false    203    203            #           2606    18302    pk_prod 
   CONSTRAINT     Q   ALTER TABLE ONLY productos
    ADD CONSTRAINT pk_prod PRIMARY KEY (codproducto);
 ;   ALTER TABLE ONLY public.productos DROP CONSTRAINT pk_prod;
       public         postgres    false    207    207            !           2606    18304    pk_produccion 
   CONSTRAINT     Z   ALTER TABLE ONLY produccion
    ADD CONSTRAINT pk_produccion PRIMARY KEY (codproduccion);
 B   ALTER TABLE ONLY public.produccion DROP CONSTRAINT pk_produccion;
       public         postgres    false    205    205            %           2606    18306 	   pk_semana 
   CONSTRAINT     N   ALTER TABLE ONLY semana
    ADD CONSTRAINT pk_semana PRIMARY KEY (codsemana);
 :   ALTER TABLE ONLY public.semana DROP CONSTRAINT pk_semana;
       public         postgres    false    209    209                       2606    18308    pk_tiempoasig 
   CONSTRAINT     S   ALTER TABLE ONLY asignacion
    ADD CONSTRAINT pk_tiempoasig PRIMARY KEY (codasg);
 B   ALTER TABLE ONLY public.asignacion DROP CONSTRAINT pk_tiempoasig;
       public         postgres    false    179    179            )           2606    18310    pk_tiempoasignado 
   CONSTRAINT     f   ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT pk_tiempoasignado PRIMARY KEY (codtiempoasignado);
 J   ALTER TABLE ONLY public.tiempoasignado DROP CONSTRAINT pk_tiempoasignado;
       public         postgres    false    213    213                       2606    18312    uq_per 
   CONSTRAINT     F   ALTER TABLE ONLY permisos
    ADD CONSTRAINT uq_per UNIQUE (usuario);
 9   ALTER TABLE ONLY public.permisos DROP CONSTRAINT uq_per;
       public         postgres    false    199    199                       1259    18313    fki_coordinacion_asignacion    INDEX     O   CREATE INDEX fki_coordinacion_asignacion ON asignacion USING btree (codcoord);
 /   DROP INDEX public.fki_coordinacion_asignacion;
       public         postgres    false    179            ,           2606    18314    actividades_coddocente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY actividades
    ADD CONSTRAINT actividades_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula) ON UPDATE CASCADE ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.actividades DROP CONSTRAINT actividades_coddocente_fkey;
       public       postgres    false    2061    173    187            -           2606    18319    actividades_codtipo_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY actividades
    ADD CONSTRAINT actividades_codtipo_fkey FOREIGN KEY (codtipo) REFERENCES tipomodalidades(codtipo) ON UPDATE CASCADE ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.actividades DROP CONSTRAINT actividades_codtipo_fkey;
       public       postgres    false    173    2091    215            0           2606    18324    cla_codclase_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY clases
    ADD CONSTRAINT cla_codclase_fkey FOREIGN KEY (codconvencion) REFERENCES convenciones(codconvencion) ON UPDATE CASCADE ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.clases DROP CONSTRAINT cla_codclase_fkey;
       public       postgres    false    183    181    2057            1           2606    18329    clases_coddocente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY clases
    ADD CONSTRAINT clases_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula) ON UPDATE CASCADE;
 G   ALTER TABLE ONLY public.clases DROP CONSTRAINT clases_coddocente_fkey;
       public       postgres    false    181    187    2061            2           2606    18334    coordinacion_codfacultad_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY coordinacion
    ADD CONSTRAINT coordinacion_codfacultad_fkey FOREIGN KEY (codfacultad) REFERENCES facultad(codfacultad);
 T   ALTER TABLE ONLY public.coordinacion DROP CONSTRAINT coordinacion_codfacultad_fkey;
       public       postgres    false    2065    191    185            .           2606    18339    fk_coordinacion_asignacion    FK CONSTRAINT     �   ALTER TABLE ONLY asignacion
    ADD CONSTRAINT fk_coordinacion_asignacion FOREIGN KEY (codcoord) REFERENCES coordinacion(codcoordinacion) ON UPDATE CASCADE ON DELETE CASCADE;
 O   ALTER TABLE ONLY public.asignacion DROP CONSTRAINT fk_coordinacion_asignacion;
       public       postgres    false    2059    179    185            3           2606    18344    horario_codconvencion_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_codconvencion_fkey FOREIGN KEY (codconvencion) REFERENCES convenciones(codconvencion);
 L   ALTER TABLE ONLY public.horario DROP CONSTRAINT horario_codconvencion_fkey;
       public       postgres    false    2057    193    183            4           2606    18349    horario_coddocente_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY horario
    ADD CONSTRAINT horario_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);
 I   ALTER TABLE ONLY public.horario DROP CONSTRAINT horario_coddocente_fkey;
       public       postgres    false    187    193    2061            5           2606    18354 $   porcentaje_codactividadmisional_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY porcentaje
    ADD CONSTRAINT porcentaje_codactividadmisional_fkey FOREIGN KEY (codactividadmisional) REFERENCES actividadmisional(codactividadmisional);
 Y   ALTER TABLE ONLY public.porcentaje DROP CONSTRAINT porcentaje_codactividadmisional_fkey;
       public       postgres    false    2048    175    201            6           2606    18359    porcentaje_codcoordinacion_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY porcentaje
    ADD CONSTRAINT porcentaje_codcoordinacion_fkey FOREIGN KEY (codcoordinacion) REFERENCES coordinacion(codcoordinacion);
 T   ALTER TABLE ONLY public.porcentaje DROP CONSTRAINT porcentaje_codcoordinacion_fkey;
       public       postgres    false    2059    185    201            7           2606    18364 #   porcentajeasig_codcoordinacion_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY porcentajeasig
    ADD CONSTRAINT porcentajeasig_codcoordinacion_fkey FOREIGN KEY (codcoordinacion) REFERENCES coordinacion(codcoordinacion) ON UPDATE CASCADE ON DELETE CASCADE;
 \   ALTER TABLE ONLY public.porcentajeasig DROP CONSTRAINT porcentajeasig_codcoordinacion_fkey;
       public       postgres    false    203    2059    185            8           2606    18369    productos_codact_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_codact_fkey FOREIGN KEY (codact) REFERENCES actividades(codactividad) ON UPDATE CASCADE ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.productos DROP CONSTRAINT productos_codact_fkey;
       public       postgres    false    2046    173    207            /           2606    18374    tiempoasig_coddocente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY asignacion
    ADD CONSTRAINT tiempoasig_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);
 O   ALTER TABLE ONLY public.asignacion DROP CONSTRAINT tiempoasig_coddocente_fkey;
       public       postgres    false    179    187    2061            9           2606    18379    tiempoasignado_coddocente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT tiempoasignado_coddocente_fkey FOREIGN KEY (coddocente) REFERENCES docentes(cedula);
 W   ALTER TABLE ONLY public.tiempoasignado DROP CONSTRAINT tiempoasignado_coddocente_fkey;
       public       postgres    false    187    2061    213            :           2606    18384 "   tiempoasignado_codobligatoria_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT tiempoasignado_codobligatoria_fkey FOREIGN KEY (codobligatoria) REFERENCES actividadobligatorias(codactividadobligatorias);
 [   ALTER TABLE ONLY public.tiempoasignado DROP CONSTRAINT tiempoasignado_codobligatoria_fkey;
       public       postgres    false    213    2050    177            ;           2606    18389 !   tiempoasignado_codporcentaje_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY tiempoasignado
    ADD CONSTRAINT tiempoasignado_codporcentaje_fkey FOREIGN KEY (codporcentaje) REFERENCES porcentaje(codporcentaje);
 Z   ALTER TABLE ONLY public.tiempoasignado DROP CONSTRAINT tiempoasignado_codporcentaje_fkey;
       public       postgres    false    201    2077    213            �   o  x��U�n�6>����HK�-;{
l�p��M����H�v%Q���m����>�_�CI�I7�f�aK�p���֨��d����]�����}f�S�S��E#W��fu4�L�,�l�w$�Lr���3&6X:ڧ�a[�ef�����.dR���\)��3��H7Ҵ�!��I�$cAwWA4�[g��=�aȄ��K�-��0���:�札����Z�Bc�gD@/q�b!j��R�XM)�[V���Kr��Zd�]/.N��-����������ꭈ��eZ`��XJ���zC�Ȣs�6FB8�e���=�mwO���"g�ɗȮ\*`i2-SO��#�l\�Q�1�X-[���0iAE_�h��_��.R �a< �\��"�����nVW?��?V����p���9	{Nf0G_����*�Dz��S-|��<�Q�}�⋽�����޻G�$����g��#{?���T�X��>؍�}��ׂk|ķK�֢w�҉M��o������/���Kj��g�[�w%3���]�H�OKf�m�'���!�܇����_�$�w�~�pm����7�h�J��*�b�I6�2����g�$�vw�]�!��y����^@�M��bo��#�ʏ�9P��l�>�Y�'R��xW�O��!�	l�V[?����@ܐ����sQ��ZVl<�E��xv�92� ���8�0��Њa!�e�~��H�x
Z�I��C��V��(�Ul�i�j�#��ƑP���v��>����F�"s�+���$���v�=��ַF�$��b��^#�C��.���=�h0�c:;���F�]�s����Nw�7��m&HUH-M�޳RE�d�lLa��}-�t�4�>����ͤ�fx�y7��	��a      �   R   x�3���+K-.�LOL�<�9�˄ӵ�$5��1�t���,9���ˌ��ő˜�1�(5%��ڂ3,���41'3%1�+F��� �vm      �   ?   x�3�L/NOKIO�4200�50".#δb���������9�)gFZz���9P	PĐ+F��� �'      �   �   x�m�Q� D��0��ܥ�?G���0fd��M���EB�Y�ڤ��\K)���~�B�dD�Ujg��|����M��6��.l���=�y����R�Y���؜���n��0�]�u�9�./1V��Wx��C� �}����>�����w�_|��~��lW{�d�ӱ��Q������1�׮Sa      �   �  x���Mn�0���)|�ǎ�$;�*eS!��.�1��F����b�#p�N�@J� M�}��7~�� ��me� |��3�=��)g��Bƚ��e�9��5kmu��>r�:Z��%�SJI�y����zo_�d�$;��:�kD�_XM�El��E�v\�_\���`B'c:����!�/QD'�F�-V����+,���Y\z�!�.�-������$�l���ղh��)�
d�1����<@�_D `_�1�����ѡ+�ܘ��R7t��,͒�6�T�.�,��[YX��(OM���j�4��J#���U8�f&��DӸ��kC�>������ث��>H���Ҷ<3TK�d�y�pdpֶ�9�
s��_��z?>���H�[~%W����X�ǩ5�9�}�q>�iu�      �     x�e��n� Fח�0q�ȏ�K'�J�n�a�iH�XD����+��$�t�w�9�����q_�s6�LNz�+�e]��0���QQ����ݭ�g����6� y����$�?��>����JdR�0��nԣ]�^H�j몮� ����0�)oR珷�Xb��A��[~G6��^�DVQ�T�Au˹ݼz�����'!a��y�kt�w^��Bt����|t�[|֫T�Z��Q}>��ư�Q�7~@~�s�ޞB���w       �   P   x�3�LL����,.)JL����4�4@�i\&��ii��h⦜%��%��@�Rs��&�rp��$K�b���� �      �   �  x��RM��0=O~�`/-4��mɹmH����%�\��lk��T�7�����J@��c��,�/��h��f�<^��iA�Ȯi�����f~9�u�e.D)8<���N����� ����J@l�)��,���e������Z�T�3x{y�fY(ˋ�C�!���y�-J���|N4[WB�ӂ�V�~�е���B�FՄ0�0�!䆤Ri9-�h�r8�`�]���v.��ͪv}�غ'�tu��"͗'rʅ%|���;�1���-z��X�ǯa���VY=��"��"{��w�j�&]!Q��T;�Ivro���K�5Nȏ؎�顟`�����������o�I���[�:*���>)���:+]��v��8��Ų, ϨXgY�@�N����"���qPr7(����C<������JO-w�;?�O�.|%���Hh�j.��6�W���Җ�      �   ?   x�3�47643777�, ����4 1��9�9��u�t9�8c���H�a����� ��      �   9   x�3�L�Q((��M-��L�,*.�2�,I-JN��4�q�rf�e��sf��q��qqq � �      �   �   x�e�M� F�3����.݌�-�bz�bS[�	��&_���jjk�ʃ
e��q�R�B.���A�D9,9���~�;X0ZxN�o�"c�����W��[v��K�)�)>ŉ���q�k/U��88v��Q�)�O~wg> :�y^�[�<E�.����E      �   s   x�E�K
�0����@�#�.�R�����]��:@�u!��0���� �~|-��VD��+"zXb53ABٺ��ȴ�c��Pr�^�P�����w�sy/�#�g�E�IHSh�����      �   I   x�3�47643777�4��,)J-�4204�50�50�,I-*��,�%�`��Y�!):��u��3F��� k.�      �   �   x�ՓKN�1��O�	��߅��8R%h�R8?���l��F.��H\Z�j&!^����*jYd
�ֈ��}PG���#TH_��_�_>/�� XW-}�X��d{��Q��.� x�ټ��ű=A�ϐ}=�NʼD�oy eUmYAu�8mKթi�*0�"G�LԳ2���<�e�Ԇ��Qw��ʮ�6)::�PҜ�Ck�wJ-�ݽ�"�c���9�/V��V���Ӈ���t>}ޮ���A���      �   �   x�u���0ϼb<H|�K��#);1�0���r�ՃG���H�\Hoh.k<[�\>H�kr����[!��b�`ZE_S�c����F�PZAaݔ崎��?ee7e����ʱ���H!m+W�!�ky��z ��M:q      �   #   x�3�44�@\�(|cN#��	�*C0����� [o	�      �   I   x�3�47643777�4�LJJIJK�L+NKL�LOi��)@.�����������g�q��Sg� Wu�      �   7  x��SKn�0]����@�⏺3�4�&� �d3�(��D�C2�{�.��|�%'v춰 @��>�FC�`i"�)'Y���sv߂�ZP� ��	���Zp�n���>�{�B���6�*�F��D۶eٷ��}k�7]�qWC�����le��)�xW���/��.D���b%sIF�҃���6��Te"G���4dv�Mz�M�����ln�M�\���}f>ٿ}x��q��>v�+eJ��mؾ�$����B���g��z��#���	{P8����IE	mj�Qk3(�z֎�nph�A�*=�@�����X;e1�׶�~���!�4��b�b���a$�d6I�~���n�;�����5��ɖ�/e��� �P�c�yQ}�we����P(N�w�՜}����"�t9?��r��-�6s�n�^+��b?�2_DM�a��␺.�:F�w����BM�Bg6�&��UA�1�<���Y��.Io���e��C���O�o��##5c�k������<������	�}T��H�y:=D��F ��l�<q�x�9��or�      �   6   x�3�4204�50�52B0͸L�S]Cӄ�����7�T�؀+F��� ~�N      �   .   x�3�47643777�LKLIKOLK�LLOIOLI�rӊ�b���� �x       �   I   x����!scC3sssS ��� *hh`ian2Ħ�S�16u&��L��3�Tg�M��:K,�0����� �V0�      �   W   x�3���+K-.�LOL�<�9�ːӵ�$5�83?�˄�9?7�$��ˌ��ő�Ѐ�1�(5%��(oh��YTR����������� Y��     