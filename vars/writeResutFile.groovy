#!/usr/bin/groovy

// 変数をファイルに書き込むメソッド
def call(data, filename)
{
    def temp = ['path':'/opt/var/jenkins/temp', 'file':'result_' + filename + '.yml']
    writeYaml(
        file: temp['path'] + "/" + temp['file'],
        data: data
    )
}
