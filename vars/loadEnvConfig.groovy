#!/usr/bin/groovy

// 設定ファイルロードメソッド
def call()
{
    // nonserializable対策
    try {
        def config = ['path':'/opt/app/conf', 'file':'env.groovy']
        def job = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    } catch(Exception e) {}

    return this
}