//
//  IAcessoBioBuilder.h
//  AcessoBio
//
//  Created by Matheus Domingos on 29/07/21.
//  Copyright © 2021 Matheus Domingos Acesso. All rights reserved.
//

#ifndef iAcessoBioBuilder_h
#define iAcessoBioBuilder_h
#import "AcessoBioCameraDelegate.h"
#import "AcessoBioThemeDelegate.h"

@protocol iAcessoBioBuilder

- (id<iAcessoBioBuilder>) setTheme: (id<AcessoBioThemeDelegate>)theme;
- (id<iAcessoBioBuilder>) setAutoCapture: (BOOL)isEnabled;
- (id<iAcessoBioBuilder>) setSmartFrame: (BOOL)isEnabled;
- (id<iAcessoBioBuilder>) setTimeoutSession: (double)timeoutInSeconds;
- (id<AcessoBioCameraDelegate>) build;

@end
#endif /* IAcessoBioBuilder_h */
