一. 配置服务服务端Server应用入口（设置配置服务端文件 RSA 非对称加解密）：
 1. 注解：EnableConfigServer
 2. 打开windows命令窗口，执行命令：
    > ./keytool.exe -genkeypair -alias config-server -keyalg RSA -dname "CN=zhenhua, OU=kingnet, O=kdc, L=shanghai, ST=shanghai, C=china" -keypass 222222 -keystore config-server.keystore -storepass 111111
    执行完后，正常情况下在会执行命令的目录下生成 server-rsa.jks 文件；
 3. 编辑 application.yaml 文件，注意填写 encrypt.keyStore 属性字段值；
 4. 启动 config-server-encrypt-rsa 模块服务，启动1个端口；
 5. 生成配置文件内容，打开windows命令窗口，执行命令：
     > curl.exe localhost:8265/encrypt -d foobar-rsa1
     AQAnkS1BpmB6Obu/Hg3qeXjDyvakHMIwFUVasKax0BIYHkc50ZRF7kcDLpG1o/iwhY8aAVyPGJXGnU7r
     1Su4NKAkQAHX6+yJq3hWd6N2GloQOIgMjjDc4cockGgxG+yoIFT1ggJ+kbzzMR6TDnPYZ3uDBLsngH0c
     9VkEaagpIcGW+2wCAu5KLq/Zh7m2oq65L4illCpPqOwbfvyiFwCpwU/0MH+QXC0+lPu/yXsxLILwRrh9
     7ZpWduQEDjMznMjSSpkbbeniilHjkUVWXsi4w36f194YN4abl3Lvv+pSzUMA72lGxIl7y50AbaeqyNM8
     ju8OKL0yDMmgmfTdxiVCK9DQIfaZHJeN9A5BEllzT5aOUATTsXtTTVSvL3+2RrcMIXw=

     >curl.exe localhost:8265/encrypt -d foobar-rsa2
     AQBF1BU5+/8EvHkJdoXFvYmYt8K5QvuyTbBl7rwg0G49QSV4IPDDarPFr/10zzcepV8UHpbVHQ9vMJAV
     6WCefmzMh0YWAPwRsLOgJIgfpbkPacRoVSvwqYEhHshNNQHNOjWT84BDBXiKXcnPeOhnMNUOiB7M05VB
     ZRVwdUuHBN/Zb/L9vxnQLTlwALS1TNfd3JUL7S61oz4JBf/c5FoQUPx/JawUz/uEwi337GCEkFmKacC8
     fF+cbjLOzsdtHkxrHZtz8QesDCwanwpZl8KbLTzeiU03uAj60qYBaoCYm+A19z+07SXHL0KKhoWp5TcA
     BDv5HY5Bv1astZVp7r+YFAwh/xYnHBYeUwBvmbjTJMYCJEuFNuWr35RhJWJSrAuI1eE=
     将这两个值进行保存到配置文件，也就是我们的Git仓库中的配置文件；
 6. 在浏览器输入地址 http://localhost:8265/foobar-default.yaml 正常情况下会输出配置文件的内容（内容为：profile: profile-default）；
 7. 在浏览器输入地址 http://localhost:8265/foobar-dev.yaml 正常情况下会输出配置文件的内容（内容为：profile: profile-dev）；
 8. 在浏览器输入地址 http://localhost:8265/foobar-rsa1.yaml 正常情况下会输出配置文件的内容（内容为：profile: foobar-rsa1）；
 9. 在浏览器输入地址 http://localhost:8265/foobar-rsa2.yaml 正常情况下会输出配置文件的内容（内容为：profile: foobar-rsa2）；
 10. 在浏览器输入地址 http://localhost:8265/foobar-rsa2.properties 正常情况下会输出配置文件的内容（内容为：profile: foobar-rsa2）；
     * 总结一：一切都正常打印，说明 SpringCloud 的解密已经能正确的完成了，但是注意加密内容保存到 properties 文件的时候，要将回车换行符去掉保存，不然将获取不到正确值；
 11. 修改 application.yaml 文件，将 encrypt.keyStore 属性值随便改下，改成比如 encrypt.secret: aaaaaaaaaaa
 12. 停止并重启 config-server-encrypt-rsa 模块服务，启动1个端口；
 13. 在浏览器输入地址 http://localhost:8265/foobar-default.yaml 正常情况下会输出配置文件的内容（内容为：profile: profile-default）；
 14. 在浏览器输入地址 http://localhost:8265/foobar-dev.yaml 正常情况下会输出配置文件的内容（内容为：profile: profile-dev）；
 15. 在浏览器输入地址 http://localhost:8265/foobar-rsa1.yaml 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
 16. 在浏览器输入地址 http://localhost:8265/foobar-rsa2.yaml 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
 17. 在浏览器输入地址 http://localhost:8265/foobar-rsa2.properties 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
     * 总结二：由此可见 encrypt.keyStore 经过赋值生成配置文件内容后，就不能轻易改变，一旦改变的话，那么原本正常的内容值将获取不到了；