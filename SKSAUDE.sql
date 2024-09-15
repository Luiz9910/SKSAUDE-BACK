PGDMP      -                |            SKSAUDE    16.4    16.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24576    SKSAUDE    DATABASE     �   CREATE DATABASE "SKSAUDE" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE "SKSAUDE";
                postgres    false                        2615    24577    sksaude    SCHEMA        CREATE SCHEMA sksaude;
    DROP SCHEMA sksaude;
                postgres    false            �            1259    24606    consulta    TABLE     �  CREATE TABLE sksaude.consulta (
    cd_consulta integer NOT NULL,
    ds_consulta text,
    dt_consulta date NOT NULL,
    cd_especialidade integer NOT NULL,
    cd_paciente integer NOT NULL,
    cd_medico integer NOT NULL,
    sn_ativo character varying(1) DEFAULT 'S'::character varying NOT NULL,
    CONSTRAINT sksaudeconstraintativo CHECK (((sn_ativo)::text = ANY ((ARRAY['S'::character varying, 'N'::character varying])::text[])))
);
    DROP TABLE sksaude.consulta;
       sksaude         heap    postgres    false    6            �            1259    24601    especialidade    TABLE     �   CREATE TABLE sksaude.especialidade (
    cd_especialidade integer NOT NULL,
    nm_especialidade character varying(200) NOT NULL
);
 "   DROP TABLE sksaude.especialidade;
       sksaude         heap    postgres    false    6            �            1259    24625    medico    TABLE     �  CREATE TABLE sksaude.medico (
    cd_medico integer NOT NULL,
    nm_medico character varying(100) NOT NULL,
    crm character varying(9) NOT NULL,
    email character varying(150) NOT NULL,
    nr_cpf character varying(15) NOT NULL,
    cd_especialidade integer NOT NULL,
    sn_ativo character varying(1) DEFAULT 'S'::character varying NOT NULL,
    CONSTRAINT sksaudeconstraitativo CHECK (((sn_ativo)::text = ANY ((ARRAY['S'::character varying, 'N'::character varying])::text[])))
);
    DROP TABLE sksaude.medico;
       sksaude         heap    postgres    false    6            �            1259    24578    paciente    TABLE     	  CREATE TABLE sksaude.paciente (
    cd_paciente integer NOT NULL,
    nm_paciente character varying(200) NOT NULL,
    email character varying(200) NOT NULL,
    dt_nascimento date NOT NULL,
    nr_cpf character varying(15) NOT NULL,
    tp_sexo character varying(1) DEFAULT 'N'::character varying NOT NULL,
    nr_telefone integer,
    tp_estado_civil character varying(1) DEFAULT 'S'::character varying NOT NULL,
    nr_cep integer NOT NULL,
    nm_cidade character varying(100) NOT NULL,
    nm_estado character varying(100) NOT NULL,
    tp_cor character varying(2) DEFAULT 'N'::character varying NOT NULL,
    tp_sanguineo character varying(3) NOT NULL,
    sn_ativo character varying(1) DEFAULT 'S'::character varying,
    dt_registro date NOT NULL,
    CONSTRAINT sdksaudeconstraintpestadocivil CHECK (((tp_estado_civil)::text = ANY ((ARRAY['C'::character varying, 'V'::character varying, 'S'::character varying, 'N'::character varying])::text[]))),
    CONSTRAINT sdksaudeconstraintsnativo CHECK (((sn_ativo)::text = ANY ((ARRAY['S'::character varying, 'N'::character varying])::text[]))),
    CONSTRAINT sdksaudeconstrainttpcor CHECK (((tp_cor)::text = ANY ((ARRAY['N'::character varying, 'B'::character varying, 'P'::character varying, 'PA'::character varying, 'A'::character varying, 'I'::character varying])::text[]))),
    CONSTRAINT sdksaudeconstrainttpsanguineo CHECK (((tp_sanguineo)::text = ANY ((ARRAY['A+'::character varying, 'A-'::character varying, 'B+'::character varying, 'B-'::character varying, 'AB-'::character varying, 'AB+'::character varying, 'O+'::character varying, 'O-'::character varying])::text[]))),
    CONSTRAINT sksaudeconstraintativo CHECK (((sn_ativo)::text = ANY ((ARRAY['S'::character varying, 'N'::character varying])::text[]))),
    CONSTRAINT sksaudeconstrainttpcor CHECK (((tp_cor)::text = ANY ((ARRAY['N'::character varying, 'B'::character varying, 'P'::character varying, 'PA'::character varying, 'A'::character varying, 'I'::character varying])::text[]))),
    CONSTRAINT sksaudeconstrainttpsanguineo CHECK (((tp_sanguineo)::text = ANY ((ARRAY['A+'::character varying, 'A-'::character varying, 'B+'::character varying, 'B-'::character varying, 'AB-'::character varying, 'AB+'::character varying, 'O+'::character varying, 'O-'::character varying])::text[])))
);
    DROP TABLE sksaude.paciente;
       sksaude         heap    postgres    false    6            �            1259    24596    paciente_log    TABLE     �   CREATE TABLE sksaude.paciente_log (
    cd_paciente_log integer NOT NULL,
    cd_paciente integer NOT NULL,
    dt_registro date NOT NULL,
    dt_atualizacao date NOT NULL
);
 !   DROP TABLE sksaude.paciente_log;
       sksaude         heap    postgres    false    6            �          0    24606    consulta 
   TABLE DATA           ~   COPY sksaude.consulta (cd_consulta, ds_consulta, dt_consulta, cd_especialidade, cd_paciente, cd_medico, sn_ativo) FROM stdin;
    sksaude          postgres    false    219   '       �          0    24601    especialidade 
   TABLE DATA           L   COPY sksaude.especialidade (cd_especialidade, nm_especialidade) FROM stdin;
    sksaude          postgres    false    218   4'       �          0    24625    medico 
   TABLE DATA           g   COPY sksaude.medico (cd_medico, nm_medico, crm, email, nr_cpf, cd_especialidade, sn_ativo) FROM stdin;
    sksaude          postgres    false    220   Q'       �          0    24578    paciente 
   TABLE DATA           �   COPY sksaude.paciente (cd_paciente, nm_paciente, email, dt_nascimento, nr_cpf, tp_sexo, nr_telefone, tp_estado_civil, nr_cep, nm_cidade, nm_estado, tp_cor, tp_sanguineo, sn_ativo, dt_registro) FROM stdin;
    sksaude          postgres    false    216   n'       �          0    24596    paciente_log 
   TABLE DATA           b   COPY sksaude.paciente_log (cd_paciente_log, cd_paciente, dt_registro, dt_atualizacao) FROM stdin;
    sksaude          postgres    false    217   �'       @           2606    24613    consulta consulta_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY sksaude.consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (cd_consulta);
 A   ALTER TABLE ONLY sksaude.consulta DROP CONSTRAINT consulta_pkey;
       sksaude            postgres    false    219            >           2606    24605     especialidade especialidade_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY sksaude.especialidade
    ADD CONSTRAINT especialidade_pkey PRIMARY KEY (cd_especialidade);
 K   ALTER TABLE ONLY sksaude.especialidade DROP CONSTRAINT especialidade_pkey;
       sksaude            postgres    false    218            B           2606    24630    medico medico_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY sksaude.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (cd_medico);
 =   ALTER TABLE ONLY sksaude.medico DROP CONSTRAINT medico_pkey;
       sksaude            postgres    false    220            <           2606    24600    paciente_log paciente_log_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY sksaude.paciente_log
    ADD CONSTRAINT paciente_log_pkey PRIMARY KEY (cd_paciente_log);
 I   ALTER TABLE ONLY sksaude.paciente_log DROP CONSTRAINT paciente_log_pkey;
       sksaude            postgres    false    217            :           2606    24588    paciente paciente_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY sksaude.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (cd_paciente);
 A   ALTER TABLE ONLY sksaude.paciente DROP CONSTRAINT paciente_pkey;
       sksaude            postgres    false    216            F           2606    24634    medico fk_especialidade_medico    FK CONSTRAINT     �   ALTER TABLE ONLY sksaude.medico
    ADD CONSTRAINT fk_especialidade_medico FOREIGN KEY (cd_especialidade) REFERENCES sksaude.especialidade(cd_especialidade);
 I   ALTER TABLE ONLY sksaude.medico DROP CONSTRAINT fk_especialidade_medico;
       sksaude          postgres    false    220    4670    218            D           2606    24639    consulta fk_medico_consulta    FK CONSTRAINT     �   ALTER TABLE ONLY sksaude.consulta
    ADD CONSTRAINT fk_medico_consulta FOREIGN KEY (cd_medico) REFERENCES sksaude.medico(cd_medico);
 F   ALTER TABLE ONLY sksaude.consulta DROP CONSTRAINT fk_medico_consulta;
       sksaude          postgres    false    219    220    4674            E           2606    24615    consulta fk_paciente_consulta    FK CONSTRAINT     �   ALTER TABLE ONLY sksaude.consulta
    ADD CONSTRAINT fk_paciente_consulta FOREIGN KEY (cd_paciente) REFERENCES sksaude.paciente(cd_paciente);
 H   ALTER TABLE ONLY sksaude.consulta DROP CONSTRAINT fk_paciente_consulta;
       sksaude          postgres    false    216    4666    219            C           2606    24620 '   especialidade fk_paciente_especialidade    FK CONSTRAINT     �   ALTER TABLE ONLY sksaude.especialidade
    ADD CONSTRAINT fk_paciente_especialidade FOREIGN KEY (cd_especialidade) REFERENCES sksaude.especialidade(cd_especialidade);
 R   ALTER TABLE ONLY sksaude.especialidade DROP CONSTRAINT fk_paciente_especialidade;
       sksaude          postgres    false    4670    218    218            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     