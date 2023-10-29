import UserDetailPage from '@/pages/user/UserDetailPage'
import UserPage from '@/pages/user/UserPage'
import React from 'react'
import { Outlet, Route, Routes } from 'react-router-dom'


export default function Users() {
    /* All <Route path> and <Link to> values in this
       component will automatically be "mounted" at the
       /users URL prefix since the <Users> element is only
       ever rendered when the URL matches /users/*
    */
    return (
        <Outlet />
    )
  }