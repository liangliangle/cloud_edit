ps -ef | grep java | grep edit | awk '{print $2}' | xargs kill -9