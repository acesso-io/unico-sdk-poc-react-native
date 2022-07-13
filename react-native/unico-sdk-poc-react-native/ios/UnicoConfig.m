#import "UnicoConfig.h"
#import <AcessoBio/AcessoBio-Swift.h>

///Esta classe é um exemplo de como implementar o AcessoBioConfigDataSource, entretanto é preferível que esses dados venham de algum Remote Config ou Backend.

@implementation UnicoConfig : NSObject

- (NSString * _Nonnull)getBundleIdentifier {
    return @"<YOUR_MOBILE_BUNDLE_IDENTIFIER>"
}

- (NSString * _Nonnull)getHostInfo {
    return @"<YOUR_HOST_INFO>"
}

- (NSString * _Nonnull)getHostKey {
    return @"<YOUR_HOST_KEY>"
}

- (NSString * _Nonnull)getMobileSdkAppId {
    return @"<YOUR_MOBILE_SDK_APP_ID>"
}

- (NSString * _Nonnull)getProjectId {
    return @"<YOUR_PROJECT_ID>"
}

- (NSString * _Nonnull)getProjectNumber {
    return @"<YOUR_PROJECT_NUMBER>"
}

@end