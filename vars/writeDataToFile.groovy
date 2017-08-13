#!groovy

// 変数をファイルに書き出すメソッド
def call(data, filename)
{
    // 書込対象のファイル
    def config = ['path':'/opt/var/jenkins/temp', 'file': 'result_job_judge_' + filename + '.yml']

    printMsg('info', 'データをファイルに出力します。')
    printMsg('info', '出力先: ' + config['path'] + "/" + config['file'])

    // ファイルが既に存在していた場合は削除する
    File f = new File(config['path'] + "/" + config['file'])
    if(f.exists())
    {
        f.delete()
        printMsg ('info', '書込対象のファイルが既に存在しているため、削除しました。')
    }

    // Yaml形式で書込
    writeYaml(
        file: config['path'] + "/" + config['file'],
        data: data
    )
}
