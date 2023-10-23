import React, { useEffect, useState } from "react";
import { Table } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { setRowKey } from "../../redux-slices/userSlice";

const MyTable = () => {
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Age",
      dataIndex: "age",
      key: "age",
    },
    {
      title: "Address",
      dataIndex: "address",
      key: "address",
    },
  ];

  const data = [
    {
      key: "1",
      name: "John Brown",
      age: 32,
      address: "New York No. 1 Lake Park",
    },
    {
      key: "2",
      name: "Jim Green",
      age: 42,
      address: "London No. 1 Lake Park",
    },
    {
      key: "3",
      name: "Joe Black",
      age: 32,
      address: "Sidney No. 1 Lake Park",
    },
    {
      key: "4",
      name: "Joe Black",
      age: 32,
      address: "Sidney No. 1 Lake Park",
    },
    {
      key: "5",
      name: "Joe Black",
      age: 32,
      address: "Sidney No. 1 Lake Park",
    },
    {
      key: "6",
      name: "Joe Black",
      age: 32,
      address: "Sidney No. 1 Lake Park",
    },

    {
      key: "7",
      name: "Joe Black",
      age: 32,
      address: "Sidney No. 1 Lake Park",
    },
  ];

  const components = {
    header: (props) => <></>,
    body: {
      row: (props) => <RowTableCustom customProps={props} />,
    },
  };

  return (
    <Table
      columns={columns}
      dataSource={data}
      components={components}
      scroll={{ y: 300 }}
    />
  );
};

export default MyTable;

function RowTableCustom(props) {
  const { customProps } = props;
  const dispatch = useDispatch();



  return (
    <>
      <tr style={{ background: "lightyellow" }}>
        <td onClick={() => dispatch(setRowKey(+customProps["data-row-key"]))}>
          {customProps.children[0].props.record.name}
        </td>
        <td>{customProps.children[0].props.record.age}</td>
        <td>{customProps.children[0].props.record.address}</td>
      </tr>

        <SubRowTableCustom currentRowKey={customProps["data-row-key"]} />

    </>
  );
}

function SubRowTableCustom(props) {
  const { currentRowKey } = props;
  const [showHideSubRow, setShowHideSubRow] = useState(false);
  const {rowKey} = useSelector(state => {
    return state.userSlice
  });

  console.log(currentRowKey, rowKey)
  useEffect(() => {
    if(!rowKey) return;

    if(rowKey == currentRowKey) {
      setShowHideSubRow(true);
    } else {
      setShowHideSubRow(false);
    }
  },[rowKey])

  return (
    <>
      {showHideSubRow && (
        <React.Fragment>
          <tr>
            <td style={{ border: "1px solid green" }}>test</td>
            <td>test</td>
            <td>test</td>
          </tr>
          <tr>
            <td style={{ border: "1px solid green" }}>test</td>
            <td>test</td>
            <td>test</td>
          </tr>
          <tr>
            <td style={{ border: "1px solid green" }}>test</td>
            <td>test</td>
            <td>test</td>
          </tr>
          <tr>
            <td style={{ border: "1px solid green" }}>test</td>
            <td>test</td>
            <td>test</td>
          </tr>
        </React.Fragment>
      )}
    </>
  );
}
