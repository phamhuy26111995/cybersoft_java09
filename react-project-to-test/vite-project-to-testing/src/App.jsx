import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import GridComponent from "./components/GridComponent";
import HomePage from "./components/HomePage";
import SelectComponents from "./components/SelectComponents";

function App() {


  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route key={1} element={<SelectComponents />} path="/select"  />
          <Route key={2} element={<GridComponent />} path="/grid"  />
          <Route key={3} element={<HomePage />} path="/" exact  />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
