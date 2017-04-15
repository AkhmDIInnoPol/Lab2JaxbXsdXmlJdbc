CREATE TABLE crm_user
(
  id serial NOT NULL,
  login character varying(255) NOT NULL,
  user_password character varying(1000) NOT NULL,
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  is_active boolean NOT NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE role
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  code character varying(255) NOT NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user_role
(
  id serial NOT NULL,
  crm_user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT pk_user_role PRIMARY KEY (id),
  CONSTRAINT fk_crm_user_role FOREIGN KEY (crm_user_id)
      REFERENCES crm_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_user_role_role FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE student
(
  id serial NOT NULL,
  is_deleted boolean NOT NULL,
  crm_user_id integer,
  CONSTRAINT pk_student PRIMARY KEY (id),
  CONSTRAINT fk_student_crm_user FOREIGN KEY (crm_user_id)
      REFERENCES crm_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE criterion
(
  id serial NOT NULL,
  title character varying(255) not null,
  max_points integer not null,
  is_additional boolean default false,
  created timestamp without time zone,
  CONSTRAINT pk_criterion PRIMARY KEY (id)
);

CREATE TABLE criterion_type
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT criterion_type_id PRIMARY KEY (id)
);


CREATE TABLE questionnaire_criterion
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  description character varying(2000) NULL,
  max_grade integer NOT NULL,
  criterion_type_id integer NOT NULL,
  crm_user_id_created integer NOT NULL,
  crm_user_id_edited integer NULL,
  edit_date timestamp without time zone NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT questionnaire_criterion_id PRIMARY KEY (id),
  CONSTRAINT fk_criterion_type_to_questionnaire_criterion FOREIGN KEY (criterion_type_id)
      REFERENCES criterion_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_crm_user_to_questionnaire_criterion FOREIGN KEY (crm_user_id_created)
      REFERENCES crm_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_crm_user_to_questionnaire_criterion2 FOREIGN KEY (crm_user_id_edited)
      REFERENCES crm_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE transcript
(
  id serial NOT NULL,
  questionnaire_criterion_id integer NOT NULL,
  min_value integer NOT NULL,
  max_value integer NOT NULL,
  text_value character varying(2000) NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT transcript_id PRIMARY KEY (id),
  CONSTRAINT fk_questionnaire_criterion_to_transcript FOREIGN KEY (questionnaire_criterion_id)
      REFERENCES questionnaire_criterion (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE questionnaire
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  is_default boolean NOT NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT questionnaire_id PRIMARY KEY (id)
);
CREATE TABLE questionnaire_criterion_to_questionnaire
(
  id serial NOT NULL,
  questionnaire_criterion_id integer NOT NULL,
  questionnaire_id integer NOT NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT pk_questionnaire_criterion_to_questionnaire PRIMARY KEY (id),
  CONSTRAINT fk_quest_criterion_to_quest_quest_criterion FOREIGN KEY (questionnaire_criterion_id)
      REFERENCES questionnaire_criterion (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_questionnaire_criterion_to_questionnaire_questionnaire FOREIGN KEY (questionnaire_id)
      REFERENCES questionnaire (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE questionnaire_instance
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  student_id integer NOT NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT questionnaire_instance_id PRIMARY KEY (id),
  CONSTRAINT fk_student_to_questionnaire_instance FOREIGN KEY (student_id)
      REFERENCES student (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE questionnaire_criterion_instance
(
  id serial NOT NULL,
  questionnaire_instance_id integer NOT NULL,
  name character varying(255) NOT NULL,
  max_grade integer NOT NULL,
  grade integer NULL,
  criterion_type_id integer NOT NULL,
  crm_user_id_edited integer NULL,
  edit_date timestamp without time zone NULL,
  is_deleted boolean NOT NULL,
  CONSTRAINT questionnaire_criterion_instance_id PRIMARY KEY (id),
  CONSTRAINT fk_questionnaire_instance_to_questionnaire_criterion_instance FOREIGN KEY (questionnaire_instance_id)
      REFERENCES questionnaire_instance (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_criterion_type_to_questionnaire_criterion_instance FOREIGN KEY (criterion_type_id)
      REFERENCES criterion_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_crm_user_to_questionnaire_criterion_instance FOREIGN KEY (crm_user_id_edited)
      REFERENCES crm_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE operation
(
  id serial NOT NULL,
  code character varying(255),
  operation_name character varying(255),
  is_deleted boolean NOT NULL,
  CONSTRAINT pk_operation PRIMARY KEY (id)
);

CREATE TABLE resource
(
  id serial NOT NULL,
  code character varying(255),
  name character varying(255),
  url character varying(255),
  is_deleted boolean NOT NULL,
  CONSTRAINT pk_resource PRIMARY KEY (id)
);

CREATE TABLE operation_resource
(
  id serial NOT NULL,
  resource_id integer NOT NULL,
  operation_id integer NOT NULL,
  CONSTRAINT pk_operation_resource PRIMARY KEY (id),
  CONSTRAINT fk_operation_resource_resource FOREIGN KEY (resource_id)
      REFERENCES resource (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_operation_resource_operation FOREIGN KEY (operation_id)
      REFERENCES operation (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE task
(
  id serial NOT NULL,
  title character varying(32) not null,
  description character varying(128) not null,
  is_lab boolean default false,
  created timestamp without time zone,
  CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE role_resource
(
  id serial NOT NULL,
  resource_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT pk_role_resource PRIMARY KEY (id),
  CONSTRAINT fk_role_resource_resource FOREIGN KEY (resource_id)
      REFERENCES resource (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_role_resource_role FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE study_group
(
  id serial NOT NULL,
  name character varying(255),
  description character varying(1000),
  is_deleted boolean NOT NULL,
  CONSTRAINT pk_study_group PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE student_to_group
(
  id serial NOT NULL,
  student_id integer NOT NULL,
  study_group_id integer NOT NULL,
  CONSTRAINT pk_student_to_group PRIMARY KEY (id),
  CONSTRAINT fk_student_to_group_study_group FOREIGN KEY (study_group_id)
      REFERENCES study_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_student_to_group_student FOREIGN KEY (student_id)
      REFERENCES student (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE mark(
    id serial NOT NULL,
    is_deleted boolean NOT NULL,
    student_id integer NOT NULL,
    task_id integer NOT NULL,
    criterion_id integer NOT NULL,
    points integer NOT NULL,
    CONSTRAINT pk_mark PRIMARY KEY (id),
    CONSTRAINT fk_mark_student FOREIGN KEY (student_id)
      REFERENCES student (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_mark_task FOREIGN KEY (task_id)
      REFERENCES task(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_mark_criterion FOREIGN KEY (criterion_id)
      REFERENCES criterion(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE  lesson  (
   id serial NOT NULL,
   study_group_id  integer NOT NULL,
   lesson_date  timestamp without time zone NOT NULL,
   room  integer NOT NULL,
   topic  character varying(255) NOT NULL,
   description  character varying(2000),
   lesson_comment  character varying(2000),
   token  character varying(255),
   token_expiration timestamp without time zone,
   is_deleted  boolean NOT NULL,
  CONSTRAINT fk_lesson PRIMARY KEY (id),
  CONSTRAINT fk_lesson_study_group FOREIGN KEY (study_group_id)
      REFERENCES study_group(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE  student_activity  (
   id serial NOT NULL,
   student_id  integer NOT NULL,
   lesson_id  integer NOT NULL,
   activity_grade  integer,
   activity_comment  character varying(2000),
   is_deleted  boolean NOT NULL,
  CONSTRAINT pk_student_activity PRIMARY KEY (id),
  CONSTRAINT fk_lesson_task FOREIGN KEY (lesson_id)
  REFERENCES lesson (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);




CREATE TABLE journal
(
    id serial NOT NULL,
    lesson_id integer NOT NULL,
    student_id integer NOT NULL,
    time_check timestamp without time zone NOT NULL,
    is_deleted  boolean NOT NULL,
    CONSTRAINT pk_journal PRIMARY KEY (id),
  CONSTRAINT fk_journal_student FOREIGN KEY (student_id)
      REFERENCES student (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_journal_lesson FOREIGN KEY (lesson_id)
      REFERENCES lesson(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE task_to_group
(
  id serial NOT NULL,
  task_id integer not null,
  study_group_id integer not null,
  is_active boolean default true,
  end_date timestamp without time zone not null,
  CONSTRAINT pk_task_to_group PRIMARY KEY (id),
  CONSTRAINT fk_task_group_task FOREIGN KEY (task_id)
      REFERENCES task (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_group_group FOREIGN KEY (study_group_id)
      REFERENCES study_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE criterion_to_task
(
  id serial NOT NULL,
  task_id integer not null,
  criterion_id integer not null,
  CONSTRAINT pk_criterion_to_task PRIMARY KEY (id),
    CONSTRAINT fk_criterion_task_task FOREIGN KEY (task_id)
      REFERENCES task(id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_criterion_task_criterion FOREIGN KEY (criterion_id)
      REFERENCES criterion (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE settings
(
  id serial NOT NULL,
  code character varying(255),
  value character varying(255),
  is_actual boolean NOT NULL,
  CONSTRAINT pk_settings PRIMARY KEY (id),
  CONSTRAINT setting_code_key UNIQUE (code)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE download
(
  id serial NOT NULL,
  student_id integer NOT NULL,
  task_id integer NOT NULL,
  git_url character varying(255),
  date timestamp without time zone NOT NULL,
  file_name character varying(255),
  download_url character varying(255),
  is_deleted boolean NOT NULL DEFAULT false,
  CONSTRAINT pk_download PRIMARY KEY (id),
  CONSTRAINT fk_download_student FOREIGN KEY (student_id)
      REFERENCES student (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_download_task FOREIGN KEY (task_id)
      REFERENCES task (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);