#!/usr/bin/groovy

// 設定ファイルロードメソッド
def call(env)
{
    def config = ['path':'/opt/app/conf', 'file':'env.yml']
    env = readYaml(
        file: config['path'] + "/" + config['file']
    )

    return env
}
