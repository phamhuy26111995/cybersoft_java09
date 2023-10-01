import React from "react";
import { Select, Spin } from "antd";
import { useEffect } from "react";


import { useSelector, useDispatch } from "react-redux";
import { fetchPagingUsers, fetchUsers } from "../redux-slices/userSlice";
import { useState } from "react";
import DebounceSelect from "./selects/DebounceSelect";

const { Option } = Select;

async function fetchUserList(username) {
  console.log('fetching user', username);
  return fetch('https://randomuser.me/api/?results=5')
    .then((response) => response.json())
    .then((body) =>
      body.results.map((user) => ({
        label: `${user.name.first} ${user.name.last}`,
        value: user.login.username,
      })),
    );
}

function SelectComponents() {
  const dispatch = useDispatch();
  const { users } = useSelector((state) => state.userSlice);
  const [value, setValue] = useState([]);


  return (
    <React.Fragment>
     <DebounceSelect
      mode="multiple"
      value={value}
      placeholder="Select users"
      fetchOptions={fetchUserList}
      onChange={(newValue) => {
        setValue(newValue);
      }}
      style={{
        width: '100%',
      }}
    />
    </React.Fragment>
  )
}

export default SelectComponents;
