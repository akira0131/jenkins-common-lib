#!groovy

// 変数をファイルに書き出すメソッド
def call(data, filename)
{
    File f = new File(fileName)
    f.write(data)
}
