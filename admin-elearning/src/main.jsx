import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { store } from './store/store';
import { Provider } from 'react-redux'


// const router = createBrowserRouter([
//   {
//     path: "/",
//     element: <App />,
//     errorElement: <NotFound />,
//     children: PAGES.map(page => page)
//   },
// ]);

ReactDOM.createRoot(document.getElementById("root")).render(
  // <RouterProvider router={router} />
  <Provider store={store}>
    <App />
  </Provider>,
);
