create table if not exists reports
(
    id           uuid primary key,
    type         int,
    user_id      uuid,
    subject_type int,
    subject_id   uuid,
    status       int,
    text         varchar(255),
    create_time  timestamp
);

comment on column reports.id is 'Порядковый номер, уникальный';
comment on column reports.subject_type is 'Тип объекта к которому относится жалоба, уникальный';
comment on column reports.subject_id is 'Идентификатор объекта к которому относится жалоба';
comment on column reports.user_id is 'Идентификатор пользователя, которым оставлена жалоба';
comment on column reports.type is 'Идентификатор причины жалобы из списка имеющихся';
comment on column reports.status is 'Текущий статус обработки жалобы';
comment on column reports.text is 'Текст жалобы';
comment on column reports.create_time is 'Дата добавления жалобы в БД';
