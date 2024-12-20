PGDMP  9    1            
    |         	   WeatherDB    17.1    17.1 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16387 	   WeatherDB    DATABASE        CREATE DATABASE "WeatherDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "WeatherDB";
                     postgres    false            �            1259    16467    expected_temperature    TABLE     �   CREATE TABLE public.expected_temperature (
    date date NOT NULL,
    max_expected_temp integer NOT NULL,
    min_expected_temp integer NOT NULL,
    parse_date date NOT NULL
);
 (   DROP TABLE public.expected_temperature;
       public         heap r       postgres    false            �            1259    16454    temperature    TABLE     z   CREATE TABLE public.temperature (
    date date NOT NULL,
    max_temp integer NOT NULL,
    min_temp integer NOT NULL
);
    DROP TABLE public.temperature;
       public         heap r       postgres    false            �          0    16467    expected_temperature 
   TABLE DATA           f   COPY public.expected_temperature (date, max_expected_temp, min_expected_temp, parse_date) FROM stdin;
    public               postgres    false    218   �
       �          0    16454    temperature 
   TABLE DATA           ?   COPY public.temperature (date, max_temp, min_temp) FROM stdin;
    public               postgres    false    217   K       [           2606    16458    temperature temperature_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.temperature
    ADD CONSTRAINT temperature_pkey PRIMARY KEY (date);
 F   ALTER TABLE ONLY public.temperature DROP CONSTRAINT temperature_pkey;
       public                 postgres    false    217            \           2606    16470 9   expected_temperature expected_temperature_parse_date_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.expected_temperature
    ADD CONSTRAINT expected_temperature_parse_date_fkey FOREIGN KEY (parse_date) REFERENCES public.temperature(date);
 c   ALTER TABLE ONLY public.expected_temperature DROP CONSTRAINT expected_temperature_parse_date_fkey;
       public               postgres    false    4699    218    217            �   K   x�u���@�w��ܩ���C���M����X ��K���l��*�@�>���w��{����L�?]��܍�(�      �      x�3202�54�54�4�4����� $�     