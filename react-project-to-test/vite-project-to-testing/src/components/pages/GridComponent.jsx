import React from "react";
import styled from "styled-components";

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: 200px 1fr auto;
  grid-template-rows: 50px 300px 50px;
  grid-template-areas: 'header header header'
                      'side content content'
                      'footer footer footer';
  height: 100%;
`;
const Header = styled.div`
  background: aqua;
  grid-area: header;

`;
const SideBar = styled.div`
  background: green;
  grid-area: side;
`;
const MainContent = styled.div`
  background: orange;
  grid-area: content;

`;
const Footer = styled.div`
  background: pink;
  grid-area: footer;
`;

function GridComponent() {
  return (
    <React.Fragment>
      <GridContainer>
        <Header>Header</Header>
        <SideBar>SideBar</SideBar>
        <MainContent>MainContent</MainContent>
        <Footer>Footer</Footer>
      </GridContainer>
    </React.Fragment>
  );
}

export default GridComponent;
