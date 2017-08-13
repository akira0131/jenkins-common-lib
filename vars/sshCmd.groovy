#!groovy

// SSHコマンド実行メソッド
def call(server, cmd)
{
    def stdout = new StringBuffer(), stderror = new StringBuffer()

    // AP設定ロード
    def config = loadAppConfig()

    // コマンド組立
    def ssh_cmd = [
        ('ssh -i ' + config.session.ssh."${server}".identity),
        ('-p '     + config.session.ssh."${server}".port),
        (            config.session.ssh."${server}".user + '@' + config.session.ssh."${server}".host),
        (cmd)
    ].join(' ')

    // 開始メッセージ
    //printMsg('info', 'SSHコマンドを実行します。')
    //printMsg('info', 'Command Param: server = ' + server)
    printMsg('info', 'Command Param: command = ' + cmd)

    // コマンド実行
    def proc = ssh_cmd.execute()

    // コマンド結果保存先設定
    proc.consumeProcessOutput(stdout, stderror)

    // コマンド結果出力が完了するまで待機
    proc.waitForProcessOutput()

    // コマンド結果をファイルに出力
    //printMsg('info', 'コマンド結果をファイルに出力します。')

    //if(stderror.size() == 0)
    //{
        // 標準出力
    //    temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_ssh_cmd_stdout_' + server + '.log']
    //}
    //else {
    //    return ['stdout':stdout, 'stderror':stderror]
    //}

    // 標準エラーが発生している場合は、標準エラーも返却する
    if(stderror.size() == 0)
    {
        return ['stdout':stdout]
    }
    else {
        return ['stdout':stdout, 'stderror':stderror]
    }
}
