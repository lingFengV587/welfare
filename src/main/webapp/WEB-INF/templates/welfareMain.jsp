<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>体彩</title>

    <script type="text/javascript" src="../static/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="../static/js-busi/welfare.js"></script>
    <link rel="stylesheet" href="../static/css/model.css">
</head>
<body>
    <h2>最新一期中奖号码：</h2>
    <div id="lotteryRecord">
        <span name="newestRecord"></span>
    </div>
    <div>
        <button type="button" class="btn" onclick="insertHistoryRecord()">更新</button>
        <button type="button" class="btn" onclick="queryNewestRecord()">查询</button>
    </div>
</body>
</html>
