#!/usr/bin/groovy

// 設定ファイルロードメソッド
def call()
{
    def config = [], env = [:]

    // nonserializable対策
    try
    {
        config = ['path':'/opt/app/conf', 'file':'env.groovy']
        env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())
    }
    catch(Exception e) {

        println 'ここは通らないよね？'
    }

    retrun env
}