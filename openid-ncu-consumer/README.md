OpenID-NCU-Consumer
=========

Simple OpenID Consumer for NCU

  - Simple
  - Only for NCU
  - Easy to Use

How to use
--------------

##### setting

* src/main/resources/openidSetting.properties ( customizable )

you only have to edit the following properties

```sh
openid.return_to=http://140.115.3.97/manage/auth
openid.realm=http://140.115.3.97
```

##### usage

1.you have to create an instance of OpenIDManager

```sh
OpenIDManager openIDManager = new OpenIDManager(); //default is 'openidSetting.properties'
...or
OpenIDManager openIDManager = new OpenIDManager( "path/to/your/setting" );
```

2.you can get the URL for being clicked or redirected by user

```sh
String url = openIDManager.getURLString();
```

3.after revice the request which is redirected from portal you **must** validate it or it will be unsecured

```sh
boolean isValid = openIDManager.isValid( request );
```

4.you can get the user data next if respone above is valid

```sh
String id = openIDManager.getIdentity( request );
```
