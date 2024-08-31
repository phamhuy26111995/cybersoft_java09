import { configureStore } from "@reduxjs/toolkit";
import  userSlice  from "../redux-slices/userSlice";
import componentASlice from "../redux-slices/componentA-slice";
import componentBSlice from "../redux-slices/componentB-slice";
import reduxEntitiesSlice from "../redux-slices/reduxEntitiesSlice";


export const store = configureStore({
    reducer : {
        userSlice : userSlice,
        ASlice : componentASlice,
        BSlice : componentBSlice,
        reduxSlice: reduxEntitiesSlice
    },
})