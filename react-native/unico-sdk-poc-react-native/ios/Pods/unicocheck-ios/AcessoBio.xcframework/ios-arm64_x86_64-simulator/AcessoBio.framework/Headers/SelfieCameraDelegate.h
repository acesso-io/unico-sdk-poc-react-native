//
//  SelfieCameraDelegate.h
//  AcessoBio
//
//  Created by Matheus Domingos on 30/07/21.
//  Copyright © 2021 Matheus Domingos Acesso. All rights reserved.
//

#ifndef SelfieCameraDelegate_h
#define SelfieCameraDelegate_h
#import "ErrorPrepare.h"
#import "AcessoBioCameraOpenerDelegate.h"

@protocol SelfieCameraDelegate

- (void)onCameraReady: (id <AcessoBioCameraOpenerDelegate>)cameraOpener;
- (void)onCameraFailed:(ErrorPrepare *)message;

@end

#endif /* SelfieCameraDelegate_h */
