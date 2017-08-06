#!/usr/bin/groovy

// SSHコマンド実行メソッド
def call(server, cmd)
{
    // 宣言
    def stdout = new StringBuffer(), stderror = new StringBuffer()

    try
    {
        // 設定ファイルロード
        loadEnvConfigSshCmd()

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

// 設定ファイルロード
@NonCPS
def loadEnvConfigSshCmd()
{
    try
    {
        def config = ['path':'/opt/app/conf', 'file':'env.groovy']
        def env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    }
    catch(Exception e) {}

    return env
}
