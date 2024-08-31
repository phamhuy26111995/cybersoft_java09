import React from "react";
import { ThemeProvider } from "styled-components";

const theme = () => {
  const skin = localStorage.getItem("skin");

  switch (skin) {
    case "ps3838":
      return {
        textColor: "violet",
        backGroundColor: "blue",
      };
    case "fatbets":
      return {
        textColor: "yello",
        backGroundColor: "red",
      };

    default:
      return {
        textColor : 'green',
        backGroundColor : 'pink'
      }
      
  }
};

export default function Theme({ children }) {
  return <ThemeProvider theme={theme}>{children}</ThemeProvider>;
}
