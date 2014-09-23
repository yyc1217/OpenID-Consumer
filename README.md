OpendID-Consumer
=========

Simple OpendID Consumer for NCU

  - Simple
  - Only for NCU
  - Easy to Use

Version
----

0.1-beta


How to use
--------------


##### setting files

* src/main/resources/openidSetting.properties
* src/main/resources/openidCheck.properties

you only have to edit openidSetting.properties with following properties

```sh
openid.return_to=http://140.115.3.97/manage/auth
openid.realm=http://140.115.3.97
```

you have to change them to your own datas





##### Use it in programming

first you have to create an instance of OpenIDManager
```sh
OpenIDManager openIDManager = new OpenIDManager();
```

second you have to get the URL used for being clicked or redirected by user
```sh
String url=openIDManager.getURLString();
```

after you revice the request from user which is redirected from portal
you can simply put the request parameters' map into the function
```sh
Map map=request.getParameterMap();
boolean isCorrect=openIDManager.checkAuthentication(map);
```

if respone is true, you can get user's data
```sh
Map map=request.getParameterMap();
String id=openIDManager.getStudentID(map);
```



License
----

Apache2.0
