import { Divider, Space } from "antd";
import React from "react";
import { useState } from "react";
import "../styles/_table.scss";
import TableNumberFour from "./sub-table/TableNumberFour";
import TableNumberOne from "./sub-table/TableNumberOne";
import TableNumberThree from "./sub-table/TableNumberThree";
import TableNumberTwo from "./sub-table/TableNumberTwo";




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
        <TableNumberOne />
        <Divider />
        <Space />
        <TableNumberTwo />
        <Divider />
        <Space />
        <TableNumberThree />
        <Divider />
        <Space />
        <TableNumberFour />
      </div>
      {/* {render()} */}
    </React.Fragment>
  );
};

export default TableComponent;
