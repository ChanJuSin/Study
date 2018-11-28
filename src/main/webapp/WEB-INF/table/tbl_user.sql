CREATE TABLE TBL_USER
(
	IDX INT NOT NULL,
	EMAIL VARCHAR(150) NOT NULL,
	PW VARCHAR(150) NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	AUTH_WT CHAR(1) DEFAULT FALSE,
	SESSION_KEY VARCHAR(150),
	SESSION_LIMIT TIMESTAMP,
	CREA_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
