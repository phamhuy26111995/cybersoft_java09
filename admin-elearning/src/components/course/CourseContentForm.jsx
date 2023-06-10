import React, { useState } from "react";
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
  Upload,
} from "antd";
import { commonProps } from "../../consts/common";
import {
  DownOutlined,
  PlusOutlined,
  MinusCircleOutlined,
  UploadOutlined,
} from "@ant-design/icons";
import VideoCourseContent from "./VideoCourseContent";
import { useEffect } from "react";

const { Panel } = Collapse;

const fakeData = [
  {
    title: "Test 1",
    videoDtos: [
      {
        title: "video 1",
        url: "http://www.youtube.com/watch",
      },
    ],
  },

  {
    title: "Test 2",
    videoDtos: [
      {
        title: "video 2",
        url: "http://www.youtube.com/watch",
      },
    ],
  },
];

function CourseContentForm(props) {
  const { courseContentForm, setDeleteVideo, setDeleteCourseContent, isEdit } =
    props;
  const [showModal, setShowModal] = useState(false);
  const [fileList, setFileList] = useState([]);

  const normFile = (e) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList;
  };

  const customUploadRequest = ({ file, onSuccess }) => {
    setTimeout(() => {
      onSuccess();
    }, 2000);
  };

  // const renderVideoDtos = (videoDtos, treeIndex) => {
  //   const handleTreeChange = (newValue, treeIndex, videoIndex) => {
  //     const newValues = [...form.getFieldValue("courseContentDtoList")];
  //     newValues[treeIndex].videoDtos[videoIndex] = newValue;
  //     form.setFieldsValue({ courseContentDtoList: newValues });
  //   };

  //   return (
  //     <Tree
  //       treeData={videoDtos}
  //       onChange={(newValue) =>
  //         handleTreeChange(newValue, treeIndex, videoIndex)
  //       }
  //     />
  //   );
  // };

  const renderCourseContent = (fields, remove) =>
    fields.map((field, treeIndex) => (
      <Panel
        header={
          <div style={{ display: "flex" }}>
            <Form.Item
              {...field}
              name={[field.name, "content"]}
              fieldKey={[field.fieldKey, "content"]}
              rules={[{ required: true, message: "Content is required" }]}
              style={{ flexGrow: 9, marginRight: "10px" }}
            >
              <Input placeholder="Content" />
            </Form.Item>
            <Button
              type="dashed"
              onClick={() => {
                setDeleteCourseContent((prevState) => {
                  let currentCourseContent = courseContentForm.getFieldValue([
                    "courseContentDtoList",
                    field.name,
                  ]);
                  if (currentCourseContent) {
                    prevState.push(currentCourseContent.id);
                    let newState = [...prevState];

                    return newState;
                  }
                });
                remove(field.name);
              }}
              icon={<MinusCircleOutlined style={{ color: "white" }} />}
              style={{ backgroundColor: "red", color: "white" }}
            >
              Remove Course
            </Button>
          </div>
        }
        key={field.name}
      >
        <Form.List name={[field.name, "videoDtos"]}>
          {(videoFields, { add, remove }) => (
            <div>
              {videoFields.map((videoField, videoIndex) => (
                <div key={videoField.key}>
                  {/* {renderVideoDtos(videoField.value, treeIndex, videoIndex)} */}
                  {/* <VideoCourseContent
                      videoDtos={videoField.value}
                      treeIndex={treeIndex}
                      form={form}
                      videoIndex={videoIndex}
                    /> */}
                  <Row {...commonProps.rowGutterForColTwo}>
                    <Col
                      className="gutter-row"
                      {...commonProps.colWillDivideTwo}
                    >
                      <Form.Item
                        labelAlign="right"
                        label="Tiêu đề video"
                        name={[videoField.name, "title"]}
                      >
                        <Input />
                      </Form.Item>
                    </Col>
                    <Col
                      className="gutter-row"
                      {...commonProps.colWillDivideTwo}
                    >
                      <Form.Item
                        label="Video Url"
                        name={[videoField.name, "url"]}
                      >
                        <Input />
                      </Form.Item>
                    </Col>
                    <Col
                      className="gutter-row"
                      {...commonProps.colWillDivideTwo}
                    >
                      <Form.Item
                        label="Thời gian video"
                        name={[videoField.name, "timeCount"]}
                      >
                        <Input />
                      </Form.Item>
                    </Col>
                  </Row>
                  <Form.Item>
                    <Button
                      type="danger"
                      onClick={() => {
                        if (isEdit) {
                          let currentVideoField =
                            courseContentForm.getFieldValue([
                              "courseContentDtoList",
                              field.name,
                              "videoDtos",
                              videoField.name,
                            ]);
                          setDeleteVideo((prevState) => {
                            if (currentVideoField) {
                              prevState.push(currentVideoField.id);
                              let newState = [...prevState];

                              return newState;
                            }
                          });
                        }
                        remove(videoField.name);
                      }}
                      icon={<MinusCircleOutlined />}
                    >
                      Remove Video
                    </Button>
                  </Form.Item>
                </div>
              ))}
              <Form.Item>
                <Button
                  type="dashed"
                  onClick={() => add()}
                  icon={<PlusOutlined />}
                >
                  Add Video
                </Button>
              </Form.Item>
            </div>
          )}
        </Form.List>
      </Panel>
    ));

  return (
    <React.Fragment>
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        size="default"
        title="Nội dung khóa học"
      >
        <Form form={courseContentForm} layout="vertical">
          <Form.List name="courseContentDtoList">
            {(fields, { add, remove }) => (
              <>
                <Collapse>
                  {fields.map((field) => (
                    <React.Fragment key={field.key}>
                      {renderCourseContent([field], remove)}
                    </React.Fragment>
                  ))}
                </Collapse>
                <Button
                  type="dashed"
                  onClick={() => add()}
                  icon={<PlusOutlined style={{ color: "white" }} />}
                  style={{
                    marginTop: "1.5rem",
                    color: "white",
                    backgroundColor: "green",
                  }}
                >
                  Add Course Content
                </Button>
              </>
            )}
          </Form.List>
        </Form>
      </Card>
    </React.Fragment>
  );
}

export default CourseContentForm;
