#!/usr/bin/groovy

// 設定ファイルロードメソッド
def call(env)
{
    // nonserializable対策
    try
    {
        //def config = ['path':'/opt/app/conf', 'file':'env.groovy']
        //env = new ConfigSlurper().parse(new File(config['path'] + "/" + config['file']).toURL())

    env.with {

       session { ssh {
          // webapサーバ
          webap.host     = '127.0.0.1'
          webap.port     = 22
          webap.user     = 'redmine'
          webap.password = 'redmine'
          webap.identity = System.properties.'user.home' + '/.ssh/id_rsa'
          // batchサーバ
          batch.host     = '127.0.0.1'
          batch.port     = 22
          batch.user     = 'redmine'
          batch.password = 'redmine'
          batch.identity = System.properties.'user.home' + '/.ssh/id_rsa'
       }}
    }
    }
    catch(Exception e) {

    	println 'env: failed'
    	throw e
    }

    return env
}
