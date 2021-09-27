# welfare

双色球历史开奖记录统计

从500彩票网https://datachart.500.com/ 得来的历次开奖信息，原本只是做个统计，正好准备从头好好学习Java，就用它构建项目。

本人技术很水，多数东西都局限在知道，所以更新会很慢，反正也没人看，自己开心就好。

version 1.0:只有后台方法，包含记录和查询最新记录，数据保存在text和excel中。

version 2.0:选择了Maven WebApp,老的不行的web应用，现在面试都没人会问。springmvc+jsp，能有个页面看结果了，也就仅此而已。

tip：web.xml配置servlet的时候,启动之后一直报404，直接就找不到localhost，翻了一堆的帖子，最后找到一个相似的，要设置context
path，idea配置运行环境时，项目名称不是由name或者url决定的，引入war包部分下面会自动将包名设置给Application context，反反复复搞到头秃，当时都准备放弃了，反正以后用不到的东西，所幸下班前搞出来了。

version 3.0:这一版准备将架构改成ssm,数据持久化到MySQL中，算是一个小型项目了，日志也搞完整点，暂时就这么多，搞完再说。
