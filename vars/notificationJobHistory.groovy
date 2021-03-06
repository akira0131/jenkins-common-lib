#!/usr/bin/groovy

// ジョブ履歴通知メソッド
def call(env, server, cmd)
{
    // ジョブステータスを追加
    manager.build.setDisplayName('#' + manager.build.number + " " + currentBuild.result)

    // 終了時間を追加
    end_time = new Date().dateTimeString
    manager.addShortText("undeployed: $end_time", "grey", "white", "0px", "white")
    manager.createSummary("gear2.gif").appendText("<h2>Undeployed: $end_time</h2>", false, false, false, "grey")
}
