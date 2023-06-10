import { Outlet, Navigate } from 'react-router-dom';
import React from 'react';
import MainLayout from './Layout';
import { PAGE_URL } from '../consts/path';
import Loading from './Loading';
import { useSelector, useDispatch } from 'react-redux';
import { showLoading, hideLoading } from '../redux-slices/globalSlice' 
import { ACCESS_TOKEN } from '../consts/common';

const PrivateRoutes = () => {
    let token = localStorage.getItem(ACCESS_TOKEN);
    let loading = useSelector((state) => state.global.loading);
    return(
        token ? <MainLayout>
            {loading && <Loading /> }
            <Outlet/>
        </MainLayout> : <Navigate to={PAGE_URL.LOGIN} />
    )
}

export default PrivateRoutes