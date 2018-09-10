mvn clean
mvn package
mvn install:install-file -Dfile=./flying-saucer-pdf/target/flying-saucer-pdf-9.1.15.jar -DpomFile=./flying-saucer-pdf/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/
mvn install:install-file -Dfile=./flying-saucer-core/target/flying-saucer-core-9.1.15.jar -DpomFile=./flying-saucer-core/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/
mvn install:install-file -Dfile=./flying-saucer-fop/target/flying-saucer-fop-9.1.15.jar -DpomFile=./flying-saucer-fop/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/
mvn install:install-file -Dfile=./flying-saucer-log4j/target/flying-saucer-log4j-9.1.15.jar -DpomFile=./flying-saucer-log4j/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/
mvn install:install-file -Dfile=./flying-saucer-pdf-itext5/target/flying-saucer-pdf-itext5-9.1.15.jar -DpomFile=./flying-saucer-pdf-itext5/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/
mvn install:install-file -Dfile=./flying-saucer-pdf-osgi/target/flying-saucer-pdf-osgi-9.1.15.jar -DpomFile=./flying-saucer-pdf-osgi/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/
mvn install:install-file -Dfile=./flying-saucer-swt/target/flying-saucer-swt-9.1.15.jar -DpomFile=./flying-saucer-swt/pom.xml -DlocalRepositoryPath=/home/prainer/dev/invoicer/repo/

