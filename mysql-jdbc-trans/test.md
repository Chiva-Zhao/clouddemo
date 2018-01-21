### 一. 简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，使用 rollbackFor 属性，数据回滚）：
 1. 给 insertUser 方法添加注解：@Transactional(propagation = Propagation.REQUIRED, isolation= Isolation.DEFAULT, rollbackFor = Exception.class)；
 2. insertUser 抛出 RollbackExceptionExtendsRuntimeException 异常；
 3. 启动 mysql-jdbc-trans 模块服务，启动1个端口；
 4. 在浏览器输入地址 http://localhost:8315/simplejdbc/list 可以看到所有用户信息成功的被打印出来；
 5. 使用 REST Client 执行 "/simplejdbc/insertUser" 添加参数执行接口，结果数据没有入库，数据被回滚了；
### 二. 简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，使用 noRollbackFor 属性，数据回滚）：
 1. 给 replaceUser 方法添加注解：@Transactional(noRollbackFor = BusinessExtendsException.class)；
 2. replaceUser 抛出 RuntimeException 异常；
 3. 启动 mysql-jdbc-trans 模块服务，启动1个端口；
 4. 在浏览器输入地址 http://localhost:8315/simplejdbc/list 可以看到所有用户信息成功的被打印出来；
 5. 使用 REST Client 执行 "/simplejdbc/replaceUser" 添加参数执行接口，结果数据没有入库，数据被回滚了；
### 三. 简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，使用 noRollbackFor 属性，数据没有回滚）：
 1. 给 replaceUser 方法添加注解：@Transactional(noRollbackFor = BusinessExtendsException.class)；
 2. replaceUser 抛出 BusinessSubExtendsException 异常；
 3. 启动 mysql-jdbc-trans 模块服务，启动1个端口；
 4. 在浏览器输入地址 http://localhost:8315/simplejdbc/list 可以看到所有用户信息成功的被打印出来；
 5. 使用 REST Client 执行 "/simplejdbc/replaceUser" 添加参数执行接口，结果数据已经入库了，数据没有回滚了；
 注意：如果要使得 noRollbackFor 属性生效，注解中 @Transactional 必须得只有 noRollbackFor 属性，然后 noRollbackFor 的异常必须得是自己定义的异常，然后抛 RuntimeException 异常，这样我们才可以测出 noRollbackFor 回滚与不回滚的场景出来；
### 四. 简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，同时操作多个DAO文件入库，然后选择注解是否进行回滚数据）：
 这里就不做多的解释了，MovieServiceImpl 就是操作多个DAO文件入库，然后处理是否回滚数据的。
 注意：如果要使得 noRollbackFor 属性生效，注解中 @Transactional 必须得只有 noRollbackFor 属性，然后 noRollbackFor 的异常必须得是自己定义的异常，然后抛 RuntimeException 异常，这样我们才可以测出 noRollbackFor 回滚与不回滚的场景出来；
 rollbackFor 属性：抛出的异常是 rollbackFor 异常的子类时都会回滚数据；
 noRollbackFor 属性：抛出的异常是 noRollbackFor 异常的子类时不会回滚数据；抛出的异常不是 noRollbackFor 异常的子类时会回滚数据；