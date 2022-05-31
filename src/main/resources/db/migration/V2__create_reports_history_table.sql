create table if not exists reports_history
(
    id            uuid primary key,
    user_id       uuid,
    before_status int,
    after_status  int,
    report_id     uuid,
    create_time   timestamp
);

comment on column reports_history.id is 'Порядковый номер, уникальный';
comment on column reports_history.user_id is 'Идентификатор пользователя, который изменил статус жалобы';
comment on column reports_history.before_status is 'Предыдущий статус обработки жалобы';
comment on column reports_history.after_status is 'Текущий статус обработки жалобы';
comment on column reports_history.report_id is 'Идентификатор жалобы';
comment on column reports_history.create_time is 'Дата добавления жалобы в БД';
