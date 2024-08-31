import { Col, Divider, Row } from "antd";
import React from "react";
const style = {
    background: '#0092ff',
    padding: '8px 0',
  };
function GridAntDesign() {
  return (
    <React.Fragment>
      <Divider orientation="left">Horizontal</Divider>
      <Row gutter={16}>
        <Col className="gutter-row" span={1}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={3} offset={2}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={3}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={3}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={3}>
          <div style={style}>col-6</div>
        </Col>
      </Row>
      {/* <Divider orientation="left">Responsive</Divider>
      <Row
        gutter={{
          xs: 8,
          sm: 16,
          md: 24,
          lg: 32,
        }}
      >
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
      </Row>
      <Divider orientation="left">Vertical</Divider>
      <Row gutter={[16, 24]}>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
        <Col className="gutter-row" span={6}>
          <div style={style}>col-6</div>
        </Col>
      </Row> */}
    </React.Fragment>
  );
}

export default GridAntDesign;