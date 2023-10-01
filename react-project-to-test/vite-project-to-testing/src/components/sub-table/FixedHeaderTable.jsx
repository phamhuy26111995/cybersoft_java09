import React from 'react';
import { Table } from 'antd';

const columns = [
  {
    title: 'Column 1',
    dataIndex: 'col1',
    key: 'col1',
    width: 150,
  },
  {
    title: 'Column 2',
    dataIndex: 'col2',
    key: 'col2',
    width: 150,
  },
  {
    title: 'Column 3',
    dataIndex: 'col3',
    key: 'col3',
    width: 150,
  },
];

const data = [
  { key: '1', col1: 't', col2: '', col3: '' },
  { key: '2', col1: '', col2: '', col3: '' },
  { key: '3', col1: '', col2: '', col3: '' },
  { key: '4', col1: '', col2: '', col3: '' },
  { key: '5', col1: '', col2: '', col3: '' },
  { key: '6', col1: '', col2: '', col3: '' },
];

const FixedHeaderTable = () => {
  return (
    <Table
      columns={columns}
      dataSource={data}
      pagination={false}
      // scroll={{ y: 300 }} // Set the height of the table body to enable vertical scrolling
      tableLayout="auto" // Set the tableLayout to "fixed"
    />
  );
};

export default FixedHeaderTable;