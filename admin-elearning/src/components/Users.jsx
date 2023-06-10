import React, { useEffect } from "react";
import { useSelector, useDispatch } from 'react-redux';
import { showLoading, hideLoading } from '../redux-slices/globalSlice' 

function Users() { 
    const dispatch = useDispatch();

    useEffect(() => {
        return () => {
            dispatch(hideLoading());
        }
    },[])

  return <h1>Users Page</h1>;
}

export default Users;
