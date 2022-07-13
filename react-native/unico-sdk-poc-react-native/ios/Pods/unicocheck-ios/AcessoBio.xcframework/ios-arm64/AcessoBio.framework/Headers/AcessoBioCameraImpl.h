//
//  AcessoBioCamera.h
//  AcessoBio
//
//  Created by Matheus Domingos on 30/07/21.
//  Copyright © 2021 Matheus Domingos Acesso. All rights reserved.
//

@import Foundation;
#import "AcessoBioCameraDelegate.h"
@class UnicoCheck;
@class UnicoEnumsErrors;
@class UnicoErrors;
NS_ASSUME_NONNULL_BEGIN

@interface AcessoBioCameraImpl : NSObject <AcessoBioCameraDelegate> {
    UnicoCheck * core;
}

- (UnicoCheck *)getCore;

- (id)initWithUnicoCheck:(UnicoCheck *)unicoCheck;

@end

NS_ASSUME_NONNULL_END
