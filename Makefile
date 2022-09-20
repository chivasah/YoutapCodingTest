include  ~/graal.mk

.DEFAULT_GOAL=sonar

run-dev:
	mvn spring-boot:run -Drun.jvmArguments="-Xss256k -Xms256m -Xmx283088K -XX:ReservedCodeCacheSize=64M -XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=121289K"

run:test
	mvn clean package spring-boot:repackage
	java -jar -Xss256k -Xms256m -Xmx283088K -XX:ReservedCodeCacheSize=64M -XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=121289K ./target/YoutapCodingTest-0.0.1-SNAPSHOT.jar

sonar:jacoco
	mvn sonar:sonar -Dsonar.projectKey=YoutapCodingTest -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7b5a066feb9baa7da1619b5bece5e92162cae4da

jacoco:test
	mvn jacoco:report

test:clean
	mvn test

clean:
	mvn clean

build:
	mvn clean compile war:war spring-boot:repackage
