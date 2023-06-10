import { createSlice, createAsyncThunk  } from "@reduxjs/toolkit";
import categoryApi from "../api/category/category_api";

const initialState = {
  userInfo: undefined,
};



export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setUserInfo:   (state, action) => {
      state.userInfo = action.payload;
    },
  },
});

export const { setUserInfo } = userSlice.actions;

export default userSlice.reducer;


