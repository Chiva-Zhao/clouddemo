 ### 一. 简单 Quartz-Cluster 微服务，支持集群分布式，并支持动态修改 Quartz 任务的 cronExpression 执行时间：

 1. 添加 Quartz 相关配置文件；
 2. 启动 simple-quartz-cluster 模块服务，启动1个端口（8395）；
 3. 然后查看日志， ScheduleTask 类的 sayHello 方法被有规律的调用，并打印日志出来；

 4. 启动 simple-quartz-cluster 模块服务，再启动2个端口（8396. 8397）；
 5. 然后看到 3 台服务器只有 1 台服务器调用了 sayHello 方法，因此 Quartz 的集群分布式也算是部署成功了；
 
 ### 二. 简单 Quartz-Cluster 微服务，支持集群分布式，并支持动态修改 Quartz 任务的 cronExpression 执行时间（动态修改定时任务的 cronExpression 时间表达式）：
 
  1. 添加 Quartz 相关配置文件；
  2. 启动 simple-quartz-cluster 模块服务，启动3个端口（8395. 8396. 8397）；
  3. 然后看到 3 台服务器只有 1 台服务器调用了 sayHello 方法打印了日志，因此 Quartz 的集群分布式也算是部署成功了；
  4. 然后新起网页输入 http://localhost:8395/modify/5 修改定时任务的触发时间；
  5. 再等一会儿就看到 3 台服务器只有 1 台服务器每隔 5 秒调用一次 sayHello 方法，因此修改定时任务的克隆表达式也算是成功了；
  
  ### 三. 简单 Quartz-Cluster 微服务，支持集群分布式，并支持动态修改 Quartz 任务的 cronExpression 执行时间（动态删除其中 1 台活跃 Quartz 服务器，然后剩下的其中 1 台自动接替）：
  
   1. 添加 Quartz 相关配置文件；
   2. 启动 simple-quartz-cluster 模块服务，启动3个端口（8395. 8396. 8397）；
   3. 然后看到 3 台服务器只有 1 台服务器调用了 sayHello 方法打印了日志，因此 Quartz 的集群分布式也算是部署成功了；
   4. 然后关闭 1 台活跃 Quartz 服务器；
   5. 再等一会儿就看到 2 台服务器中的 1 台服务器每隔一定的时间调用一次 sayHello 方法；