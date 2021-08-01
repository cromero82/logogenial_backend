INSERT INTO usuario (id,nombre,apellido, email, username, password, estado, intentos_exitosos, intentos_fallidos, avatar) VALUES (1,'Nelson', 'Camacho', 'nc@mail.co', 'nc@mail.co',  '$2a$10$5VYzA1qYNJVV7UqB/vOGAOH8fPQeurb.B6or.6iTMLCEdMjRrRb/q',1,0,0,'av-1.png');
INSERT INTO usuario (id,nombre,apellido, email, username, password, estado, intentos_exitosos, intentos_fallidos, avatar) VALUES (2,'carlos', 'romero', 'cr@mail.co', 'cr@mail.co',  '$2a$10$HEBKG0QeXV0u3ftVanWP0.SF/QUs4//C2FAYeAZL0SONM0UEMXw6q',1,0,0,'av-1.png');
INSERT INTO usuario (id, activo, apellido, eliminado, email, estado, intentos_exitosos, intentos_fallidos, nombre, password, username, avatar) VALUES (4, true, 'admin', null, 'admin@mail.co', 1, 0, 0, 'admin', '2a$10$5.MZsMoipUMEfsI.mJaEBexCQBHv8kpwTJyTwuEPu2N9M5jsUh.Qi', 'admin@mail.co','av-1.png');
INSERT INTO usuario (id, activo, apellido, avatar, eliminado, email, estado, intentos_exitosos, intentos_fallidos, nombre, password, username) VALUES (6, true, 'Romero', 'av-5.png', null, 'isabellaromero@gmail.com', 1, 0, 0, 'Isabella', '$2a$10$EN0ruIGWuRrriERXhxSj6O24.7OccW3jj5j1Ap7wvg/RsP5/F8Czi', 'isabellaromero@gmail.com');
INSERT INTO usuario (id, activo, apellido, avatar, eliminado, email, estado, intentos_exitosos, intentos_fallidos, nombre, password, username) VALUES (8, true, 'camacho', 'av-5.png', null, 'conycamacho@mail.co', 1, 0, 0, 'cony', '$2a$10$MjFn0nHetF2sHBFC3OASQOs/i5NGMBntaum7T0R3EldjdaMVO4fPm', 'conycamacho@mail.co');

insert into roles (id, nombre) values (1, 'ADMINISTRADOR');
insert into roles (id, nombre) values (2, 'TUTOR');
insert into roles (id, nombre) values (3, 'ESTUDIANTE');
insert into roles (id, nombre) values (4, 'INVITADO');

insert into usuario_rol (usuario_id, role_id)  values (1,1);
insert into usuario_rol (usuario_id, role_id)  values (1,2);
insert into usuario_rol (usuario_id, role_id)  values (1,3);
insert into usuario_rol (usuario_id, role_id)  values (2,1);
insert into usuario_rol (usuario_id, role_id)  values (2,2);
insert into usuario_rol (usuario_id, role_id)  values (2,3);
insert into usuario_rol (usuario_id, role_id)  values (4,1);
insert into usuario_rol (usuario_id, role_id)  values (4,2);
insert into usuario_rol (usuario_id, role_id)  values (4,3);
INSERT INTO usuario_rol (usuario_id, role_id) VALUES (6, 3);
INSERT INTO usuario_rol (usuario_id, role_id) VALUES (8, 3);

INSERT INTO nivel (id, activo, dificultad, nombre, eliminado) VALUES (1, true, 1, 'INICIAL', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO nivel (id, activo, dificultad, nombre, eliminado) VALUES (2, true, 5, 'INTERMEDIO', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO nivel (id, activo, dificultad, nombre, eliminado) VALUES (3, true, 8, 'AVANZADO', to_date('09-09-1999','dd-mm-yyyy'));

INSERT INTO tema (id, activo, eliminado, nombre) VALUES (3, true, to_date('09-09-1999','dd-mm-yyyy'), 'Partes del cuerpo');
INSERT INTO tema (id, activo, eliminado, nombre) VALUES (1, true, to_date('09-09-1999','dd-mm-yyyy'), 'Familia');
INSERT INTO tema (id, activo, eliminado, nombre) VALUES (2, true, to_date('09-09-1999','dd-mm-yyyy'), 'Colores');
INSERT INTO tema (id, activo, eliminado, nombre) VALUES (5, true, to_date('09-09-1999','dd-mm-yyyy'), 'Primeras letras');

INSERT INTO grupo (id, activo, anio, nombre, usuario_id, eliminado) VALUES (1, true, 2020, 'INVITADO', 2, to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO grupo (id, activo, anio, nombre, usuario_id, eliminado) VALUES (2, true, 2020, 'COLEGIO PRINCIPAL - GRUPO 1', 2, to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO grupo (id, activo, anio, nombre, usuario_id, eliminado) VALUES (3, true, 2020, 'PERSONAL - INTERMEDIOS', 2, to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO grupo (id, activo, anio, eliminado, nombre, usuario_id) VALUES (5, true, 2020, to_date('09-09-1999','dd-mm-yyyy'), 'Invitado', 4);

INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (1, true, 'archivo2020-09-27T20_03_30_587.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (2, true, 'archivo2020-09-27T20_04_00_244.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (4, true, 'archivo2020-10-13T22_26_38_267.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (5, true, 'archivo2020-10-13T22_26_54_970.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (6, true, 'archivo2020-10-13T22_27_22_215.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (7, true, 'archivo2020-10-13T22_28_36_333.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (8, true, 'archivo2020-10-13T22_29_12_746.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (9, true, 'archivo2020-10-13T22_29_54_871.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (10, true, 'archivo2020-10-13T22_30_19_451.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (11, true, 'archivo2020-10-13T22_30_54_846.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (12, true, 'archivo2020-10-13T22_31_52_279.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (13, true, 'archivo2020-10-13T22_32_31_139.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (14, true, 'archivo2020-10-13T22_33_21_206.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, nombre, eliminado) VALUES (15, true, 'archivo2020-10-13T22_34_17_470.jpg', to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO archivo (id, activo, eliminado, nombre) VALUES (17, true, to_date('09-09-1999','dd-mm-yyyy'), 'archivo2020-10-14T09_13_39_829.jpg');
INSERT INTO archivo (id, activo, eliminado, nombre) VALUES (18, true, to_date('09-09-1999','dd-mm-yyyy'), 'archivo2020-10-14T09_14_01_598.jpg');

INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, frase, nombre, usuario_creador_id, eliminado) VALUES (1, true, 1, true, 1, 'Casa', 'Casa', 1, to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, frase, nombre, usuario_creador_id, eliminado) VALUES (2, true, 2, true, 9, 'El cielo es azul y las nubes son blancas', 'El cielo es azul y las nubes son blancas', 1, to_date('09-09-1999','dd-mm-yyyy'));
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (4, true, 4, true, 5, to_date('09-09-1999','dd-mm-yyyy'), 'Paloma de la paz', 'paloma de la paz', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (5, true, 5, true, 2, to_date('09-09-1999','dd-mm-yyyy'), 'Platanos', 'Platanos', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (6, true, 6, true, 2, to_date('09-09-1999','dd-mm-yyyy'), 'Mono', 'Mono', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (7, true, 7, true, 5, to_date('09-09-1999','dd-mm-yyyy'), 'mi papa', 'mi papa', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (8, true, 8, true, 6, to_date('09-09-1999','dd-mm-yyyy'), 'pera', 'pera amarilla y hoja verde', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (9, true, 9, true, 3, to_date('09-09-1999','dd-mm-yyyy'), 'pulpo', 'pulpo', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (10, true, 10, true, 4, to_date('09-09-1999','dd-mm-yyyy'), 'seis paletas', 'seis paletas', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (11, true, 11, true, 7, to_date('09-09-1999','dd-mm-yyyy'), 'seis pelotas de colores', 'seis pelotas de colores', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (12, true, 12, true, 3, to_date('09-09-1999','dd-mm-yyyy'), 'mimo', 'mimo', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (13, true, 13, true, 2, to_date('09-09-1999','dd-mm-yyyy'), 'abeja', 'abeja', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (14, true, 14, true, 5, to_date('09-09-1999','dd-mm-yyyy'), 'amo a mi mama', 'mama y yo', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (15, true, 15, true, 9, to_date('09-09-1999','dd-mm-yyyy'), 'con mi mama y mi hermana en la playa', 'vacaciones-familia', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (17, true, 17, true, 3, to_date('09-09-1999','dd-mm-yyyy'), 'montañas', 'montaña', 1);
INSERT INTO datagenia (id, activo, archivo_id, compartido, dificultad, eliminado, frase, nombre, usuario_creador_id) VALUES (18, true, 18, true, 5, to_date('09-09-1999','dd-mm-yyyy'), 'paisaje', 'paisaje', 1);

INSERT INTO grupo_nivel (id, activo, eliminado, grupo_id, nivel_id) VALUES (1, true, to_date('09-09-1999','dd-mm-yyyy'), 2, 1);
INSERT INTO grupo_nivel (id, activo, eliminado, grupo_id, nivel_id) VALUES (2, true, to_date('09-09-1999','dd-mm-yyyy'), 2, 2);
INSERT INTO grupo_nivel (id, activo, eliminado, grupo_id, nivel_id) VALUES (4, true, to_date('09-09-1999','dd-mm-yyyy'), 5, 1);
INSERT INTO grupo_nivel (id, activo, eliminado, grupo_id, nivel_id) VALUES (5, true, to_date('09-09-1999','dd-mm-yyyy'), 5, 2);

INSERT INTO grupo_nivel_tema (id, activo, eliminado, grupo_nivel_id, tema_id) VALUES (1, true, to_date('09-09-1999','dd-mm-yyyy'), 4, 2);
INSERT INTO grupo_nivel_tema (id, activo, eliminado, grupo_nivel_id, tema_id) VALUES (2, true, to_date('09-09-1999','dd-mm-yyyy'), 5, 1);
INSERT INTO grupo_nivel_tema (id, activo, eliminado, grupo_nivel_id, tema_id) VALUES (3, true, to_date('09-09-1999','dd-mm-yyyy'), 5, 3);
INSERT INTO grupo_nivel_tema (id, activo, eliminado, grupo_nivel_id, tema_id) VALUES (5, true, to_date('09-09-1999','dd-mm-yyyy'), 4, 5);

INSERT INTO leccion (id, activo, eliminado, enumeracion, leyenda, puntos, grupo_nivel_tema_id) VALUES (1, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 'letras m y p', 1000, 5);
INSERT INTO leccion (id, activo, eliminado, enumeracion, leyenda, puntos, grupo_nivel_tema_id) VALUES (2, true, to_date('09-09-1999','dd-mm-yyyy'), 2, 'lugares', 1000, 5);

INSERT INTO pregunta (id, activo, aprobacion, frase_respuesta, eliminado, enumeracion, tipopregunta, leccion_id, respuesta_datagenia_id, usuario_autor_id) VALUES (1, true, true, 'amo a mi mama', to_date('09-09-1999','dd-mm-yyyy'), 1, 1, 1, 14, 4);
INSERT INTO pregunta (id, activo, aprobacion, frase_respuesta, eliminado, enumeracion, tipopregunta, leccion_id, respuesta_datagenia_id, usuario_autor_id) VALUES (2, true, true, 'Mono', to_date('09-09-1999','dd-mm-yyyy'), 2, 1, 1, 6, 4);
INSERT INTO pregunta (id, activo, aprobacion, frase_respuesta, eliminado, enumeracion, tipopregunta, leccion_id, respuesta_datagenia_id, usuario_autor_id) VALUES (3, true, true, 'mimo', to_date('09-09-1999','dd-mm-yyyy'), 3, 2, 1, 12, 4);
INSERT INTO pregunta (id, activo, aprobacion, frase_respuesta, eliminado, enumeracion, tipopregunta, leccion_id, respuesta_datagenia_id, usuario_autor_id) VALUES (4, true, true, 'montañas', to_date('09-09-1999','dd-mm-yyyy'), 2, 1, 2, 17, 4);
INSERT INTO pregunta (id, activo, aprobacion, frase_respuesta, eliminado, enumeracion, tipopregunta, leccion_id, respuesta_datagenia_id, usuario_autor_id) VALUES (5, true, true, 'paisaje', to_date('09-09-1999','dd-mm-yyyy'), 2, 2, 2, 18, 4);

INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (1, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 7, 1, 'mi papa');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (2, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 15, 1, 'con mi mama y mi hermana en la playa');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (5, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 9, 3, 'pulpo');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (6, true, to_date('09-09-1999','dd-mm-yyyy'), 2, 11, 3, 'seis pelotas de colores');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (4, true, to_date('09-09-1999','dd-mm-yyyy'), 2, 13, 2, 'abeja');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (3, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 9, 2, 'pulpo');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (7, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 15, 4, 'con mi mama y mi hermana en la playa');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (8, true, to_date('09-09-1999','dd-mm-yyyy'), 1, 1, 5, 'Casa');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (9, true, to_date('09-09-1999','dd-mm-yyyy'), 2, 5, 5, 'Platanos');
INSERT INTO opcion_respuesta (id, activo, eliminado, orden, opcion_datagenia_id, pregunta_id, frase_respuesta) VALUES (10, true, to_date('09-09-1999','dd-mm-yyyy'), 3, 4, 5, 'Paloma de la paz');

insert into grupo_estudiante (activo, eliminado, grupo_id, usuario_estudiante_id) values (true, to_date('09-09-1999','dd-mm-yyyy'),5,6);
insert into grupo_estudiante (activo, eliminado, grupo_id, usuario_estudiante_id) values (true, to_date('09-09-1999','dd-mm-yyyy'),5,8);

INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (1, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-01 22:18','yyyy-mm-dd HH24:MI') , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (2, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-01 22:19','yyyy-mm-dd HH24:MI') , 0, 2, 2, 6, 3, 5);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (3, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-01 22:19','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 3, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (4, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-01 22:19','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 2, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (5, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 10:45','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 4, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (6, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 10:45','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 5, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (7, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 10:47','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 5, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (8, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 11:58','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (9, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 11:58','yyyy-mm-dd HH24:MI') , 333, 1, 1, 6, 3, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (10, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 11:58','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 2, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (11, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:29','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (12, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:29','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 3, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (13, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:30','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 2, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (14, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:30','yyyy-mm-dd HH24:MI')  , 0, 2, 2, 6, 1, 1);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (15, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:31','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (16, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:31','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 3, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (17, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:31','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 2, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (18, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:37','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (19, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:37','yyyy-mm-dd HH24:MI')  , 0, 2, 2, 6, 1, 1);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (20, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-02 12:37','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (21, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-03 23:22','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 4, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (22, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-03 23:22','yyyy-mm-dd HH24:MI') , 500, 1, 1, 6, 5, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (23, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 00:16','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 1, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (24, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 00:16','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 3, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (25, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 00:16','yyyy-mm-dd HH24:MI')  , 333, 1, 1, 6, 2, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (26, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 22:41','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 4, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (27, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 22:42','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 5, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (28, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 22:42','yyyy-mm-dd HH24:MI')  , 500, 1, 1, 6, 4, null);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (29, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 22:42','yyyy-mm-dd HH24:MI')  , 0, 2, 2, 6, 5, 9);
INSERT INTO avance_pregunta (id, activo, eliminado, fecha_creacion, puntaje, resultado, tipo_avance, usuario_estudiante_id, pregunta, respuesta_pregunta) VALUES (30, true, to_date('09-09-1999','dd-mm-yyyy'), to_timestamp('2020-11-04 22:42','yyyy-mm-dd HH24:MI') , 500, 1, 1, 6, 5, null);

SELECT setval('usuario_id_seq', (SELECT MAX(id) FROM usuario)+1);
SELECT setval('grupo_id_seq', (SELECT MAX(id) FROM grupo)+1);
SELECT setval('datagenia_id_seq', (SELECT MAX(id) FROM datagenia)+1);
SELECT setval('archivo_id_seq', (SELECT MAX(id) FROM archivo)+1);
SELECT setval('nivel_id_seq', (SELECT MAX(id) FROM nivel)+1);
SELECT setval('tema_id_seq', (SELECT MAX(id) FROM tema)+1);
SELECT setval('grupo_nivel_id_seq', (SELECT MAX(id) FROM GRUPO_NIVEL)+1);
SELECT setval('grupo_nivel_tema_id_seq', (SELECT MAX(id) FROM GRUPO_NIVEL_TEMA)+1);
SELECT setval('leccion_id_seq', (SELECT MAX(id) FROM LECCION)+1);
SELECT setval('pregunta_id_seq', (SELECT MAX(id) FROM PREGUNTA)+1);
SELECT setval('opcion_respuesta_id_seq', (SELECT MAX(id) FROM opcion_respuesta)+1);
SELECT setval('grupo_estudiante_id_seq', (SELECT MAX(id) FROM grupo_estudiante)+1);
SELECT setval('avance_pregunta_id_seq', (SELECT MAX(id) FROM avance_pregunta)+1);
