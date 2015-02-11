CREATE TABLE NoteTable (ID INT(8) auto_increment primary key, NoteID INT(8), Lat FLOAT(10,6), Lon FLOAT(10,6), StreetID INT(8),	Speed VARCHAR(20), Density VARCHAR(20), TimeStamp VARCHAR(20));
	
CREATE TABLE StreetTable (StreetID INT(8) primary key, Label VARCHAR(50), Type VARCHAR(10));