#!groovy

import java.io.File.write;
import java.io.BufferedWriter;

// 変数をファイルに書き出すメソッド
def call(data, fileName)
{
    printMsg('info', 'コマンド結果をファイルに出力します。')

    temp = ['path':'/opt/var/jenkins/temp', 'file': 'result_' + fileName + '.log']
    printMsg('info', '標準出力結果出力先: ' + temp['path'] + "/" + temp['file'])
    File f = new File(temp['path'] + "/" + temp['file'])
    BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
    bw.write(data)
}
