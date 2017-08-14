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
    printMsg('info', 'SSHコマンドを実行します。')
    printMsg('info', 'Command Param: server = ' + server)
    printMsg('info', 'Command Param: command = ' + cmd)

    // コマンド実行
    def proc = ssh_cmd.execute()

    // コマンド結果を保存するストリーム先設定
    proc.consumeProcessOutput(stdout, stderror)

    // コマンド結果の出力が完了するまで待機
    proc.waitForProcessOutput()

    // コマンド結果をファイルに書き出す
    writeBufferToFile(stdout, stderror)

    // 標準エラーが発生している場合は、標準エラーも返却する
    if(stderror.size() == 0)
    {
        // ファイル読込み
        stringStdout = new File('/opt/var/jenkins/temp/ssh_cmd_stdout.txt').text

        return ['stdout':stringStdout]
    }
    else
    {
        // ファイル読込み
        stringStdout = new File('/opt/var/jenkins/temp/ssh_cmd_stdout.txt').text
        stringStderror = new File('/opt/var/jenkins/temp/ssh_cmd_stderror.txt').text

        return ['stdout':stringStdout, 'stderror':stringStderror]
    }
}

// コマンド結果をファイルに書き出すメソッド
def writeBufferToFile(stdout, stderror)
{
    // 標準エラーが発生している場合は、標準エラーもファイルに書き出す
    if(stderror.size() == 0)
    {
        // 標準出力

        // コマンド結果を書き出すインスタンス生成
        BufferedWriter bwout = new BufferedWriter(new FileWriter(new File('/opt/var/jenkins/temp/ssh_cmd_stdout.txt')))

        // 書き出すデータをセット
        bwout.write(stdout.toString())

        // メモリからファイルに書き出す
        bwout.flush()

        // 処理反映
        bwout.close()
    }
    else
    {
        // 標準出力

        // コマンド結果を書き出すインスタンス生成
        BufferedWriter bwout = new BufferedWriter(new FileWriter(new File('/opt/var/jenkins/temp/ssh_cmd_stdout.txt')))

        // 書き出すデータをセット
        bwout.write(stdout.toString())

        // メモリからファイルに書き出す
        bwout.flush()

        // 処理反映
        bwout.close()

        // 標準エラー出力

        // コマンド結果を書き出すインスタンス生成
        BufferedWriter bwerror = new BufferedWriter(new FileWriter(new File('/opt/var/jenkins/temp/ssh_cmd_stderror.txt')))

        // 書き出すデータをセット
        bwerror.write(stderror.toString())

        // メモリからファイルに書き出す
        bwerror.flush()

        // 処理反映
        bwerror.close()
    }
}
