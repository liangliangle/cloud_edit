sh cloud-edit/stop.sh
git pull
mvn clean package -DskipTests
rm -rf cloud-*
cp CoreModule/target/*.zip .
unzip cloud-edit-1.0.zip
cp –r –f application.yml cloud-edit/
rm -f cloud-edit-1.0.zip
sh cloud-edit/start.sh
