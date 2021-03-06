PGDMP          3        	        x            dbCapas    12.2    12.2 1    F           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            G           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            H           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            I           1262    81945    dbCapas    DATABASE     �   CREATE DATABASE "dbCapas" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_El Salvador.1252' LC_CTYPE = 'Spanish_El Salvador.1252';
    DROP DATABASE "dbCapas";
                postgres    false            �            1259    81946 	   centro_ed    TABLE     �   CREATE TABLE public.centro_ed (
    id_centro_ed integer NOT NULL,
    nombre character varying(100) NOT NULL,
    estado boolean NOT NULL,
    id_municipio integer NOT NULL
);
    DROP TABLE public.centro_ed;
       public         heap    postgres    false            �            1259    81949    centroEd_idCentroEd_seq    SEQUENCE     �   CREATE SEQUENCE public."centroEd_idCentroEd_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public."centroEd_idCentroEd_seq";
       public          postgres    false    202            J           0    0    centroEd_idCentroEd_seq    SEQUENCE OWNED BY     X   ALTER SEQUENCE public."centroEd_idCentroEd_seq" OWNED BY public.centro_ed.id_centro_ed;
          public          postgres    false    203            �            1259    81951    departamento    TABLE     o   CREATE TABLE public.departamento (
    id_depto integer NOT NULL,
    nombre character varying(30) NOT NULL
);
     DROP TABLE public.departamento;
       public         heap    postgres    false            �            1259    81954 
   estudiante    TABLE       CREATE TABLE public.estudiante (
    carne character varying(8) NOT NULL,
    nombre character varying(50) NOT NULL,
    apellido character varying(50) NOT NULL,
    carne_min character varying(9) NOT NULL,
    fecha_nac date NOT NULL,
    direccion character varying(200) NOT NULL,
    tel_movil character varying(8) NOT NULL,
    tel_fijo character varying(8) NOT NULL,
    id_centro_ed integer NOT NULL,
    nombre_mama character varying(100) NOT NULL,
    nombre_papa character varying(100) NOT NULL,
    id_estudiante integer NOT NULL
);
    DROP TABLE public.estudiante;
       public         heap    postgres    false            �            1259    81960    estudiante_id_estudiante_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_id_estudiante_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.estudiante_id_estudiante_seq;
       public          postgres    false    205            K           0    0    estudiante_id_estudiante_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.estudiante_id_estudiante_seq OWNED BY public.estudiante.id_estudiante;
          public          postgres    false    206            �            1259    81962    materia    TABLE     �   CREATE TABLE public.materia (
    id_materia integer NOT NULL,
    nombre character varying(50) NOT NULL,
    estado boolean NOT NULL
);
    DROP TABLE public.materia;
       public         heap    postgres    false            �            1259    81965    materia_idMateria_seq    SEQUENCE     �   CREATE SEQUENCE public."materia_idMateria_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."materia_idMateria_seq";
       public          postgres    false    207            L           0    0    materia_idMateria_seq    SEQUENCE OWNED BY     R   ALTER SEQUENCE public."materia_idMateria_seq" OWNED BY public.materia.id_materia;
          public          postgres    false    208            �            1259    81967    materia_x_estudiante    TABLE     �   CREATE TABLE public.materia_x_estudiante (
    id_materia integer NOT NULL,
    anio character(4) NOT NULL,
    ciclo character varying(2) NOT NULL,
    nota real NOT NULL,
    id_estudiante integer NOT NULL
);
 (   DROP TABLE public.materia_x_estudiante;
       public         heap    postgres    false            �            1259    81970 	   municipio    TABLE     �   CREATE TABLE public.municipio (
    id_municipio integer NOT NULL,
    nombre character varying(30) NOT NULL,
    id_depto integer NOT NULL
);
    DROP TABLE public.municipio;
       public         heap    postgres    false            �            1259    81973    usuario    TABLE     �  CREATE TABLE public.usuario (
    usuario character varying(15) NOT NULL,
    nombre character varying(50) NOT NULL,
    apellido character varying(50) NOT NULL,
    fecha_nac date NOT NULL,
    id_municipio integer NOT NULL,
    direccion character varying(200) NOT NULL,
    estado boolean NOT NULL,
    clave character varying(300) NOT NULL,
    rol integer NOT NULL,
    sesion boolean NOT NULL,
    fecha_sesion date,
    id_usuario integer NOT NULL
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �            1259    81979    usuario_id_usuario_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.usuario_id_usuario_seq;
       public          postgres    false    211            M           0    0    usuario_id_usuario_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;
          public          postgres    false    212            �
           2604    81981    centro_ed id_centro_ed    DEFAULT        ALTER TABLE ONLY public.centro_ed ALTER COLUMN id_centro_ed SET DEFAULT nextval('public."centroEd_idCentroEd_seq"'::regclass);
 E   ALTER TABLE public.centro_ed ALTER COLUMN id_centro_ed DROP DEFAULT;
       public          postgres    false    203    202            �
           2604    81982    estudiante id_estudiante    DEFAULT     �   ALTER TABLE ONLY public.estudiante ALTER COLUMN id_estudiante SET DEFAULT nextval('public.estudiante_id_estudiante_seq'::regclass);
 G   ALTER TABLE public.estudiante ALTER COLUMN id_estudiante DROP DEFAULT;
       public          postgres    false    206    205            �
           2604    81983    materia id_materia    DEFAULT     y   ALTER TABLE ONLY public.materia ALTER COLUMN id_materia SET DEFAULT nextval('public."materia_idMateria_seq"'::regclass);
 A   ALTER TABLE public.materia ALTER COLUMN id_materia DROP DEFAULT;
       public          postgres    false    208    207            �
           2604    81984    usuario id_usuario    DEFAULT     x   ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);
 A   ALTER TABLE public.usuario ALTER COLUMN id_usuario DROP DEFAULT;
       public          postgres    false    212    211            9          0    81946 	   centro_ed 
   TABLE DATA                 public          postgres    false    202   �9       ;          0    81951    departamento 
   TABLE DATA                 public          postgres    false    204   �:       <          0    81954 
   estudiante 
   TABLE DATA                 public          postgres    false    205   �;       >          0    81962    materia 
   TABLE DATA                 public          postgres    false    207   �;       @          0    81967    materia_x_estudiante 
   TABLE DATA                 public          postgres    false    209   �<       A          0    81970 	   municipio 
   TABLE DATA                 public          postgres    false    210   �<       B          0    81973    usuario 
   TABLE DATA                 public          postgres    false    211   LG       N           0    0    centroEd_idCentroEd_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public."centroEd_idCentroEd_seq"', 26, true);
          public          postgres    false    203            O           0    0    estudiante_id_estudiante_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.estudiante_id_estudiante_seq', 4, true);
          public          postgres    false    206            P           0    0    materia_idMateria_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public."materia_idMateria_seq"', 10, true);
          public          postgres    false    208            Q           0    0    usuario_id_usuario_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 8, true);
          public          postgres    false    212            �
           2606    81986    centro_ed centroEd_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.centro_ed
    ADD CONSTRAINT "centroEd_pkey" PRIMARY KEY (id_centro_ed);
 C   ALTER TABLE ONLY public.centro_ed DROP CONSTRAINT "centroEd_pkey";
       public            postgres    false    202            �
           2606    81988    departamento departamento_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (id_depto);
 H   ALTER TABLE ONLY public.departamento DROP CONSTRAINT departamento_pkey;
       public            postgres    false    204            �
           2606    81990    estudiante estudiante_carne_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_carne_key UNIQUE (carne);
 I   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_carne_key;
       public            postgres    false    205            �
           2606    81992    estudiante estudiante_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id_estudiante);
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public            postgres    false    205            �
           2606    81994    materia materia_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.materia
    ADD CONSTRAINT materia_pkey PRIMARY KEY (id_materia);
 >   ALTER TABLE ONLY public.materia DROP CONSTRAINT materia_pkey;
       public            postgres    false    207            �
           2606    81996 .   materia_x_estudiante materia_x_estudiante_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.materia_x_estudiante
    ADD CONSTRAINT materia_x_estudiante_pkey PRIMARY KEY (id_materia, id_estudiante);
 X   ALTER TABLE ONLY public.materia_x_estudiante DROP CONSTRAINT materia_x_estudiante_pkey;
       public            postgres    false    209    209            �
           2606    81998    municipio municipio_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT municipio_pkey PRIMARY KEY (id_municipio);
 B   ALTER TABLE ONLY public.municipio DROP CONSTRAINT municipio_pkey;
       public            postgres    false    210            �
           2606    82000    usuario usuario_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            postgres    false    211            �
           2606    82002    usuario usuario_usuario_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_usuario_key UNIQUE (usuario);
 E   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_usuario_key;
       public            postgres    false    211            �
           2606    82003    estudiante fk_centroEd    FK CONSTRAINT     �   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT "fk_centroEd" FOREIGN KEY (id_centro_ed) REFERENCES public.centro_ed(id_centro_ed);
 B   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT "fk_centroEd";
       public          postgres    false    202    2724    205            �
           2606    82008    centro_ed fk_idMunicipio    FK CONSTRAINT     �   ALTER TABLE ONLY public.centro_ed
    ADD CONSTRAINT "fk_idMunicipio" FOREIGN KEY (id_municipio) REFERENCES public.municipio(id_municipio);
 D   ALTER TABLE ONLY public.centro_ed DROP CONSTRAINT "fk_idMunicipio";
       public          postgres    false    2736    210    202            �
           2606    82013    municipio fk_id_depto    FK CONSTRAINT     �   ALTER TABLE ONLY public.municipio
    ADD CONSTRAINT fk_id_depto FOREIGN KEY (id_depto) REFERENCES public.departamento(id_depto) NOT VALID;
 ?   ALTER TABLE ONLY public.municipio DROP CONSTRAINT fk_id_depto;
       public          postgres    false    2726    204    210            �
           2606    82018 %   materia_x_estudiante fk_id_estudiante    FK CONSTRAINT     �   ALTER TABLE ONLY public.materia_x_estudiante
    ADD CONSTRAINT fk_id_estudiante FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id_estudiante) NOT VALID;
 O   ALTER TABLE ONLY public.materia_x_estudiante DROP CONSTRAINT fk_id_estudiante;
       public          postgres    false    209    205    2730            �
           2606    82023 "   materia_x_estudiante fk_id_materia    FK CONSTRAINT     �   ALTER TABLE ONLY public.materia_x_estudiante
    ADD CONSTRAINT fk_id_materia FOREIGN KEY (id_materia) REFERENCES public.materia(id_materia);
 L   ALTER TABLE ONLY public.materia_x_estudiante DROP CONSTRAINT fk_id_materia;
       public          postgres    false    207    209    2732            �
           2606    82028    usuario fk_municipio    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_municipio FOREIGN KEY (id_municipio) REFERENCES public.municipio(id_municipio);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_municipio;
       public          postgres    false    211    210    2736            9   �   x���MJA����v10H&?��*��4���Vʞ2i�TIw�w��3�Ŝ�q7dW���ꙵ-��`��|�o�ݵ#IA_����깴p5�0X(��+Xd���$!,�y�8�!��r�2Ӌ-��G��(�(�VYyG?�>��@͗���~қ��ZR8�`���_��78m�2��a�ԁ'�?3k������`$QL^�۫�%����j������|G��V�}�Ԭ       ;   �   x���An�0�}N�T�PC)��*B,"�j��'��%w%v܆#T!��� �z�4F?=���I��ӗh|at��T���G���d�N�y�,fI�Q�h�͞>��A�t��:�{\�t����J8�L�d��B�Yš�q�ͨr�����=��mqe^�����xe82~����Ee[�'|�e(�]��A$�Z$-u���9t(��nzp�!{�      <   
   x���          >   �   x���v
Q���W((M��L��M,I-�LTs�	uV�0�QP�I�K/M�JU�T��J'��%��(���jZsy6�hJp~rfbNj1IM�}�b���d&�f�P�sfj��b?��I��h�kJirbr���y
n����
����,=�6�T�� ����@����� �ȟ      @   
   x���          A   �
  x����n9��y
�7=4��¬d�I��E+2��#Ѡ�JUш�6Yf�E#�l�bsxH�d{�k��w�X�y�������Y��vv�ڸ��ٿ�.י�h��8��pq��G����㕣lE�ݷ��?Z����e���{p�!��'V���[z����gj���ϼ�P�qE�El��ƅi�W��RC��w��֔/!p���\e�jO������<�ef[7*�}�j��E3׶P�z��Id�jQ���}����5���1�+k��H�m���ο8U��u�^X�1�:���S	�� ��V*�4�zM];�RT��٫4m��&Q]���BEH*/]^��⏌Н����?�3�n�+m٨�������/�&d��0�mL��*2��B���x��'#�Ԝ�����P���o������7��]�S��=}��rjKf���-y��;���o�]������/�^�{�����㌁�v`�T��G	}���5��i�r썻���Yc`/�����l}t�TTB����]�r�3��v��#ʽv ���Y�s��½��l)�;�y�Mme��Bz��ǹ��8�Iᶐ��3׼���)�,��nJ3T��f����*G�Vk�y����[�6$���Rd[�il�gQ�＠�- )�^�r���N�Q	z���4ߦt���~Jڮ����V���'���%�q����|��������Q��5�d�E)	��8��|���^��q�aNӜ֑*�Rs�4�2����vc9j��AOx����0q`�xs;IggTT�y�Y�SU8N@B�on)ImR����K�o�w��<_�j"��X,�e|�>H�ىiM���0�-����g�G#�,d�J� eN��Ѿ�����A�����Ç'r�sHB���FH^�ó��+9�7a���Mt$iצ�n���}}ĕ����1a��c���r���؟Now�2* R�V>\�I6缪�t�J���(��z��J�����I�^�uO�1f�M���c5u�5u�	9fsʫ�e֜�,��>a��?��Q�����$vC�bS"7�����_�
�v�ҳ2�F?�H�5��(�-{�^�����W#/��p���[hF�ܓc7W5���Ks�k늃jIf�{�WT�_9�横�!�܂�՝��c玑^M���3��Ң+OUA��n�c�U���I*.�z��ޠ}��}��q.�uhi�R��3�֭��|�Y�|��Gg���3�<�Y,�?U�%�+�w�9b-���:��դ�5����k���Q[[�b�{�e���Ү3��7�3W�5�e�Q��fy�j�6d�x||O�3'�؍C�䲴��°9�yƹ�G�� r�z:wW�^;`'5�W1-�ыR�9ډ��{r��2��d�l\&�v ���y,�T�ź41� ȞDCʗ~L&�w�|���~�_�.ޱ��	Ŕ���9���jpiw��� .�YcB�'�Fg=�T\�5�fH|�)��햁�'�9z���@�����s�^�isRjRv`v�� ڏe4K$C�й� Ћ�\�_5���Y��ؒ;׏���*���̏��2�x�a�f6���؊t�� ,�@�U �0�����a�˸����@1���C��(����RB�"_�Ln���6bs�#̅ �u����[�������2�^��S,�A���+Ka`3��0 ��f,gP2?�D�د@��O|H�Ғ�ʚ�G�I�LV�Q&�z n��P�� ����� ��;{�o�a�^l�f\�H�`㍵�'�Ѝ������0̈2�s���Td�|��n�^,vߥ�8�S57$����Tl	�SzN~�{�b��QT��>�0�6����+H�B +���
��>ŉ/�J3C���y�}0���n���x�i �!{��1+Á���#N�LO�r_��^�7&�����!�]�%c�vJ��%� p�$�!���k�8��-�������#�m�l�e^���b�/�.l>T�ݠ"CW=���*^��:�ڏ��w�s�����$o:���*�a׻o(..�>���9���Ҹ;�p˴`���n~�Y��9۩�zR�Y,��ǼD�\0?��Ybb�N��B}����D�$Ńk� ��=/��t�a` i�Z.������:�x�Oc��:��W����8��G� ,����1mh�R�7�b������L`Ͻr����	���p��7@c�0�ţ�u����}4Dت������8֜҃�y`e���裫"�v [�}�v��;��������11���M��)ˌ�è.��c��BF��������a4JJ�XH� P�OΧ�I� �x�M� z�qU���.T��������@��&���g��Kx�ű�$�I
�ُ���� ����pl�XԖ.K��b.Ko���
� /��g:9�sح$�+s��Q���v�+���"99��c�?�"� 4�Q�w�H/�մ�e���o�<��!���ǔo�,��J�^��A���~�A��(���	�2L�o`}X��o���t��CkA9�8 /�(��L�r�:U��>V0����KWV�`��%w�TN��U�b"u��5��R�H%�~�sE�Z������rt������ߥ��(#��eVg� �5��t�U�7���d�      B   �   x��An�0D����H%�I����E$�V%��c�Ȓѷ��t�u���jvoF�7Ǯo�Op�N������5��dg�������q��)g��+XǙ֢��x%�8l�ҮL�Gt�j��E˟6d<�z
�gh����31P���Z=�����1S���/Y�I(�$�B��B
������R�^�R��״���|Zwg�ȗ�,���I�     