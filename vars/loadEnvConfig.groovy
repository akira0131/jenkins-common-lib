#!/usr/bin/groovy

// 設定ファイルロードメソッド
def call()
{
    // nonserializable対策
    try
    {
        config = ['path':'/opt/app/conf', 'file':'env.groovy']
        env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    }
    catch(Exception e) {}

    println env
    println env.size()
    return env
}
