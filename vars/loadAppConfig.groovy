#!/usr/bin/groovy

// 設定ファイルロードメソッド
def call()
{
    def config = ['path':'/opt/app/conf', 'file':'env.yml']
    def env = readYaml(
        file: config['path'] + "/" + config['file']
    )

    return env
}
