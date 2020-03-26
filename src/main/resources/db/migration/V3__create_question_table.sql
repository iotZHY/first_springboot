create table question
(
	id int auto_increment,
	title varchar(50) null,
	description text null,
	gmt_create BIGINT null,
	gmt_modified bigint null,
	creater int null,
	comment_count int default 0 null,
	read_count int default 0 null,
	like_count int default 0 null,
	tag varchar(256) null,
	constraint question_pk
		primary key (id)
);

