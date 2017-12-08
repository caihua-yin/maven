Leader election based on Zookeeper
==================================

# Build package
$ mvn package

# Start one process
$ java -jar target/leadelection-0.0.1-SNAPSHOT-jar-with-dependencies.jar
Fri Dec 08 23:04:10 CST 2017: I am slave, I am standby...
Fri Dec 08 23:04:20 CST 2017: Start finished.
Fri Dec 08 23:05:44 CST 2017: I am leader, I am doing my job...
Fri Dec 08 23:05:45 CST 2017: I am leader, I am doing my job...
Fri Dec 08 23:05:46 CST 2017: I am leader, I am doing my job...

# Start another process
$ java -jar target/leadelection-0.0.1-SNAPSHOT-jar-with-dependencies.jar
Fri Dec 08 23:04:10 CST 2017: I am slave, I am standby...
Fri Dec 08 23:04:20 CST 2017: Start finished.

# Stop first process, second process will take leadership in about 40s
