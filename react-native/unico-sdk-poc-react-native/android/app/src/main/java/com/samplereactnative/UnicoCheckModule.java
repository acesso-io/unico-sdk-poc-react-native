package com.samplereactnative;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.iAcessoBioDocument;
import com.acesso.acessobio_android.iAcessoBioSelfie;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCamera;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener;
import com.acesso.acessobio_android.onboarding.camera.document.DocumentCameraListener;
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener;
import com.acesso.acessobio_android.onboarding.types.DocumentType;
import com.acesso.acessobio_android.services.dto.ErrorBio;
import com.acesso.acessobio_android.services.dto.ResultCamera;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.logging.Logger;

import com.samplereactnative.UnicoConfigDefault;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class UnicoCheckModule extends ReactContextBaseJavaModule implements AcessoBioListener, iAcessoBioSelfie, iAcessoBioDocument {

    private static ReactApplicationContext reactContext;
    private static Logger logger = Logger.getLogger(UnicoCheckModule.class.getName());
    private static UnicoConfigDefault unicoConfigDefault = new UnicoConfigDefault();
    private static UnicoConfigLiveness unicoConfigLiveness  = new UnicoConfigLiveness();

    protected static final int REQUEST_CAMERA_PERMISSION = 1;

    public enum CameraMode {
        SMART,
        DEFAULT,
        LIVENESS,
        DOCUMENT
    }

    private IAcessoBioBuilder acessoBioBuilder;
    private UnicoCheckCamera unicoCheckCamera;

    UnicoCheckModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @Override
    public String getName() {
        return "UnicoCheckModule";
    }

    @ReactMethod
    public void addListener(String eventName) {
        // Keep: Required for RN built in Event Emitter Calls.
    }

    @ReactMethod
    public void removeListeners(Integer count) {
        // Keep: Required for RN built in Event Emitter Calls.
    }

    @ReactMethod
    public void show(String message, Callback errorCallback, Callback successCallback) {
        successCallback.invoke(message);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    private void callSmartCamera() {
        this.openCamera(CameraMode.SMART);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    private void callDefaultCamera() {
        this.openCamera(CameraMode.DEFAULT);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    private void callLivenessCamera() {
        this.openCamera(CameraMode.LIVENESS);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    private void callDocumentCamera() {
        this.openCamera(CameraMode.DOCUMENT);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    private void build(boolean hasSmart) {
        acessoBioBuilder = new AcessoBio(getCurrentActivity(), UnicoCheckModule.this);
        unicoCheckCamera = acessoBioBuilder.setSmartFrame(hasSmart).setAutoCapture(hasSmart).build();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openCamera(CameraMode mode) {
        if (hasPermission()) {

            getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mode == CameraMode.SMART) {
                        build(true);
                        unicoCheckCamera.prepareSelfieCamera(unicoConfigDefault, new SelfieCameraListener() {

                        /*
                           Para gerar o arquivo JSON é necessário criar uma API key. Siga os passos abaixo:
                            - Acesse o portal do cliente da unico com suas credenciais;
                            - Navegue em Configurações > Integração > API Key;
                            - Crie ou edite uma nova API Key;
                            - Marque o campo "Utiliza liveness ativo" como SIM caso queira habilitar a câmera Prova de Vidas ou NÃO caso queira utilizar a Câmera Normal ou Inteligente;
                            - Marque o campo "Utiliza autenticação segura na SDK" como SIM;
                            - Expanda a seção SDK iOS, adicione o nome de sua aplicação iOS e o Bundle ID;
                            - Salve as alterações.
                            - Neste momento, retornará para a página de API Key e ao lado da API Key desejada, pressione o botão de download do Bundle;
                            - Selecione a opção iOS;
                            - Clique em "Gerar";
                            Atenção: Uma nova aba será aberta contendo informações do projeto em formato JSON.
                            Caso a nova aba não abra, por favor, verifique se o seu navegador está bloqueando os popups.
                            - Salve o conteúdo desta nova aba em um novo arquivo JSON;
                            - Adicione o arquivo salvo na pasta assets do seu projeto android"
                        */

                            @Override
                            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                                cameraOpener.open(UnicoCheckModule.this);
                            }

                            @Override
                            public void onCameraFailed(String message) {
                                Toast.makeText(getCurrentActivity(), message, Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (mode == CameraMode.DEFAULT) {
                        build(false);
                        unicoCheckCamera.prepareSelfieCamera(unicoConfigDefault, new SelfieCameraListener() {
                            @Override
                            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                                cameraOpener.open(UnicoCheckModule.this);
                            }

                            @Override
                            public void onCameraFailed(String message) {
                                Toast.makeText(getCurrentActivity(), message, Toast.LENGTH_LONG).show();
                            }
                        });
                    } else if (mode == CameraMode.LIVENESS) {
                        build(false);
                        unicoCheckCamera.prepareSelfieCamera(unicoConfigLiveness, new SelfieCameraListener() {
                            @Override
                            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                                cameraOpener.open(UnicoCheckModule.this);
                            }

                            @Override
                            public void onCameraFailed(String message) {
                                Toast.makeText(getCurrentActivity(), message, Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        build(false);
                        unicoCheckCamera.prepareDocumentCamera(unicoConfigDefault, new DocumentCameraListener() {
                            @Override
                            public void onCameraReady(UnicoCheckCameraOpener.Document cameraOpener) {
                                cameraOpener.open(DocumentType.RG_FRENTE, UnicoCheckModule.this);
                            }

                            @Override
                            public void onCameraFailed(String message) {
                                Toast.makeText(getCurrentActivity(), message, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            });

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean hasPermission() {
        if (ContextCompat.checkSelfPermission(getCurrentActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(getCurrentActivity(), new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CAMERA_PERMISSION);

            return false;
        }
        return true;
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           String processStatus) {

        WritableMap params = Arguments.createMap();
        params.putString("objResult", processStatus);
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);

    }

    @Override
    public void onErrorAcessoBio(ErrorBio errorBio) {
        sendEvent(reactContext, "onError", errorBio.getDescription());
    }

    @Override
    public void onUserClosedCameraManually() {
        sendEvent(reactContext, "onError", "Usuário fechou a câmera manualmente");
    }

    @Override
    public void onSystemClosedCameraTimeoutSession() {
        sendEvent(reactContext, "onError", "Timeout de sessão excedido");
    }

    @Override
    public void onSystemChangedTypeCameraTimeoutFaceInference() {
        sendEvent(reactContext, "onError", "Timeout de inferencia inteligente de face excedido.");
    }

    @Override
    public void onSuccessSelfie(ResultCamera resultCamera) {
        // sendEvent(reactContext, "onSuccess", resultCamera.getBase64()); //base64
        // sendEvent(reactContext, "onSuccess", resultCamera.getEncrypted()); //JWT
        sendEvent(reactContext, "onSuccess", "Selfie capturada com sucesso");
    }

    @Override
    public void onErrorSelfie(ErrorBio errorBio) {
        sendEvent(reactContext, "onError", errorBio.getDescription());
    }

    @Override
    public void onSuccessDocument(ResultCamera result) {
        // sendEvent(reactContext, "onSuccess", result.getBase64()); //base64
        sendEvent(reactContext, "onSuccess", "Documento capturado com sucesso");
    }

    @Override
    public void onErrorDocument(String s) {
        sendEvent(reactContext, "onError", s);
    }


}

