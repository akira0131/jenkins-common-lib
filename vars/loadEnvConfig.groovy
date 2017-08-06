#!/usr/bin/groovy

// 設定ファイルロードメソッド
@NonCPS
def call()
{
    // nonserializable対策
    try
    {
        config = ['path':'/opt/app/conf', 'file':'env.groovy']
        env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    }
    catch(Exception e) {}

    return env
}
