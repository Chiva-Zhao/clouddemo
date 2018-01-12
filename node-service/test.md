一. 简单异构系统之 nodejs 微服务：
 1. 编写 node-service.js 文件；
 2. 启动服务（windows 命令）；
 3. 输入 node.exe node-service.js 命令，正常情况下会打印 “开始监听本地端口: 8205”，说明启动成功了；
 注意：至于 node.exe 这个命令要下载什么安装包什么的，请大家移步寻找度娘，相信大家的聪明才智很快就可以搞定这个命令的最简单用法；
 4. 新起网页页签，输入 http://localhost:8205/ ，然后打印信息为：{"index":"欢迎来到简单异构系统之 nodejs 服务首页"}
 5. 新起网页页签，输入 http://localhost:8205/health.json ，然后打印信息为：{"status":"UP"}
 5. 新起网页页签，输入 http://localhost:8205/abc ，然后打印信息为：404
 * 总结：简单的 nodejs 微服务，处理客户端请求就是如此的简单，所以市场上也有好多服务端就是用nodejs玩的；