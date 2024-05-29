import { combineReducers } from 'redux';
import { persistReducer } from 'redux-persist';
import storageSession from 'redux-persist/lib/storage/session';
import user from './user';
import dayPlans from './dayPlans'; // dayPlans 리듀서를 임포트합니다.

const persistConfig = {
    key: 'root',
    storage: storageSession,
    whitelist: ['user', 'dayPlans'], // dayPlans를 whitelist에 추가합니다.
};

const rootReducer = combineReducers({
    user,
    dayPlans, // dayPlans 리듀서를 rootReducer에 추가합니다.
});

export default persistReducer(persistConfig, rootReducer);
