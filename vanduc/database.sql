CREATE TABLE table_street(
	street_id BIGINT PRIMARY KEY,
	street_name TEXT,
	street_class  VARCHAR(10)
);
CREATE TABLE table_node(
	node_id	BIGINT PRIMARY KEY,
	node_lon VARCHAR(10),
	node_lat VARCHAR(10),
	node_time DATETIME,
	node_velocity FLOAT,
	street_id BIGINT,
	FOREIGN KEY(street_id) REFERENCES table_street(street_id)
);
