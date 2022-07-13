//
//  DocumentCameraDelegate.h
//  AcessoBio
//
//  Created by Matheus Domingos on 02/08/21.
//  Copyright © 2021 Matheus Domingos Acesso. All rights reserved.
//

#ifndef DocumentCameraDelegate_h
#define DocumentCameraDelegate_h

@protocol DocumentCameraDelegate

- (void)onCameraReadyDocument: (id <AcessoBioCameraOpenerDelegate>)cameraOpener;
- (void)onCameraFailedDocument:(ErrorPrepare *)message;


@end
#endif /* DocumentCameraDelegate_h */
