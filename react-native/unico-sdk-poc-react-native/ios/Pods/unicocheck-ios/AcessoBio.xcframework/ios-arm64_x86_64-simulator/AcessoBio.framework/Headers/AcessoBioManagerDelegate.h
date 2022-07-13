//
//  AcessoBioDelegate.h
//  AcessoBio
//
//  Created by Matheus Domingos on 29/07/21.
//  Copyright © 2021 Matheus Domingos Acesso. All rights reserved.
//

#ifndef AcessoBioManagerDelegate_h
#define AcessoBioManagerDelegate_h
#import "ErrorBio.h"

@protocol AcessoBioManagerDelegate

- (void)onErrorAcessoBioManager: (ErrorBio *)error;
- (void)onUserClosedCameraManually;
- (void)onSystemClosedCameraTimeoutSession;
- (void)onSystemChangedTypeCameraTimeoutFaceInference;

@end
#endif /* AcessoBioDelegate_h */
