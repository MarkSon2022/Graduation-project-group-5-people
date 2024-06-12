import React, { Fragment } from "react";
import { Redirect, Route } from "react-router-dom/cjs/react-router-dom.min";

import { useSelector } from "react-redux";
import "../../styles/AdminPage.module.css";
import Dashboardarea from "../../pages/AdminPage/Dashboardarea";
import Sidebar from "../../components/SideBar";

export const AdminTemplate = (props) => {
  const { Component, ...restProps } = props;
  const { user } = useSelector((root) => root.AuthReducer);
  console.log(user);
  return (
    <Route
      {...restProps}
      render={(propsRoute) => {
        if (user.role === 1) {
          return (
            <Fragment>
              <Dashboardarea />
              <div className=" row pt-5">
                <div className="col-xl-2 col-lg-2 col-md-12">
                  <Sidebar />
                </div>
                <div className="col-xl-10 col-lg-10 col-md-12">
                  <Component {...propsRoute} />
                </div>
              </div>
            </Fragment>
          );
        }
        alert("Không có quyền truy cập");
      }}
    />
  );
};
