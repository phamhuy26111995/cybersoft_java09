import React, { useEffect, useRef, useState } from "react";
import MeasurePage from "./MeasureComponent";
import FixedDataTableComponent from "./FixedDataTableComponent";
import { Table, Column, Cell } from "fixed-data-table-2";
import 'fixed-data-table-2/dist/fixed-data-table.css';

const firstNames = [
  "An",
  "Bình",
  "Cường",
  "Dung",
  "Hoa",
  "Linh",
  "Minh",
  "Nam",
  "PhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhươngPhương",
  "Quang",
  "Thảo",
  "Uyên",
  "Việt",
  "Xuân",
  "Yến",
];

const lastNames = [
  "Nguyễn",
  "Trần",
  "Lê",
  "Phạm",
  "Hoàng",
  "Huỳnh",
  "Phan",
  "Vũ",
  "Võ",
  "Đặng",
  "Bùi",
  "Đỗ",
  "Hồ",
  "Ngô",
  "Dương",
];

const generateUserName = (firstName, lastName) => {
  return (
    firstName.toLowerCase() +
    lastName.toLowerCase() +
    Math.floor(Math.random() * 100)
  ).replace(/đ/g, "d");
};

const generateSampleData = (count) => {
  return Array.from({ length: count }, (_, index) => {
    const firstName = firstNames[Math.floor(Math.random() * firstNames.length)];
    const lastName = lastNames[Math.floor(Math.random() * lastNames.length)];
    return {
      no: index + 1,
      userName: generateUserName(firstName, lastName),
      firstName: firstName,
      lastName: lastName,
    };
  });
};

const sampleData = generateSampleData(100);


export const NoCell = (props) => {
  const { data, colKey } = props;
  return (
    <div>
      <div style={{ background: "aqua" }}>{data[colKey]}</div>
    </div>
  );
};
const NoHeader = (props) => {
  return (
    <div>
      <div style={{ background: "aqua" }}>No</div>
    </div>
  );
};

export const UserNameCell = (props) => {
  const { data, colKey} = props;
  return (
    <div>
      <div style={{ background: "blue", color: "white" }}>
        {data[colKey]}
      </div>
    </div>
  );
};


export const FirstNameCell = (props) => {
  const { data, colKey } = props;
  return (
    <div>
      <div style={{ background: "red", color: "white" }}>
        {data[colKey]}
      </div>
    </div>
  );
};


export const LastNameCell = (props) => {
  const { data, colKey } = props;
  return (
    <div>
      <div style={{ background: "green", color: "white" }}>
        {data[colKey]}
      </div>
    </div>
  );
};




const cellMap = (rowData) => {
  return {
    no: <NoCell data={rowData} colKey={"no"} />,
    userName: <UserNameCell data={rowData} colKey="userName" />,
    firstName: <FirstNameCell data={rowData} colKey="firstName" />,
    lastName: <LastNameCell data={rowData} colKey="lastName" />,
  };
};

function DynamicContentTable() {
  const [isCalculateContent, setIsCalculateContent] = useState(true);
  const heightMap = useRef(new Map());
  const widthMap = useRef(new Map());

  // useEffect(() => {


  //   setIsCalculateContent(true);
  // }, []);


  return (
    <>
      {isCalculateContent   ? (
        <MeasurePage
          dataList={sampleData}
          cellMap={cellMap}
          setIsCalculateContent={setIsCalculateContent}
          heightMap={heightMap}
          widthMap={widthMap}
        />
      ) : (
        <FixedDataTableComponent sampleData={sampleData} heightMap={heightMap} widthMap={widthMap} />
        
      )}

{/* <FixedDataTableComponent sampleData={sampleData} heightMap={heightMap} widthMap={widthMap} /> */}
    </>
  );
}

export default DynamicContentTable;
