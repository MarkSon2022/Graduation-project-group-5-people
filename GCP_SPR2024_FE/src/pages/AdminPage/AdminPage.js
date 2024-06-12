import React, { Fragment } from "react";
import { useSelector } from "react-redux";
import style from "../../styles/AdminPage.module.css";

import AdminTableUser from "../../components/AdminTable/AdminTableUser.js";
import { history } from "../../App.js";
import AdminTableManager from "../../components/AdminTable/AdminTableManager.js";

export default function AdminPage() {
  const { admin } = useSelector((root) => root.AuthReducer);
  console.log(admin);
  return (
    <Fragment>
      <AdminTableUser/>
    </Fragment>
  );
}
