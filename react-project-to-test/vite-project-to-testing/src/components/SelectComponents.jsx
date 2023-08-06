import React from "react";
import { Select, Spin } from "antd";
import { useEffect } from "react";


import { useSelector, useDispatch } from "react-redux";
import { fetchPagingUsers, fetchUsers } from "../redux-slices/userSlice";
import { useState } from "react";

const { Option } = Select;

function SelectComponents() {
  const dispatch = useDispatch();
  const { users } = useSelector((state) => state.userSlice);
  const [userOptions, setUserOptions] = useState([]);
  const [pageNumber, setPageNumber] = useState(1);
  const [recordsPerPage, setRecordsPerPage] = useState(10);
  const [loading, setLoading] = useState(false);



  useEffect(() => {
    if (users.length > 0) {
      setUserOptions((prevState) => [...prevState, ...users]);
    }
  }, [users]);

  //   const handleChange = (value) => {
  //     console.log(`Selected value: ${value}`);
  //   };

  const handleChangeUser = (value) => {
    console.log(`Selected user: ${value}`);
  };

  const handlePopupScroll = (e) => {
    const { target } = e;
    // Check if user has scrolled to the bottom of the options list
    if (target.scrollTop + target.offsetHeight === target.scrollHeight) {

     
        dispatch(
          fetchPagingUsers({
            pageNumber: pageNumber + 1,
            itemsPerPage: recordsPerPage,
          })
        );
        setPageNumber(pageNumber + 1);
        setLoading(false);

    }
  };

  return (
    <React.Fragment>
      <Select
        showSearch={true}
        // optionFilterProp="children"
        onSearch={(value) => {
            dispatch(fetchPagingUsers({ pageNumber: pageNumber, itemsPerPage: recordsPerPage, searchCondition : value }))
        }}
        onChange={handleChangeUser}
        allowClear
        onPopupScroll={handlePopupScroll}
        notFoundContent={loading ? <Spin size="small" /> : null}
        virtual
        // dropdownRender={(menu) => {
        //   return (
        //     <React.Fragment>
        //       <div>
        //       {menu}
        //       </div>
        //     </React.Fragment>
        //   )
        // }}
      >
        {userOptions.map((user, index) => {
          return (
            <Option value={user.first_name} key={index}>
              {`${user.id}.${user.first_name}`}
            </Option>
          );
        })}
      </Select>
      {/* <Select
        showSearch
        onChange={handleChange}
        defaultValue="option1"
        style={{ width: 200 }}
        placeholder="Select an option"
        optionFilterProp="children"
        filterOption={(input, option) =>
          {
            console.log(input, option);
            return option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
          }
        }
      >
        <Option value="option1">Option 1</Option>
        <Option value="option2">Option 2</Option>
        <Option value="option3">Option 3</Option>
        <Option value="option4">Option 4</Option>
      </Select> */}
    </React.Fragment>
  );
}

export default SelectComponents;
