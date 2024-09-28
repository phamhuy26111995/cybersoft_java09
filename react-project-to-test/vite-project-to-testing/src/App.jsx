import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import MultipleFormAntDesign from "./components/pages/MultipleFormAntDesign";
import { FormProvider } from "antd/lib/form/context";
import GridComponent from "./components/pages/GridComponent";
import GridAntDesign from "./components/pages/GridAntDesign";
import SimpleSelect from "./components/pages/SimpleSelect";
import MeasurePage from "./components/pages/MeasureComponent";
import FixedDataTableComponent from "./components/pages/FixedDataTableComponent";
import DynamicContentTable from "./components/pages/DynamicContentTable";
import FormProviderComponent from "./components/form-ant-design/FormProviderComponent";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route
            key={1}
            element={<MultipleFormAntDesign />}
            path="/multiple-form-ant-design"
            exact
          />
          <Route key={2} element={<GridComponent />} path="/grid" exact />
          <Route key={3} element={<GridAntDesign />} path="/grid-antd" exact />
          <Route key={4} element={<SimpleSelect />} path="/simple-select" exact />
          <Route key={5} element={<FixedDataTableComponent />} path="/data-table" exact />
          <Route key={6} element={<MeasurePage />} path="/measure-page" exact />
          <Route key={7} element={<DynamicContentTable />} path="/dynamic-table" exact />
          <Route key={8} element={<FormProviderComponent />} path="/form-provider" exact />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
