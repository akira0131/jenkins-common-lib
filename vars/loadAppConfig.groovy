#!groovy

// 設定ファイルロードメソッド
def call()
{
    // 読込対象のファイル
    def config = ['path':'/opt/app/conf', 'file':'env.yml']

    printMsg('info', 'ファイルからプロパティをロードします。')
    printMsg('info', '入力先: ' + config['path'] + "/" + config['file'])

    def env = readYaml(
        file: config['path'] + "/" + config['file']
    )

    return env
}
