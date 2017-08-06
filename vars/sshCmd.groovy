#!/usr/bin/groovy

// SSHコマンド実行メソッド
def call(server, cmd)
{
    // 宣言
    def stdout = new StringBuffer(), stderror = new StringBuffer()

    // 設定ファイルロード
    try {
        def config = ['path':'/opt/app/conf', 'file':'env.groovy']
        def job = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    } catch(Exception e) {}

    println env.ssesion.ssh.webap.identity
    println env.ssesion.ssh."${server}".identity
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

    // 実行結果を返却
    def result = ['stdout':stdout, 'stderror':stderror]
    return result
}
