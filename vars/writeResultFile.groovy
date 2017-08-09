#!groovy

// 変数をファイルに書き込むメソッド
def call(data, filename)
{
    // 書込対象のファイル
    def temp = ['path':'/opt/var/jenkins/temp', 'file':'result_' + filename + '.yml']

    // ファイルが既に存在していた場合は削除する
    File f = new File(temp['path'] + "/" + temp['file'])
    if(f.exists())
    {
        f.delete()
        printMsg ('info', '書込対象のファイルが既に存在しているため、削除しました。')
    }

    // Yaml形式で書込
    writeYaml(
        file: temp['path'] + "/" + temp['file'],
        data: data
    )
}
