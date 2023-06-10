import React from "react";
import {
    Card,
    Table,
    Image,
    Form,
    Row,
    Col,
    Modal,
    Button,
    Input,
    Tree,
    Collapse,
  } from "antd"; 

function VideoCourseContent(props) {
    let {videoDtos, treeIndex , form, videoIndex} = props;
    const handleTreeChange = (newValue, treeIndex, videoIndex) => {
        const newValues = [...form.getFieldValue("courseContentDtoList")];
        newValues[treeIndex].videoDtos[videoIndex] = newValue;
        form.setFieldsValue({ courseContentDtoList: newValues });
      };
  
      return (
        <Tree
          treeData={videoDtos}
          onChange={(newValue) =>
            handleTreeChange(newValue, treeIndex, videoIndex)
          }
        />
      );
}

export default VideoCourseContent;