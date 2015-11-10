
#import <Cordova/CDV.h>

@interface CDVAnalytics : CDVPlugin

- (void)config:(CDVInvokedUrlCommand *)command;
- (void)startPage:(CDVInvokedUrlCommand *)command;
- (void)endPage:(CDVInvokedUrlCommand *)command;
- (void)setDebug:(CDVInvokedUrlCommand *)command;
- (void)logEvent:(CDVInvokedUrlCommand *)command;

@end
