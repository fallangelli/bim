

call mvn clean install -DskipTests



cd target


java -cp bim-grabserver-1.0-SNAPSHOT.jar;./lib/* org.springframework.batch.core.launch.support.CommandLineJobRunner classpath:/job-config.xml syncJob type=all source=2 date='2015-04-25 14:00'