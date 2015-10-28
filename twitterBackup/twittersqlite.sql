BEGIN TRANSACTION;
CREATE TABLE `user` (
	`izena`	TEXT
);
CREATE TABLE "txioak" (
	`nork`	TEXT,
	`data`	TEXT,
	`txioa`	TEXT,
	`rtkop`	INTEGER,
	`favkop`	INTEGER
);
CREATE TABLE "token" (
	`accessToken`	TEXT,
	`accessTokenSecret`	TEXT,
	`consumerKey`	TEXT,
	`consumerKeySecret`	TEXT
);
CREATE TABLE "rt" (
	`data`	TEXT,
	`nork`	TEXT,
	`txioa`	TEXT,
	`rtkop`	INTEGER,
	`favkop`	INTEGER
);
CREATE TABLE "md" (
	`nork`	TEXT,
	`data`	TEXT,
	`mezua`	TEXT
);
CREATE TABLE "jarraitzaileak" (
	`nor`	TEXT
);
CREATE TABLE `jarraituak` (
	`nor`	TEXT
);
CREATE TABLE "fav" (
	`nork`	INTEGER,
	`data`	TEXT,
	`txioa`	TEXT,
	`rtkop`	INTEGER,
	`favkop`	INTEGER
);
COMMIT;
