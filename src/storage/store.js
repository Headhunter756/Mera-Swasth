import { configureStore } from "@reduxjs/toolkit";
import userReducer from './user'
import tokenReducer from './token'

const store = configureStore({
    reducer: {
        user: userReducer,
        token:tokenReducer
    }
})

export default store