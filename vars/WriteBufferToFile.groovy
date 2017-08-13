#!groovy

// 変数をファイルに書き出すメソッド
def call(data, fileName)
{
    // 書込対象のファイル
    temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_' + fileName + '.log']

    printMsg('info', 'コマンド結果をファイルに出力します。')
    printMsg('info', '標準出力結果出力先: ' + temp['path'] + "/" + temp['file'])

    // ファイルが既に存在していた場合は削除する
    new File(temp['path'] + "/" + temp['file']).withWriterAppend { writer ->
    writer < data
}
}
