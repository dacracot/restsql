BEGIN TRANSACTION;
/*------------------------------------------------------------------------*/
CREATE TABLE "usr" (
	"key" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"name" TEXT NOT NULL,
	"email" TEXT NOT NULL
);
/*-------------------------------------*/
CREATE INDEX usrPkey ON usr (key);
/*------------------------------------------------------------------------*/
CREATE TABLE "sess" (
	"usrKey" INTEGER NOT NULL,
	"host" TEXT NOT NULL,
	"clock" TEXT NOT NULL DEFAULT current_timestamp,
	FOREIGN KEY("usrKey") REFERENCES usr(key)
);
/*-------------------------------------*/
CREATE INDEX sessUsrFkey ON sess (usrKey);
/*------------------------------------------------------------------------*/
CREATE TABLE "lst" (
	"name" TEXT NOT NULL,
	"owner" INTEGER NOT NULL,
	"member" INTEGER NOT NULL,
	FOREIGN KEY("owner") REFERENCES usr(key),
	FOREIGN KEY("member") REFERENCES usr(key)
);
/*-------------------------------------*/
CREATE INDEX lstPkey ON lst (owner,member);
CREATE INDEX lstUsrFkeyMember ON lst (member);
CREATE INDEX lstUsrFkeyOwner ON lst (owner);
/*------------------------------------------------------------------------*/
CREATE TABLE "dest" (
	"key" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"name" TEXT NOT NULL
);
/*-------------------------------------*/
CREATE INDEX destPkey ON dest (key);
/*------------------------------------------------------------------------*/
CREATE TABLE "src" (
	"key" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"name" TEXT NOT NULL,
	"dir" TEXT NOT NULL,
	"destKey" INTEGER NOT NULL,
	FOREIGN KEY("destKey") REFERENCES dest(key)
);
/*-------------------------------------*/
CREATE INDEX srcPkey ON src (key);
/*------------------------------------------------------------------------*/
CREATE TABLE "batch" (
	"name" TEXT NOT NULL,
	"usrKey" INTEGER NOT NULL,
	"srcKey" INTEGER NOT NULL,
	"clock" TEXT NOT NULL DEFAULT current_timestamp,
	FOREIGN KEY("usrKey") REFERENCES usr(key),
	FOREIGN KEY("srcKey") REFERENCES src(key)
);
/*-------------------------------------*/
CREATE INDEX batchPkey ON batch (usrKey,srcKey);
CREATE INDEX batchSrcFkey ON batch (srcKey);
CREATE INDEX batchUsrFkey ON batch (usrKey);
/*------------------------------------------------------------------------*/
COMMIT;
