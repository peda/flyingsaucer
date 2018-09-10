mvn clean
mvn package
mvn install:install-file -Dfile=./target/flying-saucer-pdf-9.1.15.jar -DpomFile=pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/