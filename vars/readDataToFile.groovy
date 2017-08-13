#!groovy

// 設定ファイルロードメソッド
def call(filename)
{
    // 読込対象のファイル
    def temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_job_judge_' + filename + '.yml']

    printMsg('info', 'ファイルからデータを読込みます。')
    printMsg('info', '出力先: ' + config['path'] + "/" + config['file'])

    def env = readYaml(
        file: config['path'] + "/" + config['file']
    )

    return env
}
