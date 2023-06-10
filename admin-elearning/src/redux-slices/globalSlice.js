import { createSlice  } from "@reduxjs/toolkit";
import categoryApi from "../api/category/category_api";
import { Button, notification, Space } from 'antd';

const notificationConfig = {
  placement: 'topRight',
  bottom: 50,
  duration: 3,
  rtl: true,
}

const initialState = {
  loading: false,

};


export const globalSlice = createSlice({
  name: "global",
  initialState,
  reducers: {
    showLoading:   (state, action) => {
      state.loading = true;
    },
    hideLoading: (state, action) => {
        state.loading = false;
    },
    showError : (state, action) => {
      notificationConfig.message = action.payload;
      notification.error(notificationConfig);
    },
    showInfo : (state, action) => {
      notificationConfig.message = action.payload;
      notification.info(notificationConfig)
    },
    showSuccess : (state, action) => {
      notificationConfig.message = action.payload;
      notification.success(notificationConfig)
    }
  },
});

export const { showLoading, hideLoading, showError, showInfo, showSuccess} = globalSlice.actions;

export default globalSlice.reducer;
