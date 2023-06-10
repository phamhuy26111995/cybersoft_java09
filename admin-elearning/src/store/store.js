import { configureStore } from "@reduxjs/toolkit";
import categorySlice from "../redux-slices/categorySlice";
import  globalSlice  from "../redux-slices/globalSlice";
import userSlice from "../redux-slices/userSlice";
import courseSlice from "../redux-slices/courseSlice";

export const store = configureStore({
    reducer : {
        category : categorySlice,
        global : globalSlice,
        user : userSlice,
        course : courseSlice
    },
})