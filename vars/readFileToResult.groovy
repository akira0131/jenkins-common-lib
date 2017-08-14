#!groovy

// ジョブ結果取得メソッド
def call(filename)
{
    // 読込対象のファイル
    def file = ['path':'/opt/var/jenkins/temp', 'name': 'result_job_' + filename + '.yml']

    printMsg('info', 'ジョブ結果を読込みます。')
    printMsg('info', '出力先: ' + file['path'] + "/" + file['name'])

    // Yaml形式を読込
    def result = readYaml(
        file: file['path'] + "/" + file['name']
    )

    return result
}
