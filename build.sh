sh cloud-edit/stop.sh
git pull
mvn clean package -DskipTests
rm -rf cloud-*
cp CoreModule/target/*.zip .
unzip cloud-edit-1.0.zip
cp -y application.yml cloud-edit/
sh cloud-edit/start.sh
