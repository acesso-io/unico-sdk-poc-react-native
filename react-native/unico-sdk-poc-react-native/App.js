import React, { useEffect, useState } from 'react';
import {
  View,
  TouchableOpacity,
  Text,
  NativeModules,
  NativeEventEmitter,
  ScrollView,
} from 'react-native';
import { logger } from 'react-native-logs';

var log = logger.createLogger();

const App = () => {
  const [stateCamera, setStateCamera] = useState('Olá');

  const { UnicoCheckModule } = NativeModules;
  const eventEmitter = new NativeEventEmitter(UnicoCheckModule);

  useEffect(() => {
    eventEmitter.addListener('onSuccess', e => {
      setStateCamera(e.objResult);
      log.info("sucesso-react-native");
    });

    eventEmitter.addListener('onError', e => {
      setStateCamera(e.objResult);
    });

    return () => {
      eventEmitter.removeAllListeners('onSuccess');
      eventEmitter.removeAllListeners('onError');
    };
  }, []);

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: 'white',
        alignItems: 'center',
        justifyContent: 'space-evenly',
      }}>
      <View style={{ height: 200 }}>
        <ScrollView>
          <Text style={{ fontSize: 15, color: 'black' }}>{stateCamera}</Text>
        </ScrollView>
      </View>

      <TouchableOpacity
        activeOpacity={0.6}
        onPress={() => UnicoCheckModule.callDefaultCamera()}
        style={{
          backgroundColor: 'red',
          borderRadius: 5,
          width: 150,
          height: 50,
          alignItems: 'center',
          justifyContent: 'center',
        }}>
        <Text style={{ color: 'white', fontSize: 18 }}>Camera Default</Text>
      </TouchableOpacity>

      <TouchableOpacity
        activeOpacity={0.6}
        onPress={() => UnicoCheckModule.callSmartCamera()}
        style={{
          backgroundColor: 'green',
          borderRadius: 5,
          width: 150,
          height: 50,
          alignItems: 'center',
          justifyContent: 'center',
        }}>
        <Text style={{ color: 'white', fontSize: 18 }}>Camera Smart</Text>
      </TouchableOpacity>

      <TouchableOpacity
        activeOpacity={0.6}
        onPress={() => UnicoCheckModule.callLivenessCamera()}
        style={{
          backgroundColor: 'gray',
          borderRadius: 5,
          width: 150,
          height: 50,
          alignItems: 'center',
          justifyContent: 'center',
        }}>
        <Text style={{ color: 'white', fontSize: 18 }}>Camera Liveness</Text>
      </TouchableOpacity>

      <TouchableOpacity
        activeOpacity={0.6}
        onPress={() => UnicoCheckModule.callDocumentCamera()}
        style={{
          backgroundColor: 'blue',
          borderRadius: 5,
          width: 150,
          height: 50,
          alignItems: 'center',
          justifyContent: 'center',
        }}>
        <Text style={{ color: 'white', fontSize: 18 }}>Documents</Text>
      </TouchableOpacity>
    </View >
  );
};

export default App;
