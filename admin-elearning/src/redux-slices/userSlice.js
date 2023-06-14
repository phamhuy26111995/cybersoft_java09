import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import userApi from "../api/user/user_api";
import { PAGE_URL } from "../consts/path";
import { redirect } from "react-router-dom";

import { hideLoading, showError, showLoading, showSuccess } from "./globalSlice";

const initialState = {
  userInfo: undefined,
  userList: [],
  userDetailInfo: undefined,
  roleList: [],
  total : 0
};

export const fetchAllUser = createAsyncThunk(
  "user/fetchAllUser",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
    const response = await userApi.search(body);
    thunkAPI.dispatch(hideLoading());
    return response;

    } catch (error) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lấy được thông tin của người dùng"))
    }
  }
);

export const init = createAsyncThunk(
  "user/init",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
    const response = await userApi.init();
    thunkAPI.dispatch(hideLoading());
    return response;
    } catch (error) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lấy được thông tin quyền"))
    }
  }
);

export const save = createAsyncThunk(
  "user/save",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
    const response = await userApi.save(body);
    thunkAPI.dispatch(hideLoading());
    thunkAPI.dispatch(showSuccess("Thêm mới người dùng thành công"));
    window.location.href = PAGE_URL.USERS.DETAIL.replace(":id" , response.data);
    thunkAPI.dispatch(getUserDetail({id : +response.data}))
    return response;
    } catch (error) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Thêm mới người dùng không thành công"))
    }
  }
);

export const getUserDetail = createAsyncThunk(
  "user/detail",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
    const response = await userApi.getDetail(body);
    thunkAPI.dispatch(hideLoading());
    return response;
    } catch (error) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lấy được thông tin người dùng"))
    }
  }
);

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setUserInfo: (state, action) => {
      state.userInfo = action.payload;
    },

    clearState: (state, action) => {
      return initialState;
    }
  },
  extraReducers: (builder) => {
    builder.addCase(fetchAllUser.fulfilled, (state, action) => {
      state.userList = action.payload.users;
      state.total = action.payload.total;
    }).addCase(init.fulfilled, (state, action) => {
      state.roleList = action.payload.roles;
    }).addCase(getUserDetail.fulfilled, (state, action) => {
      state.userDetailInfo = action.payload;
      state.userDetailInfo.fileFromServer = {
        uid: '-1',
        name : `${state.userDetailInfo.avatar}`,
        status : 'done',
        url : `${state.userDetailInfo.avatar}`,
        thumbUrl: `${state.userDetailInfo.avatar}`
      }
    });
  }
});

export const { setUserInfo, clearState } = userSlice.actions;

export default userSlice.reducer;
