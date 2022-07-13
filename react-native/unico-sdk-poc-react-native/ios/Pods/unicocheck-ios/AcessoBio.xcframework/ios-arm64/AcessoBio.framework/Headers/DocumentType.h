//
//  DocumentType.h
//  AcessoBio
//
//  Created by Matheus Domingos on 04/08/21.
//  Copyright © 2021 Matheus Domingos Acesso. All rights reserved.
//

@import Foundation;

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger, DocumentEnums) {
    DocumentNone,
    DocumentCNH,
    DocumentCNHFrente,
    DocumentCNHVerso,
    DocumentRG,
    DocumentRGFrente,
    DocumentRGVerso,
    DocumentCPF
};

@interface DocumentType : NSObject

@property (assign, nonatomic) DocumentEnums *documentType;

@end

NS_ASSUME_NONNULL_END
