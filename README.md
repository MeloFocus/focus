#focus
权限管理系统，目的是学习springboot和shiro，以及后续学习springcloud</br>
[访问地址]  http://47.98.153.30:8080/  账号：melo 密码：12345678

一。采用框架
===========
前端：jquery、bootstrap
后端：springboot、shiro、mybatis、redisson、swagger
数据库：mysql
jdk：1.8

二。编程记录
==========
[我的博客记录了 后端基本搭建过程] https://blog.csdn.net/u014203449/article/details/79330811</br>
[访问地址] http://47.98.153.30:8080/ 账号：melo 密码：12345678

三。部署过程
==========
1.建立名为focus的数据库，执行项目中的 sql文件</br>
2.更改application 中数据库和redis 的用户名密码</br>
3.如果不用redis的朋友，可以将配置文件、config类、安全管理器、和resouceservice中redis业务注释掉即可</br>
4.访问 localhost:8080  账号：melo 密码：12345678</br>

![](https://github.com/MeloFocus/focus/raw/master/img/user.png)</br>
![](https://github.com/MeloFocus/focus/raw/master/img/role.png)</br>
![](https://github.com/MeloFocus/focus/raw/master/img/resource.png)</br>
![](https://github.com/MeloFocus/focus/raw/master/img/auth.png)</br>
![](https://github.com/MeloFocus/focus/raw/master/img/authority.png)</br>
