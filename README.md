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

Edit the following properties:

```sh
openid.return_to={your OAuth callback url}
openid.realm={your website url}
```

##### Usage

1.Instantial OpenIDManager.

```java
OpenIDManager openIDManager = new OpenIDManager(); //defaults to 'openidSetting.properties'
```
...or
```java
OpenIDManager openIDManager = new OpenIDManager( "path/to/your/setting" );
```

2.Get the NCU portal URL and display to user by a WebView(Android apps) or status code 302 moved temporarily(normal web server).

```java
String url = openIDManager.getURLString();
```

3.You **must** validate the request redirected from NCU portal after user logining for security issues.

```java
boolean isValid = openIDManager.isValid( request );
```

4.Get portalId.

```java
String portalId = openIDManager.getIdentity( request );
```
