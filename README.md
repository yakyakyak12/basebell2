create database metadb;
use metadb;

-- 테이블 확인
select * from stadium_tb;
select * from team_tb;
select * from player_tb;
select * from out_player_tb;

-- 테이블 삭제
drop table stadium_tb;
drop table team_tb;
drop table player_tb;
drop table out_player_tb;

[CREATE TABLE stadium_tb(
stadium_id int auto_increment primary key,
stadium_name varchar(100),
stadium_created_at timestamp not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE team_tb(
team_id int auto_increment primary key,
stadium_id int,
team_name varchar(100),
team_created_at timestamp not null,
FOREIGN KEY (stadium_id) REFERENCES stadium_tb(stadium_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE player_tb(
player_id int auto_increment primary key,
team_id int,
player_name varchar(100),
player_position varchar(100),
player_created_at timestamp not null,
unique (team_id, player_position),
FOREIGN KEY (team_id) REFERENCES team_tb(team_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE out_player_tb(
out_player_id int auto_increment primary key,
player_id int,
out_player_reason varchar(100),
out_player_created_at timestamp not null,
FOREIGN KEY (player_id) REFERENCES player_tb(player_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
]()
-- 데이터 더미
insert into stadium_tb(stadium_name, stadium_created_at) values('대구 삼성 라이온즈 파크', now());
insert into stadium_tb(stadium_name, stadium_created_at) values('사직 야구장', now());
insert into stadium_tb(stadium_name, stadium_created_at) values('광주 기아 챔피언스 필드', now());

insert into team_tb(stadium_id, team_name, team_created_at) values(1,'삼성', now());
insert into team_tb(stadium_id, team_name, team_created_at) values(2,'롯데', now());
insert into team_tb(stadium_id, team_name, team_created_at) values(3,'광주', now());

insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'윤성환','투수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'강민호','포수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'공민규','1루수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'박계범','2루수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'이원석','3루수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'이학주','유격수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'김현곤','좌익수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'월리엄슨','우익수', now());
insert into player_tb(team_id, player_name, player_position,player_created_at) values(1,'박해민','중견수', now());

insert into out_player_tb(player_id, out_player_reason, out_player_created_at) values(2,'은퇴', now());
