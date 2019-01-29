ps -ef | grep java | grep edit | awk '{print $2}' | xargs kill -9
nohup java -jar kpi-backend-1.0.0.jar &