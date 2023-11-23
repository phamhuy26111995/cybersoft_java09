import { Divider, Space } from "antd";
import React from "react";
import { useState } from "react";
import "../styles/_table.scss";
import FixedHeaderTable from "./sub-table/FixedHeaderTable";
import TableNumberFour from "./sub-table/TableNumberFour";
import TableNumberOne from "./sub-table/TableNumberOne";
import TableNumberThree from "./sub-table/TableNumberThree";
import TableNumberTwo from "./sub-table/TableNumberTwo";
import UserInfoTable from "./sub-table/UserInfoTable";
import GroupColumnTable from "./sub-table/GroupColumnTable";
import DynamicForm from "./DynamicForm.jsx";
import TabComponent from "./TabComponent.jsx";
import DynamicTabsWithFormList from "./DynamicTabsWithFormList.jsx";
import EditableTabs from "./hto-product-page/EditableTabs.jsx";
import FormProvider from "./form-context/FormProvider.jsx";

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
        <FormProvider>
          <EditableTabs />
        </FormProvider>
      </div>
      {/* {render()} */}
    </React.Fragment>
  );
};

export default TableComponent;
