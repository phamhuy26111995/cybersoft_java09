import {Button, Table} from 'antd';
import React from 'react'
// import './assets/styles/_custom_second.scss'
import classes from'./assets/styles/_custom_second.module.scss';


const dataSource = [
  {
    key: '1',
    name: 'Mike',
    age: 32,
    address: '10 Downing Street',
    tags: ['nice', 'developer'],
  },
  {
    key: '2',
    name: 'Mike',
    age: 32,
    address: '10 Downing Street',
    tags: ['nice', 'developer'],
  },
  {
    key: '3',
    name: 'Mike',
    age: 32,
    address: '10 Downing Street',
    tags: ['nice', 'developer'],
  }
]  


const { Column } = Table;

function ComponentTwo() {
  return (

       <Table dataSource={dataSource} 
    //    className={"custom-component-two"}
    className={classes.componentTwo}
       >
        <Column title="Name" dataIndex="name" key="name" />
        <Column title="Age" dataIndex="age" key="age" />
        <Column title="Address" dataIndex="address" key="address" />
        <Column title="Tags" dataIndex="tags" key="tags" />
      </Table>


  
  )
}

export default ComponentTwo;