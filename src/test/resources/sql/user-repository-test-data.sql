
-- insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
-- values (1, 'kok202@naver.com', 'kok202', 'Seoul', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'ACTIVE', 0);
insert into `member` (`mem_id`, `password`, `email`, `nickname`, `role`, `create_date_time`) values ('20230705-b5aa2484b8b345d586804fef824bbd8e', '$2a$10$mnWEqJxaRi6UgWn.fBXHv.NqHAKBqUaWKUXJ3op00VJLuGWh/Ir1e', 'testmail@naver.com', 'nickname', 'MEMBER', now());