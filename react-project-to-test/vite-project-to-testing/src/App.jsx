import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import GridComponent from "./components/GridComponent";
import HomePage from "./components/HomePage";
import InfinitineScroll from "./components/InfinitineScroll";
import IntersectionObserverComponent from "./components/IntersectionObserver";
import SelectComponents from "./components/SelectComponents";
import TableComponent from "./components/TableComponent";
import DebounceComponent from "./components/websimplifier/components/DebounceComponent";
import TimeoutComponent from "./components/websimplifier/components/TimeoutComponent";
import ToggleComponent from "./components/websimplifier/components/ToggleComponent";
import ProductPage from "./components/hto-product-page/ProductPage";

function App() {


  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route key={1} element={<SelectComponents />} path="/select"  />
          <Route key={2} element={<GridComponent />} path="/grid"  />
          <Route key={3} element={<HomePage />} path="/" exact  />
          <Route key={4} element={<IntersectionObserverComponent />} path="/intersection" exact  />
          <Route key={5} element={<InfinitineScroll />} path="/infinitine" exact  />
          <Route key={6} element={<DebounceComponent />} path="/debounce" exact  />
          <Route key={7} element={<TableComponent />} path="/table" exact  />
          <Route key={8} element={<ProductPage />} path="/product" exact  />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
