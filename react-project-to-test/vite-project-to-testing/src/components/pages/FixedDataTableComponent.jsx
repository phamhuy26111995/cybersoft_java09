import React from "react";
import { Table, Column, Cell } from "fixed-data-table-2";
import "fixed-data-table-2/dist/fixed-data-table.css";
import { FirstNameCell, LastNameCell, NoCell, UserNameCell } from "./DynamicContentTable";

const FixedDataTableComponent = ({ sampleData, heightMap, widthMap }) => {


  const sum2 = Array.from(widthMap.current.values()).reduce((acc, value) => acc + value, 0);

  

  return (
    <Table
      rowHeight={50}
      rowsCount={sampleData.length}
      width={sum2 + 100}
      height={500}
      headerHeight={50}
      // rowHeightGetter={index => heightMap.current.get(index)}
    >
      <Column
        header={<Cell>No</Cell>}
        cell={({ rowIndex }) => (
          <Cell>
            <NoCell data={sampleData[rowIndex]} colKey={"no"} />
          </Cell>
        )}
        width={widthMap.current.get("no")}
      />
      <Column
        header={<Cell>UserName</Cell>}
        cell={({ rowIndex }) => (
          <Cell>
            <UserNameCell data={sampleData[rowIndex]} colKey={"userName"} />
          </Cell>
        )}
        width={widthMap.current.get("userName")}
      />
      <Column
        header={<Cell>FirstName</Cell>}
        cell={({ rowIndex }) => (
          <Cell>
            <FirstNameCell data={sampleData[rowIndex]} colKey={"firstName"} />
          </Cell>
        )}
        width={widthMap.current.get("firstName")}
      />
      <Column
        header={<Cell>LastName</Cell>}
        flexGrow={1}
        cell={({ rowIndex }) => (
          <Cell>
            <LastNameCell data={sampleData[rowIndex]} colKey={"lastName"} />
          </Cell>
        )}
        width={widthMap.current.get("lastName")}
      />
    </Table>
  );
};

export default FixedDataTableComponent;
