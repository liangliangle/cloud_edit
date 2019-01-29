echo  1:stop
echo --------------------------
sh cloud-edit/stop.sh
echo  2:update code
echo --------------------------
git pull
echo  3:package project
echo --------------------------
mvn clean package -DskipTests

rm -rf cloud-*
cp CoreModule/target/*.zip .
echo  4:build start
echo --------------------------
unzip cloud-edit-1.0.zip
rm  -f cloud-edit/application.yml
cp  application.yml cloud-edit/
rm -f cloud-edit-1.0.zip
echo  5: start project
echo --------------------------
sh cloud-edit/start.sh
