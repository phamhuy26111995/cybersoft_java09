import React, { useState } from 'react';
import { Table, Select, Button, Popconfirm } from 'antd';
import { PlusOutlined, DeleteOutlined } from '@ant-design/icons';

const { Option } = Select;

const initialData = [
  {
    key: 1,
    sport: {
      sportName: "Football",
      sportId: 1
    },
    league: {
      leagueName: "Premier League",
      leagueId: 1
    },
    oddGroup: "A",
    oddGroupInPlay: "A"
  },
  {
    key: 2,
    sport: {
      sportName: "Basketball",
      sportId: 2
    },
    league: {
      leagueName: "NBA",
      leagueId: 2
    },
    oddGroup: "B",
    oddGroupInPlay: "B"
  }
];

const EditableTable = () => {
  const [dataSource, setDataSource] = useState(initialData);
  const [count, setCount] = useState(initialData.length + 1);

  const handleAdd = () => {
    const newData = {
      key: count,
      sport: {
        sportName: "New Sport",
        sportId: count
      },
      league: {
        leagueName: "New League",
        leagueId: count
      },
      oddGroup: "A",
      oddGroupInPlay: "A"
    };
    setDataSource([...dataSource, newData]);
    setCount(count + 1);
  };

  const handleSave = (row) => {
    const newData = [...dataSource];
    const index = newData.findIndex((item) => row.key === item.key);
    const item = newData[index];
    newData.splice(index, 1, { ...item, ...row });
    setDataSource(newData);
  };

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  };

  const handleSubmit = (key) => {
    const row = dataSource.find((item) => item.key === key);
    const result = {
      sportId: row.sport.sportId,
      leagueId: row.league.leagueId,
      oddGroup: row.oddGroup,
      oddGroupInPlay: row.oddGroupInPlay
    };
    console.log('Submitted data:', result);
  };

  const columns = [
    {
      title: 'Sport',
      dataIndex: ['sport', 'sportName'],
      render: (text) => <span>{text}</span>
    },
    {
      title: 'League',
      dataIndex: ['league', 'leagueName'],
      render: (text) => <span>{text}</span>
    },
    {
      title: 'Odd Group',
      dataIndex: 'oddGroup',
      render: (text, record) => (
        <Select
          value={text}
          onChange={(value) => handleSave({ ...record, oddGroup: value })}
        >
          <Option value="A">A</Option>
          <Option value="B">B</Option>
          <Option value="C">C</Option>
          <Option value="D">D</Option>
        </Select>
      )
    },
    {
      title: 'Odd Group In Play',
      dataIndex: 'oddGroupInPlay',
      render: (text, record) => (
        <Select
          value={text}
          onChange={(value) => handleSave({ ...record, oddGroupInPlay: value })}
        >
          <Option value="A">A</Option>
          <Option value="B">B</Option>
          <Option value="C">C</Option>
          <Option value="D">D</Option>
        </Select>
      )
    },
    {
      title: 'Actions',
      dataIndex: 'actions',
      render: (_, record) => (
        <>
          <Button type="primary" onClick={() => handleSubmit(record.key)}>
            Submit
          </Button>
          <Popconfirm
            title="Are you sure to delete this row?"
            onConfirm={() => handleDelete(record.key)}
            okText="Yes"
            cancelText="No"
          >
            <Button
              type="danger"
              icon={<DeleteOutlined />}
              style={{ marginLeft: 8 }}
            >
              Delete
            </Button>
          </Popconfirm>
        </>
      )
    }
  ];

  return (
    <>
      <Button
        type="dashed"
        onClick={handleAdd}
        style={{ marginBottom: 16 }}
        icon={<PlusOutlined />}
      >
        Add row
      </Button>
      <Table
        dataSource={dataSource}
        columns={columns}
        pagination={false}
        rowKey="key"
      />
    </>
  );
};

export default EditableTable;
