#!/bin/sh

# JDK路径
JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.171-7.b10.el7.x86_64"

CURRENT_DIR="$(cd "$(dirname "$0" )" && pwd )"

PROFILE=
if [ ! -z "${1}" ]; then
    PROFILE=${1}
else
    echo "Profile must be specified, exit"
    exit 0
fi

# JVM启动参数
# -server：一定要作为第一个参数，多个CPU时性能佳
# -Xloggc：记录GC日志，建议写成绝对路径，如此便可在任意目录下执行该shell脚本
#JAVA_OPTS="-server -Xms2048m -Xmx2048m -Xloggc:${CURRENT_DIR}/gc.log"
JAVA_OPTS="-jar -Xloggc:${CURRENT_DIR}/gc-${PROFILE}.log"

# Java程序日志
APP_LOG=${CURRENT_DIR}/runlog-${PROFILE}

# Java程序主体所在的目录，即classes的上一级目录
APP_HOME=${CURRENT_DIR}

# Java主程序，也就是main(String[] args)方法类
APP_MAIN="nifa-sftp-service.jar"

# classpath参数，包括指定lib目录下的所有jar
#CLASSPATH=$APP_HOME/classes
#for tradePortalJar in "$APP_HOME"/lib/*.jar;
#do
#   CLASSPATH="$CLASSPATH":"$tradePortalJar"
#done

# 初始化全局变量，用于标识交易前置系统的PID（0表示未启动）
PID=0

# 获取Java应用的PID
# ------------------------------------------------------------------------------------------------------
# 说明：通过JDK自带的jps命令，联合Linux中的grep命令，可以准确查找到Java应用的PID
#       [jps -l]表示显示Java主程序的完整包路径
#       awk命令可以分割出PID（$1部分）及Java主程序名称（$2部分）
# 例子：[$JAVA_HOME/bin/jps -l | grep $APP_MAIN]命令执行，会看到[5775 com.cucpay.tradeportal.MainApp]
# 另外：这个命令也可以取到程序的PID-->[ps aux|grep java|grep $APP_MAIN|grep -v grep|awk '{print $2}']
# ------------------------------------------------------------------------------------------------------
getPID(){
    javaps=`ps axo pid,command | grep $APP_MAIN |grep ${PROFILE}`
    if [ -n "$javaps" ]; then
        PID=`echo $javaps | awk '{print $1}'`
    else
        PID=0
    fi
}

# 启动Java应用程序
# ------------------------------------------------------------------------------------------------------
# 1、调用getTradeProtalPID()函数，刷新$tradePortalPID全局变量
# 2、若程序已经启动（$tradePortalPID不等于0），则提示程序已启动
# 3、若程序未被启动，则执行启动命令
# 4、启动命令执行后，再次调用getTradeProtalPID()函数
# 5、若步骤4执行后，程序的PID不等于0，则打印Success，反之打印Failed
# 注意：[echo -n]表示打印字符后不换行
# 注意：[nohup command > /path/nohup.log &]是将作业输出到nohup.log，否则它会输出到该脚本目录下的nohup.out中
# ------------------------------------------------------------------------------------------------------
startup(){
    getPID
    echo "==============================================================================================="
    if [ $PID -ne 0 ]; then
        echo "$APP_MAIN already started(PID=$PID)"
        echo "==============================================================================================="
    else
        echo -n "Starting $APP_MAIN"
#        nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN > $APP_LOG/nohup.log &
        nohup $JAVA_HOME/bin/java $JAVA_OPTS $APP_MAIN --spring.profiles.active=${PROFILE} > $APP_LOG 2>&1 &
        getPID
        if [ $PID -ne 0 ]; then
            echo "(PID=$PID)...[Success]"
            echo "==============================================================================================="
        else
            echo "[Failed]"
            echo "==============================================================================================="
        fi
    fi
}

# 调用启动命令
startup
