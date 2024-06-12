import { Route } from "react-router-dom";
import Loader from "./Loader/Loader";
import DarkMode from "./DarkMode/DarkMode";
import TopBar from "./TopBar/TopBar";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import React, { useState } from "react";
export const UserTemplate = (props) => {
  const { Component, ...restProps } = props;
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  return (
    <Route
      {...restProps}
      render={(propsRoute) => {
        return (
          <div className="body__wrapper">
            <main class="main_wrapper overflow-hidden">
              {/* <TopBar />              */}
              <Header isLoggedIn={isLoggedIn} />
              <Component {...propsRoute} />
              <Footer />
            </main>
          </div>
        );
      }}
    />
  );
};
