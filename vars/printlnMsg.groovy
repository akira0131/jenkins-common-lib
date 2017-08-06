#!/usr/bin/groovy

// メッセージ出力メソッド
def call(string type,string msg)
{
    ansiColor('xterm')
    {
        switch(type)
        {
            case 'info':

                def ansiColorMsg = '[INFO]' + msg
                echo "${ansiColorMsg}"
                break

            case 'warning':

                def ansiColorMsg = '[WARNING]' + msg
                echo "\033[0;31m${ansiColorMsg}\033[0m"
                break

            case 'error':

                def ansiColorMsg = '[ERROR]' + msg
                echo "\033[0;31m${ansiColorMsg}\033[0m"
                break
        }
    }
}