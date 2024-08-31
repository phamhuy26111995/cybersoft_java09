import { Divider, Space } from "antd";
import React from "react";
import { useState } from "react";
import "../styles/_table.scss";
import FixedHeaderTable from "../sub-table/FixedHeaderTable";
import TableNumberFour from "../sub-table/TableNumberFour";
import TableNumberOne from "../sub-table/TableNumberOne";
import TableNumberThree from "../sub-table/TableNumberThree";
import TableNumberTwo from "../sub-table/TableNumberTwo";
import UserInfoTable from "../sub-table/UserInfoTable";
import GroupColumnTable from "../sub-table/GroupColumnTable";
import DynamicForm from "./DynamicForm.jsx";
import TabComponent from "./TabComponent.jsx";
import DynamicTabsWithFormList from "./DynamicTabsWithFormList.jsx";
import EditableTabs from "../hto-product-page/EditableTabs.jsx";
import FormProvider from "../form-context/FormProvider.jsx";
import VirtualTable from "../sub-table/VirtualTable";
import VirtualTableComponent from "../sub-table/VirtualTable";
import MyVirtualTable from "../sub-table/MyVirtualTable";
import LargeDataTable from "./LargeDataTable";

const columns = [
  {
    title: "A",
    dataIndex: "key",
    width: 150,
  },
  {
    title: "B",
    dataIndex: "key",
  },
  {
    title: "C",
    dataIndex: "key",
  },
  {
    title: "D",
    dataIndex: "key",
  },
  {
    title: "E",
    dataIndex: "key",
    width: 200,
  },
  {
    title: "F",
    dataIndex: "key",
    width: 100,
  },
];
const data = Array.from(
  {
    length: 20,
  },
  (_, key) => ({
    key,
  })
);

const TableComponent = () => {
  // const [test , setTest] = useState('test');

  // function render() {

  //   return <React.Fragment>
  //     <h1>{test}</h1>
  //     <button onClick={setTest('Test')}>Click</button>
  //   </React.Fragment>
  // }

  return (
    <React.Fragment>
      <div className="table-container">
        {/* <TableNumberOne />
        <Divider />
        <Space />
        <TableNumberTwo />
        <Divider />
        <Space />
        <TableNumberThree />
        <Divider />
        <Space />
        <TableNumberFour />
        <Divider />
        <Space />
        <FixedHeaderTable /> */}
        {/* <UserInfoTable /> */}
        {/*<TableNumberOne />*/}
        {/*  <DynamicForm/>*/}
        {/*  <TabComponent />*/}
        {/*  <DynamicTabsWithFormList/>*/}
        {/* <VirtualTable /> */}
        {/* <MyVirtualTable /> */}
        <LargeDataTable />
      </div>
      {/* {render()} */}
    </React.Fragment>
  );
};

export default TableComponent;
