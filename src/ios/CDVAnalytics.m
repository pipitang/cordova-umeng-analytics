#import "CDVAnalytics.h"
#import <UMMobClick/MobClick.h>
#import <UMMobClick/MobClickSocialAnalytics.h>
              
@implementation CDVAnalytics


#pragma API
- (void)config:(CDVInvokedUrlCommand *)command
{
    NSDictionary *params = [command.arguments objectAtIndex:0];
    if (!params)
    {
        [self failWithCallbackID:command.callbackId withMessage:@"参数格式错误"];
        return ;
    }

    UMConfigInstance.appKey = [params objectForKey:@"appkey"];
    UMConfigInstance.channelId =  @"App Store";
    [MobClick startWithConfigure:UMConfigInstance];

    [self successWithCallbackID:command.callbackId];
}


- (void)startPage:(CDVInvokedUrlCommand *)command
{
    NSString* page = [command.arguments objectAtIndex:0];
    [MobClick beginLogPageView:page];
    [self successWithCallbackID:command.callbackId];
}


- (void)endPage:(CDVInvokedUrlCommand *)command
{
    NSString* page = [command.arguments objectAtIndex:0];
    [MobClick endLogPageView:page];
    [self successWithCallbackID:command.callbackId];
}

- (void)setDebug:(CDVInvokedUrlCommand *)command
{
    BOOL debug = [command.arguments objectAtIndex:0];
    [MobClick setLogEnabled:debug];
    [self successWithCallbackID:command.callbackId];
}

- (void)logEvent:(CDVInvokedUrlCommand *)command
{
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString* eventId = [params objectForKey:@"eventId"];
    NSDictionary* attributes = [params objectForKey:@"attributes"];
    int number = (int)[params objectForKey:@"num"];
    [MobClick event: eventId attributes: attributes counter: number];
    [self successWithCallbackID:command.callbackId];
}


#pragma util
- (void)successWithCallbackID:(NSString *)callbackID
{
    [self successWithCallbackID:callbackID withMessage:@"OK"];
}

- (void)successWithCallbackID:(NSString *)callbackID withMessage:(NSString *)message
{
    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:message];
    [self.commandDelegate sendPluginResult:commandResult callbackId:callbackID];
}

- (void)failWithCallbackID:(NSString *)callbackID withError:(NSError *)error
{
    [self failWithCallbackID:callbackID withMessage:[error localizedDescription]];
}

- (void)failWithCallbackID:(NSString *)callbackID withMessage:(NSString *)message
{
    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:message];
    [self.commandDelegate sendPluginResult:commandResult callbackId:callbackID];
}

@end
