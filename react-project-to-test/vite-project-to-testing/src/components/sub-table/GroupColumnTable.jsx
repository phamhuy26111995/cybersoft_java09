import React, { useEffect, useState } from "react";
import { Breadcrumb, Table } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { setRowKey } from "../../redux-slices/userSlice";
const subData = [
  {
    key: "sub-1",
    name: "Lee sin",
    age: 40,
    address: "Bình Dương",
  },
  {
    key: "sub-2",
    name: "Rengar",
    age: 50,
    address: "Bình Thạnh",
  },
  {
    key: "sub-3",
    name: "Xin zhao",
    age: 60,
    address: "Thủ Đức",
  },

  {
    key: "sub-4",
    name: "Jarvan",
    age: 70,
    address: "Gò Vấp",
  },
];
const MyTable = () => {
  const columns = [
    {
      title: "Name",
      // dataIndex: "name",
      key: "name",
    },
    {
      title: "Age",
      // dataIndex: "age",
      key: "age",
    },
    {
      title: "Address",
      // dataIndex: "address",
      key: "address",
    },
  ];

  const data = [
    {
      key: "1",
      name: "John Brown",
      age: 10,
      address: "New York No. 1 Lake Park",
    },
    {
      key: "2",
      name: "Jim Green",
      age: 20,
      address: "London No. 1 Lake Park",
    },
    {
      key: "3",
      name: "Joe Black",
      age: 30,
      address: "Đồng Nai",
    },
    {
      key: "4",
      name: "Mr Bean",
      age: 40,
      address: "Bình Dương",
    },
    {
      key: "5",
      name: "Bruce Lee",
      age: 50,
      address: "Bình Thạnh",
    },
    {
      key: "6",
      name: "Stephen Chow",
      age: 60,
      address: "Thủ Đức",
    },

    {
      key: "7",
      name: "Ezreal",
      age: 70,
      address: "Gò Vấp",
    },
  ];

  const components = {
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

  const keyFields = Object.keys(customProps.children[0].props.record).filter(
    (el) => el !== "key"
  );

  console.log(customProps);
  return (
    <>
      <tr style={{ background: "blue" }}>
        {customProps.children.map((el, index) => (
          <td>
            <span
              onClick={
                el.key === "name"
                  ? () => {
                      dispatch(setRowKey(+customProps["data-row-key"]));
                    }
                  : () => {}
              }
            >
              {customProps.children[0].props.record[keyFields[index]]}
            </span>
          </td>
        ))}
        {/* <td onClick={() => dispatch(setRowKey(+customProps["data-row-key"]))}>
          {customProps.children[0].props.record.name}
        </td>
        <td>{customProps.children[0].props.record.age}</td>
        <td>{customProps.children[0].props.record.address}</td> */}
      </tr>

      <SubRowTableCustom currentRowKey={customProps["data-row-key"]} />
    </>
  );
}

function SubRowTableCustom(props) {
  const { currentRowKey } = props;
  const [showHideSubRow, setShowHideSubRow] = useState(false);
  const { rowKey } = useSelector((state) => {
    return state.userSlice;
  });

  console.log(currentRowKey, rowKey);
  useEffect(() => {
    if (!rowKey) return;

    if (rowKey == currentRowKey) {
      setShowHideSubRow(true);
    } else {
      setShowHideSubRow(false);
    }
  }, [rowKey]);

  return (
    <>
      {showHideSubRow && (
        <React.Fragment>
          <BreadcrumbCustom />
          {subData.map((el) => (
            <React.Fragment>
              <tr>
                <td>{el.name}</td>
                <td>{el.age}</td>
                <td>{el.address}</td>
              </tr>
            </React.Fragment>
          ))}
        </React.Fragment>
      )}
    </>
  );
}

function BreadcrumbCustom() {
  return (
   <tr style={{background : 'yellow'}}>
    <td colSpan={3}>
    <Breadcrumb>
      <Breadcrumb.Item>Home</Breadcrumb.Item>
      <Breadcrumb.Item>
        <a href="">Application Center</a>
      </Breadcrumb.Item>
      <Breadcrumb.Item>
        <a href="">Application List</a>
      </Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      <Breadcrumb.Item>An Application</Breadcrumb.Item>
      
    </Breadcrumb>
    </td>
   </tr>
  );
}
