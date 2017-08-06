#!/usr/bin/groovy

// SSHコマンド実行メソッド
def sshCmd(server, cmd)
{
    // 宣言
    def stdout = new StringBuffer(), stderror = new StringBuffer()

    // nonserializable対策
    try {
        // 設定ファイルロード
        def config = ['path':'/opt/app/conf', 'file':'env.groovy']
        def env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    } catch(Exception e) {}

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

    return stdout,stderr
}
