#!/bin/sh
APP_MAIN="nifa-reporting-service-1.0.jar"

PROFILE=
if [ ! -z "${1}" ]; then
    PROFILE=${1}
else
    echo "Profile must be specified, exit"
    exit 0
fi

PID=0

getPID(){
    javaps=`ps axo pid,command | grep $APP_MAIN |grep ${PROFILE}`
    if [ -n "$javaps" ]; then
        PID=`echo $javaps | awk '{print $1}'`
    else
        PID=0
    fi
}

# 停止Java应用程序
# ------------------------------------------------------------------------------------------------------
# 1、调用getPID()函数，刷新$PID全局变量
# 2、若程序已经启动（$PID不等于0），则开始执行停止程序操作，否则提示程序未运行
# 3、使用[kill -9 PID]命令强制杀掉进程
# 4、使用[$?]获取上一句命令的返回值，若其为0，表示程序已停止运行，则打印Success，反之打印Failed
# 5、为防止Java程序被启动多次，这里增加了反复检查程序进程的功能，通过递归调用shutdown()函数的方式，反复kill
# 注意：Shell编程中，[$?]表示上一句命令或者上一个函数的返回值
# ------------------------------------------------------------------------------------------------------
shutdown(){
    getPID
    echo "==============================================================================================="
    if [ $PID -ne 0 ]; then
        echo -n "Stopping $APP_MAIN(PID=$PID)..."
        kill -9 $PID
        if [ $? -eq 0 ]; then
            echo "[Success]"
            echo "==============================================================================================="
        else
            echo "[Failed]"
            echo "==============================================================================================="
        fi
        getPID
        if [ $PID -ne 0 ]; then
            shutdown
        fi
    else
        echo "$APP_MAIN is not running"
        echo "==============================================================================================="
    fi
}

shutdown
