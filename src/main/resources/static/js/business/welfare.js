var WEB_ROOT;
$(function () {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    WEB_ROOT = pathName.substr(0, index + 1);

    queryNewestRecord();
});

function insertHistoryRecord() {
    let qryUrl = WEB_ROOT + "/welfare/insertHistoryRecord";
    $.ajax({
        url: qryUrl,
        success: function (data) {
            alert("更新记录成功,更新记录数：" + data);
        },
        error: function (data) {
            alert("更新记录异常");
        }
    });
}

function queryNewestRecord() {
    let qryUrl = WEB_ROOT + "/welfare/queryNewestRecord";
    $.ajax({
        url: qryUrl,
        success: function (data) {
            alert("查询成功!");
            $("#lotteryRecord").find("span[name='newestRecord']").text(data);
        },
        error: function (data) {
            alert("查询异常");
        }
    });
}