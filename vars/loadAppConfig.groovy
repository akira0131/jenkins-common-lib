#!groovy

// 設定ファイルロードメソッド
def call()
{
    // 読込対象のファイル
    def file = ['path':'/opt/app/conf', 'name':'env.yml']

    printMsg('info', 'ファイルからプロパティをロードします。')
    printMsg('info', '入力元: ' + file['path'] + "/" + file['name'])

    // Yaml形式を読込
    def config = readYaml(
        file: file['path'] + "/" + file['name']
    )

    return config
}
