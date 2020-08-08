##  MyBatis的入门

### MyBatis的环境搭建
1. 创建maven工程并导入坐标
2. 创建实体类和dao接口
3. 创建MyBatis的主配置文件：SqlMapConfig.xml

4. 创建映射配置文件：IUserDao.xml    


### 环境搭建的注意事项
1. 在MyBatis中它把持久层的操作接口名称和映射文件也叫做：Mapper  
所以：IUserDao和IUserMapper是一样的
2. 在idea中创建目录的时候，它和包是不一样的     
包创建时：com.beishan.dao是三级结构
目录创建时：com.beishan.dao是一级目录
3. MyBatis的映射配置文件位置必须和dao接口的包结构相同
4. 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
5. 映射配置文件的操作配置(selcet)，id 属性的取值必须是dao接口的方法名       

    注意：当我们遵从了3 4 5点之后，我们在开发中就无需写dao的实现类


### MyBatis入门案例
1. 读取配置文件
2. 创建SqlSessionFactory工厂
3. 创建SqlSession
4. 创建Dao接口的代理对象
5. 执行dao中的方法
6. 释放资源     

    注意事项：  
    不要忘记在映射配置中告知mybatis要封装到哪个实体类中     
    配置的方式：指定实体类的全限定类名

    MyBatis基于注解的入门案例：     
    把IUserDao.xml移除，在dao接口的方法中使用@Select注解，并指定SQL语句     
    同时需要在SqlMaoConfig.xml中的mapper配置时，使用class属性指定dao接口的全限定类名


### 明确：
我们在实际开发中，都是越简单越好，所以都是采用不写dao实现类的方式   
不管是使用xml还是注解配置   
但是MyBatis是支持写dao实现类的


### 自定义MyBatis的分析

####    MyBatis在使用代理dao的方式实现增删改查时做什么事呢？    
只有两件事：    
1. 创建代理对象
2. 在代理对象中调用selectList

####    自定义MyBatis能通过入门案例看到类
- class Resource
- class SqlSessionFactoryBuilder
- interface SqlSessionFactory
- interface SqlSession

### OGNL表达式
OGNL(Object Graphic Navigation Language) 对象图导航语言   

它是通过对象的取值方法来获取数据    
比如：我们获取用户的名称
- 类中额写法：  user.getUserName();
- OGNL表达式写法：  user.username;  

mybatis中为什么能直接写username，而不用user.呢？    
因为在parmaeterType中已经提供了属性所属的类，所以此时不需要写对象名


### 连接池
我们在实际开发中都会使用连接池  
因为它可以减少我们获取连接所消耗的时间

####    MyBatis中的连接池

mybatis连接池提供了3种方式的配置： 
配置的位置：     
    主配置文件SqlMapConfig.xml中的dataSource标签，type属性就是表示采用何种连接池方式

type属性的取值：
- POOLED 采用传统的javax.sql.DataSource规范中的连接池，mybatis中有针对规范的实现
- UNPOOLED 采用传统的获取连接的方式，虽然也实现javax.sql.DataSource接口，但是并没有使用池的思想
- JNDI 采用服务器提供的JNDI技术实现，来获取DataSource对象，不同的服务器所能拿到的DataSource是不一样的   
注意：如果不是web或者maven的war工程，是不能使用的   
我们课程中使用的是tomcat服务器，采用连接池就是dbcp连接池


### MyBatis中的事务
- 什么是事务
- 事务的四大特性ACID
- 不考虑隔离性产生的3个问题
- 解决方法：四种隔离级别

它是通过sqlSession对象的commit方法和rollback方法实现事务的提交和回滚


### MyBatis中的多表关系
表之间的关系有几种：
- 一对多
- 多对一
- 一对一
- 多对多

MyBatis中的多表查询：   
示例：用户和账户    
一个用户可以有多个账户  
一个账户只能属于一个用户（多个账户也可以属于同一个用户）  

步骤：
1. 建立两张表：用户表和账户表   
    让用户表和账户表之间具备一对多的关系：需要使用外键在账户表中添加

2. 建立两个实体类：用户类和账户类   
    让用户和账户的实体类能体现出一对多的关系

3. 建立两个配置文件     
    - 用户的配置文件  
    - 账户的配置文件

4. 实现配置：   
    当我们查询用户时，可以同时得到用户下所包含的账户信息    
    当我们查询账户时，可以同时得到账户的所属用户信息