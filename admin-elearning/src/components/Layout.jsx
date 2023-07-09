import React from "react";
import { LEFT_MENU } from "./LeftMenu";
import { Layout, Menu, theme } from "antd";
import Logout from "./Logout";
import ElearningImage from "../assets/Elearning.png";

const { Header, Content, Footer, Sider } = Layout;

const MainLayout = (props) => {
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <Layout hasSider>
      <Sider
        style={{
          overflow: "auto",
          height: "100vh",
          position: "fixed",
          left: 0,
          top: 0,
          bottom: 0,
        }}
      >
        <div
          style={{
            height: 32,
            width: '100%',
            margin: 16,
          }}
        >
        </div>
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={["4"]}
          items={LEFT_MENU}
        />
      </Sider>
      <Layout
        className="site-layout"
        style={{
          marginLeft: 200,
        }}
      >
        <Header
          style={{
            padding: 10,
            background: colorBgContainer,
            display: "flex",
            justifyContent: 'end',
            alignItems: 'center'
          }}
          children={<Logout />}
        />
        <Content
          style={{
            margin: "24px 16px 0",
            overflow: "initial",
          }}
        >
          <div
            style={{
              padding: 24,
              textAlign: "center",
              background: colorBgContainer,
              position: "relative",
            }}
          >
            {props.children}
          </div>
        </Content>
        <Footer
          style={{
            textAlign: "center",
          }}
        >
          Created by Huy Pham
        </Footer>
      </Layout>
    </Layout>
  );
};
export default MainLayout;
