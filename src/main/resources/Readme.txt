http://localhost:8088/insert?name=name 1&email=email 1&password=password 1

http://localhost:8088/update?name=name 06&email=email 6&password=password 06&id=1

http://localhost:8088/find?id=1

http://localhost:8088/findbyemail?email=email 1

http://localhost:8088/findbyname?name=name 1

http://localhost:8088/findall

http://localhost:8088/delete?id=1


NOTE :: To set the jar file into maven repo means follow thw steps, in CMD

1.) mvn install:install-file -Dfile=D:\springGenericApi.jar -DgroupId=com.springGenericApi -DartifactId=springGenericApi -Dversion=1.0 -Dpackaging=jar

2.) in pom.xml file,
	<dependency>
			<groupId>com.springGenericApi</groupId>
			<artifactId>springGenericApi</artifactId>
			<version>1.0</version>
		</dependency>

