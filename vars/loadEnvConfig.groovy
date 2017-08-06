#!/usr/bin/groovy

// バッチ設定ロード
def call()
{
    // 設定ファイルロード
    try {
        def config = ['path':'/opt/app/conf', 'file':'env.groovy']
        def job = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    } catch(Exception e) {}
}