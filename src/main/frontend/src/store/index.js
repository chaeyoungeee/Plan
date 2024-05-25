// reducers/index.js
import { combineReducers } from "redux";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import user from "./user";
import storageSession from 'redux-persist/lib/storage/session'; 

const persistConfig = {
  key: "root",
  storage: storageSession,
  whitelist: ["user"]
};

const rootReducer = combineReducers({
  user
});

export default persistReducer(persistConfig, rootReducer);
