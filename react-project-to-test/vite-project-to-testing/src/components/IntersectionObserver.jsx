import React, { useEffect, useRef } from "react";
import "../styles/_card.scss";

const dataSource = [
  "This is the first card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is a Card",
  "This is last card",
];

const observer = new IntersectionObserver((entries) => {
  entries.forEach((entry) => console.log(entry));
}, {});

function IntersectionObserverComponent() {

  const elementRef = useRef();

  useEffect(() => {
    const observer = new IntersectionObserver(([entry]) => {
        if(entry.isIntersecting) {
            elementRef.current.style.color= "red"
        } else {
            elementRef.current.style.color= "blue"
        }
    },{threshold : 1})

    if(elementRef.current) {
        observer.observe(elementRef.current);
    }

  },[])
  return (
    <React.Fragment>
      (
      {dataSource.map((el, index) => {
        if (index === 9) {
          return (
            <div key={index} ref={elementRef} className="card">
              {el}
            </div>
          );
        }
        return <div key={index} className="card">{el}</div>;
      })}
    </React.Fragment>
  );
}

export default IntersectionObserverComponent;
