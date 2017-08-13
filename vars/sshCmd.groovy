#!groovy

// SSHコマンド実行メソッド
def call(server, cmd)
{
    stdout = new StringBuffer(), stderror = new StringBuffer()

    // AP設定ロード
    config = loadAppConfig()

    // コマンド組立
    ssh_cmd = [
        ('ssh -i ' + config.session.ssh."${server}".identity),
        ('-p '     + config.session.ssh."${server}".port),
        (            config.session.ssh."${server}".user + '@' + config.session.ssh."${server}".host),
        (cmd)
    ].join(' ')

    // 開始メッセージ
    printMsg('info', 'SSHコマンドを実行します。')
    printMsg('info', 'Command Param: server[' + server + ']')
    printMsg('info', 'Command Param: command[' + cmd + ']')

    // コマンド実行
    def proc = ssh_cmd.execute()

    // コマンド結果保存先設定
    proc.consumeProcessOutput(stdout, stderror)

    // コマンド結果出力が完了するまで待機
    proc.waitForProcessOutput()

    // コマンド結果をファイルに出力
    printMsg('info', 'コマンド結果をファイルに出力します。')

    if(stderror.size() == 0)
    {
        // 標準出力
        temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_ssh_cmd_wu_execute_cnt_stdout_' + server + '.log']
        printMsg('info', '標準出力結果出力先: ' + temp['path'] + "/" + temp['file'])

        File f = new File(temp['path'] + "/" + temp['file'])
        f.write(stdout)
    }
    else
    {
        // 標準出力
        temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_ssh_cmd_wu_execute_cnt_stdout_' + server + '.log']
        printMsg('info', '標準出力結果出力先: ' + temp['path'] + "/" + temp['file'])

        File f = new File(temp['path'] + "/" + temp['file'])
        f.write(stdout)

        // 標準エラー出力
        temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_ssh_cmd_wu_execute_cnt_stderror_' + server + '.log']
        printMsg('info', '標準エラー出力結果出力先: ' + temp['path'] + "/" + temp['file'])

        File f = new File(temp['path'] + "/" + temp['file'])
        f.write(stderror)
    }

    // コマンド結果を返却
    if(stderror.size() == 0)
    {
        return ['stdout':stdout]
    }
    else
    {
        return ['stdout':stdout, 'stderror':stderror]
    }
}
