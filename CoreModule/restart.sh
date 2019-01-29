ps -ef | grep java | grep edit | awk '{print $2}' | xargs kill -9
nohup java -jar cloud-edit-1.0.jar &