import { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PrivateRoutes from "./components/PrivateRoute";
import userApi from "./api/user/user_api";
import {  useDispatch } from 'react-redux';
import {
  showLoading,
  hideLoading,
  showError,
} from './redux-slices/globalSlice';
import {
  setUserInfo
} from './redux-slices/userSlice';

import { PAGES } from "./routes/pages";
import { useEffect } from "react";
import { PAGE_URL } from "./consts/path";
import { ACCESS_TOKEN } from "./consts/common";

function App() {
  const dispatch = useDispatch();

  const getCurrentUser = async () => {
    const token = localStorage.getItem(ACCESS_TOKEN);
    
    if(!token) return;
    try {
      dispatch(showLoading());
      let userData = await userApi.getCurrent();
      dispatch(hideLoading())
      dispatch(setUserInfo(userData));
    } catch (err) {
      dispatch(showError("Không thể lấy thông tin user"));
      localStorage.removeItem(ACCESS_TOKEN);
      window.location.assign(PAGE_URL.LOGIN);
    }
  }
  useEffect(() => {
    getCurrentUser();
  },[])

  
 
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route element={<PrivateRoutes />}>
            {PAGES.authPages.map((page) => page)}
          </Route>
          {PAGES.noAuthPages.map((page) => page)}
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
