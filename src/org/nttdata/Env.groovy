#!/usr/bin/groovy

// プロパティ取得メソッド
def call(property)
{
    // nonserializable対策
    config = ['path':'/opt/app/conf', 'file':'env.groovy']
    env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())

    if(env.contains(property))
    {
        println property
    }
    else
    {
        println '[ERROR]存在しないプロパティを参照しています。'
        println '[ERROR]プロパティ名: ' + property
    }
}
