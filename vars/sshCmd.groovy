#!/usr/bin/groovy

// SSHコマンド実行メソッド
def call(server, cmd, stdout, stderror, env)
{
    try
    {
        // コマンド組立
        def ssh_cmd = [
            ('ssh -i ' + env.session.ssh."${server}".identity),
            ('-p '     + env.session.ssh."${server}".port),
            (            env.session.ssh."${server}".user + '@' + env.session.ssh."${server}".host),
            (cmd)
        ].join(' ')

        // コマンド実行
        def proc = ssh_cmd.execute()

        // コマンド結果保存先設定
        proc.consumeProcessOutput(stdout, stderror)

        // コマンド結果出力が完了するまで待機
        proc.waitForProcessOutput()
    }
    catch(Exception e) {

        println 'failed'
        throw e
    }

    return [stdout, stderror]
}
