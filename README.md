# cordova-umeng-analytics

Cordova友盟统计插件

# 功能

目前只有基本的统计分析功能，具体功能参见官方文档。 自动更新功能，请参见[友盟更新插件](http://github.com/pipitang/cordova-umeng-update)

# 安装

1. 运行 ```cordova plugin add https://github.com/pipitang/cordova-umeng-analytics``` 

2. cordova各种衍生命令行都应该支持，例如phonegap或者ionic。

# 使用方法

## 原则


## API
### 统计分析API

#### 配置API

请务必在cordova ready中调用配置接口

```Javascript
Umeng.Analytics.config({
    appkey: 'your_app_key', 
    channel: 'your_channel'
}, function () {
    alert("友盟API初始化成功");
}, function (reason) {
    alert("友盟API初始化失败");
});
```


#### 开始记录页面停留 

页面统计集成正确，才能够获取正确的页面访问路径、访问深度（PV）的数据。对于Cordova程序来说，这里的页面一般来说是应用程序的某个TOKEN。与一般的页面浏览不同，移动APP需要正确处理被暂停和恢复状态，在Cordova中如何实现，请问度娘或者谷哥。

```Javascript
Umeng.Analytics.startPage('xxxPage', 
function () {
    alert("Success");
}, function (reason) {
    alert("Failed: " + reason);
});
```

#### 结束记录页面停留
此处传入的参数务必与startPage成对出现，参见

```Javascript
Umeng.Analytics.endPage('xxxPage', function () {
   alert("Success");
}, function (reason) {
   alert("Failed: " + reason);
});
```


#### 自定义事件 [参见](http://dev.umeng.com/analytics/functions/numekv)

```Javascript
Umeng.Analytics.logEvent({
    eventId: 'pay',
    attributes: {book:'Swfit Fundamentals'},
    num: 110
}, function () {
    alert("Success");
}, function (reason) {
alert("Failed: " + reason);
});
```

其中，eventId为必传参数，其它参数不传，即为统计该行为发生次数。如果同时传入```attributes```，则为统计各行为各属性发生的次数。如果eventId, attributes和num同时传入，则为统计"计算事件"，请查看官方文档。

#### 打开或关闭调试


```Javascript
Umeng.Analytics.enableDebug(true, function () {
   alert("Success");
}, function (reason) {
   alert("Failed: " + reason);
});
```


# FAQ

Q: Android如何调试？

A: 如果怀疑插件有BUG，请使用tag名称为cordova-umeng-analytics。

Q: Windows 版本？

A: 这个很抱歉，有个哥们买了Lumia之后一直在抱怨应用太少，很不幸，你也有这个需求 ：） 欢迎 pull request.


# TODO

1. 动态拉取依赖库
2. 页面统计自动处理应用暂停和启动

# 许可证

[MIT LICENSE](http://opensource.org/licenses/MIT)
